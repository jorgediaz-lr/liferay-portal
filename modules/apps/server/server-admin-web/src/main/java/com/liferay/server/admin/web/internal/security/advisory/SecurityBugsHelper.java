/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.server.admin.web.internal.security.advisory;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.patcher.PatcherUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Díaz
 */
@Component(immediate = true, service = SecurityBugsHelper.class)
public class SecurityBugsHelper {

	public List<Issue> getJiraIssues(String query, int buildNumber)
		throws IOException, PortalException {

		String encodedQuery = URLCodec.encodeURL(query);

		int delta = 0;

		List<Issue> issues = new ArrayList<>();

		while (true) {
			String jiraSearchApi = StringBundler.concat(
				JIRA_URL, CharPool.QUESTION, JIRA_DELTA_PARAM, delta,
				JIRA_FIELDS_PARAM, Issue.JQL_FIELDS, JIRA_JQL_PARAM,
				encodedQuery);

			Http.Options options = new Http.Options();

			Map<String, String> headers = HashMapBuilder.put(
				"Content-Type", "application/json"
			).build();

			options.setHeaders(headers);

			options.setLocation(jiraSearchApi);
			options.setPost(false);
			options.setTimeout(30000);

			byte[] bytes = http.URLtoByteArray(options);

			Http.Response response = options.getResponse();

			int responseCode = response.getResponseCode();

			String responseJSON = new String(bytes);

			if (_log.isDebugEnabled()) {
				_log.debug("responseCode: " + responseCode);
				_log.debug("responseJSON: " + responseJSON);
			}

			Map<String, Object> responseMap = null;

			String errorMessage = null;

			try {
				responseMap = (Map<String, Object>)jsonFactory.looseDeserialize(
					responseJSON);

				errorMessage = StringUtil.merge(
					(List)responseMap.get("errorMessages"),
					StringPool.COMMA_AND_SPACE);
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(exception, exception);
				}
			}

			if ((responseCode == 400) && (errorMessage != null) &&
				errorMessage.contains(
					"does not exist for the field 'fixVersion'")) {

				return Collections.emptyList();
			}

			if (responseCode != 200) {
				if (errorMessage == null) {
					errorMessage = responseJSON;
				}

				throw new PortalException(
					StringBundler.concat(
						jiraSearchApi, " returned error code ", responseCode,
						" - ", errorMessage));
			}

			List<Map<String, Object>> responseIssues =
				(List<Map<String, Object>>)responseMap.get("issues");

			for (Map<String, Object> responseIssue : responseIssues) {
				Issue issue = new Issue(responseIssue, buildNumber);

				issues.add(issue);
			}

			int issuesNumber = responseIssues.size();

			if (issuesNumber == 0) {
				return issues;
			}

			delta += issuesNumber;
		}
	}

	public List<Issue> getSev1Issues() {
		return sev1Issues;
	}

	public List<Issue> getSev2Issues() {
		return sev2Issues;
	}

	public List<Issue> getSev3Issues() {
		return sev3Issues;
	}

	public void writeLogTraces() {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			LocaleUtil.getDefault(), SecurityBugsHelper.class);

		if (ListUtil.isNotEmpty(sev1Issues)) {
			_log.error(
				language.format(
					resourceBundle, "security-vulnerabilities-not-fixed-x-x",
					new String[] {"SEV-1", getIssueKeys(sev1Issues)}));
			_log.error(
				language.format(
					resourceBundle,
					"security-vulnerabilities-update-to-fixpack-x",
					getGreatestFixpackNumber(sev1Issues)));
		}

		if (ListUtil.isNotEmpty(sev2Issues)) {
			_log.error(
				language.format(
					resourceBundle, "security-vulnerabilities-not-fixed-x-x",
					new String[] {"SEV-2", getIssueKeys(sev2Issues)}));
			_log.error(
				language.format(
					resourceBundle,
					"security-vulnerabilities-update-to-fixpack-x",
					getGreatestFixpackNumber(sev2Issues)));
		}

		if (_log.isWarnEnabled() && ListUtil.isEmpty(sev1Issues) &&
			ListUtil.isEmpty(sev2Issues) && !ListUtil.isEmpty(sev3Issues)) {

			_log.warn(
				language.format(
					resourceBundle, "security-vulnerabilities-not-fixed-x-x",
					new String[] {"SEV-3", getIssueKeys(sev3Issues)}));
			_log.warn(
				language.format(
					resourceBundle,
					"security-vulnerabilities-update-to-fixpack-x",
					getGreatestFixpackNumber(sev3Issues)));
		}
	}

	public class Issue {

		public static final String JQL_FIELDS = "labels,summary,components";

		public Issue(Map<String, Object> issueMap, int buildNumber) {
			key = (String)issueMap.get("key");

			Map<String, Object> fields = (Map<String, Object>)issueMap.get(
				"fields");

			summary = (String)fields.get("summary");

			List<Map<String, Object>> componentsJira =
				(List<Map<String, Object>>)fields.get("components");

			Stream<Map<String, Object>> componentsJiraStream =
				componentsJira.stream();

			components = componentsJiraStream.map(
				componentJira -> (String)componentJira.get("name")
			).filter(
				name -> !name.equals("Security Vulnerability")
			).collect(
				Collectors.toList()
			);

			labels = (List<String>)fields.get("labels");

			for (String label : labels) {
				try {
					if (label.startsWith("lsv-")) {
						lsv = Integer.valueOf(label.replaceFirst("lsv-", ""));

						continue;
					}
					else if (label.startsWith("sev-")) {
						sev = Integer.valueOf(label.replaceFirst("sev-", ""));

						continue;
					}

					int labelFixpackNumber = getLabelFixpackNumber(
						label, buildNumber);

					if (fixpack < labelFixpackNumber) {
						fixpack = labelFixpackNumber;
					}
				}
				catch (Exception exception) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							StringBundler.concat(
								exception, " processing label ", label,
								" in issue ", key));
					}
				}
			}
		}

		public int getFixpack() {
			return fixpack;
		}

		public String getKey() {
			return key;
		}

		public int getLsv() {
			return lsv;
		}

		public int getSev() {
			return sev;
		}

		public String getSummary() {
			return summary;
		}

		public String toString() {
			if (summary.startsWith("LSV-" + lsv) || (lsv == 0) ||
				((sev != 1) && (sev != 2))) {

				return key + " - " + summary;
			}

			return StringBundler.concat(key, " - LSV:", lsv, ": ", summary);
		}

		protected List<String> components;
		protected int fixpack;
		protected String key;
		protected List<String> labels;
		protected int lsv;
		protected int sev;
		protected String summary;

	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		initialize();

		writeLogTraces();
	}

	protected List<Issue> filterInstalledIssues(
		List<Issue> issues, String[] fixedIssues, int fixpackLevel) {

		Stream<Issue> issuesStream = issues.stream();

		return issuesStream.filter(
			issue -> !ArrayUtil.contains(fixedIssues, issue.getKey())
		).filter(
			issue -> issue.getFixpack() > fixpackLevel
		).collect(
			Collectors.toList()
		);
	}

	protected int getGreatestFixpackNumber(List<Issue> issues) {
		Stream<Issue> issuesStream = issues.stream();

		return issuesStream.mapToInt(
			issue -> issue.getFixpack()
		).max(
		).orElse(
			0
		);
	}

	protected String getIssueKeys(List<Issue> issues) {
		Stream<Issue> issuesStream = issues.stream();

		return issuesStream.map(
			issue -> issue.getKey()
		).collect(
			Collectors.joining(StringPool.COMMA_AND_SPACE)
		);
	}

	protected List<Issue> getJiraSecurityIssues(int buildNumber, int severity)
		throws IOException, PortalException {

		int majorVersion = buildNumber / 1000;
		int minorVersion = (buildNumber % 1000) / 100;

		String query = StringBundler.concat(
			"project = LPE AND status = Closed AND resolution = Fixed AND ",
			"labels = lsv AND fixVersion = \"", majorVersion, ".", minorVersion,
			".X EE\" AND labels = sev-", severity, " ORDER BY key DESC");

		if (_log.isDebugEnabled()) {
			_log.debug("JIRA query: " + query);
		}

		return getJiraIssues(query, buildNumber);
	}

	protected int getLabelFixpackNumber(String fixpackLabel, int buildNumber) {
		fixpackLabel = fixpackLabel.replaceFirst("liferay-fixpack-", "");

		String[] fixpackLabelArray = fixpackLabel.split("\\-");

		if (fixpackLabelArray.length < 3) {
			return 0;
		}

		String fixpackPrefix = fixpackLabelArray[0];

		if (!fixpackPrefix.equals("portal") && !fixpackPrefix.equals("de") &&
			!fixpackPrefix.equals("dxp")) {

			return 0;
		}

		try {
			int fixpackBuildNumber = Integer.valueOf(fixpackLabelArray[2]);

			if (fixpackBuildNumber == buildNumber) {
				return Integer.valueOf(fixpackLabelArray[1]);
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		return 0;
	}

	protected void initialize() {
		int buildNumber = ReleaseInfo.getBuildNumber();

		if ((buildNumber % 100) < 10) {
			if (_log.isInfoEnabled()) {
				_log.info("BuildNumber: " + buildNumber);
				_log.info("This Liferay installation cannot be patched");
			}

			sev1Issues = null;
			sev2Issues = null;
			sev3Issues = null;

			return;
		}

		int installedFixpackLevel = 0;

		for (String installedPatch : PatcherUtil.getInstalledPatches()) {
			installedFixpackLevel = getLabelFixpackNumber(
				installedPatch, buildNumber);

			if (installedFixpackLevel != 0) {
				break;
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("installed fixpack level: " + installedFixpackLevel);
		}

		initialize(buildNumber, installedFixpackLevel);
	}

	protected void initialize(int buildNumber, int installedFixpackLevel) {
		try {
			sev1Issues = filterInstalledIssues(
				getJiraSecurityIssues(buildNumber, 1),
				PatcherUtil.getFixedIssues(), installedFixpackLevel);
			sev2Issues = filterInstalledIssues(
				getJiraSecurityIssues(buildNumber, 2),
				PatcherUtil.getFixedIssues(), installedFixpackLevel);
			sev3Issues = filterInstalledIssues(
				getJiraSecurityIssues(buildNumber, 3),
				PatcherUtil.getFixedIssues(), installedFixpackLevel);
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Cannot connect to jira server: " +
						ioException.getMessage());
			}

			sev1Issues = null;
			sev2Issues = null;
			sev3Issues = null;
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException, portalException);
			}

			sev1Issues = null;
			sev2Issues = null;
			sev3Issues = null;
		}
	}

	protected static final String JIRA_DELTA_PARAM = "startAt=";

	protected static final String JIRA_FIELDS_PARAM = "&fields=";

	protected static final String JIRA_JQL_PARAM = "&jql=";

	protected static final String JIRA_URL =
		"https://issues.liferay.com/rest/api/2/search";

	@Reference
	protected Http http;

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected Language language;

	protected List<Issue> sev1Issues;
	protected List<Issue> sev2Issues;
	protected List<Issue> sev3Issues;

	private static final Log _log = LogFactoryUtil.getLog(
		SecurityBugsHelper.class);

}
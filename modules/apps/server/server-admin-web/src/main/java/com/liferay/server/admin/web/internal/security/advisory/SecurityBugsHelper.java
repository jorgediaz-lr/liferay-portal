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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.patcher.PatcherUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

	public void dumpSecurityIssuesInfo(
		PrintWriter out, int lowerSeverity, int buildNumber,
		int installedFixpack) {

		if ((buildNumber % 100) < 10) {
			out.println("BuildNumber: " + buildNumber);
			out.println("This Liferay installation cannot be patched");

			return;
		}

		try {
			for (int i = 1; i <= lowerSeverity; i++) {
				out.println("=== SEV-" + i + "===");
				List<Issue> issues = filterInstalledIssues(
					getJiraSecurityIssues(buildNumber, i), new String[0],
					installedFixpack);

				for (Issue issue : issues) {
					out.println(issue);
				}

				out.println("");
			}
		}
		catch (Exception exception) {
			exception.printStackTrace(out);
		}
	}

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

	public void initialization() {
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

		initialization(buildNumber, installedFixpackLevel);
	}

	public void initialization(int buildNumber, int installedFixpackLevel) {
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

	public void writeLogTraces() {
		if (ListUtil.isNotEmpty(sev1Issues)) {
			_log.error(
				"There are SEV-1 security vulnerabilities not fixed in the " +
					"system: " + getIssueKeys(sev1Issues));
			_log.error(
				StringBundler.concat(
					"Please, update the patch level of your installation to ",
					"the fixpack ", getGreatestFixpackNumber(sev1Issues), " ",
					"or a greater one. For more information, go to ",
					"https://help.liferay.com"));
		}

		if (ListUtil.isNotEmpty(sev2Issues)) {
			_log.error(
				"There are SEV-2 security vulnerabilities not fixed in the " +
					"system: " + getIssueKeys(sev2Issues));
			_log.error(
				StringBundler.concat(
					"Please, update the patch level of your installation to ",
					"the fixpack ", getGreatestFixpackNumber(sev2Issues), " ",
					"or a greater one. For more information, go to ",
					"https://help.liferay.com"));
		}

		if (_log.isWarnEnabled() && ListUtil.isEmpty(sev1Issues) &&
			ListUtil.isEmpty(sev2Issues) && !ListUtil.isEmpty(sev3Issues)) {

			_log.warn(
				"There are SEV-3 security vulnerabilities not fixed in the " +
					"system: " + getIssueKeys(sev3Issues));
			_log.warn(
				StringBundler.concat(
					"Please, update the patch level of your installation to ",
					"the fixpack ", getGreatestFixpackNumber(sev3Issues), " ",
					"or a greater one. For more information, go to ",
					"https://help.liferay.com"));
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
		initialization();

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

	protected static final String JIRA_DELTA_PARAM = "startAt=";

	protected static final String JIRA_FIELDS_PARAM = "&fields=";

	protected static final String JIRA_JQL_PARAM = "&jql=";

	protected static final String JIRA_URL =
		"https://issues.liferay.com/rest/api/2/search";

	@Reference
	protected Http http;

	@Reference
	protected JSONFactory jsonFactory;

	protected List<Issue> sev1Issues;
	protected List<Issue> sev2Issues;
	protected List<Issue> sev3Issues;

	private static final Log _log = LogFactoryUtil.getLog(
		SecurityBugsHelper.class);

}
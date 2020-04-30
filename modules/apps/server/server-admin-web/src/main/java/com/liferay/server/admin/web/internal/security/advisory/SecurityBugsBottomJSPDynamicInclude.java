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

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.servlet.taglib.BaseJSPDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Díaz
 */
@Component(immediate = true, service = DynamicInclude.class)
public class SecurityBugsBottomJSPDynamicInclude extends BaseJSPDynamicInclude {

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isOmniadmin()) {
			return;
		}

		HttpSession httpSession = httpServletRequest.getSession();

		if (GetterUtil.getBoolean(
				httpSession.getAttribute("securityBugsAdvisory"))) {

			return;
		}

		httpSession.setAttribute("securityBugsAdvisory", Boolean.TRUE);

		List<SecurityBugsHelper.Issue> issues = null;
		String securityBugsLevel = null;
		String securityBugsType = null;

		if (ListUtil.isNotEmpty(_securityBugsHelper.getSev1Issues())) {
			issues = _securityBugsHelper.getSev1Issues();
			securityBugsLevel = "SEV-1";
			securityBugsType = "danger";
		}
		else if (ListUtil.isNotEmpty(_securityBugsHelper.getSev2Issues())) {
			issues = _securityBugsHelper.getSev2Issues();
			securityBugsLevel = "SEV-2";
			securityBugsType = "danger";
		}
		else if (ListUtil.isNotEmpty(_securityBugsHelper.getSev3Issues())) {
			issues = _securityBugsHelper.getSev3Issues();
			securityBugsLevel = "SEV-3";
			securityBugsType = "warning";
		}

		if (issues == null) {
			return;
		}

		Stream<SecurityBugsHelper.Issue> issuesStream = issues.stream();

		String securityBugsIssues = issuesStream.map(
			issue -> issue.getKey()
		).map(
			issueKey -> StringBundler.concat(
				"<a href='https://issues.liferay.com/browse/", issueKey, "' >",
				issueKey, "</a>")
		).collect(
			Collectors.joining(StringPool.COMMA_AND_SPACE)
		);

		issuesStream = issues.stream();

		int securityBugsNewFixpack = issuesStream.mapToInt(
			issue -> issue.getFixpack()
		).max(
		).orElse(
			0
		);

		SessionMessages.add(
			httpServletRequest, "securityBugsIssues", securityBugsIssues);
		SessionMessages.add(
			httpServletRequest, "securityBugsLevel", securityBugsLevel);
		SessionMessages.add(
			httpServletRequest, "securityBugsType", securityBugsType);
		SessionMessages.add(
			httpServletRequest, "securityBugsNewFixpack",
			securityBugsNewFixpack);

		super.include(httpServletRequest, httpServletResponse, key);
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register("/html/common/themes/bottom.jsp#pre");
	}

	@Override
	protected String getJspPath() {
		return "/dynamic_include/bottom.jsp";
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.server.admin.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SecurityBugsBottomJSPDynamicInclude.class);

	@Reference
	private SecurityBugsHelper _securityBugsHelper;

}
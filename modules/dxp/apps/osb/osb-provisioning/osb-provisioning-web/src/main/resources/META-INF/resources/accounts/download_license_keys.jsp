<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");

DownloadLicenseKeysDisplayContext downloadLicenseKeysDisplayContext = new DownloadLicenseKeysDisplayContext(renderRequest, renderResponse);
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= LanguageUtil.get(request, "download-licenses") %>'
	/>

	<div id="downloadLicenses">
		<react:component
			data="<%= downloadLicenseKeysDisplayContext.getDownloadLicenseKeysData() %>"
			module="js/DownloadLicensesApp"
		/>
	</div>
</div>
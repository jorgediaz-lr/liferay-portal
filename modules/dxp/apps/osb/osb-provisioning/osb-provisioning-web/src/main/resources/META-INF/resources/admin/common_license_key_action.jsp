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

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommonLicenseKey commonLicenseKey = (CommonLicenseKey)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-portlet:resourceURL id="/admin/download_common_license_key" var="downloadURL">
		<portlet:param name="commonLicenseKeyId" value="<%= String.valueOf(commonLicenseKey.getCommonLicenseKeyId()) %>" />
	</liferay-portlet:resourceURL>

	<liferay-ui:icon
		message="download"
		url="<%= downloadURL %>"
	/>

	<portlet:actionURL name="/admin/delete_common_license_key" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="commonLicenseKeyId" value="<%= String.valueOf(commonLicenseKey.getCommonLicenseKeyId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-delete-this-common-license-key"
		message="delete"
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>
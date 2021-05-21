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

ContactDisplay contactDisplay = (ContactDisplay)row.getObject();

ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

TeamDisplay teamDisplay = viewTeamDisplayContext.getTeamDisplay();
%>

<liferay-ui:icon-menu
	direction="right"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
>
	<c:if test="<%= !teamDisplay.isSystem() && viewTeamDisplayContext.hasManageAccountsPermission() %>">
		<portlet:actionURL name="/accounts/edit_team" var="unassignURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
			<portlet:param name="deleteEmailAddresses" value="<%= contactDisplay.getEmailAddress() %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-unassign-this-contact"
			label="<%= false %>"
			message="unassign"
			showIcon="<%= true %>"
			url="<%= unassignURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>
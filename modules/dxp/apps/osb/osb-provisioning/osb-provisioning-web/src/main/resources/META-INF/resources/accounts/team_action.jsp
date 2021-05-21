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

TeamDisplay teamDisplay = (TeamDisplay)row.getObject();

ViewAccountTeamsDisplayContext viewAccountTeamsDisplayContext = ProvisioningWebComponentProvider.getViewAccountTeamsDisplayContext(renderRequest, renderResponse, request);
%>

<c:if test="<%= !teamDisplay.isSystem() && viewAccountTeamsDisplayContext.hasManageAccountsPermission() %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
	>
		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-delete-this-team"
			icon="trash"
			label="<%= false %>"
			showIcon="<%= true %>"
			url="<%= teamDisplay.getDeleteTeamURL() %>"
		/>
	</liferay-ui:icon-menu>
</c:if>
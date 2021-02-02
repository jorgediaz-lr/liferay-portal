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
ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

viewTeamDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");
%>

<liferay-util:include page="/accounts/view_team_header.jsp" servletContext="<%= application %>" />

<div class="account team" id="team">
	<div class="account-content team-details">
		<liferay-ui:tabs
			names="team-members,partner-reseller-si-accounts,first-line-support-accounts"
			portletURL="<%= viewTeamDisplayContext.getPortletURL() %>"
		/>

		<div class="details-table member-details">
			<c:choose>
				<c:when test='<%= tabs1.equals("first-line-support-accounts") %>'>
					<liferay-util:include page="/accounts/view_team_first_line_support_accounts.jsp" servletContext="<%= application %>" />
				</c:when>
				<c:when test='<%= tabs1.equals("partner-reseller-si-accounts") %>'>
					<liferay-util:include page="/accounts/view_team_partner_reseller_si_accounts.jsp" servletContext="<%= application %>" />
				</c:when>
				<c:otherwise>
					<liferay-util:include page="/accounts/view_team_members.jsp" servletContext="<%= application %>" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="side-panel" id="sidePanel">
		<react:component
			data="<%= viewTeamDisplayContext.getPanelData() %>"
			module="js/SidePanelApp"
		/>
	</div>
</div>
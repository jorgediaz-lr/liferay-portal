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

ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewTeamDisplayContext.getAccountDisplay();

Team team = viewTeamDisplayContext.getTeam();
%>

<div class="add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= (team != null) ? "edit-team" : "new-team" %>'
	/>

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<portlet:actionURL name="/accounts/edit_team" var="editTeamURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/edit_team" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		<portlet:param name="teamKey" value='<%= (team != null) ? team.getKey() : "" %>' />
	</portlet:actionURL>

	<aui:form action="<%= editTeamURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="editTeamFm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input inlineLabel="left" name="name" required="<%= true %>" value='<%= (team != null) ? team.getName() : "" %>' />

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>
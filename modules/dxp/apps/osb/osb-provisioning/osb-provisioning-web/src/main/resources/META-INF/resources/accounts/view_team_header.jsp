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
ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

TeamDisplay teamDisplay = viewTeamDisplayContext.getTeamDisplay();
%>

<div class="autofit-row provisioning-accounts team-header">
	<svg class="autofit-col header-icon">
		<use xlink:href="#team-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="team-name">
			<%= HtmlUtil.escape(teamDisplay.getName()) %>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="created" />
				</div>

				<%= teamDisplay.getDateCreated() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="last-modified" />
				</div>

				<%= teamDisplay.getDateModified() %>
			</li>
		</ul>
	</div>

	<div class="autofit-col">
		<c:if test="<%= !teamDisplay.isSystem() %>">
			<portlet:actionURL name="/accounts/edit_team" var="editTeamURL" />

			<portlet:renderURL var="editTeamNameURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/edit_team" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
			</portlet:renderURL>

			<aui:form action="<%= editTeamURL %>" method="post" name="editTeamFm">
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="teamKey" type="hidden" value="<%= teamDisplay.getKey() %>" />
				<aui:input name="addEmailAddresses" type="hidden" />

				<aui:button-row>

					<%
					String taglibOnClick = "if (confirm('" + LanguageUtil.get(request, "are-you-sure-you-want-to-delete-this-team") + "')) {submitForm(document.hrefFm, '" + teamDisplay.getDeleteTeamURL() + "');}";
					%>

					<aui:button cssClass="btn-secondary" href="<%= editTeamNameURL %>" value="edit" />

					<aui:button cssClass="btn-secondary" onClick="<%= taglibOnClick %>" value="delete" />
				</aui:button-row>
			</aui:form>
		</c:if>
	</div>
</div>
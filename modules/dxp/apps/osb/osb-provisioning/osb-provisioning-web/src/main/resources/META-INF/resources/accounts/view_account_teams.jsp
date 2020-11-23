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
ViewAccountTeamsDisplayContext viewAccountTeamsDisplayContext = ProvisioningWebComponentProvider.getViewAccountTeamsDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<liferay-ui:search-container
		id="teams"
		searchContainer="<%= viewAccountTeamsDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountTeamsDisplayContext.getClearResultsURL() %>"
			creationMenu="<%= viewAccountTeamsDisplayContext.getCreationMenu() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountTeamsDisplayContext.getCurrentURL() %>"
			searchContainerId="teams"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay"
			modelVar="teamDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_team" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-users"
			>
				<%= HtmlUtil.escape(teamDisplay.getName()) %>

				<div class="secondary-information">

					<%
					List<String> contactNames = teamDisplay.getContactNames();

					for (int i = 0; i < contactNames.size(); i++) {
						String contactName = contactNames.get(i);
					%>

						<%= HtmlUtil.escape(contactName) %><%= ((i + 1) < contactNames.size()) ? ", " : "" %>

					<%
					}
					%>

				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="last-modified"
			>
				<%= teamDisplay.getDateModified() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/accounts/team_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>
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
ViewContactDisplayContext viewContactDisplayContext = ProvisioningWebComponentProvider.getViewContactDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<h3 class="panel-title">
		<liferay-ui:message key="contact" />
	</h3>

	<liferay-ui:search-container
		id="contacts"
		searchContainer='<%= viewContactDisplayContext.getContactAccountsSearchContainer("customer") %>'
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			modelVar="accountDisplay"
		>
			<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.ACCOUNTS %>" var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="account-name-code"
			>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(accountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="region"
			>
				<%= accountDisplay.getRegion() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="roles"
			>
				<%= StringUtil.merge(viewContactDisplayContext.getCustomerContactRoleNames(accountDisplay.getKey()), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="teams"
			>

				<%
				List<TeamDisplay> teamDisplays = viewContactDisplayContext.getContactAccountTeamDisplays(accountDisplay.getKey());

				for (int i = 0; i < teamDisplays.size(); i++) {
					TeamDisplay teamDisplay = teamDisplays.get(i);
				%>

					<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.ACCOUNTS %>" var="teamURL">
						<portlet:param name="mvcRenderCommandName" value="/accounts/view_team" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
					</liferay-portlet:renderURL>

					<a href="<%= teamURL %>"><%= HtmlUtil.escape(teamDisplay.getName()) %></a>

					<%= ((i + 1) < teamDisplays.size()) ? "<br />" : "" %>

				<%
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-state"
			>
				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<div class="details-table table-striped">
	<h3 class="panel-title">
		<liferay-ui:message key="liferay-workers" />
	</h3>

	<liferay-ui:search-container
		id="contacts"
		searchContainer='<%= viewContactDisplayContext.getContactAccountsSearchContainer("worker") %>'
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			modelVar="accountDisplay"
		>
			<liferay-portlet:renderURL portletName="<%= ProvisioningPortletKeys.ACCOUNTS %>" var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="account-name-code"
			>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(accountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="region"
			>
				<%= accountDisplay.getRegion() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="roles"
			>
				<%= StringUtil.merge(viewContactDisplayContext.getWorkerContactRoleNames(accountDisplay.getKey()), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-state"
			>
				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>
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
AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);
%>

<div class="container-fluid home provisioning-accounts">
	<div class="advanced-search-alert">
		<div class="alert alert-info" role="alert">
			<div class="alert-autofit-row autofit-row">
				<div>
					<liferay-ui:message arguments='<%= new String[] {"<a href='https://reports.liferay.com/#/site/support/workbooks/810/views' target='_blank'>", "</a>"} %>' key="advanced-search-is-temporarily-provided-by-the-cas-monitoring-report" translateArguments="<%= false %>" />
				</div>
			</div>
		</div>
	</div>

	<portlet:renderURL var="addAccountURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/add_account" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<div class="title-bar">
		<h3><liferay-ui:message key="accounts" /></h3>

		<a aria-label="<%= LanguageUtil.get(request, "new-account") %>" class="btn btn-primary nav-btn nav-btn-monospaced" href="<%= addAccountURL %>" title="<%= LanguageUtil.get(request, "new-account") %>">
			<svg class="lexicon-icon lexicon-icon-plus" focusable="false" role="presentation">
				<use xlink:href="#plus" />
			</svg>
		</a>
	</div>

	<clay:management-toolbar
		displayContext="<%= new ViewAccountsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, accountSearchDisplayContext.getSearchContainer()) %>"
		elementClasses="full-width"
		showSearch="<%= false %>"
	/>

	<liferay-ui:search-container
		cssClass="table-hover"
		searchContainer="<%= accountSearchDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			keyProperty="accountKey"
			modelVar="accountDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-code"
			>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(accountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
				value="<%= accountDisplay.getSupportEndDate() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="partner"
				value="<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region"
				value="<%= accountDisplay.getRegion() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="sla-tier"
			>
				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>

				<div class="secondary-information">
					<%= accountDisplay.getTier() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="state"
			>
				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>
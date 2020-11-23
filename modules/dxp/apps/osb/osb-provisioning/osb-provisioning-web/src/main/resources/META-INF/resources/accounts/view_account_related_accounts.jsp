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
ViewAccountRelatedAccountsDisplayContext viewAccountRelatedAccountsDisplayContext = ProvisioningWebComponentProvider.getViewAccountRelatedAccountsDisplayContext(renderRequest, renderResponse, request);

PortletURL portletURL = viewAccountRelatedAccountsDisplayContext.getPortletURL();
%>

<div class="details-table table-striped">
	<liferay-util:include page="/common/tabs.jsp" servletContext="<%= application %>">
		<liferay-util:param name="names" value="<%= viewAccountRelatedAccountsDisplayContext.getTabsNames() %>" />
		<liferay-util:param name="param" value="tabs2" />
		<liferay-util:param name="url" value="<%= portletURL.toString() %>" />
		<liferay-util:param name="values" value="<%= viewAccountRelatedAccountsDisplayContext.getTabsValues() %>" />
	</liferay-util:include>

	<liferay-ui:search-container
		id="related-accounts"
		searchContainer="<%= viewAccountRelatedAccountsDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountRelatedAccountsDisplayContext.getClearResultsURL() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountRelatedAccountsDisplayContext.getCurrentURL() %>"
			searchContainerId="related-accounts"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			keyProperty="accountKey"
			modelVar="curAccountDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= curAccountDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-code"
			>
				<%= HtmlUtil.escape(curAccountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(curAccountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
				value="<%= curAccountDisplay.getSupportEndDate() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="partner"
				value="<%= HtmlUtil.escape(curAccountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region"
				value="<%= curAccountDisplay.getRegion() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="sla-tier"
			>
				<%= HtmlUtil.escape(curAccountDisplay.getSLAName()) %>

				<div class="secondary-information">
					<%= curAccountDisplay.getTier() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="state"
			>
				<span class="label <%= curAccountDisplay.getSubscriptionStateStyle() %>"><%= curAccountDisplay.getSubscriptionState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
			resultRowSplitter="<%= viewAccountRelatedAccountsDisplayContext.getAccountResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</div>
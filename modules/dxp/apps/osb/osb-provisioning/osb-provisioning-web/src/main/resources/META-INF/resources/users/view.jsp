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
ContactSearchDisplayContext contactSearchDisplayContext = ProvisioningWebComponentProvider.getContactSearchDisplayContext(renderRequest, renderResponse, request);
%>

<div class="title-bar">
	<h3><liferay-ui:message key="users" /></h3>
</div>

<div class="container-fluid home">
	<liferay-ui:search-container
		cssClass="table-hover"
		searchContainer="<%= contactSearchDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= contactSearchDisplayContext.getClearResultsURL() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= contactSearchDisplayContext.getCurrentURL() %>"
			searchContainerId="contacts"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
			keyProperty="contactKey"
			modelVar="contactDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/users/view_contact" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="contactEmailAddress" value="<%= contactDisplay.getEmailAddress() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-email"
			>
				<%= HtmlUtil.escape(contactDisplay.getFullName()) %>

				<div class="secondary-information">
					<%= contactDisplay.getEmailAddress() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="entitlements"
				value="<%= contactDisplay.getEntitlements() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="no.-of-accounts"
				value="<%= contactDisplay.getAccountsCount() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label <%= contactDisplay.getStatusStyle() %>"><%= contactDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>
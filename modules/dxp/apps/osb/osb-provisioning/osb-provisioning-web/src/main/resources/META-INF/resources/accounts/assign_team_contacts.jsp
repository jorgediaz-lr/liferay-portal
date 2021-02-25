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
AssignTeamContactsDisplayContext assignTeamContactsDisplayContext = ProvisioningWebComponentProvider.getAssignTeamContactsDisplayContext(renderRequest, renderResponse, request);

SearchContainer searchContainer = assignTeamContactsDisplayContext.getSearchContainer();
%>

<div class="container-fluid container-fluid-max-xl">
	<clay:management-toolbar
		clearResultsURL="<%= assignTeamContactsDisplayContext.getClearResultsURL() %>"
		filterDropdownItems="<%= assignTeamContactsDisplayContext.getFilterCustomerRoleDropdownItems() %>"
		filterLabelItems="<%= assignTeamContactsDisplayContext.getFilterCustomerRoleLabelItems() %>"
		itemsTotal="<%= searchContainer.getTotal() %>"
		searchActionURL="<%= assignTeamContactsDisplayContext.getCurrentURL() %>"
		searchContainerId="assignContacts"
		searchFormName="searchFm"
		showSearch="<%= true %>"
	/>

	<liferay-ui:search-container
		id="assignContacts"
		searchContainer="<%= searchContainer %>"
		var="contactsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
			keyProperty="emailAddress"
			modelVar="contactDisplay"
		>
			<liferay-ui:search-container-column-text
				name="name-email"
			>
				<%= HtmlUtil.escape(contactDisplay.getFullName()) %>

				<div class="secondary-information">
					<%= contactDisplay.getEmailAddress() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<%= StringUtil.merge(contactDisplay.getContactRoleNames(), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
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

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />assignContacts'
	);

	searchContainer.on('rowToggled', function(event) {
		var selectedItems = event.elements.allSelectedElements;

		var data = '';

		if (selectedItems && selectedItems.size()) {
			data = selectedItems.attr('value').join(',');
		}

		Liferay.Util.getOpener().Liferay.fire(
			'<portlet:namespace />assignContacts',
			{
				data: data
			}
		);
	});
</aui:script>
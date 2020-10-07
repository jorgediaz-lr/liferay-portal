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
String parentAccountKey = ParamUtil.getString(request, "parentAccountKey");

AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);

SearchContainer accountSearchContainer = accountSearchDisplayContext.getSearchContainer();
%>

<div class="container-fluid container-fluid-max-xl">
	<clay:management-toolbar
		clearResultsURL="<%= accountSearchDisplayContext.getClearResultsURL() %>"
		elementClasses="full-width"
		itemsTotal="<%= accountSearchContainer.getTotal() %>"
		searchActionURL="<%= currentURL %>"
		searchContainerId="parentAccountContainer"
		selectable="<%= false %>"
		showSearch="<%= true %>"
	/>

	<liferay-ui:search-container
		cssClass="details-search-container"
		id="parentAccountContainer"
		searchContainer="<%= accountSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			keyProperty="accountKey"
			modelVar="accountDisplay"
		>

			<%
			Map<String, Object> accountData = new HashMap<String, Object>();

			accountData.put("key", accountDisplay.getKey());

			row.setData(accountData);

			if (parentAccountKey.equals(accountDisplay.getKey())) {
				row.setCssClass("active");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name-code"
			>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>

				<div class="secondary-information">
					<%= HtmlUtil.escape(accountDisplay.getCode()) %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-end-date"
				value="<%= accountDisplay.getSupportEndDate() %>"
			/>

			<liferay-ui:search-container-column-text
				name="partner"
				value="<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="region"
				value="<%= accountDisplay.getRegion() %>"
			/>

			<liferay-ui:search-container-column-text
				name="sla-tier"
			>
				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>

				<div class="secondary-information">
					<%= accountDisplay.getTier() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="status"
			>
				<span class="label <%= accountDisplay.getStatusStyle() %>">
					<%= accountDisplay.getStatus() %>
				</span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	var searchContainer = document.getElementById(
		'<portlet:namespace />parentAccountContainerSearchContainer'
	);

	var table = searchContainer.querySelector('tbody');

	if (table) {
		var tableRows = table.querySelectorAll('tr');

		tableRows.forEach(<portlet:namespace />selectRow);
	}

	var anchors = document.querySelectorAll('a');

	anchors.forEach(<portlet:namespace />resetAnchor);

	var searchForm = document.querySelector('form');

	if (searchForm) {
		searchForm.addEventListener('submit', <portlet:namespace />resetData);
	}

	function <portlet:namespace />resetAnchor(element) {
		if (!element.classList.contains('dropdown-toggle')) {
			element.addEventListener('click', <portlet:namespace />resetData);
		}
	}

	function <portlet:namespace />resetData() {
		Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
			data: ''
		});
	}

	function <portlet:namespace />resetRow(row) {
		row.classList.remove('active');
	}

	function <portlet:namespace />selectRow(row) {
		row.addEventListener('click', function() {
			var activeRows = Array.from(document.getElementsByClassName('active'));

			activeRows.forEach(<portlet:namespace />resetRow);

			this.classList.add('active');

			var rowData = this.dataset;

			if (rowData) {
				Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
					data: rowData.key
				});
			}
		});
	}
</aui:script>
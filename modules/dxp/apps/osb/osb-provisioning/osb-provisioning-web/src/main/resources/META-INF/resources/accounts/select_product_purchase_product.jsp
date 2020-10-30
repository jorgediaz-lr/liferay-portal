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
EditProductPurchaseDisplayContext editProductPurchaseDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchaseDisplayContext(renderRequest, renderResponse, request);

String productKey = ParamUtil.getString(renderRequest, "productKey");

SearchContainer productSearchContainer = editProductPurchaseDisplayContext.getProductPurchaseProductSearchContainer();
%>

<clay:management-toolbar
	clearResultsURL="<%= editProductPurchaseDisplayContext.getProductPurchaseProductClearResultsURL() %>"
	itemsTotal="<%= productSearchContainer.getTotal() %>"
	searchActionURL="<%= currentURL %>"
	searchContainerId="selectProduct"
	selectable="<%= false %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		id="selectProduct"
		searchContainer="<%= productSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay"
			keyProperty="key"
			modelVar="productDisplay"
		>

			<%
			Map<String, Object> productData = new HashMap<String, Object>();

			productData.put("key", productDisplay.getKey());
			productData.put("name", HtmlUtil.escape(productDisplay.getName()));

			row.setData(productData);

			if (productKey.equals(productDisplay.getKey())) {
				row.setCssClass("active");
			}
			%>

			<liferay-ui:search-container-column-text
				name="products"
				value="<%= HtmlUtil.escape(productDisplay.getName()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	function <portlet:namespace />resetActiveClass(nodes) {
		nodes.forEach(function(node) {
			node.classList.remove('active');
		});
	}

	function <portlet:namespace />resetFormData() {
		Liferay.Util.getOpener().Liferay.fire(
			'<portlet:namespace />selectProduct',
			{
				data: ''
			}
		);
	}

	var searchContainer = document.getElementById(
		'<portlet:namespace />selectProductSearchContainer'
	);

	if (searchContainer) {
		var entries = searchContainer.querySelectorAll('tbody tr');

		entries.forEach(function(entry) {
			entry.addEventListener('click', function() {
				<portlet:namespace />resetActiveClass(entries);

				entry.classList.add('active');

				var rowData = entry.dataset;

				if (rowData) {
					Liferay.Util.getOpener().Liferay.fire(
						'<portlet:namespace />selectProduct',
						{
							data: rowData
						}
					);
				}
			});
		});
	}

	var paginationPages = document.querySelectorAll('.pagination-bar .page-link');

	paginationPages.forEach(function(page) {
		page.addEventListener('click', <portlet:namespace />resetFormData);
	});

	var searchForm = document.querySelector('form');

	if (searchForm) {
		searchForm.addEventListener('submit', <portlet:namespace />resetFormData);
	}
</aui:script>
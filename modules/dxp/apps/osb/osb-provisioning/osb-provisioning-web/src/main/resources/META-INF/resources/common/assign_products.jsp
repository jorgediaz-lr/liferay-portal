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
AssignProductsDisplayContext assignProductsDisplayContext = ProvisioningWebComponentProvider.getAssignProductsDisplayContext(renderRequest, renderResponse, request);

String[] productKeys = ParamUtil.getStringValues(renderRequest, "productKeys");

String accountKey = ParamUtil.getString(renderRequest, "accountKey");

SearchContainer searchContainer = assignProductsDisplayContext.getSearchContainer(productKeys);
%>

<clay:management-toolbar
	clearResultsURL="<%= assignProductsDisplayContext.getClearResultsURL() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= assignProductsDisplayContext.getCurrentURL() %>"
	searchContainerId="assignProducts"
	selectable="<%= true %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		id="assignProducts"
		searchContainer="<%= searchContainer %>"
		var="productsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="Object"
			modelVar="result"
		>

			<%
			String name = StringPool.BLANK;

			if (result instanceof ProductBundle) {
				ProductBundle productBundle = (ProductBundle)result;

				name = productBundle.getName();
			}
			else {
				ProductDisplay productDisplay = (ProductDisplay)result;

				name = productDisplay.getName();
			}
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name='<%= Validator.isNotNull(accountKey) ? StringPool.BLANK : "products" %>'
				value="<%= HtmlUtil.escape(name) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			resultRowSplitter="<%= Validator.isNotNull(accountKey) ? new ProductResultRowSplitter() : null %>"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />assignProducts'
	);

	searchContainer.on('rowToggled', function(event) {
		var selectedItems = document.querySelectorAll(
			'input[name="<portlet:namespace />rowIds"][type="checkbox"]:checked'
		);

		if (selectedItems) {
			var data = Array.from(selectedItems, function(item) {
				return item.value.split('_');
			});

			Liferay.Util.getOpener().Liferay.fire(
				'<portlet:namespace />assignProducts',
				{
					data: data
				}
			);
		}
	});
</aui:script>
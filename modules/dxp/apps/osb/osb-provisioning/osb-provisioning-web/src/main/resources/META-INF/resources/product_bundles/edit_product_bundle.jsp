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
String redirect = ParamUtil.getString(request, "redirect");

ProductBundle productBundle = (ProductBundle)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE);
List<Product> products = (List<Product>)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE_PRODUCTS);

List<String> productKeys = new ArrayList<>();

if (products != null) {
	productKeys = TransformUtil.transform(products, product -> product.getKey());
}
%>

<div class="add-items provisioning-product-bundle">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= (productBundle != null) ? "edit-product-bundle" : "new-product-bundle" %>'
	/>

	<portlet:actionURL name="/product_bundles/edit_product_bundle" var="editProductBundleURL">
		<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="productBundleId" value='<%= (productBundle != null) ? String.valueOf(productBundle.getProductBundleId()) : "" %>' />
	</portlet:actionURL>

	<aui:form action="<%= editProductBundleURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input name="productKeys" type="hidden" value="<%= StringUtil.merge(productKeys) %>" />

			<liferay-ui:error exception="<%= ProductBundleNameException.MustNotBeDuplicate.class %>">

				<%
				ProductBundleNameException.MustNotBeDuplicate productBundleNameException = (ProductBundleNameException.MustNotBeDuplicate)errorException;
				%>

				<%= productBundleNameException.getMessage() %>
			</liferay-ui:error>

			<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-select-at-least-one-product" />

			<aui:input inlineLabel="left" name="name" value='<%= (productBundle != null) ? productBundle.getName() : "" %>'>
				<aui:validator name="required" />
			</aui:input>

			<div class="form-group form-inline input-text-wrapper">
				<label class="control-label"><liferay-ui:message key="products" /></label>

				<div class="field table-field">
					<div class="field-name" id="<portlet:namespace />productName">
						<c:if test="<%= products != null %>">
							<table class="table table-list">
								<thead>
									<tr>
										<th>
											<liferay-ui:message key="name" />
										</th>
										<th>
										</th>
									</tr>
								</thead>

								<tbody>

									<%
									for (Product product : products) {
									%>

										<tr>
											<td>
												<%= HtmlUtil.escape(product.getName()) %>
											</td>
											<td class="text-right" id="<%= product.getKey() %>">
												<button class="btn" onclick="<portlet:namespace />removeName(this);" type="button">
													<svg class="lexicon-icon lexicon-icon-times-circle">
														<use xlink:href="#delete-icon" />
													</svg>
												</button>
											</td>
										</tr>

									<%
									}
									%>

								</tbody>
							</table>
						</c:if>
					</div>

					<aui:button onClick='<%= renderResponse.getNamespace() + "assignProducts();" %>' value="select" />
				</div>
			</div>

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />assignProducts',
		function() {
			<portlet:renderURL var="assignProductsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/product_bundles/assign_products" />
			</portlet:renderURL>

			var url = Liferay.Util.PortletURL.createPortletURL(
				'<%= assignProductsURL.toString() %>',
				{
					productKeys: document.getElementById(
						'<portlet:namespace />productKeys'
					).value
				}
			);

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />assignProducts',
				strings: {
					add: '<liferay-ui:message key="done" />',
					cancel: '<liferay-ui:message key="cancel" />'
				},
				title: '<liferay-ui:message key="select-products" />',
				url: url.toString()
			});

			itemSelectorDialog.on('selectedItemChange', function(event) {
				var selectedItems = event.newVal;

				if (selectedItems) {
					var productKeys = [];

					var display =
						'<table class="table table-list"><thead><tr><th><liferay-ui:message key="name" /></th><th></th></tr></thead><tbody>';

					for (var i = 0; i < selectedItems.length; i++) {
						var selectItem = selectedItems[i];

						productKeys.push(selectItem[0]);

						display +=
							'<tr><td>' +
							selectItem[1] +
							'</td><td class=" text-right" id="' +
							selectItem[0] +
							'"><button type="button" class="btn" onclick="<portlet:namespace />removeName(this);"><svg class="lexicon-icon lexicon-icon-times-circle"><use xlink:href="#delete-icon" /></svg></button></td></tr>';
					}

					display += '</tbody></table>';

					A.one('#<portlet:namespace />productName').html(display);
					A.one('#<portlet:namespace />productKeys').val(
						productKeys.join(',')
					);
				}
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);

	function <portlet:namespace />removeKey(key, value) {
		return key !== value;
	}

	function <portlet:namespace />removeName(object) {
		var productKeys = document.getElementById(
			'<portlet:namespace />productKeys'
		);

		if (productKeys) {
			productKeys.value = productKeys.value
				.split(',')
				.filter(
					<portlet:namespace />removeKey.bind(
						this,
						object.parentElement.id
					)
				)
				.join(',');
		}

		if (productKeys && productKeys.value == '') {
			document.getElementById('<portlet:namespace />productName').innerHTML =
				'';
		}
		else {
			object.parentElement.parentElement.remove();
			object.parentElement.remove();
		}
	}
</aui:script>
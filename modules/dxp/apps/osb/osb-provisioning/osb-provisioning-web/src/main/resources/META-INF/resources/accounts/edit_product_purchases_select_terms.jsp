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
String tabs2 = ParamUtil.getString(request, "tabs2");

EditProductPurchasesDisplayContext editProductPurchasesDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchasesDisplayContext(renderRequest, renderResponse, request);

String redirect = editProductPurchasesDisplayContext.getRedirectURL();

AccountDisplay accountDisplay = editProductPurchasesDisplayContext.getAccountDisplay();
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= editProductPurchasesDisplayContext.getTitle() %>"
	/>

	<div class="page-steps">
		<span><liferay-ui:message key="select-subscription-terms" /></span>

		<span><liferay-ui:message key="step-1-of-2" /></span>
	</div>

	<div class="subscriptions-container">
		<div class="subscriptions">
			<portlet:actionURL name="/accounts/edit_product_purchases_select_terms" var="editProductPurchasesURL">
				<portlet:param name="tabs2" value="<%= tabs2 %>" />
			</portlet:actionURL>

			<aui:form action="<%= editProductPurchasesURL %>" method="post" name="chooseTermFm" onSubmit='<%= renderResponse.getNamespace() + "submitForm(event);" %>'>
				<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
				<aui:input name="backURL" type="hidden" value="<%= currentURL %>" />
				<aui:input name="accountKey" type="hidden" value="<%= accountDisplay.getKey() %>" />
				<aui:input name="productPurchaseKeys" type="hidden" />

				<table class="table table-autofit table-list">
					<thead>
						<tr>
							<th class="table-cell-expand-small">
								<liferay-ui:message key="products" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="subscription-term" />
							</th>
						</tr>
					</thead>

					<tbody>

						<%
						List<ProductPurchaseView> productPurchaseViews = editProductPurchasesDisplayContext.getProductPurchaseViews();

						for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
						%>

							<tr>
								<td class="table-cell-expand-small">
									<%= productPurchaseView.getProduct().getName() %>
								</td>
								<td class="table-cell-expand">
									<aui:select cssClass="account-edit-subscription" label="" name="subscriptionTerm">

										<%
										List<ProductPurchaseDisplay> productPurchaseDisplays = editProductPurchasesDisplayContext.getProductPurchaseViewDisplays(productPurchaseView);

										for (ProductPurchaseDisplay productPurchaseDisplay : productPurchaseDisplays) {
										%>

											<aui:option label="<%= productPurchaseDisplay.getSupportLife() %>" value="<%= productPurchaseDisplay.getKey() %>" />

										<%
										}
										%>

									</aui:select>
								</td>
							</tr>

						<%
						}
						%>

					</tbody>
				</table>

				<aui:button-row>
					<aui:button type="submit" value="next" />

					<aui:button href="<%= redirect %>" type="cancel" />
				</aui:button-row>
			</aui:form>
		</div>
	</div>
</div>

<aui:script>
	function <portlet:namespace />submitForm(event) {
		event.preventDefault();

		var form = document.getElementById('<portlet:namespace />chooseTermFm');

		var subscriptionTerms = form.querySelectorAll(
			'#<portlet:namespace />subscriptionTerm'
		);

		var selectedProductPurchaseKeys = Array.from(subscriptionTerms, function(
			term
		) {
			return term.value;
		}).join(',');

		var productPurchaseKeys = form.querySelector(
			'#<portlet:namespace />productPurchaseKeys'
		);

		if (productPurchaseKeys) {
			productPurchaseKeys.setAttribute('value', selectedProductPurchaseKeys);
		}

		form.submit();
	}
</aui:script>
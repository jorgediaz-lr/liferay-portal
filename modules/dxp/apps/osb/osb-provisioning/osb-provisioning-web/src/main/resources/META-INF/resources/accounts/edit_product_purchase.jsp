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

EditProductPurchaseDisplayContext editProductPurchaseDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchaseDisplayContext(renderRequest, renderResponse, request);

ProductPurchase productPurchase = editProductPurchaseDisplayContext.getProductPurchase();

ProductPurchaseDisplay productPurchaseDisplay = editProductPurchaseDisplayContext.getProductPurchaseDisplay();

String productPurchaseKey = BeanParamUtil.getString(productPurchase, request, "key");
long quantity = BeanParamUtil.getLong(productPurchase, request, "quantity", 1);
boolean perpetual = BeanParamUtil.getBoolean(productPurchase, request, "perpetual");

int sizing = 1;

if (productPurchase != null) {
	Map<String, String> properties = productPurchase.getProperties();

	if (properties != null) {
		sizing = GetterUtil.getInteger(properties.get("sizing"));
	}
}
%>

<div class="add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= (productPurchase != null) ? "edit-subscription" : "add-subscription" %>'
	/>

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= ProductPurchaseQuantityException.class %>" message="to-remove-a-subscription-change-the-status-to-cancelled-instead" />

	<div class="page-content">
		<c:if test="<%= productPurchase == null %>">
			<div class="sheet">
				<div class="sheet-header">
					<h4>
						<liferay-ui:message key="subscription" />
					</h4>

					<aui:button onClick='<%= renderResponse.getNamespace() + "selectProduct();" %>' value="select" />
				</div>

				<div class="taglib-empty-result-message" id="<portlet:namespace />emptyContent">
					<div class="taglib-empty-result-message-header"></div>

					<div class="sheet-text text-center">
						<liferay-ui:message key="select-subscription-to-fill-in-details" />
					</div>

					<div class="sheet-text text-center">
						<aui:button onClick='<%= renderResponse.getNamespace() + "selectProduct();" %>' value="select" />
					</div>
				</div>
			</div>
		</c:if>

		<portlet:actionURL name="/accounts/edit_product_purchase" var="editProductPurchaseURL">
			<portlet:param name="mvcRenderCommandName" value="/accounts/edit_product_purchase" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="productPurchaseKey" value="<%= productPurchaseKey %>" />
			<portlet:param name="accountKey" value="<%= editProductPurchaseDisplayContext.getAccountKey() %>" />
		</portlet:actionURL>

		<aui:form action="<%= editProductPurchaseURL.toString() %>" method="post" name="fm">
			<aui:input name="productKey" type="hidden" />

			<div id="<portlet:namespace />subscriptionContent" hidden>
				<table class="table table-autofit table-hover table-list">
					<thead>
						<tr>
							<th class="table-cell-expand-small">
								<liferay-ui:message key="product" />
							</th>

							<c:if test="<%= productPurchase == null %>">
								<th class="table-cell-expand-small">
									<liferay-ui:message key="salesforce-opportunity-key" />
								</th>
							</c:if>

							<th class="table-cell-expand-smallest">
								<liferay-ui:message key="purchased" />
							</th>
							<th class="table-cell-expand-smallest">
								<liferay-ui:message key="perpetual-subscription" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="start-date" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="end-date" />
							</th>
							<th class="table-cell-expand">
								<liferay-ui:message key="instance-size" />
							</th>

							<c:if test="<%= productPurchase != null %>">
								<th class="table-cell-expand">
									<liferay-ui:message key="grace-period-end-date" />
								</th>
								<th class="table-cell-expand">
									<liferay-ui:message key="status" />
								</th>
								<th class="table-cell-expand-small">
									<liferay-ui:message key="subscription-term" />
								</th>
								<th class="table-cell-expand-small">
									<liferay-ui:message key="salesforce-opportunity-key" />
								</th>
							</c:if>

							<th class="table-cell-expand">
								<liferay-ui:message key="account-name" />
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td class="table-cell-expand-small" id="<portlet:namespace />productName">
								<%= (productPurchase != null) ? HtmlUtil.escape(productPurchaseDisplay.getProductName()) : "" %>
							</td>

							<c:if test="<%= productPurchase == null %>">
								<td class="table-cell-expand-small">
									<aui:input cssClass="account-edit-subscription" label="" name="salesforceOpportunityKey">
										<aui:validator name="required" />
									</aui:input>
								</td>
							</c:if>

							<td class="table-cell-expand-smallest">
								<aui:input cssClass="account-edit-subscription" label="" name="quantity" value="<%= quantity %>" />
							</td>
							<td class="table-cell-expand-smallest">
								<aui:input checked="<%= perpetual %>" cssClass="account-edit-subscription" label="" name="perpetual" onChange='<%= renderResponse.getNamespace() + "toggleDate(event.target.checked);" %>' type="checkbox" />
							</td>

							<%
							Calendar startCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

							if ((productPurchase != null) && (productPurchase.getStartDate() != null)) {
								startCal.setTime(productPurchase.getStartDate());
							}
							%>

							<td class="table-cell-expand">
								<liferay-ui:input-date
									dayParam="startDateDay"
									dayValue="<%= startCal.get(Calendar.DATE) %>"
									disabled="<%= perpetual %>"
									monthParam="startDateMonth"
									monthValue="<%= startCal.get(Calendar.MONTH) %>"
									name="startDate"
									yearParam="startDateYear"
									yearValue="<%= startCal.get(Calendar.YEAR) %>"
								/>
							</td>

							<%
							Calendar endCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

							if ((productPurchase != null) && (productPurchase.getOriginalEndDate() != null)) {
								endCal.setTime(productPurchase.getOriginalEndDate());
							}
							else {
								endCal.add(Calendar.YEAR, 1);
							}
							%>

							<td class="table-cell-expand">
								<liferay-ui:input-date
									dayParam="endDateDay"
									dayValue="<%= endCal.get(Calendar.DATE) %>"
									disabled="<%= perpetual %>"
									monthParam="endDateMonth"
									monthValue="<%= endCal.get(Calendar.MONTH) %>"
									name="endDate"
									yearParam="endDateYear"
									yearValue="<%= endCal.get(Calendar.YEAR) %>"
								/>
							</td>
							<td class="table-cell-expand">
								<aui:select cssClass="account-edit-subscription" label="" name="sizing">

									<%
									for (int i = 1; i <= 4; i++) {
									%>

										<aui:option label="<%= i %>" selected="<%= sizing == i %>" value="<%= i %>" />

									<%
									}
									%>

								</aui:select>
							</td>

							<c:if test="<%= productPurchase != null %>">

								<%
								Calendar gracePeriodEndCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

								if (productPurchase.getEndDate() != null) {
									gracePeriodEndCal.setTime(productPurchase.getEndDate());
								}
								else {
									gracePeriodEndCal.add(Calendar.YEAR, 1);
									gracePeriodEndCal.add(Calendar.DATE, 30);
								}
								%>

								<td class="table-cell-expand">
									<liferay-ui:input-date
										dayParam="gracePeriodEndDateDay"
										dayValue="<%= gracePeriodEndCal.get(Calendar.DATE) %>"
										disabled="<%= perpetual %>"
										monthParam="gracePeriodEndDateMonth"
										monthValue="<%= gracePeriodEndCal.get(Calendar.MONTH) %>"
										name="gracePeriodEndDate"
										yearParam="gracePeriodEndDateYear"
										yearValue="<%= gracePeriodEndCal.get(Calendar.YEAR) %>"
									/>
								</td>
								<td class="table-cell-expand">
									<aui:select cssClass="account-edit-subscription" label="" name="status">

										<%
										for (ProductPurchase.Status status : ProductPurchase.Status.values()) {
										%>

											<aui:option label="<%= status.toString() %>" selected="<%= productPurchase.getStatus() == status %>" value="<%= status.toString() %>" />

										<%
										}
										%>

									</aui:select>
								</td>
								<td class="table-cell-expand-small">
									<%= productPurchaseDisplay.getSupportLife() %>
								</td>
								<td class="table-cell-expand-small">
									<%= productPurchaseDisplay.getSalesforceOpportunityKey() %>
								</td>
							</c:if>

							<td class="table-cell-expand">
								<%= HtmlUtil.escape(editProductPurchaseDisplayContext.getAccountName()) %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<aui:button-row cssClass="button-holder-lg">
				<aui:button disabled="<%= productPurchase == null %>" name="submit" type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</aui:form>
	</div>
</div>

<aui:script>
	var emptyContent = document.getElementById('<portlet:namespace />emptyContent');
	var subscriptionContent = document.getElementById(
		'<portlet:namespace />subscriptionContent'
	);

	if (!emptyContent && subscriptionContent) {
		subscriptionContent.removeAttribute('hidden');
	}

	function <portlet:namespace />toggleContent(selectedItem) {
		if (emptyContent) {
			emptyContent.setAttribute('hidden', true);
		}

		if (subscriptionContent) {
			subscriptionContent.removeAttribute('hidden');
		}

		var productName = document.getElementById(
			'<portlet:namespace />productName'
		);

		if (productName) {
			productName.innerHTML = selectedItem.name;
		}

		var productKey = document.getElementById('<portlet:namespace />productKey');

		if (productKey) {
			productKey.value = selectedItem.key;
		}

		var submit = document.getElementById('<portlet:namespace />submit');

		if (submit) {
			submit.removeAttribute('disabled');
			submit.classList.remove('disabled');
		}
	}

	function <portlet:namespace />toggleDate(checked) {
		var dateInputs = document.querySelectorAll('.lfr-input-date input');

		dateInputs.forEach(function(input) {
			if (checked) {
				input.setAttribute('disabled', checked);
			}
			else {
				input.removeAttribute('disabled');
			}
		});
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectProduct',
		function() {
			<portlet:renderURL var="selectProductURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/accounts/select_product_purchase_product" />
				<portlet:param name="accountKey" value="<%= editProductPurchaseDisplayContext.getAccountKey() %>" />
			</portlet:renderURL>

			var url = Liferay.Util.PortletURL.createPortletURL(
				'<%= selectProductURL.toString() %>',
				{
					productKey: document.getElementById(
						'<portlet:namespace />productKey'
					).value
				}
			);

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />selectProduct',
				on: {
					selectedItemChange: function(event) {
						var selectedItem = event.newVal;

						if (selectedItem) {
							<portlet:namespace />toggleContent(selectedItem);
						}
					}
				},
				strings: {
					add: '<liferay-ui:message key="done" />',
					cancel: '<liferay-ui:message key="cancel" />'
				},
				title: '<liferay-ui:message key="select-subscription" />',
				url: url.toString()
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);
</aui:script>
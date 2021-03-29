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
MoveLicenseKeyDisplayContext moveLicenseKeyDisplayContext = ProvisioningWebComponentProvider.getMoveLicenseKeyDisplayContext(renderRequest, renderResponse, request);

LicenseKey licenseKey = moveLicenseKeyDisplayContext.getLicenseKey();

String licenseProductPurchaseKey = StringPool.BLANK;

if (Validator.isNotNull(licenseKey.getProductPurchaseKey())) {
	licenseProductPurchaseKey = licenseKey.getProductPurchaseKey();
}

String detachedLicenseKeysGenerated = moveLicenseKeyDisplayContext.getDetachedLicenseKeysGenerated();

Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMM dd, yyyy");
%>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		searchContainer="<%= moveLicenseKeyDisplayContext.getSearchContainer() %>"
		var="productPurchasesSearchContainer"
	>
		<liferay-ui:search-container-row
			className="Object"
			modelVar="resultRow"
		>

			<%
			ProductPurchaseDisplay productPurchaseDisplay = null;

			if (resultRow instanceof ProductPurchaseDisplay) {
				productPurchaseDisplay = (ProductPurchaseDisplay)resultRow;
			}

			String productPurchaseKey = StringPool.BLANK;
			String sizing = StringPool.DASH;
			String licenseKeysGenerated = detachedLicenseKeysGenerated;

			Calendar startDateCal = Calendar.getInstance();
			Calendar expirationDateCal = Calendar.getInstance();

			if (productPurchaseDisplay != null) {
				productPurchaseKey = productPurchaseDisplay.getKey();
				sizing = productPurchaseDisplay.getSizing();

				licenseKeysGenerated = productPurchaseDisplay.getProvisionedCount() + " / " + productPurchaseDisplay.getQuantity();

				if (productPurchaseDisplay.getStartDate() != null) {
					startDateCal.setTime(productPurchaseDisplay.getStartDate());
				}

				if (productPurchaseDisplay.getEndDate() != null) {
					expirationDateCal.setTime(productPurchaseDisplay.getEndDate());
				}
			}
			else if (licenseKey.getSizing() > 0) {
				sizing = String.valueOf(licenseKey.getSizing());

				startDateCal.setTime(licenseKey.getStartDate());
				expirationDateCal.setTime(licenseKey.getExpirationDate());
			}
			%>

			<liferay-ui:search-container-column-text
				name="start-date"
				value="<%= dateFormat.format(startDateCal.getTime()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="expiration-date"
				value="<%= dateFormat.format(expirationDateCal.getTime()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="instance-size"
				value="<%= sizing %>"
			/>

			<liferay-ui:search-container-column-text
				name="license-keys-generated"
				value="<%= licenseKeysGenerated %>"
			>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<c:choose>
					<c:when test="<%= !productPurchaseKey.equals(licenseProductPurchaseKey) %>">

						<%
						Map<String, Object> data = new HashMap<String, Object>();

						data.put("productPurchaseKey", productPurchaseKey);
						%>

						<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="current" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
			resultRowSplitter="<%= new ProductPurchaseResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler('#<portlet:namespace />fm', 'moveLicenseKey');
</aui:script>
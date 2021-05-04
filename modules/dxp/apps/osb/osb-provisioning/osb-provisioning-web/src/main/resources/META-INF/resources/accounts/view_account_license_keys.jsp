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
ViewAccountLicenseKeysDisplayContext viewAccountLicenseKeysDisplayContext = ProvisioningWebComponentProvider.getViewAccountLicenseKeysDisplayContext(renderRequest, renderResponse, request);

PortletURL portletURL = viewAccountLicenseKeysDisplayContext.getPortletURL();
%>

<div class="details-table table-striped">
	<liferay-util:include page="/common/tabs.jsp" servletContext="<%= application %>">
		<liferay-util:param name="names" value="<%= viewAccountLicenseKeysDisplayContext.getTabsNames() %>" />
		<liferay-util:param name="param" value="tabs2" />
		<liferay-util:param name="url" value="<%= portletURL.toString() %>" />
		<liferay-util:param name="values" value="active,expired,deactivated,all" />
	</liferay-util:include>

	<aui:form action="<%= currentURL %>" name="licenseKeysFm">
		<aui:input name="licenseKeyIds" type="hidden" />

		<liferay-ui:search-container
			id="license-keys"
			searchContainer="<%= viewAccountLicenseKeysDisplayContext.getSearchContainer() %>"
		>
			<clay:management-toolbar
				actionDropdownItems="<%= viewAccountLicenseKeysDisplayContext.getActionDropdownItems() %>"
				clearResultsURL="<%= viewAccountLicenseKeysDisplayContext.getClearResultsURL() %>"
				elementClasses="full-width"
				itemsTotal="<%= searchContainer.getTotal() %>"
				searchActionURL="<%= viewAccountLicenseKeysDisplayContext.getCurrentURL() %>"
				searchContainerId="license-keys"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.web.internal.display.context.LicenseKeyDisplay"
				keyProperty="licenseKeyId"
				modelVar="licenseKeyDisplay"
			>
				<liferay-ui:search-container-column-text
					name="name-description"
				>
					<%= HtmlUtil.escape(licenseKeyDisplay.getName()) %>

					<div class="secondary-information">
						<%= HtmlUtil.escape(licenseKeyDisplay.getDescription()) %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="expiration-date"
					value="<%= licenseKeyDisplay.getExpirationDate() %>"
				/>

				<liferay-ui:search-container-column-text
					name="product"
					value="<%= HtmlUtil.escape(licenseKeyDisplay.getProductName()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="type"
					value="<%= licenseKeyDisplay.getType() %>"
				/>

				<liferay-ui:search-container-column-text
					name="host-name"
					value="<%= licenseKeyDisplay.getHostName() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />license-keys'
	);

	if (searchContainer) {
		searchContainer.on('rowToggled', function(event) {
			var licenseKeyIds = '';

			var selectedItems = event.elements.allSelectedElements;

			if (selectedItems && selectedItems.size()) {
				licenseKeyIds = selectedItems.attr('value').join(',');
			}

			var licenseKeyIdsInput = A.one('#<portlet:namespace />licenseKeyIds');

			if (licenseKeyIdsInput) {
				licenseKeyIdsInput.val(licenseKeyIds);
			}
		});
	}
</aui:script>

<aui:script>
	function <portlet:namespace />downloadLicenseKeys() {
		var licenseKeysFm = document.getElementById(
			'<portlet:namespace />licenseKeysFm'
		);

		if (licenseKeysFm) {
			submitForm(
				licenseKeysFm,
				'<portlet:actionURL name="/accounts/download_license_keys"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>'
			);
		}
	}

	function <portlet:namespace />renewLicenseKeys() {
		var licenseKeysFm = document.getElementById(
			'<portlet:namespace />licenseKeysFm'
		);

		if (licenseKeysFm) {
			submitForm(
				licenseKeysFm,
				'<portlet:actionURL name="/accounts/renew_select_license_keys"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>'
			);
		}
	}
</aui:script>
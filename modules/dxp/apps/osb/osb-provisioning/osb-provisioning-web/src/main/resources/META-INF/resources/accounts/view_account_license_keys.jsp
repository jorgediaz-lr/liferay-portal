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

	<liferay-ui:search-container
		id="license-keys"
		searchContainer="<%= viewAccountLicenseKeysDisplayContext.getSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountLicenseKeysDisplayContext.getClearResultsURL() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountLicenseKeysDisplayContext.getCurrentURL() %>"
			searchContainerId="license-keys"
			selectable="<%= false %>"
			showSearch="<%= false %>"
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
				name="end-date"
				value="<%= licenseKeyDisplay.getEndDate() %>"
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
</div>
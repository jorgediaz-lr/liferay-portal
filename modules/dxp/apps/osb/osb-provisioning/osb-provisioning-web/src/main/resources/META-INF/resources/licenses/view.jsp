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
LicenseKeySearchDisplayContext licenseKeySearchDisplayContext = ProvisioningWebComponentProvider.getLicenseKeySearchDisplayContext(renderRequest, renderResponse, request);

ViewLicenseKeysManagementToolbarDisplayContext viewLicenseKeysManagementToolbarDisplayContext = ProvisioningWebComponentProvider.getViewLicenseKeysManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, licenseKeySearchDisplayContext.getSearchContainer());
%>

<div class="title-bar">
	<h3><liferay-ui:message key="licenses" /></h3>

	<portlet:renderURL var="addLicenseKeyURL">
		<portlet:param name="mvcRenderCommandName" value="/licenses/add_license_key" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<a class="btn btn-primary" href="<%= addLicenseKeyURL %>">
		<span class="lfr-btn-label"><liferay-ui:message key="generate-license" /></span>
	</a>
</div>

<div class="container-fluid home">
	<div class="licenses">
		<div class="custom-search license-search">
			<react:component
				data="<%= licenseKeySearchDisplayContext.getData() %>"
				module="js/LicenseKeySearchApp"
			/>
		</div>

		<clay:management-toolbar
			clearResultsURL="<%= viewLicenseKeysManagementToolbarDisplayContext.getClearResultsURL() %>"
			displayContext="<%= viewLicenseKeysManagementToolbarDisplayContext %>"
			elementClasses="full-width"
			searchInputName="licenseKeySearchKeywords"
			showSearch="<%= false %>"
		/>

		<liferay-ui:search-container
			cssClass="table-hover"
			searchContainer="<%= licenseKeySearchDisplayContext.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.web.internal.display.context.LicenseKeyDisplay"
				keyProperty="licenseKeyId"
				modelVar="licenseKeyDisplay"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/licenses/edit_license_key" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="licenseKeyId" value="<%= licenseKeyDisplay.getLicenseKeyId() %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name-description"
				>
					<strong>
						<%= HtmlUtil.escape(licenseKeyDisplay.getName()) %>
					</strong>

					<div class="secondary-information">
						<%= HtmlUtil.escape(licenseKeyDisplay.getDescription()) %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="account"
					value="<%= licenseKeyDisplay.getAccountName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="expiration-date"
					value="<%= HtmlUtil.escape(licenseKeyDisplay.getExpirationDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="product"
					value="<%= licenseKeyDisplay.getProductName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="type"
					value="<%= licenseKeyDisplay.getType() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="host-name"
					value="<%= licenseKeyDisplay.getHostName() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</div>
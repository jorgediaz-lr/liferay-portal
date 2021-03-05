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
LicenseSearchDisplayContext licenseSearchDisplayContext = ProvisioningWebComponentProvider.getLicenseSearchDisplayContext(renderRequest, renderResponse, request);
%>

<div class="title-bar">
	<h3><liferay-ui:message key="licenses" /></h3>
</div>

<div class="container-fluid home">
	<div class="licenses">
		<div class="custom-search license-search">
			<react:component
				data="<%= licenseSearchDisplayContext.getData() %>"
				module="js/LicenseSearchApp"
			/>
		</div>

		<clay:management-toolbar
			displayContext="<%= ProvisioningWebComponentProvider.getViewLicenseKeysManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, licenseSearchDisplayContext.getSearchContainer()) %>"
			elementClasses="full-width"
			searchInputName="licenseSearchKeywords"
			showSearch="<%= false %>"
		/>

		<liferay-ui:search-container
			cssClass="table-hover"
			searchContainer="<%= licenseSearchDisplayContext.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.web.internal.display.context.LicenseDisplay"
				keyProperty="licenseKeyId"
				modelVar="licenseDisplay"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/licenses/view_license" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="licenseKeyId" value="<%= licenseDisplay.getLicenseKeyId() %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name-description"
				>
					<%= HtmlUtil.escape(licenseDisplay.getName()) %>

					<div class="secondary-information">
						<%= HtmlUtil.escape(licenseDisplay.getDescription()) %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="account"
					value="<%= licenseDisplay.getAccountName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="end-date"
					value="<%= HtmlUtil.escape(licenseDisplay.getEndDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="product"
					value="<%= licenseDisplay.getProductName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="type"
					value="<%= licenseDisplay.getType() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="host-name"
					value="<%= licenseDisplay.getHostName() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</div>
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
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

PortletURL portletURL = viewAccountDisplayContext.getPortletURL();
%>

<div class="details-table table-striped">
	<liferay-util:include page="/common/tabs.jsp" servletContext="<%= application %>">
		<liferay-util:param name="names" value="active,inactive,all" />
		<liferay-util:param name="param" value="tabs2" />
		<liferay-util:param name="url" value="<%= portletURL.toString() %>" />
		<liferay-util:param name="values" value="active,inactive,all" />
	</liferay-util:include>

	<liferay-ui:search-container
		searchContainer="<%= viewAccountDisplayContext.getProductPurchaseViewsSearchContainer() %>"
	>
		<clay:management-toolbar
			clearResultsURL="<%= viewAccountDisplayContext.getClearResultsURL() %>"
			creationMenu="<%= viewAccountDisplayContext.getCreationMenu() %>"
			elementClasses="full-width"
			itemsTotal="<%= searchContainer.getTotal() %>"
			searchActionURL="<%= viewAccountDisplayContext.getCurrentURL() %>"
			searchContainerId="account-subscriptions"
			selectable="<%= false %>"
			showSearch="<%= true %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseViewDisplay"
			modelVar="productPurchaseViewDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_subscription" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= productPurchaseViewDisplay.getAccountKey() %>" />
				<portlet:param name="productKey" value="<%= productPurchaseViewDisplay.getProductKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product"
			>
				<%= HtmlUtil.escape(productPurchaseViewDisplay.getName()) %>

				<div class="secondary-information">
					<%= productPurchaseViewDisplay.getSizingWithLabel() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-life"
			>
				<%= productPurchaseViewDisplay.getSupportLife() %>

				<c:if test="<%= productPurchaseViewDisplay.isInSupportGap() && Validator.isNotNull(productPurchaseViewDisplay.getNextTermStartDate()) %>">
					<div class="secondary-information">
						<liferay-ui:message key="next-term-starts" />: <%= productPurchaseViewDisplay.getNextTermStartDate() %>
					</div>
				</c:if>
			</liferay-ui:search-container-column-text>

			<%
			String licenseManagerHREF = StringUtil.replace(provisioningWebConfiguration.licenseManagerHREF(), "[$ACCOUNT_KEY$]", productPurchaseViewDisplay.getAccountKey());

			licenseManagerHREF = StringUtil.replace(licenseManagerHREF, "[$PRODUCT_KEY$]", productPurchaseViewDisplay.getProductKey());
			%>

			<liferay-ui:search-container-column-text
				cssClass="semi-bold"
				href="<%= licenseManagerHREF %>"
				name="provisioned"
				value="<%= productPurchaseViewDisplay.getProvisionedCount() %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="semi-bold"
				href="<%= rowURL %>"
				name="purchased"
				value="<%= productPurchaseViewDisplay.getPurchasedCount() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="state"
			>
				<span class="label <%= productPurchaseViewDisplay.getStateStyle() %>"><%= productPurchaseViewDisplay.getState() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>
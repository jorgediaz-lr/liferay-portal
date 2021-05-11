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
ViewContactDisplayContext viewContactDisplayContext = ProvisioningWebComponentProvider.getViewContactDisplayContext(renderRequest, renderResponse, request);

viewContactDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");

String tabNames = "accounts,entitlements";

if (viewContactDisplayContext.hasManageContactsPermission()) {
	tabNames = "accounts,general,entitlements";
}
%>

<liferay-util:include page="/users/view_contact_header.jsp" servletContext="<%= application %>" />

<div class="contact" id="contact">
	<div class="contact-content">
		<liferay-ui:tabs
			names="<%= tabNames %>"
			portletURL="<%= viewContactDisplayContext.getPortletURL() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("entitlements") %>'>
				<liferay-util:include page="/users/view_contact_entitlements.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("general") %>'>
				<liferay-util:include page="/users/edit_contact.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/users/view_contact_accounts.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</div>
</div>
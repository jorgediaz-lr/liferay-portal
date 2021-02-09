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

<div class="title-bar">
	<h3><liferay-ui:message key="licenses" /></h3>
</div>

<div class="container-fluid home">
	<div class="custom-search">
		<react:component
			data="<%= new HashMap<>() %>"
			module="js/LicenseSearchApp"
		/>
	</div>
</div>
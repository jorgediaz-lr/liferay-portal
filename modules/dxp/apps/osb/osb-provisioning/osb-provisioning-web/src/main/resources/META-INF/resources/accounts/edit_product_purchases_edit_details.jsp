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
String redirect = ParamUtil.getString(request, "previous");

if (Validator.isNull(redirect)) {
	redirect = ParamUtil.getString(request, "redirect");
}

EditProductPurchasesDisplayContext editProductPurchasesDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchasesDisplayContext(renderRequest, renderResponse, request);
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= editProductPurchasesDisplayContext.getTitle() %>"
	/>

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	//TODO: add and edit product purchases react component
	//pass productPurchasesData for data
</div>
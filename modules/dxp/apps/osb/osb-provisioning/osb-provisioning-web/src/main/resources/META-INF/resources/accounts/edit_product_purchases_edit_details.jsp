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
String backURL = ParamUtil.getString(request, "backURL");

EditProductPurchasesDisplayContext editProductPurchasesDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchasesDisplayContext(renderRequest, renderResponse, request);

if (Validator.isNull(backURL)) {
	backURL = ParamUtil.getString(request, "redirect");

	if (Validator.isNull(backURL)) {
		backURL = editProductPurchasesDisplayContext.getRedirectURL();
	}
}
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= backURL %>"
		cssClass="add-items-header"
		title="<%= editProductPurchasesDisplayContext.getTitle() %>"
	/>

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= ProductPurchaseQuantityException.class %>" message="to-remove-a-subscription-change-the-status-to-cancelled-instead" />

	<div id="editSubscriptions">
		<react:component
			data="<%= editProductPurchasesDisplayContext.getEditProductPurchasesData() %>"
			module="js/EditSubscriptionsApp"
		/>
	</div>
</div>
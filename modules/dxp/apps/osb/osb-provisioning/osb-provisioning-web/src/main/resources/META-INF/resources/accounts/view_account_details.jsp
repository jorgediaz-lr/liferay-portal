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
%>

<liferay-ui:error exception="<%= AccountCodeException.class %>" message="please-enter-a-valid-code" />
<liferay-ui:error exception="<%= DuplicateDossieraKeyException.class %>" message="dossiera-keys-must-be-unique" />

<liferay-ui:error exception="<%= HttpException.class %>">

	<%
	HttpException httpException = (HttpException)errorException;
	%>

	<%= httpException.getMessage() %>
</liferay-ui:error>

<liferay-ui:error exception="<%= MultipleDossieraKeysException.class %>" message="an-account-can-only-have-one-dossiera-key" />

<div class="account-details details-table" id="accountDetails">
	<react:component
		data="<%= viewAccountDisplayContext.getAccountDetailsData() %>"
		module="js/AccountDetailsApp"
	/>
</div>
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
String redirect = ParamUtil.getString(request, "redirect");
%>

<div class="add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="new-account"
	/>

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<portlet:actionURL name="/accounts/edit_account" var="addAccountURL" />

	<aui:form action="<%= addAccountURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input inlineLabel="left" name="name" required="<%= true %>" />
			<aui:input inlineLabel="left" name="code" required="<%= true %>" />

			<aui:select inlineLabel="left" name="tier" required="<%= true %>">
				<aui:option value="" />

				<%
				for (Account.Tier tier : Account.Tier.values()) {
				%>

					<aui:option label="<%= tier %>" value="<%= tier %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select inlineLabel="left" label="support-region" name="region" required="<%= true %>">
				<aui:option value="" />

				<%
				for (Account.Region region : Account.Region.values()) {
				%>

					<aui:option label="<%= region %>" value="<%= region %>" />

				<%
				}
				%>

			</aui:select>

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>
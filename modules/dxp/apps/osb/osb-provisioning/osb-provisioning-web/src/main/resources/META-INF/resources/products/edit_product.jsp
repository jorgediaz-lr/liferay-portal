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

Product product = (Product)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT);

Map<String, String> properties = null;

if (product != null) {
	properties = product.getProperties();
}
%>

<div class="add-items provisioning-products">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= (product != null) ? "edit-product" : "new-product" %>'
	/>

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<portlet:actionURL name="/products/edit_product" var="editProductURL">
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="productKey" value='<%= (product != null) ? product.getKey() : "" %>' />
	</portlet:actionURL>

	<aui:form action="<%= editProductURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input inlineLabel="left" name="name" value='<%= (product != null) ? product.getName() : "" %>' />

			<aui:select inlineLabel="left" name="type">
				<aui:option value="" />

				<%
				String propertyType = StringPool.BLANK;

				if (properties != null) {
					String type = properties.get("type");

					if (Validator.isNotNull(type)) {
						propertyType = type;
					}
				}

				for (String productType : ProductTypeConstants.TYPES) {
				%>

					<aui:option label="<%= productType %>" selected="<%= propertyType.equals(productType) ? true : false %>" value="<%= productType %>" />

				<%
				}
				%>

			</aui:select>

			<%
			String dossieraIdMapping = StringPool.BLANK;

			if (product != null) {
				ExternalLink[] externalLinks = product.getExternalLinks();

				if (externalLinks != null) {
					for (ExternalLink externalLink : externalLinks) {
						String domain = externalLink.getDomain();
						String entityName = externalLink.getEntityName();

						if (domain.equals(ExternalLinkDomain.DOSSIERA) && entityName.equals(ExternalLinkEntityName.DOSSIERA_PRODUCT)) {
							dossieraIdMapping = externalLink.getEntityId();
						}
					}
				}
			}
			%>

			<aui:input inlineLabel="left" name="dossieraIdMapping" value="<%= dossieraIdMapping %>" />

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>
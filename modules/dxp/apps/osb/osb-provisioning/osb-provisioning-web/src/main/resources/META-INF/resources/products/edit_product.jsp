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

<portlet:actionURL name="/products/edit_product" var="editProductURL">
	<portlet:param name="mvcRenderCommandName" value="/products/edit_product" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="productKey" value='<%= (product != null) ? product.getKey() : "" %>' />
</portlet:actionURL>

<liferay-ui:error exception="<%= HttpException.class %>">

	<%
	HttpException httpException = (HttpException)errorException;
	%>

	<%= httpException.getMessage() %>
</liferay-ui:error>

<aui:form action="<%= editProductURL.toString() %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="name" value='<%= (product != null) ? product.getName() : "" %>' />

			<aui:select name="type">
				<aui:option value="" />

				<%
				String propertyType = StringPool.BLANK;

				if ((properties != null) && Validator.isNotNull(properties.get("type"))) {
					propertyType = properties.get("type");
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

			<aui:input name="dossieraIdMapping" value="<%= dossieraIdMapping %>" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>
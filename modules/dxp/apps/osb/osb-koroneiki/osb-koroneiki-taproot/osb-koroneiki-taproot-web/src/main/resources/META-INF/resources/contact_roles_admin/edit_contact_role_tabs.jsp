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
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

ContactRole contactRole = (ContactRole)request.getAttribute(TaprootWebKeys.CONTACT_ROLE);

long contactRoleId = BeanParamUtil.getLong(contactRole, request, "contactRoleId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contact_roles_admin/edit_contact_role", "tabs1", "details", "contactRoleId", contactRoleId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (contactRole != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contact_roles_admin/edit_contact_role", "tabs1", "external-links", "contactRoleId", contactRoleId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>
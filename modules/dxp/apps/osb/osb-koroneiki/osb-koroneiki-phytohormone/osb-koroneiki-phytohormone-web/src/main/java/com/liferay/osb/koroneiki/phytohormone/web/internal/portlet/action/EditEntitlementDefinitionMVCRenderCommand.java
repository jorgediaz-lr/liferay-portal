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

package com.liferay.osb.koroneiki.phytohormone.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormonePortletKeys;
import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormoneWebKeys;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + PhytohormonePortletKeys.ENTITLEMENT_DEFINITIONS_ADMIN,
		"mvc.command.name=/entitlement_definitions_admin/edit_entitlement_definition"
	},
	service = MVCRenderCommand.class
)
public class EditEntitlementDefinitionMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long entitlementDefinitionId = ParamUtil.getLong(
				renderRequest, "entitlementDefinitionId");

			if (entitlementDefinitionId > 0) {
				renderRequest.setAttribute(
					PhytohormoneWebKeys.ENTITLEMENT_DEFINITION,
					_entitlementDefinitionLocalService.getEntitlementDefinition(
						entitlementDefinitionId));
			}

			String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

			if (tabs1.equals("external-links")) {
				return "/entitlement_definitions_admin" +
					"/edit_entitlement_definition_external_links.jsp";
			}

			return "/entitlement_definitions_admin" +
				"/edit_entitlement_definition.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/entitlement_definitions_admin/error.jsp";
		}
	}

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

}
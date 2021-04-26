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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/renew_select_license_keys"
	},
	service = MVCActionCommand.class
)
public class RenewSelectLicenseKeysMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			LiferayPortletResponse liferayPortletResponse =
				_portal.getLiferayPortletResponse(actionResponse);

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/renew_license_keys");
			portletURL.setParameter(
				"redirect", ParamUtil.getString(actionRequest, "redirect"));
			portletURL.setParameter(
				"licenseKeyIds",
				ParamUtil.getString(actionRequest, "licenseKeyIds"));

			hideDefaultSuccessMessage(actionRequest);

			sendRedirect(actionRequest, actionResponse, portletURL.toString());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RenewSelectLicenseKeysMVCActionCommand.class);

	@Reference
	private Portal _portal;

}
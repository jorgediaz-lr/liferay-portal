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
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_license_keys"
	},
	service = MVCActionCommand.class
)
public class EditAccountLicenseKeysMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long[] licenseKeyIds = ParamUtil.getLongValues(
				actionRequest, "licenseKeyIds");

			String active = ParamUtil.getString(actionRequest, "active");
			String complimentary = ParamUtil.getString(
				actionRequest, "complimentary");

			for (long licenseKeyId : licenseKeyIds) {
				LicenseKey licenseKey = _licenseKeyService.getLicenseKey(
					licenseKeyId);

				if (Validator.isNotNull(active)) {
					_licenseKeyService.updateLicenseKey(
						licenseKeyId, licenseKey.getProductPurchaseKey(),
						licenseKey.isComplimentary(),
						GetterUtil.getBoolean(active));
				}
				else if (Validator.isNotNull(complimentary)) {
					_licenseKeyService.updateLicenseKey(
						licenseKeyId, licenseKey.getProductPurchaseKey(),
						GetterUtil.getBoolean(complimentary),
						licenseKey.isActive());
				}
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountLicenseKeysMVCActionCommand.class);

	@Reference
	private LicenseKeyService _licenseKeyService;

}
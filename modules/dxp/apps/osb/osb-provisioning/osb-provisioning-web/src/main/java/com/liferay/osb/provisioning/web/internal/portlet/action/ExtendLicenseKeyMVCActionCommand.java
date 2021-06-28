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
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"mvc.command.name=/licenses/extend_license_key"
	},
	service = MVCActionCommand.class
)
public class ExtendLicenseKeyMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long licenseKeyId = ParamUtil.getLong(
				actionRequest, "licenseKeyId");
			String productPurchaseKey = ParamUtil.getString(
				actionRequest, "productPurchaseKey");

			if (Validator.isNull(productPurchaseKey)) {
				LicenseKey licenseKey = _licenseKeyService.getLicenseKey(
					licenseKeyId);

				productPurchaseKey = licenseKey.getProductPurchaseKey();
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = dateFormat.parse(
				ParamUtil.getString(actionRequest, "startDate"));
			Date expirationDate = dateFormat.parse(
				ParamUtil.getString(actionRequest, "expirationDate"));

			LicenseKey licenseKey = _licenseKeyService.extendLicenseKey(
				licenseKeyId, productPurchaseKey, startDate, expirationDate);

			sendRedirect(
				actionRequest, actionResponse,
				getRedirect(
					actionRequest, actionResponse,
					licenseKey.getLicenseKeyId()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected String getRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			long licenseKeyId)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/edit_license_key");
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter(
			"licenseKeyId", StringUtil.valueOf(licenseKeyId));

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExtendLicenseKeyMVCActionCommand.class);

	@Reference
	private LicenseKeyService _licenseKeyService;

	@Reference
	private Portal _portal;

}
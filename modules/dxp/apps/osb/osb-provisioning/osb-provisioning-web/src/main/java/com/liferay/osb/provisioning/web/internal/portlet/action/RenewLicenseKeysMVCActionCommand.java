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
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
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
 * @author Jenny Chen
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/renew_license_keys"
	},
	service = MVCActionCommand.class
)
public class RenewLicenseKeysMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long[] licenseKeyIds = ParamUtil.getLongValues(
				actionRequest, "licenseKeyIds");

			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");
			String productKey = ParamUtil.getString(
				actionRequest, "productKey");

			String startDate = ParamUtil.getString(actionRequest, "startDate");
			String expirationDate = ParamUtil.getString(
				actionRequest, "expirationDate");

			if (ArrayUtil.isNotEmpty(licenseKeyIds) &&
				Validator.isNotNull(startDate) &&
				Validator.isNotNull(expirationDate)) {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date curStartDate = dateFormat.parse(
					ParamUtil.getString(actionRequest, "startDate"));
				Date curExpirationDate = dateFormat.parse(
					ParamUtil.getString(actionRequest, "expirationDate"));

				for (long licenseKeyId : licenseKeyIds) {
					_licenseKeyService.renewLicenseKey(
						licenseKeyId, curStartDate, curExpirationDate);
				}
			}

			sendRedirect(
				actionRequest, actionResponse,
				getRedirect(actionResponse, accountKey, productKey));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected String getRedirect(
			ActionResponse actionResponse, String accountKey, String productKey)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("tabs1", "licenses");
		portletURL.setParameter("accountKey", accountKey);

		if (Validator.isNull(productKey)) {
			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
		}
		else {
			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_subscription");
			portletURL.setParameter("productKey", productKey);
		}

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RenewLicenseKeysMVCActionCommand.class);

	@Reference
	private LicenseKeyService _licenseKeyService;

	@Reference
	private Portal _portal;

}
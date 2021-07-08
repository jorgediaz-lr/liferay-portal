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
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rebecca Dai
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"mvc.command.name=/accounts/extend_license_keys",
		"mvc.command.name=/licenses/extend_license_key"
	},
	service = MVCRenderCommand.class
)
public class ExtendLicenseKeysMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long licenseKeyId = ParamUtil.getLong(
				renderRequest, "licenseKeyId");

			if (licenseKeyId > 0) {
				renderRequest.setAttribute(
					ProvisioningWebKeys.LICENSE_KEY,
					_licenseKeyService.getLicenseKey(licenseKeyId));
			}
			else {
				String accountKey = ParamUtil.getString(
					renderRequest, "accountKey");
				long[] licenseKeyIds = ParamUtil.getLongValues(
					renderRequest, "licenseKeyIds");

				List<LicenseKey> licenseKeys = new ArrayList<>();

				for (long curlicenseKeyId : licenseKeyIds) {
					LicenseKey licenseKey = _licenseKeyService.getLicenseKey(
						curlicenseKeyId);

					licenseKeys.add(licenseKey);
				}

				renderRequest.setAttribute(
					ProvisioningWebKeys.ACCOUNT,
					_accountWebService.getAccount(accountKey));
				renderRequest.setAttribute(
					ProvisioningWebKeys.LICENSE_KEYS, licenseKeys);
			}

			return "/common/extend_license_keys.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			return "/common/error.jsp";
		}
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private LicenseKeyService _licenseKeyService;

}
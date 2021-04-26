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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Jenny Chen
 */
public class EditAccountLicenseKeysDisplayContext {

	public EditAccountLicenseKeysDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_licenseKeys = (List<LicenseKey>)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEYS);
	}

	public Map<String, Object> getRenewLicenseKeysData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL portletURL = _renderResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/renew_license_keys");

		data.put("renewLicenseKeysURL", portletURL.toString());

		JSONArray licenseKeysJSONArray = JSONFactoryUtil.createJSONArray();

		for (LicenseKey licenseKey : _licenseKeys) {
			licenseKeysJSONArray.put(
				JSONUtil.put("licenseKeyId", licenseKey.getLicenseKeyId()));
		}

		data.put("licenseKeys", licenseKeysJSONArray);

		return data;
	}

	private final List<LicenseKey> _licenseKeys;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}
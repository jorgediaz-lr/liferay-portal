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
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;

import java.text.Format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Yuanyuan Huang
 */
public class DownloadLicenseKeysDisplayContext {

	public DownloadLicenseKeysDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_licenseKeys = (List<LicenseKey>)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEYS);
	}

	public Map<String, Object> getDownloadLicenseKeysData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		LiferayPortletURL liferayPortletURL =
			(LiferayPortletURL)_renderResponse.createResourceURL();

		liferayPortletURL.setCopyCurrentRenderParameters(false);
		liferayPortletURL.setResourceID("/accounts/download_license_keys");

		data.put("downloadLicenseKeysURL", liferayPortletURL.toString());

		JSONArray licenseKeysJSONArray = JSONFactoryUtil.createJSONArray();

		for (LicenseKey licenseKey : _licenseKeys) {
			licenseKeysJSONArray.put(
				JSONUtil.put(
					"active", licenseKey.isActive()
				).put(
					"description", licenseKey.getDescription()
				).put(
					"expirationDate",
					_dateFormat.format(licenseKey.getExpirationDate())
				).put(
					"licenseEntryName", licenseKey.getLicenseEntryName()
				).put(
					"licenseEntryType", licenseKey.getLicenseEntryType()
				).put(
					"licenseKeyId", licenseKey.getLicenseKeyId()
				).put(
					"licenseVersion", licenseKey.getLicenseVersion()
				).put(
					"name", licenseKey.getName()
				).put(
					"productName", licenseKey.getProductName()
				).put(
					"productVersion", licenseKey.getProductVersion()
				).put(
					"startDate", _dateFormat.format(licenseKey.getStartDate())
				));
		}

		data.put("licenseKeys", licenseKeysJSONArray);

		return data;
	}

	private final Format _dateFormat;
	private final List<LicenseKey> _licenseKeys;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}
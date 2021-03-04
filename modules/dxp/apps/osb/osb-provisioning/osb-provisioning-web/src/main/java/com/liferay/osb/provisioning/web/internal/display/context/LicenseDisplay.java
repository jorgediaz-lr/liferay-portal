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

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseDisplay {

	public LicenseDisplay(
			PortletRequest portletRequest, PortletResponse portletResponse,
			LicenseKey licenseKey)
		throws Exception {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;

		_licenseKey = licenseKey;

		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_dateTimeFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy hh:mm a z");
	}

	public String getAccountName() {
		return _licenseKey.getAccountName();
	}

	public String getDescription() {
		if (Validator.isNotNull(_licenseKey.getDescription())) {
			return _licenseKey.getDescription();
		}

		return StringPool.DASH;
	}

	public String getEndDate() {
		return _dateTimeFormat.format(_licenseKey.getExpirationDate());
	}

	public String getHostName() {
		if (Validator.isNotNull(_licenseKey.getHostName())) {
			return _licenseKey.getHostName();
		}

		return StringPool.DASH;
	}

	public String getLicenseKeyId() {
		return String.valueOf(_licenseKey.getLicenseKeyId());
	}

	public String getName() {
		return _licenseKey.getLicenseEntryName();
	}

	public String getProductName() {
		return _licenseKey.getProductName();
	}

	public String getType() {
		return _licenseKey.getLicenseEntryType();
	}

	private final Format _dateFormat;
	private final Format _dateTimeFormat;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseKey _licenseKey;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;

}
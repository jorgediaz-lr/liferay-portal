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

import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.Date;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseKeyDisplay {

	public LicenseKeyDisplay(
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
			"MMMM dd, yyyy");
		_dateTimeFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMMM dd, yyyy hh:mm:ss a 'UTC'");

		_initStatus();
	}

	public String getAccountName() {
		return _licenseKey.getAccountName();
	}

	public String getCreateDate() {
		return _dateTimeFormat.format(_licenseKey.getCreateDate());
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

	public String getExpirationDate() {
		String licenseType = _licenseKey.getLicenseEntryType();

		if (licenseType.equals(LicenseType.TRIAL)) {
			Date startDate = _licenseKey.getStartDate();
			Date expirationDate = _licenseKey.getExpirationDate();

			long days = (expirationDate.getTime() - startDate.getTime()) / Time.DAY;

			return String.valueOf((int)days) + " Days";
		}

		return _dateFormat.format(_licenseKey.getExpirationDate());
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

	public String getMaxConcurrentUsersLabel() {
		if (_licenseKey.getMaxConcurrentUsers() <= 0) {
			return LanguageUtil.get(_httpServletRequest, "unlimited");
		}

		return String.valueOf(_licenseKey.getMaxConcurrentUsers());
	}

	public String getMaxUsersLabel() {
		if (_licenseKey.getMaxUsers() <= 0) {
			return LanguageUtil.get(_httpServletRequest, "unlimited");
		}

		return String.valueOf(_licenseKey.getMaxUsers());
	}

	public String getModifiedDate() {
		return _dateTimeFormat.format(_licenseKey.getModifiedDate());
	}

	public String getName() {
		return _licenseKey.getLicenseEntryName();
	}

	public String getProductName() {
		return _licenseKey.getProductName();
	}

	public String getProductVersion() {
		return _licenseKey.getProductVersion();
	}

	public String getStartDate() {
		return _dateFormat.format(_licenseKey.getCreateDate());
	}

	public String getStatus() {
		return LanguageUtil.get(_httpServletRequest, _status);
	}

	public String getStatusStyle() {
		if (_status.equals("active")) {
			return "label-success";
		}
		else if (_status.equals("expired")) {
			return "label-warning";
		}
		else {
			return "label-danger";
		}
	}

	public String getType() {
		return _licenseKey.getLicenseEntryType();
	}

	private void _initStatus() {
		if (_licenseKey.isActive()) {
			Date expirationDate = _licenseKey.getExpirationDate();

			Date now = new Date();

			if (expirationDate.after(now)) {
				_status = "active";
			}
			else {
				_status = "expired";
			}
		}
		else {
			_status = "deactivated";
		}
	}

	private final Format _dateFormat;
	private final Format _dateTimeFormat;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseKey _licenseKey;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private String _status;

}
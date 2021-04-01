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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
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
		return _licenseKey.getDescription();
	}

	public String getEndDate() {
		return _dateTimeFormat.format(_licenseKey.getExpirationDate());
	}

	public String getExpirationDate() {
		String licenseType = _licenseKey.getLicenseEntryType();

		if (licenseType.equals(LicenseType.TRIAL)) {
			Date startDate = _licenseKey.getStartDate();
			Date expirationDate = _licenseKey.getExpirationDate();

			long days =
				(expirationDate.getTime() - startDate.getTime()) / Time.DAY;

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

	public String getIpAddresses() {
		return getSplitFieldValue(_licenseKey.getIpAddresses());
	}

	public String getLicenseKeyId() {
		return String.valueOf(_licenseKey.getLicenseKeyId());
	}

	public String getMacAddresses() {
		return getSplitFieldValue(_licenseKey.getMacAddresses());
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
		return _licenseKey.getName();
	}

	public String getOwner() {
		return _licenseKey.getOwner();
	}

	public String getProductName() {
		return _licenseKey.getProductName();
	}

	public String getProductVersion() {
		return _licenseKey.getProductVersion();
	}

	public String getServerId() {
		if (Validator.isNotNull(_licenseKey.getServerId())) {
			return _licenseKey.getServerId();
		}

		return StringPool.DASH;
	}

	public String getStartDate() {
		String licenseType = _licenseKey.getLicenseEntryType();

		if (licenseType.equals(LicenseType.TRIAL)) {
			return LanguageUtil.get(_httpServletRequest, "registration");
		}

		return _dateFormat.format(_licenseKey.getStartDate());
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
		return LanguageUtil.get(
			_httpServletRequest, _licenseKey.getLicenseEntryType());
	}

	public String getUserName() {
		if (Validator.isNotNull(_licenseKey.getUserName())) {
			return _licenseKey.getUserName();
		}

		return StringPool.DASH;
	}

	public String isComplimentaryLabel() {
		if (_licenseKey.isComplimentary()) {
			return LanguageUtil.get(_httpServletRequest, "yes");
		}

		return LanguageUtil.get(_httpServletRequest, "no");
	}

	protected String getSplitFieldValue(String value) {
		String[] splitValue = StringUtil.split(value);

		if (splitValue.length > 0) {
			StringBundler sb = new StringBundler((splitValue.length * 2) - 1);

			for (int i = 0; i < splitValue.length; i++) {
				sb.append(splitValue[i]);

				if ((i + 1) < splitValue.length) {
					sb.append(StringPool.SPACE);
				}
			}

			return sb.toString();
		}

		return StringPool.DASH;
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
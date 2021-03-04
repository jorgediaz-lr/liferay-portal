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

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseSearchTerms extends LicenseDisplayTerms {

	public LicenseSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public Long getCreatorUserId() throws PortalException {
		if (Validator.isNotNull(creatorEmailAddress)) {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				PortalUtil.getDefaultCompanyId(), creatorEmailAddress);

			if (user != null) {
				return user.getUserId();
			}
		}

		return null;
	}

	public Date getDate(String date) throws ParseException {
		if (Validator.isNotNull(date)) {
			return _dateFormat.parse(date);
		}

		return null;
	}

	public long[] getLicenseEntryIds() {
		long[] licenseEntryIds = new long[types.length];

		for (int i = 0; i < types.length; i++) {
			licenseEntryIds[i] = Long.valueOf(types[i]);
		}

		return licenseEntryIds;
	}

	public Long getModifiedUserId() throws PortalException {
		if (Validator.isNotNull(modifiedEmailAddress)) {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				PortalUtil.getDefaultCompanyId(), modifiedEmailAddress);

			if (user != null) {
				return user.getUserId();
			}
		}

		return null;
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (activeLicenses.length == 1) {
			params.put("active", activeLicenses[0]);
		}

		return params;
	}

	private final DateFormat _dateFormat =
		DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}
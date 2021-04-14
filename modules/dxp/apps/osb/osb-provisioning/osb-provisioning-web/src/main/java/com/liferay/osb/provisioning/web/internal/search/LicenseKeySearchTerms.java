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

import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseKeySearchTerms extends LicenseKeyDisplayTerms {

	public LicenseKeySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public Boolean getActive() {
		if (activeLicenses.length == 1) {
			return activeLicenses[0];
		}

		return null;
	}

	public Date getDate(String date) throws ParseException {
		if (Validator.isNotNull(date)) {
			return _dateFormat.parse(date);
		}

		return null;
	}

	public Long[] getLicenseEntryIds() {
		Long[] licenseEntryIds = new Long[types.length];

		for (int i = 0; i < types.length; i++) {
			licenseEntryIds[i] = Long.valueOf(types[i]);
		}

		return licenseEntryIds;
	}

	private final DateFormat _dateFormat =
		DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}
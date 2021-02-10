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

package com.liferay.osb.customer.license.model.impl;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.customer.admin.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.LicenseKeySetLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Time;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseKeyImpl extends LicenseKeyBaseImpl {

	public LicenseKeyImpl() {
	}

	public boolean canRenew() throws PortalException {
		if (!isActive()) {
			return false;
		}

		if (getLicenseVersion() < 3) {
			return false;
		}

		Date startDate = getStartDate();
		Date expirationDate = getExpirationDate();

		if (((expirationDate.getTime() - startDate.getTime()) / Time.DAY) >
				365) {

			return false;
		}

		return true;
	}

	public AccountEntry getAccountEntry() throws PortalException {
		return AccountEntryLocalServiceUtil.getAccountEntry(
			getAccountEntryId());
	}

	public LicenseEntry getLicenseEntry() throws PortalException {
		return LicenseEntryLocalServiceUtil.getLicenseEntry(
			getLicenseEntryId());
	}

	public LicenseKeySet getLicenseKeySet() throws PortalException {
		return LicenseKeySetLocalServiceUtil.getLicenseKeySet(
			getLicenseKeySetId());
	}

	public boolean isExpired() {
		Date now = new Date();

		if (now.after(getExpirationDate())) {
			return true;
		}

		return false;
	}

}
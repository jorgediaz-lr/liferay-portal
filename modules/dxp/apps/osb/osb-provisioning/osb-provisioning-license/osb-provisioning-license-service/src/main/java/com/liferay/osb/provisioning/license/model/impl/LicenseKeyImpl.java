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

package com.liferay.osb.provisioning.license.model.impl;

import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseKeyImpl extends LicenseKeyBaseImpl {

	public LicenseKeyImpl() {
	}

	public LicenseEntry getLicenseEntry() throws PortalException {
		return LicenseEntryLocalServiceUtil.getLicenseEntry(
			getLicenseEntryId());
	}

	public boolean isExpired() {
		Date now = new Date();

		if (now.after(getExpirationDate())) {
			return true;
		}

		return false;
	}

}
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

package com.liferay.osb.provisioning.license.service.impl;

import com.liferay.osb.provisioning.license.exception.LicenseEntryNameException;
import com.liferay.osb.provisioning.license.exception.LicenseEntryVersionException;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.base.LicenseEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.license.model.LicenseEntry",
	service = AopService.class
)
public class LicenseEntryLocalServiceImpl
	extends LicenseEntryLocalServiceBaseImpl {

	public LicenseEntry addLicenseEntry(
			long userId, String productKey, String name, String type,
			int versionMin, int versionMax)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(name, versionMin, versionMax);

		long licenseEntryId = counterLocalService.increment();

		LicenseEntry licenseEntry = licenseEntryPersistence.create(
			licenseEntryId);

		licenseEntry.setUserId(user.getUserId());
		licenseEntry.setUserName(user.getFullName());
		licenseEntry.setCreateDate(now);
		licenseEntry.setModifiedDate(now);
		licenseEntry.setProductKey(productKey);
		licenseEntry.setName(name);
		licenseEntry.setType(type);
		licenseEntry.setVersionMin(versionMin);
		licenseEntry.setVersionMax(versionMax);

		return licenseEntryPersistence.update(licenseEntry);
	}

	public List<LicenseEntry> getLicenseEntries(String productKey) {
		return licenseEntryPersistence.findByProductKey(productKey);
	}

	public List<LicenseEntry> getLicenseEntries(
		String productKey, int version) {

		List<LicenseEntry> licenseEntries = licenseEntryPersistence.findByPK_V(
			productKey, version);

		licenseEntries = ListUtil.copy(licenseEntries);

		Iterator<LicenseEntry> itr = licenseEntries.iterator();

		while (itr.hasNext()) {
			LicenseEntry licenseEntry = itr.next();

			if ((licenseEntry.getVersionMax() != 0) &&
				(version > licenseEntry.getVersionMax())) {

				itr.remove();
			}
		}

		return licenseEntries;
	}

	public LicenseEntry getLicenseEntry(String productKey, String type)
		throws PortalException {

		return licenseEntryPersistence.findByPK_T(productKey, type);
	}

	public LicenseEntry updateLicenseEntry(
			long licenseEntryId, String productKey, String name, String type,
			int versionMin, int versionMax)
		throws PortalException {

		validate(name, versionMin, versionMax);

		LicenseEntry licenseEntry = licenseEntryPersistence.findByPrimaryKey(
			licenseEntryId);

		licenseEntry.setModifiedDate(new Date());
		licenseEntry.setProductKey(productKey);
		licenseEntry.setName(name);
		licenseEntry.setType(type);
		licenseEntry.setVersionMin(versionMin);
		licenseEntry.setVersionMax(versionMax);

		return licenseEntryPersistence.update(licenseEntry);
	}

	protected void validate(String name, int versionMin, int versionMax)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new LicenseEntryNameException();
		}

		if ((versionMax != 0) && (versionMin >= versionMax)) {
			throw new LicenseEntryVersionException();
		}
	}

}
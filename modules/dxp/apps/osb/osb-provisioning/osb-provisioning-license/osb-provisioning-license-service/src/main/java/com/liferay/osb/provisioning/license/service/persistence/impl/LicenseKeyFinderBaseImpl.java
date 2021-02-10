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

package com.liferay.osb.customer.license.service.persistence.impl;

import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.service.persistence.LicenseKeyPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeyFinderBaseImpl extends BasePersistenceImpl<LicenseKey> {

	public LicenseKeyFinderBaseImpl() {
		setModelClass(LicenseKey.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("key", "key_");
		dbColumnNames.put("active", "active_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getLicenseKeyPersistence().getBadColumnNames();
	}

	/**
	 * Returns the license key persistence.
	 *
	 * @return the license key persistence
	 */
	public LicenseKeyPersistence getLicenseKeyPersistence() {
		return licenseKeyPersistence;
	}

	/**
	 * Sets the license key persistence.
	 *
	 * @param licenseKeyPersistence the license key persistence
	 */
	public void setLicenseKeyPersistence(
		LicenseKeyPersistence licenseKeyPersistence) {

		this.licenseKeyPersistence = licenseKeyPersistence;
	}

	@BeanReference(type = LicenseKeyPersistence.class)
	protected LicenseKeyPersistence licenseKeyPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyFinderBaseImpl.class);

}
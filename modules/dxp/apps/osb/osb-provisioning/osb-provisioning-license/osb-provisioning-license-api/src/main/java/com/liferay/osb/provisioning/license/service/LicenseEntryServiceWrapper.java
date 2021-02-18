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

package com.liferay.osb.provisioning.license.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryService
 * @generated
 */
public class LicenseEntryServiceWrapper
	implements LicenseEntryService, ServiceWrapper<LicenseEntryService> {

	public LicenseEntryServiceWrapper(LicenseEntryService licenseEntryService) {
		_licenseEntryService = licenseEntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public LicenseEntryService getWrappedService() {
		return _licenseEntryService;
	}

	@Override
	public void setWrappedService(LicenseEntryService licenseEntryService) {
		_licenseEntryService = licenseEntryService;
	}

	private LicenseEntryService _licenseEntryService;

}
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
 * Provides a wrapper for {@link CommonLicenseKeyService}.
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKeyService
 * @generated
 */
public class CommonLicenseKeyServiceWrapper
	implements CommonLicenseKeyService,
			   ServiceWrapper<CommonLicenseKeyService> {

	public CommonLicenseKeyServiceWrapper(
		CommonLicenseKeyService commonLicenseKeyService) {

		_commonLicenseKeyService = commonLicenseKeyService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commonLicenseKeyService.getOSGiServiceIdentifier();
	}

	@Override
	public CommonLicenseKeyService getWrappedService() {
		return _commonLicenseKeyService;
	}

	@Override
	public void setWrappedService(
		CommonLicenseKeyService commonLicenseKeyService) {

		_commonLicenseKeyService = commonLicenseKeyService;
	}

	private CommonLicenseKeyService _commonLicenseKeyService;

}
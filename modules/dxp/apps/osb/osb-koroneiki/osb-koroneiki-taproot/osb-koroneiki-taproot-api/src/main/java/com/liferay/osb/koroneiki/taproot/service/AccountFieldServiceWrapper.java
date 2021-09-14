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

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountFieldService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountFieldService
 * @generated
 */
public class AccountFieldServiceWrapper
	implements AccountFieldService, ServiceWrapper<AccountFieldService> {

	public AccountFieldServiceWrapper(AccountFieldService accountFieldService) {
		_accountFieldService = accountFieldService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountFieldService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountFieldService getWrappedService() {
		return _accountFieldService;
	}

	@Override
	public void setWrappedService(AccountFieldService accountFieldService) {
		_accountFieldService = accountFieldService;
	}

	private AccountFieldService _accountFieldService;

}
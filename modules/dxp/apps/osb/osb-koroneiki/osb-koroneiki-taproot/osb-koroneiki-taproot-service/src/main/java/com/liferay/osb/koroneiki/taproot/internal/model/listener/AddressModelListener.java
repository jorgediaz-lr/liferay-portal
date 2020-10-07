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

package com.liferay.osb.koroneiki.taproot.internal.model.listener;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AddressModelListener extends BaseModelListener<Address> {

	@Override
	public void onAfterCreate(Address address) throws ModelListenerException {
		try {
			_reindex(address);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterRemove(Address address) throws ModelListenerException {
		try {
			_reindex(address);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterUpdate(Address address) throws ModelListenerException {
		try {
			_reindex(address);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	private void _reindex(Address address) throws PortalException {
		if (address.getClassNameId() == _classNameLocalService.getClassNameId(
				Account.class)) {

			_accountLocalService.reindex(address.getClassPK());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddressModelListener.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

}
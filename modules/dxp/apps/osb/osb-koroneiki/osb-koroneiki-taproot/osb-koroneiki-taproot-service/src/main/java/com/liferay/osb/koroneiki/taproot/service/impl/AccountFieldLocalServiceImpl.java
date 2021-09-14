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

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.service.base.AccountFieldLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.AccountField",
	service = AopService.class
)
public class AccountFieldLocalServiceImpl
	extends AccountFieldLocalServiceBaseImpl {

	public AccountField addAccountField(
			long userId, long accountId, String name, String value)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long accountFieldId = counterLocalService.increment();

		AccountField accountField = accountFieldPersistence.create(
			accountFieldId);

		accountField.setCompanyId(user.getCompanyId());
		accountField.setUserId(userId);
		accountField.setAccountId(accountId);
		accountField.setName(name);
		accountField.setValue(value);

		return accountFieldPersistence.update(accountField);
	}

	public List<String> getAccountFieldNames() {
		return accountFieldFinder.findNames();
	}

	public List<AccountField> getAccountFields(long accountId) {
		return accountFieldPersistence.findByAccountId(accountId);
	}

	public AccountField updateAccountField(long accountFieldId, String value)
		throws PortalException {

		AccountField accountField = accountFieldPersistence.findByPrimaryKey(
			accountFieldId);

		accountField.setValue(value);

		return accountFieldPersistence.update(accountField);
	}

}
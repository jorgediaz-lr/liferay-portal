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
 * Provides a wrapper for {@link AccountService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountService
 * @generated
 */
public class AccountServiceWrapper
	implements AccountService, ServiceWrapper<AccountService> {

	public AccountServiceWrapper(AccountService accountService) {
		_accountService = accountService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account addAccount(
			long parentAccountId, String name, String code, String description,
			long logoId, String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String language, boolean internal, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.addAccount(
			parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, language, internal, status);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account deleteAccount(
			long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.deleteAccount(accountId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account deleteAccount(
			String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.deleteAccount(accountKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account getAccount(
			long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getAccount(accountId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account getAccount(
			String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getAccount(accountKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
			getAccounts(long parentAccountId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getAccounts(parentAccountId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
			getAccounts(
				String domain, String entityName, String entityId, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getAccounts(
			domain, entityName, entityId, start, end);
	}

	@Override
	public int getAccountsCount(long parentAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getAccountsCount(parentAccountId);
	}

	@Override
	public int getAccountsCount(
			String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getAccountsCount(domain, entityName, entityId);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
			getContactAccounts(long contactId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getContactAccounts(contactId, start, end);
	}

	@Override
	public int getContactAccountsCount(long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getContactAccountsCount(contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
			getTeamAccounts(long teamId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getTeamAccounts(teamId, start, end);
	}

	@Override
	public int getTeamAccountsCount(long teamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.getTeamAccountsCount(teamId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account updateAccount(
			long accountId, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String language,
			boolean internal, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.updateAccount(
			accountId, parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, language, internal, status);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account updateAccount(
			String accountKey, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String language,
			boolean internal, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountService.updateAccount(
			accountKey, parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, language, internal, status);
	}

	@Override
	public AccountService getWrappedService() {
		return _accountService;
	}

	@Override
	public void setWrappedService(AccountService accountService) {
		_accountService = accountService;
	}

	private AccountService _accountService;

}
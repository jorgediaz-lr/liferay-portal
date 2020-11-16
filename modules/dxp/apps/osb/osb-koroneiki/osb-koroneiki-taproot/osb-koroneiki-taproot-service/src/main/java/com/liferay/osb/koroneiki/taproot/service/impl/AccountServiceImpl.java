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

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.osb.koroneiki.taproot.service.base.AccountServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=Account"
	},
	service = AopService.class
)
public class AccountServiceImpl extends AccountServiceBaseImpl {

	public Account addAccount(
			long parentAccountId, String name, String code, String description,
			long logoId, String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String language, boolean internal, String status)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), TaprootActionKeys.ADD_ACCOUNT);

		return accountLocalService.addAccount(
			getUserId(), parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, language, internal, status);
	}

	public Account deleteAccount(long accountId) throws PortalException {
		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.DELETE);

		return accountLocalService.deleteAccount(accountId);
	}

	public Account deleteAccount(String accountKey) throws PortalException {
		Account account = accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.DELETE);

		return accountLocalService.deleteAccount(account);
	}

	public Account getAccount(long accountId) throws PortalException {
		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return accountLocalService.getAccount(accountId);
	}

	public Account getAccount(String accountKey) throws PortalException {
		Account account = accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return account;
	}

	public List<Account> getAccounts(long parentAccountId, int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), parentAccountId, ActionKeys.VIEW);

		return accountLocalService.getAccounts(parentAccountId, start, end);
	}

	public List<Account> getAccounts(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		List<Account> accounts = new ArrayList<>();

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				classNameLocalService.getClassNameId(Account.class), domain,
				entityName, entityId, start, end);

		for (ExternalLink externalLink : externalLinks) {
			accounts.add(getAccount(externalLink.getClassPK()));
		}

		return accounts;
	}

	public int getAccountsCount(long parentAccountId) throws PortalException {
		_accountPermission.check(
			getPermissionChecker(), parentAccountId, ActionKeys.VIEW);

		return accountLocalService.getAccountsCount(parentAccountId);
	}

	public int getAccountsCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return _externalLinkLocalService.getExternalLinksCount(
			classNameLocalService.getClassNameId(Account.class), domain,
			entityName, entityId);
	}

	public List<Account> getContactAccounts(long contactId, int start, int end)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return accountLocalService.getContactAccounts(contactId, start, end);
	}

	public int getContactAccountsCount(long contactId) throws PortalException {
		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return accountLocalService.getContactAccountsCount(contactId);
	}

	public List<Account> getTeamAccounts(long teamId, int start, int end)
		throws PortalException {

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		return accountLocalService.getTeamAccounts(teamId, start, end);
	}

	public int getTeamAccountsCount(long teamId) throws PortalException {
		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		return accountLocalService.getTeamAccountsCount(teamId);
	}

	public Account updateAccount(
			long accountId, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String language,
			boolean internal, String status)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.UPDATE);

		return accountLocalService.updateAccount(
			accountId, parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, language, internal, status);
	}

	public Account updateAccount(
			String accountKey, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String language,
			boolean internal, String status)
		throws PortalException {

		Account account = accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.UPDATE);

		return accountLocalService.updateAccount(
			account.getAccountId(), parentAccountId, name, code, description,
			logoId, contactEmailAddress, profileEmailAddress, phoneNumber,
			faxNumber, website, tier, region, language, internal, status);
	}

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ContactPermission _contactPermission;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private TeamPermission _teamPermission;

}
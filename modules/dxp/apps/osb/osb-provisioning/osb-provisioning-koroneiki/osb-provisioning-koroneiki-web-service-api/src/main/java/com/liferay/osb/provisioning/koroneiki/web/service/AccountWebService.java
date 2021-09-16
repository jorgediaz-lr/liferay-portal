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

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface AccountWebService {

	public Account addAccount(
			String agentName, String agentUID, Account account)
		throws Exception;

	public void assignContactRolesByEmailAddress(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public void assignContactRolesByUuid(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void assignTeamRoles(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception;

	public Account fetchAccount(String accountKey) throws Exception;

	public Account getAccount(String accountKey) throws Exception;

	public List<Account> getAccounts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception;

	public long getContactAccountsCount(String contactUuid) throws Exception;

	public List<Account> search(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception;

	public long searchCount(String search, String filterString)
		throws Exception;

	public void unassignContactRolesByEmailAddress(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public void unassignContactRolesByUuid(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void unassignCustomerContact(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress)
		throws Exception;

	public void unassignTeamRoles(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception;

	public void unassignWorkerContact(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress)
		throws Exception;

	public Account updateAccount(
			String agentName, String agentUID, String accountKey,
			Account account)
		throws Exception;

}
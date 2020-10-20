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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = RoleMigration.class)
public class RoleMigration {

	public Long getAccountCustomerContactRoleId(int role) {
		return _accountCustomerRoleMap.get(role);
	}

	public Long getAccountWorkerContactRoleId(int role) {
		return _accountWorkerRoleMap.get(role);
	}

	public Long getPartnerWorkerContactRoleId(int role) {
		return _partnerWorkerRoleMap.get(role);
	}

	public Long getPortalContactRoleId(long roleId) {
		return _portalRoleMap.get(roleId);
	}

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		long partnerManagerContactRoleId = _addCustomerContactRole(
			userId, "Partner Manager");

		_portalRoleMap.put(112936638L, partnerManagerContactRoleId);
		_partnerWorkerRoleMap.put(1, partnerManagerContactRoleId);

		long partnerMemberContactRoleId = _addCustomerContactRole(
			userId, "Partner Member");

		_portalRoleMap.put(112936646L, partnerMemberContactRoleId);
		_partnerWorkerRoleMap.put(2, partnerMemberContactRoleId);

		long partnerWatcherContactRoleId = _addCustomerContactRole(
			userId, "Partner Watcher");

		_portalRoleMap.put(112936656L, partnerWatcherContactRoleId);
		_partnerWorkerRoleMap.put(0, partnerWatcherContactRoleId);
		_partnerWorkerRoleMap.put(3, partnerWatcherContactRoleId);

		_portalRoleMap.put(
			12324522L,
			_addCustomerContactRole(userId, "Marketplace User Admin"));
		_portalRoleMap.put(
			33118240L,
			_addCustomerContactRole(userId, "Marketplace App Buyer"));
		_portalRoleMap.put(
			33118252L,
			_addCustomerContactRole(userId, "Marketplace App Developer"));
		_portalRoleMap.put(
			33118264L, _addCustomerContactRole(userId, "LCS User"));
		_portalRoleMap.put(
			90852750L, _addCustomerContactRole(userId, "Sales Manager"));
		_portalRoleMap.put(
			90852751L, _addCustomerContactRole(userId, "Sales Representative"));
		_portalRoleMap.put(
			106868290L,
			_addCustomerContactRole(userId, "Analytics Cloud Owner"));

		long supportDeveloperContactRoleId = _addCustomerContactRole(
			userId, "Support Developer");

		_accountCustomerRoleMap.put(0, supportDeveloperContactRoleId);
		_accountCustomerRoleMap.put(1, supportDeveloperContactRoleId);
		_accountCustomerRoleMap.put(2, supportDeveloperContactRoleId);

		long supportWatcherContactRoleId = _addCustomerContactRole(
			userId, "Support Watcher");

		_accountCustomerRoleMap.put(3, supportWatcherContactRoleId);
		_accountCustomerRoleMap.put(4, supportWatcherContactRoleId);

		_accountCustomerRoleMap.put(
			100, _addCustomerContactRole(userId, "Support Closed Watcher"));

		ContactRole workerMemberContactRole =
			_contactRoleLocalService.getMemberContactRole(
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
					ACCOUNT_WORKER.toString());

		_accountWorkerRoleMap.put(
			0, workerMemberContactRole.getContactRoleId());

		_accountWorkerRoleMap.put(
			2, _addWorkerContactRole(userId, "Liferay Sales"));
		_accountWorkerRoleMap.put(
			4, _addWorkerContactRole(userId, "Liferay Advocacy Specialist"));
		_accountWorkerRoleMap.put(
			5, _addWorkerContactRole(userId, "Liferay Managed Services"));
		_accountWorkerRoleMap.put(
			6, _addWorkerContactRole(userId, "Liferay Customer Success"));

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private long _addCustomerContactRole(long userId, String name)
		throws Exception {

		ContactRole contactRole = _contactRoleLocalService.addContactRole(
			userId, name, StringPool.BLANK,
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				ACCOUNT_CUSTOMER.toString());

		return contactRole.getContactRoleId();
	}

	private long _addWorkerContactRole(long userId, String name)
		throws Exception {

		ContactRole contactRole = _contactRoleLocalService.addContactRole(
			userId, name, StringPool.BLANK,
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				ACCOUNT_WORKER.toString());

		return contactRole.getContactRoleId();
	}

	private static final Log _log = LogFactoryUtil.getLog(RoleMigration.class);

	private final Map<Integer, Long> _accountCustomerRoleMap = new HashMap<>();
	private final Map<Integer, Long> _accountWorkerRoleMap = new HashMap<>();

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	private final Map<Integer, Long> _partnerWorkerRoleMap = new HashMap<>();
	private final Map<Long, Long> _portalRoleMap = new HashMap<>();

}
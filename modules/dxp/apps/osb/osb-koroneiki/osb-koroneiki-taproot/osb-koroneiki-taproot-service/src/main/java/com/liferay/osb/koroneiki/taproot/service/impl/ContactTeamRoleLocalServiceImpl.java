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

import com.liferay.osb.koroneiki.taproot.exception.ContactRoleTypeException;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.ContactTeamRoleLocalServiceBaseImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.ContactTeamRole",
	service = AopService.class
)
public class ContactTeamRoleLocalServiceImpl
	extends ContactTeamRoleLocalServiceBaseImpl {

	public ContactTeamRole addContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		validate(contactId, teamId, contactRoleId);

		ContactTeamRolePK contactTeamRolePK = new ContactTeamRolePK(
			contactId, teamId, contactRoleId);

		ContactTeamRole contactTeamRole =
			contactTeamRolePersistence.fetchByPrimaryKey(contactTeamRolePK);

		if (contactTeamRole == null) {
			contactTeamRole = contactTeamRolePersistence.create(
				contactTeamRolePK);

			contactTeamRole = contactTeamRolePersistence.update(
				contactTeamRole);

			_contactLocalService.reindex(contactId);

			_teamLocalService.reindex(teamId);
		}

		return contactTeamRole;
	}

	public void deleteAccountTeamContact(long accountId, long contactId)
		throws PortalException {

		List<Team> teams = teamPersistence.findByAccountId(accountId);

		for (Team team : teams) {
			deleteContactTeamRoles(contactId, team.getTeamId());
		}
	}

	public ContactTeamRole deleteContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		ContactTeamRolePK contactTeamRolePK = new ContactTeamRolePK(
			contactId, teamId, contactRoleId);

		ContactTeamRole contactTeamRole =
			contactTeamRolePersistence.fetchByPrimaryKey(contactTeamRolePK);

		if (contactTeamRole != null) {
			deleteContactTeamRole(contactTeamRole);

			_contactLocalService.reindex(contactId);

			_teamLocalService.reindex(teamId);
		}

		return contactTeamRole;
	}

	public void deleteContactTeamRoles(long contactId, long teamId)
		throws PortalException {

		contactTeamRolePersistence.removeByCI_TI(contactId, teamId);

		_contactLocalService.reindex(contactId);

		_teamLocalService.reindex(teamId);
	}

	public List<ContactTeamRole> getContactTeamRoles(long contactId) {
		return contactTeamRolePersistence.findByContactId(contactId);
	}

	public List<ContactTeamRole> getContactTeamRolesByTeamId(long teamId) {
		return contactTeamRolePersistence.findByTeamId(teamId);
	}

	protected void validate(long contactId, long teamId, long contactRoleId)
		throws PortalException {

		contactPersistence.findByPrimaryKey(contactId);

		teamPersistence.findByPrimaryKey(teamId);

		ContactRole contactRole = contactRolePersistence.findByPrimaryKey(
			contactRoleId);

		String type = contactRole.getType();

		if (!type.equals(
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
					TEAM.toString())) {

			throw new ContactRoleTypeException();
		}
	}

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}
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

import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.taproot.exception.TeamNameException;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.TeamLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.Team",
	service = AopService.class
)
public class TeamLocalServiceImpl extends TeamLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public Team addTeam(
			long userId, long accountId, String name, boolean system)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(0, accountId, name);

		long teamId = counterLocalService.increment();

		Team team = teamPersistence.create(teamId);

		team.setCompanyId(user.getCompanyId());
		team.setUserId(userId);
		team.setTeamKey(ModelKeyGenerator.generate(teamId));
		team.setAccountId(accountId);
		team.setName(name);
		team.setSystem(system);

		team = teamPersistence.update(team);

		// Resources

		resourceLocalService.addResources(
			team.getCompanyId(), 0, userId, Team.class.getName(),
			team.getTeamId(), false, false, false);

		return team;
	}

	@Override
	public Team deleteTeam(long teamId) throws PortalException {
		return deleteTeam(teamLocalService.getTeam(teamId));
	}

	@Override
	public Team deleteTeam(Team team) throws PortalException {

		// Contact team roles

		contactTeamRolePersistence.removeByTeamId(team.getTeamId());

		// External links

		_externalLinkLocalService.deleteExternalLinks(
			classNameLocalService.getClassNameId(Team.class), team.getTeamId());

		// Resources

		resourceLocalService.deleteResource(
			team.getCompanyId(), Team.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, team.getTeamId());

		// Team account roles

		teamAccountRolePersistence.removeByTeamId(team.getTeamId());

		return teamPersistence.remove(team);
	}

	public List<Team> getAccountAssignedTeams(
		long accountId, int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("account", accountId);

		return teamFinder.findByName(null, params, start, end);
	}

	public int getAccountAssignedTeamsCount(long accountId) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("account", accountId);

		return teamFinder.countByName(null, params);
	}

	public List<Team> getAccountTeams(long accountId, int start, int end) {
		return teamPersistence.findByAccountId(accountId, start, end);
	}

	public int getAccountTeamsCount(long accountId) {
		return teamPersistence.countByAccountId(accountId);
	}

	public List<Team> getContactTeams(long contactId, int start, int end) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("contact", contactId);

		return teamFinder.findByName(null, params, start, end);
	}

	public Team getDefaultTeam(long accountId) throws PortalException {
		return teamPersistence.findByAI_S(accountId, true);
	}

	public Team getTeam(String teamKey) throws PortalException {
		return teamPersistence.findByTeamKey(teamKey);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Team reindex(long teamId) throws PortalException {
		return teamPersistence.findByPrimaryKey(teamId);
	}

	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		try {
			Indexer<Team> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				Team.class);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(false);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put("accountKey", keywords);
			attributes.put("externalLinkEntityIds", keywords);
			attributes.put("name", keywords);

			searchContext.setAttributes(attributes);

			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);

			if (sort != null) {
				searchContext.setSorts(sort);
			}

			searchContext.setStart(start);

			QueryConfig queryConfig = searchContext.getQueryConfig();

			queryConfig.setHighlightEnabled(false);
			queryConfig.setScoreEnabled(false);

			return indexer.search(searchContext);
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	public Team syncDefaultTeam(long accountId) throws PortalException {
		Account account = accountPersistence.findByPrimaryKey(accountId);

		Team team = teamPersistence.fetchByAI_S(account.getAccountId(), true);

		if (team == null) {
			team = addTeam(
				account.getUserId(), account.getAccountId(), account.getName(),
				true);
		}
		else {
			team = updateTeam(team.getTeamId(), account.getName());
		}

		List<Contact> accountContacts = _contactLocalService.getAccountContacts(
			accountId,
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				ACCOUNT_CUSTOMER.toString(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<Contact> teamContacts = _contactLocalService.getTeamContacts(
			team.getTeamId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		teamContacts = ListUtil.copy(teamContacts);

		ContactRole contactRole = _contactRoleLocalService.getMemberContactRole(
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
				TEAM.toString());

		for (Contact contact : accountContacts) {
			if (teamContacts.remove(contact)) {
				continue;
			}

			_contactTeamRoleLocalService.addContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}

		for (Contact contact : teamContacts) {
			_contactTeamRoleLocalService.deleteContactTeamRoles(
				contact.getContactId(), team.getTeamId());
		}

		return team;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Team updateTeam(long teamId, String name) throws PortalException {
		Team team = teamPersistence.findByPrimaryKey(teamId);

		validate(teamId, team.getAccountId(), name);

		team.setName(name);

		return teamPersistence.update(team);
	}

	protected void validate(long teamId, long accountId, String name)
		throws PortalException {

		Account account = accountPersistence.findByPrimaryKey(accountId);

		if (Validator.isNull(name)) {
			throw new TeamNameException();
		}

		Team team = teamPersistence.fetchByAI_N(accountId, name);

		if ((team != null) && (team.getTeamId() != teamId)) {
			throw new TeamNameException.MustNotBeDuplicate(
				name, account.getName());
		}
	}

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

}
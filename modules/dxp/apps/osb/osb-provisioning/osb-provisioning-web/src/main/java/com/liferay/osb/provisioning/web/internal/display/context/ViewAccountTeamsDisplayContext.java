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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ViewAccountTeamsDisplayContext extends ViewAccountDisplayContext {

	public ViewAccountTeamsDisplayContext() {
	}

	public CreationMenu getCreationMenu() throws Exception {
		if (!hasManageAccountsPermission()) {
			return null;
		}

		return new CreationMenu() {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName", "/accounts/edit_team",
							"redirect", getCurrentURL(), "accountKey",
							account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "new-team"));
					});
			}
		};
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-teams-were-found");

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", account.getKey());

		List<Team> teams = teamWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				teams,
				team -> new TeamDisplay(
					renderRequest, renderResponse, team,
					_getAssignedAccountsCount(
						team, TeamRoleConstants.NAME_FIRST_LINE_SUPPORT),
					_getAssignedAccountsCount(
						team, TeamRoleConstants.NAME_PARTNER))));

		int count = (int)teamWebService.searchCount(keywords, filterQuery);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private long _getAssignedAccountsCount(Team team, String teamRoleName)
		throws Exception {

		TeamRole teamRole = teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(), teamRoleName);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "assignedTeamKeyTeamRoleKeys",
			team.getKey() + "_" + teamRole.getKey());

		return accountWebService.searchCount(StringPool.BLANK, filterQuery);
	}

}
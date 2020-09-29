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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Kyle Bischof
 */
public class AssignAccountTeamDisplayContext extends ViewAccountDisplayContext {

	public AssignAccountTeamDisplayContext() {
	}

	public String getClearResultsURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/assign_account_team");
		portletURL.setParameter("accountKey", account.getKey());

		return portletURL.toString();
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-teams-were-found");

		List<Team> teams = teamWebService.search(
			keywords, "accountEntitlements/any(k:contains(k,'Partner'))",
			searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				teams,
				team -> new TeamDisplay(renderRequest, renderResponse, team)));

		int count = (int)teamWebService.searchCount(
			keywords, "accountEntitlements/any(k:contains(k,'Partner'))");

		searchContainer.setTotal(count);

		return searchContainer;
	}

}
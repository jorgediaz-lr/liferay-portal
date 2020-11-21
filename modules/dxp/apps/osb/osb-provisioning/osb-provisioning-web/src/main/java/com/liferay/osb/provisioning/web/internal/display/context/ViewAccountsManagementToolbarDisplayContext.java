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

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.web.internal.search.AccountDisplayTerm;
import com.liferay.osb.provisioning.web.internal.search.AccountDisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ViewAccountsManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewAccountsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest, SearchContainer searchContainer,
		AccountWebService accountWebService, TeamWebService teamWebService) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer);

		_accountWebService = accountWebService;
		_teamWebService = teamWebService;
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = liferayPortletResponse.createRenderURL();

		return clearResultsURL.toString();
	}

	@Override
	public List<LabelItem> getFilterLabelItems() {
		AccountDisplayTerms accountDisplayTerms =
			(AccountDisplayTerms)searchContainer.getDisplayTerms();

		if (!accountDisplayTerms.isAdvancedSearch()) {
			return null;
		}

		return new LabelItemList() {
			{
				Map<String, AccountDisplayTerm> termsMap =
					accountDisplayTerms.getTermsMap();

				for (Map.Entry<String, AccountDisplayTerm> entry :
						termsMap.entrySet()) {

					AccountDisplayTerm term = entry.getValue();

					String name = term.getName();

					String[] values = StringUtil.split(term.getValue());

					for (String value : values) {
						add(
							labelItem -> {
								PortletURL removeLabelURL = getPortletURL();

								String[] removeKeywords = ArrayUtil.remove(
									values, value);

								removeLabelURL.setParameter(
									name, StringUtil.merge(removeKeywords));

								labelItem.putData(
									"removeLabelURL",
									removeLabelURL.toString());

								labelItem.setCloseable(true);

								labelItem.setLabel(_getLabel(name, value));
							});
					}
				}
			}
		};
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "accountSearch";
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

	private String _getAccountName(String accountKey) {
		try {
			Account account = _accountWebService.getAccount(accountKey);

			return account.getName();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return accountKey;
		}
	}

	private String _getLabel(String key, String value) {
		if (key.equals("first-line-support") ||
			key.equals("partner-reseller-si")) {

			value = _getTeamName(value);
		}
		else if (key.equals("parent-account")) {
			value = _getAccountName(value);
		}

		if (value.equals(StringPool.TRUE)) {
			value = LanguageUtil.get(request, "yes");
		}
		else if (value.equals(StringPool.FALSE)) {
			value = LanguageUtil.get(request, "no");
		}

		return String.format("%s: %s", LanguageUtil.get(request, key), value);
	}

	private String _getTeamName(String teamKey) {
		try {
			Team team = _teamWebService.getTeam(teamKey);

			return team.getName();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return teamKey;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewAccountsManagementToolbarDisplayContext.class);

	private final AccountWebService _accountWebService;
	private final TeamWebService _teamWebService;

}
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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.identity.management.provider.IdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.web.internal.search.AccountSearch;
import com.liferay.osb.provisioning.web.internal.search.AccountSearchTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class AccountSearchDisplayContext {

	public AccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest, AccountReader accountReader,
		AccountWebService accountWebService, CountryService countryService,
		IdentityProvider identityProvider,
		TeamRoleWebService teamRoleWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountReader = accountReader;
		_accountWebService = accountWebService;
		_countryService = countryService;
		_identityProvider = identityProvider;
		_teamRoleWebService = teamRoleWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = _currentURLObj;

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	public Map<String, Object> getData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL accountsHomeURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		data.put("accountsHomeURL", accountsHomeURL.toString());

		data.put(
			"activeSLANames",
			new ArrayList<String>() {
				{
					add(EntitlementConstants.GOLD_SUBSCRIPTION);
					add(EntitlementConstants.PLATINUM_SUBSCRIPTION);
					add(EntitlementConstants.SILVER_SUBSCRIPTION);
				}
			});

		List<Country> countries = _countryService.getCountries();

		Stream<Country> stream = countries.stream();

		data.put(
			"countryNames",
			stream.map(
				country -> country.getName(LocaleUtil.US)
			).collect(
				Collectors.toList()
			));

		List<String> regionNames = new ArrayList<>();

		for (Account.Region region : Account.Region.values()) {
			regionNames.add(region.toString());
		}

		data.put("regionNames", regionNames);

		ResourceURL autocompleteAccountURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RESOURCE_PHASE);

		autocompleteAccountURL.setResourceID("/accounts/autocomplete");

		data.put("resourceURL", autocompleteAccountURL.toString());

		PortletURL selectAccountURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		selectAccountURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_account");
		selectAccountURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("selectAccountURL", selectAccountURL.toString());

		PortletURL selectPartnerURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		selectPartnerURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_team");
		selectPartnerURL.setParameter("partner", Boolean.TRUE.toString());
		selectPartnerURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("selectPartnerURL", selectPartnerURL.toString());

		PortletURL selectFirstLineSupportURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		selectFirstLineSupportURL.setParameter(
			"mvcRenderCommandName", "/accounts/select_team");
		selectFirstLineSupportURL.setParameter(
			"partner", Boolean.TRUE.toString());
		selectFirstLineSupportURL.setWindowState(LiferayWindowState.POP_UP);

		data.put(
			"selectFirstLineSupportURL", selectFirstLineSupportURL.toString());

		List<String> statusNames = new ArrayList<>();

		for (Account.Status status : Account.Status.values()) {
			statusNames.add(status.toString());
		}

		data.put("statusNames", statusNames);

		List<String> tierNames = new ArrayList<>();

		for (Account.Tier tier : Account.Tier.values()) {
			tierNames.add(tier.toString());
		}

		data.put("tierNames", tierNames);

		return data;
	}

	public SearchContainer getSearchContainer() throws Exception {
		if (_accountSearch != null) {
			return _accountSearch;
		}

		_accountSearch = new AccountSearch(_renderRequest, _currentURLObj);

		AccountSearchTerms searchTerms =
			(AccountSearchTerms)_accountSearch.getSearchTerms();

		String filter = null;

		if (searchTerms.isAdvancedSearch()) {
			String createdByUuid = null;

			String createdByEmailAddress =
				searchTerms.getCreatedByEmailAddress();

			if (Validator.isNotNull(createdByEmailAddress)) {
				JSONObject jsonObject = _identityProvider.fetchByEmailAddress(
					createdByEmailAddress);

				if (jsonObject != null) {
					createdByUuid = jsonObject.getString("uuid");
				}
			}

			TeamRole flsTeamRole = _teamRoleWebService.getTeamRole(
				TeamRoleConstants.NAME_FIRST_LINE_SUPPORT,
				TeamRole.Type.ACCOUNT.toString());
			TeamRole partnerTeamRole = _teamRoleWebService.getTeamRole(
				TeamRoleConstants.NAME_PARTNER,
				TeamRole.Type.ACCOUNT.toString());

			filter = searchTerms.getAdvancedSearchFilter(
				createdByUuid, flsTeamRole.getKey(), partnerTeamRole.getKey());
		}
		else {
			filter = searchTerms.getBasicSearchFilter();
		}

		String sort = StringPool.BLANK;

		if (!searchTerms.hasSearchTerms()) {
			sort = "name";
		}

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, filter, _accountSearch.getCur(),
			_accountSearch.getEnd() - _accountSearch.getStart(), sort);

		_accountSearch.setResults(
			TransformUtil.transform(
				accounts,
				account -> new AccountDisplay(
					_renderRequest, _renderResponse, _accountReader, account)));

		int count = (int)_accountWebService.searchCount(
			StringPool.BLANK, filter);

		_accountSearch.setTotal(count);

		return _accountSearch;
	}

	private final AccountReader _accountReader;
	private AccountSearch _accountSearch;
	private final AccountWebService _accountWebService;
	private final CountryService _countryService;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final IdentityProvider _identityProvider;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final TeamRoleWebService _teamRoleWebService;

}
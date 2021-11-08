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
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.dao.search.AccountResultRowSplitter;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ViewAccountRelatedAccountsDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountRelatedAccountsDisplayContext() {
	}

	public AccountResultRowSplitter getAccountResultRowSplitter() {
		return new AccountResultRowSplitter(account);
	}

	public List<AccountDisplay> getChildAccountDisplays() throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(false, "parentAccountKey", account.getKey());

		return TransformUtil.transform(
			accountWebService.search(
				StringPool.BLANK, filterQuery, 1, 1000, "name"),
			account -> new AccountDisplay(
				renderRequest, renderResponse, accountReader, account));
	}

	public AccountDisplay getParentAccountDisplay() throws Exception {
		if (Validator.isNotNull(account.getParentAccountKey())) {
			Account parentAccount = accountWebService.fetchAccount(
				account.getParentAccountKey());

			if (parentAccount != null) {
				return new AccountDisplay(
					renderRequest, renderResponse, accountReader,
					parentAccount);
			}
		}

		return null;
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-accounts-were-found");

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		FilterQuery filterQuery = null;

		String tabs2 = ParamUtil.getString(renderRequest, "tabs2");

		if (!tabs2.equals("all")) {
			filterQuery = _getFilterQuery(tabs2);
		}
		else {
			filterQuery = _getFilterQuery(StringPool.BLANK);
		}

		List<Account> accounts = accountWebService.search(
			keywords, filterQuery, 1, 1000, "name");

		searchContainer.setResults(
			TransformUtil.transform(
				accounts,
				account -> new AccountDisplay(
					renderRequest, renderResponse, accountReader, account)));

		searchContainer.setTotal(accounts.size());

		return searchContainer;
	}

	public String getTabsNames() throws Exception {
		List<String> tabsNames = new ArrayList<>();

		long allAccountsCount = accountWebService.searchCount(
			StringPool.BLANK, _getFilterQuery(StringPool.BLANK));

		tabsNames.add(getTabName("all", allAccountsCount));

		long activeAccountsCount = accountWebService.searchCount(
			StringPool.BLANK,
			_getFilterQuery(Account.Status.ACTIVE.toString()));

		tabsNames.add(getTabName("active", activeAccountsCount));

		long closedAccountsCount = accountWebService.searchCount(
			StringPool.BLANK,
			_getFilterQuery(Account.Status.CLOSED.toString()));

		tabsNames.add(getTabName("closed", closedAccountsCount));

		return StringUtil.merge(tabsNames);
	}

	public String getTabsValues() {
		return StringBundler.concat(
			"all,", Account.Status.ACTIVE.toString(), ",",
			Account.Status.CLOSED.toString());
	}

	private FilterQuery _getFilterQuery(String status) {
		FilterQuery filterQuery = new FilterQuery();

		if (Validator.isNotNull(status)) {
			filterQuery.addEquals(true, "status", status);
		}

		if (Validator.isNotNull(account.getParentAccountKey())) {
			filterQuery.addEquals(
				false, "accountKey", account.getParentAccountKey());

			FilterQuery nestedFilterQuery = new FilterQuery();

			nestedFilterQuery.addEquals(
				true, "accountKey", account.getKey(), true);
			nestedFilterQuery.addEquals(
				true, "parentAccountKey", account.getParentAccountKey());

			filterQuery.addFilterQuery(false, nestedFilterQuery);
		}

		filterQuery.addEquals(false, "parentAccountKey", account.getKey());

		return filterQuery;
	}

}
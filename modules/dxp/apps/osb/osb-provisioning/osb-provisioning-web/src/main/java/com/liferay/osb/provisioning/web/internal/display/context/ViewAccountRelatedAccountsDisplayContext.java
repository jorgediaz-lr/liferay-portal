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
		StringBundler sb = new StringBundler(3);

		sb.append("parentAccountKey eq '");
		sb.append(account.getKey());
		sb.append("'");

		return TransformUtil.transform(
			accountWebService.search(
				StringPool.BLANK, sb.toString(), 1, 1000, "name"),
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

		String filter = null;

		String tabs2 = ParamUtil.getString(renderRequest, "tabs2");

		if (!tabs2.equals("all")) {
			filter = _getFilter(tabs2);
		}
		else {
			filter = _getFilter(StringPool.BLANK);
		}

		List<Account> accounts = accountWebService.search(
			keywords, filter, 1, 1000, "name");

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
			StringPool.BLANK, _getFilter(StringPool.BLANK));

		tabsNames.add(getTabName("all", allAccountsCount));

		long activeAccountsCount = accountWebService.searchCount(
			StringPool.BLANK, _getFilter(Account.Status.ACTIVE.toString()));

		tabsNames.add(getTabName("active", activeAccountsCount));

		long closedAccountsCount = accountWebService.searchCount(
			StringPool.BLANK, _getFilter(Account.Status.CLOSED.toString()));

		tabsNames.add(getTabName("closed", closedAccountsCount));

		return StringUtil.merge(tabsNames);
	}

	public String getTabsValues() {
		return StringBundler.concat(
			"all,", Account.Status.ACTIVE.toString(), ",",
			Account.Status.CLOSED.toString());
	}

	private String _getFilter(String status) {
		StringBundler sb = new StringBundler(14);

		sb.append("(");

		if (Validator.isNotNull(account.getParentAccountKey())) {
			sb.append("accountKey eq '");
			sb.append(account.getParentAccountKey());
			sb.append("' or (parentAccountKey eq '");
			sb.append(account.getParentAccountKey());
			sb.append("' and accountKey ne '");
			sb.append(account.getKey());
			sb.append("') or ");
		}

		sb.append("parentAccountKey eq '");
		sb.append(account.getKey());
		sb.append("') ");

		if (Validator.isNotNull(status)) {
			sb.append(" and status eq '");
			sb.append(status);
			sb.append("'");
		}

		return sb.toString();
	}

}
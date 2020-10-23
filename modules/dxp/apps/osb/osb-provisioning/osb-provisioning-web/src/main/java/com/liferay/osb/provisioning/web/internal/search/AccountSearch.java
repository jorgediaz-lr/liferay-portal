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

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.Collections;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class AccountSearch extends SearchContainer<AccountDisplay> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-accounts-were-found";

	public AccountSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new AccountDisplayTerms(portletRequest),
			new AccountSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, Collections.emptyList(),
			EMPTY_RESULTS_MESSAGE);

		AccountDisplayTerms displayTerms =
			(AccountDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			AccountDisplayTerms.ACTIVE_SLAS, displayTerms.getActiveSLAs());
		iteratorURL.setParameter(
			AccountDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			AccountDisplayTerms.COUNTRY_NAME, displayTerms.getCountryName());
		iteratorURL.setParameter(
			AccountDisplayTerms.CREATE_DATE_GT, displayTerms.getCreateDateGT());
		iteratorURL.setParameter(
			AccountDisplayTerms.CREATE_DATE_LT, displayTerms.getCreateDateLT());
		iteratorURL.setParameter(
			AccountDisplayTerms.CREATED_BY_EMAIL_ADDRESS,
			displayTerms.getCreatedByEmailAddress());
		iteratorURL.setParameter(
			AccountDisplayTerms.FLS_TEAM_KEY, displayTerms.getFLSTeamKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.INTERNAL,
			String.valueOf(displayTerms.internal));
		iteratorURL.setParameter(
			AccountDisplayTerms.MODIFIED_DATE_GT,
			displayTerms.getModifiedDateGT());
		iteratorURL.setParameter(
			AccountDisplayTerms.MODIFIED_DATE_LT,
			displayTerms.getModifiedDateLT());
		iteratorURL.setParameter(
			AccountDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			AccountDisplayTerms.PARENT_ACCOUNT_KEY,
			displayTerms.getParentAccountKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.PARTNER, String.valueOf(displayTerms.partner));
		iteratorURL.setParameter(
			AccountDisplayTerms.PARTNER_TEAM_KEY,
			displayTerms.getPartnerTeamKey());
		iteratorURL.setParameter(
			AccountDisplayTerms.PROVIDES_FLS,
			String.valueOf(displayTerms.providesFLS));
		iteratorURL.setParameter(
			AccountDisplayTerms.RECEIVES_FLS,
			String.valueOf(displayTerms.receivesFLS));
		iteratorURL.setParameter(
			AccountDisplayTerms.REGIONS, displayTerms.getRegions());
		iteratorURL.setParameter(
			AccountDisplayTerms.SUBSCRIPTION_STATES,
			displayTerms.getSubscriptionStates());
		iteratorURL.setParameter(
			AccountDisplayTerms.TIERS, displayTerms.getTiers());
		iteratorURL.setParameter(
			AccountDisplayTerms.WORKER_CONTACT_EMAIL_ADDRESS,
			displayTerms.getWorkerContactEmailAddress());
	}

}
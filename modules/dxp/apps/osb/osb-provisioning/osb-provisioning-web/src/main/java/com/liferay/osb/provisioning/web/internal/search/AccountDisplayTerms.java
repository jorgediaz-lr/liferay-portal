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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountDisplayTerms extends DisplayTerms {

	public static final String ACTIVE_SLAS = "activeSLAs";

	public static final String CODE = "code";

	public static final String COUNTRY_NAME = "countryName";

	public static final String CREATE_DATE_GT = "createDateGT";

	public static final String CREATE_DATE_LT = "createDateLT";

	public static final String CREATED_BY_EMAIL_ADDRESS =
		"createdByEmailAddress";

	public static final String FLS_TEAM_KEY = "flsTeamKey";

	public static final String INTERNALS = "internals";

	public static final String MODIFIED_DATE_GT = "modifiedDateGT";

	public static final String MODIFIED_DATE_LT = "modifiedDateLT";

	public static final String NAME = "name";

	public static final String PARENT_ACCOUNT_KEY = "parentAccountKey";

	public static final String PARTNER_TEAM_KEY = "partnerTeamKey";

	public static final String PARTNERS = "partners";

	public static final String PROVIDES_FLS = "providesFLS";

	public static final String RECEIVES_FLS = "receivesFLS";

	public static final String REGIONS = "regions";

	public static final String SUBSCRIPTION_STATES = "subscriptionStates";

	public static final String TIERS = "tiers";

	public static final String WORKER_CONTACT_EMAIL_ADDRESS =
		"workerContactEmailAddress";

	public AccountDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		if (Validator.isNull(keywords)) {
			keywords = ParamUtil.getString(
				portletRequest, "accountSearchKeywords");
		}

		activeSLAs = ParamUtil.getStringValues(portletRequest, ACTIVE_SLAS);
		code = ParamUtil.getString(portletRequest, CODE);
		countryName = ParamUtil.getString(portletRequest, COUNTRY_NAME);
		createDateGT = ParamUtil.getString(portletRequest, CREATE_DATE_GT);
		createDateLT = ParamUtil.getString(portletRequest, CREATE_DATE_LT);
		createdByEmailAddress = ParamUtil.getString(
			portletRequest, CREATED_BY_EMAIL_ADDRESS);
		flsTeamKey = ParamUtil.getString(portletRequest, FLS_TEAM_KEY);
		internals = ParamUtil.getBooleanValues(portletRequest, INTERNALS);
		modifiedDateGT = ParamUtil.getString(portletRequest, MODIFIED_DATE_GT);
		modifiedDateLT = ParamUtil.getString(portletRequest, MODIFIED_DATE_LT);
		name = ParamUtil.getString(portletRequest, NAME);
		parentAccountKey = ParamUtil.getString(
			portletRequest, PARENT_ACCOUNT_KEY);
		partners = ParamUtil.getBooleanValues(portletRequest, PARTNERS);
		partnerTeamKey = ParamUtil.getString(portletRequest, PARTNER_TEAM_KEY);
		providesFLS = ParamUtil.getBooleanValues(portletRequest, PROVIDES_FLS);
		receivesFLS = ParamUtil.getBooleanValues(portletRequest, RECEIVES_FLS);
		regions = ParamUtil.getStringValues(portletRequest, REGIONS);
		subscriptionStates = ParamUtil.getStringValues(
			portletRequest, SUBSCRIPTION_STATES);
		tiers = ParamUtil.getStringValues(portletRequest, TIERS);
		workerContactEmailAddress = ParamUtil.getString(
			portletRequest, WORKER_CONTACT_EMAIL_ADDRESS);
	}

	public String[] getActiveSLAs() {
		return activeSLAs;
	}

	public String getCode() {
		return code;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getCreateDateGT() {
		return createDateGT;
	}

	public String getCreateDateLT() {
		return createDateLT;
	}

	public String getCreatedByEmailAddress() {
		return createdByEmailAddress;
	}

	public String getFLSTeamKey() {
		return flsTeamKey;
	}

	public boolean[] getInternals() {
		return internals;
	}

	public String getModifiedDateGT() {
		return modifiedDateGT;
	}

	public String getModifiedDateLT() {
		return modifiedDateLT;
	}

	public String getName() {
		return name;
	}

	public String getParentAccountKey() {
		return parentAccountKey;
	}

	public boolean[] getPartners() {
		return partners;
	}

	public String getPartnerTeamKey() {
		return partnerTeamKey;
	}

	public boolean[] getProvidesFLS() {
		return providesFLS;
	}

	public boolean[] getReceivesFLS() {
		return receivesFLS;
	}

	public String[] getRegions() {
		return regions;
	}

	public String[] getSubscriptionStates() {
		return subscriptionStates;
	}

	public List<AccountDisplayTerm> getTermsList() {
		return new ArrayList<>(
			Arrays.asList(
				new AccountDisplayTerm("account-name", NAME, name),
				new AccountDisplayTerm("code", CODE, code),
				new AccountDisplayTerm("country", COUNTRY_NAME, countryName),
				new AccountDisplayTerm(
					"created-after", CREATE_DATE_GT, createDateGT),
				new AccountDisplayTerm(
					"created-before", CREATE_DATE_LT, createDateLT),
				new AccountDisplayTerm(
					"created-by", CREATED_BY_EMAIL_ADDRESS,
					createdByEmailAddress),
				new AccountDisplayTerm(
					"first-line-support", FLS_TEAM_KEY, flsTeamKey),
				new AccountDisplayTerm(
					"internal", INTERNALS, StringUtil.merge(internals)),
				new AccountDisplayTerm(
					"modified-after", MODIFIED_DATE_GT, modifiedDateGT),
				new AccountDisplayTerm(
					"modified-before", MODIFIED_DATE_LT, modifiedDateLT),
				new AccountDisplayTerm(
					"parent-account", PARENT_ACCOUNT_KEY, parentAccountKey),
				new AccountDisplayTerm(
					"partner", PARTNERS, StringUtil.merge(partners)),
				new AccountDisplayTerm(
					"partner-reseller-si", PARTNER_TEAM_KEY, partnerTeamKey),
				new AccountDisplayTerm(
					"project-worker", WORKER_CONTACT_EMAIL_ADDRESS,
					workerContactEmailAddress),
				new AccountDisplayTerm(
					"provides-fls", PROVIDES_FLS,
					StringUtil.merge(providesFLS)),
				new AccountDisplayTerm(
					"receives-fls", RECEIVES_FLS,
					StringUtil.merge(receivesFLS)),
				new AccountDisplayTerm(
					"subscription-level", ACTIVE_SLAS,
					StringUtil.merge(activeSLAs)),
				new AccountDisplayTerm(
					"subscription-status", SUBSCRIPTION_STATES,
					StringUtil.merge(subscriptionStates)),
				new AccountDisplayTerm(
					"support-region", REGIONS, StringUtil.merge(regions)),
				new AccountDisplayTerm(
					"tier", TIERS, StringUtil.merge(tiers))));
	}

	public String[] getTiers() {
		return tiers;
	}

	public String getWorkerContactEmailAddress() {
		return workerContactEmailAddress;
	}

	protected String[] activeSLAs;
	protected String code;
	protected String countryName;
	protected String createDateGT;
	protected String createDateLT;
	protected String createdByEmailAddress;
	protected String flsTeamKey;
	protected boolean[] internals;
	protected String modifiedDateGT;
	protected String modifiedDateLT;
	protected String name;
	protected String parentAccountKey;
	protected boolean[] partners;
	protected String partnerTeamKey;
	protected boolean[] providesFLS;
	protected boolean[] receivesFLS;
	protected String[] regions;
	protected String[] subscriptionStates;
	protected String[] tiers;
	protected String workerContactEmailAddress;

}
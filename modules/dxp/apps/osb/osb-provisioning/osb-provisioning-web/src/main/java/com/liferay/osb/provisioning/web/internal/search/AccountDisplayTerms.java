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
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

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

	public Map<String, AccountDisplayTerm> getTermsMap() {
		Map<String, AccountDisplayTerm> termsMap = new HashMap<>();

		termsMap.put(
			"account-name", new AccountDisplayTerm("account-name", NAME, name));
		//termsMap.put("account-name", _getTermMap(NAME, name));

		// termsMap.put("code", _getTermMap(CODE, code));
		// termsMap.put("country", _getTermMap(COUNTRY_NAME, countryName));
		// termsMap.put(
		// 	"created-after", _getTermMap(CREATE_DATE_GT, createDateGT));
		// termsMap.put(
		// 	"created-before", _getTermMap(CREATE_DATE_LT, createDateLT));
		// termsMap.put(
		// 	"created-by",
		// 	_getTermMap(CREATED_BY_EMAIL_ADDRESS, createdByEmailAddress));
		// termsMap.put(
		// 	"first-line-support", _getTermMap(FLS_TEAM_KEY, flsTeamKey));
		// termsMap.put(
		// 	"internal", _getTermMap(INTERNALS, StringUtil.merge(internals)));
		// termsMap.put(
		// 	"modified-after", _getTermMap(MODIFIED_DATE_GT, modifiedDateGT));
		// termsMap.put(
		// 	"modified-before", _getTermMap(MODIFIED_DATE_LT, modifiedDateLT));
		// termsMap.put(
		// 	"parent-account",
		// 	_getTermMap(PARENT_ACCOUNT_KEY, parentAccountKey));
		// termsMap.put(
		// 	"partner", _getTermMap(PARTNERS, StringUtil.merge(partners)));
		// termsMap.put(
		// 	"partner-reseller-si",
		// 	_getTermMap(PARTNER_TEAM_KEY, partnerTeamKey));
		// termsMap.put(
		// 	"project-worker",
		// 	_getTermMap(
		// 		WORKER_CONTACT_EMAIL_ADDRESS, workerContactEmailAddress));
		// termsMap.put(
		// 	"provides-fls",
		// 	_getTermMap(PROVIDES_FLS, StringUtil.merge(providesFLS)));
		// termsMap.put(
		// 	"receives-fls",
		// 	_getTermMap(RECEIVES_FLS, StringUtil.merge(receivesFLS)));
		// termsMap.put(
		// 	"subscription-level",
		// 	_getTermMap(ACTIVE_SLAS, StringUtil.merge(activeSLAs)));
		// termsMap.put(
		// 	"subscription-status",
		// 	_getTermMap(
		// 		SUBSCRIPTION_STATES, StringUtil.merge(subscriptionStates)));
		// termsMap.put(
		// 	"support-region", _getTermMap(REGIONS, StringUtil.merge(regions)));
		// termsMap.put("tier", _getTermMap(TIERS, StringUtil.merge(tiers)));

		return termsMap;
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
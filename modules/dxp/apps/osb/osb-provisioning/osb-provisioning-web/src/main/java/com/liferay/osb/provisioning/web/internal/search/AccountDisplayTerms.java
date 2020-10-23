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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

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

	public static final String INTERNAL = "internal";

	public static final String MODIFIED_DATE_GT = "modifiedDateGT";

	public static final String MODIFIED_DATE_LT = "modifiedDateLT";

	public static final String NAME = "name";

	public static final String PARENT_ACCOUNT_KEY = "parentAccountKey";

	public static final String PARTNER = "partner";

	public static final String PARTNER_TEAM_KEY = "partnerTeamKey";

	public static final String PROVIDES_FLS = "providesFLS";

	public static final String RECEIVES_FLS = "receivesFLS";

	public static final String REGIONS = "regions";

	public static final String SUBSCRIPTION_STATES = "subscriptionStates";

	public static final String TIERS = "tiers";

	public static final String WORKER_CONTACT_EMAIL_ADDRESS =
		"workerContactEmailAddress";

	public AccountDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		code = ParamUtil.getString(portletRequest, CODE);
		countryName = ParamUtil.getString(portletRequest, COUNTRY_NAME);
		createDateGT = ParamUtil.getString(portletRequest, CREATE_DATE_GT);
		createDateLT = ParamUtil.getString(portletRequest, CREATE_DATE_LT);
		createdByEmailAddress = ParamUtil.getString(
			portletRequest, CREATED_BY_EMAIL_ADDRESS);
		flsTeamKey = ParamUtil.getString(portletRequest, FLS_TEAM_KEY);

		String internalString = ParamUtil.getString(portletRequest, INTERNAL);

		if (Validator.isNotNull(internalString)) {
			internal = GetterUtil.getBoolean(internalString);
		}

		modifiedDateGT = ParamUtil.getString(portletRequest, MODIFIED_DATE_GT);
		modifiedDateLT = ParamUtil.getString(portletRequest, MODIFIED_DATE_LT);
		name = ParamUtil.getString(portletRequest, NAME);

		parentAccountKey = ParamUtil.getString(
			portletRequest, PARENT_ACCOUNT_KEY);

		String partnerString = ParamUtil.getString(portletRequest, PARTNER);

		if (Validator.isNotNull(partnerString)) {
			partner = GetterUtil.getBoolean(partnerString);
		}

		partnerTeamKey = ParamUtil.getString(portletRequest, PARTNER_TEAM_KEY);

		String receivesFLSString = ParamUtil.getString(
			portletRequest, RECEIVES_FLS);

		if (Validator.isNotNull(receivesFLSString)) {
			receivesFLS = GetterUtil.getBoolean(receivesFLSString);
		}

		String providesFLSString = ParamUtil.getString(
			portletRequest, PROVIDES_FLS);

		if (Validator.isNotNull(providesFLSString)) {
			providesFLS = GetterUtil.getBoolean(providesFLSString);
		}

		activeSLAs = ParamUtil.getStringValues(portletRequest, ACTIVE_SLAS);

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

	public Boolean getInternal() {
		return internal;
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

	public Boolean getPartner() {
		return partner;
	}

	public String getPartnerTeamKey() {
		return partnerTeamKey;
	}

	public Boolean getProvidesFLS() {
		return providesFLS;
	}

	public Boolean getReceiveFLS() {
		return receivesFLS;
	}

	public String[] getRegions() {
		return regions;
	}

	public String[] getSubscriptionStates() {
		return subscriptionStates;
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
	protected Boolean internal;
	protected String modifiedDateGT;
	protected String modifiedDateLT;
	protected String name;
	protected String parentAccountKey;
	protected Boolean partner;
	protected String partnerTeamKey;
	protected Boolean providesFLS;
	protected Boolean receivesFLS;
	protected String[] regions;
	protected String[] subscriptionStates;
	protected String[] tiers;
	protected String workerContactEmailAddress;

}
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
 * @author Kyle Bischof
 */
public class LicenseDisplayTerms extends DisplayTerms {

	public static final String ACCOUNT_KEY = "accountKey";

	public static final String ACCOUNT_NAME = "accountName";

	public static final String ACTIVE_LICENSES = "activeLicenses";

	public static final String CREATE_DATE_GT = "createDateGT";

	public static final String CREATE_DATE_LT = "createDateLT";

	public static final String CREATOR_EMAIL_ADDRESS = "creatorEmailAddress";

	public static final String EXPIRE_DATE_GT = "expireDateGT";

	public static final String EXPIRE_DATE_LT = "expireDateLT";

	public static final String HOST_NAME = "hostName";

	public static final String IP_ADDRESS = "ipAddress";

	public static final String KEY = "key";

	public static final String MAC_ADDRESS = "macAddress";

	public static final String MODIFIED_DATE_GT = "modifiedDateGT";

	public static final String MODIFIED_DATE_LT = "modifiedDateLT";

	public static final String MODIFIED_EMAIL_ADDRESS = "modifiedEmailAddress";

	public static final String OWNER = "owner";

	public static final String PRODUCT_PURCHASE_KEY = "productPurchaseKey";

	public static final String PRODUCT_VERSIONS = "productVersions";

	public static final String PRODUCTS = "products";

	public static final String SERVER_ID = "serverId";

	public static final String START_DATE_GT = "startDateGT";

	public static final String START_DATE_LT = "startDateLT";

	public static final String TYPES = "types";

	public LicenseDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		if (Validator.isNull(keywords)) {
			keywords = ParamUtil.getString(
				portletRequest, "licenseSearchKeywords");
		}

		accountKey = ParamUtil.getString(portletRequest, ACCOUNT_KEY);
		accountName = ParamUtil.getString(portletRequest, ACCOUNT_NAME);
		activeLicenses = ParamUtil.getBooleanValues(
			portletRequest, ACTIVE_LICENSES);
		createDateGT = ParamUtil.getString(portletRequest, CREATE_DATE_GT);
		createDateLT = ParamUtil.getString(portletRequest, CREATE_DATE_LT);
		creatorEmailAddress = ParamUtil.getString(
			portletRequest, CREATOR_EMAIL_ADDRESS);
		expireDateGT = ParamUtil.getString(portletRequest, EXPIRE_DATE_GT);
		expireDateLT = ParamUtil.getString(portletRequest, EXPIRE_DATE_LT);
		hostName = ParamUtil.getString(portletRequest, HOST_NAME);
		ipAddress = ParamUtil.getString(portletRequest, IP_ADDRESS);
		key = ParamUtil.getString(portletRequest, KEY);
		macAddress = ParamUtil.getString(portletRequest, MAC_ADDRESS);
		modifiedDateGT = ParamUtil.getString(portletRequest, MODIFIED_DATE_GT);
		modifiedDateLT = ParamUtil.getString(portletRequest, MODIFIED_DATE_LT);
		modifiedEmailAddress = ParamUtil.getString(
			portletRequest, MODIFIED_EMAIL_ADDRESS);
		owner = ParamUtil.getString(portletRequest, OWNER);
		products = ParamUtil.getStringValues(portletRequest, PRODUCTS);
		productPurchaseKey = ParamUtil.getString(
			portletRequest, PRODUCT_PURCHASE_KEY);
		productVersions = ParamUtil.getStringValues(
			portletRequest, PRODUCT_VERSIONS);
		serverId = ParamUtil.getString(portletRequest, SERVER_ID);
		startDateGT = ParamUtil.getString(portletRequest, START_DATE_GT);
		startDateLT = ParamUtil.getString(portletRequest, START_DATE_LT);
		types = ParamUtil.getStringValues(portletRequest, TYPES);
	}

	public String getAccountKey() {
		return accountKey;
	}

	public String getAccountName() {
		return accountName;
	}

	public boolean[] getActiveLicenses() {
		return activeLicenses;
	}

	public String getCreateDateGT() {
		return createDateGT;
	}

	public String getCreateDateLT() {
		return createDateLT;
	}

	public String getCreatorEmailAddress() {
		return creatorEmailAddress;
	}

	public List<DisplayTerm> getDisplayTermsList() {
		return new ArrayList<>(
			Arrays.asList(
				new DisplayTerm("account-key", ACCOUNT_KEY, accountKey),
				new DisplayTerm("account-name", ACCOUNT_NAME, accountName),
				new DisplayTerm(
					"active-licenses", ACTIVE_LICENSES,
					StringUtil.merge(activeLicenses)),
				new DisplayTerm("created-after", CREATE_DATE_GT, createDateGT),
				new DisplayTerm("created-before", CREATE_DATE_LT, createDateLT),
				new DisplayTerm(
					"created-by", CREATOR_EMAIL_ADDRESS, creatorEmailAddress),
				new DisplayTerm("expires-after", EXPIRE_DATE_GT, expireDateGT),
				new DisplayTerm("expires-before", EXPIRE_DATE_LT, expireDateLT),
				new DisplayTerm("host-name", HOST_NAME, hostName),
				new DisplayTerm("ip-address", IP_ADDRESS, ipAddress),
				new DisplayTerm("key", KEY, key),
				new DisplayTerm("mac-address", MAC_ADDRESS, macAddress),
				new DisplayTerm(
					"modified-after", MODIFIED_DATE_GT, modifiedDateGT),
				new DisplayTerm(
					"modified-before", MODIFIED_DATE_LT, modifiedDateLT),
				new DisplayTerm(
					"modified-by", MODIFIED_EMAIL_ADDRESS,
					modifiedEmailAddress),
				new DisplayTerm("owner", OWNER, owner),
				new DisplayTerm(
					"product", PRODUCTS, StringUtil.merge(products)),
				new DisplayTerm(
					"product-purchase", PRODUCT_PURCHASE_KEY,
					productPurchaseKey),
				new DisplayTerm(
					"product-version", PRODUCT_VERSIONS,
					StringUtil.merge(productVersions)),
				new DisplayTerm("server-id", SERVER_ID, serverId),
				new DisplayTerm("started-after", START_DATE_GT, startDateGT),
				new DisplayTerm("started-before", START_DATE_LT, startDateLT),
				new DisplayTerm("type", TYPES, StringUtil.merge(types))));
	}

	public String getExpireDateGT() {
		return expireDateGT;
	}

	public String getExpireDateLT() {
		return expireDateLT;
	}

	public String getHostName() {
		return hostName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getKey() {
		return key;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public String getModifiedDateGT() {
		return modifiedDateGT;
	}

	public String getModifiedDateLT() {
		return modifiedDateLT;
	}

	public String getModifiedEmailAddress() {
		return modifiedEmailAddress;
	}

	public String getOwner() {
		return owner;
	}

	public String getProductPurchaseKey() {
		return productPurchaseKey;
	}

	public String[] getProducts() {
		return products;
	}

	public String[] getProductVersions() {
		return productVersions;
	}

	public String getServerId() {
		return serverId;
	}

	public String getStartDateGT() {
		return startDateGT;
	}

	public String getStartDateLT() {
		return startDateLT;
	}

	public String[] getTypes() {
		return types;
	}

	protected String accountKey;
	protected String accountName;
	protected boolean[] activeLicenses;
	protected String createDateGT;
	protected String createDateLT;
	protected String creatorEmailAddress;
	protected String expireDateGT;
	protected String expireDateLT;
	protected String hostName;
	protected String ipAddress;
	protected String key;
	protected String macAddress;
	protected String modifiedDateGT;
	protected String modifiedDateLT;
	protected String modifiedEmailAddress;
	protected String owner;
	protected String productPurchaseKey;
	protected String[] products;
	protected String[] productVersions;
	protected String serverId;
	protected String startDateGT;
	protected String startDateLT;
	protected String[] types;

}
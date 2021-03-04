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

import com.liferay.osb.provisioning.web.internal.display.context.LicenseDisplay;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Kyle Bischof
 */
public class LicenseSearch extends SearchContainer<LicenseDisplay> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-licenses-were-found";

	public LicenseSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new LicenseDisplayTerms(portletRequest),
			new LicenseSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, Collections.emptyList(),
			EMPTY_RESULTS_MESSAGE);

		LicenseDisplayTerms displayTerms =
			(LicenseDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			LicenseDisplayTerms.ACCOUNT_KEY, displayTerms.getAccountKey());
		iteratorURL.setParameter(
			LicenseDisplayTerms.ACCOUNT_NAME, displayTerms.getAccountName());
		iteratorURL.setParameter(
			LicenseDisplayTerms.ACTIVE_LICENSES,
			ArrayUtil.toStringArray(displayTerms.getActiveLicenses()));
		iteratorURL.setParameter(
			LicenseDisplayTerms.CREATE_DATE_GT, displayTerms.getCreateDateGT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.CREATE_DATE_LT, displayTerms.getCreateDateLT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.CREATOR_EMAIL_ADDRESS,
			displayTerms.getCreatorEmailAddress());
		iteratorURL.setParameter(
			LicenseDisplayTerms.EXPIRE_DATE_GT, displayTerms.getExpireDateGT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.EXPIRE_DATE_LT, displayTerms.getExpireDateLT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.HOST_NAME, displayTerms.getHostName());
		iteratorURL.setParameter(
			LicenseDisplayTerms.IP_ADDRESS, displayTerms.getIpAddress());
		iteratorURL.setParameter(
			LicenseDisplayTerms.KEY, displayTerms.getKey());
		iteratorURL.setParameter(
			LicenseDisplayTerms.MAC_ADDRESS, displayTerms.getMacAddress());
		iteratorURL.setParameter(
			LicenseDisplayTerms.MODIFIED_DATE_GT,
			displayTerms.getModifiedDateGT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.MODIFIED_DATE_LT,
			displayTerms.getModifiedDateLT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.MODIFIED_EMAIL_ADDRESS,
			displayTerms.getModifiedEmailAddress());
		iteratorURL.setParameter(
			LicenseDisplayTerms.OWNER, displayTerms.getOwner());
		iteratorURL.setParameter(
			LicenseDisplayTerms.PRODUCTS, displayTerms.getProducts());
		iteratorURL.setParameter(
			LicenseDisplayTerms.PRODUCT_PURCHASE_KEY,
			displayTerms.getProductPurchaseKey());
		iteratorURL.setParameter(
			LicenseDisplayTerms.PRODUCT_VERSIONS,
			displayTerms.getProductVersions());
		iteratorURL.setParameter(
			LicenseDisplayTerms.SERVER_ID, displayTerms.getServerId());
		iteratorURL.setParameter(
			LicenseDisplayTerms.START_DATE_GT, displayTerms.getStartDateGT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.START_DATE_LT, displayTerms.getStartDateLT());
		iteratorURL.setParameter(
			LicenseDisplayTerms.TYPES, displayTerms.getTypes());
	}

}
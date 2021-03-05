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

import com.liferay.osb.provisioning.web.internal.display.context.LicenseKeyDisplay;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Kyle Bischof
 */
public class LicenseKeySearch extends SearchContainer<LicenseKeyDisplay> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-licenses-were-found";

	public LicenseKeySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new LicenseKeyDisplayTerms(portletRequest),
			new LicenseKeySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, Collections.emptyList(),
			EMPTY_RESULTS_MESSAGE);

		LicenseKeyDisplayTerms displayTerms =
			(LicenseKeyDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACCOUNT_KEY, displayTerms.getAccountKey());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACCOUNT_NAME, displayTerms.getAccountName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.ACTIVE_LICENSES,
			ArrayUtil.toStringArray(displayTerms.getActiveLicenses()));
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_GT,
			displayTerms.getCreateDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATE_DATE_LT,
			displayTerms.getCreateDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.CREATOR_EMAIL_ADDRESS,
			displayTerms.getCreatorEmailAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRE_DATE_GT,
			displayTerms.getExpireDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.EXPIRE_DATE_LT,
			displayTerms.getExpireDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.HOST_NAME, displayTerms.getHostName());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.IP_ADDRESS, displayTerms.getIpAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.KEY, displayTerms.getKey());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MAC_ADDRESS, displayTerms.getMacAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_GT,
			displayTerms.getModifiedDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_DATE_LT,
			displayTerms.getModifiedDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.MODIFIED_EMAIL_ADDRESS,
			displayTerms.getModifiedEmailAddress());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.OWNER, displayTerms.getOwner());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCT_PURCHASE_KEY,
			displayTerms.getProductPurchaseKey());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCT_VERSIONS,
			displayTerms.getProductVersions());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.PRODUCTS, displayTerms.getProducts());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.SERVER_ID, displayTerms.getServerId());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_GT,
			displayTerms.getStartDateGT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.START_DATE_LT,
			displayTerms.getStartDateLT());
		iteratorURL.setParameter(
			LicenseKeyDisplayTerms.TYPES, displayTerms.getTypes());
	}

}
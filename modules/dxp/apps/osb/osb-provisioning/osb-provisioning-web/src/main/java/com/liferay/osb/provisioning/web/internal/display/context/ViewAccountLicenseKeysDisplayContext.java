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

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.util.LicenseUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Kyle Bischof
 */
public class ViewAccountLicenseKeysDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountLicenseKeysDisplayContext() {
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-licenses-were-found");

		String tabs2 = ParamUtil.getString(renderRequest, "tabs2", "active");

		Date now = new Date();

		List<LicenseKey> licenseKeys = null;

		int count = 0;

		if (tabs2.equals("active")) {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("active", true);

			licenseKeys = licenseKeyLocalService.search(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, now, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, now,
				null, params, true, searchContainer.getStart(),
				searchContainer.getEnd(),
				LicenseUtil.getLicenseKeyOrderByComparator(
					"expiration-date", "desc"));

			count = licenseKeyLocalService.searchCount(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, now,
				null, params, true);
		}
		else if (tabs2.equals("deactivated")) {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("active", false);

			licenseKeys = licenseKeyLocalService.search(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, null,
				null, params, true, searchContainer.getStart(),
				searchContainer.getEnd(),
				LicenseUtil.getLicenseKeyOrderByComparator(
					"expiration-date", "desc"));

			count = licenseKeyLocalService.searchCount(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, null,
				null, params, true);
		}
		else if (tabs2.equals("expired")) {
			licenseKeys = licenseKeyLocalService.search(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, null,
				now, new LinkedHashMap<>(), true, searchContainer.getStart(),
				searchContainer.getEnd(),
				LicenseUtil.getLicenseKeyOrderByComparator(
					"expiration-date", "desc"));

			count = licenseKeyLocalService.searchCount(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, null,
				now, new LinkedHashMap<>(), true);
		}
		else {
			licenseKeys = licenseKeyLocalService.search(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, null,
				null, new LinkedHashMap<>(), false, searchContainer.getStart(),
				searchContainer.getEnd(),
				LicenseUtil.getLicenseKeyOrderByComparator(
					"expiration-date", "desc"));

			count = licenseKeyLocalService.searchCount(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], null, null, null, null, null, null, null, null,
				null, new LinkedHashMap<>(), false);
		}

		searchContainer.setResults(
			TransformUtil.transform(
				licenseKeys,
				licenseKey -> new LicenseKeyDisplay(
					renderRequest, renderResponse, licenseKey)));

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public String getTabsNames() throws Exception {
		List<String> tabsNames = new ArrayList<>();

		Date now = new Date();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("active", true);

		int activeLicenseKeysCount = licenseKeyLocalService.searchCount(
			null, null, null, null, null, null, account.getKey(), null,
			account.getName(), null, null, new long[0], new String[0], null,
			null, new String[0], null, null, null, null, null, null, null, now,
			null, params, true);

		tabsNames.add(getTabName("active", activeLicenseKeysCount));

		int expiredLicenseKeysCount = licenseKeyLocalService.searchCount(
			null, null, null, null, null, null, account.getKey(), null,
			account.getName(), null, null, new long[0], new String[0], null,
			null, new String[0], null, null, null, null, null, null, null, null,
			now, new LinkedHashMap<>(), true);

		tabsNames.add(getTabName("expired", expiredLicenseKeysCount));

		params.put("active", false);

		int deactivatedLicenseKeysCount = licenseKeyLocalService.searchCount(
			null, null, null, null, null, null, account.getKey(), null,
			account.getName(), null, null, new long[0], new String[0], null,
			null, new String[0], null, null, null, null, null, null, null, null,
			null, params, true);

		tabsNames.add(getTabName("deactivated", deactivatedLicenseKeysCount));

		int allLicenseKeysCount = licenseKeyLocalService.searchCount(
			null, null, null, null, null, null, account.getKey(), null,
			account.getName(), null, null, new long[0], new String[0], null,
			null, new String[0], null, null, null, null, null, null, null, null,
			null, new LinkedHashMap<>(), false);

		tabsNames.add(getTabName("all", allLicenseKeysCount));

		return StringUtil.merge(tabsNames);
	}

}
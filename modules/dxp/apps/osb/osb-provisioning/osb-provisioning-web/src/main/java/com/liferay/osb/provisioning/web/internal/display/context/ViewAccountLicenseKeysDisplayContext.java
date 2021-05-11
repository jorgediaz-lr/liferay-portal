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

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

/**
 * @author Kyle Bischof
 */
public class ViewAccountLicenseKeysDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountLicenseKeysDisplayContext() {
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_productKeys = ParamUtil.getStringValues(renderRequest, "productKey");
	}

	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							StringBundler.concat(
								"javascript:", renderResponse.getNamespace(),
								"downloadLicenseKeys();"));
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "download"));
						dropdownItem.setQuickAction(true);
					});
				add(
					dropdownItem -> {
						dropdownItem.setHref(
							StringBundler.concat(
								"javascript:", renderResponse.getNamespace(),
								"renewLicenseKeys();"));
						dropdownItem.setLabel(
							LanguageUtil.get(httpServletRequest, "renew"));
						dropdownItem.setQuickAction(true);
					});
			}
		};
	}

	@Override
	public PortletURL getPortletURL() {
		if (_productKeys.length > 0) {
			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_subscription");
			portletURL.setParameter(
				"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
			portletURL.setParameter("accountKey", account.getKey());
			portletURL.setParameter("productKey", _productKeys[0]);

			return portletURL;
		}

		return super.getPortletURL();
	}

	public Map<String, Object> getRenewLicenseKeysData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("accountKey", account.getKey());

		PortletURL portletURL = renderResponse.createActionURL();

		if (_productKeys.length > 0) {
			data.put("productKey", _productKeys[0]);
		}

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/renew_license_keys");

		data.put("renewalURL", portletURL.toString());

		return data;
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-licenses-were-found");

		String tabs2 = ParamUtil.getString(renderRequest, "tabs2", "active");

		Date now = new Date();

		Hits hits = null;

		Sort sort = SortFactoryUtil.getSort(
			LicenseKey.class, Sort.LONG_TYPE, Field.MODIFIED_DATE, "desc");

		if (tabs2.equals("active")) {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), null, null, null, null, null, null,
				account.getKey(), null, null, null, null, null, _productKeys,
				null, null, null, null, null, null, null, null, null, null, now,
				null, true, true, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}
		else if (tabs2.equals("deactivated")) {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), null, null, null, null, null, null,
				account.getKey(), null, null, null, null, null, _productKeys,
				null, null, null, null, null, null, null, null, null, null,
				null, null, false, true, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}
		else if (tabs2.equals("expired")) {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), null, null, null, null, null, null,
				account.getKey(), null, null, null, null, null, _productKeys,
				null, null, null, null, null, null, null, null, null, null,
				null, now, true, true, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}
		else {
			hits = licenseKeyLocalService.search(
				themeDisplay.getCompanyId(), null, null, null, null, null, null,
				account.getKey(), null, null, null, null, null, _productKeys,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, true, searchContainer.getStart(),
				searchContainer.getEnd(), sort);
		}

		List<LicenseKey> licenseKeys = new ArrayList<>();

		for (Document document : hits.toList()) {
			long licenseKeyId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			licenseKeys.add(licenseKeyLocalService.getLicenseKey(licenseKeyId));
		}

		searchContainer.setResults(
			TransformUtil.transform(
				licenseKeys,
				licenseKey -> new LicenseKeyDisplay(
					renderRequest, renderResponse, licenseKey)));

		searchContainer.setTotal(hits.getLength());

		searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(renderResponse));

		return searchContainer;
	}

	public String getTabsNames() throws Exception {
		List<String> tabsNames = new ArrayList<>();

		Date now = new Date();

		int activeLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			account.getKey(), null, null, null, null, null, _productKeys, null,
			null, null, null, null, null, null, null, null, null, now, null,
			true, true);

		tabsNames.add(getTabName("active", activeLicenseKeysCount));

		int expiredLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			account.getKey(), null, null, null, null, null, _productKeys, null,
			null, null, null, null, null, null, null, null, null, null, now,
			true, true);

		tabsNames.add(getTabName("expired", expiredLicenseKeysCount));

		int deactivatedLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			account.getKey(), null, null, null, null, null, _productKeys, null,
			null, null, null, null, null, null, null, null, null, null, null,
			false, true);

		tabsNames.add(getTabName("deactivated", deactivatedLicenseKeysCount));

		int allLicenseKeysCount = licenseKeyLocalService.searchCount(
			themeDisplay.getCompanyId(), null, null, null, null, null, null,
			account.getKey(), null, null, null, null, null, _productKeys, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, true);

		tabsNames.add(getTabName("all", allLicenseKeysCount));

		return StringUtil.merge(tabsNames);
	}

	private String[] _productKeys;

}
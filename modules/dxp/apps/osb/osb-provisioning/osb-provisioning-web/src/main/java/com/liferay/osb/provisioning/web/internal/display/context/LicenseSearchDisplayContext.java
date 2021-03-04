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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.license.util.LicenseUtil;
import com.liferay.osb.provisioning.web.internal.search.LicenseSearch;
import com.liferay.osb.provisioning.web.internal.search.LicenseSearchTerms;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseSearchDisplayContext {

	public LicenseSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		LicenseEntryLocalService licenseEntryLocalService,
		LicenseKeyLocalService licenseKeyLocalService,
		ProductWebService productWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_licenseEntryLocalService = licenseEntryLocalService;
		_licenseKeyLocalService = licenseKeyLocalService;
		_productWebService = productWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);
	}

	public Map<String, Object> getData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL licenseHomeURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.LICENSES,
			PortletRequest.RENDER_PHASE);

		data.put("licenseHomeURL", licenseHomeURL.toString());

		JSONArray licenseEntriesJSONArray = JSONFactoryUtil.createJSONArray();

		List<LicenseEntry> licenseEntries =
			_licenseEntryLocalService.getLicenseEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (LicenseEntry licenseEntry : licenseEntries) {
			licenseEntriesJSONArray.put(
				JSONUtil.put(
					"label", licenseEntry.getName()
				).put(
					"value", licenseEntry.getLicenseEntryId()
				));
		}

		data.put("licenseTypes", licenseEntriesJSONArray);

		JSONArray productsJSONArray = JSONFactoryUtil.createJSONArray();

		List<Product> products = _productWebService.getProducts(
			StringPool.BLANK, StringPool.BLANK, 1, 1000, StringPool.BLANK);

		for (Product product : products) {
			productsJSONArray.put(
				JSONUtil.put(
					"label", product.getName()
				).put(
					"value", product.getKey()
				));
		}

		data.put("products", productsJSONArray);

		JSONArray productVersionsJSONArray = JSONFactoryUtil.createJSONArray();

		String[] productVersions = ArrayUtil.append(
			ProductVersion.DXP_VERSIONS, ProductVersion.PORTAL_VERSIONS);

		for (String productVersion : productVersions) {
			productVersionsJSONArray.put(
				JSONUtil.put(
					"label", productVersion
				).put(
					"value", productVersion
				));
		}

		data.put("productVersions", productVersionsJSONArray);

		return data;
	}

	public SearchContainer getSearchContainer() throws Exception {
		if (_licenseSearch != null) {
			return _licenseSearch;
		}

		_licenseSearch = new LicenseSearch(_renderRequest, _currentURLObj);

		LicenseSearchTerms searchTerms =
			(LicenseSearchTerms)_licenseSearch.getSearchTerms();

		List<LicenseKey> licenseKeys = null;

		int count = 0;

		if (searchTerms.isAdvancedSearch()) {
			licenseKeys = _licenseKeyLocalService.search(
				searchTerms.getCreatorUserId(),
				searchTerms.getDate(searchTerms.getCreateDateGT()),
				searchTerms.getDate(searchTerms.getCreateDateLT()),
				searchTerms.getModifiedUserId(),
				searchTerms.getDate(searchTerms.getModifiedDateGT()),
				searchTerms.getDate(searchTerms.getModifiedDateLT()),
				searchTerms.getAccountKey(),
				searchTerms.getProductPurchaseKey(),
				searchTerms.getAccountName(),
				searchTerms.getDate(searchTerms.getStartDateGT()),
				searchTerms.getDate(searchTerms.getStartDateLT()),
				searchTerms.getLicenseEntryIds(), searchTerms.getProducts(),
				null, null, searchTerms.getProductVersions(),
				searchTerms.getOwner(), null, searchTerms.getHostName(),
				searchTerms.getIpAddress(), searchTerms.getMacAddress(),
				searchTerms.getServerId(), searchTerms.getKey(),
				searchTerms.getDate(searchTerms.getExpireDateGT()),
				searchTerms.getDate(searchTerms.getStartDateLT()),
				searchTerms.getParams(), false, _licenseSearch.getStart(),
				_licenseSearch.getEnd(),
				LicenseUtil.getLicenseKeyOrderByComparator(
					"expiration-date", "desc"));

			count = _licenseKeyLocalService.searchCount(
				searchTerms.getCreatorUserId(),
				searchTerms.getDate(searchTerms.getCreateDateGT()),
				searchTerms.getDate(searchTerms.getCreateDateLT()),
				searchTerms.getModifiedUserId(),
				searchTerms.getDate(searchTerms.getModifiedDateGT()),
				searchTerms.getDate(searchTerms.getModifiedDateLT()),
				searchTerms.getAccountKey(),
				searchTerms.getProductPurchaseKey(),
				searchTerms.getAccountName(),
				searchTerms.getDate(searchTerms.getStartDateGT()),
				searchTerms.getDate(searchTerms.getStartDateLT()),
				searchTerms.getLicenseEntryIds(), searchTerms.getProducts(),
				null, null, searchTerms.getProductVersions(),
				searchTerms.getOwner(), null, searchTerms.getHostName(),
				searchTerms.getIpAddress(), searchTerms.getMacAddress(),
				searchTerms.getServerId(), searchTerms.getKey(),
				searchTerms.getDate(searchTerms.getExpireDateGT()),
				searchTerms.getDate(searchTerms.getStartDateLT()),
				searchTerms.getParams(), false);
		}
		else {
			licenseKeys = _licenseKeyLocalService.search(
				searchTerms.getKeywords(), searchTerms.getParams(),
				_licenseSearch.getStart(), _licenseSearch.getEnd(),
				LicenseUtil.getLicenseKeyOrderByComparator(
					"expiration-date", "desc"));

			count = _licenseKeyLocalService.searchCount(
				searchTerms.getKeywords(), searchTerms.getParams());
		}

		_licenseSearch.setResults(
			TransformUtil.transform(
				licenseKeys,
				licenseKey -> new LicenseDisplay(
					_renderRequest, _renderResponse, licenseKey)));

		_licenseSearch.setTotal(count);

		return _licenseSearch;
	}

	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseEntryLocalService _licenseEntryLocalService;
	private final LicenseKeyLocalService _licenseKeyLocalService;
	private LicenseSearch _licenseSearch;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}
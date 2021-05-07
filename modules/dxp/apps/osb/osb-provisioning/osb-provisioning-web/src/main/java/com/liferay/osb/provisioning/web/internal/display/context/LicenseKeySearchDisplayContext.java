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
import com.liferay.osb.provisioning.identity.management.provider.IdentityProvider;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.web.internal.search.LicenseKeySearch;
import com.liferay.osb.provisioning.web.internal.search.LicenseKeySearchTerms;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
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
public class LicenseKeySearchDisplayContext {

	public LicenseKeySearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		IdentityProvider identityProvider,
		LicenseEntryLocalService licenseEntryLocalService,
		LicenseKeyLocalService licenseKeyLocalService,
		ProductWebService productWebService,
		UserLocalService userLocalService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_identityProvider = identityProvider;
		_licenseEntryLocalService = licenseEntryLocalService;
		_licenseKeyLocalService = licenseKeyLocalService;
		_productWebService = productWebService;
		_userLocalService = userLocalService;

		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

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
		if (_licenseKeySearch != null) {
			return _licenseKeySearch;
		}

		_licenseKeySearch = new LicenseKeySearch(
			_renderRequest, _currentURLObj);

		LicenseKeySearchTerms searchTerms =
			(LicenseKeySearchTerms)_licenseKeySearch.getSearchTerms();

		Hits hits = null;

		Sort sort = SortFactoryUtil.getSort(
			LicenseKey.class, Sort.LONG_TYPE, Field.CREATE_DATE, "desc");

		if (searchTerms.isAdvancedSearch()) {
			hits = _licenseKeyLocalService.search(
				_themeDisplay.getCompanyId(),
				_getUserUuid(searchTerms.getCreatorEmailAddress()),
				searchTerms.getDate(searchTerms.getCreateDateGT()),
				searchTerms.getDate(searchTerms.getCreateDateLT()),
				_getUserUuid(searchTerms.getModifiedEmailAddress()),
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
				searchTerms.getDate(searchTerms.getExpirationDateGT()),
				searchTerms.getDate(searchTerms.getExpirationDateLT()),
				searchTerms.getActive(), searchTerms.isAndOperator(),
				_licenseKeySearch.getStart(), _licenseKeySearch.getEnd(), sort);
		}
		else {
			hits = _licenseKeyLocalService.search(
				_themeDisplay.getCompanyId(), searchTerms.getKeywords(),
				_licenseKeySearch.getStart(), _licenseKeySearch.getEnd(), sort);
		}

		List<LicenseKey> licenseKeys = new ArrayList<>();

		for (Document document : hits.toList()) {
			long licenseKeyId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			licenseKeys.add(
				_licenseKeyLocalService.getLicenseKey(licenseKeyId));
		}

		_licenseKeySearch.setResults(
			TransformUtil.transform(
				licenseKeys,
				licenseKey -> new LicenseKeyDisplay(
					_renderRequest, _renderResponse, licenseKey)));

		_licenseKeySearch.setTotal(hits.getLength());

		return _licenseKeySearch;
	}

	private String _getUserUuid(String emailAddress) throws Exception {
		if (Validator.isNull(emailAddress)) {
			return StringPool.BLANK;
		}

		User user = _userLocalService.fetchUserByEmailAddress(
			_themeDisplay.getCompanyId(), emailAddress);

		if (user != null) {
			return user.getUuid();
		}

		JSONObject jsonObject = _identityProvider.fetchByEmailAddress(
			emailAddress);

		if (jsonObject != null) {
			return jsonObject.getString("uuid");
		}

		return "not-available";
	}

	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final IdentityProvider _identityProvider;
	private final LicenseEntryLocalService _licenseEntryLocalService;
	private final LicenseKeyLocalService _licenseKeyLocalService;
	private LicenseKeySearch _licenseKeySearch;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;
	private final UserLocalService _userLocalService;

}
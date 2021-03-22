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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class AddLicenseKeyDisplayContext {

	public AddLicenseKeyDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		LicenseEntryLocalService licenseEntryLocalService,
		ProductConsumptionWebService productConsumptionWebService,
		ProductWebService productWebService,
		ProductPurchaseViewWebService productPurchaseViewWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_licenseEntryLocalService = licenseEntryLocalService;
		_productConsumptionWebService = productConsumptionWebService;
		_productWebService = productWebService;
		_productPurchaseViewWebService = productPurchaseViewWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);
	}

	public Map<String, Object> getAddLicenseKeyData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		String redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		data.put("redirect", redirect);

		PortletURL selectAccountActionURL = _renderResponse.createActionURL();

		selectAccountActionURL.setParameter(
			ActionRequest.ACTION_NAME, "/licenses/select_account");
		selectAccountActionURL.setParameter("redirect", redirect);

		data.put("selectAccountActionURL", selectAccountActionURL.toString());

		PortletURL selectAccountRenderURL = _renderResponse.createRenderURL();

		selectAccountRenderURL.setParameter(
			"mvcRenderCommandName", "/licenses/select_account");
		selectAccountRenderURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("selectAccountRenderURL", selectAccountRenderURL.toString());

		if (_account == null) {
			return data;
		}

		data.put("accountCode", _account.getCode());
		data.put("accountKey", _account.getKey());
		data.put("accountName", _account.getName());

		PortletURL addLicenseKeyURL = _renderResponse.createActionURL();

		addLicenseKeyURL.setParameter(
			ActionRequest.ACTION_NAME, "/licenses/add_license_key");
		addLicenseKeyURL.setParameter("redirect", redirect);

		data.put("addLicenseKeyURL", addLicenseKeyURL.toString());

		data.put("description", _account.getName());
		data.put("licensableProducts", _getLicensableProductsJSONArray());

		List<Integer> maxHttpSessions = new ArrayList<>();

		for (int i = 5; i <= 10; i++) {
			maxHttpSessions.add(i);
		}

		data.put("maxHttpSessions", maxHttpSessions);

		List<Integer> maxServers = new ArrayList<>();

		for (int i = 0; i <= 15; i++) {
			maxServers.add(i);
		}

		data.put("maxServers", maxServers);

		data.put("owner", _account.getName());
		data.put("purchasedProducts", _getPurchasedProductsJSONObject());

		return data;
	}

	private String _getDate(Date date, String type) throws Exception {
		if ((date == null) && type.equals("expirationDate")) {
			return StringPool.BLANK;
		}

		Calendar calendar = Calendar.getInstance(
			_themeDisplay.getTimeZone(), _themeDisplay.getLocale());

		if (type.equals("expirationDate")) {
			calendar.add(Calendar.YEAR, 1);
		}

		if (date != null) {
			calendar.setTime(date);
		}

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd");

		return dateFormat.format(calendar.getTime());
	}

	private JSONObject _getDetachedDetails(String productKey) throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("accountKey eq '");
		sb.append(_account.getKey());
		sb.append("' and productKey eq '");
		sb.append(productKey);
		sb.append("' and productPurchaseKey eq null");

		long productConsumptionsCount =
			_productConsumptionWebService.searchCount(sb.toString());

		List<Integer> sizing = new ArrayList<>();

		for (int i = 1; i <= 4; i++) {
			sizing.add(i);
		}

		return JSONUtil.put(
			"licenseKeysGenerated", productConsumptionsCount
		).put(
			"sizing", sizing
		).put(
			"startDate", _getDate(null, "startDate")
		);
	}

	private JSONArray _getLicensableProductsJSONArray() throws Exception {
		JSONArray licensableProductsJSONArray =
			JSONFactoryUtil.createJSONArray();

		List<Product> products = _productWebService.getProducts(
			StringPool.BLANK, "property_licenses eq 'true'", 1, 1000,
			StringPool.BLANK);

		for (Product product : products) {
			Map<String, String> properties = product.getProperties();

			if ((properties == null) || (properties.get("versions") == null)) {
				continue;
			}

			String[] versions = StringUtil.split(properties.get("versions"));

			if (versions.length < 1) {
				continue;
			}

			licensableProductsJSONArray.put(
				JSONUtil.put(
					"detached", _getDetachedDetails(product.getKey())
				).put(
					"productKey", product.getKey()
				).put(
					"productName", product.getName()
				).put(
					"productVersions",
					_getProductVersionsJSONObject(versions, product.getKey())
				));
		}

		return licensableProductsJSONArray;
	}

	private JSONObject _getProductVersionsJSONObject(
		String[] versions, String productKey) {

		JSONObject productVersionsJSONObject =
			JSONFactoryUtil.createJSONObject();

		for (String version : versions) {
			List<LicenseEntry> licenseEntries =
				_licenseEntryLocalService.getLicenseEntriesByVersion(
					productKey, version.trim());

			JSONArray licenseEntriesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (LicenseEntry licenseEntry : licenseEntries) {
				licenseEntriesJSONArray.put(
					JSONUtil.put(
						"licenseEntryId", licenseEntry.getLicenseEntryId()
					).put(
						"licenseEntryName", licenseEntry.getName()
					).put(
						"licenseEntryType", licenseEntry.getType()
					));
			}

			productVersionsJSONObject.put(
				version.trim(), licenseEntriesJSONArray);
		}

		return productVersionsJSONObject;
	}

	private JSONObject _getPurchasedProductsJSONObject() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("accountKey eq '");
		sb.append(_account.getKey());
		sb.append("' and property_licenses eq 'true'");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		JSONObject purchasedProductsJSONObject =
			JSONFactoryUtil.createJSONObject();

		if (productPurchaseViews.isEmpty()) {
			return purchasedProductsJSONObject;
		}

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			if (productPurchaseView.getProductConsumptions() != null) {
				Map<String, List<ProductConsumption>> productConsumptionsMap =
					new HashMap<>();

				for (ProductConsumption productConsumption :
						productPurchaseView.getProductConsumptions()) {

					List<ProductConsumption> productConsumptions =
						productConsumptionsMap.get(
							productConsumption.getProductPurchaseKey());

					if (productConsumptions == null) {
						productConsumptions = new ArrayList<>();

						productConsumptionsMap.put(
							productConsumption.getProductPurchaseKey(),
							productConsumptions);
					}

					productConsumptions.add(productConsumption);
				}

				if (productPurchaseView.getProductPurchases() != null) {
					Product product = productPurchaseView.getProduct();

					JSONArray productPurchasesJSONArray =
						JSONFactoryUtil.createJSONArray();

					for (ProductPurchase productPurchase :
							productPurchaseView.getProductPurchases()) {

						Map<String, String> properties =
							productPurchase.getProperties();

						int sizing = 0;

						if (properties != null) {
							sizing = GetterUtil.getInteger(
								properties.get("sizing"));
						}

						int provisionedCount = 0;

						List<ProductConsumption> productConsumptions =
							productConsumptionsMap.get(
								productPurchase.getKey());

						if (productConsumptions != null) {
							provisionedCount = productConsumptions.size();
						}

						String licenseKeysGenerated =
							provisionedCount + " / " +
								productPurchase.getQuantity();

						productPurchasesJSONArray.put(
							JSONUtil.put(
								"expirationDate",
								_getDate(
									productPurchase.getEndDate(),
									"expirationDate")
							).put(
								"licenseKeysGenerated", licenseKeysGenerated
							).put(
								"productPurchaseKey", productPurchase.getKey()
							).put(
								"sizing", sizing
							).put(
								"startDate",
								_getDate(
									productPurchase.getStartDate(), "startDate")
							));
					}

					purchasedProductsJSONObject.put(
						product.getKey(), productPurchasesJSONArray);
				}
			}
		}

		return purchasedProductsJSONObject;
	}

	private final Account _account;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseEntryLocalService _licenseEntryLocalService;
	private final ProductConsumptionWebService _productConsumptionWebService;
	private final ProductPurchaseViewWebService _productPurchaseViewWebService;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}
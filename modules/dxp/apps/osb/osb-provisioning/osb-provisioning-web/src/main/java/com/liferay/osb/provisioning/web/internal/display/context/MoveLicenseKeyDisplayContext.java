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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class MoveLicenseKeyDisplayContext {

	public MoveLicenseKeyDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		ProductConsumptionWebService productConsumptionWebService,
		ProductPurchaseViewWebService productPurchaseViewWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_productConsumptionWebService = productConsumptionWebService;
		_productPurchaseViewWebService = productPurchaseViewWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);

		_licenseKey = (LicenseKey)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEY);
	}

	public String getDetachedLicenseKeysGenerated() throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("accountKey eq '");
		sb.append(_licenseKey.getAccountKey());
		sb.append("' and productKey eq '");
		sb.append(_licenseKey.getProductKey());
		sb.append("' and productPurchaseKey eq null");

		long productConsumptionsCount =
			_productConsumptionWebService.searchCount(sb.toString());

		if (productConsumptionsCount > 0) {
			return String.valueOf(productConsumptionsCount);
		}

		return StringPool.DASH;
	}

	public LicenseKey getLicenseKey() {
		return _licenseKey;
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _currentURLObj, Collections.emptyList(),
			"no-product-purchases-were-found");

		List<Object> results = new ArrayList<>();

		ProductPurchaseView productPurchaseView =
			_productPurchaseViewWebService.getProductPurchaseView(
				_licenseKey.getAccountKey(), _licenseKey.getProductKey());

		if (productPurchaseView != null) {
			Map<String, List<ProductConsumption>> productConsumptionsMap =
				new HashMap<>();

			if (productPurchaseView.getProductConsumptions() != null) {
				for (ProductConsumption productConsumption :
						productPurchaseView.getProductConsumptions()) {

					List<ProductConsumption> curProductConsumptions =
						productConsumptionsMap.get(
							productConsumption.getProductPurchaseKey());

					if (curProductConsumptions == null) {
						curProductConsumptions = new ArrayList<>();

						productConsumptionsMap.put(
							productConsumption.getProductPurchaseKey(),
							curProductConsumptions);
					}

					curProductConsumptions.add(productConsumption);
				}
			}

			if (productPurchaseView.getProductPurchases() != null) {
				for (ProductPurchase curProductPurchase :
						productPurchaseView.getProductPurchases()) {

					List<ProductConsumption> productConsumptions =
						productConsumptionsMap.get(curProductPurchase.getKey());

					int productConsumptionsCount = 0;

					if (productConsumptions != null) {
						productConsumptionsCount = productConsumptions.size();
					}

					results.add(
						new ProductPurchaseDisplay(
							_httpServletRequest, curProductPurchase,
							productConsumptionsCount));
				}
			}
		}

		results.add((Object)null);

		searchContainer.setResults(results);

		searchContainer.setTotal(results.size());

		return searchContainer;
	}

	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseKey _licenseKey;
	private final ProductConsumptionWebService _productConsumptionWebService;
	private final ProductPurchaseViewWebService _productPurchaseViewWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}
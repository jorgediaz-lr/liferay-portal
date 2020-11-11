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
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.service.ProductBundleLocalServiceUtil;
import com.liferay.osb.provisioning.web.internal.dao.search.AssignProductsRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class AssignProductsDisplayContext {

	public AssignProductsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		ProductWebService productWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_productWebService = productWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);

		_accountKey = ParamUtil.getString(renderRequest, "accountKey");
	}

	public String getClearResultsURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		if (Validator.isNotNull(_accountKey)) {
			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/assign_products");
			portletURL.setParameter("accountKey", _accountKey);
		}
		else {
			portletURL.setParameter(
				"mvcRenderCommandName", "/product_bundles/assign_products");
		}

		return portletURL.toString();
	}

	public String getCurrentURL() {
		return _currentURLObj.toString();
	}

	public SearchContainer getSearchContainer(
			long[] productBundleIds, String[] productKeys)
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _currentURLObj, Collections.emptyList(),
			"no-products-were-found");

		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		List<Object> results = new ArrayList<>();

		int count = 0;

		if (Validator.isNotNull(_accountKey)) {
			results.addAll(
				ProductBundleLocalServiceUtil.getProductBundles(
					searchContainer.getStart(), searchContainer.getEnd()));

			count = results.size();
		}

		List<Product> products = _productWebService.getProducts(
			keywords, StringPool.BLANK, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		results.addAll(
			TransformUtil.transform(
				products,
				product -> new ProductDisplay(
					_renderRequest, _renderResponse, product)));

		count += (int)_productWebService.getProductsCount(
			keywords, StringPool.BLANK);

		searchContainer.setResults(results);

		searchContainer.setRowChecker(
			new AssignProductsRowChecker(
				_renderResponse, productBundleIds, Arrays.asList(productKeys)));

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private final String _accountKey;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}
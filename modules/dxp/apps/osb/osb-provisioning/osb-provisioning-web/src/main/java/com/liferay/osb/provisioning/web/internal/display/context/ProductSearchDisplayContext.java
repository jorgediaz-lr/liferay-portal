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
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.web.internal.permission.ProductPermissionChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ProductSearchDisplayContext {

	public ProductSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		ProductWebService productWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_productWebService = productWebService;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _renderResponse.createRenderURL(),
			Collections.emptyList(), "no-products-were-found");

		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		List<Product> products = _productWebService.getProducts(
			keywords, StringPool.BLANK, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				products,
				product -> new ProductDisplay(
					_renderRequest, _renderResponse, product)));

		int count = (int)_productWebService.getProductsCount(
			keywords, StringPool.BLANK);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public boolean hasManageProductsPermission() throws Exception {
		return ProductPermissionChecker.contains(
			_themeDisplay.getPermissionChecker(),
			ProvisioningActionKeys.MANAGE_PRODUCTS);
	}

	private final HttpServletRequest _httpServletRequest;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}
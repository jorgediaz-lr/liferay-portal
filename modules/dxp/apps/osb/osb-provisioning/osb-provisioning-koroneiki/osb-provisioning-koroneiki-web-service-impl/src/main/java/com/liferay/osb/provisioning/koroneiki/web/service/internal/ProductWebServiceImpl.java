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

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductSerDes;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ProductWebService.class
)
public class ProductWebServiceImpl implements ProductWebService {

	public Product addProduct(
			String agentName, String agentUID, Product product)
		throws Exception {

		return _productResource.postProduct(agentName, agentUID, product);
	}

	public void deleteProduct(
			String agentName, String agentUID, String productKey)
		throws Exception {

		_productResource.deleteProduct(agentName, agentUID, productKey);
	}

	public Product fetchProductByName(String name) throws Exception {
		HttpInvoker.HttpResponse httpResponse =
			_productResource.getProductByNameProductNameHttpResponse(
				_http.encodePath(name));

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			return null;
		}

		return ProductSerDes.toDTO(httpResponse.getContent());
	}

	public Product getProduct(String productKey) throws Exception {
		return _productResource.getProduct(productKey);
	}

	public List<Product> getProducts(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception {

		Page<Product> productsPage = _productResource.getProductsPage(
			search, filterString, Pagination.of(page, pageSize), sortString);

		if ((productsPage != null) && (productsPage.getItems() != null)) {
			return new ArrayList<>(productsPage.getItems());
		}

		return Collections.emptyList();
	}

	public List<Product> getProducts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception {

		Page<Product> productsPage =
			_productResource.getProductByExternalLinkDomainEntityNameEntityPage(
				_http.encodePath(domain), _http.encodePath(entityName),
				_http.encodePath(entityId), Pagination.of(page, pageSize));

		if ((productsPage != null) && (productsPage.getItems() != null)) {
			return new ArrayList<>(productsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long getProductsCount(String search, String filterString)
		throws Exception {

		Page<Product> productsPage = _productResource.getProductsPage(
			search, filterString, Pagination.of(1, 1), StringPool.BLANK);

		if (productsPage != null) {
			return productsPage.getTotalCount();
		}

		return 0;
	}

	public Product updateProduct(
			String agentName, String agentUID, String productKey,
			Product product)
		throws Exception {

		return _productResource.putProduct(
			agentName, agentUID, productKey, product);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductResource.Builder builder = ProductResource.builder();

		_productResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	@Reference
	private Http _http;

	private ProductResource _productResource;

}
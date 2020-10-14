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

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ProductWebService {

	public Product addProduct(
			String agentName, String agentUID, Product product)
		throws Exception;

	public void deleteProduct(
			String agentName, String agentUID, String productKey)
		throws Exception;

	public Product fetchProductByName(String name) throws Exception;

	public Product getProduct(String productKey) throws Exception;

	public List<Product> getProducts(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception;

	public List<Product> getProducts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception;

	public long getProductsCount(String search, String filterString)
		throws Exception;

	public Product updateProduct(
			String agentName, String agentUID, String productKey,
			Product product)
		throws Exception;

}
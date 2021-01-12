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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ProductPurchaseWebService {

	public ProductPurchase addProductPurchase(
			String agentName, String agentUID, String accountKey,
			ProductPurchase productPurchase)
		throws Exception;

	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception;

	public List<ProductPurchase> getProductPurchases(
			String filterString, int page, int pageSize, String sortString)
		throws Exception;

	public long getProductPurchasesCount(String filterString) throws Exception;

	public ProductPurchase updateProductPurchase(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchase productPurchase)
		throws Exception;

}
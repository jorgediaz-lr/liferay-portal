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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ProductPurchaseWebService.class
)
public class ProductPurchaseWebServiceImpl
	implements ProductPurchaseWebService {

	public ProductPurchase addProductPurchase(
			String agentName, String agentUID, String accountKey,
			ProductPurchase productPurchase)
		throws Exception {

		return _productPurchaseResource.postAccountAccountKeyProductPurchase(
			agentName, agentUID, accountKey, productPurchase);
	}

	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception {

		return _productPurchaseResource.getProductPurchase(productPurchaseKey);
	}

	public List<ProductPurchase> getProductPurchases(
			String filterString, int page, int pageSize, String sortString)
		throws Exception {

		Page<ProductPurchase> productPurchasesPage =
			_productPurchaseResource.getProductPurchasesPage(
				StringPool.BLANK, filterString, Pagination.of(page, pageSize),
				sortString);

		if ((productPurchasesPage != null) &&
			(productPurchasesPage.getItems() != null)) {

			return new ArrayList<>(productPurchasesPage.getItems());
		}

		return Collections.emptyList();
	}

	public ProductPurchase updateProductPurchase(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchase productPurchase)
		throws Exception {

		return _productPurchaseResource.putProductPurchase(
			agentName, agentUID, productPurchaseKey, productPurchase);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductPurchaseResource.Builder builder =
			ProductPurchaseResource.builder();

		_productPurchaseResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).parameter(
			"nestedFields", "products"
		).build();
	}

	private ProductPurchaseResource _productPurchaseResource;

}
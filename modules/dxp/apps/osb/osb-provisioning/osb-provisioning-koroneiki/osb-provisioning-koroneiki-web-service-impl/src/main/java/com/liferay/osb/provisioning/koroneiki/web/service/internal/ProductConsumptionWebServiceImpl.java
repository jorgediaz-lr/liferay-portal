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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
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
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ProductConsumptionWebService.class
)
public class ProductConsumptionWebServiceImpl
	implements ProductConsumptionWebService {

	public ProductConsumption addProductConsumption(
			String agentName, String agentUID, String accountKey,
			ProductConsumption productConsumption)
		throws Exception {

		return _productConsumptionResource.
			postAccountAccountKeyProductConsumption(
				agentName, agentUID, accountKey, productConsumption);
	}

	public void deleteProductConsumption(
			String agentName, String agentUID, String productConsumptionKey)
		throws Exception {

		_productConsumptionResource.deleteProductConsumption(
			agentName, agentUID, productConsumptionKey);
	}

	public List<ProductConsumption> getProductConsumptions(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception {

		Page<ProductConsumption> productConsumptionsPage =
			_productConsumptionResource.
				getProductConsumptionByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId,
					Pagination.of(page, pageSize));

		if ((productConsumptionsPage != null) &&
			(productConsumptionsPage.getItems() != null)) {

			return new ArrayList<>(productConsumptionsPage.getItems());
		}

		return Collections.emptyList();
	}

	public List<ProductConsumption> search(
			String filter, int page, int pageSize, String sort)
		throws Exception {

		Page<ProductConsumption> productConsumptionsPage =
			_productConsumptionResource.getProductConsumptionsPage(
				StringPool.BLANK, filter, Pagination.of(page, pageSize), sort);

		if ((productConsumptionsPage != null) &&
			(productConsumptionsPage.getItems() != null)) {

			return new ArrayList<>(productConsumptionsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(String filter) throws Exception {
		Page<ProductConsumption> productConsumptionsPage =
			_productConsumptionResource.getProductConsumptionsPage(
				StringPool.BLANK, filter, Pagination.of(1, 1),
				StringPool.BLANK);

		if (productConsumptionsPage != null) {
			return productConsumptionsPage.getTotalCount();
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductConsumptionResource.Builder builder =
			ProductConsumptionResource.builder();

		_productConsumptionResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private ProductConsumptionResource _productConsumptionResource;

}
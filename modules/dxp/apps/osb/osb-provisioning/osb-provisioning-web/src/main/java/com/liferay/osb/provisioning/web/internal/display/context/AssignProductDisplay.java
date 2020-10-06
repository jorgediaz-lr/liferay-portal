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
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.model.ProductBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Yuanyuan Huang
 */
public class AssignProductDisplay {

	public AssignProductDisplay(
		PortletRequest portletRequest, PortletResponse portletResponse,
		Product product, ProductBundle productBundle) {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;
		_product = product;
		_productBundle = productBundle;
	}

	public String getKey() {
		if (_product != null) {
			return _product.getKey();
		}

		return String.valueOf(_productBundle.getProductBundleId());
	}

	public String getName() {
		if (_product != null) {
			return _product.getName();
		}

		return _productBundle.getName();
	}

	public String getType() {
		if (_product != null) {
			return ProvisioningWebKeys.PRODUCT;
		}

		return ProvisioningWebKeys.PRODUCT_BUNDLE;
	}

	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private final Product _product;
	private final ProductBundle _productBundle;

}
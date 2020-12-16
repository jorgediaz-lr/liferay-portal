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

package com.liferay.osb.provisioning.internal.search.spi.model.index.contributor;

import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Yuanyuan Huang
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.provisioning.model.ProductBundle",
	service = ModelDocumentContributor.class
)
public class ProductBundleModelDocumentContributor
	implements ModelDocumentContributor<ProductBundle> {

	@Override
	public void contribute(Document document, ProductBundle productBundle) {
		document.addText(Field.NAME, productBundle.getName());

		document.addTextSortable(Field.NAME, productBundle.getName());
	}

}
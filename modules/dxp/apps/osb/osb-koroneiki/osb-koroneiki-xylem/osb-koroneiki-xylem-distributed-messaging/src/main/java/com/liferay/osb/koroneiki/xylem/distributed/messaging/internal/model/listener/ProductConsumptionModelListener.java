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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.StringPool;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.productconsumption.create",
		"remove.topic=koroneiki.productconsumption.delete",
		"update.topic=koroneiki.productconsumption.update"
	},
	service = ModelListener.class
)
public class ProductConsumptionModelListener
	extends BaseXylemModelListener<ProductConsumption> {

	@Override
	protected Callable<Message> getCallable(
			ProductConsumption productConsumption)
		throws Exception {

		Account account = _accountLocalService.getAccount(
			productConsumption.getAccountId());

		productConsumption.setAccountKey(account.getAccountKey());

		if (productConsumption.getProductPurchaseId() > 0) {
			ProductPurchase productPurchase =
				_productPurchaseLocalService.getProductPurchase(
					productConsumption.getProductPurchaseId());

			productConsumption.setProductPurchaseKey(
				productPurchase.getProductPurchaseKey());
		}
		else {
			productConsumption.setProductPurchaseKey(StringPool.BLANK);
		}

		return () -> messageFactory.create(productConsumption);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}
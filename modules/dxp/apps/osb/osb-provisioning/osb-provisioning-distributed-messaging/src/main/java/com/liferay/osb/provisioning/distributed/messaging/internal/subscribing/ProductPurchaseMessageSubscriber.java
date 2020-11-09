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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseSerDes;
import com.liferay.osb.provisioning.lcs.web.service.LCSSubscriptionEntryWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"topic.pattern=koroneiki.productpurchase.create",
		"topic.pattern=koroneiki.productpurchase.delete",
		"topic.pattern=koroneiki.productpurchase.update"
	},
	service = ProductPurchaseMessageSubscriber.class
)
public class ProductPurchaseMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		ProductPurchase productPurchase = ProductPurchaseSerDes.toDTO(
			jsonObject.toString());

		_lcsSubscriptionEntryWebService.syncToLCS(
			productPurchase.getAccountKey());
	}

	@Override
	protected void handleError(
			String routingKey, String message, Exception exception)
		throws PortalException {

		_log.error(message, exception);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductPurchaseMessageSubscriber.class);

	@Reference
	private LCSSubscriptionEntryWebService _lcsSubscriptionEntryWebService;

}
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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseSerDes;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.lcs.web.service.LCSSubscriptionEntryWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

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
		JSONObject productPurchaseJSONObject = jsonObject.getJSONObject(
			"productPurchase");

		ProductPurchase productPurchase = ProductPurchaseSerDes.toDTO(
			productPurchaseJSONObject.toString());

		Account account = _accountWebService.getAccount(
			productPurchase.getAccountKey());

		if (_hasSyncToLCS(account)) {
			_lcsSubscriptionEntryWebService.syncToLCS(
				productPurchase.getAccountKey());
		}
	}

	@Override
	protected void handleError(
			String routingKey, String message, Exception exception)
		throws PortalException {

		_log.error(message, exception);
	}

	private boolean _hasSyncToLCS(Account account) throws Exception {
		Date dateCreated = account.getDateCreated();

		if (dateCreated.getTime() <= _SYNC_TO_LCS_TIME) {
			return true;
		}

		return false;
	}

	private static final long _SYNC_TO_LCS_TIME = 1577865600000L;

	private static final Log _log = LogFactoryUtil.getLog(
		ProductPurchaseMessageSubscriber.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private LCSSubscriptionEntryWebService _lcsSubscriptionEntryWebService;

}
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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.LegacyConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "topic.pattern=entity.organization.assigned",
	service = OrganizationAssignmentMessageSubscriber.class
)
public class OrganizationAssignmentMessageSubscriber
	extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		String organizationId = organizationJSONObject.getString(
			"organizationId");

		if (!organizationId.equals(
				LegacyConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return;
		}

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		Contact contact = _contactWebservice.fetchContactByEmailAddress(
			userJSONObject.getString("emailAddress"));

		if (contact == null) {
			contact = parseContact(userJSONObject);

			_contactWebservice.addContact(
				StringPool.BLANK, StringPool.BLANK, contact);
		}

		List<Account> accounts = _accountWebservice.getAccounts(
			ExternalLinkDomain.WEB, ExternalLinkEntityName.WEB_ORGANIZATION,
			organizationId, 1, 1000);

		ContactRole contactRole = _contactRoleWebservice.fetchContactRole(
			ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
			ContactRoleConstants.NAME_MEMBER);

		for (Account account : accounts) {
			_accountWebservice.assignContactRoles(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				contact.getEmailAddress(), new String[] {contactRole.getKey()});
		}
	}

	@Override
	protected void handleError(
			String routingKey, String message, Exception exception)
		throws PortalException {

		_log.error(message, exception);
	}

	protected Contact parseContact(JSONObject jsonObject) {
		Contact contact = new Contact();

		contact.setUuid(jsonObject.getString("uuid"));
		contact.setFirstName(jsonObject.getString("firstName"));
		contact.setMiddleName(jsonObject.getString("middleName"));
		contact.setLastName(jsonObject.getString("lastName"));
		contact.setEmailAddress(jsonObject.getString("emailAddress"));

		return contact;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationAssignmentMessageSubscriber.class);

	@Reference
	private AccountWebService _accountWebservice;

	@Reference
	private ContactRoleWebService _contactRoleWebservice;

	@Reference
	private ContactWebService _contactWebservice;

}
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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Kyle Bischof
 */
public class AccountUtil {

	public static Account toAccount(
			com.liferay.osb.koroneiki.taproot.model.Account account)
		throws Exception {

		return new Account() {
			{
				assignedTeams = TransformUtil.transformToArray(
					account.getAssignedTeams(), TeamUtil::toTeam, Team.class);
				code = account.getCode();
				contactEmailAddress = account.getContactEmailAddress();
				dateCreated = account.getCreateDate();
				dateModified = account.getModifiedDate();
				description = account.getDescription();
				entitlements = TransformUtil.transformToArray(
					account.getEntitlements(), EntitlementUtil::toEntitlement,
					Entitlement.class);
				externalLinks = TransformUtil.transformToArray(
					account.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				faxNumber = account.getFaxNumber();
				internal = account.getInternal();
				key = account.getAccountKey();
				language = Language.create(account.getLanguage());
				logoId = account.getLogoId();
				name = account.getName();
				phoneNumber = account.getPhoneNumber();
				postalAddresses = TransformUtil.transformToArray(
					account.getAddresses(),
					address -> PostalAddressUtil.toPostalAddress(address),
					PostalAddress.class);
				profileEmailAddress = account.getProfileEmailAddress();
				region = Region.create(account.getRegion());
				status = Status.create(account.getStatus());
				tier = Tier.create(account.getTier());
				website = account.getWebsite();
			}
		};
	}

}
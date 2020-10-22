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

package com.liferay.osb.provisioning.web.internal.search;

import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountSearchTerms extends AccountDisplayTerms {

	public AccountSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public String getAdvancedSearchFilter(
			Set<String> subscriptionProductKeys, String createdByUuid,
			String flsTeamRoleKey, String partnerTeamRoleKey)
		throws Exception {

		StringBundler sb = new StringBundler();

		if (!ArrayUtil.isEmpty(subscriptionStates)) {
			for (int i = 0; i < subscriptionStates.length; i++) {
				String subscriptionState = subscriptionStates[i];

				if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_ACTIVE)) {

					sb.append("activeProductKeys");
				}
				else if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_CANCELLED)) {

					sb.append("cancelledProductKeys");
				}
				else if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_EXPIRED)) {

					sb.append("expiredProductKeys");
				}
				else if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_UNACTIVATED)) {

					sb.append("unactivatedProductKeys");
				}

				sb.append("/any(s:");

				Iterator iterator = subscriptionProductKeys.iterator();

				while (iterator.hasNext()) {
					sb.append("s eq '");
					sb.append(iterator.next());
					sb.append("'");

					if (iterator.hasNext()) {
						sb.append(" or ");
					}
				}

				sb.append(")");

				if ((i + 1) < subscriptionStates.length) {
					sb.append(" or ");
				}
			}
		}

		if (!ArrayUtil.isEmpty(activeSLAs)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("(");

			for (int i = 0; i < activeSLAs.length; i++) {
				sb.append("entitlements/any(s:s eq '");
				sb.append(activeSLAs[i]);
				sb.append("')");

				if ((i + 1) < activeSLAs.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
		}

		if (Validator.isNotNull(code)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("contains(code, '");
			sb.append(code);
			sb.append("')");
		}

		if (Validator.isNotNull(countryName)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("postalAddressCountries/any(s:s eq '");
			sb.append(countryName);
			sb.append("')");
		}

		if ((createDateGTDay > 0) && (createDateGTMonth > 0) &&
			(createDateGTYear > 0)) {

			Date createDate = PortalUtil.getDate(
				createDateGTMonth, createDateGTDay, createDateGTYear, null);

			if (createDate != null) {
				sb.append(_getBooleanOperator(sb));

				sb.append("dateCreated gt ");

				DateFormat dateFormat = DateUtil.getISO8601Format();

				sb.append(dateFormat.format(createDate));
			}
		}

		if ((createDateLTDay > 0) && (createDateLTMonth > 0) &&
			(createDateLTYear > 0)) {

			Date createDate = PortalUtil.getDate(
				createDateLTMonth, createDateLTDay, createDateLTYear, null);

			if (createDate != null) {
				sb.append(_getBooleanOperator(sb));

				sb.append("dateCreated lt ");

				DateFormat dateFormat = DateUtil.getISO8601Format();

				sb.append(dateFormat.format(createDate));
			}
		}

		if (Validator.isNotNull(createdByUuid)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("creatorUuid eq '");
			sb.append(createdByUuid);
			sb.append("'");
		}

		if (Validator.isNotNull(flsTeamKey)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("assignedTeamKeyTeamRoleKeys/any(s:s eq '");
			sb.append(flsTeamKey);
			sb.append("_");
			sb.append(flsTeamRoleKey);
			sb.append("')");
		}

		if (internal != null) {
			sb.append(_getBooleanOperator(sb));

			sb.append("internal eq ");
			sb.append(internal);
		}

		if ((modifiedDateGTDay > 0) && (modifiedDateGTMonth > 0) &&
			(modifiedDateGTYear > 0)) {

			Date modifiedDate = PortalUtil.getDate(
				modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear,
				null);

			if (modifiedDate != null) {
				sb.append(_getBooleanOperator(sb));

				sb.append("dateModified gt ");

				DateFormat dateFormat = DateUtil.getISO8601Format();

				sb.append(dateFormat.format(modifiedDate));
			}
		}

		if ((modifiedDateLTDay > 0) && (modifiedDateLTMonth > 0) &&
			(modifiedDateLTYear > 0)) {

			Date modifiedDate = PortalUtil.getDate(
				modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear,
				null);

			if (modifiedDate != null) {
				sb.append(_getBooleanOperator(sb));

				sb.append("dateModified lt ");

				DateFormat dateFormat = DateUtil.getISO8601Format();

				sb.append(dateFormat.format(modifiedDate));
			}
		}

		if (Validator.isNotNull(name)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("contains(name, '");
			sb.append(name);
			sb.append("')");
		}

		if (Validator.isNotNull(parentAccountKey)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("parentAccountKey eq '");
			sb.append(parentAccountKey);
			sb.append("'");
		}

		if (partner != null) {
			sb.append(_getBooleanOperator(sb));

			if (!partner) {
				sb.append("not ");
			}

			sb.append("entitlements/any(s:s eq '");
			sb.append(EntitlementConstants.PARTNER);
			sb.append("')");
		}

		if (Validator.isNotNull(partnerTeamKey)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("assignedTeamKeyTeamRoleKeys/any(s:s eq '");
			sb.append(partnerTeamKey);
			sb.append("_");
			sb.append(partnerTeamRoleKey);
			sb.append("')");
		}

		if (providesFLS != null) {
			sb.append(_getBooleanOperator(sb));

			if (!providesFLS) {
				sb.append("not ");
			}

			sb.append("teamsAssignedToAccountKeyTeamRoleKeys/any(s:contains(");
			sb.append("s, '_");
			sb.append(flsTeamRoleKey);
			sb.append("'))");
		}

		if (receivesFLS != null) {
			sb.append(_getBooleanOperator(sb));

			if (!receivesFLS) {
				sb.append("not ");
			}

			sb.append("assignedTeamKeyTeamRoleKeys/any(s:contains(s, '_");
			sb.append(flsTeamRoleKey);
			sb.append("'))");
		}

		if (!ArrayUtil.isEmpty(regions)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("(");

			for (int i = 0; i < regions.length; i++) {
				sb.append("region eq '");
				sb.append(regions[i]);
				sb.append("'");

				if ((i + 1) < regions.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
		}

		if (!ArrayUtil.isEmpty(tiers)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("(");

			for (int i = 0; i < tiers.length; i++) {
				sb.append("tier eq '");
				sb.append(tiers[i]);
				sb.append("'");

				if ((i + 1) < tiers.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
		}

		if (Validator.isNotNull(workerContactEmailAddress)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("workerContactEmailAddresses/any(s:s eq '");
			sb.append(workerContactEmailAddress);
			sb.append("')");
		}

		return sb.toString();
	}

	public String getBasicSearchFilter() {
		StringBundler sb = new StringBundler();

		if (Validator.isNotNull(keywords)) {
			String[] keywordsArray = StringUtil.split(
				keywords, StringPool.SPACE);

			for (int i = 0; i < keywordsArray.length; i++) {
				String keyword = keywordsArray[i];

				sb.append("(contains(code, '");
				sb.append(keyword);
				sb.append("') or contains(name, '");
				sb.append(keyword);
				sb.append("'))");

				if (i < (keywordsArray.length - 1)) {
					sb.append(" or ");
				}
			}
		}

		return sb.toString();
	}

	public boolean hasSearchTerms() {
		if (isAdvancedSearch()) {
			if (!ArrayUtil.isEmpty(activeSLAs) || Validator.isNotNull(code) ||
				Validator.isNotNull(countryName) ||
				((createDateGTDay > 0) && (createDateGTMonth > 0) &&
				 (createDateGTYear > 0)) ||
				((createDateLTDay > 0) && (createDateLTMonth > 0) &&
				 (createDateLTYear > 0)) ||
				Validator.isNotNull(createdByEmailAddress) ||
				Validator.isNotNull(flsTeamKey) || (internal != null) ||
				((modifiedDateGTDay > 0) && (modifiedDateGTMonth > 0) &&
				 (modifiedDateGTYear > 0)) ||
				((modifiedDateLTDay > 0) && (modifiedDateLTMonth > 0) &&
				 (modifiedDateLTYear > 0)) ||
				Validator.isNotNull(name) ||
				Validator.isNotNull(parentAccountKey) || (partner != null) ||
				Validator.isNotNull(partnerTeamKey) || (providesFLS != null) ||
				(receivesFLS != null) || !ArrayUtil.isEmpty(regions) ||
				!ArrayUtil.isEmpty(subscriptionStates) ||
				!ArrayUtil.isEmpty(tiers) ||
				Validator.isNotNull(workerContactEmailAddress)) {

				return true;
			}
		}
		else {
			if (Validator.isNotNull(keywords)) {
				return true;
			}
		}

		return false;
	}

	private String _getBooleanOperator(StringBundler sb) {
		if (sb.length() <= 0) {
			return StringPool.BLANK;
		}

		if (andOperator) {
			return " and ";
		}

		return " or ";
	}

}
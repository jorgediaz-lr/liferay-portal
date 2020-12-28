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
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
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
			sb.append("(");

			for (int i = 0; i < subscriptionStates.length; i++) {
				String subscriptionState = subscriptionStates[i];

				if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_ACTIVE)) {

					sb.append(
						_getSubscriptionStateFilter(
							"activeProductKeys", subscriptionProductKeys));
				}
				else if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_CANCELLED)) {

					sb.append("(not ");
					sb.append(
						_getSubscriptionStateFilter(
							"activeProductKeys", subscriptionProductKeys));
					sb.append(" and not ");
					sb.append(
						_getSubscriptionStateFilter(
							"expiredProductKeys", subscriptionProductKeys));
					sb.append(" and not ");
					sb.append(
						_getSubscriptionStateFilter(
							"unactivatedProductKeys", subscriptionProductKeys));
					sb.append(" and ");
					sb.append(
						_getSubscriptionStateFilter(
							"cancelledProductKeys", subscriptionProductKeys));
					sb.append(")");
				}
				else if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_EXPIRED)) {

					sb.append("(not ");
					sb.append(
						_getSubscriptionStateFilter(
							"activeProductKeys", subscriptionProductKeys));
					sb.append(" and not ");
					sb.append(
						_getSubscriptionStateFilter(
							"unactivatedProductKeys", subscriptionProductKeys));
					sb.append(" and ");
					sb.append(
						_getSubscriptionStateFilter(
							"expiredProductKeys", subscriptionProductKeys));
					sb.append(")");
				}
				else if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_NOT_AVAILABLE)) {

					sb.append("(not ");
					sb.append(
						_getSubscriptionStateFilter(
							"activeProductKeys", subscriptionProductKeys));
					sb.append(" and not ");
					sb.append(
						_getSubscriptionStateFilter(
							"cancelledProductKeys", subscriptionProductKeys));
					sb.append(" and not ");
					sb.append(
						_getSubscriptionStateFilter(
							"expiredProductKeys", subscriptionProductKeys));
					sb.append(" and not ");
					sb.append(
						_getSubscriptionStateFilter(
							"unactivatedProductKeys", subscriptionProductKeys));

					sb.append(")");
				}
				else if (subscriptionState.equals(
							ProductPurchaseConstants.STATE_UNACTIVATED)) {

					sb.append("(not ");
					sb.append(
						_getSubscriptionStateFilter(
							"activeProductKeys", subscriptionProductKeys));
					sb.append(" and ");
					sb.append(
						_getSubscriptionStateFilter(
							"unactivatedProductKeys", subscriptionProductKeys));
					sb.append(")");
				}

				if ((i + 1) < subscriptionStates.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
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

		if (Validator.isNotNull(createDateGT)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("dateCreated gt ");

			Date createDate = _dateFormat.parse(createDateGT);

			sb.append(_isoDateFormat.format(createDate));
		}

		if (Validator.isNotNull(createDateLT)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("dateCreated lt ");

			Date createDate = _dateFormat.parse(createDateLT);

			sb.append(_isoDateFormat.format(createDate));
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

		if (!ArrayUtil.isEmpty(internals)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("(");

			for (int i = 0; i < internals.length; i++) {
				sb.append("internal eq ");
				sb.append(internals[i]);

				if ((i + 1) < internals.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
		}

		if (Validator.isNotNull(modifiedDateGT)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("dateModified gt ");

			Date modifiedDate = _dateFormat.parse(modifiedDateGT);

			sb.append(_isoDateFormat.format(modifiedDate));
		}

		if (Validator.isNotNull(modifiedDateLT)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("dateModified lt ");

			Date modifiedDate = _dateFormat.parse(modifiedDateLT);

			sb.append(_isoDateFormat.format(modifiedDate));
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

		if (!ArrayUtil.isEmpty(partners)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("(");

			for (int i = 0; i < partners.length; i++) {
				if (!partners[i]) {
					sb.append("not ");
				}

				sb.append("entitlements/any(s:s eq '");
				sb.append(EntitlementConstants.PARTNER);
				sb.append("')");

				if ((i + 1) < partners.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
		}

		if (Validator.isNotNull(partnerTeamKey)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("assignedTeamKeyTeamRoleKeys/any(s:s eq '");
			sb.append(partnerTeamKey);
			sb.append("_");
			sb.append(partnerTeamRoleKey);
			sb.append("')");
		}

		if (!ArrayUtil.isEmpty(providesFLS)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("(");

			for (int i = 0; i < providesFLS.length; i++) {
				if (!providesFLS[i]) {
					sb.append("not ");
				}

				sb.append("teamsAssignedToAccountKeyTeamRoleKeys/any(s:");
				sb.append("contains(s, '_");
				sb.append(flsTeamRoleKey);
				sb.append("'))");

				if ((i + 1) < providesFLS.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
		}

		if (!ArrayUtil.isEmpty(receivesFLS)) {
			sb.append(_getBooleanOperator(sb));

			sb.append("(");

			for (int i = 0; i < receivesFLS.length; i++) {
				if (!receivesFLS[i]) {
					sb.append("not ");
				}

				sb.append("assignedTeamKeyTeamRoleKeys/any(s:contains(s, '_");
				sb.append(flsTeamRoleKey);
				sb.append("'))");

				if ((i + 1) < receivesFLS.length) {
					sb.append(" or ");
				}
			}

			sb.append(")");
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
				Validator.isNotNull(createDateGT) ||
				Validator.isNotNull(createDateLT) ||
				Validator.isNotNull(createdByEmailAddress) ||
				Validator.isNotNull(flsTeamKey) ||
				!ArrayUtil.isEmpty(internals) ||
				Validator.isNotNull(modifiedDateGT) ||
				Validator.isNotNull(modifiedDateLT) ||
				Validator.isNotNull(name) ||
				Validator.isNotNull(parentAccountKey) ||
				!ArrayUtil.isEmpty(partners) ||
				Validator.isNotNull(partnerTeamKey) ||
				!ArrayUtil.isEmpty(providesFLS) ||
				!ArrayUtil.isEmpty(receivesFLS) ||
				!ArrayUtil.isEmpty(regions) ||
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

	private String _getSubscriptionStateFilter(
		String field, Set<String> subscriptionProductKeys) {

		StringBundler sb = new StringBundler();

		sb.append(field);
		sb.append("/any(s:");

		Iterator<String> iterator = subscriptionProductKeys.iterator();

		while (iterator.hasNext()) {
			sb.append("s eq '");
			sb.append(iterator.next());
			sb.append("'");

			if (iterator.hasNext()) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		return sb.toString();
	}

	private final DateFormat _dateFormat =
		DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");
	private final DateFormat _isoDateFormat = DateUtil.getISO8601Format();

}
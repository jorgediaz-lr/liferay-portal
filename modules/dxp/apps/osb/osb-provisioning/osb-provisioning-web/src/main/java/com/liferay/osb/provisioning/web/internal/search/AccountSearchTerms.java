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
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountSearchTerms extends AccountDisplayTerms {

	public AccountSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public FilterQuery getAdvancedSearchFilter(
			String[] subscriptionProductKeys, String createdByUuid,
			String flsTeamRoleKey, String partnerTeamRoleKey)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		if (!ArrayUtil.isEmpty(subscriptionStates)) {
			filterQuery.addFilterQuery(
				_getSubscriptionStateFilter(subscriptionProductKeys),
				andOperator);
		}

		if (!ArrayUtil.isEmpty(activeSLAs)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (String activeSLA : activeSLAs) {
				nestedFilterQuery.addLambdaEquals(
					"entitlements", activeSLAs, false);
			}

			filterQuery.addFilterQuery(nestedFilterQuery, andOperator);
		}

		if (Validator.isNotNull(code)) {
			filterQuery.addContains("code", code, andOperator);
		}

		if (Validator.isNotNull(countryName)) {
			filterQuery.addLambdaEquals(
				"postalAddressCountries", countryName, andOperator);
		}

		if (Validator.isNotNull(createDateGT)) {
			filterQuery.addGreaterThan(
				"dateCreated", _dateFormat.parse(createDateGT), andOperator);
		}

		if (Validator.isNotNull(createDateLT)) {
			filterQuery.addLessThan(
				"dateCreated", _dateFormat.parse(createDateLT), andOperator);
		}

		if (Validator.isNotNull(createdByUuid)) {
			filterQuery.addEquals("creatorUuid", createdByUuid, andOperator);
		}

		if (Validator.isNotNull(externalAccountKey)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			nestedFilterQuery.addLambdaContains(
				"externalLinkEntityIds", externalAccountKey, false);
			nestedFilterQuery.addLambdaContains(
				"productPurchaseExternalLinkEntityIds", externalAccountKey,
				false);

			filterQuery.addFilterQuery(nestedFilterQuery, andOperator);
		}

		if (Validator.isNotNull(flsTeamKey)) {
			filterQuery.addLambdaEquals(
				"assignedTeamKeyTeamRoleKeys",
				flsTeamKey + "_" + flsTeamRoleKey, andOperator);
		}

		if (!ArrayUtil.isEmpty(internals)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean internal : internals) {
				nestedFilterQuery.addEquals("internal", internal, false);
			}

			filterQuery.addFilterQuery(nestedFilterQuery, andOperator);
		}

		if (Validator.isNotNull(modifiedDateGT)) {
			filterQuery.addGreaterThan(
				"dateModified", _dateFormat.parse(modifiedDateGT), andOperator);
		}

		if (Validator.isNotNull(modifiedDateLT)) {
			filterQuery.addLessThan(
				"dateModified", _dateFormat.parse(modifiedDateLT), andOperator);
		}

		if (Validator.isNotNull(name)) {
			filterQuery.addContains("name", name, andOperator);
		}

		if (Validator.isNotNull(notes)) {
			filterQuery.addLambdaContains(
				"generalNoteContent", notes, andOperator);
		}

		if (Validator.isNotNull(parentAccountKey)) {
			filterQuery.addEquals(
				"parentAccountKey", parentAccountKey, andOperator);
		}

		if (!ArrayUtil.isEmpty(partners)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean partner : partners) {
				nestedFilterQuery.addLambdaEquals(
					"entitlements", EntitlementConstants.PARTNER, !partner,
					false);
			}

			filterQuery.addFilterQuery(nestedFilterQuery, andOperator);
		}

		if (Validator.isNotNull(partnerTeamKey)) {
			filterQuery.addLambdaEquals(
				"assignedTeamKeyTeamRoleKeys",
				partnerTeamKey + "_" + partnerTeamRoleKey, andOperator);
		}

		if (!ArrayUtil.isEmpty(providesFLS)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean providesFLSValue : providesFLS) {
				nestedFilterQuery.addLambdaContains(
					"teamsAssignedToAccountKeyTeamRoleKeys",
					"_" + flsTeamRoleKey, !providesFLSValue, false);
			}

			filterQuery.addFilterQuery(nestedFilterQuery, andOperator);
		}

		if (!ArrayUtil.isEmpty(receivesFLS)) {
			FilterQuery nestedFilterQuery = new FilterQuery();

			for (boolean receivesFLSValue : receivesFLS) {
				nestedFilterQuery.addLambdaContains(
					"assignedTeamKeyTeamRoleKeys", "_" + flsTeamRoleKey,
					!receivesFLSValue, false);
			}

			filterQuery.addFilterQuery(nestedFilterQuery, andOperator);
		}

		if (!ArrayUtil.isEmpty(regions)) {
			filterQuery.addEquals("region", regions, andOperator);
		}

		if (Validator.isNotNull(salesInfo)) {
			filterQuery.addLambdaContains(
				"salesNoteContent", salesInfo, andOperator);
		}

		if (!ArrayUtil.isEmpty(tiers)) {
			filterQuery.addEquals("tier", tiers, andOperator);
		}

		if (Validator.isNotNull(workerContactEmailAddress)) {
			filterQuery.addLambdaEquals(
				"workerContactEmailAddresses", workerContactEmailAddress,
				andOperator);
		}

		return filterQuery;
	}

	public FilterQuery getBasicSearchFilter(String[] subscriptionProductKeys) {
		FilterQuery filterQuery = new FilterQuery();

		if (!ArrayUtil.isEmpty(subscriptionStates)) {
			filterQuery.addFilterQuery(
				_getSubscriptionStateFilter(subscriptionProductKeys),
				andOperator);
		}

		if (parent) {
			filterQuery.addEquals("parent", true, andOperator);
		}

		return filterQuery;
	}

	public boolean hasSearchTerms() {
		if (isAdvancedSearch()) {
			if (!ArrayUtil.isEmpty(activeSLAs) || Validator.isNotNull(code) ||
				Validator.isNotNull(countryName) ||
				Validator.isNotNull(createDateGT) ||
				Validator.isNotNull(createDateLT) ||
				Validator.isNotNull(createdByEmailAddress) ||
				Validator.isNotNull(externalAccountKey) ||
				Validator.isNotNull(flsTeamKey) ||
				!ArrayUtil.isEmpty(internals) ||
				Validator.isNotNull(modifiedDateGT) ||
				Validator.isNotNull(modifiedDateLT) ||
				Validator.isNotNull(name) || Validator.isNotNull(notes) ||
				Validator.isNotNull(parentAccountKey) ||
				!ArrayUtil.isEmpty(partners) ||
				Validator.isNotNull(partnerTeamKey) ||
				!ArrayUtil.isEmpty(providesFLS) ||
				!ArrayUtil.isEmpty(receivesFLS) ||
				!ArrayUtil.isEmpty(regions) || Validator.isNotNull(salesInfo) ||
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

	private FilterQuery _getSubscriptionStateFilter(
		String[] subscriptionProductKeys) {

		FilterQuery filterQuery = new FilterQuery();

		for (String subscriptionState : subscriptionStates) {
			if (subscriptionState.equals(
					ProductPurchaseConstants.STATE_ACTIVE)) {

				filterQuery.addLambdaEquals(
					"activeProductKeys", subscriptionProductKeys, false);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_CANCELLED)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					"activeProductKeys", subscriptionProductKeys, true, true);
				nestedFilterQuery.addLambdaEquals(
					"expiredProductKeys", subscriptionProductKeys, true, true);
				nestedFilterQuery.addLambdaEquals(
					"unactivatedProductKeys", subscriptionProductKeys, true,
					true);
				nestedFilterQuery.addLambdaEquals(
					"cancelledProductKeys", subscriptionProductKeys, true);

				filterQuery.addFilterQuery(nestedFilterQuery, false);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_EXPIRED)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					"activeProductKeys", subscriptionProductKeys, true, true);
				nestedFilterQuery.addLambdaEquals(
					"unactivatedProductKeys", subscriptionProductKeys, true,
					true);
				nestedFilterQuery.addLambdaEquals(
					"expiredProductKeys", subscriptionProductKeys, true);

				filterQuery.addFilterQuery(nestedFilterQuery, false);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_NOT_AVAILABLE)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					"activeProductKeys", subscriptionProductKeys, true, true);
				nestedFilterQuery.addLambdaEquals(
					"cancelledProductKeys", subscriptionProductKeys, true,
					true);
				nestedFilterQuery.addLambdaEquals(
					"expiredProductKeys", subscriptionProductKeys, true, true);
				nestedFilterQuery.addLambdaEquals(
					"unactivatedProductKeys", subscriptionProductKeys, true);

				filterQuery.addFilterQuery(nestedFilterQuery, false);
			}
			else if (subscriptionState.equals(
						ProductPurchaseConstants.STATE_UNACTIVATED)) {

				FilterQuery nestedFilterQuery = new FilterQuery();

				nestedFilterQuery.addLambdaEquals(
					"activeProductKeys", subscriptionProductKeys, true, true);
				nestedFilterQuery.addLambdaEquals(
					"unactivatedProductKeys", subscriptionProductKeys, true);

				filterQuery.addFilterQuery(nestedFilterQuery, false);
			}
		}

		return filterQuery;
	}

	private final DateFormat _dateFormat =
		DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}
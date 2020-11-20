/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

/**
 * This helper formats true or false filter values to yes or no display values
 * @param {string} value The filter value to be evaluated
 * @returns {string} New display value
 */
export function formatFilterValue(value) {
	switch (value) {
		case 'false':
			return Liferay.Language.get('no');
		case 'false,true':
			return (
				Liferay.Language.get('no') + ', ' + Liferay.Language.get('yes')
			);
		case 'true':
			return Liferay.Language.get('yes');
		case 'true,false':
			return (
				Liferay.Language.get('yes') + ', ' + Liferay.Language.get('no')
			);
		default:
			return value;
	}
}

/**
 * This helper matches a search param name and returns the localized display
 * name.
 * @param {string} name The filter name to be evaluated
 * @returns {string} New display value
 */
export function getFilterDisplayName(name) {
	let displayName;

	switch (name) {
		case 'activeSLAs':
			displayName = Liferay.Language.get('subscription-level');
			break;
		case 'code':
			displayName = Liferay.Language.get('code');
			break;
		case 'countryName':
			displayName = Liferay.Language.get('country');
			break;
		case 'createDateGT':
			displayName = Liferay.Language.get('created-after');
			break;
		case 'createDateLT':
			displayName = Liferay.Language.get('created-before');
			break;
		case 'createdByEmailAddress':
			displayName = Liferay.Language.get('created-by');
			break;
		case 'flsTeamName':
			displayName = Liferay.Language.get('first-line-support');
			break;
		case 'internals':
			displayName = Liferay.Language.get('internal');
			break;
		case 'name':
			displayName = Liferay.Language.get('account-name');
			break;
		case 'modifiedDateGT':
			displayName = Liferay.Language.get('modified-after');
			break;
		case 'modifiedDateLT':
			displayName = Liferay.Language.get('modified-before');
			break;
		case 'parentAccountName':
			displayName = Liferay.Language.get('parent-account');
			break;
		case 'partners':
			displayName = Liferay.Language.get('partner');
			break;
		case 'partnerTeamName':
			displayName = Liferay.Language.get('partner-reseller-si');
			break;
		case 'providesFLS':
			displayName = Liferay.Language.get('provides-fls');
			break;
		case 'receivesFLS':
			displayName = Liferay.Language.get('receives-fls');
			break;
		case 'regions':
			displayName = Liferay.Language.get('support-region');
			break;
		case 'subscriptionStates':
			displayName = Liferay.Language.get('subscription-status');
			break;
		case 'tiers':
			displayName = Liferay.Language.get('tier');
			break;
		case 'workerContactEmailAddress':
			displayName = Liferay.Language.get('project-worker');
			break;
		default:
			return;
	}

	return displayName;
}

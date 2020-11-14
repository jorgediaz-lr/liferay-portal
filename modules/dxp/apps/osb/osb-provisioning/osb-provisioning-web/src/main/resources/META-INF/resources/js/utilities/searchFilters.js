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

export function getFilterDisplayName(name) {
	let displayName;

	switch (name) {
		case 'activeSLAs':
			displayName = Liferay.Language.get('subscription-level');
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
		case 'partners':
			displayName = Liferay.Language.get('partner');
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
			displayName = name.charAt(0).toUpperCase() + name.substring(1);
	}

	return displayName;
}

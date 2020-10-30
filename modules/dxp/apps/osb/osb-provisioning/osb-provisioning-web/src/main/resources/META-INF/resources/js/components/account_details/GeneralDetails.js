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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	NAMESPACE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from '../DetailField';

function GeneralDetails({
	assignParentAccountURL,
	details,
	parentAccountName,
	tiers
}) {
	const parentAccountFormData = {parentAccountKey: ''};
	const formData = {
		code: convertDashToEmptyString(details.code),
		name: convertDashToEmptyString(details.name),
		region: convertDashToEmptyString(details.region),
		tier: convertDashToEmptyString(details.tier),
		updateAccount: true
	};

	function createSelectOptions(options) {
		return options.map(option => {
			return {
				label: option,
				value: option
			};
		});
	}

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('general-details')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('account-name')}
				fieldName="name"
				formAction={details.editAccountURL}
				formData={formData}
				value={details.name}
			/>

			<DetailField
				displayAs="label"
				fieldLabel={Liferay.Language.get('state')}
				inputStyle={details.subscriptionStateStyle}
				type={FIELD_TYPE_NONEDITABLE}
				value={details.subscriptionState}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('code')}
				fieldName="code"
				formAction={details.editAccountURL}
				formData={formData}
				value={details.code}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('created')}
				type={FIELD_TYPE_NONEDITABLE}
				value={details.dateCreated}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('tier')}
				fieldName="tier"
				formAction={details.editAccountURL}
				formData={formData}
				options={createSelectOptions(tiers)}
				type={FIELD_TYPE_SELECT}
				value={details.tier}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('last-modified')}
				type={FIELD_TYPE_NONEDITABLE}
				value={details.dateModified}
			/>

			<DetailField
				externalData={{
					formField: 'parentAccountKey',
					formName: `${NAMESPACE}editAccountHierarchyFm`,
					title: Liferay.Language.get('select-parent-account'),
					url: assignParentAccountURL
				}}
				fieldLabel={Liferay.Language.get('parent')}
				formAction={details.editAccountHierarchyURL}
				formData={parentAccountFormData}
				type={FIELD_TYPE_EXTERNAL}
				value={parentAccountName}
			/>
		</ClayList>
	);
}

GeneralDetails.propTypes = {
	assignParentAccountURL: PropTypes.string,
	details: PropTypes.shape({
		code: PropTypes.string,
		dateCreated: PropTypes.string,
		dateModified: PropTypes.string,
		editAccountHierarchyURL: PropTypes.string,
		editAccountURL: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		parterTeamName: PropTypes.string,
		region: PropTypes.string,
		subscriptionState: PropTypes.string,
		subscriptionStateStyle: PropTypes.string,
		tier: PropTypes.string
	}),
	parentAccountName: PropTypes.string,
	tiers: PropTypes.arrayOf(PropTypes.string).isRequired
};

export default GeneralDetails;

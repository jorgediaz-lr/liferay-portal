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

import {usePermissions} from '../../hooks/permissions';
import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	NAMESPACE
} from '../../utilities/constants';
import DetailField from '../DetailField';

function PartnerInfo({
	assignFirstLineSupportTeamURL,
	assignPartnerTeamURL,
	details
}) {
	const {updatePermission} = usePermissions();
	const fieldType = updatePermission
		? FIELD_TYPE_EXTERNAL
		: FIELD_TYPE_NONEDITABLE;

	const firstLineSupportFormData = {
		firstLineSupportTeamKey: '',
		updateFirstLineSupport: true
	};
	const partnerFormData = {
		partnerTeamKey: '',
		updatePartner: true
	};

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>

			<DetailField
				externalData={{
					formField: 'partnerTeamKey',
					formName: `${NAMESPACE}updatePartnerFm`,
					title: Liferay.Language.get('select-partner-team'),
					url: assignPartnerTeamURL
				}}
				fieldLabel={Liferay.Language.get('partner-reseller-si')}
				formAction={details.editAccountURL}
				formData={partnerFormData}
				type={fieldType}
				value={details.partnerTeamName}
			/>

			<DetailField
				externalData={{
					formField: 'firstLineSupportTeamKey',
					formName: `${NAMESPACE}updateFirstLineSupportFm`,
					title: Liferay.Language.get(
						'select-first-line-support-team'
					),
					url: assignFirstLineSupportTeamURL
				}}
				fieldLabel={Liferay.Language.get('first-line-support')}
				formAction={details.editAccountURL}
				formData={firstLineSupportFormData}
				type={fieldType}
				value={details.firstLineSupportTeamName}
			/>
		</ClayList>
	);
}

PartnerInfo.propTypes = {
	assignFirstLineSupportTeamURL: PropTypes.string,
	assignPartnerTeamURL: PropTypes.string,
	details: PropTypes.shape({
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		partnerTeamName: PropTypes.string
	})
};

export default PartnerInfo;

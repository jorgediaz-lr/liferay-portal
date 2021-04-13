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

import PropTypes from 'prop-types';
import React from 'react';

import {useLicense} from '../../hooks/license';
import {formatDate} from '../../utilities/date';
import {request} from '../../utilities/helpers';

function GenerateButton({formAction, redirect}) {
	const [license] = useLicense();
	const {
		licenseEntryId,
		licenseEntryName,
		licenseEntryType
	} = license.licenseEntry;
	const {productKey} = license.product;

	function handleSubmit() {
		const params = {
			...license.toJS(),
			expirationDate: formatDate(license.expirationDate),
			licenseEntryId,
			licenseEntryType,
			name: licenseEntryName,
			productKey,
			productVersion: license.version,
			serverIds: JSON.stringify(license.serverIds),
			startDate: formatDate(license.startDate)
		};

		request(formAction, params, 'formData', 'post')
			.then(({data}) => {
				if (data) {
					location.assign(redirect);
				}
			})
			.catch(err => console.error(err));
	}

	return (
		<button
			className="btn btn-primary"
			disabled={!license.description || !license.owner}
			onClick={handleSubmit}
			type="button"
		>
			{Liferay.Language.get('generate')}
		</button>
	);
}

GenerateButton.propTypse = {
	formAction: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired
};

export default GenerateButton;

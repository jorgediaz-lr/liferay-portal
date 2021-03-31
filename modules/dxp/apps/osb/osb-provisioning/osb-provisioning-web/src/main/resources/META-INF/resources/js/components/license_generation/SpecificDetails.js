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

import {useGenerateLicense} from '../../hooks/generateLicense';
import CancelLink from '../CancelLink';

function SpecificDetails({redirect}) {
	const [generateLicense, {updateLicense}] = useGenerateLicense();

	function handleDisplayPreviousPage() {
		updateLicense(generateLicense =>
			generateLicense.set('showSpecificDetails', false)
		);
	}

	return (
		<>
			<div className="page-steps">
				<span>{Liferay.Language.get('specific-details')}</span>

				<span>{Liferay.Language.get('step-2-of-2')}</span>
			</div>

			<button
				className="btn btn-secondary"
				onClick={handleDisplayPreviousPage}
				type="button"
			>
				{Liferay.Language.get('previous-step')}
			</button>

			<CancelLink redirect={redirect} />
		</>
	);
}

SpecificDetails.propTypes = {
	redirect: PropTypes.string
};

export default SpecificDetails;

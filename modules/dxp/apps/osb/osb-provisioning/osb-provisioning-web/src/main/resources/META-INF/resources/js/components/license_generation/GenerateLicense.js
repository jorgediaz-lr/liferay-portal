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

import {NewLicenseProvider, useNewLicense} from '../../hooks/newLicense';
import {PermissionsProvider} from '../../hooks/permissions';
import GeneralInformation from './GeneralInformation';
import SpecificDetails from './SpecificDetails';

function GenerateLicense(props) {
	const {allowPermanentLicenses, hasUpdateLicenseDatePermission} = props;

	return (
		<NewLicenseProvider initialLicense={{allowPermanentLicenses}}>
			<PermissionsProvider
				permissions={{
					updateDatePermission: hasUpdateLicenseDatePermission
				}}
			>
				<Generate {...props} />
			</PermissionsProvider>
		</NewLicenseProvider>
	);
}

function Generate(props) {
	const [license] = useNewLicense();

	return (
		<>
			{!license.showSpecificDetails && <GeneralInformation {...props} />}

			{license.showSpecificDetails && (
				<SpecificDetails
					addLicenseKeyURL={props.addLicenseKeyURL}
					redirect={props.redirect}
				/>
			)}
		</>
	);
}

GenerateLicense.propTypes = {
	allowPermanentLicenses: PropTypes.bool.isRequired,
	hasUpdateLicenseDatePermission: PropTypes.bool.isRequired
};

export default GenerateLicense;

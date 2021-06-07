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

import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {useLicenses} from '../../hooks/licenses';
import {LICENSE_TYPE_PRODUCTION} from '../../utilities/constants';
import {groupByAll} from '../../utilities/helpers';
import LicenseGroup from './LicenseGroup';

const MIN_LICENSE_GROUPABLE_VERSION_NUMBER = 3;

function IndividualLicenses({downloadURL}) {
	const [licenses] = useLicenses();

	const [activeVersionCompliantLicenses, oldInactiveLicenses] = partition(
		licenses.toSet().toJS(),
		({active, licenseVersion}) =>
			licenseVersion > MIN_LICENSE_GROUPABLE_VERSION_NUMBER && active
	);

	const [licensesTypeProduction, licensesTypeOther] = partition(
		activeVersionCompliantLicenses,
		({licenseEntryType}) => licenseEntryType === LICENSE_TYPE_PRODUCTION
	);

	const intersection = licensesTypeProduction.length
		? groupByAll(
				licensesTypeProduction,
				({startDate}) => startDate,
				({expirationDate}) => expirationDate,
				({licenseVersion}) => licenseVersion,
				({productVersion}) => productVersion
		  )
		: licensesTypeProduction;

	function formatLicenses(licenses) {
		return licenses.map(license => [license]);
	}

	return (
		<>
			{!!intersection.length && (
				<LicenseGroup downloadURL={downloadURL} items={intersection} />
			)}

			{!!licensesTypeOther.length && (
				<LicenseGroup
					downloadURL={downloadURL}
					items={formatLicenses(licensesTypeOther)}
				/>
			)}

			{!!oldInactiveLicenses.length && (
				<LicenseGroup
					downloadURL={downloadURL}
					items={formatLicenses(oldInactiveLicenses)}
				/>
			)}
		</>
	);
}

IndividualLicenses.propTypes = {
	downloadURL: PropTypes.string.isRequired
};

export default IndividualLicenses;

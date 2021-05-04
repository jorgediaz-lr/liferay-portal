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

import ClayTable from '@clayui/table';
import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {LICENSE_TYPE_PER_USER} from '../../utilities/constants';
import {groupByAll} from '../../utilities/helpers';
import LicenseGroup from './LicenseGroup';

const MIN_LICENSE_GROUPABLE_VERSION_NUMBER = 3;

function DownloadLicenses({downloadLicenseKeysURL, licenseKeys}) {
	const [activeVersionCompliantLicenses, oldInactiveLicenses] = partition(
		licenseKeys,
		({active, licenseVersion}) =>
			licenseVersion >= MIN_LICENSE_GROUPABLE_VERSION_NUMBER && active
	);

	const groupedLicenses = groupByAll(
		activeVersionCompliantLicenses,
		({startDate}) => startDate,
		({expirationDate}) => expirationDate,
		({licenseEntryType}) => licenseEntryType,
		groupByMaxUsers,
		groupByMaxConcurrentUsers
	);

	function groupByMaxConcurrentUsers(license) {
		if (license.licenseEntryType === LICENSE_TYPE_PER_USER) {
			return license.maxConcurrentUsers;
		}

		return license;
	}

	function groupByMaxUsers(license) {
		if (license.licenseEntryType === LICENSE_TYPE_PER_USER) {
			return license.maxUsers;
		}

		return license;
	}

	return (
		<div className="download-licenses-container">
			<ClayTable>
				<ClayTable.Head>
					<ClayTable.Row>
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('name-description')}
						</ClayTable.Cell>
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('product')}
						</ClayTable.Cell>
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('type')}
						</ClayTable.Cell>
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('start-date')}
						</ClayTable.Cell>
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('expiration-date')}
						</ClayTable.Cell>
						<ClayTable.Cell headingCell>
							{Liferay.Language.get('maximum-users')}
						</ClayTable.Cell>
						<ClayTable.Cell headingCell>
							{Liferay.Language.get('maximum-concurrent-users')}
						</ClayTable.Cell>
						<ClayTable.Cell headingCell>
							{Liferay.Language.get('status')}
						</ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Head>

				{!!groupedLicenses.length && (
					<LicenseGroup
						downloadURL={downloadLicenseKeysURL}
						licenses={groupedLicenses}
					/>
				)}

				{!!oldInactiveLicenses.length && (
					<LicenseGroup
						downloadURL={downloadLicenseKeysURL}
						licenses={oldInactiveLicenses.map(license => [license])}
					/>
				)}
			</ClayTable>
		</div>
	);
}

DownloadLicenses.propTypes = {
	downloadLicenseKeysURL: PropTypes.string.isRequired,
	licenseKeys: PropTypes.arrayOf(
		PropTypes.shape({
			active: PropTypes.bool,
			description: PropTypes.string,
			expirationDate: PropTypes.string,
			licenseEntryName: PropTypes.string,
			licenseEntryType: PropTypes.string,
			licenseKeyId: PropTypes.string,
			licenseVersion: PropTypes.number,
			maxConcurrentUsers: PropTypes.string,
			maxUsers: PropTypes.string,
			name: PropTypes.string,
			productName: PropTypes.string,
			productVersion: PropTypes.string,
			startDate: PropTypes.string
		})
	)
};

export default DownloadLicenses;

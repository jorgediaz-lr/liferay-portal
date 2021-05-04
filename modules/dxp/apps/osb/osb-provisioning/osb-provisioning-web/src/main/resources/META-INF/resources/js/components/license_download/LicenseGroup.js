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
import capitalize from 'lodash.capitalize';
import PropTypes from 'prop-types';
import React from 'react';

import {DASH, LICENSE_PORTLET_NAMESPACE} from '../../utilities/constants';

function LicenseGroup({downloadURL, licenses}) {
	let value = [];

	return licenses.map((group, index) => (
		<ClayTable.Body key={index}>
			{group.map(license => {
				value = [...value, license.licenseKeyId];

				return <License key={license.licenseKeyId} license={license} />;
			})}
			<Download actionURL={downloadURL} value={value.join()} />
		</ClayTable.Body>
	));
}

function Download({actionURL, value}) {
	return (
		<ClayTable.Row>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell>
				<form action={actionURL} method="post" name="downloadLicenses">
					<input
						name={`${LICENSE_PORTLET_NAMESPACE}licenseKeyId`}
						type="hidden"
						value={value}
					/>
					<button className="btn btn-secondary btn-sm" type="submit">
						{Liferay.Language.get('download')}
					</button>
				</form>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

function License({license}) {
	const {
		active,
		description,
		expirationDate,
		licenseEntryName,
		licenseEntryType,
		licenseKeyId,
		maxConcurrentUsers,
		maxUsers,
		name,
		productName,
		startDate
	} = license;

	return (
		<ClayTable.Row id={licenseKeyId}>
			<ClayTable.Cell className="semi-bold">
				{name}
				<div className="secondary-information">{description}</div>
			</ClayTable.Cell>
			<ClayTable.Cell>{productName}</ClayTable.Cell>
			<ClayTable.Cell>{`${licenseEntryName} (${capitalize(
				licenseEntryType
			)})`}</ClayTable.Cell>
			<ClayTable.Cell>{startDate}</ClayTable.Cell>
			<ClayTable.Cell>{expirationDate}</ClayTable.Cell>
			<ClayTable.Cell>
				{maxUsers === '0' ? DASH : maxUsers}
			</ClayTable.Cell>
			<ClayTable.Cell>
				{maxConcurrentUsers === '0' ? DASH : maxConcurrentUsers}
			</ClayTable.Cell>
			<ClayTable.Cell>
				{active ? (
					<span className="label label-success">
						{Liferay.Language.get('active')}
					</span>
				) : (
					<span className="label label-danger">
						{Liferay.Language.get('deactivated')}
					</span>
				)}
			</ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
		</ClayTable.Row>
	);
}

LicenseGroup.propTypes = {
	downloadURL: PropTypes.string.isRequired,
	licenses: PropTypes.arrayOf(
		PropTypes.arrayOf(
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
	)
};

export default LicenseGroup;

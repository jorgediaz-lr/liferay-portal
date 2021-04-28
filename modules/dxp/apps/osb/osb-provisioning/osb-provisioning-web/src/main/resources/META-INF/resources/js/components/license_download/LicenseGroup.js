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

function LicenseGroup({downloadURL, licenses}) {
	return licenses.map((group, index) => (
		<ClayTable.Body key={index}>
			{group.map(license => (
				<License key={license.licenseKeyId} license={license} />
			))}
			<Download downloadURL={downloadURL} />
		</ClayTable.Body>
	));
}

function Download({downloadURL}) {
	return (
		<ClayTable.Row>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell></ClayTable.Cell>
			<ClayTable.Cell>
				<button className="btn btn-primary" type="submit">
					{Liferay.Language.get('download')}
				</button>
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
				<label
					className="custom-checkbox custom-control"
					htmlFor="active"
				>
					<input
						aria-label={Liferay.Language.get('active-subscription')}
						checked={active}
						className="custom-control-input"
						id="active"
						readOnly
						role="checkbox"
						type="checkbox"
					/>
					<span className="custom-control-label"></span>
				</label>
			</ClayTable.Cell>
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

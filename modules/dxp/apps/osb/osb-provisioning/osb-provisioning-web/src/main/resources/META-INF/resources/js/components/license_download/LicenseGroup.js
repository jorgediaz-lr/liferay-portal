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
import PropTypes from 'prop-types';
import React from 'react';

function LicenseGroup() {
	return (
		<>
			<License />
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
		</>
	);
}

function License() {
	return (
		<ClayTable.Row id={''}>
			<ClayTable.Cell className="semi-bold">
				name, descrption
			</ClayTable.Cell>
			<ClayTable.Cell>product</ClayTable.Cell>
			<ClayTable.Cell>type</ClayTable.Cell>
			<ClayTable.Cell>start date</ClayTable.Cell>
			<ClayTable.Cell>exp date</ClayTable.Cell>
			<ClayTable.Cell>
				<label
					className="custom-checkbox custom-control"
					htmlFor="active"
				>
					<input
						aria-label={Liferay.Language.get('active-subscription')}
						checked={true}
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
	downloadURL: PropTypes.string.isRequired
};

export default LicenseGroup;

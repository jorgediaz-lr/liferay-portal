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

import ExtensionDetails from './ExtensionDetails';

export default function DetailsGroup({extensionURL, licenses}) {
	const singleLicense = licenses.length === 1;

	function handleOnSubmit() {}

	return (
		<>
			<ExtensionDetails extensionURL={extensionURL} licenses={licenses} />

			{!singleLicense && (
				<ClayTable.Body>
					<ClayTable.Row>
						<ClayTable.Cell colSpan={6}></ClayTable.Cell>
						<ClayTable.Cell>
							<>
								<button
									className="btn btn-secondary btn-sm"
									onClick={handleOnSubmit}
									role="button"
									type="button"
								>
									{Liferay.Language.get('extend')}
								</button>
							</>
						</ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Body>
			)}
		</>
	);
}

DetailsGroup.propTypes = {
	extensionURL: PropTypes.string,
	licenses: PropTypes.array
};

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

import TableDivider from '../TableDivider';
import ExtensionDetails from './ExtensionDetails';

export default function SingleExtension({extensionURL, licenses}) {
	return (
		<>
			{licenses[0].indefinite && (
				<tbody>
					<TableDivider
						colSpan={8}
						title={Liferay.Language.get('permanent-licenses')}
					/>
				</tbody>
			)}

			<ExtensionDetails extensionURL={extensionURL} licenses={licenses} />
		</>
	);
}

SingleExtension.propTypes = {
	extensionURL: PropTypes.string.isRequired,
	licenses: PropTypes.array.isRequired
};

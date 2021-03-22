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
import ClayTableCell from '@clayui/table/lib/Cell';
import PropTypes from 'prop-types';
import React from 'react';

const DASH = '-';

function Purchase({
	dividerTitle,
	expirationDate = DASH,
	licenseKeysGenerated = DASH,
	sizing = DASH,
	startDate = DASH
}) {
	return (
		<>
			{!!dividerTitle && (
				<ClayTable.Row divider={true}>
					<ClayTableCell colSpan={5}>{dividerTitle}</ClayTableCell>
				</ClayTable.Row>
			)}

			<ClayTable.Row>
				<ClayTableCell className="semi-bold">{startDate}</ClayTableCell>
				<ClayTableCell className="semi-bold">
					{expirationDate}
				</ClayTableCell>
				<ClayTableCell>{sizing}</ClayTableCell>
				<ClayTableCell>{licenseKeysGenerated}</ClayTableCell>
				<ClayTableCell>
					<button className="btn btn-secondary btn-sm">
						{Liferay.Language.get('choose')}
					</button>
				</ClayTableCell>
			</ClayTable.Row>
		</>
	);
}

Purchase.protoType = {
	dividerTitle: PropTypes.string,
	expirationDate: PropTypes.string,
	licenseKeysGenerated: PropTypes.string,
	sizing: PropTypes.number,
	startDate: PropTypes.string
};

export default Purchase;

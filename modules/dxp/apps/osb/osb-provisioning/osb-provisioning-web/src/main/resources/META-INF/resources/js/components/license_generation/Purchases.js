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

function Purchases({purchased}) {
	return (
		<div className="choose-purchase">
			<h4>{Liferay.Language.get('choose-purchase')}</h4>

			<ClayTable>
				<ClayTable.Head>
					<ClayTable.Row>
						<ClayTableCell headingCell>
							{Liferay.Language.get('start-date')}
						</ClayTableCell>
						<ClayTableCell headingCell>
							{Liferay.Language.get('expiration-date')}
						</ClayTableCell>
						<ClayTableCell headingCell>
							{Liferay.Language.get('instance-size')}
						</ClayTableCell>
						<ClayTableCell headingCell>
							{Liferay.Language.get('license-key-generated')}
						</ClayTableCell>
						<ClayTableCell headingCell>{''}</ClayTableCell>
					</ClayTable.Row>
				</ClayTable.Head>
				<ClayTable.Body>
					<Purchase />
					<Purchase dividerTitle={Liferay.Language.get('detached')} />
				</ClayTable.Body>
			</ClayTable>
		</div>
	);
}

function Purchase({dividerTitle}) {
	return (
		<>
			{!!dividerTitle && (
				<ClayTable.Row divider={true}>
					<ClayTableCell colSpan={5}>{dividerTitle}</ClayTableCell>
				</ClayTable.Row>
			)}

			<ClayTable.Row>
				<ClayTableCell>{''}</ClayTableCell>
				<ClayTableCell>{''}</ClayTableCell>
				<ClayTableCell>{''}</ClayTableCell>
				<ClayTableCell>{''}</ClayTableCell>
				<ClayTableCell>
					<button className="btn btn-secondary btn-sm">
						{Liferay.Language.get('choose')}
					</button>
				</ClayTableCell>
			</ClayTable.Row>
		</>
	);
}

Purchases.protoType = {
	purchased: PropTypes.array
};

export default Purchases;

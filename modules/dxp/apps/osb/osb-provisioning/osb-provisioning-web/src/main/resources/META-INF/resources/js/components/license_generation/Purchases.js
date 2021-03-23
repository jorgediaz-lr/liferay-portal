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

import {
	displayInMDYDateFormat,
	generateNewDate,
	getUTCAdjustedDate
} from '../../utilities/date';
import Purchase from './Purchase';

const TYPE_DEVELOPER = 'developer';

function Purchases({detached, purchased, type}) {
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
					<Purchased purchased={purchased} selectedType={type} />
					<Detached detached={detached} />
				</ClayTable.Body>
			</ClayTable>
		</div>
	);
}

function Detached({detached}) {
	const formattedDates = {};

	if (detached) {
		const startDate = getUTCAdjustedDate(new Date());
		const expirationDate = generateNewDate(startDate);

		formattedDates.expirationDate = displayInMDYDateFormat(expirationDate);
		formattedDates.startDate = displayInMDYDateFormat(startDate);
	}

	return (
		<Purchase
			dividerTitle={Liferay.Language.get('detached')}
			{...detached}
			{...formattedDates}
		/>
	);
}

function Purchased({purchased, selectedType}) {
	return (
		<>
			{!!purchased &&
				purchased.map((item, index) => {
					let expirationDate;
					let startDate;

					if (item.perpetual) {
						startDate = getUTCAdjustedDate(new Date());

						expirationDate = generateNewDate(startDate, 100);
					}
					else {
						startDate = getUTCAdjustedDate(
							new Date(item.startDate)
						);

						expirationDate = getUTCAdjustedDate(
							new Date(item.expirationDate)
						);

						if (selectedType !== TYPE_DEVELOPER) {
							expirationDate = generateNewDate(
								expirationDate,
								100
							);
						}
					}

					const formattedDates = {
						expirationDate: displayInMDYDateFormat(expirationDate),
						startDate: displayInMDYDateFormat(startDate)
					};

					return (
						<Purchase
							key={item.productPurchaseKey || index}
							{...item}
							{...formattedDates}
						/>
					);
				})}
		</>
	);
}

Purchases.protoType = {
	detached: PropTypes.shape({
		instanceSize: PropTypes.arrayOf(PropTypes.number),
		licenseKeysGenerated: PropTypes.number,
		startDate: PropTypes.string
	}),
	purchased: PropTypes.arrayOf(
		PropTypes.shape({
			expirationDate: PropTypes.string,
			licenseKeysGenerated: PropTypes.string,
			perpetual: PropTypes.bool,
			productPurchaseKey: PropTypes.string,
			sizing: PropTypes.number,
			startDate: PropTypes.string
		})
	),
	type: PropTypes.string
};

export default Purchases;

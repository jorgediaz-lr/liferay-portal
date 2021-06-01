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
import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {
	RESTRICTED_EXPIRATION_DATE_TYPES
} from '../../utilities/constants';
import {generateNewDateByYear} from '../../utilities/date';
import Purchase from './Purchase';

const TODAY = new Date();

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
							{Liferay.Language.get('licenses-generated')}
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
	const licenseDates = {};

	if (detached) {
		licenseDates.licenseStartDate = TODAY;

		licenseDates.licenseExpirationDate = generateNewDateByYear(
			licenseDates.licenseStartDate
		);
	}

	return (
		<>
			<DividerTitle title={Liferay.Language.get('detached')} />

			<Purchase {...detached} {...licenseDates} />
		</>
	);
}

function DividerTitle({title}) {
	return (
		<ClayTable.Row divider={true}>
			<ClayTableCell colSpan={5}>{title}</ClayTableCell>
		</ClayTable.Row>
	);
}

function Purchased({purchased, selectedType}) {
	const processedPurchased = purchased
		? purchased.map(item => {
				if (item.perpetual) {
					const licenseStartDate = TODAY;
					const licenseExpirationDate = generateNewDateByYear(
						licenseStartDate,
						100
					);

					return {
						...item,
						expired: false,
						licenseExpirationDate,
						licenseStartDate
					};
				}
				else {
					const licenseStartDate = new Date(item.startDate);
					const licenseExpirationDate = setExpirationDate(
						item,
						selectedType
					);
					const expired = new Date(item.endDate) < TODAY;

					return {
						...item,
						expired,
						licenseExpirationDate,
						licenseStartDate
					};
				}
		  })
		: [];
	const [active, expired] = partition(
		processedPurchased,
		({expired}) => !expired
	);

	function setExpirationDate(license, type) {
		const restricted = RESTRICTED_EXPIRATION_DATE_TYPES.find(
			restrictedType => restrictedType === type
		);

		let expirationDate = new Date(license.endDate);

		if (!restricted) {
			expirationDate = generateNewDateByYear(expirationDate, 100);
		}

		return expirationDate;
	}

	return (
		<>
			{!!active.length && (
				<>
					<DividerTitle
						title={Liferay.Language.get('active-subscriptions')}
					/>

					{active.map((item, index) => (
						<Purchase
							key={item.productPurchaseKey || index}
							{...item}
						/>
					))}
				</>
			)}

			{!!expired.length && (
				<>
					<DividerTitle
						title={Liferay.Language.get('expired-subscriptions')}
					/>

					{expired.map((item, index) => (
						<Purchase
							key={item.productPurchaseKey || index}
							{...item}
						/>
					))}
				</>
			)}
		</>
	);
}

Purchases.protoType = {
	detached: PropTypes.shape({
		instanceSize: PropTypes.arrayOf(PropTypes.number),
		licenseKeysGenerated: PropTypes.string,
		startDate: PropTypes.string
	}),
	purchased: PropTypes.arrayOf(
		PropTypes.shape({
			endDate: PropTypes.string,
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

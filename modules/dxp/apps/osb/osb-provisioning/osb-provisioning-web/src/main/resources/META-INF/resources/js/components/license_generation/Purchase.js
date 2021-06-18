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
import React, {useEffect, useState} from 'react';

import {useNewLicense} from '../../hooks/newLicense';
import {usePermissions} from '../../hooks/permissions';
import {
	DASH,
	RESTRICTED_EXPIRATION_DATE_TYPES
} from '../../utilities/constants';
import {formatDate} from '../../utilities/date';
import DatePicker from '../DatePicker';

const YEAR_IN_MS = 1000 * 60 * 60 * 24 * 365;

function Purchase({
	detached = false,
	instanceSize = DASH,
	instanceSizes,
	licenseExpirationDate,
	licenseKeysGenerated = DASH,
	licenseStartDate,
	productPurchaseKey = ''
}) {
	const [disableChoose, setDisableChoose] = useState(false);
	const [selectedExpirationDate, setSelectedExpirationDate] = useState(
		licenseExpirationDate
	);
	const [selectedStartDate, setSelectedStartDate] = useState(
		licenseStartDate
	);
	const [sizing, setSizing] = useState();

	const [validDates, setValidDates] = useState(
		!isNaN(new Date(licenseExpirationDate)) &&
			!isNaN(new Date(licenseStartDate))
	);

	const [{licenseEntry}, {updateLicense}] = useNewLicense();
	const {updateDatePermission} = usePermissions();

	const restricted = RESTRICTED_EXPIRATION_DATE_TYPES.find(
		restrictedType => restrictedType === licenseEntry.licenseEntryType
	);

	useEffect(() => {
		setDisableChoose(!validDates);
	}, [validDates]);

	function handleChoosePurchase() {
		updateLicense(license =>
			license
				.set('expirationDate', selectedExpirationDate)
				.set('startDate', selectedStartDate)

				.set('licenseKeysGenerated', licenseKeysGenerated)
				.set('productPurchaseKey', productPurchaseKey)
				.set('sizing', sizing ? sizing : instanceSize)

				.set('showSpecificDetails', true)
		);
	}

	function handleExpirationDateChange(val) {
		const expiration = Date.parse(new Date(val));
		const start = Date.parse(new Date(selectedStartDate));

		setSelectedExpirationDate(val);

		setValidDates(start < expiration);
	}

	function handleStartDateChange(val) {
		const expiration = Date.parse(new Date(selectedExpirationDate));
		const start = Date.parse(new Date(val));

		setSelectedStartDate(val);

		setValidDates(start < expiration);
	}

	function handleSizingChange(event) {
		setSizing(event.currentTarget.value);
	}

	function validateExpirationDateChange(val) {
		const expiration = Date.parse(new Date(val));
		const start = Date.parse(new Date(selectedStartDate));

		setSelectedExpirationDate(val);

		setValidDates(expiration - start <= YEAR_IN_MS && start < expiration);
	}

	return (
		<ClayTable.Row id={productPurchaseKey}>
			{licenseStartDate ? (
				<ClayTableCell
					className={`input-group-sm ${
						!validDates ? 'has-error' : ''
					}`}
				>
					<DatePicker
						defaultValue={licenseStartDate}
						inputName="startDate"
						updateFn={handleStartDateChange}
					/>
				</ClayTableCell>
			) : (
				<ClayTableCell>{DASH}</ClayTableCell>
			)}

			{!!licenseExpirationDate &&
				(updateDatePermission ||
					(!updateDatePermission && !restricted)) && (
					<ClayTableCell
						className={`input-group-sm ${
							!validDates ? 'has-error' : ''
						}`}
					>
						<DatePicker
							defaultValue={licenseExpirationDate}
							inputName="expirationDate"
							updateFn={handleExpirationDateChange}
						/>
					</ClayTableCell>
				)}

			{!!licenseExpirationDate && !updateDatePermission && restricted && (
				<>
					{!detached && (
						<ClayTableCell>
							{formatDate(licenseExpirationDate)}
						</ClayTableCell>
					)}

					{detached && (
						<ClayTableCell
							className={`input-group-sm ${
								!validDates ? 'has-error' : ''
							}`}
						>
							<DatePicker
								defaultValue={licenseExpirationDate}
								inputName="expirationDate"
								updateFn={validateExpirationDateChange}
							/>
						</ClayTableCell>
					)}
				</>
			)}

			{!licenseExpirationDate && <ClayTableCell>{DASH}</ClayTableCell>}

			<ClayTableCell>
				{instanceSizes ? (
					<label htmlFor="instanceSize">
						<select
							aria-label={Liferay.Language.get('instance-size')}
							className="form-control form-control-sm"
							disabled={!instanceSizes.length}
							id="instanceSize"
							onChange={handleSizingChange}
							value={sizing}
						>
							{instanceSizes.map(size => (
								<option key={size} value={size}>
									{size}
								</option>
							))}
						</select>
					</label>
				) : (
					instanceSize
				)}
			</ClayTableCell>
			<ClayTableCell>{licenseKeysGenerated}</ClayTableCell>
			<ClayTableCell>
				<button
					className="btn btn-secondary btn-sm"
					disabled={disableChoose}
					onClick={handleChoosePurchase}
				>
					{Liferay.Language.get('choose')}
				</button>
			</ClayTableCell>
		</ClayTable.Row>
	);
}

Purchase.protoType = {
	detached: PropTypes.bool,
	instanceSize: PropTypes.number,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	licenseExpirationDate: PropTypes.string,
	licenseKeysGenerated: PropTypes.string,
	licenseStartDate: PropTypes.string,
	productPurchaseKey: PropTypes.string
};

export default Purchase;

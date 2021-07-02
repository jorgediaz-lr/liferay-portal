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
import {
	DASH,
	RESTRICTED_EXPIRATION_DATE_TYPES
} from '../../utilities/constants';
import LicenseDates from '../LicenseDates';

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

	const restricted = !!RESTRICTED_EXPIRATION_DATE_TYPES.find(
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

	function handleSelectedExpirationDateChange(val) {
		setSelectedExpirationDate(val);
	}

	function handleSelectedStartDateChange(val) {
		setSelectedStartDate(val);
	}

	function handleSizingChange(event) {
		setSizing(event.currentTarget.value);
	}

	function handleValidateDates(bool) {
		setValidDates(bool);
	}

	return (
		<ClayTable.Row id={productPurchaseKey}>
			<LicenseDates
				detached={detached}
				expirationDate={selectedExpirationDate}
				restricted={restricted}
				startDate={selectedStartDate}
				updateExpirationDate={handleSelectedExpirationDateChange}
				updateStartDate={handleSelectedStartDateChange}
				updateValidation={handleValidateDates}
				validDates={validDates}
			/>

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
	licenseExpirationDate: PropTypes.oneOfType([
		PropTypes.instanceOf(Date),
		PropTypes.string
	]),
	licenseKeysGenerated: PropTypes.string,
	licenseStartDate: PropTypes.oneOfType([
		PropTypes.instanceOf(Date),
		PropTypes.string
	]),
	productPurchaseKey: PropTypes.string
};

export default Purchase;

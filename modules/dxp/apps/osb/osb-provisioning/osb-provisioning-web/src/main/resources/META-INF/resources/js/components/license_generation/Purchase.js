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

import {useLicense} from '../../hooks/license';
import {DASH} from '../../utilities/constants';
import DatePicker from '../DatePicker';

function Purchase({
	dividerTitle,
	expirationDate,
	instanceSize = DASH,
	instanceSizes,
	licenseKeysGenerated = DASH,
	productPurchaseKey = '',
	startDate
}) {
	const [disableChoose, setDisableChoose] = useState(false);
	const [selectedExpirationDate, setSelectedExpirationDate] = useState(
		expirationDate
	);
	const [selectedStartDate, setSelectedStartDate] = useState(startDate);
	const [sizing, setSizing] = useState();

	const [, {updateLicense}] = useLicense();

	useEffect(() => {
		if (
			!isNaN(new Date(selectedExpirationDate)) &&
			!isNaN(new Date(selectedStartDate))
		) {
			setDisableChoose(false);
		}
		else {
			setDisableChoose(true);
		}
	}, [selectedExpirationDate, selectedStartDate]);

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
		setSelectedExpirationDate(val);
	}

	function handleStartDateChange(val) {
		setSelectedStartDate(val);
	}

	function handleSizingChange(event) {
		setSizing(event.currentTarget.value);
	}

	return (
		<>
			{!!dividerTitle && (
				<ClayTable.Row divider={true}>
					<ClayTableCell colSpan={5}>{dividerTitle}</ClayTableCell>
				</ClayTable.Row>
			)}

			<ClayTable.Row id={dividerTitle ? dividerTitle : ''}>
				{startDate ? (
					<ClayTableCell
						className={`input-group-sm ${
							isNaN(new Date(selectedStartDate))
								? 'has-error'
								: ''
						}`}
					>
						<DatePicker
							defaultValue={startDate}
							inputName="startDate"
							updateFn={handleStartDateChange}
						/>
					</ClayTableCell>
				) : (
					<ClayTableCell>{DASH}</ClayTableCell>
				)}

				{expirationDate ? (
					<ClayTableCell
						className={`input-group-sm ${
							isNaN(new Date(selectedExpirationDate))
								? 'has-error'
								: ''
						}`}
					>
						<DatePicker
							defaultValue={expirationDate}
							inputName="expirationDate"
							updateFn={handleExpirationDateChange}
						/>
					</ClayTableCell>
				) : (
					<ClayTableCell>{DASH}</ClayTableCell>
				)}

				<ClayTableCell>
					{instanceSizes ? (
						<label htmlFor="instanceSize">
							<select
								aria-label={Liferay.Language.get(
									'instance-size'
								)}
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
		</>
	);
}

Purchase.protoType = {
	dividerTitle: PropTypes.string,
	expirationDate: PropTypes.string,
	instanceSize: PropTypes.number,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	licenseKeysGenerated: PropTypes.string,
	productPurchaseKey: PropTypes.string,
	startDate: PropTypes.string
};

export default Purchase;

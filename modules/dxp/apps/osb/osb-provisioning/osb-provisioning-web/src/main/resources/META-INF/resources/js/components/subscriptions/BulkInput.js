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

import {ClayCheckbox} from '@clayui/form';
import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useCallback, useEffect, useRef, useState} from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';
import {
	ADD_SUBSCRIPTIONS,
	EDIT_SUBSCRIPTIONS,
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../utilities/constants';
import {
	convertInputToDate,
	setDisabledAttribute,
	validateDateFieldFormat
} from '../../utilities/date';
import DatePicker from '../DatePicker';

function BulkInput({
	accountName,
	instanceSizes = [],
	statusOptions = [
		PRODUCT_PURCHASE_STATUS_APPROVED,
		PRODUCT_PURCHASE_STATUS_CANCELLED
	],
	subscriptionsType
}) {
	const [subscriptions, {updateAllValuesByFieldName}] = useSubscriptions();

	const quantityRef = useRef();
	const salesforceOpportunityKeyRef = useRef();
	const sizingRef = useRef();
	const statusRef = useRef();

	const getDisplayValue = useCallback(
		fieldName => {
			if (identicalValues(fieldName)) {
				return subscriptions.toList().first()[fieldName];
			}
			else {
				return '';
			}
		},
		[identicalValues, subscriptions]
	);

	const identicalValues = useCallback(
		fieldName => {
			const fieldValues = new Set(
				subscriptions.toList().map(subscription => {
					const field = subscription[fieldName];

					if (field instanceof Date) {
						return field.toJSON();
					}
					else {
						return field;
					}
				})
			);

			return fieldValues.size === 1;
		},
		[subscriptions]
	);

	const [showField, setShowField] = useState({
		perpetual: identicalValues('perpetual'),
		quantity: identicalValues('quantity'),
		salesforceOpportunityKey: identicalValues('salesforceOpportunityKey'),
		sizing: identicalValues('sizing'),
		status: identicalValues('status')
	});

	const [perpetual, setPerpetual] = useState(getDisplayValue('perpetual'));
	const [quantity, setQuantity] = useState(getDisplayValue('quantity'));
	const [salesforceOpportunityKey, setSalesforceOpportunityKey] = useState(
		getDisplayValue('salesforceOpportunityKey')
	);
	const [sizing, setSizing] = useState(getDisplayValue('sizing'));
	const [status, setStatus] = useState(getDisplayValue('status'));

	const [invalidDate, setInvalidDate] = useState({
		endDate: false,
		originalEndDate: false,
		startDate: false
	});

	useEffect(() => {
		setDisabledAttribute('bulkInput', perpetual);
	});

	useEffect(() => {
		setShowField({
			perpetual: identicalValues('perpetual'),
			quantity: identicalValues('quantity'),
			salesforceOpportunityKey: identicalValues(
				'salesforceOpportunityKey'
			),
			sizing: identicalValues('sizing'),
			status: identicalValues('status')
		});

		setPerpetual(getDisplayValue('perpetual'));
		setQuantity(getDisplayValue('quantity'));
		setSalesforceOpportunityKey(
			getDisplayValue('salesforceOpportunityKey')
		);
		setSizing(getDisplayValue('sizing'));
		setStatus(getDisplayValue('status'));
	}, [getDisplayValue, identicalValues, subscriptions]);

	useSetFocus(quantityRef, showField.quantity);
	useSetFocus(
		salesforceOpportunityKeyRef,
		showField.salesforceOpportunityKey
	);
	useSetFocus(sizingRef, showField.sizing);
	useSetFocus(statusRef, showField.status);

	function getDatePickerDisplayValue(fieldName) {
		if (identicalValues('perpetual')) {
			return getDisplayValue(fieldName);
		}
		else {
			return '';
		}
	}

	function handleOnClickPerpetual() {
		setShowField({...showField, perpetual: true});
		setPerpetual(false);

		updateAllValuesByFieldName('perpetual', false);
	}

	function handleOnClickQuantity() {
		setShowField({...showField, quantity: true});
	}

	function handleOnClickSalesforceOpportunityKey() {
		setShowField({...showField, salesforceOpportunityKey: true});
	}

	function handleOnClickSizing() {
		setShowField({...showField, sizing: true});
	}

	function handleOnClickStatus() {
		setShowField({...showField, status: true});
	}

	function handleSaveEndDate(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateAllValuesByFieldName('endDate', convertInputToDate(value));
		}

		setInvalidDate({...invalidDate, endDate: !validDateFormat});
	}

	function handleSaveGracePeriodStartDate(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateAllValuesByFieldName(
				'originalEndDate',
				convertInputToDate(value)
			);
		}

		setInvalidDate({...invalidDate, originalEndDate: !validDateFormat});
	}

	function handleSavePerpetual() {
		setDisabledAttribute('bulkInput', !perpetual);
		setPerpetual(!perpetual);

		updateAllValuesByFieldName('perpetual', !perpetual);
	}

	function handleSaveQuantity(event) {
		updateAllValuesByFieldName('quantity', event.currentTarget.value);
	}

	function handleSaveSalesforceOpportunityKey(event) {
		updateAllValuesByFieldName(
			'salesforceOpportunityKey',
			event.currentTarget.value
		);
	}

	function handleSaveSizing(event) {
		updateAllValuesByFieldName('sizing', event.currentTarget.value);
	}

	function handleSaveStartDate(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateAllValuesByFieldName('startDate', convertInputToDate(value));
		}

		setInvalidDate({...invalidDate, startDate: !validDateFormat});
	}

	function handleSaveStatus(event) {
		updateAllValuesByFieldName('status', event.currentTarget.value);
	}

	return (
		<ClayTable.Row className="bulk-input" id="bulkInput">
			<ClayTable.Cell className="input-title semi-bold">
				{Liferay.Language.get('bulk-input')}
			</ClayTable.Cell>
			<ClayTable.Cell>
				{showField.salesforceOpportunityKey && (
					<label
						htmlFor="salesforceOpportunityKeyBulkInput"
						ref={salesforceOpportunityKeyRef}
					>
						<input
							aria-label={Liferay.Language.get(
								'salesforce-opportunity-key-bulk-input'
							)}
							className="form-control form-control-sm"
							id="salesforceOpportunityKeyBulkInput"
							onChange={handleSaveSalesforceOpportunityKey}
							type="text"
							value={salesforceOpportunityKey}
						/>
					</label>
				)}

				{!showField.salesforceOpportunityKey && (
					<VariedData
						clickFn={handleOnClickSalesforceOpportunityKey}
						name={Liferay.Language.get(
							'salesforce-opportunity-key-bulk-input'
						)}
					/>
				)}
			</ClayTable.Cell>
			<ClayTable.Cell>
				{showField.quantity && (
					<label htmlFor="quantityBulkInput" ref={quantityRef}>
						<input
							aria-label={Liferay.Language.get(
								'purchased-bulk-input'
							)}
							className="form-control form-control-sm"
							id="quantityBulkInput"
							min={1}
							onChange={handleSaveQuantity}
							type="number"
							value={quantity}
						/>
					</label>
				)}

				{!showField.quantity && (
					<VariedData
						clickFn={handleOnClickQuantity}
						name={Liferay.Language.get('purchased-bulk-input')}
					/>
				)}
			</ClayTable.Cell>
			<ClayTable.Cell>
				{showField.perpetual && (
					<ClayCheckbox
						aria-checked={perpetual}
						aria-label={Liferay.Language.get(
							'perpetual-subscription-bulk-input'
						)}
						checked={perpetual}
						className="custom-control-input"
						id="perpetualBulkInput"
						onChange={handleSavePerpetual}
						role="checkbox"
					/>
				)}

				{!showField.perpetual && (
					<ClayCheckbox
						aria-label={Liferay.Language.get(
							'perpetual-subscription-bulk-input'
						)}
						className="custom-control-input"
						id="perpetualBulkInput"
						indeterminate
						onChange={handleOnClickPerpetual}
						role="checkbox"
					/>
				)}
			</ClayTable.Cell>
			<ClayTable.Cell
				className={!invalidDate.startDate ? '' : 'has-error'}
			>
				<label htmlFor="startDateBulkInput">
					<DatePicker
						defaultValue={getDatePickerDisplayValue('startDate')}
						id="startDateBulkInput"
						inputName="startDateBulkInput"
						placeholder={Liferay.Language.get('varied-data')}
						updateFn={handleSaveStartDate}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell
				className={!invalidDate.originalEndDate ? '' : 'has-error'}
			>
				<label htmlFor="gracePeriodStartDateBulkInput">
					<DatePicker
						defaultValue={getDatePickerDisplayValue(
							'originalEndDate'
						)}
						id="gracePeriodStartDateBulkInput"
						inputName="gracePeriodStartDateBulkInput"
						placeholder={Liferay.Language.get('varied-data')}
						updateFn={handleSaveGracePeriodStartDate}
					/>
				</label>
			</ClayTable.Cell>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell>
					{showField.status && (
						<label htmlFor="statusBulkInput" ref={statusRef}>
							<select
								aria-label={Liferay.Language.get(
									'subscription-status-bulk-input'
								)}
								className="form-control form-control-sm"
								disabled={statusOptions.length === 0}
								id="status"
								onChange={handleSaveStatus}
								value={status}
							>
								{statusOptions.map(option => (
									<option key={option} value={option}>
										{option}
									</option>
								))}
							</select>
						</label>
					)}

					{!showField.status && (
						<VariedData
							clickFn={handleOnClickStatus}
							name={Liferay.Language.get(
								'subscription-status-bulk-input'
							)}
						/>
					)}
				</ClayTable.Cell>
			)}

			<ClayTable.Cell>
				{showField.sizing && (
					<label htmlFor="instanceSizeBulkInput" ref={sizingRef}>
						<select
							aria-label={Liferay.Language.get(
								'instance-size-bulk-input'
							)}
							className="form-control form-control-sm"
							disabled={!instanceSizes.length}
							id="instanceSizeBulkInput"
							onChange={handleSaveSizing}
							value={sizing}
						>
							{instanceSizes.map(size => (
								<option key={size} value={size}>
									{size}
								</option>
							))}
						</select>
					</label>
				)}

				{!showField.sizing && (
					<VariedData
						clickFn={handleOnClickSizing}
						name={Liferay.Language.get('instance-size-bulk-input')}
					/>
				)}
			</ClayTable.Cell>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell
					className={!invalidDate.endDate ? '' : 'has-error'}
				>
					<label htmlFor="endDateBulkInput">
						<DatePicker
							defaultValue={getDatePickerDisplayValue('endDate')}
							id="endDateBulkInput"
							inputName="endDateBulkInput"
							placeholder={Liferay.Language.get('varied-data')}
							updateFn={handleSaveEndDate}
						/>
					</label>
				</ClayTable.Cell>
			)}

			<ClayTable.Cell>{accountName}</ClayTable.Cell>
			<ClayTable.Cell>{''}</ClayTable.Cell>
		</ClayTable.Row>
	);
}

BulkInput.protoTypes = {
	accountName: PropTypes.string.isRequired,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	statusOptions: PropTypes.arrayOf(PropTypes.string),
	subscriptionsType: PropTypes.oneOf([ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS])
		.isRequired
};

function useSetFocus(ref, state) {
	return useEffect(() => {
		if (ref.current) {
			ref.current.focus();
		}
	}, [ref, state]);
}

function VariedData({clickFn, name = ''}) {
	return (
		<button
			className="form-control form-control-sm varied-data"
			name={name}
			onClick={clickFn}
			type="button"
		>
			{Liferay.Language.get('varied-data')}
		</button>
	);
}

export default BulkInput;

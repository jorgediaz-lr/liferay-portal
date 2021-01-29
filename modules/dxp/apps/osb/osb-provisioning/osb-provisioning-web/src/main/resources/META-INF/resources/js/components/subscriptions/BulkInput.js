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
import {displayUTCDate, setDisabledAttribute} from '../../utilities/helpers';
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

	useEffect(() => {
		setDisabledAttribute(perpetual, 'bulkInput');
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

		setQuantity(getDisplayValue('quantity'));
		setSalesforceOpportunityKey(
			getDisplayValue('salesforceOpportunityKey')
		);
		setSizing(getDisplayValue('sizing'));
		setStatus(getDisplayValue('status'));
	}, [getDisplayValue, identicalValues, subscriptions]);

	useEffect(() => {
		if (quantityRef.current) {
			quantityRef.current.focus();
		}
	}, [showField.quantity]);

	useEffect(() => {
		if (salesforceOpportunityKeyRef.current) {
			salesforceOpportunityKeyRef.current.focus();
		}
	}, [showField.salesforceOpportunityKey]);

	useEffect(() => {
		if (sizingRef.current) {
			sizingRef.current.focus();
		}
	}, [showField.sizing]);

	useEffect(() => {
		if (statusRef.current) {
			statusRef.current.focus();
		}
	}, [showField.status]);

	function getDatePickerDisplayValue(fieldName) {
		if (identicalValues('perpetual')) {
			return displayUTCDate(getDisplayValue(fieldName));
		}
		else {
			return '';
		}
	}

	function handleOnChangePerpetual() {
		setDisabledAttribute(!perpetual, 'bulkInput');
		setPerpetual(!perpetual);
	}

	function handleOnChangeQuantity(event) {
		setQuantity(event.currentTarget.value);
	}

	function handleOnChangeSalesforceOpportunityKey(event) {
		setSalesforceOpportunityKey(event.currentTarget.value);
	}

	function handleOnChangeSizing(event) {
		setSizing(event.currentTarget.value);
	}

	function handleOnChangeStatus(event) {
		setStatus(event.currentTarget.value);
	}

	function handleOnClickPerpetual() {
		setShowField({...showField, perpetual: true});
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

	function handleOnKeyDownPerpetual(event) {
		if (event.keyCode === 13) {
			handleSavePerpetual();
		}
	}

	function handleOnKeyDownQuantity(event) {
		if (event.keyCode === 13) {
			handleSaveQuantity();
		}
	}

	function handleOnKeyDownSalesforceOpportunityKey(event) {
		if (event.keyCode === 13) {
			handleSaveSalesforceOpportunityKey();
		}
	}

	function handleOnKeyDownSizing(event) {
		if (event.keyCode === 13) {
			handleSaveSizing();
		}
	}

	function handleOnKeyDownStatus(event) {
		if (event.keyCode === 13) {
			handleSaveStatus();
		}
	}

	function handleSavePerpetual() {
		updateAllValuesByFieldName('perpetual', perpetual);
	}

	function handleSaveQuantity() {
		updateAllValuesByFieldName('quantity', quantity);
	}

	function handleSaveSalesforceOpportunityKey() {
		updateAllValuesByFieldName(
			'salesforceOpportunityKey',
			salesforceOpportunityKey
		);
	}

	function handleSaveSizing() {
		updateAllValuesByFieldName('sizing', sizing);
	}

	function handleSaveStatus() {
		updateAllValuesByFieldName('status', status);
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
							onBlur={handleSaveSalesforceOpportunityKey}
							onChange={handleOnChangeSalesforceOpportunityKey}
							onKeyDown={handleOnKeyDownSalesforceOpportunityKey}
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
							onBlur={handleSaveQuantity}
							onChange={handleOnChangeQuantity}
							onKeyDown={handleOnKeyDownQuantity}
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
						onBlur={handleSavePerpetual}
						onChange={handleOnChangePerpetual}
						onKeyDown={handleOnKeyDownPerpetual}
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
			<ClayTable.Cell>
				<label htmlFor="startDateBulkInput">
					<DatePicker
						defaultValue={getDatePickerDisplayValue('startDate')}
						id="startDateBulkInput"
						inputName="startDateBulkInput"
						placeholder={Liferay.Language.get('varied-data')}
						updateFn={() => {}}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="gracePeriodStartDateBulkInput">
					<DatePicker
						defaultValue={getDatePickerDisplayValue(
							'originalEndDate'
						)}
						id="gracePeriodStartDateBulkInput"
						inputName="gracePeriodStartDateBulkInput"
						placeholder={Liferay.Language.get('varied-data')}
						updateFn={() => {}}
					/>
				</label>
			</ClayTable.Cell>
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
							onBlur={handleSaveSizing}
							onChange={handleOnChangeSizing}
							onKeyDown={handleOnKeyDownSizing}
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
				<ClayTable.Cell>
					<label htmlFor="endDateBulkInput">
						<DatePicker
							defaultValue={getDatePickerDisplayValue('endDate')}
							id="endDateBulkInput"
							inputName="endDateBulkInput"
							placeholder={Liferay.Language.get('varied-data')}
							updateFn={() => {}}
						/>
					</label>
				</ClayTable.Cell>
			)}

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
								onBlur={handleSaveStatus}
								onChange={handleOnChangeStatus}
								onKeyDown={handleOnKeyDownStatus}
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

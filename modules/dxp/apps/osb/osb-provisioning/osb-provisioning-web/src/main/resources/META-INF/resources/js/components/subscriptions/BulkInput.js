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
	generateNewDateByDay,
	getIntervalInDays,
	setDisabledAttribute,
	validateDateFieldFormat
} from '../../utilities/date';
import DatePicker from '../DatePicker';

function BulkInput({
	accountName,
	dateFormatValidators,
	instanceSizes = [],
	statusOptions = [
		PRODUCT_PURCHASE_STATUS_APPROVED,
		PRODUCT_PURCHASE_STATUS_CANCELLED
	],
	subscriptionsType,
	updateBulkGracePeriod
}) {
	const [subscriptions, {updateAllValues}] = useSubscriptions();

	const gracePeriodRef = useRef();
	const quantityRef = useRef();
	const salesforceOpportunityKeyRef = useRef();
	const sizingRef = useRef();
	const statusRef = useRef();

	const fieldValueSet = useCallback(
		fieldName =>
			new Set(
				subscriptions.toList().map(subscription => {
					if (fieldName === 'gracePeriod') {
						return getIntervalInDays(
							subscription.originalEndDate,
							subscription.endDate
						);
					}

					const field = subscription[fieldName];

					return field instanceof Date ? field.toJSON() : field;
				})
			),
		[subscriptions]
	);

	const getDisplayValue = useCallback(
		fieldName => {
			const set = fieldValueSet(fieldName);

			if (set.size === 1) {
				return set.values().next().value;
			}

			return '';
		},
		[fieldValueSet]
	);

	const identicalFieldValues = useCallback(
		fieldName => {
			const set = fieldValueSet(fieldName);

			return set.size === 1;
		},
		[fieldValueSet]
	);

	const [showField, setShowField] = useState({
		gracePeriod: identicalFieldValues('gracePeriod'),
		perpetual: identicalFieldValues('perpetual'),
		quantity: identicalFieldValues('quantity'),
		salesforceOpportunityKey: identicalFieldValues(
			'salesforceOpportunityKey'
		),
		sizing: identicalFieldValues('sizing'),
		status: identicalFieldValues('status')
	});

	const [gracePeriod, setGracePeriod] = useState(
		getDisplayValue('gracePeriod')
	);
	const [perpetual, setPerpetual] = useState(getDisplayValue('perpetual'));
	const [quantity, setQuantity] = useState(getDisplayValue('quantity'));
	const [salesforceOpportunityKey, setSalesforceOpportunityKey] = useState(
		getDisplayValue('salesforceOpportunityKey')
	);
	const [sizing, setSizing] = useState(getDisplayValue('sizing'));
	const [status, setStatus] = useState(getDisplayValue('status'));

	const [invalidDateFormat, setInvalidDateFormat] = useState({
		endDate: false,
		originalEndDate: false,
		startDate: false
	});

	useEffect(() => {
		setDisabledAttribute('bulkInput', perpetual);
	});

	useEffect(() => {
		setShowField({
			gracePeriod: identicalFieldValues('gracePeriod'),
			perpetual: identicalFieldValues('perpetual'),
			quantity: identicalFieldValues('quantity'),
			salesforceOpportunityKey: identicalFieldValues(
				'salesforceOpportunityKey'
			),
			sizing: identicalFieldValues('sizing'),
			status: identicalFieldValues('status')
		});
	}, [identicalFieldValues]);

	useEffect(() => {
		setGracePeriod(getDisplayValue('gracePeriod'));
		setPerpetual(getDisplayValue('perpetual'));
		setQuantity(getDisplayValue('quantity'));
		setSalesforceOpportunityKey(
			getDisplayValue('salesforceOpportunityKey')
		);
		setSizing(getDisplayValue('sizing'));
		setStatus(getDisplayValue('status'));
	}, [getDisplayValue]);

	useSetFocus(gracePeriodRef, showField.gracePeriod);
	useSetFocus(quantityRef, showField.quantity);
	useSetFocus(
		salesforceOpportunityKeyRef,
		showField.salesforceOpportunityKey
	);
	useSetFocus(sizingRef, showField.sizing);
	useSetFocus(statusRef, showField.status);

	function getDatePickerDisplayValue(fieldName) {
		if (identicalFieldValues('perpetual')) {
			return getDisplayValue(fieldName);
		}
		else {
			return '';
		}
	}

	function handleOnClickGracePeriod() {
		setShowField({...showField, gracePeriod: true});
	}

	function handleOnClickPerpetual() {
		setShowField({...showField, perpetual: true});
		setPerpetual(false);

		updateAllValues(subscription => subscription.set('perpetual', false));
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

	function handleSaveEndDate(event) {
		const {value} = event.currentTarget;

		setGracePeriod(value);
		updateBulkGracePeriod(value);

		const validGracePeriod = validateCurrentGracePeriod(value);

		updateAllValues(subscription => {
			return subscription.update('endDate', endDate =>
				validGracePeriod
					? generateNewDateByDay(subscription.originalEndDate, value)
					: endDate
			);
		});

		dateFormatValidators(['bulk', 'endDate'], validGracePeriod);
		setInvalidDateFormat({
			...invalidDateFormat,
			endDate: !validGracePeriod
		});
	}

	function handleSaveGracePeriodStartDate(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateAllValues(subscription =>
				subscription
					.set('originalEndDate', convertInputToDate(value))
					.update('endDate', endDate =>
						validateCurrentGracePeriod(gracePeriod)
							? generateNewDateByDay(
									subscription.originalEndDate,
									gracePeriod
							  )
							: endDate
					)
			);
		}

		dateFormatValidators(['bulk', 'originalEndDate'], validDateFormat);

		setInvalidDateFormat({
			...invalidDateFormat,
			originalEndDate: !validDateFormat
		});
	}

	function handleSavePerpetual() {
		setDisabledAttribute('bulkInput', !perpetual);
		setPerpetual(!perpetual);

		updateAllValues(subscription =>
			subscription.set('perpetual', !perpetual)
		);
	}

	function handleSaveQuantity(event) {
		updateAllValues(subscription =>
			subscription.set('quantity', event.currentTarget.value)
		);
	}

	function handleSaveSalesforceOpportunityKey(event) {
		updateAllValues(subscription =>
			subscription.set(
				'salesforceOpportunityKey',
				event.currentTarget.value
			)
		);
	}

	function handleSaveSizing(event) {
		updateAllValues(subscription =>
			subscription.set('sizing', event.currentTarget.value)
		);
	}

	function handleSaveStartDate(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateAllValues(subscription =>
				subscription.set('startDate', convertInputToDate(value))
			);
		}

		dateFormatValidators(['bulk', 'startDate'], validDateFormat);
		setInvalidDateFormat({
			...invalidDateFormat,
			startDate: !validDateFormat
		});
	}

	function handleSaveStatus(event) {
		updateAllValues(subscription =>
			subscription.set('status', event.currentTarget.value)
		);
	}

	function validateCurrentGracePeriod(currentGracePeriod) {
		return currentGracePeriod !== '';
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
				className={!invalidDateFormat.startDate ? '' : 'has-error'}
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
				className={
					!invalidDateFormat.originalEndDate ? '' : 'has-error'
				}
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
					className={!invalidDateFormat.endDate ? '' : 'has-error'}
				>
					{showField.gracePeriod && (
						<label htmlFor="endDateBulkInput">
							<div className="input-group" id="endDateBulkInput">
								<div className="input-group-item">
									<input
										aria-label={Liferay.Language.get(
											'grace-period-bulk-input'
										)}
										className="form-control form-control-sm input-group-inset input-group-inset-after"
										disabled={perpetual}
										min={0}
										onChange={handleSaveEndDate}
										ref={gracePeriodRef}
										type="number"
										value={gracePeriod}
									/>
									<div
										className={`${
											perpetual ? 'disabled' : ''
										} input-group-inset-item input-group-inset-item-after`}
									>
										{Liferay.Language.get('days')}
									</div>
								</div>
							</div>
						</label>
					)}

					{!showField.gracePeriod && (
						<VariedData
							clickFn={handleOnClickGracePeriod}
							disabled={perpetual}
							name={Liferay.Language.get(
								'grace-period-bulk-input'
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
	dateFormatValidators: PropTypes.func,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	statusOptions: PropTypes.arrayOf(PropTypes.string),
	subscriptionsType: PropTypes.oneOf([ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS])
		.isRequired,
	updateBulkGracePeriod: PropTypes.func
};

function useSetFocus(ref, state) {
	return useEffect(() => {
		if (ref.current) {
			ref.current.focus();
		}
	}, [ref, state]);
}

function VariedData({clickFn, disabled = false, name = ''}) {
	return (
		<button
			aria-label={name}
			className="form-control form-control-sm varied-data"
			disabled={disabled}
			name={name}
			onClick={clickFn}
			type="button"
		>
			{Liferay.Language.get('varied-data')}
		</button>
	);
}

export default BulkInput;

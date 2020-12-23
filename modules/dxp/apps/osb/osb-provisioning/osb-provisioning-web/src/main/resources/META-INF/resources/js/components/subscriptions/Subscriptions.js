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
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';
import {
	ADD_SUBSCRIPTIONS,
	EDIT_SUBSCRIPTIONS,
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../utilities/constants';
import DatePicker from '../DatePicker';

function Subscriptions({
	accountName,
	instanceSizes = [],
	statusOptions = [
		PRODUCT_PURCHASE_STATUS_APPROVED,
		PRODUCT_PURCHASE_STATUS_CANCELLED
	],
	subscriptionsType
}) {
	const [subscriptions] = useSubscriptions();

	return (
		<ClayTable>
			<ClayTable.Head>
				<ClayTable.Row className="subscriptions-table-heading">
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('product')}
					</ClayTable.Cell>
					<ClayTable.Cell
						className="field-required"
						expanded
						headingCell
					>
						{Liferay.Language.get('salesforce-opportunity-key')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('purchased')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('perpetual-subscription')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('start-date')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('end-date')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('instance-size')}
					</ClayTable.Cell>

					{subscriptionsType === EDIT_SUBSCRIPTIONS && (
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('grace-period-end-date')}
						</ClayTable.Cell>
					)}

					{subscriptionsType === EDIT_SUBSCRIPTIONS && (
						<ClayTable.Cell
							className="table-cell-expand-smallest"
							expanded
							headingCell
						>
							{Liferay.Language.get('status')}
						</ClayTable.Cell>
					)}

					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('account-name')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell></ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>
			<ClayTable.Body>
				{subscriptions.toList().map(detail => (
					<Subscription
						accountName={accountName}
						detail={detail}
						instanceSizes={instanceSizes}
						key={
							subscriptionsType === EDIT_SUBSCRIPTIONS
								? detail.key
								: detail.productKey
						}
						statusOptions={statusOptions}
						subscriptionsType={subscriptionsType}
					/>
				))}
			</ClayTable.Body>
		</ClayTable>
	);
}

Subscriptions.propTypes = {
	accountName: PropTypes.string.isRequired,
	instanceSizes: PropTypes.arrayOf(PropTypes.number),
	statusOptions: PropTypes.arrayOf(PropTypes.string),
	subscriptionsType: PropTypes.oneOf([ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS])
		.isRequired
};

function Subscription({
	accountName,
	detail,
	instanceSizes,
	statusOptions,
	subscriptionsType
}) {
	const [disableDelete, setDisableDelete] = useState(false);
	const [invalidStartDate, setInvalidStartDate] = useState(false);
	const [
		invalidGracePeriodStartDate,
		setInvalidGracePeriodStartDate
	] = useState(false);
	const [invalidEndDate, setInvalidEndDate] = useState(false);

	const [
		subscriptions,
		{
			deleteSubscription,
			updateEndDate,
			updateOriginalEndDate,
			updatePerpetual,
			updateQuantity,
			updateSalesforceOpportunityKey,
			updateSizing,
			updateStartDate,
			updateStatus,
			updateValidDates
		}
	] = useSubscriptions();

	const {
		endDate,
		originalEndDate,
		perpetual,
		productName,
		quantity,
		salesforceOpportunityKey,
		sizing,
		startDate,
		status
	} = detail;

	const key =
		subscriptionsType === EDIT_SUBSCRIPTIONS
			? detail.key
			: detail.productKey;

	// Source formatter locks @clayui/date-picker at version 3.0.7, which does not provide an API for disabling date picker while later versions do.

	useEffect(() => {
		setDisabledAttribute(perpetual);
	});

	useEffect(() => {
		if (subscriptions.toList().toArray().length === 1) {
			setDisableDelete(true);
		}
	}, [subscriptions]);

	useEffect(() => {
		if (
			!invalidEndDate &&
			!invalidGracePeriodStartDate &&
			!invalidStartDate
		) {
			updateValidDates(key, true);
		}
		else {
			updateValidDates(key, false);
		}
	}, [
		invalidEndDate,
		invalidGracePeriodStartDate,
		invalidStartDate,
		key,
		updateValidDates
	]);

	function handleEndDateChange(value) {
		setInvalidGracePeriodStartDate(originalEndDate > value);
		setInvalidStartDate(startDate > value);

		updateEndDate(key, value);
	}

	function handleGracePeriodStartDateChange(value) {
		setInvalidStartDate(startDate > value);

		if (endDate) {
			setInvalidEndDate(value > endDate);
		}

		updateOriginalEndDate(key, value);
	}

	function handleDeleteSubscription() {
		deleteSubscription(key);
	}

	function handlePerpetualChange() {
		updatePerpetual(key, !perpetual);

		// Source formatter locks @clayui/date-picker at version 3.0.7, which does not provide an API for disabling date picker while later versions do.

		setDisabledAttribute(!perpetual);

		if (!perpetual) {
			setInvalidEndDate(false);
			setInvalidGracePeriodStartDate(false);
		}
	}

	function handleQuantityChange(event) {
		updateQuantity(key, event.currentTarget.value);
	}

	function handleSalesforceOpportunityKeyChange(event) {
		updateSalesforceOpportunityKey(key, event.currentTarget.value);
	}

	function handleSizingChange(event) {
		updateSizing(key, event.currentTarget.value);
	}

	function handleStartDateChange(value) {
		if (endDate) {
			setInvalidEndDate(value > endDate);
		}

		setInvalidGracePeriodStartDate(value > originalEndDate);

		updateStartDate(key, value);
	}

	function handleStatusChange(event) {
		updateStatus(key, event.currentTarget.value);
	}

	function setDisabledAttribute(attributeValue) {
		const dates = document.querySelectorAll(`#${key} .date-picker`);

		dates.forEach(date => {
			const dateBtn = date.querySelector('.date-picker-dropdown-toggle');
			const dateInput = date.querySelector('input.form-control');

			if (dateBtn && dateInput) {
				if (attributeValue) {
					dateBtn.setAttribute('disabled', attributeValue);
					dateInput.setAttribute('disabled', attributeValue);
				}
				else {
					dateBtn.removeAttribute('disabled');
					dateInput.removeAttribute('disabled');
				}
			}
		});
	}

	return (
		<ClayTable.Row id={key}>
			<ClayTable.Cell className="semi-bold">{productName}</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="salesforceOpportunityKey">
					<input
						aria-label={Liferay.Language.get(
							'salesforce-opportunity-key'
						)}
						className="form-control form-control-sm"
						id="salesforceOpportunityKey"
						onChange={handleSalesforceOpportunityKeyChange}
						type="text"
						value={salesforceOpportunityKey}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="quantity">
					<input
						className="form-control form-control-sm"
						id="quantity"
						min={0}
						onChange={handleQuantityChange}
						type="number"
						value={quantity}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label
					className="custom-checkbox custom-control"
					htmlFor="perpetual"
				>
					<input
						aria-checked={perpetual}
						checked={perpetual}
						className="custom-control-input"
						id="perpetual"
						onChange={handlePerpetualChange}
						role="checkbox"
						type="checkbox"
					/>
					<span className="custom-control-label"></span>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell className={invalidStartDate ? 'has-error' : ''}>
				<label htmlFor="startDate">
					<DatePicker
						defaultValue={startDate}
						id="startDate"
						inputName="startDate"
						updateFn={handleStartDateChange}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell
				className={invalidGracePeriodStartDate ? 'has-error' : ''}
			>
				<label htmlFor="gracePeriodStartDate">
					<DatePicker
						defaultValue={originalEndDate}
						id="gracePeriodStartDate"
						inputName="gracePeriodStartDate"
						updateFn={handleGracePeriodStartDateChange}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="instanceSize">
					<select
						className="form-control form-control-sm"
						disabled={instanceSizes.length === 0}
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
			</ClayTable.Cell>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell className={invalidEndDate ? 'has-error' : ''}>
					<label htmlFor="endDate">
						<DatePicker
							defaultValue={endDate}
							id="endDate"
							inputName="endDate"
							updateFn={handleEndDateChange}
						/>
					</label>
				</ClayTable.Cell>
			)}

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell>
					<label htmlFor="status">
						<select
							className="form-control form-control-sm"
							disabled={statusOptions.length === 0}
							id="status"
							onChange={handleStatusChange}
							value={status}
						>
							{statusOptions.map(option => (
								<option key={option} value={option}>
									{option}
								</option>
							))}
						</select>
					</label>
				</ClayTable.Cell>
			)}

			<ClayTable.Cell>{accountName}</ClayTable.Cell>
			<ClayTable.Cell>
				<button
					className="btn btn-icon btn-sm"
					disabled={disableDelete}
					onClick={handleDeleteSubscription}
					role="button"
					title={Liferay.Language.get('delete')}
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get(
							'delete-subscription-icon'
						)}
						className="delete-icon"
						role="img"
					>
						<use xlinkHref="#delete-icon" />
					</svg>
				</button>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

export default Subscriptions;

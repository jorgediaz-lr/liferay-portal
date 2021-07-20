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
import {
	convertInputToDate,
	setDisabledAttribute,
	validateDateFieldFormat
} from '../../utilities/date';
import DatePicker from '../DatePicker';
import IconButton from '../IconButton';
import BulkInput from './BulkInput';

function Subscriptions({
	accountName,
	instanceSizes = [],
	statusOptions = [
		PRODUCT_PURCHASE_STATUS_APPROVED,
		PRODUCT_PURCHASE_STATUS_CANCELLED
	],
	subscriptionsType,
	validateDateFormat
}) {
	const [subscriptions] = useSubscriptions();

	function getLicenseDateFormatValidator(keyPath, value) {
		validateDateFormat(keyPath, value);
	}

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
					<ClayTable.Cell
						className={
							subscriptions.size > 1 ? 'table-cell-expand' : ''
						}
						headingCell
					>
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

					{subscriptionsType === EDIT_SUBSCRIPTIONS && (
						<ClayTable.Cell
							className="table-cell-expand-smallest"
							expanded
							headingCell
						>
							{Liferay.Language.get('status')}
						</ClayTable.Cell>
					)}

					<ClayTable.Cell
						className={
							subscriptions.size > 1 ? 'table-cell-expand' : ''
						}
						headingCell
					>
						{Liferay.Language.get('instance-size')}
					</ClayTable.Cell>

					{subscriptionsType === EDIT_SUBSCRIPTIONS && (
						<ClayTable.Cell expanded headingCell>
							{Liferay.Language.get('grace-period-end-date')}
						</ClayTable.Cell>
					)}

					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('account-name')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell></ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>
			<ClayTable.Body>
				{subscriptions.size > 1 && (
					<BulkInput
						accountName={accountName}
						dateFormatValidators={getLicenseDateFormatValidator}
						instanceSizes={instanceSizes}
						statusOptions={statusOptions}
						subscriptionsType={subscriptionsType}
					/>
				)}

				{subscriptions.toList().map(subscription => (
					<Subscription
						accountName={accountName}
						dateFormatValidators={getLicenseDateFormatValidator}
						disableDelete={subscriptions.size === 1}
						instanceSizes={instanceSizes}
						key={
							subscriptionsType === EDIT_SUBSCRIPTIONS
								? `${subscription.key}_${subscription.index}`
								: `${subscription.productKey}_${subscription.index}`
						}
						statusOptions={statusOptions}
						subscription={subscription}
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
		.isRequired,
	validateDateFormat: PropTypes.func.isRequired
};

function Subscription({
	accountName,
	dateFormatValidators,
	disableDelete,
	instanceSizes,
	statusOptions,
	subscription,
	subscriptionsType
}) {
	const [invalidDateFormat, setInvalidDateFormat] = useState({
		endDate: false,
		originalEndDate: false,
		startDate: false
	});

	const [, {deleteSubscription, updateSubscription}] = useSubscriptions();

	const {
		endDate,
		index,
		originalEndDate,
		perpetual,
		productName,
		quantity,
		salesforceOpportunityKey,
		sizing,
		startDate,
		status
	} = subscription;

	const key =
		subscriptionsType === EDIT_SUBSCRIPTIONS
			? `${subscription.key}_${index}`
			: `${subscription.productKey}_${index}`;

	useEffect(() => {
		setDisabledAttribute(key, perpetual);
	});

	function handleDeleteSubscription() {
		deleteSubscription(key);
	}

	function handleEndDateChange(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateSubscription(key, subscription =>
				subscription.set('endDate', convertInputToDate(value))
			);
		}

		dateFormatValidators([key, 'endDate'], validDateFormat);
		setInvalidDateFormat({...invalidDateFormat, endDate: !validDateFormat});
	}

	function handleDeleteSubscription() {
		deleteSubscription(key);
	}

	function handleGracePeriodStartDateChange(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateSubscription(key, subscription =>
				subscription.set('originalEndDate', convertInputToDate(value))
			);
		}

		dateFormatValidators([key, 'originalEndDate'], validDateFormat);
		setInvalidDateFormat({
			...invalidDateFormat,
			originalEndDate: !validDateFormat
		});
	}

	function handlePerpetualChange() {
		updateSubscription(key, subscription =>
			subscription.set('perpetual', !perpetual)
		);

		setDisabledAttribute(key, !perpetual);
	}

	function handleQuantityChange(event) {
		updateSubscription(key, subscription =>
			subscription.set('quantity', event.currentTarget.value)
		);
	}

	function handleSalesforceOpportunityKeyChange(event) {
		updateSubscription(key, subscription =>
			subscription.set(
				'salesforceOpportunityKey',
				event.currentTarget.value
			)
		);
	}

	function handleSizingChange(event) {
		updateSubscription(key, subscription =>
			subscription.set('sizing', event.currentTarget.value)
		);
	}

	function handleStartDateChange(value) {
		const validDateFormat = validateDateFieldFormat(value);

		if (validDateFormat) {
			updateSubscription(key, subscription =>
				subscription.set('startDate', convertInputToDate(value))
			);
		}

		dateFormatValidators([key, 'startDate'], validDateFormat);
		setInvalidDateFormat({
			...invalidDateFormat,
			startDate: !validDateFormat
		});
	}

	function handleStatusChange(event) {
		updateSubscription(key, subscription =>
			subscription.set('status', event.currentTarget.value)
		);
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
						aria-label={Liferay.Language.get('purchased')}
						className="form-control form-control-sm"
						id="quantity"
						min={1}
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
						aria-label={Liferay.Language.get(
							'perpetual-subscription'
						)}
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
			<ClayTable.Cell
				className={
					subscription.validateStartDate() &&
					!invalidDateFormat.startDate
						? ''
						: 'has-error'
				}
			>
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
				className={
					subscription.validateGracePeriodStartDate() &&
					!invalidDateFormat.originalEndDate
						? ''
						: 'has-error'
				}
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

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell>
					<label htmlFor="status">
						<select
							aria-label={Liferay.Language.get('status')}
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

			<ClayTable.Cell>
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
			</ClayTable.Cell>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell
					className={
						subscription.validateEndDate() &&
						!invalidDateFormat.endDate
							? ''
							: 'has-error'
					}
				>
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

			<ClayTable.Cell>{accountName}</ClayTable.Cell>
			<ClayTable.Cell>
				<IconButton
					cssClass="btn-icon btn-sm"
					disabled={disableDelete}
					labelName={Liferay.Language.get('delete-subscription-icon')}
					onClick={handleDeleteSubscription}
					svgId="#delete-icon"
					title={Liferay.Language.get('delete')}
				/>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}

export default Subscriptions;

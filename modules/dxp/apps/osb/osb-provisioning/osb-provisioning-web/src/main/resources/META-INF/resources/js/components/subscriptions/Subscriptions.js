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

import ClayDatePicker from '@clayui/date-picker';
import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';

function Subscriptions({accountName, instanceSizes = []}) {
	const [subscriptions] = useSubscriptions();

	return (
		<ClayTable>
			<ClayTable.Head>
				<ClayTable.Row className="subscriptions-table-heading">
					<ClayTable.Cell headingCell>
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
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('start-date')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('end-date')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('instance-size')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
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
						key={detail.productKey}
					/>
				))}
			</ClayTable.Body>
		</ClayTable>
	);
}

Subscriptions.propTypes = {
	accountName: PropTypes.string.isRequired,
	instanceSizes: PropTypes.arrayOf(PropTypes.string)
};

function Subscription({accountName, detail, instanceSizes}) {
	const {
		endDate,
		perpetual,
		productKey,
		productName,
		quantity,
		salesforceOpportunityKey,
		sizing,
		startDate
	} = detail;

	const [currentEndDate, setCurrentEndDate] = useState(endDate);
	const [currentStartDate, setCurrentStartDate] = useState(startDate);

	const [
		,
		{
			deleteSubscription,
			updateEndDate,
			updatePerpetual,
			updateQuantity,
			updateSalesforceOpportunityKey,
			updateSizing,
			updateStartDate
		}
	] = useSubscriptions();

	function handleEndDateChange(event) {
		updateEndDate(productKey, event.currentTarget.value);
	}

	function handleDeleteSubscription() {
		deleteSubscription(productKey);
	}

	function handlePerpetualChange() {
		updatePerpetual(productKey, !perpetual);

		// Source formatter locks @clayui/date-picker at version 3.0.7, which does not provide an API for disabling date picker while later versions do.

		setDisabledAttribute(!perpetual);
	}

	function handleQuantityChange(event) {
		updateQuantity(productKey, event.currentTarget.value);
	}

	function handleSalesforceOpportunityKeyChange(event) {
		updateSalesforceOpportunityKey(productKey, event.currentTarget.value);
	}

	function handleSizingChange(event) {
		updateSizing(productKey, event.currentTarget.value);
	}

	function handleStartDateChange(event) {
		updateStartDate(productKey, event.currentTarget.value);
	}

	function setDisabledAttribute(attributeValue) {
		const dates = document.querySelectorAll(
			`#${detail.productKey} .date-picker`
		);

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
		<ClayTable.Row id={productKey}>
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
						className="custom-control-input"
						id="perpetual"
						onChange={handlePerpetualChange}
						role="checkbox"
						type="checkbox"
					/>
					<span className="custom-control-label"></span>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="startDate">
					<ClayDatePicker
						id="startDate"
						inputName="startDate"
						onChange={handleStartDateChange}
						onValueChange={setCurrentStartDate}
						placeholder="YYYY-MM-DD"
						value={currentStartDate}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="endDate">
					<ClayDatePicker
						id="endDate"
						inputName="endDate"
						onChange={handleEndDateChange}
						onValueChange={setCurrentEndDate}
						placeholder="YYYY-MM-DD"
						value={currentEndDate}
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
			<ClayTable.Cell>{accountName}</ClayTable.Cell>
			<ClayTable.Cell>
				<button
					className="btn btn-icon btn-sm"
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

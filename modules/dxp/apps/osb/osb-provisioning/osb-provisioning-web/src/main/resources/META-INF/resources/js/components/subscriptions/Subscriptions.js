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

function Subscriptions({instanceSizes = []}) {
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
				<Subscription instanceSizes={instanceSizes} />
			</ClayTable.Body>
		</ClayTable>
	);
}

Subscriptions.propTypes = {
	instanceSizes: PropTypes.arrayOf(PropTypes.string)
};

function Subscription({instanceSizes}) {
	const [endDate, setEndDate] = useState('');
	const [instanceSize, setInstanceSize] = useState('');
	const [perpetualSubscription, setPerpetualSubscription] = useState(false);
	const [purchased, setPurchased] = useState('');
	const [salesForceOpportunityKey, setSalesForceOpportunityKey] = useState(
		''
	);
	const [startDate, setStartDate] = useState('');

	function handleInstanceSizeChange(event) {
		setInstanceSize(event.currentTarget.value);
	}

	function handlePerpetualSubscriptionChange() {
		setPerpetualSubscription(!perpetualSubscription);

		// Source formatter locks @clayui/date-picker at version 3.0.7, which does not provide an API for disabling date picker while later versions do.
		setDisabledAttribute(!perpetualSubscription);
	}

	function handlePurchasedChange(event) {
		setPurchased(event.currentTarget.value);
	}

	function handleSalesForceOpportunityKeyChange(event) {
		setSalesForceOpportunityKey(event.currentTarget.value);
	}

	function handleDeleteSubscription() {
		// TODO
	}

	function setDisabledAttribute(attributeValue) {
		const dates = document.querySelectorAll('.date-picker');

		dates.forEach(date => {
			const dateBtn = date.querySelector('.date-picker-dropdown-toggle');
			const dateInput = date.querySelector('input.form-control');

			if (dateBtn && dateInput) {
				if (attributeValue) {
					dateBtn.setAttribute('disabled', attributeValue);
					dateInput.setAttribute('disabled', attributeValue);
				} else {
					dateBtn.removeAttribute('disabled');
					dateInput.removeAttribute('disabled');
				}
			}
		});
	}

	return (
		<ClayTable.Row id={1 /* TODO */}>
			<ClayTable.Cell>{'product name'}</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="salesForceOpportunityKey">
					<input
						className="form-control form-control-sm"
						id="salesForceOpportunityKey"
						onChange={handleSalesForceOpportunityKeyChange}
						type="text"
						value={salesForceOpportunityKey}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="purchased">
					<input
						className="form-control form-control-sm"
						id="purchased"
						min={0}
						onChange={handlePurchasedChange}
						type="number"
						value={purchased}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label
					className="custom-checkbox custom-control"
					htmlFor="perpetualSubscription"
				>
					<input
						className="custom-control-input"
						id="perpetualSubscription"
						onChange={handlePerpetualSubscriptionChange}
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
						onValueChange={setStartDate}
						placeholder="YYYY-MM-DD"
						value={startDate}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="endDate">
					<ClayDatePicker
						id="endDate"
						inputName="endDate"
						onValueChange={setEndDate}
						placeholder="YYYY-MM-DD"
						value={endDate}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="instanceSize">
					<select
						className="form-control form-control-sm"
						disabled={instanceSizes.length === 0}
						id="instanceSize"
						onChange={handleInstanceSizeChange}
						value={instanceSize}
					>
						{instanceSizes.map(size => (
							<option key={size} value={size}>
								{size}
							</option>
						))}
					</select>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>{'account name'}</ClayTable.Cell>
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

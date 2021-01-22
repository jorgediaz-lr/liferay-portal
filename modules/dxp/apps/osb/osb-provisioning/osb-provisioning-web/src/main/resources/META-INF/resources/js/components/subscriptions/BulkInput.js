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
import React from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';
import {
	ADD_SUBSCRIPTIONS,
	EDIT_SUBSCRIPTIONS,
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../utilities/constants';
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
	const [subscriptions] = useSubscriptions();

	function getDisplayValue(fieldName) {
		return subscriptions.toList().first()[fieldName];
	}

	function identicalValues(fieldName) {
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
	}

	return (
		<ClayTable.Row className="bulk-input" id="bulkInput">
			<ClayTable.Cell className="input-title semi-bold">
				{Liferay.Language.get('bulk-input')}
			</ClayTable.Cell>
			<ClayTable.Cell>
				{identicalValues('salesforceOpportunityKey') && (
					<label htmlFor="salesforceOpportunityKeyBulkInput">
						<input
							className="form-control form-control-sm"
							id="salesforceOpportunityKeyBulkInput"
							onChange={() => {}}
							type="text"
							value={getDisplayValue('salesforceOpportunityKey')}
						/>
					</label>
				)}

				{!identicalValues('salesforceOpportunityKey') && <VariedData />}
			</ClayTable.Cell>
			<ClayTable.Cell>
				{identicalValues('quantity') && (
					<label htmlFor="quantityBulkInput">
						<input
							className="form-control form-control-sm"
							id="quantityBulkInput"
							min={1}
							onChange={() => {}}
							type="number"
							value={getDisplayValue('quantity')}
						/>
					</label>
				)}

				{!identicalValues('quantity') && <VariedData />}
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label
					className="custom-checkbox custom-control"
					htmlFor="perpetualBulkInput"
				>
					{identicalValues('perpetual') && (
						<ClayCheckbox
							aria-checked={getDisplayValue('perpetual')}
							checked={getDisplayValue('perpetual')}
							className="custom-control-input"
							id="perpetualBulkInput"
							onChange={() => {}}
							role="checkbox"
						/>
					)}

					{!identicalValues('perpetual') && (
						<ClayCheckbox
							className="custom-control-input"
							id="perpetualBulkInput"
							indeterminate
							onChange={() => {}}
							role="checkbox"
						/>
					)}
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="startDateBulkInput">
					<DatePicker
						defaultValue={
							identicalValues('startDate')
								? getDisplayValue('startDate')
								: ''
						}
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
						defaultValue={
							identicalValues('originalEndDate')
								? getDisplayValue('originalEndDate')
								: ''
						}
						id="gracePeriodStartDateBulkInput"
						inputName="gracePeriodStartDateBulkInput"
						placeholder={Liferay.Language.get('varied-data')}
						updateFn={() => {}}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				{identicalValues('sizing') && (
					<label htmlFor="instanceSizeBulkInput">
						<select
							className="form-control form-control-sm"
							disabled={!instanceSizes.length}
							id="instanceSizeBulkInput"
							onChange={() => {}}
							value={getDisplayValue('sizing')}
						>
							{instanceSizes.map(size => (
								<option key={size} value={size}>
									{size}
								</option>
							))}
						</select>
					</label>
				)}

				{!identicalValues('sizing') && <VariedData />}
			</ClayTable.Cell>

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell>
					<label htmlFor="endDateBulkInput">
						<DatePicker
							defaultValue={
								identicalValues('endDate')
									? getDisplayValue('endDate')
									: ''
							}
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
					{identicalValues('status') && (
						<label htmlFor="statusBulkInput">
							<select
								className="form-control form-control-sm"
								disabled={statusOptions.length === 0}
								id="status"
								onChange={() => {}}
								value={getDisplayValue('status')}
							>
								{statusOptions.map(option => (
									<option key={option} value={option}>
										{option}
									</option>
								))}
							</select>
						</label>
					)}

					{!identicalValues('status') && <VariedData />}
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

function VariedData() {
	return (
		<button
			className="form-control form-control-sm varied-data"
			type="button"
		>
			{Liferay.Language.get('varied-data')}
		</button>
	);
}

export default BulkInput;

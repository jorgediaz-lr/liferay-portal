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

	return (
		<ClayTable.Row className="bulk-input" id="bulkInput">
			<ClayTable.Cell className="input-title semi-bold">
				{Liferay.Language.get('bulk-input')}
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="salesforceOpportunityKeyBulkInput">
					<input
						className="form-control form-control-sm"
						disabled
						id="salesforceOpportunityKeyBulkInput"
						type="text"
						value={Liferay.Language.get('varied-data')}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="quantityBulkInput">
					<input
						className="form-control form-control-sm"
						id="quantityBulkInput"
						min={0}
						onChange={() => {}}
						type="number"
						value={1}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label
					className="custom-checkbox custom-control"
					htmlFor="perpetualBulkInput"
				>
					<input
						aria-checked={false}
						checked={false}
						className="custom-control-input"
						id="perpetualBulkInput"
						onChange={() => {}}
						role="checkbox"
						type="checkbox"
					/>
					<span className="custom-control-label"></span>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="startDateBulkInput">
					<DatePicker
						defaultValue={new Date()}
						id="startDateBulkInput"
						inputName="startDateBulkInput"
						updateFn={() => {}}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="gracePeriodStartDateBulkInput">
					<DatePicker
						defaultValue={new Date()}
						id="gracePeriodStartDateBulkInput"
						inputName="gracePeriodStartDateBulkInput"
						updateFn={() => {}}
					/>
				</label>
			</ClayTable.Cell>
			<ClayTable.Cell>
				<label htmlFor="instanceSizeBulkInput">
					<select
						className="form-control form-control-sm"
						disabled={!instanceSizes.length}
						id="instanceSizeBulkInput"
						onChange={() => {}}
						value={instanceSizes[0]}
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
				<ClayTable.Cell>
					<label htmlFor="endDateBulkInput">
						<DatePicker
							defaultValue={new Date()}
							id="endDateBulkInput"
							inputName="endDateBulkInput"
							updateFn={() => {}}
						/>
					</label>
				</ClayTable.Cell>
			)}

			{subscriptionsType === EDIT_SUBSCRIPTIONS && (
				<ClayTable.Cell>
					<label htmlFor="statusBulkInput">
						<select
							className="form-control form-control-sm"
							disabled={statusOptions.length === 0}
							id="status"
							onChange={() => {}}
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

export default BulkInput;

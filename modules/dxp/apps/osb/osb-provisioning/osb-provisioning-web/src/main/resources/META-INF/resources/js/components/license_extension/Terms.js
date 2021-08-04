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

import PropTypes from 'prop-types';
import React from 'react';

import {
	DASH,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../utilities/constants';
import {displayInMDYDateFormat, getUTCAdjustedDate} from '../../utilities/date';

function Terms({termSelected = '', terms, updateTerms}) {
	function generateTermOptions() {
		return terms
			.filter(term => term.status !== PRODUCT_PURCHASE_STATUS_CANCELLED)
			.map(term => {
				if (term.perpetual) {
					return {
						label: Liferay.Language.get('perpetual'),
						value: term.productPurchaseKey
					};
				}

				const endDate = displayInMDYDateFormat(
					getUTCAdjustedDate(new Date(term.endDate))
				);
				const startDate = displayInMDYDateFormat(
					getUTCAdjustedDate(new Date(term.startDate))
				);

				return {
					label: `${startDate} ${DASH} ${endDate}`,
					value: term.productPurchaseKey
				};
			});
	}

	function handleOnChange(event) {
		updateTerms(event.currentTarget.value);
	}

	return (
		<div className="input-group">
			<div className="input-group-item">
				{!terms && DASH}

				{!!terms && (
					<label className="form-control-label" htmlFor="product">
						<select
							aria-label={Liferay.Language.get(
								'subscription-term'
							)}
							className="form-control"
							id="product"
							onChange={handleOnChange}
							value={termSelected}
						>
							{generateTermOptions().length > 1 &&
								termSelected === '' && (
									<option value=""></option>
								)}

							{generateTermOptions().map(option => (
								<option key={option.value} value={option.value}>
									{option.label}
								</option>
							))}
						</select>
					</label>
				)}
			</div>
		</div>
	);
}

Terms.propTypes = {
	termSelected: PropTypes.string,
	terms: PropTypes.arrayOf(
		PropTypes.shape({
			endDate: PropTypes.string,
			perpetual: PropTypes.bool,
			productPurchaseKey: PropTypes.string,
			startDate: PropTypes.string,
			status: PropTypes.string
		})
	),
	updateTerms: PropTypes.func.isRequired
};

export default Terms;

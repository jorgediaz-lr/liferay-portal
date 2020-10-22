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
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';

function Categorization({subscriptionStateNames, tierNames}) {
	return (
		<div className="panel-body">
			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('tier')}
				</h5>

				<CheckboxGroup fieldValues={tierNames} inputName="tiers" />
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('subscription-status')}
				</h5>

				{/* NOTE: Subscription Status selection options are mutually exclusive */}

				<CheckboxGroup fieldValues={subscriptionStateNames} inputName="subscriptionState" />
			</div>
		</div>
	);
}

Categorization.propTypes = {
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

function CheckboxGroup({fieldValues, inputName}) {
	const [values, setValues] = useState([]);

	function handleOnClick(event) {
		const currentValue = event.currentTarget.value;

		if (!values.includes(currentValue)) {
			setValues([...values, currentValue]);
		}
		else {
			setValues(values.filter(value => value !== currentValue));
		}
	}

	return (
		<>
			<input
				name={`${NAMESPACE}${inputName}`}
				type="hidden"
				value={values.join()}
			/>

			{fieldValues.map(field => (
				<ClayCheckbox
					aria-label={field}
					key={field}
					label={field}
					onClick={handleOnClick}
					value={field}
				/>
			))}
		</>
	);
}

export default Categorization;

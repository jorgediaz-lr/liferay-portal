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

import {NAMESPACE} from '../../../utilities/constants';

function Categorization({
	activeSLANames,
	regionNames,
	subscriptionStateNames,
	tierNames
}) {
	const binarySelection = [
		{
			label: Liferay.Language.get('yes'),
			value: true
		},
		{
			label: Liferay.Language.get('no'),
			value: false
		}
	];

	function simplifySLANames(names) {
		return names.map(name => ({
			label: name.replace(' Subscription', ''),
			value: name
		}));
	}

	return (
		<div className="panel-body">
			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('partner')}
				</h5>

				<CheckboxGroup
					fieldValues={binarySelection}
					inputName="partners"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('provides-fls')}
				</h5>

				<CheckboxGroup
					fieldValues={binarySelection}
					inputName="providesFLS"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('receives-fls')}
				</h5>

				<CheckboxGroup
					fieldValues={binarySelection}
					inputName="receivesFLS"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('internal')}
				</h5>

				<CheckboxGroup
					fieldValues={binarySelection}
					inputName="internals"
				/>
			</div>

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

				<CheckboxGroup
					fieldValues={subscriptionStateNames}
					inputName="subscriptionStates"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('subscription-level')}
				</h5>

				<CheckboxGroup
					fieldValues={simplifySLANames(activeSLANames)}
					inputName="activeSLAs"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('support-region')}
				</h5>

				<CheckboxGroup fieldValues={regionNames} inputName="regions" />
			</div>
		</div>
	);
}

Categorization.propTypes = {
	activeSLANames: PropTypes.array.isRequired,
	regionNames: PropTypes.array.isRequired,
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

			{fieldValues.map(field => {
				if (!field.label && !field.value) {
					return (
						<ClayCheckbox
							aria-label={field}
							key={field}
							label={field}
							onClick={handleOnClick}
							value={field}
						/>
					);
				}
				else {
					return (
						<ClayCheckbox
							aria-label={field.label}
							key={field.value}
							label={field.label}
							onClick={handleOnClick}
							value={field.value}
						/>
					);
				}
			})}
		</>
	);
}

export default Categorization;

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

const BOOTSTRAP_GRID_COL_NUM = 12;

function CheckboxGroups({
	columns = 1,
	fieldValues,
	inputName,
	namespace = NAMESPACE
}) {
	const [values, setValues] = useState(getDefaultValues());

	function getDefaultValues() {
		const defaultValues = [];

		fieldValues.forEach(field => {
			if (field.checked) {
				defaultValues.push(field.value);
			}
		});

		return defaultValues;
	}

	function handleOnChange(event) {
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
				name={`${namespace}${inputName}`}
				type="hidden"
				value={values.join()}
			/>

			{!!fieldValues && (
				<div className="row">
					{fieldValues.map(field => (
						<div
							className={`col-md-${BOOTSTRAP_GRID_COL_NUM /
								columns}`}
							key={field.value}
						>
							<Checkbox field={field} updateFn={handleOnChange} />
						</div>
					))}
				</div>
			)}
		</>
	);
}

function Checkbox({field, updateFn}) {
	const [checked, setChecked] = useState(!!field.checked);

	function handleOnChange(event) {
		setChecked(!checked);

		updateFn(event);
	}

	return (
		<ClayCheckbox
			aria-label={field.label}
			checked={checked}
			label={field.label}
			onChange={handleOnChange}
			value={field.value}
		/>
	);
}

CheckboxGroups.propTypes = {
	columns: PropTypes.number,
	fieldValues: PropTypes.arrayOf(
		PropTypes.shape({
			checked: PropTypes.bool,
			label: PropTypes.string,
			value: PropTypes.oneOfType([PropTypes.bool, PropTypes.string])
		})
	).isRequired,
	inputName: PropTypes.string.isRequired,
	namespace: PropTypes.string
};

export default CheckboxGroups;

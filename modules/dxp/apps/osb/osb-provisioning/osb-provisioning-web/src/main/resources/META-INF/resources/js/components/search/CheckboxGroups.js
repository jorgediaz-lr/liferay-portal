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

function CheckboxGroups({fieldValues, inputName}) {
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

CheckboxGroups.propTypes = {
	fieldValues: PropTypes.arrayOf(
		PropTypes.oneOfType([
			PropTypes.string,
			PropTypes.shape({
				label: PropTypes.string,
				value: PropTypes.oneOfType([PropTypes.bool, PropTypes.string])
			})
		]),
		PropTypes.arrayOf(PropTypes.string)
	),
	inputName: PropTypes.string.isRequired
};

export default CheckboxGroups;

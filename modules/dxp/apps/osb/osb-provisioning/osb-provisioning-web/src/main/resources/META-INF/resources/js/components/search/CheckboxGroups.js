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

function CheckboxGroups({columns = 1, fieldValues, inputName}) {
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

			<div className="row">
				{fieldValues.map(field => (
					<div
						className={`col-md-${BOOTSTRAP_GRID_COL_NUM / columns}`}
						key={field.value}
					>
						<ClayCheckbox
							aria-label={field.label}
							label={field.label}
							onClick={handleOnClick}
							value={field.value}
						/>
					</div>
				))}
			</div>
		</>
	);
}

CheckboxGroups.propTypes = {
	columns: PropTypes.number,
	fieldValues: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType([PropTypes.bool, PropTypes.string])
		})
	),
	inputName: PropTypes.string.isRequired
};

export default CheckboxGroups;

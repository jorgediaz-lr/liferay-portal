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

import {DASH, NAMESPACE} from '../../../utilities/constants';

function FieldSelect({
	fieldName,
	onChangeFn,
	options,
	required = false,
	value
}) {
	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleOnChange(event) {
		onChangeFn(fieldName, event.currentTarget.value);
	}

	return (
		<label className="form-control-label" htmlFor={namespacedFieldName}>
			<select
				className="form-control"
				disabled={options.length === 0}
				id={namespacedFieldName}
				name={namespacedFieldName}
				onChange={handleOnChange}
				required={required}
				value={value}
			>
				<option value="">{DASH}</option>
				{options.map((option, index) => (
					<option key={option.name || index} value={option.name}>
						{option.name}
					</option>
				))}
			</select>
		</label>
	);
}

FieldSelect.propTypes = {
	fieldName: PropTypes.string.isRequired,
	onChangeFn: PropTypes.func.isRequired,
	options: PropTypes.arrayOf(PropTypes.shape({name: PropTypes.string}))
		.isRequired,
	required: PropTypes.bool,
	value: PropTypes.string.isRequired
};

export default FieldSelect;

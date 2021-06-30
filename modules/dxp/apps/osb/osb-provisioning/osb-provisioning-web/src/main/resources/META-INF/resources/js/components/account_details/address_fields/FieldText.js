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

import {NAMESPACE} from '../../../utilities/constants';

function AddressTextInput({fieldName, onChangeFn, required = false, value}) {
	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleOnChange(event) {
		onChangeFn(fieldName, event.currentTarget.value);
	}

	return (
		<label className="form-control-label" htmlFor={namespacedFieldName}>
			<input
				className="form-control"
				id={namespacedFieldName}
				name={namespacedFieldName}
				onChange={handleOnChange}
				required={required}
				type="text"
				value={value}
			/>
		</label>
	);
}

AddressTextInput.propTypes = {
	fieldName: PropTypes.string.isRequired,
	onChangeFn: PropTypes.func.isRequired,
	required: PropTypes.bool,
	value: PropTypes.string.isRequired
};

export default AddressTextInput;

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

function AddressToggle({fieldName, onChangeFn, value = false}) {
	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleOnChange() {
		onChangeFn(fieldName, !value);
	}

	return (
		<label
			className="simple-toggle-switch toggle-switch"
			htmlFor={namespacedFieldName}
		>
			<span className="toggle-switch-check-bar">
				<input
					checked={value}
					className="toggle-switch-check"
					id={namespacedFieldName}
					name={namespacedFieldName}
					onChange={handleOnChange}
					type="checkbox"
					value={value}
				/>
				<span aria-hidden="true" className="toggle-switch-bar">
					<span className="toggle-switch-handle"></span>
				</span>
			</span>
		</label>
	);
}

AddressToggle.propTypes = {
	fieldName: PropTypes.string.isRequired,
	onChangeFn: PropTypes.func.isRequired,
	value: PropTypes.bool.isRequired
};

export default AddressToggle;

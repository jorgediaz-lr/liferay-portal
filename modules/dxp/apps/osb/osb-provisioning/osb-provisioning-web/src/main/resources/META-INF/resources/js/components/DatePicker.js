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

import ClayDatePicker from '@clayui/date-picker';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

const YEAR_OFFSET = 30;

function DatePicker({
	defaultValue = '',
	endYearOffset = YEAR_OFFSET,
	id,
	inputName,
	placeholder = 'YYYY-MM-DD',
	startYearOffset = YEAR_OFFSET,
	updateFn
}) {
	const [value, setValue] = useState(defaultValue);

	const currentYear = new Date().getFullYear();

	useEffect(() => {
		// When date is invalid, a Date object is returned

		if (defaultValue instanceof Date) {
			return;
		}
		else {
			setValue(defaultValue);
		}
	}, [defaultValue]);

	function handleOnValueChange(value) {
		setValue(value);

		if (updateFn) {
			updateFn(value);
		}
	}

	return (
		<ClayDatePicker
			id={id}
			inputName={inputName}
			onValueChange={handleOnValueChange}
			placeholder={placeholder}
			value={value}
			years={{
				end: currentYear + endYearOffset,
				start: currentYear - startYearOffset
			}}
		/>
	);
}

DatePicker.propTypes = {
	defaultValue: PropTypes.oneOfType([PropTypes.string, PropTypes.object]),
	endYearOffset: PropTypes.number,
	id: PropTypes.string,
	inputName: PropTypes.string,
	placeholder: PropTypes.string,
	startYearOffset: PropTypes.number,
	updateFn: PropTypes.func
};

export default DatePicker;

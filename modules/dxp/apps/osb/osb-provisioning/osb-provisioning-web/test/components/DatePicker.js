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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import DatePicker from '../../src/main/resources/META-INF/resources/js/components/DatePicker';

function renderDatePicker(props) {
	return render(<DatePicker id="test" inputName="test" {...props} />);
}

describe('DatePicker', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDatePicker();

		expect(container).toBeTruthy();
	});

	it('displays the default date value correctly', () => {
		const {getByPlaceholderText} = renderDatePicker({
			defaultValue: '2021-03-09'
		});

		expect(getByPlaceholderText('YYYY-MM-DD').value).toBe('2021-03-09');
	});

	it('displays the date entered in the input field correctly', () => {
		const {getByPlaceholderText} = renderDatePicker();

		expect(getByPlaceholderText('YYYY-MM-DD').value).toBe('');

		fireEvent.change(getByPlaceholderText('YYYY-MM-DD'), {
			target: {value: '2021-03-09'}
		});

		expect(getByPlaceholderText('YYYY-MM-DD').value).toBe('2021-03-09');
	});
});

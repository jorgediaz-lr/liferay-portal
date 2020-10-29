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

import ExternalSelectField from '../../src/main/resources/META-INF/resources/js/components/ExternalSelectField';

const mockClickFn = jest.fn();
const mockDeleteFn = jest.fn();

function renderExternalSelectField(props) {
	return render(
		<ExternalSelectField
			clickFn={mockClickFn}
			deleteFn={mockDeleteFn}
			id="123"
			value="test"
			{...props}
		/>
	);
}

describe('ExternalSelectField', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExternalSelectField();

		expect(container).toBeTruthy();
	});

	it('displays an external field type as a disabled input, a Select button, and a Delete icon', () => {
		const {
			container,
			getByLabelText,
			getByText
		} = renderExternalSelectField();

		const input = container.querySelector('input');

		expect(input.disabled).toBeTruthy();
		getByText('select');
		expect(getByText('select').type).toBe('button');
		getByLabelText('delete-field-icon');
	});

	it('displays no Delete icon when a delete function is not explicitly provided', () => {
		const {queryByLabelText} = renderExternalSelectField({deleteFn: null});

		expect(queryByLabelText('delete-field-icon')).toBeFalsy();
	});

	it('calls the click function when the Select button is clicked', () => {
		const {getByText} = renderExternalSelectField();

		fireEvent.click(getByText('select'));

		expect(mockClickFn).toHaveBeenCalled();
	});

	it('calls the delete function when the Delete icon is clicked', () => {
		const {getByLabelText} = renderExternalSelectField();

		fireEvent.click(getByLabelText('delete-field-icon'));

		expect(mockDeleteFn).toHaveBeenCalled();
	});

	it('prefills the input with a value when one is provided', () => {
		const {container} = renderExternalSelectField();

		expect(container.querySelector('input').value).toBe('test');
	});
});

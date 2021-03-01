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

import DropdownMultiSelect from '../../src/main/resources/META-INF/resources/js/components/DropdownMultiSelect';

const addFn = jest.fn();
const removeFn = jest.fn();

function renderDropdownMultiSelect() {
	return render(
		<DropdownMultiSelect
			addFn={addFn}
			allOptions={[
				{key: 'KEY1', name: 'One'},
				{key: 'KEY2', name: 'Two'},
				{key: 'KEY3', name: 'Three'},
				{key: 'KEY4', name: 'Four'},
				{key: 'KEY5', name: 'Five'}
			]}
			newOptions={['KEY1', 'KEY2', 'KEY3']}
			removeFn={removeFn}
		/>
	);
}

describe('DropdownMultiSelect', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDropdownMultiSelect();

		expect(container).toBeTruthy();
	});

	it('displays selected options as labels', () => {
		const {getByText} = renderDropdownMultiSelect();

		expect(getByText('One').getAttribute('class')).not.toBe(
			'dropdown-item'
		);
		expect(getByText('Two').getAttribute('class')).not.toBe(
			'dropdown-item'
		);
		expect(getByText('Three').getAttribute('class')).not.toBe(
			'dropdown-item'
		);
	});

	it('displays unselected options in the dropdowns', () => {
		const {getByText} = renderDropdownMultiSelect();

		expect(getByText('Four').getAttribute('class')).toBe('dropdown-item');
		expect(getByText('Five').getAttribute('class')).toBe('dropdown-item');
	});

	it('adds option when selected from dropdown', () => {
		const {getByText, getByTitle} = renderDropdownMultiSelect();

		fireEvent.click(getByTitle('add'));
		fireEvent.click(getByText('Four'));

		expect(addFn).toHaveBeenCalled();
	});

	it('removes option when clicked on Close button', () => {
		const {getAllByTitle} = renderDropdownMultiSelect();

		fireEvent.click(getAllByTitle('delete')[0]);

		expect(removeFn).toHaveBeenCalled();
	});
});

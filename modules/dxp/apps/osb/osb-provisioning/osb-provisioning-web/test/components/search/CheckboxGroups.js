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

import CheckboxGroups from '../../../src/main/resources/META-INF/resources/js/components/search/CheckboxGroups';

function renderCheckboxGroups(props) {
	return render(
		<CheckboxGroups
			fieldValues={[
				{label: 'One', value: '1'},
				{label: 'Two', value: '2'}
			]}
			inputName={'test input'}
			{...props}
		/>
	);
}

describe('Search', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderCheckboxGroups();

		expect(container).toBeTruthy();
	});

	it('displays checkboxes in the number of columns specified', () => {
		const {container} = renderCheckboxGroups({columns: 2});

		expect(container.querySelectorAll('.col-md-6').length).toBe(2);
	});

	it('displays the checked property correctly', () => {
		const {getByLabelText} = renderCheckboxGroups({
			fieldValues: [
				{checked: true, label: 'One', value: '1'},
				{checked: false, label: 'Two', value: '2'}
			]
		});

		expect(getByLabelText('One').checked).toBeTruthy();
	});

	it('updates the checked property correctly', () => {
		const {getByLabelText} = renderCheckboxGroups({
			fieldValues: [
				{checked: true, label: 'One', value: '1'},
				{checked: false, label: 'Two', value: '2'}
			]
		});

		fireEvent.click(getByLabelText('One'));

		expect(getByLabelText('One').checked).toBeFalsy();
	});
});

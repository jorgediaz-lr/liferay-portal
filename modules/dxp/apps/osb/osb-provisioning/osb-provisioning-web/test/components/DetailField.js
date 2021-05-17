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

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import DetailField from '../../src/main/resources/META-INF/resources/js/components/DetailField';
import {FIELD_TYPE_TEXT} from '../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderDetailField(props) {
	return render(
		<DetailField
			fieldLabel="name"
			formData={{1: 'a', 2: 'b', 3: 'c'}}
			value="test"
			{...props}
		/>
	);
}

describe('DetailField', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDetailField();

		expect(container).toBeTruthy();
	});

	it('displays label for the field', () => {
		const {getByText} = renderDetailField();

		getByText('name');
	});

	it('displays value for the field', () => {
		const {getByText} = renderDetailField();

		getByText('test');
	});

	it('displays a non editable field by default', () => {
		const {container} = renderDetailField();
		const {getByText, queryByText} = within(container);

		fireEvent.click(getByText('test'));

		expect(queryByText('save')).toBeFalsy();
		expect(queryByText('cancel')).toBeFalsy();
	});

	it('allows inline edit to be turned on', () => {
		const {container} = renderDetailField({type: FIELD_TYPE_TEXT});
		const {getByText} = within(container);

		fireEvent.click(getByText('test'));

		getByText('save');
		getByText('cancel');
	});
});

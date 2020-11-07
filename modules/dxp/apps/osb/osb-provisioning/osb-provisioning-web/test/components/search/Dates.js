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

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import Dates from '../../../src/main/resources/META-INF/resources/js/components/search/Dates';

function renderDates() {
	return render(<Dates />);
}

describe('Account', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDates();

		expect(container).toBeTruthy();
	});

	it('displays a Created By email field', () => {
		const {getByLabelText} = renderDates();

		expect(getByLabelText('created-by').type).toBe('email');
	});

	it('displays a Created After field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('created-after');
	});

	it('displays a Created Before field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('created-before');
	});

	it('displays a Modified After field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('modified-after');
	});

	it('displays a Modified Before field', () => {
		const {getByLabelText} = renderDates();

		getByLabelText('modified-before');
	});
});

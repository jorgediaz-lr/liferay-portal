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

import LicenseDetails from '../../../../src/main/resources/META-INF/resources/js/components/search/license/LicenseDetails';

function renderDetails() {
	return render(<LicenseDetails />);
}

describe('License Search General Details', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDetails();

		expect(container).toBeTruthy();
	});

	it('displays a Koroneiki Account Key field', () => {
		const {getByLabelText} = renderDetails();

		getByLabelText('account-key');
	});

	it('displays a Koroneiki Product Purchase Key field', () => {
		const {getByLabelText} = renderDetails();

		getByLabelText('product-purchase-key');
	});

	it('displays a Created By email field', () => {
		const {getByLabelText} = renderDetails();

		expect(getByLabelText('created-by').type).toBe('email');
	});

	it('displays a Last Edited By email field', () => {
		const {getByLabelText} = renderDetails();

		expect(getByLabelText('last-edited-by').type).toBe('email');
	});

	it('displays an Active checkbox fieldset', () => {
		const {getByLabelText, getByText} = renderDetails();

		getByLabelText('yes');
		getByLabelText('no');
		getByText('active');
	});
});

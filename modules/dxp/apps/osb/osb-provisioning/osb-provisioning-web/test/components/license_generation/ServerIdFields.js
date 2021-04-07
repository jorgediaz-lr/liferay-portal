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

import ServerIdFields from '../../../src/main/resources/META-INF/resources/js/components/license_generation/ServerIdFields';

function renderServerIdFields(props) {
	return render(<ServerIdFields {...props} />);
}

describe('ServerIdFields', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderServerIdFields();

		expect(container).toBeTruthy();
	});

	it('renders a Host Name field', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('host-name');
	});

	it('renders an IP Address textarea', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('ip-addresses');
	});

	it('renders a Mac Address textarea', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('mac-addresses');
	});

	it('renders an Add button', () => {
		const {getByLabelText} = renderServerIdFields();

		getByLabelText('add');
	});
});

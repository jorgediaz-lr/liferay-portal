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

import AddSubscriptions from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/AddSubscriptions';

function renderAddSubscriptions() {
	return render(
		<AddSubscriptions
			selectProductsURL="/select/products/url"
			sizing={['1', '2', '3', '4']}
		/>
	);
}

describe('AddSubscriptions', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAddSubscriptions();

		expect(container).toBeTruthy();
	});

	it('renders a Select button', () => {
		const {getByText} = renderAddSubscriptions();

		expect(getByText('select')).toBeTruthy();
	});

	it('renders a disabled Save button', () => {
		const {getByText} = renderAddSubscriptions();

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('renders a Cancel button', () => {
		const {getByText} = renderAddSubscriptions();

		expect(getByText('cancel')).toBeTruthy();
	});
});

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

import Subscriptions from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/Subscriptions';

function renderSubscriptions(props) {
	return render(
		<Subscriptions
			accountName="Test Account"
			details={[
				{
					endDate: '12/08/2021',
					productKey: 'KOR-35746',
					productName: 'Product 1',
					startDate: '12/08/2020'
				}
			]}
			instanceSizes={['1', '2', '3', '4']}
			{...props}
		/>
	);
}

describe('Subscriptions', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSubscriptions();

		expect(container).toBeTruthy();
	});

	it('displays a delete subscription icon', () => {
		const {getByLabelText} = renderSubscriptions();

		expect(getByLabelText('delete-subscription-icon')).toBeTruthy();
	});

	it('displays the product name', () => {
		const {getByText} = renderSubscriptions();

		expect(getByText('Product 1')).toBeTruthy();
	});

	it('displays the account name', () => {
		const {getByText} = renderSubscriptions();

		expect(getByText('Test Account')).toBeTruthy();
	});
});

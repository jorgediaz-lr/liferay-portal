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

import Subscriptions from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/Subscriptions';
import {SubscriptionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/subscriptions';

function mockSubscriptions() {
	return [
		{
			endDate: '12/08/2021',
			productKey: 'KOR-35735',
			productName: 'Product A',
			startDate: '12/08/2020'
		},
		{
			endDate: '12/08/2021',
			productKey: 'KOR-35803',
			productName: 'Product B',
			startDate: '12/08/2020'
		},
		{
			endDate: '12/08/2021',
			productKey: 'KOR-35746',
			productName: 'Product C',
			startDate: '12/08/2020'
		},
		{
			endDate: '12/08/2021',
			productKey: 'KOR-35757',
			productName: 'Product D',
			startDate: '12/08/2020'
		}
	];
}

function renderSubscriptions(subscriptions = mockSubscriptions()) {
	return render(
		<SubscriptionsProvider initialSubscriptions={subscriptions}>
			<Subscriptions
				accountName="Test Account"
				instanceSizes={[1, 2, 3, 4]}
			/>
		</SubscriptionsProvider>
	);
}

describe('Subscriptions', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSubscriptions();

		expect(container).toBeTruthy();
	});

	it('displays a delete subscription icon for each of the subscriptions', () => {
		const {getAllByLabelText} = renderSubscriptions();

		const allDeleteIcons = getAllByLabelText('delete-subscription-icon');

		expect(allDeleteIcons[0]).toBeTruthy();
		expect(allDeleteIcons.length).toBe(4);
	});

	it('displays a disabled delete subscription icon when there is only one subscription', () => {
		const {getAllByLabelText, getByText} = renderSubscriptions();

		const allDeleteIcons = getAllByLabelText('delete-subscription-icon');

		allDeleteIcons.forEach(icon => {
			fireEvent.click(icon);
		});

		expect(getByText('Product D'));
	});

	it('displays the product name for each of the selected products', () => {
		const {getByText} = renderSubscriptions();

		expect(getByText('Product A')).toBeTruthy();
		expect(getByText('Product B')).toBeTruthy();
		expect(getByText('Product C')).toBeTruthy();
		expect(getByText('Product D')).toBeTruthy();
	});

	it('displays the account name for each of the subscriptions', () => {
		const {getAllByText} = renderSubscriptions();

		const allAccountNames = getAllByText('Test Account');

		expect(allAccountNames[0]).toBeTruthy();
		expect(allAccountNames.length).toBe(4);
	});

	it('removes a subscription when the delete icon for that subscription is clicked', () => {
		const {
			getAllByLabelText,
			getByText,
			queryByText
		} = renderSubscriptions();

		fireEvent.click(getAllByLabelText('delete-subscription-icon')[0]);

		expect(queryByText('Product A')).toBeFalsy();
		expect(getByText('Product B')).toBeTruthy();
		expect(getByText('Product C')).toBeTruthy();
		expect(getByText('Product D')).toBeTruthy();
	});

	it('it disables date fields after checking the perpetual checkbox', () => {
		const {getAllByPlaceholderText, getAllByRole} = renderSubscriptions();

		const dateFields = getAllByPlaceholderText('YYYY-MM-DD');
		const perpetualCheckboxes = getAllByRole('checkbox');

		expect(dateFields[0].disabled).toBeFalsy();
		expect(dateFields[1].disabled).toBeFalsy();

		fireEvent.click(perpetualCheckboxes[0]);

		expect(dateFields[0].disabled).toBeTruthy();
		expect(dateFields[1].disabled).toBeTruthy();
	});
});

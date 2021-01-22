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

import BulkInput from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/BulkInput';
import {SubscriptionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/subscriptions';
import {
	EDIT_SUBSCRIPTIONS,
	PRODUCT_PURCHASE_STATUS_APPROVED,
	PRODUCT_PURCHASE_STATUS_CANCELLED
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function mockEditSubscriptions() {
	return [
		{
			endDate: '2022-01-20',
			externalLinkKey: 'KOR-35727',
			key: 'KOR-38322',
			originalEndDate: '2021-12-20',
			perpetual: true,
			productName: 'Product A',
			quantity: 1,
			salesforceOpportunityKey: 'salesForceKey123',
			sizing: 1,
			startDate: '2020-12-20',
			status: 'Cancelled'
		},
		{
			endDate: '2022-01-21',
			externalLinkKey: 'KOR-35727',
			key: 'KOR-38323',
			originalEndDate: '2021-12-21',
			perpetual: true,
			productName: 'Product B',
			quantity: 2,
			salesforceOpportunityKey: 'salesForceKey456',
			sizing: 2,
			startDate: '2020-12-21',
			status: 'Approved'
		}
	];
}

function renderBulkInput({
	subscriptions = mockEditSubscriptions(),
	...props
} = {}) {
	return render(
		<table>
			<tbody>
				<SubscriptionsProvider initialSubscriptions={subscriptions}>
					<BulkInput
						accountName="Test Account"
						instanceSizes={[1, 2, 3, 4]}
						statusOptions={[
							PRODUCT_PURCHASE_STATUS_APPROVED,
							PRODUCT_PURCHASE_STATUS_CANCELLED
						]}
						subscriptionsType={EDIT_SUBSCRIPTIONS}
						{...props}
					/>
				</SubscriptionsProvider>
			</tbody>
		</table>
	);
}

describe('Subscriptions', () => {
	afterEach(cleanup);

	describe('New Subscriptions', () => {
		it('renders', () => {
			const {container} = renderBulkInput();

			expect(container).toBeTruthy();
		});

		it('renders Varied Data for Salesforce Opportunity Key, Purchased, Instant Size, and Status fields when the subscriptions to be edited contain different values for these fields', () => {
			const {getAllByText} = renderBulkInput();

			expect(getAllByText('varied-data').length).toBe(4);
		});

		it('renders Varied Data for the three date fields when the subscriptions to be edited contain different values for these fields', () => {
			const {getAllByPlaceholderText} = renderBulkInput();

			expect(getAllByPlaceholderText('varied-data').length).toBe(3);
		});

		it('renders the field value when the subscriptions contain identical values for the said fields', () => {
			const {getAllByDisplayValue, getByDisplayValue} = renderBulkInput({
				subscriptions: [
					{
						endDate: '2022-01-21',
						externalLinkKey: 'KOR-35727',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product B',
						quantity: 2,
						salesforceOpportunityKey: 'salesForceKey456',
						sizing: 1,
						startDate: '2020-12-21',
						status: 'Approved'
					},
					{
						endDate: '2022-01-21',
						externalLinkKey: 'KOR-35727',
						key: 'KOR-38323',
						originalEndDate: '2021-12-21',
						perpetual: true,
						productName: 'Product B',
						quantity: 2,
						salesforceOpportunityKey: 'salesForceKey456',
						sizing: 1,
						startDate: '2020-12-21',
						status: 'Approved'
					}
				]
			});

			getByDisplayValue('salesForceKey456');
			getByDisplayValue('2');
			getAllByDisplayValue('2020-12-21');
			getAllByDisplayValue('2022-01-21');
			getByDisplayValue('1');
			getAllByDisplayValue('2021-12-21');
			getByDisplayValue('Approved');
		});
	});
});

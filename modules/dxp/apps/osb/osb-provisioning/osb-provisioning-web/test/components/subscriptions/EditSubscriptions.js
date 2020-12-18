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

import EditSubscriptions from '../../../src/main/resources/META-INF/resources/js/components/subscriptions/EditSubscriptions';

function renderAddSubscriptions(props) {
	return render(
		<EditSubscriptions
			accountName="Test Account"
			addSubscriptions={true}
			details={[
				{
					originalEndDate: '2021-12-08',
					productKey: 'KOR-35746',
					productName: 'Product 1',
					startDate: '2020-12-08'
				}
			]}
			editProductPurchasesURL="/edit/product/purchases/url"
			redirect="/redirect/to/subscriptions/tab"
			selectProductsURL="/select/products/url"
			sizing={[1, 2, 3, 4]}
			{...props}
		/>
	);
}

function renderEditSubscriptions(props) {
	return render(
		<EditSubscriptions
			accountName="Test Account"
			addSubscriptions={false}
			details={[
				{
					originalEndDate: '2021-12-08',
					productKey: 'KOR-35746',
					productName: 'Product 1',
					startDate: '2020-12-08'
				}
			]}
			editProductPurchasesURL="/edit/product/purchases/url"
			redirect="/redirect/to/subscriptions/tab"
			selectProductsURL="/select/products/url"
			sizing={[1, 2, 3, 4]}
			{...props}
		/>
	);
}

describe('EditSubscriptions', () => {
	afterEach(cleanup);

	describe('Add Subscriptions', () => {
		it('renders', () => {
			const {container} = renderAddSubscriptions();

			expect(container).toBeTruthy();
		});

		it('renders a subtext to describe the action', () => {
			const {getByText} = renderAddSubscriptions();

			expect(getByText('configure-subscriptions')).toBeTruthy();
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

		it('enables the Save button once a Salesforce Opportunity Key is entered', () => {
			const {getByLabelText, getByText} = renderAddSubscriptions();

			fireEvent.change(getByLabelText('salesforce-opportunity-key'), {
				target: {value: 'test'}
			});

			expect(getByText('save').disabled).toBeFalsy();
		});
	});

	describe('Edit Subscriptions', () => {
		it('renders', () => {
			const {container} = renderEditSubscriptions();

			expect(container).toBeTruthy();
		});

		it('renders subtext showing the editing step', () => {
			const {getByText} = renderEditSubscriptions();

			expect(getByText('edit-details')).toBeTruthy();
			expect(getByText('step-2-of-2')).toBeTruthy();
		});

		it('renders a Previous button if a backURL is provided', () => {
			const {getByText} = renderEditSubscriptions({
				backURL: '/back/to/previous/page'
			});

			expect(getByText('previous'));
		});
	});
});

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

import Categorization from '../../../src/main/resources/META-INF/resources/js/components/search/Categorization';

function renderCategorization() {
	return render(
		<Categorization
			activeSLANames={[
				'Gold Subscription',
				'Limited Subscription',
				'Platinum Subscription',
				'Silver Subscription'
			]}
			regionNames={[
				'Australia',
				'Brazil',
				'China',
				'Global',
				'Hungary',
				'India',
				'Japan',
				'Spain',
				'United States'
			]}
			subscriptionStateNames={[
				'Active',
				'Cancelled',
				'Expired',
				'Unactivated'
			]}
			tierNames={['OEM', 'Premier', 'Regular', 'Strategic']}
		/>
	);
}

describe('Account', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderCategorization();

		expect(container).toBeTruthy();
	});

	it('displays a Tier field', () => {
		const {getByText} = renderCategorization();

		getByText('tier');
		getByText('OEM');
		getByText('Premier');
		getByText('Regular');
		getByText('Strategic');
	});

	it('sets the input value as a comma deliminated list of clicked items when multiple checkboxes in a group are checked', () => {
		const {container, getByText} = renderCategorization();

		fireEvent.click(getByText('OEM'));
		fireEvent.click(getByText('Regular'));
		fireEvent.click(getByText('Strategic'));

		expect(
			container.querySelector('input[name = "namespacetiers"]').value
		).toBe('OEM,Regular,Strategic');

		fireEvent.click(getByText('Strategic'));

		expect(
			container.querySelector('input[name = "namespacetiers"]').value
		).toBe('OEM,Regular');
	});

	it('displays a Subscription Status field', () => {
		const {getByText} = renderCategorization();

		getByText('subscription-status');
		getByText('Active');
		getByText('Cancelled');
		getByText('Expired');
		getByText('Unactivated');
	});

	it('displays a SLA field', () => {
		const {getByText} = renderCategorization();

		getByText('sla');
		getByText('Gold Subscription');
		getByText('Limited Subscription');
		getByText('Platinum Subscription');
		getByText('Silver Subscription');
	});

	it('displays a Support Region field', () => {
		const {getByText} = renderCategorization();

		getByText('support-region');
		getByText('Australia');
		getByText('Brazil');
		getByText('China');
		getByText('Global');
		getByText('Hungary');
		getByText('India');
		getByText('Japan');
		getByText('Spain');
		getByText('United States');
	});
});

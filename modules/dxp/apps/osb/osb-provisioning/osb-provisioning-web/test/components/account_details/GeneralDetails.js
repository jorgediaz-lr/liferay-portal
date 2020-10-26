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

import GeneralDetails from '../../../src/main/resources/META-INF/resources/js/components/account_details/GeneralDetails';

function renderGeneralDetails(props) {
	return render(
		<GeneralDetails
			details={{
				code: '123',
				dateCreated: new Date().toLocaleString('en-US'),
				dateModified: new Date().toLocaleString('en-US'),
				firstLineSupportTeamName: 'Test Support Team',
				key: '123',
				name: 'Test Account',
				parterTeamName: 'Test Partner Team',
				region: 'US',
				subscriptionState: 'Active',
				subscriptionStateStyle: 'label-success',
				tier: 'Regular'
			}}
			parentAccountName="Parent Account Name"
			tiers={['1', '2', '3']}
			{...props}
		/>
	);
}

describe('GeneralDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderGeneralDetails();

		expect(container).toBeTruthy();
	});

	it('shows Account Name field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('account-name');
		getByText('Test Account');
	});

	it('shows Code field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('code');
		getByText('123');
	});

	it('shows Tier field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('tier');
		getByText('Regular');
	});

	it('shows Parent Account field', () => {
		const {getByText} = renderGeneralDetails();

		getByText('Parent Account Name');
	});
});

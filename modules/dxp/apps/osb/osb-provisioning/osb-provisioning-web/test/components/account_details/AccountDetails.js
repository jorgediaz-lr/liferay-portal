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

import {cleanup, render, wait} from '@testing-library/react';
import React from 'react';

import AccountDetails from '../../../src/main/resources/META-INF/resources/js/components/account_details/AccountDetails';

function renderAccountDetails(props) {
	return render(
		<AccountDetails
			details={{
				addPostalAddressURL: '',
				code: '123',
				dateCreated: new Date().toLocaleString('en-US'),
				dateModified: new Date().toLocaleString('en-US'),
				dossieraAccountKey: 'testDossieraAccountKey',
				dossieraProjectKey: 'testDossieraProjectKey',
				editAccountURL: 'edit/account/url',
				firstLineSupportTeamKey: 'first-line-123',
				firstLineSupportTeamName: 'Test Support Team',
				key: '123',
				name: 'Test Account',
				partnerTeamKey: 'partner-123',
				partnerTeamName: 'Test Partner Team',
				postalAddressDisplays: [],
				region: 'US',
				salesforceProjectKey: 'TestSalesForceProjectKey',
				subscriptionState: 'Active',
				subscriptionStateStyle: 'label-success',
				tier: 'Regular',
				updateDossieraAccountURL: '/update/dossiera/account',
				updateDossieraProjectURL: '/update/dossiera/project',
				updateSalesforceProjectURL: 'update/salesforce/project'
			}}
			parentAccountName="parent"
			tierNames={['1', '2', '3']}
			{...props}
		/>
	);
}

describe('AccountDetails', () => {
	beforeEach(() => {
		Liferay.Service.mockImplementation(() =>
			Promise.resolve([
				{
					countryId: '',
					name: '',
					nameCurrentValue: '',
					regionId: '',
					zipRequired: false
				}
			])
		);
	});

	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccountDetails();

		return wait(() => expect(container).toBeTruthy());
	});

	it('displays General Details section', () => {
		const {getByText} = renderAccountDetails();

		return wait(() => getByText('general-details'));
	});

	it('displays Partner Info section', () => {
		const {getByText} = renderAccountDetails();

		return wait(() => getByText('partner-info'));
	});

	it('displays Address 1 section', () => {
		const {getByText} = renderAccountDetails();

		return wait(() => getByText('address 1'));
	});

	it('displays External Account Keys section', () => {
		const {getByText} = renderAccountDetails();

		return wait(() => getByText('external-account-keys'));
	});
});

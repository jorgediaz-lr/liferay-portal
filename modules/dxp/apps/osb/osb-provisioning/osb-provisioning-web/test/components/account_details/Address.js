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

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import Address from '../../../src/main/resources/META-INF/resources/js/components/account_details/Address';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';

function renderAddress(permission = true) {
	return render(
		<PermissionsProvider permissions={{updatePermission: permission}}>
			<Address
				accountKey="key123"
				address={{
					addressCountry: 'United States',
					addressLocality: 'Diamond Bar',
					addressRegion: 'California',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '123',
					postalCode: '91765',
					primary: true,
					streetAddressLine1: '1400 Montefino Ave',
					streetAddressLine2: '-',
					streetAddressLine3: '-'
				}}
				addURL="/"
				count={1}
				countryOptions={[
					{
						label: 'China',
						value: '2',
						zipRequired: true
					},
					{
						label: 'United Arab Emirates',
						value: '217',
						zipRequired: false
					},
					{
						label: 'United States',
						value: '19',
						zipRequired: true
					}
				]}
			/>
		</PermissionsProvider>
	);
}

describe('Address', () => {
	beforeEach(() => {
		Liferay.Service.mockImplementation(() =>
			Promise.resolve([
				{
					name: 'California',
					regionId: '19005'
				}
			])
		);
	});

	afterEach(cleanup);

	it('renders', async () => {
		const {container} = renderAddress();

		await wait(() => expect(container).toBeTruthy());
	});

	it('displays all address fields as editable when any one of the address fields is clicked for a user with full editing privilege', async () => {
		const {container, getByText} = renderAddress();

		fireEvent.click(getByText('Diamond Bar'));

		await wait(() => {
			expect(container.querySelectorAll('select').length).toBe(2);
			expect(container.querySelectorAll('input[type=text]').length).toBe(
				5
			);

			getByText('save');
			getByText('cancel');
		});
	});

	it('displays all address fields as non editable when any of the fields is clicked for a user with limited editing privilege', async () => {
		const {container, getByText, queryByText} = renderAddress(false);

		fireEvent.click(getByText('Diamond Bar'));

		await wait(() => {
			expect(container.querySelectorAll('select').length).toBe(0);
			expect(container.querySelectorAll('input[type=text]').length).toBe(
				0
			);

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});

	it('displays PRC, UAE, and USA as country options when the user clicks on a Country field', async () => {
		const {getByText} = renderAddress();

		fireEvent.click(getByText('United States'));

		await wait(() => {
			getByText('China');
			getByText('United Arab Emirates');
			getByText('United States');
		});
	});

	it('displays Primary field as toggled on edit when the field is displayed as "Yes"', async () => {
		const {container, getByText} = renderAddress();

		fireEvent.click(getByText('yes'));

		await wait(() => {
			expect(
				container.querySelector('input[type=checkbox]').checked
			).toBe(true);
		});
	});
});

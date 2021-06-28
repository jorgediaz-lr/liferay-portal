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

import Address from '../../../src/main/resources/META-INF/resources/js/components/account_details/Address';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';

function renderAddress(permission = true) {
	return render(
		<PermissionsProvider permissions={{updatePermission: permission}}>
			<Address
				accountKey="key123"
				addFn={jest.fn()}
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
				count={1}
				countryOptions={[
					{
						active: true,
						countryRegions: [
							{
								active: true,
								name: 'Shanghai'
							},
							{
								active: true,
								name: 'Sichuan'
							}
						],
						name: 'China',
						zipRequired: true
					},
					{
						active: true,
						countryRegions: [],
						name: 'United Arab Emirates',
						zipRequired: false
					},
					{
						active: true,
						countryRegions: [
							{
								active: true,
								name: 'California'
							}
						],
						name: 'United States',
						zipRequired: true
					}
				]}
			/>
		</PermissionsProvider>
	);
}

describe('Address', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAddress();

		expect(container).toBeTruthy();
	});

	it('displays all address fields as editable when any one of the address fields is clicked for a user with full editing privilege', () => {
		const {container, getByText} = renderAddress();

		fireEvent.click(getByText('Diamond Bar'));

		expect(container.querySelectorAll('select').length).toBe(2);
		expect(container.querySelectorAll('input[type=text]').length).toBe(5);

		getByText('save');
		getByText('cancel');
	});

	it('displays all address fields as non editable when any of the fields is clicked for a user with limited editing privilege', () => {
		const {container, getByText, queryByText} = renderAddress(false);

		fireEvent.click(getByText('Diamond Bar'));

		expect(container.querySelectorAll('select').length).toBe(0);
		expect(container.querySelectorAll('input[type=text]').length).toBe(0);

		expect(queryByText('save')).toBeFalsy();
		expect(queryByText('cancel')).toBeFalsy();
	});

	it('displays PRC, UAE, and USA as country options when the user clicks on a Country field', () => {
		const {getByText} = renderAddress();

		fireEvent.click(getByText('United States'));

		getByText('China');
		getByText('United Arab Emirates');
		getByText('United States');
	});

	it('displays Shanghai as a region option when the user selects PRC as the country', () => {
		const {getByDisplayValue, getByText} = renderAddress();

		fireEvent.click(getByText('91765'));
		fireEvent.change(getByDisplayValue('91765'), {
			target: {value: ''}
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('displays the Save button as disabled if a required Zip Code is not entered', () => {
		const {getByDisplayValue, getByText} = renderAddress();

		fireEvent.click(getByText('United States'));
		fireEvent.change(getByDisplayValue('United States'), {
			target: {value: 'China'}
		});
	});

	it('displays Primary field as toggled on edit when the field is displayed as "Yes"', () => {
		const {container, getByText} = renderAddress();

		fireEvent.click(getByText('yes'));

		expect(container.querySelector('input[type=checkbox]').checked).toBe(
			true
		);
	});
});

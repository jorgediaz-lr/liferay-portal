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

import AccountAddresses from '../../../src/main/resources/META-INF/resources/js/components/account_details/AccountAddresses';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';

const sampleAddresses = [
	{
		addressCountry: 'United States',
		addressLocality: 'Diamond Bar',
		addressRegion: 'California',
		deletePostalAddressURL: '/',
		editPostalAddressURL: '/',
		id: '123',
		postalCode: '91765',
		streetAddressLine1: '1400 Montefino Ave',
		streetAddressLine2: '-',
		streetAddressLine3: '-'
	},
	{
		addressCountry: 'United Arab Emirates',
		addressLocality: 'Dubai Media City',
		addressRegion: '-',
		deletePostalAddressURL: '/',
		editPostalAddressURL: '/',
		id: '456',
		postalCode: '-',
		streetAddressLine1: 'Building 8',
		streetAddressLine2: 'Office 207',
		streetAddressLine3: '-'
	},
	{
		addressCountry: 'P.R. China',
		addressLocality: 'Dalian',
		addressRegion: 'Liaoning',
		deletePostalAddressURL: '/',
		editPostalAddressURL: '/',
		id: '789',
		postalCode: '116023',
		streetAddressLine1: '537 Huangpu Road Taide Building',
		streetAddressLine2: '1005 High-Tech Zone',
		streetAddressLine3: '-'
	}
];

function renderAccountAddress(addresses = [], permission = true) {
	return render(
		<PermissionsProvider permissions={{updatePermission: permission}}>
			<AccountAddresses
				accountKey="key123"
				addresses={addresses}
				addURL="/"
			/>
		</PermissionsProvider>
	);
}

describe('AccountAddresses', () => {
	beforeEach(() => {
		Liferay.Service.mockImplementation(() =>
			Promise.resolve([
				{
					countryId: '2',
					nameCurrentValue: 'China',
					zipRequired: true
				},
				{
					countryId: '217',
					nameCurrentValue: 'United Arab Emirates',
					zipRequired: false
				},
				{
					countryId: '19',
					nameCurrentValue: 'United States',
					zipRequired: true
				}
			])
		);
	});

	afterEach(cleanup);

	it('renders', async () => {
		const {container} = renderAccountAddress(sampleAddresses);

		await wait(() => expect(container).toBeTruthy());
	});

	it('displays an addresses List Group with dashes for each field when no address was provided', async () => {
		const {getAllByText} = renderAccountAddress();

		await wait(() => expect(getAllByText('-').length).toBe(7));
	});

	it('does not allow any address fields to be edited when no address was provided', async () => {
		const {getAllByText, queryByText} = renderAccountAddress();

		fireEvent.click(getAllByText('-')[0]);

		await wait(() => {
			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});

	it('displays no delete button when no address was provided', async () => {
		const {queryByLabelText} = renderAccountAddress();

		await wait(() => expect(queryByLabelText('delete')).toBeFalsy());
	});

	it('displays multiple address List Groups when multiple addresses are provided', async () => {
		const {getByText} = renderAccountAddress(sampleAddresses);

		await wait(() => {
			getByText('address 1');
			getByText('address 2');
			getByText('address 3');
		});
	});

	describe('AccountAddresses with full editing privilege', () => {
		it('displays an add button when no address was provided', async () => {
			const {queryByLabelText} = renderAccountAddress();

			await wait(() => expect(queryByLabelText('add')).toBeTruthy());
		});

		it('displays an add button for each provided addresses', async () => {
			const {getAllByLabelText} = renderAccountAddress(sampleAddresses);

			await wait(() => expect(getAllByLabelText('add').length).toBe(3));
		});

		it('displays a delete button for each provided addresses', async () => {
			const {getAllByLabelText} = renderAccountAddress(sampleAddresses);

			await wait(() =>
				expect(getAllByLabelText('delete').length).toBe(3)
			);
		});

		it('allows address fields to be edited when at least one address was provided', async () => {
			const {getByText} = renderAccountAddress(sampleAddresses);

			fireEvent.click(getByText('Diamond Bar'));

			await wait(() => {
				getByText('save');
				getByText('cancel');
			});
		});
	});

	describe('AccountAddresses with limited editing privilege', () => {
		it('does not display an add button when no address was provided', async () => {
			const {queryByLabelText} = renderAccountAddress([], false);

			await wait(() => expect(queryByLabelText('add')).toBeFalsy());
		});

		it('does not display an add button for each provided addresses', async () => {
			const {queryByLabelText} = renderAccountAddress(
				sampleAddresses,
				false
			);

			await wait(() => expect(queryByLabelText('add')).toBeFalsy());
		});

		it('does not display a delete button for each provided addresses', async () => {
			const {queryByLabelText} = renderAccountAddress(
				sampleAddresses,
				false
			);

			await wait(() => expect(queryByLabelText('delete')).toBeFalsy());
		});

		it('does not allow address fields to be edited when at least one address was provided', async () => {
			const {getByText, queryByText} = renderAccountAddress(
				sampleAddresses,
				false
			);

			fireEvent.click(getByText('Diamond Bar'));

			await wait(() => {
				expect(queryByText('save')).toBeFalsy();
				expect(queryByText('cancel')).toBeFalsy();
			});
		});
	});
});

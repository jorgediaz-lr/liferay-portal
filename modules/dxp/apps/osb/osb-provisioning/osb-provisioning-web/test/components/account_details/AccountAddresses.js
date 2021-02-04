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

function renderAccountAddress(props) {
	return render(
		<AccountAddresses
			accountKey="key123"
			addresses={[
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
			]}
			addURL="/"
			{...props}
		/>
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

	it('renders', () => {
		const {container} = renderAccountAddress();

		return wait(() => expect(container).toBeTruthy());
	});

	it('displays an addresses List Group with dashes for each field when no address was provided', () => {
		const {getAllByText} = renderAccountAddress({addresses: []});

		return wait(() => expect(getAllByText('-').length).toBe(7));
	});

	it('does not allow any address fields to be edited when no address was provided', () => {
		const {getAllByText, queryByText} = renderAccountAddress({
			addresses: []
		});

		fireEvent.click(getAllByText('-')[0]);

		return wait(() => {
			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});

	it('displays an add button when no address was provided', () => {
		const {queryByLabelText} = renderAccountAddress({addresses: []});

		return wait(() => expect(queryByLabelText('add')).toBeTruthy());
	});

	it('displays no delete button when no address was provided', () => {
		const {queryByLabelText} = renderAccountAddress({addresses: []});

		return wait(() => expect(queryByLabelText('delete')).toBeFalsy());
	});

	it('displays multiple address List Groups when multiple addresses are provided', () => {
		const {getByText} = renderAccountAddress();

		return wait(() => {
			getByText('address 1');
			getByText('address 2');
			getByText('address 3');
		});
	});

	it('allows address fields to be edited when at least one address was provided', () => {
		const {getByText} = renderAccountAddress();

		fireEvent.click(getByText('Diamond Bar'));

		return wait(() => {
			getByText('save');
			getByText('cancel');
		});
	});

	it('displays an add button for each provided addresses', () => {
		const {getAllByLabelText} = renderAccountAddress();

		return wait(() => expect(getAllByLabelText('add').length).toBe(3));
	});

	it('displays a delete button for each provided addresses', () => {
		const {getAllByLabelText} = renderAccountAddress();

		return wait(() => expect(getAllByLabelText('delete').length).toBe(3));
	});
});

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

import {cleanup, render, within} from '@testing-library/react';
import React from 'react';

import Purchases from '../../../src/main/resources/META-INF/resources/js/components/license_generation/Purchases';
import {
	displayInMDYDateFormat,
	generateNewDate,
	getUTCAdjustedDate
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

function renderPurchases({...props}) {
	return render(
		<Purchases
			purchased={[
				{
					expirationDate: '2020-04-16',
					instanceSize: 1,
					licenseKeysGenerated: '0 / 1',
					perpetual: false,
					productPurchaseKey: 'PURCHKEY-123',
					startDate: '2020-03-17'
				},
				{
					expirationDate: '',
					instanceSize: 1,
					licenseKeysGenerated: '1 / 1',
					perpetual: true,
					productPurchaseKey: 'PURCHKEY-456',
					startDate: ''
				}
			]}
			{...props}
		/>
	);
}

describe('Purchases', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderPurchases();

		expect(container).toBeTruthy();
	});

	it('displays a Start Date column', () => {
		const {getByText} = renderPurchases();

		getByText('start-date');
	});

	it('displays an Expiration Date column', () => {
		const {getByText} = renderPurchases();

		getByText('expiration-date');
	});

	it('displays an Instance Size column', () => {
		const {getByText} = renderPurchases();

		getByText('instance-size');
	});

	it('displays a License Key Generated column', () => {
		const {getByText} = renderPurchases();

		getByText('license-key-generated');
	});

	it('always displays a Detached section', () => {
		const {getByText} = renderPurchases();

		getByText('detached');
	});

	it('only renders the Detached section with default values (dashes) if no purchased product is provided', () => {
		const {getAllByText} = renderPurchases({purchased: []});

		expect(getAllByText('-').length).toBe(4);
	});

	it('allows the user to select an Instance Size from a list of choices in the Detached section', () => {
		const {getByLabelText} = renderPurchases({
			detached: {
				instanceSizes: [1, 2, 3, 4],
				licenseKeysGenerated: '0'
			}
		});

		within(getByLabelText('instance-size')).getByText('1');
		within(getByLabelText('instance-size')).getByText('2');
		within(getByLabelText('instance-size')).getByText('3');
		within(getByLabelText('instance-size')).getByText('4');
	});

	it('displays dates in MDY format', () => {
		const {getByText} = renderPurchases();

		getByText('March 17, 2020');
	});

	it('always displays Start Date in the Detached section as Today in UTC', () => {
		const {getByText} = renderPurchases({
			detached: {
				instanceSizes: [1, 2, 3, 4],
				licenseKeysGenerated: '0'
			},
			purchased: []
		});

		const utcAdjustedStartDate = getUTCAdjustedDate(new Date());

		getByText(displayInMDYDateFormat(utcAdjustedStartDate));
	});

	it('always displays Expiration Date in the Detached section as a year from the Start Date', () => {
		const {getByText} = renderPurchases({
			detached: {
				instanceSizes: [1, 2, 3, 4],
				licenseKeysGenerated: '0'
			},
			purchased: []
		});

		const utcAdjustedStartDate = getUTCAdjustedDate(new Date());
		const utcAdjustedExpirationDate = generateNewDate(utcAdjustedStartDate);

		getByText(displayInMDYDateFormat(utcAdjustedStartDate));
		getByText(displayInMDYDateFormat(utcAdjustedExpirationDate));

		const startDateYear = utcAdjustedStartDate.getFullYear();
		const expirationDateYear = utcAdjustedExpirationDate.getFullYear();

		expect(expirationDateYear - startDateYear).toBe(1);
	});

	it('always displays the Start Date of a Perpetual subscription in the Non Detached section as Today in UTC', () => {
		const {getByText} = renderPurchases();

		const utcAdjustedStartDate = getUTCAdjustedDate(new Date());

		getByText(displayInMDYDateFormat(utcAdjustedStartDate));
	});

	it('always displays the Expiration Date of a Perpetual subscription in the Non Detached section as 100 years from Today in UTC', () => {
		const {getByText} = renderPurchases();

		const utcAdjustedStartDate = getUTCAdjustedDate(new Date());
		const utcAdjustedExpirationDate = generateNewDate(
			utcAdjustedStartDate,
			100
		);

		getByText(displayInMDYDateFormat(utcAdjustedStartDate));
		getByText(displayInMDYDateFormat(utcAdjustedExpirationDate));

		expect(
			utcAdjustedExpirationDate.getFullYear() -
				utcAdjustedStartDate.getFullYear()
		).toBe(100);
	});

	it('displays the Start Date of a Non Perpetual subscription in the Non Detached section correctly', () => {
		const {getByText} = renderPurchases();

		getByText('March 17, 2020');
	});

	it('displays the Expiration Date of a Non Perpetual subscription whose license Type is NOT Developer in the Non Detached section 100 years from the subscription End Date', () => {
		const {getByText} = renderPurchases();

		getByText('April 16, 2120');
	});

	it('displays the Expiration Date of a Non Perpetual subscription whose license Type is Developer in the Non Detached section from the subscription End Date', () => {
		const {getByText} = renderPurchases({type: 'developer'});

		getByText('April 16, 2020');
	});
});

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

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import Purchases from '../../../src/main/resources/META-INF/resources/js/components/license_generation/Purchases';
import {NewLicenseProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/newLicense';
import {
	formatDate,
	generateNewDateByDay,
	generateNewDateByYear
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const TODAY = new Date();

function renderPurchases({...props}) {
	return render(
		<NewLicenseProvider>
			<Purchases
				purchased={[
					{
						endDate: '2020-04-16',
						instanceSize: 1,
						licenseKeysGenerated: '0 / 1',
						perpetual: false,
						productPurchaseKey: 'PURCHKEY-123',
						startDate: '2020-03-17'
					},
					{
						endDate: '',
						instanceSize: 1,
						licenseKeysGenerated: '1 / 1',
						perpetual: true,
						productPurchaseKey: 'PURCHKEY-456',
						startDate: ''
					}
				]}
				{...props}
			/>
		</NewLicenseProvider>
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

	it('displays a License Keys Generated column', () => {
		const {getByText} = renderPurchases();

		getByText('licenses-generated');
	});

	it('always displays a Detached section', () => {
		const {getByText} = renderPurchases();

		getByText('detached');
	});

	it('displays only the Active section for purchased subscriptions if none are expired', () => {
		const {getByText, queryByText} = renderPurchases({
			purchased: [
				{
					endDate: '',
					instanceSize: 1,
					licenseKeysGenerated: '1 / 1',
					perpetual: true,
					productPurchaseKey: 'PURCHKEY-123',
					startDate: ''
				},
				{
					endDate: '',
					instanceSize: 5,
					licenseKeysGenerated: '1 / 1',
					perpetual: true,
					productPurchaseKey: 'PURCHKEY-456',
					startDate: ''
				}
			]
		});

		getByText('active-subscriptions');
		expect(queryByText('expired-subscriptions')).toBeFalsy();
	});

	it('displays only the Expired section if no subscriptions are active', () => {
		const {getByText, queryByText} = renderPurchases({
			purchased: [
				{
					endDate: '2020-04-16',
					instanceSize: 1,
					licenseKeysGenerated: '0 / 1',
					perpetual: false,
					productPurchaseKey: 'PURCHKEY-123',
					startDate: '2020-03-17'
				},
				{
					endDate: '2020-05-16',
					instanceSize: 2,
					licenseKeysGenerated: '1 / 1',
					perpetual: false,
					productPurchaseKey: 'PURCHKEY-456',
					startDate: '2019-05-16'
				}
			]
		});

		getByText('expired-subscriptions');
		expect(queryByText('active-subscriptions')).toBeFalsy();
	});

	it('only renders the Detached section with default values (dashes) if no purchased product is provided', () => {
		const {getAllByText, getByText} = renderPurchases({purchased: []});

		expect(getAllByText('-').length).toBe(4);
		expect(getByText('choose').disabled).toBeTruthy();
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

	it('displays a Choose button for each Purchase section', () => {
		const {getAllByText} = renderPurchases();

		expect(getAllByText('choose').length).toBe(3);
	});

	describe('Dates', () => {
		// Clay Date Picker always displays two inputs for the same date

		describe('Dates for Detached Section', () => {
			it('always displays Start Date as Today in UTC', () => {
				const {getAllByDisplayValue} = renderPurchases({
					detached: {
						instanceSizes: [1, 2, 3, 4],
						licenseKeysGenerated: '0'
					},
					purchased: []
				});

				expect(getAllByDisplayValue(formatDate(TODAY)).length).toBe(2);
			});

			it('always displays Expiration Date as one year after the Start Date', () => {
				const {getAllByDisplayValue} = renderPurchases({
					detached: {
						instanceSizes: [1, 2, 3, 4],
						licenseKeysGenerated: '0'
					},
					purchased: []
				});

				const startDate = TODAY;
				const expirationDate = generateNewDateByYear(startDate);

				expect(getAllByDisplayValue(formatDate(startDate)).length).toBe(
					2
				);
				expect(
					getAllByDisplayValue(formatDate(expirationDate)).length
				).toBe(2);

				const startDateYear = startDate.getFullYear();
				const expirationDateYear = expirationDate.getFullYear();

				expect(expirationDateYear - startDateYear).toBe(1);
			});
		});

		describe('Dates for Non Detached Section', () => {
			it('always displays the Start Date of a Perpetual subscription as Today in UTC', () => {
				const {getAllByDisplayValue} = renderPurchases();

				expect(getAllByDisplayValue(formatDate(TODAY)).length).toBe(2);
			});

			it('always displays the Expiration Date of a Perpetual subscription whose Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster as 100 years from Today in UTC', () => {
				const {getAllByDisplayValue} = renderPurchases();

				const startDate = TODAY;
				const expirationDate = generateNewDateByYear(startDate, 100);

				expect(getAllByDisplayValue(formatDate(startDate)).length).toBe(
					2
				);
				expect(
					getAllByDisplayValue(formatDate(expirationDate)).length
				).toBe(2);

				expect(
					expirationDate.getFullYear() - startDate.getFullYear()
				).toBe(100);
			});

			it('always displays the Expiration Date of a Perpetual subscription whose Type is Enterpirse, Limited, OEM, or Virtual Cluster as 395 days (365 days + 30 days of grace period) from Today in UTC', () => {
				const {getAllByDisplayValue} = renderPurchases({
					type: 'virtual_cluster'
				});

				const startDate = TODAY;
				const expirationDate = generateNewDateByDay(
					generateNewDateByYear(startDate)
				);

				expect(getAllByDisplayValue(formatDate(startDate)).length).toBe(
					2
				);
				expect(
					getAllByDisplayValue(formatDate(expirationDate)).length
				).toBe(2);

				expect(
					expirationDate.getFullYear() - startDate.getFullYear()
				).toBe(1);
				expect(expirationDate.getMonth() - startDate.getMonth()).toBe(
					1
				);
			});

			it('displays the license Start Date of a Non Perpetual subscription as the subscription start date', () => {
				const {getAllByDisplayValue} = renderPurchases();

				expect(getAllByDisplayValue('2020-03-17').length).toBe(2);
			});

			it('displays the Expiration Date of a Non Perpetual subscription whose license Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster as 100 years from the subscription End Date', () => {
				const {getAllByDisplayValue} = renderPurchases();

				expect(getAllByDisplayValue('2120-03-23').length).toBe(2);
			});

			it('displays the Expiration Date of a Non Perpetual subscription whose license Type is Enterpirse, Limited, OEM, or Virtual Cluster as the subscription End Date', () => {
				const {getAllByDisplayValue} = renderPurchases({
					type: 'virtual_cluster'
				});

				expect(getAllByDisplayValue('2020-04-16').length).toBe(2);
			});
		});

		it('displays the Choose button as disabled when a date field is left empty', () => {
			const {getAllByPlaceholderText, getAllByText} = renderPurchases();

			const firstChooseBtn = getAllByText('choose')[0];

			expect(firstChooseBtn.disabled).toBeFalsy();

			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: ''}
			});

			expect(firstChooseBtn.disabled).toBeTruthy();
		});

		it('displays the Choose button as disabled when an invalid date is entered', () => {
			const {getAllByPlaceholderText, getAllByText} = renderPurchases();

			const firstChooseBtn = getAllByText('choose')[0];

			expect(firstChooseBtn.disabled).toBeFalsy();

			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-04-32'}
			});

			expect(firstChooseBtn.disabled).toBeTruthy();
		});
	});
});

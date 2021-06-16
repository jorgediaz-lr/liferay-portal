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

import {cleanup, fireEvent, render, wait, within} from '@testing-library/react';
import React from 'react';

import Purchases from '../../../src/main/resources/META-INF/resources/js/components/license_generation/Purchases';
import {
	License,
	NewLicenseProvider
} from '../../../src/main/resources/META-INF/resources/js/hooks/newLicense';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {
	formatDate,
	generateNewDateByDay,
	generateNewDateByYear
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const TODAY = new Date();

function renderPurchases({
	initialLicense = new License(),
	permission = true,
	props = {}
} = {}) {
	return render(
		<NewLicenseProvider initialLicense={initialLicense}>
			<PermissionsProvider
				permissions={{updateDatePermission: permission}}
			>
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
			</PermissionsProvider>
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
			props: {
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
			}
		});

		getByText('active-subscriptions');
		expect(queryByText('expired-subscriptions')).toBeFalsy();
	});

	it('displays only the Expired section if no subscriptions are active', () => {
		const {getByText, queryByText} = renderPurchases({
			props: {
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
			}
		});

		getByText('expired-subscriptions');
		expect(queryByText('active-subscriptions')).toBeFalsy();
	});

	it('only renders the Detached section with default values (dashes) if no purchased product is provided', () => {
		const {getAllByText, getByText} = renderPurchases({
			props: {
				purchased: []
			}
		});

		expect(getAllByText('-').length).toBe(4);
		expect(getByText('choose').disabled).toBeTruthy();
	});

	it('allows the user to select an Instance Size from a list of choices in the Detached section', () => {
		const {getByLabelText} = renderPurchases({
			props: {
				detached: {
					instanceSizes: [1, 2, 3, 4],
					licenseKeysGenerated: '0'
				}
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

		it('always displays a date picker for Start Date', () => {
			let {container} = renderPurchases();

			const fullPrivilegeStartDateDatepickers = container.querySelectorAll(
				'input[name="startDate"]'
			);

			expect(fullPrivilegeStartDateDatepickers.length).toBe(2);

			container = renderPurchases({permission: false}).container;

			const limitedPrivilegeStartDateDatepickers = container.querySelectorAll(
				'input[name="startDate"]'
			);

			expect(limitedPrivilegeStartDateDatepickers.length).toBe(2);
		});

		it('always displays Expiration Date datepicker for users with full privilege', () => {
			const {container} = renderPurchases({
				props: {
					detached: {
						instanceSizes: [1, 2, 3, 4],
						licenseKeysGenerated: '0'
					}
				}
			});

			const expDateDatepickers = container.querySelectorAll(
				'input[name="expirationDate"]'
			);

			expect(expDateDatepickers.length).toBe(3);
		});

		it('displays the Expiration Date datepicker under the Detached section for users with limited privilege', () => {
			const {container} = renderPurchases({
				permission: false,
				props: {
					detached: {
						instanceSizes: [1, 2, 3, 4],
						licenseKeysGenerated: '0'
					},
					purchased: []
				}
			});

			const expDateDatepickers = container.querySelectorAll(
				'input[name="expirationDate"]'
			);

			expect(expDateDatepickers.length).toBe(1);
		});

		it('disables the Choose button for the limited privileged user in the Detached section if the Expiration Date selected is not within one year from the start date when Type is one of Enterpirse, Limited, OEM, or Virtual Cluster', async () => {
			const {container, getAllByPlaceholderText} = renderPurchases({
				initialLicense: new License({
					licenseEntry: {
						licenseEntryType: 'virtual_cluster'
					}
				}),
				permission: false,
				props: {
					detached: {
						instanceSizes: [1, 2, 3, 4],
						licenseKeysGenerated: '0'
					},
					purchased: []
				}
			});

			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[1], {
				target: {value: formatDate(generateNewDateByYear(TODAY, 2))}
			});

			await wait(() =>
				expect(
					within(container).getByText('choose').disabled
				).toBeTruthy()
			);
		});

		it('displays the Expiration Date datepicker under the Non Detached section for users with limited privilege if the Type is NOT Enterpirse, Limited, OEM, or Virtual Cluster', () => {
			const {container} = renderPurchases({
				permission: false
			});

			const expDateDatepickers = container.querySelectorAll(
				'input[name="expirationDate"]'
			);

			expect(expDateDatepickers.length).toBe(2);
		});

		it('does not display the Expiration Date datepicker under the Non Detached section for users with limited previliege if the Type is Enterpirse, Limited, OEM, or Virtual Cluster', () => {
			const {container} = renderPurchases({
				initialLicense: new License({
					licenseEntry: {
						licenseEntryType: 'virtual_cluster'
					}
				}),
				permission: false
			});

			const expDateDatepickers = container.querySelectorAll(
				'input[name="expirationDate"]'
			);

			expect(expDateDatepickers.length).toBe(0);
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

		describe('Dates for Detached Section', () => {
			it('always displays Start Date as Today in UTC', () => {
				const {getAllByDisplayValue} = renderPurchases({
					props: {
						detached: {
							instanceSizes: [1, 2, 3, 4],
							licenseKeysGenerated: '0'
						},
						purchased: []
					}
				});

				expect(getAllByDisplayValue(formatDate(TODAY)).length).toBe(2);
			});

			it('always displays Expiration Date as one year after the Start Date', () => {
				const {getAllByDisplayValue} = renderPurchases({
					props: {
						detached: {
							instanceSizes: [1, 2, 3, 4],
							licenseKeysGenerated: '0'
						},
						purchased: []
					}
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
					initialLicense: new License({
						licenseEntry: {
							licenseEntryType: 'virtual_cluster'
						}
					})
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
					initialLicense: new License({
						licenseEntry: {
							licenseEntryType: 'virtual_cluster'
						}
					})
				});

				expect(getAllByDisplayValue('2020-04-16').length).toBe(2);
			});
		});
	});
});

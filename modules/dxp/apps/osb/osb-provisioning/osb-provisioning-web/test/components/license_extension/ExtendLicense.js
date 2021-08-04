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

import ExtendLicense from '../../../src/main/resources/META-INF/resources/js/components/license_extension/ExtendLicense';
import {formatDate} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const singleAttachedLicense = [
	{
		accountName: 'Account 1',
		expirationDate: '2122-06-08',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: '0',
		licenseType: 'development',
		productName: 'DXP 7.0',
		startDate: '2021-06-03',
		terms: [
			{
				endDate: '',
				licenseKeysGenerated: '2 / 1',
				perpetual: true,
				productPurchaseKey: 'productPurchaseKey1',
				startDate: '',
				status: 'Approved'
			},
			{
				endDate: '2022-07-02',
				licenseKeysGenerated: '1 / 1',
				perpetual: false,
				productPurchaseKey: 'productPurchaseKey2',
				startDate: '2021-06-02',
				status: 'Approved'
			}
		]
	}
];

const multipleDetachedLicenses = [
	{
		accountName: 'Account 1',
		expirationDate: '2022-06-04',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseKeysGenerated: '0',
		licenseType: 'production',
		productName: 'DXP Development',
		startDate: '2021-06-04'
	},
	{
		accountName: 'Account 2',
		expirationDate: '2027-12-14',
		indefinite: false,
		licenseKeyId: 'licenseKeyID2',
		licenseKeysGenerated: '0',
		licenseType: 'developer',
		productName: 'DXP Development',
		startDate: '2021-07-26'
	}
];

function renderExtendLicense(props) {
	return render(
		<ExtendLicense
			details={[
				{
					accountName: 'Account 1',
					expirationDate: '2021-08-21',
					indefinite: false,
					licenseKeyId: 'licenseKeyID1',
					licenseKeysGenerated: '0',
					licenseType: 'development',
					productName: 'DXP 7.0',
					startDate: '2021-07-21'
				}
			]}
			extensionURL="/extension/url"
			hasUpdateLicenseDatePermission={true}
			{...props}
		/>
	);
}

describe('ExtendLicense', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExtendLicense();

		expect(container).toBeTruthy();
	});

	describe('general display', () => {
		it('displays an Account Name table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('account-name');
		});

		it('displays a Product table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('products');
		});

		it('displays a Subscription Terms table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('subscription-term');
		});

		it('displays a Start Date table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('start-date');
		});

		it('displays an Expiration Date table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('expiration-date');
		});

		it('displays a Licenses Generated table heading', () => {
			const {getByText} = renderExtendLicense();

			getByText('licenses-generated');
		});
	});

	describe('Extend Button', () => {
		it('does not render an Extend button for a single permanent license', () => {
			const {queryByText} = renderExtendLicense({
				details: [
					{
						accountName: 'Account 1',
						expirationDate: '2022-06-04',
						indefinite: true,
						licenseKeyId: 'licenseKeyID1',
						licenseType: 'production',
						productName: 'Commerce Subscription Backup',
						startDate: '2021-06-04'
					}
				]
			});

			expect(queryByText('extend')).toBeFalsy();
		});

		it('renders an enabled Extend button for a single temporary license by default', () => {
			const {getByText} = renderExtendLicense({
				details: singleAttachedLicense
			});

			expect(getByText('extend').disabled).toBeFalsy();
		});

		it('renders one Extend button for a group of temporary licenses for the same product', () => {
			const {getAllByText} = renderExtendLicense({
				details: multipleDetachedLicenses
			});

			expect(getAllByText('extend').length).toBe(1);
		});

		it('renders a disabled Extend button if any of the dates are empty for a single extend', () => {
			const {getAllByDisplayValue, getByText} = renderExtendLicense({
				details: singleAttachedLicense
			});

			expect(getByText('extend').disabled).toBeFalsy();

			// Clay Date Picker always displays two inputs for the same date

			fireEvent.change(getAllByDisplayValue('2021-06-03')[1], {
				target: {value: ''}
			});

			expect(getByText('extend').disabled).toBeTruthy();
		});

		it('renders a disabled Extend button if any of the dates are invalid for bulk extend', () => {
			const {getAllByDisplayValue, getByText} = renderExtendLicense({
				details: multipleDetachedLicenses
			});

			expect(getByText('extend').disabled).toBeFalsy();

			// Clay Date Picker always displays two inputs for the same date

			fireEvent.change(getAllByDisplayValue(formatDate(new Date()))[1], {
				target: {value: 'invalid'}
			});

			expect(getByText('extend').disabled).toBeTruthy();
		});

		it('always renders an enabled Extend button after a new Subscription Term is selected', () => {
			const {getByDisplayValue, getByText} = renderExtendLicense({
				details: singleAttachedLicense
			});

			fireEvent.change(getByDisplayValue('perpetual'), {
				target: {value: 'productPurchaseKey2'}
			});

			expect(getByText('extend').disabled).toBeFalsy();
		});
	});

	describe('Default Dates', () => {
		it('renders the default start and expiration dates of the existing license for an Attached license entry', () => {
			const {getAllByDisplayValue} = renderExtendLicense({
				details: singleAttachedLicense
			});

			// Clay Date Picker always displays two inputs for the same date
			// The date occurs one more time in the hidden form

			expect(getAllByDisplayValue('2021-06-03').length).toBe(3);
			expect(getAllByDisplayValue('2122-06-08').length).toBe(3);
		});

		it('renders Today as the default start date for a Detached license', () => {
			const {getAllByDisplayValue} = renderExtendLicense({
				details: [
					{
						accountName: 'Account 1',
						expirationDate: '2022-06-04',
						indefinite: false,
						licenseKeyId: 'licenseKeyID1',
						licenseKeysGenerated: '0',
						licenseType: 'production',
						productName: 'Commerce Subscription Backup',
						startDate: '2021-06-04'
					}
				]
			});

			// Clay Date Picker always displays two inputs for the same date
			// The date occurs one more time in the hidden form

			expect(getAllByDisplayValue(formatDate(new Date())).length).toBe(3);
		});
	});

	it('renders the Permanent License table heading for a single permanent license', () => {
		const {getByText} = renderExtendLicense({
			details: [
				{
					accountName: 'Account 1',
					expirationDate: '2022-06-04',
					indefinite: true,
					licenseKeyId: 'licenseKeyID1',
					licenseType: 'production',
					productName: 'Commerce Subscription Backup',
					startDate: '2021-06-04'
				}
			]
		});

		getByText('permanent-licenses');
	});

	it('updates the Liceses Generated value when terms change', () => {
		const {
			getByDisplayValue,
			getByText,
			queryByText
		} = renderExtendLicense({details: singleAttachedLicense});

		getByText('2 / 1');
		expect(queryByText('1 / 1')).toBeFalsy();

		fireEvent.change(getByDisplayValue('perpetual'), {
			target: {value: 'productPurchaseKey2'}
		});

		expect(queryByText('2 / 1')).toBeFalsy();
		getByText('1 / 1');
	});

	it('does not render any terms whose status is cancelled', () => {
		const {getByText, queryByText} = renderExtendLicense({
			details: [
				{
					accountName: 'Account 1',
					expirationDate: '2122-06-08',
					indefinite: false,
					licenseKeyId: 'licenseKeyID1',
					licenseKeysGenerated: '0',
					licenseType: 'development',
					productName: 'DXP 7.0',
					startDate: '2021-06-03',
					terms: [
						{
							endDate: '',
							licenseKeysGenerated: '2 / 1',
							perpetual: true,
							productPurchaseKey: 'productPurchaseKey1',
							startDate: '',
							status: 'Approved'
						},
						{
							endDate: '2022-07-02',
							licenseKeysGenerated: '1 / 1',
							perpetual: false,
							productPurchaseKey: 'productPurchaseKey2',
							startDate: '2021-06-02',
							status: 'Cancelled'
						}
					]
				}
			]
		});

		getByText('perpetual');
		expect(queryByText('June 02, 2021 - July 02, 2022')).toBeFalsy();
	});
});

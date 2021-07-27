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

import ExtensionDetails from '../../../src/main/resources/META-INF/resources/js/components/license_extension/ExtensionDetails';
import {ExtendLicensesProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/extendLicenses';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {DASH} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';
import {formatDate} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const multipleDetachedLicenses = [
	{
		expirationDate: '2022-06-04',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseType: 'production',
		productName: 'Commerce Subscription Backup',
		startDate: '2021-06-04'
	},
	{
		expirationDate: '2027-12-14',
		indefinite: false,
		licenseKeyId: 'licenseKeyID2',
		licenseType: 'developer',
		productName: 'DXP Development',
		startDate: '2021-07-26'
	}
];

const singleDetachedLicense = [
	{
		expirationDate: '2022-06-04',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseType: 'production',
		productName: 'Commerce Subscription Backup',
		startDate: '2021-06-04'
	}
];

const singleAttachedLicense = [
	{
		expirationDate: '2122-06-08',
		indefinite: false,
		licenseKeyId: 'licenseKeyID1',
		licenseType: 'development',
		productName: 'DXP 7.0',
		startDate: '2021-06-03',
		terms: [
			{
				endDate: '',
				perpetual: true,
				productPurchaseKey: 'productPurchaseKey1',
				startDate: ''
			},
			{
				endDate: '2022-07-02',
				perpetual: false,
				productPurchaseKey: 'productPurchaseKey2',
				startDate: '2021-06-02'
			}
		]
	}
];

function renderExtensionDetails(initialLicenses, permission = true) {
	return render(
		<table>
			<ExtendLicensesProvider initialLicenses={initialLicenses}>
				<PermissionsProvider
					permissions={{updateDatePermission: permission}}
				>
					<ExtensionDetails extensionURL="/extension/url" />
				</PermissionsProvider>
			</ExtendLicensesProvider>
		</table>
	);
}

describe('ExtensionDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExtensionDetails(singleDetachedLicense);

		expect(container).toBeTruthy();
	});

	it('renders the Terms of a Detached license as a dash', () => {
		const {getByText} = renderExtensionDetails(singleDetachedLicense);

		getByText(DASH);
	});

	it('renders the Terms in the dropdown of a Non Detached license', () => {
		const {getByText} = renderExtensionDetails(singleAttachedLicense);

		getByText('perpetual');
		getByText('June 2, 2021 - July 2, 2022');
	});

	it('renders a disabled X icon for a single license extension', () => {
		const {getByLabelText, getByTitle} = renderExtensionDetails(
			singleDetachedLicense
		);

		getByLabelText('delete-license-icon');
		expect(getByTitle('delete').disabled).toBeTruthy();
	});

	it('renders an enabled X iconf if there are multiple license extensions', () => {
		const {getAllByTitle} = renderExtensionDetails(
			multipleDetachedLicenses
		);

		expect(getAllByTitle('delete').disabled).toBeFalsy();
	});

	it('renders an enabled Extend button for a single temporary license by default', () => {
		const {getByText} = renderExtensionDetails(singleAttachedLicense);

		expect(getByText('extend').disabled).toBeFalsy();
	});

	it('does not render an Extend button for a single permanent license', () => {
		const {queryByText} = renderExtensionDetails([
			{
				expirationDate: '2022-06-04',
				indefinite: true,
				licenseKeyId: 'licenseKeyID1',
				licenseType: 'production',
				productName: 'Commerce Subscription Backup',
				startDate: '2021-06-04'
			}
		]);

		expect(queryByText('extend')).toBeFalsy();
	});

	it('renders a disabled Extend button if any of the dates are empty', () => {
		const {getAllByDisplayValue, getByText} = renderExtensionDetails(
			singleAttachedLicense
		);

		// Clay Date Picker always displays two inputs for the same date

		fireEvent.change(getAllByDisplayValue('2021-06-03')[1], {
			target: {value: ''}
		});

		expect(getByText('extend').disabled).toBeTruthy();
	});

	it('always renders an enabled Extend button after a new Subscription Term is selected', () => {
		const {getByDisplayValue, getByText} = renderExtensionDetails(
			singleAttachedLicense
		);

		fireEvent.change(getByDisplayValue('perpetual'), {
			target: {value: 'June 2, 2021 - July 2, 2022'}
		});

		expect(getByText('extend').disabled).toBeFalsy();
	});

	it('renders the default start and expiration dates of the existing license for an Attached license entry', () => {
		const {getAllByDisplayValue} = renderExtensionDetails(
			singleAttachedLicense
		);

		// Clay Date Picker always displays two inputs for the same date
		// The date occurs one more time in the hidden form

		expect(getAllByDisplayValue('2021-06-03').length).toBe(3);
		expect(getAllByDisplayValue('2122-06-08').length).toBe(3);
	});

	it('renders Today as the default start date for a Detached license', () => {
		const {getAllByDisplayValue} = renderExtensionDetails(
			singleDetachedLicense
		);

		// Clay Date Picker always displays two inputs for the same date
		// The date occurs one more time in the hidden form

		expect(getAllByDisplayValue(formatDate(new Date())).length).toBe(3);
	});
});

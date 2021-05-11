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

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import LicenseGroup from '../../../src/main/resources/META-INF/resources/js/components/license_download/LicenseGroup';
import {
	formatDate,
	generateNewDate
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const multipleLicenses = [
	[
		{
			active: true,
			description: 'Test Account description',
			expirationDate: 'April 16, 2122',
			licenseEntryName: 'Portal Backup',
			licenseEntryType: 'production',
			licenseKeyId: '85602',
			licenseVersion: 3,
			name: 'License 1',
			productName: 'Portal Backup',
			productVersion: '6.1 GA1',
			startDate: 'March 17, 2021'
		},
		{
			active: true,
			description: 'Test Account description',
			expirationDate: 'April 16, 2122',
			licenseEntryName: 'Portal Backup',
			licenseEntryType: 'production',
			licenseKeyId: '85603',
			licenseVersion: 3,
			name: 'License 2',
			productName: 'Portal Backup',
			productVersion: '6.2',
			startDate: 'March 17, 2021'
		}
	]
];

function renderLicenseGroup(props) {
	return render(
		<table>
			<LicenseGroup
				downloadURL="/download/license/key/url"
				licenses={[
					[
						{
							active: true,
							description: 'Test Account description',
							expirationDate: 'April 16, 2122',
							licenseEntryName: 'Portal Backup',
							licenseEntryType: 'production',
							licenseKeyId: '85602',
							licenseVersion: 3,
							name: 'License 1',
							productName: 'Portal Backup',
							productVersion: '6.1 GA1',
							startDate: 'March 17, 2021'
						}
					]
				]}
				{...props}
			/>
		</table>
	);
}

describe('LicenseGroup', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderLicenseGroup();

		expect(container).toBeTruthy();
	});

	it('displays a download button', () => {
		const {getByText} = renderLicenseGroup();

		getByText('download');
	});

	it('only displays one Download button per group no matter how many licenses are listed', () => {
		const {getAllByText} = renderLicenseGroup({licenses: multipleLicenses});

		expect(getAllByText('download').length).toBe(1);
	});

	it('displays the Name and Description correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('License 1');
		getByText('Test Account description');
	});

	it('displays the Product Name correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('Portal Backup');
	});

	it('displays the Product Type correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('Portal Backup (Production)');
	});

	it('displays the Start Date correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('March 17, 2021');
	});

	it('displays the Expiration Date correctly', () => {
		const {getByText} = renderLicenseGroup();

		getByText('April 16, 2122');
	});

	it('displays the Deactivated Status label correctly', () => {
		const {getByText} = renderLicenseGroup({
			licenses: [
				[
					{
						active: false,
						description: 'Test Account description',
						expirationDate: 'April 16, 2122',
						licenseEntryName: 'Portal Backup',
						licenseEntryType: 'production',
						licenseKeyId: '85602',
						licenseVersion: 3,
						name: 'License 1',
						productName: 'Portal Backup',
						productVersion: '6.1 GA1',
						startDate: 'March 17, 2021'
					}
				]
			]
		});

		getByText('deactivated');
	});

	it('displays the Active Status label correctly', () => {
		const newExpirationDate = generateNewDate(new Date(), 2);

		const {getByText} = renderLicenseGroup({
			licenses: [
				[
					{
						active: true,
						description: 'Test Account description',
						expirationDate: formatDate(newExpirationDate),
						licenseEntryName: 'Portal Backup',
						licenseEntryType: 'production',
						licenseKeyId: '85602',
						licenseVersion: 3,
						name: 'License 1',
						productName: 'Portal Backup',
						productVersion: '6.1 GA1',
						startDate: 'March 17, 2020'
					}
				]
			]
		});

		getByText('active');
	});

	it('displays the Expired Status label correctly', () => {
		const {getByText} = renderLicenseGroup({
			licenses: [
				[
					{
						active: true,
						description: 'Test Account description',
						expirationDate: 'April 10, 2020',
						licenseEntryName: 'Portal Backup',
						licenseEntryType: 'production',
						licenseKeyId: '85602',
						licenseVersion: 3,
						name: 'License 1',
						productName: 'Portal Backup',
						productVersion: '6.1 GA1',
						startDate: 'March 17, 2020'
					}
				]
			]
		});

		getByText('expired');
	});
});

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

import DownloadLicenses from '../../../src/main/resources/META-INF/resources/js/components/license_download/DownloadLicenses';

function renderDownloadLicenses(props) {
	return render(
		<DownloadLicenses
			downloadLicenseKeysURL="/download/license/key/url"
			licenseKeys={[
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 1',
					productName: 'Portal Backup',
					productVersion: '6.1 GA1',
					startDate: 'March 17, 2021'
				},
				// Different productVersion, groupable with above license
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 2',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				// Inactive license
				{
					active: false,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85604',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 3',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				// Different expirationDate
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 17, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85605',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 4',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				// Different startDate
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85606',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 5',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 16, 2021'
				},
				// Different startDate
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85607',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 6',
					productName: 'Portal Backup',
					productVersion: '6.1',
					startDate: 'March 16, 2021'
				},
				// Different startDate
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85608',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 7',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 14, 2021'
				},
				// Different licenseEntryType
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'cluster',
					licenseKeyId: '85609',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 8',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				// Old licenseVersion
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85610',
					licenseVersion: 1,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 9',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				}
			]}
			{...props}
		/>
	);
}

describe('DownloadLicenses', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderDownloadLicenses();

		expect(container).toBeTruthy();
	});

	it('groups licenses that are active, has a license versions greater than 3, shares the same start and expiration dates, and license type', () => {
		const {container, getAllByText} = renderDownloadLicenses();

		const groups = container.querySelectorAll('tbody');

		within(groups[0]).getByText('License 1');
		within(groups[0]).getByText('License 2');

		expect(getAllByText('download').length).toBe(7);
	});

	it('groups licenses with the Pre-User type that contain the same maxConcurrentUsers and maxUsers value', () => {
		const {container, getAllByText} = renderDownloadLicenses({
			licenseKeys: [
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85600',
					licenseVersion: 3,
					maxConcurrentUsers: '0',
					maxUsers: '0',
					name: 'License 1',
					productName: 'Portal Backup',
					productVersion: '6.1 GA1',
					startDate: 'March 17, 2021'
				},
				// type Pre-User
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'per-user',
					licenseKeyId: '85601',
					licenseVersion: 3,
					maxConcurrentUsers: '1',
					maxUsers: '2',
					name: 'License 2',
					productName: 'Portal Backup',
					productVersion: '6.1 GA1',
					startDate: 'March 17, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'per-user',
					licenseKeyId: '85602',
					licenseVersion: 3,
					maxConcurrentUsers: '1',
					maxUsers: '2',
					name: 'License 3',
					productName: 'Portal Backup',
					productVersion: '6.1 GA1',
					startDate: 'March 17, 2021'
				},
				// different maxConcurrentUsers
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'per-user',
					licenseKeyId: '85603',
					licenseVersion: 3,
					maxConcurrentUsers: '2',
					maxUsers: '2',
					name: 'License 4',
					productName: 'Portal Backup',
					productVersion: '6.1 GA1',
					startDate: 'March 17, 2021'
				}
			]
		});

		const groups = container.querySelectorAll('tbody');

		within(groups[0]).getByText('License 1');

		within(groups[1]).getByText('License 2');
		within(groups[1]).getByText('License 3');

		within(groups[2]).getByText('License 4');

		expect(getAllByText('download').length).toBe(3);
	});
});

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
					licenseVersion: 4,
					name: 'License 1',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				// groupable with above license
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 4,
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
					licenseVersion: 4,
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
					licenseVersion: 4,
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
					licenseVersion: 4,
					name: 'License 5',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 16, 2021'
				},
				// Different license version & product version
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'DXP Development',
					licenseEntryType: 'production',
					licenseKeyId: '85607',
					licenseVersion: 6,
					name: 'License 6',
					productName: 'DXP Development',
					productVersion: '7.4',
					startDate: 'March 17, 2021'
				},
				// Different license version & product version
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'DXP Development',
					licenseEntryType: 'production',
					licenseKeyId: '85608',
					licenseVersion: 6,
					name: 'License 7',
					productName: 'DXP Development',
					productVersion: '7.1',
					startDate: 'March 17, 2021'
				},
				// licenseEntryType is not Production
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'cluster',
					licenseKeyId: '85609',
					licenseVersion: 4,
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
					licenseVersion: 2,
					name: 'License 9',
					productName: 'Portal Backup',
					productVersion: '6.0',
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

	it('groups licenses that are active, has a license versions greater than 3, has a license type that is Production, and shares the same start date, expiration date, product version, and license version', () => {
		const {container, getAllByText} = renderDownloadLicenses();

		const groups = container.querySelectorAll('tbody');

		within(groups[0]).getByText('License 1');
		within(groups[0]).getByText('License 2');

		expect(getAllByText('download').length).toBe(8);
	});

	it('does not group licenses whose version is 3', () => {
		const {getAllByText} = renderDownloadLicenses({
			licenseKeys: [
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
					productVersion: '6.1',
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
					productVersion: '6.1',
					startDate: 'March 17, 2021'
				}
			]
		});

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose Type is not Production', () => {
		const {getAllByText} = renderDownloadLicenses({
			licenseKeys: [
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'elastic',
					licenseKeyId: '85602',
					licenseVersion: 4,
					name: 'License 1',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'elastic',
					licenseKeyId: '85603',
					licenseVersion: 4,
					name: 'License 2',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				}
			]
		});

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose start date is not the same', () => {
		const {getAllByText} = renderDownloadLicenses({
			licenseKeys: [
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 4,
					name: 'License 1',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 4,
					name: 'License 2',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 13, 2021'
				}
			]
		});

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose expiration date is not the same', () => {
		const {getAllByText} = renderDownloadLicenses({
			licenseKeys: [
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 14, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 4,
					name: 'License 1',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 4,
					name: 'License 2',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				}
			]
		});

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose license version is not the same', () => {
		// In real life, licenses wouldn't have different license versions without having different product and product versions

		const {getAllByText} = renderDownloadLicenses({
			licenseKeys: [
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 4,
					name: 'License 1',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 5,
					name: 'License 2',
					productName: 'Portal Backup',
					productVersion: '6.2',
					startDate: 'March 17, 2021'
				}
			]
		});

		expect(getAllByText('download').length).toBe(2);
	});

	it('does not group licenses whose product version is not the same', () => {
		const {getAllByText} = renderDownloadLicenses({
			licenseKeys: [
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'DXP Development',
					licenseEntryType: 'production',
					licenseKeyId: '85602',
					licenseVersion: 6,
					name: 'License 1',
					productName: 'DXP Development',
					productVersion: '7.1',
					startDate: 'March 17, 2021'
				},
				{
					active: true,
					description: 'Test Account description',
					expirationDate: 'April 16, 2122',
					licenseEntryName: 'DXP Development',
					licenseEntryType: 'production',
					licenseKeyId: '85603',
					licenseVersion: 6,
					name: 'License 2',
					productName: 'DXP Development',
					productVersion: '7.4',
					startDate: 'March 17, 2021'
				}
			]
		});

		expect(getAllByText('download').length).toBe(2);
	});
});

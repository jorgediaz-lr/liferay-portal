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

import SpecificDetails from '../../../src/main/resources/META-INF/resources/js/components/license_generation/SpecificDetails';
import {
	License,
	LicenseProvider
} from '../../../src/main/resources/META-INF/resources/js/hooks/license';
import {
	displayInMDYDateFormat,
	getUTCAdjustedDate
} from '../../../src/main/resources/META-INF/resources/js/utilities/date';

const dummyLicense = new License({
	accountKey: 'KEY-ABC',
	accountName: 'Test Account',
	complimentary: true,
	expirationDate: new Date(),
	licenseEntry: {
		licenseEntryId: 'ID-123',
		licenseEntryName: 'Test License Entry Name',
		licenseEntryType: 'developer'
	},
	licenseKeysGenerated: '0',
	name: 'Test Account',
	product: {productKey: 'PRODUCT-123', productName: 'Test Product'},
	productPurchaseKey: 'PPKEY-123',
	showSpecificDetails: true,
	sizing: '1',
	startDate: new Date(),
	version: '1.0'
});

function renderSpecificDetails(props) {
	return render(
		<LicenseProvider initialLicense={dummyLicense}>
			<SpecificDetails
				addLicenseKeyURL="add/license/key/url"
				redirect="/redirect/url"
				{...props}
			/>
		</LicenseProvider>
	);
}

describe('SpecificDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSpecificDetails();

		expect(container).toBeTruthy();
	});

	it('displays a Previous Step button', () => {
		const {getByText} = renderSpecificDetails();

		getByText('previous-step');
	});

	it('displays a Cancel button', () => {
		const {getByText} = renderSpecificDetails();

		getByText('cancel');
	});

	it('displays the Account Name correctly', () => {
		const {getByDisplayValue} = renderSpecificDetails();

		getByDisplayValue('Test Account');
	});

	it('displays the Product Name correctly', () => {
		const {getByText} = renderSpecificDetails();

		getByText('Test Product');
	});

	it('displays the Version field correctly', () => {
		const {getByText} = renderSpecificDetails();

		getByText('1.0');
	});

	it('displays the Type field correctly', () => {
		const {getByText} = renderSpecificDetails();

		getByText('Developer');
	});

	it('displays the Start and Expiration date fields correctly', () => {
		const {getAllByText} = renderSpecificDetails();

		const utcAdjustedDate = displayInMDYDateFormat(
			getUTCAdjustedDate(new Date())
		);

		expect(getAllByText(utcAdjustedDate).length).toBe(2);
	});

	it('displays the Complimentary checkbox correctly', () => {
		const {getByLabelText} = renderSpecificDetails();

		expect(getByLabelText('complimentary').checked).toBeTruthy();
	});

	it('displays the Server Id Fields section if the selected Type is one of Backup, Cluster, Limited, Non-production, Per-user, or Production', () => {
		const {getByText} = render(
			<LicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</LicenseProvider>
		);

		getByText('server-id-fields');
	});

	it('does not display the Server Id Fields section if the selected Type is one of Developer, Developer-cluster, Elastic, Enterprise, or OEM', () => {
		const {queryByText} = renderSpecificDetails();

		expect(queryByText('server-id-fields')).toBeFalsy();
	});

	it('does not display the Maximum Servers input if the selected Type is not Cluster', () => {
		const {queryByLabelText} = renderSpecificDetails();

		expect(queryByLabelText('maximum-servers')).toBeFalsy();
	});

	it('displays the Maximum Servers input if the selected Type is Cluster', () => {
		const {getByLabelText} = render(
			<LicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</LicenseProvider>
		);

		getByLabelText('maximum-servers');
	});

	it('does not display the Maximum Connections input if the selected Type is not Developer or Developer Cluster', () => {
		const {queryByLabelText} = render(
			<LicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'oem'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</LicenseProvider>
		);

		expect(queryByLabelText('maximum-connections')).toBeFalsy();
	});

	it('displays the Maximum Connections input if the selected Type is Developer', () => {
		const {getByLabelText} = renderSpecificDetails();

		getByLabelText('maximum-connections');
	});

	it('displays the Maximum Connections input if the selected Type is Developer Cluster', () => {
		const {getByLabelText} = render(
			<LicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'developer_cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</LicenseProvider>
		);

		getByLabelText('maximum-connections');
	});

	it('displays a Generate button', () => {
		const {getByText} = renderSpecificDetails();

		getByText('generate');
	});

	it('displays a disabled Generate button if the Owner field is empty', () => {
		const {getByText} = renderSpecificDetails();

		fireEvent.change(getByText('owner'), {taget: {value: ''}});

		expect(getByText('generate').disabled).toBeTruthy();
	});

	it('displays a disabled Generate button if the Description field is empty', () => {
		const {getByText} = renderSpecificDetails();

		fireEvent.change(getByText('description'), {taget: {value: ''}});

		expect(getByText('generate').disabled).toBeTruthy();
	});

	it('displays a disabled Generate button if the Server ID Fields are displayed but no value has been entered in any of its three fields', () => {
		const {getByText} = render(
			<LicenseProvider
				initialLicense={
					new License({
						licenseEntry: {
							licenseEntryType: 'cluster'
						}
					})
				}
			>
				<SpecificDetails
					addLicenseKeyURL="add/license/key/url"
					redirect={'/redirect/url'}
				/>
			</LicenseProvider>
		);

		expect(getByText('generate').disabled).toBeTruthy();
	});
});

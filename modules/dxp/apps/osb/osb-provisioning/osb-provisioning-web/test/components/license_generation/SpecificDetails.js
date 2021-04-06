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
	accountCode: 'ABC',
	accountKey: 'KEY-ABC',
	accountName: 'Test Account',
	complimentary: true,
	description: '',
	expirationDate: new Date(),
	licenseEntry: {
		licenseEntryId: 'ID-123',
		licenseEntryName: 'Test License Entry Name',
		licenseEntryType: 'type'
	},
	licenseKeysGenerated: '0',
	owner: '',
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
			<SpecificDetails redirect={'/redirect/url'} {...props} />
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

		getByText('Type');
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
});

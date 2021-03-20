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

import GeneralInformation from '../../../src/main/resources/META-INF/resources/js/components/license_generation/GeneralInformation';

const licensableProducts = [
	{
		productKey: 'KEY-123',
		productName: 'Product A',
		productVersions: {
			6.1: [
				{
					licenseEntryId: 98765,
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production'
				},
				{
					licenseEntryId: 87654,
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'development'
				}
			],
			6.2: [
				{
					licenseEntryId: 98765,
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'production'
				},
				{
					licenseEntryId: 87654,
					licenseEntryName: 'Portal Backup',
					licenseEntryType: 'development'
				}
			]
		}
	}
];

function renderGeneralInformation(props) {
	return render(
		<GeneralInformation
			redirect="/back/url"
			selectAccountActionURL="/action/url"
			selectAccountRenderURL="render/url"
			{...props}
		/>
	);
}

describe('GeneralInformation', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderGeneralInformation();

		expect(container).toBeTruthy();
	});

	it('displays a sub heading showing the editing step', () => {
		const {getAllByText, getByText} = renderGeneralInformation();

		expect(getAllByText('general-information').length).toBe(2);
		expect(getByText('step-1-of-2')).toBeTruthy();
	});

	it('displays a Cancel button', () => {
		const {getByText} = renderGeneralInformation();

		expect(getByText('cancel')).toBeTruthy();
	});

	it('displays a disabled Product select if account has not been selected', () => {
		const {getByLabelText} = renderGeneralInformation();

		expect(getByLabelText('product').disabled).toBeTruthy();
	});

	it('displays a disabled Type select if account has not been selected', () => {
		const {getByLabelText} = renderGeneralInformation();

		expect(getByLabelText('type').disabled).toBeTruthy();
	});

	it('displays a disabled Version select if account has not been selected', () => {
		const {getByLabelText} = renderGeneralInformation();

		expect(getByLabelText('version').disabled).toBeTruthy();
	});

	it('displays the account name if one is provided', () => {
		const {getByDisplayValue} = renderGeneralInformation({
			accountName: 'Test Account'
		});

		getByDisplayValue('Test Account');
	});

	it('populates the Product select with options if a list of licensable products is provided', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			accountName: 'Test Account',
			licensableProducts
		});

		getByText('Product A');
		expect(getByLabelText('product').disabled).toBeFalsy();
		expect(getByLabelText('type').disabled).toBeTruthy();
		expect(getByLabelText('version').disabled).toBeTruthy();
	});

	it('populates the Version select with options based on the Product selected', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			accountName: 'Test Account',
			licensableProducts
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});

		getByText('6.1');
		getByText('6.2');
		expect(getByLabelText('version').disabled).toBeFalsy();
		expect(getByLabelText('type').disabled).toBeTruthy();
	});

	it('populates the Type select with options based on the Product and Version selected', () => {
		const {getByLabelText, getByText} = renderGeneralInformation({
			accountName: 'Test Account',
			licensableProducts
		});

		fireEvent.change(getByLabelText('product'), {
			target: {value: 'KEY-123'}
		});
		fireEvent.change(getByLabelText('version'), {
			target: {value: '6.1'}
		});

		getByText('Production');
		getByText('Development');
		expect(getByLabelText('type').disabled).toBeFalsy();
	});
});

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

import ExtendLicense from '../../../src/main/resources/META-INF/resources/js/components/license_extension/ExtendLicense';

function renderExtendLicense() {
	return render(
		<ExtendLicense
			details={[
				{
					expirationDate: '2021-08-21',
					indefinite: false,
					licenseKeyId: 'licenseKeyID1',
					licenseType: 'development',
					productName: 'DXP 7.0',
					startDate: '2021-07-21'
				}
			]}
			extensionURL="/extension/url"
			hasUpdateLicenseDatePermission={true}
		/>
	);
}

describe('ExtendLicense', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExtendLicense();

		expect(container).toBeTruthy();
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
});

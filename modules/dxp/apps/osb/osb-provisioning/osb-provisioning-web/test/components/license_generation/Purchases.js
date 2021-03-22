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

import Purchases from '../../../src/main/resources/META-INF/resources/js/components/license_generation/Purchases';

function renderPurchases({...props}) {
	return render(
		<Purchases
			purchased={[
				{
					expirationDate: '2022-04-16',
					licenseKeysGenerated: '0 / 1',
					productPurchaseKey: 'PURCHKEY-123',
					sizing: 1,
					startDate: '2021-03-17'
				},
				{
					expirationDate: '-',
					licenseKeysGenerated: '1 / 1',
					productPurchaseKey: 'PURCHKEY-456',
					sizing: 1,
					startDate: '2021-03-22'
				}
			]}
			{...props}
		/>
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

	it('displays a License Key Generated column', () => {
		const {getByText} = renderPurchases();

		getByText('license-key-generated');
	});

	it('displays a Detached section', () => {
		const {getByText} = renderPurchases();

		getByText('detached');
	});

	it('only renders the Detached section if no purchased product is provided', () => {
		const {getAllByText} = renderPurchases({purchased: []});

		expect(getAllByText('-').length).toBe(4);
	});
});

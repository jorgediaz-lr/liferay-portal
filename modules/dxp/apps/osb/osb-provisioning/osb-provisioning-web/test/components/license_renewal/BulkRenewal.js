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

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import BulkRenewal from '../../../src/main/resources/META-INF/resources/js/components/license_renewal/BulkRenewal';

const handleOnClick = () => {
	const event = new CustomEvent('bulkRenewLicenses', {
		detail: {
			licenseKeyIds: 'id123',
			modalVisible: true
		}
	});

	window.dispatchEvent(event);
};

describe('BulkRenewal', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = render(
			<BulkRenewal accountKey="KEY-1" renewalURL="/renewal/url" />
		);

		expect(container).toBeTruthy();
	});

	it('triggers the renewal modal when the renew button was clicked', async () => {
		const {getByText} = render(
			<div>
				<button onClick={handleOnClick}>Test</button>

				<BulkRenewal accountKey="KEY-1" renewalURL="/renewal/url" />
			</div>
		);

		fireEvent.click(getByText('Test'));

		return await wait(() => {
			getByText('start-date');
			getByText('expiration-date');
		});
	});
});

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
import {GenerateLicenseProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/generateLicense';

function renderSpecificDetails(props) {
	return render(
		<GenerateLicenseProvider>
			<SpecificDetails redirect={'/redirect/url'} {...props} />
		</GenerateLicenseProvider>
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
});

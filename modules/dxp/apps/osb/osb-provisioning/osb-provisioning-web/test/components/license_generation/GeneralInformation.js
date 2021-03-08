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

import GeneralInformation from '../../../src/main/resources/META-INF/resources/js/components/license_generation/GeneralInformation';

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
});

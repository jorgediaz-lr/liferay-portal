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

import SupportInformation from '../../../src/main/resources/META-INF/resources/js/components/support_information/SupportInformation';

function renderSupportInformation() {
	return render(
		<SupportInformation
			account={{
				code: '123',
				editAccountURL: 'edit/account/url',
				key: '123',
				name: 'Test Account',
				region: 'United States',
				status: 'Active',
				tier: 'Regular'
			}}
			accountAttachmentURL="account/attachment/url"
			instructions="Sample instructions text"
			language={{id: 'en_US', name: 'English'}}
			languageList={[
				{id: 'en_US', name: 'English'},
				{id: 'zh_CN', name: 'Chinese'},
				{id: 'es_ES', name: 'Spanish'}
			]}
			oemInstructionsFileName="oemInstructionsFile"
			regionNames={['United States', 'China', 'Spain']}
			updateAccountAttachmentURL="update/account/attachment/url"
			updateAccountURL="edit/account/url"
			updateInstructionsURL="update/instructions/url"
			updateLanguageIdURL="update/language/id/url"
		/>
	);
}

describe('SupportInformation', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSupportInformation();

		expect(container).toBeTruthy();
	});

	it('displays Details section', () => {
		const {getByText} = renderSupportInformation();

		getByText('details');
	});

	it('displays Instructions section', () => {
		const {getByText} = renderSupportInformation();

		getByText('support-instructions');
	});
});

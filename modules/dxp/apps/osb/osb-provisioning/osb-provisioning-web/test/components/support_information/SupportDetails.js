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

import SupportDetails from '../../../src/main/resources/META-INF/resources/js/components/support_information/SupportDetails';

function renderSupportDetails() {
	return render(
		<SupportDetails
			account={{
				code: '123',
				editAccountURL: 'edit/account/url',
				key: '123',
				name: 'Test Account',
				region: 'United States',
				status: 'Active',
				tier: 'Regular'
			}}
			language={{id: 'en_US', name: 'English'}}
			languageList={[
				{id: 'en_US', name: 'English'},
				{id: 'zh_CN', name: 'Chinese'},
				{id: 'es_ES', name: 'Spanish'}
			]}
			regionNames={['United States', 'China', 'Spain']}
			updateAccountURL="edit/account/url"
			updateLanguageIdURL="update/language/id/url"
		/>
	);
}

describe('SupportDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSupportDetails();

		expect(container).toBeTruthy();
	});

	it('displays Details header', () => {
		const {getByText} = renderSupportDetails();

		getByText('details');
	});

	it('displays Support region title', () => {
		const {getByText} = renderSupportDetails();

		getByText('support-region');
	});

	it('displays Support language title', () => {
		const {getByText} = renderSupportDetails();

		getByText('support-language');
	});

	it('displays region options when the user clicks on the Region field', () => {
		const {getByText} = renderSupportDetails();

		fireEvent.click(getByText('United States'));

		getByText('China');
		getByText('Spain');
	});

	it('displays language options when the user clicks on the Language field', () => {
		const {getByText} = renderSupportDetails();

		fireEvent.click(getByText('English'));

		getByText('Chinese');
		getByText('Spanish');
	});
});

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

import Search from '../../../../src/main/resources/META-INF/resources/js/components/search/account/Search';

function renderSearch() {
	return render(
		<Search
			accountsHomeURL="/accounts/home/URL"
			activeSLANames={[]}
			countryNames={[]}
			regionNames={[]}
			resourceURL="/resource/URL"
			selectAccountURL="/select/account/url"
			selectFirstLineSupportURL="/select/fls/url"
			selectPartnerURL="/select/partner/url"
			subscriptionStateNames={[]}
			tierNames={[]}
		/>
	);
}

describe('Account Search', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSearch();

		expect(container).toBeTruthy();
	});

	it('displays a search input', () => {
		const {getByPlaceholderText} = renderSearch();

		getByPlaceholderText('search-accounts');
	});

	it('displays a search icon', () => {
		const {getByLabelText} = renderSearch();

		getByLabelText('search-icon');
	});

	it('displays a caret to trigger Advanced Search', () => {
		const {getByLabelText} = renderSearch();

		getByLabelText('advanced-search-icon');
	});

	it('disables autocomplete when Advanced Search is expanded', () => {
		const {getByLabelText, getByPlaceholderText} = renderSearch();

		fireEvent.click(getByLabelText('advanced-search-icon'));

		expect(getByPlaceholderText('search-accounts').disabled).toBe(true);
	});

	it('opens the Advanced Search when the caret is clicked', () => {
		const {getByLabelText} = renderSearch();

		fireEvent.click(getByLabelText('advanced-search-icon'));

		const advancedSearchToggler = getByLabelText('close-advanced-search');

		expect(
			advancedSearchToggler.getAttribute('aria-expanded')
		).toBeTruthy();
	});
});

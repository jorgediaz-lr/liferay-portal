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

import times from 'lodash.times';
import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import {default as AccountSearch} from '../../../src/main/resources/META-INF/resources/js/components/search/account/Search';
import {default as LicenseSearch} from '../../../src/main/resources/META-INF/resources/js/components/search/license/Search';

function renderAccountSearch() {
	return render(
		<AccountSearch
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

const dummyData = index => {
	return {
		label: `name ${index}`,
		value: `value${index}`
	};
};

function renderLicenseSearch() {
	return render(
		<LicenseSearch
			licenseHomeURL="/license/home/URL"
			licenseTypes={times(Math.random() * 100, dummyData)}
			productNames={times(Math.random() * 100, dummyData)}
			productVersions={times(Math.random() * 100, dummyData)}
		/>
	);
}

describe('Search', () => {
	afterEach(cleanup);

	describe('Account Search', () => {
		it('renders', () => {
			const {container} = renderAccountSearch();

			expect(container).toBeTruthy();
		});

		it('displays a search input', () => {
			const {getByPlaceholderText} = renderAccountSearch();

			getByPlaceholderText('search-accounts');
		});

		it('displays a search icon', () => {
			const {getByLabelText} = renderAccountSearch();

			getByLabelText('search-icon');
		});

		it('displays a caret to trigger Advanced Search', () => {
			const {getByLabelText} = renderAccountSearch();

			getByLabelText('advanced-search-icon');
		});

		it('disables autocomplete when Advanced Search is expanded', () => {
			const {
				getByLabelText,
				getByPlaceholderText
			} = renderAccountSearch();

			fireEvent.click(getByLabelText('advanced-search-icon'));

			expect(getByPlaceholderText('search-accounts').disabled).toBe(true);
		});

		it('opens the Advanced Search when the caret is clicked', () => {
			const {getByLabelText} = renderAccountSearch();

			fireEvent.click(getByLabelText('advanced-search-icon'));

			const advancedSearchToggler = getByLabelText(
				'close-advanced-search'
			);

			expect(
				advancedSearchToggler.getAttribute('aria-expanded')
			).toBeTruthy();
		});
	});

	describe('License Search', () => {
		it('renders', () => {
			const {container} = renderLicenseSearch();

			expect(container).toBeTruthy();
		});

		it('displays a search input', () => {
			const {getByPlaceholderText} = renderLicenseSearch();

			getByPlaceholderText('search-licenses');
		});

		it('displays a search icon', () => {
			const {getByLabelText} = renderLicenseSearch();

			getByLabelText('search-icon');
		});

		it('displays a caret to trigger Advanced Search', () => {
			const {getByLabelText} = renderLicenseSearch();

			getByLabelText('advanced-search-icon');
		});

		it('disables keywords search when Advanced Search is expanded', () => {
			const {
				getByLabelText,
				getByPlaceholderText
			} = renderLicenseSearch();

			fireEvent.click(getByLabelText('advanced-search-icon'));

			expect(getByPlaceholderText('search-licenses').disabled).toBe(true);
		});

		it('opens the Advanced Search when the caret is clicked', () => {
			const {getByLabelText} = renderLicenseSearch();

			fireEvent.click(getByLabelText('advanced-search-icon'));

			const advancedSearchToggler = getByLabelText(
				'close-advanced-search'
			);

			expect(
				advancedSearchToggler.getAttribute('aria-expanded')
			).toBeTruthy();
		});
	});
});

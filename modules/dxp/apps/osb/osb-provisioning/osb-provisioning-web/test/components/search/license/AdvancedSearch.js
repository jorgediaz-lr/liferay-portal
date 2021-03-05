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

import AdvancedSearch from '../../../../src/main/resources/META-INF/resources/js/components/search/license/AdvancedSearch';

function renderAdvancedSearch(props) {
	return render(
		<AdvancedSearch
			clickOutsideCallback={jest.fn()}
			formAction="/url"
			licenseTypes={[{label: 'type1', value: 'l-1'}]}
			products={[
				{label: 'name1', value: 'n-1'},
				{label: 'name2', value: 'n-2'}
			]}
			productVersions={[{label: 'version1', value: 'v-1'}]}
			{...props}
		/>
	);
}

describe('License AdvancedSearch', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAdvancedSearch();

		expect(container).toBeTruthy();
	});

	it('displays a Clear button and a Search button', () => {
		const {getByText} = renderAdvancedSearch();

		getByText('clear');
		getByText('search');
	});

	it('displays a set of match results radio buttons with the options of Any or All', () => {
		const {getByText} = renderAdvancedSearch();

		getByText('match:');
		getByText('any');
		getByText('all');
	});

	it('displays the All match results option as checked by default', () => {
		const {getByLabelText} = renderAdvancedSearch();

		expect(getByLabelText('all').checked).toBeTruthy();

		fireEvent.click(getByLabelText('any'));

		expect(getByLabelText('any').checked).toBeTruthy();
		expect(getByLabelText('all').checked).toBeFalsy();
	});

	it('displays a General Details section, a License Type section, a Product section, a Product Version section, and a Dates section if all props are supplied', () => {
		const {getByText} = renderAdvancedSearch();

		getByText('general-details');
		getByText('license-type');
		getByText('product');
		getByText('product-version');
		getByText('dates');
	});
});

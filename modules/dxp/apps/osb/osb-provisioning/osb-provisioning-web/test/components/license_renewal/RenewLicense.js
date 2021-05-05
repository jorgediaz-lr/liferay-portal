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

import RenewLicense from '../../../src/main/resources/META-INF/resources/js/components/license_renewal/RenewLicense';

function renderRenewLicense() {
	return render(
		<RenewLicense expirationDate="2022-04-14" startDate="2021-04-14" />
	);
}

describe('RenewLicense', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderRenewLicense();

		expect(container).toBeTruthy();
	});

	it('displays a Renew button initially', () => {
		const {getByText} = renderRenewLicense();

		getByText('renew');
	});

	it('displays a renewal modal after the Renew button is clicked', async () => {
		const {getByText, queryAllByText} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => expect(queryAllByText('renew').length).toBe(3));
	});

	// Clay Date Picker always displays two inputs for the same date

	it('displays a start date in the renewal modal', async () => {
		const {getByText, queryAllByDisplayValue} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => {
			getByText('start-date');

			expect(queryAllByDisplayValue('2021-04-14').length).toBe(2);
		});
	});

	it('displays an expiration date in the renewal modal', async () => {
		const {getByText, queryAllByDisplayValue} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => {
			getByText('expiration-date');

			expect(queryAllByDisplayValue('2022-04-14').length).toBe(2);
		});
	});

	it('disables the Renew button initially', async () => {
		const {getByText, queryAllByText} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() =>
			expect(queryAllByText('renew')[2].disabled).toBe(true)
		);
	});

	it('reenables the Renew button after a date has been updated', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2020-04-15'}
			});

			expect(queryAllByText('renew')[2].disabled).toBe(false);
		});
	});

	it('keeps the Renew button disabled if a date is modified but left empty', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: ''}
			});

			expect(queryAllByText('renew')[2].disabled).toBe(true);
		});
	});

	it('keeps the Renew button disabled if an invalid date is entered', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-04-32'}
			});

			expect(queryAllByText('renew')[2].disabled).toBe(true);
		});
	});

	it('keeps the Renew button disabled until the input date matches the MDYDateFormat', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2021-04'}
			});

			expect(queryAllByText('renew')[2].disabled).toBe(true);
		});
	});

	it('keeps the Renew button disabled until the input Start Date is later than the Expiration Date', async () => {
		const {
			getAllByPlaceholderText,
			getByText,
			queryAllByText
		} = renderRenewLicense();

		fireEvent.click(getByText('renew'));

		return await wait(() => {
			fireEvent.change(getAllByPlaceholderText('YYYY-MM-DD')[0], {
				target: {value: '2022-04-15'}
			});

			expect(queryAllByText('renew')[2].disabled).toBe(true);
		});
	});
});

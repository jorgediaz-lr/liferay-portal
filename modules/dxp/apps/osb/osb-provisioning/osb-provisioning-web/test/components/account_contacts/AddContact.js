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

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import AddContact from '../../../src/main/resources/META-INF/resources/js/components/account_contacts/AddContact';

function renderAddContact(props) {
	const allRoles = [
		{key: 'KEY-100', name: 'Manager'},
		{key: 'KEY-101', name: 'Member'},
		{key: 'KEY-102', name: 'Analyst'},
		{key: 'KEY-103', name: 'Designer'}
	];

	return render(
		<AddContact
			accountName={'Test Account'}
			allRoles={allRoles}
			redirect="/"
			{...props}
		/>
	);
}

describe('AccountAddress', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAddContact();

		expect(container).toBeTruthy();
	});

	it('displays email, roles, and account name headers', () => {
		const {getByText} = renderAddContact({});

		getByText('email');
		getByText('roles');
		getByText('account');
	});

	it('displays full name if name, email, and roles are provided', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100'],
			emailAddress: 'test1@liferay.com',
			fullName: 'Test One'
		});

		getByText('name');
		getByText('Test One');
	});

	it('displays contact roles if name, email, and roles are provided', () => {
		const {container} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com',
			fullName: 'Test One'
		});

		const {getByText} = within(
			container.querySelector('.input-group-item')
		);

		getByText('Manager');
		getByText('Member');
	});

	it('does not display full name if email and roles are not provided', () => {
		const {queryByText} = renderAddContact({
			fullName: 'Test One'
		});

		expect(queryByText('name')).toBeFalsy();
		expect(queryByText('Test One')).toBeFalsy();
	});

	it('disables save if email is blank', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: ''
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables save if no contact roles are selected', () => {
		const {getByText} = renderAddContact({
			currentRoles: [],
			emailAddress: 'test1@liferay.com'
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('enables save if email and contact roles are populated', () => {
		const {getByText} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com',
			fullName: 'Test One'
		});

		expect(getByText('save').disabled).toBeFalsy();
	});

	it('adds contact roles when selected from dropdown', () => {
		const {container, getByText, getByTitle} = renderAddContact();

		fireEvent.click(getByTitle('add-roles'));

		fireEvent.click(getByText('Manager'));

		expect(
			within(container.querySelector('.input-group-item')).queryByText(
				'Manager'
			)
		).toBeTruthy();
	});

	it('removes contact roles when clicked on close', () => {
		const {container, queryAllByTitle} = renderAddContact({
			currentRoles: ['KEY-100', 'KEY-101'],
			emailAddress: 'test1@liferay.com',
			fullName: 'Test One'
		});

		fireEvent.click(queryAllByTitle('delete')[0]);

		expect(
			within(container.querySelector('.input-group-item')).queryByText(
				'Manager'
			)
		).toBeFalsy();
	});
});

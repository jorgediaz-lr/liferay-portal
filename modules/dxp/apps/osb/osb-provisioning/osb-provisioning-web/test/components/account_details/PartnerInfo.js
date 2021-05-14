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

import PartnerInfo from '../../../src/main/resources/META-INF/resources/js/components/account_details/PartnerInfo';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';

function renderPartnerInfo(permission = true) {
	return render(
		<PermissionsProvider permissions={{updatePermission: permission}}>
			<PartnerInfo
				details={{
					firstLineSupportTeamName: 'Test First Line Support Team',
					key: '123',
					partnerTeamName: 'Test Partner Team'
				}}
				editFirstLineSupportTeamURL="/edit/first/line/support/team/url"
				editPartnerTeamURL="/edit/partner/team/url"
			/>
		</PermissionsProvider>
	);
}

describe('Partner Info', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderPartnerInfo();

		expect(container).toBeTruthy();
	});

	it('shows Partner Team name', () => {
		const {getByText} = renderPartnerInfo();

		getByText('Test Partner Team');
	});

	it('shows First Line Support Team name', () => {
		const {getByText} = renderPartnerInfo();

		getByText('Test First Line Support Team');
	});

	describe('Partner Info with full editing privilege', () => {
		it('allows the Partner Team field to be edited', () => {
			const {getByText} = renderPartnerInfo();

			fireEvent.click(getByText('Test Partner Team'));

			getByText('save');
			getByText('cancel');
		});

		it('allows the First Line Support Team field to be edited', () => {
			const {getByText} = renderPartnerInfo();

			fireEvent.click(getByText('Test First Line Support Team'));

			getByText('save');
			getByText('cancel');
		});
	});

	describe('Partner Info with limited editing privilege', () => {
		it('allows the Partner Team field to be edited', () => {
			const {getByText, queryByText} = renderPartnerInfo(false);

			fireEvent.click(getByText('Test Partner Team'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});

		it('allows the First Line Support Team field to be edited', () => {
			const {getByText, queryByText} = renderPartnerInfo(false);

			fireEvent.click(getByText('Test First Line Support Team'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});
});

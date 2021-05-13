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

import Instructions from '../../../src/main/resources/META-INF/resources/js/components/support_information/Instructions';
import {PermissionsProvider} from '../../../src/main/resources/META-INF/resources/js/hooks/permissions';
import {DASH} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderInstructions(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: true}}>
			<Instructions
				accountAttachmentURL="account/attachment/url"
				accountKey="123"
				fileName="OEM instruction file"
				instructions="Sample support instructions text"
				updateAccountAttachmentURL="update/account/attachment/URL"
				updateInstructionsURL="update/instructions/url"
				{...props}
			/>
		</PermissionsProvider>
	);
}

function renderInstructionsWithoutPermission(props) {
	return render(
		<PermissionsProvider permissions={{updatePermission: false}}>
			<Instructions
				accountAttachmentURL="account/attachment/url"
				accountKey="123"
				fileName="OEM instruction file"
				instructions="Sample support instructions text"
				updateAccountAttachmentURL="update/account/attachment/URL"
				updateInstructionsURL="update/instructions/url"
				{...props}
			/>
		</PermissionsProvider>
	);
}

describe('Instructions', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderInstructions();

		expect(container).toBeTruthy();
	});

	it('displays Instructions title', () => {
		const {getByText} = renderInstructions();

		getByText('oem-instructions');
		getByText('support-instructions');
	});

	it('displays Support Instructions text', () => {
		const {getByText} = renderInstructions();

		getByText('Sample support instructions text');
	});

	it('shows OEM instructions file when one is provided', () => {
		const {getByText} = renderInstructions();

		getByText('OEM instruction file');
	});

	it('shows a message when support project does not exist', () => {
		// This happens when there is no support project on the Customer system connected to the current account

		const {getByText} = renderInstructions({
			updateAccountAttachmentURL: '',
			updateInstructionsURL: ''
		});

		getByText('support-project-does-not-exist');
	});

	describe('Instructions with full editing privilege', () => {
		it('shows Support Instructions as editable when clicked on', () => {
			const {getByText} = renderInstructions();

			fireEvent.click(getByText('Sample support instructions text'));

			getByText('save');
			getByText('cancel');
		});

		it('shows no OEM instructions file when one is not provided', () => {
			const {container, queryByText} = renderInstructions({fileName: ''});

			expect(container.querySelector('a')).toBe(null);
			expect(queryByText(DASH)).toBeFalsy();
		});

		it('shows a file selection for OEM instructions', () => {
			const {container} = renderInstructions();

			expect(
				container.querySelector('input[type = "file"]')
			).toBeTruthy();
		});
	});

	describe('Instructions with no editing privilege', () => {
		it('prevents Support Instructions from being edited', () => {
			const {
				getByText,
				queryByText
			} = renderInstructionsWithoutPermission();

			fireEvent.click(getByText('Sample support instructions text'));

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});

		it('shows a dash when no OEM instructions file is provided', () => {
			const {queryByText} = renderInstructionsWithoutPermission({
				fileName: ''
			});

			expect(queryByText(DASH)).toBeTruthy();
		});

		it('does not show a file selection for OEM instructions', () => {
			const {container} = renderInstructionsWithoutPermission();

			expect(container.querySelector('input[type = "file"]')).toBeFalsy();
		});
	});
});

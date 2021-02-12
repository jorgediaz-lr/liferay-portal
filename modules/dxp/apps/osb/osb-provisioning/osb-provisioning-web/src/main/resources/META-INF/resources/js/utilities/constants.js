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

export const BINARY_SELECTION = [
	{
		label: Liferay.Language.get('yes'),
		value: true
	},
	{
		label: Liferay.Language.get('no'),
		value: false
	}
];
export const NAMESPACE = window.ProvisioningConstants.namespace;

// Note properties

export const NOTE_FORMAT_HTML = window.ProvisioningConstants.noteFormat.html;
export const NOTE_FORMAT_PLAIN =
	window.ProvisioningConstants.noteFormat.plaintext;
export const NOTE_PRIORITY_PINNED = 1;
export const NOTE_PRIORITY_UNPINNED = 2;
export const NOTE_STATUS_APPROVED =
	window.ProvisioningConstants.noteStatus.approved;
export const NOTE_STATUS_ARCHIVED =
	window.ProvisioningConstants.noteStatus.archived;
export const NOTE_TYPE_GENERAL = window.ProvisioningConstants.noteType.general;
export const NOTE_TYPE_SALES = window.ProvisioningConstants.noteType.sales;

// Action types for interacting with a Note

export const ADD_NOTE = 'addNote';
export const ARCHIVE_NOTE = 'archiveNote';
export const EDIT_NOTE = 'editNote';
export const PIN_NOTE = 'pinNote';

// Inline edit field types

export const FIELD_SIZE_DEFAULT = 'default';
export const FIELD_SIZE_SMALL = 'small';

export const FIELD_TYPE_EXTERNAL = 'external';
export const FIELD_TYPE_NONEDITABLE = 'noneditable';
export const FIELD_TYPE_SELECT = 'select';
export const FIELD_TYPE_TEXT = 'text';
export const FIELD_TYPE_TEXTAREA = 'textarea';
export const FIELD_TYPE_TOGGLE = 'toggle';

// Product Purchase status

export const ADD_SUBSCRIPTIONS = 'add';
export const EDIT_SUBSCRIPTIONS = 'edit';
export const PRODUCT_PURCHASE_STATUS_APPROVED = 'Approved';
export const PRODUCT_PURCHASE_STATUS_CANCELLED = 'Cancelled';

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {UPDATE_FRAGMENT_ENTRY_LINK_SEARCH_OPTIONS} from './actions.es';
import {
	disableSavingChangesStatusAction,
	enableSavingChangesStatusAction,
	updateLastSaveDateAction
} from './saveChanges.es';
import {updatePageEditorLayoutDataAction} from './updatePageEditorLayoutData.es';

function updateFragmentSearchOptions(fragmentEntryLinkId, nonIndexable) {
	return function(dispatch) {
		dispatch(enableSavingChangesStatusAction());

		dispatch(
			updateFragmentSearchOptionsConfig(fragmentEntryLinkId, nonIndexable)
		);

		dispatch(updatePageEditorLayoutDataAction());

		dispatch(updateLastSaveDateAction());
		dispatch(disableSavingChangesStatusAction());
	};
}

function updateFragmentSearchOptionsConfig(fragmentEntryLinkId, nonIndexable) {
	return {
		fragmentEntryLinkId,
		nonIndexable,
		type: UPDATE_FRAGMENT_ENTRY_LINK_SEARCH_OPTIONS
	};
}

export {updateFragmentSearchOptions};

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

import {OrderedMap, Record} from 'immutable';
import React, {useContext, useState} from 'react';

import {
	NOTE_FORMAT_PLAIN,
	NOTE_STATUS_APPROVED,
	NOTE_TYPE_GENERAL
} from '../utilities/constants';

// Notes definition with default values

export const NoteRecord = Record({
	content: '',
	createDate: null,
	creatorName: '-',
	creatorPortraitURL: null,
	edited: false,
	format: NOTE_FORMAT_PLAIN,
	htmlContent: '',
	id: null,
	pinned: false,
	status: NOTE_STATUS_APPROVED,
	type: NOTE_TYPE_GENERAL,
	updateURL: null
});

const NotesContext = React.createContext();

function createNote(note) {
	const {
		content,
		createDate,
		creatorName,
		creatorPortraitURL,
		edited,
		format,
		htmlContent,
		key,
		pinned,
		status,
		type,
		updateURL
	} = note;

	return [
		key,
		NoteRecord({
			content,
			createDate,
			creatorName,
			creatorPortraitURL,
			edited,
			format,
			htmlContent,
			id: key,
			pinned,
			status,
			type,
			updateURL
		})
	];
}

export function NotesProvider({initialNotes = [], children}) {
	const processedNotes = initialNotes.map(note => createNote(note));
	const [notes, setNotes] = useState(OrderedMap(processedNotes));

	return (
		<NotesContext.Provider
			value={[
				notes,
				{
					addNote(note) {
						setNotes(OrderedMap([createNote(note)]).merge(notes));
					},
					archiveNote(id, status) {
						setNotes(notes.setIn([id, 'status'], status));
					},
					editNote(id, content, htmlContent, edited) {
						setNotes(
							notes
								.setIn([id, 'content'], content)
								.setIn([id, 'htmlContent'], htmlContent)
								.setIn([id, 'edited'], edited)
						);
					},
					pinNote(id, pinned) {
						setNotes(notes.setIn([id, 'pinned'], pinned));
					}
				}
			]}
		>
			{children}
		</NotesContext.Provider>
	);
}

export function useNotes() {
	return useContext(NotesContext);
}

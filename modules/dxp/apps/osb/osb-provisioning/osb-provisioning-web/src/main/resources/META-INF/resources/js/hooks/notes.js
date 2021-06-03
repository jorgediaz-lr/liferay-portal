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

export function NotesProvider({initialNotes = [], children}) {
	const processedNotes = initialNotes.map(note => [
		note.key,
		NoteRecord({
			content: note.content,
			createDate: note.createDate,
			creatorName: note.creatorName,
			creatorPortraitURL: note.creatorPortraitURL,
			edited: note.edited,
			format: note.format,
			htmlContent: note.htmlContent,
			id: note.key,
			pinned: note.pinned,
			status: note.status,
			type: note.type,
			updateURL: note.updateNoteURL
		})
	]);

	const [notes, setNotes] = useState(OrderedMap(processedNotes));

	// Actions that can be performed on a Note

	return (
		<NotesContext.Provider
			value={[
				notes,
				{
					addNote(note) {
						setNotes(notes.set(note.id, note));
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

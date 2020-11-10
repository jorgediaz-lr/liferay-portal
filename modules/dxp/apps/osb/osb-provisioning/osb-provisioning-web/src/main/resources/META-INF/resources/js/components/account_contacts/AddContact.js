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

import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import ContactEntry from './ContactEntry';

export default function AddContact({
	accountName,
	allRoles = [],
	currentRoles = [],
	emailAddress,
	fullName,
	redirect
}) {
	const [contactEmailAddress, setContactEmailAddress] = useState(
		emailAddress
	);
	const [newRoles, setNewRoles] = useState(currentRoles);

	const knownContact = !!(
		currentRoles.length !== 0 &&
		emailAddress &&
		fullName
	);

	function handleAdd(key) {
		if (!newRoles.includes(key)) {
			setNewRoles([...newRoles, key]);
		}
	}

	function handleRemove(key) {
		setNewRoles(newRoles.filter(item => !item.match(key)));
	}

	return (
		<>
			<input
				name={`${NAMESPACE}addContactRoleKeys`}
				type="hidden"
				value={newRoles.join(',')}
			/>
			<input
				name={`${NAMESPACE}deleteContactRoleKeys`}
				type="hidden"
				value={allRoles
					.map(item => item.key)
					.filter(key => !newRoles.includes(key))
					.join(',')}
			/>

			<table className="table table-autofit table-list table-nowrap">
				<thead>
					<tr>
						{knownContact && (
							<th className="table-cell-expand">
								<span className="text-truncate-inline">
									<span className="text-secondary text-truncate">
										{Liferay.Language.get('name')}
									</span>
								</span>
							</th>
						)}
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('email')}
									{!knownContact && (
										<span className="text-warning">
											{'*'}
										</span>
									)}
								</span>
							</span>
						</th>
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('roles')}
									<span className="text-warning">{'*'}</span>
								</span>
							</span>
						</th>
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('account')}
								</span>
							</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<ContactEntry
						accountName={accountName}
						addFn={handleAdd}
						allRoles={allRoles}
						contactFullName={fullName}
						emailAddress={contactEmailAddress}
						knownContact={knownContact}
						newRoles={newRoles}
						removeFn={handleRemove}
						setEmailAddress={setContactEmailAddress}
					/>
				</tbody>
			</table>

			<div className="button-holder button-holder-lg" role="group">
				<button
					className="btn btn-primary save-btn"
					disabled={!(newRoles.length > 0 && contactEmailAddress)}
					role="button"
					type="submit"
				>
					{Liferay.Language.get('save')}
				</button>

				<a className="btn btn-secondary" href={redirect}>
					{Liferay.Language.get('cancel')}
				</a>
			</div>
		</>
	);
}

AddContact.propTypes = {
	accountName: PropTypes.string,
	allRoles: PropTypes.arrayOf(
		PropTypes.shape({
			key: PropTypes.string,
			name: PropTypes.string
		})
	),
	currentRoles: PropTypes.arrayOf(PropTypes.string),
	emailAddress: PropTypes.string,
	fullName: PropTypes.string,
	redirect: PropTypes.string
};

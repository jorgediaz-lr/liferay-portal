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

import ClayDropDown from '@clayui/drop-down';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';

export default function ContactLine({
	accountName,
	addContactRoleKeys,
	addKey,
	allContactRoles,
	emailAddress,
	knownContact,
	removeKey,
	setEmailAddress,
	userFullName
}) {
	function handleEmailChange(event) {
		setEmailAddress(event.currentTarget.value);
	}

	return (
		<tr className="contact-line-entry">
			{knownContact && (
				<td className="table-cell-expand">
					<span className="text-truncate-inline">
						<span className="semi-bold text-truncate">
							{userFullName}
						</span>
					</span>
				</td>
			)}
			<td className="table-cell-expand">
				{knownContact && (
					<span className="text-truncate-inline">
						<span className="text-truncate">{emailAddress}</span>
					</span>
				)}
				<input
					className="form-control"
					name={`${NAMESPACE}emailAddress`}
					onChange={handleEmailChange}
					type={knownContact ? 'hidden' : 'text'}
					value={emailAddress}
				/>
			</td>
			<td className="table-cell-expand">
				<ContactRoleSelect
					addContactRoleKeys={addContactRoleKeys}
					addKey={addKey}
					allContactRoles={allContactRoles}
					removeKey={removeKey}
				/>
			</td>
			<td className="table-cell-expand">
				<span className="text-truncate-inline">
					<span className="text-truncate">{accountName}</span>
				</span>
			</td>
		</tr>
	);
}

ContactLine.propTypes = {
	accountName: PropTypes.string,
	addContactRoleKeys: PropTypes.arrayOf(PropTypes.string),
	addKey: PropTypes.func,
	allContactRoles: PropTypes.arrayOf(
		PropTypes.shape({
			key: PropTypes.string,
			name: PropTypes.string
		})
	),
	emailAddress: PropTypes.string,
	knownContact: PropTypes.bool,
	removeKey: PropTypes.func,
	setEmailAddress: PropTypes.func,
	userFullName: PropTypes.string
};

function ContactRoleSelect({
	addContactRoleKeys = [],
	addKey,
	allContactRoles = [],
	removeKey
}) {
	const [active, setActive] = useState(false);

	const processedAllContactRoles = allContactRoles.reduce(
		(allRoles, role) => {
			return {...allRoles, [role.key]: role};
		},
		{}
	);

	const triggerElement = (
		<div className="input-group input-group-stacked-sm-down">
			<div className={`input-group-item ${active ? 'input-focus' : ''}`}>
				<div className="form-control form-control-tag-group input-group-inset input-group-inset-after">
					{addContactRoleKeys.map(
						roleKey =>
							processedAllContactRoles[roleKey] && (
								<ContactRoleLabel
									key={roleKey}
									name={
										processedAllContactRoles[roleKey][
											'name'
										]
									}
									removeRole={event => {
										// Stops the click event on the label's close button from propagating up and triggering the dropdown.

										event.stopPropagation();

										removeKey(roleKey);
									}}
								/>
							)
					)}
				</div>

				<div className="input-group-inset-item input-group-inset-item-after">
					<button
						className="btn btn-unstyled"
						onClick={event => {
							event.preventDefault();
							setActive(!active);
						}}
						tabIndex="0"
						title={Liferay.Language.get('add-roles')}
					>
						<svg
							aria-label={Liferay.Language.get('select')}
							className="lexicon-icon lexicon-icon-caret-double"
							role="img"
						>
							<use xlinkHref="#caret-double" />
						</svg>
					</button>
				</div>
			</div>
		</div>
	);

	return (
		<ClayDropDown
			active={active}
			onActiveChange={setActive}
			trigger={triggerElement}
		>
			<ClayDropDown.ItemList className="roles-dropdown">
				<ClayDropDown.Group>
					{allContactRoles.map(role => (
						<ClayDropDown.Item
							key={role.key}
							onClick={() => addKey(role.key)}
						>
							{role.name}
						</ClayDropDown.Item>
					))}
				</ClayDropDown.Group>
			</ClayDropDown.ItemList>
		</ClayDropDown>
	);
}

ContactRoleSelect.propTypes = {
	addContactRoleKeys: PropTypes.arrayOf(PropTypes.string),
	addKey: PropTypes.func.isRequired,
	allContactRoles: PropTypes.arrayOf(
		PropTypes.shape({
			key: PropTypes.string,
			name: PropTypes.string
		})
	),
	removeKey: PropTypes.func.isRequired
};

function ContactRoleLabel({name, removeRole}) {
	return (
		<span className="label label-lg label-secondary">
			<span className="label-item label-item-expand">{name}</span>
			<span className="label-item label-item-after">
				<button
					className="close"
					onClick={removeRole}
					tabIndex="0"
					title={Liferay.Language.get('delete')}
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('close')}
						className="lexicon-icon lexicon-icon-times reference-mark"
						role="img"
					>
						<use xlinkHref="#times" />
					</svg>
				</button>
			</span>
		</span>
	);
}

ContactRoleLabel.propTypes = {
	name: PropTypes.string.isRequired,
	removeRole: PropTypes.func.isRequired
};

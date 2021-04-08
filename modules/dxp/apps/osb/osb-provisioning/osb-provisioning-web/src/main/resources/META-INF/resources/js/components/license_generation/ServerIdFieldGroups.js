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

import {List} from 'immutable';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import IconButton from '../IconButton';

function ServerIdFieldGroups() {
	const initialValue = {hostName: '', ipAddresses: '', macAddresses: ''};

	const [groups, setGroups] = useState(List.of(initialValue));

	function updateGroups(id, fieldValues = null) {
		let newGroup;

		if (fieldValues !== null) {
			newGroup = groups.set(id, fieldValues);
		}
		else {
			newGroup = groups.get(id)
				? groups.delete(id)
				: groups.insert(id, initialValue);
		}

		setGroups(newGroup);
	}

	return (
		<div className="col-md-12 form-group">
			<h4>{Liferay.Language.get('server-id-fields')}</h4>

			<div className="server-id-field-groups">
				{groups.map((group, index) => (
					<FieldGroup
						group={group}
						id={index}
						key={index}
						showDelete={groups.size > 1}
						updateFn={updateGroups}
					/>
				))}
			</div>
		</div>
	);
}

function FieldGroup({group, id, showDelete = false, updateFn}) {
	function handleHostNameChange(event) {
		updateFn(id, {...group, hostName: event.currentTarget.value});
	}

	function handleIpAddressChange(event) {
		updateFn(id, {
			...group,
			ipAddresses: event.currentTarget.value
		});
	}

	function handleMacAddressChange(event) {
		updateFn(id, {
			...group,
			macAddresses: event.currentTarget.value
		});
	}

	return (
		<div className="server-id-field">
			<div className="col-md-12 form-group">
				<label
					className="form-control-label"
					htmlFor={`hostName-${id}`}
				>
					{Liferay.Language.get('host-name')}
				</label>

				<input
					className="form-control"
					id={`hostName-${id}`}
					onChange={handleHostNameChange}
					type="text"
					value={group.hostName}
				/>
			</div>

			<div className="col-md-12 form-group">
				<label
					className="form-control-label"
					htmlFor={`ipAddresses-${id}`}
				>
					{Liferay.Language.get('ip-addresses')}
				</label>
				<textarea
					className="form-control"
					id={`ipAddresses-${id}`}
					onChange={handleIpAddressChange}
					rows={2}
					value={group.ipAddresses}
				/>
			</div>

			<div className="col-md-12 form-group">
				<label
					className="form-control-label"
					htmlFor={`macAddresses-${id}`}
				>
					{Liferay.Language.get('mac-addresses')}
				</label>
				<textarea
					className="form-control"
					id={`macAddresses-${id}`}
					onChange={handleMacAddressChange}
					rows={2}
					value={group.macAddresses}
				/>
			</div>

			<div className="btn-group col-md-12" role="group">
				<div className="btn-group-item">
					<IconButton
						cssClass="add-fields btn-secondary nav-btn nav-btn-monospaced"
						labelName={Liferay.Language.get('add')}
						onClick={() => {
							updateFn(id + 1);
						}}
						svgId="#plus"
						title={Liferay.Language.get('add')}
					/>
				</div>

				{showDelete && (
					<div className="btn-group-item">
						<IconButton
							cssClass="btn-secondary delete-fields nav-btn nav-btn-monospaced"
							labelName={Liferay.Language.get('delete')}
							onClick={() => {
								updateFn(id);
							}}
							svgId="#hr"
							title={Liferay.Language.get('delete')}
						/>
					</div>
				)}
			</div>
		</div>
	);
}

FieldGroup.propTypes = {
	group: PropTypes.shape({
		hostName: PropTypes.string,
		ipAddresses: PropTypes.string,
		macAddresses: PropTypes.string
	}),
	id: PropTypes.number.isRequired,

	showDelete: PropTypes.bool,
	updateFn: PropTypes.func.isRequired
};

export default ServerIdFieldGroups;

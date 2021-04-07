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

import React from 'react';

import IconButton from '../IconButton';

function ServerIdFields() {
	return (
		<div className="col-md-12 form-group">
			<h4>{Liferay.Language.get('server-id-fields')}</h4>

			<Field />
		</div>
	);
}

function Field() {
	function handleHostNameChange(event) {
		console.log(event.currentTarget.value);
	}

	function handleIpAddressChange(event) {
		console.log(event.currentTarget.value);
	}

	function handleMacAddressChange(event) {
		console.log(event.currentTarget.value);
	}

	return (
		<div className="server-id-field">
			<div className="col-md-12 form-group">
				<label className="form-control-label" htmlFor="hostName">
					{Liferay.Language.get('host-name')}
				</label>

				<input
					className="form-control"
					id="hostName"
					onChange={handleHostNameChange}
					type="text"
				/>
			</div>

			<div className="col-md-12 form-group">
				<label className="form-control-label" htmlFor="ipAddress">
					{Liferay.Language.get('ip-addresses')}
				</label>
				<textarea
					className="form-control"
					id="ipAddress"
					onChange={handleIpAddressChange}
					rows={2}
					value=""
				/>
			</div>

			<div className="col-md-12 form-group">
				<label className="form-control-label" htmlFor="macAddress">
					{Liferay.Language.get('mac-addresses')}
				</label>
				<textarea
					className="form-control"
					id="macAddress"
					onChange={handleMacAddressChange}
					rows={2}
					value=""
				/>
			</div>

			<div className="btn-group col-md-12" role="group">
				<div className="btn-group-item">
					<IconButton
						cssClass="add-fields btn-secondary nav-btn nav-btn-monospaced"
						labelName={Liferay.Language.get('add')}
						onClick={() => {}}
						svgId="#plus"
						title={Liferay.Language.get('add')}
					/>
				</div>

				<div className="btn-group-item">
					<IconButton
						cssClass="btn-secondary delete-fields nav-btn nav-btn-monospaced"
						labelName={Liferay.Language.get('delete')}
						onClick={() => {}}
						svgId="#hr"
						title={Liferay.Language.get('delete')}
					/>
				</div>
			</div>
		</div>
	);
}

export default ServerIdFields;

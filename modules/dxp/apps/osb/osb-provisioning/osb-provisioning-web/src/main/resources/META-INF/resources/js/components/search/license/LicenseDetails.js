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

import {NAMESPACE} from '../../../utilities/constants';

function LicenseDetails() {
	return (
		<div className="panel-body">
			<div className="col-md-6 form-group">
				<label htmlFor="koroneikiAccountKey">
					{Liferay.Language.get('koroneiki-account-key')}
				</label>
				<input
					className="form-control form-control-sm"
					id="koroneikiAccountKey"
					name={`${NAMESPACE}koroneikiAccountKey`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="koroneikiProductPurchaseKey">
					{Liferay.Language.get('koroneiki-product-purchase-key')}
				</label>
				<input
					className="form-control form-control-sm"
					id="koroneikiProductPurchaseKey"
					name={`${NAMESPACE}koroneikiProductPurchaseKey`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="accountName">
					{Liferay.Language.get('account')}
				</label>
				<input
					className="form-control form-control-sm"
					id="accountName"
					name={`${NAMESPACE}accountName`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="owner">{Liferay.Language.get('owner')}</label>
				<input
					className="form-control form-control-sm"
					id="owner"
					name={`${NAMESPACE}owner`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="hostName">
					{Liferay.Language.get('host-name')}
				</label>
				<input
					className="form-control form-control-sm"
					id="hostName"
					name={`${NAMESPACE}hostName`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="ipAddress">
					{Liferay.Language.get('ip-address')}
				</label>
				<input
					className="form-control form-control-sm"
					id="ipAddress"
					name={`${NAMESPACE}ipAddress`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="macAddress">
					{Liferay.Language.get('mac-address')}
				</label>
				<input
					className="form-control form-control-sm"
					id="macAddress"
					name={`${NAMESPACE}macAddress`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="serverId">
					{Liferay.Language.get('server-id')}
				</label>
				<input
					className="form-control form-control-sm"
					id="serverId"
					name={`${NAMESPACE}serverId`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="licenseKey">
					{Liferay.Language.get('key')}
				</label>
				<input
					className="form-control form-control-sm"
					id="licenseKey"
					name={`${NAMESPACE}key`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="creatorEmailAddress">
					{Liferay.Language.get('created-by')}
				</label>
				<input
					className="form-control form-control-sm"
					id="creatorEmailAddress"
					name={`${NAMESPACE}creatorEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-email-address')}
					</div>
				</div>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="editorEmailAddress">
					{Liferay.Language.get('last-edited-by')}
				</label>
				<input
					className="form-control form-control-sm"
					id="editorEmailAddress"
					name={`${NAMESPACE}editorEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-email-address')}
					</div>
				</div>
			</div>
		</div>
	);
}

export default LicenseDetails;

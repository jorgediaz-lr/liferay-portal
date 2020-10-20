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
import React from 'react';

import {NAMESPACE} from '../../utilities/constants';

function Account({countryNames}) {
	return (
		<div className="row">
			<div className="col-md-6">
				<div className="form-group">
					<label htmlFor="name">
						{Liferay.Language.get('account-name')}
					</label>
					<input
						className="form-control form-control-sm"
						id="name"
						name={`${NAMESPACE}name`}
						type="text"
					/>
				</div>

				<div className="form-group-item">
					<label htmlFor="countryName">
						{Liferay.Language.get('country')}
					</label>
					<select
						className="form-control"
						id="countryName"
						name={`${NAMESPACE}countryName`}
					>
						<option></option>
						{countryNames.map(name => (
							<option key={name} value={name}>
								{name}
							</option>
						))}
					</select>
				</div>
			</div>

			<div className="col-md-6">
				<div className="form-group">
					<label htmlFor="code">{Liferay.Language.get('code')}</label>
					<input
						className="form-control form-control-sm"
						id="code"
						name={`${NAMESPACE}code`}
						type="text"
					/>
				</div>
			</div>
		</div>
	);
}

Account.propTypes = {
	countryNames: PropTypes.array.isRequired
};

export default Account;

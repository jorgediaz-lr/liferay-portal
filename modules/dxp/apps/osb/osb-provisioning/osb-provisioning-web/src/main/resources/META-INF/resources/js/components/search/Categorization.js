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

function Categorization({subscriptionStateNames, tierNames}) {
	return (
		<div className="panel-body">
			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('tier')}
				</h5>

				{tierNames.map(tier => (
					<Checkbox key={tier} labelName={tier} />
				))}
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('subscription-status')}
				</h5>

				{subscriptionStateNames.map(subscription => (
					<Checkbox key={subscription} labelName={subscription} />
				))}
			</div>
		</div>
	);
}

Categorization.propTypes = {
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

function Checkbox({labelName}) {
	return (
		<div className="custom-checkbox custom-control">
			<label>
				<input className="custom-control-input" type="checkbox" />
				<span className="custom-control-label">
					<span className="custom-control-label-text">
						{labelName}
					</span>
				</span>
			</label>
		</div>
	);
}

export default Categorization;

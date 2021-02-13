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

import {BINARY_SELECTION} from '../../../utilities/constants';
import CheckboxGroups from '../CheckboxGroups';

function Categorization({
	activeSLANames,
	regionNames,
	subscriptionStateNames,
	tierNames
}) {
	function simplifySLANames(names) {
		return names.map(name => ({
			label: name.replace(' Subscription', ''),
			value: name
		}));
	}

	return (
		<div className="panel-body">
			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('partner')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="partners"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('provides-fls')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="providesFLS"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('receives-fls')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="receivesFLS"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('internal')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="internals"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('tier')}
				</h5>

				<CheckboxGroups fieldValues={tierNames} inputName="tiers" />
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('subscription-status')}
				</h5>

				<CheckboxGroups
					fieldValues={subscriptionStateNames}
					inputName="subscriptionStates"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('subscription-level')}
				</h5>

				<CheckboxGroups
					fieldValues={simplifySLANames(activeSLANames)}
					inputName="activeSLAs"
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('support-region')}
				</h5>

				<CheckboxGroups fieldValues={regionNames} inputName="regions" />
			</div>
		</div>
	);
}

Categorization.propTypes = {
	activeSLANames: PropTypes.array.isRequired,
	regionNames: PropTypes.array.isRequired,
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

export default Categorization;

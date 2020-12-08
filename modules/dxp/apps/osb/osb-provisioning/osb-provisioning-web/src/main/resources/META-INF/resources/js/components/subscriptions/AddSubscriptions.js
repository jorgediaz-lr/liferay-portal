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

import Subscriptions from './Subscriptions';

function AddSubscriptions({accountName, details, selectProductsURL, sizing}) {
	return (
		<div className="subscriptions-container">
			<div className="subscriptions-header">
				<b>{Liferay.Language.get('configure-subscriptions')}</b>
				<button className="btn btn-secondary" type="button">
					{Liferay.Language.get('select')}
				</button>
			</div>

			<div className="subscriptions">
				<Subscriptions
					accountName={accountName}
					details={details}
					instanceSizes={sizing}
				/>
			</div>

			<div className="button-holder">
				<button
					className="btn btn-primary"
					disabled={true}
					type="submit"
				>
					{Liferay.Language.get('save')}
				</button>

				<button className="btn btn-secondary" type="cancel">
					{Liferay.Language.get('cancel')}
				</button>
			</div>
		</div>
	);
}

AddSubscriptions.propTypes = {
	accountName: PropTypes.string.isRequired,
	details: PropTypes.arrayOf(
		PropTypes.shape({
			endDate: PropTypes.string,
			productKey: PropTypes.string,
			productName: PropTypes.string,
			startDate: PropTypes.string
		})
	),
	selectProductsURL: PropTypes.string,
	sizing: PropTypes.arrayOf(PropTypes.string)
};

export default AddSubscriptions;

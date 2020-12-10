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
import React, {useEffect, useState} from 'react';

import {
	SubscriptionsProvider,
	useSubscriptions
} from '../../hooks/subscriptions';
import Subscriptions from './Subscriptions';

function AddSubscriptions({accountName, details, redirect, sizing}) {
	return (
		<SubscriptionsProvider initialSubscriptions={details}>
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
						instanceSizes={sizing}
					/>
				</div>

				<SubscriptionActions backURL={redirect} />
			</div>
		</SubscriptionsProvider>
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
	redirect: PropTypes.string,
	selectProductsURL: PropTypes.string,
	sizing: PropTypes.arrayOf(PropTypes.string)
};

function SubscriptionActions({backURL}) {
	const [subscriptions] = useSubscriptions();

	const [disableSave, setDisableSave] = useState(true);

	useEffect(() => {
		if (
			subscriptions
				.toList()
				.toArray()
				.every(subscription => subscription.salesforceOpportunityKey)
		) {
			setDisableSave(false);
		}
	}, [subscriptions]);

	return (
		<div className="button-holder">
			<button
				className="btn btn-primary"
				disabled={disableSave}
				type="submit"
			>
				{Liferay.Language.get('save')}
			</button>

			<a className="btn btn-secondary" href={backURL} type="button">
				{Liferay.Language.get('cancel')}
			</a>
		</div>
	);
}

export default AddSubscriptions;

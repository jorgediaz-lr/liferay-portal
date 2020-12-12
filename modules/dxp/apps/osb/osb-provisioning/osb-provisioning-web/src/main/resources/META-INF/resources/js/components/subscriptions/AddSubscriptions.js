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
import {NAMESPACE} from '../../utilities/constants';
import Subscriptions from './Subscriptions';

function AddSubscriptions({
	accountName,
	details,
	editProductPurchasesURL,
	redirect,
	sizing
}) {
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

				<SubscriptionActions
					backURL={redirect}
					formAction={editProductPurchasesURL}
				/>
			</div>
		</SubscriptionsProvider>
	);
}

AddSubscriptions.propTypes = {
	accountName: PropTypes.string.isRequired,
	details: PropTypes.arrayOf(
		PropTypes.shape({
			originalEndDate: PropTypes.string,
			productKey: PropTypes.string,
			productName: PropTypes.string,
			startDate: PropTypes.string
		})
	),
	editProductPurchasesURL: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	selectProductsURL: PropTypes.string,
	sizing: PropTypes.arrayOf(PropTypes.number)
};

function SubscriptionActions({backURL, formAction}) {
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

	function processSubmissionData() {
		const submissionData = subscriptions
			.toList()
			.toJS()
			.map(entry => {
				entry['externalLinks'] = [
					{
						domain: 'salesforce',
						entityId: entry.salesforceOpportunityKey,
						entityName: 'opportunity'
					}
				];
				entry['originalEndDate'] = formatDateSecondFormat(
					entry.originalEndDate
				);
				entry['properties'] = {sizing: entry.sizing};
				entry['startDate'] = formatDateSecondFormat(entry.startDate);

				delete entry['productName'];
				delete entry['salesforceOpportunityKey'];
				delete entry['sizing'];

				return entry;
			});

		return JSON.stringify(submissionData);
	}

	function formatDateSecondFormat(val) {
		return JSON.stringify(val)
			.replace(/.(\d+)Z/g, 'Z')
			.replace(/"/g, '');
	}

	return (
		<form action={formAction} method="post">
			<input
				name={`${NAMESPACE}data`}
				type="hidden"
				value={processSubmissionData()}
			/>

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
		</form>
	);
}

export default AddSubscriptions;

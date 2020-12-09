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

import {Map, Record} from 'immutable';
import React, {useContext, useState} from 'react';

export const SubscriptionRecord = Record({
	endDate: '',
	perpetual: false,
	productKey: null,
	productName: '',
	quantity: '1',
	salesforceOpportunityKey: '',
	sizing: '1',
	startDate: ''
});

const SubscriptionsContext = React.createContext();

export function SubscriptionsProvider({initialSubscriptions = [], children}) {
	const processedSubscriptions = initialSubscriptions.map(detail => {
		return [detail.productKey, SubscriptionRecord(detail)];
	});

	const [subscriptions, setSubscriptions] = useState(
		Map(processedSubscriptions)
	);

	return (
		<SubscriptionsContext.Provider
			value={[
				subscriptions,
				{
					addSubscription(subscription) {
						setSubscriptions(
							subscriptions.set(
								subscription.productKey,
								subscription
							)
						);
					},
					deleteSubscription(productKey) {
						setSubscriptions(subscriptions.delete(productKey));
					},
					updateEndDate(productKey, endDate) {
						setSubscriptions(
							subscriptions.setIn(
								[productKey, 'endDate'],
								endDate
							)
						);
					},
					updatePerpetual(productKey, perpetual) {
						setSubscriptions(
							subscriptions.setIn(
								[productKey, 'perpetual'],
								perpetual
							)
						);
					},
					updateQuantity(productKey, quantity) {
						setSubscriptions(
							subscriptions.setIn(
								[productKey, 'quantity'],
								quantity
							)
						);
					},
					updateSalesforceOpportunityKey(
						productKey,
						salesforceOpportunityKey
					) {
						setSubscriptions(
							subscriptions.setIn(
								[productKey, 'salesforceOpportunityKey'],
								salesforceOpportunityKey
							)
						);
					},
					updateSizing(productKey, sizing) {
						setSubscriptions(
							subscriptions.setIn([productKey, 'sizing'], sizing)
						);
					},
					updateStartDate(productKey, startDate) {
						setSubscriptions(
							subscriptions.setIn(
								[productKey, 'startDate'],
								startDate
							)
						);
					}
				}
			]}
		>
			{children}
		</SubscriptionsContext.Provider>
	);
}

export function useSubscriptions() {
	return useContext(SubscriptionsContext);
}

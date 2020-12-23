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

import {PRODUCT_PURCHASE_STATUS_APPROVED} from '../utilities/constants';

function generateEndDate() {
	const newEndYear = new Date().getFullYear() + 1;

	return new Date(new Date().setFullYear(newEndYear));
}

export const SubscriptionRecord = Record({
	endDate: null,
	key: null,
	originalEndDate: generateEndDate(),
	perpetual: false,
	productKey: null,
	productName: '',
	quantity: '1',
	salesforceOpportunityKey: '',
	sizing: '1',
	startDate: new Date(),
	status: PRODUCT_PURCHASE_STATUS_APPROVED,
	validDates: true
});

const SubscriptionsContext = React.createContext();

export function SubscriptionsProvider({initialSubscriptions = [], children}) {
	const processedSubscriptions = initialSubscriptions.map(detail => {
		return [
			detail.key ? detail.key : detail.productKey,
			SubscriptionRecord({
				...detail,
				endDate: detail.endDate ? new Date(detail.endDate) : null,
				originalEndDate: new Date(detail.originalEndDate),
				startDate: new Date(detail.startDate)
			})
		];
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
					deleteSubscription(key) {
						setSubscriptions(subscriptions.delete(key));
					},
					updateEndDate(key, endDate) {
						setSubscriptions(
							subscriptions.setIn([key, 'endDate'], endDate)
						);
					},
					updateOriginalEndDate(key, originalEndDate) {
						setSubscriptions(
							subscriptions.setIn(
								[key, 'originalEndDate'],
								originalEndDate
							)
						);
					},
					updatePerpetual(key, perpetual) {
						setSubscriptions(
							subscriptions.setIn([key, 'perpetual'], perpetual)
						);
					},
					updateQuantity(key, quantity) {
						setSubscriptions(
							subscriptions.setIn([key, 'quantity'], quantity)
						);
					},
					updateSalesforceOpportunityKey(
						key,
						salesforceOpportunityKey
					) {
						setSubscriptions(
							subscriptions.setIn(
								[key, 'salesforceOpportunityKey'],
								salesforceOpportunityKey
							)
						);
					},
					updateSizing(key, sizing) {
						setSubscriptions(
							subscriptions.setIn([key, 'sizing'], sizing)
						);
					},
					updateStartDate(key, startDate) {
						setSubscriptions(
							subscriptions.setIn([key, 'startDate'], startDate)
						);
					},
					updateStatus(key, status) {
						setSubscriptions(
							subscriptions.setIn([key, 'status'], status)
						);
					},
					updateValidDates(key, valid) {
						setSubscriptions(
							subscriptions.setIn([key, 'validDates'], valid)
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

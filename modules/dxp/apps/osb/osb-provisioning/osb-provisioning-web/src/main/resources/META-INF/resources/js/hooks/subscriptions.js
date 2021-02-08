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
import {convertInputToDate} from '../utilities/helpers';

function generateEndDate() {
	const newEndYear = new Date().getFullYear() + 1;

	return new Date(new Date().setFullYear(newEndYear));
}

export class Subscription extends Record({
	endDate: null,
	externalLinkKey: null,
	index: 0,
	key: null,
	originalEndDate: generateEndDate(),
	perpetual: false,
	productKey: null,
	productName: '',
	quantity: 1,
	salesforceOpportunityKey: '',
	sizing: 1,
	startDate: new Date(),
	status: PRODUCT_PURCHASE_STATUS_APPROVED
}) {
	validateAllDates() {
		return (
			this.validateEndDate() &&
			this.validateGracePeriodStartDate() &&
			this.validateEndDate()
		);
	}

	validateEndDate() {
		if (this.perpetual) {
			return true;
		}

		if (this.endDate) {
			return (
				this.startDate < this.endDate &&
				this.originalEndDate < this.endDate
			);
		}
		else if (this.endDate === '') {
			return false;
		}
		else {
			return true;
		}
	}

	validateGracePeriodStartDate() {
		if (this.perpetual) {
			return true;
		}

		if (!this.originalEndDate) {
			return false;
		}
		else if (this.endDate) {
			return (
				this.startDate < this.originalEndDate &&
				this.originalEndDate < this.endDate
			);
		}
		else {
			return this.startDate < this.originalEndDate;
		}
	}

	validateStartDate() {
		if (this.perpetual) {
			return true;
		}

		if (!this.startDate) {
			return false;
		}
		else if (this.endDate) {
			return (
				this.startDate < this.originalEndDate &&
				this.startDate < this.endDate
			);
		}
		else {
			return this.startDate < this.originalEndDate;
		}
	}
}

const SubscriptionsContext = React.createContext();

export function SubscriptionsProvider({initialSubscriptions = [], children}) {
	const duplicateSubscriptions = {};

	const processedSubscriptions = initialSubscriptions.map(subscription => {
		const key = subscription.key
			? subscription.key
			: subscription.productKey;

		if (duplicateSubscriptions[key] !== undefined) {
			duplicateSubscriptions[key] = duplicateSubscriptions[key] + 1;
		}
		else {
			duplicateSubscriptions[key] = 0;
		}

		const index = duplicateSubscriptions[key];

		return [
			`${key}_${index}`,
			new Subscription({
				...subscription,
				endDate: subscription.endDate
					? new Date(subscription.endDate)
					: null,
				index,
				originalEndDate: new Date(subscription.originalEndDate),
				startDate: new Date(subscription.startDate)
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
					updateAllValuesByFieldName(fieldName, newValue) {
						setSubscriptions(
							subscriptions.map(subscription =>
								subscription.set(fieldName, newValue)
							)
						);
					},
					updateEndDate(key, endDate) {
						setSubscriptions(
							subscriptions.setIn(
								[key, 'endDate'],
								convertInputToDate(endDate)
							)
						);
					},
					updateOriginalEndDate(key, originalEndDate) {
						setSubscriptions(
							subscriptions.setIn(
								[key, 'originalEndDate'],
								convertInputToDate(originalEndDate)
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
							subscriptions.setIn(
								[key, 'startDate'],
								convertInputToDate(startDate)
							)
						);
					},
					updateStatus(key, status) {
						setSubscriptions(
							subscriptions.setIn([key, 'status'], status)
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

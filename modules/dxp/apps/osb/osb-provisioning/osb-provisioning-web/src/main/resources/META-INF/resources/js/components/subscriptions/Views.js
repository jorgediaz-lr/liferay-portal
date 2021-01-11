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

import ClayAlert from '@clayui/alert';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';
import {ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS} from '../../utilities/constants';
import SubscriptionActions from './SubscriptionActions';
import Subscriptions from './Subscriptions';

export function AddView({
	accountName,
	editProductPurchasesURL,
	redirect,
	sizing
}) {
	const [displayAlert, setDisplayAlert] = useState(false);

	useSetDisplayAlert(setDisplayAlert);

	return (
		<div className="subscriptions-container">
			<div className="subscriptions-header">
				<b>{Liferay.Language.get('configure-subscriptions')}</b>
				<button className="btn btn-secondary" type="button">
					{Liferay.Language.get('select')}
				</button>
			</div>

			{displayAlert && (
				<InvalidDateAlert
					message={Liferay.Language.get(
						'please-make-sure-the-start-date-is-before-the-end-date'
					)}
				/>
			)}

			<div className="info">
				<svg
					aria-label={Liferay.Language.get('info-icon')}
					className="lexicon-icon-info-circle-full"
					role="img"
				>
					<use xlinkHref="#info-circle-full" />
				</svg>

				{Liferay.Language.get('date-and-time-displayed-in-utc')}
			</div>

			<div className="subscriptions">
				<Subscriptions
					accountName={accountName}
					instanceSizes={sizing}
					subscriptionsType={ADD_SUBSCRIPTIONS}
				/>
			</div>

			<SubscriptionActions
				formAction={editProductPurchasesURL}
				redirectURL={redirect}
				subscriptionsType={ADD_SUBSCRIPTIONS}
			/>
		</div>
	);
}

AddView.propTypes = {
	accountName: PropTypes.string.isRequired,
	editProductPurchasesURL: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	selectProductsURL: PropTypes.string,
	sizing: PropTypes.arrayOf(PropTypes.number)
};

export function EditView({
	accountName,
	backURL,
	editProductPurchasesURL,
	redirect,
	sizing,
	status
}) {
	const [displayAlert, setDisplayAlert] = useState(false);

	useSetDisplayAlert(setDisplayAlert);

	return (
		<>
			<div className="subscriptions-step">
				<span>{Liferay.Language.get('edit-details')}</span>

				<span>{Liferay.Language.get('step-2-of-2')}</span>
			</div>

			<div className="subscriptions-container">
				{displayAlert && (
					<InvalidDateAlert
						message={Liferay.Language.get(
							'please-make-sure-the-start-date-is-before-the-end-date-and-the-end-date-is-before-the-grace-period-end-date'
						)}
					/>
				)}

				<div className="info">
					<svg
						aria-label={Liferay.Language.get('info-icon')}
						className="lexicon-icon-info-circle-full"
						role="img"
					>
						<use xlinkHref="#info-circle-full" />
					</svg>

					{Liferay.Language.get('date-and-time-displayed-in-utc')}
				</div>

				<div className="subscriptions">
					<Subscriptions
						accountName={accountName}
						instanceSizes={sizing}
						statusOptions={status}
						subscriptionsType={EDIT_SUBSCRIPTIONS}
					/>
				</div>

				<SubscriptionActions
					backURL={backURL}
					formAction={editProductPurchasesURL}
					redirectURL={redirect}
					subscriptionsType={EDIT_SUBSCRIPTIONS}
				/>
			</div>
		</>
	);
}

EditView.propTypes = {
	accountName: PropTypes.string.isRequired,
	backURL: PropTypes.string,
	editProductPurchasesURL: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	sizing: PropTypes.arrayOf(PropTypes.number),
	status: PropTypes.arrayOf(PropTypes.string)
};

function InvalidDateAlert({message}) {
	return (
		<ClayAlert
			displayType="danger"
			title={Liferay.Language.get('invalid-date')}
		>
			{message}
		</ClayAlert>
	);
}

function useSetDisplayAlert(callback) {
	const [subscriptions] = useSubscriptions();

	return useEffect(() => {
		function validateDateFields() {
			return subscriptions
				.toList()
				.every(subscription => subscription.validateAllDates());
		}

		if (validateDateFields()) {
			callback(false);
		}
		else {
			callback(true);
		}
	}, [callback, subscriptions]);
}

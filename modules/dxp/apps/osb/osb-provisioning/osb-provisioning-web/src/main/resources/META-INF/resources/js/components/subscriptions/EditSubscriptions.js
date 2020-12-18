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

import {SubscriptionsProvider} from '../../hooks/subscriptions';
import {ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS} from '../../utilities/constants';
import SubscriptionActions from './SubscriptionActions';
import Subscriptions from './Subscriptions';

function EditSubscriptions({
	accountName,
	addSubscriptions,
	backURL,
	details,
	editProductPurchasesURL,
	redirect,
	sizing
}) {
	return (
		<SubscriptionsProvider initialSubscriptions={details}>
			{addSubscriptions && (
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
							subscriptionsType={ADD_SUBSCRIPTIONS}
						/>
					</div>

					<SubscriptionActions
						formAction={editProductPurchasesURL}
						redirectURL={redirect}
						subscriptionsType={ADD_SUBSCRIPTIONS}
					/>
				</div>
			)}

			{!addSubscriptions && (
				<>
					<div className="subscriptions-step">
						<span>{Liferay.Language.get('edit-details')}</span>

						<span>{Liferay.Language.get('step-2-of-2')}</span>
					</div>

					<div className="subscriptions-container">
						<div className="subscriptions">
							<Subscriptions
								accountName={accountName}
								instanceSizes={sizing}
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
			)}
		</SubscriptionsProvider>
	);
}

EditSubscriptions.propTypes = {
	accountName: PropTypes.string.isRequired,
	addSubscriptions: PropTypes.bool.isRequired,
	backURL: PropTypes.string,
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

export default EditSubscriptions;

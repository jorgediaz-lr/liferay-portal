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
import {AddView, EditView} from './Views';

function EditSubscriptions({
	accountName,
	addSubscriptions,
	backURL,
	details,
	editProductPurchasesURL,
	redirect,
	selectProductsURL,
	sizing,
	status
}) {
	return (
		<SubscriptionsProvider initialSubscriptions={details}>
			{addSubscriptions && (
				<AddView
					accountName={accountName}
					editProductPurchasesURL={editProductPurchasesURL}
					redirect={redirect}
					selectProductsURL={selectProductsURL}
					sizing={sizing}
				/>
			)}

			{!addSubscriptions && (
				<EditView
					accountName={accountName}
					backURL={backURL}
					editProductPurchasesURL={editProductPurchasesURL}
					redirect={redirect}
					sizing={sizing}
					status={status}
				/>
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
	sizing: PropTypes.arrayOf(PropTypes.number),
	status: PropTypes.arrayOf(PropTypes.string)
};

export default EditSubscriptions;

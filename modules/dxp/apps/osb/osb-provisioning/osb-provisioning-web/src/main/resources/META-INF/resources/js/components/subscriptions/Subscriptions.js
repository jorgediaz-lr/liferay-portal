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

import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React from 'react';

function Subscriptions() {
	return (
		<ClayTable>
			<ClayTable.Head>
				<ClayTable.Row className="subscriptions-table-heading">
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('product')}
					</ClayTable.Cell>
					<ClayTable.Cell expanded headingCell>
						{Liferay.Language.get('salesforce-opportunity-key')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('purchased')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('perpetual-subscription')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('start-date')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('end-date')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('instance-size')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{Liferay.Language.get('account-name')}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell></ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>
			<ClayTable.Body>
				<Subscription />
			</ClayTable.Body>
		</ClayTable>
	);
}

function Subscription() {
	return (
		<ClayTable.Row>
			<ClayTable.Cell>{'product name'}</ClayTable.Cell>
			<ClayTable.Cell>{'input'}</ClayTable.Cell>
			<ClayTable.Cell>{'input num'}</ClayTable.Cell>
			<ClayTable.Cell>{'checkbox'}</ClayTable.Cell>
			<ClayTable.Cell>{'date field'}</ClayTable.Cell>
			<ClayTable.Cell>{'date'}</ClayTable.Cell>
			<ClayTable.Cell>{'dropdown'}</ClayTable.Cell>
			<ClayTable.Cell>{'account name'}</ClayTable.Cell>
			<ClayTable.Cell>{'x btn'}</ClayTable.Cell>
		</ClayTable.Row>
	);
}

export default Subscriptions;

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

import React from 'react';

import CardEntryRenderer from './CardEntryRenderer';
import {GUEST_ID} from '../../helper/index';

function CardListRenderer({
	commerceAccountId = GUEST_ID,
	CPEntries,
	checkoutURL,
	portletNamespace
}) {
	return (
		<div className={'align-items-center d-flex'}>
			{CPEntries.map((entry, index) => (
				<div
					className={
						'col-sm-12 col-md-4 osb-commerce-product-card-container'
					}
				>
					<CardEntryRenderer
						checkoutURL={checkoutURL}
						commerceAccountId={commerceAccountId}
						key={index}
						isFeatured={index === 1}
						isTrial={index === 0}
						namespace={portletNamespace}
						{...entry}
					/>
				</div>
			))}
		</div>
	)
}

export default CardListRenderer;
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayIcon from '@clayui/icon';
import React from 'react';
import PropTypes from 'prop-types';

function SubscriptionEntry({
   description,
   name,
   productImageURL: imageURL,
   spritemap
}) {

	const debugFeatures = [
		"This is an amazing feature",
		"This is an amazing feature with a super long text",
		"This is yet another cool feature",
		"And much more. No, really. I mean it."
	];

	return (
		<div className={'subscription-entry'}>
			<div className={'header'}>
				<div className={'image'}>
					<img alt={name} src={imageURL}/>
				</div>

				<div className={'name'}>
					<h1>{name}</h1>
				</div>

				<div className={'description'}>
					<p className={'text-truncate'}>{description}</p>
				</div>
			</div>

			{debugFeatures.length && (
				<div className={'features'}>
					<ul>
					{
						debugFeatures.map((feature) => {
							return (
								<li>
									<div className={'list-icon'}>
										<ClayIcon symbol={'check'} spritemap={spritemap} />
									</div>
									<div className={'feature'}>
										<span className={'text-truncate'}>
											{feature}
										</span>
									</div>
								</li>
							);
						})
					}
					</ul>
				</div>
			)}
		</div>
	);
}

SubscriptionEntry.defaultProps = {
	description: 'This is the description of the product',
	features: [
		"This is an amazing feature",
		"This is an amazing feature with a super long text",
		"This is yet another cool feature",
		"And much more. No, really. I mean it."
	]
};

SubscriptionEntry.propTypes = {
	description: PropTypes.string,
	features: PropTypes.arrayOf(PropTypes.string),
	name: PropTypes.string,
	productImageURL: PropTypes.string,
	skuId: PropTypes.string,
	spritemap: PropTypes.string
};

export default SubscriptionEntry;
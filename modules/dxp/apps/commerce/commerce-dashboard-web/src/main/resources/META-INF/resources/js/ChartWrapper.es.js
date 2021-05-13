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

import ClayChart from '@clayui/charts';
import React, {useCallback, useRef} from 'react';
import ResizeObserver from 'resize-observer-polyfill';

export default function ChartWrapper({data, loading, noDataErrorMessage}) {
	const chart = useRef();

	const resize = useCallback(() => chart.current && chart.current.resize(), [
		chart
	]);

	const wrapper = useCallback(
		node => {
			if (node !== null) {
				new ResizeObserver(resize).observe(node);
			}
		},
		[resize]
	);

	if (loading) {
		return <span aria-hidden="true" className="loading-animation" />;
	}
	else if (!data.data.columns.length) {
		return <p>{noDataErrorMessage}</p>;
	}
	else {
		return (
			<div ref={wrapper}>
				<ClayChart {...data} ref={chart} />
			</div>
		);
	}
}

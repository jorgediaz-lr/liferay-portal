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
import ClayTableCell from '@clayui/table/lib/Cell';
import PropTypes from 'prop-types';
import React from 'react';

export default function TableDivider({colSpan, title = ''}) {
	return (
		<ClayTable.Row divider={true}>
			<ClayTableCell colSpan={colSpan}>{title}</ClayTableCell>
		</ClayTable.Row>
	);
}

TableDivider.propTypes = {
	colSpan: PropTypes.number.isRequired,
	title: PropTypes.string
};

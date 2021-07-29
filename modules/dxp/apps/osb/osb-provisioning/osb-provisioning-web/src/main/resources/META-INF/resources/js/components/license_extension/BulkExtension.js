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

import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {useExtendLicenses} from '../../hooks/extendLicenses';
import {groupByAll} from '../../utilities/helpers';
import TableDivider from '../TableDivider';
import DetailsGroup from './DetailsGroup';
import ExtensionDetails from './ExtensionDetails';

export default function BulkExtension({extensionURL}) {
	const [licenses] = useExtendLicenses();

	const [permanent, temporary] = partition(
		licenses.toList().toJS(),
		({indefinite}) => indefinite
	);

	const permanentOrderedByProduct = permanent.length
		? groupByAll(permanent, ({productName}) => productName)
		: permanent;
	const temporaryOrderedByProduct = temporary.length
		? groupByAll(temporary, ({productName}) => productName)
		: temporary;

	return (
		<>
			{!!temporary.length &&
				temporaryOrderedByProduct.map((temp, index) => (
					<DetailsGroup
						extensionURL={extensionURL}
						key={index}
						licenses={temp}
					/>
				))}

			{!!permanent.length && (
				<>
					<tbody>
						<TableDivider
							colSpan={8}
							title={Liferay.Language.get('permanent-licenses')}
						/>
					</tbody>

					{permanentOrderedByProduct.map((perm, index) => (
						<ExtensionDetails key={index} licenses={perm} />
					))}
				</>
			)}
		</>
	);
}

BulkExtension.propTypes = {
	extensionURL: PropTypes.string.isRequired
};

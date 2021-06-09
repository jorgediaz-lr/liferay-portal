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

import flatten from 'lodash.flatten';
import partition from 'lodash.partition';
import PropTypes from 'prop-types';
import React from 'react';

import {useLicenses} from '../../hooks/licenses';
import {groupBy, groupByAll} from '../../utilities/helpers';
import TableDivider from '../TableDivider';
import LicenseGroup from './LicenseGroup';

const COMMERCE = 'Commerce';
const COMMERCE_LICENSE_VERSION = 3;
const DXP_LICENSE_VERSION = 5;

function CombinedLicenses({downloadURL}) {
	const [licenses] = useLicenses();

	const [activeCommerceDXPLicenses] = partition(
		licenses.toSet().toJS(),
		({active, licenseVersion, productName}) =>
			(licenseVersion >= DXP_LICENSE_VERSION ||
				(licenseVersion === COMMERCE_LICENSE_VERSION &&
					productName.includes(COMMERCE))) &&
			active
	);

	const intersection = activeCommerceDXPLicenses.length
		? groupByAll(
				activeCommerceDXPLicenses,
				({startDate}) => startDate,
				({expirationDate}) => expirationDate,
				({sizing}) => sizing
		  )
		: [];

	const [dxp, commerce] = intersection.length
		? partition(
				flatten(intersection),
				({licenseVersion}) => licenseVersion >= DXP_LICENSE_VERSION
		  )
		: [[], []];

	const commerceGrouping = groupByAll(
		commerce,
		({licenseEntryType}) => licenseEntryType
	);
	const dxpGrouping = groupByAll(
		dxp,
		({licenseEntryType}) => licenseEntryType,
		({productVersion}) => productVersion
	);

	const transformedCommerceGrouping = groupBy(
		commerceGrouping,
		item => item.length
	);
	const transformedDXPGrouping = groupBy(dxpGrouping, item => item.length);
	const commerceSet = new Set(Object.keys(transformedCommerceGrouping));
	const dxpSet = new Set(Object.keys(transformedDXPGrouping));
	const licenseQtyIntersection = new Set(
		[...commerceSet].filter(val => dxpSet.has(val))
	);

	let combinedLicenses = [];
	// Suppress eslint false alarm for unused var
	/* eslint-disable no-unused-vars */

	/* eslint-disable-next-line no-for-of-loops/no-for-of-loops */
	for (const value of licenseQtyIntersection.values()) {
		combinedLicenses = [
			...combinedLicenses,
			[
				...flatten(transformedCommerceGrouping[value]),
				...flatten(transformedDXPGrouping[value])
			]
		];
	}
	/* eslint-enable no-unused-vars */

	return (
		<>
			{!!combinedLicenses.length && (
				<>
					<tbody>
						<TableDivider
							colSpan={11}
							title={Liferay.Language.get(
								'dxp-commerce-combined-licenses'
							)}
						/>
					</tbody>

					<LicenseGroup
						downloadURL={downloadURL}
						items={combinedLicenses}
					/>
				</>
			)}
		</>
	);
}

CombinedLicenses.propTypes = {
	downloadURL: PropTypes.string.isRequired
};

export default CombinedLicenses;

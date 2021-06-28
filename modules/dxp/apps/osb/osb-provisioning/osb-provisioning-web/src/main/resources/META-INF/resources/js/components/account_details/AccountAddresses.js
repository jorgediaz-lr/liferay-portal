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
import React, {useState} from 'react';

import {DASH} from '../../utilities/constants';
import Address from './Address';

function AccountAddresses({
	accountKey,
	addAddressURL,
	addresses,
	countryOptions
}) {
	const INITIAL_ADDRESS = {
		addressCountry: DASH,
		addressLocality: DASH,
		addressRegion: DASH,
		editPostalAddressURL: addAddressURL,
		id: '',
		postalCode: DASH,
		primary: false,
		streetAddressLine1: DASH,
		streetAddressLine2: DASH,
		streetAddressLine3: DASH
	};

	const [accountAddresses, setAccountAddresses] = useState(
		addresses.length === 0 ? [INITIAL_ADDRESS] : addresses
	);

	const processedCountryOptions = countryOptions.map(
		({active, countryRegions, name, zipRequired}) => ({
			active,
			countryRegions,
			name,
			zipRequired
		})
	);

	function addAddressEntry() {
		setAccountAddresses([...accountAddresses, INITIAL_ADDRESS]);
	}

	return (
		<>
			{accountAddresses.map((address, index) => (
				<Address
					accountKey={accountKey}
					addFn={addAddressEntry}
					address={address}
					count={index + 1}
					countryOptions={processedCountryOptions}
					key={address.id || index + 1}
				/>
			))}
		</>
	);
}

AccountAddresses.propTypes = {
	accountKey: PropTypes.string.isRequired,
	addAddressURL: PropTypes.string.isRequired,
	addresses: PropTypes.arrayOf(
		PropTypes.shape({
			addressCountry: PropTypes.string,
			addressLocality: PropTypes.string,
			addressRegion: PropTypes.string,
			deletePostalAddressURL: PropTypes.string,
			editPostalAddressURL: PropTypes.string,
			id: PropTypes.string,
			postalCode: PropTypes.string,
			primary: PropTypes.bool,
			streetAddressLine1: PropTypes.string,
			streetAddressLine2: PropTypes.string,
			streetAddressLine3: PropTypes.string
		})
	),
	countryOptions: PropTypes.arrayOf(
		PropTypes.shape({
			active: PropTypes.bool,
			countryRegions: PropTypes.arrayOf(
				PropTypes.shape({
					active: PropTypes.bool,
					countryName: PropTypes.string,
					name: PropTypes.string
				})
			),
			name: PropTypes.string,
			zipRequired: PropTypes.bool
		})
	).isRequired
};

export default AccountAddresses;

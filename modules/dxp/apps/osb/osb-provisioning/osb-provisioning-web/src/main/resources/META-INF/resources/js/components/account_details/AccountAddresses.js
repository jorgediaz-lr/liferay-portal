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
import React, {useEffect, useState} from 'react';

import {DASH} from '../../utilities/constants';
import Address from './Address';

function AccountAddresses({accountKey, addURL, addresses}) {
	const [countryOptions, setCountryOptions] = useState([]);

	useEffect(() => {
		Liferay.Service('/country/get-countries', {active: true})
			.then(countries => {
				const options = countries.map(country => {
					return {
						label: country.nameCurrentValue,
						value: country.countryId,
						zipRequired: country.zipRequired
					};
				});

				options.unshift({label: '-', value: '0', zipRequired: false});

				setCountryOptions(options);
			})
			.catch(err => console.error(err));
	}, []);

	if (addresses.length === 0) {
		addresses.push({
			addressCountry: DASH,
			addressLocality: DASH,
			addressRegion: DASH,
			id: '',
			postalCode: DASH,
			primary: false,
			readOnly: true,
			streetAddressLine1: DASH,
			streetAddressLine2: DASH,
			streetAddressLine3: DASH
		});
	}

	return (
		<>
			{addresses.map((address, index) => (
				<Address
					accountKey={accountKey}
					address={address}
					addURL={addURL}
					count={index + 1}
					countryOptions={countryOptions}
					key={address.id}
				/>
			))}
		</>
	);
}

AccountAddresses.propTypes = {
	accountKey: PropTypes.string.isRequired,
	addURL: PropTypes.string.isRequired,
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
	)
};

export default AccountAddresses;

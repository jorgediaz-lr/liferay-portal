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

import {Record} from 'immutable';
import React, {useContext, useState} from 'react';

export const GenerateLicense = Record({
	accountCode: '',
	accountKey: '',
	accountName: '',
	complimentary: false,
	description: '',
	expirationDate: null,
	licenseEntry: {
		licenseEntryId: '',
		licenseEntryName: '',
		licenseEntryType: ''
	},
	licenseKeysGenerated: '',
	owner: '',
	product: {productKey: '', productName: ''},
	productPurchaseKey: '',
	showSpecificDetails: false,
	sizing: '',
	startDate: null,
	version: ''
});

const GenerateLicenseContext = React.createContext();

export function GenerateLicenseProvider({
	license = new GenerateLicense(),
	children
}) {
	const [generateLicense, setGenerateLicense] = useState(license);

	return (
		<GenerateLicenseContext.Provider
			value={[
				generateLicense,
				{
					updateLicense(updater) {
						setGenerateLicense(updater(generateLicense));
					}
				}
			]}
		>
			{children}
		</GenerateLicenseContext.Provider>
	);
}

export function useGenerateLicense() {
	return useContext(GenerateLicenseContext);
}

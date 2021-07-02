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

import {Map, Record} from 'immutable';
import React, {createContext, useContext, useState} from 'react';

export const LicenseRecord = Record({
	expirationDate: '',
	extensionURL: '',
	licenseKeyId: '',
	licenseType: '',
	productName: '',
	productPurchaseKey: '',
	startDate: '',
	terms: null
});

const ExtendLicensesContext = createContext();

export function ExtendLicensesProvider({initialLicenses = [], children}) {
	const processedLicenses = initialLicenses.map(license => [
		license.licenseKeyId,
		LicenseRecord(license)
	]);

	const [licenses, setLicenses] = useState(Map(processedLicenses));

	return (
		<ExtendLicensesContext.Provider
			value={[
				licenses,
				{
					removeLicense(key) {
						setLicenses(licenses.delete(key));
					},
					updateLicense(key, updater) {
						setLicenses(licenses.update(key, updater));
					}
				}
			]}
		>
			{children}
		</ExtendLicensesContext.Provider>
	);
}

export function useExtendLicenses() {
	return useContext(ExtendLicensesContext);
}

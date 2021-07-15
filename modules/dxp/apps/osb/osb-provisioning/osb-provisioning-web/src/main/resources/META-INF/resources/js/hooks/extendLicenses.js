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

import {
	deriveLicenseDates,
	getDetachedLicenseDates
} from '../utilities/license';

export const LicenseRecord = Record({
	expirationDate: '',
	licenseKeyId: '',
	licenseType: '',
	productName: '',
	productPurchaseKey: '',
	readyToExtend: false,
	startDate: '',
	terms: null
});

const ExtendLicensesContext = createContext();

export function ExtendLicensesProvider({initialLicenses = [], children}) {
	const processedLicenses = initialLicenses.map(license => {
		let licenseDates = getDetachedLicenseDates();
		let productPurchaseKey = '';

		if (license.terms) {
			const firstTerms = license.terms[0];

			licenseDates = deriveLicenseDates(firstTerms, license.licenseType);
			productPurchaseKey = firstTerms.productPurchaseKey;
		}

		const {
			licenseExpirationDate: expirationDate,
			licenseStartDate: startDate
		} = licenseDates;

		return [
			license.licenseKeyId,
			LicenseRecord({
				...license,
				expirationDate,
				productPurchaseKey,
				startDate
			})
		];
	});

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

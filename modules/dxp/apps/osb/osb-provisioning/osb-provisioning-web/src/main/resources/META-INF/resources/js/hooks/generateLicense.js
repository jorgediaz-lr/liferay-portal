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

const GenerateLicense = Record({
	accountKey: '',
	accountName: '',
	complimentary: false,
	description: '',
	expirationDate: null,
	licenseKeyGenerated: '',
	owner: '',
	productPurchaseKey: null,
	selectedProduct: null,
	selectedType: '',
	selectedVersion: '',
	showSpecificDetails: false,
	sizing: '',
	startDate: null
});

const GenerateLicenseContext = React.createContext();

export function GenerateLicenseProvider({children}) {
	const [generateLicense, setGenerateLicense] = useState(GenerateLicense);

	return (
		<GenerateLicenseContext.Provider
			value={[
				generateLicense,
				{
					setAccountKey(key) {
						setGenerateLicense(
							generateLicense.set('accountKey', key)
						);
					},
					setAccountName(name) {
						setGenerateLicense(
							generateLicense
								.set('accountName', name)
								.update('description', name => {
									const existingValue = generateLicense.get(
										'description'
									);

									return existingValue === ''
										? name
										: existingValue;
								})
								.update('owner', name => {
									const existingValue = generateLicense.get(
										'owner'
									);

									return existingValue === ''
										? name
										: existingValue;
								})
						);
					},
					setComplimentary(complimentary = true) {
						setGenerateLicense(
							generateLicense.set('complimentary', complimentary)
						);
					},
					setDescription(description) {
						setGenerateLicense(
							generateLicense.set('description', description)
						);
					},
					setExpirationDate(date) {
						setGenerateLicense(
							generateLicense.set('expirationDate', date)
						);
					},
					setInstanceSize(sizing) {
						setGenerateLicense(
							generateLicense.set('sizing', sizing)
						);
					},
					setLicensesGenerated(licensesGenerated) {
						setGenerateLicense(
							generateLicense.set(
								'licenseKeyGenerated',
								licensesGenerated
							)
						);
					},
					setOwner(owner) {
						setGenerateLicense(generateLicense.set('owner', owner));
					},
					setProductPurchaseKey(key) {
						setGenerateLicense(
							generateLicense.set('productPurchaseKey', key)
						);
					},
					setSelectedProduct(product) {
						setGenerateLicense(
							generateLicense.set('selectedProduct', product)
						);
					},
					setSelectedType(type) {
						setGenerateLicense(
							generateLicense.set('selectedType', type)
						);
					},
					setSelectedVersion(version) {
						setGenerateLicense(
							generateLicense.set('selectedVersion', version)
						);
					},
					setShowSpecificDetails(show = true) {
						setGenerateLicense(
							generateLicense.set('showSpecificDetails', show)
						);
					},
					setStartDate(date) {
						setGenerateLicense(
							generateLicense.set('startDate', date)
						);
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

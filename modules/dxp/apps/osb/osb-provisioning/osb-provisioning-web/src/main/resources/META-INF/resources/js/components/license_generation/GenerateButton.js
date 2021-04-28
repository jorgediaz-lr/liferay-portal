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
import React from 'react';

import {useLicense} from '../../hooks/license';
import {formatDate} from '../../utilities/date';
import {request, validateIPv4s, validateMAC} from '../../utilities/helpers';

function GenerateButton({formAction, redirect, serverIdValidatable = false}) {
	const [license] = useLicense();
	const {licenseEntryId, licenseEntryType} = license.licenseEntry;
	const {productKey} = license.product;
	const {serverIds} = license;

	function disableGenerate() {
		return !license.description || !license.owner || serverIdValidatable
			? !validateServerIds()
			: false;
	}

	function handleSubmit() {
		const params = {
			...license.toJS(),
			expirationDate: formatDate(license.expirationDate),
			licenseEntryId,
			licenseEntryType,
			productKey,
			productVersion: license.version,
			serverIds: JSON.stringify(serverIds),
			startDate: formatDate(license.startDate)
		};

		request(formAction, params, 'formData', 'post')
			.then(({data}) => {
				if (data) {
					location.assign(redirect);
				}
			})
			.catch(err => console.error(err));
	}

	function validateIpAddresses() {
		return serverIds.every(({ipAddresses}) => {
			if (ipAddresses) {
				return validateIPv4s(ipAddresses);
			}
			else {
				return true;
			}
		});
	}

	function validateMacAddresses() {
		return serverIds.every(({macAddresses}) => {
			if (macAddresses) {
				return validateMAC(macAddresses);
			}
			else {
				return true;
			}
		});
	}

	function validateFields() {
		return serverIds
			.filter(
				({hostName, ipAddresses, macAddresses}) =>
					!hostName && !ipAddresses && !macAddresses
			)
			.isEmpty();
	}

	function validateServerIds() {
		return (
			validateFields() && validateIpAddresses() && validateMacAddresses()
		);
	}

	return (
		<button
			className="btn btn-primary"
			disabled={disableGenerate()}
			onClick={handleSubmit}
			type="button"
		>
			{Liferay.Language.get('generate')}
		</button>
	);
}

GenerateButton.propTypse = {
	formAction: PropTypes.string.isRequired,
	redirect: PropTypes.string.isRequired,
	serverIdValidatable: PropTypes.bool
};

export default GenerateButton;

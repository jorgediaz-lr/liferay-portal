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
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {LicenseRecord, useExtendLicenses} from '../../hooks/extendLicenses';
import {RESTRICTED_EXPIRATION_DATE_TYPES} from '../../utilities/constants';
import {formatDate} from '../../utilities/date';
import HiddenForm from '../HiddenForm';
import IconButton from '../IconButton';
import LicenseDates from '../LicenseDates';
import Terms from './Terms';

function Detail({disableDelete, extensionURL, license}) {
	const [, {removeLicense, updateLicense}] = useExtendLicenses();
	const formRef = useRef();

	const {
		expirationDate,
		licenseKeyId,
		licenseType,
		productName,
		productPurchaseKey,
		readyToExtend,
		startDate,
		terms
	} = license.toJS();

	const [disableExtend, setDisableExtend] = useState(false);
	const [selectedExpirationDate, setSelectedExpirationDate] = useState(
		expirationDate
	);
	const [selectedStartDate, setSelectedStartDate] = useState(startDate);
	const [validDates, setValidDates] = useState(
		!isNaN(new Date(expirationDate)) && !isNaN(new Date(startDate))
	);

	const restricted = !!RESTRICTED_EXPIRATION_DATE_TYPES.find(
		restrictedType => restrictedType === licenseType
	);

	useEffect(() => {
		setValidDates(
			!isNaN(new Date(expirationDate)) && !isNaN(new Date(startDate))
		);
	}, [expirationDate, startDate]);

	useEffect(() => {
		if (readyToExtend && formRef.current) {
			formRef.current.submit();
		}
	}, [readyToExtend]);

	useEffect(() => {
		setDisableExtend(!validDates);
	}, [validDates]);

	function handleExpirationDateChange(val) {
		setSelectedExpirationDate(val);
	}

	function handleOnSubmit() {
		updateLicense(licenseKeyId, license =>
			license
				.set('expirationDate', selectedExpirationDate)
				.set('startDate', selectedStartDate)

				.set('readyToExtend', true)
		);
	}

	function handleRemove() {
		removeLicense(licenseKeyId);
	}

	function handleStartDateChange(val) {
		setSelectedStartDate(val);
	}

	function handleTermsChange(val) {
		updateLicense(licenseKeyId, license =>
			license.set('productPurchaseKey', val)
		);
	}

	function handleValidDates(bool) {
		setValidDates(bool);
	}

	return (
		<ClayTable.Body id={licenseKeyId}>
			<ClayTable.Row>
				<ClayTable.Cell>{productName}</ClayTable.Cell>
				<ClayTable.Cell className="input-group-sm">
					<Terms
						terms={terms}
						termSelected={productPurchaseKey}
						updateTerms={handleTermsChange}
					/>
				</ClayTable.Cell>
				<LicenseDates
					detached={!!terms}
					expirationDate={expirationDate}
					restricted={restricted}
					startDate={startDate}
					updateExpirationDate={handleExpirationDateChange}
					updateStartDate={handleStartDateChange}
					updateValidation={handleValidDates}
					validDates={validDates}
				/>
				<ClayTable.Cell>
					<HiddenForm
						fields={{
							expirationDate: formatDate(expirationDate),
							licenseKeyId,
							productPurchaseKey,
							startDate: formatDate(startDate)
						}}
						formAction={extensionURL}
						formName="extendLicenseFm"
						ref={formRef}
					/>

					<button
						className="btn btn-secondary btn-sm"
						disabled={disableExtend}
						onClick={handleOnSubmit}
						role="button"
						type="button"
					>
						{Liferay.Language.get('extend')}
					</button>
				</ClayTable.Cell>
				<ClayTable.Cell>
					<IconButton
						cssClass="btn-icon btn-sm"
						disabled={disableDelete}
						labelName={Liferay.Language.get('delete-license-icon')}
						onClick={handleRemove}
						svgId="#delete-icon"
						title={Liferay.Language.get('delete')}
					/>
				</ClayTable.Cell>
			</ClayTable.Row>
		</ClayTable.Body>
	);
}

Detail.propTypes = {
	disableDelete: PropTypes.bool,
	extensionURL: PropTypes.string,
	license: PropTypes.instanceOf(LicenseRecord)
};

function ExtensionDetails({extensionURL}) {
	const [licenses] = useExtendLicenses();

	const disableDelete = licenses.size <= 1;

	return (
		<>
			{licenses.toList().map(license => (
				<Detail
					disableDelete={disableDelete}
					extensionURL={extensionURL}
					key={license.licenseKeyId}
					license={license}
				/>
			))}
		</>
	);
}

ExtensionDetails.propTypes = {
	extensionURL: PropTypes.string
};

export default ExtensionDetails;

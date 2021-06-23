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
import React, {useEffect, useRef, useState} from 'react';

import {formatDate} from '../../utilities/date';
import HiddenForm from '../HiddenForm';
import ReplacementModal from './ReplacementModal';

export default function BulkReplacement({accountKey, productKey = '', replacementURL}) {
	const formRef = useRef();

	const [expirationDate, setExpirationDate] = useState('');
	const [licenseKeyIds, setLicenseKeyIds] = useState('');
	const [modalVisible, setModalVisible] = useState(false);
	const [startDate, setStartDate] = useState('');

	useEffect(() => {
		const listener = event => {
			const {detail} = event;

			setLicenseKeyIds(detail.licenseKeyIds);
			setModalVisible(detail.modalVisible);
		};

		window.addEventListener('bulkReplaceLicenses', listener);

		return () => window.removeEventListener('bulkReplaceLicenses', listener);
	});

	useEffect(() => {
		if (startDate && expirationDate && formRef.current) {
			formRef.current.submit();
		}
	}, [expirationDate, startDate]);

	function handleClose() {
		setModalVisible(false);
	}

	function handleReplace(startDate, expirationDate) {
		setStartDate(startDate);
		setExpirationDate(expirationDate);
	}

	return (
		<>
			{modalVisible && (
				<ReplacementModal closeFn={handleClose} replaceFn={handleReplace} />
			)}

			<HiddenForm
				fields={{
					accountKey,
					expirationDate: formatDate(expirationDate),
					licenseKeyIds,
					productKey,
					startDate: formatDate(startDate)
				}}
				formAction={replacementURL}
				formName="bulkLicenseReplacement"
				ref={formRef}
			/>
		</>
	);
}

BulkReplacement.propTypes = {
	accountKey: PropTypes.string,
	productKey: PropTypes.string,
	replacementURL: PropTypes.string
};

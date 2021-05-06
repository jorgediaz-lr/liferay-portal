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
import RenewalModal from './RenewalModal';

export default function BulkRenewal({accountKey, renewalURL}) {
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

		window.addEventListener('bulkRenewLicenses', listener);

		return () => window.removeEventListener('bulkRenewLicenses', listener);
	});

	useEffect(() => {
		if (startDate && expirationDate && formRef.current) {
			formRef.current.submit();
		}
	}, [expirationDate, startDate]);

	function handleClose() {
		setModalVisible(false);
	}

	function handleRenew(startDate, expirationDate) {
		setStartDate(startDate);
		setExpirationDate(expirationDate);
	}

	return (
		<>
			{modalVisible && (
				<RenewalModal
					closeFn={handleClose}
					expirationDate={''}
					renewFn={handleRenew}
					startDate={''}
				/>
			)}

			<HiddenForm
				fields={{
					accountKey,
					expirationDate: formatDate(expirationDate),
					licenseKeyIds,
					startDate: formatDate(startDate)
				}}
				formAction={renewalURL}
				formName="bulkLicenseRenewal"
				ref={formRef}
			/>
		</>
	);
}

BulkRenewal.propTypes = {
	accountKey: PropTypes.string,
	renewalURL: PropTypes.string
};

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

import {NAMESPACE} from '../../utilities/constants';
import {formatDate} from '../../utilities/date';
import ReplacementModal from './ReplacementModal';

export default function ReplaceLicense({
	expirationDate = '',
	replacementURL,
	startDate = ''
}) {
	const [modalVisible, setModalVisible] = useState(false);

	function handleClose() {
		setModalVisible(false);
	}

	function handleOnClick() {
		setModalVisible(true);
	}

	function handleReplace(newStartDate, newExpirationDate) {
		const form = document.getElementById(`${NAMESPACE}editLicenseFm`);

		const expirationDateField = document.getElementById(
			`${NAMESPACE}expirationDate`
		);
		const startDateField = document.getElementById(`${NAMESPACE}startDate`);

		if (form && expirationDateField && startDateField) {
			handleClose();

			expirationDateField.value = formatDate(newExpirationDate);
			startDateField.value = formatDate(newStartDate);

			if (replacementURL) {
				form.action = replacementURL;
			}

			form.submit();
		}
	}

	return (
		<>
			<button
				className="btn btn-secondary btn-sm"
				onClick={handleOnClick}
				type="button"
			>
				{Liferay.Language.get('replace')}
			</button>

			{modalVisible && (
				<ReplacementModal
					closeFn={handleClose}
					expirationDate={expirationDate}
					replaceFn={handleReplace}
					startDate={startDate}
				/>
			)}
		</>
	);
}

ReplaceLicense.propTypes = {
	expirationDate: PropTypes.string,
	replacementURL: PropTypes.string,
	startDate: PropTypes.string
};

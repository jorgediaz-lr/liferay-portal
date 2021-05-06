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

import ClayButton from '@clayui/button';
import ClayModal, {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {validateDateFieldFormat} from '../../utilities/date';
import DatePicker from '../DatePicker';

function RenewalModal({closeFn, expirationDate, renewFn, startDate}) {
	const [currentExpirationDate, setCurrentExpirationDate] = useState(
		expirationDate
	);
	const [currentStartDate, setCurrentStartDate] = useState(startDate);
	const [disableRenew, setDisableRenew] = useState(true);

	const {observer, onClose} = useModal({
		onClose: closeFn
	});

	useEffect(() => {
		if (
			!isNaN(new Date(currentExpirationDate)) &&
			!isNaN(new Date(currentStartDate)) &&
			validateDateFieldFormat(currentExpirationDate) &&
			validateDateFieldFormat(currentStartDate) &&
			(expirationDate !== currentExpirationDate ||
				startDate !== currentStartDate) &&
			new Date(currentExpirationDate) > new Date(currentStartDate)
		) {
			setDisableRenew(false);
		}
		else {
			setDisableRenew(true);
		}
	}, [currentExpirationDate, currentStartDate, expirationDate, startDate]);

	function handleExpirationDateChange(val) {
		setCurrentExpirationDate(val);
	}

	function handleRenew() {
		renewFn(currentStartDate, currentExpirationDate);
	}

	function handleStartDateChange(val) {
		setCurrentStartDate(val);
	}

	return (
		<ClayModal observer={observer} size="full-screen">
			<ClayModal.Header>{Liferay.Language.get('renew')}</ClayModal.Header>
			<ClayModal.Body>
				<div className="add-items-sheet sheet sheet-lg">
					<div
						className={`form-group form-inline input-text-wrapper ${
							isNaN(new Date(currentStartDate)) ? 'has-error' : ''
						}`}
					>
						<label className="control-label" htmlFor="startDate">
							{Liferay.Language.get('start-date')}
						</label>

						<DatePicker
							defaultValue={startDate}
							id="startDate"
							inputName="startDate"
							updateFn={handleStartDateChange}
						/>
					</div>

					<div
						className={`form-group form-inline input-text-wrapper ${
							isNaN(new Date(currentExpirationDate))
								? 'has-error'
								: ''
						}`}
					>
						<label
							className="control-label"
							htmlFor="expirationDate"
						>
							{Liferay.Language.get('expiration-date')}
						</label>

						<DatePicker
							defaultValue={expirationDate}
							id="expirationDate"
							inputName="expirationDate"
							updateFn={handleExpirationDateChange}
						/>
					</div>
				</div>
			</ClayModal.Body>
			<ClayModal.Footer
				last={
					<ClayButton.Group spaced>
						<ClayButton displayType="secondary" onClick={onClose}>
							{Liferay.Language.get('cancel')}
						</ClayButton>
						<ClayButton
							disabled={disableRenew}
							onClick={handleRenew}
						>
							{Liferay.Language.get('renew')}
						</ClayButton>
					</ClayButton.Group>
				}
			/>
		</ClayModal>
	);
}

RenewalModal.propTypes = {
	closeFn: PropTypes.func.isRequired,
	expirationDate: PropTypes.string.isRequired,
	renewFn: PropTypes.func.isRequired,
	startDate: PropTypes.string.isRequired
};

export default RenewalModal;

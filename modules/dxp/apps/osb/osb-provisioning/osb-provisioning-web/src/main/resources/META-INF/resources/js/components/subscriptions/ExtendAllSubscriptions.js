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

import {formatDate, generateNewDateByDay} from '../../utilities/date';
import HiddenForm from '../HiddenForm';
import ExtendEndDateModal from './ExtendEndDateModal';

export default function ExtendAllSubscriptions({
	extendActiveSubscriptionsURL,
	latestActiveSubscriptionEndDate
}) {
	const formRef = useRef();

	const [endDate, setEndDate] = useState('');
	const [modalVisible, setModalVisible] = useState(false);

	useEffect(() => {
		const listener = event => {
			setModalVisible(event.detail.modalVisible);
		};

		window.addEventListener('extendAllActiveSubscriptions', listener);

		return () =>
			window.removeEventListener(
				'extendAllActiveSubscriptions',
				listener
			);
	});

	useEffect(() => {
		if (endDate && formRef.current) {
			formRef.current.submit();
		}
	}, [endDate]);

	return (
		<>
			{modalVisible && (
				<ExtendEndDateModal
					closeFn={() => setModalVisible(false)}
					extendFn={endDate => setEndDate(endDate)}
					latestActiveSubscriptionEndDate={
						latestActiveSubscriptionEndDate
					}
					newEndDate={generateNewDateByDay(
						latestActiveSubscriptionEndDate
					)}
				/>
			)}

			<HiddenForm
				fields={{
					endDate: formatDate(endDate)
				}}
				formAction={extendActiveSubscriptionsURL}
				formName="extendAllSubscriptions"
				ref={formRef}
			/>
		</>
	);
}

ExtendAllSubscriptions.propTypes = {
	extendActiveSubscriptionsURL: PropTypes.string.isRequired,
	latestActiveSubscriptionEndDate: PropTypes.string.isRequired
};

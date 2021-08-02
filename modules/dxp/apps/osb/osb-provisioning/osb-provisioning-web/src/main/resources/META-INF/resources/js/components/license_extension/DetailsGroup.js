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
import {Map} from 'immutable';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

import {FieldData} from '../../hooks/extendLicenses';
import {formatDate} from '../../utilities/date';
import ExtendButton from './ExtendButton';
import ExtensionDetails from './ExtensionDetails';

export default function DetailsGroup({extensionURL, licenses}) {
	const formRef = useRef();
	const [fieldData, setFieldData] = useState(
		Map(
			licenses.map(license => [
				license.licenseKeyId,
				new FieldData(license)
			])
		)
	);

	function deriveExtendDisabledState() {
		return fieldData.toList().every(data => data.hasValidDates());
	}

	function getFieldData() {
		return fieldData
			.toList()
			.toJS()
			.map(data => ({
				...data,
				...{expirationDate: formatDate(data.expirationDate)},
				...{startDate: formatDate(data.startDate)}
			}));
	}

	function handleDateChange(keyPath, value) {
		setFieldData(fieldData.setIn(keyPath, value));
	}

	function handleOnSubmit() {
		if (formRef.current) {
			formRef.current.submit();
		}
	}

	return (
		<>
			<ExtensionDetails
				extensionURL={extensionURL}
				licenses={licenses}
				updateDate={handleDateChange}
			/>

			{licenses.length !== 1 && (
				<ClayTable.Body>
					<ClayTable.Row>
						<ClayTable.Cell colSpan={6}></ClayTable.Cell>
						<ClayTable.Cell>
							<ExtendButton
								disabled={!deriveExtendDisabledState()}
								fields={{
									licenseKeys: JSON.stringify(getFieldData())
								}}
								formAction={extensionURL}
								ref={formRef}
								submitHandler={handleOnSubmit}
							/>
						</ClayTable.Cell>
						<ClayTable.Cell></ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Body>
			)}
		</>
	);
}

DetailsGroup.propTypes = {
	extensionURL: PropTypes.string,
	licenses: PropTypes.array
};

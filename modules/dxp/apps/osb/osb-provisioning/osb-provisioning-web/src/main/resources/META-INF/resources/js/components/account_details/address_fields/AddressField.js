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

import ClayList from '@clayui/list';
import React, {useState} from 'react';

import {DASH} from '../../../utilities/constants';
import EditableField from '../../EditableField';
import RequiredFieldMarker from '../../RequiredFieldMarker';
import FieldSelect from './FieldSelect';
import FieldText from './FieldText';
import FieldToggle from './FieldToggle';

export const Select = props => FieldWrapper(FieldSelect, {...props});
export const Text = props => FieldWrapper(FieldText, {...props});
export const Toggle = props => FieldWrapper(FieldToggle, {...props});

function FieldWrapper(AddressField, props) {
	const {
		editable,
		fieldLabel,
		readOnly,
		readOnlyValue,
		required = false,
		setEditableFn,
		value
	} = props;
	const [fieldEditable, setFieldEditable] = useState(false);

	function getDisplayValue() {
		if (readOnlyValue) {
			return readOnlyValue;
		}

		if (!value) {
			return DASH;
		}

		return value;
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>
					{fieldLabel} {required && <RequiredFieldMarker />}
				</ClayList.ItemTitle>

				{readOnly && (
					<div className="list-group-text">{getDisplayValue()}</div>
				)}

				{!readOnly && (
					<div className="list-group-text">
						{!editable && (
							<div className="inline-edit">
								<div
									onClick={() => setEditableFn(true)}
									onMouseEnter={() => setFieldEditable(true)}
									onMouseLeave={() => setFieldEditable(false)}
								>
									{fieldEditable ? (
										<EditableField
											value={getDisplayValue()}
										/>
									) : (
										getDisplayValue()
									)}
								</div>
							</div>
						)}

						{editable && <AddressField {...props} />}
					</div>
				)}
			</div>
		</ClayList.Item>
	);
}

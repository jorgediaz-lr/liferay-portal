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

import {ClayToggle} from '@clayui/form';
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

	const displayValue = getDisplayValue();

	function getDisplayValue() {
		if (readOnlyValue) {
			return readOnlyValue;
		}

		if (typeof value === 'boolean') {
			return null;
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

				{readOnly && displayValue && (
					<div className="list-group-text">{displayValue}</div>
				)}

				{readOnly && !displayValue && (
					<ClayToggle
						aria-label={fieldLabel}
						disabled
						toggled={value}
					/>
				)}

				{!readOnly && (
					<div className="list-group-text">
						{!editable && displayValue && (
							<div className="inline-edit">
								<div
									onClick={() => setEditableFn(true)}
									onMouseEnter={() => setFieldEditable(true)}
									onMouseLeave={() => setFieldEditable(false)}
								>
									{fieldEditable ? (
										<EditableField value={displayValue} />
									) : (
										displayValue
									)}
								</div>
							</div>
						)}

						{!editable && !displayValue && (
							<ClayToggle
								aria-label={fieldLabel}
								onToggle={() => setEditableFn(true)}
								toggled={value}
							/>
						)}

						{editable && <AddressField {...props} />}
					</div>
				)}
			</div>
		</ClayList.Item>
	);
}

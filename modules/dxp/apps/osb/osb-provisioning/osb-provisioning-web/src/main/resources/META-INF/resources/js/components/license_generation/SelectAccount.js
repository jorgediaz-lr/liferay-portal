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

import {NAMESPACE} from '../../utilities/constants';
import {itemSelectorDialogSelection} from '../../utilities/itemSelectorDialogHelper';
import ExternalSelectField from '../ExternalSelectField';

function SelectAccount({
	accountKey = '',
	accountName = '',
	actionURL,
	dialogURL
}) {
	const formRef = useRef();

	const [selectedAccountKey, setSelectedAccountKey] = useState(accountKey);
	const [selectedAccountName, setSelectedAccountName] = useState(accountName);

	useEffect(() => {
		if (
			formRef.current &&
			selectedAccountKey !== '' &&
			selectedAccountKey !== accountKey
		) {
			formRef.current.submit();
		}
	}, [accountKey, selectedAccountKey]);

	function handleClick() {
		const assignInputValueFromDialog = fieldData => {
			const {key, name} = JSON.parse(fieldData);

			if (key) {
				setSelectedAccountKey(key);
			}

			if (name) {
				setSelectedAccountName(name);
			}
		};

		itemSelectorDialogSelection(
			{
				formField: 'accountKey',
				title: Liferay.Language.get('select-account'),
				url: dialogURL
			},
			assignInputValueFromDialog
		);
	}

	return (
		<form
			action={actionURL}
			method="post"
			name="selectAccount"
			ref={formRef}
		>
			<input
				name={`${NAMESPACE}accountKey`}
				type="hidden"
				value={selectedAccountKey}
			/>

			<ExternalSelectField
				clickFn={handleClick}
				id={'accountName'}
				value={selectedAccountName}
			/>
		</form>
	);
}

SelectAccount.propTypes = {
	actionURL: PropTypes.string.isRequired,
	dialogURL: PropTypes.string.isRequired
};

export default SelectAccount;

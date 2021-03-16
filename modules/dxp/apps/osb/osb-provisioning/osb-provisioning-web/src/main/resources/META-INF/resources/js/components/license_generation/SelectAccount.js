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

function SelectAccount({actionURL, dialogURL}) {
	const formRef = useRef();

	const [accountKey, setAccountKey] = useState('');
	const [accountName, setAccountName] = useState('');

	useEffect(() => {
		if (formRef.current && accountKey !== '') {
			formRef.current.submit();
		}
	}, [accountKey]);

	function handleClick() {
		const assignInputValueFromDialog = fieldData => {
			const {key, name} = JSON.parse(fieldData);

			if (key) {
				setAccountKey(key);
			}

			if (name) {
				setAccountName(name);
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
				value={accountKey}
			/>

			<ExternalSelectField
				clickFn={handleClick}
				id={'accountName'}
				value={accountName}
			/>
		</form>
	);
}

SelectAccount.propTypes = {
	actionURL: PropTypes.string.isRequired,
	dialogURL: PropTypes.string.isRequired
};

export default SelectAccount;

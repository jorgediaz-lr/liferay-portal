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
import React from 'react';

import HiddenForm from '../HiddenForm';

const ExtendButton = React.forwardRef(
	({disabled = false, fields, formAction, submitHandler}, ref) => (
		<>
			<HiddenForm
				fields={fields}
				formAction={formAction}
				formName="extendLicensesFm"
				ref={ref}
			/>

			<button
				className="btn btn-secondary btn-sm"
				disabled={disabled}
				onClick={submitHandler}
				role="button"
				type="button"
			>
				{Liferay.Language.get('extend')}
			</button>
		</>
	)
);

ExtendButton.propTypes = {
	disabled: PropTypes.bool,
	fields: PropTypes.object.isRequired,
	formAction: PropTypes.string.isRequired,
	submitHandler: PropTypes.func.isRequired
};

export default ExtendButton;

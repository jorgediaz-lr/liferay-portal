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

function EditableField({value}) {
	return (
		<div className="editable-field">
			<div className="field">{value}</div>
			<div className="edit-icon">
				<svg
					aria-label={Liferay.Language.get('edit-field-icon')}
					className="lexicon-icon-pencil"
					role="img"
				>
					<use xlinkHref="#pencil" />
				</svg>
			</div>
		</div>
	);
}

EditableField.propTypes = {
	value: PropTypes.oneOfType([PropTypes.bool, PropTypes.string]).isRequired
};

export default EditableField;

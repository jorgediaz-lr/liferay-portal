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

import ClayDatePicker from '@clayui/date-picker';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';

function Dates() {
	return (
		<div className="panel-body">
			<div className="col-md-12 form-group">
				<label htmlFor="createdByEmailAddress">
					{Liferay.Language.get('created-by')}
				</label>
				<input
					className="form-control form-control-sm"
					id="createdByEmailAddress"
					name={`${NAMESPACE}createdByEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-liferay-email-address')}
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="createdAfter">
					{Liferay.Language.get('created-after')}
				</label>

				<DatePicker
					id="createdAfter"
					inputName={`${NAMESPACE}createDateGT`}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="createdBefore">
					{Liferay.Language.get('created-before')}
				</label>

				<DatePicker
					id="createdBefore"
					inputName={`${NAMESPACE}createDateLT`}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="modifiedAfter">
					{Liferay.Language.get('modified-after')}
				</label>

				<DatePicker
					id="modifiedAfter"
					inputName={`${NAMESPACE}modifiedDateGT`}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="modifiedBefore">
					{Liferay.Language.get('modified-before')}
				</label>

				<DatePicker
					id="modifiedBefore"
					inputName={`${NAMESPACE}modifiedDateLT`}
				/>
			</div>
		</div>
	);
}

function DatePicker({id, inputName}) {
	const [value, setValue] = useState('');

	return (
		<ClayDatePicker
			className="form-control-sm"
			id={id}
			inputName={inputName}
			onValueChange={setValue}
			placeholder="YYYY-MM-DD"
			value={value}
		/>
	);
}

export default Dates;

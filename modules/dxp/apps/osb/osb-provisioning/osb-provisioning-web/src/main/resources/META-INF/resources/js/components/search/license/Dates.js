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

import React from 'react';

import {NAMESPACE} from '../../../utilities/constants';
import DatePicker from '../../DatePicker';

function Dates() {
	return (
		<div className="panel-body panel-dates">
			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('created-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="createdAfter">
							{Liferay.Language.get('start-date')}
						</label>

						<DatePicker
							id="createdAfter"
							inputName={`${NAMESPACE}createDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="createdBefore">
							{Liferay.Language.get('end-date')}
						</label>

						<DatePicker
							id="createdBefore"
							inputName={`${NAMESPACE}createDateLT`}
						/>
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('modified-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="modifiedAfter">
							{Liferay.Language.get('start-date')}
						</label>

						<DatePicker
							id="modifiedAfter"
							inputName={`${NAMESPACE}modifiedDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="modifiedBefore">
							{Liferay.Language.get('end-date')}
						</label>

						<DatePicker
							id="modifiedBefore"
							inputName={`${NAMESPACE}modifiedDateLT`}
						/>
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('started-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="startedAfter">
							{Liferay.Language.get('start-date')}
						</label>

						<DatePicker
							id="startedAfter"
							inputName={`${NAMESPACE}startDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="startedBefore">
							{Liferay.Language.get('end-date')}
						</label>

						<DatePicker
							id="startedBefore"
							inputName={`${NAMESPACE}startDateLT`}
						/>
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('expires-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="expiresAfter">
							{Liferay.Language.get('start-date')}
						</label>

						<DatePicker
							id="expiresAfter"
							inputName={`${NAMESPACE}expirationDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="expiresBefore">
							{Liferay.Language.get('end-date')}
						</label>

						<DatePicker
							id="expiresBefore"
							inputName={`${NAMESPACE}expirationDateLT`}
						/>
					</div>
				</div>
			</div>
		</div>
	);
}

export default Dates;

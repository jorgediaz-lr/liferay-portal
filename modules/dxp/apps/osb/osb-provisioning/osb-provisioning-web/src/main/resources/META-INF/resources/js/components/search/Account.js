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
import React, {useState} from 'react';

import {FIELD_SIZE_SMALL, NAMESPACE} from '../../utilities/constants';
import itemSelectorDialogWrapper from '../../utilities/itemSelectorDialogWrapper';
import ExternalSelectField from '../ExternalSelectField';

function Account({
	countryNames,
	selectAccountURL,
	selectFirstLineSupportURL,
	selectPartnerURL
}) {
	return (
		<div className="panel-body">
			<div className="col-md-6 form-group">
				<label htmlFor="name">
					{Liferay.Language.get('account-name')}
				</label>
				<input
					className="form-control form-control-sm"
					id="name"
					name={`${NAMESPACE}name`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="code">{Liferay.Language.get('code')}</label>
				<input
					className="form-control form-control-sm"
					id="code"
					name={`${NAMESPACE}code`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('parent-account')}
				</h5>

				<ExternalSelect
					externalData={{
						formField: 'parentAccountKey',
						formName: `${NAMESPACE}editAccountHierarchyFm`,
						title: Liferay.Language.get('select-parent-account'),
						url: selectAccountURL
					}}
					id="parentAccountKey"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="workerContactEmailAddress">
					{Liferay.Language.get('project-worker')}
				</label>
				<input
					className="form-control form-control-sm"
					id="workerContactEmailAddress"
					name={`${NAMESPACE}workerContactEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-liferay-email-address')}
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('partner-reseller-si')}
				</h5>

				<ExternalSelect
					externalData={{
						formField: 'partnerTeamKey',
						formName: `${NAMESPACE}updatePartnerFm`,
						title: Liferay.Language.get('select-partner-team'),
						url: selectPartnerURL
					}}
					id="partnerTeamKey"
				/>
			</div>

			<div className="col-md-6 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('first-line-support')}
				</h5>

				<ExternalSelect
					externalData={{
						formField: 'flsTeamKey',
						formName: `${NAMESPACE}updateFirstLineSupportFm`,
						title: Liferay.Language.get(
							'select-first-line-support-team'
						),
						url: selectFirstLineSupportURL
					}}
					id="flsTeamKey"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="countryName">
					{Liferay.Language.get('country')}
				</label>
				<select
					className="form-control form-control-sm"
					id="countryName"
					name={`${NAMESPACE}countryName`}
				>
					<option></option>
					{countryNames.map(name => (
						<option key={name} value={name}>
							{name}
						</option>
					))}
				</select>
			</div>
		</div>
	);
}

Account.propTypes = {
	countryNames: PropTypes.array.isRequired,
	selectAccountURL: PropTypes.string.isRequired,
	selectFirstLineSupportURL: PropTypes.string.isRequired,
	selectPartnerURL: PropTypes.string.isRequired
};

function ExternalSelect({externalData, id}) {
	const [value, setValue] = useState('');

	function handleClick() {
		itemSelectorDialogWrapper(externalData);

		setValue('1');
	}

	return (
		<ExternalSelectField
			clickFn={handleClick}
			id={id}
			inputSize={FIELD_SIZE_SMALL}
			value={value}
		/>
	);
}

export default Account;

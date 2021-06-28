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
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {usePermissions} from '../../hooks/permissions';
import {
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TOGGLE,
	NAMESPACE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import EditableField from '../EditableField';
import IconButton from '../IconButton';
import RequiredFieldMarker from '../RequiredFieldMarker';

function Address({accountKey, addFn, address, count, countryOptions}) {
	const [editable, setEditable] = useState(false);
	const [country, setCountry] = useState(
		convertDashToEmptyString(address.addressCountry)
	);
	const [zipCode, setZipCode] = useState(
		convertDashToEmptyString(address.postalCode)
	);
	const [selectedCountry, setSelectedCountry] = useState();

	const formRef = useRef();

	const {updatePermission} = usePermissions();

	useEffect(() => {
		setSelectedCountry(countryOptions.find(({name}) => name === country));
	}, [country, countryOptions]);

	function getRegionOptions() {
		return selectedCountry
			? selectedCountry.countryRegions
			: selectedCountry;
	}

	function getZipcodeRequirement() {
		return selectedCountry ? selectedCountry.zipRequired : false;
	}

	function handleCancel() {
		location.reload();
	}

	function handleCountryUpdate(value) {
		setCountry(value);
	}

	function handleOnClick(bool) {
		setEditable(bool);
	}

	function handlePostalCodeUpdate(value) {
		setZipCode(value);
	}

	function handleSave() {
		formRef.current.submit();
	}

	return (
		<form
			action={address.editPostalAddressURL}
			key={address.id}
			method="post"
			ref={formRef}
		>
			<input
				name={`${NAMESPACE}accountKey`}
				type="hidden"
				value={accountKey}
			/>

			<ClayList>
				<ClayList.Header>
					{Liferay.Language.get('address')} {count}
				</ClayList.Header>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('street-1')}
					fieldName="streetAddressLine1"
					onClick={handleOnClick}
					readOnly={!updatePermission}
					required
					value={address.streetAddressLine1}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('city')}
					fieldName="addressLocality"
					onClick={handleOnClick}
					readOnly={!updatePermission}
					required
					value={address.addressLocality}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('street-2')}
					fieldName="streetAddressLine2"
					onClick={handleOnClick}
					readOnly={!updatePermission}
					value={address.streetAddressLine2}
				/>

				<AddressField
					displayValue={address.addressRegion}
					editable={editable}
					fieldLabel={Liferay.Language.get('state-province')}
					fieldName="addressRegionName"
					onClick={handleOnClick}
					options={getRegionOptions()}
					readOnly={!updatePermission}
					type={FIELD_TYPE_SELECT}
					value={address.addressLocality}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('street-3')}
					fieldName="streetAddressLine3"
					onClick={handleOnClick}
					readOnly={!updatePermission}
					value={address.streetAddressLine3}
				/>

				<AddressField
					editable={editable}
					fieldLabel={Liferay.Language.get('postal-code')}
					fieldName="addressZip"
					onClick={handleOnClick}
					readOnly={!updatePermission}
					required={getZipcodeRequirement()}
					updateFn={handlePostalCodeUpdate}
					value={address.postalCode}
				/>

				<AddressField
					displayValue={address.addressCountry}
					editable={editable}
					fieldLabel={Liferay.Language.get('country')}
					fieldName="addressCountryName"
					onClick={handleOnClick}
					options={countryOptions}
					readOnly={!updatePermission}
					type={FIELD_TYPE_SELECT}
					updateFn={handleCountryUpdate}
					value={country}
				/>

				<AddressField
					displayValue={
						address.primary
							? Liferay.Language.get('yes')
							: Liferay.Language.get('no')
					}
					editable={editable}
					fieldLabel={Liferay.Language.get('primary')}
					fieldName="addressPrimary"
					onClick={handleOnClick}
					readOnly={!updatePermission}
					type={FIELD_TYPE_TOGGLE}
					value={address.primary}
				/>

				{updatePermission && (
					<ClayList.Item
						className={`address-controls ${
							editable ? 'editing' : ''
						}`}
						flex
					>
						{editable && (
							<div className="btn-group" role="group">
								<div className="btn-group-item">
									<button
										className="btn btn-primary btn-sm save-btn"
										disabled={
											getZipcodeRequirement() && !zipCode
										}
										onClick={handleSave}
										role="button"
										type="button"
									>
										{Liferay.Language.get('save')}
									</button>
								</div>

								<div className="btn-group-item">
									<button
										className="btn btn-secondary btn-sm cancel-btn"
										onClick={handleCancel}
										role="button"
										type="button"
									>
										{Liferay.Language.get('cancel')}
									</button>
								</div>
							</div>
						)}

						{!!address.id && (
							<div className="btn-group" role="group">
								<div className="btn-group-item">
									<IconButton
										cssClass="add-address btn btn-secondary nav-btn nav-btn-monospaced"
										labelName={Liferay.Language.get('add')}
										onClick={addFn}
										svgId="#plus"
										title={Liferay.Language.get('add')}
									/>
								</div>

								{!!address.deletePostalAddressURL && (
									<div className="btn-group-item">
										<IconButton
											cssClass="btn-secondary delete-address nav-btn nav-btn-monospaced"
											labelName={Liferay.Language.get(
												'delete'
											)}
											onClick={() => {
												if (
													window.confirm(
														Liferay.Language.get(
															'are-you-sure-you-want-to-delete-this-address'
														)
													)
												) {
													window.location.assign(
														address.deletePostalAddressURL
													);
												}
											}}
											svgId="#hr"
											title={Liferay.Language.get(
												'delete'
											)}
										/>
									</div>
								)}
							</div>
						)}
					</ClayList.Item>
				)}
			</ClayList>
		</form>
	);
}

Address.propTypes = {
	accountKey: PropTypes.string,
	addFn: PropTypes.func.isRequired,
	address: PropTypes.shape({
		addressCountry: PropTypes.string,
		addressLocality: PropTypes.string,
		deletePostalAddressURL: PropTypes.string,
		editPostalAddressURL: PropTypes.string,
		id: PropTypes.string,
		postalCode: PropTypes.string,
		primary: PropTypes.bool,
		streetAddressLine1: PropTypes.string,
		streetAddressLine2: PropTypes.string,
		streetAddressLine3: PropTypes.string
	}),
	count: PropTypes.number,
	countryOptions: PropTypes.arrayOf(
		PropTypes.shape({
			active: PropTypes.bool,
			countryRegions: PropTypes.array,
			name: PropTypes.string,
			zipRequired: PropTypes.bool
		})
	)
};

function AddressField({
	displayValue,
	editable = false,
	fieldLabel,
	fieldName,
	onClick,
	options = [],
	readOnly,
	required = false,
	type = FIELD_TYPE_TEXT,
	updateFn,
	value
}) {
	const [fieldEditable, setFieldEditable] = useState(false);
	const [fieldValue, setFieldValue] = useState(value);

	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	useEffect(() => {
		setFieldValue(value);
	}, [value]);

	function handleChange(event) {
		const currentTarget = event.currentTarget;

		setFieldValue(currentTarget.value);

		if (updateFn) {
			updateFn(currentTarget.value);
		}
	}

	function handleToggle() {
		setFieldValue(!fieldValue);
	}

	function getDisplayValue() {
		return displayValue ? displayValue : fieldValue;
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
									onClick={() => onClick(true)}
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

						{editable && type === FIELD_TYPE_SELECT && (
							<label
								className="form-control-label"
								htmlFor={namespacedFieldName}
							>
								<select
									className="form-control"
									disabled={options.length === 0}
									id={namespacedFieldName}
									name={namespacedFieldName}
									onChange={handleChange}
									value={convertDashToEmptyString(fieldValue)}
								>
									<option value=""></option>
									{options.map((option, index) => (
										<option
											key={option.name || index}
											value={option.name}
										>
											{option.name}
										</option>
									))}
								</select>
							</label>
						)}

						{editable && type === FIELD_TYPE_TEXT && (
							<label
								className="form-control-label"
								htmlFor={namespacedFieldName}
							>
								<input
									className="form-control"
									id={namespacedFieldName}
									name={namespacedFieldName}
									onChange={handleChange}
									type="text"
									value={convertDashToEmptyString(fieldValue)}
								/>
							</label>
						)}

						{editable && type === FIELD_TYPE_TOGGLE && (
							<label
								className="simple-toggle-switch toggle-switch"
								htmlFor={namespacedFieldName}
							>
								<span className="toggle-switch-check-bar">
									<input
										checked={fieldValue}
										className="toggle-switch-check"
										id={namespacedFieldName}
										name={namespacedFieldName}
										onChange={handleToggle}
										type="checkbox"
										value={fieldValue}
									/>
									<span
										aria-hidden="true"
										className="toggle-switch-bar"
									>
										<span className="toggle-switch-handle"></span>
									</span>
								</span>
							</label>
						)}
					</div>
				)}
			</div>
		</ClayList.Item>
	);
}

export default Address;

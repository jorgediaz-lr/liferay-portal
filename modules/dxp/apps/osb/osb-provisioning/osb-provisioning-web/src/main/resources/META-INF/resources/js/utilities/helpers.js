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

import axios from 'axios';

import {NAMESPACE} from '../utilities/constants';

/**
 * Certain empty values are represented by a dash in the UI.
 * This helper converts that value from its dash representation to its actual
 * value.
 * @param {string} value The value to be evaluated
 * @returns {string} The value after it's checked
 */
export function convertDashToEmptyString(value) {
	return value === '-' ? '' : value;
}

/**
 * This helper takes the incoming value and converts any non empty string value
 * to a Date object.
 * @param {Object|string} value Date value from user input, could be a date
 * object from selecting a date picker or a string from manual input.
 * @returns {Object|string} Date object or empty string.
 */
export function convertInputToDate(value) {
	return value === '' ? '' : new Date(value);
}

/**
 * Date objects are by default in Pacific Standard Time (PST).
 * This helper ensures dates are displayed in UTC.
 * @param {object} date The date object.
 * @returns {string} The string reprensetation of the UTC date.
 */
export function displayUTCDate(date) {
	const match = JSON.stringify(date).match(/"(?<utcDate>\d+-\d+-\d+)T/);

	return (match && match.groups.utcDate) || date;
}

/**
 * Returns a promise of the request data
 * @param {string} endpoint The endpoint to post to
 * @param {object} params The parameters object to post with
 * @param {string} encoding The data encoding for the request. Defaults to JSON.
 * @param {string} method The desired action to be performed for a given resource. Defaults to the GET method.
 * @returns {Promise} A Promise of the object that results from the Request
 */
export function request(endpoint, params, encoding = 'json', method = 'get') {
	const namespacedParams = {};

	if (encoding === 'json') {
		Object.entries(params)
			.map(([key, value]) => [`${NAMESPACE}${key}`, value])
			.forEach(([key, value]) => {
				namespacedParams[key] = value;
			});
	}

	let namespacedData = null;

	if (encoding === 'formData') {
		namespacedData = new FormData();

		Object.entries(params).forEach(([key, value]) =>
			namespacedData.append(`${NAMESPACE}${key}`, value)
		);
	}

	return axios.request({
		data: namespacedData,
		method,
		params: namespacedParams,
		url: endpoint
	});
}

/**
 * Source formatter locks @clayui/date-picker at version 3.0.7, which does not
 * provide an API for disabling/enabling date picker while later versions do.
 * This helper manually disables/enables the date picker.
 * @param {boolean} attributeValue The value, whether to disable or enable.
 * @param {string} identifier The target to disable.
 */
export function setDisabledAttribute(attributeValue, identifier) {
	const dates = document.querySelectorAll(`#${identifier} .date-picker`);

	dates.forEach(date => {
		const dateBtn = date.querySelector('.date-picker-dropdown-toggle');
		const dateInput = date.querySelector('input.form-control');

		if (dateBtn && dateInput) {
			if (attributeValue) {
				dateBtn.setAttribute('disabled', attributeValue);
				dateInput.setAttribute('disabled', attributeValue);
			}
			else {
				dateBtn.removeAttribute('disabled');
				dateInput.removeAttribute('disabled');
			}
		}
	});
}

/**
 * Validates user input from the Date picker, which could be either manual
 * input (string) in the format of YYYY-MM-DD or selection through the date
 * picker (date object).
 * This helper is used to verify the user has finished inputting the expected
 * the date in the input fields before attempting to save.
 * @param {Object|string} value Date value from user input.
 * @returns {boolean}
 */
export function validateDateFieldValue(value) {
	if (typeof value === 'string' && value !== '') {
		return /\d{4}-\d{2}-\d{2}/.test(value);
	}
	else {
		return true;
	}
}

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

/**
 * This helper takes a date object and converts it to a UTC date with the day
 * starts at midnight.
 * @param {Object} Date A date object.
 * @returns {Object} A new Date object representing UTC
 */
export function convertDateToUTCDate(date) {
	const year = date.getUTCFullYear();
	const month = date.getUTCMonth();
	const day = date.getUTCDate();

	return new Date(year, month, day);
}

/**
 * This helper takes a date object and converts it to the start of the day with
 * the hour set at midnight. Note, this is to get around Clay Date Picker
 * setting date at noon.
 * @param {Object} Date A date object.
 * @returns {Object} A new Date object starting at midnight
 */
export function convertDateToStartOfDate(date) {
	const year = date.getFullYear();
	const month = date.getMonth();
	const day = date.getDate();

	return new Date(year, month, day);
}

/**
 * This helper takes the incoming value, date or string and converts them to a
 * new Date object representing UTC with the hour set at midnight.
 * @param {Object|string} value Date value from user input, could be a date
 * object from selecting a date picker or a string value from manual input.
 * @returns {Object|string} Date object or empty string.
 */
export function convertInputToDate(value) {
	return value === '' ? '' : convertDateToStartOfDate(new Date(value));
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

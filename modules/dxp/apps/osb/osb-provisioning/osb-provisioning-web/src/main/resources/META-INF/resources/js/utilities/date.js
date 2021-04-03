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
 * Takes the possible incoming value, date or string, and converts
 * it to a Date object. Except in the case of an empty string, in which case it
 * passes through.
 * @param {Object|string} value Date value from user input, could be a date
 * object from the dropdown date picker or string from input field.
 * @returns {Object|string} Date object or empty string.
 */
export function convertInputToDate(value) {
	return value === '' ? '' : new Date(value);
}

/**
 * Displays a date object in the MDY format.
 * @param {Object} date Date to be formatted.
 * @returns {string} String representation of the date in MDY format
 */
export function displayInMDYDateFormat(date) {
	return new Intl.DateTimeFormat('en-US', {
		day: 'numeric',
		month: 'long',
		year: 'numeric'
	}).format(date);
}

/**
 * Generates a new date based on the starting point and the offset in years
 * indicated.
 * @param {Object|string} startDate Starting point in which to generate the new
 * date from. If invalid or missing, will default to today's date.
 * @param {number} offset Offset in years (positive or negative) from the
 * starting date.
 * @returns {Object} Date object.
 */
export function generateNewDate(startDate = new Date(), offset = 1) {
	let startDateCopy = new Date(startDate);

	if (isNaN(startDateCopy)) {
		startDateCopy = new Date();
	}

	const newEndYear = startDateCopy.getUTCFullYear() + offset;

	return new Date(startDateCopy.setFullYear(newEndYear));
}

/**
 * Generates a new date adjusted for UTC.
 * @param {*} value Any value intended to represent a date.
 * @returns {*} New date object adjusted for UTC or the original input value if
 * not a valid Date object.
 */
export function getUTCAdjustedDate(value) {
	if (value instanceof Date) {
		const utcAdjustedDate = new Date(value.getTime());

		utcAdjustedDate.setHours(
			utcAdjustedDate.getHours() +
				utcAdjustedDate.getTimezoneOffset() / 60
		);

		return utcAdjustedDate;
	}

	return value;
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

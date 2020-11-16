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

export function addClickOutsideListener(listener) {
	document.addEventListener('mousedown', listener);
}

/**
 * Certain empty values are represented by a dash in the UI.
 * This helper converts that value from its dash representation to its true
 * value.
 * @param {string} value The value to be evaluated
 * @returns {string} The value after it's checked
 */
export function convertDashToEmptyString(value) {
	return value === '-' ? '' : value;
}

export function handleClickOutside(callback, ref, event) {
	const clickOutside = ref && !ref.contains(event.target);

	if (clickOutside) {
		callback();
	}
}

export function removeClickOutsideListener(listener) {
	document.removeEventListener('mousedown', listener);
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

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
import groupBy from 'lodash.groupby';

import {IPV4, MACADDRESS, NAMESPACE} from '../utilities/constants';

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
 * Generalized recursive grouping algorithm that groups the input based on the 
 * callbacks provided. 
 * @param {Array} items An array of objects to be grouped
 * @callback groupFns Callbacks to group the inputs 
 * @returns {Array} The grouped result
 */
export function groupByAll(items, ...groupFns) {
	if (groupFns.length === 0) {
		return [items];
	}

	const [groupFn, ...restGroupFns] = groupFns;
	const grouped = groupBy(items, groupFn);
	const result = [];

	// Suppress eslint false alarm for unused var
	/* eslint-disable no-unused-vars */

	/* eslint-disable-next-line no-for-of-loops/no-for-of-loops */
	for (const group of Object.values(grouped)) {
		result.push(...groupByAll(group, ...restGroupFns));
	}
	/* eslint-enable no-unused-vars */

	return result;
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
 * Submits a form when the user presses the Enter key
 * @param {object} event The event to check the key that was pressed.
 * @param {object} formRef Ref of the form to be submitted.
 */
export function submitOnEnter(event, formRef) {
	if (event.keyCode === 13) {
		formRef.current.submit();
	}
}

/**
 * Takes an input and evaluates whether the input contains a single or multiple
 * valid IPv4 addresses.
 * @param {string} input The value to be evaluated
 * @returns {boolean} Whether the input is valid or not
 */
export function validateIPv4s(input) {
	if (input) {
		const chuncks = input.trim().split(/\s*,\s*|\s+/);

		return chuncks.every(chunck => chunck.match(IPV4));
	} else {
		return false;
	}
}

/**
 * Takes an input and evaluates whether the input contains a single or multiple
 * valid MAC addresses.
 * @param {string} input The value to be evaluated
 * @returns {boolean} Whether the input is valid or not
 */
export function validateMAC(input) {
	if (input) {
		const chuncks = input.trim().split(/\s*,\s*|\s+/);

		return chuncks.every(chunck => chunck.match(MACADDRESS));
	} else {
		return false;
	}
}

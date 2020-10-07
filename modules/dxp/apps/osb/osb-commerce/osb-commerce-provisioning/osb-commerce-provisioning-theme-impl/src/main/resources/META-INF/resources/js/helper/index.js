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

import {fetch} from 'frontend-js-web';

const ADD_TO_ORDER_ENDPOINT = '/o/commerce-ui/cart-item',
	MAX_PRODUCT_QUANTITY = '1';

export const GUEST_ID = '-1';

export const TRIAL_SKU = 'TRIAL101';

export function addToOrder(commerceAccountId, productId, options = '[]') {
	const formData = new FormData();

	formData.append('commerceAccountId', commerceAccountId);
	formData.append('groupId', Liferay.ThemeDisplay.getScopeGroupId());
	formData.append('languageId', Liferay.ThemeDisplay.getLanguageId());
	formData.append('options', options);
	formData.append('productId', productId);
	formData.append('quantity', MAX_PRODUCT_QUANTITY);

	return fetch(ADD_TO_ORDER_ENDPOINT, {body: formData, method: 'POST'})
		.then(({ok}) => (ok ? Promise.resolve() : Promise.reject()))
		.catch(error => {
			console.error(error);
		});
}

export function mapToFeatures(stringifiedJSON) {
	try {
		const options = JSON.parse(stringifiedJSON);

		return options.map(({value}) => `${value}`);
	}
	catch (ignore) {
		return [];
	}
}

export function getCurrentURLParameter(parameterName) {
	const URLParameters = new URL(window.location.href).searchParams;

	return URLParameters.get(parameterName);
}

export function hasURLComponent(componentName) {
	return window.location.href.indexOf(componentName) > -1;
}

export const OSBCommerceCookieUtil = {
	COOKIE_SCOPE: 'OSB_COMMERCE_PROVISIONING_',

	getValue(key) {
		const [, value] = document.cookie.split(`${this.COOKIE_SCOPE}${key}=`);

		return !value ? null : value.split(';')[0];
	},

	delete(key) {
		const value = this.getValue(key);

		value && this.set(key, value, 0);
	},

	set(key, value, expires, path = '/') {
		const expirationDate =
			expires instanceof Date ? expires : new Date(expires);

		const cookieValue = `${this.COOKIE_SCOPE}${key}=${value};`,
			cookieExp = `expires=${expirationDate.toUTCString()};`,
			cookiePath = `path=${path};`;

		document.cookie = `${cookieValue}${cookieExp}${cookiePath}`;
	}
};

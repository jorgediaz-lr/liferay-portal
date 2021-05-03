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

import {
	groupBy,
	groupByAll,
	validateIPv4s,
	validateMAC
} from '../../src/main/resources/META-INF/resources/js/utilities/helpers';

describe('groupBy', () => {
	it('groups the input correctly', () => {
		const data = [
			{a: 3, b: 1},
			{a: 3, b: 2}
		];

		expect(groupBy(data, item => item.a)).toEqual({
			3: [
				{a: 3, b: 1},
				{a: 3, b: 2}
			]
		});
	});
});

describe('groupByAll', () => {
	it('groups the input appropriately', () => {
		const data = [
			{a: 3, b: 2, c: 5},
			{a: 3, b: 2, c: 6},
			{a: 3, b: 1, c: 6},
			{a: 3, b: 1, c: 6},
			{a: 3, b: 1, c: 2},
			{a: 2, b: 3, c: 1, d: 10},
			{a: 2, b: 3, c: 1, d: 12}
		];

		expect(
			groupByAll(
				data,
				item => item.a,
				item => item.b,
				item => item.c
			)
		).toEqual([
			[
				{a: 2, b: 3, c: 1, d: 10},
				{a: 2, b: 3, c: 1, d: 12}
			],
			[{a: 3, b: 1, c: 2}],
			[
				{a: 3, b: 1, c: 6},
				{a: 3, b: 1, c: 6}
			],
			[{a: 3, b: 2, c: 5}],
			[{a: 3, b: 2, c: 6}]
		]);
	});
});

describe('validateIPv4s', () => {
	it('validates multiple valid IP addresses deliminated via comma, space, or new line', () => {
		expect(
			validateIPv4s(
				`127.0.0.1,\n123.1.2.3, 192.168.1.101\n\n0.0.0.0,172.16.254.1 98.139.180.149\n,8.8.8.8\r255.255.255.0 `
			)
		).toBeTruthy();
	});

	it('validates multiple IP addresses containing invalid values correctly', () => {
		expect(validateIPv4s('0.0.0.0.0, 127.0.0.1')).toBeFalsy();
	});

	it('validates a single valid IP address correctly', () => {
		expect(validateIPv4s('127.0.0.1')).toBeTruthy();
	});

	it('validates a single invalid IP address correctly', () => {
		expect(validateIPv4s('257.0.0.2')).toBeFalsy();

		expect(validateIPv4s('2343.22.22')).toBeFalsy();
	});

	it('handles empty input', () => {
		expect(validateIPv4s('')).toBeFalsy();
	});

	it('handles null input', () => {
		expect(validateIPv4s(null)).toBeFalsy();
	});

	it('handles undefined input', () => {
		expect(validateIPv4s(undefined)).toBeFalsy();
	});
});

describe('validateMAC', () => {
	it('validates a MAC address seperated with two digit octets correctly', () => {
		expect(validateMAC('00:00:0A:BB:28:FC')).toBeTruthy();
		expect(validateMAC('00.00.0A.BB.28.FC')).toBeTruthy();
		expect(validateMAC('01-02-03-04-ab-cd')).toBeTruthy();
	});

	it('validates a MAC address separated with four digit octets correctly', () => {
		expect(validateMAC('0000:0ABB:28FC')).toBeTruthy();
		expect(validateMAC('0000.0ABB.28FC')).toBeTruthy();
		expect(validateMAC('0000-0ABB-28FC')).toBeTruthy();
	});

	it('validates multiple MAC address with different formats correctly', () => {
		expect(validateMAC('00:00:0A:BB:28:FC, 0000-0ABB-28FC')).toBeTruthy();
	});

	it('validates invalid MAC address correctly', () => {
		expect(validateMAC('zz-00-34-xx-35-64')).toBeFalsy();
	});

	it('handles empty input', () => {
		expect(validateMAC('')).toBeFalsy();
	});

	it('handles null input', () => {
		expect(validateMAC(null)).toBeFalsy();
	});

	it('handles undefined input', () => {
		expect(validateMAC(undefined)).toBeFalsy();
	});
});

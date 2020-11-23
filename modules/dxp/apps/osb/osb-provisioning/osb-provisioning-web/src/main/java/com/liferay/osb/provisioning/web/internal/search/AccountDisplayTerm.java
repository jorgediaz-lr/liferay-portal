/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.web.internal.search;

/**
 * @author Rebecca Dai
 */
public class AccountDisplayTerm {

	public AccountDisplayTerm(String label, String name, String value) {
		_label = label;
		_name = name;
		_value = value;
	}

	public String getLabel() {
		return _label;
	}

	public String getName() {
		return _name;
	}

	public String getValue() {
		return _value;
	}

	private final String _label;
	private final String _name;
	private final String _value;

}
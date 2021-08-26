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

package com.liferay.osb.koroneiki.phloem.rest.internal;

import com.liferay.petra.lang.CentralizedThreadLocal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class PhloemNestedFieldsContextThreadLocal {

	public static void addContextName(String name) {
		LinkedList<String> contextNames = _contextNamesThreadLocal.get();

		contextNames.add(name);
	}

	public static Object getContextValue(String name) {
		Map<String, Object> contextValuesMap = _contextValuesThreadLocal.get();

		return contextValuesMap.get(name);
	}

	public static String getLastContextName() {
		LinkedList<String> contextNames = _contextNamesThreadLocal.get();

		if (contextNames.isEmpty()) {
			return null;
		}

		return contextNames.getLast();
	}

	public static void setContextValue(String name, Object value) {
		Map<String, Object> contextValuesMap = _contextValuesThreadLocal.get();

		contextValuesMap.put(name, value);
	}

	private static final ThreadLocal<LinkedList<String>>
		_contextNamesThreadLocal = new CentralizedThreadLocal<>(
			PhloemNestedFieldsContextThreadLocal.class +
				"._contextNamesThreadLocal",
			LinkedList::new);
	private static final ThreadLocal<Map<String, Object>>
		_contextValuesThreadLocal = new CentralizedThreadLocal<>(
			PhloemNestedFieldsContextThreadLocal.class +
				"._contextValuesThreadLocal",
			HashMap::new);

}
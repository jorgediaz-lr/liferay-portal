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

package com.liferay.osb.provisioning.license.helper.constants;

import com.liferay.petra.string.StringPool;

/**
 * @author Kyle Bischof
 */
public class LicenseSizing {

	public static final String FOUR = "sizing-4";

	public static final String ONE = "sizing-1";

	public static final String THREE = "sizing-3";

	public static final String TWO = "sizing-2";

	public static String getLabel(String sizing) {
		if (sizing.equals(ONE)) {
			return "Sizing 1";
		}
		else if (sizing.equals(TWO)) {
			return "Sizing 2";
		}
		else if (sizing.equals(THREE)) {
			return "Sizing 3";
		}
		else if (sizing.equals(FOUR)) {
			return "Sizing 4";
		}

		return StringPool.BLANK;
	}

}
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

	public static final int SIZING_1 = 1;

	public static final int SIZING_2 = 2;

	public static final int SIZING_3 = 3;

	public static final int SIZING_4 = 4;

	public static String getSizingLabel(int sizing) {
		if (sizing == SIZING_1) {
			return "sizing-1";
		}
		else if (sizing == SIZING_2) {
			return "sizing-2";
		}
		else if (sizing == SIZING_3) {
			return "sizing-3";
		}
		else if (sizing == SIZING_4) {
			return "sizing-4";
		}

		return StringPool.BLANK;
	}

}
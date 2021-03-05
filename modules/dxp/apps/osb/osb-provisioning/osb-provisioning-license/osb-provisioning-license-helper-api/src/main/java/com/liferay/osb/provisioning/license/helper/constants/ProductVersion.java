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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class ProductVersion {

	public static final String COMMERCE_LICENSE_VERSION_1 = "1";

	public static final String DXP_VERSION_7_0 = "7.0";

	public static final String DXP_VERSION_7_1 = "7.1";

	public static final String DXP_VERSION_7_2 = "7.2";

	public static final String DXP_VERSION_7_3 = "7.3";

	public static final String DXP_VERSION_7_4 = "7.4";

	public static final String[] DXP_VERSIONS = {
		DXP_VERSION_7_0, DXP_VERSION_7_1, DXP_VERSION_7_2, DXP_VERSION_7_3,
		DXP_VERSION_7_4
	};

	public static final String PORTAL_MINOR_VERSION_6_1 = "6.1";

	public static final String PORTAL_MINOR_VERSION_6_2 = "6.2";

	public static final String PORTAL_VERSION_6_1_10 = "6.1 GA1";

	public static final String PORTAL_VERSION_6_1_20 = "6.1 GA2";

	public static final String PORTAL_VERSION_6_2_10 = "6.2 EE";

	public static final String[] PORTAL_VERSIONS = {
		PORTAL_VERSION_6_1_10, PORTAL_VERSION_6_1_20, PORTAL_VERSION_6_2_10
	};

	public static final Map<String, Integer> productVersionMap =
		new HashMap<String, Integer>() {
			{
				put(COMMERCE_LICENSE_VERSION_1, 10);
				put(DXP_VERSION_7_0, 5);
				put(DXP_VERSION_7_1, 6);
				put(DXP_VERSION_7_2, 7);
				put(DXP_VERSION_7_3, 8);
				put(DXP_VERSION_7_4, 9);
				put(PORTAL_MINOR_VERSION_6_1, 0);
				put(PORTAL_MINOR_VERSION_6_2, 3);
				put(PORTAL_VERSION_6_1_10, 1);
				put(PORTAL_VERSION_6_1_20, 2);
				put(PORTAL_VERSION_6_2_10, 4);
			}
		};

	public static final int getOrder(String productVersion) {
		if (productVersionMap.get(productVersion) != null) {
			return productVersionMap.get(productVersion);
		}

		return -1;
	}

}
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

import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class ProductVersion {

	public static final String COMMERCE_LICENSE_VERSION_1 = "1";

	public static final String DXP_MAJOR_VERSION_7 = "7";

	public static final String DXP_VERSION_7_0 = "7.0";

	public static final String DXP_VERSION_7_1 = "7.1";

	public static final String DXP_VERSION_7_2 = "7.2";

	public static final String DXP_VERSION_7_3 = "7.3";

	public static final String DXP_VERSION_7_4 = "7.4";

	public static final String[] DXP_VERSIONS = {
		DXP_VERSION_7_0, DXP_VERSION_7_1, DXP_VERSION_7_2, DXP_VERSION_7_3
	};

	public static final String[] DXP_VERSIONS_UNRELEASED = {DXP_VERSION_7_4};

	public static final String PORTAL_MAJOR_VERSION_6 = "6";

	public static final String PORTAL_MINOR_VERSION_6_1 = "6.1";

	public static final String PORTAL_MINOR_VERSION_6_2 = "6.2";

	public static final String[] PORTAL_MINOR_VERSIONS = {
		PORTAL_MINOR_VERSION_6_1, PORTAL_MINOR_VERSION_6_2
	};

	public static final String PORTAL_VERSION_6_1_10 = "6.1 GA1";

	public static final String PORTAL_VERSION_6_1_20 = "6.1 GA2";

	public static final String PORTAL_VERSION_6_1_30 = "6.1 GA3";

	public static final String PORTAL_VERSION_6_2_10 = "6.2 EE";

	public static final String[] PORTAL_VERSIONS = {
		PORTAL_VERSION_6_1_10, PORTAL_VERSION_6_1_20, PORTAL_VERSION_6_1_30,
		PORTAL_VERSION_6_2_10
	};

	public static final int getOrder(
		String productVersion, boolean includeUnreleasedVersions) {

		Map<String, Integer> productVersionMap = _getProductVersionMap(
			includeUnreleasedVersions);

		if (productVersionMap.get(productVersion) != null) {
			return productVersionMap.get(productVersion);
		}

		return -1;
	}

	public static final String[] getProductVersions(
		String productName, boolean includeUnreleasedVersions) {

		if (productName.contains("Commerce Subscription")) {
			return new String[] {COMMERCE_LICENSE_VERSION_1};
		}
		else if (productName.startsWith("DXP") &&
				 !productName.contains("DXP Cloud")) {

			if (includeUnreleasedVersions) {
				return ArrayUtil.append(DXP_VERSIONS, DXP_VERSIONS_UNRELEASED);
			}

			return DXP_VERSIONS;
		}
		else if ((productName.contains("Portal") &&
				  !productName.contains("Early Access Program")) ||
				 productName.startsWith("TCAT Portal")) {

			return PORTAL_VERSIONS;
		}

		return new String[0];
	}

	private static final List<String> _getProductOrderList(
		boolean includeUnreleasedVersions) {

		List<String> productVersions = new ArrayList<>();

		Collections.addAll(productVersions, PORTAL_VERSIONS);
		Collections.addAll(productVersions, PORTAL_MINOR_VERSIONS);
		Collections.addAll(productVersions, DXP_VERSIONS);

		if (includeUnreleasedVersions) {
			Collections.addAll(productVersions, DXP_VERSIONS_UNRELEASED);
		}

		productVersions.add(COMMERCE_LICENSE_VERSION_1);

		return productVersions;
	}

	private static final Map<String, Integer> _getProductVersionMap(
		boolean includeUnreleasedVersions) {

		Map<String, Integer> productVersionMap = new HashMap<>();

		int i = 0;

		for (String version : _getProductOrderList(includeUnreleasedVersions)) {
			productVersionMap.put(version, i);

			i++;
		}

		return productVersionMap;
	}

}
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

/**
 * @author Kyle Bischof
 */
public class LicenseVersion {

	public static int getAppLicenseVersion() {
		return 3;
	}

	public static int getLicenseVersion(
		String productName, int productVersion) {

		if (productName.contains("Commerce")) {
			return getAppLicenseVersion();
		}

		if (productVersion >=
				ProductVersion.DIGITAL_ENTERPRISE_VERSION_7_1_10) {

			return 6;
		}

		if (productVersion >=
				ProductVersion.DIGITAL_ENTERPRISE_VERSION_7_0_10) {

			return 5;
		}

		if (productVersion >= ProductVersion.PORTAL_VERSION_6_1_20) {
			return 4;
		}

		if (productVersion >= ProductVersion.PORTAL_VERSION_6_1_10) {
			return 3;
		}

		if ((productVersion >= ProductVersion.PORTAL_VERSION_5_2_9) ||
			((productVersion >= ProductVersion.PORTAL_VERSION_5_1_9) &&
			 (productVersion < ProductVersion.PORTAL_VERSION_5_2_4))) {

			return 2;
		}

		return 1;
	}

}
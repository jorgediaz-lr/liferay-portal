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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;

/**
 * @author Kyle Bischof
 */
public class ProductVersion {

	public static final int COMMERCE_LICENSE_VERSION_1 = 47000;

	public static final int DIGITAL_ENTERPRISE_MAJOR_VERSION_7 = 42000;

	public static final int DIGITAL_ENTERPRISE_MINOR_VERSION_7_0 = 43000;

	public static final int DIGITAL_ENTERPRISE_MINOR_VERSION_7_1 = 43001;

	public static final int DIGITAL_ENTERPRISE_MINOR_VERSION_7_2 = 43002;

	public static final int DIGITAL_ENTERPRISE_MINOR_VERSION_7_3 = 43003;

	public static final int DIGITAL_ENTERPRISE_MINOR_VERSION_7_4 = 43004;

	public static final int DIGITAL_ENTERPRISE_VERSION_7_0_10 = 41000;

	public static final int DIGITAL_ENTERPRISE_VERSION_7_0_20 = 41019;

	public static final int DIGITAL_ENTERPRISE_VERSION_7_1_10 = 41100;

	public static final int DIGITAL_ENTERPRISE_VERSION_7_2_10 = 41200;

	public static final int DIGITAL_ENTERPRISE_VERSION_7_3_10 = 41300;

	public static final int DIGITAL_ENTERPRISE_VERSION_7_4_10 = 41400;

	public static final String LIST_TYPE_COMMERCE_ALL_VERSIONS =
		Product.class.getName() + ".commerceAllVersions";

	public static final String LIST_TYPE_COMMERCE_MAJOR_VERSIONS =
		Product.class.getName() + ".commerceMajorVersions";

	public static final String LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS =
		Product.class.getName() + ".dxpAllVersions";

	public static final String LIST_TYPE_DIGITAL_ENTERPRISE_MAJOR_VERSIONS =
		Product.class.getName() + ".dxpMajorVersions";

	public static final String LIST_TYPE_PORTAL_ALL_VERSIONS =
		Product.class.getName() + ".portalAllVersions";

	public static final String LIST_TYPE_PORTAL_MAJOR_VERSIONS =
		Product.class.getName() + ".portalMajorVersions";

	public static final int PORTAL_MINOR_VERSION_6_1 = 22003;

	public static final int PORTAL_MINOR_VERSION_6_2 = 22004;

	public static final int PORTAL_VERSION_6_1_10 = 20060;

	public static final int PORTAL_VERSION_6_1_20 = 20061;

	public static final int PORTAL_VERSION_6_2_10 = 20080;

	public static String getAllListType(String majorListType) {
		if (majorListType.equals(LIST_TYPE_COMMERCE_MAJOR_VERSIONS)) {
			return LIST_TYPE_COMMERCE_ALL_VERSIONS;
		}
		else if (majorListType.equals(LIST_TYPE_PORTAL_MAJOR_VERSIONS)) {
			return LIST_TYPE_PORTAL_ALL_VERSIONS;
		}
		else if (majorListType.equals(
					LIST_TYPE_DIGITAL_ENTERPRISE_MAJOR_VERSIONS)) {

			return LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS;
		}

		return majorListType;
	}

	public static String getLabel(int productVersion) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(productVersion);

			return listType.getName();
		}
		catch (Exception exception) {
			return StringPool.BLANK;
		}
	}

}
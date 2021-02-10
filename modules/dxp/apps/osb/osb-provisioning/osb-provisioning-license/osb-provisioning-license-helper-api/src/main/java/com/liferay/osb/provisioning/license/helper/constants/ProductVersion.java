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
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;

/**
 * @author Kyle Bischof
 */
public class ProductVersion {

	public static final int PORTAL_VERSION_6_1_10 = 20060;

	public static String getLabel(int productVersion) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(productVersion);

			return listType.getName();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

}
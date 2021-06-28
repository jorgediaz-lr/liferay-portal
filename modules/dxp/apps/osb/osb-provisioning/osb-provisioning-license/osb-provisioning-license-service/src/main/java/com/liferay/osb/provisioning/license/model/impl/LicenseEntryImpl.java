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

package com.liferay.osb.provisioning.license.model.impl;

import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

/**
 * @author Kyle Bischof
 */
public class LicenseEntryImpl extends LicenseEntryBaseImpl {

	public LicenseEntryImpl() {
	}

	public String getDisplayName() {
		String name = getName();
		String typeLabel = LicenseType.getLabel(getType());

		if (!name.contains(typeLabel)) {
			return StringBundler.concat(
				name, StringPool.SPACE, StringPool.OPEN_PARENTHESIS, typeLabel,
				StringPool.CLOSE_PARENTHESIS);
		}

		return name;
	}

}
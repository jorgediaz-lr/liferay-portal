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

package com.liferay.osb.provisioning.license.internal.upgrade.v1_1_0;

import com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeCommonLicenseKey extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateCommonLicenseKey();
	}

	protected void updateCommonLicenseKey() throws Exception {
		if (!hasTable(CommonLicenseKeyModelImpl.TABLE_NAME)) {
			runSQL(CommonLicenseKeyModelImpl.TABLE_SQL_CREATE);
		}
	}

}
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

package com.liferay.osb.koroneiki.taproot.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeAccount extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccount();
	}

	protected void updateAccount() throws Exception {
		if (!hasColumn("Koroneiki_Account", "dataRegion")) {
			runSQL("alter table Koroneiki_Account add dataRegion varchar(75)");

			runSQL(
				"update Koroneiki_Account set dataRegion = 'Brazil' where " +
					"region = 'Brazil'");
			runSQL(
				"update Koroneiki_Account set dataRegion = 'Hungary' where " +
					"region = 'Global' or region = 'Hungary' or region = " +
						"'Spain'");
			runSQL(
				"update Koroneiki_Account set dataRegion = 'Japan' where " +
					"region = 'Australia' or region = 'China' or region = " +
						"'India' or region = 'Japan'");
			runSQL(
				"update Koroneiki_Account set dataRegion = 'United States' " +
					"where region = 'United States'");
		}
	}

}
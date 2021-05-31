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

package com.liferay.osb.provisioning.data.migration.internal.portlet.action;

import com.liferay.osb.provisioning.data.migration.internal.constants.DataMigrationPortletKeys;
import com.liferay.osb.provisioning.data.migration.internal.migration.LicenseEntryMigration;
import com.liferay.osb.provisioning.data.migration.internal.migration.LicenseKeyMigration;
import com.liferay.osb.provisioning.data.migration.internal.migration.ProductLicensesMigration;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.index.IndexStatusManager;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + DataMigrationPortletKeys.ADMIN,
		"mvc.command.name=/migrate_data"
	},
	service = MVCActionCommand.class
)
public class MigrateDataMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			StopWatch stopWatch = new StopWatch();

			stopWatch.start();

			_indexStatusManager.setIndexReadOnly(true);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			_licenseEntryMigration.migrate(themeDisplay.getUserId());

			_licenseKeyMigration.migrate(themeDisplay.getUserId());

			_productLicensesMigration.migrate(themeDisplay.getUserId());

			if (_log.isInfoEnabled()) {
				_log.info("Migration took " + stopWatch.getTime() + " ms");
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
		finally {
			_indexStatusManager.setIndexReadOnly(false);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MigrateDataMVCActionCommand.class);

	@Reference
	private IndexStatusManager _indexStatusManager;

	@Reference
	private LicenseEntryMigration _licenseEntryMigration;

	@Reference
	private LicenseKeyMigration _licenseKeyMigration;

	@Reference
	private ProductLicensesMigration _productLicensesMigration;

}
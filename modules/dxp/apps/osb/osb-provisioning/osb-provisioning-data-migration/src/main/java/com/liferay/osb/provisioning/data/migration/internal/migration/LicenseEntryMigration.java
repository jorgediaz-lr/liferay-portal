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

package com.liferay.osb.provisioning.data.migration.internal.migration;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(immediate = true, service = LicenseEntryMigration.class)
public class LicenseEntryMigration {

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		StringBundler sb = new StringBundler(6);

		sb.append("select OSB_LicenseEntry.licenseEntryId, ");
		sb.append("OSB_LicenseEntry.name, OSB_LicenseEntry.type_, min.name, ");
		sb.append("max.name from OSB_LicenseEntry left join ");
		sb.append("CUSTOMER_ListType min on OSB_LicenseEntry.versionMin = ");
		sb.append("min.listTypeId left join CUSTOMER_ListType max on ");
		sb.append("OSB_LicenseEntry.versionMax = max.listTypeId");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String name = _getNewName(resultSet.getString(2));

				Product product = _productWebService.fetchProductByName(name);

				if (product == null) {
					_log.error("Unable to find product with name " + name);

					continue;
				}

				LicenseEntry licenseEntry =
					_licenseEntryLocalService.createLicenseEntry(
						resultSet.getLong(1));

				licenseEntry.setUserId(userId);
				licenseEntry.setProductKey(product.getKey());
				licenseEntry.setName(name);
				licenseEntry.setType(resultSet.getString(3));
				licenseEntry.setVersionMin(resultSet.getString(4));
				licenseEntry.setVersionMax(resultSet.getString(5));

				_licenseEntryLocalService.addLicenseEntry(licenseEntry);
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private static String _getNewName(String name) {
		String newName = StringUtil.replace(name, "Digital Enterprise", "DXP");

		if (newName.startsWith("Liferay ")) {
			newName = StringUtil.replaceFirst(
				newName, "Liferay ", StringPool.BLANK);
		}

		return newName;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseEntryMigration.class);

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private ProductWebService _productWebService;

}
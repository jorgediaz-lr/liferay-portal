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

package com.liferay.osb.provisioning.data.migration.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
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

	public static String getNewName(String name) {
		String newName = StringUtil.replace(name, "Digital Enterprise", "DXP");

		if (newName.startsWith("Liferay ")) {
			newName = StringUtil.replaceFirst(
				newName, "Liferay ", StringPool.BLANK);
		}

		return newName;
	}

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		StringBundler sb = new StringBundler(5);

		sb.append("select OSB_LicenseEntry.name, OSB_LicenseEntry.type_,");
		sb.append("a.name, b.name from OSB_LicenseEntry left join ");
		sb.append("Customer_Listtype a on OSB_LicenseEntry.versionMin = ");
		sb.append("a.listTypeId left join Customer_Listtype b on ");
		sb.append("OSB_LicenseEntry.versionMax = b.listTypeId;");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String name = getNewName(resultSet.getString(1));

				Product product = _productWebService.fetchProductByName(name);

				if (product == null) {
					_log.error("Unable to find product with name " + name);

					continue;
				}

				String type = resultSet.getString(2);
				String versionMin = resultSet.getString(3);
				String versionMax = resultSet.getString(4);

				_licenseEntryLocalService.addLicenseEntry(
					userId, product.getKey(), name, type, versionMin,
					versionMax);
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseEntryMigration.class);

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private ProductWebService _productWebService;

}
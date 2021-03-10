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
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ProductLicensesMigration.class)
public class ProductLicensesMigration {

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		User user = _userLocalService.getUser(userId);

		StringBundler sb = new StringBundler(1);

		sb.append("select koroneikiProductKey, licenses from OSB_ProductEntry");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String koroneikiProductKey = resultSet.getString(1);

				Product product = _productWebService.fetchProduct(
					koroneikiProductKey);

				if (product != null) {
					boolean licenses = resultSet.getBoolean(2);

					Map<String, String> properties = product.getProperties();

					properties.put("licenses", String.valueOf(licenses));

					product.setProperties(properties);

					_productWebService.updateProduct(
						user.getFullName(), user.getUuid(), koroneikiProductKey,
						product);
				}
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductLicensesMigration.class);

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private UserLocalService _userLocalService;

}
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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Collections;
import java.util.Date;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = LicenseKeyMigration.class)
public class LicenseKeyMigration {

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		StringBundler sb = new StringBundler(9);

		sb.append("select corpProjectId, OSB_ProductEntry.name, ");
		sb.append("licenseKeyId, startDate, expirationDate, ");
		sb.append("OSB_AccountEntry.accountEntryId from OSB_LicenseKey inner ");
		sb.append("join OSB_AccountEntry on OSB_LicenseKey.accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_LicenseKey.productEntryId = ");
		sb.append("OSB_ProductEntry.productEntryId where productId = ");
		sb.append("'Portal' and complimentary = 0 and active_ = 1 and ");
		sb.append("OSB_AccountEntry.status != 500");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong(1);

				Account account = _accountLocalService.fetchAccount(
					corpProjectId);

				if (account == null) {
					_log.error(
						"Unable to find account for account entry " +
							resultSet.getLong("accountEntryId"));

					continue;
				}

				String productEntryName = ProductEntryMigration.getNewName(
					resultSet.getString(2));

				ProductEntry productEntry =
					_productEntryLocalService.fetchProductEntryByName(
						productEntryName);

				if (productEntry == null) {
					_log.error(
						"Unable to find product with name " + productEntryName);

					continue;
				}

				Date startDate = resultSet.getDate("startDate");
				Date endDate = resultSet.getDate("expirationDate");

				ProductConsumption productConsumption =
					_productConsumptionLocalService.addProductConsumption(
						userId, corpProjectId, productEntry.getProductEntryId(),
						0, startDate, endDate, Collections.emptyList());

				long licenseKeyId = resultSet.getLong(3);

				_externalLinkLocalService.addExternalLink(
					userId, ProductConsumption.class.getName(),
					productConsumption.getProductConsumptionId(),
					ExternalLinkDomain.CUSTOMER,
					ExternalLinkEntityName.LICENSE_KEY,
					String.valueOf(licenseKeyId));
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyMigration.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

}
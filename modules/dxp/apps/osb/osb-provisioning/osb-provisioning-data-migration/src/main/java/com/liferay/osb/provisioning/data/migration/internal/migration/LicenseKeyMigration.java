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

import com.liferay.osb.provisioning.license.helper.constants.LicenseSizing;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = LicenseKeyMigration.class)
public class LicenseKeyMigration {

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		User user = _userLocalService.getUser(userId);

		_migrate(user.getCompanyId(), 0, 1000);

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private void _migrate(long companyId, int start, int batchSize)
		throws Exception {

		StringBundler sb = new StringBundler(14);

		sb.append("select OSB_LicenseKey.*, OSB_LicenseKeySet.name,");
		sb.append("OSB_ProductEntry.koroneikiProductKey, user1.uuid_ as ");
		sb.append("userUuid, user2.uuid_ as modifiedUserUuid from ");
		sb.append("OSB_LicenseKey left join OSB_LicenseKeySet on ");
		sb.append("OSB_LicenseKey.licenseKeySetId = ");
		sb.append("OSB_LicenseKeySet.licenseKeySetId left join ");
		sb.append("OSB_ProductEntry on OSB_ProductEntry.productEntryId = ");
		sb.append("OSB_LicenseKey.productEntryId left join CUSTOMER_User ");
		sb.append("user1 on user1.userId = OSB_LicenseKey.userId left join ");
		sb.append("CUSTOMER_User user2 on user2.userId = ");
		sb.append("OSB_LicenseKey.modifiedUserId order by licenseKeyId limit ");
		sb.append(start);
		sb.append(",");
		sb.append(batchSize);

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (!resultSet.isBeforeFirst()) {
				return;
			}

			Timestamp timestamp = new Timestamp(1608364800000L);

			while (resultSet.next()) {
				LicenseKey licenseKey =
					_licenseKeyLocalService.createLicenseKey(
						resultSet.getLong("licenseKeyId"));

				licenseKey.setUuid(resultSet.getString("uuid_"));
				licenseKey.setCompanyId(companyId);
				licenseKey.setUserUuid(resultSet.getString("userUuid"));
				licenseKey.setUserName(resultSet.getString("userName"));
				licenseKey.setCreateDate(resultSet.getTimestamp("createDate"));
				licenseKey.setModifiedUserUuid(
					resultSet.getString("modifiedUserUuid"));
				licenseKey.setModifiedUserName(
					resultSet.getString("modifiedUserName"));
				licenseKey.setModifiedDate(
					resultSet.getTimestamp("modifiedDate"));
				licenseKey.setAssetReceiptLicenseUuid(
					resultSet.getString("assetReceiptLicenseUuid"));
				licenseKey.setAccountKey(
					resultSet.getString("koroneikiAccountKey"));
				licenseKey.setProductPurchaseKey(
					resultSet.getString("koroneikiProductPurchaseKey"));
				licenseKey.setLicenseEntryId(
					resultSet.getLong("licenseEntryId"));
				licenseKey.setProductKey(
					resultSet.getString("koroneikiProductKey"));
				licenseKey.setAccountName(
					resultSet.getString("accountEntryName"));
				licenseKey.setLicenseEntryName(
					resultSet.getString("licenseEntryName"));
				licenseKey.setLicenseEntryType(
					resultSet.getString("licenseEntryType"));
				licenseKey.setLicenseVersion(
					resultSet.getInt("licenseVersion"));
				licenseKey.setProductName(
					resultSet.getString("productEntryName"));
				licenseKey.setProductId(resultSet.getString("productId"));
				licenseKey.setProductVersion(
					resultSet.getString("productVersionLabel"));
				licenseKey.setClusterId(resultSet.getLong("clusterId"));
				licenseKey.setName(resultSet.getString("name"));
				licenseKey.setOwner(resultSet.getString("owner"));
				licenseKey.setMaxServers(resultSet.getInt("maxServers"));
				licenseKey.setMaxConcurrentUsers(
					resultSet.getLong("maxConcurrentUsers"));
				licenseKey.setMaxUsers(resultSet.getLong("maxUsers"));
				licenseKey.setMaxHttpSessions(
					resultSet.getInt("maxHttpSessions"));

				String sizing = resultSet.getString("sizing");

				if (sizing.equals("0")) {
					licenseKey.setSizing(StringPool.BLANK);
				}
				else if (timestamp.before(
							resultSet.getTimestamp("createDate"))) {

					licenseKey.setSizing(
						StringUtil.insert(
							resultSet.getString("sizing"), "sizing-", 0));
				}
				else {
					licenseKey.setSizing(
						LicenseSizing.getLabel(
							StringUtil.insert(
								resultSet.getString("sizing"), "sizing-", 0)));
				}

				licenseKey.setDescription(resultSet.getString("description"));
				licenseKey.setHostName(resultSet.getString("hostName"));
				licenseKey.setIpAddresses(resultSet.getString("ipAddresses"));
				licenseKey.setMacAddresses(resultSet.getString("macAddresses"));
				licenseKey.setServerId(resultSet.getString("serverId"));
				licenseKey.setKey(resultSet.getString("key_"));
				licenseKey.setStartDate(resultSet.getTimestamp("startDate"));
				licenseKey.setExpirationDate(
					resultSet.getTimestamp("expirationDate"));
				licenseKey.setAdditionalInfo(
					resultSet.getString("additionalInfo"));
				licenseKey.setComplimentary(
					resultSet.getBoolean("complimentary"));
				licenseKey.setActive(resultSet.getBoolean("active_"));

				_licenseKeyLocalService.addLicenseKey(licenseKey);
			}
		}

		_migrate(companyId, start + batchSize, batchSize);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyMigration.class);

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private UserLocalService _userLocalService;

}
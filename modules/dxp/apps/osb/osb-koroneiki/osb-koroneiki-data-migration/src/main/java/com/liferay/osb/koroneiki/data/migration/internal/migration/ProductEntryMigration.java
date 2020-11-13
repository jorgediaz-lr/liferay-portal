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
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ProductEntryMigration.class)
public class ProductEntryMigration {

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

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				"select * from OSB_ProductEntry");
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String name = getNewName(resultSet.getString("name"));
				int type = resultSet.getInt("type_");

				List<ProductField> productFields = new ArrayList<>();

				if ((type == 1) || (type == 2)) {
					ProductField productField =
						_productFieldLocalService.createProductField(0);

					productField.setName("type");

					if (type == 1) {
						productField.setValue("add-on");
					}
					else if (type == 2) {
						productField.setValue("primary");
					}

					productFields.add(productField);
				}

				ProductEntry productEntry =
					_productEntryLocalService.addProductEntry(
						userId, name, productFields);

				long productEntryId = resultSet.getLong("productEntryId");

				_migrateExternalIdMappers(
					connection, userId, productEntryId,
					productEntry.getProductEntryId());

				if (_log.isInfoEnabled()) {
					_log.info("Migrated ProductEntry " + name);
				}
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private void _migrateExternalIdMappers(
			Connection connection, long userId, long oldProductEntryId,
			long newProductEntryId)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append("select * from OSB_ExternalIdMapper where classNameId = ");
		sb.append("1400970 and classPK = ");
		sb.append(oldProductEntryId);
		sb.append(" and type_ = 3");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				_externalLinkLocalService.addExternalLink(
					userId, ProductEntry.class.getName(), newProductEntryId,
					ExternalLinkDomain.DOSSIERA,
					ExternalLinkEntityName.DOSSIERA_PRODUCT,
					resultSet.getString("externalId"));
			}
		}
	}

	private static final String _NAME_GOLD = "Gold Subscription";

	private static final String _NAME_LIMITED = "Limited Subscription";

	private static final String _NAME_PLATINUM = "Platinum Subscription";

	private static final Log _log = LogFactoryUtil.getLog(
		ProductEntryMigration.class);

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

}
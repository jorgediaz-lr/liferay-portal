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
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase.Status;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = OfferingEntryMigration.class)
public class OfferingEntryMigration {

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		ProductEntry productEntry =
			_productEntryLocalService.getProductEntryByName(_NAME_GOLD);

		_goldProductEntryId = productEntry.getProductEntryId();

		productEntry = _productEntryLocalService.getProductEntryByName(
			_NAME_LIMITED);

		_limitedProductEntryId = productEntry.getProductEntryId();

		productEntry = _productEntryLocalService.getProductEntryByName(
			_NAME_PLATINUM);

		_platinumProductEntryId = productEntry.getProductEntryId();

		StringBundler sb = new StringBundler(15);

		sb.append("select corpProjectId, OSB_AccountEntry.accountentryid, ");
		sb.append("OSB_ProductEntry.name, supportResponseId, startDate, ");
		sb.append("supportEndDate, quantity, OSB_OfferingEntry.status, ");
		sb.append("licenses, sizing, version, externalId from ");
		sb.append("OSB_OfferingEntry inner join OSB_AccountEntry on ");
		sb.append("OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_OfferingEntry.productEntryId = ");
		sb.append("OSB_ProductEntry.productEntryId inner join OSB_OrderEntry ");
		sb.append("on OSB_OfferingEntry.orderEntryId = ");
		sb.append("OSB_OrderEntry.orderEntryId left join ");
		sb.append("OSB_ExternalIdMapper on OSB_OrderEntry.orderEntryId = ");
		sb.append("OSB_ExternalIdMapper.classPK and ");
		sb.append("OSB_ExternalIdMapper.classNameId = 4117502 where ");
		sb.append("OSB_AccountEntry.status != 500 and corpProjectId > 0");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			ResultSetMetaData metaData = resultSet.getMetaData();

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong(1);

				Account account = _accountLocalService.fetchAccount(
					corpProjectId);

				if (account == null) {
					_log.error(
						"Unable to find account with accountEntryId " +
							resultSet.getLong(2));

					continue;
				}

				String productEntryName = ProductEntryMigration.getNewName(
					resultSet.getString(3));
				Date startDate = resultSet.getDate(5);
				Date endDate = resultSet.getDate(6);
				int quantity = resultSet.getInt(7);

				int status = resultSet.getInt(8);

				if (status != 2) {
					status = WorkflowConstants.STATUS_APPROVED;
				}
				else {
					status = WorkflowConstants.STATUS_CANCELLED;
				}

				List<ProductField> productFields = new ArrayList<>();

				for (int i = 9; i < 12; i++) {
					ProductField productField =
						_productFieldLocalService.createProductField(0);

					String value = resultSet.getString(i);

					if (Validator.isNull(value)) {
						continue;
					}

					String name = metaData.getColumnName(i);

					if (name.equals("version") && !value.equals("0")) {
						value = _liferayVersionMap.get(value);
					}

					productField.setName(name);

					productField.setValue(value);

					productFields.add(productField);
				}

				if (_isImported(
						corpProjectId, productEntryName, startDate, endDate,
						quantity, status, productFields)) {

					continue;
				}

				long supportResponseId = resultSet.getLong(4);

				_updateSLASubscription(
					account.getAccountId(), supportResponseId, startDate,
					endDate);

				try {
					ProductEntry curProductEntry =
						_productEntryLocalService.getProductEntryByName(
							productEntryName);

					ProductPurchase productPurchase =
						_productPurchaseLocalService.addProductPurchase(
							userId, account.getAccountId(),
							curProductEntry.getProductEntryId(), startDate,
							endDate, endDate, quantity, status, productFields);

					String externalId = resultSet.getString(12);

					if (Validator.isNotNull(externalId)) {
						_externalLinkLocalService.addExternalLink(
							userId, ProductPurchase.class.getName(),
							productPurchase.getProductPurchaseId(),
							ExternalLinkDomain.SALESFORCE,
							ExternalLinkEntityName.SALESFORCE_OPPORTUNITY,
							externalId);
					}
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			}

			for (Map.Entry<Long, Map<Long, List<ProductPurchase>>> entry :
					_accountSLASubscriptions.entrySet()) {

				long accountId = entry.getKey();

				List<ProductPurchase> slaProductPurchases =
					_getSLAProductPurchases(entry.getValue());

				for (ProductPurchase productPurchase : slaProductPurchases) {
					try {
						_productPurchaseLocalService.addProductPurchase(
							userId, accountId,
							productPurchase.getProductEntryId(),
							productPurchase.getStartDate(),
							productPurchase.getEndDate(),
							productPurchase.getOriginalEndDate(),
							productPurchase.getQuantity(),
							productPurchase.getStatus(),
							Collections.emptyList());
					}
					catch (Exception exception) {
						_log.error(exception, exception);
					}
				}
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private long _getProductEntryId(long supportResponseId) {
		if (supportResponseId == _SUPPORT_RESPONSE_PLATINUM_ID) {
			return _platinumProductEntryId;
		}
		else if (supportResponseId == _SUPPORT_RESPONSE_GOLD_ID) {
			return _goldProductEntryId;
		}

		return _limitedProductEntryId;
	}

	private List<ProductPurchase> _getSLAProductPurchases(
		Map<Long, List<ProductPurchase>> productPurchasesMap) {

		List<ProductPurchase> productPurchases = new ArrayList<>();

		List<ProductPurchase> platinumProductPurchases =
			productPurchasesMap.get(_platinumProductEntryId);

		if (platinumProductPurchases != null) {
			for (ProductPurchase productPurchase : platinumProductPurchases) {
				if (_isDateCovered(productPurchases, productPurchase)) {
					continue;
				}

				productPurchases.add(productPurchase);
			}
		}

		List<ProductPurchase> goldProductPurchases = productPurchasesMap.get(
			_goldProductEntryId);

		if (goldProductPurchases != null) {
			for (ProductPurchase productPurchase : goldProductPurchases) {
				if (_isDateCovered(productPurchases, productPurchase)) {
					continue;
				}

				productPurchases.add(productPurchase);
			}
		}

		List<ProductPurchase> limitedProductPurchases = productPurchasesMap.get(
			_limitedProductEntryId);

		if (limitedProductPurchases != null) {
			for (ProductPurchase productPurchase : limitedProductPurchases) {
				if (_isDateCovered(productPurchases, productPurchase)) {
					continue;
				}

				productPurchases.add(productPurchase);
			}
		}

		return productPurchases;
	}

	private boolean _isDateCovered(
		List<ProductPurchase> productPurchases,
		ProductPurchase productPurchase) {

		Date startDate = productPurchase.getStartDate();
		Date endDate = productPurchase.getEndDate();

		for (ProductPurchase curProductPurchase : productPurchases) {
			Date curStartDate = curProductPurchase.getStartDate();
			Date curEndDate = curProductPurchase.getEndDate();

			if ((startDate.getTime() >= curStartDate.getTime()) &&
				(endDate.getTime() <= curEndDate.getTime())) {

				return true;
			}
		}

		return false;
	}

	private boolean _isImported(
		long corpProjectId, String productEntryName, Date startDate,
		Date endDate, int quantity, int status,
		List<ProductField> productFields) {

		StringBundler sb = new StringBundler();

		sb.append(corpProjectId);
		sb.append(productEntryName);
		sb.append(startDate.getTime());
		sb.append(endDate.getTime());
		sb.append(quantity);
		sb.append(status);

		for (ProductField productField : productFields) {
			sb.append(productField.getName());
			sb.append(productField.getValue());
		}

		String key = sb.toString();

		if (_importedOfferingEntries.contains(key)) {
			return true;
		}

		_importedOfferingEntries.add(key);

		return false;
	}

	private void _updateSLASubscription(
		long accountId, long supportResponseId, Date startDate, Date endDate) {

		if (supportResponseId == _SUPPORT_RESPONSE_FLOATING_ID) {
			return;
		}

		Map<Long, List<ProductPurchase>> slaSubscriptionMap =
			_accountSLASubscriptions.get(accountId);

		if (slaSubscriptionMap == null) {
			slaSubscriptionMap = new HashMap<>();

			_accountSLASubscriptions.put(accountId, slaSubscriptionMap);
		}

		long productEntryId = _getProductEntryId(supportResponseId);

		List<ProductPurchase> productPurchases = slaSubscriptionMap.get(
			productEntryId);

		if (productPurchases == null) {
			productPurchases = new ArrayList<>();

			slaSubscriptionMap.put(productEntryId, productPurchases);
		}

		ProductPurchase productPurchase =
			_productPurchaseLocalService.createProductPurchase(0);

		productPurchase.setProductEntryId(productEntryId);
		productPurchase.setStartDate(startDate);
		productPurchase.setEndDate(
			new Date(endDate.getTime() + (30 * Time.DAY)));
		productPurchase.setOriginalEndDate(endDate);
		productPurchase.setQuantity(1);
		productPurchase.setStatus(
			WorkflowConstants.getLabelStatus(Status.APPROVED.toString()));

		productPurchases.add(productPurchase);
	}

	private static final String _NAME_GOLD = "Gold Subscription";

	private static final String _NAME_LIMITED = "Limited Subscription";

	private static final String _NAME_PLATINUM = "Platinum Subscription";

	private static final long _SUPPORT_RESPONSE_FLOATING_ID = 91578207;

	private static final long _SUPPORT_RESPONSE_GOLD_ID = 77672072;

	private static final long _SUPPORT_RESPONSE_PLATINUM_ID = 77672068;

	private static final Log _log = LogFactoryUtil.getLog(
		OfferingEntryMigration.class);

	private static final Set<String> _importedOfferingEntries = new HashSet<>();
	private static final Map<String, String> _liferayVersionMap =
		new HashMap<String, String>() {
			{
				put("21000", "5");
				put("21001", "6");
				put("22002", "6.0");
				put("22003", "6.1");
				put("22004", "6.2");
				put("42000", "7");
			}
		};

	@Reference
	private AccountLocalService _accountLocalService;

	private final Map<Long, Map<Long, List<ProductPurchase>>>
		_accountSLASubscriptions = new HashMap<>();

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	private long _goldProductEntryId;
	private long _limitedProductEntryId;
	private long _platinumProductEntryId;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}
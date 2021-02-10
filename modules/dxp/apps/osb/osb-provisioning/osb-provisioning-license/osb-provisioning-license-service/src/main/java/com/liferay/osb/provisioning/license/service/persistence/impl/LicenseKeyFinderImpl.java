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

package com.liferay.osb.customer.license.service.persistence.impl;

import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.service.persistence.LicenseKeyFinder;
import com.liferay.osb.customer.license.service.persistence.LicenseKeyUtil;
import com.liferay.osb.customer.util.OSBCustomSQL;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class LicenseKeyFinderImpl
	extends LicenseKeyFinderBaseImpl implements LicenseKeyFinder {

	public static final String
		COUNT_BY_U_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A =
			LicenseKeyFinder.class.getName() +
				".countByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A";

	public static final String
		FIND_BY_U_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A =
			LicenseKeyFinder.class.getName() +
				".findByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A";

	public static final String JOIN_BY_ACCOUNT_MEMBERSHIP =
		LicenseKeyFinder.class.getName() + ".joinByAccountMembership";

	public static final String JOIN_BY_ACTIVE =
		LicenseKeyFinder.class.getName() + ".joinByActive";

	public static final String JOIN_BY_ASSET_RECEIPT_ITEM =
		LicenseKeyFinder.class.getName() + ".joinByAssetReceiptLicense";

	public static final String JOIN_BY_USER =
		LicenseKeyFinder.class.getName() + ".joinByUser";

	public int countByKeywords(
		String keywords, LinkedHashMap<String, Object> params) {

		String[] accountEntryNames = null;
		String[] licenseKeySetNames = null;
		String[] productEntryNames = null;
		String[] productIds = null;
		String[] owners = null;
		String[] descriptions = null;
		String[] hostNames = null;
		String[] ipAddresses = null;
		String[] macAddresses = null;
		String[] serverIds = null;
		String[] keys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			accountEntryNames = CustomSQLUtil.keywords(keywords);
			licenseKeySetNames = CustomSQLUtil.keywords(keywords);
			productEntryNames = CustomSQLUtil.keywords(keywords);
			productIds = CustomSQLUtil.keywords(keywords);
			owners = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
			hostNames = CustomSQLUtil.keywords(keywords);
			ipAddresses = CustomSQLUtil.keywords(keywords);
			macAddresses = CustomSQLUtil.keywords(keywords);
			serverIds = CustomSQLUtil.keywords(keywords);
			keys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			null, null, null, null, null, null, keywords, keywords,
			accountEntryNames, licenseKeySetNames, null, null, new long[0],
			new long[0], productEntryNames, productIds, new int[0], owners,
			descriptions, hostNames, ipAddresses, macAddresses, serverIds, keys,
			null, null, params, andOperator);
	}

	public int countByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
		Long createUserId, Date createDateGT, Date createDateLT,
		Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
		String koroneikiAccountKey, String koroneikiProductPurchaseKey,
		String accountEntryName, String licenseKeySetName, Date startDateGT,
		Date startDateLT, long[] licenseEntryIds, long[] productEntryIds,
		String productEntryName, String productId, int[] productVersions,
		String owner, String description, String hostName, String ipAddress,
		String macAddress, String serverId, String key, Date expirationDateGT,
		Date expirationDateLT, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		String[] accountEntryNames = _osbCustomSQL.keywords(accountEntryName);
		String[] licenseKeySetNames = CustomSQLUtil.keywords(licenseKeySetName);
		String[] productEntryNames = CustomSQLUtil.keywords(productEntryName);
		String[] owners = CustomSQLUtil.keywords(owner);
		String[] descriptions = CustomSQLUtil.keywords(description);
		String[] hostNames = CustomSQLUtil.keywords(hostName);
		String[] ipAddresses = CustomSQLUtil.keywords(ipAddress);
		String[] macAddresses = CustomSQLUtil.keywords(macAddress);
		String[] serverIds = CustomSQLUtil.keywords(serverId);
		String[] keys = CustomSQLUtil.keywords(key);

		return countByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, koroneikiAccountKey,
			koroneikiProductPurchaseKey, accountEntryNames, licenseKeySetNames,
			createDateGT, createDateLT, licenseEntryIds, productEntryIds,
			productEntryNames, new String[] {productId}, productVersions,
			owners, descriptions, hostNames, ipAddresses, macAddresses,
			serverIds, keys, expirationDateGT, expirationDateLT, params,
			andOperator);
	}

	public List<LicenseKey> findByKeywords(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		String[] accountEntryNames = null;
		String[] licenseKeySetNames = null;
		String[] productEntryNames = null;
		String[] productIds = null;
		String[] owners = null;
		String[] descriptions = null;
		String[] hostNames = null;
		String[] ipAddresses = null;
		String[] macAddresses = null;
		String[] serverIds = null;
		String[] keys = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			accountEntryNames = CustomSQLUtil.keywords(keywords);
			licenseKeySetNames = CustomSQLUtil.keywords(keywords);
			productEntryNames = CustomSQLUtil.keywords(keywords);
			productIds = CustomSQLUtil.keywords(keywords);
			owners = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
			hostNames = CustomSQLUtil.keywords(keywords);
			ipAddresses = CustomSQLUtil.keywords(keywords);
			macAddresses = CustomSQLUtil.keywords(keywords);
			serverIds = CustomSQLUtil.keywords(keywords);
			keys = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			null, null, null, null, null, null, keywords, keywords,
			accountEntryNames, licenseKeySetNames, null, null, new long[0],
			new long[0], productEntryNames, productIds, new int[0], owners,
			descriptions, hostNames, ipAddresses, macAddresses, serverIds, keys,
			null, null, params, andOperator, start, end, obc);
	}

	public List<LicenseKey>
		findByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			Long createUserId, Date createDateGT, Date createDateLT,
			Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String accountEntryName, String licenseKeySetName, Date startDateGT,
			Date startDateLT, long[] licenseEntryIds, long[] productEntryIds,
			String productEntryName, String productId, int[] productVersions,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc) {

		String[] accountEntryNames = _osbCustomSQL.keywords(accountEntryName);
		String[] licenseKeySetNames = CustomSQLUtil.keywords(licenseKeySetName);
		String[] productEntryNames = CustomSQLUtil.keywords(productEntryName);
		String[] owners = CustomSQLUtil.keywords(owner);
		String[] descriptions = CustomSQLUtil.keywords(description);
		String[] hostNames = CustomSQLUtil.keywords(hostName);
		String[] ipAddresses = CustomSQLUtil.keywords(ipAddress);
		String[] macAddresses = CustomSQLUtil.keywords(macAddress);
		String[] serverIds = CustomSQLUtil.keywords(serverId);
		String[] keys = CustomSQLUtil.keywords(key);

		return findByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, koroneikiAccountKey,
			koroneikiProductPurchaseKey, accountEntryNames, licenseKeySetNames,
			createDateGT, createDateLT, licenseEntryIds, productEntryIds,
			productEntryNames, new String[] {productId}, productVersions,
			owners, descriptions, hostNames, ipAddresses, macAddresses,
			serverIds, keys, expirationDateGT, expirationDateLT, params,
			andOperator, start, end, obc);
	}

	protected int countByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
		Long createUserId, Date createDateGT, Date createDateLT,
		Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
		String koroneikiAccountKey, String koroneikiProductPurchaseKey,
		String[] accountEntryNames, String[] licenseKeySetNames,
		Date startDateGT, Date startDateLT, long[] licenseEntryIds,
		long[] productEntryIds, String[] productEntryNames, String[] productIds,
		int[] productVersions, String[] owners, String[] descriptions,
		String[] hostNames, String[] ipAddresses, String[] macAddresses,
		String[] serverIds, String[] keys, Date expirationDateGT,
		Date expirationDateLT, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);
		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		accountEntryNames = CustomSQLUtil.keywords(accountEntryNames);
		licenseKeySetNames = CustomSQLUtil.keywords(licenseKeySetNames);

		Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
		Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

		productEntryNames = CustomSQLUtil.keywords(productEntryNames);
		productIds = CustomSQLUtil.keywords(productIds);
		owners = CustomSQLUtil.keywords(owners);
		descriptions = CustomSQLUtil.keywords(descriptions);
		hostNames = CustomSQLUtil.keywords(hostNames);
		ipAddresses = CustomSQLUtil.keywords(ipAddresses);
		macAddresses = CustomSQLUtil.keywords(macAddresses);
		serverIds = CustomSQLUtil.keywords(serverIds);
		keys = CustomSQLUtil.keywords(keys);

		Timestamp expirationDateGT_TS = CalendarUtil.getTimestamp(
			expirationDateGT);
		Timestamp expirationDateLT_TS = CalendarUtil.getTimestamp(
			expirationDateLT);

		String sql = CustomSQLUtil.get(
			getClass(),
			COUNT_BY_U_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A);

		sql = replaceSQL(
			sql, createUserId, modifiedUserId, accountEntryNames,
			licenseKeySetNames, licenseEntryIds, productEntryIds,
			productEntryNames, productIds, productVersions, owners,
			descriptions, hostNames, ipAddresses, macAddresses, serverIds, keys,
			params, andOperator);

		String selectSql = PortalCustomSQLUtil.get(
			"com.liferay.util.dao.orm.CustomSQL.countBySelectSQL");

		sql = StringUtil.replace(selectSql, "[$SELECT_SQL$]", sql);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, params, createUserId, createDateGT_TS, createDateLT_TS,
				modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS,
				koroneikiAccountKey, koroneikiProductPurchaseKey,
				accountEntryNames, licenseKeySetNames, startDateGT_TS,
				startDateLT_TS, licenseEntryIds, productEntryIds,
				productEntryNames, productIds, productVersions, owners,
				descriptions, hostNames, ipAddresses, macAddresses, serverIds,
				keys, expirationDateGT_TS, expirationDateLT_TS);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<LicenseKey>
		findByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			Long createUserId, Date createDateGT, Date createDateLT,
			Long modifiedUserId, Date modifiedDateGT, Date modifiedDateLT,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String[] accountEntryNames, String[] licenseKeySetNames,
			Date startDateGT, Date startDateLT, long[] licenseEntryIds,
			long[] productEntryIds, String[] productEntryNames,
			String[] productIds, int[] productVersions, String[] owners,
			String[] descriptions, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, String[] keys,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc) {

		Timestamp createDateGT_TS = CalendarUtil.getTimestamp(createDateGT);
		Timestamp createDateLT_TS = CalendarUtil.getTimestamp(createDateLT);
		Timestamp modifiedDateGT_TS = CalendarUtil.getTimestamp(modifiedDateGT);
		Timestamp modifiedDateLT_TS = CalendarUtil.getTimestamp(modifiedDateLT);

		accountEntryNames = CustomSQLUtil.keywords(accountEntryNames);
		licenseKeySetNames = CustomSQLUtil.keywords(licenseKeySetNames);

		Timestamp startDateGT_TS = CalendarUtil.getTimestamp(startDateGT);
		Timestamp startDateLT_TS = CalendarUtil.getTimestamp(startDateLT);

		productEntryNames = CustomSQLUtil.keywords(productEntryNames);
		productIds = CustomSQLUtil.keywords(productIds);
		owners = CustomSQLUtil.keywords(owners);
		descriptions = CustomSQLUtil.keywords(descriptions);
		hostNames = CustomSQLUtil.keywords(hostNames);
		ipAddresses = CustomSQLUtil.keywords(ipAddresses);
		macAddresses = CustomSQLUtil.keywords(macAddresses);
		serverIds = CustomSQLUtil.keywords(serverIds);
		keys = CustomSQLUtil.keywords(keys);

		Timestamp expirationDateGT_TS = CalendarUtil.getTimestamp(
			expirationDateGT);
		Timestamp expirationDateLT_TS = CalendarUtil.getTimestamp(
			expirationDateLT);

		String sql = CustomSQLUtil.get(
			getClass(), FIND_BY_U_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A);

		sql = replaceSQL(
			sql, createUserId, modifiedUserId, accountEntryNames,
			licenseKeySetNames, licenseEntryIds, productEntryIds,
			productEntryNames, productIds, productVersions, owners,
			descriptions, hostNames, ipAddresses, macAddresses, serverIds, keys,
			params, andOperator);

		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("licenseKeyId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(
				qPos, params, createUserId, createDateGT_TS, createDateLT_TS,
				modifiedUserId, modifiedDateGT_TS, modifiedDateLT_TS,
				koroneikiAccountKey, koroneikiProductPurchaseKey,
				accountEntryNames, licenseKeySetNames, startDateGT_TS,
				startDateLT_TS, licenseEntryIds, productEntryIds,
				productEntryNames, productIds, productVersions, owners,
				descriptions, hostNames, ipAddresses, macAddresses, serverIds,
				keys, expirationDateGT_TS, expirationDateLT_TS);

			List<Long> licenseKeyIds = (List<Long>)QueryUtil.list(
				q, getDialect(), start, end);

			List<LicenseKey> licenseKeys = new ArrayList<>(
				licenseKeyIds.size());

			for (Long licenseKeyId : licenseKeyIds) {
				LicenseKey licenseKey = LicenseKeyUtil.findByPrimaryKey(
					licenseKeyId);

				licenseKeys.add(licenseKey);
			}

			return licenseKeys;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getJoin(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getJoin(key, value));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("active")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACTIVE);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if (params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (Validator.isNotNull(value)) {
				sb.append(getWhere(key, value));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("accountMembership")) {
			String[] valueArray = (String[])value;

			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACCOUNT_MEMBERSHIP);

			StringBundler sb = new StringBundler((valueArray.length * 2) + 2);

			sb.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < valueArray.length; i++) {
				if (i > 0) {
					sb.append(" OR ");
				}

				sb.append("(OSB_LicenseKey.koroneikiAccountKey = ?)");
			}

			sb.append(StringPool.CLOSE_PARENTHESIS);

			join = StringUtil.replace(
				join, "OSB_LicenseKey.koroneikiAccountKey = ?", sb.toString());
		}
		else if (key.equals("active")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ACTIVE);
		}
		else if (key.equals("assetReceiptLicense")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_ASSET_RECEIPT_ITEM);
		}
		else if (key.equals("user")) {
			join = CustomSQLUtil.get(getClass(), JOIN_BY_USER);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				String substring = join.substring(pos + 5);

				join = substring.concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	protected String replaceSQL(
		String sql, Long createUserId, Long modifiedUserId,
		String[] accountEntryNames, String[] licenseKeySetNames,
		long[] licenseEntryIds, long[] productEntryIds,
		String[] productEntryNames, String[] productIds, int[] productVersions,
		String[] owners, String[] descriptions, String[] hostNames,
		String[] ipAddresses, String[] macAddresses, String[] serverIds,
		String[] keys, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		if (createUserId == null) {
			sql = StringUtil.removeSubstring(sql, _USER_ID_SQL);
		}

		if (modifiedUserId == null) {
			sql = StringUtil.removeSubstring(sql, _MODIFIED_USER_ID_SQL);
		}

		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_AccountEntry.name)", StringPool.LIKE, true,
			accountEntryNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_AccountEntry.code_)", StringPool.LIKE, true,
			accountEntryNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKeySet.name)", StringPool.LIKE, false,
			licenseKeySetNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_LicenseKey.licenseEntryId", false, licenseEntryIds);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_LicenseKey.productEntryId", false, productEntryIds);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.productEntryName)", StringPool.LIKE,
			false, productEntryNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.productId)", StringPool.EQUAL, false,
			productIds);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "OSB_LicenseKey.productVersion", false, productVersions);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.owner)", StringPool.LIKE, false, owners);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.description)", StringPool.LIKE, false,
			descriptions);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.hostName)", StringPool.LIKE, false,
			hostNames);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.ipAddresses)", StringPool.LIKE, false,
			ipAddresses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.macAddresses)", StringPool.LIKE, false,
			macAddresses);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.serverId)", StringPool.LIKE, false,
			serverIds);
		sql = CustomSQLUtil.replaceKeywords(
			sql, "LOWER(OSB_LicenseKey.key_)", StringPool.LIKE, false, keys);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

		return sql;
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params) {

		if (params == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			Object value = entry.getValue();

			if (value instanceof Boolean) {
				Boolean valueBoolean = (Boolean)value;

				if (valueBoolean != null) {
					qPos.add(valueBoolean);
				}
			}
			else if (value instanceof Integer) {
				Integer valueInteger = (Integer)value;

				if (valueInteger != null) {
					qPos.add(valueInteger);
				}
			}
			else if (value instanceof Long) {
				Long valueLong = (Long)value;

				if (Validator.isNotNull(valueLong)) {
					qPos.add(valueLong);
				}
			}
			else if (value instanceof Long[]) {
				Long[] valueArray = (Long[])value;

				for (Long valueLong : valueArray) {
					if (Validator.isNotNull(valueLong)) {
						qPos.add(valueLong);
					}
				}
			}
			else if (value instanceof String) {
				String valueString = (String)value;

				if (Validator.isNotNull(valueString)) {
					qPos.add(valueString);
				}
			}
			else if (value instanceof String[]) {
				String[] valueArray = (String[])value;

				for (String valueString : valueArray) {
					qPos.add(valueString);
				}
			}
		}
	}

	protected void setJoin(
		QueryPos qPos, LinkedHashMap<String, Object> params, Long createUserId,
		Timestamp createDateGT, Timestamp createDateLT, Long modifiedUserId,
		Timestamp modifiedDateGT, Timestamp modifiedDateLT,
		String koroneikiAccountKey, String koroneikiProductPurchaseKey,
		String[] accountEntryNames, String[] licenseKeySetNames,
		Timestamp startDateGT, Timestamp startDateLT, long[] licenseEntryIds,
		long[] productEntryIds, String[] productEntryNames, String[] productIds,
		int[] productVersions, String[] owners, String[] descriptions,
		String[] hostNames, String[] ipAddresses, String[] macAddresses,
		String[] serverIds, String[] keys, Timestamp expirationDateGT,
		Timestamp expirationDateLT) {

		setJoin(qPos, params);

		if (createUserId != null) {
			qPos.add(createUserId);
		}

		qPos.add(createDateGT);
		qPos.add(createDateGT);
		qPos.add(createDateLT);
		qPos.add(createDateLT);

		if (modifiedUserId != null) {
			qPos.add(modifiedUserId);
		}

		qPos.add(modifiedDateGT);
		qPos.add(modifiedDateGT);
		qPos.add(modifiedDateLT);
		qPos.add(modifiedDateLT);
		qPos.add(koroneikiAccountKey);
		qPos.add(koroneikiAccountKey);
		qPos.add(koroneikiProductPurchaseKey);
		qPos.add(koroneikiProductPurchaseKey);
		qPos.add(accountEntryNames, 2);
		qPos.add(accountEntryNames, 2);
		qPos.add(licenseKeySetNames, 2);
		qPos.add(startDateGT);
		qPos.add(startDateGT);
		qPos.add(startDateLT);
		qPos.add(startDateLT);
		qPos.add(licenseEntryIds);
		qPos.add(productEntryIds);
		qPos.add(productEntryNames, 2);
		qPos.add(productIds, 2);
		qPos.add(productVersions);
		qPos.add(owners, 2);
		qPos.add(descriptions, 2);
		qPos.add(hostNames, 2);
		qPos.add(ipAddresses, 2);
		qPos.add(macAddresses, 2);
		qPos.add(serverIds, 2);
		qPos.add(keys, 2);
		qPos.add(expirationDateGT);
		qPos.add(expirationDateGT);
		qPos.add(expirationDateLT);
		qPos.add(expirationDateLT);
	}

	private static final String _MODIFIED_USER_ID_SQL =
		"(OSB_LicenseKey.modifiedUserId = ?) [$AND_OR_CONNECTOR$]";

	private static final String _USER_ID_SQL =
		"(OSB_LicenseKey.userId = ?) [$AND_OR_CONNECTOR$]";

	@ServiceReference(type = OSBCustomSQL.class)
	private OSBCustomSQL _osbCustomSQL;

}
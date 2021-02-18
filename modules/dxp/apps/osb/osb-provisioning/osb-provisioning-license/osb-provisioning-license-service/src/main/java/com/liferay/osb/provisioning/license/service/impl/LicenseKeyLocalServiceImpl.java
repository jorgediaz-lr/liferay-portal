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

package com.liferay.osb.provisioning.license.service.impl;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.exception.DuplicateIPAddressException;
import com.liferay.osb.provisioning.license.exception.DuplicateMACAddressException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyActiveException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyDescriptionException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyIPAddressException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyMACAddressException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyOwnerException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyProductVersionException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyRenewException;
import com.liferay.osb.provisioning.license.exception.LicenseKeyServerInfoException;
import com.liferay.osb.provisioning.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.provisioning.license.generator.KeyGenerator;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.helper.constants.LicenseVersion;
import com.liferay.osb.provisioning.license.helper.constants.ProductId;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.base.LicenseKeyLocalServiceBaseImpl;
import com.liferay.osb.provisioning.license.util.LicenseUtil;
import com.liferay.osb.provisioning.license.util.comparator.LicenseKeyExpirationDateComparator;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseKeyLocalServiceImpl extends LicenseKeyLocalServiceBaseImpl {

	public static final long LIFETIME_INDEFINITE_VALUE =
		36500 * 1440 * Time.MINUTE;

	public LicenseKey addDeveloperLicenseKey(
			long userId, String accountKey, String productKey,
			int productMinorVersion)
		throws Exception {

		User user = userLocalService.getUser(userId);

		Account account = _accountWebService.getAccount(accountKey);

		String name = "Developer Activation Keys";

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			productKey, LicenseType.DEVELOPER);
		Product product = _productWebService.getProduct(productKey);

		Date startDate = new Date();

		Date expirationDate = new Date(
			startDate.getTime() + LIFETIME_INDEFINITE_VALUE);

		return addLicenseKey(
			userId, name, licenseEntry, product, accountKey, StringPool.BLANK,
			account.getCode(), account.getName(),
			getProductVersion(productMinorVersion), 0, user.getFullName(), 1, 5,
			0, 0, 0, account.getName() + " Developer Activation Keys",
			new String[0], new String[0], new String[0],
			new String[] {LicenseType.DEVELOPER}, startDate, expirationDate,
			StringPool.BLANK, true, true);
	}

	public LicenseKey addLicenseKey(
			long userId, String name, LicenseEntry licenseEntry,
			Product product, String accountKey, String productPurchaseKey,
			String accountCode, String accountName, int productVersion,
			long clusterId, String owner, int maxServers, int maxHttpSessions,
			int maxConcurrentUsers, int maxUsers, int sizing,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws Exception {

		User user = userLocalService.getUser(userId);

		if (!complimentary) {
			product = _productWebService.getProduct(
				licenseEntry.getProductKey());
		}

		String licenseEntryType = licenseEntry.getType();

		int licenseVersion = LicenseVersion.getLicenseVersion(
			product.getName(), productVersion);

		Date now = new Date();

		if (startDate == null) {
			startDate = now;
		}

		validate(product, productVersion, owner, description);

		return doAddLicenseKeyVersion3_4(
			now, user, licenseEntry, product, accountKey, productPurchaseKey,
			accountCode, accountName, licenseEntryType, licenseVersion,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			additionalInfo, complimentary, active);
	}

	public LicenseKey addLicenseKey(
			long userId, String name, long licenseEntryId, String productKey,
			String accountKey, String productPurchaseKey, String accountCode,
			String accountName, int productVersion, long clusterId,
			String owner, int maxServers, int maxHttpSessions,
			int maxConcurrentUsers, int maxUsers, int sizing,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, Date startDate,
			Date expirationDate, boolean complimentary, boolean active)
		throws Exception {

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			licenseEntryId);

		return addLicenseKey(
			userId, name, licenseEntry,
			_productWebService.getProduct(productKey), accountKey,
			productPurchaseKey, accountCode, accountName, productVersion,
			clusterId, owner, maxServers, maxHttpSessions, maxConcurrentUsers,
			maxUsers, sizing, description, hostNames, ipAddresses, macAddresses,
			serverIds, startDate, expirationDate, StringPool.BLANK,
			complimentary, active);
	}

	public LicenseKey addLicenseKey(
			long userId, String assetReceiptLicenseUuid,
			String licenseEntryType, String productName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws Exception {

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		int licenseVersion = 3;

		productName = LicenseUtil.trimText(productName);
		owner = LicenseUtil.trimText(owner);
		description = LicenseUtil.trimText(description);
		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		validate(
			licenseEntryType, owner, description, hostName, ipAddresses,
			macAddresses);

		String key = _keyGenerator.generate(
			StringPool.BLANK, StringPool.BLANK, licenseEntryType,
			licenseVersion, productName, productId,
			String.valueOf(productVersion), owner, 0, 0, 0, maxUsers, 0,
			description, hostName, ipAddresses, macAddresses,
			new String[] {serverId}, startDate, expirationDate);

		long licenseKeyId = counterLocalService.increment();

		LicenseKey licenseKey = licenseKeyPersistence.create(licenseKeyId);

		licenseKey.setUserId(user.getUserId());
		licenseKey.setUserName(user.getFullName());
		licenseKey.setCreateDate(now);
		licenseKey.setModifiedUserId(user.getUserId());
		licenseKey.setModifiedUserName(user.getFullName());
		licenseKey.setModifiedDate(now);
		licenseKey.setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
		licenseKey.setLicenseEntryType(licenseEntryType);
		licenseKey.setLicenseVersion(licenseVersion);
		licenseKey.setProductName(productName);
		licenseKey.setProductId(productId);
		licenseKey.setProductVersion(productVersion);
		licenseKey.setProductVersionLabel(String.valueOf(productVersion));
		licenseKey.setOwner(owner);
		licenseKey.setMaxUsers(maxUsers);
		licenseKey.setDescription(description);
		licenseKey.setHostName(hostName);
		licenseKey.setIpAddresses(ipAddresses);
		licenseKey.setMacAddresses(macAddresses);
		licenseKey.setServerId(serverId);
		licenseKey.setKey(key);
		licenseKey.setStartDate(startDate);
		licenseKey.setExpirationDate(expirationDate);
		licenseKey.setComplimentary(false);
		licenseKey.setActive(true);

		return licenseKeyPersistence.update(licenseKey);
	}

	public List<LicenseKey> getAccountLicenseKeys(String accountKey) {
		return licenseKeyPersistence.findByAccountKey(
			accountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new LicenseKeyExpirationDateComparator());
	}

	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean active) {

		return licenseKeyPersistence.findByARLU_A(
			assetReceiptLicenseUuid, active);
	}

	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return licenseKeyPersistence.findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return licenseKeyPersistence.countByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public LicenseKey getFirstLicenseKey(
			String accountKey, OrderByComparator obc)
		throws PortalException {

		return licenseKeyPersistence.findByAccountKey_First(accountKey, obc);
	}

	public LicenseKey getLicenseKeyByUuid(String uuid) throws PortalException {
		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByUuid(uuid);

		if (licenseKeys.isEmpty()) {
			throw new NoSuchLicenseKeyException("{uuid=" + uuid + "}");
		}

		return licenseKeys.get(0);
	}

	public List<LicenseKey> getLicenseKeys(long userId, String accountKey) {
		return licenseKeyPersistence.findByU_AK(userId, accountKey);
	}

	public List<LicenseKey> getLicenseKeys(
		String productPurchaseKey, int start, int end) {

		return licenseKeyPersistence.findByProductPurchaseKey(
			productPurchaseKey, start, end);
	}

	public List<LicenseKey> getLicenseKeys(
		String productPurchaseKey, long clusterId) {

		return licenseKeyPersistence.findByPPK_CI(
			productPurchaseKey, clusterId);
	}

	public List<LicenseKey> getLicenseKeys(String productId, String serverId) {
		return licenseKeyPersistence.findByPI_SI(productId, serverId);
	}

	public List<LicenseKey> getLicenseKeys(
		String accountKey, String productKey, int start, int end) {

		return licenseKeyPersistence.findByAK_PK(
			accountKey, productKey, start, end);
	}

	public List<LicenseKey> getLicenseKeys(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public List<LicenseKey> getLicenseKeysByName(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator obc) {

		return licenseKeyPersistence.findByPN_SI_A(
			productName, serverId, active, start, end, obc);
	}

	public List<LicenseKey> getLicenseKeysByUserIdProductId(
		long userId, String productId) {

		return licenseKeyPersistence.findByU_PI(userId, productId);
	}

	public int getLicenseKeysCount(String productPurchaseKey) {
		return licenseKeyPersistence.countByProductPurchaseKey(
			productPurchaseKey);
	}

	public int getLicenseKeysCount(String accountKey, String productKey) {
		return licenseKeyPersistence.countByAK_PK(accountKey, productKey);
	}

	public List<LicenseKey> getProductPurchaseGroupLicenseKeys(
		String[] productPurchaseKeys, boolean complimentary, boolean active,
		int start, int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByPPK_C_A(
			productPurchaseKeys, complimentary, active, start, end, obc);
	}

	public int getProductPurchaseGroupLicenseKeysCount(
		String[] productPurchaseKeys, boolean complimentary, boolean active) {

		return licenseKeyPersistence.countByPPK_C_A(
			productPurchaseKeys, complimentary, active);
	}

	public List<LicenseKey> getProductPurchaseLicenseKeys(
		String productPurchaseKey) {

		return licenseKeyPersistence.findByProductPurchaseKey(
			productPurchaseKey);
	}

	public List<LicenseKey> getProductPurchaseLicenseKeys(
		String productPurchaseKey, boolean complimentary, boolean active) {

		return licenseKeyPersistence.findByPPK_C_A(
			productPurchaseKey, complimentary, active);
	}

	public List<LicenseKey> getProductPurchaseLicenseKeys(
		String productPurchaseKey, long clusterId) {

		return licenseKeyPersistence.findByPPK_CI(
			productPurchaseKey, clusterId);
	}

	public List<LicenseKey> getProductPurchaseLicenseKeys(
		String productPurchaseKey, long clusterId, boolean active) {

		return licenseKeyPersistence.findByPPK_CI_A(
			productPurchaseKey, clusterId, active);
	}

	public int getProductPurchaseLicenseKeysCount(String productPurchaseKey) {
		return licenseKeyPersistence.countByProductPurchaseKey(
			productPurchaseKey);
	}

	public int getProductPurchaseLicenseKeysCount(
		String productPurchaseKey, boolean complimentary, boolean active) {

		int count = licenseKeyPersistence.countByPPK_NotLET_C_A(
			productPurchaseKey, LicenseType.CLUSTER, complimentary, active);

		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByPPK_LET_C_A(
			productPurchaseKey, LicenseType.CLUSTER, complimentary, active);

		Set<Long> clusterIds = new HashSet<>();

		for (LicenseKey licenseKey : licenseKeys) {
			if (licenseKey.getLicenseVersion() >= 3) {
				if (clusterIds.contains(licenseKey.getClusterId())) {
					continue;
				}

				clusterIds.add(licenseKey.getClusterId());
			}

			count += licenseKey.getMaxServers();
		}

		return count;
	}

	public int getProductPurchaseLicenseKeysCount(
		String productPurchaseKey, long clusterId) {

		return licenseKeyPersistence.countByPPK_CI(
			productPurchaseKey, clusterId);
	}

	public int getProductPurchaseLicenseKeysCount(
		String productPurchaseKey, long clusterId, boolean active) {

		return licenseKeyPersistence.countByPPK_CI_A(
			productPurchaseKey, clusterId, active);
	}

	public int getUserLicenseKeysCount(long userId, String accountKey) {
		return licenseKeyPersistence.countByU_AK(userId, accountKey);
	}

	public LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, Date startDate, Date expirationDate)
		throws Exception {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		if (!licenseKey.isActive()) {
			throw new LicenseKeyActiveException();
		}

		updateLicenseKey(userId, licenseKeyId, false);

		return addLicenseKey(
			userId, licenseKey.getAssetReceiptLicenseUuid(),
			licenseKey.getLicenseEntryType(), licenseKey.getProductName(),
			licenseKey.getProductId(), licenseKey.getProductVersion(),
			licenseKey.getOwner(), licenseKey.getMaxUsers(),
			licenseKey.getDescription(), licenseKey.getHostName(),
			licenseKey.getIpAddresses(), licenseKey.getMacAddresses(),
			licenseKey.getServerId(), startDate, expirationDate);
	}

	public LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, Date startDate, int renewTime)
		throws Exception {

		User user = userLocalService.getUser(userId);

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		Product product = _productWebService.getProduct(
			licenseKey.getProductKey());
		LicenseEntry licenseEntry = licenseKey.getLicenseEntry();

		if (!licenseKey.canRenew()) {
			throw new LicenseKeyRenewException();
		}

		licenseKey.setActive(false);

		licenseKey = licenseKeyPersistence.update(licenseKey);

		String description = renewTime + "-Day License";

		Date expirationDate = new Date(
			startDate.getTime() + (renewTime * Time.DAY));

		return doAddLicenseKeyVersion3_4(
			new Date(), user, licenseKey.getLicenseEntry(), product,
			licenseKey.getAccountKey(), licenseKey.getProductPurchaseKey(),
			licenseKey.getAccountCode(), licenseKey.getAccountName(),
			licenseEntry.getType(), licenseKey.getLicenseVersion(),
			licenseKey.getProductVersion(), licenseKey.getClusterId(),
			licenseKey.getOwner(), licenseKey.getMaxServers(),
			licenseKey.getMaxHttpSessions(), licenseKey.getMaxConcurrentUsers(),
			licenseKey.getMaxUsers(), licenseKey.getSizing(), description,
			new String[] {licenseKey.getHostName()},
			new String[] {licenseKey.getIpAddresses()},
			new String[] {licenseKey.getMacAddresses()},
			new String[] {licenseKey.getServerId()}, startDate, expirationDate,
			licenseKey.getAdditionalInfo(), licenseKey.isComplimentary(), true);
	}

	public List<LicenseKey> search(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, String accountKey,
		String productPurchaseKey, String accountName, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, long[] licenseEntryIds,
		String[] productKeys, String productName, String productId,
		int[] productVersions, String owner, String description,
		String hostName, String ipAddress, String macAddress, String serverId,
		String key, int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, OrderByComparator obc) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear);
		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);
		Date expirationDateGT = PortalUtil.getDate(
			expirationDateGTMonth, expirationDateGTDay, expirationDateGTYear);
		Date expirationDateLT = PortalUtil.getDate(
			expirationDateLTMonth, expirationDateLTDay, expirationDateLTYear);

		return licenseKeyFinder.
			findByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
				createUserId, createDateGT, createDateLT, modifiedUserId,
				modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
				accountName, startDateGT, startDateLT, licenseEntryIds,
				productKeys, productName, productId, productVersions, owner,
				description, hostName, ipAddress, macAddress, serverId, key,
				expirationDateGT, expirationDateLT, params, andSearch, start,
				end, obc);
	}

	public List<LicenseKey> search(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		return licenseKeyFinder.findByKeywords(
			keywords, params, start, end, obc);
	}

	public int searchCount(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, String accountKey,
		String productPurchaseKey, String accountName, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, long[] licenseEntryIds,
		String[] productKeys, String productName, String productId,
		int[] productVersions, String owner, String description,
		String hostName, String ipAddress, String macAddress, String serverId,
		String key, int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear);
		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);
		Date expirationDateGT = PortalUtil.getDate(
			expirationDateGTMonth, expirationDateGTDay, expirationDateGTYear);
		Date expirationDateLT = PortalUtil.getDate(
			expirationDateLTMonth, expirationDateLTDay, expirationDateLTYear);

		return licenseKeyFinder.
			countByU_C_M_M_AK_PPK_A_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
				createUserId, createDateGT, createDateLT, modifiedUserId,
				modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
				accountName, startDateGT, startDateLT, licenseEntryIds,
				productKeys, productName, productId, productVersions, owner,
				description, hostName, ipAddress, macAddress, serverId, key,
				expirationDateGT, expirationDateLT, params, andSearch);
	}

	public int searchCount(
		String keywords, LinkedHashMap<String, Object> params) {

		return licenseKeyFinder.countByKeywords(keywords, params);
	}

	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws Exception {

		User user = userLocalService.getUser(userId);

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		if (active && !licenseKey.isActive()) {
			if (!licenseKey.isComplimentary()) {
				_addProductConsumption(user, licenseKey);
			}
		}
		else if (!active && licenseKey.isActive()) {
			_deleteProductConsumption(user, licenseKey);
		}

		licenseKey.setModifiedUserId(user.getUserId());
		licenseKey.setModifiedUserName(user.getFullName());
		licenseKey.setModifiedDate(new Date());
		licenseKey.setActive(active);

		licenseKeyPersistence.update(licenseKey);
	}

	public LicenseKey updateLicenseKey(
			long userId, long licenseKeyId, String productPurchaseKey,
			String name, boolean complimentary, boolean active)
		throws Exception {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		String type = licenseKey.getLicenseEntryType();

		List<LicenseKey> clusterLicenseKeys = getClusterLicenseKeys(
			licenseKey, type);

		doUpdateLicenseKeyVersion3(
			new Date(), licenseKey, productPurchaseKey, clusterLicenseKeys,
			userId, complimentary, active);

		return licenseKey;
	}

	public LicenseKey updateLicenseKey(
			long licenseKeyId, String accountKey, String productPurchaseKey)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		licenseKey.setAccountKey(accountKey);
		licenseKey.setProductPurchaseKey(productPurchaseKey);

		return licenseKeyPersistence.update(licenseKey);
	}

	protected LicenseKey doAddLicenseKey(
			User user, Date now, LicenseEntry licenseEntry, String accountKey,
			String productPurchaseKey, String accountCode, String accountName,
			String licenseEntryName, String licenseEntryType,
			int licenseVersion, String productName, String productId,
			int productVersion, String productVersionLabel, long clusterId,
			String owner, int maxServers, long maxConcurrentUsers,
			long maxUsers, int maxHttpSessions, int sizing, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, String key, Date startDate, Date expirationDate,
			String additionalInfo, boolean complimentary, boolean active)
		throws Exception {

		long licenseKeyId = counterLocalService.increment();

		LicenseKey licenseKey = licenseKeyPersistence.create(licenseKeyId);

		licenseKey.setUserId(user.getUserId());
		licenseKey.setUserName(user.getFullName());
		licenseKey.setCreateDate(now);
		licenseKey.setModifiedUserId(user.getUserId());
		licenseKey.setModifiedUserName(user.getFullName());
		licenseKey.setModifiedDate(now);
		licenseKey.setAccountKey(accountKey);
		licenseKey.setProductPurchaseKey(productPurchaseKey);
		licenseKey.setLicenseEntryId(licenseEntry.getLicenseEntryId());
		licenseKey.setProductKey(licenseEntry.getProductKey());
		licenseKey.setAccountCode(accountCode);
		licenseKey.setAccountName(accountName);
		licenseKey.setLicenseEntryName(licenseEntryName);
		licenseKey.setLicenseEntryType(licenseEntryType);
		licenseKey.setLicenseVersion(licenseVersion);
		licenseKey.setProductName(productName);
		licenseKey.setProductId(productId);
		licenseKey.setProductVersion(productVersion);
		licenseKey.setProductVersionLabel(productVersionLabel);
		licenseKey.setClusterId(clusterId);
		licenseKey.setOwner(owner);
		licenseKey.setMaxServers(maxServers);
		licenseKey.setMaxConcurrentUsers(maxConcurrentUsers);
		licenseKey.setMaxUsers(maxUsers);
		licenseKey.setMaxHttpSessions(maxHttpSessions);
		licenseKey.setSizing(sizing);
		licenseKey.setDescription(description);
		licenseKey.setHostName(hostName);
		licenseKey.setIpAddresses(ipAddresses);
		licenseKey.setMacAddresses(macAddresses);
		licenseKey.setServerId(serverId);
		licenseKey.setKey(key);
		licenseKey.setStartDate(startDate);
		licenseKey.setExpirationDate(expirationDate);
		licenseKey.setAdditionalInfo(additionalInfo);
		licenseKey.setComplimentary(complimentary);
		licenseKey.setActive(active);

		if (!complimentary && active) {
			_addProductConsumption(user, licenseKey);
		}

		return licenseKeyPersistence.update(licenseKey);
	}

	protected LicenseKey doAddLicenseKeyVersion3_4(
			Date now, User user, LicenseEntry licenseEntry, Product product,
			String accountKey, String productPurchaseKey, String accountCode,
			String accountName, String licenseEntryType, int licenseVersion,
			int productVersion, long clusterId, String owner, int maxServers,
			int maxHttpSessions, long maxConcurrentUsers, long maxUsers,
			int sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, String[] serverIds,
			Date startDate, Date expirationDate, String additionalInfo,
			boolean complimentary, boolean active)
		throws Exception {

		accountName = LicenseUtil.trimText(accountName);

		String licenseEntryName = LicenseUtil.trimText(licenseEntry.getName());
		String productName = LicenseUtil.trimText(product.getName());

		String productId = ProductId.PORTAL;

		if (productName.contains(ProductConstants.NAME_COMMERCE_SUBSCRIPTION)) {
			productId = ProductId.COMMERCE;
		}

		String productVersionLabel = LicenseUtil.trimText(
			ProductVersion.getLabel(productVersion));

		owner = LicenseUtil.trimText(owner);

		if (!licenseEntryType.equals(LicenseType.CLUSTER)) {
			maxServers = 1;
		}

		description = LicenseUtil.trimText(description);

		if (licenseEntryType.equals(LicenseType.DEVELOPER) ||
			licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

			if (maxHttpSessions == 0) {
				maxHttpSessions = 10;
			}
		}
		else {
			maxHttpSessions = 0;
		}

		if (licenseEntryType.equals(LicenseType.CLUSTER)) {
			if (clusterId <= 0) {
				clusterId = counterLocalService.increment(
					getCounterName(
						accountKey, product.getKey(), productPurchaseKey));
			}
			else {
				List<LicenseKey> clusterLicenseKeys =
					licenseKeyPersistence.findByPPK_CI(
						productPurchaseKey, clusterId);

				if (!clusterLicenseKeys.isEmpty()) {
					LicenseKey clusterLicenseKey = clusterLicenseKeys.get(0);

					maxServers = clusterLicenseKey.getMaxServers();
					startDate = clusterLicenseKey.getStartDate();
					expirationDate = clusterLicenseKey.getExpirationDate();
				}
			}
		}

		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		LicenseKey licenseKey = null;

		int keyCount = 0;

		if (ArrayUtil.isNotEmpty(serverIds)) {
			keyCount = serverIds.length;
		}
		else if (hostNames != null) {
			keyCount = hostNames.length;
		}

		for (int i = 0; i < keyCount; i++) {
			String hostName = StringPool.BLANK;
			String curIpAddresses = StringPool.BLANK;
			String curMacAddresses = StringPool.BLANK;
			String serverId = StringPool.BLANK;

			if ((hostNames != null) && (hostNames.length > i)) {
				hostName = hostNames[i];
				curIpAddresses = ipAddresses[i];
				curMacAddresses = macAddresses[i];
			}

			if ((serverIds != null) && (serverIds.length > i)) {
				serverId = serverIds[i];
			}

			if (!licenseEntryType.equals(LicenseType.CLUSTER)) {
				clusterId = counterLocalService.increment(
					getCounterName(
						accountKey, product.getKey(), productPurchaseKey));
			}

			String key = _keyGenerator.generate(
				accountName, licenseEntryName, licenseEntryType, licenseVersion,
				productName, productId, productVersionLabel, owner, maxServers,
				maxHttpSessions, maxConcurrentUsers, maxUsers, sizing,
				description, hostName, curIpAddresses, curMacAddresses,
				new String[] {serverId}, startDate, expirationDate);

			licenseKey = doAddLicenseKey(
				user, now, licenseEntry, accountKey, productPurchaseKey,
				accountCode, accountName, licenseEntryName, licenseEntryType,
				licenseVersion, productName, productId, productVersion,
				productVersionLabel, clusterId, owner, maxServers,
				maxConcurrentUsers, maxUsers, maxHttpSessions, sizing,
				description, hostName, curIpAddresses, curMacAddresses,
				serverId, key, startDate, expirationDate, additionalInfo,
				complimentary, active);
		}

		return licenseKey;
	}

	protected void doUpdateLicenseKeyVersion3(
			Date now, LicenseKey licenseKey, String productPurchaseKey,
			List<LicenseKey> clusterLicenseKeys, long userId,
			boolean complimentary, boolean active)
		throws Exception {

		User user = userLocalService.getUser(userId);

		long clusterId = licenseKey.getClusterId();

		if (!productPurchaseKey.equals(licenseKey.getProductPurchaseKey())) {
			Product product = _productWebService.getProduct(
				licenseKey.getProductKey());

			clusterId = counterLocalService.increment(
				getCounterName(
					licenseKey.getAccountKey(), product.getKey(),
					productPurchaseKey));
		}

		for (LicenseKey clusterLicenseKey : clusterLicenseKeys) {
			boolean updateProductPurchaseKey = false;
			boolean updateComplimentary = false;
			boolean updateActive = false;

			if (!productPurchaseKey.equals(
					clusterLicenseKey.getProductPurchaseKey())) {

				updateProductPurchaseKey = true;
			}

			if (complimentary != clusterLicenseKey.isComplimentary()) {
				updateComplimentary = true;
			}

			if (active != clusterLicenseKey.isActive()) {
				updateActive = true;
			}

			if (clusterLicenseKey.getLicenseKeyId() ==
					licenseKey.getLicenseKeyId()) {

				clusterLicenseKey.setActive(active);
			}

			clusterLicenseKey.setModifiedUserId(user.getUserId());
			clusterLicenseKey.setModifiedUserName(user.getFullName());
			clusterLicenseKey.setModifiedDate(now);
			clusterLicenseKey.setProductPurchaseKey(productPurchaseKey);
			clusterLicenseKey.setClusterId(clusterId);
			clusterLicenseKey.setComplimentary(complimentary);

			clusterLicenseKey = licenseKeyPersistence.update(clusterLicenseKey);

			if (updateProductPurchaseKey) {
				_deleteProductConsumption(user, clusterLicenseKey);

				if (active && !complimentary) {
					_addProductConsumption(user, clusterLicenseKey);
				}
			}
			else if (active) {
				if (!complimentary && (updateComplimentary || updateActive)) {
					_addProductConsumption(user, licenseKey);
				}
				else if (complimentary && updateComplimentary) {
					_deleteProductConsumption(user, licenseKey);
				}
			}
			else if (updateActive) {
				_deleteProductConsumption(user, licenseKey);
			}
		}
	}

	protected List<LicenseKey> getClusterLicenseKeys(
		LicenseKey licenseKey, String type) {

		if ((type.equals(LicenseType.CLUSTER) ||
			 type.equals(LicenseType.DEVELOPER_CLUSTER)) &&
			(licenseKey.getLicenseVersion() != 2)) {

			return licenseKeyPersistence.findByPPK_CI(
				licenseKey.getProductPurchaseKey(), licenseKey.getClusterId());
		}

		return Arrays.asList(licenseKey);
	}

	protected String getCounterName(String productPurchaseKey) {
		return LicenseKey.class.getName(
		).concat(
			StringPool.POUND
		).concat(
			String.valueOf(productPurchaseKey)
		);
	}

	protected String getCounterName(
		String accountKey, String productKey, String productPurchaseKey) {

		StringBundler sb = new StringBundler(5);

		sb.append(LicenseKey.class.getName());
		sb.append(StringPool.POUND);

		if (Validator.isNotNull(productPurchaseKey)) {
			sb.append(productPurchaseKey);
		}
		else {
			sb.append(accountKey);
			sb.append(StringPool.POUND);
			sb.append(productKey);
		}

		return sb.toString();
	}

	protected int getProductVersion(int productMinorVersion) {
		if (productMinorVersion == ProductVersion.COMMERCE_LICENSE_VERSION_1) {
			return productMinorVersion;
		}
		else if (productMinorVersion ==
					ProductVersion.DIGITAL_ENTERPRISE_MINOR_VERSION_7_4) {

			return ProductVersion.DIGITAL_ENTERPRISE_VERSION_7_4_10;
		}
		else if (productMinorVersion ==
					ProductVersion.DIGITAL_ENTERPRISE_MINOR_VERSION_7_3) {

			return ProductVersion.DIGITAL_ENTERPRISE_VERSION_7_3_10;
		}
		else if (productMinorVersion ==
					ProductVersion.DIGITAL_ENTERPRISE_MINOR_VERSION_7_2) {

			return ProductVersion.DIGITAL_ENTERPRISE_VERSION_7_2_10;
		}
		else if (productMinorVersion ==
					ProductVersion.DIGITAL_ENTERPRISE_MINOR_VERSION_7_1) {

			return ProductVersion.DIGITAL_ENTERPRISE_VERSION_7_1_10;
		}
		else if (productMinorVersion ==
					ProductVersion.DIGITAL_ENTERPRISE_MINOR_VERSION_7_0) {

			return ProductVersion.DIGITAL_ENTERPRISE_VERSION_7_0_10;
		}
		else if (productMinorVersion ==
					ProductVersion.PORTAL_MINOR_VERSION_6_2) {

			return ProductVersion.PORTAL_VERSION_6_2_10;
		}
		else if (productMinorVersion ==
					ProductVersion.PORTAL_MINOR_VERSION_6_1) {

			return ProductVersion.PORTAL_VERSION_6_1_10;
		}

		return 0;
	}

	protected void validate(
			Product product, int productVersion, String owner,
			String description)
		throws PortalException {

		if (productVersion <= 0) {
			throw new LicenseKeyProductVersionException();
		}

		ListType listType = listTypeLocalService.fetchListType(productVersion);

		String version = StringPool.BLANK;

		Map<String, String> properties = product.getProperties();

		if (properties != null) {
			String curVersion = properties.get("version");

			if (Validator.isNotNull(curVersion)) {
				version = curVersion;
			}
		}

		String listTypeType = ProductVersion.getAllListType(
			Product.class.getName() + StringPool.PERIOD + version);

		if ((listType == null) || !listTypeType.equals(listType.getType())) {
			throw new LicenseKeyProductVersionException();
		}

		if (Validator.isNull(owner)) {
			throw new LicenseKeyOwnerException();
		}

		if (Validator.isNull(description)) {
			throw new LicenseKeyDescriptionException();
		}
	}

	protected void validate(
			String licenseEntryType, String owner, String description,
			String hostName, String ipAddresses, String macAddresses)
		throws PortalException {

		if (Validator.isNull(owner)) {
			throw new LicenseKeyOwnerException();
		}

		if (Validator.isNull(description)) {
			throw new LicenseKeyDescriptionException();
		}

		if (!licenseEntryType.equals(LicenseType.ENTERPRISE)) {
			Set<String> distinctIpAddresses = new HashSet<>();

			String[] curIpAddresses = StringUtil.split(ipAddresses);

			for (String ipAddress : curIpAddresses) {
				validateIpAddress(ipAddress);

				if (distinctIpAddresses.contains(ipAddress)) {
					throw new DuplicateIPAddressException();
				}

				distinctIpAddresses.add(ipAddress);
			}

			Set<String> distinctMacAddresses = new HashSet<>();

			String[] curMacAddresses = StringUtil.split(macAddresses);

			for (String macAddress : curMacAddresses) {
				validateMacAddress(macAddress);

				if (distinctMacAddresses.contains(macAddress)) {
					throw new DuplicateMACAddressException();
				}

				distinctMacAddresses.add(macAddress);
			}

			if (Validator.isNull(hostName) && distinctIpAddresses.isEmpty() &&
				distinctMacAddresses.isEmpty()) {

				throw new LicenseKeyServerInfoException();
			}
		}
	}

	protected void validateIpAddress(String ipAddress) throws PortalException {
		if (!Validator.isIPAddress(ipAddress)) {
			throw new LicenseKeyIPAddressException();
		}
	}

	protected void validateMacAddress(String macAddress)
		throws PortalException {

		String curMacAddress = StringUtil.replace(
			macAddress, CharPool.DASH, CharPool.COLON);

		String[] octets = StringUtil.split(curMacAddress, StringPool.COLON);

		if (octets.length != 6) {
			throw new LicenseKeyMACAddressException();
		}

		for (String octet : octets) {
			if (octet.length() > 2) {
				throw new LicenseKeyMACAddressException();
			}

			char[] charArray = octet.toCharArray();

			for (char c : charArray) {
				if (!Validator.isDigit(c) &&
					((c < 65) || ((c > 70) && (c < 97)) || (c > 102))) {

					throw new LicenseKeyMACAddressException();
				}
			}
		}
	}

	private void _addProductConsumption(User user, LicenseKey licenseKey)
		throws Exception {

		ProductConsumption productConsumption = new ProductConsumption();

		productConsumption.setEndDate(licenseKey.getExpirationDate());

		Product product = _productWebService.getProduct(
			licenseKey.getProductKey());

		productConsumption.setProductKey(product.getKey());

		if (Validator.isNotNull(licenseKey.getProductPurchaseKey())) {
			productConsumption.setProductPurchaseKey(
				licenseKey.getProductPurchaseKey());
		}

		productConsumption.setStartDate(licenseKey.getStartDate());

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(ExternalLinkDomain.CUSTOMER);
		externalLink.setEntityName(ExternalLinkEntityName.CUSTOMER_LICENSE_KEY);
		externalLink.setEntityId(String.valueOf(licenseKey.getLicenseKeyId()));

		productConsumption.setExternalLinks(new ExternalLink[] {externalLink});

		_productConsumptionWebService.addProductConsumption(
			user.getFullName(), user.getUuid(), licenseKey.getAccountKey(),
			productConsumption);
	}

	private void _deleteProductConsumption(User user, LicenseKey licenseKey)
		throws Exception {

		List<ProductConsumption> productConsumptions =
			_productConsumptionWebService.getProductConsumptions(
				ExternalLinkDomain.CUSTOMER,
				ExternalLinkEntityName.CUSTOMER_LICENSE_KEY,
				String.valueOf(licenseKey.getLicenseKeyId()), 1, 1000);

		for (ProductConsumption productConsumption : productConsumptions) {
			_productConsumptionWebService.deleteProductConsumption(
				user.getFullName(), user.getUuid(),
				productConsumption.getKey());
		}
	}

	@ServiceReference(type = AccountWebService.class)
	private AccountWebService _accountWebService;

	@ServiceReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@ServiceReference(type = LicenseEntryLocalService.class)
	private LicenseEntryLocalService _licenseEntryLocalService;

	@ServiceReference(type = ProductConsumptionWebService.class)
	private ProductConsumptionWebService _productConsumptionWebService;

	@ServiceReference(type = ProductWebService.class)
	private ProductWebService _productWebService;

}
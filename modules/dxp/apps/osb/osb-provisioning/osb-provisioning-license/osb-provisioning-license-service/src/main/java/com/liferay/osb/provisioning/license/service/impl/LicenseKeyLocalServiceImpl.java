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

package com.liferay.osb.customer.license.service.impl;

import com.liferay.osb.customer.admin.constants.LicenseEntryConstants;
import com.liferay.osb.customer.admin.constants.ProductEntryConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.LicenseEntryLocalService;
import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.customer.license.constants.LicenseKeyConstants;
import com.liferay.osb.customer.license.exception.DuplicateIPAddressException;
import com.liferay.osb.customer.license.exception.DuplicateMACAddressException;
import com.liferay.osb.customer.license.exception.LicenseKeyActiveException;
import com.liferay.osb.customer.license.exception.LicenseKeyDescriptionException;
import com.liferay.osb.customer.license.exception.LicenseKeyIPAddressException;
import com.liferay.osb.customer.license.exception.LicenseKeyMACAddressException;
import com.liferay.osb.customer.license.exception.LicenseKeyOwnerException;
import com.liferay.osb.customer.license.exception.LicenseKeyProductVersionException;
import com.liferay.osb.customer.license.exception.LicenseKeyRenewException;
import com.liferay.osb.customer.license.exception.LicenseKeyServerIdException;
import com.liferay.osb.customer.license.exception.LicenseKeyServerInfoException;
import com.liferay.osb.customer.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.customer.license.generator.KeyGenerator;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.base.LicenseKeyLocalServiceBaseImpl;
import com.liferay.osb.customer.license.util.LicenseUtil;
import com.liferay.osb.customer.license.util.comparator.LicenseKeyExpirationDateComparator;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CountryService;
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
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseKeyLocalServiceImpl extends LicenseKeyLocalServiceBaseImpl {

	public LicenseKey addDeveloperLicenseKey(
			long userId, long accountEntryId, long productEntryId,
			int productMinorVersion)
		throws Exception {

		User user = userLocalService.getUser(userId);

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			accountEntryId);

		String name = "Developer Activation Keys";

		LicenseKeySet licenseKeySet =
			licenseKeySetPersistence.fetchByKA_N_First(
				accountEntry.getKoroneikiAccountKey(), name, null);

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			productEntryId, LicenseEntryConstants.TYPE_DEVELOPER);
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productEntryId);

		Date startDate = new Date();

		Date expirationDate = new Date(
			startDate.getTime() +
				LicenseKeyConstants.LIFETIME_INDEFINITE_VALUE);

		return addLicenseKey(
			userId, licenseKeySet, name, licenseEntry, productEntry,
			accountEntry.getKoroneikiAccountKey(), StringPool.BLANK,
			accountEntry.getName(), getProductVersion(productMinorVersion), 0,
			user.getFullName(), 1, 5, 0, 0, 0,
			accountEntry.getName() + " Developer Activation Keys",
			new String[0], new String[0], new String[0],
			new String[] {LicenseKeyConstants.SERVER_ID_DEVELOPER}, startDate,
			expirationDate, StringPool.BLANK, true, true);
	}

	public LicenseKey addLicenseKey(
			long userId, LicenseKeySet licenseKeySet, String name,
			LicenseEntry licenseEntry, ProductEntry productEntry,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String accountEntryName, int productVersion, long clusterId,
			String owner, int maxServers, int maxHttpSessions,
			int maxConcurrentUsers, int maxUsers, int sizing,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws Exception {

		User user = userLocalService.getUser(userId);

		if (!complimentary) {
			productEntry = licenseEntry.getProductEntry();
		}

		String licenseEntryType = licenseEntry.getType();

		int licenseVersion = LicenseKeyConstants.getLicenseVersion(
			productEntry, productVersion);

		Date now = new Date();

		if (startDate == null) {
			startDate = now;
		}

		validate(
			licenseEntry, licenseVersion, productEntry, productVersion, owner,
			maxServers, description, hostNames, ipAddresses, macAddresses,
			serverIds, complimentary);

		if (licenseKeySet == null) {
			licenseKeySet = licenseKeySetLocalService.addLicenseKeySet(
				user.getUserId(), koroneikiAccountKey, name);
		}

		if (licenseVersion >= 3) {
			return doAddLicenseKeyVersion3_4(
				now, user, licenseKeySet, name, licenseEntry, productEntry,
				koroneikiAccountKey, koroneikiProductPurchaseKey,
				accountEntryName, licenseEntryType, licenseVersion,
				productVersion, clusterId, owner, maxServers, maxHttpSessions,
				maxConcurrentUsers, maxUsers, sizing, description, hostNames,
				ipAddresses, macAddresses, serverIds, startDate, expirationDate,
				additionalInfo, complimentary, active);
		}

		return doAddLicenseKey(
			now, user, licenseKeySet, name, licenseEntry, productEntry,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			licenseEntryType, licenseVersion, productVersion, clusterId, owner,
			maxServers, maxConcurrentUsers, maxUsers, description, serverIds,
			startDate, expirationDate, additionalInfo, complimentary, active);
	}

	public LicenseKey addLicenseKey(
			long userId, long licenseKeySetId, String name, long licenseEntryId,
			long productEntryId, String koroneikiAccountKey,
			String koroneikiProductPurchaseKey, String accountEntryName,
			int productVersion, long clusterId, String owner, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			int sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, String[] serverIds,
			Date startDate, Date expirationDate, boolean complimentary,
			boolean active)
		throws Exception {

		LicenseKeySet licenseKeySet = null;

		if (licenseKeySetId > 0) {
			licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
				licenseKeySetId);
		}

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			licenseEntryId);
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productEntryId);

		return addLicenseKey(
			userId, licenseKeySet, name, licenseEntry, productEntry,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			StringPool.BLANK, complimentary, active);
	}

	public LicenseKey addLicenseKey(
			long userId, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws Exception {

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		int licenseVersion = 3;

		productEntryName = LicenseUtil.trimText(productEntryName);
		owner = LicenseUtil.trimText(owner);
		description = LicenseUtil.trimText(description);
		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		validate(
			licenseEntryType, owner, description, hostName, ipAddresses,
			macAddresses);

		String key = _keyGenerator.generate(
			StringPool.BLANK, StringPool.BLANK, licenseEntryType,
			licenseVersion, productEntryName, productId,
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
		licenseKey.setProductEntryName(productEntryName);
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

	public List<LicenseKey> getAccountEntryLicenseKeys(long accountEntryId) {
		return licenseKeyPersistence.findByAccountEntryId(
			accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
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
			long accountEntryId, OrderByComparator obc)
		throws PortalException {

		return licenseKeyPersistence.findByAccountEntryId_First(
			accountEntryId, obc);
	}

	public LicenseKey getLicenseKeyByUuid(String uuid) throws PortalException {
		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByUuid(uuid);

		if (licenseKeys.isEmpty()) {
			throw new NoSuchLicenseKeyException("{uuid=" + uuid + "}");
		}

		return licenseKeys.get(0);
	}

	public List<LicenseKey> getLicenseKeys(long userId, long accountEntryId) {
		return licenseKeyPersistence.findByU_AEI(userId, accountEntryId);
	}

	public List<LicenseKey> getLicenseKeys(long userId, String productId) {
		return licenseKeyPersistence.findByU_PI(userId, productId);
	}

	public List<LicenseKey> getLicenseKeys(
		String koroneikiProductPurchaseKey, int start, int end) {

		return licenseKeyPersistence.findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, start, end);
	}

	public List<LicenseKey> getLicenseKeys(
		String koroneikiProductPurchaseKey, long clusterId) {

		return licenseKeyPersistence.findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId);
	}

	public List<LicenseKey> getLicenseKeys(
		String koroneikiAccountKey, long productEntryId, int start, int end) {

		return licenseKeyPersistence.findByKA_PEI(
			koroneikiAccountKey, productEntryId, start, end);
	}

	public List<LicenseKey> getLicenseKeys(String productId, String serverId) {
		return licenseKeyPersistence.findByPI_SI(productId, serverId);
	}

	public List<LicenseKey> getLicenseKeys(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public List<LicenseKey> getLicenseKeysByName(
		String productEntryName, String serverId, boolean active, int start,
		int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByPEN_SI_A(
			productEntryName, serverId, active, start, end, obc);
	}

	public int getLicenseKeysCount(String koroneikiProductPurchaseKey) {
		return licenseKeyPersistence.countByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey);
	}

	public int getLicenseKeysCount(
		String koroneikiAccountKey, long productEntryId) {

		return licenseKeyPersistence.countByKA_PEI(
			koroneikiAccountKey, productEntryId);
	}

	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId) {
		return licenseKeyPersistence.findByLicenseKeySetId(licenseKeySetId);
	}

	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByOEI_C_A(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	public int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		return licenseKeyPersistence.countByOEI_C_A(
			offeringEntryIds, complimentary, active);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(long offeringEntryId) {
		return licenseKeyPersistence.findByOfferingEntryId(offeringEntryId);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, boolean complimentary, boolean active) {

		return licenseKeyPersistence.findByOEI_C_A(
			offeringEntryId, complimentary, active);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId) {

		return licenseKeyPersistence.findByOEI_CI(offeringEntryId, clusterId);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId, boolean active) {

		return licenseKeyPersistence.findByOEI_CI_A(
			offeringEntryId, clusterId, active);
	}

	public int getOfferingEntryLicenseKeysCount(long offeringEntryId) {
		return licenseKeyPersistence.countByOfferingEntryId(offeringEntryId);
	}

	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, boolean complimentary, boolean active) {

		int count = licenseKeyPersistence.countByOEI_NotLET_C_A(
			offeringEntryId, LicenseEntryConstants.TYPE_CLUSTER, complimentary,
			active);

		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByOEI_LET_C_A(
			offeringEntryId, LicenseEntryConstants.TYPE_CLUSTER, complimentary,
			active);

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

	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId) {

		return licenseKeyPersistence.countByOEI_CI(offeringEntryId, clusterId);
	}

	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId, boolean active) {

		return licenseKeyPersistence.countByOEI_CI_A(
			offeringEntryId, clusterId, active);
	}

	public int getUserLicenseKeysCount(long userId, long accountEntryId) {
		return licenseKeyPersistence.countByU_AEI(userId, accountEntryId);
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
			licenseKey.getLicenseEntryType(), licenseKey.getProductEntryName(),
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

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
			licenseKey.getLicenseKeySetId());
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			licenseKey.getProductEntryId());
		LicenseEntry licenseEntry = licenseKey.getLicenseEntry();

		if (!licenseKey.canRenew()) {
			throw new LicenseKeyRenewException();
		}

		licenseKey.setActive(false);

		licenseKey = licenseKeyPersistence.update(licenseKey);

		String description = String.valueOf(renewTime) + "-Day License";

		Date expirationDate = new Date(
			startDate.getTime() + (renewTime * Time.DAY));

		return doAddLicenseKeyVersion3_4(
			new Date(), user, licenseKeySet, licenseKeySet.getName(),
			licenseKey.getLicenseEntry(), productEntry,
			licenseKey.getKoroneikiAccountKey(),
			licenseKey.getKoroneikiProductPurchaseKey(),
			licenseKey.getAccountEntryName(), licenseEntry.getType(),
			licenseKey.getLicenseVersion(), licenseKey.getProductVersion(),
			licenseKey.getClusterId(), licenseKey.getOwner(),
			licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
			licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
			licenseKey.getSizing(), description,
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
		int modifiedDateLTMonth, int modifiedDateLTYear,
		String koroneikiAccountKey, String koroneikiProductPurchaseKey,
		String accountEntryName, String licenseKeySetName, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, long[] licenseEntryIds,
		long[] productEntryIds, String productEntryName, String productId,
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
			findByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
				createUserId, createDateGT, createDateLT, modifiedUserId,
				modifiedDateGT, modifiedDateLT, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName,
				licenseKeySetName, startDateGT, startDateLT, licenseEntryIds,
				productEntryIds, productEntryName, productId, productVersions,
				owner, description, hostName, ipAddress, macAddress, serverId,
				key, expirationDateGT, expirationDateLT, params, andSearch,
				start, end, obc);
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
		int modifiedDateLTMonth, int modifiedDateLTYear,
		String koroneikiAccountKey, String koroneikiProductPurchaseKey,
		String accountEntryName, String licenseKeySetName, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, long[] licenseEntryIds,
		long[] productEntryIds, String productEntryName, String productId,
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
			countByU_C_M_M_KA_KPP_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
				createUserId, createDateGT, createDateLT, modifiedUserId,
				modifiedDateGT, modifiedDateLT, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName,
				licenseKeySetName, startDateGT, startDateLT, licenseEntryIds,
				productEntryIds, productEntryName, productId, productVersions,
				owner, description, hostName, ipAddress, macAddress, serverId,
				key, expirationDateGT, expirationDateLT, params, andSearch);
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
			long licenseKeyId, long accountEntryId, long offeringEntryId,
			long orderEntryId)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		licenseKey.setAccountEntryId(accountEntryId);
		licenseKey.setOrderEntryId(orderEntryId);
		licenseKey.setOfferingEntryId(offeringEntryId);

		return licenseKeyPersistence.update(licenseKey);
	}

	public LicenseKey updateLicenseKey(
			long userId, long licenseKeyId, long licenseKeySetId,
			String koroneikiProductPurchaseKey, String name,
			boolean complimentary, boolean active)
		throws Exception {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		String type = licenseKey.getLicenseEntryType();

		List<LicenseKey> clusterLicenseKeys = getClusterLicenseKeys(
			licenseKey, type);

		if (licenseKey.getLicenseVersion() >= 3) {
			doUpdateLicenseKeyVersion3(
				new Date(), licenseKey, koroneikiProductPurchaseKey,
				clusterLicenseKeys, userId, licenseKeyId, licenseKeySetId, name,
				complimentary, active);
		}
		else {
			doUpdateLicenseKey(
				new Date(), licenseKey, koroneikiProductPurchaseKey,
				clusterLicenseKeys, userId, licenseKeyId, licenseKeySetId, name,
				complimentary, active);
		}

		return licenseKey;
	}

	protected LicenseKey doAddLicenseKey(
			Date now, User user, LicenseKeySet licenseKeySet, String name,
			LicenseEntry licenseEntry, ProductEntry productEntry,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String accountEntryName, String licenseEntryType,
			int licenseVersion, int productVersion, long clusterId,
			String owner, int maxServers, int maxConcurrentUsers, int maxUsers,
			String description, String[] serverIds, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws Exception {

		if (clusterId <= 0) {
			clusterId = counterLocalService.increment(
				getCounterName(
					koroneikiAccountKey, productEntry.getKoroneikiProductKey(),
					koroneikiProductPurchaseKey));
		}

		accountEntryName = LicenseUtil.trimText(accountEntryName);
		String licenseEntryName = LicenseUtil.trimText(licenseEntry.getName());
		String productEntryName = LicenseUtil.trimText(productEntry.getName());

		String productId = ProductEntryConstants.PRODUCT_ID_PORTAL;

		if (productEntry.isCommerce()) {
			productId = ProductEntryConstants.PRODUCT_ID_COMMERCE;
		}

		String productVersionLabel = LicenseUtil.trimText(
			LicenseKeyConstants.getProductVersionLabel(productVersion));

		owner = LicenseUtil.trimText(owner);

		if ((licenseVersion != 2) ||
			(!licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) &&
			 !licenseEntryType.equals(
				 LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER))) {

			maxServers = 1;
		}

		description = LicenseUtil.trimText(description);

		int maxHttpSessions = 0;

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) ||
			licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {

			maxHttpSessions = 10;
		}

		String[] macAddresses = null;

		if (((licenseVersion == 2) &&
			 licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION)) ||
			((licenseVersion != 2) &&
			 (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			  licenseEntryType.equals(
				  LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)))) {

			macAddresses = serverIds;
		}
		else {
			macAddresses = new String[serverIds.length];
		}

		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		String key = _keyGenerator.generate(
			accountEntryName, licenseEntryName, licenseEntryType,
			licenseVersion, productEntryName, productId, productVersionLabel,
			owner, maxServers, maxHttpSessions, 0, 0, 0, description, null,
			null, null, serverIds, startDate, expirationDate);

		LicenseKey licenseKey = null;

		for (int i = 0; i < serverIds.length; i++) {
			licenseKey = doAddLicenseKey(
				user, now, licenseKeySet, licenseEntry, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName, licenseEntryName,
				licenseEntryType, licenseVersion, productEntryName, productId,
				productVersion, productVersionLabel, clusterId, owner,
				maxServers, 0, 0, maxHttpSessions, 0, description,
				StringPool.BLANK, StringPool.BLANK, macAddresses[i],
				serverIds[i], key, startDate, expirationDate, additionalInfo,
				complimentary, active);
		}

		return licenseKey;
	}

	protected LicenseKey doAddLicenseKey(
			User user, Date now, LicenseKeySet licenseKeySet,
			LicenseEntry licenseEntry, String koroneikiAccountKey,
			String koroneikiProductPurchaseKey, String accountEntryName,
			String licenseEntryName, String licenseEntryType,
			int licenseVersion, String productEntryName, String productId,
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
		licenseKey.setLicenseKeySetId(licenseKeySet.getLicenseKeySetId());
		licenseKey.setKoroneikiAccountKey(koroneikiAccountKey);
		licenseKey.setKoroneikiProductPurchaseKey(koroneikiProductPurchaseKey);
		licenseKey.setLicenseEntryId(licenseEntry.getLicenseEntryId());
		licenseKey.setProductEntryId(licenseEntry.getProductEntryId());
		licenseKey.setAccountEntryName(accountEntryName);
		licenseKey.setLicenseEntryName(licenseEntryName);
		licenseKey.setLicenseEntryType(licenseEntryType);
		licenseKey.setLicenseVersion(licenseVersion);
		licenseKey.setProductEntryName(productEntryName);
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
			Date now, User user, LicenseKeySet licenseKeySet, String name,
			LicenseEntry licenseEntry, ProductEntry productEntry,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String accountEntryName, String licenseEntryType,
			int licenseVersion, int productVersion, long clusterId,
			String owner, int maxServers, int maxHttpSessions,
			long maxConcurrentUsers, long maxUsers, int sizing,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws Exception {

		accountEntryName = LicenseUtil.trimText(accountEntryName);

		String licenseEntryName = LicenseUtil.trimText(licenseEntry.getName());
		String productEntryName = LicenseUtil.trimText(productEntry.getName());

		String productId = ProductEntryConstants.PRODUCT_ID_PORTAL;

		if (productEntry.isCommerce()) {
			productId = ProductEntryConstants.PRODUCT_ID_COMMERCE;
		}

		String productVersionLabel = LicenseUtil.trimText(
			LicenseKeyConstants.getProductVersionLabel(productVersion));

		owner = LicenseUtil.trimText(owner);

		if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER)) {
			maxServers = 1;
		}

		description = LicenseUtil.trimText(description);

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

			if (maxHttpSessions == 0) {
				maxHttpSessions = 10;
			}
		}
		else {
			maxHttpSessions = 0;
		}

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER)) {
			if (clusterId <= 0) {
				clusterId = counterLocalService.increment(
					getCounterName(
						koroneikiAccountKey,
						productEntry.getKoroneikiProductKey(),
						koroneikiProductPurchaseKey));
			}
			else {
				List<LicenseKey> clusterLicenseKeys =
					licenseKeyPersistence.findByKPP_CI(
						koroneikiProductPurchaseKey, clusterId);

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

			if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER)) {
				clusterId = counterLocalService.increment(
					getCounterName(
						koroneikiAccountKey,
						productEntry.getKoroneikiProductKey(),
						koroneikiProductPurchaseKey));
			}

			String key = _keyGenerator.generate(
				accountEntryName, licenseEntryName, licenseEntryType,
				licenseVersion, productEntryName, productId,
				productVersionLabel, owner, maxServers, maxHttpSessions,
				maxConcurrentUsers, maxUsers, sizing, description, hostName,
				curIpAddresses, curMacAddresses, new String[] {serverId},
				startDate, expirationDate);

			licenseKey = doAddLicenseKey(
				user, now, licenseKeySet, licenseEntry, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName, licenseEntryName,
				licenseEntryType, licenseVersion, productEntryName, productId,
				productVersion, productVersionLabel, clusterId, owner,
				maxServers, maxConcurrentUsers, maxUsers, maxHttpSessions,
				sizing, description, hostName, curIpAddresses, curMacAddresses,
				serverId, key, startDate, expirationDate, additionalInfo,
				complimentary, active);
		}

		return licenseKey;
	}

	protected void doUpdateLicenseKey(
			Date now, LicenseKey licenseKey, String koroneikiProductPurchaseKey,
			List<LicenseKey> clusterLicenseKeys, long userId, long licenseKeyId,
			long licenseKeySetId, String name, boolean complimentary,
			boolean active)
		throws Exception {

		User user = userLocalService.getUser(userId);

		if (licenseKeySetId <= 0) {
			LicenseKeySet licenseKeySet =
				licenseKeySetLocalService.addLicenseKeySet(
					userId, licenseKey.getAccountEntryId(), name);

			licenseKeySetId = licenseKeySet.getLicenseKeySetId();
		}
		else {
			licenseKeySetPersistence.findByPrimaryKey(licenseKeySetId);
		}

		long clusterId = licenseKey.getClusterId();

		if (!koroneikiProductPurchaseKey.equals(
				licenseKey.getKoroneikiProductPurchaseKey())) {

			ProductEntry productEntry =
				_productEntryLocalService.getProductEntry(
					licenseKey.getProductEntryId());

			clusterId = counterLocalService.increment(
				getCounterName(
					licenseKey.getKoroneikiAccountKey(),
					productEntry.getKoroneikiProductKey(),
					koroneikiProductPurchaseKey));
		}

		for (LicenseKey clusterLicenseKey : clusterLicenseKeys) {
			boolean updateKoroneikiProductPurchaseKey = false;
			boolean updateComplimentary = false;
			boolean updateActive = false;

			if (!koroneikiProductPurchaseKey.equals(
					clusterLicenseKey.getKoroneikiProductPurchaseKey())) {

				updateKoroneikiProductPurchaseKey = true;
			}

			if (complimentary != clusterLicenseKey.isComplimentary()) {
				updateComplimentary = true;
			}

			if (active != clusterLicenseKey.isActive()) {
				updateActive = true;
			}

			clusterLicenseKey.setModifiedUserId(user.getUserId());
			clusterLicenseKey.setModifiedUserName(user.getFullName());
			clusterLicenseKey.setModifiedDate(now);
			clusterLicenseKey.setLicenseKeySetId(licenseKeySetId);
			clusterLicenseKey.setKoroneikiProductPurchaseKey(
				koroneikiProductPurchaseKey);
			clusterLicenseKey.setClusterId(clusterId);
			clusterLicenseKey.setComplimentary(complimentary);
			clusterLicenseKey.setActive(active);

			clusterLicenseKey = licenseKeyPersistence.update(clusterLicenseKey);

			if (updateKoroneikiProductPurchaseKey) {
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

	protected void doUpdateLicenseKeyVersion3(
			Date now, LicenseKey licenseKey, String koroneikiProductPurchaseKey,
			List<LicenseKey> clusterLicenseKeys, long userId, long licenseKeyId,
			long licenseKeySetId, String name, boolean complimentary,
			boolean active)
		throws Exception {

		User user = userLocalService.getUser(userId);

		if (licenseKeySetId <= 0) {
			LicenseKeySet licenseKeySet =
				licenseKeySetLocalService.addLicenseKeySet(
					userId, licenseKey.getAccountEntryId(), name);

			licenseKeySetId = licenseKeySet.getLicenseKeySetId();
		}
		else {
			licenseKeySetPersistence.findByPrimaryKey(licenseKeySetId);
		}

		long clusterId = licenseKey.getClusterId();

		if (!koroneikiProductPurchaseKey.equals(
				licenseKey.getKoroneikiProductPurchaseKey())) {

			ProductEntry productEntry =
				_productEntryLocalService.getProductEntry(
					licenseKey.getProductEntryId());

			clusterId = counterLocalService.increment(
				getCounterName(
					licenseKey.getKoroneikiAccountKey(),
					productEntry.getKoroneikiProductKey(),
					koroneikiProductPurchaseKey));
		}

		for (LicenseKey clusterLicenseKey : clusterLicenseKeys) {
			boolean updateKoroneikiProductPurchaseKey = false;
			boolean updateComplimentary = false;
			boolean updateActive = false;

			if (!koroneikiProductPurchaseKey.equals(
					clusterLicenseKey.getKoroneikiProductPurchaseKey())) {

				updateKoroneikiProductPurchaseKey = true;
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
			clusterLicenseKey.setLicenseKeySetId(licenseKeySetId);
			clusterLicenseKey.setKoroneikiProductPurchaseKey(
				koroneikiProductPurchaseKey);
			clusterLicenseKey.setClusterId(clusterId);
			clusterLicenseKey.setComplimentary(complimentary);

			clusterLicenseKey = licenseKeyPersistence.update(clusterLicenseKey);

			if (updateKoroneikiProductPurchaseKey) {
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

		if ((type.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			 type.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) &&
			(licenseKey.getLicenseVersion() != 2)) {

			return licenseKeyPersistence.findByOEI_CI(
				licenseKey.getOfferingEntryId(), licenseKey.getClusterId());
		}

		return Arrays.asList(licenseKey);
	}

	protected String getCounterName(long offeringEntryId) {
		return LicenseKey.class.getName(
		).concat(
			StringPool.POUND
		).concat(
			String.valueOf(offeringEntryId)
		);
	}

	protected String getCounterName(
		String koroneikiAccountKey, String koroneikiProductKey,
		String koroneikiProductPurchaseKey) {

		StringBundler sb = new StringBundler(5);

		sb.append(LicenseKey.class.getName());
		sb.append(StringPool.POUND);

		if (Validator.isNotNull(koroneikiProductPurchaseKey)) {
			sb.append(koroneikiProductPurchaseKey);
		}
		else {
			sb.append(koroneikiAccountKey);
			sb.append(StringPool.POUND);
			sb.append(koroneikiProductKey);
		}

		return sb.toString();
	}

	protected int getProductVersion(int productMinorVersion) {
		if (productMinorVersion ==
				ProductEntryConstants.COMMERCE_LICENSE_VERSION_1) {

			return productMinorVersion;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.
						DIGITAL_ENTERPRISE_MINOR_VERSION_7_4) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_4_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.
						DIGITAL_ENTERPRISE_MINOR_VERSION_7_3) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_3_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.
						DIGITAL_ENTERPRISE_MINOR_VERSION_7_2) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_2_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.
						DIGITAL_ENTERPRISE_MINOR_VERSION_7_1) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_1_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.
						DIGITAL_ENTERPRISE_MINOR_VERSION_7_0) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_6_2) {

			return ProductEntryConstants.PORTAL_VERSION_6_2_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_6_1) {

			return ProductEntryConstants.PORTAL_VERSION_6_1_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_6_0) {

			return ProductEntryConstants.PORTAL_VERSION_6_0_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_5_2) {

			return ProductEntryConstants.PORTAL_VERSION_5_2_9;
		}

		return 0;
	}

	protected void validate(
			LicenseEntry licenseEntry, int licenseVersion,
			ProductEntry productEntry, int productVersion, String owner,
			int maxServers, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, String[] serverIds,
			boolean complimentary)
		throws PortalException {

		if (productVersion <= 0) {
			throw new LicenseKeyProductVersionException();
		}

		ListType listType = listTypeLocalService.fetchListType(productVersion);

		String listTypeType = ProductEntryConstants.getAllListType(
			ProductEntry.class.getName() + StringPool.PERIOD +
				productEntry.getVersionsListType());

		if ((listType == null) || !listTypeType.equals(listType.getType())) {
			throw new LicenseKeyProductVersionException();
		}

		if (Validator.isNull(owner)) {
			throw new LicenseKeyOwnerException();
		}

		String type = licenseEntry.getType();

		if (Validator.isNull(description)) {
			throw new LicenseKeyDescriptionException();
		}

		if (licenseVersion <= 2) {
			if (serverIds.length == 0) {
				throw new LicenseKeyServerIdException();
			}

			for (String serverId : serverIds) {
				if (type.equals(LicenseEntryConstants.TYPE_ENTERPRISE) ||
					type.equals(LicenseEntryConstants.TYPE_OEM)) {

					continue;
				}

				if (Validator.isNull(serverId)) {
					throw new LicenseKeyServerIdException();
				}

				if (serverId.equals(LicenseKeyConstants.SERVER_ID_DEVELOPER) &&
					(complimentary ||
					 type.equals(LicenseEntryConstants.TYPE_DEVELOPER))) {

					continue;
				}

				if (serverId.length() == 44) {
					validateServerId(serverId);
				}
				else if (serverId.length() == 17) {
					validateMacAddress(serverId);
				}
				else {
					//throw new LicenseKeyServerIdException();
				}
			}
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

		if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_ENTERPRISE)) {
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

	protected void validateServerId(String serverId) throws PortalException {
		char[] charArray = serverId.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];

			if (((i + 1) % 5) == 0) {
				if (c != CharPool.DASH) {
					throw new LicenseKeyServerIdException();
				}
			}
			else if (!Validator.isDigit(c) && !Validator.isChar(c)) {
				throw new LicenseKeyServerIdException();
			}
		}
	}

	private void _addProductConsumption(User user, LicenseKey licenseKey)
		throws Exception {

		ProductConsumption productConsumption = new ProductConsumption();

		productConsumption.setEndDate(licenseKey.getExpirationDate());

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			licenseKey.getProductEntryId());

		productConsumption.setProductKey(productEntry.getKoroneikiProductKey());

		if (Validator.isNotNull(licenseKey.getKoroneikiProductPurchaseKey())) {
			productConsumption.setProductPurchaseKey(
				licenseKey.getKoroneikiProductPurchaseKey());
		}

		productConsumption.setStartDate(licenseKey.getStartDate());

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(ExternalLinkDomain.CUSTOMER);
		externalLink.setEntityName(ExternalLinkEntityName.CUSTOMER_LICENSE_KEY);
		externalLink.setEntityId(String.valueOf(licenseKey.getLicenseKeyId()));

		productConsumption.setExternalLinks(new ExternalLink[] {externalLink});

		_productConsumptionWebService.addProductConsumption(
			user.getFullName(), user.getUuid(),
			licenseKey.getKoroneikiAccountKey(), productConsumption);
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

	@ServiceReference(type = AccountEntryLocalService.class)
	private AccountEntryLocalService _accountEntryLocalService;

	@BeanReference(type = CountryService.class)
	private CountryService _countryService;

	@ServiceReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@ServiceReference(type = LicenseEntryLocalService.class)
	private LicenseEntryLocalService _licenseEntryLocalService;

	@ServiceReference(type = ProductConsumptionWebService.class)
	private ProductConsumptionWebService _productConsumptionWebService;

	@ServiceReference(type = ProductEntryLocalService.class)
	private ProductEntryLocalService _productEntryLocalService;

}
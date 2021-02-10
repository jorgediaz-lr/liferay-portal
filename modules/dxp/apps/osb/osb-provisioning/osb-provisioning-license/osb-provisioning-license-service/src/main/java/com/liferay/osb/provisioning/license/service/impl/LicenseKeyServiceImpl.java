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
import com.liferay.osb.customer.admin.service.permission.AccountEntryPermission;
import com.liferay.osb.customer.constants.OSBActionKeys;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.customer.license.constants.LicenseKeyConstants;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.service.base.LicenseKeyServiceBaseImpl;
import com.liferay.osb.customer.license.service.permission.AssetReceiptPermission;
import com.liferay.osb.customer.license.service.permission.LicenseKeyPermission;
import com.liferay.osb.customer.license.util.LicenseKeyExporter;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class LicenseKeyServiceImpl extends LicenseKeyServiceBaseImpl {

	public LicenseKey addDeveloperLicenseKey(
			long accountEntryId, long productEntryId, int productMinorVersion)
		throws Exception {

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			accountEntryId);
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productEntryId);

		try {
			User user = getUser();

			StringBundler sb = new StringBundler(7);

			sb.append("accountKey eq '");
			sb.append(accountEntry.getKoroneikiAccountKey());
			sb.append("' and customerContactUuids/any(s:s eq '");
			sb.append(user.getUuid());
			sb.append("') and state eq 'active' and (property_type eq ");
			sb.append("'primary' or contains(name, 'Commerce Subscription') ");
			sb.append("or contains(name, 'DXP Cloud Subscription'))");

			List<ProductPurchaseView> productPurchaseViews =
				_productPurchaseViewWebService.getProductPurchaseViews(
					StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

			boolean hasActiveProduct = false;

			for (ProductPurchaseView productPurchaseView :
					productPurchaseViews) {

				Product product = productPurchaseView.getProduct();

				ProductEntry curProductEntry =
					_productEntryLocalService.getProductEntryByKoroneikiKey(
						product.getKey());

				if ((curProductEntry.isCommerceSubscription() &&
					 productEntry.isCommerceSubscription()) ||
					(curProductEntry.isDigitalEnterprise() &&
					 productEntry.isDigitalEnterprise()) ||
					(curProductEntry.isDigitalEnterprise() &&
					 productEntry.isDXPCloud()) ||
					(curProductEntry.isPortal() && productEntry.isPortal())) {

					hasActiveProduct = true;
				}
			}

			if (!hasActiveProduct) {
				throw new PrincipalException();
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return licenseKeyLocalService.addDeveloperLicenseKey(
			getUserId(), accountEntryId, productEntryId, productMinorVersion);
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

		AccountEntryPermission.check(
			getPermissionChecker(), koroneikiAccountKey,
			OSBActionKeys.ADD_LICENSE);

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			licenseEntryId);

		ProductEntry productEntry = null;

		if (productEntryId > 0) {
			productEntry = _productEntryLocalService.getProductEntry(
				productEntryId);
		}
		else {
			productEntry = _productEntryLocalService.getProductEntry(
				licenseEntry.getProductEntryId());
		}

		String licenseEntryType = licenseEntry.getType();

		if ((LicenseKeyConstants.getLicenseVersion(
				productEntry, productVersion) >= 3) &&
			(licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			 licenseEntryType.equals(
				 LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) &&
			(maxHttpSessions != 5) &&
			!_roleLocalService.hasUserRole(
				getUserId(), OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) &&
			!_roleLocalService.hasUserRole(
				getUserId(), OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}

		return licenseKeyLocalService.addLicenseKey(
			userId, licenseKeySetId, name, licenseEntryId, productEntryId,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			complimentary, true);
	}

	@JSONWebService
	public LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws Exception {

		validateJSONWebServicePermissions();

		User user = userLocalService.getUserByUuidAndCompanyId(
			userUuid, OSBCustomerConstants.COMPANY_ID);

		return licenseKeyLocalService.addLicenseKey(
			user.getUserId(), assetReceiptLicenseUuid, licenseEntryType,
			productEntryName, productId, productVersion, owner, maxUsers,
			description, hostName, ipAddresses, macAddresses, serverId,
			startDate, expirationDate);
	}

	@JSONWebService
	public String generateCommerceLicenseKey(
			String owner, Date startDate, long licenseLifetime)
		throws Exception {

		validateJSONWebServicePermissions();

		Date expirationDate = new Date(startDate.getTime() + licenseLifetime);

		LicenseKey licenseKey = licenseKeyLocalService.addLicenseKey(
			getUserId(), StringPool.BLANK,
			LicenseEntryConstants.TYPE_ENTERPRISE, "Liferay Commerce",
			ProductEntryConstants.PRODUCT_ID_COMMERCE, 1, owner, 0,
			"Trial Activation Key", StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK, startDate, expirationDate);

		return _licenseKeyExporter.toLI(licenseKey);
	}

	@JSONWebService
	public String generateWeDeployLicenseKey(
			String owner, Date startDate, long licenseLifetime)
		throws Exception {

		validateJSONWebServicePermissions();

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			OSBCustomerConstants.ACCOUNT_ENTRY_WEDEPLOY_ID);

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			OSBCustomerConstants.PRODUCT_ENTRY_DIGITAL_ENTERPRISE_DEVELOPER_ID);

		LicenseEntry licenseEntry = _licenseEntryLocalService.getLicenseEntry(
			productEntry.getProductEntryId(),
			LicenseEntryConstants.TYPE_DEVELOPER);

		Date expirationDate = new Date(startDate.getTime() + licenseLifetime);

		LicenseKey licenseKey = licenseKeyLocalService.addLicenseKey(
			getUserId(), null, "Trial Activation Key", licenseEntry,
			productEntry, accountEntry.getKoroneikiAccountKey(),
			StringPool.BLANK, accountEntry.getName(),
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0, owner,
			0, 5, 0, 0, 1, "Trial Activation Key", new String[0], new String[0],
			new String[0],
			new String[] {LicenseKeyConstants.SERVER_ID_DEVELOPER}, startDate,
			expirationDate, StringPool.BLANK, true, true);

		return _licenseKeyExporter.toXML(licenseKey);
	}

	@JSONWebService
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@JSONWebService
	public int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public LicenseKey getLicenseKey(long licenseKeyId) throws PortalException {
		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (Validator.isNotNull(licenseKey.getAssetReceiptLicenseUuid())) {
			AssetReceiptPermission.check(
				getPermissionChecker(), OSBActionKeys.VIEW);
		}
		else {
			LicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, ActionKeys.VIEW);
		}

		return licenseKey;
	}

	@JSONWebService
	public LicenseKey getLicenseKey(String uuid) throws PortalException {
		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeyByUuid(uuid);
	}

	public List<LicenseKey> getLicenseKeys(long userId, String productId)
		throws PortalException {

		List<LicenseKey> licenseKeys = licenseKeyLocalService.getLicenseKeys(
			userId, productId);

		return filterLicenseKeys(licenseKeys);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeys(String productId, String serverId)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeys(productId, serverId);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end, OrderByComparator obc)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeysByName(
			String productEntryName, String serverId, boolean active, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeysByName(
			productEntryName, serverId, active, start, end, obc);
	}

	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId)
		throws PortalException {

		List<LicenseKey> licenseKeys =
			licenseKeyPersistence.findByLicenseKeySetId(licenseKeySetId);

		return filterLicenseKeys(licenseKeys);
	}

	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
			long[] offeringEntryIds, boolean complimentary, boolean active,
			int start, int end, OrderByComparator obc)
		throws PortalException {

		if (!isAccountAdmin(getUserId()) &&
			!_roleLocalService.hasUserRole(
				getUserId(),
				OSBCustomerConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

			throw new PrincipalException();
		}

		return licenseKeyLocalService.getOfferingEntryGroupLicenseKeys(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	public int getOfferingEntryGroupLicenseKeysCount(
			long[] offeringEntryIds, boolean complimentary, boolean active)
		throws PortalException {

		if (!isAccountAdmin(getUserId()) &&
			!_roleLocalService.hasUserRole(
				getUserId(),
				OSBCustomerConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

			throw new PrincipalException();
		}

		return licenseKeyLocalService.getOfferingEntryGroupLicenseKeysCount(
			offeringEntryIds, complimentary, active);
	}

	@JSONWebService
	public int getOfferingEntryLicenseKeysCount(
			long offeringEntryId, boolean complimentary, boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getOfferingEntryLicenseKeysCount(
			offeringEntryId, complimentary, active);
	}

	@JSONWebService
	public boolean isActive(String serverId, String productId, String key)
		throws PortalException {

		validateJSONWebServicePermissions();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("active", true);

		int activeLicensesCount = licenseKeyLocalService.searchCount(
			null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, null, null,
			null, 0, 0, 0, 0, 0, 0, new long[0], new long[0], null, productId,
			new int[0], null, null, null, null, null, serverId, key, 0, 0, 0, 0,
			0, 0, params, true);

		if (activeLicensesCount > 0) {
			return true;
		}

		return false;
	}

	@JSONWebService
	public LicenseKey registerLicenseKey(
			String orderEntryUuid, String productEntryName, int liferayVersion,
			int maxServers, String hostName, String ipAddresses,
			String macAddresses, String serverId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public LicenseKey renewLicenseKey(
			long licenseKeyId, Date startDate, int renewTime)
		throws Exception {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		AccountEntryPermission.check(
			getPermissionChecker(), licenseKey.getKoroneikiAccountKey(),
			OSBActionKeys.ADD_LICENSE);

		return licenseKeyLocalService.renewLicenseKey(
			getUserId(), licenseKeyId, startDate, renewTime);
	}

	@JSONWebService
	public LicenseKey renewLicenseKey(
			String uuid, Date startDate, Date expirationDate)
		throws Exception {

		validateJSONWebServicePermissions();

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKeyByUuid(
			uuid);

		return licenseKeyLocalService.renewLicenseKey(
			getUserId(), licenseKey.getLicenseKeyId(), startDate,
			expirationDate);
	}

	public List<LicenseKey> search(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String koroneikiAccountKey,
			String koroneikiProductPurchaseKey, String accountEntryName,
			String licenseKeySetName, int startDateGTDay, int startDateGTMonth,
			int startDateGTYear, int startDateLTDay, int startDateLTMonth,
			int startDateLTYear, long[] licenseEntryIds, long[] productEntryIds,
			String productEntryName, String productId, int[] productVersions,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			int expirationDateGTDay, int expirationDateGTMonth,
			int expirationDateGTYear, int expirationDateLTDay,
			int expirationDateLTMonth, int expirationDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, OrderByComparator obc)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.search(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, koroneikiAccountKey,
			koroneikiProductPurchaseKey, accountEntryName, licenseKeySetName,
			startDateGTDay, startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, licenseEntryIds, productEntryIds,
			productEntryName, productId, productVersions, owner, description,
			hostName, ipAddress, macAddress, serverId, key, expirationDateGTDay,
			expirationDateGTMonth, expirationDateGTYear, expirationDateLTDay,
			expirationDateLTMonth, expirationDateLTYear, params, andSearch,
			start, end, obc);
	}

	public List<LicenseKey> search(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.search(keywords, params, start, end, obc);
	}

	public int searchCount(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String koroneikiAccountKey,
			String koroneikiProductPurchaseKey, String accountEntryName,
			String licenseKeySetName, int startDateGTDay, int startDateGTMonth,
			int startDateGTYear, int startDateLTDay, int startDateLTMonth,
			int startDateLTYear, long[] licenseEntryIds, long[] productEntryIds,
			String productEntryName, String productId, int[] productVersions,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			int expirationDateGTDay, int expirationDateGTMonth,
			int expirationDateGTYear, int expirationDateLTDay,
			int expirationDateLTMonth, int expirationDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.searchCount(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, koroneikiAccountKey,
			koroneikiProductPurchaseKey, accountEntryName, licenseKeySetName,
			startDateGTDay, startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, licenseEntryIds, productEntryIds,
			productEntryName, productId, productVersions, owner, description,
			hostName, ipAddress, macAddress, serverId, key, expirationDateGTDay,
			expirationDateGTMonth, expirationDateGTYear, expirationDateLTDay,
			expirationDateLTMonth, expirationDateLTYear, params, andSearch);
	}

	public int searchCount(
			String keywords, LinkedHashMap<String, Object> params)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.searchCount(keywords, params);
	}

	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws Exception {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (licenseKey.isActive() != active) {
			if (Validator.isNull(licenseKey.getServerId())) {
				LicenseKeyPermission.check(
					getPermissionChecker(), licenseKey,
					OSBActionKeys.UPDATE_ADVANCED);
			}
			else {
				AssetReceiptPermission.check(
					getPermissionChecker(), OSBActionKeys.MANAGE);
			}
		}
		else {
			LicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_BASIC);
		}

		licenseKeyLocalService.updateLicenseKey(userId, licenseKeyId, active);
	}

	public LicenseKey updateLicenseKey(
			long licenseKeyId, long licenseKeySetId,
			String koroneikiProductPurchaseKey, String name,
			boolean complimentary, boolean active)
		throws Exception {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (!koroneikiProductPurchaseKey.equals(
				licenseKey.getKoroneikiProductPurchaseKey())) {

			LicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_ADMIN);
		}
		else if (licenseKey.isActive() != active) {
			LicenseKeyPermission.check(
				getPermissionChecker(), licenseKey,
				OSBActionKeys.UPDATE_ADVANCED);
		}
		else {
			LicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_BASIC);
		}

		return licenseKeyLocalService.updateLicenseKey(
			getUserId(), licenseKeyId, licenseKeySetId,
			koroneikiProductPurchaseKey, name, complimentary, active);
	}

	@JSONWebService
	public void updateLicenseKey(String userUuid, String uuid, boolean active)
		throws Exception {

		validateJSONWebServicePermissions();

		User user = userLocalService.getUserByUuidAndCompanyId(
			userUuid, OSBCustomerConstants.COMPANY_ID);

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKeyByUuid(
			uuid);

		licenseKeyLocalService.updateLicenseKey(
			user.getUserId(), licenseKey.getLicenseKeyId(), active);
	}

	@JSONWebService
	public void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws Exception {

		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByARLU_A(
			assetReceiptLicenseUuid, !active);

		for (LicenseKey licenseKey : licenseKeys) {
			licenseKeyLocalService.updateLicenseKey(
				getUserId(), licenseKey.getLicenseKeyId(), active);
		}
	}

	protected void addPermissionParams(LinkedHashMap<String, Object> params)
		throws Exception {

		if (isAccountAdmin(getUserId())) {
			return;
		}

		StringBundler sb = new StringBundler(3);

		sb.append("contactUuids/any(s:s eq '");

		User user = getUser();

		sb.append(user.getUuid());

		sb.append("')");

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, sb.toString(), 1, 1000, null);

		if (accounts.isEmpty()) {
			params.put("accountMembership", new String[] {StringPool.BLANK});
		}
		else {
			String[] koroneikiAccountKeys = new String[accounts.size()];

			for (int i = 0; i < accounts.size(); i++) {
				Account account = accounts.get(i);

				koroneikiAccountKeys[i] = account.getKey();
			}

			params.put("accountMembership", koroneikiAccountKeys);
		}

		params.put("active", Boolean.TRUE);
		params.put("user", user.getUserId());
	}

	protected List<LicenseKey> filterLicenseKeys(List<LicenseKey> licenseKeys)
		throws PortalException {

		List<LicenseKey> filteredLicenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = filteredLicenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!LicenseKeyPermission.contains(
					getPermissionChecker(), licenseKey, ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return filteredLicenseKeys;
	}

	protected boolean isAccountAdmin(long userId) {
		if (_roleLocalService.hasUserRole(
				userId, OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				userId, OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		return false;
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!_roleLocalService.hasUserRole(
				getUserId(), OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

	@ServiceReference(type = AccountEntryLocalService.class)
	private AccountEntryLocalService _accountEntryLocalService;

	@ServiceReference(type = AccountWebService.class)
	private AccountWebService _accountWebService;

	@ServiceReference(type = LicenseEntryLocalService.class)
	private LicenseEntryLocalService _licenseEntryLocalService;

	@ServiceReference(type = LicenseKeyExporter.class)
	private LicenseKeyExporter _licenseKeyExporter;

	@ServiceReference(type = ProductEntryLocalService.class)
	private ProductEntryLocalService _productEntryLocalService;

	@ServiceReference(type = ProductPurchaseViewWebService.class)
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@ServiceReference(type = RoleLocalService.class)
	private RoleLocalService _roleLocalService;

}
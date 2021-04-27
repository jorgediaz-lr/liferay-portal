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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.base.LicenseKeyServiceBaseImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=provisioning",
		"json.web.service.context.path=LicenseKey"
	},
	service = AopService.class
)
public class LicenseKeyServiceImpl extends LicenseKeyServiceBaseImpl {

	public LicenseKey addDeveloperLicenseKey(
			String accountKey, String productKey, String productVersion)
		throws Exception {

		Product product = _productWebService.getProduct(productKey);

		try {
			User user = getUser();

			StringBundler sb = new StringBundler(7);

			sb.append("accountKey eq '");
			sb.append(accountKey);
			sb.append("' and customerContactUuids/any(s:s eq '");
			sb.append(user.getUuid());
			sb.append("') and state eq 'active' and (property_type eq ");
			sb.append("'primary' or contains(name, 'Commerce Subscription') ");
			sb.append("or contains(name, 'DXP Cloud Subscription'))");

			List<ProductPurchaseView> productPurchaseViews =
				_productPurchaseViewWebService.getProductPurchaseViews(
					StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

			boolean hasActiveProduct = false;

			String productName = product.getName();

			for (ProductPurchaseView productPurchaseView :
					productPurchaseViews) {

				Product curProduct = productPurchaseView.getProduct();

				String curProductName = curProduct.getName();

				if ((curProductName.contains(
						ProductConstants.NAME_COMMERCE_SUBSCRIPTION) &&
					 productName.contains(
						 ProductConstants.NAME_COMMERCE_SUBSCRIPTION)) ||
					((curProductName.contains(
						ProductConstants.NAME_DIGITAL_ENTERPRISE) ||
					  curProductName.startsWith(ProductConstants.NAME_DXP)) &&
					 (productName.contains(
						 ProductConstants.NAME_DIGITAL_ENTERPRISE) ||
					  productName.startsWith(ProductConstants.NAME_DXP) ||
					  productName.contains(ProductConstants.NAME_DXP_CLOUD))) ||
					(curProductName.contains(ProductConstants.NAME_PORTAL) &&
					 productName.contains(ProductConstants.NAME_PORTAL))) {

					hasActiveProduct = true;
				}
			}

			if (!hasActiveProduct) {
				throw new PrincipalException();
			}
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}

		return licenseKeyLocalService.addDeveloperLicenseKey(
			getUserId(), accountKey, productKey, productVersion);
	}

	public LicenseKey addLicenseKey(
			long userId, long licenseEntryId, String productKey,
			String accountKey, String productPurchaseKey, String accountName,
			String productVersion, long clusterId, String name, String owner,
			int maxServers, int maxHttpSessions, int maxConcurrentUsers,
			int maxUsers, int sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, String[] serverIds,
			Date startDate, Date expirationDate, boolean complimentary,
			boolean active)
		throws Exception {

		//TODO: add permission check

		return licenseKeyLocalService.addLicenseKey(
			userId, licenseEntryId, productKey, accountKey, productPurchaseKey,
			accountName, productVersion, clusterId, name, owner, maxServers,
			maxHttpSessions, maxConcurrentUsers, maxUsers, sizing, description,
			hostNames, ipAddresses, macAddresses, serverIds, startDate,
			expirationDate, complimentary, true);
	}

	@JSONWebService
	public LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws Exception {

		validateJSONWebServicePermissions();

		long companyId = _portalInstancesLocalService.getDefaultCompanyId();

		User user = userLocalService.getDefaultUser(companyId);

		return licenseKeyLocalService.addLicenseKey(
			user.getUserId(), assetReceiptLicenseUuid, licenseEntryType,
			productName, productId, String.valueOf(productVersion), owner,
			maxUsers, description, hostName, ipAddresses, macAddresses,
			serverId, startDate, expirationDate);
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
		//TODO: add permission check

		return licenseKeyLocalService.getLicenseKey(licenseKeyId);
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
			String productName, String serverId, boolean active, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeysByName(
			productName, serverId, active, start, end, obc);
	}

	public List<LicenseKey> getProductPurchaseGroupLicenseKeys(
			String[] productPurchaseKeys, boolean complimentary, boolean active,
			int start, int end, OrderByComparator obc)
		throws PortalException {

		//TODO: add permission check

		return licenseKeyLocalService.getProductPurchaseGroupLicenseKeys(
			productPurchaseKeys, complimentary, active, start, end, obc);
	}

	public int getProductPurchaseGroupLicenseKeysCount(
			String[] productPurchaseKeys, boolean complimentary, boolean active)
		throws PortalException {

		//TODO: add permission check

		return licenseKeyLocalService.getProductPurchaseGroupLicenseKeysCount(
			productPurchaseKeys, complimentary, active);
	}

	public int getProductPurchaseLicenseKeysCount(
			String productPurchaseKey, boolean complimentary, boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getProductPurchaseLicenseKeysCount(
			productPurchaseKey, complimentary, active);
	}

	@JSONWebService
	public boolean isActive(String serverId, String productId, String key)
		throws PortalException {

		validateJSONWebServicePermissions();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("active", true);

		int activeLicensesCount = licenseKeyLocalService.searchCount(
			null, null, null, null, null, null, null, null, null, null, null,
			new long[0], new String[0], null, productId, new String[0],
			new long[0], null, null, null, null, null, serverId, key, null,
			null, params, true);

		if (activeLicensesCount > 0) {
			return true;
		}

		return false;
	}

	public LicenseKey renewLicenseKey(
			long licenseKeyId, Date startDate, Date expirationDate)
		throws Exception {

		//TODO: add permission check

		return licenseKeyLocalService.renewLicenseKey(
			getUserId(), licenseKeyId, startDate, expirationDate);
	}

	public Hits search(
			long companyId, String createUserUuid, Date createDateGT,
			Date createDateLT, String modifiedUserUuid, Date modifiedDateGT,
			Date modifiedDateLT, String accountKey, String productPurchaseKey,
			String accountName, Date startDateGT, Date startDateLT,
			Long[] licenseEntryIds, String[] productKeys, String productName,
			String productId, String[] productVersions, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT, Boolean active,
			boolean andSearch, int start, int end, Sort sort)
		throws Exception {

		//addPermissionParams(params);

		return licenseKeyLocalService.search(
			companyId, createUserUuid, createDateGT, createDateLT,
			modifiedUserUuid, modifiedDateGT, modifiedDateLT, accountKey,
			productPurchaseKey, accountName, startDateGT, startDateLT,
			licenseEntryIds, productKeys, productName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGT, expirationDateLT,
			active, andSearch, start, end, sort);
	}

	public List<LicenseKey> search(
			String createUserUuid, Date createDateGT, Date createDateLT,
			String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			Date startDateGT, Date startDateLT, long[] licenseEntryIds,
			String[] productKeys, String productName, String productId,
			String[] productVersions, long[] clusterIds, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, OrderByComparator obc)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.search(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch, start, end,
			obc);
	}

	public List<LicenseKey> search(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.search(keywords, params, start, end, obc);
	}

	public int searchCount(
			String createUserUuid, Date createDateGT, Date createDateLT,
			String modifiedUserUuid, Date modifiedDateGT, Date modifiedDateLT,
			String accountKey, String productPurchaseKey, String accountName,
			Date startDateGT, Date startDateLT, long[] licenseEntryIds,
			String[] productKeys, String productName, String productId,
			String[] productVersions, long[] clusterIds, String owner,
			String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			Date expirationDateGT, Date expirationDateLT,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.searchCount(
			createUserUuid, createDateGT, createDateLT, modifiedUserUuid,
			modifiedDateGT, modifiedDateLT, accountKey, productPurchaseKey,
			accountName, startDateGT, startDateLT, licenseEntryIds, productKeys,
			productName, productId, productVersions, clusterIds, owner,
			description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGT, expirationDateLT, params, andSearch);
	}

	public int searchCount(
			String keywords, LinkedHashMap<String, Object> params)
		throws Exception {

		addPermissionParams(params);

		return licenseKeyLocalService.searchCount(keywords, params);
	}

	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws Exception {

		//TODO: add permission check

		licenseKeyLocalService.updateLicenseKey(userId, licenseKeyId, active);
	}

	public LicenseKey updateLicenseKey(
			long licenseKeyId, String productPurchaseKey, boolean complimentary,
			boolean active)
		throws Exception {

		//TODO: add permission check

		return licenseKeyLocalService.updateLicenseKey(
			getUserId(), licenseKeyId, productPurchaseKey, complimentary,
			active);
	}

	@JSONWebService
	public void updateLicenseKey(String userUuid, String uuid, boolean active)
		throws Exception {

		validateJSONWebServicePermissions();

		long companyId = _portalInstancesLocalService.getDefaultCompanyId();

		User user = userLocalService.getDefaultUser(companyId);

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
			String[] accountKeys = new String[accounts.size()];

			for (int i = 0; i < accounts.size(); i++) {
				Account account = accounts.get(i);

				accountKeys[i] = account.getKey();
			}

			params.put("accountMembership", accountKeys);
		}

		params.put("active", Boolean.TRUE);
		params.put("user", user.getUserId());
	}

	protected List<LicenseKey> filterLicenseKeys(List<LicenseKey> licenseKeys)
		throws PortalException {

		//TODO: add permission check

		return ListUtil.copy(licenseKeys);
	}

	protected boolean isAccountAdmin(long userId) {
		//TODO: add permission check

		return true;
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private RoleLocalService _roleLocalService;

}
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

package com.liferay.osb.customer.license.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseKeyService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyService
 * @generated
 */
public class LicenseKeyServiceWrapper
	implements LicenseKeyService, ServiceWrapper<LicenseKeyService> {

	public LicenseKeyServiceWrapper(LicenseKeyService licenseKeyService) {
		_licenseKeyService = licenseKeyService;
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey
			addDeveloperLicenseKey(
				long accountEntryId, long productEntryId,
				int productMinorVersion)
		throws Exception {

		return _licenseKeyService.addDeveloperLicenseKey(
			accountEntryId, productEntryId, productMinorVersion);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey addLicenseKey(
			long userId, long licenseKeySetId, String name, long licenseEntryId,
			long productEntryId, String koroneikiAccountKey,
			String koroneikiProductPurchaseKey, String accountEntryName,
			int productVersion, long clusterId, String owner, int maxServers,
			int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
			int sizing, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, String[] serverIds,
			java.util.Date startDate, java.util.Date expirationDate,
			boolean complimentary, boolean active)
		throws Exception {

		return _licenseKeyService.addLicenseKey(
			userId, licenseKeySetId, name, licenseEntryId, productEntryId,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			complimentary, active);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyService.addLicenseKey(
			userUuid, assetReceiptLicenseUuid, licenseEntryType,
			productEntryName, productId, productVersion, owner, maxUsers,
			description, hostName, ipAddresses, macAddresses, serverId,
			startDate, expirationDate);
	}

	@Override
	public String generateCommerceLicenseKey(
			String owner, java.util.Date startDate, long licenseLifetime)
		throws Exception {

		return _licenseKeyService.generateCommerceLicenseKey(
			owner, startDate, licenseLifetime);
	}

	@Override
	public String generateWeDeployLicenseKey(
			String owner, java.util.Date startDate, long licenseLifetime)
		throws Exception {

		return _licenseKeyService.generateWeDeployLicenseKey(
			owner, startDate, licenseLifetime);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			getAssetReceiptLicenseLicenseKeys(
				String assetReceiptLicenseUuid, boolean complimentary,
				boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey getLicenseKey(
			long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey getLicenseKey(
			String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKey(uuid);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			getLicenseKeys(long userId, String productId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeys(userId, productId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			getLicenseKeys(String productId, String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeys(productId, serverId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			getLicenseKeys(
				String assetReceiptLicenseUuid, String productId,
				String serverId, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			getLicenseKeysByName(
				String productEntryName, String serverId, boolean active,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeysByName(
			productEntryName, serverId, active, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			getLicenseKeySetLicenseKeys(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			getOfferingEntryGroupLicenseKeys(
				long[] offeringEntryIds, boolean complimentary, boolean active,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getOfferingEntryGroupLicenseKeys(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	@Override
	public int getOfferingEntryGroupLicenseKeysCount(
			long[] offeringEntryIds, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getOfferingEntryGroupLicenseKeysCount(
			offeringEntryIds, complimentary, active);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(
			long offeringEntryId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.getOfferingEntryLicenseKeysCount(
			offeringEntryId, complimentary, active);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseKeyService.getOSGiServiceIdentifier();
	}

	@Override
	public boolean isActive(String serverId, String productId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.isActive(serverId, productId, key);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey registerLicenseKey(
			String orderEntryUuid, String productEntryName, int liferayVersion,
			int maxServers, String hostName, String ipAddresses,
			String macAddresses, String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyService.registerLicenseKey(
			orderEntryUuid, productEntryName, liferayVersion, maxServers,
			hostName, ipAddresses, macAddresses, serverId);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey renewLicenseKey(
			long licenseKeyId, java.util.Date startDate, int renewTime)
		throws Exception {

		return _licenseKeyService.renewLicenseKey(
			licenseKeyId, startDate, renewTime);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey renewLicenseKey(
			String uuid, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyService.renewLicenseKey(
			uuid, startDate, expirationDate);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			search(
				Long createUserId, int createDateGTDay, int createDateGTMonth,
				int createDateGTYear, int createDateLTDay,
				int createDateLTMonth, int createDateLTYear,
				Long modifiedUserId, int modifiedDateGTDay,
				int modifiedDateGTMonth, int modifiedDateGTYear,
				int modifiedDateLTDay, int modifiedDateLTMonth,
				int modifiedDateLTYear, String koroneikiAccountKey,
				String koroneikiProductPurchaseKey, String accountEntryName,
				String licenseKeySetName, int startDateGTDay,
				int startDateGTMonth, int startDateGTYear, int startDateLTDay,
				int startDateLTMonth, int startDateLTYear,
				long[] licenseEntryIds, long[] productEntryIds,
				String productEntryName, String productId,
				int[] productVersions, String owner, String description,
				String hostName, String ipAddress, String macAddress,
				String serverId, String key, int expirationDateGTDay,
				int expirationDateGTMonth, int expirationDateGTYear,
				int expirationDateLTDay, int expirationDateLTMonth,
				int expirationDateLTYear,
				java.util.LinkedHashMap<String, Object> params,
				boolean andSearch, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws Exception {

		return _licenseKeyService.search(
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

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
			search(
				String keywords, java.util.LinkedHashMap<String, Object> params,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws Exception {

		return _licenseKeyService.search(keywords, params, start, end, obc);
	}

	@Override
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
			java.util.LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		return _licenseKeyService.searchCount(
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

	@Override
	public int searchCount(
			String keywords, java.util.LinkedHashMap<String, Object> params)
		throws Exception {

		return _licenseKeyService.searchCount(keywords, params);
	}

	@Override
	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws Exception {

		_licenseKeyService.updateLicenseKey(userId, licenseKeyId, active);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey updateLicenseKey(
			long licenseKeyId, long licenseKeySetId,
			String koroneikiProductPurchaseKey, String name,
			boolean complimentary, boolean active)
		throws Exception {

		return _licenseKeyService.updateLicenseKey(
			licenseKeyId, licenseKeySetId, koroneikiProductPurchaseKey, name,
			complimentary, active);
	}

	@Override
	public void updateLicenseKey(String userUuid, String uuid, boolean active)
		throws Exception {

		_licenseKeyService.updateLicenseKey(userUuid, uuid, active);
	}

	@Override
	public void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws Exception {

		_licenseKeyService.updateLicenseKeys(assetReceiptLicenseUuid, active);
	}

	@Override
	public LicenseKeyService getWrappedService() {
		return _licenseKeyService;
	}

	@Override
	public void setWrappedService(LicenseKeyService licenseKeyService) {
		_licenseKeyService = licenseKeyService;
	}

	private LicenseKeyService _licenseKeyService;

}
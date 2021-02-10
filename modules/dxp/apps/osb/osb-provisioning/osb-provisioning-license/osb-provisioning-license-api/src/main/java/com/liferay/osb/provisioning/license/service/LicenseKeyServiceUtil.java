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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for LicenseKey. This utility wraps
 * <code>com.liferay.osb.customer.license.service.impl.LicenseKeyServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyService
 * @generated
 */
public class LicenseKeyServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.license.service.impl.LicenseKeyServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
			addDeveloperLicenseKey(
				long accountEntryId, long productEntryId,
				int productMinorVersion)
		throws Exception {

		return getService().addDeveloperLicenseKey(
			accountEntryId, productEntryId, productMinorVersion);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			addLicenseKey(
				long userId, long licenseKeySetId, String name,
				long licenseEntryId, long productEntryId,
				String koroneikiAccountKey, String koroneikiProductPurchaseKey,
				String accountEntryName, int productVersion, long clusterId,
				String owner, int maxServers, int maxHttpSessions,
				int maxConcurrentUsers, int maxUsers, int sizing,
				String description, String[] hostNames, String[] ipAddresses,
				String[] macAddresses, String[] serverIds,
				java.util.Date startDate, java.util.Date expirationDate,
				boolean complimentary, boolean active)
		throws Exception {

		return getService().addLicenseKey(
			userId, licenseKeySetId, name, licenseEntryId, productEntryId,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			complimentary, active);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			addLicenseKey(
				String userUuid, String assetReceiptLicenseUuid,
				String licenseEntryType, String productEntryName,
				String productId, int productVersion, String owner,
				long maxUsers, String description, String hostName,
				String ipAddresses, String macAddresses, String serverId,
				java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		return getService().addLicenseKey(
			userUuid, assetReceiptLicenseUuid, licenseEntryType,
			productEntryName, productId, productVersion, owner, maxUsers,
			description, hostName, ipAddresses, macAddresses, serverId,
			startDate, expirationDate);
	}

	public static String generateCommerceLicenseKey(
			String owner, java.util.Date startDate, long licenseLifetime)
		throws Exception {

		return getService().generateCommerceLicenseKey(
			owner, startDate, licenseLifetime);
	}

	public static String generateWeDeployLicenseKey(
			String owner, java.util.Date startDate, long licenseLifetime)
		throws Exception {

		return getService().generateWeDeployLicenseKey(
			owner, startDate, licenseLifetime);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getAssetReceiptLicenseLicenseKeys(
					String assetReceiptLicenseUuid, boolean complimentary,
					boolean active)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			getLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKey(licenseKeyId);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			getLicenseKey(String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKey(uuid);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
				long userId, String productId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeys(userId, productId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
				String productId, String serverId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeys(productId, serverId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
				String assetReceiptLicenseUuid, String productId,
				String serverId, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getLicenseKeysByName(
					String productEntryName, String serverId, boolean active,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeysByName(
			productEntryName, serverId, active, start, end, obc);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getLicenseKeySetLicenseKeys(long licenseKeySetId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getOfferingEntryGroupLicenseKeys(
					long[] offeringEntryIds, boolean complimentary,
					boolean active, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOfferingEntryGroupLicenseKeys(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
			long[] offeringEntryIds, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOfferingEntryGroupLicenseKeysCount(
			offeringEntryIds, complimentary, active);
	}

	public static int getOfferingEntryLicenseKeysCount(
			long offeringEntryId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOfferingEntryLicenseKeysCount(
			offeringEntryId, complimentary, active);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static boolean isActive(
			String serverId, String productId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().isActive(serverId, productId, key);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			registerLicenseKey(
				String orderEntryUuid, String productEntryName,
				int liferayVersion, int maxServers, String hostName,
				String ipAddresses, String macAddresses, String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().registerLicenseKey(
			orderEntryUuid, productEntryName, liferayVersion, maxServers,
			hostName, ipAddresses, macAddresses, serverId);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			renewLicenseKey(
				long licenseKeyId, java.util.Date startDate, int renewTime)
		throws Exception {

		return getService().renewLicenseKey(licenseKeyId, startDate, renewTime);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			renewLicenseKey(
				String uuid, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		return getService().renewLicenseKey(uuid, startDate, expirationDate);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> search(
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

		return getService().search(
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

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> search(
				String keywords, java.util.LinkedHashMap<String, Object> params,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
			throws Exception {

		return getService().search(keywords, params, start, end, obc);
	}

	public static int searchCount(
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

		return getService().searchCount(
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

	public static int searchCount(
			String keywords, java.util.LinkedHashMap<String, Object> params)
		throws Exception {

		return getService().searchCount(keywords, params);
	}

	public static void updateLicenseKey(
			long userId, long licenseKeyId, boolean active)
		throws Exception {

		getService().updateLicenseKey(userId, licenseKeyId, active);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			updateLicenseKey(
				long licenseKeyId, long licenseKeySetId,
				String koroneikiProductPurchaseKey, String name,
				boolean complimentary, boolean active)
		throws Exception {

		return getService().updateLicenseKey(
			licenseKeyId, licenseKeySetId, koroneikiProductPurchaseKey, name,
			complimentary, active);
	}

	public static void updateLicenseKey(
			String userUuid, String uuid, boolean active)
		throws Exception {

		getService().updateLicenseKey(userUuid, uuid, active);
	}

	public static void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws Exception {

		getService().updateLicenseKeys(assetReceiptLicenseUuid, active);
	}

	public static LicenseKeyService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LicenseKeyService, LicenseKeyService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LicenseKeyService.class);

		ServiceTracker<LicenseKeyService, LicenseKeyService> serviceTracker =
			new ServiceTracker<LicenseKeyService, LicenseKeyService>(
				bundle.getBundleContext(), LicenseKeyService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
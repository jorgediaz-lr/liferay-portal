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
 * Provides the local service utility for LicenseKey. This utility wraps
 * <code>com.liferay.osb.customer.license.service.impl.LicenseKeyLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalService
 * @generated
 */
public class LicenseKeyLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.license.service.impl.LicenseKeyLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
			addDeveloperLicenseKey(
				long userId, long accountEntryId, long productEntryId,
				int productMinorVersion)
		throws Exception {

		return getService().addDeveloperLicenseKey(
			userId, accountEntryId, productEntryId, productMinorVersion);
	}

	/**
	 * Adds the license key to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKey the license key
	 * @return the license key that was added
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
		addLicenseKey(
			com.liferay.osb.customer.license.model.LicenseKey licenseKey) {

		return getService().addLicenseKey(licenseKey);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			addLicenseKey(
				long userId,
				com.liferay.osb.customer.license.model.LicenseKeySet
					licenseKeySet,
				String name,
				com.liferay.osb.customer.admin.model.LicenseEntry licenseEntry,
				com.liferay.osb.customer.admin.model.ProductEntry productEntry,
				String koroneikiAccountKey, String koroneikiProductPurchaseKey,
				String accountEntryName, int productVersion, long clusterId,
				String owner, int maxServers, int maxHttpSessions,
				int maxConcurrentUsers, int maxUsers, int sizing,
				String description, String[] hostNames, String[] ipAddresses,
				String[] macAddresses, String[] serverIds,
				java.util.Date startDate, java.util.Date expirationDate,
				String additionalInfo, boolean complimentary, boolean active)
		throws Exception {

		return getService().addLicenseKey(
			userId, licenseKeySet, name, licenseEntry, productEntry,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			additionalInfo, complimentary, active);
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
				long userId, String assetReceiptLicenseUuid,
				String licenseEntryType, String productEntryName,
				String productId, int productVersion, String owner,
				long maxUsers, String description, String hostName,
				String ipAddresses, String macAddresses, String serverId,
				java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		return getService().addLicenseKey(
			userId, assetReceiptLicenseUuid, licenseEntryType, productEntryName,
			productId, productVersion, owner, maxUsers, description, hostName,
			ipAddresses, macAddresses, serverId, startDate, expirationDate);
	}

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
		createLicenseKey(long licenseKeyId) {

		return getService().createLicenseKey(licenseKeyId);
	}

	/**
	 * Deletes the license key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKey the license key
	 * @return the license key that was removed
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
		deleteLicenseKey(
			com.liferay.osb.customer.license.model.LicenseKey licenseKey) {

		return getService().deleteLicenseKey(licenseKey);
	}

	/**
	 * Deletes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
			deleteLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLicenseKey(licenseKeyId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.license.model.impl.LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.license.model.impl.LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
		fetchLicenseKey(long licenseKeyId) {

		return getService().fetchLicenseKey(licenseKeyId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getAccountEntryLicenseKeys(long accountEntryId) {

		return getService().getAccountEntryLicenseKeys(accountEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getAssetReceiptLicenseLicenseKeys(
				String assetReceiptLicenseUuid, boolean active) {

		return getService().getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, active);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getAssetReceiptLicenseLicenseKeys(
				String assetReceiptLicenseUuid, boolean complimentary,
				boolean active) {

		return getService().getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return getService().getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			getFirstLicenseKey(
				long accountEntryId,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFirstLicenseKey(accountEntryId, obc);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.license.model.impl.LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of license keies
	 */
	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeies(
			int start, int end) {

		return getService().getLicenseKeies(start, end);
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	public static int getLicenseKeiesCount() {
		return getService().getLicenseKeiesCount();
	}

	/**
	 * Returns the license key with the primary key.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
			getLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKey(licenseKeyId);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			getLicenseKeyByUuid(String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeyByUuid(uuid);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
			long userId, long accountEntryId) {

		return getService().getLicenseKeys(userId, accountEntryId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
			long userId, String productId) {

		return getService().getLicenseKeys(userId, productId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
			String koroneikiProductPurchaseKey, int start, int end) {

		return getService().getLicenseKeys(
			koroneikiProductPurchaseKey, start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
			String koroneikiProductPurchaseKey, long clusterId) {

		return getService().getLicenseKeys(
			koroneikiProductPurchaseKey, clusterId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
			String koroneikiAccountKey, long productEntryId, int start,
			int end) {

		return getService().getLicenseKeys(
			koroneikiAccountKey, productEntryId, start, end);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
			String productId, String serverId) {

		return getService().getLicenseKeys(productId, serverId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return getService().getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getLicenseKeysByName(
				String productEntryName, String serverId, boolean active,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc) {

		return getService().getLicenseKeysByName(
			productEntryName, serverId, active, start, end, obc);
	}

	public static int getLicenseKeysCount(String koroneikiProductPurchaseKey) {
		return getService().getLicenseKeysCount(koroneikiProductPurchaseKey);
	}

	public static int getLicenseKeysCount(
		String koroneikiAccountKey, long productEntryId) {

		return getService().getLicenseKeysCount(
			koroneikiAccountKey, productEntryId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getLicenseKeySetLicenseKeys(long licenseKeySetId) {

		return getService().getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getOfferingEntryGroupLicenseKeys(
				long[] offeringEntryIds, boolean complimentary, boolean active,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc) {

		return getService().getOfferingEntryGroupLicenseKeys(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		return getService().getOfferingEntryGroupLicenseKeysCount(
			offeringEntryIds, complimentary, active);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getOfferingEntryLicenseKeys(long offeringEntryId) {

		return getService().getOfferingEntryLicenseKeys(offeringEntryId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getOfferingEntryLicenseKeys(
				long offeringEntryId, boolean complimentary, boolean active) {

		return getService().getOfferingEntryLicenseKeys(
			offeringEntryId, complimentary, active);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getOfferingEntryLicenseKeys(long offeringEntryId, long clusterId) {

		return getService().getOfferingEntryLicenseKeys(
			offeringEntryId, clusterId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
			getOfferingEntryLicenseKeys(
				long offeringEntryId, long clusterId, boolean active) {

		return getService().getOfferingEntryLicenseKeys(
			offeringEntryId, clusterId, active);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId) {
		return getService().getOfferingEntryLicenseKeysCount(offeringEntryId);
	}

	public static int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, boolean complimentary, boolean active) {

		return getService().getOfferingEntryLicenseKeysCount(
			offeringEntryId, complimentary, active);
	}

	public static int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId) {

		return getService().getOfferingEntryLicenseKeysCount(
			offeringEntryId, clusterId);
	}

	public static int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId, boolean active) {

		return getService().getOfferingEntryLicenseKeysCount(
			offeringEntryId, clusterId, active);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getUserLicenseKeysCount(
		long userId, long accountEntryId) {

		return getService().getUserLicenseKeysCount(userId, accountEntryId);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			renewLicenseKey(
				long userId, long licenseKeyId, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		return getService().renewLicenseKey(
			userId, licenseKeyId, startDate, expirationDate);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			renewLicenseKey(
				long userId, long licenseKeyId, java.util.Date startDate,
				int renewTime)
		throws Exception {

		return getService().renewLicenseKey(
			userId, licenseKeyId, startDate, renewTime);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> search(
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
			java.util.LinkedHashMap<String, Object> params, boolean andSearch,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

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
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return getService().search(keywords, params, start, end, obc);
	}

	public static int searchCount(
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
		java.util.LinkedHashMap<String, Object> params, boolean andSearch) {

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
		String keywords, java.util.LinkedHashMap<String, Object> params) {

		return getService().searchCount(keywords, params);
	}

	/**
	 * Updates the license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeyLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKey the license key
	 * @return the license key that was updated
	 */
	public static com.liferay.osb.customer.license.model.LicenseKey
		updateLicenseKey(
			com.liferay.osb.customer.license.model.LicenseKey licenseKey) {

		return getService().updateLicenseKey(licenseKey);
	}

	public static void updateLicenseKey(
			long userId, long licenseKeyId, boolean active)
		throws Exception {

		getService().updateLicenseKey(userId, licenseKeyId, active);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			updateLicenseKey(
				long licenseKeyId, long accountEntryId, long offeringEntryId,
				long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLicenseKey(
			licenseKeyId, accountEntryId, offeringEntryId, orderEntryId);
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			updateLicenseKey(
				long userId, long licenseKeyId, long licenseKeySetId,
				String koroneikiProductPurchaseKey, String name,
				boolean complimentary, boolean active)
		throws Exception {

		return getService().updateLicenseKey(
			userId, licenseKeyId, licenseKeySetId, koroneikiProductPurchaseKey,
			name, complimentary, active);
	}

	public static LicenseKeyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LicenseKeyLocalService, LicenseKeyLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LicenseKeyLocalService.class);

		ServiceTracker<LicenseKeyLocalService, LicenseKeyLocalService>
			serviceTracker =
				new ServiceTracker
					<LicenseKeyLocalService, LicenseKeyLocalService>(
						bundle.getBundleContext(), LicenseKeyLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
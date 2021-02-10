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
 * Provides a wrapper for {@link LicenseKeyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalService
 * @generated
 */
public class LicenseKeyLocalServiceWrapper
	implements LicenseKeyLocalService, ServiceWrapper<LicenseKeyLocalService> {

	public LicenseKeyLocalServiceWrapper(
		LicenseKeyLocalService licenseKeyLocalService) {

		_licenseKeyLocalService = licenseKeyLocalService;
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey
			addDeveloperLicenseKey(
				long userId, long accountEntryId, long productEntryId,
				int productMinorVersion)
		throws Exception {

		return _licenseKeyLocalService.addDeveloperLicenseKey(
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKey addLicenseKey(
		com.liferay.osb.customer.license.model.LicenseKey licenseKey) {

		return _licenseKeyLocalService.addLicenseKey(licenseKey);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey addLicenseKey(
			long userId,
			com.liferay.osb.customer.license.model.LicenseKeySet licenseKeySet,
			String name,
			com.liferay.osb.customer.admin.model.LicenseEntry licenseEntry,
			com.liferay.osb.customer.admin.model.ProductEntry productEntry,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String accountEntryName, int productVersion, long clusterId,
			String owner, int maxServers, int maxHttpSessions,
			int maxConcurrentUsers, int maxUsers, int sizing,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, java.util.Date startDate,
			java.util.Date expirationDate, String additionalInfo,
			boolean complimentary, boolean active)
		throws Exception {

		return _licenseKeyLocalService.addLicenseKey(
			userId, licenseKeySet, name, licenseEntry, productEntry,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			additionalInfo, complimentary, active);
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

		return _licenseKeyLocalService.addLicenseKey(
			userId, licenseKeySetId, name, licenseEntryId, productEntryId,
			koroneikiAccountKey, koroneikiProductPurchaseKey, accountEntryName,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			maxConcurrentUsers, maxUsers, sizing, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDate, expirationDate,
			complimentary, active);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey addLicenseKey(
			long userId, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyLocalService.addLicenseKey(
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKey createLicenseKey(
		long licenseKeyId) {

		return _licenseKeyLocalService.createLicenseKey(licenseKeyId);
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKey deleteLicenseKey(
		com.liferay.osb.customer.license.model.LicenseKey licenseKey) {

		return _licenseKeyLocalService.deleteLicenseKey(licenseKey);
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKey deleteLicenseKey(
			long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.deleteLicenseKey(licenseKeyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseKeyLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _licenseKeyLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _licenseKeyLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _licenseKeyLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _licenseKeyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _licenseKeyLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey fetchLicenseKey(
		long licenseKeyId) {

		return _licenseKeyLocalService.fetchLicenseKey(licenseKeyId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getAccountEntryLicenseKeys(long accountEntryId) {

		return _licenseKeyLocalService.getAccountEntryLicenseKeys(
			accountEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _licenseKeyLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean active) {

		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, active);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active) {

		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey getFirstLicenseKey(
			long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getFirstLicenseKey(accountEntryId, obc);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _licenseKeyLocalService.getIndexableActionableDynamicQuery();
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
	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeies(int start, int end) {

		return _licenseKeyLocalService.getLicenseKeies(start, end);
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	@Override
	public int getLicenseKeiesCount() {
		return _licenseKeyLocalService.getLicenseKeiesCount();
	}

	/**
	 * Returns the license key with the primary key.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.license.model.LicenseKey getLicenseKey(
			long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey
			getLicenseKeyByUuid(String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getLicenseKeyByUuid(uuid);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeys(long userId, long accountEntryId) {

		return _licenseKeyLocalService.getLicenseKeys(userId, accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeys(long userId, String productId) {

		return _licenseKeyLocalService.getLicenseKeys(userId, productId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeys(String koroneikiProductPurchaseKey, int start, int end) {

		return _licenseKeyLocalService.getLicenseKeys(
			koroneikiProductPurchaseKey, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeys(String koroneikiProductPurchaseKey, long clusterId) {

		return _licenseKeyLocalService.getLicenseKeys(
			koroneikiProductPurchaseKey, clusterId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeys(
			String koroneikiAccountKey, long productEntryId, int start,
			int end) {

		return _licenseKeyLocalService.getLicenseKeys(
			koroneikiAccountKey, productEntryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeys(String productId, String serverId) {

		return _licenseKeyLocalService.getLicenseKeys(productId, serverId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _licenseKeyLocalService.getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeysByName(
			String productEntryName, String serverId, boolean active, int start,
			int end, com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _licenseKeyLocalService.getLicenseKeysByName(
			productEntryName, serverId, active, start, end, obc);
	}

	@Override
	public int getLicenseKeysCount(String koroneikiProductPurchaseKey) {
		return _licenseKeyLocalService.getLicenseKeysCount(
			koroneikiProductPurchaseKey);
	}

	@Override
	public int getLicenseKeysCount(
		String koroneikiAccountKey, long productEntryId) {

		return _licenseKeyLocalService.getLicenseKeysCount(
			koroneikiAccountKey, productEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getLicenseKeySetLicenseKeys(long licenseKeySetId) {

		return _licenseKeyLocalService.getLicenseKeySetLicenseKeys(
			licenseKeySetId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getOfferingEntryGroupLicenseKeys(
			long[] offeringEntryIds, boolean complimentary, boolean active,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _licenseKeyLocalService.getOfferingEntryGroupLicenseKeys(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	@Override
	public int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		return _licenseKeyLocalService.getOfferingEntryGroupLicenseKeysCount(
			offeringEntryIds, complimentary, active);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getOfferingEntryLicenseKeys(long offeringEntryId) {

		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(
			offeringEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getOfferingEntryLicenseKeys(
			long offeringEntryId, boolean complimentary, boolean active) {

		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(
			offeringEntryId, complimentary, active);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getOfferingEntryLicenseKeys(long offeringEntryId, long clusterId) {

		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(
			offeringEntryId, clusterId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		getOfferingEntryLicenseKeys(
			long offeringEntryId, long clusterId, boolean active) {

		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(
			offeringEntryId, clusterId, active);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(
			offeringEntryId);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, boolean complimentary, boolean active) {

		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(
			offeringEntryId, complimentary, active);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId) {

		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(
			offeringEntryId, clusterId);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId, boolean active) {

		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(
			offeringEntryId, clusterId, active);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseKeyLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getUserLicenseKeysCount(long userId, long accountEntryId) {
		return _licenseKeyLocalService.getUserLicenseKeysCount(
			userId, accountEntryId);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, java.util.Date startDate,
			java.util.Date expirationDate)
		throws Exception {

		return _licenseKeyLocalService.renewLicenseKey(
			userId, licenseKeyId, startDate, expirationDate);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, java.util.Date startDate,
			int renewTime)
		throws Exception {

		return _licenseKeyLocalService.renewLicenseKey(
			userId, licenseKeyId, startDate, renewTime);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
		search(
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

		return _licenseKeyLocalService.search(
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
			com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _licenseKeyLocalService.search(
			keywords, params, start, end, obc);
	}

	@Override
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
		java.util.LinkedHashMap<String, Object> params, boolean andSearch) {

		return _licenseKeyLocalService.searchCount(
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
		String keywords, java.util.LinkedHashMap<String, Object> params) {

		return _licenseKeyLocalService.searchCount(keywords, params);
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKey updateLicenseKey(
		com.liferay.osb.customer.license.model.LicenseKey licenseKey) {

		return _licenseKeyLocalService.updateLicenseKey(licenseKey);
	}

	@Override
	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws Exception {

		_licenseKeyLocalService.updateLicenseKey(userId, licenseKeyId, active);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey updateLicenseKey(
			long licenseKeyId, long accountEntryId, long offeringEntryId,
			long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeyLocalService.updateLicenseKey(
			licenseKeyId, accountEntryId, offeringEntryId, orderEntryId);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKey updateLicenseKey(
			long userId, long licenseKeyId, long licenseKeySetId,
			String koroneikiProductPurchaseKey, String name,
			boolean complimentary, boolean active)
		throws Exception {

		return _licenseKeyLocalService.updateLicenseKey(
			userId, licenseKeyId, licenseKeySetId, koroneikiProductPurchaseKey,
			name, complimentary, active);
	}

	@Override
	public LicenseKeyLocalService getWrappedService() {
		return _licenseKeyLocalService;
	}

	@Override
	public void setWrappedService(
		LicenseKeyLocalService licenseKeyLocalService) {

		_licenseKeyLocalService = licenseKeyLocalService;
	}

	private LicenseKeyLocalService _licenseKeyLocalService;

}
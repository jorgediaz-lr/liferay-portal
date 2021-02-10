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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for LicenseKey. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LicenseKeyLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.customer.license.service.impl.LicenseKeyLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the license key local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LicenseKeyLocalServiceUtil} if injection and service tracking are not available.
	 */
	public LicenseKey addDeveloperLicenseKey(
			long userId, long accountEntryId, long productEntryId,
			int productMinorVersion)
		throws Exception;

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
	@Indexable(type = IndexableType.REINDEX)
	public LicenseKey addLicenseKey(LicenseKey licenseKey);

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
		throws Exception;

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
		throws Exception;

	public LicenseKey addLicenseKey(
			long userId, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws Exception;

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	@Transactional(enabled = false)
	public LicenseKey createLicenseKey(long licenseKeyId);

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
	@Indexable(type = IndexableType.DELETE)
	public LicenseKey deleteLicenseKey(LicenseKey licenseKey);

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
	@Indexable(type = IndexableType.DELETE)
	public LicenseKey deleteLicenseKey(long licenseKeyId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey fetchLicenseKey(long licenseKeyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAccountEntryLicenseKeys(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getFirstLicenseKey(
			long accountEntryId, OrderByComparator obc)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeies(int start, int end);

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLicenseKeiesCount();

	/**
	 * Returns the license key with the primary key.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws PortalException if a license key with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(long licenseKeyId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKeyByUuid(String uuid) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(long userId, long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(long userId, String productId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(
		String koroneikiProductPurchaseKey, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(
		String koroneikiProductPurchaseKey, long clusterId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(
		String koroneikiAccountKey, long productEntryId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(String productId, String serverId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeysByName(
		String productEntryName, String serverId, boolean active, int start,
		int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLicenseKeysCount(String koroneikiProductPurchaseKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLicenseKeysCount(
		String koroneikiAccountKey, long productEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(long offeringEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId, boolean active);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserLicenseKeysCount(long userId, long accountEntryId);

	public LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, Date startDate, Date expirationDate)
		throws Exception;

	public LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, Date startDate, int renewTime)
		throws Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
		int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> search(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
		LinkedHashMap<String, Object> params, boolean andSearch);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(
		String keywords, LinkedHashMap<String, Object> params);

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
	@Indexable(type = IndexableType.REINDEX)
	public LicenseKey updateLicenseKey(LicenseKey licenseKey);

	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws Exception;

	public LicenseKey updateLicenseKey(
			long licenseKeyId, long accountEntryId, long offeringEntryId,
			long orderEntryId)
		throws PortalException;

	public LicenseKey updateLicenseKey(
			long userId, long licenseKeyId, long licenseKeySetId,
			String koroneikiProductPurchaseKey, String name,
			boolean complimentary, boolean active)
		throws Exception;

}
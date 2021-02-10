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

package com.liferay.osb.customer.license.service.persistence;

import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the license key service. This utility wraps <code>com.liferay.osb.customer.license.service.persistence.impl.LicenseKeyPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyPersistence
 * @generated
 */
public class LicenseKeyUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(LicenseKey licenseKey) {
		getPersistence().clearCache(licenseKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, LicenseKey> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LicenseKey update(LicenseKey licenseKey) {
		return getPersistence().update(licenseKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LicenseKey update(
		LicenseKey licenseKey, ServiceContext serviceContext) {

		return getPersistence().update(licenseKey, serviceContext);
	}

	/**
	 * Returns all the license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByUuid_First(
			String uuid, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByUuid_First(
		String uuid, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByUuid_Last(
			String uuid, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByUuid_Last(
		String uuid, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where uuid = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByUuid_PrevAndNext(
			long licenseKeyId, String uuid,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByUuid_PrevAndNext(
			licenseKeyId, uuid, orderByComparator);
	}

	/**
	 * Removes all the license keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching license keies
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId) {
		return getPersistence().findByLicenseKeySetId(licenseKeySetId);
	}

	/**
	 * Returns a range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end) {

		return getPersistence().findByLicenseKeySetId(
			licenseKeySetId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByLicenseKeySetId(
			licenseKeySetId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLicenseKeySetId(
			licenseKeySetId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByLicenseKeySetId_First(
			long licenseKeySetId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByLicenseKeySetId_First(
			licenseKeySetId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByLicenseKeySetId_First(
		long licenseKeySetId, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByLicenseKeySetId_First(
			licenseKeySetId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByLicenseKeySetId_Last(
			long licenseKeySetId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByLicenseKeySetId_Last(
			licenseKeySetId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByLicenseKeySetId_Last(
		long licenseKeySetId, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByLicenseKeySetId_Last(
			licenseKeySetId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByLicenseKeySetId_PrevAndNext(
			long licenseKeyId, long licenseKeySetId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByLicenseKeySetId_PrevAndNext(
			licenseKeyId, licenseKeySetId, orderByComparator);
	}

	/**
	 * Removes all the license keies where licenseKeySetId = &#63; from the database.
	 *
	 * @param licenseKeySetId the license key set ID
	 */
	public static void removeByLicenseKeySetId(long licenseKeySetId) {
		getPersistence().removeByLicenseKeySetId(licenseKeySetId);
	}

	/**
	 * Returns the number of license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the number of matching license keies
	 */
	public static int countByLicenseKeySetId(long licenseKeySetId) {
		return getPersistence().countByLicenseKeySetId(licenseKeySetId);
	}

	/**
	 * Returns all the license keies where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		return getPersistence().findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey);
	}

	/**
	 * Returns a range of all the license keies where koroneikiProductPurchaseKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey, int start, int end) {

		return getPersistence().findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where koroneikiProductPurchaseKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where koroneikiProductPurchaseKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByKoroneikiProductPurchaseKey_First(
			String koroneikiProductPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKoroneikiProductPurchaseKey_First(
			koroneikiProductPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByKoroneikiProductPurchaseKey_First(
		String koroneikiProductPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByKoroneikiProductPurchaseKey_First(
			koroneikiProductPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByKoroneikiProductPurchaseKey_Last(
			String koroneikiProductPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKoroneikiProductPurchaseKey_Last(
			koroneikiProductPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByKoroneikiProductPurchaseKey_Last(
		String koroneikiProductPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByKoroneikiProductPurchaseKey_Last(
			koroneikiProductPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByKoroneikiProductPurchaseKey_PrevAndNext(
			long licenseKeyId, String koroneikiProductPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKoroneikiProductPurchaseKey_PrevAndNext(
			licenseKeyId, koroneikiProductPurchaseKey, orderByComparator);
	}

	/**
	 * Removes all the license keies where koroneikiProductPurchaseKey = &#63; from the database.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 */
	public static void removeByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		getPersistence().removeByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey);
	}

	/**
	 * Returns the number of license keies where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @return the number of matching license keies
	 */
	public static int countByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		return getPersistence().countByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey);
	}

	/**
	 * Returns all the license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns a range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByAccountEntryId_First(
			long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByAccountEntryId_First(
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByAccountEntryId_Last(
			long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByAccountEntryId_Last(
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByAccountEntryId_PrevAndNext(
			long licenseKeyId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAccountEntryId_PrevAndNext(
			licenseKeyId, accountEntryId, orderByComparator);
	}

	/**
	 * Removes all the license keies where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the number of license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 */
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByOfferingEntryId(long offeringEntryId) {
		return getPersistence().findByOfferingEntryId(offeringEntryId);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end) {

		return getPersistence().findByOfferingEntryId(
			offeringEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByOfferingEntryId(
			offeringEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOfferingEntryId(
			offeringEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOfferingEntryId_First(
			long offeringEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOfferingEntryId_First(
			offeringEntryId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOfferingEntryId_First(
		long offeringEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOfferingEntryId_First(
			offeringEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOfferingEntryId_Last(
			long offeringEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOfferingEntryId_Last(
			offeringEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOfferingEntryId_Last(
		long offeringEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOfferingEntryId_Last(
			offeringEntryId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByOfferingEntryId_PrevAndNext(
			long licenseKeyId, long offeringEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOfferingEntryId_PrevAndNext(
			licenseKeyId, offeringEntryId, orderByComparator);
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 */
	public static void removeByOfferingEntryId(long offeringEntryId) {
		getPersistence().removeByOfferingEntryId(offeringEntryId);
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the number of matching license keies
	 */
	public static int countByOfferingEntryId(long offeringEntryId) {
		return getPersistence().countByOfferingEntryId(offeringEntryId);
	}

	/**
	 * Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByU_AEI(
		long userId, long accountEntryId) {

		return getPersistence().findByU_AEI(userId, accountEntryId);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end) {

		return getPersistence().findByU_AEI(userId, accountEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByU_AEI(
			userId, accountEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_AEI(
			userId, accountEntryId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByU_AEI_First(
			long userId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_AEI_First(
			userId, accountEntryId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByU_AEI_First(
		long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByU_AEI_First(
			userId, accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByU_AEI_Last(
			long userId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_AEI_Last(
			userId, accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByU_AEI_Last(
		long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByU_AEI_Last(
			userId, accountEntryId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByU_AEI_PrevAndNext(
			long licenseKeyId, long userId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_AEI_PrevAndNext(
			licenseKeyId, userId, accountEntryId, orderByComparator);
	}

	/**
	 * Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 */
	public static void removeByU_AEI(long userId, long accountEntryId) {
		getPersistence().removeByU_AEI(userId, accountEntryId);
	}

	/**
	 * Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 */
	public static int countByU_AEI(long userId, long accountEntryId) {
		return getPersistence().countByU_AEI(userId, accountEntryId);
	}

	/**
	 * Returns all the license keies where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByU_PI(long userId, String productId) {
		return getPersistence().findByU_PI(userId, productId);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByU_PI(
		long userId, String productId, int start, int end) {

		return getPersistence().findByU_PI(userId, productId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByU_PI(
		long userId, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByU_PI(
			userId, productId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByU_PI(
		long userId, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_PI(
			userId, productId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByU_PI_First(
			long userId, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_PI_First(
			userId, productId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByU_PI_First(
		long userId, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByU_PI_First(
			userId, productId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByU_PI_Last(
			long userId, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_PI_Last(
			userId, productId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByU_PI_Last(
		long userId, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByU_PI_Last(
			userId, productId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByU_PI_PrevAndNext(
			long licenseKeyId, long userId, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_PI_PrevAndNext(
			licenseKeyId, userId, productId, orderByComparator);
	}

	/**
	 * Removes all the license keies where userId = &#63; and productId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 */
	public static void removeByU_PI(long userId, String productId) {
		getPersistence().removeByU_PI(userId, productId);
	}

	/**
	 * Returns the number of license keies where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @return the number of matching license keies
	 */
	public static int countByU_PI(long userId, String productId) {
		return getPersistence().countByU_PI(userId, productId);
	}

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active) {

		return getPersistence().findByARLU_A(assetReceiptLicenseUuid, active);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end) {

		return getPersistence().findByARLU_A(
			assetReceiptLicenseUuid, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByARLU_A(
			assetReceiptLicenseUuid, active, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByARLU_A(
			assetReceiptLicenseUuid, active, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByARLU_A_First(
			String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_A_First(
			assetReceiptLicenseUuid, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByARLU_A_First(
		String assetReceiptLicenseUuid, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByARLU_A_First(
			assetReceiptLicenseUuid, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByARLU_A_Last(
			String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_A_Last(
			assetReceiptLicenseUuid, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByARLU_A_Last(
		String assetReceiptLicenseUuid, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByARLU_A_Last(
			assetReceiptLicenseUuid, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByARLU_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_A_PrevAndNext(
			licenseKeyId, assetReceiptLicenseUuid, active, orderByComparator);
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 */
	public static void removeByARLU_A(
		String assetReceiptLicenseUuid, boolean active) {

		getPersistence().removeByARLU_A(assetReceiptLicenseUuid, active);
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByARLU_A(
		String assetReceiptLicenseUuid, boolean active) {

		return getPersistence().countByARLU_A(assetReceiptLicenseUuid, active);
	}

	/**
	 * Returns all the license keies where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId) {

		return getPersistence().findByKA_PEI(
			koroneikiAccountKey, productEntryId);
	}

	/**
	 * Returns a range of all the license keies where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId, int start, int end) {

		return getPersistence().findByKA_PEI(
			koroneikiAccountKey, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByKA_PEI(
			koroneikiAccountKey, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByKA_PEI(
			koroneikiAccountKey, productEntryId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByKA_PEI_First(
			String koroneikiAccountKey, long productEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKA_PEI_First(
			koroneikiAccountKey, productEntryId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByKA_PEI_First(
		String koroneikiAccountKey, long productEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByKA_PEI_First(
			koroneikiAccountKey, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByKA_PEI_Last(
			String koroneikiAccountKey, long productEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKA_PEI_Last(
			koroneikiAccountKey, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByKA_PEI_Last(
		String koroneikiAccountKey, long productEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByKA_PEI_Last(
			koroneikiAccountKey, productEntryId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByKA_PEI_PrevAndNext(
			long licenseKeyId, String koroneikiAccountKey, long productEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKA_PEI_PrevAndNext(
			licenseKeyId, koroneikiAccountKey, productEntryId,
			orderByComparator);
	}

	/**
	 * Removes all the license keies where koroneikiAccountKey = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 */
	public static void removeByKA_PEI(
		String koroneikiAccountKey, long productEntryId) {

		getPersistence().removeByKA_PEI(koroneikiAccountKey, productEntryId);
	}

	/**
	 * Returns the number of license keies where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @return the number of matching license keies
	 */
	public static int countByKA_PEI(
		String koroneikiAccountKey, long productEntryId) {

		return getPersistence().countByKA_PEI(
			koroneikiAccountKey, productEntryId);
	}

	/**
	 * Returns all the license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId) {

		return getPersistence().findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId);
	}

	/**
	 * Returns a range of all the license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId, int start,
		int end) {

		return getPersistence().findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByKPP_CI_First(
			String koroneikiProductPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKPP_CI_First(
			koroneikiProductPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByKPP_CI_First(
		String koroneikiProductPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByKPP_CI_First(
			koroneikiProductPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByKPP_CI_Last(
			String koroneikiProductPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKPP_CI_Last(
			koroneikiProductPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByKPP_CI_Last(
		String koroneikiProductPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByKPP_CI_Last(
			koroneikiProductPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByKPP_CI_PrevAndNext(
			long licenseKeyId, String koroneikiProductPurchaseKey,
			long clusterId, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByKPP_CI_PrevAndNext(
			licenseKeyId, koroneikiProductPurchaseKey, clusterId,
			orderByComparator);
	}

	/**
	 * Removes all the license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63; from the database.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 */
	public static void removeByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId) {

		getPersistence().removeByKPP_CI(koroneikiProductPurchaseKey, clusterId);
	}

	/**
	 * Returns the number of license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	public static int countByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId) {

		return getPersistence().countByKPP_CI(
			koroneikiProductPurchaseKey, clusterId);
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId) {

		return getPersistence().findByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end) {

		return getPersistence().findByOEI_CI(
			offeringEntryId, clusterId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByOEI_CI(
			offeringEntryId, clusterId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOEI_CI(
			offeringEntryId, clusterId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_CI_First(
			long offeringEntryId, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_CI_First(
			offeringEntryId, clusterId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_CI_First(
		long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_CI_First(
			offeringEntryId, clusterId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_CI_Last(
			long offeringEntryId, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_CI_Last(
			offeringEntryId, clusterId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_CI_Last(
		long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_CI_Last(
			offeringEntryId, clusterId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByOEI_CI_PrevAndNext(
			long licenseKeyId, long offeringEntryId, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_CI_PrevAndNext(
			licenseKeyId, offeringEntryId, clusterId, orderByComparator);
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 */
	public static void removeByOEI_CI(long offeringEntryId, long clusterId) {
		getPersistence().removeByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	public static int countByOEI_CI(long offeringEntryId, long clusterId) {
		return getPersistence().countByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	 * Returns all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPI_SI(
		String productId, String serverId) {

		return getPersistence().findByPI_SI(productId, serverId);
	}

	/**
	 * Returns a range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end) {

		return getPersistence().findByPI_SI(productId, serverId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPI_SI(
			productId, serverId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPI_SI(
			productId, serverId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPI_SI_First(
			String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPI_SI_First(
			productId, serverId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPI_SI_First(
		String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPI_SI_First(
			productId, serverId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPI_SI_Last(
			String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPI_SI_Last(
			productId, serverId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPI_SI_Last(
		String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPI_SI_Last(
			productId, serverId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPI_SI_PrevAndNext(
			long licenseKeyId, String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPI_SI_PrevAndNext(
			licenseKeyId, productId, serverId, orderByComparator);
	}

	/**
	 * Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 */
	public static void removeByPI_SI(String productId, String serverId) {
		getPersistence().removeByPI_SI(productId, serverId);
	}

	/**
	 * Returns the number of license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the number of matching license keies
	 */
	public static int countByPI_SI(String productId, String serverId) {
		return getPersistence().countByPI_SI(productId, serverId);
	}

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return getPersistence().findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end) {

		return getPersistence().findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByARLU_C_A_First(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_C_A_First(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByARLU_C_A_First(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByARLU_C_A_First(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByARLU_C_A_Last(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_C_A_Last(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByARLU_C_A_Last(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByARLU_C_A_Last(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByARLU_C_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_C_A_PrevAndNext(
			licenseKeyId, assetReceiptLicenseUuid, complimentary, active,
			orderByComparator);
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public static void removeByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		getPersistence().removeByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return getPersistence().countByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active) {

		return getPersistence().findByOEI_CI_A(
			offeringEntryId, clusterId, active);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end) {

		return getPersistence().findByOEI_CI_A(
			offeringEntryId, clusterId, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByOEI_CI_A(
			offeringEntryId, clusterId, active, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOEI_CI_A(
			offeringEntryId, clusterId, active, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_CI_A_First(
			long offeringEntryId, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_CI_A_First(
			offeringEntryId, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_CI_A_First(
		long offeringEntryId, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_CI_A_First(
			offeringEntryId, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_CI_A_Last(
			long offeringEntryId, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_CI_A_Last(
			offeringEntryId, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_CI_A_Last(
		long offeringEntryId, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_CI_A_Last(
			offeringEntryId, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByOEI_CI_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, long clusterId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_CI_A_PrevAndNext(
			licenseKeyId, offeringEntryId, clusterId, active,
			orderByComparator);
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 */
	public static void removeByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active) {

		getPersistence().removeByOEI_CI_A(offeringEntryId, clusterId, active);
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active) {

		return getPersistence().countByOEI_CI_A(
			offeringEntryId, clusterId, active);
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active) {

		return getPersistence().findByOEI_C_A(
			offeringEntryId, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end) {

		return getPersistence().findByOEI_C_A(
			offeringEntryId, complimentary, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByOEI_C_A(
			offeringEntryId, complimentary, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOEI_C_A(
			offeringEntryId, complimentary, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_C_A_First(
			long offeringEntryId, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_C_A_First(
			offeringEntryId, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_C_A_First(
		long offeringEntryId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_C_A_First(
			offeringEntryId, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_C_A_Last(
			long offeringEntryId, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_C_A_Last(
			offeringEntryId, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_C_A_Last(
		long offeringEntryId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_C_A_Last(
			offeringEntryId, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByOEI_C_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_C_A_PrevAndNext(
			licenseKeyId, offeringEntryId, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		return getPersistence().findByOEI_C_A(
			offeringEntryIds, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end) {

		return getPersistence().findByOEI_C_A(
			offeringEntryIds, complimentary, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByOEI_C_A(
			offeringEntryIds, complimentary, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOEI_C_A(
			offeringEntryIds, complimentary, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public static void removeByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active) {

		getPersistence().removeByOEI_C_A(
			offeringEntryId, complimentary, active);
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active) {

		return getPersistence().countByOEI_C_A(
			offeringEntryId, complimentary, active);
	}

	/**
	 * Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		return getPersistence().countByOEI_C_A(
			offeringEntryIds, complimentary, active);
	}

	/**
	 * Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active) {

		return getPersistence().findByPEN_SI_A(
			productEntryName, serverId, active);
	}

	/**
	 * Returns a range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active, int start,
		int end) {

		return getPersistence().findByPEN_SI_A(
			productEntryName, serverId, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPEN_SI_A(
			productEntryName, serverId, active, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPEN_SI_A(
			productEntryName, serverId, active, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPEN_SI_A_First(
			String productEntryName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPEN_SI_A_First(
			productEntryName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPEN_SI_A_First(
		String productEntryName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPEN_SI_A_First(
			productEntryName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPEN_SI_A_Last(
			String productEntryName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPEN_SI_A_Last(
			productEntryName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPEN_SI_A_Last(
		String productEntryName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPEN_SI_A_Last(
			productEntryName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPEN_SI_A_PrevAndNext(
			long licenseKeyId, String productEntryName, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPEN_SI_A_PrevAndNext(
			licenseKeyId, productEntryName, serverId, active,
			orderByComparator);
	}

	/**
	 * Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 */
	public static void removeByPEN_SI_A(
		String productEntryName, String serverId, boolean active) {

		getPersistence().removeByPEN_SI_A(productEntryName, serverId, active);
	}

	/**
	 * Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByPEN_SI_A(
		String productEntryName, String serverId, boolean active) {

		return getPersistence().countByPEN_SI_A(
			productEntryName, serverId, active);
	}

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		return getPersistence().findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end) {

		return getPersistence().findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByARLU_PI_SI_A_First(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_PI_SI_A_First(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByARLU_PI_SI_A_First(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByARLU_PI_SI_A_First(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByARLU_PI_SI_A_Last(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_PI_SI_A_Last(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByARLU_PI_SI_A_Last(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByARLU_PI_SI_A_Last(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByARLU_PI_SI_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, String productId,
			String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByARLU_PI_SI_A_PrevAndNext(
			licenseKeyId, assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 */
	public static void removeByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		getPersistence().removeByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active);
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		return getPersistence().countByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active);
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		return getPersistence().findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end) {

		return getPersistence().findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_LET_C_A_First(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_LET_C_A_First(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_LET_C_A_First(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_LET_C_A_First(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_LET_C_A_Last(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_LET_C_A_Last(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_LET_C_A_Last(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_LET_C_A_Last(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByOEI_LET_C_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_LET_C_A_PrevAndNext(
			licenseKeyId, offeringEntryId, licenseEntryType, complimentary,
			active, orderByComparator);
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public static void removeByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		getPersistence().removeByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		return getPersistence().countByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		return getPersistence().findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end) {

		return getPersistence().findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_NotLET_C_A_First(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_NotLET_C_A_First(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_NotLET_C_A_First(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_NotLET_C_A_First(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByOEI_NotLET_C_A_Last(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_NotLET_C_A_Last(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByOEI_NotLET_C_A_Last(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByOEI_NotLET_C_A_Last(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByOEI_NotLET_C_A_PrevAndNext(
			licenseKeyId, offeringEntryId, licenseEntryType, complimentary,
			active, orderByComparator);
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public static void removeByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		getPersistence().removeByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		return getPersistence().countByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);
	}

	/**
	 * Caches the license key in the entity cache if it is enabled.
	 *
	 * @param licenseKey the license key
	 */
	public static void cacheResult(LicenseKey licenseKey) {
		getPersistence().cacheResult(licenseKey);
	}

	/**
	 * Caches the license keies in the entity cache if it is enabled.
	 *
	 * @param licenseKeies the license keies
	 */
	public static void cacheResult(List<LicenseKey> licenseKeies) {
		getPersistence().cacheResult(licenseKeies);
	}

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	public static LicenseKey create(long licenseKeyId) {
		return getPersistence().create(licenseKeyId);
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey remove(long licenseKeyId)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().remove(licenseKeyId);
	}

	public static LicenseKey updateImpl(LicenseKey licenseKey) {
		return getPersistence().updateImpl(licenseKey);
	}

	/**
	 * Returns the license key with the primary key or throws a <code>NoSuchLicenseKeyException</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey findByPrimaryKey(long licenseKeyId)
		throws com.liferay.osb.customer.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPrimaryKey(licenseKeyId);
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	public static LicenseKey fetchByPrimaryKey(long licenseKeyId) {
		return getPersistence().fetchByPrimaryKey(licenseKeyId);
	}

	/**
	 * Returns all the license keies.
	 *
	 * @return the license keies
	 */
	public static List<LicenseKey> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of license keies
	 */
	public static List<LicenseKey> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license keies
	 */
	public static List<LicenseKey> findAll(
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license keies
	 */
	public static List<LicenseKey> findAll(
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the license keies from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LicenseKeyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LicenseKeyPersistence, LicenseKeyPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LicenseKeyPersistence.class);

		ServiceTracker<LicenseKeyPersistence, LicenseKeyPersistence>
			serviceTracker =
				new ServiceTracker
					<LicenseKeyPersistence, LicenseKeyPersistence>(
						bundle.getBundleContext(), LicenseKeyPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
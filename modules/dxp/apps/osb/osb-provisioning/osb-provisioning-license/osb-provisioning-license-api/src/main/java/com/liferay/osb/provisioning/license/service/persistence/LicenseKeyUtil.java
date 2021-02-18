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

package com.liferay.osb.provisioning.license.service.persistence;

import com.liferay.osb.provisioning.license.model.LicenseKey;
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
 * The persistence utility for the license key service. This utility wraps <code>com.liferay.osb.provisioning.license.service.persistence.impl.LicenseKeyPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
	 * Returns all the license keies where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByAccountKey(String accountKey) {
		return getPersistence().findByAccountKey(accountKey);
	}

	/**
	 * Returns a range of all the license keies where accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end) {

		return getPersistence().findByAccountKey(accountKey, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByAccountKey(
			accountKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountKey(
			accountKey, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByAccountKey_First(
			String accountKey, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAccountKey_First(
			accountKey, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByAccountKey_First(
		String accountKey, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByAccountKey_First(
			accountKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByAccountKey_Last(
			String accountKey, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAccountKey_Last(
			accountKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByAccountKey_Last(
		String accountKey, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByAccountKey_Last(
			accountKey, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where accountKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByAccountKey_PrevAndNext(
			long licenseKeyId, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAccountKey_PrevAndNext(
			licenseKeyId, accountKey, orderByComparator);
	}

	/**
	 * Removes all the license keies where accountKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 */
	public static void removeByAccountKey(String accountKey) {
		getPersistence().removeByAccountKey(accountKey);
	}

	/**
	 * Returns the number of license keies where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the number of matching license keies
	 */
	public static int countByAccountKey(String accountKey) {
		return getPersistence().countByAccountKey(accountKey);
	}

	/**
	 * Returns all the license keies where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey) {

		return getPersistence().findByProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end) {

		return getPersistence().findByProductPurchaseKey(
			productPurchaseKey, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByProductPurchaseKey(
			productPurchaseKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByProductPurchaseKey(
			productPurchaseKey, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByProductPurchaseKey_First(
			String productPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByProductPurchaseKey_First(
			productPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByProductPurchaseKey_First(
		String productPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByProductPurchaseKey_First(
			productPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByProductPurchaseKey_Last(
			String productPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByProductPurchaseKey_Last(
			productPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByProductPurchaseKey_Last(
		String productPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByProductPurchaseKey_Last(
			productPurchaseKey, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByProductPurchaseKey_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByProductPurchaseKey_PrevAndNext(
			licenseKeyId, productPurchaseKey, orderByComparator);
	}

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 */
	public static void removeByProductPurchaseKey(String productPurchaseKey) {
		getPersistence().removeByProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the number of matching license keies
	 */
	public static int countByProductPurchaseKey(String productPurchaseKey) {
		return getPersistence().countByProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Returns all the license keies where userId = &#63; and accountKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByU_AK(long userId, String accountKey) {
		return getPersistence().findByU_AK(userId, accountKey);
	}

	/**
	 * Returns a range of all the license keies where userId = &#63; and accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByU_AK(
		long userId, String accountKey, int start, int end) {

		return getPersistence().findByU_AK(userId, accountKey, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByU_AK(
		long userId, String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByU_AK(
			userId, accountKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where userId = &#63; and accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByU_AK(
		long userId, String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_AK(
			userId, accountKey, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and accountKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByU_AK_First(
			long userId, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_AK_First(
			userId, accountKey, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and accountKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByU_AK_First(
		long userId, String accountKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByU_AK_First(
			userId, accountKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByU_AK_Last(
			long userId, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_AK_Last(
			userId, accountKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByU_AK_Last(
		long userId, String accountKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByU_AK_Last(
			userId, accountKey, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userId = &#63; and accountKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByU_AK_PrevAndNext(
			long licenseKeyId, long userId, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByU_AK_PrevAndNext(
			licenseKeyId, userId, accountKey, orderByComparator);
	}

	/**
	 * Removes all the license keies where userId = &#63; and accountKey = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 */
	public static void removeByU_AK(long userId, String accountKey) {
		getPersistence().removeByU_AK(userId, accountKey);
	}

	/**
	 * Returns the number of license keies where userId = &#63; and accountKey = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountKey the account key
	 * @return the number of matching license keies
	 */
	public static int countByU_AK(long userId, String accountKey) {
		return getPersistence().countByU_AK(userId, accountKey);
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
	 * Returns all the license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByAK_PK(
		String accountKey, String productKey) {

		return getPersistence().findByAK_PK(accountKey, productKey);
	}

	/**
	 * Returns a range of all the license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end) {

		return getPersistence().findByAK_PK(accountKey, productKey, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByAK_PK(
			accountKey, productKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAK_PK(
			accountKey, productKey, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByAK_PK_First(
			String accountKey, String productKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAK_PK_First(
			accountKey, productKey, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByAK_PK_First(
		String accountKey, String productKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByAK_PK_First(
			accountKey, productKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByAK_PK_Last(
			String accountKey, String productKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAK_PK_Last(
			accountKey, productKey, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByAK_PK_Last(
		String accountKey, String productKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByAK_PK_Last(
			accountKey, productKey, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByAK_PK_PrevAndNext(
			long licenseKeyId, String accountKey, String productKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByAK_PK_PrevAndNext(
			licenseKeyId, accountKey, productKey, orderByComparator);
	}

	/**
	 * Removes all the license keies where accountKey = &#63; and productKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 */
	public static void removeByAK_PK(String accountKey, String productKey) {
		getPersistence().removeByAK_PK(accountKey, productKey);
	}

	/**
	 * Returns the number of license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @return the number of matching license keies
	 */
	public static int countByAK_PK(String accountKey, String productKey) {
		return getPersistence().countByAK_PK(accountKey, productKey);
	}

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId) {

		return getPersistence().findByPPK_CI(productPurchaseKey, clusterId);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end) {

		return getPersistence().findByPPK_CI(
			productPurchaseKey, clusterId, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPPK_CI(
			productPurchaseKey, clusterId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPPK_CI(
			productPurchaseKey, clusterId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_CI_First(
			String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_CI_First(
			productPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_CI_First(
		String productPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_CI_First(
			productPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_CI_Last(
			String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_CI_Last(
			productPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_CI_Last(
		String productPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_CI_Last(
			productPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPPK_CI_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_CI_PrevAndNext(
			licenseKeyId, productPurchaseKey, clusterId, orderByComparator);
	}

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and clusterId = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 */
	public static void removeByPPK_CI(
		String productPurchaseKey, long clusterId) {

		getPersistence().removeByPPK_CI(productPurchaseKey, clusterId);
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	public static int countByPPK_CI(String productPurchaseKey, long clusterId) {
		return getPersistence().countByPPK_CI(productPurchaseKey, clusterId);
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
	 * Returns all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active) {

		return getPersistence().findByPPK_CI_A(
			productPurchaseKey, clusterId, active);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end) {

		return getPersistence().findByPPK_CI_A(
			productPurchaseKey, clusterId, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPPK_CI_A(
			productPurchaseKey, clusterId, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPPK_CI_A(
			productPurchaseKey, clusterId, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_CI_A_First(
			String productPurchaseKey, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_CI_A_First(
			productPurchaseKey, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_CI_A_First(
		String productPurchaseKey, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_CI_A_First(
			productPurchaseKey, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_CI_A_Last(
			String productPurchaseKey, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_CI_A_Last(
			productPurchaseKey, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_CI_A_Last(
		String productPurchaseKey, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_CI_A_Last(
			productPurchaseKey, clusterId, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPPK_CI_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, long clusterId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_CI_A_PrevAndNext(
			licenseKeyId, productPurchaseKey, clusterId, active,
			orderByComparator);
	}

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 */
	public static void removeByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active) {

		getPersistence().removeByPPK_CI_A(
			productPurchaseKey, clusterId, active);
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active) {

		return getPersistence().countByPPK_CI_A(
			productPurchaseKey, clusterId, active);
	}

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKey, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKey, complimentary, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKey, complimentary, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKey, complimentary, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_C_A_First(
			String productPurchaseKey, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_C_A_First(
			productPurchaseKey, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_C_A_First(
		String productPurchaseKey, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_C_A_First(
			productPurchaseKey, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_C_A_Last(
			String productPurchaseKey, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_C_A_Last(
			productPurchaseKey, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_C_A_Last(
		String productPurchaseKey, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_C_A_Last(
			productPurchaseKey, complimentary, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPPK_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_C_A_PrevAndNext(
			licenseKeyId, productPurchaseKey, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns all the license keies where productPurchaseKey = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKeies the product purchase keies
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKeies, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKeies the product purchase keies
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKeies, complimentary, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKeies the product purchase keies
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKeies, complimentary, active, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPPK_C_A(
			productPurchaseKeies, complimentary, active, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public static void removeByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active) {

		getPersistence().removeByPPK_C_A(
			productPurchaseKey, complimentary, active);
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active) {

		return getPersistence().countByPPK_C_A(
			productPurchaseKey, complimentary, active);
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKeies the product purchase keies
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active) {

		return getPersistence().countByPPK_C_A(
			productPurchaseKeies, complimentary, active);
	}

	/**
	 * Returns all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active) {

		return getPersistence().findByPN_SI_A(productName, serverId, active);
	}

	/**
	 * Returns a range of all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start,
		int end) {

		return getPersistence().findByPN_SI_A(
			productName, serverId, active, start, end);
	}

	/**
	 * Returns an ordered range of all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPN_SI_A(
			productName, serverId, active, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPN_SI_A(
			productName, serverId, active, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPN_SI_A_First(
			String productName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPN_SI_A_First(
			productName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPN_SI_A_First(
		String productName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPN_SI_A_First(
			productName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPN_SI_A_Last(
			String productName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPN_SI_A_Last(
			productName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPN_SI_A_Last(
		String productName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPN_SI_A_Last(
			productName, serverId, active, orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPN_SI_A_PrevAndNext(
			long licenseKeyId, String productName, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPN_SI_A_PrevAndNext(
			licenseKeyId, productName, serverId, active, orderByComparator);
	}

	/**
	 * Removes all the license keies where productName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 */
	public static void removeByPN_SI_A(
		String productName, String serverId, boolean active) {

		getPersistence().removeByPN_SI_A(productName, serverId, active);
	}

	/**
	 * Returns the number of license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByPN_SI_A(
		String productName, String serverId, boolean active) {

		return getPersistence().countByPN_SI_A(productName, serverId, active);
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
	 * Returns all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		return getPersistence().findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end) {

		return getPersistence().findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_LET_C_A_First(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_LET_C_A_First(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_LET_C_A_First(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_LET_C_A_First(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_LET_C_A_Last(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_LET_C_A_Last(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_LET_C_A_Last(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_LET_C_A_Last(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPPK_LET_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			String licenseEntryType, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_LET_C_A_PrevAndNext(
			licenseKeyId, productPurchaseKey, licenseEntryType, complimentary,
			active, orderByComparator);
	}

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public static void removeByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		getPersistence().removeByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		return getPersistence().countByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public static List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		return getPersistence().findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end) {

		return getPersistence().findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public static List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_NotLET_C_A_First(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_NotLET_C_A_First(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_NotLET_C_A_First(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_NotLET_C_A_First(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public static LicenseKey findByPPK_NotLET_C_A_Last(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_NotLET_C_A_Last(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public static LicenseKey fetchByPPK_NotLET_C_A_Last(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		return getPersistence().fetchByPPK_NotLET_C_A_Last(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public static LicenseKey[] findByPPK_NotLET_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			String licenseEntryType, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseKeyException {

		return getPersistence().findByPPK_NotLET_C_A_PrevAndNext(
			licenseKeyId, productPurchaseKey, licenseEntryType, complimentary,
			active, orderByComparator);
	}

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public static void removeByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		getPersistence().removeByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public static int countByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		return getPersistence().countByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);
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
		throws com.liferay.osb.provisioning.license.exception.
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
		throws com.liferay.osb.provisioning.license.exception.
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
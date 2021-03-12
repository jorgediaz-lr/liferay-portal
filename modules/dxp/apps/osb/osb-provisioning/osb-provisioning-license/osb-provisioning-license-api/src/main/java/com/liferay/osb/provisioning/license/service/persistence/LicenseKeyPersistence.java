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

import com.liferay.osb.provisioning.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyUtil
 * @generated
 */
@ProviderType
public interface LicenseKeyPersistence extends BasePersistence<LicenseKey> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseKeyUtil} to access the license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByUuid(String uuid);

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
	public java.util.List<LicenseKey> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the license keies before and after the current license key in the ordered set where uuid = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public LicenseKey[] findByUuid_PrevAndNext(
			long licenseKeyId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching license keies
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the license keies where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByAccountKey(String accountKey);

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
	public java.util.List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end);

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
	public java.util.List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByAccountKey_First(
			String accountKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByAccountKey_First(
		String accountKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByAccountKey_Last(
			String accountKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByAccountKey_Last(
		String accountKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the license keies before and after the current license key in the ordered set where accountKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public LicenseKey[] findByAccountKey_PrevAndNext(
			long licenseKeyId, String accountKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where accountKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 */
	public void removeByAccountKey(String accountKey);

	/**
	 * Returns the number of license keies where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the number of matching license keies
	 */
	public int countByAccountKey(String accountKey);

	/**
	 * Returns all the license keies where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey);

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
	public java.util.List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end);

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
	public java.util.List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByProductPurchaseKey_First(
			String productPurchaseKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByProductPurchaseKey_First(
		String productPurchaseKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByProductPurchaseKey_Last(
			String productPurchaseKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByProductPurchaseKey_Last(
		String productPurchaseKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public LicenseKey[] findByProductPurchaseKey_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 */
	public void removeByProductPurchaseKey(String productPurchaseKey);

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the number of matching license keies
	 */
	public int countByProductPurchaseKey(String productPurchaseKey);

	/**
	 * Returns all the license keies where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByU_AK(
		String userUuid, String accountKey);

	/**
	 * Returns a range of all the license keies where userUuid = &#63; and accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public java.util.List<LicenseKey> findByU_AK(
		String userUuid, String accountKey, int start, int end);

	/**
	 * Returns an ordered range of all the license keies where userUuid = &#63; and accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public java.util.List<LicenseKey> findByU_AK(
		String userUuid, String accountKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license keies where userUuid = &#63; and accountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public java.util.List<LicenseKey> findByU_AK(
		String userUuid, String accountKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByU_AK_First(
			String userUuid, String accountKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByU_AK_First(
		String userUuid, String accountKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByU_AK_Last(
			String userUuid, String accountKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByU_AK_Last(
		String userUuid, String accountKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public LicenseKey[] findByU_AK_PrevAndNext(
			long licenseKeyId, String userUuid, String accountKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where userUuid = &#63; and accountKey = &#63; from the database.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 */
	public void removeByU_AK(String userUuid, String accountKey);

	/**
	 * Returns the number of license keies where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @return the number of matching license keies
	 */
	public int countByU_AK(String userUuid, String accountKey);

	/**
	 * Returns all the license keies where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByU_PI(
		String userUuid, String productId);

	/**
	 * Returns a range of all the license keies where userUuid = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	public java.util.List<LicenseKey> findByU_PI(
		String userUuid, String productId, int start, int end);

	/**
	 * Returns an ordered range of all the license keies where userUuid = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	public java.util.List<LicenseKey> findByU_PI(
		String userUuid, String productId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license keies where userUuid = &#63; and productId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	public java.util.List<LicenseKey> findByU_PI(
		String userUuid, String productId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByU_PI_First(
			String userUuid, String productId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByU_PI_First(
		String userUuid, String productId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByU_PI_Last(
			String userUuid, String productId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByU_PI_Last(
		String userUuid, String productId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the license keies before and after the current license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public LicenseKey[] findByU_PI_PrevAndNext(
			long licenseKeyId, String userUuid, String productId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where userUuid = &#63; and productId = &#63; from the database.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 */
	public void removeByU_PI(String userUuid, String productId);

	/**
	 * Returns the number of license keies where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @return the number of matching license keies
	 */
	public int countByU_PI(String userUuid, String productId);

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active);

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
	public java.util.List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByARLU_A_First(
			String assetReceiptLicenseUuid, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByARLU_A_First(
		String assetReceiptLicenseUuid, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByARLU_A_Last(
			String assetReceiptLicenseUuid, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByARLU_A_Last(
		String assetReceiptLicenseUuid, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByARLU_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 */
	public void removeByARLU_A(String assetReceiptLicenseUuid, boolean active);

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByARLU_A(String assetReceiptLicenseUuid, boolean active);

	/**
	 * Returns all the license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByAK_PK(
		String accountKey, String productKey);

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
	public java.util.List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end);

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
	public java.util.List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByAK_PK_First(
			String accountKey, String productKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByAK_PK_First(
		String accountKey, String productKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByAK_PK_Last(
			String accountKey, String productKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByAK_PK_Last(
		String accountKey, String productKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByAK_PK_PrevAndNext(
			long licenseKeyId, String accountKey, String productKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where accountKey = &#63; and productKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 */
	public void removeByAK_PK(String accountKey, String productKey);

	/**
	 * Returns the number of license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @return the number of matching license keies
	 */
	public int countByAK_PK(String accountKey, String productKey);

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId);

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
	public java.util.List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end);

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
	public java.util.List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByPPK_CI_First(
			String productPurchaseKey, long clusterId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPPK_CI_First(
		String productPurchaseKey, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByPPK_CI_Last(
			String productPurchaseKey, long clusterId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPPK_CI_Last(
		String productPurchaseKey, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByPPK_CI_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, long clusterId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and clusterId = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 */
	public void removeByPPK_CI(String productPurchaseKey, long clusterId);

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	public int countByPPK_CI(String productPurchaseKey, long clusterId);

	/**
	 * Returns all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByPI_SI(
		String productId, String serverId);

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
	public java.util.List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end);

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
	public java.util.List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByPI_SI_First(
			String productId, String serverId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPI_SI_First(
		String productId, String serverId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	public LicenseKey findByPI_SI_Last(
			String productId, String serverId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPI_SI_Last(
		String productId, String serverId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByPI_SI_PrevAndNext(
			long licenseKeyId, String productId, String serverId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 */
	public void removeByPI_SI(String productId, String serverId);

	/**
	 * Returns the number of license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the number of matching license keies
	 */
	public int countByPI_SI(String productId, String serverId);

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end);

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
	public java.util.List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

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
	public LicenseKey findByARLU_C_A_First(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByARLU_C_A_First(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey findByARLU_C_A_Last(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByARLU_C_A_Last(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByARLU_C_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid,
			boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public void removeByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active);

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active);

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active);

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
	public java.util.List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end);

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
	public java.util.List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

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
	public LicenseKey findByPPK_CI_A_First(
			String productPurchaseKey, long clusterId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPPK_CI_A_First(
		String productPurchaseKey, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey findByPPK_CI_A_Last(
			String productPurchaseKey, long clusterId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPPK_CI_A_Last(
		String productPurchaseKey, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByPPK_CI_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, long clusterId,
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 */
	public void removeByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active);

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active);

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end);

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
	public java.util.List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

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
	public LicenseKey findByPPK_C_A_First(
			String productPurchaseKey, boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPPK_C_A_First(
		String productPurchaseKey, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey findByPPK_C_A_Last(
			String productPurchaseKey, boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPPK_C_A_Last(
		String productPurchaseKey, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByPPK_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, boolean complimentary,
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public java.util.List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end);

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
	public java.util.List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public void removeByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active);

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active);

	/**
	 * Returns the number of license keies where productPurchaseKey = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKeies the product purchase keies
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active);

	/**
	 * Returns all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active);

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
	public java.util.List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start,
		int end);

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
	public java.util.List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

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
	public LicenseKey findByPN_SI_A_First(
			String productName, String serverId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the first license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPN_SI_A_First(
		String productName, String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey findByPN_SI_A_Last(
			String productName, String serverId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the last license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	public LicenseKey fetchByPN_SI_A_Last(
		String productName, String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByPN_SI_A_PrevAndNext(
			long licenseKeyId, String productName, String serverId,
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where productName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 */
	public void removeByPN_SI_A(
		String productName, String serverId, boolean active);

	/**
	 * Returns the number of license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByPN_SI_A(
		String productName, String serverId, boolean active);

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active);

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
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

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
	public LicenseKey findByARLU_PI_SI_A_First(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByARLU_PI_SI_A_First(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey findByARLU_PI_SI_A_Last(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByARLU_PI_SI_A_Last(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByARLU_PI_SI_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, String productId,
			String serverId, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 */
	public void removeByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active);

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active);

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

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
	public LicenseKey findByPPK_LET_C_A_First(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByPPK_LET_C_A_First(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey findByPPK_LET_C_A_Last(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByPPK_LET_C_A_Last(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByPPK_LET_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			String licenseEntryType, boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public void removeByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active);

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active);

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	public java.util.List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

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
	public LicenseKey findByPPK_NotLET_C_A_First(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByPPK_NotLET_C_A_First(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey findByPPK_NotLET_C_A_Last(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByPPK_NotLET_C_A_Last(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public LicenseKey[] findByPPK_NotLET_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			String licenseEntryType, boolean complimentary, boolean active,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
				orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	public void removeByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active);

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	public int countByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active);

	/**
	 * Caches the license key in the entity cache if it is enabled.
	 *
	 * @param licenseKey the license key
	 */
	public void cacheResult(LicenseKey licenseKey);

	/**
	 * Caches the license keies in the entity cache if it is enabled.
	 *
	 * @param licenseKeies the license keies
	 */
	public void cacheResult(java.util.List<LicenseKey> licenseKeies);

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	public LicenseKey create(long licenseKeyId);

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public LicenseKey remove(long licenseKeyId)
		throws NoSuchLicenseKeyException;

	public LicenseKey updateImpl(LicenseKey licenseKey);

	/**
	 * Returns the license key with the primary key or throws a <code>NoSuchLicenseKeyException</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	public LicenseKey findByPrimaryKey(long licenseKeyId)
		throws NoSuchLicenseKeyException;

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	public LicenseKey fetchByPrimaryKey(long licenseKeyId);

	/**
	 * Returns all the license keies.
	 *
	 * @return the license keies
	 */
	public java.util.List<LicenseKey> findAll();

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
	public java.util.List<LicenseKey> findAll(int start, int end);

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
	public java.util.List<LicenseKey> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator);

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
	public java.util.List<LicenseKey> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the license keies from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	public int countAll();

}
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

import com.liferay.osb.provisioning.license.exception.NoSuchLicenseEntryException;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the license entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryUtil
 * @generated
 */
@ProviderType
public interface LicenseEntryPersistence extends BasePersistence<LicenseEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseEntryUtil} to access the license entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the license entries where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductKey(String productKey);

	/**
	 * Returns a range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductKey(
		String productKey, int start, int end);

	/**
	 * Returns an ordered range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductKey(
		String productKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductKey(
		String productKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByProductKey_First(
			String productKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the first license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByProductKey_First(
		String productKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns the last license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByProductKey_Last(
			String productKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the last license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByProductKey_Last(
		String productKey,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productKey = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public LicenseEntry[] findByProductKey_PrevAndNext(
			long licenseEntryId, String productKey,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Removes all the license entries where productKey = &#63; from the database.
	 *
	 * @param productKey the product key
	 */
	public void removeByProductKey(String productKey);

	/**
	 * Returns the number of license entries where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the number of matching license entries
	 */
	public int countByProductKey(String productKey);

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByPK_T(String productKey, String type)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByPK_T(String productKey, String type);

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByPK_T(
		String productKey, String type, boolean useFinderCache);

	/**
	 * Removes the license entry where productKey = &#63; and type = &#63; from the database.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the license entry that was removed
	 */
	public LicenseEntry removeByPK_T(String productKey, String type)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the number of license entries where productKey = &#63; and type = &#63;.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the number of matching license entries
	 */
	public int countByPK_T(String productKey, String type);

	/**
	 * Caches the license entry in the entity cache if it is enabled.
	 *
	 * @param licenseEntry the license entry
	 */
	public void cacheResult(LicenseEntry licenseEntry);

	/**
	 * Caches the license entries in the entity cache if it is enabled.
	 *
	 * @param licenseEntries the license entries
	 */
	public void cacheResult(java.util.List<LicenseEntry> licenseEntries);

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	public LicenseEntry create(long licenseEntryId);

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public LicenseEntry remove(long licenseEntryId)
		throws NoSuchLicenseEntryException;

	public LicenseEntry updateImpl(LicenseEntry licenseEntry);

	/**
	 * Returns the license entry with the primary key or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 */
	public LicenseEntry fetchByPrimaryKey(long licenseEntryId);

	/**
	 * Returns all the license entries.
	 *
	 * @return the license entries
	 */
	public java.util.List<LicenseEntry> findAll();

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	public java.util.List<LicenseEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license entries
	 */
	public java.util.List<LicenseEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license entries
	 */
	public java.util.List<LicenseEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the license entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	public int countAll();

}
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.license.exception.NoSuchLicenseKeySetException;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the license key set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetUtil
 * @generated
 */
@ProviderType
public interface LicenseKeySetPersistence
	extends BasePersistence<LicenseKeySet> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseKeySetUtil} to access the license key set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LicenseKeySet> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId);

	/**
	 * Returns a range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	 * Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	public LicenseKeySet findByAccountEntryId_First(
			long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	public LicenseKeySet fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	public LicenseKeySet findByAccountEntryId_Last(
			long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	public LicenseKeySet fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	public LicenseKeySet[] findByAccountEntryId_PrevAndNext(
			long licenseKeySetId, long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Removes all the license key sets where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	public void removeByAccountEntryId(long accountEntryId);

	/**
	 * Returns the number of license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license key sets
	 */
	public int countByAccountEntryId(long accountEntryId);

	/**
	 * Returns all the license key sets where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @return the matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name);

	/**
	 * Returns a range of all the license key sets where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name, int start, int end);

	/**
	 * Returns an ordered range of all the license key sets where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license key sets where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	public LicenseKeySet findByKA_N_First(
			String koroneikiAccountKey, String name,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Returns the first license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	public LicenseKeySet fetchByKA_N_First(
		String koroneikiAccountKey, String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns the last license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	public LicenseKeySet findByKA_N_Last(
			String koroneikiAccountKey, String name,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Returns the last license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	public LicenseKeySet fetchByKA_N_Last(
		String koroneikiAccountKey, String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	public LicenseKeySet[] findByKA_N_PrevAndNext(
			long licenseKeySetId, String koroneikiAccountKey, String name,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Removes all the license key sets where koroneikiAccountKey = &#63; and name = &#63; from the database.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 */
	public void removeByKA_N(String koroneikiAccountKey, String name);

	/**
	 * Returns the number of license key sets where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @return the number of matching license key sets
	 */
	public int countByKA_N(String koroneikiAccountKey, String name);

	/**
	 * Returns all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name);

	/**
	 * Returns a range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name, int start, int end);

	/**
	 * Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license key sets
	 */
	public java.util.List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	public LicenseKeySet findByU_AEI_N_First(
			long userId, long accountEntryId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	public LicenseKeySet fetchByU_AEI_N_First(
		long userId, long accountEntryId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	public LicenseKeySet findByU_AEI_N_Last(
			long userId, long accountEntryId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	public LicenseKeySet fetchByU_AEI_N_Last(
		long userId, long accountEntryId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	public LicenseKeySet[] findByU_AEI_N_PrevAndNext(
			long licenseKeySetId, long userId, long accountEntryId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
				orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	 * Removes all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 */
	public void removeByU_AEI_N(long userId, long accountEntryId, String name);

	/**
	 * Returns the number of license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the number of matching license key sets
	 */
	public int countByU_AEI_N(long userId, long accountEntryId, String name);

	/**
	 * Caches the license key set in the entity cache if it is enabled.
	 *
	 * @param licenseKeySet the license key set
	 */
	public void cacheResult(LicenseKeySet licenseKeySet);

	/**
	 * Caches the license key sets in the entity cache if it is enabled.
	 *
	 * @param licenseKeySets the license key sets
	 */
	public void cacheResult(java.util.List<LicenseKeySet> licenseKeySets);

	/**
	 * Creates a new license key set with the primary key. Does not add the license key set to the database.
	 *
	 * @param licenseKeySetId the primary key for the new license key set
	 * @return the new license key set
	 */
	public LicenseKeySet create(long licenseKeySetId);

	/**
	 * Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	public LicenseKeySet remove(long licenseKeySetId)
		throws NoSuchLicenseKeySetException;

	public LicenseKeySet updateImpl(LicenseKeySet licenseKeySet);

	/**
	 * Returns the license key set with the primary key or throws a <code>NoSuchLicenseKeySetException</code> if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	public LicenseKeySet findByPrimaryKey(long licenseKeySetId)
		throws NoSuchLicenseKeySetException;

	/**
	 * Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	 */
	public LicenseKeySet fetchByPrimaryKey(long licenseKeySetId);

	/**
	 * Returns all the license key sets.
	 *
	 * @return the license key sets
	 */
	public java.util.List<LicenseKeySet> findAll();

	/**
	 * Returns a range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of license key sets
	 */
	public java.util.List<LicenseKeySet> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license key sets
	 */
	public java.util.List<LicenseKeySet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license key sets
	 */
	public java.util.List<LicenseKeySet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the license key sets from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of license key sets.
	 *
	 * @return the number of license key sets
	 */
	public int countAll();

}
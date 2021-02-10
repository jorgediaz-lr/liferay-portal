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

package com.liferay.osb.customer.license.service.persistence.impl;

import com.liferay.osb.customer.license.exception.NoSuchLicenseKeySetException;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.model.impl.LicenseKeySetImpl;
import com.liferay.osb.customer.license.model.impl.LicenseKeySetModelImpl;
import com.liferay.osb.customer.license.service.persistence.LicenseKeySetPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the license key set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeySetPersistenceImpl
	extends BasePersistenceImpl<LicenseKeySet>
	implements LicenseKeySetPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LicenseKeySetUtil</code> to access the license key set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LicenseKeySetImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByAccountEntryId;
	private FinderPath _finderPathWithoutPaginationFindByAccountEntryId;
	private FinderPath _finderPathCountByAccountEntryId;

	/**
	 * Returns all the license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(
			accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end) {

		return findByAccountEntryId(accountEntryId, start, end, null);
	}

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
	@Override
	public List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		return findByAccountEntryId(
			accountEntryId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAccountEntryId;
				finderArgs = new Object[] {accountEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAccountEntryId;
			finderArgs = new Object[] {
				accountEntryId, start, end, orderByComparator
			};
		}

		List<LicenseKeySet> list = null;

		if (useFinderCache) {
			list = (List<LicenseKeySet>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKeySet licenseKeySet : list) {
					if (accountEntryId != licenseKeySet.getAccountEntryId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountEntryId);

				list = (List<LicenseKeySet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByAccountEntryId_First(
			long accountEntryId,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = fetchByAccountEntryId_First(
			accountEntryId, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountEntryId=");
		sb.append(accountEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeySetException(sb.toString());
	}

	/**
	 * Returns the first license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByAccountEntryId_First(
		long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		List<LicenseKeySet> list = findByAccountEntryId(
			accountEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByAccountEntryId_Last(
			long accountEntryId,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = fetchByAccountEntryId_Last(
			accountEntryId, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountEntryId=");
		sb.append(accountEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeySetException(sb.toString());
	}

	/**
	 * Returns the last license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByAccountEntryId_Last(
		long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKeySet> list = findByAccountEntryId(
			accountEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license key sets before and after the current license key set in the ordered set where accountEntryId = &#63;.
	 *
	 * @param licenseKeySetId the primary key of the current license key set
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet[] findByAccountEntryId_PrevAndNext(
			long licenseKeySetId, long accountEntryId,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = findByPrimaryKey(licenseKeySetId);

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet[] array = new LicenseKeySetImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(
				session, licenseKeySet, accountEntryId, orderByComparator,
				true);

			array[1] = licenseKeySet;

			array[2] = getByAccountEntryId_PrevAndNext(
				session, licenseKeySet, accountEntryId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKeySet getByAccountEntryId_PrevAndNext(
		Session session, LicenseKeySet licenseKeySet, long accountEntryId,
		OrderByComparator<LicenseKeySet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

		sb.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accountEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						licenseKeySet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKeySet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license key sets where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (LicenseKeySet licenseKeySet :
				findByAccountEntryId(
					accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license key sets
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = _finderPathCountByAccountEntryId;

		Object[] finderArgs = new Object[] {accountEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEYSET_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountEntryId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 =
		"licenseKeySet.accountEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByKA_N;
	private FinderPath _finderPathWithoutPaginationFindByKA_N;
	private FinderPath _finderPathCountByKA_N;

	/**
	 * Returns all the license key sets where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @return the matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name) {

		return findByKA_N(
			koroneikiAccountKey, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	@Override
	public List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name, int start, int end) {

		return findByKA_N(koroneikiAccountKey, name, start, end, null);
	}

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
	@Override
	public List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		return findByKA_N(
			koroneikiAccountKey, name, start, end, orderByComparator, true);
	}

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
	@Override
	public List<LicenseKeySet> findByKA_N(
		String koroneikiAccountKey, String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
		boolean useFinderCache) {

		koroneikiAccountKey = Objects.toString(koroneikiAccountKey, "");
		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByKA_N;
				finderArgs = new Object[] {koroneikiAccountKey, name};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByKA_N;
			finderArgs = new Object[] {
				koroneikiAccountKey, name, start, end, orderByComparator
			};
		}

		List<LicenseKeySet> list = null;

		if (useFinderCache) {
			list = (List<LicenseKeySet>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKeySet licenseKeySet : list) {
					if (!koroneikiAccountKey.equals(
							licenseKeySet.getKoroneikiAccountKey()) ||
						!name.equals(licenseKeySet.getName())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

			boolean bindKoroneikiAccountKey = false;

			if (koroneikiAccountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_3);
			}
			else {
				bindKoroneikiAccountKey = true;

				sb.append(_FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_2);
			}

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_KA_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_KA_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKoroneikiAccountKey) {
					queryPos.add(koroneikiAccountKey);
				}

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<LicenseKeySet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByKA_N_First(
			String koroneikiAccountKey, String name,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = fetchByKA_N_First(
			koroneikiAccountKey, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiAccountKey=");
		sb.append(koroneikiAccountKey);

		sb.append(", name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchLicenseKeySetException(sb.toString());
	}

	/**
	 * Returns the first license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByKA_N_First(
		String koroneikiAccountKey, String name,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		List<LicenseKeySet> list = findByKA_N(
			koroneikiAccountKey, name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set
	 * @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet findByKA_N_Last(
			String koroneikiAccountKey, String name,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = fetchByKA_N_Last(
			koroneikiAccountKey, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiAccountKey=");
		sb.append(koroneikiAccountKey);

		sb.append(", name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchLicenseKeySetException(sb.toString());
	}

	/**
	 * Returns the last license key set in the ordered set where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByKA_N_Last(
		String koroneikiAccountKey, String name,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		int count = countByKA_N(koroneikiAccountKey, name);

		if (count == 0) {
			return null;
		}

		List<LicenseKeySet> list = findByKA_N(
			koroneikiAccountKey, name, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public LicenseKeySet[] findByKA_N_PrevAndNext(
			long licenseKeySetId, String koroneikiAccountKey, String name,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		koroneikiAccountKey = Objects.toString(koroneikiAccountKey, "");
		name = Objects.toString(name, "");

		LicenseKeySet licenseKeySet = findByPrimaryKey(licenseKeySetId);

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet[] array = new LicenseKeySetImpl[3];

			array[0] = getByKA_N_PrevAndNext(
				session, licenseKeySet, koroneikiAccountKey, name,
				orderByComparator, true);

			array[1] = licenseKeySet;

			array[2] = getByKA_N_PrevAndNext(
				session, licenseKeySet, koroneikiAccountKey, name,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKeySet getByKA_N_PrevAndNext(
		Session session, LicenseKeySet licenseKeySet,
		String koroneikiAccountKey, String name,
		OrderByComparator<LicenseKeySet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

		boolean bindKoroneikiAccountKey = false;

		if (koroneikiAccountKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_3);
		}
		else {
			bindKoroneikiAccountKey = true;

			sb.append(_FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_2);
		}

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_KA_N_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_KA_N_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindKoroneikiAccountKey) {
			queryPos.add(koroneikiAccountKey);
		}

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						licenseKeySet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKeySet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license key sets where koroneikiAccountKey = &#63; and name = &#63; from the database.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 */
	@Override
	public void removeByKA_N(String koroneikiAccountKey, String name) {
		for (LicenseKeySet licenseKeySet :
				findByKA_N(
					koroneikiAccountKey, name, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets where koroneikiAccountKey = &#63; and name = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param name the name
	 * @return the number of matching license key sets
	 */
	@Override
	public int countByKA_N(String koroneikiAccountKey, String name) {
		koroneikiAccountKey = Objects.toString(koroneikiAccountKey, "");
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByKA_N;

		Object[] finderArgs = new Object[] {koroneikiAccountKey, name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEYSET_WHERE);

			boolean bindKoroneikiAccountKey = false;

			if (koroneikiAccountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_3);
			}
			else {
				bindKoroneikiAccountKey = true;

				sb.append(_FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_2);
			}

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_KA_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_KA_N_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKoroneikiAccountKey) {
					queryPos.add(koroneikiAccountKey);
				}

				if (bindName) {
					queryPos.add(name);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_2 =
		"licenseKeySet.koroneikiAccountKey = ? AND ";

	private static final String _FINDER_COLUMN_KA_N_KORONEIKIACCOUNTKEY_3 =
		"(licenseKeySet.koroneikiAccountKey IS NULL OR licenseKeySet.koroneikiAccountKey = '') AND ";

	private static final String _FINDER_COLUMN_KA_N_NAME_2 =
		"licenseKeySet.name = ?";

	private static final String _FINDER_COLUMN_KA_N_NAME_3 =
		"(licenseKeySet.name IS NULL OR licenseKeySet.name = '')";

	private FinderPath _finderPathWithPaginationFindByU_AEI_N;
	private FinderPath _finderPathWithoutPaginationFindByU_AEI_N;
	private FinderPath _finderPathCountByU_AEI_N;

	/**
	 * Returns all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the matching license key sets
	 */
	@Override
	public List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name) {

		return findByU_AEI_N(
			userId, accountEntryId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	@Override
	public List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name, int start, int end) {

		return findByU_AEI_N(userId, accountEntryId, name, start, end, null);
	}

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
	@Override
	public List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		return findByU_AEI_N(
			userId, accountEntryId, name, start, end, orderByComparator, true);
	}

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
	@Override
	public List<LicenseKeySet> findByU_AEI_N(
		long userId, long accountEntryId, String name, int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator,
		boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_AEI_N;
				finderArgs = new Object[] {userId, accountEntryId, name};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_AEI_N;
			finderArgs = new Object[] {
				userId, accountEntryId, name, start, end, orderByComparator
			};
		}

		List<LicenseKeySet> list = null;

		if (useFinderCache) {
			list = (List<LicenseKeySet>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKeySet licenseKeySet : list) {
					if ((userId != licenseKeySet.getUserId()) ||
						(accountEntryId != licenseKeySet.getAccountEntryId()) ||
						!name.equals(licenseKeySet.getName())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

			sb.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

			sb.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(accountEntryId);

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<LicenseKeySet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

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
	@Override
	public LicenseKeySet findByU_AEI_N_First(
			long userId, long accountEntryId, String name,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = fetchByU_AEI_N_First(
			userId, accountEntryId, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", accountEntryId=");
		sb.append(accountEntryId);

		sb.append(", name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchLicenseKeySetException(sb.toString());
	}

	/**
	 * Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByU_AEI_N_First(
		long userId, long accountEntryId, String name,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		List<LicenseKeySet> list = findByU_AEI_N(
			userId, accountEntryId, name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public LicenseKeySet findByU_AEI_N_Last(
			long userId, long accountEntryId, String name,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = fetchByU_AEI_N_Last(
			userId, accountEntryId, name, orderByComparator);

		if (licenseKeySet != null) {
			return licenseKeySet;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", accountEntryId=");
		sb.append(accountEntryId);

		sb.append(", name=");
		sb.append(name);

		sb.append("}");

		throw new NoSuchLicenseKeySetException(sb.toString());
	}

	/**
	 * Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	 */
	@Override
	public LicenseKeySet fetchByU_AEI_N_Last(
		long userId, long accountEntryId, String name,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		int count = countByU_AEI_N(userId, accountEntryId, name);

		if (count == 0) {
			return null;
		}

		List<LicenseKeySet> list = findByU_AEI_N(
			userId, accountEntryId, name, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public LicenseKeySet[] findByU_AEI_N_PrevAndNext(
			long licenseKeySetId, long userId, long accountEntryId, String name,
			OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException {

		name = Objects.toString(name, "");

		LicenseKeySet licenseKeySet = findByPrimaryKey(licenseKeySetId);

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet[] array = new LicenseKeySetImpl[3];

			array[0] = getByU_AEI_N_PrevAndNext(
				session, licenseKeySet, userId, accountEntryId, name,
				orderByComparator, true);

			array[1] = licenseKeySet;

			array[2] = getByU_AEI_N_PrevAndNext(
				session, licenseKeySet, userId, accountEntryId, name,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKeySet getByU_AEI_N_PrevAndNext(
		Session session, LicenseKeySet licenseKeySet, long userId,
		long accountEntryId, String name,
		OrderByComparator<LicenseKeySet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LICENSEKEYSET_WHERE);

		sb.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

		sb.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(LicenseKeySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(accountEntryId);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						licenseKeySet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKeySet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 */
	@Override
	public void removeByU_AEI_N(long userId, long accountEntryId, String name) {
		for (LicenseKeySet licenseKeySet :
				findByU_AEI_N(
					userId, accountEntryId, name, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param name the name
	 * @return the number of matching license key sets
	 */
	@Override
	public int countByU_AEI_N(long userId, long accountEntryId, String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByU_AEI_N;

		Object[] finderArgs = new Object[] {userId, accountEntryId, name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEYSET_WHERE);

			sb.append(_FINDER_COLUMN_U_AEI_N_USERID_2);

			sb.append(_FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_AEI_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_U_AEI_N_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(accountEntryId);

				if (bindName) {
					queryPos.add(name);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_AEI_N_USERID_2 =
		"licenseKeySet.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_AEI_N_ACCOUNTENTRYID_2 =
		"licenseKeySet.accountEntryId = ? AND ";

	private static final String _FINDER_COLUMN_U_AEI_N_NAME_2 =
		"licenseKeySet.name = ?";

	private static final String _FINDER_COLUMN_U_AEI_N_NAME_3 =
		"(licenseKeySet.name IS NULL OR licenseKeySet.name = '')";

	public LicenseKeySetPersistenceImpl() {
		setModelClass(LicenseKeySet.class);
	}

	/**
	 * Caches the license key set in the entity cache if it is enabled.
	 *
	 * @param licenseKeySet the license key set
	 */
	@Override
	public void cacheResult(LicenseKeySet licenseKeySet) {
		entityCache.putResult(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey(),
			licenseKeySet);

		licenseKeySet.resetOriginalValues();
	}

	/**
	 * Caches the license key sets in the entity cache if it is enabled.
	 *
	 * @param licenseKeySets the license key sets
	 */
	@Override
	public void cacheResult(List<LicenseKeySet> licenseKeySets) {
		for (LicenseKeySet licenseKeySet : licenseKeySets) {
			if (entityCache.getResult(
					LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey()) ==
						null) {

				cacheResult(licenseKeySet);
			}
			else {
				licenseKeySet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license key sets.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LicenseKeySetImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license key set.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseKeySet licenseKeySet) {
		entityCache.removeResult(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LicenseKeySet> licenseKeySets) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseKeySet licenseKeySet : licenseKeySets) {
			entityCache.removeResult(
				LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeySetImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new license key set with the primary key. Does not add the license key set to the database.
	 *
	 * @param licenseKeySetId the primary key for the new license key set
	 * @return the new license key set
	 */
	@Override
	public LicenseKeySet create(long licenseKeySetId) {
		LicenseKeySet licenseKeySet = new LicenseKeySetImpl();

		licenseKeySet.setNew(true);
		licenseKeySet.setPrimaryKey(licenseKeySetId);

		return licenseKeySet;
	}

	/**
	 * Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet remove(long licenseKeySetId)
		throws NoSuchLicenseKeySetException {

		return remove((Serializable)licenseKeySetId);
	}

	/**
	 * Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet remove(Serializable primaryKey)
		throws NoSuchLicenseKeySetException {

		Session session = null;

		try {
			session = openSession();

			LicenseKeySet licenseKeySet = (LicenseKeySet)session.get(
				LicenseKeySetImpl.class, primaryKey);

			if (licenseKeySet == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseKeySetException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(licenseKeySet);
		}
		catch (NoSuchLicenseKeySetException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected LicenseKeySet removeImpl(LicenseKeySet licenseKeySet) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(licenseKeySet)) {
				licenseKeySet = (LicenseKeySet)session.get(
					LicenseKeySetImpl.class, licenseKeySet.getPrimaryKeyObj());
			}

			if (licenseKeySet != null) {
				session.delete(licenseKeySet);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (licenseKeySet != null) {
			clearCache(licenseKeySet);
		}

		return licenseKeySet;
	}

	@Override
	public LicenseKeySet updateImpl(LicenseKeySet licenseKeySet) {
		boolean isNew = licenseKeySet.isNew();

		if (!(licenseKeySet instanceof LicenseKeySetModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(licenseKeySet.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					licenseKeySet);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in licenseKeySet proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LicenseKeySet implementation " +
					licenseKeySet.getClass());
		}

		LicenseKeySetModelImpl licenseKeySetModelImpl =
			(LicenseKeySetModelImpl)licenseKeySet;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (licenseKeySet.getCreateDate() == null)) {
			if (serviceContext == null) {
				licenseKeySet.setCreateDate(now);
			}
			else {
				licenseKeySet.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!licenseKeySetModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				licenseKeySet.setModifiedDate(now);
			}
			else {
				licenseKeySet.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(licenseKeySet);

				licenseKeySet.setNew(false);
			}
			else {
				licenseKeySet = (LicenseKeySet)session.merge(licenseKeySet);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LicenseKeySetModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				licenseKeySetModelImpl.getAccountEntryId()
			};

			finderCache.removeResult(_finderPathCountByAccountEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountEntryId, args);

			args = new Object[] {
				licenseKeySetModelImpl.getKoroneikiAccountKey(),
				licenseKeySetModelImpl.getName()
			};

			finderCache.removeResult(_finderPathCountByKA_N, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByKA_N, args);

			args = new Object[] {
				licenseKeySetModelImpl.getUserId(),
				licenseKeySetModelImpl.getAccountEntryId(),
				licenseKeySetModelImpl.getName()
			};

			finderCache.removeResult(_finderPathCountByU_AEI_N, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_AEI_N, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((licenseKeySetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeySetModelImpl.getOriginalAccountEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByAccountEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountEntryId, args);

				args = new Object[] {
					licenseKeySetModelImpl.getAccountEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByAccountEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountEntryId, args);
			}

			if ((licenseKeySetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByKA_N.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeySetModelImpl.getOriginalKoroneikiAccountKey(),
					licenseKeySetModelImpl.getOriginalName()
				};

				finderCache.removeResult(_finderPathCountByKA_N, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKA_N, args);

				args = new Object[] {
					licenseKeySetModelImpl.getKoroneikiAccountKey(),
					licenseKeySetModelImpl.getName()
				};

				finderCache.removeResult(_finderPathCountByKA_N, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKA_N, args);
			}

			if ((licenseKeySetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_AEI_N.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeySetModelImpl.getOriginalUserId(),
					licenseKeySetModelImpl.getOriginalAccountEntryId(),
					licenseKeySetModelImpl.getOriginalName()
				};

				finderCache.removeResult(_finderPathCountByU_AEI_N, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AEI_N, args);

				args = new Object[] {
					licenseKeySetModelImpl.getUserId(),
					licenseKeySetModelImpl.getAccountEntryId(),
					licenseKeySetModelImpl.getName()
				};

				finderCache.removeResult(_finderPathCountByU_AEI_N, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AEI_N, args);
			}
		}

		entityCache.putResult(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, licenseKeySet.getPrimaryKey(),
			licenseKeySet, false);

		licenseKeySet.resetOriginalValues();

		return licenseKeySet;
	}

	/**
	 * Returns the license key set with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLicenseKeySetException {

		LicenseKeySet licenseKeySet = fetchByPrimaryKey(primaryKey);

		if (licenseKeySet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLicenseKeySetException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return licenseKeySet;
	}

	/**
	 * Returns the license key set with the primary key or throws a <code>NoSuchLicenseKeySetException</code> if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set
	 * @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet findByPrimaryKey(long licenseKeySetId)
		throws NoSuchLicenseKeySetException {

		return findByPrimaryKey((Serializable)licenseKeySetId);
	}

	/**
	 * Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key set
	 * @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LicenseKeySet licenseKeySet = (LicenseKeySet)serializable;

		if (licenseKeySet == null) {
			Session session = null;

			try {
				session = openSession();

				licenseKeySet = (LicenseKeySet)session.get(
					LicenseKeySetImpl.class, primaryKey);

				if (licenseKeySet != null) {
					cacheResult(licenseKeySet);
				}
				else {
					entityCache.putResult(
						LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeySetImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeySetImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return licenseKeySet;
	}

	/**
	 * Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	 */
	@Override
	public LicenseKeySet fetchByPrimaryKey(long licenseKeySetId) {
		return fetchByPrimaryKey((Serializable)licenseKeySetId);
	}

	@Override
	public Map<Serializable, LicenseKeySet> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LicenseKeySet> map =
			new HashMap<Serializable, LicenseKeySet>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LicenseKeySet licenseKeySet = fetchByPrimaryKey(primaryKey);

			if (licenseKeySet != null) {
				map.put(primaryKey, licenseKeySet);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeySetImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LicenseKeySet)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			(uncachedPrimaryKeys.size() * 2) + 1);

		sb.append(_SQL_SELECT_LICENSEKEYSET_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (LicenseKeySet licenseKeySet :
					(List<LicenseKeySet>)query.list()) {

				map.put(licenseKeySet.getPrimaryKeyObj(), licenseKeySet);

				cacheResult(licenseKeySet);

				uncachedPrimaryKeys.remove(licenseKeySet.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeySetImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the license key sets.
	 *
	 * @return the license key sets
	 */
	@Override
	public List<LicenseKeySet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<LicenseKeySet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<LicenseKeySet> findAll(
		int start, int end,
		OrderByComparator<LicenseKeySet> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<LicenseKeySet> findAll(
		int start, int end, OrderByComparator<LicenseKeySet> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<LicenseKeySet> list = null;

		if (useFinderCache) {
			list = (List<LicenseKeySet>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LICENSEKEYSET);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEKEYSET;

				sql = sql.concat(LicenseKeySetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LicenseKeySet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the license key sets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LicenseKeySet licenseKeySet : findAll()) {
			remove(licenseKeySet);
		}
	}

	/**
	 * Returns the number of license key sets.
	 *
	 * @return the number of license key sets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LICENSEKEYSET);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LicenseKeySetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license key set persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);

		_finderPathCountAll = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByAccountEntryId = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountEntryId = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountEntryId", new String[] {Long.class.getName()},
			LicenseKeySetModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByAccountEntryId = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByKA_N = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKA_N",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByKA_N = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKA_N",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseKeySetModelImpl.KORONEIKIACCOUNTKEY_COLUMN_BITMASK |
			LicenseKeySetModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByKA_N = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKA_N",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByU_AEI_N = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_AEI_N = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED,
			LicenseKeySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			LicenseKeySetModelImpl.USERID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeySetModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByU_AEI_N = new FinderPath(
			LicenseKeySetModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(LicenseKeySetImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_LICENSEKEYSET =
		"SELECT licenseKeySet FROM LicenseKeySet licenseKeySet";

	private static final String _SQL_SELECT_LICENSEKEYSET_WHERE_PKS_IN =
		"SELECT licenseKeySet FROM LicenseKeySet licenseKeySet WHERE licenseKeySetId IN (";

	private static final String _SQL_SELECT_LICENSEKEYSET_WHERE =
		"SELECT licenseKeySet FROM LicenseKeySet licenseKeySet WHERE ";

	private static final String _SQL_COUNT_LICENSEKEYSET =
		"SELECT COUNT(licenseKeySet) FROM LicenseKeySet licenseKeySet";

	private static final String _SQL_COUNT_LICENSEKEYSET_WHERE =
		"SELECT COUNT(licenseKeySet) FROM LicenseKeySet licenseKeySet WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseKeySet.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LicenseKeySet exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LicenseKeySet exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeySetPersistenceImpl.class);

}
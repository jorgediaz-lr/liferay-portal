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

import com.liferay.osb.customer.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.model.impl.LicenseKeyImpl;
import com.liferay.osb.customer.license.model.impl.LicenseKeyModelImpl;
import com.liferay.osb.customer.license.service.persistence.LicenseKeyPersistence;
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Arrays;
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
 * The persistence implementation for the license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeyPersistenceImpl
	extends BasePersistenceImpl<LicenseKey> implements LicenseKeyPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LicenseKeyUtil</code> to access the license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LicenseKeyImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!uuid.equals(licenseKey.getUuid())) {
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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByUuid_First(
			String uuid, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByUuid_First(uuid, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByUuid_First(
		String uuid, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByUuid_Last(
			String uuid, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByUuid_Last(uuid, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByUuid_Last(
		String uuid, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByUuid_PrevAndNext(
			long licenseKeyId, String uuid,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		uuid = Objects.toString(uuid, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, licenseKey, uuid, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByUuid_PrevAndNext(
				session, licenseKey, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByUuid_PrevAndNext(
		Session session, LicenseKey licenseKey, String uuid,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LicenseKey licenseKey :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching license keies
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"licenseKey.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(licenseKey.uuid IS NULL OR licenseKey.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByLicenseKeySetId;
	private FinderPath _finderPathWithoutPaginationFindByLicenseKeySetId;
	private FinderPath _finderPathCountByLicenseKeySetId;

	/**
	 * Returns all the license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId) {
		return findByLicenseKeySetId(
			licenseKeySetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end) {

		return findByLicenseKeySetId(licenseKeySetId, start, end, null);
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
	@Override
	public List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByLicenseKeySetId(
			licenseKeySetId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByLicenseKeySetId;
				finderArgs = new Object[] {licenseKeySetId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByLicenseKeySetId;
			finderArgs = new Object[] {
				licenseKeySetId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (licenseKeySetId != licenseKey.getLicenseKeySetId()) {
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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(licenseKeySetId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByLicenseKeySetId_First(
			long licenseKeySetId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByLicenseKeySetId_First(
			licenseKeySetId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("licenseKeySetId=");
		sb.append(licenseKeySetId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByLicenseKeySetId_First(
		long licenseKeySetId, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByLicenseKeySetId(
			licenseKeySetId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByLicenseKeySetId_Last(
			long licenseKeySetId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByLicenseKeySetId_Last(
			licenseKeySetId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("licenseKeySetId=");
		sb.append(licenseKeySetId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByLicenseKeySetId_Last(
		long licenseKeySetId, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByLicenseKeySetId(licenseKeySetId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByLicenseKeySetId(
			licenseKeySetId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByLicenseKeySetId_PrevAndNext(
			long licenseKeyId, long licenseKeySetId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByLicenseKeySetId_PrevAndNext(
				session, licenseKey, licenseKeySetId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByLicenseKeySetId_PrevAndNext(
				session, licenseKey, licenseKeySetId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByLicenseKeySetId_PrevAndNext(
		Session session, LicenseKey licenseKey, long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(licenseKeySetId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where licenseKeySetId = &#63; from the database.
	 *
	 * @param licenseKeySetId the license key set ID
	 */
	@Override
	public void removeByLicenseKeySetId(long licenseKeySetId) {
		for (LicenseKey licenseKey :
				findByLicenseKeySetId(
					licenseKeySetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where licenseKeySetId = &#63;.
	 *
	 * @param licenseKeySetId the license key set ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByLicenseKeySetId(long licenseKeySetId) {
		FinderPath finderPath = _finderPathCountByLicenseKeySetId;

		Object[] finderArgs = new Object[] {licenseKeySetId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(licenseKeySetId);

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

	private static final String
		_FINDER_COLUMN_LICENSEKEYSETID_LICENSEKEYSETID_2 =
			"licenseKey.licenseKeySetId = ?";

	private FinderPath
		_finderPathWithPaginationFindByKoroneikiProductPurchaseKey;
	private FinderPath
		_finderPathWithoutPaginationFindByKoroneikiProductPurchaseKey;
	private FinderPath _finderPathCountByKoroneikiProductPurchaseKey;

	/**
	 * Returns all the license keies where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		return findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey, int start, int end) {

		return findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, start, end, null);
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
	@Override
	public List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		koroneikiProductPurchaseKey = Objects.toString(
			koroneikiProductPurchaseKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByKoroneikiProductPurchaseKey;
				finderArgs = new Object[] {koroneikiProductPurchaseKey};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByKoroneikiProductPurchaseKey;
			finderArgs = new Object[] {
				koroneikiProductPurchaseKey, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!koroneikiProductPurchaseKey.equals(
							licenseKey.getKoroneikiProductPurchaseKey())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindKoroneikiProductPurchaseKey = false;

			if (koroneikiProductPurchaseKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_3);
			}
			else {
				bindKoroneikiProductPurchaseKey = true;

				sb.append(
					_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKoroneikiProductPurchaseKey) {
					queryPos.add(koroneikiProductPurchaseKey);
				}

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByKoroneikiProductPurchaseKey_First(
			String koroneikiProductPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByKoroneikiProductPurchaseKey_First(
			koroneikiProductPurchaseKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiProductPurchaseKey=");
		sb.append(koroneikiProductPurchaseKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByKoroneikiProductPurchaseKey_First(
		String koroneikiProductPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByKoroneikiProductPurchaseKey_Last(
			String koroneikiProductPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByKoroneikiProductPurchaseKey_Last(
			koroneikiProductPurchaseKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiProductPurchaseKey=");
		sb.append(koroneikiProductPurchaseKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByKoroneikiProductPurchaseKey_Last(
		String koroneikiProductPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByKoroneikiProductPurchaseKey(
			koroneikiProductPurchaseKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByKoroneikiProductPurchaseKey_PrevAndNext(
			long licenseKeyId, String koroneikiProductPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		koroneikiProductPurchaseKey = Objects.toString(
			koroneikiProductPurchaseKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByKoroneikiProductPurchaseKey_PrevAndNext(
				session, licenseKey, koroneikiProductPurchaseKey,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByKoroneikiProductPurchaseKey_PrevAndNext(
				session, licenseKey, koroneikiProductPurchaseKey,
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

	protected LicenseKey getByKoroneikiProductPurchaseKey_PrevAndNext(
		Session session, LicenseKey licenseKey,
		String koroneikiProductPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindKoroneikiProductPurchaseKey = false;

		if (koroneikiProductPurchaseKey.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_3);
		}
		else {
			bindKoroneikiProductPurchaseKey = true;

			sb.append(
				_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_2);
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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindKoroneikiProductPurchaseKey) {
			queryPos.add(koroneikiProductPurchaseKey);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where koroneikiProductPurchaseKey = &#63; from the database.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 */
	@Override
	public void removeByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		for (LicenseKey licenseKey :
				findByKoroneikiProductPurchaseKey(
					koroneikiProductPurchaseKey, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where koroneikiProductPurchaseKey = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @return the number of matching license keies
	 */
	@Override
	public int countByKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		koroneikiProductPurchaseKey = Objects.toString(
			koroneikiProductPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByKoroneikiProductPurchaseKey;

		Object[] finderArgs = new Object[] {koroneikiProductPurchaseKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindKoroneikiProductPurchaseKey = false;

			if (koroneikiProductPurchaseKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_3);
			}
			else {
				bindKoroneikiProductPurchaseKey = true;

				sb.append(
					_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKoroneikiProductPurchaseKey) {
					queryPos.add(koroneikiProductPurchaseKey);
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

	private static final String
		_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_2 =
			"licenseKey.koroneikiProductPurchaseKey = ?";

	private static final String
		_FINDER_COLUMN_KORONEIKIPRODUCTPURCHASEKEY_KORONEIKIPRODUCTPURCHASEKEY_3 =
			"(licenseKey.koroneikiProductPurchaseKey IS NULL OR licenseKey.koroneikiProductPurchaseKey = '')";

	private FinderPath _finderPathWithPaginationFindByAccountEntryId;
	private FinderPath _finderPathWithoutPaginationFindByAccountEntryId;
	private FinderPath _finderPathCountByAccountEntryId;

	/**
	 * Returns all the license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByAccountEntryId(long accountEntryId) {
		return findByAccountEntryId(
			accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end) {

		return findByAccountEntryId(accountEntryId, start, end, null);
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
	@Override
	public List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByAccountEntryId(
			accountEntryId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (accountEntryId != licenseKey.getAccountEntryId()) {
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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountEntryId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByAccountEntryId_First(
			long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByAccountEntryId_First(
			accountEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountEntryId=");
		sb.append(accountEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAccountEntryId_First(
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByAccountEntryId(
			accountEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByAccountEntryId_Last(
			long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByAccountEntryId_Last(
			accountEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountEntryId=");
		sb.append(accountEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAccountEntryId_Last(
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByAccountEntryId(accountEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByAccountEntryId(
			accountEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByAccountEntryId_PrevAndNext(
			long licenseKeyId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByAccountEntryId_PrevAndNext(
				session, licenseKey, accountEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByAccountEntryId_PrevAndNext(
				session, licenseKey, accountEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByAccountEntryId_PrevAndNext(
		Session session, LicenseKey licenseKey, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accountEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByAccountEntryId(long accountEntryId) {
		for (LicenseKey licenseKey :
				findByAccountEntryId(
					accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = _finderPathCountByAccountEntryId;

		Object[] finderArgs = new Object[] {accountEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

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
		"licenseKey.accountEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByOfferingEntryId;
	private FinderPath _finderPathWithoutPaginationFindByOfferingEntryId;
	private FinderPath _finderPathCountByOfferingEntryId;

	/**
	 * Returns all the license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOfferingEntryId(long offeringEntryId) {
		return findByOfferingEntryId(
			offeringEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end) {

		return findByOfferingEntryId(offeringEntryId, start, end, null);
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
	@Override
	public List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByOfferingEntryId(
			offeringEntryId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOfferingEntryId;
				finderArgs = new Object[] {offeringEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOfferingEntryId;
			finderArgs = new Object[] {
				offeringEntryId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (offeringEntryId != licenseKey.getOfferingEntryId()) {
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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOfferingEntryId_First(
			long offeringEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOfferingEntryId_First(
			offeringEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOfferingEntryId_First(
		long offeringEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByOfferingEntryId(
			offeringEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOfferingEntryId_Last(
			long offeringEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOfferingEntryId_Last(
			offeringEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOfferingEntryId_Last(
		long offeringEntryId, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByOfferingEntryId(offeringEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOfferingEntryId(
			offeringEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByOfferingEntryId_PrevAndNext(
			long licenseKeyId, long offeringEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOfferingEntryId_PrevAndNext(
				session, licenseKey, offeringEntryId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOfferingEntryId_PrevAndNext(
				session, licenseKey, offeringEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOfferingEntryId_PrevAndNext(
		Session session, LicenseKey licenseKey, long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(offeringEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 */
	@Override
	public void removeByOfferingEntryId(long offeringEntryId) {
		for (LicenseKey licenseKey :
				findByOfferingEntryId(
					offeringEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOfferingEntryId(long offeringEntryId) {
		FinderPath finderPath = _finderPathCountByOfferingEntryId;

		Object[] finderArgs = new Object[] {offeringEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

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

	private static final String
		_FINDER_COLUMN_OFFERINGENTRYID_OFFERINGENTRYID_2 =
			"licenseKey.offeringEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByU_AEI;
	private FinderPath _finderPathWithoutPaginationFindByU_AEI;
	private FinderPath _finderPathCountByU_AEI;

	/**
	 * Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_AEI(long userId, long accountEntryId) {
		return findByU_AEI(
			userId, accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end) {

		return findByU_AEI(userId, accountEntryId, start, end, null);
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
	@Override
	public List<LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByU_AEI(
			userId, accountEntryId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByU_AEI(
		long userId, long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_AEI;
				finderArgs = new Object[] {userId, accountEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_AEI;
			finderArgs = new Object[] {
				userId, accountEntryId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((userId != licenseKey.getUserId()) ||
						(accountEntryId != licenseKey.getAccountEntryId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_U_AEI_USERID_2);

			sb.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(accountEntryId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_AEI_First(
			long userId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_AEI_First(
			userId, accountEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", accountEntryId=");
		sb.append(accountEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_AEI_First(
		long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByU_AEI(
			userId, accountEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByU_AEI_Last(
			long userId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_AEI_Last(
			userId, accountEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", accountEntryId=");
		sb.append(accountEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_AEI_Last(
		long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByU_AEI(userId, accountEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByU_AEI(
			userId, accountEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByU_AEI_PrevAndNext(
			long licenseKeyId, long userId, long accountEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_AEI_PrevAndNext(
				session, licenseKey, userId, accountEntryId, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByU_AEI_PrevAndNext(
				session, licenseKey, userId, accountEntryId, orderByComparator,
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

	protected LicenseKey getByU_AEI_PrevAndNext(
		Session session, LicenseKey licenseKey, long userId,
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_U_AEI_USERID_2);

		sb.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(accountEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 */
	@Override
	public void removeByU_AEI(long userId, long accountEntryId) {
		for (LicenseKey licenseKey :
				findByU_AEI(
					userId, accountEntryId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountEntryId the account entry ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByU_AEI(long userId, long accountEntryId) {
		FinderPath finderPath = _finderPathCountByU_AEI;

		Object[] finderArgs = new Object[] {userId, accountEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_U_AEI_USERID_2);

			sb.append(_FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	private static final String _FINDER_COLUMN_U_AEI_USERID_2 =
		"licenseKey.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_AEI_ACCOUNTENTRYID_2 =
		"licenseKey.accountEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByU_PI;
	private FinderPath _finderPathWithoutPaginationFindByU_PI;
	private FinderPath _finderPathCountByU_PI;

	/**
	 * Returns all the license keies where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_PI(long userId, String productId) {
		return findByU_PI(
			userId, productId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByU_PI(
		long userId, String productId, int start, int end) {

		return findByU_PI(userId, productId, start, end, null);
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
	@Override
	public List<LicenseKey> findByU_PI(
		long userId, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByU_PI(
			userId, productId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByU_PI(
		long userId, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productId = Objects.toString(productId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_PI;
				finderArgs = new Object[] {userId, productId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_PI;
			finderArgs = new Object[] {
				userId, productId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((userId != licenseKey.getUserId()) ||
						!productId.equals(licenseKey.getProductId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_U_PI_USERID_2);

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_PI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_U_PI_PRODUCTID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				if (bindProductId) {
					queryPos.add(productId);
				}

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_PI_First(
			long userId, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_PI_First(
			userId, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", productId=");
		sb.append(productId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_PI_First(
		long userId, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByU_PI(
			userId, productId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByU_PI_Last(
			long userId, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_PI_Last(
			userId, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", productId=");
		sb.append(productId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_PI_Last(
		long userId, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByU_PI(userId, productId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByU_PI(
			userId, productId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByU_PI_PrevAndNext(
			long licenseKeyId, long userId, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productId = Objects.toString(productId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_PI_PrevAndNext(
				session, licenseKey, userId, productId, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByU_PI_PrevAndNext(
				session, licenseKey, userId, productId, orderByComparator,
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

	protected LicenseKey getByU_PI_PrevAndNext(
		Session session, LicenseKey licenseKey, long userId, String productId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_U_PI_USERID_2);

		boolean bindProductId = false;

		if (productId.isEmpty()) {
			sb.append(_FINDER_COLUMN_U_PI_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			sb.append(_FINDER_COLUMN_U_PI_PRODUCTID_2);
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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (bindProductId) {
			queryPos.add(productId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where userId = &#63; and productId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 */
	@Override
	public void removeByU_PI(long userId, String productId) {
		for (LicenseKey licenseKey :
				findByU_PI(
					userId, productId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where userId = &#63; and productId = &#63;.
	 *
	 * @param userId the user ID
	 * @param productId the product ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByU_PI(long userId, String productId) {
		productId = Objects.toString(productId, "");

		FinderPath finderPath = _finderPathCountByU_PI;

		Object[] finderArgs = new Object[] {userId, productId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_U_PI_USERID_2);

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_PI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_U_PI_PRODUCTID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				if (bindProductId) {
					queryPos.add(productId);
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

	private static final String _FINDER_COLUMN_U_PI_USERID_2 =
		"licenseKey.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_PI_PRODUCTID_2 =
		"licenseKey.productId = ?";

	private static final String _FINDER_COLUMN_U_PI_PRODUCTID_3 =
		"(licenseKey.productId IS NULL OR licenseKey.productId = '')";

	private FinderPath _finderPathWithPaginationFindByARLU_A;
	private FinderPath _finderPathWithoutPaginationFindByARLU_A;
	private FinderPath _finderPathCountByARLU_A;

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active) {

		return findByARLU_A(
			assetReceiptLicenseUuid, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end) {

		return findByARLU_A(assetReceiptLicenseUuid, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByARLU_A(
			assetReceiptLicenseUuid, active, start, end, orderByComparator,
			true);
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
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByARLU_A;
				finderArgs = new Object[] {assetReceiptLicenseUuid, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByARLU_A;
			finderArgs = new Object[] {
				assetReceiptLicenseUuid, active, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!assetReceiptLicenseUuid.equals(
							licenseKey.getAssetReceiptLicenseUuid()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_A_First(
			String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_A_First(
			assetReceiptLicenseUuid, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_A_First(
		String assetReceiptLicenseUuid, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByARLU_A(
			assetReceiptLicenseUuid, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByARLU_A_Last(
			String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_A_Last(
			assetReceiptLicenseUuid, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_A_Last(
		String assetReceiptLicenseUuid, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByARLU_A(assetReceiptLicenseUuid, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLU_A(
			assetReceiptLicenseUuid, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByARLU_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLU_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLU_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, active,
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

	protected LicenseKey getByARLU_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String assetReceiptLicenseUuid,
		boolean active, OrderByComparator<LicenseKey> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindAssetReceiptLicenseUuid = false;

		if (assetReceiptLicenseUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3);
		}
		else {
			bindAssetReceiptLicenseUuid = true;

			sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2);
		}

		sb.append(_FINDER_COLUMN_ARLU_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindAssetReceiptLicenseUuid) {
			queryPos.add(assetReceiptLicenseUuid);
		}

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 */
	@Override
	public void removeByARLU_A(String assetReceiptLicenseUuid, boolean active) {
		for (LicenseKey licenseKey :
				findByARLU_A(
					assetReceiptLicenseUuid, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLU_A(String assetReceiptLicenseUuid, boolean active) {
		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = _finderPathCountByARLU_A;

		Object[] finderArgs = new Object[] {assetReceiptLicenseUuid, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(active);

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

	private static final String
		_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2 =
			"licenseKey.assetReceiptLicenseUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3 =
			"(licenseKey.assetReceiptLicenseUuid IS NULL OR licenseKey.assetReceiptLicenseUuid = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByKA_PEI;
	private FinderPath _finderPathWithoutPaginationFindByKA_PEI;
	private FinderPath _finderPathCountByKA_PEI;

	/**
	 * Returns all the license keies where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId) {

		return findByKA_PEI(
			koroneikiAccountKey, productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId, int start, int end) {

		return findByKA_PEI(
			koroneikiAccountKey, productEntryId, start, end, null);
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
	@Override
	public List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByKA_PEI(
			koroneikiAccountKey, productEntryId, start, end, orderByComparator,
			true);
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
	@Override
	public List<LicenseKey> findByKA_PEI(
		String koroneikiAccountKey, long productEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		koroneikiAccountKey = Objects.toString(koroneikiAccountKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByKA_PEI;
				finderArgs = new Object[] {koroneikiAccountKey, productEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByKA_PEI;
			finderArgs = new Object[] {
				koroneikiAccountKey, productEntryId, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!koroneikiAccountKey.equals(
							licenseKey.getKoroneikiAccountKey()) ||
						(productEntryId != licenseKey.getProductEntryId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindKoroneikiAccountKey = false;

			if (koroneikiAccountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_3);
			}
			else {
				bindKoroneikiAccountKey = true;

				sb.append(_FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_2);
			}

			sb.append(_FINDER_COLUMN_KA_PEI_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
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

				queryPos.add(productEntryId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByKA_PEI_First(
			String koroneikiAccountKey, long productEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByKA_PEI_First(
			koroneikiAccountKey, productEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiAccountKey=");
		sb.append(koroneikiAccountKey);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByKA_PEI_First(
		String koroneikiAccountKey, long productEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByKA_PEI(
			koroneikiAccountKey, productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByKA_PEI_Last(
			String koroneikiAccountKey, long productEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByKA_PEI_Last(
			koroneikiAccountKey, productEntryId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiAccountKey=");
		sb.append(koroneikiAccountKey);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByKA_PEI_Last(
		String koroneikiAccountKey, long productEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByKA_PEI(koroneikiAccountKey, productEntryId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByKA_PEI(
			koroneikiAccountKey, productEntryId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByKA_PEI_PrevAndNext(
			long licenseKeyId, String koroneikiAccountKey, long productEntryId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		koroneikiAccountKey = Objects.toString(koroneikiAccountKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByKA_PEI_PrevAndNext(
				session, licenseKey, koroneikiAccountKey, productEntryId,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByKA_PEI_PrevAndNext(
				session, licenseKey, koroneikiAccountKey, productEntryId,
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

	protected LicenseKey getByKA_PEI_PrevAndNext(
		Session session, LicenseKey licenseKey, String koroneikiAccountKey,
		long productEntryId, OrderByComparator<LicenseKey> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindKoroneikiAccountKey = false;

		if (koroneikiAccountKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_3);
		}
		else {
			bindKoroneikiAccountKey = true;

			sb.append(_FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_2);
		}

		sb.append(_FINDER_COLUMN_KA_PEI_PRODUCTENTRYID_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindKoroneikiAccountKey) {
			queryPos.add(koroneikiAccountKey);
		}

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where koroneikiAccountKey = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByKA_PEI(
		String koroneikiAccountKey, long productEntryId) {

		for (LicenseKey licenseKey :
				findByKA_PEI(
					koroneikiAccountKey, productEntryId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where koroneikiAccountKey = &#63; and productEntryId = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param productEntryId the product entry ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByKA_PEI(String koroneikiAccountKey, long productEntryId) {
		koroneikiAccountKey = Objects.toString(koroneikiAccountKey, "");

		FinderPath finderPath = _finderPathCountByKA_PEI;

		Object[] finderArgs = new Object[] {
			koroneikiAccountKey, productEntryId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindKoroneikiAccountKey = false;

			if (koroneikiAccountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_3);
			}
			else {
				bindKoroneikiAccountKey = true;

				sb.append(_FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_2);
			}

			sb.append(_FINDER_COLUMN_KA_PEI_PRODUCTENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKoroneikiAccountKey) {
					queryPos.add(koroneikiAccountKey);
				}

				queryPos.add(productEntryId);

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

	private static final String _FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_2 =
		"licenseKey.koroneikiAccountKey = ? AND ";

	private static final String _FINDER_COLUMN_KA_PEI_KORONEIKIACCOUNTKEY_3 =
		"(licenseKey.koroneikiAccountKey IS NULL OR licenseKey.koroneikiAccountKey = '') AND ";

	private static final String _FINDER_COLUMN_KA_PEI_PRODUCTENTRYID_2 =
		"licenseKey.productEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByKPP_CI;
	private FinderPath _finderPathWithoutPaginationFindByKPP_CI;
	private FinderPath _finderPathCountByKPP_CI;

	/**
	 * Returns all the license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId) {

		return findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId, int start,
		int end) {

		return findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, start, end, null);
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
	@Override
	public List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		koroneikiProductPurchaseKey = Objects.toString(
			koroneikiProductPurchaseKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByKPP_CI;
				finderArgs = new Object[] {
					koroneikiProductPurchaseKey, clusterId
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByKPP_CI;
			finderArgs = new Object[] {
				koroneikiProductPurchaseKey, clusterId, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!koroneikiProductPurchaseKey.equals(
							licenseKey.getKoroneikiProductPurchaseKey()) ||
						(clusterId != licenseKey.getClusterId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindKoroneikiProductPurchaseKey = false;

			if (koroneikiProductPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_3);
			}
			else {
				bindKoroneikiProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_KPP_CI_CLUSTERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKoroneikiProductPurchaseKey) {
					queryPos.add(koroneikiProductPurchaseKey);
				}

				queryPos.add(clusterId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByKPP_CI_First(
			String koroneikiProductPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByKPP_CI_First(
			koroneikiProductPurchaseKey, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiProductPurchaseKey=");
		sb.append(koroneikiProductPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByKPP_CI_First(
		String koroneikiProductPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByKPP_CI_Last(
			String koroneikiProductPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByKPP_CI_Last(
			koroneikiProductPurchaseKey, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("koroneikiProductPurchaseKey=");
		sb.append(koroneikiProductPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByKPP_CI_Last(
		String koroneikiProductPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByKPP_CI(koroneikiProductPurchaseKey, clusterId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByKPP_CI(
			koroneikiProductPurchaseKey, clusterId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByKPP_CI_PrevAndNext(
			long licenseKeyId, String koroneikiProductPurchaseKey,
			long clusterId, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		koroneikiProductPurchaseKey = Objects.toString(
			koroneikiProductPurchaseKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByKPP_CI_PrevAndNext(
				session, licenseKey, koroneikiProductPurchaseKey, clusterId,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByKPP_CI_PrevAndNext(
				session, licenseKey, koroneikiProductPurchaseKey, clusterId,
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

	protected LicenseKey getByKPP_CI_PrevAndNext(
		Session session, LicenseKey licenseKey,
		String koroneikiProductPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindKoroneikiProductPurchaseKey = false;

		if (koroneikiProductPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_3);
		}
		else {
			bindKoroneikiProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_2);
		}

		sb.append(_FINDER_COLUMN_KPP_CI_CLUSTERID_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindKoroneikiProductPurchaseKey) {
			queryPos.add(koroneikiProductPurchaseKey);
		}

		queryPos.add(clusterId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63; from the database.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 */
	@Override
	public void removeByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId) {

		for (LicenseKey licenseKey :
				findByKPP_CI(
					koroneikiProductPurchaseKey, clusterId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where koroneikiProductPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByKPP_CI(
		String koroneikiProductPurchaseKey, long clusterId) {

		koroneikiProductPurchaseKey = Objects.toString(
			koroneikiProductPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByKPP_CI;

		Object[] finderArgs = new Object[] {
			koroneikiProductPurchaseKey, clusterId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindKoroneikiProductPurchaseKey = false;

			if (koroneikiProductPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_3);
			}
			else {
				bindKoroneikiProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_KPP_CI_CLUSTERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindKoroneikiProductPurchaseKey) {
					queryPos.add(koroneikiProductPurchaseKey);
				}

				queryPos.add(clusterId);

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

	private static final String
		_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_2 =
			"licenseKey.koroneikiProductPurchaseKey = ? AND ";

	private static final String
		_FINDER_COLUMN_KPP_CI_KORONEIKIPRODUCTPURCHASEKEY_3 =
			"(licenseKey.koroneikiProductPurchaseKey IS NULL OR licenseKey.koroneikiProductPurchaseKey = '') AND ";

	private static final String _FINDER_COLUMN_KPP_CI_CLUSTERID_2 =
		"licenseKey.clusterId = ?";

	private FinderPath _finderPathWithPaginationFindByOEI_CI;
	private FinderPath _finderPathWithoutPaginationFindByOEI_CI;
	private FinderPath _finderPathCountByOEI_CI;

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI(long offeringEntryId, long clusterId) {
		return findByOEI_CI(
			offeringEntryId, clusterId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end) {

		return findByOEI_CI(offeringEntryId, clusterId, start, end, null);
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
	@Override
	public List<LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByOEI_CI(
			offeringEntryId, clusterId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByOEI_CI(
		long offeringEntryId, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOEI_CI;
				finderArgs = new Object[] {offeringEntryId, clusterId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOEI_CI;
			finderArgs = new Object[] {
				offeringEntryId, clusterId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						(clusterId != licenseKey.getClusterId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

			sb.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				queryPos.add(clusterId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_CI_First(
			long offeringEntryId, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_CI_First(
			offeringEntryId, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_CI_First(
		long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByOEI_CI(
			offeringEntryId, clusterId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByOEI_CI_Last(
			long offeringEntryId, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_CI_Last(
			offeringEntryId, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByOEI_CI_Last(
		long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByOEI_CI(offeringEntryId, clusterId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_CI(
			offeringEntryId, clusterId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByOEI_CI_PrevAndNext(
			long licenseKeyId, long offeringEntryId, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_CI_PrevAndNext(
				session, licenseKey, offeringEntryId, clusterId,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_CI_PrevAndNext(
				session, licenseKey, offeringEntryId, clusterId,
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

	protected LicenseKey getByOEI_CI_PrevAndNext(
		Session session, LicenseKey licenseKey, long offeringEntryId,
		long clusterId, OrderByComparator<LicenseKey> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

		sb.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(offeringEntryId);

		queryPos.add(clusterId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 */
	@Override
	public void removeByOEI_CI(long offeringEntryId, long clusterId) {
		for (LicenseKey licenseKey :
				findByOEI_CI(
					offeringEntryId, clusterId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_CI(long offeringEntryId, long clusterId) {
		FinderPath finderPath = _finderPathCountByOEI_CI;

		Object[] finderArgs = new Object[] {offeringEntryId, clusterId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2);

			sb.append(_FINDER_COLUMN_OEI_CI_CLUSTERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				queryPos.add(clusterId);

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

	private static final String _FINDER_COLUMN_OEI_CI_OFFERINGENTRYID_2 =
		"licenseKey.offeringEntryId = ? AND ";

	private static final String _FINDER_COLUMN_OEI_CI_CLUSTERID_2 =
		"licenseKey.clusterId = ?";

	private FinderPath _finderPathWithPaginationFindByPI_SI;
	private FinderPath _finderPathWithoutPaginationFindByPI_SI;
	private FinderPath _finderPathCountByPI_SI;

	/**
	 * Returns all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(String productId, String serverId) {
		return findByPI_SI(
			productId, serverId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end) {

		return findByPI_SI(productId, serverId, start, end, null);
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
	@Override
	public List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPI_SI(
			productId, serverId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPI_SI;
				finderArgs = new Object[] {productId, serverId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPI_SI;
			finderArgs = new Object[] {
				productId, serverId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productId.equals(licenseKey.getProductId()) ||
						!serverId.equals(licenseKey.getServerId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPI_SI_First(
			String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPI_SI_First(
			productId, serverId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPI_SI_First(
		String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPI_SI(
			productId, serverId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPI_SI_Last(
			String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPI_SI_Last(
			productId, serverId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPI_SI_Last(
		String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPI_SI(productId, serverId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPI_SI(
			productId, serverId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPI_SI_PrevAndNext(
			long licenseKeyId, String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPI_SI_PrevAndNext(
				session, licenseKey, productId, serverId, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByPI_SI_PrevAndNext(
				session, licenseKey, productId, serverId, orderByComparator,
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

	protected LicenseKey getByPI_SI_PrevAndNext(
		Session session, LicenseKey licenseKey, String productId,
		String serverId, OrderByComparator<LicenseKey> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindProductId = false;

		if (productId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
		}

		boolean bindServerId = false;

		if (serverId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
		}
		else {
			bindServerId = true;

			sb.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductId) {
			queryPos.add(productId);
		}

		if (bindServerId) {
			queryPos.add(serverId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 */
	@Override
	public void removeByPI_SI(String productId, String serverId) {
		for (LicenseKey licenseKey :
				findByPI_SI(
					productId, serverId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPI_SI(String productId, String serverId) {
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = _finderPathCountByPI_SI;

		Object[] finderArgs = new Object[] {productId, serverId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
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

	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_2 =
		"licenseKey.productId = ? AND ";

	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_3 =
		"(licenseKey.productId IS NULL OR licenseKey.productId = '') AND ";

	private static final String _FINDER_COLUMN_PI_SI_SERVERID_2 =
		"licenseKey.serverId = ?";

	private static final String _FINDER_COLUMN_PI_SI_SERVERID_3 =
		"(licenseKey.serverId IS NULL OR licenseKey.serverId = '')";

	private FinderPath _finderPathWithPaginationFindByARLU_C_A;
	private FinderPath _finderPathWithoutPaginationFindByARLU_C_A;
	private FinderPath _finderPathCountByARLU_C_A;

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end) {

		return findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByARLU_C_A;
				finderArgs = new Object[] {
					assetReceiptLicenseUuid, complimentary, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByARLU_C_A;
			finderArgs = new Object[] {
				assetReceiptLicenseUuid, complimentary, active, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!assetReceiptLicenseUuid.equals(
							licenseKey.getAssetReceiptLicenseUuid()) ||
						(complimentary != licenseKey.isComplimentary()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_ARLU_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_C_A_First(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_C_A_First(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByARLU_C_A_First(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByARLU_C_A_Last(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_C_A_Last(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByARLU_C_A_Last(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByARLU_C_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLU_C_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, complimentary,
				active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLU_C_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, complimentary,
				active, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByARLU_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String assetReceiptLicenseUuid,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindAssetReceiptLicenseUuid = false;

		if (assetReceiptLicenseUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3);
		}
		else {
			bindAssetReceiptLicenseUuid = true;

			sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2);
		}

		sb.append(_FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_ARLU_C_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindAssetReceiptLicenseUuid) {
			queryPos.add(assetReceiptLicenseUuid);
		}

		queryPos.add(complimentary);

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		for (LicenseKey licenseKey :
				findByARLU_C_A(
					assetReceiptLicenseUuid, complimentary, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = _finderPathCountByARLU_C_A;

		Object[] finderArgs = new Object[] {
			assetReceiptLicenseUuid, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_ARLU_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

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

	private static final String
		_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2 =
			"licenseKey.assetReceiptLicenseUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3 =
			"(licenseKey.assetReceiptLicenseUuid IS NULL OR licenseKey.assetReceiptLicenseUuid = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_ARLU_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByOEI_CI_A;
	private FinderPath _finderPathWithoutPaginationFindByOEI_CI_A;
	private FinderPath _finderPathCountByOEI_CI_A;

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active) {

		return findByOEI_CI_A(
			offeringEntryId, clusterId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end) {

		return findByOEI_CI_A(
			offeringEntryId, clusterId, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByOEI_CI_A(
			offeringEntryId, clusterId, active, start, end, orderByComparator,
			true);
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
	@Override
	public List<LicenseKey> findByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOEI_CI_A;
				finderArgs = new Object[] {offeringEntryId, clusterId, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOEI_CI_A;
			finderArgs = new Object[] {
				offeringEntryId, clusterId, active, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						(clusterId != licenseKey.getClusterId()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

			sb.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

			sb.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				queryPos.add(clusterId);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_CI_A_First(
			long offeringEntryId, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_CI_A_First(
			offeringEntryId, clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_CI_A_First(
		long offeringEntryId, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByOEI_CI_A(
			offeringEntryId, clusterId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByOEI_CI_A_Last(
			long offeringEntryId, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_CI_A_Last(
			offeringEntryId, clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_CI_A_Last(
		long offeringEntryId, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByOEI_CI_A(offeringEntryId, clusterId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_CI_A(
			offeringEntryId, clusterId, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByOEI_CI_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, long clusterId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_CI_A_PrevAndNext(
				session, licenseKey, offeringEntryId, clusterId, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_CI_A_PrevAndNext(
				session, licenseKey, offeringEntryId, clusterId, active,
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

	protected LicenseKey getByOEI_CI_A_PrevAndNext(
		Session session, LicenseKey licenseKey, long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

		sb.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

		sb.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(offeringEntryId);

		queryPos.add(clusterId);

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 */
	@Override
	public void removeByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active) {

		for (LicenseKey licenseKey :
				findByOEI_CI_A(
					offeringEntryId, clusterId, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_CI_A(
		long offeringEntryId, long clusterId, boolean active) {

		FinderPath finderPath = _finderPathCountByOEI_CI_A;

		Object[] finderArgs = new Object[] {offeringEntryId, clusterId, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2);

			sb.append(_FINDER_COLUMN_OEI_CI_A_CLUSTERID_2);

			sb.append(_FINDER_COLUMN_OEI_CI_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				queryPos.add(clusterId);

				queryPos.add(active);

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

	private static final String _FINDER_COLUMN_OEI_CI_A_OFFERINGENTRYID_2 =
		"licenseKey.offeringEntryId = ? AND ";

	private static final String _FINDER_COLUMN_OEI_CI_A_CLUSTERID_2 =
		"licenseKey.clusterId = ? AND ";

	private static final String _FINDER_COLUMN_OEI_CI_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByOEI_C_A;
	private FinderPath _finderPathWithoutPaginationFindByOEI_C_A;
	private FinderPath _finderPathCountByOEI_C_A;
	private FinderPath _finderPathWithPaginationCountByOEI_C_A;

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active) {

		return findByOEI_C_A(
			offeringEntryId, complimentary, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end) {

		return findByOEI_C_A(
			offeringEntryId, complimentary, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByOEI_C_A(
			offeringEntryId, complimentary, active, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOEI_C_A;
				finderArgs = new Object[] {
					offeringEntryId, complimentary, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOEI_C_A;
			finderArgs = new Object[] {
				offeringEntryId, complimentary, active, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						(complimentary != licenseKey.isComplimentary()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

			sb.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				queryPos.add(complimentary);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByOEI_C_A_First(
			long offeringEntryId, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_C_A_First(
			offeringEntryId, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_C_A_First(
		long offeringEntryId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByOEI_C_A(
			offeringEntryId, complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByOEI_C_A_Last(
			long offeringEntryId, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_C_A_Last(
			offeringEntryId, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_C_A_Last(
		long offeringEntryId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByOEI_C_A(offeringEntryId, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_C_A(
			offeringEntryId, complimentary, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByOEI_C_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_C_A_PrevAndNext(
				session, licenseKey, offeringEntryId, complimentary, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_C_A_PrevAndNext(
				session, licenseKey, offeringEntryId, complimentary, active,
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

	protected LicenseKey getByOEI_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

		sb.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(offeringEntryId);

		queryPos.add(complimentary);

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
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
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		return findByOEI_C_A(
			offeringEntryIds, complimentary, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end) {

		return findByOEI_C_A(
			offeringEntryIds, complimentary, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByOEI_C_A(
			offeringEntryIds, complimentary, active, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		if (offeringEntryIds == null) {
			offeringEntryIds = new long[0];
		}
		else if (offeringEntryIds.length > 1) {
			offeringEntryIds = ArrayUtil.unique(offeringEntryIds);

			Arrays.sort(offeringEntryIds);
		}

		if (offeringEntryIds.length == 1) {
			return findByOEI_C_A(
				offeringEntryIds[0], complimentary, active, start, end,
				orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					StringUtil.merge(offeringEntryIds), complimentary, active
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				StringUtil.merge(offeringEntryIds), complimentary, active,
				start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				_finderPathWithPaginationFindByOEI_C_A, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!ArrayUtil.contains(
							offeringEntryIds,
							licenseKey.getOfferingEntryId()) ||
						(complimentary != licenseKey.isComplimentary()) ||
						(active != licenseKey.isActive())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			if (offeringEntryIds.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_7);

				sb.append(StringUtil.merge(offeringEntryIds));

				sb.append(")");

				sb.append(")");

				sb.append(WHERE_AND);
			}

			sb.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(complimentary);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByOEI_C_A, finderArgs,
						list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByOEI_C_A, finderArgs);
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
	 * Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active) {

		for (LicenseKey licenseKey :
				findByOEI_C_A(
					offeringEntryId, complimentary, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_C_A(
		long offeringEntryId, boolean complimentary, boolean active) {

		FinderPath finderPath = _finderPathCountByOEI_C_A;

		Object[] finderArgs = new Object[] {
			offeringEntryId, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2);

			sb.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				queryPos.add(complimentary);

				queryPos.add(active);

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

	/**
	 * Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryIds the offering entry IDs
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByOEI_C_A(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		if (offeringEntryIds == null) {
			offeringEntryIds = new long[0];
		}
		else if (offeringEntryIds.length > 1) {
			offeringEntryIds = ArrayUtil.unique(offeringEntryIds);

			Arrays.sort(offeringEntryIds);
		}

		Object[] finderArgs = new Object[] {
			StringUtil.merge(offeringEntryIds), complimentary, active
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByOEI_C_A, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			if (offeringEntryIds.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_7);

				sb.append(StringUtil.merge(offeringEntryIds));

				sb.append(")");

				sb.append(")");

				sb.append(WHERE_AND);
			}

			sb.append(_FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_C_A_ACTIVE_2);

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(complimentary);

				queryPos.add(active);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByOEI_C_A, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByOEI_C_A, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_2 =
		"licenseKey.offeringEntryId = ? AND ";

	private static final String _FINDER_COLUMN_OEI_C_A_OFFERINGENTRYID_7 =
		"licenseKey.offeringEntryId IN (";

	private static final String _FINDER_COLUMN_OEI_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_OEI_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByPEN_SI_A;
	private FinderPath _finderPathWithoutPaginationFindByPEN_SI_A;
	private FinderPath _finderPathCountByPEN_SI_A;

	/**
	 * Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active) {

		return findByPEN_SI_A(
			productEntryName, serverId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active, int start,
		int end) {

		return findByPEN_SI_A(
			productEntryName, serverId, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByPEN_SI_A(
			productEntryName, serverId, active, start, end, orderByComparator,
			true);
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
	@Override
	public List<LicenseKey> findByPEN_SI_A(
		String productEntryName, String serverId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productEntryName = Objects.toString(productEntryName, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPEN_SI_A;
				finderArgs = new Object[] {productEntryName, serverId, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPEN_SI_A;
			finderArgs = new Object[] {
				productEntryName, serverId, active, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productEntryName.equals(
							licenseKey.getProductEntryName()) ||
						!serverId.equals(licenseKey.getServerId()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindProductEntryName = false;

			if (productEntryName.isEmpty()) {
				sb.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
			}
			else {
				bindProductEntryName = true;

				sb.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductEntryName) {
					queryPos.add(productEntryName);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPEN_SI_A_First(
			String productEntryName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPEN_SI_A_First(
			productEntryName, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productEntryName=");
		sb.append(productEntryName);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPEN_SI_A_First(
		String productEntryName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPEN_SI_A(
			productEntryName, serverId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPEN_SI_A_Last(
			String productEntryName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPEN_SI_A_Last(
			productEntryName, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productEntryName=");
		sb.append(productEntryName);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPEN_SI_A_Last(
		String productEntryName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPEN_SI_A(productEntryName, serverId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPEN_SI_A(
			productEntryName, serverId, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPEN_SI_A_PrevAndNext(
			long licenseKeyId, String productEntryName, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productEntryName = Objects.toString(productEntryName, "");
		serverId = Objects.toString(serverId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPEN_SI_A_PrevAndNext(
				session, licenseKey, productEntryName, serverId, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPEN_SI_A_PrevAndNext(
				session, licenseKey, productEntryName, serverId, active,
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

	protected LicenseKey getByPEN_SI_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String productEntryName,
		String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindProductEntryName = false;

		if (productEntryName.isEmpty()) {
			sb.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
		}
		else {
			bindProductEntryName = true;

			sb.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
		}

		boolean bindServerId = false;

		if (serverId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
		}
		else {
			bindServerId = true;

			sb.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
		}

		sb.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductEntryName) {
			queryPos.add(productEntryName);
		}

		if (bindServerId) {
			queryPos.add(serverId);
		}

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 */
	@Override
	public void removeByPEN_SI_A(
		String productEntryName, String serverId, boolean active) {

		for (LicenseKey licenseKey :
				findByPEN_SI_A(
					productEntryName, serverId, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productEntryName the product entry name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPEN_SI_A(
		String productEntryName, String serverId, boolean active) {

		productEntryName = Objects.toString(productEntryName, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = _finderPathCountByPEN_SI_A;

		Object[] finderArgs = new Object[] {productEntryName, serverId, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductEntryName = false;

			if (productEntryName.isEmpty()) {
				sb.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3);
			}
			else {
				bindProductEntryName = true;

				sb.append(_FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PEN_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_PEN_SI_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductEntryName) {
					queryPos.add(productEntryName);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

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

	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_2 =
		"licenseKey.productEntryName = ? AND ";

	private static final String _FINDER_COLUMN_PEN_SI_A_PRODUCTENTRYNAME_3 =
		"(licenseKey.productEntryName IS NULL OR licenseKey.productEntryName = '') AND ";

	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_2 =
		"licenseKey.serverId = ? AND ";

	private static final String _FINDER_COLUMN_PEN_SI_A_SERVERID_3 =
		"(licenseKey.serverId IS NULL OR licenseKey.serverId = '') AND ";

	private static final String _FINDER_COLUMN_PEN_SI_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByARLU_PI_SI_A;
	private FinderPath _finderPathWithoutPaginationFindByARLU_PI_SI_A;
	private FinderPath _finderPathCountByARLU_PI_SI_A;

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		return findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end) {

		return findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			null);
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
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByARLU_PI_SI_A;
				finderArgs = new Object[] {
					assetReceiptLicenseUuid, productId, serverId, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByARLU_PI_SI_A;
			finderArgs = new Object[] {
				assetReceiptLicenseUuid, productId, serverId, active, start,
				end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!assetReceiptLicenseUuid.equals(
							licenseKey.getAssetReceiptLicenseUuid()) ||
						!productId.equals(licenseKey.getProductId()) ||
						!serverId.equals(licenseKey.getServerId()) ||
						(active != licenseKey.isActive())) {

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
					6 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(6);
			}

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2);
			}

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	@Override
	public LicenseKey findByARLU_PI_SI_A_First(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_PI_SI_A_First(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByARLU_PI_SI_A_First(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByARLU_PI_SI_A_Last(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_PI_SI_A_Last(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByARLU_PI_SI_A_Last(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByARLU_PI_SI_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, String productId,
			String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLU_PI_SI_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, productId,
				serverId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLU_PI_SI_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, productId,
				serverId, active, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByARLU_PI_SI_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String assetReceiptLicenseUuid,
		String productId, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindAssetReceiptLicenseUuid = false;

		if (assetReceiptLicenseUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3);
		}
		else {
			bindAssetReceiptLicenseUuid = true;

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2);
		}

		boolean bindProductId = false;

		if (productId.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2);
		}

		boolean bindServerId = false;

		if (serverId.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3);
		}
		else {
			bindServerId = true;

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2);
		}

		sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindAssetReceiptLicenseUuid) {
			queryPos.add(assetReceiptLicenseUuid);
		}

		if (bindProductId) {
			queryPos.add(productId);
		}

		if (bindServerId) {
			queryPos.add(serverId);
		}

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 */
	@Override
	public void removeByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		for (LicenseKey licenseKey :
				findByARLU_PI_SI_A(
					assetReceiptLicenseUuid, productId, serverId, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
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
	@Override
	public int countByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = _finderPathCountByARLU_PI_SI_A;

		Object[] finderArgs = new Object[] {
			assetReceiptLicenseUuid, productId, serverId, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2);
			}

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

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

	private static final String
		_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2 =
			"licenseKey.assetReceiptLicenseUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3 =
			"(licenseKey.assetReceiptLicenseUuid IS NULL OR licenseKey.assetReceiptLicenseUuid = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2 =
		"licenseKey.productId = ? AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3 =
		"(licenseKey.productId IS NULL OR licenseKey.productId = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2 =
		"licenseKey.serverId = ? AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3 =
		"(licenseKey.serverId IS NULL OR licenseKey.serverId = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByOEI_LET_C_A;
	private FinderPath _finderPathWithoutPaginationFindByOEI_LET_C_A;
	private FinderPath _finderPathCountByOEI_LET_C_A;

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		return findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end) {

		return findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, null);
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
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByOEI_LET_C_A;
				finderArgs = new Object[] {
					offeringEntryId, licenseEntryType, complimentary, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByOEI_LET_C_A;
			finderArgs = new Object[] {
				offeringEntryId, licenseEntryType, complimentary, active, start,
				end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						!licenseEntryType.equals(
							licenseKey.getLicenseEntryType()) ||
						(complimentary != licenseKey.isComplimentary()) ||
						(active != licenseKey.isActive())) {

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
					6 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(6);
			}

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					queryPos.add(licenseEntryType);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	@Override
	public LicenseKey findByOEI_LET_C_A_First(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_LET_C_A_First(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", licenseEntryType=");
		sb.append(licenseEntryType);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_LET_C_A_First(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByOEI_LET_C_A_Last(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_LET_C_A_Last(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", licenseEntryType=");
		sb.append(licenseEntryType);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_LET_C_A_Last(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_LET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByOEI_LET_C_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		licenseEntryType = Objects.toString(licenseEntryType, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_LET_C_A_PrevAndNext(
				session, licenseKey, offeringEntryId, licenseEntryType,
				complimentary, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_LET_C_A_PrevAndNext(
				session, licenseKey, offeringEntryId, licenseEntryType,
				complimentary, active, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_LET_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

		boolean bindLicenseEntryType = false;

		if (licenseEntryType.isEmpty()) {
			sb.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
		}
		else {
			bindLicenseEntryType = true;

			sb.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
		}

		sb.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(offeringEntryId);

		if (bindLicenseEntryType) {
			queryPos.add(licenseEntryType);
		}

		queryPos.add(complimentary);

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		for (LicenseKey licenseKey :
				findByOEI_LET_C_A(
					offeringEntryId, licenseEntryType, complimentary, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
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
	@Override
	public int countByOEI_LET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = _finderPathCountByOEI_LET_C_A;

		Object[] finderArgs = new Object[] {
			offeringEntryId, licenseEntryType, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					queryPos.add(licenseEntryType);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

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

	private static final String _FINDER_COLUMN_OEI_LET_C_A_OFFERINGENTRYID_2 =
		"licenseKey.offeringEntryId = ? AND ";

	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_2 =
		"licenseKey.licenseEntryType = ? AND ";

	private static final String _FINDER_COLUMN_OEI_LET_C_A_LICENSEENTRYTYPE_3 =
		"(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType = '') AND ";

	private static final String _FINDER_COLUMN_OEI_LET_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_OEI_LET_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByOEI_NotLET_C_A;
	private FinderPath _finderPathWithPaginationCountByOEI_NotLET_C_A;

	/**
	 * Returns all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		return findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end) {

		return findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, null);
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
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByOEI_NotLET_C_A;
		finderArgs = new Object[] {
			offeringEntryId, licenseEntryType, complimentary, active, start,
			end, orderByComparator
		};

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if ((offeringEntryId != licenseKey.getOfferingEntryId()) ||
						licenseEntryType.equals(
							licenseKey.getLicenseEntryType()) ||
						(complimentary != licenseKey.isComplimentary()) ||
						(active != licenseKey.isActive())) {

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
					6 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(6);
			}

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					queryPos.add(licenseEntryType);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	@Override
	public LicenseKey findByOEI_NotLET_C_A_First(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_NotLET_C_A_First(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", licenseEntryType!=");
		sb.append(licenseEntryType);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_NotLET_C_A_First(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByOEI_NotLET_C_A_Last(
			long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByOEI_NotLET_C_A_Last(
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("offeringEntryId=");
		sb.append(offeringEntryId);

		sb.append(", licenseEntryType!=");
		sb.append(licenseEntryType);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByOEI_NotLET_C_A_Last(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByOEI_NotLET_C_A(
			offeringEntryId, licenseEntryType, complimentary, active, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(
			long licenseKeyId, long offeringEntryId, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		licenseEntryType = Objects.toString(licenseEntryType, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByOEI_NotLET_C_A_PrevAndNext(
				session, licenseKey, offeringEntryId, licenseEntryType,
				complimentary, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByOEI_NotLET_C_A_PrevAndNext(
				session, licenseKey, offeringEntryId, licenseEntryType,
				complimentary, active, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByOEI_NotLET_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, long offeringEntryId,
		String licenseEntryType, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

		boolean bindLicenseEntryType = false;

		if (licenseEntryType.isEmpty()) {
			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
		}
		else {
			bindLicenseEntryType = true;

			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
		}

		sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(offeringEntryId);

		if (bindLicenseEntryType) {
			queryPos.add(licenseEntryType);
		}

		queryPos.add(complimentary);

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param offeringEntryId the offering entry ID
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		for (LicenseKey licenseKey :
				findByOEI_NotLET_C_A(
					offeringEntryId, licenseEntryType, complimentary, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
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
	@Override
	public int countByOEI_NotLET_C_A(
		long offeringEntryId, String licenseEntryType, boolean complimentary,
		boolean active) {

		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = _finderPathWithPaginationCountByOEI_NotLET_C_A;

		Object[] finderArgs = new Object[] {
			offeringEntryId, licenseEntryType, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2);

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(offeringEntryId);

				if (bindLicenseEntryType) {
					queryPos.add(licenseEntryType);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

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

	private static final String
		_FINDER_COLUMN_OEI_NOTLET_C_A_OFFERINGENTRYID_2 =
			"licenseKey.offeringEntryId = ? AND ";

	private static final String
		_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_2 =
			"licenseKey.licenseEntryType != ? AND ";

	private static final String
		_FINDER_COLUMN_OEI_NOTLET_C_A_LICENSEENTRYTYPE_3 =
			"(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType != '') AND ";

	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_OEI_NOTLET_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	public LicenseKeyPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("key", "key_");
		dbColumnNames.put("active", "active_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(LicenseKey.class);
	}

	/**
	 * Caches the license key in the entity cache if it is enabled.
	 *
	 * @param licenseKey the license key
	 */
	@Override
	public void cacheResult(LicenseKey licenseKey) {
		entityCache.putResult(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED, LicenseKeyImpl.class,
			licenseKey.getPrimaryKey(), licenseKey);

		licenseKey.resetOriginalValues();
	}

	/**
	 * Caches the license keies in the entity cache if it is enabled.
	 *
	 * @param licenseKeies the license keies
	 */
	@Override
	public void cacheResult(List<LicenseKey> licenseKeies) {
		for (LicenseKey licenseKey : licenseKeies) {
			if (entityCache.getResult(
					LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeyImpl.class, licenseKey.getPrimaryKey()) == null) {

				cacheResult(licenseKey);
			}
			else {
				licenseKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license keies.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LicenseKeyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license key.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseKey licenseKey) {
		entityCache.removeResult(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED, LicenseKeyImpl.class,
			licenseKey.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LicenseKey> licenseKeies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseKey licenseKey : licenseKeies) {
			entityCache.removeResult(
				LicenseKeyModelImpl.ENTITY_CACHE_ENABLED, LicenseKeyImpl.class,
				licenseKey.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				LicenseKeyModelImpl.ENTITY_CACHE_ENABLED, LicenseKeyImpl.class,
				primaryKey);
		}
	}

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	@Override
	public LicenseKey create(long licenseKeyId) {
		LicenseKey licenseKey = new LicenseKeyImpl();

		licenseKey.setNew(true);
		licenseKey.setPrimaryKey(licenseKeyId);

		String uuid = PortalUUIDUtil.generate();

		licenseKey.setUuid(uuid);

		return licenseKey;
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey remove(long licenseKeyId)
		throws NoSuchLicenseKeyException {

		return remove((Serializable)licenseKeyId);
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey remove(Serializable primaryKey)
		throws NoSuchLicenseKeyException {

		Session session = null;

		try {
			session = openSession();

			LicenseKey licenseKey = (LicenseKey)session.get(
				LicenseKeyImpl.class, primaryKey);

			if (licenseKey == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseKeyException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(licenseKey);
		}
		catch (NoSuchLicenseKeyException noSuchEntityException) {
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
	protected LicenseKey removeImpl(LicenseKey licenseKey) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(licenseKey)) {
				licenseKey = (LicenseKey)session.get(
					LicenseKeyImpl.class, licenseKey.getPrimaryKeyObj());
			}

			if (licenseKey != null) {
				session.delete(licenseKey);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (licenseKey != null) {
			clearCache(licenseKey);
		}

		return licenseKey;
	}

	@Override
	public LicenseKey updateImpl(LicenseKey licenseKey) {
		boolean isNew = licenseKey.isNew();

		if (!(licenseKey instanceof LicenseKeyModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(licenseKey.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(licenseKey);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in licenseKey proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LicenseKey implementation " +
					licenseKey.getClass());
		}

		LicenseKeyModelImpl licenseKeyModelImpl =
			(LicenseKeyModelImpl)licenseKey;

		if (Validator.isNull(licenseKey.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			licenseKey.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (licenseKey.getCreateDate() == null)) {
			if (serviceContext == null) {
				licenseKey.setCreateDate(now);
			}
			else {
				licenseKey.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!licenseKeyModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				licenseKey.setModifiedDate(now);
			}
			else {
				licenseKey.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(licenseKey);

				licenseKey.setNew(false);
			}
			else {
				licenseKey = (LicenseKey)session.merge(licenseKey);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LicenseKeyModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {licenseKeyModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {licenseKeyModelImpl.getLicenseKeySetId()};

			finderCache.removeResult(_finderPathCountByLicenseKeySetId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByLicenseKeySetId, args);

			args = new Object[] {
				licenseKeyModelImpl.getKoroneikiProductPurchaseKey()
			};

			finderCache.removeResult(
				_finderPathCountByKoroneikiProductPurchaseKey, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByKoroneikiProductPurchaseKey,
				args);

			args = new Object[] {licenseKeyModelImpl.getAccountEntryId()};

			finderCache.removeResult(_finderPathCountByAccountEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountEntryId, args);

			args = new Object[] {licenseKeyModelImpl.getOfferingEntryId()};

			finderCache.removeResult(_finderPathCountByOfferingEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByOfferingEntryId, args);

			args = new Object[] {
				licenseKeyModelImpl.getUserId(),
				licenseKeyModelImpl.getAccountEntryId()
			};

			finderCache.removeResult(_finderPathCountByU_AEI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_AEI, args);

			args = new Object[] {
				licenseKeyModelImpl.getUserId(),
				licenseKeyModelImpl.getProductId()
			};

			finderCache.removeResult(_finderPathCountByU_PI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_PI, args);

			args = new Object[] {
				licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByARLU_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByARLU_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getKoroneikiAccountKey(),
				licenseKeyModelImpl.getProductEntryId()
			};

			finderCache.removeResult(_finderPathCountByKA_PEI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByKA_PEI, args);

			args = new Object[] {
				licenseKeyModelImpl.getKoroneikiProductPurchaseKey(),
				licenseKeyModelImpl.getClusterId()
			};

			finderCache.removeResult(_finderPathCountByKPP_CI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByKPP_CI, args);

			args = new Object[] {
				licenseKeyModelImpl.getOfferingEntryId(),
				licenseKeyModelImpl.getClusterId()
			};

			finderCache.removeResult(_finderPathCountByOEI_CI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByOEI_CI, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductId(),
				licenseKeyModelImpl.getServerId()
			};

			finderCache.removeResult(_finderPathCountByPI_SI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPI_SI, args);

			args = new Object[] {
				licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
				licenseKeyModelImpl.isComplimentary(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByARLU_C_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByARLU_C_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getOfferingEntryId(),
				licenseKeyModelImpl.getClusterId(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByOEI_CI_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByOEI_CI_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getOfferingEntryId(),
				licenseKeyModelImpl.isComplimentary(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByOEI_C_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByOEI_C_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductEntryName(),
				licenseKeyModelImpl.getServerId(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByPEN_SI_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPEN_SI_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
				licenseKeyModelImpl.getProductId(),
				licenseKeyModelImpl.getServerId(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByARLU_PI_SI_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByARLU_PI_SI_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getOfferingEntryId(),
				licenseKeyModelImpl.getLicenseEntryType(),
				licenseKeyModelImpl.isComplimentary(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByOEI_LET_C_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByOEI_LET_C_A, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {licenseKeyModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByLicenseKeySetId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalLicenseKeySetId()
				};

				finderCache.removeResult(
					_finderPathCountByLicenseKeySetId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByLicenseKeySetId, args);

				args = new Object[] {licenseKeyModelImpl.getLicenseKeySetId()};

				finderCache.removeResult(
					_finderPathCountByLicenseKeySetId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByLicenseKeySetId, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByKoroneikiProductPurchaseKey.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalKoroneikiProductPurchaseKey()
				};

				finderCache.removeResult(
					_finderPathCountByKoroneikiProductPurchaseKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKoroneikiProductPurchaseKey,
					args);

				args = new Object[] {
					licenseKeyModelImpl.getKoroneikiProductPurchaseKey()
				};

				finderCache.removeResult(
					_finderPathCountByKoroneikiProductPurchaseKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKoroneikiProductPurchaseKey,
					args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAccountEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByAccountEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountEntryId, args);

				args = new Object[] {licenseKeyModelImpl.getAccountEntryId()};

				finderCache.removeResult(
					_finderPathCountByAccountEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountEntryId, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByOfferingEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalOfferingEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByOfferingEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOfferingEntryId, args);

				args = new Object[] {licenseKeyModelImpl.getOfferingEntryId()};

				finderCache.removeResult(
					_finderPathCountByOfferingEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOfferingEntryId, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_AEI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalUserId(),
					licenseKeyModelImpl.getOriginalAccountEntryId()
				};

				finderCache.removeResult(_finderPathCountByU_AEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AEI, args);

				args = new Object[] {
					licenseKeyModelImpl.getUserId(),
					licenseKeyModelImpl.getAccountEntryId()
				};

				finderCache.removeResult(_finderPathCountByU_AEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AEI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_PI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalUserId(),
					licenseKeyModelImpl.getOriginalProductId()
				};

				finderCache.removeResult(_finderPathCountByU_PI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_PI, args);

				args = new Object[] {
					licenseKeyModelImpl.getUserId(),
					licenseKeyModelImpl.getProductId()
				};

				finderCache.removeResult(_finderPathCountByU_PI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_PI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByARLU_A.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByKA_PEI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalKoroneikiAccountKey(),
					licenseKeyModelImpl.getOriginalProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByKA_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKA_PEI, args);

				args = new Object[] {
					licenseKeyModelImpl.getKoroneikiAccountKey(),
					licenseKeyModelImpl.getProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByKA_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKA_PEI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByKPP_CI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.
						getOriginalKoroneikiProductPurchaseKey(),
					licenseKeyModelImpl.getOriginalClusterId()
				};

				finderCache.removeResult(_finderPathCountByKPP_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKPP_CI, args);

				args = new Object[] {
					licenseKeyModelImpl.getKoroneikiProductPurchaseKey(),
					licenseKeyModelImpl.getClusterId()
				};

				finderCache.removeResult(_finderPathCountByKPP_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByKPP_CI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByOEI_CI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalOfferingEntryId(),
					licenseKeyModelImpl.getOriginalClusterId()
				};

				finderCache.removeResult(_finderPathCountByOEI_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_CI, args);

				args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.getClusterId()
				};

				finderCache.removeResult(_finderPathCountByOEI_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_CI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPI_SI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductId(),
					licenseKeyModelImpl.getOriginalServerId()
				};

				finderCache.removeResult(_finderPathCountByPI_SI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPI_SI, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductId(),
					licenseKeyModelImpl.getServerId()
				};

				finderCache.removeResult(_finderPathCountByPI_SI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPI_SI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByARLU_C_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getOriginalComplimentary(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_C_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.isComplimentary(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_C_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByOEI_CI_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalOfferingEntryId(),
					licenseKeyModelImpl.getOriginalClusterId(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByOEI_CI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_CI_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.getClusterId(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByOEI_CI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_CI_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByOEI_C_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalOfferingEntryId(),
					licenseKeyModelImpl.getOriginalComplimentary(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByOEI_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_C_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.isComplimentary(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByOEI_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_C_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPEN_SI_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductEntryName(),
					licenseKeyModelImpl.getOriginalServerId(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByPEN_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPEN_SI_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductEntryName(),
					licenseKeyModelImpl.getServerId(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByPEN_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPEN_SI_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByARLU_PI_SI_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getOriginalProductId(),
					licenseKeyModelImpl.getOriginalServerId(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_PI_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_PI_SI_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getProductId(),
					licenseKeyModelImpl.getServerId(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_PI_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_PI_SI_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByOEI_LET_C_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalOfferingEntryId(),
					licenseKeyModelImpl.getOriginalLicenseEntryType(),
					licenseKeyModelImpl.getOriginalComplimentary(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByOEI_LET_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_LET_C_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getOfferingEntryId(),
					licenseKeyModelImpl.getLicenseEntryType(),
					licenseKeyModelImpl.isComplimentary(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByOEI_LET_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByOEI_LET_C_A, args);
			}
		}

		entityCache.putResult(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED, LicenseKeyImpl.class,
			licenseKey.getPrimaryKey(), licenseKey, false);

		licenseKey.resetOriginalValues();

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPrimaryKey(primaryKey);

		if (licenseKey == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLicenseKeyException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or throws a <code>NoSuchLicenseKeyException</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey findByPrimaryKey(long licenseKeyId)
		throws NoSuchLicenseKeyException {

		return findByPrimaryKey((Serializable)licenseKeyId);
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED, LicenseKeyImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		LicenseKey licenseKey = (LicenseKey)serializable;

		if (licenseKey == null) {
			Session session = null;

			try {
				session = openSession();

				licenseKey = (LicenseKey)session.get(
					LicenseKeyImpl.class, primaryKey);

				if (licenseKey != null) {
					cacheResult(licenseKey);
				}
				else {
					entityCache.putResult(
						LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
						LicenseKeyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeyImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey fetchByPrimaryKey(long licenseKeyId) {
		return fetchByPrimaryKey((Serializable)licenseKeyId);
	}

	@Override
	public Map<Serializable, LicenseKey> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, LicenseKey> map =
			new HashMap<Serializable, LicenseKey>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			LicenseKey licenseKey = fetchByPrimaryKey(primaryKey);

			if (licenseKey != null) {
				map.put(primaryKey, licenseKey);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				LicenseKeyModelImpl.ENTITY_CACHE_ENABLED, LicenseKeyImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (LicenseKey)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			(uncachedPrimaryKeys.size() * 2) + 1);

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE_PKS_IN);

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

			for (LicenseKey licenseKey : (List<LicenseKey>)query.list()) {
				map.put(licenseKey.getPrimaryKeyObj(), licenseKey);

				cacheResult(licenseKey);

				uncachedPrimaryKeys.remove(licenseKey.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
					LicenseKeyImpl.class, primaryKey, nullModel);
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
	 * Returns all the license keies.
	 *
	 * @return the license keies
	 */
	@Override
	public List<LicenseKey> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<LicenseKey> findAll(
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findAll(
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LICENSEKEY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEKEY;

				sql = sql.concat(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Removes all the license keies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LicenseKey licenseKey : findAll()) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LICENSEKEY);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LicenseKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license key persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			LicenseKeyModelImpl.UUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByLicenseKeySetId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLicenseKeySetId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByLicenseKeySetId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLicenseKeySetId",
			new String[] {Long.class.getName()},
			LicenseKeyModelImpl.LICENSEKEYSETID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByLicenseKeySetId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLicenseKeySetId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByKoroneikiProductPurchaseKey =
			new FinderPath(
				LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByKoroneikiProductPurchaseKey",
				new String[] {
					String.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByKoroneikiProductPurchaseKey =
			new FinderPath(
				LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
				LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByKoroneikiProductPurchaseKey",
				new String[] {String.class.getName()},
				LicenseKeyModelImpl.KORONEIKIPRODUCTPURCHASEKEY_COLUMN_BITMASK |
				LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByKoroneikiProductPurchaseKey = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKoroneikiProductPurchaseKey",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByAccountEntryId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountEntryId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountEntryId",
			new String[] {Long.class.getName()},
			LicenseKeyModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByAccountEntryId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByOfferingEntryId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOfferingEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByOfferingEntryId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOfferingEntryId",
			new String[] {Long.class.getName()},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByOfferingEntryId = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOfferingEntryId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByU_AEI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_AEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_AEI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_AEI",
			new String[] {Long.class.getName(), Long.class.getName()},
			LicenseKeyModelImpl.USERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByU_AEI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AEI",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByU_PI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_PI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_PI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_PI",
			new String[] {Long.class.getName(), String.class.getName()},
			LicenseKeyModelImpl.USERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByU_PI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_PI",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByARLU_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_A",
			new String[] {String.class.getName(), Boolean.class.getName()},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_A",
			new String[] {String.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByKA_PEI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKA_PEI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByKA_PEI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKA_PEI",
			new String[] {String.class.getName(), Long.class.getName()},
			LicenseKeyModelImpl.KORONEIKIACCOUNTKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByKA_PEI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKA_PEI",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByKPP_CI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKPP_CI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByKPP_CI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKPP_CI",
			new String[] {String.class.getName(), Long.class.getName()},
			LicenseKeyModelImpl.KORONEIKIPRODUCTPURCHASEKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByKPP_CI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKPP_CI",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByOEI_CI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_CI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByOEI_CI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_CI",
			new String[] {Long.class.getName(), Long.class.getName()},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByOEI_CI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_CI",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByPI_SI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPI_SI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPI_SI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPI_SI",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPI_SI = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPI_SI",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByARLU_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByOEI_CI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByOEI_CI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByOEI_CI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_CI_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByOEI_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByOEI_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByOEI_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationCountByOEI_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_C_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByPEN_SI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPEN_SI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTENTRYNAME_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPEN_SI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPEN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByARLU_PI_SI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_PI_SI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_PI_SI_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});

		_finderPathWithPaginationFindByOEI_LET_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByOEI_LET_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.OFFERINGENTRYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.LICENSEENTRYTYPE_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByOEI_LET_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOEI_LET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});

		_finderPathWithPaginationFindByOEI_NotLET_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOEI_NotLET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByOEI_NotLET_C_A = new FinderPath(
			LicenseKeyModelImpl.ENTITY_CACHE_ENABLED,
			LicenseKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByOEI_NotLET_C_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(LicenseKeyImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_LICENSEKEY =
		"SELECT licenseKey FROM LicenseKey licenseKey";

	private static final String _SQL_SELECT_LICENSEKEY_WHERE_PKS_IN =
		"SELECT licenseKey FROM LicenseKey licenseKey WHERE licenseKeyId IN (";

	private static final String _SQL_SELECT_LICENSEKEY_WHERE =
		"SELECT licenseKey FROM LicenseKey licenseKey WHERE ";

	private static final String _SQL_COUNT_LICENSEKEY =
		"SELECT COUNT(licenseKey) FROM LicenseKey licenseKey";

	private static final String _SQL_COUNT_LICENSEKEY_WHERE =
		"SELECT COUNT(licenseKey) FROM LicenseKey licenseKey WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseKey.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LicenseKey exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LicenseKey exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "key", "active"});

}
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

package com.liferay.osb.provisioning.license.service.persistence.impl;

import com.liferay.osb.provisioning.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.model.impl.LicenseKeyImpl;
import com.liferay.osb.provisioning.license.model.impl.LicenseKeyModelImpl;
import com.liferay.osb.provisioning.license.service.persistence.LicenseKeyPersistence;
import com.liferay.osb.provisioning.license.service.persistence.impl.constants.ProvisioningPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

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
@Component(service = LicenseKeyPersistence.class)
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

	private FinderPath _finderPathWithPaginationFindByAccountKey;
	private FinderPath _finderPathWithoutPaginationFindByAccountKey;
	private FinderPath _finderPathCountByAccountKey;

	/**
	 * Returns all the license keies where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByAccountKey(String accountKey) {
		return findByAccountKey(
			accountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end) {

		return findByAccountKey(accountKey, start, end, null);
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
	@Override
	public List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByAccountKey(
			accountKey, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByAccountKey(
		String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		accountKey = Objects.toString(accountKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAccountKey;
				finderArgs = new Object[] {accountKey};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAccountKey;
			finderArgs = new Object[] {
				accountKey, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!accountKey.equals(licenseKey.getAccountKey())) {
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

			boolean bindAccountKey = false;

			if (accountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_3);
			}
			else {
				bindAccountKey = true;

				sb.append(_FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_2);
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

				if (bindAccountKey) {
					queryPos.add(accountKey);
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
	 * Returns the first license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByAccountKey_First(
			String accountKey, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByAccountKey_First(
			accountKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountKey=");
		sb.append(accountKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAccountKey_First(
		String accountKey, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByAccountKey(
			accountKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByAccountKey_Last(
			String accountKey, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByAccountKey_Last(
			accountKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountKey=");
		sb.append(accountKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAccountKey_Last(
		String accountKey, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByAccountKey(accountKey);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByAccountKey(
			accountKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByAccountKey_PrevAndNext(
			long licenseKeyId, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		accountKey = Objects.toString(accountKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByAccountKey_PrevAndNext(
				session, licenseKey, accountKey, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByAccountKey_PrevAndNext(
				session, licenseKey, accountKey, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByAccountKey_PrevAndNext(
		Session session, LicenseKey licenseKey, String accountKey,
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

		boolean bindAccountKey = false;

		if (accountKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_3);
		}
		else {
			bindAccountKey = true;

			sb.append(_FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_2);
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

		if (bindAccountKey) {
			queryPos.add(accountKey);
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
	 * Removes all the license keies where accountKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 */
	@Override
	public void removeByAccountKey(String accountKey) {
		for (LicenseKey licenseKey :
				findByAccountKey(
					accountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the number of matching license keies
	 */
	@Override
	public int countByAccountKey(String accountKey) {
		accountKey = Objects.toString(accountKey, "");

		FinderPath finderPath = _finderPathCountByAccountKey;

		Object[] finderArgs = new Object[] {accountKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAccountKey = false;

			if (accountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_3);
			}
			else {
				bindAccountKey = true;

				sb.append(_FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAccountKey) {
					queryPos.add(accountKey);
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

	private static final String _FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_2 =
		"licenseKey.accountKey = ?";

	private static final String _FINDER_COLUMN_ACCOUNTKEY_ACCOUNTKEY_3 =
		"(licenseKey.accountKey IS NULL OR licenseKey.accountKey = '')";

	private FinderPath _finderPathWithPaginationFindByProductPurchaseKey;
	private FinderPath _finderPathWithoutPaginationFindByProductPurchaseKey;
	private FinderPath _finderPathCountByProductPurchaseKey;

	/**
	 * Returns all the license keies where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey) {

		return findByProductPurchaseKey(
			productPurchaseKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end) {

		return findByProductPurchaseKey(productPurchaseKey, start, end, null);
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
	@Override
	public List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByProductPurchaseKey(
			productPurchaseKey, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByProductPurchaseKey(
		String productPurchaseKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByProductPurchaseKey;
				finderArgs = new Object[] {productPurchaseKey};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByProductPurchaseKey;
			finderArgs = new Object[] {
				productPurchaseKey, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productPurchaseKey.equals(
							licenseKey.getProductPurchaseKey())) {

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

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_2);
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

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
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
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByProductPurchaseKey_First(
			String productPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByProductPurchaseKey_First(
			productPurchaseKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByProductPurchaseKey_First(
		String productPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByProductPurchaseKey(
			productPurchaseKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByProductPurchaseKey_Last(
			String productPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByProductPurchaseKey_Last(
			productPurchaseKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByProductPurchaseKey_Last(
		String productPurchaseKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByProductPurchaseKey(productPurchaseKey);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByProductPurchaseKey(
			productPurchaseKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByProductPurchaseKey_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByProductPurchaseKey_PrevAndNext(
				session, licenseKey, productPurchaseKey, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByProductPurchaseKey_PrevAndNext(
				session, licenseKey, productPurchaseKey, orderByComparator,
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

	protected LicenseKey getByProductPurchaseKey_PrevAndNext(
		Session session, LicenseKey licenseKey, String productPurchaseKey,
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

		boolean bindProductPurchaseKey = false;

		if (productPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_3);
		}
		else {
			bindProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_2);
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

		if (bindProductPurchaseKey) {
			queryPos.add(productPurchaseKey);
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
	 * Removes all the license keies where productPurchaseKey = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 */
	@Override
	public void removeByProductPurchaseKey(String productPurchaseKey) {
		for (LicenseKey licenseKey :
				findByProductPurchaseKey(
					productPurchaseKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the number of matching license keies
	 */
	@Override
	public int countByProductPurchaseKey(String productPurchaseKey) {
		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByProductPurchaseKey;

		Object[] finderArgs = new Object[] {productPurchaseKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
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
		_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_2 =
			"licenseKey.productPurchaseKey = ?";

	private static final String
		_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_3 =
			"(licenseKey.productPurchaseKey IS NULL OR licenseKey.productPurchaseKey = '')";

	private FinderPath _finderPathWithPaginationFindByU_AK;
	private FinderPath _finderPathWithoutPaginationFindByU_AK;
	private FinderPath _finderPathCountByU_AK;

	/**
	 * Returns all the license keies where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_AK(String userUuid, String accountKey) {
		return findByU_AK(
			userUuid, accountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<LicenseKey> findByU_AK(
		String userUuid, String accountKey, int start, int end) {

		return findByU_AK(userUuid, accountKey, start, end, null);
	}

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
	@Override
	public List<LicenseKey> findByU_AK(
		String userUuid, String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByU_AK(
			userUuid, accountKey, start, end, orderByComparator, true);
	}

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
	@Override
	public List<LicenseKey> findByU_AK(
		String userUuid, String accountKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		userUuid = Objects.toString(userUuid, "");
		accountKey = Objects.toString(accountKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_AK;
				finderArgs = new Object[] {userUuid, accountKey};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_AK;
			finderArgs = new Object[] {
				userUuid, accountKey, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!userUuid.equals(licenseKey.getUserUuid()) ||
						!accountKey.equals(licenseKey.getAccountKey())) {

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

			boolean bindUserUuid = false;

			if (userUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_AK_USERUUID_3);
			}
			else {
				bindUserUuid = true;

				sb.append(_FINDER_COLUMN_U_AK_USERUUID_2);
			}

			boolean bindAccountKey = false;

			if (accountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_AK_ACCOUNTKEY_3);
			}
			else {
				bindAccountKey = true;

				sb.append(_FINDER_COLUMN_U_AK_ACCOUNTKEY_2);
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

				if (bindUserUuid) {
					queryPos.add(userUuid);
				}

				if (bindAccountKey) {
					queryPos.add(accountKey);
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
	 * Returns the first license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_AK_First(
			String userUuid, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_AK_First(
			userUuid, accountKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userUuid=");
		sb.append(userUuid);

		sb.append(", accountKey=");
		sb.append(accountKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_AK_First(
		String userUuid, String accountKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByU_AK(
			userUuid, accountKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_AK_Last(
			String userUuid, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_AK_Last(
			userUuid, accountKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userUuid=");
		sb.append(userUuid);

		sb.append(", accountKey=");
		sb.append(accountKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_AK_Last(
		String userUuid, String accountKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByU_AK(userUuid, accountKey);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByU_AK(
			userUuid, accountKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public LicenseKey[] findByU_AK_PrevAndNext(
			long licenseKeyId, String userUuid, String accountKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		userUuid = Objects.toString(userUuid, "");
		accountKey = Objects.toString(accountKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_AK_PrevAndNext(
				session, licenseKey, userUuid, accountKey, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByU_AK_PrevAndNext(
				session, licenseKey, userUuid, accountKey, orderByComparator,
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

	protected LicenseKey getByU_AK_PrevAndNext(
		Session session, LicenseKey licenseKey, String userUuid,
		String accountKey, OrderByComparator<LicenseKey> orderByComparator,
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

		boolean bindUserUuid = false;

		if (userUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_U_AK_USERUUID_3);
		}
		else {
			bindUserUuid = true;

			sb.append(_FINDER_COLUMN_U_AK_USERUUID_2);
		}

		boolean bindAccountKey = false;

		if (accountKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_U_AK_ACCOUNTKEY_3);
		}
		else {
			bindAccountKey = true;

			sb.append(_FINDER_COLUMN_U_AK_ACCOUNTKEY_2);
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

		if (bindUserUuid) {
			queryPos.add(userUuid);
		}

		if (bindAccountKey) {
			queryPos.add(accountKey);
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
	 * Removes all the license keies where userUuid = &#63; and accountKey = &#63; from the database.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 */
	@Override
	public void removeByU_AK(String userUuid, String accountKey) {
		for (LicenseKey licenseKey :
				findByU_AK(
					userUuid, accountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where userUuid = &#63; and accountKey = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param accountKey the account key
	 * @return the number of matching license keies
	 */
	@Override
	public int countByU_AK(String userUuid, String accountKey) {
		userUuid = Objects.toString(userUuid, "");
		accountKey = Objects.toString(accountKey, "");

		FinderPath finderPath = _finderPathCountByU_AK;

		Object[] finderArgs = new Object[] {userUuid, accountKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindUserUuid = false;

			if (userUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_AK_USERUUID_3);
			}
			else {
				bindUserUuid = true;

				sb.append(_FINDER_COLUMN_U_AK_USERUUID_2);
			}

			boolean bindAccountKey = false;

			if (accountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_AK_ACCOUNTKEY_3);
			}
			else {
				bindAccountKey = true;

				sb.append(_FINDER_COLUMN_U_AK_ACCOUNTKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUserUuid) {
					queryPos.add(userUuid);
				}

				if (bindAccountKey) {
					queryPos.add(accountKey);
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

	private static final String _FINDER_COLUMN_U_AK_USERUUID_2 =
		"licenseKey.userUuid = ? AND ";

	private static final String _FINDER_COLUMN_U_AK_USERUUID_3 =
		"(licenseKey.userUuid IS NULL OR licenseKey.userUuid = '') AND ";

	private static final String _FINDER_COLUMN_U_AK_ACCOUNTKEY_2 =
		"licenseKey.accountKey = ?";

	private static final String _FINDER_COLUMN_U_AK_ACCOUNTKEY_3 =
		"(licenseKey.accountKey IS NULL OR licenseKey.accountKey = '')";

	private FinderPath _finderPathWithPaginationFindByU_PI;
	private FinderPath _finderPathWithoutPaginationFindByU_PI;
	private FinderPath _finderPathCountByU_PI;

	/**
	 * Returns all the license keies where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByU_PI(String userUuid, String productId) {
		return findByU_PI(
			userUuid, productId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<LicenseKey> findByU_PI(
		String userUuid, String productId, int start, int end) {

		return findByU_PI(userUuid, productId, start, end, null);
	}

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
	@Override
	public List<LicenseKey> findByU_PI(
		String userUuid, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByU_PI(
			userUuid, productId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<LicenseKey> findByU_PI(
		String userUuid, String productId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		userUuid = Objects.toString(userUuid, "");
		productId = Objects.toString(productId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_PI;
				finderArgs = new Object[] {userUuid, productId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_PI;
			finderArgs = new Object[] {
				userUuid, productId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!userUuid.equals(licenseKey.getUserUuid()) ||
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

			boolean bindUserUuid = false;

			if (userUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_PI_USERUUID_3);
			}
			else {
				bindUserUuid = true;

				sb.append(_FINDER_COLUMN_U_PI_USERUUID_2);
			}

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

				if (bindUserUuid) {
					queryPos.add(userUuid);
				}

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
	 * Returns the first license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_PI_First(
			String userUuid, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_PI_First(
			userUuid, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userUuid=");
		sb.append(userUuid);

		sb.append(", productId=");
		sb.append(productId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_PI_First(
		String userUuid, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByU_PI(
			userUuid, productId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByU_PI_Last(
			String userUuid, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByU_PI_Last(
			userUuid, productId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userUuid=");
		sb.append(userUuid);

		sb.append(", productId=");
		sb.append(productId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByU_PI_Last(
		String userUuid, String productId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByU_PI(userUuid, productId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByU_PI(
			userUuid, productId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public LicenseKey[] findByU_PI_PrevAndNext(
			long licenseKeyId, String userUuid, String productId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		userUuid = Objects.toString(userUuid, "");
		productId = Objects.toString(productId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByU_PI_PrevAndNext(
				session, licenseKey, userUuid, productId, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByU_PI_PrevAndNext(
				session, licenseKey, userUuid, productId, orderByComparator,
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
		Session session, LicenseKey licenseKey, String userUuid,
		String productId, OrderByComparator<LicenseKey> orderByComparator,
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

		boolean bindUserUuid = false;

		if (userUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_U_PI_USERUUID_3);
		}
		else {
			bindUserUuid = true;

			sb.append(_FINDER_COLUMN_U_PI_USERUUID_2);
		}

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

		if (bindUserUuid) {
			queryPos.add(userUuid);
		}

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
	 * Removes all the license keies where userUuid = &#63; and productId = &#63; from the database.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 */
	@Override
	public void removeByU_PI(String userUuid, String productId) {
		for (LicenseKey licenseKey :
				findByU_PI(
					userUuid, productId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where userUuid = &#63; and productId = &#63;.
	 *
	 * @param userUuid the user uuid
	 * @param productId the product ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByU_PI(String userUuid, String productId) {
		userUuid = Objects.toString(userUuid, "");
		productId = Objects.toString(productId, "");

		FinderPath finderPath = _finderPathCountByU_PI;

		Object[] finderArgs = new Object[] {userUuid, productId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindUserUuid = false;

			if (userUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_U_PI_USERUUID_3);
			}
			else {
				bindUserUuid = true;

				sb.append(_FINDER_COLUMN_U_PI_USERUUID_2);
			}

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

				if (bindUserUuid) {
					queryPos.add(userUuid);
				}

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

	private static final String _FINDER_COLUMN_U_PI_USERUUID_2 =
		"licenseKey.userUuid = ? AND ";

	private static final String _FINDER_COLUMN_U_PI_USERUUID_3 =
		"(licenseKey.userUuid IS NULL OR licenseKey.userUuid = '') AND ";

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

	private FinderPath _finderPathWithPaginationFindByAK_PK;
	private FinderPath _finderPathWithoutPaginationFindByAK_PK;
	private FinderPath _finderPathCountByAK_PK;

	/**
	 * Returns all the license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByAK_PK(String accountKey, String productKey) {
		return findByAK_PK(
			accountKey, productKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end) {

		return findByAK_PK(accountKey, productKey, start, end, null);
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
	@Override
	public List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByAK_PK(
			accountKey, productKey, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByAK_PK(
		String accountKey, String productKey, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		accountKey = Objects.toString(accountKey, "");
		productKey = Objects.toString(productKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAK_PK;
				finderArgs = new Object[] {accountKey, productKey};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAK_PK;
			finderArgs = new Object[] {
				accountKey, productKey, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!accountKey.equals(licenseKey.getAccountKey()) ||
						!productKey.equals(licenseKey.getProductKey())) {

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

			boolean bindAccountKey = false;

			if (accountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_AK_PK_ACCOUNTKEY_3);
			}
			else {
				bindAccountKey = true;

				sb.append(_FINDER_COLUMN_AK_PK_ACCOUNTKEY_2);
			}

			boolean bindProductKey = false;

			if (productKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_AK_PK_PRODUCTKEY_3);
			}
			else {
				bindProductKey = true;

				sb.append(_FINDER_COLUMN_AK_PK_PRODUCTKEY_2);
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

				if (bindAccountKey) {
					queryPos.add(accountKey);
				}

				if (bindProductKey) {
					queryPos.add(productKey);
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
	 * Returns the first license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByAK_PK_First(
			String accountKey, String productKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByAK_PK_First(
			accountKey, productKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountKey=");
		sb.append(accountKey);

		sb.append(", productKey=");
		sb.append(productKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAK_PK_First(
		String accountKey, String productKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByAK_PK(
			accountKey, productKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByAK_PK_Last(
			String accountKey, String productKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByAK_PK_Last(
			accountKey, productKey, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountKey=");
		sb.append(accountKey);

		sb.append(", productKey=");
		sb.append(productKey);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByAK_PK_Last(
		String accountKey, String productKey,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByAK_PK(accountKey, productKey);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByAK_PK(
			accountKey, productKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByAK_PK_PrevAndNext(
			long licenseKeyId, String accountKey, String productKey,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		accountKey = Objects.toString(accountKey, "");
		productKey = Objects.toString(productKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByAK_PK_PrevAndNext(
				session, licenseKey, accountKey, productKey, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByAK_PK_PrevAndNext(
				session, licenseKey, accountKey, productKey, orderByComparator,
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

	protected LicenseKey getByAK_PK_PrevAndNext(
		Session session, LicenseKey licenseKey, String accountKey,
		String productKey, OrderByComparator<LicenseKey> orderByComparator,
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

		boolean bindAccountKey = false;

		if (accountKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_AK_PK_ACCOUNTKEY_3);
		}
		else {
			bindAccountKey = true;

			sb.append(_FINDER_COLUMN_AK_PK_ACCOUNTKEY_2);
		}

		boolean bindProductKey = false;

		if (productKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_AK_PK_PRODUCTKEY_3);
		}
		else {
			bindProductKey = true;

			sb.append(_FINDER_COLUMN_AK_PK_PRODUCTKEY_2);
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

		if (bindAccountKey) {
			queryPos.add(accountKey);
		}

		if (bindProductKey) {
			queryPos.add(productKey);
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
	 * Removes all the license keies where accountKey = &#63; and productKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 */
	@Override
	public void removeByAK_PK(String accountKey, String productKey) {
		for (LicenseKey licenseKey :
				findByAK_PK(
					accountKey, productKey, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where accountKey = &#63; and productKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @param productKey the product key
	 * @return the number of matching license keies
	 */
	@Override
	public int countByAK_PK(String accountKey, String productKey) {
		accountKey = Objects.toString(accountKey, "");
		productKey = Objects.toString(productKey, "");

		FinderPath finderPath = _finderPathCountByAK_PK;

		Object[] finderArgs = new Object[] {accountKey, productKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAccountKey = false;

			if (accountKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_AK_PK_ACCOUNTKEY_3);
			}
			else {
				bindAccountKey = true;

				sb.append(_FINDER_COLUMN_AK_PK_ACCOUNTKEY_2);
			}

			boolean bindProductKey = false;

			if (productKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_AK_PK_PRODUCTKEY_3);
			}
			else {
				bindProductKey = true;

				sb.append(_FINDER_COLUMN_AK_PK_PRODUCTKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAccountKey) {
					queryPos.add(accountKey);
				}

				if (bindProductKey) {
					queryPos.add(productKey);
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

	private static final String _FINDER_COLUMN_AK_PK_ACCOUNTKEY_2 =
		"licenseKey.accountKey = ? AND ";

	private static final String _FINDER_COLUMN_AK_PK_ACCOUNTKEY_3 =
		"(licenseKey.accountKey IS NULL OR licenseKey.accountKey = '') AND ";

	private static final String _FINDER_COLUMN_AK_PK_PRODUCTKEY_2 =
		"licenseKey.productKey = ?";

	private static final String _FINDER_COLUMN_AK_PK_PRODUCTKEY_3 =
		"(licenseKey.productKey IS NULL OR licenseKey.productKey = '')";

	private FinderPath _finderPathWithPaginationFindByPPK_CI;
	private FinderPath _finderPathWithoutPaginationFindByPPK_CI;
	private FinderPath _finderPathCountByPPK_CI;

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId) {

		return findByPPK_CI(
			productPurchaseKey, clusterId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end) {

		return findByPPK_CI(productPurchaseKey, clusterId, start, end, null);
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
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPPK_CI(
			productPurchaseKey, clusterId, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPPK_CI;
				finderArgs = new Object[] {productPurchaseKey, clusterId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPPK_CI;
			finderArgs = new Object[] {
				productPurchaseKey, clusterId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productPurchaseKey.equals(
							licenseKey.getProductPurchaseKey()) ||
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

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_CI_CLUSTERID_2);

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

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
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
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPPK_CI_First(
			String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_CI_First(
			productPurchaseKey, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_CI_First(
		String productPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPPK_CI(
			productPurchaseKey, clusterId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPPK_CI_Last(
			String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_CI_Last(
			productPurchaseKey, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_CI_Last(
		String productPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPPK_CI(productPurchaseKey, clusterId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPPK_CI(
			productPurchaseKey, clusterId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPPK_CI_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPPK_CI_PrevAndNext(
				session, licenseKey, productPurchaseKey, clusterId,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPPK_CI_PrevAndNext(
				session, licenseKey, productPurchaseKey, clusterId,
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

	protected LicenseKey getByPPK_CI_PrevAndNext(
		Session session, LicenseKey licenseKey, String productPurchaseKey,
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

		boolean bindProductPurchaseKey = false;

		if (productPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3);
		}
		else {
			bindProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2);
		}

		sb.append(_FINDER_COLUMN_PPK_CI_CLUSTERID_2);

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

		if (bindProductPurchaseKey) {
			queryPos.add(productPurchaseKey);
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
	 * Removes all the license keies where productPurchaseKey = &#63; and clusterId = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 */
	@Override
	public void removeByPPK_CI(String productPurchaseKey, long clusterId) {
		for (LicenseKey licenseKey :
				findByPPK_CI(
					productPurchaseKey, clusterId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPPK_CI(String productPurchaseKey, long clusterId) {
		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByPPK_CI;

		Object[] finderArgs = new Object[] {productPurchaseKey, clusterId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_CI_CLUSTERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
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

	private static final String _FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2 =
		"licenseKey.productPurchaseKey = ? AND ";

	private static final String _FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3 =
		"(licenseKey.productPurchaseKey IS NULL OR licenseKey.productPurchaseKey = '') AND ";

	private static final String _FINDER_COLUMN_PPK_CI_CLUSTERID_2 =
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

	private FinderPath _finderPathWithPaginationFindByPPK_CI_A;
	private FinderPath _finderPathWithoutPaginationFindByPPK_CI_A;
	private FinderPath _finderPathCountByPPK_CI_A;

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active) {

		return findByPPK_CI_A(
			productPurchaseKey, clusterId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end) {

		return findByPPK_CI_A(
			productPurchaseKey, clusterId, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByPPK_CI_A(
			productPurchaseKey, clusterId, active, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPPK_CI_A;
				finderArgs = new Object[] {
					productPurchaseKey, clusterId, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPPK_CI_A;
			finderArgs = new Object[] {
				productPurchaseKey, clusterId, active, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productPurchaseKey.equals(
							licenseKey.getProductPurchaseKey()) ||
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

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_CI_A_CLUSTERID_2);

			sb.append(_FINDER_COLUMN_PPK_CI_A_ACTIVE_2);

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

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

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
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPPK_CI_A_First(
			String productPurchaseKey, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_CI_A_First(
			productPurchaseKey, clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPPK_CI_A_First(
		String productPurchaseKey, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPPK_CI_A(
			productPurchaseKey, clusterId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPPK_CI_A_Last(
			String productPurchaseKey, long clusterId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_CI_A_Last(
			productPurchaseKey, clusterId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPPK_CI_A_Last(
		String productPurchaseKey, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPPK_CI_A(productPurchaseKey, clusterId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPPK_CI_A(
			productPurchaseKey, clusterId, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPPK_CI_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, long clusterId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPPK_CI_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, clusterId, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPPK_CI_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, clusterId, active,
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

	protected LicenseKey getByPPK_CI_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String productPurchaseKey,
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

		boolean bindProductPurchaseKey = false;

		if (productPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_3);
		}
		else {
			bindProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_2);
		}

		sb.append(_FINDER_COLUMN_PPK_CI_A_CLUSTERID_2);

		sb.append(_FINDER_COLUMN_PPK_CI_A_ACTIVE_2);

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

		if (bindProductPurchaseKey) {
			queryPos.add(productPurchaseKey);
		}

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
	 * Removes all the license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 */
	@Override
	public void removeByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active) {

		for (LicenseKey licenseKey :
				findByPPK_CI_A(
					productPurchaseKey, clusterId, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and clusterId = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPPK_CI_A(
		String productPurchaseKey, long clusterId, boolean active) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByPPK_CI_A;

		Object[] finderArgs = new Object[] {
			productPurchaseKey, clusterId, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_CI_A_CLUSTERID_2);

			sb.append(_FINDER_COLUMN_PPK_CI_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

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

	private static final String _FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_2 =
		"licenseKey.productPurchaseKey = ? AND ";

	private static final String _FINDER_COLUMN_PPK_CI_A_PRODUCTPURCHASEKEY_3 =
		"(licenseKey.productPurchaseKey IS NULL OR licenseKey.productPurchaseKey = '') AND ";

	private static final String _FINDER_COLUMN_PPK_CI_A_CLUSTERID_2 =
		"licenseKey.clusterId = ? AND ";

	private static final String _FINDER_COLUMN_PPK_CI_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByPPK_C_A;
	private FinderPath _finderPathWithoutPaginationFindByPPK_C_A;
	private FinderPath _finderPathCountByPPK_C_A;
	private FinderPath _finderPathWithPaginationCountByPPK_C_A;

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active) {

		return findByPPK_C_A(
			productPurchaseKey, complimentary, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end) {

		return findByPPK_C_A(
			productPurchaseKey, complimentary, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByPPK_C_A(
			productPurchaseKey, complimentary, active, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPPK_C_A;
				finderArgs = new Object[] {
					productPurchaseKey, complimentary, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPPK_C_A;
			finderArgs = new Object[] {
				productPurchaseKey, complimentary, active, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productPurchaseKey.equals(
							licenseKey.getProductPurchaseKey()) ||
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

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_C_A_ACTIVE_2);

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

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
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
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPPK_C_A_First(
			String productPurchaseKey, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_C_A_First(
			productPurchaseKey, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPPK_C_A_First(
		String productPurchaseKey, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPPK_C_A(
			productPurchaseKey, complimentary, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPPK_C_A_Last(
			String productPurchaseKey, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_C_A_Last(
			productPurchaseKey, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPPK_C_A_Last(
		String productPurchaseKey, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPPK_C_A(productPurchaseKey, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPPK_C_A(
			productPurchaseKey, complimentary, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPPK_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPPK_C_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, complimentary, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPPK_C_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, complimentary, active,
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

	protected LicenseKey getByPPK_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String productPurchaseKey,
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

		boolean bindProductPurchaseKey = false;

		if (productPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_3);
		}
		else {
			bindProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_2);
		}

		sb.append(_FINDER_COLUMN_PPK_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_PPK_C_A_ACTIVE_2);

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

		if (bindProductPurchaseKey) {
			queryPos.add(productPurchaseKey);
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
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active) {

		return findByPPK_C_A(
			productPurchaseKeies, complimentary, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end) {

		return findByPPK_C_A(
			productPurchaseKeies, complimentary, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByPPK_C_A(
			productPurchaseKeies, complimentary, active, start, end,
			orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		if (productPurchaseKeies == null) {
			productPurchaseKeies = new String[0];
		}
		else if (productPurchaseKeies.length > 1) {
			for (int i = 0; i < productPurchaseKeies.length; i++) {
				productPurchaseKeies[i] = Objects.toString(
					productPurchaseKeies[i], "");
			}

			productPurchaseKeies = ArrayUtil.sortedUnique(productPurchaseKeies);
		}

		if (productPurchaseKeies.length == 1) {
			return findByPPK_C_A(
				productPurchaseKeies[0], complimentary, active, start, end,
				orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					StringUtil.merge(productPurchaseKeies), complimentary,
					active
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				StringUtil.merge(productPurchaseKeies), complimentary, active,
				start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				_finderPathWithPaginationFindByPPK_C_A, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!ArrayUtil.contains(
							productPurchaseKeies,
							licenseKey.getProductPurchaseKey()) ||
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

			if (productPurchaseKeies.length > 0) {
				sb.append("(");

				for (int i = 0; i < productPurchaseKeies.length; i++) {
					String productPurchaseKey = productPurchaseKeies[i];

					if (productPurchaseKey.isEmpty()) {
						sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_6);
					}
					else {
						sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_5);
					}

					if ((i + 1) < productPurchaseKeies.length) {
						sb.append(WHERE_OR);
					}
				}

				sb.append(")");

				sb.append(WHERE_AND);
			}

			sb.append(_FINDER_COLUMN_PPK_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_C_A_ACTIVE_2);

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

				for (String productPurchaseKey : productPurchaseKeies) {
					if ((productPurchaseKey != null) &&
						!productPurchaseKey.isEmpty()) {

						queryPos.add(productPurchaseKey);
					}
				}

				queryPos.add(complimentary);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByPPK_C_A, finderArgs,
						list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByPPK_C_A, finderArgs);
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
	 * Removes all the license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active) {

		for (LicenseKey licenseKey :
				findByPPK_C_A(
					productPurchaseKey, complimentary, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPPK_C_A(
		String productPurchaseKey, boolean complimentary, boolean active) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByPPK_C_A;

		Object[] finderArgs = new Object[] {
			productPurchaseKey, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
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

	/**
	 * Returns the number of license keies where productPurchaseKey = any &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKeies the product purchase keies
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPPK_C_A(
		String[] productPurchaseKeies, boolean complimentary, boolean active) {

		if (productPurchaseKeies == null) {
			productPurchaseKeies = new String[0];
		}
		else if (productPurchaseKeies.length > 1) {
			for (int i = 0; i < productPurchaseKeies.length; i++) {
				productPurchaseKeies[i] = Objects.toString(
					productPurchaseKeies[i], "");
			}

			productPurchaseKeies = ArrayUtil.sortedUnique(productPurchaseKeies);
		}

		Object[] finderArgs = new Object[] {
			StringUtil.merge(productPurchaseKeies), complimentary, active
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByPPK_C_A, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			if (productPurchaseKeies.length > 0) {
				sb.append("(");

				for (int i = 0; i < productPurchaseKeies.length; i++) {
					String productPurchaseKey = productPurchaseKeies[i];

					if (productPurchaseKey.isEmpty()) {
						sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_6);
					}
					else {
						sb.append(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_5);
					}

					if ((i + 1) < productPurchaseKeies.length) {
						sb.append(WHERE_OR);
					}
				}

				sb.append(")");

				sb.append(WHERE_AND);
			}

			sb.append(_FINDER_COLUMN_PPK_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_C_A_ACTIVE_2);

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				for (String productPurchaseKey : productPurchaseKeies) {
					if ((productPurchaseKey != null) &&
						!productPurchaseKey.isEmpty()) {

						queryPos.add(productPurchaseKey);
					}
				}

				queryPos.add(complimentary);

				queryPos.add(active);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByPPK_C_A, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByPPK_C_A, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_2 =
		"licenseKey.productPurchaseKey = ? AND ";

	private static final String _FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_3 =
		"(licenseKey.productPurchaseKey IS NULL OR licenseKey.productPurchaseKey = '') AND ";

	private static final String _FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_5 =
		"(" + removeConjunction(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_2) +
			")";

	private static final String _FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_6 =
		"(" + removeConjunction(_FINDER_COLUMN_PPK_C_A_PRODUCTPURCHASEKEY_3) +
			")";

	private static final String _FINDER_COLUMN_PPK_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_PPK_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByPN_SI_A;
	private FinderPath _finderPathWithoutPaginationFindByPN_SI_A;
	private FinderPath _finderPathCountByPN_SI_A;

	/**
	 * Returns all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active) {

		return findByPN_SI_A(
			productName, serverId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start,
		int end) {

		return findByPN_SI_A(productName, serverId, active, start, end, null);
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
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPN_SI_A(
			productName, serverId, active, start, end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productName = Objects.toString(productName, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPN_SI_A;
				finderArgs = new Object[] {productName, serverId, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPN_SI_A;
			finderArgs = new Object[] {
				productName, serverId, active, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productName.equals(licenseKey.getProductName()) ||
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

			boolean bindProductName = false;

			if (productName.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3);
			}
			else {
				bindProductName = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_PN_SI_A_ACTIVE_2);

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

				if (bindProductName) {
					queryPos.add(productName);
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
	 * Returns the first license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPN_SI_A_First(
			String productName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPN_SI_A_First(
			productName, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productName=");
		sb.append(productName);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPN_SI_A_First(
		String productName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPN_SI_A(
			productName, serverId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPN_SI_A_Last(
			String productName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPN_SI_A_Last(
			productName, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productName=");
		sb.append(productName);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
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
	@Override
	public LicenseKey fetchByPN_SI_A_Last(
		String productName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPN_SI_A(productName, serverId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPN_SI_A(
			productName, serverId, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPN_SI_A_PrevAndNext(
			long licenseKeyId, String productName, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productName = Objects.toString(productName, "");
		serverId = Objects.toString(serverId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPN_SI_A_PrevAndNext(
				session, licenseKey, productName, serverId, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPN_SI_A_PrevAndNext(
				session, licenseKey, productName, serverId, active,
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

	protected LicenseKey getByPN_SI_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String productName,
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

		boolean bindProductName = false;

		if (productName.isEmpty()) {
			sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3);
		}
		else {
			bindProductName = true;

			sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2);
		}

		boolean bindServerId = false;

		if (serverId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_3);
		}
		else {
			bindServerId = true;

			sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_2);
		}

		sb.append(_FINDER_COLUMN_PN_SI_A_ACTIVE_2);

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

		if (bindProductName) {
			queryPos.add(productName);
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
	 * Removes all the license keies where productName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 */
	@Override
	public void removeByPN_SI_A(
		String productName, String serverId, boolean active) {

		for (LicenseKey licenseKey :
				findByPN_SI_A(
					productName, serverId, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPN_SI_A(
		String productName, String serverId, boolean active) {

		productName = Objects.toString(productName, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = _finderPathCountByPN_SI_A;

		Object[] finderArgs = new Object[] {productName, serverId, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductName = false;

			if (productName.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3);
			}
			else {
				bindProductName = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_PN_SI_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductName) {
					queryPos.add(productName);
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

	private static final String _FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2 =
		"licenseKey.productName = ? AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3 =
		"(licenseKey.productName IS NULL OR licenseKey.productName = '') AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_SERVERID_2 =
		"licenseKey.serverId = ? AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_SERVERID_3 =
		"(licenseKey.serverId IS NULL OR licenseKey.serverId = '') AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_ACTIVE_2 =
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

	private FinderPath _finderPathWithPaginationFindByPPK_LET_C_A;
	private FinderPath _finderPathWithoutPaginationFindByPPK_LET_C_A;
	private FinderPath _finderPathCountByPPK_LET_C_A;

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		return findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end) {

		return findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, null);
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
	@Override
	public List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");
		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPPK_LET_C_A;
				finderArgs = new Object[] {
					productPurchaseKey, licenseEntryType, complimentary, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPPK_LET_C_A;
			finderArgs = new Object[] {
				productPurchaseKey, licenseEntryType, complimentary, active,
				start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productPurchaseKey.equals(
							licenseKey.getProductPurchaseKey()) ||
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

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_2);
			}

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_PPK_LET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_LET_C_A_ACTIVE_2);

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

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

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
	@Override
	public LicenseKey findByPPK_LET_C_A_First(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_LET_C_A_First(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

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
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_LET_C_A_First(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPPK_LET_C_A_Last(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_LET_C_A_Last(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

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
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_LET_C_A_Last(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPPK_LET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active,
			count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPPK_LET_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			String licenseEntryType, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");
		licenseEntryType = Objects.toString(licenseEntryType, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPPK_LET_C_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, licenseEntryType,
				complimentary, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPPK_LET_C_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, licenseEntryType,
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

	protected LicenseKey getByPPK_LET_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String productPurchaseKey,
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

		boolean bindProductPurchaseKey = false;

		if (productPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_3);
		}
		else {
			bindProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_2);
		}

		boolean bindLicenseEntryType = false;

		if (licenseEntryType.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_3);
		}
		else {
			bindLicenseEntryType = true;

			sb.append(_FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_2);
		}

		sb.append(_FINDER_COLUMN_PPK_LET_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_PPK_LET_C_A_ACTIVE_2);

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

		if (bindProductPurchaseKey) {
			queryPos.add(productPurchaseKey);
		}

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
	 * Removes all the license keies where productPurchaseKey = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		for (LicenseKey licenseKey :
				findByPPK_LET_C_A(
					productPurchaseKey, licenseEntryType, complimentary, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
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
	@Override
	public int countByPPK_LET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");
		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = _finderPathCountByPPK_LET_C_A;

		Object[] finderArgs = new Object[] {
			productPurchaseKey, licenseEntryType, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_2);
			}

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_PPK_LET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_LET_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

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
		_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_2 =
			"licenseKey.productPurchaseKey = ? AND ";

	private static final String
		_FINDER_COLUMN_PPK_LET_C_A_PRODUCTPURCHASEKEY_3 =
			"(licenseKey.productPurchaseKey IS NULL OR licenseKey.productPurchaseKey = '') AND ";

	private static final String _FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_2 =
		"licenseKey.licenseEntryType = ? AND ";

	private static final String _FINDER_COLUMN_PPK_LET_C_A_LICENSEENTRYTYPE_3 =
		"(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType = '') AND ";

	private static final String _FINDER_COLUMN_PPK_LET_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_PPK_LET_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByPPK_NotLET_C_A;
	private FinderPath _finderPathWithPaginationCountByPPK_NotLET_C_A;

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		return findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end) {

		return findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, null);
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
	@Override
	public List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, orderByComparator, true);
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
	@Override
	public List<LicenseKey> findByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");
		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByPPK_NotLET_C_A;
		finderArgs = new Object[] {
			productPurchaseKey, licenseEntryType, complimentary, active, start,
			end, orderByComparator
		};

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productPurchaseKey.equals(
							licenseKey.getProductPurchaseKey()) ||
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

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_2);
			}

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_ACTIVE_2);

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

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

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
	@Override
	public LicenseKey findByPPK_NotLET_C_A_First(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_NotLET_C_A_First(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

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
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_NotLET_C_A_First(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey findByPPK_NotLET_C_A_Last(
			String productPurchaseKey, String licenseEntryType,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_NotLET_C_A_Last(
			productPurchaseKey, licenseEntryType, complimentary, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

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
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_NotLET_C_A_Last(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPPK_NotLET_C_A(
			productPurchaseKey, licenseEntryType, complimentary, active,
			count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public LicenseKey[] findByPPK_NotLET_C_A_PrevAndNext(
			long licenseKeyId, String productPurchaseKey,
			String licenseEntryType, boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");
		licenseEntryType = Objects.toString(licenseEntryType, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPPK_NotLET_C_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, licenseEntryType,
				complimentary, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPPK_NotLET_C_A_PrevAndNext(
				session, licenseKey, productPurchaseKey, licenseEntryType,
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

	protected LicenseKey getByPPK_NotLET_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String productPurchaseKey,
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

		boolean bindProductPurchaseKey = false;

		if (productPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_3);
		}
		else {
			bindProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_2);
		}

		boolean bindLicenseEntryType = false;

		if (licenseEntryType.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_3);
		}
		else {
			bindLicenseEntryType = true;

			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_2);
		}

		sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_ACTIVE_2);

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

		if (bindProductPurchaseKey) {
			queryPos.add(productPurchaseKey);
		}

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
	 * Removes all the license keies where productPurchaseKey = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param licenseEntryType the license entry type
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		for (LicenseKey licenseKey :
				findByPPK_NotLET_C_A(
					productPurchaseKey, licenseEntryType, complimentary, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
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
	@Override
	public int countByPPK_NotLET_C_A(
		String productPurchaseKey, String licenseEntryType,
		boolean complimentary, boolean active) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");
		licenseEntryType = Objects.toString(licenseEntryType, "");

		FinderPath finderPath = _finderPathWithPaginationCountByPPK_NotLET_C_A;

		Object[] finderArgs = new Object[] {
			productPurchaseKey, licenseEntryType, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_2);
			}

			boolean bindLicenseEntryType = false;

			if (licenseEntryType.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_3);
			}
			else {
				bindLicenseEntryType = true;

				sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_2);
			}

			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_PPK_NOTLET_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

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
		_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_2 =
			"licenseKey.productPurchaseKey = ? AND ";

	private static final String
		_FINDER_COLUMN_PPK_NOTLET_C_A_PRODUCTPURCHASEKEY_3 =
			"(licenseKey.productPurchaseKey IS NULL OR licenseKey.productPurchaseKey = '') AND ";

	private static final String
		_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_2 =
			"licenseKey.licenseEntryType != ? AND ";

	private static final String
		_FINDER_COLUMN_PPK_NOTLET_C_A_LICENSEENTRYTYPE_3 =
			"(licenseKey.licenseEntryType IS NULL OR licenseKey.licenseEntryType != '') AND ";

	private static final String _FINDER_COLUMN_PPK_NOTLET_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_PPK_NOTLET_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	public LicenseKeyPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("key", "key_");
		dbColumnNames.put("active", "active_");

		setDBColumnNames(dbColumnNames);

		setModelClass(LicenseKey.class);

		setModelImplClass(LicenseKeyImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the license key in the entity cache if it is enabled.
	 *
	 * @param licenseKey the license key
	 */
	@Override
	public void cacheResult(LicenseKey licenseKey) {
		entityCache.putResult(
			entityCacheEnabled, LicenseKeyImpl.class,
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
					entityCacheEnabled, LicenseKeyImpl.class,
					licenseKey.getPrimaryKey()) == null) {

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
			entityCacheEnabled, LicenseKeyImpl.class,
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
				entityCacheEnabled, LicenseKeyImpl.class,
				licenseKey.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, LicenseKeyImpl.class, primaryKey);
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

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {licenseKeyModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {licenseKeyModelImpl.getAccountKey()};

			finderCache.removeResult(_finderPathCountByAccountKey, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountKey, args);

			args = new Object[] {licenseKeyModelImpl.getProductPurchaseKey()};

			finderCache.removeResult(
				_finderPathCountByProductPurchaseKey, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductPurchaseKey, args);

			args = new Object[] {
				licenseKeyModelImpl.getUserUuid(),
				licenseKeyModelImpl.getAccountKey()
			};

			finderCache.removeResult(_finderPathCountByU_AK, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_AK, args);

			args = new Object[] {
				licenseKeyModelImpl.getUserUuid(),
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
				licenseKeyModelImpl.getAccountKey(),
				licenseKeyModelImpl.getProductKey()
			};

			finderCache.removeResult(_finderPathCountByAK_PK, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAK_PK, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductPurchaseKey(),
				licenseKeyModelImpl.getClusterId()
			};

			finderCache.removeResult(_finderPathCountByPPK_CI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPPK_CI, args);

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
				licenseKeyModelImpl.getProductPurchaseKey(),
				licenseKeyModelImpl.getClusterId(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByPPK_CI_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPPK_CI_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductPurchaseKey(),
				licenseKeyModelImpl.isComplimentary(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByPPK_C_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPPK_C_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductName(),
				licenseKeyModelImpl.getServerId(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByPN_SI_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPN_SI_A, args);

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
				licenseKeyModelImpl.getProductPurchaseKey(),
				licenseKeyModelImpl.getLicenseEntryType(),
				licenseKeyModelImpl.isComplimentary(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByPPK_LET_C_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPPK_LET_C_A, args);

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
				 _finderPathWithoutPaginationFindByAccountKey.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAccountKey()
				};

				finderCache.removeResult(_finderPathCountByAccountKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountKey, args);

				args = new Object[] {licenseKeyModelImpl.getAccountKey()};

				finderCache.removeResult(_finderPathCountByAccountKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountKey, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductPurchaseKey.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductPurchaseKey()
				};

				finderCache.removeResult(
					_finderPathCountByProductPurchaseKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductPurchaseKey, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductPurchaseKey()
				};

				finderCache.removeResult(
					_finderPathCountByProductPurchaseKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductPurchaseKey, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_AK.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalUserUuid(),
					licenseKeyModelImpl.getOriginalAccountKey()
				};

				finderCache.removeResult(_finderPathCountByU_AK, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AK, args);

				args = new Object[] {
					licenseKeyModelImpl.getUserUuid(),
					licenseKeyModelImpl.getAccountKey()
				};

				finderCache.removeResult(_finderPathCountByU_AK, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AK, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_PI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalUserUuid(),
					licenseKeyModelImpl.getOriginalProductId()
				};

				finderCache.removeResult(_finderPathCountByU_PI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_PI, args);

				args = new Object[] {
					licenseKeyModelImpl.getUserUuid(),
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
				 _finderPathWithoutPaginationFindByAK_PK.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAccountKey(),
					licenseKeyModelImpl.getOriginalProductKey()
				};

				finderCache.removeResult(_finderPathCountByAK_PK, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAK_PK, args);

				args = new Object[] {
					licenseKeyModelImpl.getAccountKey(),
					licenseKeyModelImpl.getProductKey()
				};

				finderCache.removeResult(_finderPathCountByAK_PK, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAK_PK, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPPK_CI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductPurchaseKey(),
					licenseKeyModelImpl.getOriginalClusterId()
				};

				finderCache.removeResult(_finderPathCountByPPK_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_CI, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductPurchaseKey(),
					licenseKeyModelImpl.getClusterId()
				};

				finderCache.removeResult(_finderPathCountByPPK_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_CI, args);
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
				 _finderPathWithoutPaginationFindByPPK_CI_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductPurchaseKey(),
					licenseKeyModelImpl.getOriginalClusterId(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByPPK_CI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_CI_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductPurchaseKey(),
					licenseKeyModelImpl.getClusterId(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByPPK_CI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_CI_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPPK_C_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductPurchaseKey(),
					licenseKeyModelImpl.getOriginalComplimentary(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByPPK_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_C_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductPurchaseKey(),
					licenseKeyModelImpl.isComplimentary(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByPPK_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_C_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPN_SI_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductName(),
					licenseKeyModelImpl.getOriginalServerId(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByPN_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPN_SI_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductName(),
					licenseKeyModelImpl.getServerId(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByPN_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPN_SI_A, args);
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
				 _finderPathWithoutPaginationFindByPPK_LET_C_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductPurchaseKey(),
					licenseKeyModelImpl.getOriginalLicenseEntryType(),
					licenseKeyModelImpl.getOriginalComplimentary(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByPPK_LET_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_LET_C_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductPurchaseKey(),
					licenseKeyModelImpl.getLicenseEntryType(),
					licenseKeyModelImpl.isComplimentary(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByPPK_LET_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_LET_C_A, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, LicenseKeyImpl.class,
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
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey fetchByPrimaryKey(long licenseKeyId) {
		return fetchByPrimaryKey((Serializable)licenseKeyId);
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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "licenseKeyId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LICENSEKEY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LicenseKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license key persistence.
	 */
	@Activate
	public void activate() {
		LicenseKeyModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		LicenseKeyModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			LicenseKeyModelImpl.UUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByAccountKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountKey",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountKey",
			new String[] {String.class.getName()},
			LicenseKeyModelImpl.ACCOUNTKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByAccountKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountKey",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByProductPurchaseKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductPurchaseKey",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductPurchaseKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByProductPurchaseKey", new String[] {String.class.getName()},
			LicenseKeyModelImpl.PRODUCTPURCHASEKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByProductPurchaseKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByProductPurchaseKey", new String[] {String.class.getName()});

		_finderPathWithPaginationFindByU_AK = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_AK",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_AK = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_AK",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseKeyModelImpl.USERUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACCOUNTKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByU_AK = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AK",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByU_PI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_PI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_PI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_PI",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseKeyModelImpl.USERUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByU_PI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_PI",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByARLU_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_A",
			new String[] {String.class.getName(), Boolean.class.getName()},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_A",
			new String[] {String.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByAK_PK = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAK_PK",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAK_PK = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAK_PK",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseKeyModelImpl.ACCOUNTKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByAK_PK = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAK_PK",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByPPK_CI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPPK_CI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPPK_CI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPPK_CI",
			new String[] {String.class.getName(), Long.class.getName()},
			LicenseKeyModelImpl.PRODUCTPURCHASEKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPPK_CI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPPK_CI",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByPI_SI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPI_SI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPI_SI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPI_SI",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPI_SI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPI_SI",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByARLU_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByPPK_CI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPPK_CI_A",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPPK_CI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPPK_CI_A",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTPURCHASEKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPPK_CI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPPK_CI_A",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByPPK_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPPK_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPPK_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPPK_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTPURCHASEKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPPK_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPPK_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationCountByPPK_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPPK_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByPN_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPN_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTNAME_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPN_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByARLU_PI_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_PI_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
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
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});

		_finderPathWithPaginationFindByPPK_LET_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPPK_LET_C_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPPK_LET_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPPK_LET_C_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTPURCHASEKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.LICENSEENTRYTYPE_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPPK_LET_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPPK_LET_C_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});

		_finderPathWithPaginationFindByPPK_NotLET_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPPK_NotLET_C_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByPPK_NotLET_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPPK_NotLET_C_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(LicenseKeyImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.license.model.LicenseKey"),
			true);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_LICENSEKEY =
		"SELECT licenseKey FROM LicenseKey licenseKey";

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
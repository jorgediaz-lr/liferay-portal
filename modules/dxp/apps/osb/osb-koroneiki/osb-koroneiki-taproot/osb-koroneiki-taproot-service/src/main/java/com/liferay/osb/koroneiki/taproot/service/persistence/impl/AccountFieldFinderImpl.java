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

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.service.persistence.AccountFieldFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = AccountFieldFinder.class)
public class AccountFieldFinderImpl
	extends AccountFieldFinderBaseImpl implements AccountFieldFinder {

	public static final String FIND_NAMES =
		AccountFieldFinder.class.getName() + ".findNames";

	@Override
	public List<String> findNames() {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_NAMES);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("name", Type.STRING);

			List<String> accountFieldNames = new ArrayList<>();

			Iterator<String> itr = q.iterate();

			while (itr.hasNext()) {
				String name = itr.next();

				if (Validator.isNotNull(name)) {
					accountFieldNames.add(name);
				}
			}

			return accountFieldNames;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Reference
	private CustomSQL _customSQL;

}
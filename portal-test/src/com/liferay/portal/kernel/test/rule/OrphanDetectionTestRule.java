/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.test.rule;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionCustomizer;
import com.liferay.portal.kernel.dao.orm.SessionWrapper;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;

import org.junit.runner.Description;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Jorge Díaz
 */
public class OrphanDetectionTestRule
	extends MethodTestRule<ServiceRegistration<SessionCustomizer>> {

	public static final OrphanDetectionTestRule INSTANCE =
		new OrphanDetectionTestRule();

	@Override
	protected void afterMethod(
			Description description,
			ServiceRegistration<SessionCustomizer> serviceRegistration,
			Object target)
		throws Throwable {

		serviceRegistration.unregister();
	}

	@Override
	protected ServiceRegistration<SessionCustomizer> beforeMethod(
			Description description, Object target)
		throws Throwable {

		BundleContext bundleContext = SystemBundleUtil.getBundleContext();

		return bundleContext.registerService(
			SessionCustomizer.class, new OrphanDetectionSessionCustomizer(),
			null);
	}

	private OrphanDetectionTestRule() {
	}

	private static class OrphanDetectionSessionCustomizer
		implements SessionCustomizer {

		@Override
		public Session customize(Session session) {
			return new OrphanDetectionSessionWrapper(session);
		}

	}

	private static class OrphanDetectionSessionWrapper extends SessionWrapper {

		@Override
		public void delete(Object object) throws ORMException {
			super.delete(object);

			BaseModel<?> baseModel = (BaseModel<?>)object;

			_checkOrphanData(baseModel);
		}

		private OrphanDetectionSessionWrapper(Session session) {
			super(session);
		}

		private void _checkOrphanData(BaseModel<?> baseModel)
			throws ORMException {

			if (!(baseModel instanceof ShardedModel)) {
				return;
			}

			ShardedModel shardedModel = (ShardedModel)baseModel;

			int count =
				ResourcePermissionLocalServiceUtil.getResourcePermissionsCount(
					shardedModel.getCompanyId(), baseModel.getModelClassName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(baseModel.getPrimaryKeyObj()));

			if (count > 0) {
				throw new ORMException(
					"Orphan ResourcePermission after " + baseModel +
						" deletion");
			}
		}

	}

}
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

import com.liferay.petra.io.unsync.UnsyncPrintWriter;
import com.liferay.petra.io.unsync.UnsyncStringWriter;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionCustomizer;
import com.liferay.portal.kernel.dao.orm.SessionWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.PersistedResourcedModelLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.transaction.TransactionAttribute;
import com.liferay.portal.kernel.transaction.TransactionLifecycleListener;
import com.liferay.portal.kernel.transaction.TransactionStatus;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.runner.Description;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Jorge Díaz
 */
public class OrphanDetectionTestRule
	extends MethodTestRule<List<ServiceRegistration<?>>> {

	public static final OrphanDetectionTestRule INSTANCE =
		new OrphanDetectionTestRule();

	@Override
	protected void afterMethod(
			Description description,
			List<ServiceRegistration<?>> serviceRegistrations, Object target)
		throws Throwable {

		for (ServiceRegistration<?> serviceRegistration :
				serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	protected List<ServiceRegistration<?>> beforeMethod(
			Description description, Object target)
		throws Throwable {

		Stack<Map<BaseModel<?>, String>> recordsStack = new Stack<>();

		BundleContext bundleContext = SystemBundleUtil.getBundleContext();

		return ListUtil.fromArray(
			bundleContext.registerService(
				SessionCustomizer.class,
				new OrphanDetectionSessionCustomizer(recordsStack), null),
			bundleContext.registerService(
				TransactionLifecycleListener.class,
				new OrphanDetectionTransactionLifecycleListener(recordsStack),
				null));
	}

	private OrphanDetectionTestRule() {
	}

	private static class OrphanDetectionSessionCustomizer
		implements SessionCustomizer {

		@Override
		public Session customize(Session session) {
			return new OrphanDetectionSessionWrapper(session, _recordsStack);
		}

		private OrphanDetectionSessionCustomizer(
			Stack<Map<BaseModel<?>, String>> recordsStack) {

			_recordsStack = recordsStack;
		}

		private Stack<Map<BaseModel<?>, String>> _recordsStack;

	}

	private static class OrphanDetectionSessionWrapper extends SessionWrapper {

		@Override
		public void delete(Object object) throws ORMException {
			super.delete(object);

			if (object instanceof BaseModel<?>) {
				_record(object);
			}
		}

		private OrphanDetectionSessionWrapper(
			Session session, Stack<Map<BaseModel<?>, String>> recordsStack) {

			super(session);

			_recordsStack = recordsStack;
		}

		private void _record(Object object) {
			if (_recordsStack.isEmpty()) {
				return;
			}

			BaseModel<?> baseModel = (BaseModel<?>)object;

			if (baseModel.isNew()) {
				return;
			}

			UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

			Exception exception = new Exception();

			exception.printStackTrace(
				new UnsyncPrintWriter(unsyncStringWriter));

			Map<BaseModel<?>, String> records = _recordsStack.peek();

			records.put(baseModel, unsyncStringWriter.toString());
		}

		private Stack<Map<BaseModel<?>, String>> _recordsStack;

	}

	private static class OrphanDetectionTransactionLifecycleListener
		implements TransactionLifecycleListener {

		@Override
		public void committed(
			TransactionAttribute transactionAttribute,
			TransactionStatus transactionStatus) {

			Map<BaseModel<?>, String> records = _recordsStack.pop();

			Map<String, PersistedModelLocalService>
				persistedModelLocalServices = _getPersistedModelLocalServices();

			for (Map.Entry<BaseModel<?>, String> entry : records.entrySet()) {
				BaseModel<?> baseModel = entry.getKey();

				if (!(baseModel instanceof ShardedModel)) {
					continue;
				}

				ShardedModel shardedModel = (ShardedModel)baseModel;

				int count =
					ResourcePermissionLocalServiceUtil.
						getResourcePermissionsCount(
							shardedModel.getCompanyId(),
							baseModel.getModelClassName(),
							ResourceConstants.SCOPE_INDIVIDUAL,
							String.valueOf(baseModel.getPrimaryKeyObj()));

				Assert.assertEquals(
					StringBundler.concat(
						"Orphan ResourcePermission after the deletion of ",
						baseModel.getModelClassName(), ": ", baseModel,
						" with backtraceInfo ", entry.getValue()),
					0, count);

				if (baseModel instanceof ResourcedModel) {
					try {
						_checkResourceModelResourcePermissions(
							baseModel, entry.getValue(),
							persistedModelLocalServices);
					}
					catch (PortalException portalException) {
						throw new SystemException(portalException);
					}
				}
			}
		}

		@Override
		public void created(
			TransactionAttribute transactionAttribute,
			TransactionStatus transactionStatus) {

			_recordsStack.push(new ConcurrentHashMap<>());
		}

		@Override
		public void rollbacked(
			TransactionAttribute transactionAttribute,
			TransactionStatus transactionStatus, Throwable throwable) {

			_recordsStack.pop();
		}

		private OrphanDetectionTransactionLifecycleListener(
			Stack<Map<BaseModel<?>, String>> recordsStack) {

			_recordsStack = recordsStack;
		}

		private void _checkResourceModelResourcePermissions(
				BaseModel<?> baseModel, String backtraceInfo,
				Map<String, PersistedModelLocalService>
					persistedModelLocalServices)
			throws PortalException {

			PersistedModelLocalService persistedModelLocalService =
				persistedModelLocalServices.get(baseModel.getModelClassName());

			if (!(persistedModelLocalService instanceof
					PersistedResourcedModelLocalService)) {

				return;
			}

			ResourcedModel resourcedModel = (ResourcedModel)baseModel;

			PersistedResourcedModelLocalService
				persistedResourcedModelLocalService =
					(PersistedResourcedModelLocalService)
						persistedModelLocalService;

			List<? extends PersistedModel> persistedResourcedModels =
				persistedResourcedModelLocalService.getPersistedModel(
					resourcedModel.getResourcePrimKey());

			if (!persistedResourcedModels.isEmpty()) {
				return;
			}

			ShardedModel shardedModel = (ShardedModel)baseModel;

			int count =
				ResourcePermissionLocalServiceUtil.getResourcePermissionsCount(
					shardedModel.getCompanyId(), baseModel.getModelClassName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(resourcedModel.getResourcePrimKey()));

			Assert.assertEquals(
				StringBundler.concat(
					"Orphan ResourcePermission after the deletion of ",
					baseModel.getModelClassName(), ": ", baseModel,
					" with backtraceInfo ", backtraceInfo),
				0, count);
		}

		private Map<String, PersistedModelLocalService>
			_getPersistedModelLocalServices() {

			return ReflectionTestUtil.getFieldValue(
				PersistedModelLocalServiceRegistryUtil.
					getPersistedModelLocalServiceRegistry(),
				"_persistedModelLocalServices");
		}

		private Stack<Map<BaseModel<?>, String>> _recordsStack;

	}

}
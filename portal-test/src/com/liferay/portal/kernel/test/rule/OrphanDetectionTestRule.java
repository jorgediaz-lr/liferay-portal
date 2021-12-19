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
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.module.util.SystemBundleUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.transaction.TransactionAttribute;
import com.liferay.portal.kernel.transaction.TransactionLifecycleListener;
import com.liferay.portal.kernel.transaction.TransactionStatus;

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
	extends MethodTestRule<OrphanDetectionTestRule.DataBag> {

	public static final OrphanDetectionTestRule INSTANCE =
		new OrphanDetectionTestRule();

	public static class DataBag {

		private DataBag(
			ServiceRegistration<SessionCustomizer>
				sessionCustomizerServiceRegistration,
			ServiceRegistration<TransactionLifecycleListener>
				transactionLifecycleListenerServiceRegistration) {

			_sessionCustomizerServiceRegistration =
				sessionCustomizerServiceRegistration;
			_transactionLifecycleListenerServiceRegistration =
				transactionLifecycleListenerServiceRegistration;
		}

		private final ServiceRegistration<SessionCustomizer>
			_sessionCustomizerServiceRegistration;
		private final ServiceRegistration<TransactionLifecycleListener>
			_transactionLifecycleListenerServiceRegistration;

	}

	@Override
	protected void afterMethod(
			Description description, DataBag dataBag, Object target)
		throws Throwable {

		ServiceRegistration<SessionCustomizer>
			sessionCustomizerServiceRegistration =
				dataBag._sessionCustomizerServiceRegistration;

		sessionCustomizerServiceRegistration.unregister();

		ServiceRegistration<TransactionLifecycleListener>
			transactionLifecycleListenerServiceRegistration =
				dataBag._transactionLifecycleListenerServiceRegistration;

		transactionLifecycleListenerServiceRegistration.unregister();
	}

	@Override
	protected DataBag beforeMethod(Description description, Object target)
		throws Throwable {

		Stack<Map<BaseModel<?>, String>> recordsStack = new Stack<>();

		BundleContext bundleContext = SystemBundleUtil.getBundleContext();

		return new DataBag(
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

			if (!(object instanceof BaseModel<?>) ||
				!_checkOrphanData(object)) {

				return;
			}

			_record(object);
		}

		private OrphanDetectionSessionWrapper(
			Session session, Stack<Map<BaseModel<?>, String>> recordsStack) {

			super(session);

			_recordsStack = recordsStack;
		}

		private boolean _checkOrphanData(Object object) {
			BaseModel<?> baseModel = (BaseModel<?>)object;

			if (!(baseModel instanceof ShardedModel)) {
				return false;
			}

			ShardedModel shardedModel = (ShardedModel)baseModel;

			int count =
				ResourcePermissionLocalServiceUtil.getResourcePermissionsCount(
					shardedModel.getCompanyId(), baseModel.getModelClassName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(baseModel.getPrimaryKeyObj()));

			if (count == 0) {
				return false;
			}

			return true;
		}

		private void _record(Object object) {
			if (_recordsStack.isEmpty()) {
				/* TODO THROW AN ERROR ? */

				return;
			}

			BaseModel<?> baseModel = (BaseModel<?>)object;

			if (baseModel.isNew()) {
				return;
			}

			Thread currentThread = Thread.currentThread();

			UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

			unsyncStringWriter.write("Thread name : ");
			unsyncStringWriter.write(currentThread.getName());
			unsyncStringWriter.write(", id : ");
			unsyncStringWriter.write(String.valueOf(currentThread.getId()));
			unsyncStringWriter.write(", created : ");
			unsyncStringWriter.write(baseModel.toString());
			unsyncStringWriter.write(" at \n");

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

			for (Map.Entry<BaseModel<?>, String> entry : records.entrySet()) {
				_checkOrphanData(entry.getKey(), entry.getValue());
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

		private void _checkOrphanData(
				BaseModel<?> baseModel, String backtraceInfo)
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

			Assert.assertEquals(
				StringBundler.concat(
					"Orphan ResourcePermission after the deletion of ",
					baseModel.getModelClassName(), ": ", baseModel,
					" with backtraceInfo ", backtraceInfo),
				0, count);
		}

		private Stack<Map<BaseModel<?>, String>> _recordsStack;

	}

}
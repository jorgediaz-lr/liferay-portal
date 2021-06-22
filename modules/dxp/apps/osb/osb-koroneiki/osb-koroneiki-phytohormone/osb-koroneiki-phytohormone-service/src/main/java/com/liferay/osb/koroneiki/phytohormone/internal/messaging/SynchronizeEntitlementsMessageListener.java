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

package com.liferay.osb.koroneiki.phytohormone.internal.messaging;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class SynchronizeEntitlementsMessageListener
	extends BaseEntitlementsMessageListener {

	@Activate
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		int checkInterval = GetterUtil.getInteger(
			properties.get("check.interval"), 15);

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, checkInterval, TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Running account entitlement synchronization");
		}

		List<EntitlementDefinition> entitlementDefinitions =
			entitlementDefinitionLocalService.getEntitlementDefinitions(
				Account.class.getName(), WorkflowConstants.STATUS_APPROVED);

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			addEntitlements(
				entitlementDefinition.getCompanyId(),
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getClassNameId(),
				entitlementDefinition.getName(),
				entitlementDefinition.getDefinition());
		}

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			removeEntitlements(
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getName(),
				entitlementDefinition.getDefinition());
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Running contact entitlement synchronization");
		}

		entitlementDefinitions =
			entitlementDefinitionLocalService.getEntitlementDefinitions(
				Contact.class.getName(), WorkflowConstants.STATUS_APPROVED);

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			addEntitlements(
				entitlementDefinition.getCompanyId(),
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getClassNameId(),
				entitlementDefinition.getName(),
				entitlementDefinition.getDefinition());
		}

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			removeEntitlements(
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getName(),
				entitlementDefinition.getDefinition());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SynchronizeEntitlementsMessageListener.class);

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}
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

package com.liferay.osb.provisioning.identity.management.internal.cache;

import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.kernel.cache.configurator.PortalCacheConfiguratorSettings;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = "portal.cache.manager.name=" + PortalCacheManagerNames.MULTI_VM,
	service = PortalCacheConfiguratorSettings.class
)
public class OktaMultiVMClusteredCacheConfiguratorSettings
	extends PortalCacheConfiguratorSettings {

	public OktaMultiVMClusteredCacheConfiguratorSettings() {
		super(
			OktaMultiVMClusteredCacheConfiguratorSettings.class.
				getClassLoader(),
			"META-INF/module-multi-vm-clustered.xml");
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}
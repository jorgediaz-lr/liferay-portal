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

package com.liferay.osb.provisioning.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Kyle Bischof
 */
@ExtendedObjectClassDefinition(category = "provisioning")
@Meta.OCD(
	id = "com.liferay.osb.provisioning.web.internal.configuration.ProvisioningWebConfiguration",
	localization = "content/Language", name = "provisioning-configuration-name"
)
public interface ProvisioningWebConfiguration {

	@Meta.AD(
		deflt = "5.1|5.1 SP1|5.1 SP2|5.1 SP3|5.1 SP4|5.1 SP5|5.2|5.2 SP1|5.2 SP2|5.2 SP3|5.2 SP4|5.2 SP5|6.0|6.0 SP1|6.0 SP2",
		name = "add-license-hidden-versions", required = false
	)
	public String[] addLicenseHiddenVersions();

}
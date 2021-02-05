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
@ExtendedObjectClassDefinition(generateUI = false)
@Meta.OCD(
	id = "com.liferay.osb.provisioning.web.internal.configuration.ProvisioningWebConfiguration"
)
public interface ProvisioningWebConfiguration {

	@Meta.AD(required = false)
	public String generateLicenseHREF();

	@Meta.AD(deflt = "false", required = false)
	public boolean licensesPortletEnabled();

	@Meta.AD(required = false)
	public String licenseManagerHREF();

}
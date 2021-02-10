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

package com.liferay.osb.customer.license.service.impl;

import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.base.LicenseKeySetServiceBaseImpl;
import com.liferay.osb.customer.license.service.permission.LicenseKeySetPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class LicenseKeySetServiceImpl extends LicenseKeySetServiceBaseImpl {

	public LicenseKeySet deleteLicenseKeySet(long licenseKeySetId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);

		LicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySet, ActionKeys.DELETE);

		return licenseKeySetLocalService.deleteLicenseKeySet(licenseKeySet);
	}

	public LicenseKeySet getLicenseKeySet(long licenseKeySetId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);

		LicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySet, ActionKeys.VIEW);

		return licenseKeySet;
	}

	public LicenseKeySet updateLicenseKeySet(long licenseKeySetId, String name)
		throws PortalException {

		LicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySetId, ActionKeys.UPDATE);

		return licenseKeySetLocalService.updateLicenseKeySet(
			licenseKeySetId, name);
	}

}
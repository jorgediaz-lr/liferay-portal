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

package com.liferay.osb.provisioning.license.service.permission;

import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class LicenseKeyPermission {

	public static void check(
			PermissionChecker permissionChecker, LicenseKey licenseKey,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKey, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long licenseKeyId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKeyId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, LicenseKey licenseKey,
			String actionId)
		throws PortalException {

		//TODO

		return true;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long licenseKeyId,
			String actionId)
		throws PortalException {

		LicenseKey licenseKey = LicenseKeyLocalServiceUtil.getLicenseKey(
			licenseKeyId);

		return contains(permissionChecker, licenseKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setContactRoleWebService(
		ContactRoleWebService contactRoleWebService) {

		_contactRoleWebService = contactRoleWebService;
	}

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	private static ContactRoleWebService _contactRoleWebService;
	private static RoleLocalService _roleLocalService;

}
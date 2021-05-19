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

package com.liferay.osb.provisioning.license.internal.permission;

import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.RoleConstants;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = LicenseKeyPermission.class)
public class LicenseKeyPermissionImpl implements LicenseKeyPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, LicenseKey licenseKey,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKey, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, LicenseKey.class.getName(),
				licenseKey.getLicenseKeyId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long licenseKeyId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKeyId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, LicenseKey.class.getName(), licenseKeyId,
				actionId);
		}
	}

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, LicenseKey.class.getName(), actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, LicenseKey licenseKey,
			String actionId)
		throws PortalException {

		return contains(permissionChecker, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long licenseKeyId,
			String actionId)
		throws PortalException {

		LicenseKey licenseKey = _licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		return contains(permissionChecker, licenseKey, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_ADMIN, false) ||
			_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_WORKER, false)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_WATCHER, false) &&
			ArrayUtil.contains(_PROVISIONING_WATCHER_ACTION_IDS, actionId)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_CONTACT_WORKER, false) &&
			ArrayUtil.contains(
				_PROVISIONING_CONTACT_WORKER_ACTION_IDS, actionId)) {

			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setLicenseKeyLocalService(
		LicenseKeyLocalService licenseKeyLocalService) {

		_licenseKeyLocalService = licenseKeyLocalService;
	}

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	private static final String[] _PROVISIONING_CONTACT_WORKER_ACTION_IDS = {
		ProvisioningActionKeys.VIEW
	};

	private static final String[] _PROVISIONING_WATCHER_ACTION_IDS = {
		ProvisioningActionKeys.VIEW
	};

	private static LicenseKeyLocalService _licenseKeyLocalService;
	private static RoleLocalService _roleLocalService;

}
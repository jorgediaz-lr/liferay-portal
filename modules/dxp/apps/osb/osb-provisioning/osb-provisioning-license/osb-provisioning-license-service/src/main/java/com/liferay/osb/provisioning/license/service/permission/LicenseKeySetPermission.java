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

package com.liferay.osb.customer.license.service.permission;

import com.liferay.osb.customer.constants.OSBActionKeys;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class LicenseKeySetPermission {

	public static void check(
			PermissionChecker permissionChecker, LicenseKeySet licenseKeySet,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKeySet, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long licenseKeySetId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, licenseKeySetId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, LicenseKeySet licenseKeySet,
			String actionId)
		throws PortalException {

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(),
				OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(),
				OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

			return true;
		}

		try {
			User user = permissionChecker.getUser();

			boolean accountCustomer = false;
			boolean accountWorker = false;

			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountContactRoles(
					licenseKeySet.getKoroneikiAccountKey(), user.getUuid(), 1,
					1000);

			for (ContactRole contactRole : contactRoles) {
				if (contactRole.getType() ==
						ContactRole.Type.ACCOUNT_CUSTOMER) {

					accountCustomer = true;
				}
				else if (contactRole.getType() ==
							ContactRole.Type.ACCOUNT_WORKER) {

					accountWorker = true;
				}
			}

			if (accountWorker) {
				return true;
			}

			if (actionId.equals(OSBActionKeys.VIEW) && accountCustomer) {
				return true;
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long licenseKeySetId,
			String actionId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			LicenseKeySetLocalServiceUtil.getLicenseKeySet(licenseKeySetId);

		return contains(permissionChecker, licenseKeySet, actionId);
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
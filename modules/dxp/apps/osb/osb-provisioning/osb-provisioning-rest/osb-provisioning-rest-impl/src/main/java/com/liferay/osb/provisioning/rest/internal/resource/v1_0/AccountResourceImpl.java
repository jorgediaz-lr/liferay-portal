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

package com.liferay.osb.provisioning.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.rest.internal.ProvisioningContactThreadLocal;
import com.liferay.osb.provisioning.rest.resource.v1_0.AccountResource;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountResource.class
)
public class AccountResourceImpl extends BaseAccountResourceImpl {

	@Override
	public void deleteAccountContactByUuidContactUuidRole(
			String accountKey, String contactUuid, String[] contactRoleNames)
		throws Exception {

		_checkAccountAdminContactRole(accountKey);

		String[] contactRoleKeys = new String[contactRoleNames.length];

		for (int i = 0; i < contactRoleNames.length; i++) {
			String contactRoleName = contactRoleNames[i];

			ContactRole contactRole = _contactRoleWebService.fetchContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(), contactRoleName);

			if (contactRole == null) {
				throw new PortalException(
					"Unable to find contact role with name " + contactRoleName);
			}

			contactRoleKeys[i] = contactRole.getKey();
		}

		_accountWebService.unassignContactRolesByUuid(
			_getAgentName(), _getAgentUID(), accountKey, contactUuid,
			contactRoleKeys);
	}

	@Override
	public void putAccountContactByUuidContactUuidRole(
			String accountKey, String contactUuid, String[] contactRoleNames)
		throws Exception {

		_checkAccountAdminContactRole(accountKey);

		String[] contactRoleKeys = new String[contactRoleNames.length];

		for (int i = 0; i < contactRoleNames.length; i++) {
			String contactRoleName = contactRoleNames[i];

			ContactRole contactRole = _contactRoleWebService.fetchContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(), contactRoleName);

			if (contactRole == null) {
				throw new PortalException(
					"Unable to find contact role with name " + contactRoleName);
			}

			contactRoleKeys[i] = contactRole.getKey();
		}

		_accountWebService.assignContactRolesByUuid(
			_getAgentName(), _getAgentUID(), accountKey, contactUuid,
			contactRoleKeys);
	}

	private void _checkAccountAdminContactRole(String accountKey)
		throws Exception {

		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountCustomerContactRoles(
					accountKey, contact.getEmailAddress(), 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(ContactRoleConstants.NAME_ADMINISTRATOR)) {
					return;
				}
			}
		}
		else if (_isOmniAdmin()) {
			return;
		}

		throw new PrincipalException();
	}

	private String _getAgentName() {
		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			return StringBundler.concat(
				contact.getFirstName(), StringPool.SPACE,
				contact.getLastName());
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = permissionChecker.getUser();

		return user.getFullName();
	}

	private String _getAgentUID() {
		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			return contact.getUuid();
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = permissionChecker.getUser();

		return user.getUuid();
	}

	private boolean _isOmniAdmin() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		return false;
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

}
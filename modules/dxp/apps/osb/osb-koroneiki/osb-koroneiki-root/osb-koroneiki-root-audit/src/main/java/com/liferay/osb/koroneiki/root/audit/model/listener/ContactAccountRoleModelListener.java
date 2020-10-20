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

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ContactAccountRoleModelListener
	extends BaseAuditModelListener<ContactAccountRole> {

	@Override
	public void onAfterCreate(ContactAccountRole contactAccountRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Account account = contactAccountRole.getAccount();

			Contact contact = contactAccountRole.getContact();
			ContactRole contactRole = contactAccountRole.getContactRole();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				contactAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				AuditEntry.Action.ASSIGN.toString(), "Contact Role",
				StringPool.BLANK, StringPool.BLANK, contactRole.getName(),
				String.valueOf(contactAccountRole.getContactRoleId()),
				contact.getFullName(), getServiceContext(contactAccountRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactAccountRole.getContactRoleId(),
				AuditEntry.Action.ASSIGN.toString(), "Account",
				StringPool.BLANK, StringPool.BLANK, account.getName(),
				String.valueOf(account.getAccountId()), contactRole.getName(),
				getServiceContext(contactAccountRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(ContactAccountRole contactAccountRole)
		throws ModelListenerException {

		if (!isEnabled()) {
			return;
		}

		try {
			Account account = contactAccountRole.getAccount();

			Contact contact = contactAccountRole.getContact();
			ContactRole contactRole = contactAccountRole.getContactRole();

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class),
				contactAccountRole.getAccountId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				AuditEntry.Action.UNASSIGN.toString(), "Contact Role",
				contactRole.getName(),
				String.valueOf(contactAccountRole.getContactRoleId()),
				StringPool.BLANK, StringPool.BLANK, contact.getFullName(),
				getServiceContext(contactAccountRole));

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactAccountRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactAccountRole.getContactRoleId(),
				AuditEntry.Action.UNASSIGN.toString(), "Account",
				account.getName(), String.valueOf(account.getAccountId()),
				StringPool.BLANK, StringPool.BLANK, contactRole.getName(),
				getServiceContext(contactAccountRole));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

}
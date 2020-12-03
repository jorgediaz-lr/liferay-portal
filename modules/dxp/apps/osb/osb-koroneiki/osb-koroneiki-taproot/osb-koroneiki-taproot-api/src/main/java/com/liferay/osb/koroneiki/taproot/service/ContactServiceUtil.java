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

package com.liferay.osb.koroneiki.taproot.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Contact. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContactService
 * @generated
 */
public class ContactServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.koroneiki.taproot.model.Contact addContact(
			String uuid, String firstName, String middleName, String lastName,
			String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addContact(
			uuid, firstName, middleName, lastName, emailAddress, languageId,
			emailAddressVerified);
	}

	public static com.liferay.osb.koroneiki.taproot.model.Contact deleteContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteContact(contactId);
	}

	public static com.liferay.osb.koroneiki.taproot.model.Contact
			fetchContactByUuid(String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchContactByUuid(uuid);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.Contact> getAccountContacts(
				long accountId, String contactRoleType, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountContacts(
			accountId, contactRoleType, start, end);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.Contact> getAccountContacts(
				String accountKey, String contactRoleType, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountContacts(
			accountKey, contactRoleType, start, end);
	}

	public static int getAccountContactsCount(
			long accountId, String contactRoleType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountContactsCount(accountId, contactRoleType);
	}

	public static int getAccountContactsCount(
			String accountKey, String contactRoleType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountContactsCount(
			accountKey, contactRoleType);
	}

	public static com.liferay.osb.koroneiki.taproot.model.Contact getContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContact(contactId);
	}

	public static com.liferay.osb.koroneiki.taproot.model.Contact
			getContactByEmailAddress(String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContactByEmailAddress(emailAddress);
	}

	public static com.liferay.osb.koroneiki.taproot.model.Contact
			getContactByUuid(String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getContactByUuid(uuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.Contact> getTeamContacts(
				String teamKey, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTeamContacts(teamKey, start, end);
	}

	public static int getTeamContactsCount(String teamKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTeamContactsCount(teamKey);
	}

	public static com.liferay.osb.koroneiki.taproot.model.Contact updateContact(
			long contactId, String uuid, String firstName, String middleName,
			String lastName, String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateContact(
			contactId, uuid, firstName, middleName, lastName, emailAddress,
			languageId, emailAddressVerified);
	}

	public static ContactService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContactService, ContactService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContactService.class);

		ServiceTracker<ContactService, ContactService> serviceTracker =
			new ServiceTracker<ContactService, ContactService>(
				bundle.getBundleContext(), ContactService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
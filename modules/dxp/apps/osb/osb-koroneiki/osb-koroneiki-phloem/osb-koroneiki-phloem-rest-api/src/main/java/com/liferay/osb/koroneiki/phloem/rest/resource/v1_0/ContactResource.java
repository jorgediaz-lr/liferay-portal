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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactPermission;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Locale;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/koroneiki-rest/v1.0
 *
 * @author Amos Fong
 * @generated
 */
@Generated("")
@ProviderType
public interface ContactResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public Page<Contact> getAccountAccountKeyContactsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public Page<Contact> getAccountAccountKeyCustomerContactsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public Page<Contact> getAccountAccountKeyWorkerContactsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public Page<Contact> getContactsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Contact postContact(
			String agentName, String agentUID, Contact contact)
		throws Exception;

	public void deleteContactByEmailAddresEmailAddress(
			String agentName, String agentUID, String emailAddress)
		throws Exception;

	public Contact getContactByEmailAddresEmailAddress(String emailAddress)
		throws Exception;

	public Contact putContactByEmailAddresEmailAddress(
			String agentName, String agentUID, String emailAddress,
			Contact contact)
		throws Exception;

	public void deleteContactByUuidContactUuid(
			String agentName, String agentUID, String contactUuid)
		throws Exception;

	public Contact getContactByUuidContactUuid(String contactUuid)
		throws Exception;

	public Contact putContactByUuidContactUuid(
			String agentName, String agentUID, String contactUuid,
			Contact contact)
		throws Exception;

	public void deleteContactByUuidContactUuidContactPermission(
			String agentName, String agentUID, String contactUuid,
			ContactPermission contactPermission)
		throws Exception;

	public void putContactByUuidContactUuidContactPermission(
			String agentName, String agentUID, String contactUuid,
			ContactPermission contactPermission)
		throws Exception;

	public Page<Contact> getTeamTeamKeyContactsPage(
			String teamKey, Pagination pagination)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser);

	public static class FactoryHolder {

		public static volatile Factory factory;

	}

	@ProviderType
	public interface Builder {

		public ContactResource build();

		public Builder checkPermissions(boolean checkPermissions);

		public Builder httpServletRequest(
			HttpServletRequest httpServletRequest);

		public Builder preferredLocale(Locale preferredLocale);

		public Builder user(com.liferay.portal.kernel.model.User user);

	}

	@ProviderType
	public interface Factory {

		public Builder create();

	}

}
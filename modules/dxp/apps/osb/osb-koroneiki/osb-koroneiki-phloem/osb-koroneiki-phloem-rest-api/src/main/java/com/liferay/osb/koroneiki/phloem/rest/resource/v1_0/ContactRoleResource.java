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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRolePermission;
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
public interface ContactRoleResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public Page<ContactRole>
			getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ContactRole> getContactRolesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public ContactRole postContactRole(
			String agentName, String agentUID, ContactRole contactRole)
		throws Exception;

	public void deleteContactRole(
			String agentName, String agentUID, String contactRoleKey)
		throws Exception;

	public ContactRole getContactRole(String contactRoleKey) throws Exception;

	public ContactRole putContactRole(
			String agentName, String agentUID, String contactRoleKey,
			ContactRole contactRole)
		throws Exception;

	public void deleteContactRoleContactRolePermission(
			String agentName, String agentUID, String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception;

	public void putContactRoleContactRolePermission(
			String agentName, String agentUID, String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception;

	public ContactRole getContactRoleContactRoleTypeContactRoleName(
			String contactRoleType, String contactRoleName)
		throws Exception;

	public Page<ContactRole> getTeamTeamKeyContactByEmailAddressRolesPage(
			String teamKey, String emailAddress, Pagination pagination)
		throws Exception;

	public Page<ContactRole> getTeamTeamKeyContactByUuidContactUuidRolesPage(
			String teamKey, String contactUuid, Pagination pagination)
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

		public ContactRoleResource build();

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
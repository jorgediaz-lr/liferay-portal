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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.ActionUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.constraints.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseContactRoleResourceImpl
	implements ContactRoleResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's contact's customer and worker contact roles."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactEmailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path(
		"/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole>
			getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@NotNull @Parameter(hidden = true)
				@PathParam("contactEmailAddress") String contactEmailAddress,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's contact's customer and worker contact roles."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole>
			getAccountAccountKeyContactByUuidContactUuidRolesPage(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@NotNull @Parameter(hidden = true) @PathParam("contactUuid")
					String contactUuid,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-email-address/{contactEmailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's contact's customer contact roles."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactEmailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path(
		"/accounts/{accountKey}/customer-contacts/by-email-address/{contactEmailAddress}/roles"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@NotNull @Parameter(hidden = true)
				@PathParam("contactEmailAddress") String contactEmailAddress,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's contact's customer contact roles."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path(
		"/accounts/{accountKey}/customer-contacts/by-uuid/{contactUuid}/roles"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@NotNull @Parameter(hidden = true) @PathParam("contactUuid")
					String contactUuid,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-email-address/{contactEmailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's contact's customer contact roles."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactEmailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path(
		"/accounts/{accountKey}/worker-contacts/by-email-address/{contactEmailAddress}/roles"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@NotNull @Parameter(hidden = true)
				@PathParam("contactEmailAddress") String contactEmailAddress,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's contact's customer contact roles."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/worker-contacts/by-uuid/{contactUuid}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@NotNull @Parameter(hidden = true) @PathParam("contactUuid")
					String contactUuid,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the contact roles. Results can be paginated, filtered, searched, and sorted."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "search"),
			@Parameter(in = ParameterIn.QUERY, name = "filter"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize"),
			@Parameter(in = ParameterIn.QUERY, name = "sort")
		}
	)
	@Path("/contact-roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole> getContactRolesPage(
			@Parameter(hidden = true) @QueryParam("search") String search,
			@Context Filter filter, @Context Pagination pagination,
			@Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles' -d $'{"description": ___, "name": ___, "type": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID")
		}
	)
	@Path("/contact-roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public ContactRole postContactRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			ContactRole contactRole)
		throws Exception {

		return new ContactRole();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}'  -u 'test@liferay.com:test'
	 */
	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "contactRoleKey")
		}
	)
	@Path("/contact-roles/{contactRoleKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public void deleteContactRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("contactRoleKey")
				String contactRoleKey)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the contact role.")
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "contactRoleKey")}
	)
	@Path("/contact-roles/{contactRoleKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public ContactRole getContactRole(
			@NotNull @Parameter(hidden = true) @PathParam("contactRoleKey")
				String contactRoleKey)
		throws Exception {

		return new ContactRole();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}' -d $'{"description": ___, "name": ___, "type": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "contactRoleKey")
		}
	)
	@Path("/contact-roles/{contactRoleKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public ContactRole putContactRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("contactRoleKey")
				String contactRoleKey,
			ContactRole contactRole)
		throws Exception {

		return new ContactRole();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}/contact-role-permissions' -d $'{"assignContact": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "contactRoleKey")
		}
	)
	@Path("/contact-roles/{contactRoleKey}/contact-role-permissions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public void deleteContactRoleContactRolePermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("contactRoleKey")
				String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}/contact-role-permissions' -d $'{"assignContact": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "contactRoleKey")
		}
	)
	@Path("/contact-roles/{contactRoleKey}/contact-role-permissions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public void putContactRoleContactRolePermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("contactRoleKey")
				String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/contact-roles/{contactRoleType}/{contactRoleName}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the contact role by name.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "contactRoleType"),
			@Parameter(in = ParameterIn.PATH, name = "contactRoleName")
		}
	)
	@Path("/contact-roles/{contactRoleType}/{contactRoleName}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public ContactRole getContactRoleContactRoleTypeContactRoleName(
			@NotNull @Parameter(hidden = true) @PathParam("contactRoleType")
				String contactRoleType,
			@NotNull @Parameter(hidden = true) @PathParam("contactRoleName")
				String contactRoleName)
		throws Exception {

		return new ContactRole();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address/{emailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the team's contact's contact roles.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.PATH, name = "emailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-email-address/{emailAddress}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole> getTeamTeamKeyContactByEmailAddressRolesPage(
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @PathParam("emailAddress") String
				emailAddress,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the team's contact's contact roles.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ContactRole")})
	public Page<ContactRole> getTeamTeamKeyContactByUuidContactUuidRolesPage(
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany) {

		this.contextCompany = contextCompany;
	}

	public void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {

		this.contextHttpServletRequest = contextHttpServletRequest;
	}

	public void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {

		this.contextHttpServletResponse = contextHttpServletResponse;
	}

	public void setContextUriInfo(UriInfo contextUriInfo) {
		this.contextUriInfo = contextUriInfo;
	}

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser) {

		this.contextUser = contextUser;
	}

	protected Map<String, String> addAction(
		String actionName, GroupedModel groupedModel, String methodName) {

		return ActionUtil.addAction(
			actionName, getClass(), groupedModel, methodName,
			contextScopeChecker, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, Long id, String methodName, Long ownerId,
		String permissionName, Long siteId) {

		return ActionUtil.addAction(
			actionName, getClass(), id, methodName, contextScopeChecker,
			ownerId, permissionName, siteId, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, String methodName, String permissionName,
		Long siteId) {

		return addAction(
			actionName, siteId, methodName, null, permissionName, siteId);
	}

	protected <T, R> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	protected AcceptLanguage contextAcceptLanguage;
	protected com.liferay.portal.kernel.model.Company contextCompany;
	protected HttpServletRequest contextHttpServletRequest;
	protected HttpServletResponse contextHttpServletResponse;
	protected Object contextScopeChecker;
	protected UriInfo contextUriInfo;
	protected com.liferay.portal.kernel.model.User contextUser;
	protected GroupLocalService groupLocalService;
	protected ResourceActionLocalService resourceActionLocalService;
	protected ResourcePermissionLocalService resourcePermissionLocalService;
	protected RoleLocalService roleLocalService;

}
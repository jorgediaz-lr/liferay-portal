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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
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
public abstract class BaseAccountResourceImpl implements AccountResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(
		description = "Retrieves the accounts. Results can be paginated, filtered, searched, and sorted."
	)
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "search"),
			@Parameter(in = ParameterIn.QUERY, name = "filter"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize"),
			@Parameter(in = ParameterIn.QUERY, name = "sort")
		}
	)
	@Path("/accounts")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public Page<Account> getAccountsPage(
			@Parameter(hidden = true) @QueryParam("search") String search,
			@Context Filter filter, @Context Pagination pagination,
			@Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts' -d $'{"assignedTeams": ___, "code": ___, "contactEmailAddress": ___, "contacts": ___, "dataRegion": ___, "description": ___, "externalLinks": ___, "faxNumber": ___, "internal": ___, "language": ___, "logoId": ___, "name": ___, "parentAccountKey": ___, "phoneNumber": ___, "postalAddresses": ___, "productPurchases": ___, "profileEmailAddress": ___, "region": ___, "status": ___, "tier": ___, "website": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID")
		}
	)
	@Path("/accounts")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public Account postAccount(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			Account account)
		throws Exception {

		return new Account();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/by-external-link/{domain}/{entityName}/{entityId}'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the account by the external link.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "domain"),
			@Parameter(in = ParameterIn.PATH, name = "entityName"),
			@Parameter(in = ParameterIn.PATH, name = "entityId"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/by-external-link/{domain}/{entityName}/{entityId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public Page<Account> getAccountByExternalLinkDomainEntityNameEntityPage(
			@NotNull @Parameter(hidden = true) @PathParam("domain") String
				domain,
			@NotNull @Parameter(hidden = true) @PathParam("entityName") String
				entityName,
			@NotNull @Parameter(hidden = true) @PathParam("entityId") String
				entityId,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey")
		}
	)
	@Path("/accounts/{accountKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccount(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the account.")
	@Override
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "accountKey")}
	)
	@Path("/accounts/{accountKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public Account getAccount(
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey)
		throws Exception {

		return new Account();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}' -d $'{"assignedTeams": ___, "code": ___, "contactEmailAddress": ___, "contacts": ___, "dataRegion": ___, "description": ___, "externalLinks": ___, "faxNumber": ___, "internal": ___, "language": ___, "logoId": ___, "name": ___, "parentAccountKey": ___, "phoneNumber": ___, "postalAddresses": ___, "productPurchases": ___, "profileEmailAddress": ___, "region": ___, "status": ___, "tier": ___, "website": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey")
		}
	)
	@Path("/accounts/{accountKey}")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Account")})
	public Account putAccount(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			Account account)
		throws Exception {

		return new Account();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/account-permissions' -d $'{"assignContact": ___, "assignTeam": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@DELETE
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey")
		}
	)
	@Path("/accounts/{accountKey}/account-permissions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountAccountPermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			AccountPermission accountPermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/account-permissions' -d $'{"assignContact": ___, "assignTeam": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey")
		}
	)
	@Path("/accounts/{accountKey}/account-permissions")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Account")})
	public void putAccountAccountPermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			AccountPermission accountPermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams/{teamKey}/roles'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns roles from the team for the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "teamRoleKeys")
		}
	)
	@Path("/accounts/{accountKey}/assigned-teams/{teamKey}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountAssignedTeamTeamKeyRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @QueryParam("teamRoleKeys")
				String[] teamRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams/{teamKey}/roles'  -u 'test@liferay.com:test'
	 */
	@Operation(description = "Assigns roles to the team for the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "teamRoleKeys")
		}
	)
	@Path("/accounts/{accountKey}/assigned-teams/{teamKey}/roles")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Account")})
	public void putAccountAssignedTeamTeamKeyRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @QueryParam("teamRoleKeys")
				String[] teamRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/child-accounts'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the account's child accounts.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/child-accounts")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public Page<Account> getAccountChildAccountsPage(
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(
		description = "Unassigns roles from the contact for the account."
	)
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactEmailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path(
		"/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountContactByEmailAddresContactEmailAddressRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @PathParam("contactEmailAddress")
				String contactEmailAddress,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@Operation(description = "Assigns roles to the contact for the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactEmailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path(
		"/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles"
	)
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Account")})
	public void putAccountContactByEmailAddresContactEmailAddressRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @PathParam("contactEmailAddress")
				String contactEmailAddress,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(
		description = "Unassigns roles from the contact for the account."
	)
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path("/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountContactByUuidContactUuidRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@Operation(description = "Assigns roles to the contact for the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path("/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Account")})
	public void putAccountContactByUuidContactUuidRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-email-address'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns customer contacts from the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "contactEmailAddresses")
		}
	)
	@Path("/accounts/{accountKey}/customer-contacts/by-email-address")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountCustomerContactByEmailAddres(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true)
			@QueryParam("contactEmailAddresses")
			String[] contactEmailAddresses)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-uuid'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns contacts from the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "contactUuids")
		}
	)
	@Path("/accounts/{accountKey}/customer-contacts/by-uuid")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountCustomerContactByUuid(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @QueryParam("contactUuids")
				String[] contactUuids)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-email-address'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns worker contacts from the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "contactEmailAddresses")
		}
	)
	@Path("/accounts/{accountKey}/worker-contacts/by-email-address")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountWorkerContactByEmailAddres(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true)
			@QueryParam("contactEmailAddresses")
			String[] contactEmailAddresses)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-uuid'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns contacts from the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "contactUuids")
		}
	)
	@Path("/accounts/{accountKey}/worker-contacts/by-uuid")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public void deleteAccountWorkerContactByUuid(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@NotNull @Parameter(hidden = true) @QueryParam("contactUuids")
				String[] contactUuids)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/accounts'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the contact's accounts.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/contacts/by-uuid/{contactUuid}/accounts")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public Page<Account> getContactByUuidContactUuidAccountsPage(
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/assigned-accounts'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the team's assigned accounts.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/teams/{teamKey}/assigned-accounts")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Account")})
	public Page<Account> getTeamTeamKeyAssignedAccountsPage(
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
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

	public void setGroupLocalService(GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	public void setRoleLocalService(RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
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
		String actionName, Long id, String methodName,
		ModelResourcePermission modelResourcePermission) {

		return ActionUtil.addAction(
			actionName, getClass(), id, methodName, contextScopeChecker,
			modelResourcePermission, contextUriInfo);
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
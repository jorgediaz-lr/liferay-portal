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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamPermission;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
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
public abstract class BaseTeamResourceImpl implements TeamResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the teams assigned to the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/assigned-teams")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public Page<Team> getAccountAccountKeyAssignedTeamsPage(
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/teams'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the account's teams.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/teams")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public Page<Team> getAccountAccountKeyTeamsPage(
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/teams' -d $'{"key": ___, "name": ___, "teamRoles": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
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
	@Path("/accounts/{accountKey}/teams")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public Team postAccountAccountKeyTeam(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			Team team)
		throws Exception {

		return new Team();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(
		description = "Retrieves the teams. Results can be paginated, filtered, searched, and sorted."
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
	@Path("/teams")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public Page<Team> getTeamsPage(
			@Parameter(hidden = true) @QueryParam("search") String search,
			@Context Filter filter, @Context Pagination pagination,
			@Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/by-external-link/{domain}/{entityName}/{entityId}'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the team by the external link.")
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
	@Path("/teams/by-external-link/{domain}/{entityName}/{entityId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public Page<Team> getTeamByExternalLinkDomainEntityNameEntityPage(
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
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey")
		}
	)
	@Path("/teams/{teamKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public void deleteTeam(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the team.")
	@Override
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "teamKey")})
	@Path("/teams/{teamKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public Team getTeam(
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey)
		throws Exception {

		return new Team();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}' -d $'{"key": ___, "name": ___, "teamRoles": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey")
		}
	)
	@Path("/teams/{teamKey}")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Team")})
	public Team putTeam(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			Team team)
		throws Exception {

		return new Team();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns contacts from the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "emailAddresses")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-email-address")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public void deleteTeamContactByEmailAddress(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @QueryParam("emailAddresses")
				String[] emailAddresses)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address'  -u 'test@liferay.com:test'
	 */
	@Operation(description = "Assigns contacts to the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "emailAddresses")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-email-address")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Team")})
	public void putTeamContactByEmailAddress(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @QueryParam("emailAddresses")
				String[] emailAddresses)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address/{emailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns roles from the contact for the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.PATH, name = "emailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-email-address/{emailAddress}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public void deleteTeamContactByEmailAddressRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @PathParam("emailAddress") String
				emailAddress,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address/{emailAddress}/roles'  -u 'test@liferay.com:test'
	 */
	@Operation(description = "Assigns roles to the contact for the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.PATH, name = "emailAddress"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-email-address/{emailAddress}/roles")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Team")})
	public void putTeamContactByEmailAddressRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @PathParam("emailAddress") String
				emailAddress,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns contacts from the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "contactUuids")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-uuid")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public void deleteTeamContactByUuid(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @QueryParam("contactUuids")
				String[] contactUuids)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid'  -u 'test@liferay.com:test'
	 */
	@Operation(description = "Assigns contacts to the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "contactUuids")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-uuid")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Team")})
	public void putTeamContactByUuid(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @QueryParam("contactUuids")
				String[] contactUuids)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Operation(description = "Unassigns roles from the contact for the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public void deleteTeamContactByUuidContactUuidRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles'  -u 'test@liferay.com:test'
	 */
	@Operation(description = "Assigns roles to the contact for the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "contactRoleKeys")
		}
	)
	@Path("/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Team")})
	public void putTeamContactByUuidContactUuidRole(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			@NotNull @Parameter(hidden = true) @QueryParam("contactRoleKeys")
				String[] contactRoleKeys)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/team-permissions' -d $'{"assignContact": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@DELETE
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey")
		}
	)
	@Path("/teams/{teamKey}/team-permissions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Team")})
	public void deleteTeamTeamPermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			TeamPermission teamPermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/team-permissions' -d $'{"assignContact": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey")
		}
	)
	@Path("/teams/{teamKey}/team-permissions")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "Team")})
	public void putTeamTeamPermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			TeamPermission teamPermission)
		throws Exception {
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
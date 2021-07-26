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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.GroupedModel;
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
public abstract class BaseExternalLinkResourceImpl
	implements ExternalLinkResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/external-links'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the account's external links.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/external-links")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public Page<ExternalLink> getAccountAccountKeyExternalLinksPage(
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/external-links' -d $'{"domain": ___, "entityId": ___, "entityName": ___, "key": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Operation(description = "Adds an external link to the account.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "accountKey")
		}
	)
	@Path("/accounts/{accountKey}/external-links")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink postAccountAccountKeyExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("accountKey") String
				accountKey,
			ExternalLink externalLink)
		throws Exception {

		return new ExternalLink();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/external-links'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the contact's external links.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "contactUuid"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/contacts/by-uuid/{contactUuid}/external-links")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public Page<ExternalLink> getContactByUuidContactUuidExternalLinksPage(
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/external-links' -d $'{"domain": ___, "entityId": ___, "entityName": ___, "key": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Operation(description = "Adds an external link to the contact.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "contactUuid")
		}
	)
	@Path("/contacts/by-uuid/{contactUuid}/external-links")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink postContactByUuidContactUuidExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("contactUuid") String
				contactUuid,
			ExternalLink externalLink)
		throws Exception {

		return new ExternalLink();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/external-links/{externalLinkKey}'  -u 'test@liferay.com:test'
	 */
	@DELETE
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "externalLinkKey")
		}
	)
	@Path("/external-links/{externalLinkKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public void deleteExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("externalLinkKey")
				String externalLinkKey)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/external-links/{externalLinkKey}'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the external link.")
	@Override
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "externalLinkKey")}
	)
	@Path("/external-links/{externalLinkKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink getExternalLink(
			@NotNull @Parameter(hidden = true) @PathParam("externalLinkKey")
				String externalLinkKey)
		throws Exception {

		return new ExternalLink();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/external-links/{externalLinkKey}' -d $'{"domain": ___, "entityId": ___, "entityName": ___, "key": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Operation(
		description = "Updates the external link. Only the entityId field can be updated."
	)
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "externalLinkKey")
		}
	)
	@Path("/external-links/{externalLinkKey}")
	@Produces({"application/json", "application/xml"})
	@PUT
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink putExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("externalLinkKey")
				String externalLinkKey,
			ExternalLink externalLink)
		throws Exception {

		return new ExternalLink();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionKey}/external-links'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(
		description = "Retrieves the product consumption's external links."
	)
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "productConsumptionKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/product-consumptions/{productConsumptionKey}/external-links")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public Page<ExternalLink>
			getProductConsumptionProductConsumptionKeyExternalLinksPage(
				@NotNull @Parameter(hidden = true)
				@PathParam("productConsumptionKey")
				String productConsumptionKey,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/product-consumptions/{productConsumptionKey}/external-links' -d $'{"domain": ___, "entityId": ___, "entityName": ___, "key": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Operation(
		description = "Adds an external link to the product consumption."
	)
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "productConsumptionKey")
		}
	)
	@Path("/product-consumptions/{productConsumptionKey}/external-links")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink postProductConsumptionProductConsumptionKeyExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true)
			@PathParam("productConsumptionKey")
			String productConsumptionKey,
			ExternalLink externalLink)
		throws Exception {

		return new ExternalLink();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/product-purchases/{productPurchaseKey}/external-links'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the product purchase's external links.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "productPurchaseKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/product-purchases/{productPurchaseKey}/external-links")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public Page<ExternalLink>
			getProductPurchaseProductPurchaseKeyExternalLinksPage(
				@NotNull @Parameter(hidden = true)
				@PathParam("productPurchaseKey")
				String productPurchaseKey,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/product-purchases/{productPurchaseKey}/external-links' -d $'{"domain": ___, "entityId": ___, "entityName": ___, "key": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Operation(description = "Adds an external link to the product purchase.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "productPurchaseKey")
		}
	)
	@Path("/product-purchases/{productPurchaseKey}/external-links")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink postProductPurchaseProductPurchaseKeyExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("productPurchaseKey")
				String productPurchaseKey,
			ExternalLink externalLink)
		throws Exception {

		return new ExternalLink();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/{productKey}/external-links'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the product's external links.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "productKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/products/{productKey}/external-links")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public Page<ExternalLink> getProductProductKeyExternalLinksPage(
			@NotNull @Parameter(hidden = true) @PathParam("productKey") String
				productKey,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/{productKey}/external-links' -d $'{"domain": ___, "entityId": ___, "entityName": ___, "key": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Operation(description = "Adds an external link to the product.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "productKey")
		}
	)
	@Path("/products/{productKey}/external-links")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink postProductProductKeyExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("productKey") String
				productKey,
			ExternalLink externalLink)
		throws Exception {

		return new ExternalLink();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/external-links'  -u 'test@liferay.com:test'
	 */
	@GET
	@Operation(description = "Retrieves the team's external links.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "teamKey"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/teams/{teamKey}/external-links")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public Page<ExternalLink> getTeamTeamKeyExternalLinksPage(
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/teams/{teamKey}/external-links' -d $'{"domain": ___, "entityId": ___, "entityName": ___, "key": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Consumes({"application/json", "application/xml"})
	@Operation(description = "Adds an external link to the team.")
	@Override
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "teamKey")
		}
	)
	@Path("/teams/{teamKey}/external-links")
	@POST
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ExternalLink")})
	public ExternalLink postTeamTeamKeyExternalLink(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("teamKey") String
				teamKey,
			ExternalLink externalLink)
		throws Exception {

		return new ExternalLink();
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
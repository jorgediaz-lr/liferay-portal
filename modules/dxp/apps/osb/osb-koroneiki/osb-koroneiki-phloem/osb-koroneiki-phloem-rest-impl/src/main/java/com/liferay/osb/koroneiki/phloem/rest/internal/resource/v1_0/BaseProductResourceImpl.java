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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPermission;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
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
public abstract class BaseProductResourceImpl implements ProductResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/products'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the products. Results can be paginated, filtered, searched, and sorted."
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
	@Path("/products")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<Product> getProductsPage(
			@Parameter(hidden = true) @QueryParam("search") String search,
			@Context Filter filter, @Context Pagination pagination,
			@Context Sort[] sorts)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/koroneiki-rest/v1.0/products' -d $'{"externalLinks": ___, "name": ___, "properties": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
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
	@Path("/products")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Product postProduct(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			Product product)
		throws Exception {

		return new Product();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/by-external-link/{domain}/{entityName}/{entityId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the products by the external link.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "domain"),
			@Parameter(in = ParameterIn.PATH, name = "entityName"),
			@Parameter(in = ParameterIn.PATH, name = "entityId"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/products/by-external-link/{domain}/{entityName}/{entityId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<Product> getProductByExternalLinkDomainEntityNameEntityPage(
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
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/by-name/{productName}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the product with the exact name.")
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "productName")}
	)
	@Path("/products/by-name/{productName}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Product getProductByNameProductName(
			@NotNull @Parameter(hidden = true) @PathParam("productName") String
				productName)
		throws Exception {

		return new Product();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/{productKey}'  -u 'test@liferay.com:test'
	 */
	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "productKey")
		}
	)
	@Path("/products/{productKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public void deleteProduct(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("productKey") String
				productKey)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/{productKey}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(description = "Retrieves the product.")
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "productKey")}
	)
	@Path("/products/{productKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Product getProduct(
			@NotNull @Parameter(hidden = true) @PathParam("productKey") String
				productKey)
		throws Exception {

		return new Product();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/{productKey}' -d $'{"externalLinks": ___, "name": ___, "properties": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "productKey")
		}
	)
	@Path("/products/{productKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Product putProduct(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("productKey") String
				productKey,
			Product product)
		throws Exception {

		return new Product();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/{productKey}/product-permissions' -d $'{"consume": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "productKey")
		}
	)
	@Path("/products/{productKey}/product-permissions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public void deleteProductProductPermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("productKey") String
				productKey,
			ProductPermission productPermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/koroneiki-rest/v1.0/products/{productKey}/product-permissions' -d $'{"consume": ___, "delete": ___, "permissions": ___, "roleNames": ___, "update": ___, "view": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "agentName"),
			@Parameter(in = ParameterIn.QUERY, name = "agentUID"),
			@Parameter(in = ParameterIn.PATH, name = "productKey")
		}
	)
	@Path("/products/{productKey}/product-permissions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public void putProductProductPermission(
			@Parameter(hidden = true) @QueryParam("agentName") String agentName,
			@Parameter(hidden = true) @QueryParam("agentUID") String agentUID,
			@NotNull @Parameter(hidden = true) @PathParam("productKey") String
				productKey,
			ProductPermission productPermission)
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
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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchasePermission;
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
public interface ProductPurchaseResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public Page<ProductPurchase> getAccountAccountKeyProductPurchasesPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public ProductPurchase postAccountAccountKeyProductPurchase(
			String agentName, String agentUID, String accountKey,
			ProductPurchase productPurchase)
		throws Exception;

	public Page<ProductPurchase>
			getContactByUuidContactUuidProductPurchasesPage(
				String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ProductPurchase> getProductPurchasesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public Page<ProductPurchase>
			getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception;

	public void deleteProductPurchase(
			String agentName, String agentUID, String productPurchaseKey)
		throws Exception;

	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception;

	public ProductPurchase putProductPurchase(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchase productPurchase)
		throws Exception;

	public void deleteProductPurchaseProductPurchasePermission(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchasePermission productPurchasePermission)
		throws Exception;

	public void putProductPurchaseProductPurchasePermission(
			String agentName, String agentUID, String productPurchaseKey,
			ProductPurchasePermission productPurchasePermission)
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

		public ProductPurchaseResource build();

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
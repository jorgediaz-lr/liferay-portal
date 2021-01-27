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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;

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
public interface PostalAddressResource {

	public static Builder builder() {
		return FactoryHolder.factory.create();
	}

	public Page<PostalAddress> getAccountAccountKeyPostalAddressesPage(
			String accountKey)
		throws Exception;

	public PostalAddress postAccountAccountKeyPostalAddress(
			String agentName, String agentUID, String accountKey,
			PostalAddress postalAddress)
		throws Exception;

	public void deletePostalAddress(
			String agentName, String agentUID, Long postalAddressId)
		throws Exception;

	public PostalAddress getPostalAddress(Long postalAddressId)
		throws Exception;

	public PostalAddress putPostalAddress(
			String agentName, String agentUID, Long postalAddressId,
			PostalAddress postalAddress)
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

		public PostalAddressResource build();

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
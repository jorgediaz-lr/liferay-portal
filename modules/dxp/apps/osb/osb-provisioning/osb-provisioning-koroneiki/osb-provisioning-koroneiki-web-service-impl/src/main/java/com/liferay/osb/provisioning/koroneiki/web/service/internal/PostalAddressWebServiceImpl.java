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

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.PostalAddressResource;
import com.liferay.osb.provisioning.koroneiki.web.service.PostalAddressWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = PostalAddressWebService.class
)
public class PostalAddressWebServiceImpl implements PostalAddressWebService {

	public PostalAddress addPostalAddress(
			String agentName, String agentUID, String accountKey,
			PostalAddress postalAddress)
		throws Exception {

		return _postalAddressResource.postAccountAccountKeyPostalAddress(
			agentName, agentUID, accountKey, postalAddress);
	}

	public void deletePostalAddress(
			String agentName, String agentUID, Long postalAddressId)
		throws Exception {

		_postalAddressResource.deletePostalAddress(
			agentName, agentUID, postalAddressId);
	}

	public List<PostalAddress> getAccountPostalAddresss(String accountKey)
		throws Exception {

		Page<PostalAddress> postalAddresssPage =
			_postalAddressResource.getAccountAccountKeyPostalAddressesPage(
				accountKey);

		if ((postalAddresssPage != null) &&
			(postalAddresssPage.getItems() != null)) {

			return new ArrayList<>(postalAddresssPage.getItems());
		}

		return Collections.emptyList();
	}

	public PostalAddress updatePostalAddress(
			String agentName, String agentUID, Long postalAddressId,
			PostalAddress postalAddress)
		throws Exception {

		return _postalAddressResource.putPostalAddress(
			agentName, agentUID, postalAddressId, postalAddress);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		PostalAddressResource.Builder builder = PostalAddressResource.builder();

		_postalAddressResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private PostalAddressResource _postalAddressResource;

}
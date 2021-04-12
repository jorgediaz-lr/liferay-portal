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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Country;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.CountryUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.CountryResource;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/country.properties",
	scope = ServiceScope.PROTOTYPE, service = CountryResource.class
)
public class CountryResourceImpl extends BaseCountryResourceImpl {

	@Override
	public Page<Country> getCountriesPage() throws Exception {
		return Page.of(
			transform(_countryService.getCountries(), CountryUtil::toCountry));
	}

	@Reference
	private CountryService _countryService;

}
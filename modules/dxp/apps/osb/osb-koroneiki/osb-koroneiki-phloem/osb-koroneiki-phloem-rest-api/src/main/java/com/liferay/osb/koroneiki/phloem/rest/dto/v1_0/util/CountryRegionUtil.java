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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.CountryRegion;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.CountryServiceUtil;

/**
 * @author Yuanyuan Huang
 */
public class CountryRegionUtil {

	public static CountryRegion toCountryRegion(Region region)
		throws Exception {

		return new CountryRegion() {
			{
				active = region.isActive();
				code = region.getRegionCode();

				Country country = CountryServiceUtil.getCountry(
					region.getCountryId());

				countryName = country.getName();

				name = region.getName();
			}
		};
	}

}
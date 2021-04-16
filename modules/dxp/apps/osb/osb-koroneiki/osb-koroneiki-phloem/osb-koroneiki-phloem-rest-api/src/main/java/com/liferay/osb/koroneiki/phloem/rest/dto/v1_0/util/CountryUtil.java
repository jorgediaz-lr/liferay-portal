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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Country;

/**
 * @author Yuanyuan Huang
 */
public class CountryUtil {

	public static Country toCountry(
			com.liferay.portal.kernel.model.Country country)
		throws Exception {

		return new Country() {
			{
				a2 = country.getA2();
				a3 = country.getA3();
				active = country.isActive();
				idd = country.getIdd();
				name = country.getName();
				zipRequired = country.isZipRequired();
			}
		};
	}

}
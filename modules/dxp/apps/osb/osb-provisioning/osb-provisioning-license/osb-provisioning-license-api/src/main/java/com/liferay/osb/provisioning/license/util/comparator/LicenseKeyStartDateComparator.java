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

package com.liferay.osb.customer.license.util.comparator;

import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class LicenseKeyStartDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "startDate ASC";

	public static final String ORDER_BY_DESC = "startDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"startDate"};

	public LicenseKeyStartDateComparator() {
		this(false);
	}

	public LicenseKeyStartDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		LicenseKey licenseKey1 = (LicenseKey)obj1;
		LicenseKey licenseKey2 = (LicenseKey)obj2;

		int value = DateUtil.compareTo(
			licenseKey1.getStartDate(), licenseKey2.getStartDate());

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}
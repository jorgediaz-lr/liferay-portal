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

package com.liferay.osb.customer.license.util;

import com.liferay.osb.customer.admin.constants.LicenseEntryConstants;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.customer.license.util.comparator.LicenseKeyExpirationDateComparator;
import com.liferay.osb.customer.license.util.comparator.LicenseKeyStartDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Amos Fong
 */
public class LicenseUtil {

	public static OrderByComparator getLicenseKeyOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("start-date")) {
			orderByComparator = new LicenseKeyStartDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("expiration-date")) {
			orderByComparator = new LicenseKeyExpirationDateComparator(
				orderByAsc);
		}
		else {
			orderByComparator = new LicenseKeyStartDateComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static boolean isAggregate(long licenseKeySetId)
		throws PortalException {

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySetId);

		licenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = licenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!licenseKey.isActive()) {
				itr.remove();
			}
		}

		if (licenseKeys.isEmpty() || (licenseKeys.size() <= 1)) {
			return false;
		}

		LicenseKey firstLicenseKey = licenseKeys.get(0);

		String licenseEntryType = firstLicenseKey.getLicenseEntryType();
		Date startDate = firstLicenseKey.getStartDate();
		Date expirationDate = firstLicenseKey.getExpirationDate();

		for (LicenseKey licenseKey : licenseKeys) {
			if (licenseKey.getLicenseVersion() < 3) {
				return false;
			}

			String curLicenseEntryType = licenseKey.getLicenseEntryType();

			if (!curLicenseEntryType.equals(
					LicenseEntryConstants.TYPE_PER_USER) &&
				!curLicenseEntryType.equals(
					LicenseEntryConstants.TYPE_PRODUCTION)) {

				return false;
			}

			if (curLicenseEntryType.equals(
					LicenseEntryConstants.TYPE_PER_USER)) {

				if (firstLicenseKey.getMaxConcurrentUsers() !=
						licenseKey.getMaxConcurrentUsers()) {

					return false;
				}

				if (firstLicenseKey.getMaxUsers() != licenseKey.getMaxUsers()) {
					return false;
				}
			}

			if (!licenseEntryType.equals(curLicenseEntryType)) {
				return false;
			}

			if (!DateUtil.equals(startDate, licenseKey.getStartDate())) {
				return false;
			}

			if (!DateUtil.equals(
					expirationDate, licenseKey.getExpirationDate())) {

				return false;
			}
		}

		return true;
	}

	public static boolean isRenewAggregate(long licenseKeySetId)
		throws PortalException {

		if (!isAggregate(licenseKeySetId)) {
			return false;
		}

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySetId);

		licenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = licenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!licenseKey.isActive()) {
				itr.remove();
			}
		}

		if (licenseKeys.isEmpty()) {
			return false;
		}

		LicenseKey firstLicenseKey = licenseKeys.get(0);

		if (!firstLicenseKey.canRenew()) {
			return false;
		}

		return true;
	}

	public static String trimText(String text) {

		// Copied from org.dom4j.tree.AbstractBranch.getTextTrim()

		StringBuffer textContent = new StringBuffer();

		StringTokenizer tokenizer = new StringTokenizer(text);

		while (tokenizer.hasMoreTokens()) {
			String str = tokenizer.nextToken();

			textContent.append(str);

			if (tokenizer.hasMoreTokens()) {
				textContent.append(" ");
			}
		}

		return textContent.toString();
	}

}
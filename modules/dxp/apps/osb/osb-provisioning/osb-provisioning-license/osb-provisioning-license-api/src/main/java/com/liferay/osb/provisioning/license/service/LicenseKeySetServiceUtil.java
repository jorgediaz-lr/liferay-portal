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

package com.liferay.osb.customer.license.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for LicenseKeySet. This utility wraps
 * <code>com.liferay.osb.customer.license.service.impl.LicenseKeySetServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetService
 * @generated
 */
public class LicenseKeySetServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.license.service.impl.LicenseKeySetServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.customer.license.model.LicenseKeySet
			deleteLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLicenseKeySet(licenseKeySetId);
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			getLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeySet(licenseKeySetId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			updateLicenseKeySet(long licenseKeySetId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLicenseKeySet(licenseKeySetId, name);
	}

	public static LicenseKeySetService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LicenseKeySetService, LicenseKeySetService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LicenseKeySetService.class);

		ServiceTracker<LicenseKeySetService, LicenseKeySetService>
			serviceTracker =
				new ServiceTracker<LicenseKeySetService, LicenseKeySetService>(
					bundle.getBundleContext(), LicenseKeySetService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
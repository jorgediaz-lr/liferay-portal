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
 * Provides the local service utility for LicenseKeySet. This utility wraps
 * <code>com.liferay.osb.customer.license.service.impl.LicenseKeySetLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetLocalService
 * @generated
 */
public class LicenseKeySetLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.license.service.impl.LicenseKeySetLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the license key set to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKeySet the license key set
	 * @return the license key set that was added
	 */
	public static com.liferay.osb.customer.license.model.LicenseKeySet
		addLicenseKeySet(
			com.liferay.osb.customer.license.model.LicenseKeySet
				licenseKeySet) {

		return getService().addLicenseKeySet(licenseKeySet);
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			addLicenseKeySet(long userId, long accountEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLicenseKeySet(userId, accountEntryId, name);
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			addLicenseKeySet(
				long userId, String koroneikiAccountKey, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLicenseKeySet(userId, koroneikiAccountKey, name);
	}

	/**
	 * Creates a new license key set with the primary key. Does not add the license key set to the database.
	 *
	 * @param licenseKeySetId the primary key for the new license key set
	 * @return the new license key set
	 */
	public static com.liferay.osb.customer.license.model.LicenseKeySet
		createLicenseKeySet(long licenseKeySetId) {

		return getService().createLicenseKeySet(licenseKeySetId);
	}

	/**
	 * Deletes the license key set from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKeySet the license key set
	 * @return the license key set that was removed
	 * @throws PortalException
	 */
	public static com.liferay.osb.customer.license.model.LicenseKeySet
			deleteLicenseKeySet(
				com.liferay.osb.customer.license.model.LicenseKeySet
					licenseKeySet)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLicenseKeySet(licenseKeySet);
	}

	/**
	 * Deletes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set that was removed
	 * @throws PortalException if a license key set with the primary key could not be found
	 */
	public static com.liferay.osb.customer.license.model.LicenseKeySet
			deleteLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLicenseKeySet(licenseKeySetId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.license.model.impl.LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.license.model.impl.LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
		fetchLicenseKeySet(long licenseKeySetId) {

		return getService().fetchLicenseKeySet(licenseKeySetId);
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKeySet>
			getAccountEntryLicenseKeySets(
				long accountEntryId, int start, int end) {

		return getService().getAccountEntryLicenseKeySets(
			accountEntryId, start, end);
	}

	public static int getAccountEntryLicenseKeySetsCount(long accountEntryId) {
		return getService().getAccountEntryLicenseKeySetsCount(accountEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the license key set with the primary key.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set
	 * @throws PortalException if a license key set with the primary key could not be found
	 */
	public static com.liferay.osb.customer.license.model.LicenseKeySet
			getLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseKeySet(licenseKeySetId);
	}

	/**
	 * Returns a range of all the license key sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.license.model.impl.LicenseKeySetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license key sets
	 * @param end the upper bound of the range of license key sets (not inclusive)
	 * @return the range of license key sets
	 */
	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKeySet>
			getLicenseKeySets(int start, int end) {

		return getService().getLicenseKeySets(start, end);
	}

	/**
	 * Returns the number of license key sets.
	 *
	 * @return the number of license key sets
	 */
	public static int getLicenseKeySetsCount() {
		return getService().getLicenseKeySetsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the license key set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseKeySetLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseKeySet the license key set
	 * @return the license key set that was updated
	 */
	public static com.liferay.osb.customer.license.model.LicenseKeySet
		updateLicenseKeySet(
			com.liferay.osb.customer.license.model.LicenseKeySet
				licenseKeySet) {

		return getService().updateLicenseKeySet(licenseKeySet);
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			updateLicenseKeySet(long licenseKeySetId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLicenseKeySet(licenseKeySetId, name);
	}

	public static LicenseKeySetLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LicenseKeySetLocalService, LicenseKeySetLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LicenseKeySetLocalService.class);

		ServiceTracker<LicenseKeySetLocalService, LicenseKeySetLocalService>
			serviceTracker =
				new ServiceTracker
					<LicenseKeySetLocalService, LicenseKeySetLocalService>(
						bundle.getBundleContext(),
						LicenseKeySetLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
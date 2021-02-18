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

package com.liferay.osb.provisioning.license.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LicenseEntry. This utility wraps
 * <code>com.liferay.osb.provisioning.license.service.impl.LicenseEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryLocalService
 * @generated
 */
public class LicenseEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.provisioning.license.service.impl.LicenseEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the license entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntry the license entry
	 * @return the license entry that was added
	 */
	public static com.liferay.osb.provisioning.license.model.LicenseEntry
		addLicenseEntry(
			com.liferay.osb.provisioning.license.model.LicenseEntry
				licenseEntry) {

		return getService().addLicenseEntry(licenseEntry);
	}

	public static com.liferay.osb.provisioning.license.model.LicenseEntry
			addLicenseEntry(
				long userId, String productKey, String name, String type,
				int versionMin, int versionMax)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLicenseEntry(
			userId, productKey, name, type, versionMin, versionMax);
	}

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	public static com.liferay.osb.provisioning.license.model.LicenseEntry
		createLicenseEntry(long licenseEntryId) {

		return getService().createLicenseEntry(licenseEntryId);
	}

	/**
	 * Deletes the license entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntry the license entry
	 * @return the license entry that was removed
	 */
	public static com.liferay.osb.provisioning.license.model.LicenseEntry
		deleteLicenseEntry(
			com.liferay.osb.provisioning.license.model.LicenseEntry
				licenseEntry) {

		return getService().deleteLicenseEntry(licenseEntry);
	}

	/**
	 * Deletes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws PortalException if a license entry with the primary key could not be found
	 */
	public static com.liferay.osb.provisioning.license.model.LicenseEntry
			deleteLicenseEntry(long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLicenseEntry(licenseEntryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseEntryModelImpl</code>.
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

	public static com.liferay.osb.provisioning.license.model.LicenseEntry
		fetchLicenseEntry(long licenseEntryId) {

		return getService().fetchLicenseEntry(licenseEntryId);
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
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.license.model.impl.LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
			getLicenseEntries(int start, int end) {

		return getService().getLicenseEntries(start, end);
	}

	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
			getLicenseEntries(String productKey) {

		return getService().getLicenseEntries(productKey);
	}

	public static java.util.List
		<com.liferay.osb.provisioning.license.model.LicenseEntry>
			getLicenseEntries(String productKey, int version) {

		return getService().getLicenseEntries(productKey, version);
	}

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	public static int getLicenseEntriesCount() {
		return getService().getLicenseEntriesCount();
	}

	/**
	 * Returns the license entry with the primary key.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws PortalException if a license entry with the primary key could not be found
	 */
	public static com.liferay.osb.provisioning.license.model.LicenseEntry
			getLicenseEntry(long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseEntry(licenseEntryId);
	}

	public static com.liferay.osb.provisioning.license.model.LicenseEntry
			getLicenseEntry(String productKey, String type)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLicenseEntry(productKey, type);
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
	 * Updates the license entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LicenseEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param licenseEntry the license entry
	 * @return the license entry that was updated
	 */
	public static com.liferay.osb.provisioning.license.model.LicenseEntry
		updateLicenseEntry(
			com.liferay.osb.provisioning.license.model.LicenseEntry
				licenseEntry) {

		return getService().updateLicenseEntry(licenseEntry);
	}

	public static com.liferay.osb.provisioning.license.model.LicenseEntry
			updateLicenseEntry(
				long licenseEntryId, String productKey, String name,
				String type, int versionMin, int versionMax)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLicenseEntry(
			licenseEntryId, productKey, name, type, versionMin, versionMax);
	}

	public static LicenseEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LicenseEntryLocalService, LicenseEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LicenseEntryLocalService.class);

		ServiceTracker<LicenseEntryLocalService, LicenseEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<LicenseEntryLocalService, LicenseEntryLocalService>(
						bundle.getBundleContext(),
						LicenseEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
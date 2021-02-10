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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseKeySetLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetLocalService
 * @generated
 */
public class LicenseKeySetLocalServiceWrapper
	implements LicenseKeySetLocalService,
			   ServiceWrapper<LicenseKeySetLocalService> {

	public LicenseKeySetLocalServiceWrapper(
		LicenseKeySetLocalService licenseKeySetLocalService) {

		_licenseKeySetLocalService = licenseKeySetLocalService;
	}

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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
		addLicenseKeySet(
			com.liferay.osb.customer.license.model.LicenseKeySet
				licenseKeySet) {

		return _licenseKeySetLocalService.addLicenseKeySet(licenseKeySet);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			addLicenseKeySet(long userId, long accountEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.addLicenseKeySet(
			userId, accountEntryId, name);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			addLicenseKeySet(
				long userId, String koroneikiAccountKey, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.addLicenseKeySet(
			userId, koroneikiAccountKey, name);
	}

	/**
	 * Creates a new license key set with the primary key. Does not add the license key set to the database.
	 *
	 * @param licenseKeySetId the primary key for the new license key set
	 * @return the new license key set
	 */
	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
		createLicenseKeySet(long licenseKeySetId) {

		return _licenseKeySetLocalService.createLicenseKeySet(licenseKeySetId);
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			deleteLicenseKeySet(
				com.liferay.osb.customer.license.model.LicenseKeySet
					licenseKeySet)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.deleteLicenseKeySet(licenseKeySet);
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			deleteLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.deleteLicenseKeySet(licenseKeySetId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseKeySetLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _licenseKeySetLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _licenseKeySetLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _licenseKeySetLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _licenseKeySetLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _licenseKeySetLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
		fetchLicenseKeySet(long licenseKeySetId) {

		return _licenseKeySetLocalService.fetchLicenseKeySet(licenseKeySetId);
	}

	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKeySet>
		getAccountEntryLicenseKeySets(long accountEntryId, int start, int end) {

		return _licenseKeySetLocalService.getAccountEntryLicenseKeySets(
			accountEntryId, start, end);
	}

	@Override
	public int getAccountEntryLicenseKeySetsCount(long accountEntryId) {
		return _licenseKeySetLocalService.getAccountEntryLicenseKeySetsCount(
			accountEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _licenseKeySetLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _licenseKeySetLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the license key set with the primary key.
	 *
	 * @param licenseKeySetId the primary key of the license key set
	 * @return the license key set
	 * @throws PortalException if a license key set with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			getLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);
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
	@Override
	public java.util.List<com.liferay.osb.customer.license.model.LicenseKeySet>
		getLicenseKeySets(int start, int end) {

		return _licenseKeySetLocalService.getLicenseKeySets(start, end);
	}

	/**
	 * Returns the number of license key sets.
	 *
	 * @return the number of license key sets
	 */
	@Override
	public int getLicenseKeySetsCount() {
		return _licenseKeySetLocalService.getLicenseKeySetsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseKeySetLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
		updateLicenseKeySet(
			com.liferay.osb.customer.license.model.LicenseKeySet
				licenseKeySet) {

		return _licenseKeySetLocalService.updateLicenseKeySet(licenseKeySet);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			updateLicenseKeySet(long licenseKeySetId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetLocalService.updateLicenseKeySet(
			licenseKeySetId, name);
	}

	@Override
	public LicenseKeySetLocalService getWrappedService() {
		return _licenseKeySetLocalService;
	}

	@Override
	public void setWrappedService(
		LicenseKeySetLocalService licenseKeySetLocalService) {

		_licenseKeySetLocalService = licenseKeySetLocalService;
	}

	private LicenseKeySetLocalService _licenseKeySetLocalService;

}
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

package com.liferay.osb.customer.license.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LicenseKey service. Represents a row in the &quot;OSB_LicenseKey&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.license.model.impl.LicenseKeyImpl"
)
@ProviderType
public interface LicenseKey extends LicenseKeyModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.license.model.impl.LicenseKeyImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LicenseKey, Long> LICENSE_KEY_ID_ACCESSOR =
		new Accessor<LicenseKey, Long>() {

			@Override
			public Long get(LicenseKey licenseKey) {
				return licenseKey.getLicenseKeyId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LicenseKey> getTypeClass() {
				return LicenseKey.class;
			}

		};

	public boolean canRenew()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.osb.customer.admin.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.osb.customer.admin.model.LicenseEntry getLicenseEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public LicenseKeySet getLicenseKeySet()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isExpired();

}
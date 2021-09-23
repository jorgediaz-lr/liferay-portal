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

package com.liferay.osb.provisioning.license.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the CommonLicenseKey service. Represents a row in the &quot;Provisioning_CommonLicenseKey&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKeyModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyImpl"
)
@ProviderType
public interface CommonLicenseKey
	extends CommonLicenseKeyModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommonLicenseKey, Long>
		COMMON_LICENSE_KEY_ID_ACCESSOR =
			new Accessor<CommonLicenseKey, Long>() {

				@Override
				public Long get(CommonLicenseKey commonLicenseKey) {
					return commonLicenseKey.getCommonLicenseKeyId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommonLicenseKey> getTypeClass() {
					return CommonLicenseKey.class;
				}

			};

	public String getFileDir();

	public String getFilePath();

}
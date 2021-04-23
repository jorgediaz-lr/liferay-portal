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

package com.liferay.osb.provisioning.license.internal.model.listener;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(immediate = true, service = ModelListener.class)
public class LicenseKeyModelListener extends BaseModelListener<LicenseKey> {

	@Override
	public void onAfterCreate(LicenseKey licenseKey)
		throws ModelListenerException {

		try {
			_reindex(licenseKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterUpdate(LicenseKey licenseKey)
		throws ModelListenerException {

		try {
			_reindex(licenseKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	private void _reindex(LicenseKey licenseKey) throws PortalException {
		_licenseKeyLocalService.reindex(licenseKey.getLicenseKeyId());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyModelListener.class);

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

}
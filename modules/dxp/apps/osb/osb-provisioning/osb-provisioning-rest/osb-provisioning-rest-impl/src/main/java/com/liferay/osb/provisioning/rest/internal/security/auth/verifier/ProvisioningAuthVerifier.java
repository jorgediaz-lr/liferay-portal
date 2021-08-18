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

package com.liferay.osb.provisioning.rest.internal.security.auth.verifier;

import com.liferay.admin.kernel.util.Omniadmin;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.http.HttpAuthManagerUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthorizationHeader;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "auth.verifier.ProvisioningAuthVerifier.urls.includes=/o/provisioning-rest/*",
	service = AuthVerifier.class
)
public class ProvisioningAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return ProvisioningAuthVerifier.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		try {
			String[] credentials = verify(accessControlContext.getRequest());

			if (credentials != null) {
				authVerifierResult.setPassword(credentials[1]);
				authVerifierResult.setPasswordBasedAuthentication(true);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(credentials[0]));
			}
		}
		catch (AuthException authException) {
			if (_log.isDebugEnabled()) {
				_log.debug(authException, authException);
			}

			HttpServletResponse httpServletResponse =
				accessControlContext.getResponse();

			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					httpServletResponse.getOutputStream())) {

				objectOutputStream.writeObject(authException);

				authVerifierResult.setState(
					AuthVerifierResult.State.INVALID_CREDENTIALS);
			}
			catch (IOException ioException) {
				_log.error(ioException, ioException);

				throw authException;
			}
		}

		return authVerifierResult;
	}

	protected String[] verify(HttpServletRequest httpServletRequest)
		throws AuthException {

		long userId = 0;

		try {
			userId = HttpAuthManagerUtil.getBasicUserId(httpServletRequest);
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}

		if ((userId > 0) && !_omniadmin.isOmniadmin(userId)) {
			return null;
		}

		HttpAuthorizationHeader httpAuthorizationHeader =
			HttpAuthManagerUtil.parse(httpServletRequest);

		Map<String, String> params =
			httpAuthorizationHeader.getAuthParameters();

		String[] credentials = new String[2];

		credentials[0] = String.valueOf(userId);
		credentials[1] = params.get("password");

		return credentials;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProvisioningAuthVerifier.class);

	@Reference
	private Omniadmin _omniadmin;

}
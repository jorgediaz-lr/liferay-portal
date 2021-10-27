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

package com.liferay.osb.distributed.messaging.google.pubsub.connector;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import com.liferay.portal.kernel.util.GetterUtil;

import java.io.IOException;

import java.util.Arrays;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Amos Fong
 */
public abstract class BaseServiceAccountCredentialsProvider
	implements ServiceAccountCredentialsProvider {

	@Override
	public CredentialsProvider getCredentialsProvider() throws IOException {
		Credentials credentials = ServiceAccountCredentials.fromPkcs8(
			_clientId, _clientEmailAddress, _privateKeyPkcs8, _privateKeyId,
			Arrays.asList(_SCOPE));

		return FixedCredentialsProvider.create(credentials);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_clientEmailAddress = GetterUtil.getString(
			properties.get("clientEmailAddress"));
		_clientId = GetterUtil.getString(properties.get("clientId"));
		_privateKeyId = GetterUtil.getString(properties.get("privateKeyId"));
		_privateKeyPkcs8 = GetterUtil.getString(
			properties.get("privateKeyPkcs8"));
	}

	private static final String _SCOPE =
		"https://www.googleapis.com/auth/cloud-platform";

	private String _clientEmailAddress;
	private String _clientId;
	private String _privateKeyId;
	private String _privateKeyPkcs8;

}
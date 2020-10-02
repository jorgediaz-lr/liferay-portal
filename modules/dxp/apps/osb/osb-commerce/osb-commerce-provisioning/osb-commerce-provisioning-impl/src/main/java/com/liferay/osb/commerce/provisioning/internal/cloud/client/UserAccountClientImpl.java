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

package com.liferay.osb.commerce.provisioning.internal.cloud.client;

import com.liferay.headless.osb.commerce.client.dto.v1_0.UserAccount;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author Ivica Cardic
 */
public class UserAccountClientImpl
	extends BaseClientImpl implements UserAccountClient {

	public UserAccountClientImpl(
		String oauthClientId, String oauthClientSecret) {

		_oauthClientId = oauthClientId;
		_oauthClientSecret = oauthClientSecret;
	}

	@Override
	public void destroy() {
	}

	@Override
	public UserAccount postUserAccount(
		UserAccount userAccount, String virtualHostname) {

		return executePost(
			_getAuthorizationHeader(virtualHostname), userAccount,
			virtualHostname + _USER_ACCOUNTS_PATH, UserAccount.class);
	}

	private String _getAuthorizationHeader(String virtualHostname) {
		HttpPost httpPost = new HttpPost(virtualHostname + _OAUTH2_TOKEN_PATH);

		try {
			httpPost.setEntity(
				new UrlEncodedFormEntity(
					new ArrayList<NameValuePair>() {
						{
							add(
								new BasicNameValuePair(
									"client_id", _oauthClientId));
							add(
								new BasicNameValuePair(
									"client_secret", _oauthClientSecret));
							add(
								new BasicNameValuePair(
									"grant_type", "client_credentials"));
						}
					},
					"UTF-8"));
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			throw new SystemException(unsupportedEncodingException);
		}

		Map<String, String> result = convert(
			execute(null, httpPost), Map.class);

		return "Bearer: " + result.get("access_token");
	}

	private static final String _OAUTH2_TOKEN_PATH = "/o/oauth2/token";

	private static final String _USER_ACCOUNTS_PATH =
		"/o/headless-osb-commerce/v1.0/user-accounts";

	private final String _oauthClientId;
	private final String _oauthClientSecret;

}
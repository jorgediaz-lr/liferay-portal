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

package com.liferay.osb.koroneiki.root.identity.management.internal.provider;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactException;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientFactory;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"api.token=", "error.email.address=", "host=", "port=", "protocol=",
		"provider=web"
	},
	service = ContactIdentityProvider.class
)
public class WebContactIdentityProvider implements ContactIdentityProvider {

	public Contact fetchContactByEmailAddress(String emailAddress)
		throws Exception {

		Contact contact = _contactLocalService.fetchContactByEmailAddress(
			emailAddress);

		if (contact == null) {
			contact = _importContactByEmailAddress(emailAddress);
		}

		return contact;
	}

	public Contact fetchContactByProviderId(String providerId)
		throws Exception {

		Contact contact = _contactLocalService.fetchContactByUuid(providerId);

		if (contact == null) {
			contact = _importContactByUuid(providerId);
		}

		return contact;
	}

	public Contact getContactByEmailAddress(String emailAddress)
		throws Exception {

		Contact contact = fetchContactByEmailAddress(emailAddress);

		if (contact == null) {
			throw new NoSuchContactException();
		}

		return contact;
	}

	public Contact getContactByProviderId(String providerId) throws Exception {
		Contact contact = fetchContactByProviderId(providerId);

		if (contact == null) {
			throw new NoSuchContactException();
		}

		return contact;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_errorEmailAddress = String.valueOf(
			properties.get("error.email.address"));

		if (Validator.isNotNull(properties.get("host"))) {
			Map<String, Object> jsonWebServiceClientProperties =
				new HashMap<>();

			jsonWebServiceClientProperties.put(
				"headers",
				"Authorization=token " + properties.get("api.token"));
			jsonWebServiceClientProperties.put(
				"hostName", properties.get("host"));
			jsonWebServiceClientProperties.put(
				"hostPort", properties.get("port"));
			jsonWebServiceClientProperties.put(
				"protocol", properties.get("protocol"));

			_jsonWebServiceClient = _jsonWebServiceClientFactory.getInstance(
				jsonWebServiceClientProperties, false);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_jsonWebServiceClient != null) {
			_jsonWebServiceClient.destroy();
		}
	}

	private long _getDefaultUserId() throws PortalException {
		if (_defaultUserId <= 0) {
			long companyId = _portalInstancesLocalService.getDefaultCompanyId();

			User user = _userLocalService.getDefaultUser(companyId);

			_defaultUserId = user.getUserId();
		}

		return _defaultUserId;
	}

	private JSONObject _getToJSONObject(
			String url, Map<String, String> parameters)
		throws Exception {

		if (_jsonWebServiceClient == null) {
			return null;
		}

		try {
			String response = _jsonWebServiceClient.doGet(url, parameters);

			return _jsonFactory.createJSONObject(response);
		}
		catch (JSONWebServiceInvocationException
					jsonWebServiceInvocationException) {

			if (jsonWebServiceInvocationException.getStatus() ==
					HttpServletResponse.SC_NOT_FOUND) {

				return null;
			}

			_sendEmail(jsonWebServiceInvocationException, parameters);

			throw jsonWebServiceInvocationException;
		}
		catch (Exception exception) {
			_sendEmail(exception, parameters);

			throw exception;
		}
	}

	private Contact _importContactByEmailAddress(String emailAddress)
		throws Exception {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("emailAddress", emailAddress);

		JSONObject jsonObject = _getToJSONObject(
			_URL_API_REST_USERS + "email_address", parameters);

		if (jsonObject == null) {
			return null;
		}

		return _contactLocalService.addContact(
			jsonObject.getString("uuid"), _getDefaultUserId(),
			jsonObject.getString("firstName"),
			jsonObject.getString("middleName"),
			jsonObject.getString("lastName"), emailAddress,
			jsonObject.getString("languageId"),
			_isEmailAddressVerified(jsonObject));
	}

	private Contact _importContactByUuid(String uuid) throws Exception {
		JSONObject jsonObject = _getToJSONObject(
			_URL_API_REST_USERS + uuid, Collections.emptyMap());

		if (jsonObject == null) {
			return null;
		}

		return _contactLocalService.addContact(
			uuid, _getDefaultUserId(), jsonObject.getString("firstName"),
			jsonObject.getString("middleName"),
			jsonObject.getString("lastName"),
			jsonObject.getString("emailAddress"),
			jsonObject.getString("languageId"),
			_isEmailAddressVerified(jsonObject));
	}

	private boolean _isEmailAddressVerified(JSONObject jsonObject)
		throws Exception {

		JSONArray rolesJSONArray = jsonObject.getJSONArray("roles");

		if (rolesJSONArray != null) {
			for (int i = 0; i < rolesJSONArray.length(); i++) {
				JSONObject roleJSONObject = rolesJSONArray.getJSONObject(i);

				String name = roleJSONObject.getString("name");

				if (Validator.isNotNull(name) && name.equals("Verified User")) {
					return true;
				}
			}
		}

		return false;
	}

	private void _sendEmail(
		Exception exception, Map<String, String> parameters) {

		if (Validator.isNull(_errorEmailAddress)) {
			return;
		}

		StringBundler sb = new StringBundler(5);

		if (parameters != null) {
			sb.append("<strong>Parameters: </strong><br />");
			sb.append(MapUtil.toString(parameters));
			sb.append("<br /><br />");
		}

		sb.append("<strong>Stack Trace:</strong><br />");

		sb.append(
			StringUtil.replace(
				StackTraceUtil.getStackTrace(exception), CharPool.NEW_LINE,
				"<br />"));

		try {
			InternetAddress from = new InternetAddress("no-reply@liferay.com");
			InternetAddress to = new InternetAddress(_errorEmailAddress);

			MailMessage mailMessage = new MailMessage(
				from, to, "Auto Generated Web API Error Message", sb.toString(),
				true);

			_mailService.sendEmail(mailMessage);
		}
		catch (AddressException addressException) {
			_log.error(addressException, addressException);
		}
	}

	private static final String _URL_API_REST_USERS = "/osb-entity-web/users/";

	private static final Log _log = LogFactoryUtil.getLog(
		WebContactIdentityProvider.class);

	@Reference
	private ContactLocalService _contactLocalService;

	private long _defaultUserId;
	private String _errorEmailAddress;

	@Reference
	private JSONFactory _jsonFactory;

	private JSONWebServiceClient _jsonWebServiceClient;

	@Reference
	private JSONWebServiceClientFactory _jsonWebServiceClientFactory;

	@Reference
	private MailService _mailService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	@Reference
	private UserLocalService _userLocalService;

}
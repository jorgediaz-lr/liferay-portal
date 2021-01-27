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

package com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactRoleSerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface ContactRoleResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<ContactRole>
			getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyContactByUuidContactUuidRolesPageHttpResponse(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyCustomerContactByUuidContactUuidRolesPageHttpResponse(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
				String accountKey, String contactEmailAddress,
				Pagination pagination)
		throws Exception;

	public Page<ContactRole>
			getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyWorkerContactByUuidContactUuidRolesPageHttpResponse(
				String accountKey, String contactUuid, Pagination pagination)
		throws Exception;

	public Page<ContactRole> getContactRolesPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getContactRolesPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public ContactRole postContactRole(
			String agentName, String agentUID, ContactRole contactRole)
		throws Exception;

	public HttpInvoker.HttpResponse postContactRoleHttpResponse(
			String agentName, String agentUID, ContactRole contactRole)
		throws Exception;

	public void deleteContactRole(
			String agentName, String agentUID, String contactRoleKey)
		throws Exception;

	public HttpInvoker.HttpResponse deleteContactRoleHttpResponse(
			String agentName, String agentUID, String contactRoleKey)
		throws Exception;

	public ContactRole getContactRole(String contactRoleKey) throws Exception;

	public HttpInvoker.HttpResponse getContactRoleHttpResponse(
			String contactRoleKey)
		throws Exception;

	public ContactRole putContactRole(
			String agentName, String agentUID, String contactRoleKey,
			ContactRole contactRole)
		throws Exception;

	public HttpInvoker.HttpResponse putContactRoleHttpResponse(
			String agentName, String agentUID, String contactRoleKey,
			ContactRole contactRole)
		throws Exception;

	public void deleteContactRoleContactRolePermission(
			String agentName, String agentUID, String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteContactRoleContactRolePermissionHttpResponse(
				String agentName, String agentUID, String contactRoleKey,
				ContactRolePermission contactRolePermission)
		throws Exception;

	public void putContactRoleContactRolePermission(
			String agentName, String agentUID, String contactRoleKey,
			ContactRolePermission contactRolePermission)
		throws Exception;

	public HttpInvoker.HttpResponse
			putContactRoleContactRolePermissionHttpResponse(
				String agentName, String agentUID, String contactRoleKey,
				ContactRolePermission contactRolePermission)
		throws Exception;

	public ContactRole getContactRoleContactRoleTypeContactRoleName(
			String contactRoleType, String contactRoleName)
		throws Exception;

	public HttpInvoker.HttpResponse
			getContactRoleContactRoleTypeContactRoleNameHttpResponse(
				String contactRoleType, String contactRoleName)
		throws Exception;

	public Page<ContactRole> getTeamTeamKeyContactByEmailAddressRolesPage(
			String teamKey, String emailAddress, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getTeamTeamKeyContactByEmailAddressRolesPageHttpResponse(
				String teamKey, String emailAddress, Pagination pagination)
		throws Exception;

	public Page<ContactRole> getTeamTeamKeyContactByUuidContactUuidRolesPage(
			String teamKey, String contactUuid, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getTeamTeamKeyContactByUuidContactUuidRolesPageHttpResponse(
				String teamKey, String contactUuid, Pagination pagination)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public ContactRoleResource build() {
			return new ContactRoleResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		private Builder() {
		}

		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class ContactRoleResourceImpl implements ContactRoleResource {

		public Page<ContactRole>
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					String accountKey, String contactEmailAddress,
					Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
					accountKey, contactEmailAddress, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
					String accountKey, String contactEmailAddress,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles",
				accountKey, contactEmailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole>
				getAccountAccountKeyContactByUuidContactUuidRolesPage(
					String accountKey, String contactUuid,
					Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyContactByUuidContactUuidRolesPageHttpResponse(
					accountKey, contactUuid, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyContactByUuidContactUuidRolesPageHttpResponse(
					String accountKey, String contactUuid,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles",
				accountKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole>
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
					String accountKey, String contactEmailAddress,
					Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
					accountKey, contactEmailAddress, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
					String accountKey, String contactEmailAddress,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-email-address/{contactEmailAddress}/roles",
				accountKey, contactEmailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole>
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
					String accountKey, String contactUuid,
					Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPageHttpResponse(
					accountKey, contactUuid, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyCustomerContactByUuidContactUuidRolesPageHttpResponse(
					String accountKey, String contactUuid,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-uuid/{contactUuid}/roles",
				accountKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole>
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
					String accountKey, String contactEmailAddress,
					Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
					accountKey, contactEmailAddress, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageHttpResponse(
					String accountKey, String contactEmailAddress,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-email-address/{contactEmailAddress}/roles",
				accountKey, contactEmailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole>
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
					String accountKey, String contactUuid,
					Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPageHttpResponse(
					accountKey, contactUuid, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyWorkerContactByUuidContactUuidRolesPageHttpResponse(
					String accountKey, String contactUuid,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-uuid/{contactUuid}/roles",
				accountKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole> getContactRolesPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactRolesPageHttpResponse(
					search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getContactRolesPageHttpResponse(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (search != null) {
				httpInvoker.parameter("search", String.valueOf(search));
			}

			if (filterString != null) {
				httpInvoker.parameter("filter", filterString);
			}

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			if (sortString != null) {
				httpInvoker.parameter("sort", sortString);
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/contact-roles");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public ContactRole postContactRole(
				String agentName, String agentUID, ContactRole contactRole)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = postContactRoleHttpResponse(
				agentName, agentUID, contactRole);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactRoleSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse postContactRoleHttpResponse(
				String agentName, String agentUID, ContactRole contactRole)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactRole.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/contact-roles");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteContactRole(
				String agentName, String agentUID, String contactRoleKey)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteContactRoleHttpResponse(
					agentName, agentUID, contactRoleKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse deleteContactRoleHttpResponse(
				String agentName, String agentUID, String contactRoleKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}",
				contactRoleKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public ContactRole getContactRole(String contactRoleKey)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = getContactRoleHttpResponse(
				contactRoleKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactRoleSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getContactRoleHttpResponse(
				String contactRoleKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}",
				contactRoleKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public ContactRole putContactRole(
				String agentName, String agentUID, String contactRoleKey,
				ContactRole contactRole)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = putContactRoleHttpResponse(
				agentName, agentUID, contactRoleKey, contactRole);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactRoleSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse putContactRoleHttpResponse(
				String agentName, String agentUID, String contactRoleKey,
				ContactRole contactRole)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactRole.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}",
				contactRoleKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteContactRoleContactRolePermission(
				String agentName, String agentUID, String contactRoleKey,
				ContactRolePermission contactRolePermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteContactRoleContactRolePermissionHttpResponse(
					agentName, agentUID, contactRoleKey, contactRolePermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteContactRoleContactRolePermissionHttpResponse(
					String agentName, String agentUID, String contactRoleKey,
					ContactRolePermission contactRolePermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}/contact-role-permissions",
				contactRoleKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putContactRoleContactRolePermission(
				String agentName, String agentUID, String contactRoleKey,
				ContactRolePermission contactRolePermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putContactRoleContactRolePermissionHttpResponse(
					agentName, agentUID, contactRoleKey, contactRolePermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				putContactRoleContactRolePermissionHttpResponse(
					String agentName, String agentUID, String contactRoleKey,
					ContactRolePermission contactRolePermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				contactRolePermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}/contact-role-permissions",
				contactRoleKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public ContactRole getContactRoleContactRoleTypeContactRoleName(
				String contactRoleType, String contactRoleName)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactRoleContactRoleTypeContactRoleNameHttpResponse(
					contactRoleType, contactRoleName);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return ContactRoleSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getContactRoleContactRoleTypeContactRoleNameHttpResponse(
					String contactRoleType, String contactRoleName)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/contact-roles/{contactRoleType}/{contactRoleName}",
				contactRoleType, contactRoleName);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole> getTeamTeamKeyContactByEmailAddressRolesPage(
				String teamKey, String emailAddress, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamTeamKeyContactByEmailAddressRolesPageHttpResponse(
					teamKey, emailAddress, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getTeamTeamKeyContactByEmailAddressRolesPageHttpResponse(
					String teamKey, String emailAddress, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address/{emailAddress}/roles",
				teamKey, emailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<ContactRole>
				getTeamTeamKeyContactByUuidContactUuidRolesPage(
					String teamKey, String contactUuid, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamTeamKeyContactByUuidContactUuidRolesPageHttpResponse(
					teamKey, contactUuid, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, ContactRoleSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getTeamTeamKeyContactByUuidContactUuidRolesPageHttpResponse(
					String teamKey, String contactUuid, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles",
				teamKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private ContactRoleResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			ContactRoleResource.class.getName());

		private Builder _builder;

	}

}
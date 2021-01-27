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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AuditEntrySerDes;

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
public interface AuditEntryResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<AuditEntry> getAccountAccountKeyAuditEntriesPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyAuditEntriesPageHttpResponse(
				String accountKey, Pagination pagination)
		throws Exception;

	public AuditEntry getAuditEntry(String auditEntryKey) throws Exception;

	public HttpInvoker.HttpResponse getAuditEntryHttpResponse(
			String auditEntryKey)
		throws Exception;

	public Page<AuditEntry> getContactRoleContactRoleKeyAuditEntriesPage(
			String contactRoleKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getContactRoleContactRoleKeyAuditEntriesPageHttpResponse(
				String contactRoleKey, Pagination pagination)
		throws Exception;

	public Page<AuditEntry> getContactByUuidContactUuidAuditEntriesPage(
			String contactUuid, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getContactByUuidContactUuidAuditEntriesPageHttpResponse(
				String contactUuid, Pagination pagination)
		throws Exception;

	public Page<AuditEntry> getTeamRoleTeamRoleKeyAuditEntriesPage(
			String teamRoleKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getTeamRoleTeamRoleKeyAuditEntriesPageHttpResponse(
				String teamRoleKey, Pagination pagination)
		throws Exception;

	public Page<AuditEntry> getTeamTeamKeyAuditEntriesPage(
			String teamKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse getTeamTeamKeyAuditEntriesPageHttpResponse(
			String teamKey, Pagination pagination)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public AuditEntryResource build() {
			return new AuditEntryResourceImpl(this);
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

	public static class AuditEntryResourceImpl implements AuditEntryResource {

		public Page<AuditEntry> getAccountAccountKeyAuditEntriesPage(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyAuditEntriesPageHttpResponse(
					accountKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, AuditEntrySerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyAuditEntriesPageHttpResponse(
					String accountKey, Pagination pagination)
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
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/audit-entries",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public AuditEntry getAuditEntry(String auditEntryKey) throws Exception {
			HttpInvoker.HttpResponse httpResponse = getAuditEntryHttpResponse(
				auditEntryKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return AuditEntrySerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getAuditEntryHttpResponse(
				String auditEntryKey)
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
						"/o/koroneiki-rest/v1.0/audit-entries/{auditEntryKey}",
				auditEntryKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<AuditEntry> getContactRoleContactRoleKeyAuditEntriesPage(
				String contactRoleKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactRoleContactRoleKeyAuditEntriesPageHttpResponse(
					contactRoleKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, AuditEntrySerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getContactRoleContactRoleKeyAuditEntriesPageHttpResponse(
					String contactRoleKey, Pagination pagination)
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
						"/o/koroneiki-rest/v1.0/contact-roles/{contactRoleKey}/audit-entries",
				contactRoleKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<AuditEntry> getContactByUuidContactUuidAuditEntriesPage(
				String contactUuid, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactByUuidContactUuidAuditEntriesPageHttpResponse(
					contactUuid, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, AuditEntrySerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getContactByUuidContactUuidAuditEntriesPageHttpResponse(
					String contactUuid, Pagination pagination)
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
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/audit-entries",
				contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<AuditEntry> getTeamRoleTeamRoleKeyAuditEntriesPage(
				String teamRoleKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamRoleTeamRoleKeyAuditEntriesPageHttpResponse(
					teamRoleKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, AuditEntrySerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getTeamRoleTeamRoleKeyAuditEntriesPageHttpResponse(
					String teamRoleKey, Pagination pagination)
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
						"/o/koroneiki-rest/v1.0/team-roles/{teamRoleKey}/audit-entries",
				teamRoleKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<AuditEntry> getTeamTeamKeyAuditEntriesPage(
				String teamKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamTeamKeyAuditEntriesPageHttpResponse(teamKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, AuditEntrySerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getTeamTeamKeyAuditEntriesPageHttpResponse(
					String teamKey, Pagination pagination)
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
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/audit-entries",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private AuditEntryResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			AuditEntryResource.class.getName());

		private Builder _builder;

	}

}
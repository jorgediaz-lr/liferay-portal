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

package com.liferay.osb.provisioning.rest.client.resource.v1_0;

import com.liferay.osb.provisioning.rest.client.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.client.http.HttpInvoker;
import com.liferay.osb.provisioning.rest.client.pagination.Page;
import com.liferay.osb.provisioning.rest.client.pagination.Pagination;
import com.liferay.osb.provisioning.rest.client.problem.Problem;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.LicenseKeySerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public interface LicenseKeyResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<LicenseKey> getLicenseKeysPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getLicenseKeysPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Page<LicenseKey> postLicenseKeysPage(LicenseKey[] licenseKeys)
		throws Exception;

	public HttpInvoker.HttpResponse postLicenseKeysPageHttpResponse(
			LicenseKey[] licenseKeys)
		throws Exception;

	public void putLicenseKeyActivate(Long[] licenseKeyIds) throws Exception;

	public HttpInvoker.HttpResponse putLicenseKeyActivateHttpResponse(
			Long[] licenseKeyIds)
		throws Exception;

	public void putLicenseKeyDeactivate(Long[] licenseKeyIds) throws Exception;

	public HttpInvoker.HttpResponse putLicenseKeyDeactivateHttpResponse(
			Long[] licenseKeyIds)
		throws Exception;

	public void getLicenseKeyDownload(Long[] licenseKeyIds) throws Exception;

	public HttpInvoker.HttpResponse getLicenseKeyDownloadHttpResponse(
			Long[] licenseKeyIds)
		throws Exception;

	public void getLicenseKeyDownloadLicenseKey(Long licenseKeyId)
		throws Exception;

	public HttpInvoker.HttpResponse getLicenseKeyDownloadLicenseKeyHttpResponse(
			Long licenseKeyId)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public LicenseKeyResource build() {
			return new LicenseKeyResourceImpl(this);
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

		public Builder parameters(String... parameters) {
			if ((parameters.length % 2) != 0) {
				throw new IllegalArgumentException(
					"Parameters length is not an even number");
			}

			for (int i = 0; i < parameters.length; i += 2) {
				String parameterName = String.valueOf(parameters[i]);
				String parameterValue = String.valueOf(parameters[i + 1]);

				_parameters.put(parameterName, parameterValue);
			}

			return this;
		}

		private Builder() {
		}

		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "";
		private String _password = "";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class LicenseKeyResourceImpl implements LicenseKeyResource {

		public Page<LicenseKey> getLicenseKeysPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getLicenseKeysPageHttpResponse(
					search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, LicenseKeySerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getLicenseKeysPageHttpResponse(
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
					_builder._port + "/o/provisioning-rest/v1.0/license-keys");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<LicenseKey> postLicenseKeysPage(LicenseKey[] licenseKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postLicenseKeysPageHttpResponse(licenseKeys);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, LicenseKeySerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse postLicenseKeysPageHttpResponse(
				LicenseKey[] licenseKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					licenseKeys
				).map(
					value -> String.valueOf(value)
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

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

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/provisioning-rest/v1.0/license-keys");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putLicenseKeyActivate(Long[] licenseKeyIds)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putLicenseKeyActivateHttpResponse(licenseKeyIds);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

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

		public HttpInvoker.HttpResponse putLicenseKeyActivateHttpResponse(
				Long[] licenseKeyIds)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					licenseKeyIds
				).map(
					value -> String.valueOf(value)
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

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

			if (licenseKeyIds != null) {
				for (int i = 0; i < licenseKeyIds.length; i++) {
					httpInvoker.parameter(
						"licenseKeyIds", String.valueOf(licenseKeyIds[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/provisioning-rest/v1.0/license-keys/activate");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putLicenseKeyDeactivate(Long[] licenseKeyIds)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putLicenseKeyDeactivateHttpResponse(licenseKeyIds);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

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

		public HttpInvoker.HttpResponse putLicenseKeyDeactivateHttpResponse(
				Long[] licenseKeyIds)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					licenseKeyIds
				).map(
					value -> String.valueOf(value)
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

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

			if (licenseKeyIds != null) {
				for (int i = 0; i < licenseKeyIds.length; i++) {
					httpInvoker.parameter(
						"licenseKeyIds", String.valueOf(licenseKeyIds[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/provisioning-rest/v1.0/license-keys/deactivate");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void getLicenseKeyDownload(Long[] licenseKeyIds)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getLicenseKeyDownloadHttpResponse(licenseKeyIds);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}
		}

		public HttpInvoker.HttpResponse getLicenseKeyDownloadHttpResponse(
				Long[] licenseKeyIds)
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

			if (licenseKeyIds != null) {
				for (int i = 0; i < licenseKeyIds.length; i++) {
					httpInvoker.parameter(
						"licenseKeyIds", String.valueOf(licenseKeyIds[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/provisioning-rest/v1.0/license-keys/download");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void getLicenseKeyDownloadLicenseKey(Long licenseKeyId)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getLicenseKeyDownloadLicenseKeyHttpResponse(licenseKeyId);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}
		}

		public HttpInvoker.HttpResponse
				getLicenseKeyDownloadLicenseKeyHttpResponse(Long licenseKeyId)
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
						"/o/provisioning-rest/v1.0/license-keys/download/{licenseKeyId}");

			httpInvoker.path("licenseKeyId", licenseKeyId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private LicenseKeyResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			LicenseKeyResource.class.getName());

		private Builder _builder;

	}

}
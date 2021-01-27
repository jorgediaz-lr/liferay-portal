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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamPermission;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamSerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface TeamResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<Team> getAccountAccountKeyAssignedTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountAccountKeyAssignedTeamsPageHttpResponse(
				String accountKey, Pagination pagination)
		throws Exception;

	public Page<Team> getAccountAccountKeyTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse getAccountAccountKeyTeamsPageHttpResponse(
			String accountKey, Pagination pagination)
		throws Exception;

	public Team postAccountAccountKeyTeam(
			String agentName, String agentUID, String accountKey, Team team)
		throws Exception;

	public HttpInvoker.HttpResponse postAccountAccountKeyTeamHttpResponse(
			String agentName, String agentUID, String accountKey, Team team)
		throws Exception;

	public Page<Team> getTeamsPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getTeamsPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Page<Team> getTeamByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getTeamByExternalLinkDomainEntityNameEntityPageHttpResponse(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception;

	public void deleteTeam(String agentName, String agentUID, String teamKey)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamHttpResponse(
			String agentName, String agentUID, String teamKey)
		throws Exception;

	public Team getTeam(String teamKey) throws Exception;

	public HttpInvoker.HttpResponse getTeamHttpResponse(String teamKey)
		throws Exception;

	public Team putTeam(
			String agentName, String agentUID, String teamKey, Team team)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamHttpResponse(
			String agentName, String agentUID, String teamKey, Team team)
		throws Exception;

	public void deleteTeamContactByEmailAddress(
			String agentName, String agentUID, String teamKey,
			String[] emailAddresses)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamContactByEmailAddressHttpResponse(
			String agentName, String agentUID, String teamKey,
			String[] emailAddresses)
		throws Exception;

	public void putTeamContactByEmailAddress(
			String agentName, String agentUID, String teamKey,
			String[] emailAddresses)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamContactByEmailAddressHttpResponse(
			String agentName, String agentUID, String teamKey,
			String[] emailAddresses)
		throws Exception;

	public void deleteTeamContactByEmailAddressRole(
			String agentName, String agentUID, String teamKey,
			String emailAddress, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteTeamContactByEmailAddressRoleHttpResponse(
				String agentName, String agentUID, String teamKey,
				String emailAddress, String[] contactRoleKeys)
		throws Exception;

	public void putTeamContactByEmailAddressRole(
			String agentName, String agentUID, String teamKey,
			String emailAddress, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putTeamContactByEmailAddressRoleHttpResponse(
				String agentName, String agentUID, String teamKey,
				String emailAddress, String[] contactRoleKeys)
		throws Exception;

	public void deleteTeamContactByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamContactByUuidHttpResponse(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public void putTeamContactByUuid(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamContactByUuidHttpResponse(
			String agentName, String agentUID, String teamKey,
			String[] contactUuids)
		throws Exception;

	public void deleteTeamContactByUuidContactUuidRole(
			String agentName, String agentUID, String teamKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteTeamContactByUuidContactUuidRoleHttpResponse(
				String agentName, String agentUID, String teamKey,
				String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void putTeamContactByUuidContactUuidRole(
			String agentName, String agentUID, String teamKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putTeamContactByUuidContactUuidRoleHttpResponse(
				String agentName, String agentUID, String teamKey,
				String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void deleteTeamTeamPermission(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamTeamPermissionHttpResponse(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception;

	public void putTeamTeamPermission(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamTeamPermissionHttpResponse(
			String agentName, String agentUID, String teamKey,
			TeamPermission teamPermission)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public TeamResource build() {
			return new TeamResourceImpl(this);
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

	public static class TeamResourceImpl implements TeamResource {

		public Page<Team> getAccountAccountKeyAssignedTeamsPage(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyAssignedTeamsPageHttpResponse(
					accountKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, TeamSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyAssignedTeamsPageHttpResponse(
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
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Team> getAccountAccountKeyTeamsPage(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyTeamsPageHttpResponse(
					accountKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, TeamSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyTeamsPageHttpResponse(
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
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/teams",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Team postAccountAccountKeyTeam(
				String agentName, String agentUID, String accountKey, Team team)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postAccountAccountKeyTeamHttpResponse(
					agentName, agentUID, accountKey, team);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return TeamSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse postAccountAccountKeyTeamHttpResponse(
				String agentName, String agentUID, String accountKey, Team team)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(team.toString(), "application/json");

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
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/teams",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Team> getTeamsPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = getTeamsPageHttpResponse(
				search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, TeamSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getTeamsPageHttpResponse(
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
					_builder._port + "/o/koroneiki-rest/v1.0/teams");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Team> getTeamByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamByExternalLinkDomainEntityNameEntityPageHttpResponse(
					domain, entityName, entityId, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return Page.of(content, TeamSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getTeamByExternalLinkDomainEntityNameEntityPageHttpResponse(
					String domain, String entityName, String entityId,
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
						"/o/koroneiki-rest/v1.0/teams/by-external-link/{domain}/{entityName}/{entityId}",
				domain, entityName, entityId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeam(
				String agentName, String agentUID, String teamKey)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = deleteTeamHttpResponse(
				agentName, agentUID, teamKey);

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

		public HttpInvoker.HttpResponse deleteTeamHttpResponse(
				String agentName, String agentUID, String teamKey)
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
					_builder._port + "/o/koroneiki-rest/v1.0/teams/{teamKey}",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Team getTeam(String teamKey) throws Exception {
			HttpInvoker.HttpResponse httpResponse = getTeamHttpResponse(
				teamKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return TeamSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getTeamHttpResponse(String teamKey)
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
					_builder._port + "/o/koroneiki-rest/v1.0/teams/{teamKey}",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Team putTeam(
				String agentName, String agentUID, String teamKey, Team team)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = putTeamHttpResponse(
				agentName, agentUID, teamKey, team);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return TeamSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse putTeamHttpResponse(
				String agentName, String agentUID, String teamKey, Team team)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(team.toString(), "application/json");

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
					_builder._port + "/o/koroneiki-rest/v1.0/teams/{teamKey}",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamContactByEmailAddress(
				String agentName, String agentUID, String teamKey,
				String[] emailAddresses)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByEmailAddressHttpResponse(
					agentName, agentUID, teamKey, emailAddresses);

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
				deleteTeamContactByEmailAddressHttpResponse(
					String agentName, String agentUID, String teamKey,
					String[] emailAddresses)
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

			if (emailAddresses != null) {
				for (int i = 0; i < emailAddresses.length; i++) {
					httpInvoker.parameter(
						"emailAddresses", String.valueOf(emailAddresses[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamContactByEmailAddress(
				String agentName, String agentUID, String teamKey,
				String[] emailAddresses)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByEmailAddressHttpResponse(
					agentName, agentUID, teamKey, emailAddresses);

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
				putTeamContactByEmailAddressHttpResponse(
					String agentName, String agentUID, String teamKey,
					String[] emailAddresses)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					emailAddresses
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

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (emailAddresses != null) {
				for (int i = 0; i < emailAddresses.length; i++) {
					httpInvoker.parameter(
						"emailAddresses", String.valueOf(emailAddresses[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-email-address",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamContactByEmailAddressRole(
				String agentName, String agentUID, String teamKey,
				String emailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByEmailAddressRoleHttpResponse(
					agentName, agentUID, teamKey, emailAddress,
					contactRoleKeys);

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
				deleteTeamContactByEmailAddressRoleHttpResponse(
					String agentName, String agentUID, String teamKey,
					String emailAddress, String[] contactRoleKeys)
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

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
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

		public void putTeamContactByEmailAddressRole(
				String agentName, String agentUID, String teamKey,
				String emailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByEmailAddressRoleHttpResponse(
					agentName, agentUID, teamKey, emailAddress,
					contactRoleKeys);

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
				putTeamContactByEmailAddressRoleHttpResponse(
					String agentName, String agentUID, String teamKey,
					String emailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactRoleKeys
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

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
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

		public void deleteTeamContactByUuid(
				String agentName, String agentUID, String teamKey,
				String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByUuidHttpResponse(
					agentName, agentUID, teamKey, contactUuids);

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

		public HttpInvoker.HttpResponse deleteTeamContactByUuidHttpResponse(
				String agentName, String agentUID, String teamKey,
				String[] contactUuids)
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

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamContactByUuid(
				String agentName, String agentUID, String teamKey,
				String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByUuidHttpResponse(
					agentName, agentUID, teamKey, contactUuids);

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

		public HttpInvoker.HttpResponse putTeamContactByUuidHttpResponse(
				String agentName, String agentUID, String teamKey,
				String[] contactUuids)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactUuids
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

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamContactByUuidContactUuidRole(
				String agentName, String agentUID, String teamKey,
				String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByUuidContactUuidRoleHttpResponse(
					agentName, agentUID, teamKey, contactUuid, contactRoleKeys);

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
				deleteTeamContactByUuidContactUuidRoleHttpResponse(
					String agentName, String agentUID, String teamKey,
					String contactUuid, String[] contactRoleKeys)
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

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
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

		public void putTeamContactByUuidContactUuidRole(
				String agentName, String agentUID, String teamKey,
				String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByUuidContactUuidRoleHttpResponse(
					agentName, agentUID, teamKey, contactUuid, contactRoleKeys);

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
				putTeamContactByUuidContactUuidRoleHttpResponse(
					String agentName, String agentUID, String teamKey,
					String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactRoleKeys
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

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
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

		public void deleteTeamTeamPermission(
				String agentName, String agentUID, String teamKey,
				TeamPermission teamPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamTeamPermissionHttpResponse(
					agentName, agentUID, teamKey, teamPermission);

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

		public HttpInvoker.HttpResponse deleteTeamTeamPermissionHttpResponse(
				String agentName, String agentUID, String teamKey,
				TeamPermission teamPermission)
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
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/team-permissions",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamTeamPermission(
				String agentName, String agentUID, String teamKey,
				TeamPermission teamPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamTeamPermissionHttpResponse(
					agentName, agentUID, teamKey, teamPermission);

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

		public HttpInvoker.HttpResponse putTeamTeamPermissionHttpResponse(
				String agentName, String agentUID, String teamKey,
				TeamPermission teamPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(teamPermission.toString(), "application/json");

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
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/team-permissions",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private TeamResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			TeamResource.class.getName());

		private Builder _builder;

	}

}
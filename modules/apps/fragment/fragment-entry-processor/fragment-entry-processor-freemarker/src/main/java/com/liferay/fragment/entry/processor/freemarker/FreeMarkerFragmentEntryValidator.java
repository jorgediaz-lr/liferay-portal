/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.entry.processor.freemarker;

import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.fragment.entry.processor.freemarker.internal.configuration.FreeMarkerFragmentEntryProcessorConfiguration;
import com.liferay.fragment.exception.FragmentEntryContentException;
import com.liferay.fragment.input.template.parser.InputTemplateNode;
import com.liferay.fragment.processor.FragmentEntryValidator;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.petra.io.DummyWriter;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.DummyHttpServletResponse;
import com.liferay.portal.kernel.servlet.taglib.aui.ScriptData;
import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = "fragment.entry.processor.priority:Integer=1",
	service = FragmentEntryValidator.class
)
public class FreeMarkerFragmentEntryValidator
	implements FragmentEntryValidator {

	@Override
	public void validateFragmentEntryHTML(
			String html, JSONObject configurationJSONObject, Locale locale)
		throws PortalException {

		FreeMarkerFragmentEntryProcessorConfiguration
			freeMarkerFragmentEntryProcessorConfiguration =
				_configurationProvider.getCompanyConfiguration(
					FreeMarkerFragmentEntryProcessorConfiguration.class,
					CompanyThreadLocal.getCompanyId());

		if (!freeMarkerFragmentEntryProcessorConfiguration.enable() ||
			!_isFreeMarkerTemplate(html)) {

			return;
		}

		HttpServletRequest httpServletRequest = null;
		HttpServletResponse httpServletResponse = null;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext != null) {
			httpServletRequest = serviceContext.getRequest();
			httpServletResponse = serviceContext.getResponse();
		}

		if (_log.isDebugEnabled()) {
			_log.debug("=== Fragment Entry Validator Debug Info ===");
			_log.debug("httpServletRequest: " + httpServletRequest);

			if (httpServletRequest != null) {
				String remoteUser = httpServletRequest.getRemoteUser();
				String timestamp = new SimpleDateFormat(
					"dd/MMM/yyyy:HH:mm:ss Z"
				).format(
					new Date()
				);
				String requestLine = StringBundler.concat(
					httpServletRequest.getMethod(), " ",
					httpServletRequest.getRequestURI(),
					(httpServletRequest.getQueryString() != null) ?
						"?" + httpServletRequest.getQueryString() : "",
					" ", httpServletRequest.getProtocol());
				String referer = httpServletRequest.getHeader("Referer");
				String userAgent = httpServletRequest.getHeader("User-Agent");

				_log.debug(
					String.format(
						"httpServletRequest: %s - %s [%s] \"%s\" \"%s\" \"%s\"",
						httpServletRequest.getRemoteAddr(),
						(remoteUser != null) ? remoteUser : "-", timestamp,
						requestLine, (referer != null) ? referer : "-",
						(userAgent != null) ? userAgent : "-"));

				ThemeDisplay themeDisplay =
					(ThemeDisplay)httpServletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				_log.debug(
					"httpServletRequest.getAttribute(THEME_DISPLAY): " +
						themeDisplay);

				if (themeDisplay != null) {
					_log.debug(
						String.format(
							"ThemeDisplay - Company: %d, Group: %d, User: %s (ID: %d), Layout: %d, SignedIn: %b, Locale: %s, TimeZone: %s",
							themeDisplay.getCompanyId(),
							themeDisplay.getScopeGroupId(),
							themeDisplay.getUser(
							).getFullName(),
							themeDisplay.getUserId(), themeDisplay.getPlid(),
							themeDisplay.isSignedIn(), themeDisplay.getLocale(),
							themeDisplay.getTimeZone(
							).getID()));
					_log.debug(
						StringBundler.concat(
							"Portal URL: ", themeDisplay.getPortalURL(),
							", Current URL: ", themeDisplay.getURLCurrent()));
				}

				HttpSession httpSession = httpServletRequest.getSession(false);

				_log.debug(
					"Session: " +
						((httpSession != null) ? httpSession.getId() : "null"));
			}

			_log.debug("serviceContext: " + serviceContext);

			if (serviceContext != null) {
				_log.debug(
					String.format(
						"ServiceContext - Company: %d, Group: %d, User: %d, Command: %s, UUID: %s",
						serviceContext.getCompanyId(),
						serviceContext.getScopeGroupId(),
						serviceContext.getUserId(), serviceContext.getCommand(),
						serviceContext.getUuid()));
				_log.debug(
					"ServiceContext Current URL: " +
						serviceContext.getCurrentURL());
			}

			_log.debug(
				String.format(
					"Thread: %s, Export: %b, Import: %b, Staging: %b",
					Thread.currentThread(
					).getName(),
					ExportImportThreadLocal.isExportInProcess(),
					ExportImportThreadLocal.isImportInProcess(),
					ExportImportThreadLocal.isStagingInProcess()));

			_log.debug("===========================================");
		}

		if ((httpServletRequest == null) ||
			(httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY) == null)) {

			return;
		}

		if (httpServletResponse == null) {
			httpServletResponse = new DummyHttpServletResponse();
		}

		ScriptData scriptData = (ScriptData)httpServletRequest.getAttribute(
			WebKeys.AUI_SCRIPT_DATA);

		try {
			httpServletRequest.setAttribute(
				WebKeys.AUI_SCRIPT_DATA, new ScriptData());

			JSONObject configurationDefaultValuesJSONObject =
				_fragmentEntryConfigurationParser.
					getConfigurationDefaultValuesJSONObject(
						configurationJSONObject);

			Template template = TemplateManagerUtil.getTemplate(
				TemplateConstants.LANG_TYPE_FTL,
				new StringTemplateResource("template_id", "[#ftl] " + html),
				true);

			template.putAll(
				HashMapBuilder.<String, Object>put(
					"configuration", configurationDefaultValuesJSONObject
				).put(
					"fragmentElementId", StringPool.BLANK
				).put(
					"fragmentEntryLinkNamespace", StringPool.BLANK
				).put(
					"fragmentName", StringPool.BLANK
				).put(
					"input",
					new InputTemplateNode(
						StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
						false, "name", false, false, false, false, "type",
						"value", Collections.emptyMap())
				).put(
					"layoutMode", Constants.VIEW
				).putAll(
					_fragmentEntryConfigurationParser.getContextObjects(
						configurationDefaultValuesJSONObject,
						configurationJSONObject, null, new long[0])
				).build());

			template.prepareTaglib(httpServletRequest, httpServletResponse);

			template.prepare(httpServletRequest);

			template.processTemplate(new DummyWriter());
		}
		catch (TemplateException templateException) {
			throw new FragmentEntryContentException(
				_getMessage(templateException, locale), templateException);
		}
		finally {
			httpServletRequest.setAttribute(
				WebKeys.AUI_SCRIPT_DATA, scriptData);
		}
	}

	private String _getMessage(
		TemplateException templateException, Locale locale) {

		String message = _language.get(locale, "freemarker-syntax-is-invalid");

		Throwable causeThrowable = templateException.getCause();

		String causeThrowableMessage = causeThrowable.getLocalizedMessage();

		if (Validator.isNotNull(causeThrowableMessage)) {
			message = message + "\n\n" + causeThrowableMessage;
		}

		return message;
	}

	private boolean _isFreeMarkerTemplate(String html) {
		if (html.contains("${") || html.contains("[#") || html.contains("[@")) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog("LPP-60743");

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private Language _language;

}
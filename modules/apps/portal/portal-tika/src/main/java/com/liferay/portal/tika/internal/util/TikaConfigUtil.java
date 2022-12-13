/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.tika.internal.util;

import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tika.internal.configuration.TikaConfiguration;

import java.io.IOException;

import java.util.Map;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Shuyang Zhou
 * @author Jorge Díaz
 */
@Component(
	configurationPid = "com.liferay.portal.tika.internal.configuration.TikaConfiguration",
	service = {}
)
public class TikaConfigUtil {

	public static String[] getTextExtractionForkProcessMimeTypes() {
		return _tikaConfiguration.textExtractionForkProcessMimeTypes();
	}

	public static TikaConfig getTikaConfig() {
		return _tikaConfig;
	}

	public static String getTikaConfigXml() throws IOException {
		String tikaConfigXml = _tikaConfiguration.tikaConfigXml();

		if (Validator.isNotNull(tikaConfigXml)) {
			return tikaConfigXml;
		}

		return StreamUtil.toString(
			TikaConfigUtil.class.getResourceAsStream("dependencies/tika.xml"));
	}

	public static Parser getTikaParser() {
		return _parser;
	}

	public static boolean isTextExtractionForkProcessEnabled() {
		return _tikaConfiguration.textExtractionForkProcessEnabled();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_tikaConfiguration = ConfigurableUtil.createConfigurable(
			TikaConfiguration.class, properties);

		try {
			_tikaConfig = new TikaConfig(
				new UnsyncByteArrayInputStream(getTikaConfigXml().getBytes()));

			_parser = new AutoDetectParser(_tikaConfig);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private static volatile Parser _parser;
	private static volatile TikaConfig _tikaConfig;
	private static volatile TikaConfiguration _tikaConfiguration;

}
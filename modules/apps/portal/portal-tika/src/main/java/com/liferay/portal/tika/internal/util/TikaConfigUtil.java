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

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tika.internal.configuration.PortalTikaConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import org.apache.tika.config.TikaConfig;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Shuyang Zhou
 * @author Jorge Díaz
 */
@Component(
	configurationPid = "com.liferay.portal.tika.internal.configuration.PortalTikaConfiguration",
	service = {}
)
public class TikaConfigUtil {

	public static TikaConfig getTikaConfig() {
		return _tikaConfig;
	}

	public static InputStream getTikaConfigInputStream()
		throws FileNotFoundException {

		String tikaConfigPath = _portalTikaConfiguration.tikaConfigPath();

		if (Validator.isNotNull(tikaConfigPath)) {
			Path path = Paths.get(tikaConfigPath);

			return new FileInputStream(path.toFile());
		}

		return TikaConfigUtil.class.getResourceAsStream(
			"dependencies/tika.xml");
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_portalTikaConfiguration = ConfigurableUtil.createConfigurable(
			PortalTikaConfiguration.class, properties);

		try {
			_tikaConfig = new TikaConfig(getTikaConfigInputStream());
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private static volatile PortalTikaConfiguration _portalTikaConfiguration;
	private static volatile TikaConfig _tikaConfig;

}
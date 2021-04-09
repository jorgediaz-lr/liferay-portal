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

package com.liferay.portal.file.install.internal.properties;

import com.liferay.petra.io.unsync.UnsyncBufferedReader;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Matthew Tambara
 */
public class CFGProperties implements ConfigurationProperties {

	@Override
	public Object get(String key) throws IOException {
		Map.Entry<String, List<String>> entry = _storage.get(key);

		if (entry == null) {
			return null;
		}

		return entry.getKey();
	}

	@Override
	public Set<String> keySet() {
		return _storage.keySet();
	}

	@Override
	public void load(Reader reader) throws IOException {
		try (UnsyncBufferedReader unsyncBufferedReader = _wrap(reader)) {
			String line = unsyncBufferedReader.readLine();

			List<String> lines = new ArrayList<>();

			String key = StringPool.BLANK;
			String value = StringPool.BLANK;

			while (line != null) {
				lines.add(line);

				if ((line.length() < 1) || line.startsWith(StringPool.POUND) ||
					line.startsWith(StringPool.EXCLAMATION)) {

					line = unsyncBufferedReader.readLine();

					continue;
				}

				if (Validator.isNull(value)) {
					int index = _getSeparator(line);

					if (index == -1) {
						key = key.concat(line.trim());

						if (line.endsWith(StringPool.BACK_SLASH)) {
							key = key.substring(0, key.length() - 1);

							line = unsyncBufferedReader.readLine();

							continue;
						}
					}
					else {
						key = key.concat(line.substring(0, index));

						value = line.substring(index + 1);
					}
				}
				else {
					value = value.concat(line.trim());
				}

				if (line.endsWith(StringPool.BACK_SLASH)) {
					value = value.substring(0, value.length() - 1);

					line = unsyncBufferedReader.readLine();

					continue;
				}

				_storage.put(
					key.trim(),
					new AbstractMap.SimpleImmutableEntry<>(
						InterpolationUtil.substVars(value.trim()),
						new ArrayList<>(lines)));

				key = StringPool.BLANK;
				value = StringPool.BLANK;

				lines.clear();

				line = unsyncBufferedReader.readLine();
			}
		}
	}

	@Override
	public void put(String key, Object value) throws IOException {
		StringBundler sb = new StringBundler();

		if (value instanceof Collection) {
			Collection<?> collection = (Collection<?>)value;

			for (Object object : collection) {
				sb.append(object.toString());
				sb.append(StringPool.COMMA);
			}

			if (!collection.isEmpty()) {
				sb.setIndex(sb.index() - 1);
			}
		}
		else {
			Class<?> clazz = value.getClass();

			if (clazz.isArray()) {
				Object[] array = (Object[])value;

				for (Object object : array) {
					sb.append(object.toString());
					sb.append(StringPool.COMMA);
				}

				if (array.length > 0) {
					sb.setIndex(sb.index() - 1);
				}
			}
			else {
				sb.append(value.toString());
			}
		}

		_storage.put(
			key, new AbstractMap.SimpleImmutableEntry<>(sb.toString(), null));
	}

	@Override
	public void remove(String key) {
		_storage.remove(key);
	}

	@Override
	public void save(Writer writer) throws IOException {
		StringBundler sb = new StringBundler();

		for (Map.Entry<String, Map.Entry<String, List<String>>> entry :
				_storage.entrySet()) {

			Map.Entry<String, List<String>> values = entry.getValue();

			List<String> lines = values.getValue();

			if (lines == null) {
				sb.append(entry.getKey());
				sb.append(StringPool.EQUAL);
				sb.append(values.getKey());
				sb.append(_LINE_SEPARATOR);
			}
			else {
				for (String line : lines) {
					sb.append(line);
					sb.append(_LINE_SEPARATOR);
				}
			}
		}

		if (sb.index() > 0) {
			sb.setIndex(sb.index() - 1);
		}

		writer.write(sb.toString());
	}

	private int _getSeparator(String line) {
		int indexColon = line.indexOf(CharPool.COLON);
		int indexEqual = line.indexOf(CharPool.EQUAL);

		if (indexColon == -1) {
			return indexEqual;
		}

		if (indexEqual == -1) {
			return indexColon;
		}

		return Math.min(indexColon, indexEqual);
	}

	private UnsyncBufferedReader _wrap(Reader reader) {
		if (reader == null) {
			return null;
		}

		return new UnsyncBufferedReader(reader);
	}

	private static final String _LINE_SEPARATOR = System.getProperty(
		"line.separator");

	private final Map<String, Map.Entry<String, List<String>>> _storage =
		new LinkedHashMap<>();

}
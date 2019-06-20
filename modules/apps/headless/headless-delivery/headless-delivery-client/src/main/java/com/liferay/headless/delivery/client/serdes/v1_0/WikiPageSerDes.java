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

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class WikiPageSerDes {

	public static WikiPage toDTO(String json) {
		WikiPageJSONParser wikiPageJSONParser = new WikiPageJSONParser();

		return wikiPageJSONParser.parseToDTO(json);
	}

	public static WikiPage[] toDTOs(String json) {
		WikiPageJSONParser wikiPageJSONParser = new WikiPageJSONParser();

		return wikiPageJSONParser.parseToDTOs(json);
	}

	public static String toJSON(WikiPage wikiPage) {
		if (wikiPage == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (wikiPage.getContent() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"content\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getContent()));

			sb.append("\"");
		}

		if (wikiPage.getHeadline() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"headline\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getHeadline()));

			sb.append("\"");
		}

		if (wikiPage.getSiteId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteId\": ");

			sb.append(wikiPage.getSiteId());
		}

		if (wikiPage.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append("\"");

			sb.append(_escape(wikiPage.getTitle()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		WikiPageJSONParser wikiPageJSONParser = new WikiPageJSONParser();

		return wikiPageJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(WikiPage wikiPage) {
		if (wikiPage == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		if (wikiPage.getContent() == null) {
			map.put("content", null);
		}
		else {
			map.put("content", String.valueOf(wikiPage.getContent()));
		}

		if (wikiPage.getHeadline() == null) {
			map.put("headline", null);
		}
		else {
			map.put("headline", String.valueOf(wikiPage.getHeadline()));
		}

		if (wikiPage.getSiteId() == null) {
			map.put("siteId", null);
		}
		else {
			map.put("siteId", String.valueOf(wikiPage.getSiteId()));
		}

		if (wikiPage.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(wikiPage.getTitle()));
		}

		return map;
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		string = string.replace("\\", "\\\\");

		return string.replace("\"", "\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static class WikiPageJSONParser extends BaseJSONParser<WikiPage> {

		@Override
		protected WikiPage createDTO() {
			return new WikiPage();
		}

		@Override
		protected WikiPage[] createDTOArray(int size) {
			return new WikiPage[size];
		}

		@Override
		protected void setField(
			WikiPage wikiPage, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "content")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setContent((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "headline")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setHeadline((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteId")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setSiteId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					wikiPage.setTitle((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}
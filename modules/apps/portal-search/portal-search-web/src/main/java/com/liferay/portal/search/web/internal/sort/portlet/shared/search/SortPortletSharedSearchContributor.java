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

package com.liferay.portal.search.web.internal.sort.portlet.shared.search;

import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.sort.FieldSort;
import com.liferay.portal.search.sort.NestedSort;
import com.liferay.portal.search.sort.Sort;
import com.liferay.portal.search.sort.SortBuilder;
import com.liferay.portal.search.sort.SortBuilderFactory;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;
import com.liferay.portal.search.web.internal.sort.constants.SortPortletKeys;
import com.liferay.portal.search.web.internal.sort.portlet.SortPortletPreferences;
import com.liferay.portal.search.web.internal.sort.portlet.SortPortletPreferencesImpl;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + SortPortletKeys.SORT,
	service = PortletSharedSearchContributor.class
)
public class SortPortletSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		SortPortletPreferences sortPortletPreferences =
			new SortPortletPreferencesImpl(
				portletSharedSearchSettings.getPortletPreferencesOptional());

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getSearchRequestBuilder();

		Stream<Sort> stream = buildSorts(
			portletSharedSearchSettings, sortPortletPreferences);

		searchRequestBuilder.sorts(stream.toArray(Sort[]::new));
	}

	protected Sort buildSort(String fieldValue, Locale locale) {
		SortOrder sortOrder = SortOrder.ASC;

		if (fieldValue.endsWith("+")) {
			fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
		}
		else if (fieldValue.endsWith("-")) {
			fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
			sortOrder = SortOrder.DESC;
		}

		if (fieldValue.startsWith(DDMIndexer.DDM_FIELD_PREFIX)) {
			return _buildDDMFieldSort(fieldValue, locale, sortOrder);
		}

		SortBuilder sortBuilder = _sortBuilderFactory.getSortBuilder();

		return sortBuilder.field(
			fieldValue
		).sortOrder(
			sortOrder
		).locale(
			locale
		).build();
	}

	protected Stream<Sort> buildSorts(
		PortletSharedSearchSettings portletSharedSearchSettings,
		SortPortletPreferences sortPortletPreferences) {

		List<String> fieldValues = getFieldValues(
			sortPortletPreferences.getParameterName(),
			portletSharedSearchSettings);

		ThemeDisplay themeDisplay =
			portletSharedSearchSettings.getThemeDisplay();

		Stream<String> stream = fieldValues.stream();

		return stream.filter(
			fieldValue -> !fieldValue.isEmpty()
		).map(
			fieldValue -> buildSort(fieldValue, themeDisplay.getLocale())
		);
	}

	protected List<String> getFieldValues(
		String parameterName,
		PortletSharedSearchSettings portletSharedSearchSettings) {

		String[] fieldValues = portletSharedSearchSettings.getParameterValues(
			parameterName);

		if (ArrayUtil.isNotEmpty(fieldValues)) {
			return Arrays.asList(fieldValues);
		}

		String portletId = portletSharedSearchSettings.getPortletId();
		ThemeDisplay themeDisplay =
			portletSharedSearchSettings.getThemeDisplay();

		try {
			PortletPreferences portletPreferences =
				PortletPreferencesFactoryUtil.getExistingPortletSetup(
					themeDisplay.getLayout(), portletId);

			SortPortletPreferences sortPortletPreferences =
				new SortPortletPreferencesImpl(Optional.of(portletPreferences));

			JSONArray fieldsJSONArray =
				sortPortletPreferences.getFieldsJSONArray();

			JSONObject jsonObject = fieldsJSONArray.getJSONObject(0);

			String fieldValue = jsonObject.getString("field");

			return ListUtil.fromArray(fieldValue);
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	@Reference
	protected DDMIndexer ddmIndexer;

	private Sort _buildDDMFieldSort(
		String fieldValue, Locale locale, SortOrder sortOrder) {

		DDMFormField ddmFormField = _getDDMFormField(fieldValue);

		if (!GetterUtil.getBoolean(ddmFormField.getProperty("localizable"))) {
			locale = null;
		}

		FieldSort fieldSort = _sorts.field(
			_getDDMStructureSortableFieldName(
				fieldValue, ddmFormField.getType(), locale),
			sortOrder);

		if (ddmIndexer.isLegacyDDMIndexFieldsEnabled()) {
			return fieldSort;
		}

		NestedSort nestedSort = _sorts.nested(DDMIndexer.DDM_FIELD_ARRAY);

		StringBundler sb = new StringBundler(3);

		sb.append(fieldValue);

		if (locale != null) {
			sb.append(StringPool.UNDERLINE);
			sb.append(LocaleUtil.toLanguageId(locale));
		}

		nestedSort.setFilterQuery(
			_queries.term(
				StringBundler.concat(
					DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
					DDMIndexer.DDM_FIELD_NAME),
				sb.toString()));

		fieldSort.setNestedSort(nestedSort);

		return fieldSort;
	}

	private DDMFormField _getDDMFormField(String sortField) {
		String[] sortFields = sortField.split(DDMIndexer.DDM_FIELD_SEPARATOR);

		long ddmStructureId = GetterUtil.getLong(sortFields[2]);
		String fieldName = sortFields[3];

		try {
			DDMStructure ddmStructure = _ddmStructureLocalService.getStructure(
				ddmStructureId);

			return ddmStructure.getDDMFormField(fieldName);
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	private String _getDDMStructureSortableFieldName(
		String ddmFormField, String ddmFormFieldType, Locale locale) {

		StringBundler sb = new StringBundler(5);

		if (ddmIndexer.isLegacyDDMIndexFieldsEnabled()) {
			sb.append(ddmFormField);
			sb.append(StringPool.UNDERLINE);

			if (locale != null) {
				sb.append(LocaleUtil.toLanguageId(locale));
				sb.append(StringPool.UNDERLINE);
			}
		}
		else {
			sb.append(DDMIndexer.DDM_FIELD_ARRAY);
			sb.append(StringPool.PERIOD);

			String indexType =
				ddmFormField.split(DDMIndexer.DDM_FIELD_SEPARATOR)[1];

			sb.append(ddmIndexer.getValueFieldName(indexType, locale));

			sb.append(StringPool.UNDERLINE);
		}

		if (Objects.equals(ddmFormFieldType, DDMFormFieldType.DECIMAL) ||
			Objects.equals(ddmFormFieldType, DDMFormFieldType.INTEGER) ||
			Objects.equals(ddmFormFieldType, DDMFormFieldType.NUMBER) ||
			Objects.equals(ddmFormFieldType, DDMFormFieldType.NUMERIC)) {

			sb.append("Number");
		}
		else {
			sb.append("String");
		}

		return Field.getSortableFieldName(sb.toString());
	}

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private Queries _queries;

	@Reference
	private SortBuilderFactory _sortBuilderFactory;

	@Reference
	private Sorts _sorts;

}
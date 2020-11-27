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

package com.liferay.portal.search.internal.facet.custom;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.FilterAggregation;
import com.liferay.portal.search.aggregation.bucket.NestedAggregation;
import com.liferay.portal.search.aggregation.bucket.TermsAggregation;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.custom.CustomFacetFactory;
import com.liferay.portal.search.facet.custom.CustomFacetSearchContributor;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.SearchRequestBuilder;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(service = CustomFacetSearchContributor.class)
public class CustomFacetSearchContributorImpl
	implements CustomFacetSearchContributor {

	@Override
	public void contribute(
		SearchRequestBuilder searchRequestBuilder,
		Consumer<CustomFacetBuilder> customFacetBuilderConsumer) {

		Facet facet = searchRequestBuilder.withSearchContextGet(
			searchContext -> {
				CustomFacetBuilderImpl customFacetBuilderImpl =
					new CustomFacetBuilderImpl(searchContext);

				customFacetBuilderConsumer.accept(customFacetBuilderImpl);

				return customFacetBuilderImpl.build();
			});

		searchRequestBuilder.withFacetContext(
			facetContext -> facetContext.addFacet(facet));

		String fieldName = facet.getFieldName();

		if (ddmIndexer.isLegacyDDMIndexFieldsEnabled() ||
			!fieldName.startsWith(DDMIndexer.DDM_FIELD_PREFIX)) {

			return;
		}

		DDMStructureField ddmStructureField = DDMStructureField.from(
			fieldName);

		TermsAggregation termsAggregation = aggregations.terms(
				facet.getAggregationName(),
				ddmStructureField.getDDMStructureNestedFieldName());

		FilterAggregation filterAggregation = aggregations.filter(
			"filterAggregation",
			queries.term(
				DDMIndexer.DDM_FIELD_ARRAY + "." + DDMIndexer.DDM_FIELD_NAME,
				ddmStructureField.getDDMStructureFieldName()));

		filterAggregation.addChildAggregation(termsAggregation);

		NestedAggregation nestedAggregation = aggregations.nested(
			ddmStructureField.getDDMStructureFieldName(),
			DDMIndexer.DDM_FIELD_ARRAY);

		nestedAggregation.addChildAggregation(filterAggregation);

		searchRequestBuilder.addAggregation(nestedAggregation);
	}

	@Reference
	protected Aggregations aggregations;

	@Reference
	protected CustomFacetFactory customFacetFactory;

	@Reference
	protected DDMIndexer ddmIndexer;

	@Reference
	protected Queries queries;

	private static class DDMStructureField {

		public static DDMStructureField from(String ddmStructureField) {
			String[] ddmStructureParts = StringUtil.split(
				ddmStructureField, DDMIndexer.DDM_FIELD_SEPARATOR);

			String[] ddmFieldParts = StringUtil.split(
				ddmStructureParts[3], StringPool.UNDERLINE);

			return new DDMStructureField(
				ddmStructureParts[2], ddmStructureParts[1],
				ddmFieldParts[1] + "_" + ddmFieldParts[2], ddmFieldParts[0]);
		}

		public String getDDMStructureFieldName() {
			return StringBundler.concat(
				DDMIndexer.DDM_FIELD_PREFIX, _indexType,
				DDMIndexer.DDM_FIELD_SEPARATOR, _ddmStructureId,
				DDMIndexer.DDM_FIELD_SEPARATOR, _name, StringPool.UNDERLINE,
				_locale);
		}

		public String getDDMStructureNestedFieldName() {
			return StringBundler.concat(
				DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
				DDMIndexer.DDM_VALUE_FIELD_NAME_PREFIX,
				StringUtil.upperCaseFirstLetter(_indexType),
				StringPool.UNDERLINE, _locale);
		}

		/*public String getLocale() {
			return _locale;
		}

		public String getNestedFieldName() {
			return StringBundler.concat(
				DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
				DDMIndexer.DDM_FIELD_NAME);
		}*/

		private DDMStructureField(
			String ddmStructureId, String indexType, String locale,
			String name) {

			_ddmStructureId = ddmStructureId;
			_indexType = indexType;
			_locale = locale;
			_name = name;
		}

		private final String _ddmStructureId;
		private final String _indexType;
		private final String _locale;
		private final String _name;

	}

	private class CustomFacetBuilderImpl implements CustomFacetBuilder {

		public CustomFacetBuilderImpl(SearchContext searchContext) {
			_searchContext = searchContext;
		}

		@Override
		public CustomFacetBuilder aggregationName(String aggregationName) {
			_aggregationName = aggregationName;

			return this;
		}

		public Facet build() {
			Facet facet = customFacetFactory.newInstance(_searchContext);

			facet.setAggregationName(_aggregationName);
			facet.setFacetConfiguration(buildFacetConfiguration(facet));
			facet.setFieldName(_fieldToAggregate);

			facet.select(_selectedValues);

			return facet;
		}

		@Override
		public CustomFacetBuilder fieldToAggregate(String fieldToAggregate) {
			_fieldToAggregate = fieldToAggregate;

			return this;
		}

		@Override
		public CustomFacetBuilder frequencyThreshold(int frequencyThreshold) {
			_frequencyThreshold = frequencyThreshold;

			return this;
		}

		@Override
		public CustomFacetBuilder maxTerms(int maxTerms) {
			_maxTerms = maxTerms;

			return this;
		}

		@Override
		public CustomFacetBuilder selectedValues(String... selectedValues) {
			_selectedValues = selectedValues;

			return this;
		}

		protected FacetConfiguration buildFacetConfiguration(Facet facet) {
			FacetConfiguration facetConfiguration = new FacetConfiguration();

			facetConfiguration.setFieldName(facet.getFieldName());
			facetConfiguration.setOrder("OrderHitsDesc");
			facetConfiguration.setStatic(false);
			facetConfiguration.setWeight(1.1);

			JSONObject jsonObject = facetConfiguration.getData();

			jsonObject.put(
				"frequencyThreshold", _frequencyThreshold
			).put(
				"maxTerms", _maxTerms
			);

			return facetConfiguration;
		}

		private String _aggregationName;
		private String _fieldToAggregate;
		private int _frequencyThreshold;
		private int _maxTerms;
		private final SearchContext _searchContext;
		private String[] _selectedValues;

	}

}
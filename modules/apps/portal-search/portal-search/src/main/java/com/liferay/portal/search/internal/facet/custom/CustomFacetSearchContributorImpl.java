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
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.facet.custom.CustomFacetFactory;
import com.liferay.portal.search.facet.custom.CustomFacetSearchContributor;
import com.liferay.portal.search.internal.facet.NestedFacetImpl;
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
	}

	@Reference
	protected Aggregations aggregations;

	@Reference
	protected CustomFacetFactory customFacetFactory;

	@Reference
	protected DDMIndexer ddmIndexer;

	@Reference
	protected Queries queries;

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
			if (!ddmIndexer.isLegacyDDMIndexFieldsEnabled() &&
				_fieldToAggregate.startsWith(DDMIndexer.DDM_FIELD_PREFIX)) {

				return buildNestedFacet();
			}

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

		protected Facet buildNestedFacet() {
			Facet facet = new NestedFacetImpl(null, _searchContext);

			facet.setAggregationName(_aggregationName);
			facet.setFacetConfiguration(buildFacetConfiguration(facet));
			facet.setFieldName(_fieldToAggregate);

			facet.select(_selectedValues);

			FacetConfiguration facetConfiguration =
				facet.getFacetConfiguration();

			JSONObject jsonObject = facetConfiguration.getData();

			String[] ddmStructureParts = StringUtil.split(
				_fieldToAggregate, DDMIndexer.DDM_FIELD_SEPARATOR);

			String[] ddmFieldParts = StringUtil.split(
				ddmStructureParts[3], StringPool.UNDERLINE);

			jsonObject.put(
				"nestedAggregationName",
				ddmIndexer.getValueFieldName(
					ddmStructureParts[1],
					LocaleUtil.fromLanguageId(
						ddmFieldParts[1] + "_" + ddmFieldParts[2]))
			).put(
				"nestedPath", DDMIndexer.DDM_FIELD_ARRAY
			).put(
				"nestedTermFieldName",
				StringBundler.concat(
					DDMIndexer.DDM_FIELD_ARRAY, StringPool.PERIOD,
					DDMIndexer.DDM_FIELD_NAME)
			).put(
				"nestedTermValue", _fieldToAggregate
			);

			return facet;
		}

		private String _aggregationName;
		private String _fieldToAggregate;
		private int _frequencyThreshold;
		private int _maxTerms;
		private final SearchContext _searchContext;
		private String[] _selectedValues;

	}

}
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

package com.liferay.portal.search.internal.facet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.QueryFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.search.generic.BooleanClauseImpl;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.NestedQuery;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.facet.Facet;

/**
 * @author Jorge Díaz
 */
public class NestedFacetImpl extends FacetImpl implements Facet {

	public NestedFacetImpl(String fieldName, SearchContext searchContext) {
		super(fieldName, searchContext);
	}

	@Override
	protected BooleanClause<Filter> doGetFacetFilterBooleanClause() {
		String[] selections = getSelections();

		if (ArrayUtil.isEmpty(selections)) {
			return null;
		}

		FacetConfiguration facetConfiguration = getFacetConfiguration();

		JSONObject dataJSONObject = facetConfiguration.getData();

		String nestedAggregationName = dataJSONObject.getString(
			"nestedAggregationName");
		String nestedTermFieldName = dataJSONObject.getString(
			"nestedTermFieldName");
		String nestedTermValue = dataJSONObject.getString("nestedTermValue");
		String nestedPath = dataJSONObject.getString("nestedPath");

		TermsFilter nestedTermFieldFilter = new TermsFilter(
			nestedTermFieldName);

		nestedTermFieldFilter.addValue(nestedTermValue);

		TermsFilter nestedAggregationFilter = new TermsFilter(
			nestedPath + "." + nestedAggregationName);

		nestedAggregationFilter.addValues(selections);

		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.add(nestedTermFieldFilter, BooleanClauseOccur.MUST);
		booleanFilter.add(nestedAggregationFilter, BooleanClauseOccur.MUST);

		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.setPreBooleanFilter(booleanFilter);

		NestedQuery nestedQuery = new NestedQuery(nestedPath, booleanQuery);

		QueryFilter queryFilter = new QueryFilter(nestedQuery);

		return new BooleanClauseImpl<>(queryFilter, BooleanClauseOccur.MUST);
	}

}
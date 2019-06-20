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

package com.liferay.headless.delivery.internal.resource.v1_0;

import com.liferay.headless.common.spi.service.context.ServiceContextUtil;
import com.liferay.headless.delivery.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.internal.odata.entity.v1_0.WikiPageEntityModel;
import com.liferay.headless.delivery.resource.v1_0.WikiPageResource;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.WikiNodeService;
import com.liferay.wiki.service.WikiPageService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/wiki-page.properties",
	scope = ServiceScope.PROTOTYPE, service = WikiPageResource.class
)
public class WikiPageResourceImpl
	extends BaseWikiPageResourceImpl implements EntityModelResource {

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Page<WikiPage> getWikiNodeWikiPagesPage(
			Long wikiNodeId, String search, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
				BooleanFilter booleanFilter =
					booleanQuery.getPreBooleanFilter();

				booleanFilter.add(
					new TermFilter("nodeId", String.valueOf(wikiNodeId)),
					BooleanClauseOccur.MUST);
			},
			filter, com.liferay.wiki.model.WikiPage.class, search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> _toWiki(
				_wikiPageService.getPage(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public WikiPage getWikiPage(Long wikiPageId) throws Exception {
		return _toWiki(_wikiPageService.getPage(wikiPageId));
	}

	@Override
	public WikiPage postWikiNodeWikiPage(Long wikiNodeId, WikiPage wikiPage)
		throws Exception {

		WikiNode wikiNode = _wikiNodeService.getNode(wikiNodeId);

		return _toWiki(
			_wikiPageService.addPage(
				wikiNodeId, wikiPage.getTitle(), wikiPage.getContent(),
				wikiPage.getHeadline(), true,
				ServiceContextUtil.createServiceContext(
					wikiNode.getGroupId(), "all")));
	}

	private WikiPage _toWiki(com.liferay.wiki.model.WikiPage wikiPage) {
		return new WikiPage() {
			{
				content = wikiPage.getContent();
				headline = wikiPage.getSummary();
				title = wikiPage.getTitle();
			}
		};
	}

	private final WikiPageEntityModel _entityModel = new WikiPageEntityModel();

	@Context
	private User _user;

	@Reference
	private WikiNodeService _wikiNodeService;

	@Reference
	private WikiPageService _wikiPageService;

}
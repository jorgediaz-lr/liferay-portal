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

import com.liferay.headless.delivery.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.resource.v1_0.WikiPageResource;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.WikiNodeService;
import com.liferay.wiki.service.WikiPageService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/wiki-page.properties",
	scope = ServiceScope.PROTOTYPE, service = WikiPageResource.class
)
public class WikiPageResourceImpl extends BaseWikiPageResourceImpl {

	@Override
	public WikiPage getWikiPage(Long wikiPageId) throws Exception {
		return _toWiki(_wikiPageService.getPage(wikiPageId));
	}

	@Override
	public Page<WikiPage> getWikiNodeWikiPagesPage(
		Long wikiNodeId, Pagination pagination) throws Exception {

		WikiNode wikiNode = _wikiNodeService.getNode(wikiNodeId);

		return Page.of(TransformUtil.transform(_wikiPageService.getPages(
			wikiNode.getGroupId(), _user.getUserId(),
			wikiNodeId, 0, pagination.getStartPosition(),
			pagination.getEndPosition()), this::_toWiki),
			pagination,
			_wikiPageService.getPagesCount(
				wikiNode.getGroupId(), _user.getUserId(), wikiNodeId, 0));
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

	@Context
	private User _user;

	@Reference
	private WikiPageService _wikiPageService;

	@Reference
	private WikiNodeService _wikiNodeService;
}
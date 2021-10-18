/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ContactSearchDisplayContext {

	public ContactSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		AccountWebService accountWebService,
		ContactWebService contactWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountWebService = accountWebService;
		_contactWebService = contactWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = _currentURLObj;

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	public String getCurrentURL() {
		return _currentURLObj.toString();
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _currentURLObj, Collections.emptyList(),
			"no-contacts-were-found");

		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		List<Contact> contacts = _contactWebService.search(
			keywords, null, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(),
			StringPool.BLANK);

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> new ContactDisplay(
					_httpServletRequest, contact, null)));

		int count = (int)_contactWebService.searchCount(keywords, null);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private final AccountWebService _accountWebService;
	private final ContactWebService _contactWebService;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}
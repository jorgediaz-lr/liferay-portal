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

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.provisioning.web.internal.search.AccountDisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ViewAccountsManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public ViewAccountsManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest,
		SearchContainer searchContainer) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			searchContainer);
	}

	@Override
	public String getClearResultsURL() {
		PortletURL clearResultsURL = liferayPortletResponse.createRenderURL();

		return clearResultsURL.toString();
	}

	@Override
	public List<LabelItem> getFilterLabelItems() {
		AccountDisplayTerms accountDisplayTerms =
			(AccountDisplayTerms)searchContainer.getDisplayTerms();

		if (!accountDisplayTerms.isAdvancedSearch()) {
			return null;
		}

		return new LabelItemList() {
			{
				Map<String, String> termsMap =
					accountDisplayTerms.getTermsMap();

				for (Map.Entry<String, String> entry : termsMap.entrySet()) {
					String key = entry.getKey();

					String[] values = StringUtil.split(entry.getValue());

					for (String value : values) {
						add(
							labelItem -> {
								PortletURL removeLabelURL = getPortletURL();

								String[] removeKeywords = ArrayUtil.remove(
									values, value);

								removeLabelURL.setParameter(
									key, StringUtil.merge(removeKeywords));

								labelItem.putData(
									"removeLabelURL",
									removeLabelURL.toString());

								labelItem.setCloseable(true);

								String label = String.format(
									"%s: %s", LanguageUtil.get(request, key),
									value);

								labelItem.setLabel(label);
							});
					}
				}
			}
		};
	}

	@Override
	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	@Override
	public String getSearchContainerId() {
		return "accountSearch";
	}

	@Override
	public Boolean isSelectable() {
		return false;
	}

}
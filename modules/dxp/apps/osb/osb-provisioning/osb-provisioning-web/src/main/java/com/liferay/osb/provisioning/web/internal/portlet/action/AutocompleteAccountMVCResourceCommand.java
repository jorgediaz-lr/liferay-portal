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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.ADMIN,
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"javax.portlet.name=" + ProvisioningPortletKeys.PRODUCT_BUNDLES,
		"javax.portlet.name=" + ProvisioningPortletKeys.PRODUCTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.USERS,
		"mvc.command.name=/accounts/autocomplete"
	},
	service = MVCResourceCommand.class
)
public class AutocompleteAccountMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			JSONArray jsonArray = getJSONArray(
				resourceRequest, resourceResponse);

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(resourceResponse);

			httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(
				httpServletResponse, jsonArray.toString());
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	protected JSONArray getJSONArray(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String[] keywords = StringUtil.split(
			ParamUtil.getString(resourceRequest, "autocompleteKeywords"),
			StringPool.SPACE);

		if (!ArrayUtil.isEmpty(keywords)) {
			StringBundler sb = new StringBundler();

			for (int i = 0; i < keywords.length; i++) {
				String keyword = keywords[i];

				sb.append("(contains(code, '");
				sb.append(keyword);
				sb.append("') or contains(name, '");
				sb.append(keyword);
				sb.append("'))");

				if (i < (keywords.length - 1)) {
					sb.append(" or ");
				}
			}

			int maxResults = ParamUtil.getInteger(
				resourceRequest, "maxResults", 20);

			List<Account> accounts = _accountWebService.search(
				StringPool.BLANK, sb.toString(), 1, maxResults, null);

			for (Account account : accounts) {
				PortletURL portletURL = PortletURLFactoryUtil.create(
					resourceRequest, ProvisioningPortletKeys.ACCOUNTS,
					PortletRequest.RENDER_PHASE);

				portletURL.setParameter(
					"mvcRenderCommandName", "/accounts/view_account");
				portletURL.setParameter("accountKey", account.getKey());

				JSONObject jsonObject = JSONUtil.put(
					"key", account.getKey()
				).put(
					"name", account.getName()
				).put(
					"url", portletURL.toString()
				);

				if (Validator.isNotNull(account.getCode())) {
					jsonObject.put("code", account.getCode());
				}

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AutocompleteAccountMVCResourceCommand.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private Portal _portal;

}
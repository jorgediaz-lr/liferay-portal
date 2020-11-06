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
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/", "mvc.command.name=/accounts/view"
	},
	service = MVCRenderCommand.class
)
public class AccountsViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			String[] keywords = StringUtil.split(
				ParamUtil.getString(renderRequest, "keywords"),
				StringPool.SPACE);

			if ((keywords.length == 1) && StringUtil.isUpperCase(keywords[0])) {
				List<Account> accounts = _accountWebService.search(
					StringPool.BLANK, "code eq '" + keywords[0] + "'", 0, 1,
					null);

				if (!accounts.isEmpty()) {
					renderRequest.setAttribute(
						ProvisioningWebKeys.ACCOUNT, accounts.get(0));

					return "/accounts/view_account.jsp";
				}
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}

		return "/accounts/view.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountsViewMVCRenderCommand.class);

	@Reference
	private AccountWebService _accountWebService;

}
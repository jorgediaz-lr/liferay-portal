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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.lcs.web.service.LCSSubscriptionEntryWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/sync_to_lcs"
	},
	service = MVCActionCommand.class
)
public class SyncToLCSMVCActionCommand extends BaseMVCActionCommand {

	protected void addExternalLink(
			ActionRequest actionRequest, String accountKey,
			String corpProjectId)
		throws Exception {

		List<ExternalLink> externalLinks =
			_externalLinkWebService.getExternalLinks(accountKey, 1, 1000);

		for (ExternalLink externalLink : externalLinks) {
			String domain = externalLink.getDomain();
			String entityName = externalLink.getEntityName();

			if (domain.equals(ExternalLinkDomain.LCS) &&
				entityName.equals(ExternalLinkEntityName.LCS_CORP_PROJECT)) {

				return;
			}
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(ExternalLinkDomain.LCS);
		externalLink.setEntityName(ExternalLinkEntityName.LCS_CORP_PROJECT);
		externalLink.setEntityId(corpProjectId);

		_externalLinkWebService.addAccountExternalLink(
			user.getFullName(), user.getUuid(), accountKey, externalLink);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			String corpProjectId = _lcsSubscriptionEntryWebService.syncToLCS(
				accountKey);

			addExternalLink(actionRequest, accountKey, corpProjectId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			SessionErrors.add(actionRequest, exception.getClass());
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SyncToLCSMVCActionCommand.class);

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

	@Reference
	private LCSSubscriptionEntryWebService _lcsSubscriptionEntryWebService;

}
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

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.exception.ContactRequiredException;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.zendesk.model.ZendeskOrganization;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.model.ZendeskUser;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskUserWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		"mvc.command.name=/accounts/unassign_customer_contact"
	},
	service = MVCActionCommand.class
)
public class UnassignAccountCustomerContactMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");

			List<ZendeskTicket> zendeskTickets = _getZendeskTickets(
				accountKey, emailAddress);

			if ((zendeskTickets == null) || zendeskTickets.isEmpty()) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)actionRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				User user = themeDisplay.getUser();

				_accountWebService.unassignCustomerContact(
					user.getFullName(), user.getUuid(), accountKey,
					emailAddress);
			}
			else {
				SessionErrors.add(
					actionRequest, ContactRequiredException.class);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			SessionErrors.add(actionRequest, exception.getClass(), exception);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private List<ZendeskTicket> _getZendeskTickets(
			String accountKey, String emailAddress)
		throws Exception {

		AccountEntry accountEntry = _accountEntryWebService.fetchAccountEntry(
			accountKey);

		if (accountEntry == null) {
			return null;
		}

		ZendeskOrganization zendeskOrganization =
			_zendeskOrganizationWebService.getZendeskOrganization(
				String.valueOf(accountEntry.getAccountEntryId()));

		ZendeskUser zendeskUser =
			_zendeskUserWebService.getZendeskUserByEmailAddress(emailAddress);

		if ((zendeskOrganization == null) || (zendeskUser == null)) {
			return null;
		}

		Set<String> criteria = new HashSet<>();

		criteria.add(
			"organization:" + zendeskOrganization.getZendeskOrganizationId());
		criteria.add("requester:" + zendeskUser.getZendeskUserId());
		criteria.add("status<closed");

		return _zendeskTicketWebService.getZendeskTickets(criteria);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UnassignAccountCustomerContactMVCActionCommand.class);

	@Reference
	private AccountEntryWebService _accountEntryWebService;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}
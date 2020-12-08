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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.customer.model.AccountEntry;
import com.liferay.osb.provisioning.customer.web.service.AccountEntryWebService;
import com.liferay.osb.provisioning.exception.ContactRequiredException;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.zendesk.model.ZendeskOrganization;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.model.ZendeskUser;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskUserWebService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
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
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");
			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");

			_validateZendeskTickets(accountKey, emailAddress);

			User user = themeDisplay.getUser();

			_accountWebService.unassignCustomerContact(
				user.getFullName(), user.getUuid(), accountKey, emailAddress);
		}
		catch (Exception exception) {
			if (!(exception instanceof ContactRequiredException)) {
				_log.error(exception, exception);
			}

			SessionErrors.add(actionRequest, exception.getClass(), exception);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private long _getDeveloperCount(String accountKey) throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("accountKeysContactRoleKeys/any(s:s eq '");
		sb.append(accountKey);
		sb.append(StringPool.UNDERLINE);

		ContactRole supportDeveloperContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_DEVELOPER);

		sb.append(supportDeveloperContactRole.getKey());

		sb.append("')");

		return _contactWebService.searchCount(StringPool.BLANK, sb.toString());
	}

	private List<Long> _getFLSZendeskOrganizationIds(String accountKey)
		throws Exception {

		List<Long> zendeskOrganizationIds = new ArrayList<>();

		String filterString = StringBundler.concat(
			"accountKey eq '", accountKey, "' and system eq true");

		List<Team> teams = _teamWebService.search(
			StringPool.BLANK, filterString, 1, 1, StringPool.BLANK);

		if (teams.isEmpty()) {
			return zendeskOrganizationIds;
		}

		Team defaultTeam = teams.get(0);

		TeamRole flsTeamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(),
			TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

		StringBundler sb = new StringBundler(5);

		sb.append("assignedTeamKeyTeamRoleKeys/any(s:s eq '");
		sb.append(defaultTeam.getKey());
		sb.append("_");
		sb.append(flsTeamRole.getKey());
		sb.append("')");

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		for (Account account : accounts) {
			AccountEntry accountEntry =
				_accountEntryWebService.fetchAccountEntry(account.getKey());

			if (accountEntry == null) {
				continue;
			}

			ZendeskOrganization zendeskOrganization =
				_zendeskOrganizationWebService.getZendeskOrganization(
					String.valueOf(accountEntry.getAccountEntryId()));

			zendeskOrganizationIds.add(
				zendeskOrganization.getZendeskOrganizationId());
		}

		return zendeskOrganizationIds;
	}

	private void _validateZendeskTickets(String accountKey, String emailAddress)
		throws Exception {

		if (_getDeveloperCount(accountKey) > 1) {
			return;
		}

		ZendeskUser zendeskUser =
			_zendeskUserWebService.getZendeskUserByEmailAddress(emailAddress);

		if (zendeskUser == null) {
			return;
		}

		List<Long> zendeskOrganizationIds = _getFLSZendeskOrganizationIds(
			accountKey);

		AccountEntry accountEntry = _accountEntryWebService.fetchAccountEntry(
			accountKey);

		if (accountEntry != null) {
			ZendeskOrganization zendeskOrganization =
				_zendeskOrganizationWebService.getZendeskOrganization(
					String.valueOf(accountEntry.getAccountEntryId()));

			if (zendeskOrganization != null) {
				zendeskOrganizationIds.add(
					zendeskOrganization.getZendeskOrganizationId());
			}
		}

		if (zendeskOrganizationIds.isEmpty()) {
			return;
		}

		Set<String> criteria = new HashSet<>();

		for (long zendeskOrganizationId : zendeskOrganizationIds) {
			criteria.add("organization:" + zendeskOrganizationId);
		}

		criteria.add("requester:" + zendeskUser.getZendeskUserId());
		criteria.add("status<closed");

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		if (!zendeskTickets.isEmpty()) {
			throw new ContactRequiredException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UnassignAccountCustomerContactMVCActionCommand.class);

	@Reference
	private AccountEntryWebService _accountEntryWebService;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private ZendeskOrganizationWebService _zendeskOrganizationWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}
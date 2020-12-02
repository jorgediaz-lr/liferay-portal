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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.ContactRequiredException;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.model.ZendeskUser;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskUserWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_team"
	},
	service = MVCActionCommand.class
)
public class EditTeamMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteTeam(ActionRequest actionRequest, User user)
		throws Exception {

		String teamKey = ParamUtil.getString(actionRequest, "teamKey");

		_teamWebService.deleteTeam(user.getFullName(), user.getUuid(), teamKey);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deleteTeam(actionRequest, user);

				sendRedirect(actionRequest, actionResponse);
			}
			else {
				String teamKey = updateTeam(actionRequest, user);

				sendRedirect(
					actionRequest, actionResponse,
					getRedirect(actionResponse, teamKey));
			}
		}
		catch (Exception exception) {
			if (exception instanceof ContactRequiredException ||
				exception instanceof HttpException) {

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				sendRedirect(actionRequest, actionResponse);
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected String getRedirect(ActionResponse actionResponse, String teamKey)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/accounts/view_team");
		portletURL.setParameter("teamKey", teamKey);

		return portletURL.toString();
	}

	protected String updateTeam(ActionRequest actionRequest, User user)
		throws Exception {

		String teamKey = ParamUtil.getString(actionRequest, "teamKey");

		String name = ParamUtil.getString(actionRequest, "name");

		if (Validator.isNotNull(name)) {
			Team team = new Team();

			team.setName(name);

			if (Validator.isNotNull(teamKey)) {
				team = _teamWebService.updateTeam(
					user.getFullName(), user.getUuid(), teamKey, team);
			}
			else {
				String accountKey = ParamUtil.getString(
					actionRequest, "accountKey");

				team = _teamWebService.addTeam(
					user.getFullName(), user.getUuid(), accountKey, team);
			}

			teamKey = team.getKey();
		}

		String[] addEmailAddresses = ParamUtil.getStringValues(
			actionRequest, "addEmailAddresses");
		String[] deleteEmailAddresses = ParamUtil.getStringValues(
			actionRequest, "deleteEmailAddresses");

		if (!ArrayUtil.isEmpty(addEmailAddresses)) {
			_teamWebService.assignContacts(
				user.getFullName(), user.getUuid(), teamKey, addEmailAddresses);
		}

		if (!ArrayUtil.isEmpty(deleteEmailAddresses)) {
			_validateUnassignContacts(teamKey, deleteEmailAddresses);

			_teamWebService.unassignContacts(
				user.getFullName(), user.getUuid(), teamKey,
				deleteEmailAddresses);
		}

		return teamKey;
	}

	private void _validateUnassignContacts(
			String teamKey, String[] deleteEmailAddresses)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("teamKeys/any(s:s eq '");
		sb.append(teamKey);
		sb.append("')");

		List<Contact> contacts = _contactWebService.search(
			StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		Iterator<Contact> iterator = contacts.iterator();

		while (iterator.hasNext()) {
			Contact contact = iterator.next();

			if (ArrayUtil.contains(
					deleteEmailAddresses, contact.getEmailAddress())) {

				iterator.remove();
			}
		}

		if (!contacts.isEmpty()) {
			return;
		}

		for (String emailAddress : deleteEmailAddresses) {
			ZendeskUser zendeskUser =
				_zendeskUserWebService.getZendeskUserByEmailAddress(
					emailAddress);

			if (zendeskUser == null) {
				continue;
			}

			Set<String> criteria = new HashSet<>();

			criteria.add("requester:" + zendeskUser.getZendeskUserId());
			criteria.add("status<closed");

			List<ZendeskTicket> zendeskTickets =
				_zendeskTicketWebService.getZendeskTickets(criteria);

			if (!zendeskTickets.isEmpty()) {
				throw new ContactRequiredException();
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditTeamMVCActionCommand.class);

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private Portal _portal;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}
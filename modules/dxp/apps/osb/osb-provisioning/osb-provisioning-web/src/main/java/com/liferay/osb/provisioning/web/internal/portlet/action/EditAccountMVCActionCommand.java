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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.AccountCodeException;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

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
		"mvc.command.name=/accounts/edit_account"
	},
	service = MVCActionCommand.class
)
public class EditAccountMVCActionCommand extends BaseMVCActionCommand {

	protected String addAccount(
			ActionRequest actionRequest, ActionResponse actionResponse,
			User user)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");
		String code = ParamUtil.getString(actionRequest, "code");
		String tier = ParamUtil.getString(actionRequest, "tier");
		String region = ParamUtil.getString(actionRequest, "region");

		validate(code);

		Account account = new Account();

		account.setName(name);

		if (Validator.isNotNull(code)) {
			account.setCode(code);
		}

		if (Validator.isNotNull(tier)) {
			account.setTier(Account.Tier.create(tier));
		}

		if (Validator.isNotNull(region)) {
			account.setRegion(Account.Region.create(region));
		}

		Account newAccount = _accountWebService.addAccount(
			user.getFullName(), user.getUuid(), account);

		return newAccount.getKey();
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");

		try {
			if (Validator.isNotNull(accountKey)) {
				updateAccount(actionRequest, actionResponse, accountKey, user);
			}
			else {
				accountKey = addAccount(actionRequest, actionResponse, user);
			}

			sendRedirect(
				actionRequest, actionResponse,
				getRedirect(actionResponse, accountKey));
		}
		catch (Exception exception) {
			if (exception instanceof AccountCodeException ||
				exception instanceof HttpException) {

				if (_log.isDebugEnabled()) {
					_log.debug(exception, exception);
				}

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				if (Validator.isNotNull(accountKey)) {
					sendRedirect(
						actionRequest, actionResponse,
						getRedirect(actionResponse, accountKey));
				}
				else {
					actionResponse.setRenderParameter(
						"mvcRenderCommandName", "/accounts/add_account");
				}
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected String getRedirect(
			ActionResponse actionResponse, String accountKey)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("tabs1", "details");
		portletURL.setParameter("accountKey", accountKey);

		return portletURL.toString();
	}

	protected void updateAccount(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String accountKey, User user)
		throws Exception {

		boolean updateAccount = ParamUtil.getBoolean(
			actionRequest, "updateAccount");

		if (updateAccount) {
			String name = ParamUtil.getString(actionRequest, "name");
			String code = ParamUtil.getString(actionRequest, "code");
			String tier = ParamUtil.getString(actionRequest, "tier");
			String region = ParamUtil.getString(actionRequest, "region");

			validate(code);

			Account account = new Account();

			account.setName(name);

			if (Validator.isNotNull(code)) {
				account.setCode(code);
			}

			if (Validator.isNotNull(tier)) {
				account.setTier(Account.Tier.create(tier));
			}

			if (Validator.isNotNull(region)) {
				account.setRegion(Account.Region.create(region));
			}

			_accountWebService.updateAccount(
				user.getFullName(), user.getUuid(), accountKey, account);
		}

		boolean updatePartner = ParamUtil.getBoolean(
			actionRequest, "updatePartner");

		if (updatePartner) {
			String partnerTeamKey = ParamUtil.getString(
				actionRequest, "partnerTeamKey");

			updateAssignedTeam(
				user, accountKey, partnerTeamKey,
				TeamRoleConstants.NAME_PARTNER);
		}

		boolean updateFirstLineSupport = ParamUtil.getBoolean(
			actionRequest, "updateFirstLineSupport");

		if (updateFirstLineSupport) {
			String firstLineSupportTeamKey = ParamUtil.getString(
				actionRequest, "firstLineSupportTeamKey");

			updateAssignedTeam(
				user, accountKey, firstLineSupportTeamKey,
				TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);
		}
	}

	protected void updateAssignedTeam(
			User user, String accountKey, String teamKey, String teamRoleName)
		throws Exception {

		TeamRole teamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(), teamRoleName);

		String filterString = StringBundler.concat(
			"accountKeyTeamRoleKeys/any(s:s eq '", accountKey, "_",
			teamRole.getKey(), "')");

		List<Team> teams = _teamWebService.search(
			StringPool.BLANK, filterString, 1, 1000, StringPool.BLANK);

		for (Team team : teams) {
			if (teamKey.equals(team.getKey())) {
				return;
			}

			_accountWebService.unassignTeamRoles(
				user.getFullName(), user.getUuid(), accountKey, team.getKey(),
				new String[] {teamRole.getKey()});
		}

		if (Validator.isNotNull(teamKey)) {
			_accountWebService.assignTeamRoles(
				user.getFullName(), user.getUuid(), accountKey, teamKey,
				new String[] {teamRole.getKey()});
		}
	}

	protected void validate(String code) throws PortalException {
		if (Validator.isNull(code)) {
			throw new AccountCodeException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private Portal _portal;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

}
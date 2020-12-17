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
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

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
		"mvc.command.name=/accounts/edit_account_hierarchy"
	},
	service = MVCActionCommand.class
)
public class EditAccountHierarchyMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			String parentAccountKey = ParamUtil.getString(
				actionRequest, "parentAccountKey");
			String[] addChildAccountKeys = ParamUtil.getStringValues(
				actionRequest, "addChildAccountKeys");
			String[] deleteChildAccountKeys = ParamUtil.getStringValues(
				actionRequest, "deleteChildAccountKeys");

			Account account = new Account();

			account.setParentAccountKey(parentAccountKey);

			_accountWebService.updateAccount(
				user.getFullName(), user.getUuid(), accountKey, account);

			if (!ArrayUtil.isEmpty(addChildAccountKeys)) {
				for (String childAccountKey : addChildAccountKeys) {
					Account childAccount = new Account();

					childAccount.setParentAccountKey(accountKey);

					_accountWebService.updateAccount(
						user.getFullName(), user.getUuid(), childAccountKey,
						childAccount);
				}
			}

			if (!ArrayUtil.isEmpty(deleteChildAccountKeys)) {
				for (String childAccountKey : deleteChildAccountKeys) {
					Account childAccount = new Account();

					childAccount.setParentAccountKey(StringPool.BLANK);

					_accountWebService.updateAccount(
						user.getFullName(), user.getUuid(), childAccountKey,
						childAccount);
				}
			}
		}
		catch (Problem.ProblemException problemException) {
			_log.error(problemException, problemException);

			SessionErrors.add(
				actionRequest, problemException.getClass(), problemException);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountHierarchyMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

}
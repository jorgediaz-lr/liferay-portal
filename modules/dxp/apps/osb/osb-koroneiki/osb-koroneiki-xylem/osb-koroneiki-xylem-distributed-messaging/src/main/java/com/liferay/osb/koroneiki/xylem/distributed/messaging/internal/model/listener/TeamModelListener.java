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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.team.create",
		"remove.topic=koroneiki.team.delete",
		"update.topic=koroneiki.team.update"
	},
	service = ModelListener.class
)
public class TeamModelListener extends BaseXylemModelListener<Team> {

	@Override
	protected Callable<Message> getCallable(Team team) throws Exception {
		Account account = _accountLocalService.getAccount(team.getAccountId());

		team.setAccountKey(account.getAccountKey());

		return () -> messageFactory.create(team);
	}

	@Reference
	private AccountLocalService _accountLocalService;

}
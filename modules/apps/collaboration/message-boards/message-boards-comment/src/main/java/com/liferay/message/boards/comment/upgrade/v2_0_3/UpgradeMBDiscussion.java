/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.message.boards.comment.upgrade.v2_0_3;

import com.liferay.message.boards.kernel.model.MBDiscussion;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBDiscussionLocalService;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.kernel.service.MBThreadLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Díaz
 */
public class UpgradeMBDiscussion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		final ActionableDynamicQuery actionableDynamicQuery =
			_mbThreadLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<MBThread>() {

				@Override
				public void performAction(MBThread mbThread)
					throws PortalException {

					if ((mbThread.getMessageCount() > 1) &&
						(mbThread.getCategoryId() != -1)) {

						return;
					}

					long threadId = mbThread.getThreadId();

					MBDiscussion mbDiscussion =
						_mbDiscussionLocalService.fetchThreadDiscussion(
							threadId);

					if (mbDiscussion == null) {

						return;
					}

					_mbMessageLocalService.deleteDiscussionMessages(
						mbDiscussion.getClassName(), mbDiscussion.getClassPK());
				}

			});

		actionableDynamicQuery.performActions();
	}

	@Reference(unbind = "-")
	protected void setMBDiscussionLocalService(
		MBDiscussionLocalService mbDiscussionLocalService) {

		_mbDiscussionLocalService = mbDiscussionLocalService;
	}

	@Reference(unbind = "-")
	protected void setMBMessageLocalService(
		MBMessageLocalService mbMessageLocalService) {

		_mbMessageLocalService = mbMessageLocalService;
	}

	@Reference(unbind = "-")
	protected void setMBThreadLocalService(
		MBThreadLocalService mbThreadLocalService) {

		_mbThreadLocalService = mbThreadLocalService;
	}

	private MBDiscussionLocalService _mbDiscussionLocalService;
	private MBMessageLocalService _mbMessageLocalService;
	private MBThreadLocalService _mbThreadLocalService;

}
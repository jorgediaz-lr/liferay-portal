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

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_2_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

/**
 * @author Sam Ziemer
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"151", _SITE_ADMINISTRATION_WORKFLOW}, {"158", _USER_WORKFLOW}
		};
	}

	/**
	 * @see com.liferay.portal.workflow.web.internal.constants.WorkflowPortletKeys
	 */
	private static final String _SITE_ADMINISTRATION_WORKFLOW =
		"com_liferay_portal_workflow_web_internal_portlet_" +
			"SiteAdministrationWorkflowPortlet";

	/**
	 * @see com.liferay.portal.workflow.web.internal.constants.WorkflowPortletKeys
	 */
	private static final String _USER_WORKFLOW =
		"com_liferay_portal_workflow_web_internal_portlet_UserWorkflowPortlet";

}
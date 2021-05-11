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

package com.liferay.osb.provisioning.web.internal.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = {})
public class ContactPermissionChecker {

	public static boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		return false;
	}

}
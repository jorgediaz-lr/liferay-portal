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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.constants.ContactRoleSystem;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class ContactRoleImpl extends ContactRoleBaseImpl {

	public ContactRoleImpl() {
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			ContactRole.class.getName(), getContactRoleId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public boolean isMember() {
		if (isSystem()) {
			String name = getName();

			if (name.equals(ContactRoleSystem.NAME_MEMBER)) {
				return true;
			}
		}

		return false;
	}

}
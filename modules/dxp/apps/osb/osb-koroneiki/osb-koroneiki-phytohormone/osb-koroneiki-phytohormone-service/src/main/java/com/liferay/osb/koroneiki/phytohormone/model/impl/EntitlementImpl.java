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

package com.liferay.osb.koroneiki.phytohormone.model.impl;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class EntitlementImpl extends EntitlementBaseImpl {

	public EntitlementImpl() {
	}

	public EntitlementDefinition getEntitlementDefinition()
		throws PortalException {

		return EntitlementDefinitionLocalServiceUtil.getEntitlementDefinition(
			getEntitlementDefinitionId());
	}

	public String getEntitlementDefinitionKey() throws PortalException {
		if (_entitlementDefinitionKey != null) {
			return _entitlementDefinitionKey;
		}

		EntitlementDefinition entitlementDefinition =
			getEntitlementDefinition();

		return entitlementDefinition.getEntitlementDefinitionKey();
	}

	public void setEntitlementDefinitionKey(String entitlementDefinitionKey) {
		_entitlementDefinitionKey = entitlementDefinitionKey;
	}

	private String _entitlementDefinitionKey;

}
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

package com.liferay.osb.provisioning.license.internal.util;

import com.liferay.osb.provisioning.license.util.OSBCustomSQL;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = OSBCustomSQL.class)
public class OSBCustomSQLImpl implements OSBCustomSQL {

	public String[] keywords(String keywords) {
		if (Validator.isNull(keywords) ||
			!keywords.startsWith(StringPool.QUOTE) ||
			!keywords.endsWith(StringPool.QUOTE)) {

			List<String> keywordsList = new ArrayList<>();

			String[] keywordsArray = CustomSQLUtil.keywords(keywords);

			for (String curKeywords : keywordsArray) {
				if (Validator.isNull(curKeywords)) {
					continue;
				}

				if (curKeywords.length() > 3) {
					keywordsList.add(curKeywords);
				}
			}

			return keywordsList.toArray(new String[0]);
		}

		keywords = StringUtil.unquote(keywords);

		return new String[] {StringUtil.quote(keywords, StringPool.PERCENT)};
	}

}
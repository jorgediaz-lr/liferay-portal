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

package com.liferay.journal.web.internal.asset;

import com.liferay.asset.kernel.model.BaseDDMFormValuesReader;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMFieldLocalService;
import com.liferay.dynamic.data.mapping.util.DDMBeanTranslatorUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Adolfo Pérez
 */
public final class JournalArticleDDMFormValuesReader
	extends BaseDDMFormValuesReader {

	public JournalArticleDDMFormValuesReader(JournalArticle article) {
		_article = article;
	}

	@Override
	public DDMFormValues getDDMFormValues() throws PortalException {
		try {
			DDMStructure ddmStructure = _article.getDDMStructure();

			return DDMBeanTranslatorUtil.translate(
				_ddmFieldLocalService.getDDMFormValues(
					ddmStructure.getDDMForm(), _article.getId()));
		}
		catch (Exception exception) {
			throw new PortalException(
				"Unable to read fields for article " + _article.getId(),
				exception);
		}
	}

	public void setDDMFieldLocalService(
		DDMFieldLocalService ddmFieldLocalService) {

		_ddmFieldLocalService = ddmFieldLocalService;
	}

	private final JournalArticle _article;
	private DDMFieldLocalService _ddmFieldLocalService;

}
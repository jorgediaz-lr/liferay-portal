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

package com.liferay.portlet.journal.model.impl;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalFeedImpl extends JournalFeedBaseImpl {

	public JournalFeedImpl() {
	}


	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getStructureKey()}
	 */
	@Override
	public String getStructureId() {
		return getStructureKey();
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #setStructureKey()}
	 */
	@Override
	public void setStructureId(String structureKey) {
		setStructureKey(structureKey);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getTemplateKey()}
	 */
	@Override
	public String getTemplateId() {
		return getTemplateKey();
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #setTemplateKey()}
	 */
	@Override
	public void setTemplateId(String templateKey) {
		setTemplateKey(templateKey);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getTemplateKey()}
	 */
	@Override
	public String getRendererTemplateId() {
		return getRendererTemplateKey();
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #setTemplateKey()}
	 */
	@Override
	public void setRendererTemplateId(String rendererTemplateKey) {
		setRendererTemplateKey(rendererTemplateKey);
	}

}
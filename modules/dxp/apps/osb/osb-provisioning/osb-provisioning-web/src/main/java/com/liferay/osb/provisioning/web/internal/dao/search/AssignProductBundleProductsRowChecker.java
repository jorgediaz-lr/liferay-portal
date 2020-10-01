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

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.util.List;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class AssignProductBundleProductsRowChecker
	extends EmptyOnClickRowChecker {

	public AssignProductBundleProductsRowChecker(
		RenderResponse renderResponse, List<String> productKeys) {

		super(renderResponse);

		_productKeys = productKeys;
	}

	@Override
	public String getRowCheckBox(
		HttpServletRequest httpServletRequest, ResultRow resultRow) {

		ProductDisplay productDisplay = (ProductDisplay)resultRow.getObject();

		return getRowCheckBox(
			httpServletRequest, isChecked(resultRow.getObject()),
			isDisabled(resultRow.getObject()),
			productDisplay.getKey() + "_" +
				HtmlUtil.escape(productDisplay.getName()));
	}

	@Override
	public boolean isChecked(Object obj) {
		ProductDisplay productDisplay = (ProductDisplay)obj;

		try {
			return _productKeys.contains(productDisplay.getKey());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignProductBundleProductsRowChecker.class);

	private final List<String> _productKeys;

}
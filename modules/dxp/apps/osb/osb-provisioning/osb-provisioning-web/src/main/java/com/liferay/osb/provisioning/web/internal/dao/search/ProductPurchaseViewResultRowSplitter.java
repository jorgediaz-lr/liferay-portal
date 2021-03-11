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

import com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseViewDisplay;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanyuan Huang
 */
public class ProductPurchaseViewResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> primaryProductResultRows = new ArrayList<>();
		List<ResultRow> addOnProductResultRows = new ArrayList<>();
		List<ResultRow> regularProductResultRows = new ArrayList<>();
		List<ResultRow> otherProductResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			ProductPurchaseViewDisplay productPurchaseViewDisplay =
				(ProductPurchaseViewDisplay)resultRow.getObject();

			String type = productPurchaseViewDisplay.getType();

			if (type.equals("primary")) {
				primaryProductResultRows.add(resultRow);
			}
			else if (type.equals("add-on")) {
				addOnProductResultRows.add(resultRow);
			}
			else if (type.equals("regular")) {
				regularProductResultRows.add(resultRow);
			}
			else {
				otherProductResultRows.add(resultRow);
			}
		}

		if (!primaryProductResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"primary", primaryProductResultRows));
		}

		if (!addOnProductResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("add-on", addOnProductResultRows));
		}

		if (!regularProductResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"regular", regularProductResultRows));
		}

		if (!otherProductResultRows.isEmpty()) {
			if (!resultRowSplitterEntries.isEmpty()) {
				resultRowSplitterEntries.add(
					new ResultRowSplitterEntry(
						"other", otherProductResultRows));
			}
			else {
				resultRowSplitterEntries.add(
					new ResultRowSplitterEntry(
						StringPool.BLANK, otherProductResultRows));
			}
		}

		return resultRowSplitterEntries;
	}

}
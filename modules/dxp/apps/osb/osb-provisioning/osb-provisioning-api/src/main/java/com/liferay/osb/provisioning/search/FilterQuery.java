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

package com.liferay.osb.provisioning.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class FilterQuery {

	public void addContains(String field, String value, boolean required) {
		addContains(field, value, false, required);
	}

	public void addContains(
		String field, String value, boolean negate, boolean required) {

		StringBundler sb = new StringBundler(6);

		if (negate) {
			sb.append("not ");
		}

		sb.append("contains(");
		sb.append(field);
		sb.append(", '");
		sb.append(_escape(value));
		sb.append("')");

		_addFilter(sb.toString(), required);
	}

	public void addEquals(String field, boolean value, boolean required) {
		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" eq ");
		sb.append(value);

		_addFilter(sb.toString(), required);
	}

	public void addEquals(String field, String value, boolean required) {
		addEquals(field, value, false, required);
	}

	public void addEquals(
		String field, String value, boolean negate, boolean required) {

		StringBundler sb = new StringBundler(5);

		sb.append(field);

		if (negate) {
			sb.append(" ne ");
		}
		else {
			sb.append(" eq ");
		}

		if (value != null) {
			sb.append("'");
		}

		sb.append(_escape(value));

		if (value != null) {
			sb.append("'");
		}

		_addFilter(sb.toString(), required);
	}

	public void addEquals(String field, String[] values, boolean required) {
		StringBundler sb = new StringBundler(4);

		for (int i = 0; i < values.length; i++) {
			sb.append("s eq '");
			sb.append(_escape(values[i]));
			sb.append("'");

			if ((i + 1) < values.length) {
				sb.append(" or ");
			}
		}

		_addFilter(sb.toString(), required);
	}

	public void addFilterQuery(FilterQuery filterQuery, boolean required) {
		_addFilter(filterQuery.toString(), required);
	}

	public void addGreaterThan(String field, Date dateValue, boolean required) {
		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" gt ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(sb.toString(), required);
	}

	public void addGreaterThanEquals(
		String field, Date dateValue, boolean required) {

		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" ge ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(sb.toString(), required);
	}

	public void addLambdaContains(
		String field, String value, boolean required) {

		addLambdaContains(field, value, false, required);
	}

	public void addLambdaContains(
		String field, String value, boolean negate, boolean required) {

		StringBundler sb = new StringBundler(5);

		if (negate) {
			sb.append("not ");
		}

		sb.append(field);
		sb.append("/any(s:contains(s, '");
		sb.append(_escape(value));
		sb.append("'))");

		_addFilter(sb.toString(), required);
	}

	public void addLambdaEquals(String field, String value, boolean required) {
		addLambdaEquals(field, value, false, required);
	}

	public void addLambdaEquals(
		String field, String value, boolean negate, boolean required) {

		StringBundler sb = new StringBundler(5);

		if (negate) {
			sb.append("not ");
		}

		sb.append(field);
		sb.append("/any(s:s eq '");
		sb.append(_escape(value));
		sb.append("')");

		_addFilter(sb.toString(), required);
	}

	public void addLambdaEquals(
		String field, String[] values, boolean required) {

		addLambdaEquals(field, values, false, required);
	}

	public void addLambdaEquals(
		String field, String[] values, boolean negate, boolean required) {

		StringBundler sb = new StringBundler();

		if (negate) {
			sb.append("not ");
		}

		sb.append(field);
		sb.append("/any(s:");

		for (int i = 0; i < values.length; i++) {
			sb.append("s eq '");
			sb.append(_escape(values[i]));
			sb.append("'");

			if ((i + 1) < values.length) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		_addFilter(sb.toString(), required);
	}

	public void addLessThan(String field, Date dateValue, boolean required) {
		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" lt ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(sb.toString(), required);
	}

	public void addLessThanEquals(
		String field, Date dateValue, boolean required) {

		StringBundler sb = new StringBundler(3);

		sb.append(field);
		sb.append(" le ");
		sb.append(_isoDateFormat.format(dateValue));

		_addFilter(sb.toString(), required);
	}

	public void addStartsWith(String field, String value, boolean required) {
		StringBundler sb = new StringBundler(5);

		sb.append("startsWith(");
		sb.append(field);
		sb.append(", '");
		sb.append(_escape(value));
		sb.append("')");

		_addFilter(sb.toString(), required);
	}

	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBundler sb = new StringBundler(7);

		if (!_filters.isEmpty()) {
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringUtil.merge(_filters, " or "));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			if (!_requiredFilters.isEmpty()) {
				sb.append(" and ");
			}
		}

		if (!_requiredFilters.isEmpty()) {
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(StringUtil.merge(_requiredFilters, " and "));
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		_toString = sb.toString();

		return _toString;
	}

	private void _addFilter(String filter, boolean required) {
		if (required) {
			_requiredFilters.add(filter);
		}
		else {
			_filters.add(filter);
		}
	}

	private String _escape(String value) {
		if (value == null) {
			return "null";
		}

		return value.replaceAll(
			StringPool.APOSTROPHE, StringPool.DOUBLE_APOSTROPHE);
	}

	private final List<String> _filters = new ArrayList<>();
	private final DateFormat _isoDateFormat = DateUtil.getISO8601Format();
	private final List<String> _requiredFilters = new ArrayList<>();
	private String _toString;

}
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

package com.liferay.portal.kernel.test.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.db.DBInspector;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

/**
 * @author Preston Crary
 */
public class DBAssertionUtil {

	public static void assertColumns(String tableName, String... columnNames)
		throws SQLException {

		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = StringUtil.toLowerCase(columnNames[i]);
		}

		Set<String> columnNamesSet = SetUtil.fromArray(columnNames);

		try (Connection connection = DataAccess.getConnection()) {
			DBInspector dbInspector = new DBInspector(connection);

			DatabaseMetaData databaseMetaData = connection.getMetaData();

			try (ResultSet resultSet = databaseMetaData.getColumns(
					dbInspector.getCatalog(), dbInspector.getSchema(),
					dbInspector.normalizeName(tableName), null)) {

				while (resultSet.next()) {
					String columnName = StringUtil.toLowerCase(
						resultSet.getString("COLUMN_NAME"));

					Assert.assertTrue(
						columnName + " should not exist",
						columnNamesSet.remove(columnName));
				}
			}
		}

		Assert.assertEquals(
			columnNamesSet.toString(), 0, columnNamesSet.size());
	}

	public static void assertTablesWithInvalidRecords(
			String columnName, long wrongValue)
		throws Exception {

		List<String> invalidTables = getTablesWithInvalidRecords(
			columnName, wrongValue);

		Assert.assertEquals(Collections.emptyList(), invalidTables);
	}

	protected static List<String> getTablesWithInvalidRecords(
			String columnName, long wrongValue)
		throws Exception {

		List<String> invalidTables = new ArrayList<>();

		try (Connection connection = DataAccess.getConnection()) {
			DBInspector dbInspector = new DBInspector(connection);

			String catalog = dbInspector.getCatalog();
			String schema = dbInspector.getSchema();

			DatabaseMetaData databaseMetaData = connection.getMetaData();

			try (ResultSet tableResultSet = databaseMetaData.getTables(
					catalog, schema, null, new String[] {"TABLE"})) {

				while (tableResultSet.next()) {
					String tableName = dbInspector.normalizeName(
						tableResultSet.getString("TABLE_NAME"));

					if (_ignoredTable(tableName) ||
						!dbInspector.hasColumn(tableName, columnName)) {

						continue;
					}

					if (hasInvalidRecords(
							connection, tableName, columnName, wrongValue)) {

						invalidTables.add(tableName);
					}
				}
			}
		}

		return invalidTables;
	}

	protected static boolean hasInvalidRecords(
			Connection connection, String tableName, String columnName,
			long wrongValue)
		throws SQLException {

		String query = StringBundler.concat(
			"select count(*) from ", tableName, " where ", columnName, " = ",
			wrongValue);

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				query);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next() && (resultSet.getInt(1) > 0)) {
				return true;
			}
		}

		return false;
	}

	private static boolean _ignoredTable(String tableName) {
		for (String ignoredTableName : _ignoredTableNames) {
			if (StringUtil.equalsIgnoreCase(ignoredTableName, tableName)) {
				return true;
			}
		}

		return false;
	}

	private static final Set<String> _ignoredTableNames = new HashSet<>(
		Arrays.asList("Audit_AuditEvent"));

}
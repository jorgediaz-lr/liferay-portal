/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.data.cleanup.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBInspector;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.upgrade.data.cleanup.util.OrphanReferencesDataCleanupUtil;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.data.cleanup.DDMStorageLinkDataCleanupPreupgradeProcess;

import java.sql.Connection;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luis Ortiz
 */
@DataGuard(autoDelete = false, scope = DataGuard.Scope.METHOD)
@RunWith(Arquillian.class)
public class DDMStorageLinkDataCleanupPreupgradeProcessTest
	extends DDMStorageLinkDataCleanupPreupgradeProcess {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_connection = DataAccess.getConnection();

		_db = DBManagerUtil.getDB();

		_dbInspector = new DBInspector(_connection);
	}

	@AfterClass
	public static void tearDownClass() {
		DataAccess.cleanUp(_connection);
	}

	@Test
	public void testUpgrade() throws Exception {
		long contentId = RandomTestUtil.nextLong();

		_db.runSQL(
			_connection,
			StringBundler.concat(
				"insert into DDMContent (mvccVersion, ctCollectionId, ",
				"contentId) values (0, 0, ", contentId, ")"));
		_db.runSQL(
			_connection,
			StringBundler.concat(
				"insert into DDMField (mvccVersion, ctCollectionId, fieldId, ",
				"storageId) values (0, 0, ", RandomTestUtil.nextLong(), ", ",
				contentId, ")"));
		_db.runSQL(
			_connection,
			StringBundler.concat(
				"insert into DDMFieldAttribute (mvccVersion, ctCollectionId, ",
				"fieldAttributeId, storageId) values (0, 0, ",
				RandomTestUtil.nextLong(), ", ", contentId, ")"));

		try (LogCapture logCapture = LoggerTestUtil.configureLog4JLogger(
				OrphanReferencesDataCleanupUtil.class.getName(),
				LoggerTestUtil.INFO)) {

			upgrade();

			List<String> messages = logCapture.getMessages();

			Assert.assertEquals(messages.toString(), 3, messages.size());

			Assert.assertTrue(
				messages.contains(
					StringBundler.concat(
						"Table ", _dbInspector.normalizeName("DDMContent"),
						", 1 row deleted because ",
						_dbInspector.normalizeName("contentId"),
						StringPool.SPACE, contentId,
						" was not found in column ",
						_dbInspector.normalizeName("classPK"), " from table ",
						_dbInspector.normalizeName("DDMStorageLink"))));
			Assert.assertTrue(
				messages.contains(
					StringBundler.concat(
						"Table ", _dbInspector.normalizeName("DDMField"),
						", 1 row deleted because ",
						_dbInspector.normalizeName("storageId"),
						StringPool.SPACE, contentId,
						" was not found in column ",
						_dbInspector.normalizeName("classPK"), " from table ",
						_dbInspector.normalizeName("DDMStorageLink"))));
			Assert.assertTrue(
				messages.contains(
					StringBundler.concat(
						"Table ",
						_dbInspector.normalizeName("DDMFieldAttribute"),
						", 1 row deleted because ",
						_dbInspector.normalizeName("storageId"),
						StringPool.SPACE, contentId,
						" was not found in column ",
						_dbInspector.normalizeName("classPK"), " from table ",
						_dbInspector.normalizeName("DDMStorageLink"))));
		}
		finally {
			_db.runSQL(
				_connection,
				"delete from DDMContent where contentId = " + contentId);
			_db.runSQL(
				_connection,
				"delete from DDMField where storageId = " + contentId);
			_db.runSQL(
				_connection,
				"delete from DDMFieldAttribute where storageId = " + contentId);
		}
	}

	private static Connection _connection;
	private static DB _db;
	private static DBInspector _dbInspector;

}
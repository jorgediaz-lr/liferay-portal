/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.jenkins.results.parser.testray;

import com.liferay.jenkins.results.parser.DownstreamBuildReport;
import com.liferay.jenkins.results.parser.TestClassReport;
import com.liferay.jenkins.results.parser.TestReport;
import com.liferay.jenkins.results.parser.test.clazz.PlaywrightJUnitTestClass;
import com.liferay.jenkins.results.parser.test.clazz.PlaywrightTestClassMethod;
import com.liferay.jenkins.results.parser.test.clazz.group.AxisTestClassGroup;
import com.liferay.jenkins.results.parser.test.clazz.group.PlaywrightSegmentTestClassGroup;
import com.liferay.jenkins.results.parser.test.clazz.group.SegmentTestClassGroup;

import java.util.Arrays;

import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Jorge Díaz
 */
public class PlaywrightBatchBuildTestrayCaseResultTest
	extends com.liferay.jenkins.results.parser.Test {

	@Test
	public void testFindTestReportMatchesUnsubstitutedName() {
		PlaywrightBatchBuildTestrayCaseResult
			playwrightBatchBuildTestrayCaseResult = _mockCaseResult(
				_PROJECT_NAME, _SPEC_FILE_PATH);

		TestReport testReport = Mockito.mock(TestReport.class);

		Mockito.when(
			testReport.getTestClassName()
		).thenReturn(
			_SPEC_FILE_PATH
		);

		Mockito.when(
			testReport.getTestName()
		).thenReturn(
			_TEST_NAME
		);

		TestClassReport testClassReport = Mockito.mock(TestClassReport.class);

		Mockito.when(
			testClassReport.getTestClassName()
		).thenReturn(
			_SPEC_FILE_PATH
		);

		Mockito.when(
			testClassReport.getTestReports()
		).thenReturn(
			Arrays.asList(testReport)
		);

		DownstreamBuildReport downstreamBuildReport = Mockito.mock(
			DownstreamBuildReport.class);

		Mockito.when(
			downstreamBuildReport.getTestClassReports()
		).thenReturn(
			Arrays.asList(testClassReport)
		);

		Mockito.doReturn(
			downstreamBuildReport
		).when(
			playwrightBatchBuildTestrayCaseResult
		).getDownstreamBuildReport();

		testSame(
			testReport, playwrightBatchBuildTestrayCaseResult.findTestReport());
	}

	@Test
	public void testGetNameWithMissingSegmentTestClassGroup() {
		PlaywrightBatchBuildTestrayCaseResult
			playwrightBatchBuildTestrayCaseResult = _mockCaseResult(
				null, _SPEC_FILE_PATH);

		testEquals(_CASE_NAME, playwrightBatchBuildTestrayCaseResult.getName());
	}

	@Test
	public void testGetNameWithOwnedDirectory() {
		PlaywrightBatchBuildTestrayCaseResult
			playwrightBatchBuildTestrayCaseResult = _mockCaseResult(
				"portal-db-infrastructure.upgrade", _SPEC_FILE_PATH);

		testEquals(_CASE_NAME, playwrightBatchBuildTestrayCaseResult.getName());
	}

	@Test
	public void testGetNameWithSharedDirectory() {
		PlaywrightBatchBuildTestrayCaseResult
			playwrightBatchBuildTestrayCaseResult = _mockCaseResult(
				_PROJECT_NAME, _SPEC_FILE_PATH);

		testEquals(
			"portal-db-infrastructure/upgrade-portal-7210" +
				"/portalSmokeUpgrade.spec.ts > " + _TEST_NAME,
			playwrightBatchBuildTestrayCaseResult.getName());
	}

	@Test
	public void testGetProjectSpecFilePathWithNestedDirectory() {
		_testGetProjectSpecFilePath(
			"commerce-order-web.main",
			"commerce/commerce-order-web/main/commerceAdminOrder.spec.ts",
			"commerce/commerce-order-web/main/commerceAdminOrder.spec.ts");
	}

	@Test
	public void testGetProjectSpecFilePathWithNoDirectory() {
		_testGetProjectSpecFilePath(
			"portal-db-infrastructure.upgrade", "portalSmokeUpgrade.spec.ts",
			"portalSmokeUpgrade.spec.ts");
	}

	@Test
	public void testGetProjectSpecFilePathWithNullProjectName() {
		_testGetProjectSpecFilePath(null, _SPEC_FILE_PATH, _SPEC_FILE_PATH);

		_testGetProjectSpecFilePath("", _SPEC_FILE_PATH, _SPEC_FILE_PATH);
	}

	@Test
	public void testGetProjectSpecFilePathWithNullSpecFilePath() {
		_testGetProjectSpecFilePath(_PROJECT_NAME, null, null);
	}

	@Test
	public void testGetProjectSpecFilePathWithOwnedDirectory() {
		_testGetProjectSpecFilePath(
			"portal-db-infrastructure.upgrade", _SPEC_FILE_PATH,
			_SPEC_FILE_PATH);

		_testGetProjectSpecFilePath(
			"data-cleanup.main", "data-cleanup/main/dataCleanup.spec.ts",
			"data-cleanup/main/dataCleanup.spec.ts");
	}

	@Test
	public void testGetProjectSpecFilePathWithSharedDirectory() {
		_testGetProjectSpecFilePath(
			_PROJECT_NAME, _SPEC_FILE_PATH,
			"portal-db-infrastructure/upgrade-portal-7210" +
				"/portalSmokeUpgrade.spec.ts");

		_testGetProjectSpecFilePath(
			"portal-db-infrastructure.upgrade-portal-621021", _SPEC_FILE_PATH,
			"portal-db-infrastructure/upgrade-portal-621021" +
				"/portalSmokeUpgrade.spec.ts");
	}

	@Test
	public void testGetProjectSpecFilePathWithSingleDirectory() {
		_testGetProjectSpecFilePath(
			_PROJECT_NAME, "upgrade/portalSmokeUpgrade.spec.ts",
			"upgrade-portal-7210/portalSmokeUpgrade.spec.ts");
	}

	@Test
	public void testGetProjectSpecFilePathWithUndottedProjectName() {
		_testGetProjectSpecFilePath(
			"main", "smoke/main/smoke.spec.ts", "smoke/main/smoke.spec.ts");
	}

	private PlaywrightBatchBuildTestrayCaseResult _mockCaseResult(
		String projectName, String specFilePath) {

		PlaywrightBatchBuildTestrayCaseResult
			playwrightBatchBuildTestrayCaseResult = Mockito.mock(
				PlaywrightBatchBuildTestrayCaseResult.class,
				Mockito.CALLS_REAL_METHODS);

		PlaywrightJUnitTestClass playwrightJUnitTestClass = Mockito.mock(
			PlaywrightJUnitTestClass.class);

		Mockito.when(
			playwrightJUnitTestClass.getSpecFilePath()
		).thenReturn(
			specFilePath
		);

		Mockito.doReturn(
			playwrightJUnitTestClass
		).when(
			playwrightBatchBuildTestrayCaseResult
		).getTestClass();

		PlaywrightTestClassMethod playwrightTestClassMethod = Mockito.mock(
			PlaywrightTestClassMethod.class);

		Mockito.when(
			playwrightTestClassMethod.getName()
		).thenReturn(
			_CASE_NAME
		);

		Mockito.doReturn(
			playwrightTestClassMethod
		).when(
			playwrightBatchBuildTestrayCaseResult
		).getTestClassMethod();

		SegmentTestClassGroup segmentTestClassGroup = null;

		if (projectName != null) {
			PlaywrightSegmentTestClassGroup playwrightSegmentTestClassGroup =
				Mockito.mock(PlaywrightSegmentTestClassGroup.class);

			Mockito.when(
				playwrightSegmentTestClassGroup.getProjectName()
			).thenReturn(
				projectName
			);

			segmentTestClassGroup = playwrightSegmentTestClassGroup;
		}

		AxisTestClassGroup axisTestClassGroup = Mockito.mock(
			AxisTestClassGroup.class);

		Mockito.when(
			axisTestClassGroup.getSegmentTestClassGroup()
		).thenReturn(
			segmentTestClassGroup
		);

		Mockito.doReturn(
			axisTestClassGroup
		).when(
			playwrightBatchBuildTestrayCaseResult
		).getAxisTestClassGroup();

		return playwrightBatchBuildTestrayCaseResult;
	}

	private void _testGetProjectSpecFilePath(
		String projectName, String specFilePath, String expectedSpecFilePath) {

		String actualSpecFilePath =
			PlaywrightBatchBuildTestrayCaseResult.getProjectSpecFilePath(
				projectName, specFilePath);

		testEquals(expectedSpecFilePath, actualSpecFilePath);
	}

	private static final String _CASE_NAME =
		PlaywrightBatchBuildTestrayCaseResultTest._SPEC_FILE_PATH + " > " +
			PlaywrightBatchBuildTestrayCaseResultTest._TEST_NAME;

	private static final String _PROJECT_NAME =
		"portal-db-infrastructure.upgrade-portal-7210";

	private static final String _SPEC_FILE_PATH =
		"portal-db-infrastructure/upgrade/portalSmokeUpgrade.spec.ts";

	private static final String _TEST_NAME =
		"View portal smoke upgrade › Can view upgraded portal content as admin";

}
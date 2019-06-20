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

package com.liferay.headless.delivery.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.delivery.client.dto.v1_0.WikiPage;
import com.liferay.headless.delivery.client.http.HttpInvoker;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.resource.v1_0.WikiPageResource;
import com.liferay.headless.delivery.client.serdes.v1_0.WikiPageSerDes;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseWikiPageResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_wikiPageResource.setContextCompany(testCompany);

		WikiPageResource.Builder builder = WikiPageResource.builder();

		wikiPageResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		WikiPage wikiPage1 = randomWikiPage();

		String json = objectMapper.writeValueAsString(wikiPage1);

		WikiPage wikiPage2 = WikiPageSerDes.toDTO(json);

		Assert.assertTrue(equals(wikiPage1, wikiPage2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		WikiPage wikiPage = randomWikiPage();

		String json1 = objectMapper.writeValueAsString(wikiPage);
		String json2 = WikiPageSerDes.toJSON(wikiPage);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		WikiPage wikiPage = randomWikiPage();

		wikiPage.setContent(regex);
		wikiPage.setHeadline(regex);
		wikiPage.setTitle(regex);

		String json = WikiPageSerDes.toJSON(wikiPage);

		Assert.assertFalse(json.contains(regex));

		wikiPage = WikiPageSerDes.toDTO(json);

		Assert.assertEquals(regex, wikiPage.getContent());
		Assert.assertEquals(regex, wikiPage.getHeadline());
		Assert.assertEquals(regex, wikiPage.getTitle());
	}

	@Test
	public void testGetWikiPage() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetWikiNodeWikiPagesPage() throws Exception {
		Long wikiNodeId = testGetWikiNodeWikiPagesPage_getWikiNodeId();
		Long irrelevantWikiNodeId =
			testGetWikiNodeWikiPagesPage_getIrrelevantWikiNodeId();

		if ((irrelevantWikiNodeId != null)) {
			WikiPage irrelevantWikiPage =
				testGetWikiNodeWikiPagesPage_addWikiPage(
					irrelevantWikiNodeId, randomIrrelevantWikiPage());

			Page<WikiPage> page = wikiPageResource.getWikiNodeWikiPagesPage(
				irrelevantWikiNodeId);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantWikiPage),
				(List<WikiPage>)page.getItems());
			assertValid(page);
		}

		WikiPage wikiPage1 = testGetWikiNodeWikiPagesPage_addWikiPage(
			wikiNodeId, randomWikiPage());

		WikiPage wikiPage2 = testGetWikiNodeWikiPagesPage_addWikiPage(
			wikiNodeId, randomWikiPage());

		Page<WikiPage> page = wikiPageResource.getWikiNodeWikiPagesPage(
			wikiNodeId);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(wikiPage1, wikiPage2),
			(List<WikiPage>)page.getItems());
		assertValid(page);
	}

	protected WikiPage testGetWikiNodeWikiPagesPage_addWikiPage(
			Long wikiNodeId, WikiPage wikiPage)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetWikiNodeWikiPagesPage_getWikiNodeId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetWikiNodeWikiPagesPage_getIrrelevantWikiNodeId()
		throws Exception {

		return null;
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(WikiPage wikiPage1, WikiPage wikiPage2) {
		Assert.assertTrue(
			wikiPage1 + " does not equal " + wikiPage2,
			equals(wikiPage1, wikiPage2));
	}

	protected void assertEquals(
		List<WikiPage> wikiPages1, List<WikiPage> wikiPages2) {

		Assert.assertEquals(wikiPages1.size(), wikiPages2.size());

		for (int i = 0; i < wikiPages1.size(); i++) {
			WikiPage wikiPage1 = wikiPages1.get(i);
			WikiPage wikiPage2 = wikiPages2.get(i);

			assertEquals(wikiPage1, wikiPage2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<WikiPage> wikiPages1, List<WikiPage> wikiPages2) {

		Assert.assertEquals(wikiPages1.size(), wikiPages2.size());

		for (WikiPage wikiPage1 : wikiPages1) {
			boolean contains = false;

			for (WikiPage wikiPage2 : wikiPages2) {
				if (equals(wikiPage1, wikiPage2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				wikiPages2 + " does not contain " + wikiPage1, contains);
		}
	}

	protected void assertValid(WikiPage wikiPage) {
		boolean valid = true;

		if (!Objects.equals(wikiPage.getSiteId(), testGroup.getGroupId())) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("content", additionalAssertFieldName)) {
				if (wikiPage.getContent() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("headline", additionalAssertFieldName)) {
				if (wikiPage.getHeadline() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (wikiPage.getTitle() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<WikiPage> page) {
		boolean valid = false;

		java.util.Collection<WikiPage> wikiPages = page.getItems();

		int size = wikiPages.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(WikiPage wikiPage1, WikiPage wikiPage2) {
		if (wikiPage1 == wikiPage2) {
			return true;
		}

		if (!Objects.equals(wikiPage1.getSiteId(), wikiPage2.getSiteId())) {
			return false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("content", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						wikiPage1.getContent(), wikiPage2.getContent())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("headline", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						wikiPage1.getHeadline(), wikiPage2.getHeadline())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						wikiPage1.getTitle(), wikiPage2.getTitle())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_wikiPageResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_wikiPageResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, WikiPage wikiPage) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("content")) {
			sb.append("'");
			sb.append(String.valueOf(wikiPage.getContent()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("headline")) {
			sb.append("'");
			sb.append(String.valueOf(wikiPage.getHeadline()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("siteId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("title")) {
			sb.append("'");
			sb.append(String.valueOf(wikiPage.getTitle()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected WikiPage randomWikiPage() throws Exception {
		return new WikiPage() {
			{
				content = RandomTestUtil.randomString();
				headline = RandomTestUtil.randomString();
				siteId = testGroup.getGroupId();
				title = RandomTestUtil.randomString();
			}
		};
	}

	protected WikiPage randomIrrelevantWikiPage() throws Exception {
		WikiPage randomIrrelevantWikiPage = randomWikiPage();

		randomIrrelevantWikiPage.setSiteId(irrelevantGroup.getGroupId());

		return randomIrrelevantWikiPage;
	}

	protected WikiPage randomPatchWikiPage() throws Exception {
		return randomWikiPage();
	}

	protected WikiPageResource wikiPageResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseWikiPageResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.delivery.resource.v1_0.WikiPageResource
		_wikiPageResource;

}
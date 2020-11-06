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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class ViewAccountLiferayWorkersDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountLiferayWorkersDisplayContext() {
	}

	public Map<String, Object> getAssignLiferayWorkerData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("accountName", account.getName());
		data.put("allRoles", _getContactRoleJSONObjects());

		Contact contact = (Contact)renderRequest.getAttribute(
			ProvisioningWebKeys.CONTACT);

		if (contact != null) {
			data.put("currentRoles", _getContactRoleKeys(contact));
		}

		data.put(
			"emailAddress",
			BeanParamUtil.getString(contact, renderRequest, "emailAddress"));

		if (contact != null) {
			ContactDisplay contactDisplay = new ContactDisplay(
				httpServletRequest, 0, contact, null);

			data.put("fullName", contactDisplay.getFullName());
		}

		data.put("redirect", ParamUtil.getString(renderRequest, "redirect"));

		return data;
	}

	public String getAssignLiferayWorkerTitle() {
		Contact contact = (Contact)renderRequest.getAttribute(
			ProvisioningWebKeys.CONTACT);

		if (contact != null) {
			return "edit-roles";
		}

		return "assign-liferay-worker";
	}

	public CreationMenu getCreationMenu() {
		return new CreationMenu() {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName",
							"/accounts/assign_liferay_workers", "redirect",
							getCurrentURL(), "accountKey", account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "assign-liferay-worker"));
					});
			}
		};
	}

	public List<DropdownItem> getFilterDropdownItems() throws Exception {
		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getFilterRoleDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "filter-by-role"));
					});
			}
		};
	}

	public List<LabelItem> getFilterLabelItems() {
		return new LabelItemList() {
			{
				String[] contactRoleKeys = ParamUtil.getStringValues(
					renderRequest, "contactRoleKeys");

				for (String contactRoleKey : contactRoleKeys) {
					add(
						labelItem -> {
							PortletURL removeLabelURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] removeContactRoleKeys = ArrayUtil.remove(
								contactRoleKeys, contactRoleKey);

							removeLabelURL.setParameter(
								"contactRoleKeys",
								StringUtil.merge(removeContactRoleKeys));

							labelItem.putData(
								"removeLabelURL", removeLabelURL.toString());

							labelItem.setCloseable(true);

							ContactRole contactRole =
								contactRoleWebService.getContactRole(
									contactRoleKey);

							String label = String.format(
								"%s: %s",
								LanguageUtil.get(
									httpServletRequest, "contact-role"),
								contactRole.getName());

							labelItem.setLabel(label);
						});
				}
			}
		};
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-contacts-were-found");

		StringBundler sb = new StringBundler();

		sb.append("workerAccountKeys/any(s:s eq '");
		sb.append(account.getKey());
		sb.append("')");

		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		if (!ArrayUtil.isEmpty(contactRoleKeys)) {
			sb.append(" and accountKeysContactRoleKeys/any(s:");

			for (int i = 0; i < contactRoleKeys.length; i++) {
				if (i > 0) {
					sb.append(" or ");
				}

				sb.append("s eq '");
				sb.append(account.getKey());
				sb.append("_");
				sb.append(contactRoleKeys[i]);
				sb.append("'");
			}

			sb.append(")");
		}

		List<Contact> contacts = contactWebService.search(
			keywords, sb.toString(), searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						contactRoleWebService.getAccountWorkerContactRoles(
							account.getKey(), contact.getEmailAddress(), 1,
							1000);

					return new ContactDisplay(
						httpServletRequest, 0, contact, contactRoles);
				}));

		int count = (int)contactWebService.searchCount(keywords, sb.toString());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private List<JSONObject> _getContactRoleJSONObjects() throws Exception {
		List<JSONObject> contactRoleJSONObjects = new ArrayList<>();

		for (ContactRole contactRole : _getContactRoles()) {
			contactRoleJSONObjects.add(
				JSONUtil.put(
					"key", contactRole.getKey()
				).put(
					"name", contactRole.getName()
				));
		}

		return contactRoleJSONObjects;
	}

	private List<String> _getContactRoleKeys(Contact contact) throws Exception {
		List<String> contactRoleKeys = new ArrayList<>();

		List<ContactRole> contactRoles =
			contactRoleWebService.getAccountWorkerContactRoles(
				account.getKey(), contact.getEmailAddress(), 1, 1000);

		for (ContactRole contactRole : contactRoles) {
			contactRoleKeys.add(contactRole.getKey());
		}

		return contactRoleKeys;
	}

	private List<ContactRole> _getContactRoles() throws Exception {
		return contactRoleWebService.search(
			"type eq '" + ContactRole.Type.ACCOUNT_WORKER.toString() + "'", 1,
			1000, "name");
	}

	private List<DropdownItem> _getFilterRoleDropdownItems() throws Exception {
		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		return new DropdownItemList() {
			{
				for (ContactRole contactRole : _getContactRoles()) {
					add(
						dropdownItem -> {
							dropdownItem.setActive(
								ArrayUtil.contains(
									contactRoleKeys, contactRole.getKey()));

							PortletURL portletURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] newContactRoleKeys = ArrayUtil.append(
								contactRoleKeys, contactRole.getKey());

							dropdownItem.setHref(
								portletURL, "contactRoleKeys",
								StringUtil.merge(newContactRoleKeys));

							dropdownItem.setLabel(contactRole.getName());
						});
				}
			}
		};
	}

}
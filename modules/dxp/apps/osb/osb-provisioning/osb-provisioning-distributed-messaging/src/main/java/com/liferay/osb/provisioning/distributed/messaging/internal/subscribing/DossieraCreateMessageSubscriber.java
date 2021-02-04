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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.distributed.messaging.internal.configuration.DistributedMessagingConfiguration;
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.SalesforceConstants;
import com.liferay.osb.provisioning.identity.management.provider.IdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.petra.content.ContentUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.lock.model.Lock;
import com.liferay.portal.lock.service.LockLocalService;

import java.net.URL;

import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.distributed.messaging.internal.configuration.DistributedMessagingConfiguration",
	immediate = true, property = "topic.pattern=dossiera.provisioning.create",
	service = DossieraCreateMessageSubscriber.class
)
public class DossieraCreateMessageSubscriber extends BaseMessageSubscriber {

	@Activate
	protected void activate(Map<String, Object> properties) {
		_distributedMessagingConfiguration =
			ConfigurableUtil.createConfigurable(
				DistributedMessagingConfiguration.class, properties);
	}

	protected void checkWarnings(
			String accountKey, Account account, Account partnerAccount,
			boolean partnerFirstLineSupport, List<Contact> inactiveContacts,
			List<Contact> missingContacts, String salesforceOpportunityTypeName,
			int salesforceOpportunityType)
		throws Exception {

		if (Validator.isNull(accountKey) &&
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS)) {

			_logWarning(
				"The opportunity type is " + salesforceOpportunityTypeName +
					" and the project does not exists");
		}

		if (Validator.isNotNull(accountKey) &&
			((salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS) ||
			 (salesforceOpportunityType ==
				 SalesforceConstants.
					 OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS))) {

			_logWarning(
				"The opportunity type is " + salesforceOpportunityTypeName +
					" and the project already exists");
		}

		StringBundler sb = new StringBundler(5);

		sb.append("accountKeysContactRoleKeys/any(s:s eq '");
		sb.append(accountKey);
		sb.append(StringPool.UNDERLINE);

		ContactRole supportDeveloperContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_DEVELOPER);

		sb.append(supportDeveloperContactRole.getKey());

		sb.append("')");

		long curDeveloperCount = _contactWebService.searchCount(
			StringPool.BLANK, sb.toString());

		int maxDeveloperCount = _accountReader.getMaxDeveloperCount(account);

		if (curDeveloperCount > maxDeveloperCount) {
			_logWarning(
				StringBundler.concat(
					"Maximum developer contacts is ", maxDeveloperCount,
					" but there are ", curDeveloperCount,
					" developer contacts"));
		}

		if (!inactiveContacts.isEmpty()) {
			sb = new StringBundler((2 * inactiveContacts.size()) + 2);

			sb.append("The following inactive contact(s) cannot be assigned ");
			sb.append("to the account:<br />");

			for (Contact contact : inactiveContacts) {
				sb.append(contact.getEmailAddress());
				sb.append("<br />");
			}

			_logWarning(sb.toString());
		}

		if (!missingContacts.isEmpty()) {
			sb = new StringBundler((2 * missingContacts.size()) + 2);

			sb.append("The following missing contact(s) cannot be assigned ");
			sb.append("to the account:<br />");

			for (Contact contact : missingContacts) {
				sb.append(contact.getEmailAddress());
				sb.append("<br />");
			}

			_logWarning(sb.toString());
		}

		Team curPartnerTeam = _accountReader.getPartnerTeam(account);

		String curPartnerAccountKey = StringPool.BLANK;

		if (curPartnerTeam != null) {
			curPartnerAccountKey = curPartnerTeam.getAccountKey();
		}

		String partnerAccountKey = StringPool.BLANK;

		if (partnerAccount != null) {
			partnerAccountKey = partnerAccount.getKey();
		}

		if (!curPartnerAccountKey.equals(partnerAccountKey)) {
			sb = new StringBundler(5);

			sb.append("The partner account (");

			if (partnerAccount != null) {
				sb.append(partnerAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(") is different from the existing partner account (");

			if (curPartnerTeam != null) {
				Account curPartnerAccount = _accountWebService.getAccount(
					curPartnerTeam.getAccountKey());

				sb.append(curPartnerAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(").");

			_logWarning(sb.toString());
		}

		Team curFLSTeam = _accountReader.getFirstLineSupportTeam(account);

		String curFLSAccountKey = StringPool.BLANK;

		if (curFLSTeam != null) {
			curFLSAccountKey = curFLSTeam.getAccountKey();
		}

		if (((curFLSTeam == null) && partnerFirstLineSupport) ||
			((curFLSTeam != null) &&
			 (!partnerFirstLineSupport ||
			  !curFLSAccountKey.equals(partnerAccountKey)))) {

			sb = new StringBundler(5);

			sb.append("The FLS partner account (");

			if (partnerAccount != null) {
				sb.append(partnerAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(") is different from the existing FLS partner account (");

			if (curFLSTeam != null) {
				Account curFLSAccount = _accountWebService.getAccount(
					curFLSTeam.getAccountKey());

				sb.append(curFLSAccount.getName());
			}
			else {
				sb.append("N/A");
			}

			sb.append(").");

			_logWarning(sb.toString());
		}
	}

	protected Account createAccount(
			Contact[] contacts, ExternalLink[] externalLinks,
			Account.Language language, Account.Region region,
			PostalAddress postalAddress, ProductPurchase[] productPurchases,
			Team[] partnerTeams, JSONObject jsonObject)
		throws Exception {

		Account account = new Account();

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");
		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		String accountName = accountJSONObject.getString("_name");

		if (projectJSONObject != null) {
			Account parentAccount = createParentAccount(jsonObject);

			String projectName = projectJSONObject.getString("_name");

			account.setName(projectName);
			account.setCode(_getCode(accountName, projectName));

			account.setParentAccountKey(parentAccount.getKey());
		}
		else {
			account.setName(accountName);
			account.setCode(_getCode(accountName, null));
		}

		accountName = account.getName();

		List<Account> duplicateAccounts = _accountWebService.search(
			StringPool.BLANK, "name eq '" + accountName + "'", 0, 1, null);

		if (!duplicateAccounts.isEmpty()) {
			_logWarning("Account name must be unique");
		}

		if (accountName.contains(StringPool.PIPE)) {
			_logWarning("Account name must not contain the | character");
		}

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		if (ownerJSONObject != null) {
			account.setContactEmailAddress(
				ownerJSONObject.getString("_emailAddress"));
		}

		account.setContacts(contacts);
		account.setExternalLinks(externalLinks);
		account.setPostalAddresses(new PostalAddress[] {postalAddress});
		account.setProductPurchases(productPurchases);
		account.setRegion(region);
		account.setLanguage(language);

		String productFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (!productFamily.equals("P")) {
			account.setTier(Account.Tier.T4);
		}

		account.setAssignedTeams(partnerTeams);

		return _accountWebService.addAccount(
			StringPool.BLANK, StringPool.BLANK, account);
	}

	protected void createAccountNote(JSONObject jsonObject, Account account)
		throws Exception {

		Note note = new Note();

		note.setContent(getNoteContent(jsonObject, account));
		note.setFormat(Note.Format.PLAIN);
		note.setStatus(Note.Status.APPROVED);
		note.setType(Note.Type.SALES);

		_noteWebService.addNote(
			StringPool.BLANK, StringPool.BLANK, account.getKey(), note);
	}

	protected Account createParentAccount(JSONObject jsonObject)
		throws Exception {

		JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

		String dossieraAccountKey = accountJSONObject.getString(
			"_dossieraAccountKey");

		Account parentAccount = fetchAccount(dossieraAccountKey);

		if (parentAccount != null) {
			return parentAccount;
		}

		parentAccount = new Account();

		String accountName = accountJSONObject.getString("_name");

		parentAccount.setName(accountName);
		parentAccount.setCode(_getCode(accountName, null));

		ExternalLink dossieraExternalLink = new ExternalLink();

		dossieraExternalLink.setDomain(ExternalLinkDomain.DOSSIERA);
		dossieraExternalLink.setEntityName(
			ExternalLinkEntityName.DOSSIERA_ACCOUNT);
		dossieraExternalLink.setEntityId(dossieraAccountKey);

		String salesforceAccountKey = jsonObject.getString(
			"_salesforceAccountKey");

		ExternalLink salesforceExternalLink = new ExternalLink();

		salesforceExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
		salesforceExternalLink.setEntityName(
			ExternalLinkEntityName.SALESFORCE_ACCOUNT);
		salesforceExternalLink.setEntityId(salesforceAccountKey);

		parentAccount.setExternalLinks(
			new ExternalLink[] {dossieraExternalLink, salesforceExternalLink});

		List<Account> duplicateAccounts = _accountWebService.search(
			StringPool.BLANK, "name eq '" + accountName + "'", 0, 1, null);

		if (!duplicateAccounts.isEmpty()) {
			_logWarning("Parent Account name must be unique");
		}

		if (accountName.contains(StringPool.PIPE)) {
			_logWarning("Parent Account name must not contain the | character");
		}

		return _accountWebService.addAccount(
			StringPool.BLANK, StringPool.BLANK, parentAccount);
	}

	protected void createZendeskTicket(
			Account account, PostalAddress postalAddress,
			List<ProductPurchase> productPurchases,
			String salesforceOpportunityTypeName,
			String salesforceOpportunityKey)
		throws Exception {

		ZendeskTicket zendeskTicket = new ZendeskTicket();

		Map<Long, String> customFields = new HashMap<>();

		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldProvisioningComponentId(),
			"opportunity_invoiced");
		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldOpportunityOwnerId(),
			account.getContactEmailAddress());
		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldPrimaryAddressCountryId(),
			postalAddress.getAddressCountry());
		customFields.put(
			_distributedMessagingConfiguration.zendeskCustomFieldProductId(),
			"Provisioning Request");

		String region = "provisioning_" + account.getRegionAsString();

		customFields.put(
			_distributedMessagingConfiguration.
				zendeskCustomFieldSupportRegionId(),
			StringUtil.replace(
				StringUtil.toLowerCase(region), CharPool.SPACE,
				CharPool.UNDERLINE));

		zendeskTicket.setCustomFields(customFields);

		StringBundler sb = new StringBundler(14);

		String accountName = account.getName();

		sb.append("Account Name: ");
		sb.append(accountName);
		sb.append("<br />Account Code: ");
		sb.append(account.getCode());
		sb.append("<br />Opportunity Type: ");
		sb.append(salesforceOpportunityTypeName);
		sb.append("<br />Date Created: ");
		sb.append(account.getDateCreated());
		sb.append("<br />Provisioning Account Link: <a href='");

		Group group = _groupLocalService.getFriendlyURLGroup(
			_portal.getDefaultCompanyId(), "/control_panel");

		Map<String, String[]> params = new LinkedHashMap<>();

		params.put(
			StringPool.UNDERLINE + ProvisioningPortletKeys.ACCOUNTS +
				"_mvcRenderCommandName",
			new String[] {"/accounts/view_account"});
		params.put(
			StringPool.UNDERLINE + ProvisioningPortletKeys.ACCOUNTS +
				"_accountKey",
			new String[] {account.getKey()});

		sb.append(
			_portal.getControlPanelFullURL(
				group.getGroupId(), ProvisioningPortletKeys.ACCOUNTS, params));

		sb.append("'>Provisioning Account</a><br />Salesforce Opportunity ");
		sb.append("Link: <a href='https://login.salesforce.com/");
		sb.append(salesforceOpportunityKey);
		sb.append("'>Salesforce Opportunity</a>");

		StringBundler subjectSB = new StringBundler(
			6 + (productPurchases.size() * 2));

		List<String> warningMessages = _warningMessagesThreadLocal.get();

		if (!warningMessages.isEmpty()) {
			sb.append("<br /><br />Warnings: ");

			for (String warningMessage : warningMessages) {
				sb.append("<br />");
				sb.append(warningMessage);
			}

			subjectSB = subjectSB.append("[Warning] ");
		}

		List<Note> pinnedNotes = _noteWebService.getNotes(
			account.getKey(), Note.Type.GENERAL.toString(), 1, StringPool.BLANK,
			1, 1000);

		if (!pinnedNotes.isEmpty()) {
			sb.append("<br /><br />Pinned Notes:");

			for (Note pinnedNote : pinnedNotes) {
				sb.append("<br /><br />");
				sb.append(pinnedNote.getDateCreated());
				sb.append("<br />");
				sb.append(pinnedNote.getContent());
			}
		}

		subjectSB.append(salesforceOpportunityTypeName);
		subjectSB.append(": ");

		Set<String> productTypes = new HashSet<>();

		sb.append("<br /><br />Products Purchased in This Opportunity:");

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			sb.append("<br /><br />");
			sb.append(product.getName());
			sb.append("<br />Start Date - End Date: ");
			sb.append(getDateRange(productPurchase));

			Map<String, String> properties = productPurchase.getProperties();

			if (properties != null) {
				String productType = properties.get("productType");

				if ((productType != null) &&
					!productTypes.contains(productType)) {

					if (!productTypes.isEmpty()) {
						subjectSB.append(", ");
					}

					subjectSB.append(productType);

					productTypes.add(productType);
				}
			}
		}

		if (!productTypes.isEmpty()) {
			subjectSB.append(StringPool.SPACE);
		}

		subjectSB.append("Subscription(s) for ");

		int maxLength = 150 - subjectSB.length();

		if (accountName.length() > maxLength) {
			subjectSB.append(
				accountName.substring(0, maxLength - accountName.length()));
		}
		else {
			subjectSB.append(accountName);
		}

		if (account.getRegion() == Account.Region.UNITED_STATES) {
			sb.append(
				StringBundler.concat(
					"<br /><br />US Provisioning - Working with 2019 H2 ",
					"Pricing Promotions: <a href='https://grow.liferay.com",
					"/people/US+Provisioning+-+Working+with+2019+H2+Pricing",
					"+Promotions'>Grow Link</a>"));
		}

		zendeskTicket.setDescription(sb.toString());

		zendeskTicket.setRequesterId(
			_distributedMessagingConfiguration.
				provisioningZendeskRequesterId());
		zendeskTicket.setSubject(subjectSB.toString());
		zendeskTicket.setZendeskOrganizationId(
			_distributedMessagingConfiguration.
				provisioningZendeskOrganizationId());

		_zendeskTicketWebService.createZendeskTicket(zendeskTicket);
	}

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Parsing message: " + jsonObject.toString());
		}

		if (!hasOpportunityProductFamily(jsonObject)) {
			return;
		}

		_warningMessagesThreadLocal.set(new ArrayList<String>());

		String salesforceOpportunityStageName = jsonObject.getString(
			"_salesforceOpportunityStageName");

		String salesforceOpportunityTypeName = jsonObject.getString(
			"_salesforceOpportunityType");

		int salesforceOpportunityType = getSalesforceOpportunityType(
			salesforceOpportunityTypeName);

		if (!_isValidOpportunity(
				salesforceOpportunityStageName, salesforceOpportunityType)) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Opportunity is not closed won or a renewal that is " +
						"closed lost");
			}

			return;
		}

		List<Contact> activeContacts = new ArrayList<>();
		List<Contact> inactiveContacts = new ArrayList<>();
		List<Contact> missingContacts = new ArrayList<>();

		if ((salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS) ||
			(salesforceOpportunityType ==
				SalesforceConstants.
					OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS)) {

			List<Contact> contacts = parseContacts(jsonObject);

			for (Contact contact : contacts) {
				Integer status = _identityProvider.fetchStatusByEmailAddress(
					contact.getEmailAddress());

				if (status == null) {
					missingContacts.add(contact);
				}
				else if (status == WorkflowConstants.STATUS_APPROVED) {
					activeContacts.add(contact);
				}
				else {
					inactiveContacts.add(contact);
				}
			}
		}

		List<ProductPurchase> productPurchases = parseProductPurchases(
			jsonObject);

		boolean analyticsCloud = hasAnalyticsCloud(productPurchases);

		PostalAddress postalAddress = parseAddress(jsonObject);

		Account.Language language = getLanguage(
			jsonObject, postalAddress.getAddressCountry());

		String languageId = _getLanguageId(language);

		String soldBy = jsonObject.getString("_salesforceOpportunitySoldBy");

		Account.Region region = getSupportRegion(
			soldBy, postalAddress.getAddressCountry());

		Account account = null;

		String accountKey = getAccountKey(jsonObject);

		Account partnerAccount = parsePartnerAccount(jsonObject);

		boolean partnerFirstLineSupport = jsonObject.getBoolean(
			"_partnerFirstLineSupport");

		if (Validator.isNotNull(accountKey)) {
			account = updateAccount(
				accountKey, activeContacts, region, productPurchases,
				jsonObject);
		}
		else {
			ExternalLink[] externalLinks = parseExternalLinks(jsonObject);

			Team[] partnerTeams = parsePartnerTeams(
				partnerAccount, partnerFirstLineSupport);

			account = createAccount(
				activeContacts.toArray(new Contact[0]), externalLinks, language,
				region, postalAddress,
				productPurchases.toArray(new ProductPurchase[0]), partnerTeams,
				jsonObject);

			if (analyticsCloud) {
				sendAnalyticsCloudWelcomeEmail(activeContacts, languageId);
			}
		}

		createAccountNote(jsonObject, account);

		checkWarnings(
			accountKey, account, partnerAccount, partnerFirstLineSupport,
			inactiveContacts, missingContacts, salesforceOpportunityTypeName,
			salesforceOpportunityType);

		String salesforceOpportunityProductFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (!salesforceOpportunityProductFamily.equals("P")) {
			String salesforceOpportunityKey = jsonObject.getString(
				"_salesforceOpportunityKey");

			createZendeskTicket(
				account, postalAddress, productPurchases,
				salesforceOpportunityTypeName, salesforceOpportunityKey);
		}

		for (Contact contact : missingContacts) {
			sendUserCreationEmail(contact, account, analyticsCloud, languageId);
		}
	}

	protected Account fetchAccount(String dossieraAccountKey) throws Exception {
		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_ACCOUNT, dossieraAccountKey, 1, 1);

		if (!accounts.isEmpty()) {
			return accounts.get(0);
		}

		return null;
	}

	protected String getAccountKey(JSONObject jsonObject) throws Exception {
		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		if (projectJSONObject != null) {
			String dossieraProjectKey = projectJSONObject.getString(
				"_dossieraProjectKey");

			List<Account> accounts = _accountWebService.getAccounts(
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_PROJECT, dossieraProjectKey, 1,
				1);

			if (!accounts.isEmpty()) {
				Account account = accounts.get(0);

				return account.getKey();
			}
		}
		else {
			JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

			String dossieraAccountKey = accountJSONObject.getString(
				"_dossieraAccountKey");

			Account account = fetchAccount(dossieraAccountKey);

			if (account != null) {
				return account.getKey();
			}
		}

		return null;
	}

	protected String getContactFullName(Contact contact) {
		StringBundler sb = new StringBundler(5);

		if (Validator.isNotNull(contact.getFirstName())) {
			sb.append(contact.getFirstName());
		}

		if (Validator.isNotNull(contact.getMiddleName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getMiddleName());
		}

		if (Validator.isNotNull(contact.getLastName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getLastName());
		}

		return sb.toString();
	}

	protected String getDateRange(ProductPurchase productPurchase) {
		if ((productPurchase.getStartDate() != null) &&
			(productPurchase.getOriginalEndDate() != null)) {

			Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"yyyy/MM/dd");

			StringBundler sb = new StringBundler(4);

			sb.append(dateFormat.format(productPurchase.getStartDate()));
			sb.append(" - ");
			sb.append(dateFormat.format(productPurchase.getOriginalEndDate()));
			sb.append(" (UTC)");

			return sb.toString();
		}

		return "Perpetual";
	}

	protected Account.Language getLanguage(
		JSONObject jsonObject, String country) {

		String soldBy = jsonObject.getString("_salesforceOpportunitySoldBy");

		if (Validator.isNull(soldBy)) {
			_logWarning(
				"Sold by field is empty. Defaulting support language to " +
					"English.");

			return Account.Language.ENGLISH;
		}

		if (soldBy.equals("Liferay Africa") ||
			soldBy.equals("Liferay Australia") ||
			soldBy.equals("Liferay Canada") ||
			soldBy.equals("Liferay France") ||
			soldBy.equals("Liferay Germany") ||
			soldBy.equals("Liferay Hungary") ||
			soldBy.equals("Liferay India") ||
			soldBy.equals("Liferay International") ||
			soldBy.equals("Liferay Italy") ||
			soldBy.equals("Liferay Middle East") ||
			soldBy.equals("Liferay Netherlands") ||
			soldBy.equals("Liferay Nordic") ||
			soldBy.equals("Liferay Singapore") || soldBy.equals("Liferay UK") ||
			soldBy.equals("Liferay US")) {

			return Account.Language.ENGLISH;
		}
		else if (soldBy.equals("Liferay Brazil")) {
			if (Validator.isNotNull(country) && country.equals("Brazil")) {
				return Account.Language.PORTUGUESE;
			}

			return Account.Language.SPANISH;
		}
		else if (soldBy.equals("Liferay China")) {
			if (Validator.isNotNull(country) && country.equals("China")) {
				return Account.Language.CHINESE;
			}

			return Account.Language.ENGLISH;
		}
		else if (soldBy.equals("Liferay Japan")) {
			return Account.Language.JAPANESE;
		}
		else if (soldBy.equals("Liferay Spain")) {
			if (Validator.isNotNull(country) &&
				(country.equals("Cyprus") || country.equals("Greece") ||
				 country.equals("Italy") || country.equals("Portugal"))) {

				return Account.Language.ENGLISH;
			}

			return Account.Language.SPANISH;
		}

		_logWarning(
			StringBundler.concat(
				"Unable to find matching support language for ", soldBy,
				" and ", country, ". Defaulting support language to English."));

		return Account.Language.ENGLISH;
	}

	protected String getNoteContent(JSONObject jsonObject, Account account)
		throws PortalException {

		ProductPurchase[] productPurchases = account.getProductPurchases();

		Map<String, Map<String, Integer>> subscriptionsMap = new TreeMap<>();

		for (ProductPurchase productPurchase : productPurchases) {
			String key = getDateRange(productPurchase);

			Map<String, Integer> productsMap = subscriptionsMap.get(key);

			if (productsMap == null) {
				productsMap = new TreeMap<>();

				subscriptionsMap.put(key, productsMap);
			}

			String productName = getNotesProductName(account, productPurchase);

			int quantity = GetterUtil.getInteger(productsMap.get(productName));

			quantity += productPurchase.getQuantity();

			productsMap.put(productName, quantity);
		}

		StringBundler sb = new StringBundler();

		for (Map.Entry<String, Map<String, Integer>> entry :
				subscriptionsMap.entrySet()) {

			String dateRange = entry.getKey();
			Map<String, Integer> productsMap = entry.getValue();

			sb.append("Subscriptions:");
			sb.append(StringPool.NEW_LINE);

			for (Map.Entry<String, Integer> productsEntry :
					productsMap.entrySet()) {

				sb.append(StringPool.TAB);
				sb.append(productsEntry.getKey());
				sb.append(" (");
				sb.append(productsEntry.getValue());
				sb.append(")");
				sb.append(StringPool.NEW_LINE);
			}

			sb.append("Dates: ");
			sb.append(dateRange);
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.NEW_LINE);
		}

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		sb.append("Owner: ");
		sb.append(ownerJSONObject.getString("_firstName"));
		sb.append(StringPool.SPACE);
		sb.append(ownerJSONObject.getString("_lastName"));
		sb.append(StringPool.NEW_LINE);

		sb.append("SFDC: https://login.salesforce.com/");
		sb.append(jsonObject.getString("_salesforceOpportunityKey"));

		return sb.toString();
	}

	protected String getNotesProductName(
			Account account, ProductPurchase productPurchase)
		throws PortalException {

		Map<String, String> properties = productPurchase.getProperties();

		String productType = properties.get("productType");

		if ((productType != null) &&
			productType.equals(SalesforceConstants.PRODUCT_TYPE_RENEWAL)) {

			return productType;
		}

		StringBundler sb = new StringBundler(8);

		if (Validator.isNotNull(productType)) {
			sb.append(productType);
			sb.append(StringPool.SPACE);
		}

		Product product = productPurchase.getProduct();

		String productName = product.getName();

		if (!ArrayUtil.contains(
				ProductConstants.NAMES_SUBSCRIPTION, productName)) {

			ProductPurchase slaProductPurchase =
				_accountReader.getSLAProductPurchase(account);

			if (slaProductPurchase != null) {
				Product slaProduct = slaProductPurchase.getProduct();

				if (slaProduct != null) {
					sb.append(
						StringUtil.removeSubstring(
							slaProduct.getName(), " Subscription"));

					sb.append(StringPool.SPACE);
				}
			}
		}

		sb.append(productName);

		sb.append(StringPool.SPACE);

		if (!productName.contains("Sizing") &&
			Validator.isNotNull(properties.get("sizing"))) {

			sb.append("Sizing ");
			sb.append(properties.get("sizing"));
		}

		return sb.toString();
	}

	protected PostalAddress getPostalAddress(JSONObject jsonObject) {
		PostalAddress postalAddress = new PostalAddress();

		String city = jsonObject.getString("_city");

		city = ModelHintsUtil.trimString(Address.class.getName(), "city", city);

		postalAddress.setAddressLocality(city);

		String countryName = jsonObject.getString("_country");

		if (Validator.isNotNull(countryName)) {
			postalAddress.setAddressCountry(countryName);

			String regionName = jsonObject.getString("_region");

			postalAddress.setAddressRegion(regionName);
		}

		String street = jsonObject.getString("_street");

		String street1 = street;

		String street2 = StringPool.BLANK;
		String street3 = StringPool.BLANK;

		int maxLength = ModelHintsUtil.getMaxLength(
			Address.class.getName(), "street1");

		if (street1.length() > maxLength) {
			street1 = street1.substring(0, maxLength);

			street2 = street.substring(maxLength);

			if (street2.length() > maxLength) {
				street2 = street2.substring(0, maxLength);

				street3 = street.substring(maxLength * 2);

				if (street3.length() > maxLength) {
					street3 = street3.substring(0, maxLength);
				}
			}
		}

		postalAddress.setStreetAddressLine1(street1);
		postalAddress.setStreetAddressLine2(street2);
		postalAddress.setStreetAddressLine3(street3);

		postalAddress.setPostalCode(jsonObject.getString("_postalCode"));

		return postalAddress;
	}

	protected String getProvisioningEmailAddress(String accountRegion) {
		if (accountRegion.equals(Account.Region.AUSTRALIA.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressAustralia();
		}
		else if (accountRegion.equals(Account.Region.BRAZIL.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressBrazil();
		}
		else if (accountRegion.equals(Account.Region.CHINA.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressChina();
		}
		else if (accountRegion.equals(Account.Region.HUNGARY.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressHungary();
		}
		else if (accountRegion.equals(Account.Region.INDIA.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressIndia();
		}
		else if (accountRegion.equals(Account.Region.JAPAN.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressJapan();
		}
		else if (accountRegion.equals(Account.Region.SPAIN.toString())) {
			return _distributedMessagingConfiguration.
				provisioningEmailAddressSpain();
		}
		else if (accountRegion.equals(
					Account.Region.UNITED_STATES.toString())) {

			return _distributedMessagingConfiguration.
				provisioningEmailAddressUS();
		}

		return _distributedMessagingConfiguration.
			provisioningEmailAddressGlobal();
	}

	protected int getSalesforceOpportunityType(
		String salesforceOpportunityTypeName) {

		if (StringUtil.equalsIgnoreCase(
				salesforceOpportunityTypeName, "Existing Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_EXISTING_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "New Business")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_NEW_BUSINESS;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName, "Renewal")) {

			return SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL;
		}
		else if (StringUtil.equalsIgnoreCase(
					salesforceOpportunityTypeName,
					"New Project Existing Business")) {

			return SalesforceConstants.
				OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS;
		}
		else {
			return 0;
		}
	}

	protected Account.Region getSupportRegion(
		String soldBy, String countryName) {

		if (Validator.isNull(soldBy)) {
			_logWarning(
				"Sold by field is empty. Defaulting support region to global.");

			return Account.Region.GLOBAL;
		}

		if (soldBy.equals("Liferay Africa") ||
			soldBy.equals("Liferay France") ||
			soldBy.equals("Liferay Germany") ||
			soldBy.equals("Liferay Hungary") ||
			soldBy.equals("Liferay International") ||
			soldBy.equals("Liferay Italy") ||
			soldBy.equals("Liferay Middle East") ||
			soldBy.equals("Liferay Netherlands") ||
			soldBy.equals("Liferay Nordic") || soldBy.equals("Liferay UK")) {

			return Account.Region.HUNGARY;
		}
		else if (soldBy.equals("Liferay Australia")) {
			return Account.Region.AUSTRALIA;
		}
		else if (soldBy.equals("Liferay Brazil")) {
			return Account.Region.BRAZIL;
		}
		else if (soldBy.equals("Liferay Canada") ||
				 soldBy.equals("Liferay US")) {

			return Account.Region.UNITED_STATES;
		}
		else if (soldBy.equals("Liferay China") ||
				 soldBy.equals("Liferay Singapore")) {

			return Account.Region.CHINA;
		}
		else if (soldBy.equals("Liferay India")) {
			return Account.Region.INDIA;
		}
		else if (soldBy.equals("Liferay Japan")) {
			return Account.Region.JAPAN;
		}
		else if (soldBy.equals("Liferay Spain")) {
			if (Validator.isNotNull(countryName) &&
				(countryName.equals("Cypress") ||
				 countryName.equals("Greece") || countryName.equals("Italy"))) {

				return Account.Region.HUNGARY;
			}

			return Account.Region.SPAIN;
		}

		_logWarning(
			StringBundler.concat(
				"Unable to find matching support region for ", soldBy, " and ",
				countryName, ". Defaulting support region to global."));

		return Account.Region.GLOBAL;
	}

	@Override
	protected void handleError(
			String routingKey, String message, Exception exception)
		throws PortalException {

		ZendeskTicket zendeskTicket = new ZendeskTicket();

		Map<Long, String> customFields = new HashMap<>();

		customFields.put(
			_distributedMessagingConfiguration.zendeskCustomFieldProductId(),
			"Provisioning Request");

		zendeskTicket.setCustomFields(customFields);

		StringBundler sb = new StringBundler(7);

		sb.append("An unexpected error occurred.<br />Routing Key: ");
		sb.append(routingKey);
		sb.append("<br />Message:<br /><pre>");
		sb.append(message);
		sb.append("</pre><br />Error:<br /><pre>");
		sb.append(StackTraceUtil.getStackTrace(exception));
		sb.append("</pre>");

		_log.error("Creating error Zendesk ticket: " + sb.toString());

		zendeskTicket.setDescription(sb.toString());

		zendeskTicket.setGroupId(
			_distributedMessagingConfiguration.provisioningZendeskGroupId());
		zendeskTicket.setRequesterId(
			_distributedMessagingConfiguration.
				provisioningZendeskRequesterId());
		zendeskTicket.setSubject("Auto-Provisioning Error");
		zendeskTicket.setZendeskOrganizationId(
			_distributedMessagingConfiguration.
				provisioningZendeskOrganizationId());

		zendeskTicketWebService.createZendeskTicket(zendeskTicket);
	}

	protected boolean hasAnalyticsCloud(
		List<ProductPurchase> productPurchases) {

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			String name = product.getName();

			if (name.equals(
					"Liferay Analytics Cloud Subscription - Business") ||
				name.equals(
					"Liferay Analytics Cloud Subscription - Enterprise")) {

				return true;
			}
		}

		return false;
	}

	protected boolean hasOpportunityProductFamily(JSONObject jsonObject) {
		String salesforceOpportunityProductFamily = jsonObject.getString(
			"_salesforceOpportunityProductFamily");

		if (Validator.isNull(salesforceOpportunityProductFamily)) {
			return false;
		}

		for (String productFamilyToken : _PRODUCT_FAMILY_TOKENS) {
			if (salesforceOpportunityProductFamily.contains(
					productFamilyToken)) {

				return true;
			}
		}

		return false;
	}

	@Override
	protected boolean isParseMessage(Message message) throws Exception {
		String salesforceOpportunityKey = _getSalesforceOpportunityKey(message);

		if (Validator.isNull(salesforceOpportunityKey)) {
			return false;
		}

		String owner = null;

		ClusterNode localClusterNode =
			ClusterExecutorUtil.getLocalClusterNode();

		if (localClusterNode != null) {
			owner = localClusterNode.getClusterNodeId();
		}

		Lock lock = _lockLocalService.lock(
			Message.class.getName(), salesforceOpportunityKey, owner);

		if (!lock.isNew()) {
			return false;
		}

		StringBundler sb = new StringBundler(7);

		sb.append("externalLinkEntityIds/any(s:s eq '");
		sb.append(ExternalLinkDomain.SALESFORCE);
		sb.append(StringPool.UNDERLINE);
		sb.append(ExternalLinkEntityName.SALESFORCE_OPPORTUNITY);
		sb.append(StringPool.UNDERLINE);
		sb.append(salesforceOpportunityKey);
		sb.append("')");

		long productPurchaseCount =
			_productPurchaseWebService.getProductPurchasesCount(sb.toString());

		if (productPurchaseCount > 0) {
			return false;
		}

		return true;
	}

	protected PostalAddress parseAddress(JSONObject jsonObject) {
		JSONObject billingAddressJSONObject = jsonObject.getJSONObject(
			"_billingAddress");
		JSONObject shippingAddressJSONObject = jsonObject.getJSONObject(
			"_shippingAddress");

		PostalAddress postalAddress = null;

		if (shippingAddressJSONObject != null) {
			postalAddress = getPostalAddress(shippingAddressJSONObject);
		}
		else if (billingAddressJSONObject != null) {
			postalAddress = getPostalAddress(billingAddressJSONObject);
		}
		else {
			postalAddress = new PostalAddress();
		}

		if (Validator.isNull(postalAddress.getAddressLocality())) {
			postalAddress.setAddressLocality("N/A");
		}

		if (Validator.isNull(postalAddress.getStreetAddressLine1())) {
			postalAddress.setStreetAddressLine1("N/A");
		}

		if (Validator.isNull(postalAddress.getPostalCode())) {
			postalAddress.setPostalCode("N/A");
		}

		postalAddress.setPrimary(true);

		return postalAddress;
	}

	protected List<Contact> parseContacts(JSONObject jsonObject)
		throws Exception {

		List<Contact> contacts = new ArrayList<>();

		JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

		if (ownerJSONObject != null) {
			Contact contact = new Contact();

			contact.setFirstName(ownerJSONObject.getString("_firstName"));
			contact.setLastName(ownerJSONObject.getString("_lastName"));
			contact.setEmailAddress(ownerJSONObject.getString("_emailAddress"));

			ContactRole contactRole = _contactRoleWebService.fetchContactRole(
				ContactRole.Type.ACCOUNT_WORKER.toString(),
				ContactRoleConstants.NAME_LIFERAY_SALES);

			contact.setContactRoles(new ContactRole[] {contactRole});

			contacts.add(contact);
		}

		JSONArray contactsJSONArray = jsonObject.getJSONArray("_contacts");

		if (contactsJSONArray == null) {
			return contacts;
		}

		for (int i = 0; i < contactsJSONArray.length(); i++) {
			JSONObject contactJSONObject = contactsJSONArray.getJSONObject(i);

			Contact contact = new Contact();

			contact.setFirstName(contactJSONObject.getString("_firstName"));
			contact.setLastName(contactJSONObject.getString("_lastName"));
			contact.setEmailAddress(
				contactJSONObject.getString("_emailAddress"));

			ContactRole contactRole = _contactRoleWebService.fetchContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_WATCHER);

			contact.setContactRoles(new ContactRole[] {contactRole});

			contacts.add(contact);
		}

		return contacts;
	}

	protected ExternalLink[] parseExternalLinks(JSONObject jsonObject) {
		String salesforceAccountKey = jsonObject.getString(
			"_salesforceAccountKey");

		ExternalLink accountExternalLink = new ExternalLink();

		accountExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
		accountExternalLink.setEntityName(
			ExternalLinkEntityName.SALESFORCE_ACCOUNT);
		accountExternalLink.setEntityId(salesforceAccountKey);

		ExternalLink dossieraExternalLink = new ExternalLink();

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		if (projectJSONObject != null) {
			String dossieraProjectKey = projectJSONObject.getString(
				"_dossieraProjectKey");

			dossieraExternalLink.setDomain(ExternalLinkDomain.DOSSIERA);
			dossieraExternalLink.setEntityName(
				ExternalLinkEntityName.DOSSIERA_PROJECT);
			dossieraExternalLink.setEntityId(dossieraProjectKey);
		}
		else {
			JSONObject accountJSONObject = jsonObject.getJSONObject("_account");

			String dossieraAccountKey = accountJSONObject.getString(
				"_dossieraAccountKey");

			dossieraExternalLink.setDomain(ExternalLinkDomain.DOSSIERA);
			dossieraExternalLink.setEntityName(
				ExternalLinkEntityName.DOSSIERA_ACCOUNT);
			dossieraExternalLink.setEntityId(dossieraAccountKey);
		}

		String salesforceProjectKey = jsonObject.getString(
			"_salesforceProjectKey");

		if (Validator.isNull(salesforceProjectKey)) {
			return new ExternalLink[] {
				accountExternalLink, dossieraExternalLink
			};
		}

		ExternalLink projectExternalLink = new ExternalLink();

		projectExternalLink.setDomain(ExternalLinkDomain.SALESFORCE);
		projectExternalLink.setEntityName(
			ExternalLinkEntityName.SALESFORCE_PROJECT);
		projectExternalLink.setEntityId(salesforceProjectKey);

		return new ExternalLink[] {
			accountExternalLink, dossieraExternalLink, projectExternalLink
		};
	}

	protected Account parsePartnerAccount(JSONObject jsonObject)
		throws Exception {

		JSONObject partnerAccountJSONObject = jsonObject.getJSONObject(
			"_partnerAccount");

		if (partnerAccountJSONObject == null) {
			return null;
		}

		String dossieraAccountKey = partnerAccountJSONObject.getString(
			"_dossieraAccountKey");

		if (Validator.isNull(dossieraAccountKey)) {
			return null;
		}

		return fetchAccount(dossieraAccountKey);
	}

	protected Team[] parsePartnerTeams(
			Account partnerAccount, boolean partnerFirstLineSupport)
		throws Exception {

		TeamRole partnerTeamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(), TeamRoleConstants.NAME_PARTNER);

		if ((partnerAccount == null) || (partnerTeamRole == null)) {
			return new Team[0];
		}

		String filterString = StringBundler.concat(
			"accountKey eq '", partnerAccount.getKey(), "' and system eq true");

		List<Team> partnerDefaultTeams = _teamWebService.search(
			StringPool.BLANK, filterString, 1, 1, StringPool.BLANK);

		if (partnerDefaultTeams.isEmpty()) {
			return new Team[0];
		}

		Team partnerDefaultTeam = partnerDefaultTeams.get(0);

		partnerDefaultTeam.setTeamRoles(new TeamRole[] {partnerTeamRole});

		TeamRole flsTeamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(),
			TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

		if ((flsTeamRole == null) || !partnerFirstLineSupport) {
			return new Team[] {partnerDefaultTeam};
		}

		filterString = StringBundler.concat(
			"accountKey eq '", partnerAccount.getKey(),
			"' and contains(name, 'FLS')");

		List<Team> partnerFLSTeams = _teamWebService.search(
			StringPool.BLANK, filterString, 1, 1, StringPool.BLANK);

		if (!partnerFLSTeams.isEmpty()) {
			Team partnerFLSTeam = partnerFLSTeams.get(0);

			partnerFLSTeam.setTeamRoles(new TeamRole[] {flsTeamRole});

			return new Team[] {partnerDefaultTeam, partnerFLSTeam};
		}

		partnerDefaultTeam.setTeamRoles(
			new TeamRole[] {partnerTeamRole, flsTeamRole});

		return new Team[] {partnerDefaultTeam};
	}

	protected List<ProductPurchase> parseProductPurchases(JSONObject jsonObject)
		throws Exception {

		JSONArray bundledProductsJSONArray = jsonObject.getJSONArray(
			"_bundledProducts");

		if (bundledProductsJSONArray == null) {
			return Collections.emptyList();
		}

		String salesforceOpportunityKey = jsonObject.getString(
			"_salesforceOpportunityKey");

		ExternalLink externalLink = null;

		if (Validator.isNotNull(salesforceOpportunityKey)) {
			externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.SALESFORCE);
			externalLink.setEntityName(
				ExternalLinkEntityName.SALESFORCE_OPPORTUNITY);
			externalLink.setEntityId(salesforceOpportunityKey);
		}

		List<ProductPurchase> productPurchases = new ArrayList<>();

		for (int i = 0; i < bundledProductsJSONArray.length(); i++) {
			JSONObject bundledProductJSONObject =
				bundledProductsJSONArray.getJSONObject(i);

			JSONArray purchasedProductsJSONArray =
				bundledProductJSONObject.getJSONArray("_purchasedProducts");

			for (int j = 0; j < purchasedProductsJSONArray.length(); j++) {
				JSONObject purchasedProductJSONObject =
					purchasedProductsJSONArray.getJSONObject(j);

				ProductPurchase productPurchase = new ProductPurchase();

				Date startDate = _portal.getDate(
					purchasedProductJSONObject.getInt("_startMonth") - 1,
					purchasedProductJSONObject.getInt("_startDay"),
					purchasedProductJSONObject.getInt("_startYear"));

				Date originalEndDate = _portal.getDate(
					purchasedProductJSONObject.getInt("_endMonth") - 1,
					purchasedProductJSONObject.getInt("_endDay"),
					purchasedProductJSONObject.getInt("_endYear"));

				if ((startDate != null) && (originalEndDate != null)) {
					productPurchase.setStartDate(startDate);

					productPurchase.setOriginalEndDate(originalEndDate);

					Calendar calendar = Calendar.getInstance();

					calendar.setTime(originalEndDate);

					calendar.add(Calendar.DATE, 30);

					productPurchase.setEndDate(calendar.getTime());
				}
				else {
					productPurchase.setPerpetual(true);
				}

				Product product = _getProduct(
					purchasedProductJSONObject.getString("_name"));

				productPurchase.setProduct(product);

				int quantity = purchasedProductJSONObject.getInt("_quantity");

				if (quantity > 0) {
					productPurchase.setQuantity(quantity);
				}

				Map<String, String> properties = new HashMap<>();

				String environment = purchasedProductJSONObject.getString(
					"_environment");

				if (Validator.isNotNull(environment)) {
					properties.put("environment", environment);
				}

				String productType = purchasedProductJSONObject.getString(
					"_productType");

				if (Validator.isNotNull(productType)) {
					properties.put("productType", productType);
				}

				String sizing = purchasedProductJSONObject.getString("_sizing");

				if (Validator.isNotNull(sizing) &&
					sizing.startsWith("Sizing ")) {

					properties.put("sizing", sizing.substring(7));
				}

				if (!properties.isEmpty()) {
					productPurchase.setProperties(properties);
				}

				if (externalLink != null) {
					productPurchase.setExternalLinks(
						new ExternalLink[] {externalLink});
				}

				productPurchases.add(productPurchase);
			}
		}

		return productPurchases;
	}

	@Override
	protected void postParseMessage(Message message) {
		String salesforceOpportunityKey = _getSalesforceOpportunityKey(message);

		if (Validator.isNull(salesforceOpportunityKey)) {
			return;
		}

		_lockLocalService.unlock(
			Message.class.getName(), salesforceOpportunityKey);
	}

	protected void sendAnalyticsCloudWelcomeEmail(
			List<Contact> contacts, String languageId)
		throws PortalException {

		for (Contact contact : contacts) {
			String body = _getEmailTemplate(
				"email_analytics_cloud_welcome_body_" + languageId + ".tmpl",
				"email_analytics_cloud_welcome_body.tmpl");

			Locale locale = LocaleUtil.fromLanguageId(languageId);

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			String subject = LanguageUtil.get(
				resourceBundle, "you-have-been-invited-to-analytics-cloud");

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			subscriptionSender.setBody(body);
			subscriptionSender.setCompanyId(_portal.getDefaultCompanyId());
			subscriptionSender.setFrom(
				"no-reply@liferay.com", "Liferay Analytics Cloud");
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setMailId(
				"analytics_cloud_welcome", contact.getKey());
			subscriptionSender.setReplyToAddress("no-reply@liferay.com");
			subscriptionSender.setSubject(subject);

			subscriptionSender.addRuntimeSubscribers(
				contact.getEmailAddress(), getContactFullName(contact));

			subscriptionSender.flushNotificationsAsync();
		}
	}

	protected void sendUserCreationEmail(
		Contact contact, Account account, boolean analyticsCloud,
		String languageId) {

		String body = _getEmailTemplate(
			"email_provisioning_create_account_body_" + languageId + ".tmpl",
			"email_provisioning_create_account_body.tmpl");

		String provisioningEmailAddress = getProvisioningEmailAddress(
			account.getRegionAsString());

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		StringBundler sb = new StringBundler(2);

		if (analyticsCloud) {
			sb.append("analytics-cloud-");
		}

		sb.append("help-center-all-of-our-downloads-and-our-support-system");

		String subscriptionServices = LanguageUtil.get(
			resourceBundle, sb.toString());
		String subject = LanguageUtil.get(
			resourceBundle, "welcome-to-liferay-support");

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(_portal.getDefaultCompanyId());
		subscriptionSender.setContextAttributes(
			"[$ACCOUNT_ENTRY_NAME$]", account.getName(),
			"[$SUBSCRIPTION_SERVICES$]", subscriptionServices, "[$TO_NAME$]",
			getContactFullName(contact));
		subscriptionSender.setFrom(
			provisioningEmailAddress, "Liferay Provisioning");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("provisioning");
		subscriptionSender.setReplyToAddress(provisioningEmailAddress);
		subscriptionSender.setSubject(subject);

		subscriptionSender.addRuntimeSubscribers(
			contact.getEmailAddress(), getContactFullName(contact));
		subscriptionSender.addRuntimeSubscribers(
			provisioningEmailAddress, getContactFullName(contact));

		subscriptionSender.flushNotificationsAsync();
	}

	protected Account updateAccount(
			String accountKey, List<Contact> contacts, Account.Region region,
			List<ProductPurchase> productPurchases, JSONObject jsonObject)
		throws Exception {

		Account account = _accountWebService.getAccount(accountKey);

		if (Validator.isNull(account.getContactEmailAddress()) ||
			Validator.isNull(account.getRegion())) {

			if (Validator.isNull(account.getContactEmailAddress())) {
				JSONObject ownerJSONObject = jsonObject.getJSONObject("_owner");

				if (ownerJSONObject != null) {
					account.setContactEmailAddress(
						ownerJSONObject.getString("_emailAddress"));
				}
			}

			if (Validator.isNull(account.getRegion())) {
				account.setRegion(region);
			}

			_accountWebService.updateAccount(
				StringPool.BLANK, StringPool.BLANK, accountKey, account);
		}

		for (Contact contact : contacts) {
			Contact curContact = _contactWebService.fetchContactByEmailAddress(
				contact.getEmailAddress());

			if (curContact == null) {
				_contactWebService.addContact(
					StringPool.BLANK, StringPool.BLANK, contact);
			}

			ContactRole[] contactRoles = contact.getContactRoles();

			String[] contactRoleKeys = new String[contactRoles.length];

			for (int i = 0; i < contactRoles.length; i++) {
				ContactRole contactRole = contactRoles[i];

				contactRoleKeys[i] = contactRole.getKey();
			}

			_accountWebService.assignContactRoles(
				StringPool.BLANK, StringPool.BLANK, accountKey,
				contact.getEmailAddress(), contactRoleKeys);
		}

		StringBundler sb = new StringBundler(3);

		sb.append("accountKey eq '");
		sb.append(accountKey);
		sb.append("' and state eq 'Active'");

		List<ProductPurchase> curProductPurchases =
			_productPurchaseWebService.getProductPurchases(
				sb.toString(), 1, 1000, StringPool.BLANK);

		Date newStartDate = new Date();

		boolean renewal = false;

		for (ProductPurchase productPurchase : productPurchases) {
			Map<String, String> properties = productPurchase.getProperties();

			if (!renewal &&
				ArrayUtil.contains(
					SalesforceConstants.PRODUCT_TYPES_RENEWAL,
					properties.get("productType"))) {

				renewal = true;
			}

			if (renewal) {
				Product product = productPurchase.getProduct();

				if (ArrayUtil.contains(
						ProductConstants.NAMES_PARTNERSHIP,
						product.getName()) ||
					ArrayUtil.contains(
						ProductConstants.NAMES_SUBSCRIPTION,
						product.getName())) {

					newStartDate = productPurchase.getStartDate();
				}
			}

			_productPurchaseWebService.addProductPurchase(
				StringPool.BLANK, StringPool.BLANK, accountKey,
				productPurchase);
		}

		if (renewal) {
			for (ProductPurchase productPurchase : curProductPurchases) {
				if (newStartDate.before(productPurchase.getEndDate())) {
					productPurchase.setEndDate(newStartDate);

					_productPurchaseWebService.updateProductPurchase(
						StringPool.BLANK, StringPool.BLANK,
						productPurchase.getKey(), productPurchase);
				}
			}
		}

		return _accountWebService.getAccount(accountKey);
	}

	private static String _getEmailTemplate(
		String templateName, String defaultTemplateName) {

		ClassLoader classLoader =
			DossieraCreateMessageSubscriber.class.getClassLoader();

		String templateDirName =
			"com/liferay/osb/provisioning/distributed/messaging/internal" +
				"/dependencies/";

		URL url = classLoader.getResource(templateDirName + templateName);

		if (url != null) {
			return ContentUtil.get(
				DossieraCreateMessageSubscriber.class.getClassLoader(),
				templateDirName + templateName);
		}

		return ContentUtil.get(
			DossieraCreateMessageSubscriber.class.getClassLoader(),
			templateDirName + defaultTemplateName);
	}

	private String _getCode(String parentAccountName, String accountName)
		throws Exception {

		String code = StringUtil.extractChars(parentAccountName);

		if (code.length() > 6) {
			code = code.substring(0, 6);
		}

		if (accountName != null) {
			code += StringUtil.extractChars(accountName);
		}

		if (code.length() > 12) {
			code = code.substring(0, 12);
		}

		code = StringUtil.toUpperCase(code);

		if (!_isDuplicateCode(code)) {
			return code;
		}

		int i = 1;

		while (true) {
			String tempCode = code + i;

			if (!_isDuplicateCode(tempCode)) {
				return tempCode;
			}

			i++;
		}
	}

	private String _getLanguageId(Account.Language accountLanguage) {
		String language = accountLanguage.toString();

		if (language.equals(Account.Language.CHINESE.toString())) {
			return "zh_CN";
		}
		else if (language.equals(Account.Language.ENGLISH.toString())) {
			return "en_US";
		}
		else if (language.equals(Account.Language.JAPANESE.toString())) {
			return "ja_JP";
		}
		else if (language.equals(Account.Language.PORTUGUESE.toString())) {
			return "pt_BR";
		}
		else if (language.equals(Account.Language.SPANISH.toString())) {
			return "es_ES";
		}
		else {
			return StringPool.BLANK;
		}
	}

	private Product _getProduct(String productName) throws Exception {
		List<Product> products = _productWebService.getProducts(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_PRODUCT, productName, 1, 1);

		if (!products.isEmpty()) {
			return products.get(0);
		}

		Product product = new Product();

		product.setName(productName);

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(ExternalLinkDomain.DOSSIERA);
		externalLink.setEntityName(ExternalLinkEntityName.DOSSIERA_PRODUCT);
		externalLink.setEntityId(productName);

		product.setExternalLinks(new ExternalLink[] {externalLink});

		return product;
	}

	private String _getSalesforceOpportunityKey(Message message) {
		try {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				(String)message.getPayload());

			return jsonObject.getString("_salesforceOpportunityKey");
		}
		catch (JSONException jsonException1) {
			try {
				JSONArray jsonArray = jsonFactory.createJSONArray(
					(String)message.getPayload());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					return jsonObject.getString("_salesforceOpportunityKey");
				}
			}
			catch (JSONException jsonException2) {
			}
		}

		return StringPool.BLANK;
	}

	private boolean _isDuplicateCode(String code) throws Exception {
		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, "code eq '" + code + "'", 1, 1, null);

		if (!accounts.isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean _isValidOpportunity(
		String salesforceOpportunityStageName, int salesforceOpportunityType) {

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_LOST) &&
			(salesforceOpportunityType ==
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		if (salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_WON) &&
			(salesforceOpportunityType !=
				SalesforceConstants.OPPORTUNITY_TYPE_RENEWAL)) {

			return true;
		}

		return false;
	}

	private void _logWarning(String s) {
		List<String> warningMessages = _warningMessagesThreadLocal.get();

		warningMessages.add(s);
	}

	private static final String[] _PRODUCT_FAMILY_TOKENS = {"E", "P", "S"};

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraCreateMessageSubscriber.class);

	private static final ThreadLocal<ArrayList<String>>
		_warningMessagesThreadLocal = new CentralizedThreadLocal<>(
			DossieraCreateMessageSubscriber.class +
				"._warningMessagesThreadLocal");

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	private volatile DistributedMessagingConfiguration
		_distributedMessagingConfiguration;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private IdentityProvider _identityProvider;

	@Reference
	private LockLocalService _lockLocalService;

	@Reference
	private NoteWebService _noteWebService;

	@Reference
	private Portal _portal;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}
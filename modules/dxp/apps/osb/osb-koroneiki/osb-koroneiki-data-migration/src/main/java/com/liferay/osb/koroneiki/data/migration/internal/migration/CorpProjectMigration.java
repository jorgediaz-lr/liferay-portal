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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Language;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Status;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Tier;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.AuditEntryLocalService;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = CorpProjectMigration.class)
public class CorpProjectMigration {

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		User user = _userLocalService.getUser(userId);

		StringBundler sb = new StringBundler(16);

		sb.append("select OSB_CorpProject.*, ");
		sb.append("OSB_AccountEntry.accountEntryId, ");
		sb.append("OSB_AccountEntry.dossieraAccountKey, ");
		sb.append("OSB_AccountEntry.code_, OSB_AccountEntry.type_, ");
		sb.append("OSB_AccountEntry.tier, OSB_AccountEntry.notes, ");
		sb.append("OSB_AccountEntries_SupportRegions.supportRegionId, ");
		sb.append("OSB_AccountEntryLanguage.languageId from OSB_CorpProject ");
		sb.append("inner join OSB_AccountEntry on ");
		sb.append("OSB_AccountEntry.corpProjectUuid = OSB_CorpProject.uuid_ ");
		sb.append("left join OSB_AccountEntries_SupportRegions on ");
		sb.append("OSB_AccountEntries_SupportRegions.accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId left join ");
		sb.append("OSB_AccountEntryLanguage on ");
		sb.append("OSB_AccountEntryLanguage.accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId where ");
		sb.append("OSB_AccountEntry.status != 500");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			Set<Long> corpProjectIds = new HashSet<>();

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong("corpProjectId");

				if (corpProjectIds.contains(corpProjectId)) {
					continue;
				}

				corpProjectIds.add(corpProjectId);

				Account account = _accountLocalService.createAccount(
					corpProjectId);

				account.setCompanyId(user.getCompanyId());
				account.setUserId(userId);
				account.setCreateDate(resultSet.getTimestamp("createDate"));
				account.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
				account.setAccountKey(
					ModelKeyGenerator.generate(account.getAccountId()));
				account.setParentAccountId(
					_getParentAccountId(
						resultSet.getString("dossieraAccountKey")));
				account.setName(resultSet.getString("name"));
				account.setCode(resultSet.getString("code_"));
				account.setTier(_getTier(resultSet.getInt("tier")));
				account.setRegion(
					_getRegion(resultSet.getLong("supportRegionId")));
				account.setLanguage(
					_getLanguage(resultSet.getString("languageId")));
				account.setInternal(_isInternal(resultSet.getInt("type_")));
				account.setStatus(Status.ACTIVE.toString());

				_accountLocalService.addAccount(account);

				_accountNoteLocalService.addAccountNote(
					userId, StringPool.BLANK, StringPool.BLANK,
					account.getAccountId(), Note.Type.GENERAL.toString(), 2,
					resultSet.getString("notes"), Note.Format.PLAIN.toString(),
					Note.Status.APPROVED.toString());

				long accountEntryId = resultSet.getLong("accountEntryId");

				_migrateAddress(
					connection, userId, account.getAccountId(), accountEntryId);

				_migrateAuditEntries(
					connection, userId, account.getAccountId(), accountEntryId);

				if (Validator.isNotNull(
						resultSet.getString("dossieraProjectKey"))) {

					_externalLinkLocalService.addExternalLink(
						userId, Account.class.getName(), account.getAccountId(),
						ExternalLinkDomain.DOSSIERA,
						ExternalLinkEntityName.DOSSIERA_PROJECT,
						resultSet.getString("dossieraProjectKey"));
				}

				_externalLinkLocalService.addExternalLink(
					userId, Account.class.getName(), account.getAccountId(),
					ExternalLinkDomain.LCS,
					ExternalLinkEntityName.LCS_CORP_PROJECT,
					String.valueOf(corpProjectId));

				if (Validator.isNotNull(
						resultSet.getString("salesforceProjectKey"))) {

					_externalLinkLocalService.addExternalLink(
						userId, Account.class.getName(), account.getAccountId(),
						ExternalLinkDomain.SALESFORCE,
						ExternalLinkEntityName.SALESFORCE_PROJECT,
						resultSet.getString("salesforceProjectKey"));
				}

				_externalLinkLocalService.addExternalLink(
					userId, Account.class.getName(), account.getAccountId(),
					ExternalLinkDomain.WEB,
					ExternalLinkEntityName.WEB_CORP_PROJECT,
					resultSet.getString("uuid_"));

				if (_log.isInfoEnabled()) {
					_log.info("Migrated CorpProject " + account.getAccountId());
				}
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private String _getAction(int action) {
		if (action == 1) {
			return "Add";
		}
		else if (action == 2) {
			return "Assign";
		}
		else if (action == 3) {
			return "Delete";
		}
		else if (action == 5) {
			return "Unassign";
		}
		else if (action == 6) {
			return "Update";
		}
		else if (action == 7) {
			return "Renew";
		}
		else if (action == 11) {
			return "Audit";
		}

		return StringPool.BLANK;
	}

	private String _getField(int field) {
		if (field == 34015) {
			return "Role";
		}
		else if (field == 34017) {
			return "Status";
		}
		else if (field == 34021) {
			return "User";
		}
		else if (field == 34025) {
			return "Renew Count";
		}
		else if (field == 34054) {
			return "Tier";
		}
		else if (field == 34055) {
			return "Partner";
		}
		else if (field == 34058) {
			return "Corp Project";
		}
		else if (field == 34059) {
			return "Corp Entry Name";
		}
		else if (field == 34060) {
			return "Name";
		}
		else if (field == 34061) {
			return "Code";
		}
		else if (field == 34062) {
			return "Type";
		}
		else if (field == 34064) {
			return "Partner Managed Support";
		}
		else if (field == 34065) {
			return "Instructions";
		}
		else if (field == 34066) {
			return "Notes";
		}
		else if (field == 34067) {
			return "Renewal Contact User";
		}
		else if (field == 34069) {
			return "Address";
		}
		else if (field == 34070) {
			return "Languages";
		}
		else if (field == 34071) {
			return "Support Regions";
		}
		else if (field == 34072) {
			return "N/A";
		}
		else if (field == 34078) {
			return "Dossiera Account Key";
		}

		return StringPool.BLANK;
	}

	private long _getFieldClassNameId(long fieldClassNameId) {
		if ((fieldClassNameId == 1400962) || (fieldClassNameId == 1400964)) {
			return _classNameLocalService.getClassNameId(Account.class);
		}
		else if (fieldClassNameId == 1400963) {
			return _classNameLocalService.getClassNameId(Account.class);
		}
		else if (fieldClassNameId == 1400969) {
			return _classNameLocalService.getClassNameId(ProductPurchase.class);
		}

		return 0;
	}

	private String _getLanguage(String languageId) {
		if (Validator.isNull(languageId)) {
			return StringPool.BLANK;
		}

		if (languageId.equals("es_ES")) {
			return Language.SPANISH.toString();
		}
		else if (languageId.equals("ja_JP")) {
			return Language.JAPANESE.toString();
		}
		else if (languageId.equals("pt_BR")) {
			return Language.PORTUGUESE.toString();
		}
		else if (languageId.equals("zh_CN")) {
			return Language.CHINESE.toString();
		}

		return Language.ENGLISH.toString();
	}

	private long _getParentAccountId(String dossieraAccountKey)
		throws Exception {

		if (Validator.isNull(dossieraAccountKey)) {
			return 0;
		}

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				_classNameLocalService.getClassNameId(Account.class),
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_ACCOUNT, dossieraAccountKey, 0,
				1);

		if (!externalLinks.isEmpty()) {
			ExternalLink externalLink = externalLinks.get(0);

			return externalLink.getClassPK();
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Unable to find account with dossiera account key " +
					dossieraAccountKey);
		}

		return 0;
	}

	private String _getRegion(long supportRegionId) {
		if (supportRegionId == 42442481) {
			return "Australia";
		}
		else if (supportRegionId == 42356516) {
			return "Brazil";
		}
		else if (supportRegionId == 42356502) {
			return "China";
		}
		else if (supportRegionId == 70917309) {
			return "Global";
		}
		else if (supportRegionId == 42356493) {
			return "Hungary";
		}
		else if (supportRegionId == 42356498) {
			return "India";
		}
		else if (supportRegionId == 45637701) {
			return "Japan";
		}
		else if (supportRegionId == 42356507) {
			return "Spain";
		}

		return "United States";
	}

	private String _getTier(int tier) {
		if (tier == 1) {
			return Tier.T4.toString();
		}
		else if (tier == 2) {
			return Tier.T4.toString();
		}
		else if (tier == 3) {
			return Tier.T1.toString();
		}
		else if (tier == 4) {
			return Tier.T2.toString();
		}
		else if (tier == 5) {
			return Tier.T3.toString();
		}

		return StringPool.BLANK;
	}

	private boolean _isInternal(int type) {
		if (type == 3) {
			return true;
		}

		return false;
	}

	private void _migrateAddress(
			Connection connection, long userId, long accountId,
			long accountEntryId)
		throws Exception {

		StringBundler sb = new StringBundler(7);

		sb.append("select CUSTOMER_Address.*, CUSTOMER_Country.A2, ");
		sb.append("CUSTOMER_Region.name as regionName from CUSTOMER_Address ");
		sb.append("left join CUSTOMER_Country on CUSTOMER_Country.countryId ");
		sb.append("= CUSTOMER_Address.countryId left join CUSTOMER_Region on ");
		sb.append("CUSTOMER_Region.regionId = CUSTOMER_Address.regionId ");
		sb.append("where CUSTOMER_Address.classPK = ");
		sb.append(accountEntryId);

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				String countryA2 = resultSet.getString("A2");

				long regionId = 0;
				long countryId = 0;

				if (Validator.isNotNull(countryA2) && !countryA2.equals("HK")) {
					Country country = _countryService.getCountryByA2(countryA2);

					countryId = country.getCountryId();

					String regionName = resultSet.getString("regionName");

					if (Validator.isNotNull(regionName)) {
						List<Region> regions = _regionService.getRegions(
							countryId);

						for (Region region : regions) {
							if (regionName.equals(region.getName())) {
								regionId = region.getRegionId();

								break;
							}
						}
					}
				}

				_addressLocalService.addAddress(
					userId, Account.class.getName(), accountId,
					resultSet.getString("street1"),
					resultSet.getString("street2"),
					resultSet.getString("street3"), resultSet.getString("city"),
					resultSet.getString("zip"), regionId, countryId, 0,
					resultSet.getBoolean("mailing"),
					resultSet.getBoolean("primary_"), new ServiceContext());
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _migrateAuditEntries(
			Connection connection, long userId, long accountId,
			long accountEntryId)
		throws Exception {

		StringBundler sb = new StringBundler(2);

		sb.append("select * from OSB_AuditEntry where classPK = ");
		sb.append(accountEntryId);

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String userName = resultSet.getString("userName");
				Date createDate = resultSet.getDate("createDate");
				long auditSetId = resultSet.getLong("auditSetId");
				long fieldClassNameId = resultSet.getLong("fieldClassNameId");
				int action = resultSet.getInt("action");
				int field = resultSet.getInt("field");
				String oldLabel = resultSet.getString("oldLabel");
				String oldValue = resultSet.getString("oldValue");
				String newLabel = resultSet.getString("newLabel");
				String newValue = resultSet.getString("newValue");
				String description = resultSet.getString("description");

				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setAttribute("agentName", userName);
				serviceContext.setAttribute("auditSetId", auditSetId);
				serviceContext.setCreateDate(createDate);

				_auditEntryLocalService.addAuditEntry(
					userId,
					_classNameLocalService.getClassNameId(Account.class),
					accountId, _getFieldClassNameId(fieldClassNameId), 0,
					_getAction(action), _getField(field), oldLabel, oldValue,
					newLabel, newValue, description, serviceContext);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CorpProjectMigration.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private AuditEntryLocalService _auditEntryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CountryService _countryService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private RegionService _regionService;

	@Reference
	private UserLocalService _userLocalService;

}
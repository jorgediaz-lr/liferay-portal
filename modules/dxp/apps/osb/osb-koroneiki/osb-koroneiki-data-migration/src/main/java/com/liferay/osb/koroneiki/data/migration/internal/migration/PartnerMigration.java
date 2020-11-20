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
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = PartnerMigration.class)
public class PartnerMigration {

	public void migrate(long userId) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		TeamRole flsTeamRole = _teamRoleLocalService.addTeamRole(
			userId, "First Line Support", StringPool.BLANK,
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.
				ACCOUNT.toString());

		_flsTeamRoleId = flsTeamRole.getTeamRoleId();

		TeamRole partnerTeamRole = _teamRoleLocalService.addTeamRole(
			userId, "Partner", StringPool.BLANK,
			com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.
				ACCOUNT.toString());

		_partnerTeamRoleId = partnerTeamRole.getTeamRoleId();

		try (Connection connection = DataAccess.getConnection()) {
			_migratePartnerEntries(connection, userId);

			_migratePartnerWorkers(connection, userId);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Migration took " + stopWatch.getTime() + " ms");
		}
	}

	private void _assignTeam(
			Connection connection, long userId, long partnerEntryId,
			Account partnerAccount, String code)
		throws Exception {

		Team flsTeam = null;

		StringBundler sb = new StringBundler(6);

		sb.append("select OSB_CorpProject.corpProjectId, ");
		sb.append("partnerManagedSupport from OSB_AccountEntry inner join ");
		sb.append("OSB_CorpProject on OSB_CorpProject.uuid_ = ");
		sb.append("OSB_AccountEntry.corpProjectUuid where partnerEntryId = ");
		sb.append(partnerEntryId);
		sb.append(" and OSB_AccountEntry.status != 500");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong(1);

				Account account = _accountLocalService.fetchAccount(
					corpProjectId);

				if (account == null) {
					_log.error(
						"Unable to find account with account id " +
							corpProjectId);

					continue;
				}

				Team defaultTeam = _teamLocalService.getDefaultTeam(
					partnerAccount.getAccountId());

				_teamAccountRoleLocalService.addTeamAccountRole(
					defaultTeam.getTeamId(), account.getAccountId(),
					_partnerTeamRoleId);

				boolean partnerManagedSupport = resultSet.getBoolean(2);

				if (partnerManagedSupport) {
					if (flsTeam == null) {
						flsTeam = _teamLocalService.addTeam(
							userId, partnerAccount.getAccountId(),
							code + " FLS", false);

						_accountFLSTeamMap.put(
							partnerAccount.getAccountId(), flsTeam.getTeamId());
					}

					_teamAccountRoleLocalService.addTeamAccountRole(
						flsTeam.getTeamId(), account.getAccountId(),
						_flsTeamRoleId);
				}
			}
		}
	}

	private Account _getAccount(String dossieraAccountKey)
		throws PortalException {

		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				_classNameLocalService.getClassNameId(Account.class),
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_ACCOUNT, dossieraAccountKey, 0,
				1);

		if (externalLinks.isEmpty()) {
			return null;
		}

		ExternalLink externalLink = externalLinks.get(0);

		return _accountLocalService.getAccount(externalLink.getClassPK());
	}

	private String _getDossieraAccountKey(
			Connection connection, long partnerEntryId)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("select dossieraAccountKey from OSB_PartnerEntry where ");
		sb.append("partnerEntryId = ");
		sb.append(partnerEntryId);

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				return resultSet.getString(1);
			}
		}

		return StringPool.BLANK;
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

	private void _migrateAddress(
			Connection connection, long userId, long accountId,
			long partnerEntryId)
		throws Exception {

		StringBundler sb = new StringBundler(7);

		sb.append("select CUSTOMER_Address.*, CUSTOMER_Country.A2, ");
		sb.append("CUSTOMER_Region.name as regionName from CUSTOMER_Address ");
		sb.append("left join CUSTOMER_Country on CUSTOMER_Country.countryId ");
		sb.append("= CUSTOMER_Address.countryId left join CUSTOMER_Region on ");
		sb.append("CUSTOMER_Region.regionId = CUSTOMER_Address.regionId ");
		sb.append("where CUSTOMER_Address.classPK = ");
		sb.append(partnerEntryId);

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

	private void _migratePartnerEntries(Connection connection, long userId)
		throws Exception {

		ProductEntry productEntry =
			_productEntryLocalService.fetchProductEntryByName(
				"Service Partnership");

		if (productEntry == null) {
			List<ProductField> productFields = new ArrayList<>();

			ProductField productField =
				_productFieldLocalService.createProductField(0);

			productField.setName("type");
			productField.setValue("regular");

			productFields.add(productField);

			productEntry = _productEntryLocalService.addProductEntry(
				userId, "Service Partnership", productFields);
		}

		StringBundler sb = new StringBundler(7);

		sb.append("select dossieraAccountKey, code_, notes, ");
		sb.append("OSB_PartnerEntry.partnerEntryId, parentPartnerEntryId, ");
		sb.append("OSB_PartnerEntries_SupportRegions.supportRegionId from ");
		sb.append("OSB_PartnerEntry left join ");
		sb.append("OSB_PartnerEntries_SupportRegions on ");
		sb.append("OSB_PartnerEntries_SupportRegions.partnerEntryId = ");
		sb.append("OSB_PartnerEntry.partnerEntryId where status = 0");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			Date startDate = new Date(1577865600000L);
			Date endDate = new Date(1614585600000L);
			Date originalEndDate = new Date(1609401600000L);

			while (resultSet.next()) {
				String dossieraAccountKey = resultSet.getString(1);

				if (Validator.isNull(dossieraAccountKey)) {
					dossieraAccountKey = _getDossieraAccountKey(
						connection, resultSet.getLong(5));
				}

				Account account = _getAccount(dossieraAccountKey);

				if (account == null) {
					_log.error(
						StringBundler.concat(
							"Unable to find account with dossiera account key ",
							dossieraAccountKey, " for ",
							resultSet.getString(2)));

					continue;
				}

				account.setRegion(
					_getRegion(resultSet.getLong("supportRegionId")));

				_accountLocalService.updateAccount(account);

				_productPurchaseLocalService.addProductPurchase(
					userId, account.getAccountId(),
					productEntry.getProductEntryId(), startDate, endDate,
					originalEndDate, 1, 0, new ArrayList<>());

				long partnerEntryId = resultSet.getLong(4);

				_migrateAddress(
					connection, userId, account.getAccountId(), partnerEntryId);

				_accountNoteLocalService.addAccountNote(
					userId, StringPool.BLANK, StringPool.BLANK,
					account.getAccountId(), Note.Type.GENERAL.toString(), 2,
					resultSet.getString(3), Note.Format.PLAIN.toString(),
					Note.Status.APPROVED.toString());

				_assignTeam(
					connection, userId, partnerEntryId, account,
					resultSet.getString(2));
			}
		}
	}

	private void _migratePartnerWorkers(Connection connection, long userId)
		throws Exception {

		StringBundler sb = new StringBundler(12);

		sb.append("select role, dossieraAccountKey, CUSTOMER_User.uuid_, ");
		sb.append("CUSTOMER_User.firstName, CUSTOMER_User.middleName, ");
		sb.append("CUSTOMER_User.lastName, CUSTOMER_User.emailAddress, ");
		sb.append("CUSTOMER_User.languageId, CUSTOMER_Users_Roles.roleId ");
		sb.append("from OSB_PartnerWorker inner join CUSTOMER_User on ");
		sb.append("CUSTOMER_User.userId = OSB_PartnerWorker.userId inner ");
		sb.append("join OSB_PartnerEntry on OSB_PartnerEntry.partnerEntryId ");
		sb.append("= OSB_PartnerWorker.partnerEntryId left join ");
		sb.append("CUSTOMER_Users_Roles on CUSTOMER_Users_Roles.userId = ");
		sb.append("CUSTOMER_User.userId and CUSTOMER_Users_Roles.roleId = ");
		sb.append("1546579 where dossieraAccountKey != '' and ");
		sb.append("OSB_PartnerEntry.status = 0");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String dossieraAccountKey = resultSet.getString(2);

				Account account = _getAccount(dossieraAccountKey);

				if (account == null) {
					_log.error(
						"Unable to find account with dossiera account key " +
							dossieraAccountKey);

					continue;
				}

				String contactUuid = resultSet.getString(3);

				Contact contact = _contactLocalService.fetchContactByUuid(
					contactUuid);

				if (contact == null) {
					String contactFirstName = resultSet.getString(4);
					String contactMiddleName = resultSet.getString(5);
					String contactLastName = resultSet.getString(6);
					String contactEmailAddress = resultSet.getString(7);
					String contactLanguageId = resultSet.getString(8);

					boolean emailAddressVerified = false;

					if (resultSet.getLong(9) != 0) {
						emailAddressVerified = true;
					}

					contact = _contactLocalService.addContact(
						contactUuid, userId, contactFirstName,
						contactMiddleName, contactLastName, contactEmailAddress,
						contactLanguageId, emailAddressVerified);
				}

				int role = resultSet.getInt(1);

				Long contactRoleId =
					_roleMigration.getPartnerWorkerContactRoleId(role);

				if (contactRoleId != null) {
					_contactAccountRoleLocalService.addContactAccountRole(
						contact.getContactId(), account.getAccountId(),
						contactRoleId);

					if (_accountFLSTeamMap.containsKey(
							account.getAccountId())) {

						ContactRole teamMemberContactRole =
							_contactRoleLocalService.getMemberContactRole(
								"Team");

						_contactTeamRoleLocalService.addContactTeamRole(
							contact.getContactId(),
							_accountFLSTeamMap.get(account.getAccountId()),
							teamMemberContactRole.getContactRoleId());
					}
				}
				else {
					_log.error(
						"Unable to find contactRoleId with partner role = " +
							role);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PartnerMigration.class);

	private final Map<Long, Long> _accountFLSTeamMap = new HashMap<>();

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactAccountRoleLocalService _contactAccountRoleLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

	@Reference
	private CountryService _countryService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	private long _flsTeamRoleId;
	private long _partnerTeamRoleId;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

	@Reference
	private RegionService _regionService;

	@Reference
	private RoleMigration _roleMigration;

	@Reference
	private TeamAccountRoleLocalService _teamAccountRoleLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

}
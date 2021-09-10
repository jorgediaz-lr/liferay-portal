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

package com.liferay.osb.provisioning.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.exporter.LicenseKeyExporter;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.util.LicenseKeyUtil;
import com.liferay.osb.provisioning.rest.internal.ProvisioningContactThreadLocal;
import com.liferay.osb.provisioning.rest.internal.odata.entity.v1_0.LicenseKeyEntityModel;
import com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/license-key.properties",
	scope = ServiceScope.PROTOTYPE, service = LicenseKeyResource.class
)
public class LicenseKeyResourceImpl
	extends BaseLicenseKeyResourceImpl implements EntityModelResource {

	@Override
	public Page<LicenseKey> getAccountAccountKeyLicenseKeysPage(
			String accountKey, String search, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception {

		_checkAccountMembership(accountKey);

		return SearchUtil.search(
			booleanQuery -> booleanQuery.addRequiredTerm(
				"accountKey", accountKey),
			filter, com.liferay.osb.provisioning.license.model.LicenseKey.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> LicenseKeyUtil.toLicenseKey(
				_licenseKeyLocalService.getLicenseKey(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Response
			getAccountAccountKeyProductGroupProductGroupNameProductVersionDevelopmentLicenseKey(
				String accountKey, String productGroupName,
				String productVersion)
		throws Exception {

		_checkAccountMembership(accountKey);

		List<LicenseEntry> licenseEntries =
			_licenseEntryLocalService.getLicenseEntriesByType(
				LicenseType.DEVELOPER);

		LicenseEntry licenseEntry = null;

		for (LicenseEntry curLicenseEntry : licenseEntries) {
			String curLicenseEntryName = curLicenseEntry.getName();

			if (StringUtil.startsWith(curLicenseEntryName, productGroupName)) {
				licenseEntry = curLicenseEntry;
			}
		}

		if (licenseEntry == null) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		Product product = _productWebService.getProduct(
			licenseEntry.getProductKey());

		String productName = product.getName();

		StringBundler sb = new StringBundler(5);

		sb.append("accountKey eq '");
		sb.append(accountKey);
		sb.append("' and state eq 'active' and (property_type eq 'primary' ");
		sb.append("or contains(name, 'Commerce Subscription') or ");
		sb.append("contains(name, 'DXP Cloud Subscription'))");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		boolean hasActiveProduct = false;

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			Product curProduct = productPurchaseView.getProduct();

			String curProductName = curProduct.getName();

			if ((curProductName.contains(
					ProductConstants.NAME_COMMERCE_SUBSCRIPTION) &&
				 productName.contains(
					 ProductConstants.NAME_COMMERCE_SUBSCRIPTION)) ||
				((curProductName.startsWith(ProductConstants.NAME_DXP) ||
				  curProductName.contains(ProductConstants.NAME_DXP_CLOUD)) &&
				 productName.startsWith(ProductConstants.NAME_DXP) &&
				 !productName.contains(ProductConstants.NAME_DXP_CLOUD)) ||
				(curProductName.contains(ProductConstants.NAME_PORTAL) &&
				 productName.contains(ProductConstants.NAME_PORTAL))) {

				hasActiveProduct = true;
			}
		}

		if (!hasActiveProduct) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		String fileName = _licenseKeyExporter.getFileName(
			productName, productVersion, "Developer Activation Keys");

		String licenseXML = StringUtil.read(
			LicenseKeyResourceImpl.class.getResourceAsStream(
				"/dependencies/" + fileName));

		return Response.ok(
			licenseXML.getBytes()
		).header(
			"content-disposition", "attachment; filename=\"" + fileName + "\""
		).type(
			ContentTypes.TEXT_XML
		).build();
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Response getLicenseKeyDownload(Long licenseKeyId) throws Exception {
		com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
			_licenseKeyLocalService.getLicenseKey(licenseKeyId);

		_checkAccountMembership(licenseKey.getAccountKey());

		if (licenseKey.getLicenseVersion() >= 2) {
			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion(),
				licenseKey.getName());

			String licenseXML = _licenseKeyExporter.toXML(
				licenseKey.getKey(), licenseKey.getAccountName(),
				licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(),
				licenseKey.getHostName(), licenseKey.getIpAddresses(),
				licenseKey.getMacAddresses(), licenseKey.getServerId(),
				licenseKey.getStartDate(), licenseKey.getExpirationDate(),
				licenseKey.getCreateDate());

			return Response.ok(
				licenseXML.getBytes()
			).header(
				"content-disposition",
				"attachment; filename=\"" + fileName + "\""
			).type(
				ContentTypes.TEXT_XML
			).build();
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	@Override
	public Response getLicenseKeyDownload(Long[] licenseKeyIds)
		throws Exception {

		List<com.liferay.osb.provisioning.license.model.LicenseKey>
			licenseKeys = new ArrayList<>();

		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			if (!licenseKey.getActive()) {
				continue;
			}

			_checkAccountMembership(licenseKey.getAccountKey());

			licenseKeys.add(licenseKey);
		}

		if (ArrayUtil.isEmpty(licenseKeyIds) || licenseKeys.isEmpty()) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		if (_isAggregate(licenseKeys)) {
			String[] hostNames = new String[licenseKeys.size()];
			String[] ipAddresses = new String[licenseKeys.size()];
			String[] macAddresses = new String[licenseKeys.size()];
			String[] serverIds = new String[licenseKeys.size()];

			for (int i = 0; i < licenseKeys.size(); i++) {
				com.liferay.osb.provisioning.license.model.LicenseKey
					licenseKey = licenseKeys.get(i);

				hostNames[i] = licenseKey.getHostName();
				ipAddresses[i] = licenseKey.getIpAddresses();
				macAddresses[i] = licenseKey.getMacAddresses();
				serverIds[i] = licenseKey.getServerId();
			}

			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				licenseKeys.get(0);

			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion(),
				licenseKey.getName());

			String licenseXML = _licenseKeyExporter.toXML(
				licenseKey.getAccountName(), licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(), hostNames,
				ipAddresses, macAddresses, serverIds, licenseKey.getStartDate(),
				licenseKey.getExpirationDate(), licenseKey.getCreateDate());

			return Response.ok(
				licenseXML.getBytes()
			).header(
				"content-disposition",
				"attachment; filename=\"" + fileName + "\""
			).type(
				ContentTypes.TEXT_XML
			).build();
		}

		Set<String> names = new HashSet<>();
		Set<String> productNames = new HashSet<>();

		String[] licenseXMLs = new String[licenseKeys.size()];

		for (int i = 0; i < licenseKeys.size(); i++) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				licenseKeys.get(i);

			names.add(licenseKey.getName());
			productNames.add(licenseKey.getProductName());

			licenseXMLs[i] = _licenseKeyExporter.toXML(
				licenseKey.getKey(), licenseKey.getAccountName(),
				licenseKey.getLicenseEntryName(),
				licenseKey.getLicenseEntryType(),
				licenseKey.getLicenseVersion(), licenseKey.getProductName(),
				licenseKey.getProductId(), licenseKey.getProductVersion(),
				licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
				licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
				licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
				licenseKey.getSizing(), licenseKey.getDescription(),
				licenseKey.getHostName(), licenseKey.getIpAddresses(),
				licenseKey.getMacAddresses(), licenseKey.getServerId(),
				licenseKey.getStartDate(), licenseKey.getExpirationDate(),
				licenseKey.getCreateDate());
		}

		String fileName = _licenseKeyExporter.getFileName(
			ArrayUtil.toStringArray(productNames),
			ArrayUtil.toStringArray(names));

		String licenseXML = _licenseKeyExporter.aggregateXMLs(licenseXMLs);

		return Response.ok(
			licenseXML.getBytes()
		).header(
			"content-disposition", "attachment; filename=\"" + fileName + "\""
		).type(
			ContentTypes.TEXT_XML
		).build();
	}

	@Override
	public Page<LicenseKey> postAccountAccountKeyLicenseKeysPage(
			String accountKey, LicenseKey[] licenseKeys)
		throws Exception {

		_checkAccountAdminContactRole(accountKey);

		List<LicenseKey> curLicenseKeys = new ArrayList<>();

		for (LicenseKey licenseKey : licenseKeys) {
			LicenseKey.LicenseEntryType licenseEntryType =
				licenseKey.getLicenseEntryType();
			LicenseKey.Sizing sizing = licenseKey.getSizing();

			com.liferay.osb.provisioning.license.model.LicenseKey
				curLicenseKey = _licenseKeyLocalService.addLicenseKey(
					contextUser.getUserId(), licenseEntryType.getValue(),
					licenseKey.getProductKey(), accountKey,
					licenseKey.getProductPurchaseKey(),
					licenseKey.getProductVersion(), licenseKey.getName(),
					licenseKey.getOwner(), licenseKey.getMaxClusterNodes(),
					sizing.getValue(), licenseKey.getDescription(),
					licenseKey.getHostName(), licenseKey.getIpAddresses(),
					licenseKey.getMacAddresses(), licenseKey.getStartDate(),
					licenseKey.getExpirationDate(),
					licenseKey.getComplimentary(), licenseKey.getActive());

			curLicenseKeys.add(LicenseKeyUtil.toLicenseKey(curLicenseKey));
		}

		return Page.of(curLicenseKeys);
	}

	@Override
	public Page<LicenseKey> postLicenseKeysExtendPage(LicenseKey[] licenseKeys)
		throws Exception {

		for (LicenseKey licenseKey : licenseKeys) {
			com.liferay.osb.provisioning.license.model.LicenseKey
				curLicenseKey = _licenseKeyLocalService.getLicenseKey(
					licenseKey.getId());

			_checkAccountAdminContactRole(curLicenseKey.getAccountKey());
		}

		List<LicenseKey> curLicenseKeys = new ArrayList<>();

		for (LicenseKey licenseKey : licenseKeys) {
			com.liferay.osb.provisioning.license.model.LicenseKey
				curLicenseKey = _licenseKeyLocalService.extendLicenseKey(
					contextUser.getUserId(), licenseKey.getId(),
					licenseKey.getProductPurchaseKey(),
					licenseKey.getStartDate(), licenseKey.getExpirationDate());

			curLicenseKeys.add(LicenseKeyUtil.toLicenseKey(curLicenseKey));
		}

		return Page.of(curLicenseKeys);
	}

	@Override
	public void putLicenseKeyActivate(Long[] licenseKeyIds) throws Exception {
		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_checkAccountAdminContactRole(licenseKey.getAccountKey());
		}

		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_licenseKeyLocalService.updateLicenseKey(
				contextUser.getUserId(), licenseKeyId,
				licenseKey.getProductPurchaseKey(),
				licenseKey.getComplimentary(), true);
		}
	}

	@Override
	public void putLicenseKeyDeactivate(Long[] licenseKeyIds) throws Exception {
		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_checkAccountAdminContactRole(licenseKey.getAccountKey());
		}

		for (long licenseKeyId : licenseKeyIds) {
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
				_licenseKeyLocalService.getLicenseKey(licenseKeyId);

			_licenseKeyLocalService.updateLicenseKey(
				contextUser.getUserId(), licenseKeyId,
				licenseKey.getProductPurchaseKey(),
				licenseKey.getComplimentary(), false);
		}
	}

	private void _checkAccountAdminContactRole(String accountKey)
		throws Exception {

		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountCustomerContactRoles(
					accountKey, contact.getEmailAddress(), 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(ContactRoleConstants.NAME_ADMINISTRATOR)) {
					return;
				}
			}
		}
		else if (_isOmniAdmin()) {
			return;
		}

		throw new PrincipalException();
	}

	private void _checkAccountMembership(String accountKey)
		throws PrincipalException {

		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			for (Account account : contact.getAccounts()) {
				if (accountKey.equals(account.getKey())) {
					return;
				}
			}
		}
		else if (_isOmniAdmin()) {
			return;
		}

		throw new PrincipalException();
	}

	private boolean _isAggregate(
			List<com.liferay.osb.provisioning.license.model.LicenseKey>
				licenseKeys)
		throws Exception {

		if (licenseKeys.isEmpty() || (licenseKeys.size() <= 1)) {
			return false;
		}

		com.liferay.osb.provisioning.license.model.LicenseKey firstLicenseKey =
			licenseKeys.get(0);

		int licenseVersion = firstLicenseKey.getLicenseVersion();
		String productVersion = firstLicenseKey.getProductVersion();
		Date startDate = firstLicenseKey.getStartDate();
		Date expirationDate = firstLicenseKey.getExpirationDate();

		for (com.liferay.osb.provisioning.license.model.LicenseKey licenseKey :
				licenseKeys) {

			int curLicenseVersion = licenseKey.getLicenseVersion();

			if ((curLicenseVersion < 4) ||
				(curLicenseVersion != licenseVersion)) {

				return false;
			}

			String curProductVersion = licenseKey.getProductVersion();

			if (!curProductVersion.equals(productVersion)) {
				return false;
			}

			String curLicenseEntryType = licenseKey.getLicenseEntryType();

			if (!curLicenseEntryType.equals(LicenseType.PRODUCTION)) {
				return false;
			}

			if (!DateUtil.equals(startDate, licenseKey.getStartDate())) {
				return false;
			}

			if (!DateUtil.equals(
					expirationDate, licenseKey.getExpirationDate())) {

				return false;
			}
		}

		return true;
	}

	private boolean _isOmniAdmin() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		return false;
	}

	private static final EntityModel _entityModel = new LicenseKeyEntityModel();

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductWebService _productWebService;

}
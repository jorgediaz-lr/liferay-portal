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

import com.liferay.osb.provisioning.license.exporter.LicenseKeyExporter;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.util.LicenseKeyUtil;
import com.liferay.osb.provisioning.rest.internal.odata.entity.v1_0.LicenseKeyEntityModel;
import com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
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
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
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
	public Response getLicenseKeyDownloadLicenseKey(Long licenseKeyId)
		throws Exception {

		com.liferay.osb.provisioning.license.model.LicenseKey licenseKey =
			_licenseKeyLocalService.getLicenseKey(licenseKeyId);

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
	public Page<LicenseKey> getLicenseKeysPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
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
	public Page<LicenseKey> postLicenseKeysPage(LicenseKey[] licenseKeys)
		throws Exception {

		List<LicenseKey> curLicenseKeys = new ArrayList<>();

		for (LicenseKey licenseKey : licenseKeys) {
			LicenseKey.LicenseEntryType licenseEntryType =
				licenseKey.getLicenseEntryType();
			LicenseKey.Sizing sizing = licenseKey.getSizing();

			com.liferay.osb.provisioning.license.model.LicenseKey
				curLicenseKey = _licenseKeyLocalService.addLicenseKey(
					contextUser.getUserId(), licenseEntryType.getValue(),
					licenseKey.getProductKey(), licenseKey.getAccountKey(),
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
	public void putLicenseKeyActivate(Long[] licenseKeyIds) throws Exception {
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

			_licenseKeyLocalService.updateLicenseKey(
				contextUser.getUserId(), licenseKeyId,
				licenseKey.getProductPurchaseKey(),
				licenseKey.getComplimentary(), false);
		}
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

	private static final EntityModel _entityModel = new LicenseKeyEntityModel();

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

}
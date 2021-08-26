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

import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.util.LicenseKeyUtil;
import com.liferay.osb.provisioning.rest.internal.odata.entity.v1_0.LicenseKeyEntityModel;
import com.liferay.osb.provisioning.rest.resource.v1_0.LicenseKeyResource;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

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

	private static final EntityModel _entityModel = new LicenseKeyEntityModel();

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

}
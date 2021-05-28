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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.license.exporter.LicenseKeyExporter;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.osb.provisioning.license.util.LicenseUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"mvc.command.name=/accounts/download_license_keys",
		"mvc.command.name=/licenses/download_license_key"
	},
	service = MVCResourceCommand.class
)
public class DownloadLicenseKeyMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			long[] licenseKeyIds = ParamUtil.getLongValues(
				resourceRequest, "licenseKeyIds");

			if (licenseKeyIds.length > 1) {
				downloadAggregateLicenseKey(
					resourceRequest, resourceResponse, licenseKeyIds);
			}
			else if (licenseKeyIds.length == 1) {
				downloadLicenseKey(
					resourceRequest, resourceResponse, licenseKeyIds[0]);
			}
			else {
				long licenseKeyId = ParamUtil.getLong(
					resourceRequest, "licenseKeyId");

				downloadLicenseKey(
					resourceRequest, resourceResponse, licenseKeyId);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	protected void downloadAggregateLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			long[] licenseKeyIds)
		throws Exception {

		List<LicenseKey> licenseKeys = new ArrayList<>();

		String[] keys = new String[licenseKeyIds.length];
		String[] accountNames = new String[licenseKeyIds.length];
		String[] licenseEntryNames = new String[licenseKeyIds.length];
		String[] licenseTypes = new String[licenseKeyIds.length];
		int[] licenseVersions = new int[licenseKeyIds.length];
		String[] productNames = new String[licenseKeyIds.length];
		String[] productIds = new String[licenseKeyIds.length];
		String[] productVersions = new String[licenseKeyIds.length];
		String[] owners = new String[licenseKeyIds.length];
		int[] maxServers = new int[licenseKeyIds.length];
		int[] maxHttpSessions = new int[licenseKeyIds.length];
		long[] maxConcurrentUsers = new long[licenseKeyIds.length];
		long[] maxUsers = new long[licenseKeyIds.length];
		String[] sizings = new String[licenseKeyIds.length];
		String[] descriptions = new String[licenseKeyIds.length];
		String[] hostNames = new String[licenseKeyIds.length];
		String[] ipAddresses = new String[licenseKeyIds.length];
		String[] macAddresses = new String[licenseKeyIds.length];
		String[] serverIds = new String[licenseKeyIds.length];
		Date[] startDates = new Date[licenseKeyIds.length];
		Date[] expirationDates = new Date[licenseKeyIds.length];
		Date[] createDates = new Date[licenseKeyIds.length];

		for (int i = 0; i < licenseKeyIds.length; i++) {
			LicenseKey licenseKey = _licenseKeyService.getLicenseKey(
				licenseKeyIds[i]);

			if (!licenseKey.isActive()) {
				continue;
			}

			licenseKeys.add(licenseKey);

			keys[i] = licenseKey.getKey();
			accountNames[i] = licenseKey.getAccountName();
			licenseEntryNames[i] = licenseKey.getLicenseEntryName();
			licenseTypes[i] = licenseKey.getLicenseEntryType();
			licenseVersions[i] = licenseKey.getLicenseVersion();
			productNames[i] = licenseKey.getProductName();
			productIds[i] = licenseKey.getProductId();
			productVersions[i] = licenseKey.getProductVersion();
			owners[i] = licenseKey.getOwner();
			maxServers[i] = licenseKey.getMaxServers();
			maxHttpSessions[i] = licenseKey.getMaxHttpSessions();
			maxConcurrentUsers[i] = licenseKey.getMaxConcurrentUsers();
			maxUsers[i] = licenseKey.getMaxUsers();
			sizings[i] = licenseKey.getSizing();
			descriptions[i] = licenseKey.getDescription();
			hostNames[i] = licenseKey.getHostName();
			ipAddresses[i] = licenseKey.getIpAddresses();
			macAddresses[i] = licenseKey.getMacAddresses();
			serverIds[i] = licenseKey.getServerId();
			startDates[i] = licenseKey.getStartDate();
			expirationDates[i] = licenseKey.getExpirationDate();
			createDates[i] = licenseKey.getCreateDate();
		}

		if (LicenseUtil.isAggregate(licenseKeys)) {
			LicenseKey licenseKey = licenseKeys.get(0);

			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion());

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

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				licenseXML.getBytes(), ContentTypes.TEXT_XML);
		}
		else {
			String fileName = _licenseKeyExporter.getFileName(
				new String[] {
					ProductConstants.NAME_COMMERCE, ProductConstants.NAME_DXP
				});

			String licenseXML = _licenseKeyExporter.toXML(
				keys, accountNames, licenseEntryNames, licenseTypes,
				licenseVersions, productNames, productIds, productVersions,
				owners, maxServers, maxHttpSessions, maxConcurrentUsers,
				maxUsers, sizings, descriptions, hostNames, ipAddresses,
				macAddresses, serverIds, startDates, expirationDates,
				createDates);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				licenseXML.getBytes(), ContentTypes.TEXT_XML);
		}
	}

	protected void downloadLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			long licenseKeyId)
		throws Exception {

		LicenseKey licenseKey = _licenseKeyService.getLicenseKey(licenseKeyId);

		if (licenseKey.getLicenseVersion() == 1) {
			String encodedLicenseFile =
				_licenseKeyExporter.toEncodedLicenseFile(
					licenseKey.getServerId(), licenseKey.getKey());

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, "license",
				encodedLicenseFile.getBytes(),
				ContentTypes.APPLICATION_OCTET_STREAM);
		}
		else if (licenseKey.getLicenseVersion() >= 2) {
			String fileName = _licenseKeyExporter.getFileName(
				licenseKey.getProductName(), licenseKey.getProductVersion());

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

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				licenseXML.getBytes(), ContentTypes.TEXT_XML);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadLicenseKeyMVCResourceCommand.class);

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyService _licenseKeyService;

}
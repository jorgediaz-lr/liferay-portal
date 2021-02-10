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

package com.liferay.osb.provisioning.license.exporter.internal;

import com.liferay.osb.provisioning.license.exporter.LicenseKeyExporter;
import com.liferay.osb.provisioning.license.generator.KeyGenerator;
import com.liferay.osb.provisioning.license.helper.constants.LicenseSizing;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.helper.util.OSBFileUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.xml.DocUtil;
import com.liferay.portal.kernel.io.Base64OutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = LicenseKeyExporter.class)
public class LicenseKeyExporterImpl implements LicenseKeyExporter {

	public String getFileName(String productName, String productVersion) {
		StringBundler sb = new StringBundler(5);

		productName = StringUtil.extractChars(productName);

		if (productName.startsWith("Portal")) {
			productName = productName.substring(6);
		}

		sb.append("activation-key-");
		sb.append(productName);
		sb.append(StringPool.DASH);
		sb.append(productVersion);

		sb.append(".xml");

		String fileName = StringUtil.replace(
			sb.toString(), CharPool.SPACE, StringPool.BLANK);

		return StringUtil.toLowerCase(fileName);
	}

	public String toEncodedLicenseFile(String serverId, String key) {
		Properties licenseProperties = new Properties();

		licenseProperties.setProperty("serverId", serverId);
		licenseProperties.setProperty("licenseKey", key);

		String licenseFileDecoded = PropertiesUtil.toString(licenseProperties);

		return Base64.objectToString(licenseFileDecoded);
	}

	public File toFile(
			String key, String accountName, String licenseEntryName,
			String licenseType, int licenseVersion, String productName,
			String productId, int productVersion, String owner, int maxServers,
			int maxHttpSessions, long maxConcurrentUsers, long maxUsers,
			int sizing, String description, String hostNames,
			String ipAddresses, String macAddresses, String[] serverIds,
			Date startDate, Date expirationDate, Date createDate)
		throws Exception {

		File file = OSBFileUtil.createTempFile(
			getFileName(productName, String.valueOf(productVersion)));

		FileUtil.write(
			file,
			toXML(
				key, accountName, licenseEntryName, licenseType, licenseVersion,
				productName, productId, productVersion, owner, maxServers,
				maxHttpSessions, maxConcurrentUsers, maxUsers, sizing,
				description, hostNames, ipAddresses, macAddresses, serverIds,
				startDate, expirationDate, createDate));

		return file;
	}

	public String toLI(
			String key, String accountName, String licenseEntryName,
			String licenseType, int licenseVersion, String productName,
			String productId, int productVersion, String owner, int maxServers,
			int maxHttpSessions, long maxConcurrentUsers, long maxUsers,
			int sizing, String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, Date startDate,
			Date expirationDate)
		throws IOException {

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = null;

		try {
			objectOutputStream = new ObjectOutputStream(
				new Base64OutputStream(unsyncByteArrayOutputStream));

			objectOutputStream.writeInt(4);
			objectOutputStream.writeUTF(GetterUtil.getString(accountName));
			objectOutputStream.writeUTF(GetterUtil.getString(description));
			objectOutputStream.writeObject(expirationDate);

			String[] hostNames = null;

			if (Validator.isNotNull(hostName)) {
				hostNames = new String[] {hostName};
			}
			else {
				hostNames = new String[0];
			}

			objectOutputStream.writeObject(hostNames);

			objectOutputStream.writeObject(StringUtil.split(ipAddresses));
			objectOutputStream.writeUTF(GetterUtil.getString(key));
			objectOutputStream.writeLong(System.currentTimeMillis());
			objectOutputStream.writeUTF(GetterUtil.getString(licenseEntryName));
			objectOutputStream.writeUTF(GetterUtil.getString(licenseType));
			objectOutputStream.writeUTF(String.valueOf(licenseVersion));

			objectOutputStream.writeObject(StringUtil.split(macAddresses));
			objectOutputStream.writeInt(maxHttpSessions);
			objectOutputStream.writeInt(maxServers);
			objectOutputStream.writeLong(maxConcurrentUsers);
			objectOutputStream.writeLong(maxUsers);

			objectOutputStream.writeUTF(LicenseSizing.getLabel(sizing));

			objectOutputStream.writeUTF(GetterUtil.getString(owner));
			objectOutputStream.writeUTF(GetterUtil.getString(productName));
			objectOutputStream.writeUTF(GetterUtil.getString(productId));
			objectOutputStream.writeUTF(String.valueOf(productVersion));

			String[] serverIds = null;

			if (Validator.isNotNull(serverId)) {
				serverIds = new String[] {serverId};
			}
			else {
				serverIds = new String[0];
			}

			objectOutputStream.writeObject(serverIds);

			objectOutputStream.writeObject(startDate);

			objectOutputStream.flush();

			return new String(unsyncByteArrayOutputStream.toByteArray());
		}
		finally {
			if (objectOutputStream != null) {
				objectOutputStream.close();
			}

			if (unsyncByteArrayOutputStream != null) {
				unsyncByteArrayOutputStream.close();
			}
		}
	}

	public String toXML(Map<String, String> properties, String key)
		throws Exception {

		Document document = toXMLVersion3_4(properties, key, false);

		return document.formattedString();
	}

	public String toXML(
			String key, String accountName, String licenseEntryName,
			String licenseType, int licenseVersion, String productName,
			String productId, int productVersion, String owner, int maxServers,
			int maxHttpSessions, long maxConcurrentUsers, long maxUsers,
			int sizing, String description, String hostNames,
			String ipAddresses, String macAddresses, String[] serverIds,
			Date startDate, Date expirationDate, Date createDate)
		throws Exception {

		Document document = null;

		Map<String, String> properties = _getProperties(
			accountName, licenseEntryName, licenseType, licenseVersion,
			productName, productId, productVersion, owner, maxServers,
			maxHttpSessions, maxConcurrentUsers, maxUsers, sizing, description,
			hostNames, ipAddresses, macAddresses, serverIds, startDate,
			expirationDate, createDate);

		if (licenseVersion >= 3) {
			document = toXMLVersion3_4(properties, key, false);
		}
		else {
			document = toXMLVersion2(properties, key);
		}

		return document.formattedString();
	}

	protected void exportServerToXML(
		Element element, Map<String, String> properties) {

		Element hostNamesElement = element.addElement("host-names");

		String[] hostNames = StringUtil.split(properties.get("hostNames"));

		for (String hostName : hostNames) {
			DocUtil.add(hostNamesElement, "host-name", hostName);
		}

		Element ipAddressesElement = element.addElement("ip-addresses");

		String[] ipAddresses = StringUtil.split(properties.get("ipAddresses"));

		for (String ipAddress : ipAddresses) {
			DocUtil.add(ipAddressesElement, "ip-address", ipAddress);
		}

		Element macAddressesElement = element.addElement("mac-addresses");

		String[] macAddresses = StringUtil.split(
			properties.get("macAddresses"));

		for (String macAddress : macAddresses) {
			DocUtil.add(macAddressesElement, "mac-address", macAddress);
		}

		String[] serverIds = StringUtil.split(properties.get("serverIds"));

		if (serverIds.length > 0) {
			Element serverIdElement = element.addElement("server-ids");

			for (String serverId : serverIds) {
				DocUtil.add(serverIdElement, "server-id", serverId);
			}
		}
	}

	protected Document toXMLVersion2(Map<String, String> properties, String key)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("license");

		String licenseEntryType = properties.get("type");
		String licenseVersion = properties.get("version");

		DocUtil.add(
			rootElement, "account-name", properties.get("accountEntryName"));
		DocUtil.add(rootElement, "owner", properties.get("owner"));
		DocUtil.add(rootElement, "description", properties.get("description"));
		DocUtil.add(
			rootElement, "product-name", properties.get("productEntryName"));
		DocUtil.add(
			rootElement, "product-version", properties.get("productVersion"));
		DocUtil.add(
			rootElement, "license-name", properties.get("licenseEntryName"));
		DocUtil.add(rootElement, "license-type", licenseEntryType);
		DocUtil.add(rootElement, "license-version", licenseVersion);

		DateFormat longDateFormatDateTime = DateFormat.getDateTimeInstance(
			DateFormat.FULL, DateFormat.FULL, LocaleUtil.US);

		longDateFormatDateTime.setTimeZone(TimeZone.getTimeZone("GMT"));

		if (licenseEntryType.equals(LicenseType.TRIAL)) {
			DocUtil.add(rootElement, "start-date", "Registration");

			long lifetime = GetterUtil.getLong(properties.get("lifetime"));

			DocUtil.add(rootElement, "lifetime", String.valueOf(lifetime));
		}
		else {
			Date startDate = new Date(
				GetterUtil.getLong(properties.get("startDate")));

			DocUtil.add(
				rootElement, "start-date",
				longDateFormatDateTime.format(startDate));

			Date expirationDate = new Date(
				GetterUtil.getLong(properties.get("expirationDate")));

			DocUtil.add(
				rootElement, "expiration-date",
				longDateFormatDateTime.format(expirationDate));
		}

		if (licenseEntryType.equals(LicenseType.CLUSTER) ||
			licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

			DocUtil.add(
				rootElement, "max-servers", properties.get("maxServers"));
		}

		if (licenseEntryType.equals(LicenseType.DEVELOPER) ||
			licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER) ||
			licenseEntryType.equals(LicenseType.TRIAL)) {

			DocUtil.add(
				rootElement, "max-http-sessions",
				properties.get("maxHttpSessions"));
		}

		if (licenseEntryType.equals(LicenseType.PRODUCTION)) {
			Element serverIdElement = rootElement.addElement("server-ids");

			String[] serverIds = StringUtil.split(properties.get("serverIds"));

			for (String serverId : serverIds) {
				DocUtil.add(serverIdElement, "server-id", serverId);
			}
		}

		DocUtil.add(rootElement, "key", key);

		return document;
	}

	protected Document toXMLVersion3_4(
			Map<String, String> properties, String key, boolean aggregate)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("license");

		String productId = properties.get("productId");
		String licenseEntryType = properties.get("type");
		long licenseVersion = GetterUtil.getLong(properties.get("version"));

		if (Validator.isNull(productId)) {
			DocUtil.add(
				rootElement, "account-name",
				properties.get("accountEntryName"));
		}

		DocUtil.add(rootElement, "owner", properties.get("owner"));
		DocUtil.add(rootElement, "description", properties.get("description"));
		DocUtil.add(
			rootElement, "product-name", properties.get("productEntryName"));

		if (Validator.isNotNull(productId)) {
			DocUtil.add(rootElement, "product-id", productId);
		}

		DocUtil.add(
			rootElement, "product-version", properties.get("productVersion"));

		if (Validator.isNull(productId)) {
			DocUtil.add(
				rootElement, "license-name",
				properties.get("licenseEntryName"));
		}

		DocUtil.add(rootElement, "license-type", licenseEntryType);
		DocUtil.add(
			rootElement, "license-version", String.valueOf(licenseVersion));

		DateFormat longDateFormatDateTime = DateFormat.getDateTimeInstance(
			DateFormat.FULL, DateFormat.FULL, LocaleUtil.US);

		longDateFormatDateTime.setTimeZone(TimeZone.getTimeZone("GMT"));

		Date startDate = new Date(
			GetterUtil.getLong(properties.get("startDate")));

		DocUtil.add(
			rootElement, "start-date",
			longDateFormatDateTime.format(startDate));

		Date expirationDate = new Date(
			GetterUtil.getLong(properties.get("expirationDate")));

		DocUtil.add(
			rootElement, "expiration-date",
			longDateFormatDateTime.format(expirationDate));

		if (licenseEntryType.equals(LicenseType.CLUSTER) ||
			((licenseVersion >= 4) &&
			 (licenseEntryType.equals(LicenseType.LIMITED) ||
			  licenseEntryType.equals(LicenseType.PRODUCTION)))) {

			DocUtil.add(
				rootElement, "max-servers", properties.get("maxServers"));
		}

		if (licenseEntryType.equals(LicenseType.DEVELOPER) ||
			licenseEntryType.equals(LicenseType.DEVELOPER_CLUSTER)) {

			DocUtil.add(
				rootElement, "max-http-sessions",
				properties.get("maxHttpSessions"));
		}

		if (licenseEntryType.equals(LicenseType.PER_USER)) {
			String maxConcurrentUsers = properties.get("maxConcurrentUsers");

			if (Validator.isNotNull(maxConcurrentUsers)) {
				DocUtil.add(
					rootElement, "max-concurrent-users",
					properties.get("maxConcurrentUsers"));
			}

			String maxUsers = properties.get("maxUsers");

			if (Validator.isNotNull(maxUsers)) {
				DocUtil.add(
					rootElement, "max-users", properties.get("maxUsers"));
			}
		}

		String instanceSize = properties.get("instanceSize");

		if (Validator.isNotNull(instanceSize)) {
			DocUtil.add(rootElement, "instance-size", instanceSize);
		}

		if (!aggregate) {
			if (licenseEntryType.equals(LicenseType.CLUSTER) ||
				licenseEntryType.equals(LicenseType.LIMITED) ||
				licenseEntryType.equals(LicenseType.PER_USER) ||
				licenseEntryType.equals(LicenseType.PRODUCTION)) {

				exportServerToXML(rootElement, properties);
			}

			DocUtil.add(rootElement, "key", key);
		}

		return document;
	}

	private Map<String, String> _getProperties(
		String accountName, String licenseEntryName, String licenseType,
		int licenseVersion, String productName, String productId,
		int productVersion, String owner, int maxServers, int maxHttpSessions,
		long maxConcurrentUsers, long maxUsers, int sizing, String description,
		String hostNames, String ipAddresses, String macAddresses,
		String[] serverIds, Date startDate, Date expirationDate,
		Date createDate) {

		Map<String, String> properties = _keyGenerator.getProperties(
			accountName, licenseEntryName, licenseType, licenseVersion,
			productName, productId, ProductVersion.getLabel(productVersion),
			owner, maxServers, maxHttpSessions, maxConcurrentUsers, maxUsers,
			sizing, description, hostNames, ipAddresses, macAddresses,
			serverIds, startDate, expirationDate);

		// See LRDCOM-2568

		if (productVersion == ProductVersion.PORTAL_VERSION_6_1_10) {
			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.DAY_OF_MONTH, 31);
			cal.set(Calendar.MONTH, 6);
			cal.set(Calendar.YEAR, 2012);

			if (createDate.before(cal.getTime())) {
				properties.put("productVersion", "6.1");
			}
		}

		return properties;
	}

	@Reference
	private KeyGenerator _keyGenerator;

}
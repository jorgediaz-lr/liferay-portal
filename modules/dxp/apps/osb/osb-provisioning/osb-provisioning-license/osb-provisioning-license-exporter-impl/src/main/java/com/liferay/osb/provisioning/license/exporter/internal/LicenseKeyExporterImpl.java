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

package com.liferay.osb.customer.license.internal.util;

import com.liferay.osb.customer.admin.constants.LicenseEntryConstants;
import com.liferay.osb.customer.admin.constants.ProductEntryConstants;
import com.liferay.osb.customer.license.constants.LicenseKeyConstants;
import com.liferay.osb.customer.license.generator.KeyGenerator;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.util.LicenseKeyExporter;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.xml.DocUtil;
import com.liferay.portal.kernel.io.Base64OutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

    public String getFileName(LicenseKey licenseKey) {
        StringBundler sb = new StringBundler(7);

        String productEntryName = StringUtil.extractChars(
            licenseKey.getProductEntryName());

        if (productEntryName.startsWith("Portal")) {
            productEntryName = productEntryName.substring(6);
        }

        sb.append("activation-key-");
        sb.append(productEntryName);
        sb.append(StringPool.DASH);
        sb.append(licenseKey.getProductVersionLabel());

        try {
            LicenseKeySet licenseKeySet = licenseKey.getLicenseKeySet();

            sb.append(StringPool.DASH);
            sb.append(StringUtil.extractChars(licenseKeySet.getName()));
        }
        catch (Exception e) {
        }

        sb.append(".xml");

        String fileName = StringUtil.replace(
            sb.toString(), CharPool.SPACE, StringPool.BLANK);

        return StringUtil.toLowerCase(fileName);
    }

    public String toEncodedLicenseFile(LicenseKey licenseKey) throws Exception {
        Properties licenseProperties = new Properties();

        licenseProperties.setProperty("serverId", licenseKey.getServerId());
        licenseProperties.setProperty("licenseKey", licenseKey.getKey());

        String licenseFileDecoded = PropertiesUtil.toString(licenseProperties);

        return Base64.objectToString(licenseFileDecoded);
    }

    public File toFile(LicenseKey licenseKey) throws Exception {
        File file = OSBFileUtil.createTempFile(getFileName(licenseKey));

        FileUtil.write(file, toXML(licenseKey));

        return file;
    }

    public String toLI(LicenseKey licenseKey) throws IOException {
        UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
            new UnsyncByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(
                new Base64OutputStream(unsyncByteArrayOutputStream));

            objectOutputStream.writeInt(4);
            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getAccountEntryName()));
            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getDescription()));
            objectOutputStream.writeObject(licenseKey.getExpirationDate());

            String[] hostNames = null;

            if (Validator.isNotNull(licenseKey.getHostName())) {
                hostNames = new String[] {licenseKey.getHostName()};
            }
            else {
                hostNames = new String[0];
            }

            objectOutputStream.writeObject(hostNames);

            objectOutputStream.writeObject(
                StringUtil.split(licenseKey.getIpAddresses()));
            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getKey()));
            objectOutputStream.writeLong(System.currentTimeMillis());
            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getLicenseEntryName()));
            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getLicenseEntryType()));
            objectOutputStream.writeUTF(
                String.valueOf(licenseKey.getLicenseVersion()));

            objectOutputStream.writeObject(
                StringUtil.split(licenseKey.getMacAddresses()));
            objectOutputStream.writeInt(licenseKey.getMaxHttpSessions());
            objectOutputStream.writeInt(licenseKey.getMaxServers());
            objectOutputStream.writeLong(licenseKey.getMaxConcurrentUsers());
            objectOutputStream.writeLong(licenseKey.getMaxUsers());

            String sizing = StringPool.BLANK;

            if (licenseKey.getSizing() > 0) {
                sizing = LanguageUtil.get(
                    LocaleUtil.US,
                    LicenseKeyConstants.getSizingLabel(licenseKey.getSizing()));
            }

            objectOutputStream.writeUTF(sizing);

            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getOwner()));
            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getProductEntryName()));
            objectOutputStream.writeUTF(
                GetterUtil.getString(licenseKey.getProductId()));
            objectOutputStream.writeUTF(
                String.valueOf(licenseKey.getProductVersion()));

            String[] serverIds = null;

            if (Validator.isNotNull(licenseKey.getServerId())) {
                serverIds = new String[] {licenseKey.getServerId()};
            }
            else {
                serverIds = new String[0];
            }

            objectOutputStream.writeObject(serverIds);

            objectOutputStream.writeObject(licenseKey.getStartDate());

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

    public String toXML(LicenseKey licenseKey) throws Exception {
        Document document = null;

        Map<String, String> properties = _getProperties(licenseKey);

        String key = licenseKey.getKey();

        if (licenseKey.getLicenseVersion() >= 3) {
            document = toXMLVersion3_4(properties, key, false);
        }
        else {
            document = toXMLVersion2(properties, key);
        }

        return document.formattedString();
    }

    public String toXML(List<LicenseKey> licenseKeys) throws Exception {
        licenseKeys = ListUtil.copy(licenseKeys);

        Iterator<LicenseKey> itr = licenseKeys.iterator();

        while (itr.hasNext()) {
            LicenseKey licenseKey = itr.next();

            if (!licenseKey.isActive()) {
                itr.remove();
            }
        }

        LicenseKey firstLicenseKey = licenseKeys.get(0);

        Map<String, String> properties = _getProperties(firstLicenseKey);

        if (firstLicenseKey.getLicenseVersion() >= 4) {
            String licenseEntryType = firstLicenseKey.getLicenseEntryType();

            if (licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
                licenseEntryType.equals(
                LicenseEntryConstants.TYPE_PRODUCTION)) {

                properties.put(
                    "maxServers", String.valueOf(licenseKeys.size()));
            }
        }

        Document document = toXMLVersion3_4(properties, StringPool.BLANK, true);

        Element rootElement = document.getRootElement();

        List<String> hostNames = new ArrayList<>();
        List<String> ipAddresses = new ArrayList<>();
        List<String> macAddresses = new ArrayList<>();

        Element serversElement = rootElement.addElement("servers");

        for (LicenseKey licenseKey : licenseKeys) {
            Map<String, String> curProperties = _getProperties(licenseKey);

            Element serverElement = serversElement.addElement("server");

            exportServerToXML(serverElement, curProperties);

            String curHostName = curProperties.get("hostNames");

            if (Validator.isNotNull(curHostName)) {
                hostNames.add(curHostName);
            }

            List<String> curIpAddresses = ListUtil.fromArray(
                StringUtil.split(curProperties.get("ipAddresses")));

            ipAddresses.addAll(curIpAddresses);

            List<String> curMacAddresses = ListUtil.fromArray(
                StringUtil.split(curProperties.get("macAddresses")));

            macAddresses.addAll(curMacAddresses);
        }

        properties.put("hostNames", StringUtil.merge(hostNames));
        properties.put("ipAddresses", StringUtil.merge(ipAddresses));
        properties.put("macAddresses", StringUtil.merge(macAddresses));

        String key = _keyGenerator.generate(properties);

        DocUtil.add(rootElement, "key", key);

        return document.formattedString();
    }

    public String toXML(Map<String, String> properties, String key)
        throws Exception {

        Document document = toXMLVersion3_4(properties, key, false);

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

        TimeZone timeZone = TimeZone.getTimeZone("GMT");

        longDateFormatDateTime.setTimeZone(timeZone);

        if (licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {
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

        if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
            licenseEntryType.equals(
            LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

            DocUtil.add(
            rootElement, "max-servers", properties.get("maxServers"));
        }

        if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
            licenseEntryType.equals(
            LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) ||
            licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {

            DocUtil.add(
                rootElement, "max-http-sessions",
                properties.get("maxHttpSessions"));
        }

        if (licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION)) {
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

        TimeZone timeZone = TimeZone.getTimeZone("GMT");

        longDateFormatDateTime.setTimeZone(timeZone);

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

        if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
            ((licenseVersion >= 4) &&
            (licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
            licenseEntryType.equals(
            LicenseEntryConstants.TYPE_PRODUCTION)))) {

            DocUtil.add(
                rootElement, "max-servers", properties.get("maxServers"));
        }

        if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
            licenseEntryType.equals(
            LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

            DocUtil.add(
                rootElement, "max-http-sessions",
                properties.get("maxHttpSessions"));
        }

        if (licenseEntryType.equals(LicenseEntryConstants.TYPE_PER_USER)) {
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
            if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
                licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
                licenseEntryType.equals(LicenseEntryConstants.TYPE_PER_USER) ||
                licenseEntryType.equals(
                LicenseEntryConstants.TYPE_PRODUCTION)) {

                exportServerToXML(rootElement, properties);
            }

            DocUtil.add(rootElement, "key", key);
        }

        return document;
    }

    private Map<String, String> _getProperties(LicenseKey licenseKey) {
        String[] serverIds = {licenseKey.getServerId()};

        Map<String, String> properties = _keyGenerator.getProperties(
            licenseKey.getAccountEntryName(), licenseKey.getLicenseEntryName(),
            licenseKey.getLicenseEntryType(), licenseKey.getLicenseVersion(),
            licenseKey.getProductEntryName(), licenseKey.getProductId(),
            licenseKey.getProductVersionLabel(), licenseKey.getOwner(),
            licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
            licenseKey.getMaxConcurrentUsers(), licenseKey.getMaxUsers(),
            licenseKey.getSizing(), licenseKey.getDescription(),
            licenseKey.getHostName(), licenseKey.getIpAddresses(),
            licenseKey.getMacAddresses(), serverIds, licenseKey.getStartDate(),
            licenseKey.getExpirationDate());

        // See LRDCOM-2568

        if (licenseKey.getProductVersion() ==
            ProductEntryConstants.PORTAL_VERSION_6_1_10) {

            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.DAY_OF_MONTH, 31);
            cal.set(Calendar.MONTH, 6);
            cal.set(Calendar.YEAR, 2012);

            Date createDate = licenseKey.getCreateDate();

            if (createDate.before(cal.getTime())) {
                properties.put("productVersion", "6.1");
            }
        }

        return properties;
    }

    @Reference
    private KeyGenerator _keyGenerator;

}
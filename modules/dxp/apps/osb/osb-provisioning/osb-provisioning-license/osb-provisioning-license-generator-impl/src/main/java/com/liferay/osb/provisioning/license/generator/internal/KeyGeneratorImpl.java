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

package com.liferay.osb.customer.license.generator.internal;

import com.liferay.osb.customer.admin.constants.LicenseEntryConstants;
import com.liferay.osb.customer.admin.constants.ProductEntryConstants;
import com.liferay.osb.customer.license.constants.LicenseKeyConstants;
import com.liferay.osb.customer.license.generator.KeyGenerator;
import com.liferay.petra.encryptor.Encryptor;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.security.Key;
import java.security.MessageDigest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@Component(immediate = true, service = KeyGenerator.class)
public class KeyGeneratorImpl implements KeyGenerator {

	public Properties decryptServerId(byte[] bytes) throws Exception {
		Properties serverProperties = new Properties();

		for (Key key : _keys) {
			bytes = Encryptor.decryptUnencodedAsBytes(key, bytes);
		}

		PropertiesUtil.load(serverProperties, new String(bytes));

		return serverProperties;
	}

	public String generate(Map<String, String> properties) {
		return _encrypt(properties);
	}

	public String generate(
		String accountEntryName, String licenseEntryName,
		String licenseEntryType, int licenseVersion, String productEntryName,
		String productId, String productVersionLabel, String owner,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, int sizing, String description, String hostName,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate) {

		Map<String, String> properties = getProperties(
			accountEntryName, licenseEntryName, licenseEntryType,
			licenseVersion, productEntryName, productId, productVersionLabel,
			owner, maxServers, maxHttpSessions, maxConcurrentUsers, maxUsers,
			sizing, description, hostName, ipAddresses, macAddresses, serverIds,
			startDate, expirationDate);

		return _encrypt(properties);
	}

	public Map<String, String> getProperties(
		String accountEntryName, String licenseEntryName,
		String licenseEntryType, int licenseVersion, String productEntryName,
		String productId, String productVersionLabel, String owner,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, int sizing, String description, String hostNames,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate) {

		Arrays.sort(serverIds);

		long lifetime = expirationDate.getTime() - startDate.getTime();

		Map<String, String> properties = new HashMap<>();

		properties.put("version", String.valueOf(licenseVersion));

		if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {
			properties.put("startDate", String.valueOf(startDate.getTime()));
		}

		properties.put("description", description);
		properties.put("owner", owner);
		properties.put("type", licenseEntryType);

		if (licenseVersion == 1) {
			properties.put("lifetime", String.valueOf(lifetime));
			properties.put("productVersion", productVersionLabel);

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

				for (int i = 0; i < serverIds.length; i++) {
					String serverId = StringUtil.replace(
						serverIds[i], CharPool.DASH, CharPool.COLON);

					serverId = StringUtil.toLowerCase(serverId.trim());

					properties.put("macAddress." + i, serverId);
				}
			}
			else {
				String serverId = serverIds[0].trim();

				properties.put("serverId", serverId);
			}
		}
		else if (licenseVersion == 2) {
			properties.put("accountEntryName", accountEntryName);
			properties.put("licenseEntryName", licenseEntryName);
			properties.put("productEntryName", productEntryName);
			properties.put("productVersion", productVersionLabel);

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {
				properties.put("lifetime", String.valueOf(lifetime));
			}
			else {
				properties.put(
					"expirationDate", String.valueOf(expirationDate.getTime()));
			}

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

				properties.put("maxServers", String.valueOf(maxServers));
			}

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) ||
				licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {

				properties.put(
					"maxHttpSessions", String.valueOf(maxHttpSessions));
			}

			if (licenseEntryType.equals(
					LicenseEntryConstants.TYPE_PRODUCTION)) {

				String serverIdsList = StringUtil.merge(serverIds);

				serverIdsList = StringUtil.toLowerCase(serverIdsList);
				serverIdsList = StringUtil.replace(
					serverIdsList, CharPool.DASH, CharPool.COLON);

				properties.put("serverIds", serverIdsList);
			}
		}
		else if (licenseVersion >= 3) {
			if (productId.equals(ProductEntryConstants.PRODUCT_ID_PORTAL)) {
				properties.put("accountEntryName", accountEntryName);
				properties.put("licenseEntryName", licenseEntryName);
				properties.put("productVersion", productVersionLabel);
			}
			else {
				properties.put("productId", productId);
				properties.put("productVersion", productVersionLabel);
			}

			properties.put(
				"expirationDate", String.valueOf(expirationDate.getTime()));
			properties.put("productEntryName", productEntryName);

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
				((licenseVersion >= 4) &&
				 (licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
				  licenseEntryType.equals(
					  LicenseEntryConstants.TYPE_PRODUCTION)))) {

				properties.put("maxServers", String.valueOf(maxServers));
			}

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

				properties.put(
					"maxHttpSessions", String.valueOf(maxHttpSessions));
			}

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_PER_USER)) {
				if (maxConcurrentUsers > 0) {
					properties.put(
						"maxConcurrentUsers",
						String.valueOf(maxConcurrentUsers));
				}

				if (maxUsers > 0) {
					properties.put("maxUsers", String.valueOf(maxUsers));
				}
			}

			if ((licenseVersion >= 6) && (sizing > 0)) {
				properties.put(
					"instanceSize",
					LanguageUtil.get(
						LocaleUtil.US,
						LicenseKeyConstants.getSizingLabel(sizing)));
			}

			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
				licenseEntryType.equals(LicenseEntryConstants.TYPE_LIMITED) ||
				licenseEntryType.equals(LicenseEntryConstants.TYPE_PER_USER) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_PRODUCTION)) {

				properties.put("hostNames", hostNames);
				properties.put("ipAddresses", ipAddresses);
				properties.put(
					"macAddresses",
					StringUtil.replace(
						macAddresses, CharPool.DASH, CharPool.COLON));

				if (serverIds.length > 0) {
					properties.put("serverIds", StringUtil.merge(serverIds));
				}
			}
		}

		return properties;
	}

	public String getServerId(
		String hostName, String ipAddresses, String macAddresses) {

		try {
			Properties serverIdProperties = new Properties();

			serverIdProperties.put("hostName", hostName);
			serverIdProperties.put("ipAddresses", ipAddresses);
			serverIdProperties.put(
				"macAddresses",
				StringUtil.replace(
					macAddresses, CharPool.DASH, CharPool.COLON));

			UUID uuid = UUID.randomUUID();

			serverIdProperties.put("salt", uuid.toString());

			String propertiesString = PropertiesUtil.toString(
				serverIdProperties);

			byte[] bytes = propertiesString.getBytes(StringPool.UTF8);

			for (int i = _keys.length - 1; i >= 0; i--) {
				bytes = Encryptor.encryptUnencoded(_keys[i], bytes);
			}

			return Base64.objectToString(bytes);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return StringPool.BLANK;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		try {
			Class<?> clazz = getClass();

			String[] keys = StringUtil.split(
				StringUtil.read(
					clazz.getClassLoader(),
					"com/liferay/osb/customer/license/generator/dependencies" +
						"/keys.txt"),
				StringPool.NEW_LINE);

			_keys[0] = (Key)Base64.stringToObject(keys[175]);
			_keys[1] = (Key)Base64.stringToObject(keys[542]);
			_keys[2] = (Key)Base64.stringToObject(keys[706]);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private String _digest(String text, String algorithm) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

		messageDigest.update(text.getBytes());

		byte[] bytes = messageDigest.digest();

		StringBuilder sb = new StringBuilder(bytes.length << 1);

		for (byte curByte : bytes) {
			int byte_ = curByte & 0xff;

			sb.append(_HEX_CHARACTERS[byte_ >> 4]);
			sb.append(_HEX_CHARACTERS[byte_ & 0xf]);
		}

		return sb.toString();
	}

	private String _digestsToString(List<String> digests) {
		StringBundler sb = new StringBundler(digests.size());

		for (String digest : digests) {
			sb.append(digest);
		}

		return sb.toString();
	}

	private String _encrypt(Map<String, String> properties) {
		int licenseVersion = GetterUtil.getInteger(properties.get("version"));
		String productId = properties.get("productId");

		try {
			if (licenseVersion == 1) {
				return _encryptVersion1(properties);
			}
			else if (licenseVersion >= 2) {
				return _encryptVersion2(productId, properties);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return StringPool.BLANK;
	}

	private String _encryptVersion1(Map<String, String> properties)
		throws Exception {

		StringBundler sb = new StringBundler(properties.size() * 4);

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			sb.append(entry.getKey());
			sb.append(StringPool.EQUAL);
			sb.append(entry.getValue());
			sb.append(StringPool.NEW_LINE);
		}

		String propertiesString = sb.toString();

		byte[] bytes = propertiesString.getBytes(StringPool.UTF8);

		for (int i = _keys.length - 1; i >= 0; i--) {
			bytes = Encryptor.encryptUnencoded(_keys[i], bytes);
		}

		return Base64.objectToString(bytes);
	}

	private String _encryptVersion2(
			String productId, Map<String, String> properties)
		throws Exception {

		List<String> keys = new ArrayList<>(properties.keySet());

		Collections.sort(keys);

		List<String> digests = new ArrayList<>(properties.size());

		for (int i = 0; i < keys.size(); i++) {
			String text = properties.get(keys.get(i));

			String algorithm = _getAlgorithm(productId, i);

			String digest = _digest(text, algorithm);

			digests.add(digest);
		}

		digests = _shortenDigests(digests);

		for (int i = 0; i < digests.size(); i++) {
			String digest = digests.get(i);

			String algorithm = _getAlgorithm(productId, i);

			digest = _digest(digest, algorithm);

			digests.set(i, digest);
		}

		if (Validator.isNull(productId) ||
			productId.equals(ProductEntryConstants.PRODUCT_ID_PORTAL)) {

			return _interweaveDigest(digests);
		}

		return _digestsToString(digests);
	}

	private String _getAlgorithm(String productId, int i) {
		if (Validator.isNull(productId) ||
			productId.equals(ProductEntryConstants.PRODUCT_ID_PORTAL)) {

			return _ALGORITHMS[i % _ALGORITHMS.length];
		}

		return _ALGORITHMS[2];
	}

	private String _interweaveDigest(List<String> digests) {
		int size = digests.size();

		int finalLength = 0;
		int shortestLength = Integer.MAX_VALUE;

		for (String digest : digests) {
			int length = digest.length();

			finalLength += length;

			if (length < shortestLength) {
				shortestLength = length;
			}
		}

		StringBuilder sb = new StringBuilder(finalLength);

		for (int i = 0; i < shortestLength; i++) {
			for (int j = 0; j < size; j++) {
				String digest = digests.get(j);

				sb.append(digest.charAt(i));
			}
		}

		for (String digest : digests) {
			if (digest.length() > shortestLength) {
				sb.append(digest.substring(shortestLength));
			}
		}

		return sb.toString();
	}

	private List<String> _shortenDigests(List<String> digests)
		throws Exception {

		int size = digests.size();

		int groupSize = size / 4;

		if ((groupSize * 4) < size) {
			groupSize++;
		}

		List<String> shortenedDigests = new ArrayList<>(4);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size; i++) {
			String digest = digests.get(i);

			if ((i != 0) && ((i % groupSize) == 0)) {
				shortenedDigests.add(sb.toString());

				sb.setLength(0);
			}

			sb.append(digest);
		}

		if (shortenedDigests.size() < 4) {
			shortenedDigests.add(sb.toString());
		}

		return shortenedDigests;
	}

	private static final String[] _ALGORITHMS = {
		"MD5", "SHA-1", "SHA-256", "SHA-512"
	};

	private static final char[] _HEX_CHARACTERS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'
	};

	private static final Log _log = LogFactoryUtil.getLog(
		KeyGeneratorImpl.class);

	private final Key[] _keys = new Key[3];

}
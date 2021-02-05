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

package com.liferay.osb.customer.license.generator;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public interface KeyGenerator {

	public Properties decryptServerId(byte[] bytes) throws Exception;

	public String generate(Map<String, String> properties);

	public String generate(
		String accountEntryName, String licenseEntryName,
		String licenseEntryType, int licenseVersion, String productEntryName,
		String productId, String productVersionLabel, String owner,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, int sizing, String description, String hostName,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate);

	public Map<String, String> getProperties(
		String accountEntryName, String licenseEntryName,
		String licenseEntryType, int licenseVersion, String productEntryName,
		String productId, String productVersionLabel, String owner,
		int maxServers, int maxHttpSessions, long maxConcurrentUsers,
		long maxUsers, int sizing, String description, String hostNames,
		String ipAddresses, String macAddresses, String[] serverIds,
		Date startDate, Date expirationDate);

	public String getServerId(
		String hostName, String ipAddresses, String macAddresses);

}
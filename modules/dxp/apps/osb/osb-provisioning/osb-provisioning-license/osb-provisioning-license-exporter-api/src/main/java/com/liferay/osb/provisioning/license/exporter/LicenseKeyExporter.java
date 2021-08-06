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

package com.liferay.osb.provisioning.license.exporter;

import java.util.Date;

/**
 * @author Amos Fong
 */
public interface LicenseKeyExporter {

	public String aggregateXMLs(String[] xmls) throws Exception;

	public String getFileName(
		String productName, String productVersion, String name);

	public String getFileName(String[] productNames, String[] names);

	public String toEncodedLicenseFile(String serverId, String key);

	public String toLI(
			String key, String accountName, String licenseEntryName,
			String licenseType, int licenseVersion, String productName,
			String productId, String productVersion, String owner,
			int maxClusterNodes, int maxServers, int maxHttpSessions,
			long maxConcurrentUsers, long maxUsers, String sizing,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, Date startDate,
			Date expirationDate)
		throws Exception;

	public String toXML(
			String accountName, String licenseEntryName, String licenseType,
			int licenseVersion, String productName, String productId,
			String productVersion, String owner, int maxClusterNodes,
			int maxServers, int maxHttpSessions, long maxConcurrentUsers,
			long maxUsers, String sizing, String description,
			String[] hostNames, String[] ipAddresses, String[] macAddresses,
			String[] serverIds, Date startDate, Date expirationDate,
			Date createDate)
		throws Exception;

	public String toXML(
			String key, String accountName, String licenseEntryName,
			String licenseType, int licenseVersion, String productName,
			String productId, String productVersion, String owner,
			int maxClusterNodes, int maxServers, int maxHttpSessions,
			long maxConcurrentUsers, long maxUsers, String sizing,
			String description, String hostNames, String ipAddresses,
			String macAddresses, String serverIds, Date startDate,
			Date expirationDate, Date createDate)
		throws Exception;

}
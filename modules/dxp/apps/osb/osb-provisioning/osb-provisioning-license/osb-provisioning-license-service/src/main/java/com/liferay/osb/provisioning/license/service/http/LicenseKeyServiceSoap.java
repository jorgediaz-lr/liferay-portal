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

package com.liferay.osb.customer.license.service.http;

import com.liferay.osb.customer.license.service.LicenseKeyServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>LicenseKeyServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.customer.license.model.LicenseKeySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.customer.license.model.LicenseKey</code>, that is translated to a
 * <code>com.liferay.osb.customer.license.model.LicenseKeySoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyServiceHttp
 * @generated
 */
public class LicenseKeyServiceSoap {

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			addDeveloperLicenseKey(
				long accountEntryId, long productEntryId,
				int productMinorVersion)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.addDeveloperLicenseKey(
					accountEntryId, productEntryId, productMinorVersion);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			addLicenseKey(
				long userId, long licenseKeySetId, String name,
				long licenseEntryId, long productEntryId,
				String koroneikiAccountKey, String koroneikiProductPurchaseKey,
				String accountEntryName, int productVersion, long clusterId,
				String owner, int maxServers, int maxHttpSessions,
				int maxConcurrentUsers, int maxUsers, int sizing,
				String description, String[] hostNames, String[] ipAddresses,
				String[] macAddresses, String[] serverIds,
				java.util.Date startDate, java.util.Date expirationDate,
				boolean complimentary, boolean active)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.addLicenseKey(
					userId, licenseKeySetId, name, licenseEntryId,
					productEntryId, koroneikiAccountKey,
					koroneikiProductPurchaseKey, accountEntryName,
					productVersion, clusterId, owner, maxServers,
					maxHttpSessions, maxConcurrentUsers, maxUsers, sizing,
					description, hostNames, ipAddresses, macAddresses,
					serverIds, startDate, expirationDate, complimentary,
					active);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			addLicenseKey(
				String userUuid, String assetReceiptLicenseUuid,
				String licenseEntryType, String productEntryName,
				String productId, int productVersion, String owner,
				long maxUsers, String description, String hostName,
				String ipAddresses, String macAddresses, String serverId,
				java.util.Date startDate, java.util.Date expirationDate)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.addLicenseKey(
					userUuid, assetReceiptLicenseUuid, licenseEntryType,
					productEntryName, productId, productVersion, owner,
					maxUsers, description, hostName, ipAddresses, macAddresses,
					serverId, startDate, expirationDate);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String generateCommerceLicenseKey(
			String owner, java.util.Date startDate, long licenseLifetime)
		throws RemoteException {

		try {
			String returnValue =
				LicenseKeyServiceUtil.generateCommerceLicenseKey(
					owner, startDate, licenseLifetime);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String generateWeDeployLicenseKey(
			String owner, java.util.Date startDate, long licenseLifetime)
		throws RemoteException {

		try {
			String returnValue =
				LicenseKeyServiceUtil.generateWeDeployLicenseKey(
					owner, startDate, licenseLifetime);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap[]
			getAssetReceiptLicenseLicenseKeys(
				String assetReceiptLicenseUuid, boolean complimentary,
				boolean active)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
				returnValue =
					LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeys(
						assetReceiptLicenseUuid, complimentary, active);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws RemoteException {

		try {
			int returnValue =
				LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeysCount(
					assetReceiptLicenseUuid, complimentary, active);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			getLicenseKey(long licenseKeyId)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.getLicenseKey(licenseKeyId);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			getLicenseKey(String uuid)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.getLicenseKey(uuid);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap[]
			getLicenseKeys(long userId, String productId)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
				returnValue = LicenseKeyServiceUtil.getLicenseKeys(
					userId, productId);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap[]
			getLicenseKeys(String productId, String serverId)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
				returnValue = LicenseKeyServiceUtil.getLicenseKeys(
					productId, serverId);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap[]
			getLicenseKeys(
				String assetReceiptLicenseUuid, String productId,
				String serverId, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
				returnValue = LicenseKeyServiceUtil.getLicenseKeys(
					assetReceiptLicenseUuid, productId, serverId, active, start,
					end, obc);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap[]
			getLicenseKeysByName(
				String productEntryName, String serverId, boolean active,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
				returnValue = LicenseKeyServiceUtil.getLicenseKeysByName(
					productEntryName, serverId, active, start, end, obc);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap[]
			getLicenseKeySetLicenseKeys(long licenseKeySetId)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
				returnValue = LicenseKeyServiceUtil.getLicenseKeySetLicenseKeys(
					licenseKeySetId);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap[]
			getOfferingEntryGroupLicenseKeys(
				long[] offeringEntryIds, boolean complimentary, boolean active,
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.customer.license.model.LicenseKey>
				returnValue =
					LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeys(
						offeringEntryIds, complimentary, active, start, end,
						obc);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
			long[] offeringEntryIds, boolean complimentary, boolean active)
		throws RemoteException {

		try {
			int returnValue =
				LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeysCount(
					offeringEntryIds, complimentary, active);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getOfferingEntryLicenseKeysCount(
			long offeringEntryId, boolean complimentary, boolean active)
		throws RemoteException {

		try {
			int returnValue =
				LicenseKeyServiceUtil.getOfferingEntryLicenseKeysCount(
					offeringEntryId, complimentary, active);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static boolean isActive(
			String serverId, String productId, String key)
		throws RemoteException {

		try {
			boolean returnValue = LicenseKeyServiceUtil.isActive(
				serverId, productId, key);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			registerLicenseKey(
				String orderEntryUuid, String productEntryName,
				int liferayVersion, int maxServers, String hostName,
				String ipAddresses, String macAddresses, String serverId)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.registerLicenseKey(
					orderEntryUuid, productEntryName, liferayVersion,
					maxServers, hostName, ipAddresses, macAddresses, serverId);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			renewLicenseKey(
				long licenseKeyId, java.util.Date startDate, int renewTime)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.renewLicenseKey(
					licenseKeyId, startDate, renewTime);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			renewLicenseKey(
				String uuid, java.util.Date startDate,
				java.util.Date expirationDate)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.renewLicenseKey(
					uuid, startDate, expirationDate);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void updateLicenseKey(
			long userId, long licenseKeyId, boolean active)
		throws RemoteException {

		try {
			LicenseKeyServiceUtil.updateLicenseKey(
				userId, licenseKeyId, active);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySoap
			updateLicenseKey(
				long licenseKeyId, long licenseKeySetId,
				String koroneikiProductPurchaseKey, String name,
				boolean complimentary, boolean active)
		throws RemoteException {

		try {
			com.liferay.osb.customer.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.updateLicenseKey(
					licenseKeyId, licenseKeySetId, koroneikiProductPurchaseKey,
					name, complimentary, active);

			return com.liferay.osb.customer.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void updateLicenseKey(
			String userUuid, String uuid, boolean active)
		throws RemoteException {

		try {
			LicenseKeyServiceUtil.updateLicenseKey(userUuid, uuid, active);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws RemoteException {

		try {
			LicenseKeyServiceUtil.updateLicenseKeys(
				assetReceiptLicenseUuid, active);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LicenseKeyServiceSoap.class);

}
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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>LicenseKeyServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyServiceSoap
 * @generated
 */
public class LicenseKeyServiceHttp {

	public static com.liferay.osb.customer.license.model.LicenseKey
			addDeveloperLicenseKey(
				HttpPrincipal httpPrincipal, long accountEntryId,
				long productEntryId, int productMinorVersion)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "addDeveloperLicenseKey",
				_addDeveloperLicenseKeyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEntryId, productEntryId, productMinorVersion);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			addLicenseKey(
				HttpPrincipal httpPrincipal, long userId, long licenseKeySetId,
				String name, long licenseEntryId, long productEntryId,
				String koroneikiAccountKey, String koroneikiProductPurchaseKey,
				String accountEntryName, int productVersion, long clusterId,
				String owner, int maxServers, int maxHttpSessions,
				int maxConcurrentUsers, int maxUsers, int sizing,
				String description, String[] hostNames, String[] ipAddresses,
				String[] macAddresses, String[] serverIds,
				java.util.Date startDate, java.util.Date expirationDate,
				boolean complimentary, boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "addLicenseKey",
				_addLicenseKeyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, licenseKeySetId, name, licenseEntryId,
				productEntryId, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName, productVersion,
				clusterId, owner, maxServers, maxHttpSessions,
				maxConcurrentUsers, maxUsers, sizing, description, hostNames,
				ipAddresses, macAddresses, serverIds, startDate, expirationDate,
				complimentary, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			addLicenseKey(
				HttpPrincipal httpPrincipal, String userUuid,
				String assetReceiptLicenseUuid, String licenseEntryType,
				String productEntryName, String productId, int productVersion,
				String owner, long maxUsers, String description,
				String hostName, String ipAddresses, String macAddresses,
				String serverId, java.util.Date startDate,
				java.util.Date expirationDate)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "addLicenseKey",
				_addLicenseKeyParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userUuid, assetReceiptLicenseUuid, licenseEntryType,
				productEntryName, productId, productVersion, owner, maxUsers,
				description, hostName, ipAddresses, macAddresses, serverId,
				startDate, expirationDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static String generateCommerceLicenseKey(
			HttpPrincipal httpPrincipal, String owner, java.util.Date startDate,
			long licenseLifetime)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "generateCommerceLicenseKey",
				_generateCommerceLicenseKeyParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, owner, startDate, licenseLifetime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static String generateWeDeployLicenseKey(
			HttpPrincipal httpPrincipal, String owner, java.util.Date startDate,
			long licenseLifetime)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "generateWeDeployLicenseKey",
				_generateWeDeployLicenseKeyParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, owner, startDate, licenseLifetime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getAssetReceiptLicenseLicenseKeys(
					HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
					boolean complimentary, boolean active)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class,
				"getAssetReceiptLicenseLicenseKeys",
				_getAssetReceiptLicenseLicenseKeysParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, complimentary, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
			HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
			boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class,
				"getAssetReceiptLicenseLicenseKeysCount",
				_getAssetReceiptLicenseLicenseKeysCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, complimentary, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			getLicenseKey(HttpPrincipal httpPrincipal, long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKey",
				_getLicenseKeyParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			getLicenseKey(HttpPrincipal httpPrincipal, String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKey",
				_getLicenseKeyParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
				HttpPrincipal httpPrincipal, long userId, String productId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeys",
				_getLicenseKeysParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, productId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
				HttpPrincipal httpPrincipal, String productId, String serverId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeys",
				_getLicenseKeysParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productId, serverId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> getLicenseKeys(
				HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
				String productId, String serverId, boolean active, int start,
				int end, com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeys",
				_getLicenseKeysParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, productId, serverId, active,
				start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getLicenseKeysByName(
					HttpPrincipal httpPrincipal, String productEntryName,
					String serverId, boolean active, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeysByName",
				_getLicenseKeysByNameParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, productEntryName, serverId, active, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getLicenseKeySetLicenseKeys(
					HttpPrincipal httpPrincipal, long licenseKeySetId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getLicenseKeySetLicenseKeys",
				_getLicenseKeySetLicenseKeysParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeySetId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey>
				getOfferingEntryGroupLicenseKeys(
					HttpPrincipal httpPrincipal, long[] offeringEntryIds,
					boolean complimentary, boolean active, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator obc)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getOfferingEntryGroupLicenseKeys",
				_getOfferingEntryGroupLicenseKeysParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, offeringEntryIds, complimentary, active, start, end,
				obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
			HttpPrincipal httpPrincipal, long[] offeringEntryIds,
			boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class,
				"getOfferingEntryGroupLicenseKeysCount",
				_getOfferingEntryGroupLicenseKeysCountParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, offeringEntryIds, complimentary, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getOfferingEntryLicenseKeysCount(
			HttpPrincipal httpPrincipal, long offeringEntryId,
			boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "getOfferingEntryLicenseKeysCount",
				_getOfferingEntryLicenseKeysCountParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, offeringEntryId, complimentary, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static boolean isActive(
			HttpPrincipal httpPrincipal, String serverId, String productId,
			String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "isActive",
				_isActiveParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, serverId, productId, key);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			registerLicenseKey(
				HttpPrincipal httpPrincipal, String orderEntryUuid,
				String productEntryName, int liferayVersion, int maxServers,
				String hostName, String ipAddresses, String macAddresses,
				String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "registerLicenseKey",
				_registerLicenseKeyParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, orderEntryUuid, productEntryName, liferayVersion,
				maxServers, hostName, ipAddresses, macAddresses, serverId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			renewLicenseKey(
				HttpPrincipal httpPrincipal, long licenseKeyId,
				java.util.Date startDate, int renewTime)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "renewLicenseKey",
				_renewLicenseKeyParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeyId, startDate, renewTime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			renewLicenseKey(
				HttpPrincipal httpPrincipal, String uuid,
				java.util.Date startDate, java.util.Date expirationDate)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "renewLicenseKey",
				_renewLicenseKeyParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, uuid, startDate, expirationDate);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> search(
				HttpPrincipal httpPrincipal, Long createUserId,
				int createDateGTDay, int createDateGTMonth,
				int createDateGTYear, int createDateLTDay,
				int createDateLTMonth, int createDateLTYear,
				Long modifiedUserId, int modifiedDateGTDay,
				int modifiedDateGTMonth, int modifiedDateGTYear,
				int modifiedDateLTDay, int modifiedDateLTMonth,
				int modifiedDateLTYear, String koroneikiAccountKey,
				String koroneikiProductPurchaseKey, String accountEntryName,
				String licenseKeySetName, int startDateGTDay,
				int startDateGTMonth, int startDateGTYear, int startDateLTDay,
				int startDateLTMonth, int startDateLTYear,
				long[] licenseEntryIds, long[] productEntryIds,
				String productEntryName, String productId,
				int[] productVersions, String owner, String description,
				String hostName, String ipAddress, String macAddress,
				String serverId, String key, int expirationDateGTDay,
				int expirationDateGTMonth, int expirationDateGTYear,
				int expirationDateLTDay, int expirationDateLTMonth,
				int expirationDateLTYear,
				java.util.LinkedHashMap<String, Object> params,
				boolean andSearch, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
			throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "search", _searchParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, createUserId, createDateGTDay, createDateGTMonth,
				createDateGTYear, createDateLTDay, createDateLTMonth,
				createDateLTYear, modifiedUserId, modifiedDateGTDay,
				modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
				modifiedDateLTMonth, modifiedDateLTYear, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName,
				licenseKeySetName, startDateGTDay, startDateGTMonth,
				startDateGTYear, startDateLTDay, startDateLTMonth,
				startDateLTYear, licenseEntryIds, productEntryIds,
				productEntryName, productId, productVersions, owner,
				description, hostName, ipAddress, macAddress, serverId, key,
				expirationDateGTDay, expirationDateGTMonth,
				expirationDateGTYear, expirationDateLTDay,
				expirationDateLTMonth, expirationDateLTYear, params, andSearch,
				start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.license.model.LicenseKey> search(
				HttpPrincipal httpPrincipal, String keywords,
				java.util.LinkedHashMap<String, Object> params, int start,
				int end, com.liferay.portal.kernel.util.OrderByComparator obc)
			throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "search", _searchParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, keywords, params, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.license.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int searchCount(
			HttpPrincipal httpPrincipal, Long createUserId, int createDateGTDay,
			int createDateGTMonth, int createDateGTYear, int createDateLTDay,
			int createDateLTMonth, int createDateLTYear, Long modifiedUserId,
			int modifiedDateGTDay, int modifiedDateGTMonth,
			int modifiedDateGTYear, int modifiedDateLTDay,
			int modifiedDateLTMonth, int modifiedDateLTYear,
			String koroneikiAccountKey, String koroneikiProductPurchaseKey,
			String accountEntryName, String licenseKeySetName,
			int startDateGTDay, int startDateGTMonth, int startDateGTYear,
			int startDateLTDay, int startDateLTMonth, int startDateLTYear,
			long[] licenseEntryIds, long[] productEntryIds,
			String productEntryName, String productId, int[] productVersions,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			int expirationDateGTDay, int expirationDateGTMonth,
			int expirationDateGTYear, int expirationDateLTDay,
			int expirationDateLTMonth, int expirationDateLTYear,
			java.util.LinkedHashMap<String, Object> params, boolean andSearch)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "searchCount",
				_searchCountParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, createUserId, createDateGTDay, createDateGTMonth,
				createDateGTYear, createDateLTDay, createDateLTMonth,
				createDateLTYear, modifiedUserId, modifiedDateGTDay,
				modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
				modifiedDateLTMonth, modifiedDateLTYear, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName,
				licenseKeySetName, startDateGTDay, startDateGTMonth,
				startDateGTYear, startDateLTDay, startDateLTMonth,
				startDateLTYear, licenseEntryIds, productEntryIds,
				productEntryName, productId, productVersions, owner,
				description, hostName, ipAddress, macAddress, serverId, key,
				expirationDateGTDay, expirationDateGTMonth,
				expirationDateGTYear, expirationDateLTDay,
				expirationDateLTMonth, expirationDateLTYear, params, andSearch);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int searchCount(
			HttpPrincipal httpPrincipal, String keywords,
			java.util.LinkedHashMap<String, Object> params)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "searchCount",
				_searchCountParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, keywords, params);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void updateLicenseKey(
			HttpPrincipal httpPrincipal, long userId, long licenseKeyId,
			boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "updateLicenseKey",
				_updateLicenseKeyParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, licenseKeyId, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKey
			updateLicenseKey(
				HttpPrincipal httpPrincipal, long licenseKeyId,
				long licenseKeySetId, String koroneikiProductPurchaseKey,
				String name, boolean complimentary, boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "updateLicenseKey",
				_updateLicenseKeyParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeyId, licenseKeySetId,
				koroneikiProductPurchaseKey, name, complimentary, active);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.license.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void updateLicenseKey(
			HttpPrincipal httpPrincipal, String userUuid, String uuid,
			boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "updateLicenseKey",
				_updateLicenseKeyParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userUuid, uuid, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void updateLicenseKeys(
			HttpPrincipal httpPrincipal, String assetReceiptLicenseUuid,
			boolean active)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeyServiceUtil.class, "updateLicenseKeys",
				_updateLicenseKeysParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, assetReceiptLicenseUuid, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LicenseKeyServiceHttp.class);

	private static final Class<?>[] _addDeveloperLicenseKeyParameterTypes0 =
		new Class[] {long.class, long.class, int.class};
	private static final Class<?>[] _addLicenseKeyParameterTypes1 =
		new Class[] {
			long.class, long.class, String.class, long.class, long.class,
			String.class, String.class, String.class, int.class, long.class,
			String.class, int.class, int.class, int.class, int.class, int.class,
			String.class, String[].class, String[].class, String[].class,
			String[].class, java.util.Date.class, java.util.Date.class,
			boolean.class, boolean.class
		};
	private static final Class<?>[] _addLicenseKeyParameterTypes2 =
		new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, int.class, String.class, long.class, String.class,
			String.class, String.class, String.class, String.class,
			java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _generateCommerceLicenseKeyParameterTypes3 =
		new Class[] {String.class, java.util.Date.class, long.class};
	private static final Class<?>[] _generateWeDeployLicenseKeyParameterTypes4 =
		new Class[] {String.class, java.util.Date.class, long.class};
	private static final Class<?>[]
		_getAssetReceiptLicenseLicenseKeysParameterTypes5 = new Class[] {
			String.class, boolean.class, boolean.class
		};
	private static final Class<?>[]
		_getAssetReceiptLicenseLicenseKeysCountParameterTypes6 = new Class[] {
			String.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _getLicenseKeyParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _getLicenseKeyParameterTypes8 =
		new Class[] {String.class};
	private static final Class<?>[] _getLicenseKeysParameterTypes9 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getLicenseKeysParameterTypes10 =
		new Class[] {String.class, String.class};
	private static final Class<?>[] _getLicenseKeysParameterTypes11 =
		new Class[] {
			String.class, String.class, String.class, boolean.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getLicenseKeysByNameParameterTypes12 =
		new Class[] {
			String.class, String.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getLicenseKeySetLicenseKeysParameterTypes13 = new Class[] {long.class};
	private static final Class<?>[]
		_getOfferingEntryGroupLicenseKeysParameterTypes14 = new Class[] {
			long[].class, boolean.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getOfferingEntryGroupLicenseKeysCountParameterTypes15 = new Class[] {
			long[].class, boolean.class, boolean.class
		};
	private static final Class<?>[]
		_getOfferingEntryLicenseKeysCountParameterTypes16 = new Class[] {
			long.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _isActiveParameterTypes17 = new Class[] {
		String.class, String.class, String.class
	};
	private static final Class<?>[] _registerLicenseKeyParameterTypes18 =
		new Class[] {
			String.class, String.class, int.class, int.class, String.class,
			String.class, String.class, String.class
		};
	private static final Class<?>[] _renewLicenseKeyParameterTypes19 =
		new Class[] {long.class, java.util.Date.class, int.class};
	private static final Class<?>[] _renewLicenseKeyParameterTypes20 =
		new Class[] {String.class, java.util.Date.class, java.util.Date.class};
	private static final Class<?>[] _searchParameterTypes21 = new Class[] {
		Long.class, int.class, int.class, int.class, int.class, int.class,
		int.class, Long.class, int.class, int.class, int.class, int.class,
		int.class, int.class, String.class, String.class, String.class,
		String.class, int.class, int.class, int.class, int.class, int.class,
		int.class, long[].class, long[].class, String.class, String.class,
		int[].class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, int.class, int.class,
		int.class, int.class, int.class, int.class,
		java.util.LinkedHashMap.class, boolean.class, int.class, int.class,
		com.liferay.portal.kernel.util.OrderByComparator.class
	};
	private static final Class<?>[] _searchParameterTypes22 = new Class[] {
		String.class, java.util.LinkedHashMap.class, int.class, int.class,
		com.liferay.portal.kernel.util.OrderByComparator.class
	};
	private static final Class<?>[] _searchCountParameterTypes23 = new Class[] {
		Long.class, int.class, int.class, int.class, int.class, int.class,
		int.class, Long.class, int.class, int.class, int.class, int.class,
		int.class, int.class, String.class, String.class, String.class,
		String.class, int.class, int.class, int.class, int.class, int.class,
		int.class, long[].class, long[].class, String.class, String.class,
		int[].class, String.class, String.class, String.class, String.class,
		String.class, String.class, String.class, int.class, int.class,
		int.class, int.class, int.class, int.class,
		java.util.LinkedHashMap.class, boolean.class
	};
	private static final Class<?>[] _searchCountParameterTypes24 = new Class[] {
		String.class, java.util.LinkedHashMap.class
	};
	private static final Class<?>[] _updateLicenseKeyParameterTypes25 =
		new Class[] {long.class, long.class, boolean.class};
	private static final Class<?>[] _updateLicenseKeyParameterTypes26 =
		new Class[] {
			long.class, long.class, String.class, String.class, boolean.class,
			boolean.class
		};
	private static final Class<?>[] _updateLicenseKeyParameterTypes27 =
		new Class[] {String.class, String.class, boolean.class};
	private static final Class<?>[] _updateLicenseKeysParameterTypes28 =
		new Class[] {String.class, boolean.class};

}
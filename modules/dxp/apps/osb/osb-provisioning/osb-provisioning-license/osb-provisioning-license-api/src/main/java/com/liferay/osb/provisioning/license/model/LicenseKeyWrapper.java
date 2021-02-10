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

package com.liferay.osb.customer.license.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LicenseKey}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKey
 * @generated
 */
public class LicenseKeyWrapper implements LicenseKey, ModelWrapper<LicenseKey> {

	public LicenseKeyWrapper(LicenseKey licenseKey) {
		_licenseKey = licenseKey;
	}

	@Override
	public Class<?> getModelClass() {
		return LicenseKey.class;
	}

	@Override
	public String getModelClassName() {
		return LicenseKey.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("licenseKeyId", getLicenseKeyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedUserId", getModifiedUserId());
		attributes.put("modifiedUserName", getModifiedUserName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("licenseKeySetId", getLicenseKeySetId());
		attributes.put("assetReceiptLicenseUuid", getAssetReceiptLicenseUuid());
		attributes.put("koroneikiAccountKey", getKoroneikiAccountKey());
		attributes.put(
			"koroneikiProductPurchaseKey", getKoroneikiProductPurchaseKey());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("orderEntryId", getOrderEntryId());
		attributes.put("offeringEntryId", getOfferingEntryId());
		attributes.put("licenseEntryId", getLicenseEntryId());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("accountEntryName", getAccountEntryName());
		attributes.put("licenseEntryName", getLicenseEntryName());
		attributes.put("licenseEntryType", getLicenseEntryType());
		attributes.put("licenseVersion", getLicenseVersion());
		attributes.put("productEntryName", getProductEntryName());
		attributes.put("productId", getProductId());
		attributes.put("productVersion", getProductVersion());
		attributes.put("productVersionLabel", getProductVersionLabel());
		attributes.put("clusterId", getClusterId());
		attributes.put("owner", getOwner());
		attributes.put("maxServers", getMaxServers());
		attributes.put("maxConcurrentUsers", getMaxConcurrentUsers());
		attributes.put("maxUsers", getMaxUsers());
		attributes.put("maxHttpSessions", getMaxHttpSessions());
		attributes.put("sizing", getSizing());
		attributes.put("description", getDescription());
		attributes.put("hostName", getHostName());
		attributes.put("ipAddresses", getIpAddresses());
		attributes.put("macAddresses", getMacAddresses());
		attributes.put("serverId", getServerId());
		attributes.put("key", getKey());
		attributes.put("startDate", getStartDate());
		attributes.put("expirationDate", getExpirationDate());
		attributes.put("additionalInfo", getAdditionalInfo());
		attributes.put("complimentary", isComplimentary());
		attributes.put("active", isActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long licenseKeyId = (Long)attributes.get("licenseKeyId");

		if (licenseKeyId != null) {
			setLicenseKeyId(licenseKeyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long modifiedUserId = (Long)attributes.get("modifiedUserId");

		if (modifiedUserId != null) {
			setModifiedUserId(modifiedUserId);
		}

		String modifiedUserName = (String)attributes.get("modifiedUserName");

		if (modifiedUserName != null) {
			setModifiedUserName(modifiedUserName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long licenseKeySetId = (Long)attributes.get("licenseKeySetId");

		if (licenseKeySetId != null) {
			setLicenseKeySetId(licenseKeySetId);
		}

		String assetReceiptLicenseUuid = (String)attributes.get(
			"assetReceiptLicenseUuid");

		if (assetReceiptLicenseUuid != null) {
			setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
		}

		String koroneikiAccountKey = (String)attributes.get(
			"koroneikiAccountKey");

		if (koroneikiAccountKey != null) {
			setKoroneikiAccountKey(koroneikiAccountKey);
		}

		String koroneikiProductPurchaseKey = (String)attributes.get(
			"koroneikiProductPurchaseKey");

		if (koroneikiProductPurchaseKey != null) {
			setKoroneikiProductPurchaseKey(koroneikiProductPurchaseKey);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long orderEntryId = (Long)attributes.get("orderEntryId");

		if (orderEntryId != null) {
			setOrderEntryId(orderEntryId);
		}

		Long offeringEntryId = (Long)attributes.get("offeringEntryId");

		if (offeringEntryId != null) {
			setOfferingEntryId(offeringEntryId);
		}

		Long licenseEntryId = (Long)attributes.get("licenseEntryId");

		if (licenseEntryId != null) {
			setLicenseEntryId(licenseEntryId);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
		}

		String accountEntryName = (String)attributes.get("accountEntryName");

		if (accountEntryName != null) {
			setAccountEntryName(accountEntryName);
		}

		String licenseEntryName = (String)attributes.get("licenseEntryName");

		if (licenseEntryName != null) {
			setLicenseEntryName(licenseEntryName);
		}

		String licenseEntryType = (String)attributes.get("licenseEntryType");

		if (licenseEntryType != null) {
			setLicenseEntryType(licenseEntryType);
		}

		Integer licenseVersion = (Integer)attributes.get("licenseVersion");

		if (licenseVersion != null) {
			setLicenseVersion(licenseVersion);
		}

		String productEntryName = (String)attributes.get("productEntryName");

		if (productEntryName != null) {
			setProductEntryName(productEntryName);
		}

		String productId = (String)attributes.get("productId");

		if (productId != null) {
			setProductId(productId);
		}

		Integer productVersion = (Integer)attributes.get("productVersion");

		if (productVersion != null) {
			setProductVersion(productVersion);
		}

		String productVersionLabel = (String)attributes.get(
			"productVersionLabel");

		if (productVersionLabel != null) {
			setProductVersionLabel(productVersionLabel);
		}

		Long clusterId = (Long)attributes.get("clusterId");

		if (clusterId != null) {
			setClusterId(clusterId);
		}

		String owner = (String)attributes.get("owner");

		if (owner != null) {
			setOwner(owner);
		}

		Integer maxServers = (Integer)attributes.get("maxServers");

		if (maxServers != null) {
			setMaxServers(maxServers);
		}

		Long maxConcurrentUsers = (Long)attributes.get("maxConcurrentUsers");

		if (maxConcurrentUsers != null) {
			setMaxConcurrentUsers(maxConcurrentUsers);
		}

		Long maxUsers = (Long)attributes.get("maxUsers");

		if (maxUsers != null) {
			setMaxUsers(maxUsers);
		}

		Integer maxHttpSessions = (Integer)attributes.get("maxHttpSessions");

		if (maxHttpSessions != null) {
			setMaxHttpSessions(maxHttpSessions);
		}

		Integer sizing = (Integer)attributes.get("sizing");

		if (sizing != null) {
			setSizing(sizing);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String hostName = (String)attributes.get("hostName");

		if (hostName != null) {
			setHostName(hostName);
		}

		String ipAddresses = (String)attributes.get("ipAddresses");

		if (ipAddresses != null) {
			setIpAddresses(ipAddresses);
		}

		String macAddresses = (String)attributes.get("macAddresses");

		if (macAddresses != null) {
			setMacAddresses(macAddresses);
		}

		String serverId = (String)attributes.get("serverId");

		if (serverId != null) {
			setServerId(serverId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}

		String additionalInfo = (String)attributes.get("additionalInfo");

		if (additionalInfo != null) {
			setAdditionalInfo(additionalInfo);
		}

		Boolean complimentary = (Boolean)attributes.get("complimentary");

		if (complimentary != null) {
			setComplimentary(complimentary);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public boolean canRenew()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKey.canRenew();
	}

	@Override
	public Object clone() {
		return new LicenseKeyWrapper((LicenseKey)_licenseKey.clone());
	}

	@Override
	public int compareTo(LicenseKey licenseKey) {
		return _licenseKey.compareTo(licenseKey);
	}

	@Override
	public com.liferay.osb.customer.admin.model.AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKey.getAccountEntry();
	}

	/**
	 * Returns the account entry ID of this license key.
	 *
	 * @return the account entry ID of this license key
	 */
	@Override
	public long getAccountEntryId() {
		return _licenseKey.getAccountEntryId();
	}

	/**
	 * Returns the account entry name of this license key.
	 *
	 * @return the account entry name of this license key
	 */
	@Override
	public String getAccountEntryName() {
		return _licenseKey.getAccountEntryName();
	}

	/**
	 * Returns the active of this license key.
	 *
	 * @return the active of this license key
	 */
	@Override
	public boolean getActive() {
		return _licenseKey.getActive();
	}

	/**
	 * Returns the additional info of this license key.
	 *
	 * @return the additional info of this license key
	 */
	@Override
	public String getAdditionalInfo() {
		return _licenseKey.getAdditionalInfo();
	}

	/**
	 * Returns the asset receipt license uuid of this license key.
	 *
	 * @return the asset receipt license uuid of this license key
	 */
	@Override
	public String getAssetReceiptLicenseUuid() {
		return _licenseKey.getAssetReceiptLicenseUuid();
	}

	/**
	 * Returns the cluster ID of this license key.
	 *
	 * @return the cluster ID of this license key
	 */
	@Override
	public long getClusterId() {
		return _licenseKey.getClusterId();
	}

	/**
	 * Returns the complimentary of this license key.
	 *
	 * @return the complimentary of this license key
	 */
	@Override
	public boolean getComplimentary() {
		return _licenseKey.getComplimentary();
	}

	/**
	 * Returns the create date of this license key.
	 *
	 * @return the create date of this license key
	 */
	@Override
	public Date getCreateDate() {
		return _licenseKey.getCreateDate();
	}

	/**
	 * Returns the description of this license key.
	 *
	 * @return the description of this license key
	 */
	@Override
	public String getDescription() {
		return _licenseKey.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _licenseKey.getExpandoBridge();
	}

	/**
	 * Returns the expiration date of this license key.
	 *
	 * @return the expiration date of this license key
	 */
	@Override
	public Date getExpirationDate() {
		return _licenseKey.getExpirationDate();
	}

	/**
	 * Returns the host name of this license key.
	 *
	 * @return the host name of this license key
	 */
	@Override
	public String getHostName() {
		return _licenseKey.getHostName();
	}

	/**
	 * Returns the ip addresses of this license key.
	 *
	 * @return the ip addresses of this license key
	 */
	@Override
	public String getIpAddresses() {
		return _licenseKey.getIpAddresses();
	}

	/**
	 * Returns the key of this license key.
	 *
	 * @return the key of this license key
	 */
	@Override
	public String getKey() {
		return _licenseKey.getKey();
	}

	/**
	 * Returns the koroneiki account key of this license key.
	 *
	 * @return the koroneiki account key of this license key
	 */
	@Override
	public String getKoroneikiAccountKey() {
		return _licenseKey.getKoroneikiAccountKey();
	}

	/**
	 * Returns the koroneiki product purchase key of this license key.
	 *
	 * @return the koroneiki product purchase key of this license key
	 */
	@Override
	public String getKoroneikiProductPurchaseKey() {
		return _licenseKey.getKoroneikiProductPurchaseKey();
	}

	@Override
	public com.liferay.osb.customer.admin.model.LicenseEntry getLicenseEntry()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKey.getLicenseEntry();
	}

	/**
	 * Returns the license entry ID of this license key.
	 *
	 * @return the license entry ID of this license key
	 */
	@Override
	public long getLicenseEntryId() {
		return _licenseKey.getLicenseEntryId();
	}

	/**
	 * Returns the license entry name of this license key.
	 *
	 * @return the license entry name of this license key
	 */
	@Override
	public String getLicenseEntryName() {
		return _licenseKey.getLicenseEntryName();
	}

	/**
	 * Returns the license entry type of this license key.
	 *
	 * @return the license entry type of this license key
	 */
	@Override
	public String getLicenseEntryType() {
		return _licenseKey.getLicenseEntryType();
	}

	/**
	 * Returns the license key ID of this license key.
	 *
	 * @return the license key ID of this license key
	 */
	@Override
	public long getLicenseKeyId() {
		return _licenseKey.getLicenseKeyId();
	}

	@Override
	public LicenseKeySet getLicenseKeySet()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKey.getLicenseKeySet();
	}

	/**
	 * Returns the license key set ID of this license key.
	 *
	 * @return the license key set ID of this license key
	 */
	@Override
	public long getLicenseKeySetId() {
		return _licenseKey.getLicenseKeySetId();
	}

	/**
	 * Returns the license version of this license key.
	 *
	 * @return the license version of this license key
	 */
	@Override
	public int getLicenseVersion() {
		return _licenseKey.getLicenseVersion();
	}

	/**
	 * Returns the mac addresses of this license key.
	 *
	 * @return the mac addresses of this license key
	 */
	@Override
	public String getMacAddresses() {
		return _licenseKey.getMacAddresses();
	}

	/**
	 * Returns the max concurrent users of this license key.
	 *
	 * @return the max concurrent users of this license key
	 */
	@Override
	public long getMaxConcurrentUsers() {
		return _licenseKey.getMaxConcurrentUsers();
	}

	/**
	 * Returns the max http sessions of this license key.
	 *
	 * @return the max http sessions of this license key
	 */
	@Override
	public int getMaxHttpSessions() {
		return _licenseKey.getMaxHttpSessions();
	}

	/**
	 * Returns the max servers of this license key.
	 *
	 * @return the max servers of this license key
	 */
	@Override
	public int getMaxServers() {
		return _licenseKey.getMaxServers();
	}

	/**
	 * Returns the max users of this license key.
	 *
	 * @return the max users of this license key
	 */
	@Override
	public long getMaxUsers() {
		return _licenseKey.getMaxUsers();
	}

	/**
	 * Returns the modified date of this license key.
	 *
	 * @return the modified date of this license key
	 */
	@Override
	public Date getModifiedDate() {
		return _licenseKey.getModifiedDate();
	}

	/**
	 * Returns the modified user ID of this license key.
	 *
	 * @return the modified user ID of this license key
	 */
	@Override
	public long getModifiedUserId() {
		return _licenseKey.getModifiedUserId();
	}

	/**
	 * Returns the modified user name of this license key.
	 *
	 * @return the modified user name of this license key
	 */
	@Override
	public String getModifiedUserName() {
		return _licenseKey.getModifiedUserName();
	}

	/**
	 * Returns the modified user uuid of this license key.
	 *
	 * @return the modified user uuid of this license key
	 */
	@Override
	public String getModifiedUserUuid() {
		return _licenseKey.getModifiedUserUuid();
	}

	/**
	 * Returns the offering entry ID of this license key.
	 *
	 * @return the offering entry ID of this license key
	 */
	@Override
	public long getOfferingEntryId() {
		return _licenseKey.getOfferingEntryId();
	}

	/**
	 * Returns the order entry ID of this license key.
	 *
	 * @return the order entry ID of this license key
	 */
	@Override
	public long getOrderEntryId() {
		return _licenseKey.getOrderEntryId();
	}

	/**
	 * Returns the owner of this license key.
	 *
	 * @return the owner of this license key
	 */
	@Override
	public String getOwner() {
		return _licenseKey.getOwner();
	}

	/**
	 * Returns the primary key of this license key.
	 *
	 * @return the primary key of this license key
	 */
	@Override
	public long getPrimaryKey() {
		return _licenseKey.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _licenseKey.getPrimaryKeyObj();
	}

	/**
	 * Returns the product entry ID of this license key.
	 *
	 * @return the product entry ID of this license key
	 */
	@Override
	public long getProductEntryId() {
		return _licenseKey.getProductEntryId();
	}

	/**
	 * Returns the product entry name of this license key.
	 *
	 * @return the product entry name of this license key
	 */
	@Override
	public String getProductEntryName() {
		return _licenseKey.getProductEntryName();
	}

	/**
	 * Returns the product ID of this license key.
	 *
	 * @return the product ID of this license key
	 */
	@Override
	public String getProductId() {
		return _licenseKey.getProductId();
	}

	/**
	 * Returns the product version of this license key.
	 *
	 * @return the product version of this license key
	 */
	@Override
	public int getProductVersion() {
		return _licenseKey.getProductVersion();
	}

	/**
	 * Returns the product version label of this license key.
	 *
	 * @return the product version label of this license key
	 */
	@Override
	public String getProductVersionLabel() {
		return _licenseKey.getProductVersionLabel();
	}

	/**
	 * Returns the server ID of this license key.
	 *
	 * @return the server ID of this license key
	 */
	@Override
	public String getServerId() {
		return _licenseKey.getServerId();
	}

	/**
	 * Returns the sizing of this license key.
	 *
	 * @return the sizing of this license key
	 */
	@Override
	public int getSizing() {
		return _licenseKey.getSizing();
	}

	/**
	 * Returns the start date of this license key.
	 *
	 * @return the start date of this license key
	 */
	@Override
	public Date getStartDate() {
		return _licenseKey.getStartDate();
	}

	/**
	 * Returns the support response ID of this license key.
	 *
	 * @return the support response ID of this license key
	 */
	@Override
	public long getSupportResponseId() {
		return _licenseKey.getSupportResponseId();
	}

	/**
	 * Returns the user ID of this license key.
	 *
	 * @return the user ID of this license key
	 */
	@Override
	public long getUserId() {
		return _licenseKey.getUserId();
	}

	/**
	 * Returns the user name of this license key.
	 *
	 * @return the user name of this license key
	 */
	@Override
	public String getUserName() {
		return _licenseKey.getUserName();
	}

	/**
	 * Returns the user uuid of this license key.
	 *
	 * @return the user uuid of this license key
	 */
	@Override
	public String getUserUuid() {
		return _licenseKey.getUserUuid();
	}

	/**
	 * Returns the uuid of this license key.
	 *
	 * @return the uuid of this license key
	 */
	@Override
	public String getUuid() {
		return _licenseKey.getUuid();
	}

	@Override
	public int hashCode() {
		return _licenseKey.hashCode();
	}

	/**
	 * Returns <code>true</code> if this license key is active.
	 *
	 * @return <code>true</code> if this license key is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isActive() {
		return _licenseKey.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _licenseKey.isCachedModel();
	}

	/**
	 * Returns <code>true</code> if this license key is complimentary.
	 *
	 * @return <code>true</code> if this license key is complimentary; <code>false</code> otherwise
	 */
	@Override
	public boolean isComplimentary() {
		return _licenseKey.isComplimentary();
	}

	@Override
	public boolean isEscapedModel() {
		return _licenseKey.isEscapedModel();
	}

	@Override
	public boolean isExpired() {
		return _licenseKey.isExpired();
	}

	@Override
	public boolean isNew() {
		return _licenseKey.isNew();
	}

	@Override
	public void persist() {
		_licenseKey.persist();
	}

	/**
	 * Sets the account entry ID of this license key.
	 *
	 * @param accountEntryId the account entry ID of this license key
	 */
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_licenseKey.setAccountEntryId(accountEntryId);
	}

	/**
	 * Sets the account entry name of this license key.
	 *
	 * @param accountEntryName the account entry name of this license key
	 */
	@Override
	public void setAccountEntryName(String accountEntryName) {
		_licenseKey.setAccountEntryName(accountEntryName);
	}

	/**
	 * Sets whether this license key is active.
	 *
	 * @param active the active of this license key
	 */
	@Override
	public void setActive(boolean active) {
		_licenseKey.setActive(active);
	}

	/**
	 * Sets the additional info of this license key.
	 *
	 * @param additionalInfo the additional info of this license key
	 */
	@Override
	public void setAdditionalInfo(String additionalInfo) {
		_licenseKey.setAdditionalInfo(additionalInfo);
	}

	/**
	 * Sets the asset receipt license uuid of this license key.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid of this license key
	 */
	@Override
	public void setAssetReceiptLicenseUuid(String assetReceiptLicenseUuid) {
		_licenseKey.setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_licenseKey.setCachedModel(cachedModel);
	}

	/**
	 * Sets the cluster ID of this license key.
	 *
	 * @param clusterId the cluster ID of this license key
	 */
	@Override
	public void setClusterId(long clusterId) {
		_licenseKey.setClusterId(clusterId);
	}

	/**
	 * Sets whether this license key is complimentary.
	 *
	 * @param complimentary the complimentary of this license key
	 */
	@Override
	public void setComplimentary(boolean complimentary) {
		_licenseKey.setComplimentary(complimentary);
	}

	/**
	 * Sets the create date of this license key.
	 *
	 * @param createDate the create date of this license key
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_licenseKey.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this license key.
	 *
	 * @param description the description of this license key
	 */
	@Override
	public void setDescription(String description) {
		_licenseKey.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_licenseKey.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_licenseKey.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_licenseKey.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the expiration date of this license key.
	 *
	 * @param expirationDate the expiration date of this license key
	 */
	@Override
	public void setExpirationDate(Date expirationDate) {
		_licenseKey.setExpirationDate(expirationDate);
	}

	/**
	 * Sets the host name of this license key.
	 *
	 * @param hostName the host name of this license key
	 */
	@Override
	public void setHostName(String hostName) {
		_licenseKey.setHostName(hostName);
	}

	/**
	 * Sets the ip addresses of this license key.
	 *
	 * @param ipAddresses the ip addresses of this license key
	 */
	@Override
	public void setIpAddresses(String ipAddresses) {
		_licenseKey.setIpAddresses(ipAddresses);
	}

	/**
	 * Sets the key of this license key.
	 *
	 * @param key the key of this license key
	 */
	@Override
	public void setKey(String key) {
		_licenseKey.setKey(key);
	}

	/**
	 * Sets the koroneiki account key of this license key.
	 *
	 * @param koroneikiAccountKey the koroneiki account key of this license key
	 */
	@Override
	public void setKoroneikiAccountKey(String koroneikiAccountKey) {
		_licenseKey.setKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	 * Sets the koroneiki product purchase key of this license key.
	 *
	 * @param koroneikiProductPurchaseKey the koroneiki product purchase key of this license key
	 */
	@Override
	public void setKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		_licenseKey.setKoroneikiProductPurchaseKey(koroneikiProductPurchaseKey);
	}

	/**
	 * Sets the license entry ID of this license key.
	 *
	 * @param licenseEntryId the license entry ID of this license key
	 */
	@Override
	public void setLicenseEntryId(long licenseEntryId) {
		_licenseKey.setLicenseEntryId(licenseEntryId);
	}

	/**
	 * Sets the license entry name of this license key.
	 *
	 * @param licenseEntryName the license entry name of this license key
	 */
	@Override
	public void setLicenseEntryName(String licenseEntryName) {
		_licenseKey.setLicenseEntryName(licenseEntryName);
	}

	/**
	 * Sets the license entry type of this license key.
	 *
	 * @param licenseEntryType the license entry type of this license key
	 */
	@Override
	public void setLicenseEntryType(String licenseEntryType) {
		_licenseKey.setLicenseEntryType(licenseEntryType);
	}

	/**
	 * Sets the license key ID of this license key.
	 *
	 * @param licenseKeyId the license key ID of this license key
	 */
	@Override
	public void setLicenseKeyId(long licenseKeyId) {
		_licenseKey.setLicenseKeyId(licenseKeyId);
	}

	/**
	 * Sets the license key set ID of this license key.
	 *
	 * @param licenseKeySetId the license key set ID of this license key
	 */
	@Override
	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKey.setLicenseKeySetId(licenseKeySetId);
	}

	/**
	 * Sets the license version of this license key.
	 *
	 * @param licenseVersion the license version of this license key
	 */
	@Override
	public void setLicenseVersion(int licenseVersion) {
		_licenseKey.setLicenseVersion(licenseVersion);
	}

	/**
	 * Sets the mac addresses of this license key.
	 *
	 * @param macAddresses the mac addresses of this license key
	 */
	@Override
	public void setMacAddresses(String macAddresses) {
		_licenseKey.setMacAddresses(macAddresses);
	}

	/**
	 * Sets the max concurrent users of this license key.
	 *
	 * @param maxConcurrentUsers the max concurrent users of this license key
	 */
	@Override
	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_licenseKey.setMaxConcurrentUsers(maxConcurrentUsers);
	}

	/**
	 * Sets the max http sessions of this license key.
	 *
	 * @param maxHttpSessions the max http sessions of this license key
	 */
	@Override
	public void setMaxHttpSessions(int maxHttpSessions) {
		_licenseKey.setMaxHttpSessions(maxHttpSessions);
	}

	/**
	 * Sets the max servers of this license key.
	 *
	 * @param maxServers the max servers of this license key
	 */
	@Override
	public void setMaxServers(int maxServers) {
		_licenseKey.setMaxServers(maxServers);
	}

	/**
	 * Sets the max users of this license key.
	 *
	 * @param maxUsers the max users of this license key
	 */
	@Override
	public void setMaxUsers(long maxUsers) {
		_licenseKey.setMaxUsers(maxUsers);
	}

	/**
	 * Sets the modified date of this license key.
	 *
	 * @param modifiedDate the modified date of this license key
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_licenseKey.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the modified user ID of this license key.
	 *
	 * @param modifiedUserId the modified user ID of this license key
	 */
	@Override
	public void setModifiedUserId(long modifiedUserId) {
		_licenseKey.setModifiedUserId(modifiedUserId);
	}

	/**
	 * Sets the modified user name of this license key.
	 *
	 * @param modifiedUserName the modified user name of this license key
	 */
	@Override
	public void setModifiedUserName(String modifiedUserName) {
		_licenseKey.setModifiedUserName(modifiedUserName);
	}

	/**
	 * Sets the modified user uuid of this license key.
	 *
	 * @param modifiedUserUuid the modified user uuid of this license key
	 */
	@Override
	public void setModifiedUserUuid(String modifiedUserUuid) {
		_licenseKey.setModifiedUserUuid(modifiedUserUuid);
	}

	@Override
	public void setNew(boolean n) {
		_licenseKey.setNew(n);
	}

	/**
	 * Sets the offering entry ID of this license key.
	 *
	 * @param offeringEntryId the offering entry ID of this license key
	 */
	@Override
	public void setOfferingEntryId(long offeringEntryId) {
		_licenseKey.setOfferingEntryId(offeringEntryId);
	}

	/**
	 * Sets the order entry ID of this license key.
	 *
	 * @param orderEntryId the order entry ID of this license key
	 */
	@Override
	public void setOrderEntryId(long orderEntryId) {
		_licenseKey.setOrderEntryId(orderEntryId);
	}

	/**
	 * Sets the owner of this license key.
	 *
	 * @param owner the owner of this license key
	 */
	@Override
	public void setOwner(String owner) {
		_licenseKey.setOwner(owner);
	}

	/**
	 * Sets the primary key of this license key.
	 *
	 * @param primaryKey the primary key of this license key
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_licenseKey.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_licenseKey.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the product entry ID of this license key.
	 *
	 * @param productEntryId the product entry ID of this license key
	 */
	@Override
	public void setProductEntryId(long productEntryId) {
		_licenseKey.setProductEntryId(productEntryId);
	}

	/**
	 * Sets the product entry name of this license key.
	 *
	 * @param productEntryName the product entry name of this license key
	 */
	@Override
	public void setProductEntryName(String productEntryName) {
		_licenseKey.setProductEntryName(productEntryName);
	}

	/**
	 * Sets the product ID of this license key.
	 *
	 * @param productId the product ID of this license key
	 */
	@Override
	public void setProductId(String productId) {
		_licenseKey.setProductId(productId);
	}

	/**
	 * Sets the product version of this license key.
	 *
	 * @param productVersion the product version of this license key
	 */
	@Override
	public void setProductVersion(int productVersion) {
		_licenseKey.setProductVersion(productVersion);
	}

	/**
	 * Sets the product version label of this license key.
	 *
	 * @param productVersionLabel the product version label of this license key
	 */
	@Override
	public void setProductVersionLabel(String productVersionLabel) {
		_licenseKey.setProductVersionLabel(productVersionLabel);
	}

	/**
	 * Sets the server ID of this license key.
	 *
	 * @param serverId the server ID of this license key
	 */
	@Override
	public void setServerId(String serverId) {
		_licenseKey.setServerId(serverId);
	}

	/**
	 * Sets the sizing of this license key.
	 *
	 * @param sizing the sizing of this license key
	 */
	@Override
	public void setSizing(int sizing) {
		_licenseKey.setSizing(sizing);
	}

	/**
	 * Sets the start date of this license key.
	 *
	 * @param startDate the start date of this license key
	 */
	@Override
	public void setStartDate(Date startDate) {
		_licenseKey.setStartDate(startDate);
	}

	/**
	 * Sets the support response ID of this license key.
	 *
	 * @param supportResponseId the support response ID of this license key
	 */
	@Override
	public void setSupportResponseId(long supportResponseId) {
		_licenseKey.setSupportResponseId(supportResponseId);
	}

	/**
	 * Sets the user ID of this license key.
	 *
	 * @param userId the user ID of this license key
	 */
	@Override
	public void setUserId(long userId) {
		_licenseKey.setUserId(userId);
	}

	/**
	 * Sets the user name of this license key.
	 *
	 * @param userName the user name of this license key
	 */
	@Override
	public void setUserName(String userName) {
		_licenseKey.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this license key.
	 *
	 * @param userUuid the user uuid of this license key
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_licenseKey.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this license key.
	 *
	 * @param uuid the uuid of this license key
	 */
	@Override
	public void setUuid(String uuid) {
		_licenseKey.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LicenseKey>
		toCacheModel() {

		return _licenseKey.toCacheModel();
	}

	@Override
	public LicenseKey toEscapedModel() {
		return new LicenseKeyWrapper(_licenseKey.toEscapedModel());
	}

	@Override
	public String toString() {
		return _licenseKey.toString();
	}

	@Override
	public LicenseKey toUnescapedModel() {
		return new LicenseKeyWrapper(_licenseKey.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _licenseKey.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKeyWrapper)) {
			return false;
		}

		LicenseKeyWrapper licenseKeyWrapper = (LicenseKeyWrapper)object;

		if (Objects.equals(_licenseKey, licenseKeyWrapper._licenseKey)) {
			return true;
		}

		return false;
	}

	@Override
	public LicenseKey getWrappedModel() {
		return _licenseKey;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _licenseKey.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _licenseKey.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_licenseKey.resetOriginalValues();
	}

	private final LicenseKey _licenseKey;

}
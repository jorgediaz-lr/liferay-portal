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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.license.service.http.LicenseKeyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeySoap implements Serializable {

	public static LicenseKeySoap toSoapModel(LicenseKey model) {
		LicenseKeySoap soapModel = new LicenseKeySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLicenseKeyId(model.getLicenseKeyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLicenseKeySetId(model.getLicenseKeySetId());
		soapModel.setAssetReceiptLicenseUuid(
			model.getAssetReceiptLicenseUuid());
		soapModel.setKoroneikiAccountKey(model.getKoroneikiAccountKey());
		soapModel.setKoroneikiProductPurchaseKey(
			model.getKoroneikiProductPurchaseKey());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setOrderEntryId(model.getOrderEntryId());
		soapModel.setOfferingEntryId(model.getOfferingEntryId());
		soapModel.setLicenseEntryId(model.getLicenseEntryId());
		soapModel.setProductEntryId(model.getProductEntryId());
		soapModel.setSupportResponseId(model.getSupportResponseId());
		soapModel.setAccountEntryName(model.getAccountEntryName());
		soapModel.setLicenseEntryName(model.getLicenseEntryName());
		soapModel.setLicenseEntryType(model.getLicenseEntryType());
		soapModel.setLicenseVersion(model.getLicenseVersion());
		soapModel.setProductEntryName(model.getProductEntryName());
		soapModel.setProductId(model.getProductId());
		soapModel.setProductVersion(model.getProductVersion());
		soapModel.setProductVersionLabel(model.getProductVersionLabel());
		soapModel.setClusterId(model.getClusterId());
		soapModel.setOwner(model.getOwner());
		soapModel.setMaxServers(model.getMaxServers());
		soapModel.setMaxConcurrentUsers(model.getMaxConcurrentUsers());
		soapModel.setMaxUsers(model.getMaxUsers());
		soapModel.setMaxHttpSessions(model.getMaxHttpSessions());
		soapModel.setSizing(model.getSizing());
		soapModel.setDescription(model.getDescription());
		soapModel.setHostName(model.getHostName());
		soapModel.setIpAddresses(model.getIpAddresses());
		soapModel.setMacAddresses(model.getMacAddresses());
		soapModel.setServerId(model.getServerId());
		soapModel.setKey(model.getKey());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setAdditionalInfo(model.getAdditionalInfo());
		soapModel.setComplimentary(model.isComplimentary());
		soapModel.setActive(model.isActive());

		return soapModel;
	}

	public static LicenseKeySoap[] toSoapModels(LicenseKey[] models) {
		LicenseKeySoap[] soapModels = new LicenseKeySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LicenseKeySoap[][] toSoapModels(LicenseKey[][] models) {
		LicenseKeySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LicenseKeySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LicenseKeySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LicenseKeySoap[] toSoapModels(List<LicenseKey> models) {
		List<LicenseKeySoap> soapModels = new ArrayList<LicenseKeySoap>(
			models.size());

		for (LicenseKey model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LicenseKeySoap[soapModels.size()]);
	}

	public LicenseKeySoap() {
	}

	public long getPrimaryKey() {
		return _licenseKeyId;
	}

	public void setPrimaryKey(long pk) {
		setLicenseKeyId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLicenseKeyId() {
		return _licenseKeyId;
	}

	public void setLicenseKeyId(long licenseKeyId) {
		_licenseKeyId = licenseKeyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;
	}

	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getLicenseKeySetId() {
		return _licenseKeySetId;
	}

	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKeySetId = licenseKeySetId;
	}

	public String getAssetReceiptLicenseUuid() {
		return _assetReceiptLicenseUuid;
	}

	public void setAssetReceiptLicenseUuid(String assetReceiptLicenseUuid) {
		_assetReceiptLicenseUuid = assetReceiptLicenseUuid;
	}

	public String getKoroneikiAccountKey() {
		return _koroneikiAccountKey;
	}

	public void setKoroneikiAccountKey(String koroneikiAccountKey) {
		_koroneikiAccountKey = koroneikiAccountKey;
	}

	public String getKoroneikiProductPurchaseKey() {
		return _koroneikiProductPurchaseKey;
	}

	public void setKoroneikiProductPurchaseKey(
		String koroneikiProductPurchaseKey) {

		_koroneikiProductPurchaseKey = koroneikiProductPurchaseKey;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getOrderEntryId() {
		return _orderEntryId;
	}

	public void setOrderEntryId(long orderEntryId) {
		_orderEntryId = orderEntryId;
	}

	public long getOfferingEntryId() {
		return _offeringEntryId;
	}

	public void setOfferingEntryId(long offeringEntryId) {
		_offeringEntryId = offeringEntryId;
	}

	public long getLicenseEntryId() {
		return _licenseEntryId;
	}

	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntryId = licenseEntryId;
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	public long getSupportResponseId() {
		return _supportResponseId;
	}

	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;
	}

	public String getAccountEntryName() {
		return _accountEntryName;
	}

	public void setAccountEntryName(String accountEntryName) {
		_accountEntryName = accountEntryName;
	}

	public String getLicenseEntryName() {
		return _licenseEntryName;
	}

	public void setLicenseEntryName(String licenseEntryName) {
		_licenseEntryName = licenseEntryName;
	}

	public String getLicenseEntryType() {
		return _licenseEntryType;
	}

	public void setLicenseEntryType(String licenseEntryType) {
		_licenseEntryType = licenseEntryType;
	}

	public int getLicenseVersion() {
		return _licenseVersion;
	}

	public void setLicenseVersion(int licenseVersion) {
		_licenseVersion = licenseVersion;
	}

	public String getProductEntryName() {
		return _productEntryName;
	}

	public void setProductEntryName(String productEntryName) {
		_productEntryName = productEntryName;
	}

	public String getProductId() {
		return _productId;
	}

	public void setProductId(String productId) {
		_productId = productId;
	}

	public int getProductVersion() {
		return _productVersion;
	}

	public void setProductVersion(int productVersion) {
		_productVersion = productVersion;
	}

	public String getProductVersionLabel() {
		return _productVersionLabel;
	}

	public void setProductVersionLabel(String productVersionLabel) {
		_productVersionLabel = productVersionLabel;
	}

	public long getClusterId() {
		return _clusterId;
	}

	public void setClusterId(long clusterId) {
		_clusterId = clusterId;
	}

	public String getOwner() {
		return _owner;
	}

	public void setOwner(String owner) {
		_owner = owner;
	}

	public int getMaxServers() {
		return _maxServers;
	}

	public void setMaxServers(int maxServers) {
		_maxServers = maxServers;
	}

	public long getMaxConcurrentUsers() {
		return _maxConcurrentUsers;
	}

	public void setMaxConcurrentUsers(long maxConcurrentUsers) {
		_maxConcurrentUsers = maxConcurrentUsers;
	}

	public long getMaxUsers() {
		return _maxUsers;
	}

	public void setMaxUsers(long maxUsers) {
		_maxUsers = maxUsers;
	}

	public int getMaxHttpSessions() {
		return _maxHttpSessions;
	}

	public void setMaxHttpSessions(int maxHttpSessions) {
		_maxHttpSessions = maxHttpSessions;
	}

	public int getSizing() {
		return _sizing;
	}

	public void setSizing(int sizing) {
		_sizing = sizing;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getHostName() {
		return _hostName;
	}

	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	public String getIpAddresses() {
		return _ipAddresses;
	}

	public void setIpAddresses(String ipAddresses) {
		_ipAddresses = ipAddresses;
	}

	public String getMacAddresses() {
		return _macAddresses;
	}

	public void setMacAddresses(String macAddresses) {
		_macAddresses = macAddresses;
	}

	public String getServerId() {
		return _serverId;
	}

	public void setServerId(String serverId) {
		_serverId = serverId;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public String getAdditionalInfo() {
		return _additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		_additionalInfo = additionalInfo;
	}

	public boolean getComplimentary() {
		return _complimentary;
	}

	public boolean isComplimentary() {
		return _complimentary;
	}

	public void setComplimentary(boolean complimentary) {
		_complimentary = complimentary;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private String _uuid;
	private long _licenseKeyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _licenseKeySetId;
	private String _assetReceiptLicenseUuid;
	private String _koroneikiAccountKey;
	private String _koroneikiProductPurchaseKey;
	private long _accountEntryId;
	private long _orderEntryId;
	private long _offeringEntryId;
	private long _licenseEntryId;
	private long _productEntryId;
	private long _supportResponseId;
	private String _accountEntryName;
	private String _licenseEntryName;
	private String _licenseEntryType;
	private int _licenseVersion;
	private String _productEntryName;
	private String _productId;
	private int _productVersion;
	private String _productVersionLabel;
	private long _clusterId;
	private String _owner;
	private int _maxServers;
	private long _maxConcurrentUsers;
	private long _maxUsers;
	private int _maxHttpSessions;
	private int _sizing;
	private String _description;
	private String _hostName;
	private String _ipAddresses;
	private String _macAddresses;
	private String _serverId;
	private String _key;
	private Date _startDate;
	private Date _expirationDate;
	private String _additionalInfo;
	private boolean _complimentary;
	private boolean _active;

}
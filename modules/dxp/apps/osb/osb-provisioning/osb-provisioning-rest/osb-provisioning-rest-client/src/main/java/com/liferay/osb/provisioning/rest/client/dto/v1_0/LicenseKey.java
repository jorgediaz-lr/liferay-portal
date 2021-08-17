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

package com.liferay.osb.provisioning.rest.client.dto.v1_0;

import com.liferay.osb.provisioning.rest.client.function.UnsafeSupplier;
import com.liferay.osb.provisioning.rest.client.serdes.v1_0.LicenseKeySerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
public class LicenseKey implements Cloneable, Serializable {

	public static LicenseKey toDTO(String json) {
		return LicenseKeySerDes.toDTO(json);
	}

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public void setAccountKey(
		UnsafeSupplier<String, Exception> accountKeyUnsafeSupplier) {

		try {
			accountKey = accountKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String accountKey;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setAccountName(
		UnsafeSupplier<String, Exception> accountNameUnsafeSupplier) {

		try {
			accountName = accountNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String accountName;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setActive(
		UnsafeSupplier<Boolean, Exception> activeUnsafeSupplier) {

		try {
			active = activeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean active;

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setAdditionalInfo(
		UnsafeSupplier<String, Exception> additionalInfoUnsafeSupplier) {

		try {
			additionalInfo = additionalInfoUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String additionalInfo;

	public String getAssetReceiptLicenseUuid() {
		return assetReceiptLicenseUuid;
	}

	public void setAssetReceiptLicenseUuid(String assetReceiptLicenseUuid) {
		this.assetReceiptLicenseUuid = assetReceiptLicenseUuid;
	}

	public void setAssetReceiptLicenseUuid(
		UnsafeSupplier<String, Exception>
			assetReceiptLicenseUuidUnsafeSupplier) {

		try {
			assetReceiptLicenseUuid =
				assetReceiptLicenseUuidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String assetReceiptLicenseUuid;

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	public void setClusterId(
		UnsafeSupplier<Long, Exception> clusterIdUnsafeSupplier) {

		try {
			clusterId = clusterIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long clusterId;

	public Boolean getComplimentary() {
		return complimentary;
	}

	public void setComplimentary(Boolean complimentary) {
		this.complimentary = complimentary;
	}

	public void setComplimentary(
		UnsafeSupplier<Boolean, Exception> complimentaryUnsafeSupplier) {

		try {
			complimentary = complimentaryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean complimentary;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date createDate;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String description;

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setExpirationDate(
		UnsafeSupplier<Date, Exception> expirationDateUnsafeSupplier) {

		try {
			expirationDate = expirationDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date expirationDate;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setHostName(
		UnsafeSupplier<String, Exception> hostNameUnsafeSupplier) {

		try {
			hostName = hostNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String hostName;

	public String getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(String ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	public void setIpAddresses(
		UnsafeSupplier<String, Exception> ipAddressesUnsafeSupplier) {

		try {
			ipAddresses = ipAddressesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ipAddresses;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String key;

	public Long getLicenseEntryId() {
		return licenseEntryId;
	}

	public void setLicenseEntryId(Long licenseEntryId) {
		this.licenseEntryId = licenseEntryId;
	}

	public void setLicenseEntryId(
		UnsafeSupplier<Long, Exception> licenseEntryIdUnsafeSupplier) {

		try {
			licenseEntryId = licenseEntryIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long licenseEntryId;

	public String getLicenseEntryName() {
		return licenseEntryName;
	}

	public void setLicenseEntryName(String licenseEntryName) {
		this.licenseEntryName = licenseEntryName;
	}

	public void setLicenseEntryName(
		UnsafeSupplier<String, Exception> licenseEntryNameUnsafeSupplier) {

		try {
			licenseEntryName = licenseEntryNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String licenseEntryName;

	public String getLicenseEntryType() {
		return licenseEntryType;
	}

	public void setLicenseEntryType(String licenseEntryType) {
		this.licenseEntryType = licenseEntryType;
	}

	public void setLicenseEntryType(
		UnsafeSupplier<String, Exception> licenseEntryTypeUnsafeSupplier) {

		try {
			licenseEntryType = licenseEntryTypeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String licenseEntryType;

	public Long getLicenseKeyId() {
		return licenseKeyId;
	}

	public void setLicenseKeyId(Long licenseKeyId) {
		this.licenseKeyId = licenseKeyId;
	}

	public void setLicenseKeyId(
		UnsafeSupplier<Long, Exception> licenseKeyIdUnsafeSupplier) {

		try {
			licenseKeyId = licenseKeyIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long licenseKeyId;

	public Integer getLicenseVersion() {
		return licenseVersion;
	}

	public void setLicenseVersion(Integer licenseVersion) {
		this.licenseVersion = licenseVersion;
	}

	public void setLicenseVersion(
		UnsafeSupplier<Integer, Exception> licenseVersionUnsafeSupplier) {

		try {
			licenseVersion = licenseVersionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer licenseVersion;

	public String getMacAddresses() {
		return macAddresses;
	}

	public void setMacAddresses(String macAddresses) {
		this.macAddresses = macAddresses;
	}

	public void setMacAddresses(
		UnsafeSupplier<String, Exception> macAddressesUnsafeSupplier) {

		try {
			macAddresses = macAddressesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String macAddresses;

	public Integer getMaxClusterNodes() {
		return maxClusterNodes;
	}

	public void setMaxClusterNodes(Integer maxClusterNodes) {
		this.maxClusterNodes = maxClusterNodes;
	}

	public void setMaxClusterNodes(
		UnsafeSupplier<Integer, Exception> maxClusterNodesUnsafeSupplier) {

		try {
			maxClusterNodes = maxClusterNodesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer maxClusterNodes;

	public Long getMaxConcurrentUsers() {
		return maxConcurrentUsers;
	}

	public void setMaxConcurrentUsers(Long maxConcurrentUsers) {
		this.maxConcurrentUsers = maxConcurrentUsers;
	}

	public void setMaxConcurrentUsers(
		UnsafeSupplier<Long, Exception> maxConcurrentUsersUnsafeSupplier) {

		try {
			maxConcurrentUsers = maxConcurrentUsersUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long maxConcurrentUsers;

	public Integer getMaxHttpSessions() {
		return maxHttpSessions;
	}

	public void setMaxHttpSessions(Integer maxHttpSessions) {
		this.maxHttpSessions = maxHttpSessions;
	}

	public void setMaxHttpSessions(
		UnsafeSupplier<Integer, Exception> maxHttpSessionsUnsafeSupplier) {

		try {
			maxHttpSessions = maxHttpSessionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer maxHttpSessions;

	public Integer getMaxServers() {
		return maxServers;
	}

	public void setMaxServers(Integer maxServers) {
		this.maxServers = maxServers;
	}

	public void setMaxServers(
		UnsafeSupplier<Integer, Exception> maxServersUnsafeSupplier) {

		try {
			maxServers = maxServersUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Integer maxServers;

	public Long getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(Long maxUsers) {
		this.maxUsers = maxUsers;
	}

	public void setMaxUsers(
		UnsafeSupplier<Long, Exception> maxUsersUnsafeSupplier) {

		try {
			maxUsers = maxUsersUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long maxUsers;

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setModifiedDate(
		UnsafeSupplier<Date, Exception> modifiedDateUnsafeSupplier) {

		try {
			modifiedDate = modifiedDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date modifiedDate;

	public String getModifiedUserName() {
		return modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	public void setModifiedUserName(
		UnsafeSupplier<String, Exception> modifiedUserNameUnsafeSupplier) {

		try {
			modifiedUserName = modifiedUserNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String modifiedUserName;

	public String getModifiedUserUuid() {
		return modifiedUserUuid;
	}

	public void setModifiedUserUuid(String modifiedUserUuid) {
		this.modifiedUserUuid = modifiedUserUuid;
	}

	public void setModifiedUserUuid(
		UnsafeSupplier<String, Exception> modifiedUserUuidUnsafeSupplier) {

		try {
			modifiedUserUuid = modifiedUserUuidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String modifiedUserUuid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String name;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setOwner(
		UnsafeSupplier<String, Exception> ownerUnsafeSupplier) {

		try {
			owner = ownerUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String owner;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductId(
		UnsafeSupplier<String, Exception> productIdUnsafeSupplier) {

		try {
			productId = productIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productId;

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public void setProductKey(
		UnsafeSupplier<String, Exception> productKeyUnsafeSupplier) {

		try {
			productKey = productKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productKey;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductName(
		UnsafeSupplier<String, Exception> productNameUnsafeSupplier) {

		try {
			productName = productNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productName;

	public String getProductPurchaseKey() {
		return productPurchaseKey;
	}

	public void setProductPurchaseKey(String productPurchaseKey) {
		this.productPurchaseKey = productPurchaseKey;
	}

	public void setProductPurchaseKey(
		UnsafeSupplier<String, Exception> productPurchaseKeyUnsafeSupplier) {

		try {
			productPurchaseKey = productPurchaseKeyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productPurchaseKey;

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public void setProductVersion(
		UnsafeSupplier<String, Exception> productVersionUnsafeSupplier) {

		try {
			productVersion = productVersionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String productVersion;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public void setServerId(
		UnsafeSupplier<String, Exception> serverIdUnsafeSupplier) {

		try {
			serverId = serverIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String serverId;

	public String getSizing() {
		return sizing;
	}

	public void setSizing(String sizing) {
		this.sizing = sizing;
	}

	public void setSizing(
		UnsafeSupplier<String, Exception> sizingUnsafeSupplier) {

		try {
			sizing = sizingUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String sizing;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStartDate(
		UnsafeSupplier<Date, Exception> startDateUnsafeSupplier) {

		try {
			startDate = startDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date startDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserName(
		UnsafeSupplier<String, Exception> userNameUnsafeSupplier) {

		try {
			userName = userNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String userName;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public void setUserUuid(
		UnsafeSupplier<String, Exception> userUuidUnsafeSupplier) {

		try {
			userUuid = userUuidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String userUuid;

	@Override
	public LicenseKey clone() throws CloneNotSupportedException {
		return (LicenseKey)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKey)) {
			return false;
		}

		LicenseKey licenseKey = (LicenseKey)object;

		return Objects.equals(toString(), licenseKey.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return LicenseKeySerDes.toJSON(this);
	}

}
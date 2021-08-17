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

package com.liferay.osb.provisioning.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kyle Bischof
 * @generated
 */
@Generated("")
@GraphQLName(description = "Represents a license key.", value = "LicenseKey")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "LicenseKey")
public class LicenseKey implements Serializable {

	public static LicenseKey toDTO(String json) {
		return ObjectMapperUtil.readValue(LicenseKey.class, json);
	}

	@Schema(description = "The key of the license key's account.")
	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	@JsonIgnore
	public void setAccountKey(
		UnsafeSupplier<String, Exception> accountKeyUnsafeSupplier) {

		try {
			accountKey = accountKeyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The key of the license key's account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String accountKey;

	@Schema(description = "The name of the license key's account.")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@JsonIgnore
	public void setAccountName(
		UnsafeSupplier<String, Exception> accountNameUnsafeSupplier) {

		try {
			accountName = accountNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The name of the license key's account.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String accountName;

	@Schema(description = "If the license key is active or not.")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@JsonIgnore
	public void setActive(
		UnsafeSupplier<Boolean, Exception> activeUnsafeSupplier) {

		try {
			active = activeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "If the license key is active or not.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean active;

	@Schema(
		description = "Any additional info for the license key if applicable."
	)
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@JsonIgnore
	public void setAdditionalInfo(
		UnsafeSupplier<String, Exception> additionalInfoUnsafeSupplier) {

		try {
			additionalInfo = additionalInfoUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "Any additional info for the license key if applicable."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String additionalInfo;

	@Schema(description = "The uuid of the asset receipt of this license key.")
	public String getAssetReceiptLicenseUuid() {
		return assetReceiptLicenseUuid;
	}

	public void setAssetReceiptLicenseUuid(String assetReceiptLicenseUuid) {
		this.assetReceiptLicenseUuid = assetReceiptLicenseUuid;
	}

	@JsonIgnore
	public void setAssetReceiptLicenseUuid(
		UnsafeSupplier<String, Exception>
			assetReceiptLicenseUuidUnsafeSupplier) {

		try {
			assetReceiptLicenseUuid =
				assetReceiptLicenseUuidUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The uuid of the asset receipt of this license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String assetReceiptLicenseUuid;

	@Schema(
		description = "The id of the cluster of the license key if applicable."
	)
	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	@JsonIgnore
	public void setClusterId(
		UnsafeSupplier<Long, Exception> clusterIdUnsafeSupplier) {

		try {
			clusterId = clusterIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The id of the cluster of the license key if applicable."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long clusterId;

	@Schema(description = "If the license key is complimentary or not.")
	public Boolean getComplimentary() {
		return complimentary;
	}

	public void setComplimentary(Boolean complimentary) {
		this.complimentary = complimentary;
	}

	@JsonIgnore
	public void setComplimentary(
		UnsafeSupplier<Boolean, Exception> complimentaryUnsafeSupplier) {

		try {
			complimentary = complimentaryUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "If the license key is complimentary or not.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean complimentary;

	@Schema(description = "The create date of the license key.")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The create date of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Date createDate;

	@Schema(description = "The description of the license key.")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The description of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@Schema(description = "The date the license key expires.")
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonIgnore
	public void setExpirationDate(
		UnsafeSupplier<Date, Exception> expirationDateUnsafeSupplier) {

		try {
			expirationDate = expirationDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The date the license key expires.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date expirationDate;

	@Schema(description = "The host name of the license key.")
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@JsonIgnore
	public void setHostName(
		UnsafeSupplier<String, Exception> hostNameUnsafeSupplier) {

		try {
			hostName = hostNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The host name of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String hostName;

	@Schema(description = "The IP addresses of the license key.")
	public String getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(String ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	@JsonIgnore
	public void setIpAddresses(
		UnsafeSupplier<String, Exception> ipAddressesUnsafeSupplier) {

		try {
			ipAddresses = ipAddressesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The IP addresses of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String ipAddresses;

	@Schema(description = "The encrypted key of the license key.")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@JsonIgnore
	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The encrypted key of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String key;

	@Schema(description = "The id of the license entry of the license key.")
	public Long getLicenseEntryId() {
		return licenseEntryId;
	}

	public void setLicenseEntryId(Long licenseEntryId) {
		this.licenseEntryId = licenseEntryId;
	}

	@JsonIgnore
	public void setLicenseEntryId(
		UnsafeSupplier<Long, Exception> licenseEntryIdUnsafeSupplier) {

		try {
			licenseEntryId = licenseEntryIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The id of the license entry of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long licenseEntryId;

	@Schema(description = "The name of the license entry of the license key.")
	public String getLicenseEntryName() {
		return licenseEntryName;
	}

	public void setLicenseEntryName(String licenseEntryName) {
		this.licenseEntryName = licenseEntryName;
	}

	@JsonIgnore
	public void setLicenseEntryName(
		UnsafeSupplier<String, Exception> licenseEntryNameUnsafeSupplier) {

		try {
			licenseEntryName = licenseEntryNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The name of the license entry of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String licenseEntryName;

	@Schema(description = "The type of the license entry of the license key.")
	public String getLicenseEntryType() {
		return licenseEntryType;
	}

	public void setLicenseEntryType(String licenseEntryType) {
		this.licenseEntryType = licenseEntryType;
	}

	@JsonIgnore
	public void setLicenseEntryType(
		UnsafeSupplier<String, Exception> licenseEntryTypeUnsafeSupplier) {

		try {
			licenseEntryType = licenseEntryTypeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The type of the license entry of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String licenseEntryType;

	@Schema(description = "The id of this license key.")
	public Long getLicenseKeyId() {
		return licenseKeyId;
	}

	public void setLicenseKeyId(Long licenseKeyId) {
		this.licenseKeyId = licenseKeyId;
	}

	@JsonIgnore
	public void setLicenseKeyId(
		UnsafeSupplier<Long, Exception> licenseKeyIdUnsafeSupplier) {

		try {
			licenseKeyId = licenseKeyIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The id of this license key.")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long licenseKeyId;

	@Schema(description = "The version of the license key.")
	public Integer getLicenseVersion() {
		return licenseVersion;
	}

	public void setLicenseVersion(Integer licenseVersion) {
		this.licenseVersion = licenseVersion;
	}

	@JsonIgnore
	public void setLicenseVersion(
		UnsafeSupplier<Integer, Exception> licenseVersionUnsafeSupplier) {

		try {
			licenseVersion = licenseVersionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The version of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer licenseVersion;

	@Schema(description = "The MAC addresses of the license key.")
	public String getMacAddresses() {
		return macAddresses;
	}

	public void setMacAddresses(String macAddresses) {
		this.macAddresses = macAddresses;
	}

	@JsonIgnore
	public void setMacAddresses(
		UnsafeSupplier<String, Exception> macAddressesUnsafeSupplier) {

		try {
			macAddresses = macAddressesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The MAC addresses of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String macAddresses;

	@Schema(
		description = "The maximum number of cluster nodes of the license key if applicable."
	)
	public Integer getMaxClusterNodes() {
		return maxClusterNodes;
	}

	public void setMaxClusterNodes(Integer maxClusterNodes) {
		this.maxClusterNodes = maxClusterNodes;
	}

	@JsonIgnore
	public void setMaxClusterNodes(
		UnsafeSupplier<Integer, Exception> maxClusterNodesUnsafeSupplier) {

		try {
			maxClusterNodes = maxClusterNodesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The maximum number of cluster nodes of the license key if applicable."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer maxClusterNodes;

	@Schema(
		description = "The maximum number of concurrent users the license key can handle."
	)
	public Long getMaxConcurrentUsers() {
		return maxConcurrentUsers;
	}

	public void setMaxConcurrentUsers(Long maxConcurrentUsers) {
		this.maxConcurrentUsers = maxConcurrentUsers;
	}

	@JsonIgnore
	public void setMaxConcurrentUsers(
		UnsafeSupplier<Long, Exception> maxConcurrentUsersUnsafeSupplier) {

		try {
			maxConcurrentUsers = maxConcurrentUsersUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The maximum number of concurrent users the license key can handle."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long maxConcurrentUsers;

	@Schema(
		description = "The maximum number of http sessions the license key can handle."
	)
	public Integer getMaxHttpSessions() {
		return maxHttpSessions;
	}

	public void setMaxHttpSessions(Integer maxHttpSessions) {
		this.maxHttpSessions = maxHttpSessions;
	}

	@JsonIgnore
	public void setMaxHttpSessions(
		UnsafeSupplier<Integer, Exception> maxHttpSessionsUnsafeSupplier) {

		try {
			maxHttpSessions = maxHttpSessionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The maximum number of http sessions the license key can handle."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer maxHttpSessions;

	@Schema(description = "The maximum number of servers of the license key.")
	public Integer getMaxServers() {
		return maxServers;
	}

	public void setMaxServers(Integer maxServers) {
		this.maxServers = maxServers;
	}

	@JsonIgnore
	public void setMaxServers(
		UnsafeSupplier<Integer, Exception> maxServersUnsafeSupplier) {

		try {
			maxServers = maxServersUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The maximum number of servers of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Integer maxServers;

	@Schema(
		description = "The maximum number of users the license key can handle."
	)
	public Long getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(Long maxUsers) {
		this.maxUsers = maxUsers;
	}

	@JsonIgnore
	public void setMaxUsers(
		UnsafeSupplier<Long, Exception> maxUsersUnsafeSupplier) {

		try {
			maxUsers = maxUsersUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The maximum number of users the license key can handle."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long maxUsers;

	@Schema(description = "The last date this license key was modified.")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@JsonIgnore
	public void setModifiedDate(
		UnsafeSupplier<Date, Exception> modifiedDateUnsafeSupplier) {

		try {
			modifiedDate = modifiedDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The last date this license key was modified.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date modifiedDate;

	@Schema(
		description = "The name of the user who last modified this license key."
	)
	public String getModifiedUserName() {
		return modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	@JsonIgnore
	public void setModifiedUserName(
		UnsafeSupplier<String, Exception> modifiedUserNameUnsafeSupplier) {

		try {
			modifiedUserName = modifiedUserNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The name of the user who last modified this license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String modifiedUserName;

	@Schema(
		description = "The uuid of the user who last modified this license key."
	)
	public String getModifiedUserUuid() {
		return modifiedUserUuid;
	}

	public void setModifiedUserUuid(String modifiedUserUuid) {
		this.modifiedUserUuid = modifiedUserUuid;
	}

	@JsonIgnore
	public void setModifiedUserUuid(
		UnsafeSupplier<String, Exception> modifiedUserUuidUnsafeSupplier) {

		try {
			modifiedUserUuid = modifiedUserUuidUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The uuid of the user who last modified this license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String modifiedUserUuid;

	@Schema(description = "The name of the license key.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The name of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String name;

	@Schema(description = "The owner of the license key.")
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@JsonIgnore
	public void setOwner(
		UnsafeSupplier<String, Exception> ownerUnsafeSupplier) {

		try {
			owner = ownerUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The owner of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String owner;

	@Schema(description = "The id of the product of the license key.")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@JsonIgnore
	public void setProductId(
		UnsafeSupplier<String, Exception> productIdUnsafeSupplier) {

		try {
			productId = productIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The id of the product of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productId;

	@Schema(description = "The key of the license key's product.")
	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	@JsonIgnore
	public void setProductKey(
		UnsafeSupplier<String, Exception> productKeyUnsafeSupplier) {

		try {
			productKey = productKeyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The key of the license key's product.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productKey;

	@Schema(description = "The name of the product of the license key.")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@JsonIgnore
	public void setProductName(
		UnsafeSupplier<String, Exception> productNameUnsafeSupplier) {

		try {
			productName = productNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The name of the product of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productName;

	@Schema(
		description = "The key of the license key's product purchase if applicable."
	)
	public String getProductPurchaseKey() {
		return productPurchaseKey;
	}

	public void setProductPurchaseKey(String productPurchaseKey) {
		this.productPurchaseKey = productPurchaseKey;
	}

	@JsonIgnore
	public void setProductPurchaseKey(
		UnsafeSupplier<String, Exception> productPurchaseKeyUnsafeSupplier) {

		try {
			productPurchaseKey = productPurchaseKeyUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The key of the license key's product purchase if applicable."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productPurchaseKey;

	@Schema(description = "The version of the product of the license key.")
	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	@JsonIgnore
	public void setProductVersion(
		UnsafeSupplier<String, Exception> productVersionUnsafeSupplier) {

		try {
			productVersion = productVersionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The version of the product of the license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String productVersion;

	@Schema(
		description = "The id of the server of the license key if applicable."
	)
	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@JsonIgnore
	public void setServerId(
		UnsafeSupplier<String, Exception> serverIdUnsafeSupplier) {

		try {
			serverId = serverIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The id of the server of the license key if applicable."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String serverId;

	@Schema(description = "The sizing of the license key.")
	public String getSizing() {
		return sizing;
	}

	public void setSizing(String sizing) {
		this.sizing = sizing;
	}

	@JsonIgnore
	public void setSizing(
		UnsafeSupplier<String, Exception> sizingUnsafeSupplier) {

		try {
			sizing = sizingUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "The sizing of the license key.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String sizing;

	@Schema(description = "The date the license key can start being used.")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonIgnore
	public void setStartDate(
		UnsafeSupplier<Date, Exception> startDateUnsafeSupplier) {

		try {
			startDate = startDateUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The date the license key can start being used."
	)
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Date startDate;

	@Schema(description = "The name of the user who created this license key.")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public void setUserName(
		UnsafeSupplier<String, Exception> userNameUnsafeSupplier) {

		try {
			userName = userNameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The name of the user who created this license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String userName;

	@Schema(description = "The uuid of the user who created this license key.")
	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	@JsonIgnore
	public void setUserUuid(
		UnsafeSupplier<String, Exception> userUuidUnsafeSupplier) {

		try {
			userUuid = userUuidUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(
		description = "The uuid of the user who created this license key."
	)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String userUuid;

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
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (accountKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountKey\": ");

			sb.append("\"");

			sb.append(_escape(accountKey));

			sb.append("\"");
		}

		if (accountName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountName\": ");

			sb.append("\"");

			sb.append(_escape(accountName));

			sb.append("\"");
		}

		if (active != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(active);
		}

		if (additionalInfo != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"additionalInfo\": ");

			sb.append("\"");

			sb.append(_escape(additionalInfo));

			sb.append("\"");
		}

		if (assetReceiptLicenseUuid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assetReceiptLicenseUuid\": ");

			sb.append("\"");

			sb.append(_escape(assetReceiptLicenseUuid));

			sb.append("\"");
		}

		if (clusterId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"clusterId\": ");

			sb.append(clusterId);
		}

		if (complimentary != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"complimentary\": ");

			sb.append(complimentary);
		}

		if (createDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(createDate));

			sb.append("\"");
		}

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
		}

		if (expirationDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"expirationDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(expirationDate));

			sb.append("\"");
		}

		if (hostName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hostName\": ");

			sb.append("\"");

			sb.append(_escape(hostName));

			sb.append("\"");
		}

		if (ipAddresses != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ipAddresses\": ");

			sb.append("\"");

			sb.append(_escape(ipAddresses));

			sb.append("\"");
		}

		if (key != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(key));

			sb.append("\"");
		}

		if (licenseEntryId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryId\": ");

			sb.append(licenseEntryId);
		}

		if (licenseEntryName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryName\": ");

			sb.append("\"");

			sb.append(_escape(licenseEntryName));

			sb.append("\"");
		}

		if (licenseEntryType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseEntryType\": ");

			sb.append("\"");

			sb.append(_escape(licenseEntryType));

			sb.append("\"");
		}

		if (licenseKeyId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseKeyId\": ");

			sb.append(licenseKeyId);
		}

		if (licenseVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"licenseVersion\": ");

			sb.append(licenseVersion);
		}

		if (macAddresses != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"macAddresses\": ");

			sb.append("\"");

			sb.append(_escape(macAddresses));

			sb.append("\"");
		}

		if (maxClusterNodes != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxClusterNodes\": ");

			sb.append(maxClusterNodes);
		}

		if (maxConcurrentUsers != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxConcurrentUsers\": ");

			sb.append(maxConcurrentUsers);
		}

		if (maxHttpSessions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxHttpSessions\": ");

			sb.append(maxHttpSessions);
		}

		if (maxServers != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxServers\": ");

			sb.append(maxServers);
		}

		if (maxUsers != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"maxUsers\": ");

			sb.append(maxUsers);
		}

		if (modifiedDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(modifiedDate));

			sb.append("\"");
		}

		if (modifiedUserName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserName\": ");

			sb.append("\"");

			sb.append(_escape(modifiedUserName));

			sb.append("\"");
		}

		if (modifiedUserUuid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedUserUuid\": ");

			sb.append("\"");

			sb.append(_escape(modifiedUserUuid));

			sb.append("\"");
		}

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		if (owner != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"owner\": ");

			sb.append("\"");

			sb.append(_escape(owner));

			sb.append("\"");
		}

		if (productId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productId\": ");

			sb.append("\"");

			sb.append(_escape(productId));

			sb.append("\"");
		}

		if (productKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productKey\": ");

			sb.append("\"");

			sb.append(_escape(productKey));

			sb.append("\"");
		}

		if (productName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productName\": ");

			sb.append("\"");

			sb.append(_escape(productName));

			sb.append("\"");
		}

		if (productPurchaseKey != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productPurchaseKey\": ");

			sb.append("\"");

			sb.append(_escape(productPurchaseKey));

			sb.append("\"");
		}

		if (productVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"productVersion\": ");

			sb.append("\"");

			sb.append(_escape(productVersion));

			sb.append("\"");
		}

		if (serverId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"serverId\": ");

			sb.append("\"");

			sb.append(_escape(serverId));

			sb.append("\"");
		}

		if (sizing != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"sizing\": ");

			sb.append("\"");

			sb.append(_escape(sizing));

			sb.append("\"");
		}

		if (startDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(startDate));

			sb.append("\"");
		}

		if (userName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userName\": ");

			sb.append("\"");

			sb.append(_escape(userName));

			sb.append("\"");
		}

		if (userUuid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userUuid\": ");

			sb.append("\"");

			sb.append(_escape(userUuid));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}
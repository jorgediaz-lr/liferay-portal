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

package com.liferay.osb.customer.license.model.impl;

import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LicenseKey in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeyCacheModel
	implements CacheModel<LicenseKey>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKeyCacheModel)) {
			return false;
		}

		LicenseKeyCacheModel licenseKeyCacheModel =
			(LicenseKeyCacheModel)object;

		if (licenseKeyId == licenseKeyCacheModel.licenseKeyId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, licenseKeyId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(89);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", licenseKeyId=");
		sb.append(licenseKeyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedUserId=");
		sb.append(modifiedUserId);
		sb.append(", modifiedUserName=");
		sb.append(modifiedUserName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", licenseKeySetId=");
		sb.append(licenseKeySetId);
		sb.append(", assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);
		sb.append(", koroneikiAccountKey=");
		sb.append(koroneikiAccountKey);
		sb.append(", koroneikiProductPurchaseKey=");
		sb.append(koroneikiProductPurchaseKey);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", orderEntryId=");
		sb.append(orderEntryId);
		sb.append(", offeringEntryId=");
		sb.append(offeringEntryId);
		sb.append(", licenseEntryId=");
		sb.append(licenseEntryId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", supportResponseId=");
		sb.append(supportResponseId);
		sb.append(", accountEntryName=");
		sb.append(accountEntryName);
		sb.append(", licenseEntryName=");
		sb.append(licenseEntryName);
		sb.append(", licenseEntryType=");
		sb.append(licenseEntryType);
		sb.append(", licenseVersion=");
		sb.append(licenseVersion);
		sb.append(", productEntryName=");
		sb.append(productEntryName);
		sb.append(", productId=");
		sb.append(productId);
		sb.append(", productVersion=");
		sb.append(productVersion);
		sb.append(", productVersionLabel=");
		sb.append(productVersionLabel);
		sb.append(", clusterId=");
		sb.append(clusterId);
		sb.append(", owner=");
		sb.append(owner);
		sb.append(", maxServers=");
		sb.append(maxServers);
		sb.append(", maxConcurrentUsers=");
		sb.append(maxConcurrentUsers);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", maxHttpSessions=");
		sb.append(maxHttpSessions);
		sb.append(", sizing=");
		sb.append(sizing);
		sb.append(", description=");
		sb.append(description);
		sb.append(", hostName=");
		sb.append(hostName);
		sb.append(", ipAddresses=");
		sb.append(ipAddresses);
		sb.append(", macAddresses=");
		sb.append(macAddresses);
		sb.append(", serverId=");
		sb.append(serverId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", additionalInfo=");
		sb.append(additionalInfo);
		sb.append(", complimentary=");
		sb.append(complimentary);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LicenseKey toEntityModel() {
		LicenseKeyImpl licenseKeyImpl = new LicenseKeyImpl();

		if (uuid == null) {
			licenseKeyImpl.setUuid("");
		}
		else {
			licenseKeyImpl.setUuid(uuid);
		}

		licenseKeyImpl.setLicenseKeyId(licenseKeyId);
		licenseKeyImpl.setUserId(userId);

		if (userName == null) {
			licenseKeyImpl.setUserName("");
		}
		else {
			licenseKeyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseKeyImpl.setCreateDate(null);
		}
		else {
			licenseKeyImpl.setCreateDate(new Date(createDate));
		}

		licenseKeyImpl.setModifiedUserId(modifiedUserId);

		if (modifiedUserName == null) {
			licenseKeyImpl.setModifiedUserName("");
		}
		else {
			licenseKeyImpl.setModifiedUserName(modifiedUserName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseKeyImpl.setModifiedDate(null);
		}
		else {
			licenseKeyImpl.setModifiedDate(new Date(modifiedDate));
		}

		licenseKeyImpl.setLicenseKeySetId(licenseKeySetId);

		if (assetReceiptLicenseUuid == null) {
			licenseKeyImpl.setAssetReceiptLicenseUuid("");
		}
		else {
			licenseKeyImpl.setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
		}

		if (koroneikiAccountKey == null) {
			licenseKeyImpl.setKoroneikiAccountKey("");
		}
		else {
			licenseKeyImpl.setKoroneikiAccountKey(koroneikiAccountKey);
		}

		if (koroneikiProductPurchaseKey == null) {
			licenseKeyImpl.setKoroneikiProductPurchaseKey("");
		}
		else {
			licenseKeyImpl.setKoroneikiProductPurchaseKey(
				koroneikiProductPurchaseKey);
		}

		licenseKeyImpl.setAccountEntryId(accountEntryId);
		licenseKeyImpl.setOrderEntryId(orderEntryId);
		licenseKeyImpl.setOfferingEntryId(offeringEntryId);
		licenseKeyImpl.setLicenseEntryId(licenseEntryId);
		licenseKeyImpl.setProductEntryId(productEntryId);
		licenseKeyImpl.setSupportResponseId(supportResponseId);

		if (accountEntryName == null) {
			licenseKeyImpl.setAccountEntryName("");
		}
		else {
			licenseKeyImpl.setAccountEntryName(accountEntryName);
		}

		if (licenseEntryName == null) {
			licenseKeyImpl.setLicenseEntryName("");
		}
		else {
			licenseKeyImpl.setLicenseEntryName(licenseEntryName);
		}

		if (licenseEntryType == null) {
			licenseKeyImpl.setLicenseEntryType("");
		}
		else {
			licenseKeyImpl.setLicenseEntryType(licenseEntryType);
		}

		licenseKeyImpl.setLicenseVersion(licenseVersion);

		if (productEntryName == null) {
			licenseKeyImpl.setProductEntryName("");
		}
		else {
			licenseKeyImpl.setProductEntryName(productEntryName);
		}

		if (productId == null) {
			licenseKeyImpl.setProductId("");
		}
		else {
			licenseKeyImpl.setProductId(productId);
		}

		licenseKeyImpl.setProductVersion(productVersion);

		if (productVersionLabel == null) {
			licenseKeyImpl.setProductVersionLabel("");
		}
		else {
			licenseKeyImpl.setProductVersionLabel(productVersionLabel);
		}

		licenseKeyImpl.setClusterId(clusterId);

		if (owner == null) {
			licenseKeyImpl.setOwner("");
		}
		else {
			licenseKeyImpl.setOwner(owner);
		}

		licenseKeyImpl.setMaxServers(maxServers);
		licenseKeyImpl.setMaxConcurrentUsers(maxConcurrentUsers);
		licenseKeyImpl.setMaxUsers(maxUsers);
		licenseKeyImpl.setMaxHttpSessions(maxHttpSessions);
		licenseKeyImpl.setSizing(sizing);

		if (description == null) {
			licenseKeyImpl.setDescription("");
		}
		else {
			licenseKeyImpl.setDescription(description);
		}

		if (hostName == null) {
			licenseKeyImpl.setHostName("");
		}
		else {
			licenseKeyImpl.setHostName(hostName);
		}

		if (ipAddresses == null) {
			licenseKeyImpl.setIpAddresses("");
		}
		else {
			licenseKeyImpl.setIpAddresses(ipAddresses);
		}

		if (macAddresses == null) {
			licenseKeyImpl.setMacAddresses("");
		}
		else {
			licenseKeyImpl.setMacAddresses(macAddresses);
		}

		if (serverId == null) {
			licenseKeyImpl.setServerId("");
		}
		else {
			licenseKeyImpl.setServerId(serverId);
		}

		if (key == null) {
			licenseKeyImpl.setKey("");
		}
		else {
			licenseKeyImpl.setKey(key);
		}

		if (startDate == Long.MIN_VALUE) {
			licenseKeyImpl.setStartDate(null);
		}
		else {
			licenseKeyImpl.setStartDate(new Date(startDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			licenseKeyImpl.setExpirationDate(null);
		}
		else {
			licenseKeyImpl.setExpirationDate(new Date(expirationDate));
		}

		if (additionalInfo == null) {
			licenseKeyImpl.setAdditionalInfo("");
		}
		else {
			licenseKeyImpl.setAdditionalInfo(additionalInfo);
		}

		licenseKeyImpl.setComplimentary(complimentary);
		licenseKeyImpl.setActive(active);

		licenseKeyImpl.resetOriginalValues();

		return licenseKeyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		licenseKeyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();

		modifiedUserId = objectInput.readLong();
		modifiedUserName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		licenseKeySetId = objectInput.readLong();
		assetReceiptLicenseUuid = objectInput.readUTF();
		koroneikiAccountKey = objectInput.readUTF();
		koroneikiProductPurchaseKey = objectInput.readUTF();

		accountEntryId = objectInput.readLong();

		orderEntryId = objectInput.readLong();

		offeringEntryId = objectInput.readLong();

		licenseEntryId = objectInput.readLong();

		productEntryId = objectInput.readLong();

		supportResponseId = objectInput.readLong();
		accountEntryName = objectInput.readUTF();
		licenseEntryName = objectInput.readUTF();
		licenseEntryType = objectInput.readUTF();

		licenseVersion = objectInput.readInt();
		productEntryName = objectInput.readUTF();
		productId = objectInput.readUTF();

		productVersion = objectInput.readInt();
		productVersionLabel = objectInput.readUTF();

		clusterId = objectInput.readLong();
		owner = objectInput.readUTF();

		maxServers = objectInput.readInt();

		maxConcurrentUsers = objectInput.readLong();

		maxUsers = objectInput.readLong();

		maxHttpSessions = objectInput.readInt();

		sizing = objectInput.readInt();
		description = objectInput.readUTF();
		hostName = objectInput.readUTF();
		ipAddresses = objectInput.readUTF();
		macAddresses = objectInput.readUTF();
		serverId = objectInput.readUTF();
		key = objectInput.readUTF();
		startDate = objectInput.readLong();
		expirationDate = objectInput.readLong();
		additionalInfo = objectInput.readUTF();

		complimentary = objectInput.readBoolean();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(licenseKeyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(modifiedUserId);

		if (modifiedUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifiedUserName);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(licenseKeySetId);

		if (assetReceiptLicenseUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assetReceiptLicenseUuid);
		}

		if (koroneikiAccountKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(koroneikiAccountKey);
		}

		if (koroneikiProductPurchaseKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(koroneikiProductPurchaseKey);
		}

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(orderEntryId);

		objectOutput.writeLong(offeringEntryId);

		objectOutput.writeLong(licenseEntryId);

		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(supportResponseId);

		if (accountEntryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountEntryName);
		}

		if (licenseEntryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(licenseEntryName);
		}

		if (licenseEntryType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(licenseEntryType);
		}

		objectOutput.writeInt(licenseVersion);

		if (productEntryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productEntryName);
		}

		if (productId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productId);
		}

		objectOutput.writeInt(productVersion);

		if (productVersionLabel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(productVersionLabel);
		}

		objectOutput.writeLong(clusterId);

		if (owner == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(owner);
		}

		objectOutput.writeInt(maxServers);

		objectOutput.writeLong(maxConcurrentUsers);

		objectOutput.writeLong(maxUsers);

		objectOutput.writeInt(maxHttpSessions);

		objectOutput.writeInt(sizing);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (hostName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(hostName);
		}

		if (ipAddresses == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ipAddresses);
		}

		if (macAddresses == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(macAddresses);
		}

		if (serverId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(serverId);
		}

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(expirationDate);

		if (additionalInfo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(additionalInfo);
		}

		objectOutput.writeBoolean(complimentary);

		objectOutput.writeBoolean(active);
	}

	public String uuid;
	public long licenseKeyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedUserId;
	public String modifiedUserName;
	public long modifiedDate;
	public long licenseKeySetId;
	public String assetReceiptLicenseUuid;
	public String koroneikiAccountKey;
	public String koroneikiProductPurchaseKey;
	public long accountEntryId;
	public long orderEntryId;
	public long offeringEntryId;
	public long licenseEntryId;
	public long productEntryId;
	public long supportResponseId;
	public String accountEntryName;
	public String licenseEntryName;
	public String licenseEntryType;
	public int licenseVersion;
	public String productEntryName;
	public String productId;
	public int productVersion;
	public String productVersionLabel;
	public long clusterId;
	public String owner;
	public int maxServers;
	public long maxConcurrentUsers;
	public long maxUsers;
	public int maxHttpSessions;
	public int sizing;
	public String description;
	public String hostName;
	public String ipAddresses;
	public String macAddresses;
	public String serverId;
	public String key;
	public long startDate;
	public long expirationDate;
	public String additionalInfo;
	public boolean complimentary;
	public boolean active;

}
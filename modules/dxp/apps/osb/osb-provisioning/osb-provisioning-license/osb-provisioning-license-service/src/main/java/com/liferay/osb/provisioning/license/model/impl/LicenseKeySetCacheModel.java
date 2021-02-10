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

import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LicenseKeySet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LicenseKeySetCacheModel
	implements CacheModel<LicenseKeySet>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKeySetCacheModel)) {
			return false;
		}

		LicenseKeySetCacheModel licenseKeySetCacheModel =
			(LicenseKeySetCacheModel)object;

		if (licenseKeySetId == licenseKeySetCacheModel.licenseKeySetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, licenseKeySetId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{licenseKeySetId=");
		sb.append(licenseKeySetId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", koroneikiAccountKey=");
		sb.append(koroneikiAccountKey);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LicenseKeySet toEntityModel() {
		LicenseKeySetImpl licenseKeySetImpl = new LicenseKeySetImpl();

		licenseKeySetImpl.setLicenseKeySetId(licenseKeySetId);
		licenseKeySetImpl.setUserId(userId);

		if (userName == null) {
			licenseKeySetImpl.setUserName("");
		}
		else {
			licenseKeySetImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			licenseKeySetImpl.setCreateDate(null);
		}
		else {
			licenseKeySetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			licenseKeySetImpl.setModifiedDate(null);
		}
		else {
			licenseKeySetImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (koroneikiAccountKey == null) {
			licenseKeySetImpl.setKoroneikiAccountKey("");
		}
		else {
			licenseKeySetImpl.setKoroneikiAccountKey(koroneikiAccountKey);
		}

		licenseKeySetImpl.setAccountEntryId(accountEntryId);

		if (name == null) {
			licenseKeySetImpl.setName("");
		}
		else {
			licenseKeySetImpl.setName(name);
		}

		licenseKeySetImpl.resetOriginalValues();

		return licenseKeySetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		licenseKeySetId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		koroneikiAccountKey = objectInput.readUTF();

		accountEntryId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(licenseKeySetId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (koroneikiAccountKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(koroneikiAccountKey);
		}

		objectOutput.writeLong(accountEntryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long licenseKeySetId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String koroneikiAccountKey;
	public long accountEntryId;
	public String name;

}
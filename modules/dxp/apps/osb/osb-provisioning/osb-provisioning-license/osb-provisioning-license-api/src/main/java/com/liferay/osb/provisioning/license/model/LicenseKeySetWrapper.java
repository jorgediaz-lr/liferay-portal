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
 * This class is a wrapper for {@link LicenseKeySet}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySet
 * @generated
 */
public class LicenseKeySetWrapper
	implements LicenseKeySet, ModelWrapper<LicenseKeySet> {

	public LicenseKeySetWrapper(LicenseKeySet licenseKeySet) {
		_licenseKeySet = licenseKeySet;
	}

	@Override
	public Class<?> getModelClass() {
		return LicenseKeySet.class;
	}

	@Override
	public String getModelClassName() {
		return LicenseKeySet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseKeySetId", getLicenseKeySetId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("koroneikiAccountKey", getKoroneikiAccountKey());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseKeySetId = (Long)attributes.get("licenseKeySetId");

		if (licenseKeySetId != null) {
			setLicenseKeySetId(licenseKeySetId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String koroneikiAccountKey = (String)attributes.get(
			"koroneikiAccountKey");

		if (koroneikiAccountKey != null) {
			setKoroneikiAccountKey(koroneikiAccountKey);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Object clone() {
		return new LicenseKeySetWrapper((LicenseKeySet)_licenseKeySet.clone());
	}

	@Override
	public int compareTo(LicenseKeySet licenseKeySet) {
		return _licenseKeySet.compareTo(licenseKeySet);
	}

	/**
	 * Returns the account entry ID of this license key set.
	 *
	 * @return the account entry ID of this license key set
	 */
	@Override
	public long getAccountEntryId() {
		return _licenseKeySet.getAccountEntryId();
	}

	/**
	 * Returns the create date of this license key set.
	 *
	 * @return the create date of this license key set
	 */
	@Override
	public Date getCreateDate() {
		return _licenseKeySet.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _licenseKeySet.getExpandoBridge();
	}

	/**
	 * Returns the koroneiki account key of this license key set.
	 *
	 * @return the koroneiki account key of this license key set
	 */
	@Override
	public String getKoroneikiAccountKey() {
		return _licenseKeySet.getKoroneikiAccountKey();
	}

	/**
	 * Returns the license key set ID of this license key set.
	 *
	 * @return the license key set ID of this license key set
	 */
	@Override
	public long getLicenseKeySetId() {
		return _licenseKeySet.getLicenseKeySetId();
	}

	/**
	 * Returns the modified date of this license key set.
	 *
	 * @return the modified date of this license key set
	 */
	@Override
	public Date getModifiedDate() {
		return _licenseKeySet.getModifiedDate();
	}

	/**
	 * Returns the name of this license key set.
	 *
	 * @return the name of this license key set
	 */
	@Override
	public String getName() {
		return _licenseKeySet.getName();
	}

	/**
	 * Returns the primary key of this license key set.
	 *
	 * @return the primary key of this license key set
	 */
	@Override
	public long getPrimaryKey() {
		return _licenseKeySet.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _licenseKeySet.getPrimaryKeyObj();
	}

	/**
	 * Returns the user ID of this license key set.
	 *
	 * @return the user ID of this license key set
	 */
	@Override
	public long getUserId() {
		return _licenseKeySet.getUserId();
	}

	/**
	 * Returns the user name of this license key set.
	 *
	 * @return the user name of this license key set
	 */
	@Override
	public String getUserName() {
		return _licenseKeySet.getUserName();
	}

	/**
	 * Returns the user uuid of this license key set.
	 *
	 * @return the user uuid of this license key set
	 */
	@Override
	public String getUserUuid() {
		return _licenseKeySet.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _licenseKeySet.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _licenseKeySet.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _licenseKeySet.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _licenseKeySet.isNew();
	}

	@Override
	public void persist() {
		_licenseKeySet.persist();
	}

	/**
	 * Sets the account entry ID of this license key set.
	 *
	 * @param accountEntryId the account entry ID of this license key set
	 */
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_licenseKeySet.setAccountEntryId(accountEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_licenseKeySet.setCachedModel(cachedModel);
	}

	/**
	 * Sets the create date of this license key set.
	 *
	 * @param createDate the create date of this license key set
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_licenseKeySet.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_licenseKeySet.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_licenseKeySet.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_licenseKeySet.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the koroneiki account key of this license key set.
	 *
	 * @param koroneikiAccountKey the koroneiki account key of this license key set
	 */
	@Override
	public void setKoroneikiAccountKey(String koroneikiAccountKey) {
		_licenseKeySet.setKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	 * Sets the license key set ID of this license key set.
	 *
	 * @param licenseKeySetId the license key set ID of this license key set
	 */
	@Override
	public void setLicenseKeySetId(long licenseKeySetId) {
		_licenseKeySet.setLicenseKeySetId(licenseKeySetId);
	}

	/**
	 * Sets the modified date of this license key set.
	 *
	 * @param modifiedDate the modified date of this license key set
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_licenseKeySet.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this license key set.
	 *
	 * @param name the name of this license key set
	 */
	@Override
	public void setName(String name) {
		_licenseKeySet.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_licenseKeySet.setNew(n);
	}

	/**
	 * Sets the primary key of this license key set.
	 *
	 * @param primaryKey the primary key of this license key set
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_licenseKeySet.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_licenseKeySet.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the user ID of this license key set.
	 *
	 * @param userId the user ID of this license key set
	 */
	@Override
	public void setUserId(long userId) {
		_licenseKeySet.setUserId(userId);
	}

	/**
	 * Sets the user name of this license key set.
	 *
	 * @param userName the user name of this license key set
	 */
	@Override
	public void setUserName(String userName) {
		_licenseKeySet.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this license key set.
	 *
	 * @param userUuid the user uuid of this license key set
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_licenseKeySet.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LicenseKeySet>
		toCacheModel() {

		return _licenseKeySet.toCacheModel();
	}

	@Override
	public LicenseKeySet toEscapedModel() {
		return new LicenseKeySetWrapper(_licenseKeySet.toEscapedModel());
	}

	@Override
	public String toString() {
		return _licenseKeySet.toString();
	}

	@Override
	public LicenseKeySet toUnescapedModel() {
		return new LicenseKeySetWrapper(_licenseKeySet.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _licenseKeySet.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LicenseKeySetWrapper)) {
			return false;
		}

		LicenseKeySetWrapper licenseKeySetWrapper =
			(LicenseKeySetWrapper)object;

		if (Objects.equals(
				_licenseKeySet, licenseKeySetWrapper._licenseKeySet)) {

			return true;
		}

		return false;
	}

	@Override
	public LicenseKeySet getWrappedModel() {
		return _licenseKeySet;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _licenseKeySet.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _licenseKeySet.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_licenseKeySet.resetOriginalValues();
	}

	private final LicenseKeySet _licenseKeySet;

}
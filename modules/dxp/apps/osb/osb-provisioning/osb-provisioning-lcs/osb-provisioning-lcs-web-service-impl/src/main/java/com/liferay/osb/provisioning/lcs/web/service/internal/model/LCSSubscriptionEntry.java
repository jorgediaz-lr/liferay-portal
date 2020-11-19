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

package com.liferay.osb.provisioning.lcs.web.service.internal.model;

import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class LCSSubscriptionEntry {

	public boolean getActive() {
		return false;
	}

	public double getActualPrice() {
		return 0.0;
	}

	public String getCurrencyCode() {
		return StringPool.BLANK;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public int getInstanceSize() {
		return _instanceSize;
	}

	public String getPlatform() {
		return StringPool.BLANK;
	}

	public String getPlatformVersion() {
		return StringPool.BLANK;
	}

	public String getProduct() {
		return _product;
	}

	public int getProductVersion() {
		return _productVersion;
	}

	public int getQuantity() {
		return 0;
	}

	public int getServersAllowed() {
		return _serversAllowed;
	}

	public int getServersUsed() {
		return 0;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public Date getSupportEndDate() {
		return _supportEndDate;
	}

	public Date getSupportStartDate() {
		return _supportStartDate;
	}

	public String getType() {
		return _type;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public void setInstanceSize(int instanceSize) {
		_instanceSize = instanceSize;
	}

	public void setProduct(String product) {
		_product = product;
	}

	public void setProductVersion(int productVersion) {
		_productVersion = productVersion;
	}

	public void setServersAllowed(int serversAllowed) {
		_serversAllowed = serversAllowed;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public void setSupportEndDate(Date supportEndDate) {
		_supportEndDate = supportEndDate;
	}

	public void setSupportStartDate(Date supportStartDate) {
		_supportStartDate = supportStartDate;
	}

	public void setType(String type) {
		_type = type;
	}

	private Date _endDate;
	private int _instanceSize;
	private String _product;
	private int _productVersion;
	private int _serversAllowed;
	private Date _startDate;
	private Date _supportEndDate;
	private Date _supportStartDate;
	private String _type;

}
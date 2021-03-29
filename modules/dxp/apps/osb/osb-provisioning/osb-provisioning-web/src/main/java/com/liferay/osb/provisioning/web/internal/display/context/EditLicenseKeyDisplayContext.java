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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class EditLicenseKeyDisplayContext {

	public EditLicenseKeyDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest,
			LicenseKeyLocalService licenseKeyLocalService)
		throws Exception {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_licenseKeyLocalService = licenseKeyLocalService;

		_licenseKey = (LicenseKey)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEY);

		_licenseKeyDisplay = new LicenseKeyDisplay(
			renderRequest, renderResponse, _licenseKey);

		_licenseType = _licenseKey.getLicenseEntryType();
		_licenseVersion = _licenseKey.getLicenseVersion();
	}

	public List<LicenseKey> getClusterLicenseKeys() {
		return _licenseKeyLocalService.getProductPurchaseLicenseKeys(
			_licenseKey.getProductPurchaseKey(), _licenseKey.getClusterId());
	}

	public String getDownloadLicenseKeyURL() throws Exception {
		ResourceURL resourceURL = _renderResponse.createResourceURL();

		resourceURL.setParameter(
			"licenseKeyId", String.valueOf(_licenseKey.getLicenseKeyId()));
		resourceURL.setResourceID("/licenses/download_license_key");

		return resourceURL.toString();
	}

	public String getEditLicenseKeyURL() throws Exception {
		PortletURL portletURL = _renderResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/licenses/edit_license_key");

		return portletURL.toString();
	}

	public String getExpirationDateLabel() {
		if (_licenseType.equals(LicenseType.TRIAL)) {
			return LanguageUtil.get(_httpServletRequest, "lifetime");
		}

		return LanguageUtil.get(_httpServletRequest, "expiration-date");
	}

	public String getLastModifiedUserNameDate() {
		return _licenseKey.getModifiedUserName() + " On " +
			_licenseKeyDisplay.getModifiedDate();
	}

	public LicenseKey getLicenseKey() {
		return _licenseKey;
	}

	public LicenseKeyDisplay getLicenseKeyDisplay() {
		return _licenseKeyDisplay;
	}

	public String getMoveLicenseKeyURL() throws Exception {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setWindowState(LiferayWindowState.POP_UP);
		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/move_license_key");
		portletURL.setParameter(
			"licenseKeyId", String.valueOf(_licenseKey.getLicenseKeyId()));

		return portletURL.toString();
	}

	public String getUpdateActiveLabel() {
		if (_licenseKey.isActive()) {
			return LanguageUtil.get(_httpServletRequest, "deactivate");
		}

		return LanguageUtil.get(_httpServletRequest, "activate");
	}

	public String getUpdateComplimentaryLabel() {
		if (_licenseKey.isComplimentary()) {
			return LanguageUtil.get(
				_httpServletRequest, "remove-complimentary");
		}

		return LanguageUtil.get(_httpServletRequest, "make-complimentary");
	}

	public boolean isClusterLicenseKeyVisable() {
		if ((_licenseVersion >= 3) &&
			_licenseType.equals(LicenseType.CLUSTER)) {

			return true;
		}

		return false;
	}

	public boolean isComplimentaryVisable() {
		if ((_licenseVersion >= 3) &&
			!_licenseType.equals(LicenseType.CLUSTER)) {

			return true;
		}

		return false;
	}

	public boolean isDownloadVisable() {
		if (!isClusterLicenseKeyVisable() && _licenseKey.isActive() &&
			((_licenseVersion >= 2) ||
			 _licenseType.equals(LicenseType.CLUSTER) ||
			 _licenseType.equals(LicenseType.DEVELOPER_CLUSTER))) {

			return true;
		}

		return false;
	}

	public boolean isHostNameVisable() {
		if ((_licenseVersion >= 3) &&
			(_licenseType.equals(LicenseType.LIMITED) ||
			 _licenseType.equals(LicenseType.PER_USER) ||
			 _licenseType.equals(LicenseType.PRODUCTION))) {

			return true;
		}

		return false;
	}

	public boolean isIpAddressesVisable() {
		if ((_licenseVersion >= 3) &&
			(_licenseType.equals(LicenseType.LIMITED) ||
			 _licenseType.equals(LicenseType.PER_USER) ||
			 _licenseType.equals(LicenseType.PRODUCTION))) {

			return true;
		}

		return false;
	}

	public boolean isKeyVisable() {
		if (_licenseVersion == 1) {
			return true;
		}

		return false;
	}

	public boolean isMacAddressesVisable() {
		if (((_licenseVersion >= 3) &&
			 (_licenseType.equals(LicenseType.LIMITED) ||
			  _licenseType.equals(LicenseType.PER_USER) ||
			  _licenseType.equals(LicenseType.PRODUCTION))) ||
			((_licenseVersion == 2) &&
			 _licenseType.equals(LicenseType.PRODUCTION)) ||
			((_licenseVersion == 1) &&
			 (_licenseType.equals(LicenseType.CLUSTER) ||
			  _licenseType.equals(LicenseType.DEVELOPER_CLUSTER)))) {

			return true;
		}

		return false;
	}

	public boolean isMaximumConcurrentUsersVisable() {
		if ((_licenseVersion >= 3) &&
			_licenseType.equals(LicenseType.PER_USER)) {

			return true;
		}

		return false;
	}

	public boolean isMaximumConnectionsVisable() {
		if ((_licenseVersion >= 3) &&
			(_licenseType.equals(LicenseType.DEVELOPER) ||
			 _licenseType.equals(LicenseType.DEVELOPER_CLUSTER))) {

			return true;
		}

		return false;
	}

	public boolean isMaximumUsersVisable() {
		if ((_licenseVersion >= 3) &&
			_licenseType.equals(LicenseType.PER_USER)) {

			return true;
		}

		return false;
	}

	public boolean isMaxmumServersVisable() {
		if (((_licenseVersion >= 3) &&
			 _licenseType.equals(LicenseType.CLUSTER)) ||
			((_licenseVersion == 2) &&
			 (_licenseType.equals(LicenseType.CLUSTER) ||
			  _licenseType.equals(LicenseType.DEVELOPER_CLUSTER)))) {

			return true;
		}

		return false;
	}

	public boolean isRenewVisable() throws Exception {
		if (_licenseKey.canRenew() && (_licenseVersion >= 3) &&
			!_licenseType.equals(LicenseType.CLUSTER)) {

			return true;
		}

		return false;
	}

	public boolean isServerIdVisable() {
		if (((_licenseVersion >= 3) &&
			 (_licenseType.equals(LicenseType.LIMITED) ||
			  _licenseType.equals(LicenseType.PER_USER) ||
			  _licenseType.equals(LicenseType.PRODUCTION)) &&
			 Validator.isNotNull(_licenseKey.getServerId())) ||
			((_licenseVersion == 1) &&
			 _licenseType.equals(LicenseType.PRODUCTION))) {

			return true;
		}

		return false;
	}

	private final HttpServletRequest _httpServletRequest;
	private final LicenseKey _licenseKey;
	private final LicenseKeyDisplay _licenseKeyDisplay;
	private final LicenseKeyLocalService _licenseKeyLocalService;
	private final String _licenseType;
	private final int _licenseVersion;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}
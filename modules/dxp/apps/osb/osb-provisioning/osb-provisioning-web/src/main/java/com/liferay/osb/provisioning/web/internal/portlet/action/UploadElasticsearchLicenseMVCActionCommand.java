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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.license.exception.DuplicateCommonLicenseKeyException;
import com.liferay.osb.provisioning.license.helper.constants.ProductEnvironment;
import com.liferay.osb.provisioning.license.service.CommonLicenseKeyLocalService;
import com.liferay.osb.provisioning.rest.dto.v1_0.ProductGroup;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.InputStream;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ADMIN,
		"mvc.command.name=/admin/upload_elasticsearch_license"
	},
	service = MVCActionCommand.class
)
public class UploadElasticsearchLicenseMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			UploadPortletRequest uploadPortletRequest =
				_portal.getUploadPortletRequest(actionRequest);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			try (InputStream inputStream = uploadPortletRequest.getFileAsStream(
					"elasticsearchLicenseFile")) {

				String fileName = uploadPortletRequest.getFileName(
					"elasticsearchLicenseFile");

				String fileContent = StringUtil.read(inputStream);

				JSONObject jsonObject = _jsonFactory.createJSONObject(
					fileContent);

				JSONObject licenseJSONObject = jsonObject.getJSONObject(
					"license");

				String issuedTo = licenseJSONObject.getString("issued_to");

				String productEnvironment = null;

				if (issuedTo.contains(ProductEnvironment.BACKUP)) {
					productEnvironment = ProductEnvironment.BACKUP;
				}
				else if (issuedTo.contains(ProductEnvironment.NON_PRODUCTION)) {
					productEnvironment = ProductEnvironment.NON_PRODUCTION;
				}
				else {
					productEnvironment = ProductEnvironment.PRODUCTION;
				}

				Date startDate = new Date(
					licenseJSONObject.getLong("start_date_in_millis"));
				Date endDate = new Date(
					licenseJSONObject.getLong("expiry_date_in_millis"));

				_commonLicenseKeyLocalService.addCommonLicenseKey(
					themeDisplay.getUserId(),
					ProductGroup.Name.ENTERPRISE_SEARCH.toString(),
					productEnvironment, StringPool.BLANK, startDate, endDate,
					fileName, fileContent);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			if (exception instanceof DuplicateCommonLicenseKeyException) {
				SessionErrors.add(
					actionRequest, exception.getClass(), exception);
			}
			else {
				throw exception;
			}
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UploadElasticsearchLicenseMVCActionCommand.class);

	@Reference
	private CommonLicenseKeyLocalService _commonLicenseKeyLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Portal _portal;

}
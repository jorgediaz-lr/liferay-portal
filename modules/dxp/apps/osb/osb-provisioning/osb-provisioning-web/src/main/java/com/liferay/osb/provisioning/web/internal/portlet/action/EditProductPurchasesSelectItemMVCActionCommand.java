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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_product_purchases_choose_terms",
		"mvc.command.name=/accounts/edit_product_purchases_select_products"
	},
	service = MVCActionCommand.class
)
public class EditProductPurchasesSelectItemMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");
			String backURL = ParamUtil.getString(actionRequest, "backURL");
			String productBundleIds = ParamUtil.getString(
				actionRequest, "productBundleIds");
			String productKeys = ParamUtil.getString(
				actionRequest, "productKeys");
			String productPurchaseKeys = ParamUtil.getString(
				actionRequest, "productPurchaseKeys");
			String productPurchaseViewKeys = ParamUtil.getString(
				actionRequest, "productPurchaseViewKeys");
			String redirect = ParamUtil.getString(actionRequest, "redirect");

			LiferayPortletResponse liferayPortletResponse =
				_portal.getLiferayPortletResponse(actionResponse);

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter("accountKey", accountKey);

			if (Validator.isNotNull(backURL)) {
				portletURL.setParameter("backURL", backURL);
			}
			else if (Validator.isNotNull(redirect)) {
				portletURL.setParameter("redirect", redirect);
			}

			if (Validator.isNotNull(productBundleIds)) {
				portletURL.setParameter("productBundleIds", productBundleIds);
			}

			if (Validator.isNotNull(productKeys)) {
				portletURL.setParameter("productKeys", productKeys);
			}

			if (Validator.isNotNull(productPurchaseKeys)) {
				portletURL.setParameter(
					"productPurchaseKeys", productPurchaseKeys);
			}

			if (Validator.isNotNull(productPurchaseViewKeys)) {
				portletURL.setParameter(
					"productPurchaseViewKeys", productPurchaseViewKeys);
			}

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/edit_product_purchases");

			hideDefaultSuccessMessage(actionRequest);

			sendRedirect(actionRequest, actionResponse, portletURL.toString());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductPurchasesSelectItemMVCActionCommand.class);

	@Reference
	private Portal _portal;

}
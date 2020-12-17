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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.ProductPurchaseQuantityException;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_product_purchase"
	},
	service = MVCActionCommand.class
)
public class EditProductPurchaseMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updateProductPurchase(actionRequest);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Problem.ProblemException problemException) {
			SessionErrors.add(
				actionRequest, problemException.getClass(), problemException);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateProductPurchase(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String productPurchaseKey = ParamUtil.getString(
			actionRequest, "productPurchaseKey");

		boolean perpetual = ParamUtil.getBoolean(actionRequest, "perpetual");

		int quantity = ParamUtil.getInteger(actionRequest, "quantity");

		if (quantity <= 0) {
			throw new ProductPurchaseQuantityException();
		}

		String status = ParamUtil.getString(
			actionRequest, "status",
			ProductPurchase.Status.APPROVED.toString());
		int sizing = ParamUtil.getInteger(actionRequest, "sizing");
		String salesforceOpportunityKey = ParamUtil.getString(
			actionRequest, "salesforceOpportunityKey");

		ProductPurchase productPurchase = new ProductPurchase();

		if (Validator.isNull(productPurchaseKey)) {
			String productKey = ParamUtil.getString(
				actionRequest, "productKey");

			productPurchase.setProduct(
				_productWebService.getProduct(productKey));
		}

		if (perpetual) {
			productPurchase.setPerpetual(perpetual);
		}
		else {
			int startDateMonth = ParamUtil.getInteger(
				actionRequest, "startDateMonth");
			int startDateDay = ParamUtil.getInteger(
				actionRequest, "startDateDay");
			int startDateYear = ParamUtil.getInteger(
				actionRequest, "startDateYear");

			Date startDate = _portal.getDate(
				startDateMonth, startDateDay, startDateYear,
				themeDisplay.getTimeZone(), null);

			int originalEndMonth = ParamUtil.getInteger(
				actionRequest, "endDateMonth");
			int originalEndDay = ParamUtil.getInteger(
				actionRequest, "endDateDay");
			int originalEndYear = ParamUtil.getInteger(
				actionRequest, "endDateYear");

			Date originalEndDate = _portal.getDate(
				originalEndMonth, originalEndDay, originalEndYear,
				themeDisplay.getTimeZone(), null);

			Calendar calendar = CalendarFactoryUtil.getCalendar(
				themeDisplay.getTimeZone());

			calendar.setTime(originalEndDate);

			calendar.add(Calendar.DATE, 30);

			int gracePeriodEndDateMonth = ParamUtil.getInteger(
				actionRequest, "gracePeriodEndDateMonth",
				calendar.get(Calendar.MONTH));
			int gracePeriodEndDateDay = ParamUtil.getInteger(
				actionRequest, "gracePeriodEndDateDay",
				calendar.get(Calendar.DATE));
			int gracePeriodEndDateYear = ParamUtil.getInteger(
				actionRequest, "gracePeriodEndDateYear",
				calendar.get(Calendar.YEAR));

			Date endDate = _portal.getDate(
				gracePeriodEndDateMonth, gracePeriodEndDateDay,
				gracePeriodEndDateYear, themeDisplay.getTimeZone(), null);

			productPurchase.setStartDate(startDate);
			productPurchase.setOriginalEndDate(originalEndDate);
			productPurchase.setEndDate(endDate);
		}

		productPurchase.setQuantity(quantity);
		productPurchase.setStatus(ProductPurchase.Status.create(status));

		Map<String, String> properties = new HashMap<>();

		if (Validator.isNotNull(productPurchaseKey)) {
			ProductPurchase curProductPurchase =
				_productPurchaseWebService.getProductPurchase(
					productPurchaseKey);

			Map<String, String> curProperties =
				curProductPurchase.getProperties();

			if (curProperties != null) {
				for (Map.Entry<String, String> entry :
						curProperties.entrySet()) {

					properties.put(entry.getKey(), entry.getValue());
				}
			}
		}

		properties.put("sizing", String.valueOf(sizing));

		productPurchase.setProperties(properties);

		if (Validator.isNull(productPurchaseKey) &&
			Validator.isNotNull(salesforceOpportunityKey)) {

			ExternalLink externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.SALESFORCE);
			externalLink.setEntityName(
				ExternalLinkEntityName.SALESFORCE_OPPORTUNITY);
			externalLink.setEntityId(salesforceOpportunityKey);

			productPurchase.setExternalLinks(new ExternalLink[] {externalLink});
		}

		if (Validator.isNull(productPurchaseKey)) {
			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			_productPurchaseWebService.addProductPurchase(
				user.getFullName(), user.getUuid(), accountKey,
				productPurchase);
		}
		else {
			_productPurchaseWebService.updateProductPurchase(
				user.getFullName(), user.getUuid(), productPurchaseKey,
				productPurchase);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductPurchaseMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

}
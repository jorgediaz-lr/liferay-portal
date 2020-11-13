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

package com.liferay.osb.provisioning.lcs.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.lcs.web.service.LCSSubscriptionEntryWebService;
import com.liferay.osb.provisioning.lcs.web.service.internal.configuration.LCSConfiguration;
import com.liferay.osb.provisioning.lcs.web.service.internal.model.LCSSubscriptionEntry;
import com.liferay.osb.provisioning.license.constants.LicenseType;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientFactory;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.lcs.web.service.internal.configuration.LCSConfiguration",
	immediate = true, service = LCSSubscriptionEntryWebService.class
)
public class LCSSubscriptionEntryWebServiceImpl
	implements LCSSubscriptionEntryWebService {

	public String syncToLCS(String accountKey) throws Exception {
		String corpProjectId = _getCorpProjectId(accountKey);

		List<LCSSubscriptionEntry> lcsSubscriptionEntries =
			_getLCSSubscriptionEntries(accountKey);

		Map<String, String> parameters = new HashMap<>();

		parameters.put("corpProjectId", corpProjectId);
		parameters.put(
			"lcsSubscriptionEntries",
			_jsonFactory.looseSerialize(lcsSubscriptionEntries));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Sending request to " + _URL_API_JSONWS_LCS_GATEWAY +
					"/send-lcs-subscription-entries");

			_log.debug(MapUtil.toString(parameters));
		}

		_jsonWebServiceClient.doPost(
			_URL_API_JSONWS_LCS_GATEWAY + "/send-lcs-subscription-entries",
			parameters);

		return corpProjectId;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		LCSConfiguration lcsConfiguration = ConfigurableUtil.createConfigurable(
			LCSConfiguration.class, properties);

		Map<String, Object> jsonWebServicePproperties = new HashMap<>();

		jsonWebServicePproperties.put(
			"headers", "OSB_LCS_API_Token=" + lcsConfiguration.apiToken());
		jsonWebServicePproperties.put("hostName", lcsConfiguration.host());
		jsonWebServicePproperties.put(
			"hostPort", String.valueOf(lcsConfiguration.port()));
		jsonWebServicePproperties.put("protocol", lcsConfiguration.scheme());

		_jsonWebServiceClient = _jsonWebServiceClientFactory.getInstance(
			jsonWebServicePproperties, false);
	}

	@Deactivate
	protected void deactivate() {
		if (_jsonWebServiceClient != null) {
			_jsonWebServiceClient.destroy();
		}
	}

	private LCSSubscriptionEntry _createLCSSubscriptionEntry(
			ProductPurchaseView productPurchaseView,
			ProductPurchaseView slaProductPurchaseView)
		throws Exception {

		String lcsProduct = _getLCSProduct(
			productPurchaseView.getProduct(), slaProductPurchaseView);

		if (Validator.isNull(lcsProduct)) {
			return null;
		}

		String type = _getType(lcsProduct);

		if (Validator.isNull(type)) {
			return null;
		}

		LCSSubscriptionEntry lcsSubscriptionEntry = new LCSSubscriptionEntry();

		lcsSubscriptionEntry.setEndDate(
			_getEndDate(productPurchaseView.getProductPurchases()));
		lcsSubscriptionEntry.setInstanceSize(
			_getInstanceSize(productPurchaseView.getProductPurchases()));
		lcsSubscriptionEntry.setProduct(lcsProduct);
		lcsSubscriptionEntry.setProductVersion(
			_getBuildVersion(productPurchaseView));
		lcsSubscriptionEntry.setServersAllowed(
			_getServersAllowed(productPurchaseView));
		lcsSubscriptionEntry.setStartDate(
			_getStartDate(productPurchaseView.getProductPurchases()));
		lcsSubscriptionEntry.setType(type);

		if (slaProductPurchaseView != null) {
			lcsSubscriptionEntry.setSupportEndDate(
				_getEndDate(slaProductPurchaseView.getProductPurchases()));
			lcsSubscriptionEntry.setSupportStartDate(
				_getStartDate(slaProductPurchaseView.getProductPurchases()));
		}

		return lcsSubscriptionEntry;
	}

	private int _getBuildVersion(ProductPurchaseView productPurchaseView) {
		Product product = productPurchaseView.getProduct();

		String productName = product.getName();

		if (productName.contains(_DXP)) {
			return 7000;
		}

		return 6200;
	}

	private String _getCorpProjectId(String accountKey) throws Exception {
		Account account = _accountWebService.getAccount(accountKey);

		for (ExternalLink externalLink : account.getExternalLinks()) {
			String domain = externalLink.getDomain();
			String entityName = externalLink.getEntityName();

			if (domain.equals(ExternalLinkDomain.LCS) &&
				entityName.equals(ExternalLinkEntityName.LCS_CORP_PROJECT)) {

				return externalLink.getEntityId();
			}
		}

		return accountKey.substring(4);
	}

	private Date _getEndDate(ProductPurchase[] productPurchases) {
		Date endDate = _START_DATE_PERPETUAL;

		for (ProductPurchase productPurchase : productPurchases) {
			Date curEndDate = productPurchase.getEndDate();

			if (curEndDate == null) {
				return _END_DATE_PERPETUAL;
			}

			if (curEndDate.after(endDate)) {
				endDate = curEndDate;
			}
		}

		return endDate;
	}

	private int _getInstanceSize(ProductPurchase[] productPurchases) {
		Date now = new Date();

		int instanceSize = 0;

		for (ProductPurchase productPurchase : productPurchases) {
			Map<String, String> properties = productPurchase.getProperties();

			if (properties != null) {
				int sizing = GetterUtil.getInteger(properties.get("sizing"));
				Date startDate = productPurchase.getStartDate();
				Date endDate = productPurchase.getEndDate();

				if ((sizing > instanceSize) &&
					((startDate == null) || startDate.before(now)) &&
					((endDate == null) || endDate.after(now))) {

					instanceSize = sizing;
				}
			}
		}

		return instanceSize;
	}

	private String _getKey(LCSSubscriptionEntry lcsSubscriptionEntry) {
		StringBundler sb = new StringBundler(5);

		sb.append(lcsSubscriptionEntry.getProduct());
		sb.append(StringPool.POUND);
		sb.append(lcsSubscriptionEntry.getProductVersion());
		sb.append(StringPool.POUND);

		Date endDate = lcsSubscriptionEntry.getEndDate();

		sb.append(endDate.getTime());

		return sb.toString();
	}

	private String _getLCSProduct(
		Product product, ProductPurchaseView slaProductPurchaseView) {

		String lcsProduct = StringPool.BLANK;

		String productName = product.getName();

		if (productName.contains(_DXP_BACKUP) ||
			productName.contains(_PORTAL_BACKUP)) {

			lcsProduct = _PORTAL_BACKUP;
		}
		else if (productName.contains(_DXP_DEVELOPMENT) ||
				 productName.contains(_PORTAL_DEVELOPMENT)) {

			lcsProduct = _PORTAL_DEVELOPMENT;
		}
		else if (productName.contains(_DXP_ENTERPRISE) ||
				 productName.contains(_PORTAL_ENTERPRISE)) {

			lcsProduct = _PORTAL_ENTERPRISE;
		}
		else if (productName.contains(_DXP_LIMITED) ||
				 productName.contains(_PORTAL_LIMITED)) {

			lcsProduct = _PORTAL_LIMITED;
		}
		else if (productName.contains(_DXP_NON_PRODUCTION) ||
				 productName.contains(_PORTAL_NON_PRODUCTION)) {

			lcsProduct = _PORTAL_NON_PRODUCTION;
		}
		else if (productName.contains(_DXP_PRODUCTION) ||
				 productName.contains(_PORTAL_PRODUCTION)) {

			lcsProduct = _PORTAL_PRODUCTION;
		}
		else if (productName.contains(_ELASTIC_ACTIVATION)) {
			lcsProduct = _ELASTIC_ACTIVATION;
		}

		if (Validator.isNull(lcsProduct)) {
			return null;
		}

		String supportLevel = _getSupportLevel(slaProductPurchaseView);

		if (Validator.isNotNull(supportLevel)) {
			lcsProduct = supportLevel + StringPool.SPACE + lcsProduct;
		}

		return lcsProduct;
	}

	private List<LCSSubscriptionEntry> _getLCSSubscriptionEntries(
			String accountKey)
		throws Exception {

		Map<String, LCSSubscriptionEntry> lcsSubscriptionEntriesMap =
			new HashMap<>();

		StringBundler sb = new StringBundler(5);

		sb.append("(accountKey eq '");
		sb.append(accountKey);
		sb.append("') and (state eq '");
		sb.append(ProductPurchaseConstants.STATE_ACTIVE);
		sb.append("')");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		ProductPurchaseView slaProductPurchaseView = _getSLAProductPurchaseView(
			productPurchaseViews);

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			LCSSubscriptionEntry lcsSubscriptionEntry =
				_createLCSSubscriptionEntry(
					productPurchaseView, slaProductPurchaseView);

			if (lcsSubscriptionEntry == null) {
				continue;
			}

			String key = _getKey(lcsSubscriptionEntry);

			LCSSubscriptionEntry curLCSSubscriptionEntry =
				lcsSubscriptionEntriesMap.get(key);

			if (curLCSSubscriptionEntry != null) {
				lcsSubscriptionEntry = _mergeLCSSubscriptionEntries(
					lcsSubscriptionEntry, curLCSSubscriptionEntry);
			}

			lcsSubscriptionEntriesMap.put(key, lcsSubscriptionEntry);
		}

		return new ArrayList<>(lcsSubscriptionEntriesMap.values());
	}

	private int _getServersAllowed(ProductPurchaseView productPurchaseView) {
		Date now = new Date();

		int serversAllowed = 0;

		for (ProductPurchase productPurchase :
				productPurchaseView.getProductPurchases()) {

			Date startDate = productPurchase.getStartDate();
			Date endDate = productPurchase.getEndDate();

			if (((startDate != null) && startDate.after(now)) ||
				((endDate != null) && endDate.before(now))) {

				continue;
			}

			serversAllowed += productPurchase.getQuantity();
		}

		for (ProductConsumption productConsumption :
				productPurchaseView.getProductConsumptions()) {

			Date startDate = productConsumption.getStartDate();
			Date endDate = productConsumption.getEndDate();

			if (((startDate != null) && startDate.after(now)) ||
				((endDate != null) && endDate.before(now))) {

				continue;
			}

			serversAllowed--;
		}

		if (serversAllowed < 0) {
			return 0;
		}

		return serversAllowed;
	}

	private ProductPurchaseView _getSLAProductPurchaseView(
		List<ProductPurchaseView> productPurchaseViews) {

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			Product product = productPurchaseView.getProduct();

			String productName = product.getName();

			if (productName.equals(ProductConstants.NAME_GOLD) ||
				productName.equals(ProductConstants.NAME_PLATINUM) ||
				productName.equals(ProductConstants.NAME_SILVER)) {

				return productPurchaseView;
			}
		}

		return null;
	}

	private Date _getStartDate(ProductPurchase[] productPurchases) {
		Date startDate = _END_DATE_PERPETUAL;

		for (ProductPurchase productPurchase : productPurchases) {
			Date curStartDate = productPurchase.getStartDate();

			if (curStartDate == null) {
				return _START_DATE_PERPETUAL;
			}

			if (curStartDate.before(startDate)) {
				startDate = curStartDate;
			}
		}

		return startDate;
	}

	private String _getSupportLevel(ProductPurchaseView productPurchaseView) {
		if (productPurchaseView == null) {
			return null;
		}

		Product product = productPurchaseView.getProduct();

		String productName = product.getName();

		if (productName.equals(ProductConstants.NAME_GOLD)) {
			return "Gold";
		}

		if (productName.equals(ProductConstants.NAME_PLATINUM)) {
			return "Platinum";
		}

		if (productName.equals(ProductConstants.NAME_SILVER)) {
			return "Silver";
		}

		return null;
	}

	private String _getType(String lcsProduct) {
		if (lcsProduct.contains(_DXP_BACKUP) ||
			lcsProduct.contains(_PORTAL_BACKUP)) {

			return LicenseType.BACKUP;
		}
		else if (lcsProduct.contains(_DXP_DEVELOPMENT) ||
				 lcsProduct.contains(_PORTAL_DEVELOPMENT)) {

			return LicenseType.DEVELOPER;
		}
		else if (lcsProduct.contains(_DXP_ENTERPRISE) ||
				 lcsProduct.contains(_PORTAL_ENTERPRISE)) {

			return LicenseType.ENTERPRISE;
		}
		else if (lcsProduct.contains(_DXP_LIMITED) ||
				 lcsProduct.contains(_PORTAL_LIMITED)) {

			return LicenseType.LIMITED;
		}
		else if (lcsProduct.contains(_DXP_NON_PRODUCTION) ||
				 lcsProduct.contains(_PORTAL_NON_PRODUCTION)) {

			return LicenseType.NON_PRODUCTION;
		}
		else if (lcsProduct.contains(_DXP_PRODUCTION) ||
				 lcsProduct.contains(_PORTAL_PRODUCTION)) {

			return LicenseType.PRODUCTION;
		}
		else if (lcsProduct.contains(_ELASTIC_ACTIVATION)) {
			return LicenseType.ELASTIC;
		}
		else {
			return StringPool.BLANK;
		}
	}

	private LCSSubscriptionEntry _mergeLCSSubscriptionEntries(
		LCSSubscriptionEntry lcsSubscriptionEntry,
		LCSSubscriptionEntry curLCSSubscriptionEntry) {

		if (lcsSubscriptionEntry.getServersAllowed() !=
				_ALLOWED_SERVERS_UNLIMITED) {

			lcsSubscriptionEntry.setServersAllowed(
				lcsSubscriptionEntry.getServersAllowed() +
					curLCSSubscriptionEntry.getServersAllowed());
		}

		if (lcsSubscriptionEntry.getInstanceSize() <
				curLCSSubscriptionEntry.getInstanceSize()) {

			lcsSubscriptionEntry.setInstanceSize(
				curLCSSubscriptionEntry.getInstanceSize());
		}

		Date supportEndDate = lcsSubscriptionEntry.getSupportEndDate();

		if ((curLCSSubscriptionEntry.getSupportEndDate() != null) &&
			((supportEndDate == null) ||
			 supportEndDate.after(
				 curLCSSubscriptionEntry.getSupportEndDate()))) {

			lcsSubscriptionEntry.setSupportEndDate(
				curLCSSubscriptionEntry.getSupportEndDate());
		}

		return lcsSubscriptionEntry;
	}

	private static final int _ALLOWED_SERVERS_UNLIMITED = 10000;

	private static final String _DXP = "DXP";

	private static final String _DXP_BACKUP = "DXP Backup";

	private static final String _DXP_DEVELOPMENT = "DXP Development";

	private static final String _DXP_ENTERPRISE =
		"DXP Unlimited Enterprise-Wide";

	private static final String _DXP_LIMITED = "DXP Limited";

	private static final String _DXP_NON_PRODUCTION = "DXP Non-Production";

	private static final String _DXP_PRODUCTION = "DXP Production";

	private static final String _ELASTIC_ACTIVATION = "Elastic Activation";

	private static final Date _END_DATE_PERPETUAL = new Date(4102444800000L);

	private static final String _PORTAL_BACKUP = "Portal Backup";

	private static final String _PORTAL_DEVELOPMENT = "Portal Development";

	private static final String _PORTAL_ENTERPRISE = "Portal Enterprise";

	private static final String _PORTAL_LIMITED = "Portal Limited";

	private static final String _PORTAL_NON_PRODUCTION =
		"Portal Non-Production";

	private static final String _PORTAL_PRODUCTION = "Portal Production";

	private static final Date _START_DATE_PERPETUAL = new Date(0L);

	private static final String _URL_API_JSONWS =
		"/osb-lcs-gateway-web/api/jsonws";

	private static final String _URL_API_JSONWS_LCS_GATEWAY =
		_URL_API_JSONWS + "/lcsgateway";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSSubscriptionEntryWebServiceImpl.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private JSONFactory _jsonFactory;

	private JSONWebServiceClient _jsonWebServiceClient;

	@Reference
	private JSONWebServiceClientFactory _jsonWebServiceClientFactory;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

}
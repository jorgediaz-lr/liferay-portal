package com.liferay.osb.commerce.provisioning.theme.internal.product.renderer.list;

import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.content.render.list.CPContentListRenderer;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.osb.commerce.provisioning.theme.internal.helper.OSBCommerceThemeHttpHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {
		"commerce.product.content.list.renderer.key=" + OSBCommerceProvisioningCPContentListRenderer.KEY,
		"commerce.product.content.list.renderer.order=1000",
		"commerce.product.content.list.renderer.portlet.name=" + CPPortletKeys.CP_PUBLISHER_WEB
	}
)
public class OSBCommerceProvisioningCPContentListRenderer
	implements CPContentListRenderer {

	public static final String KEY = "osb-commerce-provisioning-list";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "osb-commerce-provisioning");
	}

	@Override
	public void render(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) throws Exception {

		httpServletRequest.setAttribute(
			"osb-commerce-provisioning:CPContentList",
			_getCPEntriesRenderProps(httpServletRequest));

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/product_publisher/render/view.jsp");
	}

	private Map<String, Object> _getCPEntriesRenderProps(
		HttpServletRequest httpServletRequest)
		throws PortalException, WindowStateException {

		CPDataSourceResult cpDataSourceResult = (CPDataSourceResult)
			httpServletRequest.getAttribute(CPWebKeys.CP_DATA_SOURCE_RESULT);

		List<Map<String, Object>> cpEntriesRenderProps = new ArrayList<>();

		for (CPCatalogEntry cpCatalogEntry : cpDataSourceResult.getCPCatalogEntries()) {
			cpEntriesRenderProps.add(
				_getCPEntryRenderProps(cpCatalogEntry, httpServletRequest));
		}

		PortletURL checkoutPortletURL = _commerceOrderHttpHelper
			.getCommerceCheckoutPortletURL(httpServletRequest);

		checkoutPortletURL.setWindowState(LiferayWindowState.MAXIMIZED);

		return new HashMap<String, Object>() {{
			put("CPEntries", cpEntriesRenderProps);
			put("checkoutURL", checkoutPortletURL.toString());
			put("commerceAccountId", _commerceThemeHttpHelper
				.getCurrentCommerceAccountId(httpServletRequest));
		}};
	}

	private Map<String, Object> _getCPEntryRenderProps(
		CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
		throws PortalException {

		List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();

		CPSku cpSku = null;

		if (cpSkus.size() == 1) {
			cpSku = cpSkus.get(0);
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay) request.getAttribute(
				WebKeys.THEME_DISPLAY);

		Map<String, Object> renderProps = new HashMap<>();

		renderProps.put("description", cpCatalogEntry.getShortDescription());

		renderProps.put(
			"detailURL",
			_cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay));

		renderProps.put("name", cpCatalogEntry.getName());
		renderProps.put(
			"productImageURL", cpCatalogEntry.getDefaultImageFileUrl());
		renderProps.put("productId", cpCatalogEntry.getCPDefinitionId());

		if (cpSku != null) {
			renderProps.put("sku", cpSku.getSku());
			renderProps.put("skuId", cpSku.getCPInstanceId());
		}

		renderProps.put(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");

		return renderProps;
	}

	@Reference
	private CPContentHelper _cpContentHelper;

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private OSBCommerceThemeHttpHelper _commerceThemeHttpHelper;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.osb.commerce.provisioning.theme.impl)"
	)
	private ServletContext _servletContext;
}
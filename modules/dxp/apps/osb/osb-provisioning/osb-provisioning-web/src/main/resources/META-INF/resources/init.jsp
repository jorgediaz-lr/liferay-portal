<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team" %><%@
page import="com.liferay.osb.provisioning.constants.ProductTypeConstants" %><%@
page import="com.liferay.osb.provisioning.constants.ProvisioningPortletKeys" %><%@
page import="com.liferay.osb.provisioning.constants.ProvisioningWebKeys" %><%@
page import="com.liferay.osb.provisioning.exception.ContactRequiredException" %><%@
page import="com.liferay.osb.provisioning.exception.ProductBundleNameException" %><%@
page import="com.liferay.osb.provisioning.exception.ProductPurchaseQuantityException" %><%@
page import="com.liferay.osb.provisioning.exception.RequiredProductException" %><%@
page import="com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException" %><%@
page import="com.liferay.osb.provisioning.model.ProductBundle" %><%@
page import="com.liferay.osb.provisioning.service.ProductBundleLocalServiceUtil" %><%@
page import="com.liferay.osb.provisioning.web.internal.configuration.ProvisioningWebConfiguration" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AccountSearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AssignAccountTeamDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AssignProductBundleProductsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AssignTeamContactsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.AuditEntryDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ContactSearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.EditProductPurchaseDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseViewDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ProductSearchDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountContactsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountLiferayWorkersDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountRelatedAccountsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountTeamsDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewAccountsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewContactDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewContactsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewProductsManagementToolbarDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewSubscriptionDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.display.context.ViewTeamDisplayContext" %><%@
page import="com.liferay.osb.provisioning.web.internal.util.ProvisioningWebComponentProvider" %><%@
page import="com.liferay.petra.json.web.service.client.JSONWebServiceException" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchCountryException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchRegionException" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Address" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.vulcan.util.TransformUtil" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ResourceURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
ProvisioningWebConfiguration provisioningWebConfiguration = (ProvisioningWebConfiguration)liferayPortletRequest.getAttribute(ProvisioningWebConfiguration.class.getName());
%>

<aui:script>
	window.ProvisioningConstants = {
		namespace: '${renderResponse.namespace}',
		noteFormat: {
			html: '<%= Note.Format.HTML %>',
			plaintext: '<%= Note.Format.PLAIN %>'
		},
		noteStatus: {
			approved: '<%= Note.Status.APPROVED %>',
			archived: '<%= Note.Status.ARCHIVED %>'
		},
		noteType: {
			general: '<%= Note.Type.GENERAL %>',
			sales: '<%= Note.Type.SALES %>'
		}
	};
</aui:script>
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

<%@ include file="/init.jsp" %>

<liferay-util:include page="/common/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
String redirect = ParamUtil.getString(request, "redirect");

EditLicenseKeyDisplayContext editLicenseKeyDisplayContext = ProvisioningWebComponentProvider.getEditLicenseKeyDisplayContext(renderRequest, renderResponse, request);

LicenseKey licenseKey = editLicenseKeyDisplayContext.getLicenseKey();

LicenseKeyDisplay licenseKeyDisplay = editLicenseKeyDisplayContext.getLicenseKeyDisplay();

String licenseProductPurchaseKey = StringPool.BLANK;

if (Validator.isNotNull(licenseKey.getProductPurchaseKey())) {
	licenseProductPurchaseKey = licenseKey.getProductPurchaseKey();
}
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= licenseKey.getOwner() %>"
	/>

	<aui:form action="<%= editLicenseKeyDisplayContext.getEditLicenseKeyURL() %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="licenseKeyId" type="hidden" value="<%= licenseKeyDisplay.getLicenseKeyId() %>" />
		<aui:input name="clusterLicenseKeyId" type="hidden" />
		<aui:input name="productPurchaseKey" type="hidden" value="<%= licenseProductPurchaseKey %>" />
		<aui:input name="complimentary" type="hidden" value="<%= licenseKey.isComplimentary() %>" />
		<aui:input name="active" type="hidden" value="<%= licenseKey.isActive() %>" />
		<aui:input name="startDate" type="hidden" />
		<aui:input name="endDate" type="hidden" />

		<div class="sheet">
			<aui:row>
				<aui:col md="4">
					<liferay-ui:message key="product" />:

					<%= HtmlUtil.escape(licenseKeyDisplay.getProductName()) %>
				</aui:col>

				<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisable() %>">
					<aui:col md="4">
						<liferay-ui:message key="owner" />:

						<%= HtmlUtil.escape(licenseKeyDisplay.getOwner()) %>
					</aui:col>

					<aui:col md="4">
						<liferay-ui:message key="status" />:

						<span class="label <%= licenseKeyDisplay.getStatusStyle() %>"><%= licenseKeyDisplay.getStatus() %>
					</aui:col>
				</c:if>

				<aui:col md="4">
					<liferay-ui:message key="version" />:

					<%= HtmlUtil.escape(licenseKeyDisplay.getProductVersion()) %>
				</aui:col>

				<aui:col md="4">
					<liferay-ui:message key="created-by" />:

					<%= HtmlUtil.escape(licenseKeyDisplay.getUserName()) %>
				</aui:col>

				<aui:col md="4">
					<liferay-ui:message key="start-date" />:

					<%= HtmlUtil.escape(licenseKeyDisplay.getStartDate()) %>
				</aui:col>

				<aui:col md="4">
					<liferay-ui:message key="type" />:

					<%= HtmlUtil.escape(licenseKeyDisplay.getType()) %>
				</aui:col>

				<aui:col md="4">
					<liferay-ui:message key="create-date" />:

					<%= HtmlUtil.escape(licenseKeyDisplay.getCreateDate()) %>
				</aui:col>

				<aui:col md="4">
					<%= editLicenseKeyDisplayContext.getExpirationDateLabel() %>:

					<%= HtmlUtil.escape(licenseKeyDisplay.getExpirationDate()) %>
				</aui:col>

				<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisable() %>">
					<aui:col md="4">
						<liferay-ui:message key="description" />:

						<%= HtmlUtil.escape(licenseKeyDisplay.getDescription()) %>
					</aui:col>
				</c:if>

				<aui:col md="8">
					<liferay-ui:message key="last-modified" />:

					<%= HtmlUtil.escape(editLicenseKeyDisplayContext.getLastModifiedUserNameDate()) %>
				</aui:col>

				<c:if test="<%= editLicenseKeyDisplayContext.isMaximumConnectionsVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="maximum-connections" />:

						<%= licenseKey.getMaxHttpSessions() %>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isMaximumConcurrentUsersVisable() %>">
					<aui:col md="4">
						<liferay-ui:message key="maximum-concurrent-users" />:

						<%= HtmlUtil.escape(licenseKeyDisplay.getMaxConcurrentUsersLabel()) %>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isMaximumUsersVisable() %>">
					<aui:col md="4">
						<liferay-ui:message key="maximum-users" />:

						<%= HtmlUtil.escape(licenseKeyDisplay.getMaxUsersLabel()) %>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isMaxmumServersVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="maximum-servers" />:

						<%= licenseKey.getMaxServers() %>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isMacAddressesVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="mac-addresses" />:

						<c:choose>
							<c:when test="<%= licenseKey.getLicenseVersion() >= 3 %>">

								<%
								String[] macAddresses = StringUtil.split(licenseKey.getMacAddresses());

								if (macAddresses.length > 0) {
									for (int i = 0; i < macAddresses.length; i++) {
								%>

										<%= HtmlUtil.escape(macAddresses[i]) %><%= ((i + 1) < macAddresses.length) ? " " : "" %>

								<%
									}
								}
								else {
								%>

									-

								<%
								}
								%>

							</c:when>
							<c:otherwise>

								<%
								List<LicenseKey> clusterLicenseKeys = editLicenseKeyDisplayContext.getClusterLicenseKeys();

								if (!clusterLicenseKeys.isEmpty()) {
									for (int i = 0; i < clusterLicenseKeys.size(); i++) {
										LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);

										LicenseKeyDisplay clusterLicenseKeyDisplay = new LicenseKeyDisplay(renderRequest, renderResponse, clusterLicenseKey);
								%>

										<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getServerId()) %><%= ((i + 1) < clusterLicenseKeys.size()) ? " " : "" %>

								<%
									}
								}
								else {
								%>

									-

								<%
								}
								%>

							</c:otherwise>
						</c:choose>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isIpAddressesVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="ip-addresses" />:

							<%
							String[] ipAddresses = StringUtil.split(licenseKey.getIpAddresses());

							if (ipAddresses.length > 0) {
								for (int i = 0; i < ipAddresses.length; i++) {
							%>

									<%= HtmlUtil.escape(ipAddresses[i]) %><%= ((i + 1) < ipAddresses.length) ? " " : "" %>

							<%
								}
							}
							else {
							%>

								-

							<%
							}
							%>

					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isHostNameVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="host-name" />:

						<%= HtmlUtil.escape(licenseKeyDisplay.getHostName()) %>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isServerIdVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="server-id" />:

						<%= HtmlUtil.escape(licenseKeyDisplay.getServerId()) %>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isComplimentaryVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="complimentary" />:

						<liferay-ui:message key="<%= licenseKeyDisplay.isComplimentaryLabel() %>" />
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isKeyVisable() %>">
					<aui:col md="12">
						<liferay-ui:message key="key" />:

						<%= HtmlUtil.escape(licenseKey.getKey()) %>
					</aui:col>
				</c:if>

				<c:if test="<%= editLicenseKeyDisplayContext.isClusterLicenseKeyVisable() %>">

					<%
					List<LicenseKey> clusterLicenseKeys = editLicenseKeyDisplayContext.getClusterLicenseKeys();

					for (int i = 0; i < clusterLicenseKeys.size(); i++) {
						LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);

						LicenseKeyDisplay clusterLicenseKeyDisplay = new LicenseKeyDisplay(renderRequest, renderResponse, clusterLicenseKey);
					%>

						<aui:col md="4">
							<liferay-ui:message key="owner" />:

							<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getOwner()) %>
						</aui:col>

						<aui:col md="4">
							<liferay-ui:message key="status" />:

							<span class="label <%= clusterLicenseKeyDisplay.getStatusStyle() %>"><%= clusterLicenseKeyDisplay.getStatus() %>
						</aui:col>

						<aui:col md="12">
							<liferay-ui:message key="description" />:

							<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getDescription()) %>
						</aui:col>

						<aui:col md="12">
							<liferay-ui:message key="mac-addresses" />:

								<%
								String[] clusterMacAddresseses = StringUtil.split(clusterLicenseKey.getMacAddresses());

								if (clusterMacAddresseses.length > 0) {
									for (int j = 0; j < clusterMacAddresseses.length; j++) {
								%>

										<%= HtmlUtil.escape(clusterMacAddresseses[j]) %><%= ((j + 1) < clusterMacAddresseses.length) ? " " : "" %>

								<%
									}
								}
								else {
								%>

									-

								<%
								}
								%>

						</aui:col>

						<aui:col md="12">
							<liferay-ui:message key="ip-addresses" />:

								<%
								String[] clusterIpAddresses = StringUtil.split(clusterLicenseKey.getIpAddresses());

								if (clusterIpAddresses.length > 0) {
									for (int j = 0; j < clusterIpAddresses.length; j++) {
								%>

										<%= HtmlUtil.escape(clusterIpAddresses[j]) %><%= ((j + 1) < clusterIpAddresses.length) ? " " : "" %>

								<%
									}
								}
								else {
								%>

									-

								<%
								}
								%>

						</aui:col>

						<aui:col md="12">
							<liferay-ui:message key="host-name" />:

							<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getHostName()) %>
						</aui:col>

						<aui:col md="12">
							<div class="button-holder">

								<%
								String clusterUpdateActive = "activate";

								if (clusterLicenseKey.isActive()) {
									clusterUpdateActive = "deactivate";
								}
								%>

								<button class="btn" onclick="<portlet:namespace />updateValues(<%= !clusterLicenseKey.isActive() %>, <%= clusterLicenseKey.isComplimentary() %>, '<%= clusterLicenseKey.getLicenseKeyId() %>');" type="button">
									<liferay-ui:message key="<%= clusterUpdateActive %>" />
								</button>

								<portlet:resourceURL id="/licenses/download_license_key" var="downloadClusterLicenseKeyURL">
									<portlet:param name="licenseKeyId" value="<%= String.valueOf(clusterLicenseKey.getLicenseKeyId()) %>" />
								</portlet:resourceURL>

								<a class="btn" href="<%= downloadClusterLicenseKeyURL %>" type="button">
									<clay:icon
										symbol="download"
									/>
								</a>
							</div>
						</aui:col>

					<%
					}
					%>

				</c:if>

				<aui:col md="12">
					<div class="button-holder">
						<c:if test="<%= editLicenseKeyDisplayContext.isRenewVisable() %>">
							<button class="btn" onclick="<portlet:namespace />renewLicenseKey();" type="button">
								<liferay-ui:message key="renew" />
							</button>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isComplimentaryVisable() %>">
							<button class="btn" onclick="<portlet:namespace />updateValues(<%= licenseKey.isActive() %>, <%= !licenseKey.isComplimentary() %>);" type="button">
								<liferay-ui:message key="<%= editLicenseKeyDisplayContext.getUpdateComplimentaryLabel() %>" />
							</button>
						</c:if>

						<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisable() %>">
							<button class="btn" onclick="<portlet:namespace />updateValues(<%= !licenseKey.isActive() %>, <%= licenseKey.isComplimentary() %>);" type="button">
								<liferay-ui:message key="<%= editLicenseKeyDisplayContext.getUpdateActiveLabel() %>" />
							</button>
						</c:if>

						<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisable() %>">
							<button class="btn" onclick="<portlet:namespace />moveLicenseKey('<%= editLicenseKeyDisplayContext.getMoveLicenseKeyURL() %>');" type="button">
								<clay:icon
									symbol="move-folder"
								/>
							</button>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isDownloadVisable() %>">
							<a class="btn" href="<%= editLicenseKeyDisplayContext.getDownloadLicenseKeyURL() %>" type="button">
								<clay:icon
									symbol="download"
								/>
							</a>
						</c:if>
					</div>
				</aui:col>
			</aui:row>
		</div>
	</aui:form>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />renewLicenseKey',
		function() {
			var A = AUI();

			<portlet:renderURL var="renewLicenseKeyURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/licenses/renew_license_key" />
			</portlet:renderURL>

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: 'selectedItemChange',
				strings: {
					add: '<liferay-ui:message key="renew" />',
					cancel: '<liferay-ui:message key="cancel" />'
				},
				title: '<liferay-ui:message key="renew" />',
				url: '<%= renewLicenseKeyURL %>'
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);

	<portlet:namespace />moveLicenseKey = function(url) {
		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					modal: true
				},
				eventName: 'moveLicenseKey',
				title: '<liferay-ui:message key="move-license" />',
				uri: url
			},
			function(event) {
				var productPurchaseKeyField = document.getElementById(
					'<portlet:namespace />productPurchaseKey'
				);

				if (productPurchaseKeyField) {
					productPurchaseKeyField.value = event.productpurchasekey;
				}

				var form = document.getElementById('<portlet:namespace />fm');

				if (form) {
					form.submit();
				}
			}
		);
	};

	function <portlet:namespace />updateValues(
		active,
		complimentary,
		clusterLicenseKeyId
	) {
		var activeField = document.getElementById('<portlet:namespace />active');

		if (activeField) {
			activeField.value = active;
		}

		var complimentaryField = document.getElementById(
			'<portlet:namespace />complimentary'
		);

		if (complimentaryField) {
			complimentaryField.value = complimentary;
		}

		var clusterLicenseKeyIdField = document.getElementById(
			'<portlet:namespace />clusterLicenseKeyId'
		);

		if (clusterLicenseKeyIdField) {
			clusterLicenseKeyIdField.value = clusterLicenseKeyId;
		}

		var form = document.getElementById('<portlet:namespace />fm');

		if (form) {
			form.submit();
		}
	}
</aui:script>
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

LicenseKey licenseKey = (LicenseKey)renderRequest.getAttribute(ProvisioningWebKeys.LICENSE_KEY);

LicenseKeyDisplay licenseKeyDisplay = new LicenseKeyDisplay(renderRequest, renderResponse, licenseKey);

List<LicenseKey> clusterLicenseKeys = new ArrayList<>();

String licenseType = licenseKey.getLicenseEntryType();

if (licenseType.equals(LicenseType.CLUSTER)) {
	clusterLicenseKeys = (List<LicenseKey>)renderRequest.getAttribute(ProvisioningWebKeys.LICENSE_KEYS);
}

String updateComplimentary = "make-complimentary";

if (licenseKey.isComplimentary()) {
	updateComplimentary = "remove-complimentary";
}

String updateActive = "activate";

if (licenseKey.isActive()) {
	updateActive = "deactivate";
}

PortletURL moveLicensekeyURL = renderResponse.createRenderURL();

moveLicensekeyURL.setWindowState(LiferayWindowState.POP_UP);
moveLicensekeyURL.setParameter("mvcRenderCommandName", "/licenses/move_license_key");
moveLicensekeyURL.setParameter("licenseKeyId", String.valueOf(licenseKey.getLicenseKeyId()));
%>

<div class="add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= licenseKey.getOwner() %>"
	/>

	<portlet:actionURL name="/licenses/edit_license_key" var="editLicenseKeyURL" />

	<aui:form action="<%= editLicenseKeyURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="licenseKeyId" type="hidden" value="<%= licenseKey.getLicenseKeyId() %>" />
		<aui:input name="clusterLicenseKeyId" type="hidden" />
		<aui:input name="productPurchaseKey" type="hidden" value="<%= licenseKey.getProductPurchaseKey() %>" />
		<aui:input name="complimentary" type="hidden" value="<%= licenseKey.isComplimentary() %>" />
		<aui:input name="active" type="hidden" value="<%= licenseKey.isActive() %>" />
		<aui:input name="startDate" type="hidden" />
		<aui:input name="endDate" type="hidden" />

		<div class="sheet">
			<aui:row>
				<aui:col md="4">
					<span><liferay-ui:message key="product" />:</span>

					<%= HtmlUtil.escape(licenseKey.getProductName()) %>
				</aui:col>

				<c:if test="<%= (licenseKey.getLicenseVersion() < 3) || !licenseType.equals(LicenseType.CLUSTER) %>">
					<aui:col md="4">
						<span><liferay-ui:message key="owner" />:</span>

						<%= HtmlUtil.escape(licenseKey.getOwner()) %>
					</aui:col>

					<aui:col md="4">
						<span><liferay-ui:message key="status" />:</span>

						<span class="label <%= licenseKeyDisplay.getStatusStyle() %>"><%= licenseKeyDisplay.getStatus() %></span>
					</aui:col>
				</c:if>

				<aui:col md="4">
					<span><liferay-ui:message key="version" />:</span>

					<%= licenseKey.getProductVersion() %>
				</aui:col>

				<aui:col md="4">
					<span><liferay-ui:message key="created-by" />:</span>

					<%= HtmlUtil.escape(licenseKey.getUserName()) %>
				</aui:col>

				<aui:col md="4">
					<span><liferay-ui:message key='<%= licenseType.equals(LicenseType.TRIAL)? "registration": "start-date" %>' />:</span>

					<%= licenseKeyDisplay.getStartDate() %>
				</aui:col>

				<aui:col md="4">
					<span><liferay-ui:message key="type" />:</span>

					<%= LanguageUtil.get(request, licenseType) %>
				</aui:col>

				<aui:col md="4">
					<span><liferay-ui:message key="create-date" />:</span>

					<%= licenseKeyDisplay.getCreateDate() %>
				</aui:col>

				<aui:col md="4">
					<span><liferay-ui:message key='<%= licenseType.equals(LicenseType.TRIAL)? "lifetime": "expiration-date" %>' />:</span>

					<%= licenseKeyDisplay.getExpirationDate() %>
				</aui:col>

				<aui:col md="4">
					<span><liferay-ui:message key="description" />:</span>

					<%= HtmlUtil.escape(licenseKey.getDescription()) %>
				</aui:col>

				<aui:col md="8">
					<span><liferay-ui:message key="last-modified" />:</span>

					<%= HtmlUtil.escape(licenseKey.getModifiedUserName()) %> <liferay-ui:message key="on" /> <%= licenseKeyDisplay.getModifiedDate() %>
				</aui:col>

				<c:choose>
					<c:when test="<%= licenseKey.getLicenseVersion() >= 3 %>">
						<c:if test="<%= licenseType.equals(LicenseType.DEVELOPER) || licenseType.equals(LicenseType.DEVELOPER_CLUSTER) %>">
							<aui:col md="12">
								<span><liferay-ui:message key="maximum-connections" />:</span>

								<%= licenseKey.getMaxHttpSessions() %>
							</aui:col>
						</c:if>

						<c:if test="<%= licenseType.equals(LicenseType.PER_USER) %>">
							<aui:col md="4">
								<span><liferay-ui:message key="maximum-concurrent-users" />:</span>

								<%= licenseKeyDisplay.getMaxConcurrentUsersLabel() %>
							</aui:col>

							<aui:col md="4">
								<span><liferay-ui:message key="maximum-users" />:</span>

								<%= licenseKeyDisplay.getMaxUsersLabel() %>
							</aui:col>
						</c:if>

						<c:if test="<%= licenseType.equals(LicenseType.CLUSTER) %>">
							<aui:col md="12">
								<span><liferay-ui:message key="maximum-servers" />:</span>

								<%= licenseKey.getMaxServers() %>
							</aui:col>
						</c:if>

						<c:choose>
							<c:when test="<%= licenseType.equals(LicenseType.CLUSTER) %>">

								<%
								for (int i = 0; i < clusterLicenseKeys.size(); i++) {
									LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);

									LicenseKeyDisplay clusterLicenseKeyDisplay = new LicenseKeyDisplay(renderRequest, renderResponse, clusterLicenseKey);
								%>

									<div></div>

									<aui:col md="4">
										<span><liferay-ui:message key="owner" />:</span>

										<%= HtmlUtil.escape(clusterLicenseKey.getOwner()) %>
									</aui:col>

									<aui:col md="4">
										<span><liferay-ui:message key="status" />:</span>

										<span class="label <%= clusterLicenseKeyDisplay.getStatusStyle() %>"><%= clusterLicenseKeyDisplay.getStatus() %></span>
									</aui:col>

									<aui:col md="12">
										<span><liferay-ui:message key="description" />:</span>

										<%= HtmlUtil.escape(clusterLicenseKey.getDescription()) %>
									</aui:col>

									<aui:col md="12">
										<span><liferay-ui:message key="mac-addresses" />:</span>

										<span>

											<%
											String[] clusterMacAddresseses = StringUtil.split(clusterLicenseKey.getMacAddresses());

											for (int j = 0; j < clusterMacAddresseses.length; j++) {
											%>

												<%= clusterMacAddresseses[j] %><%= ((j + 1) < clusterMacAddresseses.length) ? "<br />" : "" %>

											<%
											}
											%>

										</span>
									</aui:col>

									<aui:col md="12">
										<span><liferay-ui:message key="ip-addresses" />:</span>

										<span>

											<%
											String[] clusterIpAddresses = StringUtil.split(clusterLicenseKey.getIpAddresses());

											for (int j = 0; j < clusterIpAddresses.length; j++) {
											%>

												<%= clusterIpAddresses[j] %><%= ((j + 1) < clusterIpAddresses.length) ? "<br />" : "" %>

											<%
											}
											%>

										</span>
									</aui:col>

									<aui:col md="12">
										<span><liferay-ui:message key="host-name" />:</span>

										<%= HtmlUtil.escape(clusterLicenseKey.getHostName()) %>
									</aui:col>

									<aui:col md="12">
										<div class="button-holder">
											<span><liferay-ui:message key="download" /></span>

											<%
											String clusterUpdateActive = clusterLicenseKey.isActive() ? "deactivate" : "activate";
											%>

											<button class="btn" onclick="<portlet:namespace />updateActive('<%= clusterUpdateActive %>', '<%= clusterLicenseKey.getLicenseKeyId() %>');" type="button">
												<liferay-ui:message key="<%= clusterUpdateActive %>" />
											</button>
										</div>
									</aui:col>

								<%
								}
								%>

								<div></div>

								<aui:col md="12">
									<span><liferay-ui:message key="add-new-cluster-key" /></span>
								</aui:col>
							</c:when>
							<c:otherwise>
								<c:if test="<%= licenseType.equals(LicenseType.LIMITED) || licenseType.equals(LicenseType.PER_USER) || licenseType.equals(LicenseType.PRODUCTION) %>">
									<aui:col md="12">
										<span><liferay-ui:message key="mac-addresses" />:</span>

										<span>

											<%
											String[] macAddresses = StringUtil.split(licenseKey.getMacAddresses());

											for (int i = 0; i < macAddresses.length; i++) {
											%>

												<%= macAddresses[i] %><%= ((i + 1) < macAddresses.length) ? "<br />" : "" %>

											<%
											}
											%>

										</span>
									</aui:col>

									<aui:col md="12">
										<span><liferay-ui:message key="ip-addresses" />:</span>

										<span>

											<%
											String[] ipAddresses = StringUtil.split(licenseKey.getIpAddresses());

											for (int i = 0; i < ipAddresses.length; i++) {
											%>

												<%= ipAddresses[i] %><%= ((i + 1) < ipAddresses.length) ? "<br />" : "" %>

											<%
											}
											%>

										</span>
									</aui:col>

									<aui:col md="12">
										<span><liferay-ui:message key="host-name" />:</span>

										<%= HtmlUtil.escape(licenseKey.getHostName()) %>
									</aui:col>

									<%
									String serverId = licenseKey.getServerId();
									%>

									<c:if test="<%= Validator.isNotNull(serverId) %>">
										<aui:col md="12">
											<span><liferay-ui:message key="server-id" />:</span>

											<%= serverId %>
										</aui:col>
									</c:if>
								</c:if>

								<aui:col md="12">
									<span><liferay-ui:message key="complimentary" />:</span>

									<liferay-ui:message key='<%= licenseKey.isComplimentary() ? "yes": "no" %>' />
								</aui:col>

								<aui:col md="12">
									<div class="button-holder">
										<c:if test="<%= licenseKey.canRenew() %>">
											<button class="btn" onclick="<portlet:namespace />renewLicenseKey();" type="button">
												<liferay-ui:message key="renew" />
											</button>
										</c:if>

										<button class="btn" onclick="<portlet:namespace />updateComplimentary('<%= updateComplimentary %>');" type="button">
											<liferay-ui:message key="<%= updateComplimentary %>" />
										</button>

										<button class="btn" onclick="<portlet:namespace />updateActive('<%= updateActive %>', null);" type="button">
											<liferay-ui:message key="<%= updateActive %>" />
										</button>

										<c:if test="<%= licenseKey.isActive() %>">
											<span><liferay-ui:message key="download" /></span>
										</c:if>

										<button class="btn" onclick="<portlet:namespace />moveLicenseKey('<%= moveLicensekeyURL %>');" type="button">
											<clay:icon
												symbol="move-folder"
											/>
										</button>
									</div>
								</aui:col>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="<%= licenseKey.getLicenseVersion() == 2 %>">
								<c:choose>
									<c:when test="<%= licenseType.equals(LicenseType.CLUSTER) || licenseType.equals(LicenseType.DEVELOPER_CLUSTER) %>">
										<aui:col md="12">
											<span><liferay-ui:message key="maximum-servers" />:</span>

											<%= licenseKey.getMaxServers() %>
										</aui:col>
									</c:when>
									<c:when test="<%= licenseType.equals(LicenseType.PRODUCTION) %>">
										<aui:col md="12">
											<span><liferay-ui:message key="mac-addresses" />:</span>

											<%
											for (int i = 0; i < clusterLicenseKeys.size(); i++) {
												LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);
											%>

												<%= clusterLicenseKey.getServerId() %><%= ((i + 1) < clusterLicenseKeys.size()) ? "<br />" : "" %>

											<%
											}
											%>

										</aui:col>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="<%= licenseType.equals(LicenseType.CLUSTER) || licenseType.equals(LicenseType.DEVELOPER_CLUSTER) %>">
										<aui:col md="12">
											<span><liferay-ui:message key="mac-addresses" />:</span>

											<%
											for (int i = 0; i < clusterLicenseKeys.size(); i++) {
												LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);
											%>

												<%= clusterLicenseKey.getServerId() %><%= ((i + 1) < clusterLicenseKeys.size()) ? "<br />" : "" %>

											<%
											}
											%>

										</aui:col>
									</c:when>
									<c:when test="<%= licenseType.equals(LicenseType.PRODUCTION) %>">
										<aui:col md="12">
											<span><liferay-ui:message key="server-id" />:</span>

											<%= licenseKey.getServerId() %>
										</aui:col>
									</c:when>
								</c:choose>

								<aui:col md="12">
									<span><liferay-ui:message key="key" />:</span>

									<%= licenseKey.getKey() %>
								</aui:col>
							</c:otherwise>
						</c:choose>

						<aui:col md="12">
							<div class="button-holder">
								<button class="btn" onclick="<portlet:namespace />updateActive('<%= updateActive %>', null);" type="button">
									<liferay-ui:message key="<%= updateActive %>" />
								</button>

								<c:if test="<%= licenseKey.isActive() && ((licenseKey.getLicenseVersion() == 2) || licenseType.equals(LicenseType.CLUSTER) || licenseType.equals(LicenseType.DEVELOPER_CLUSTER)) %>">
									<span><liferay-ui:message key="download" /></span>
								</c:if>

								<button class="btn" onclick="<portlet:namespace />moveLicenseKey('<%= moveLicensekeyURL %>');" type="button">
									<clay:icon
										symbol="move-folder"
									/>
								</button>
							</div>
						</aui:col>
					</c:otherwise>
				</c:choose>
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
				document.getElementById(
					'<portlet:namespace />productPurchaseKey'
				).value = event.productpurchasekey;

				document.getElementById('<portlet:namespace />fm').submit();
			}
		);
	};

	function <portlet:namespace />updateActive(action, clusterLicenseKeyId) {
		var confirmMessage =
			action == 'activate'
				? '<liferay-ui:message key="are-you-sure-you-want-to-activate-this-license-key" />'
				: '<liferay-ui:message key="are-you-sure-you-want-to-deactivate-this-license-key" />';

		if (confirm(confirmMessage)) {
			document.getElementById('<portlet:namespace />active').value =
				action == 'activate' ? true : false;

			document.getElementById(
				'<portlet:namespace />clusterLicenseKeyId'
			).value = clusterLicenseKeyId ? clusterLicenseKeyId : '';

			document.getElementById('<portlet:namespace />fm').submit();
		}
	}

	function <portlet:namespace />updateComplimentary(action) {
		var confirmMessage =
			action == 'make-complimentary'
				? '<liferay-ui:message key="are-you-sure-you-want-to-make-complimentary-for-this-license-key" />'
				: '<liferay-ui:message key="are-you-sure-you-want-to-remove-complimentary-for-this-license-key" />';

		if (confirm(confirmMessage)) {
			document.getElementById('<portlet:namespace />complimentary').value =
				action == 'make-complimentary' ? true : false;

			document.getElementById('<portlet:namespace />fm').submit();
		}
	}
</aui:script>
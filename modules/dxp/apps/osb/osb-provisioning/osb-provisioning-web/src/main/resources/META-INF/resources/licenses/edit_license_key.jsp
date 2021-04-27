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

<div class="add-items edit-license">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= licenseKey.getOwner() %>"
	/>

	<aui:form action="<%= editLicenseKeyDisplayContext.getEditLicenseKeyURL() %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="editLicenseFm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="licenseKeyId" type="hidden" value="<%= licenseKeyDisplay.getLicenseKeyId() %>" />
		<aui:input name="clusterLicenseKeyId" type="hidden" value="0" />
		<aui:input name="productPurchaseKey" type="hidden" value="<%= licenseProductPurchaseKey %>" />
		<aui:input name="complimentary" type="hidden" value="<%= licenseKey.isComplimentary() %>" />
		<aui:input name="active" type="hidden" value="<%= licenseKey.isActive() %>" />
		<aui:input name="startDate" type="hidden" />
		<aui:input name="expirationDate" type="hidden" />

		<div class="add-items-sheet edit-license-sheet sheet">
			<aui:row>
				<aui:col md="4">
					<dl>
						<div>
							<dt>
								<liferay-ui:message key="product" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getProductName()) %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="version" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getProductVersion()) %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="type" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getType()) %>
							</dd>
						</div>
					</dl>

					<dl class="server-id-fields">
						<c:if test="<%= editLicenseKeyDisplayContext.isServerIdVisible() %>">
							<div class="server-id">
								<dt>
									<liferay-ui:message key="server-id" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getServerId()) %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isHostNameVisible() %>">
							<div class="host-name">
								<dt>
									<liferay-ui:message key="host-name" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getHostName()) %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isIpAddressesVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="ip-addresses" />
								</dt>
								<dd>
									<%= licenseKeyDisplay.getIpAddresses() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isMacAddressesVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="mac-addresses" />
								</dt>
								<dd>
									<c:choose>
										<c:when test="<%= licenseKey.getLicenseVersion() >= 3 %>">
											<%= licenseKeyDisplay.getMacAddresses() %>
										</c:when>
										<c:otherwise>
											<%= HtmlUtil.escape(editLicenseKeyDisplayContext.getClusterLicenseKeysDisplay()) %>
										</c:otherwise>
									</c:choose>
								</dd>
							</div>
						</c:if>
					</dl>
				</aui:col>

				<aui:col md="4">
					<dl>
						<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="owner" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getOwner()) %>
								</dd>
							</div>

							<div>
								<dt>
									<liferay-ui:message key="description" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getDescription()) %>
								</dd>
							</div>
						</c:if>
					</dl>

					<dl>
						<c:if test="<%= editLicenseKeyDisplayContext.isKeyVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="key" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKey.getKey()) %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isMaximumConnectionsVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-connections" />
								</dt>
								<dd>
									<%= licenseKey.getMaxHttpSessions() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isMaximumConcurrentUsersVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-concurrent-users" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getMaxConcurrentUsersLabel()) %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isMaximumUsersVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-users" />
								</dt>
								<dd>
									<%= HtmlUtil.escape(licenseKeyDisplay.getMaxUsersLabel()) %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isMaxmumServersVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="maximum-servers" />
								</dt>
								<dd>
									<%= licenseKey.getMaxServers() %>
								</dd>
							</div>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isComplimentaryVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="complimentary" />
								</dt>
								<dd>
									<liferay-ui:message key="<%= licenseKeyDisplay.isComplimentaryLabel() %>" />
								</dd>
							</div>
						</c:if>
					</dl>
				</aui:col>

				<aui:col md="4">
					<dl>
						<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisible() %>">
							<div>
								<dt>
									<liferay-ui:message key="status" />
								</dt>
								<dd>
									<span class="label <%= licenseKeyDisplay.getStatusStyle() %>"><%= licenseKeyDisplay.getStatus() %></span>
								</dd>
							</div>
						</c:if>

						<div>
							<dt>
								<liferay-ui:message key="start-date" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getStartDate()) %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="expiration-date" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getExpirationDate()) %>
							</dd>
						</div>
					</dl>

					<dl>
						<div>
							<dt>
								<liferay-ui:message key="created-by" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getUserName()) %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="create-date" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(licenseKeyDisplay.getCreateDate()) %>
							</dd>
						</div>

						<div>
							<dt>
								<liferay-ui:message key="last-modified" />
							</dt>
							<dd>
								<%= HtmlUtil.escape(editLicenseKeyDisplayContext.getLastModifiedUserNameDate()) %>
							</dd>
						</div>
					</dl>
				</aui:col>

				<c:if test="<%= editLicenseKeyDisplayContext.isClusterLicenseKeyVisible() %>">
					<aui:col cssClass="cluster-licenses" md="12">
						<aui:row>

							<%
							List<LicenseKey> clusterLicenseKeys = editLicenseKeyDisplayContext.getClusterLicenseKeys();

							for (int i = 0; i < clusterLicenseKeys.size(); i++) {
								LicenseKey clusterLicenseKey = clusterLicenseKeys.get(i);

								LicenseKeyDisplay clusterLicenseKeyDisplay = new LicenseKeyDisplay(renderRequest, renderResponse, clusterLicenseKey);
							%>

								<aui:col cssClass="license" md="12">
									<aui:row>
										<aui:col md="4">
											<dl class="server-id-fields">
												<div class="host-name">
													<dt>
														<liferay-ui:message key="host-name" />
													</dt>
													<dd>
														<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getHostName()) %>
													</dd>
												</div>

												<div>
													<dt>
														<liferay-ui:message key="ip-addresses" />
													</dt>
													<dd>
														<%= clusterLicenseKeyDisplay.getIpAddresses() %>
													</dd>
												</div>

												<div>
													<dt>
														<liferay-ui:message key="mac-addresses" />
													</dt>
													<dd>
														<%= clusterLicenseKeyDisplay.getMacAddresses() %>
													</dd>
												</div>
											</dl>
										</aui:col>

										<aui:col md="4">
											<dl>
												<div>
													<dt>
														<liferay-ui:message key="owner" />
													</dt>
													<dd>
														<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getOwner()) %>
													</dd>
												</div>

												<div>
													<dt>
														<liferay-ui:message key="description" />
													</dt>
													<dd>
														<%= HtmlUtil.escape(clusterLicenseKeyDisplay.getDescription()) %>
													</dd>
												</div>
											</dl>
										</aui:col>

										<aui:col md="4">
											<dl>
												<div>
													<dt>
														<liferay-ui:message key="status" />
													</dt>
													<dd>
														<span class="label <%= clusterLicenseKeyDisplay.getStatusStyle() %>">
															<%= clusterLicenseKeyDisplay.getStatus() %>
														</span>
													</dd>
												</div>
											</dl>
										</aui:col>

										<aui:col cssClass="edit-license-actions" md="12">

											<%
											String clusterUpdateActive = "activate";

											if (clusterLicenseKey.isActive()) {
												clusterUpdateActive = "deactivate";
											}
											%>

											<button class="btn btn-secondary btn-sm" onclick="<portlet:namespace />updateValues(<%= !clusterLicenseKey.isActive() %>, <%= clusterLicenseKey.isComplimentary() %>, '<%= clusterLicenseKey.getLicenseKeyId() %>');" type="button">
												<liferay-ui:message key="<%= clusterUpdateActive %>" />
											</button>

											<portlet:resourceURL id="/licenses/download_license_key" var="downloadClusterLicenseKeyURL">
												<portlet:param name="licenseKeyId" value="<%= String.valueOf(clusterLicenseKey.getLicenseKeyId()) %>" />
											</portlet:resourceURL>

											<a class="btn btn-monospaced btn-secondary" href="<%= downloadClusterLicenseKeyURL %>" type="button">
												<clay:icon
													symbol="download"
												/>
											</a>
										</aui:col>
									</aui:row>
								</aui:col>

							<%
							}
							%>

						</aui:row>
					</aui:col>
				</c:if>

				<aui:col cssClass="edit-license-actions" md="12">
					<div>
						<span id="renewLicense">
							<react:component
								data="<%= editLicenseKeyDisplayContext.getRenewLicenseData() %>"
								module="js/RenewLicenseApp"
							/>
						</span>

						<c:if test="<%= editLicenseKeyDisplayContext.isComplimentaryVisible() %>">
							<button class="btn btn-secondary btn-sm" onclick="<portlet:namespace />updateValues(<%= licenseKey.isActive() %>, <%= !licenseKey.isComplimentary() %>);" type="button">
								<liferay-ui:message key="<%= editLicenseKeyDisplayContext.getUpdateComplimentaryLabel() %>" />
							</button>
						</c:if>

						<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisible() %>">
							<button class="btn btn-secondary btn-sm" onclick="<portlet:namespace />updateValues(<%= !licenseKey.isActive() %>, <%= licenseKey.isComplimentary() %>);" type="button">
								<liferay-ui:message key="<%= editLicenseKeyDisplayContext.getUpdateActiveLabel() %>" />
							</button>
						</c:if>
					</div>

					<div>
						<c:if test="<%= !editLicenseKeyDisplayContext.isClusterLicenseKeyVisible() %>">
							<button class="btn btn-monospaced btn-secondary" onclick="<portlet:namespace />moveLicenseKey('<%= editLicenseKeyDisplayContext.getMoveLicenseKeyURL() %>');" type="button">
								<clay:icon
									symbol="move-folder"
								/>
							</button>
						</c:if>

						<c:if test="<%= editLicenseKeyDisplayContext.isDownloadVisible() %>">
							<a class="btn btn-monospaced btn-secondary" href="<%= editLicenseKeyDisplayContext.getDownloadLicenseKeyURL() %>" type="button">
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

				var form = document.getElementById(
					'<portlet:namespace />editLicenseFm'
				);

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

		var form = document.getElementById('<portlet:namespace />editLicenseFm');

		if (form) {
			form.submit();
		}
	}
</aui:script>
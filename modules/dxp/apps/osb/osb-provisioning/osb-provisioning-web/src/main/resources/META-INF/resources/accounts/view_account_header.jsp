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

<%
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<div class="account-header autofit-row provisioning-accounts">
	<svg class="autofit-col header-icon">
		<use xlink:href="#account-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="account-name">
			<span class="account-code">
				<%= HtmlUtil.escape(accountDisplay.getCode()) %>
			</span>

			<%= HtmlUtil.escape(accountDisplay.getName()) %>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="state" />
				</div>

				<span class="label <%= accountDisplay.getSubscriptionStateStyle() %>"><%= accountDisplay.getSubscriptionState() %></span>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="support-region" />
				</div>

				<%= accountDisplay.getRegion() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="country" />
				</div>

				<%= accountDisplay.getPrimaryCountry() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="sla" />
				</div>

				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="tier" />
				</div>

				<%= accountDisplay.getTier() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="ewsa" />
				</div>

				<%= accountDisplay.getEWSA() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="developers" />
				</div>

				<%= accountDisplay.getDeveloperContactUsage() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="partner" />
				</div>

				<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>
			</li>
		</ul>
	</div>

	<div class="left-button">
		<portlet:actionURL name="/accounts/sync_to_lcs" var="syncToLcsURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		</portlet:actionURL>

		<aui:form action="<%= syncToLcsURL %>" method="post" name="fm">
			<aui:button cssClass="btn-secondary btn-sm" href="<%= syncToLcsURL %>" value="sync-to-lcs" />
		</aui:form>
	</div>

	<div>
		<portlet:actionURL name="/accounts/sync_to_zendesk" var="syncToZendeskURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		</portlet:actionURL>

		<aui:form action="<%= syncToZendeskURL %>" method="post" name="fm1">
			<aui:button cssClass="btn-secondary btn-sm" href="<%= syncToZendeskURL %>" value="sync-to-zendesk" />
		</aui:form>
	</div>

	<clay:dropdown-actions
		dropdownItems="<%= viewAccountDisplayContext.getHeaderActionDropdownItems() %>"
		elementClasses="nav-btn"
	/>
</div>

<div class="hide" id="<portlet:namespace />updateStatus">
	<p>
		<%= LanguageUtil.get(request, "are-you-sure-you-want-to-" + viewAccountDisplayContext.getWorkflowStep() + "-the-account-listed-below") %>
	</p>

	<div class="sheet-subtitle">
		<liferay-ui:message key="account" />
	</div>

	<table class="table table-list">
		<tr>
			<th>
				<liferay-ui:message key="name" />
			</th>
			<th>
				<liferay-ui:message key="code" />
			</th>
		</tr>
		<tr>
			<td>
				<%= HtmlUtil.escape(accountDisplay.getName()) %>
			</td>
			<td>
				<%= HtmlUtil.escape(accountDisplay.getCode()) %>
			</td>
		</tr>
	</table>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateStatus',
		function(workflowURL) {
			var A = AUI();

			Liferay.Util.Window.getWindow({
				dialog: {
					bodyContent: A.one('#<portlet:namespace />updateStatus').html(),
					destroyOnHide: true,
					height: 400,
					'toolbars.footer': [
						{
							cssClass: 'btn-secondary',
							label: '<liferay-ui:message key="cancel" />',
							on: {
								click: function() {
									Liferay.Util.getWindow(
										'<portlet:namespace />updateStatusDialog'
									).destroy();
								}
							}
						},
						{
							cssClass: 'btn-primary',
							label:
								'<%= LanguageUtil.get(request, viewAccountDisplayContext.getWorkflowStep() + "-account") %>',
							on: {
								click: function() {
									submitForm(document.hrefFm, workflowURL);
								}
							}
						}
					],
					width: 700
				},
				dialogIframe: {
					bodyCssClass: 'dialog-with-footer'
				},
				id: '<portlet:namespace />updateStatusDialog',
				title:
					'<%= LanguageUtil.get(request, viewAccountDisplayContext.getWorkflowStep() + "-account") %>'
			});
		},
		['liferay-util-window']
	);
</aui:script>
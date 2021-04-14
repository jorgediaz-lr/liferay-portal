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

ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<div class="add-items provisioning-accounts">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="add-address"
	/>

	<liferay-ui:error exception="<%= Problem.ProblemException.class %>">

		<%
		Problem.ProblemException problemException = (Problem.ProblemException)errorException;
		%>

		<%= problemException.getMessage() %>
	</liferay-ui:error>

	<portlet:actionURL name="/accounts/edit_postal_address" var="editPostalAddressURL" />

	<aui:form action="<%= editPostalAddressURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="accountKey" type="hidden" value="<%= accountDisplay.getKey() %>" />

			<aui:model-context model="<%= Address.class %>" />

			<aui:input disabled="<%= true %>" fieldParam="<%= HtmlUtil.escape(accountDisplay.getName()) %>" id="account" inlineLabel="left" name="account" placeholder="<%= HtmlUtil.escape(accountDisplay.getName()) %>" type="text" />

			<div class="form-group form-inline">
				<label class="control-label" for="<portlet:namespace />addressPrimary">
					<liferay-ui:message key="primary" />
				</label>

				<input class="field" id="<portlet:namespace />addressPrimary" name="<portlet:namespace />addressPrimary" type="checkbox" />
			</div>

			<aui:input fieldParam="streetAddressLine1" id="streetAddressLine1" inlineLabel="left" name="street1" required="<%= true %>" />

			<aui:input fieldParam="streetAddressLine2" id="streetAddressLine2" inlineLabel="left" name="street2" />

			<aui:input fieldParam="streetAddressLine3" id="streetAddressLine3" inlineLabel="left" name="street3" />

			<aui:input fieldParam="addressLocality" id="addressLocality" inlineLabel="left" name="city" required="<%= true %>" />

			<aui:select inlineLabel="left" label="country" name="addressCountryId" />

			<aui:select id="addressRegionId" inlineLabel="left" label="region" name="addressRegionId" />

			<div class="form-group form-inline">
				<label class="control-label" for="<portlet:namespace />addressZip">
					<liferay-ui:message key="postal-code" />

					<span hidden id="<portlet:namespace />addressZipRequiredWrapper">
						<aui:icon cssClass="reference-mark text-warning" image="asterisk" markupView="lexicon" />

						<span class="hide-accessible"><liferay-ui:message key="required" /></span>
					</span>
				</label>

				<input class="field form-control lfr-input-text" id="<portlet:namespace />addressZip" name="<portlet:namespace />addressZip" type="text" />
			</div>

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>

<aui:script use="liferay-address,liferay-dynamic-select">
	new Liferay.DynamicSelect([
		{
			select: '<portlet:namespace />addressCountryId',
			selectData: Liferay.Address.getCountries,
			selectDesc: 'nameCurrentValue',
			selectId: 'countryId',
			selectSort: '<%= true %>',
			selectVal: '0'
		},
		{
			select: '<portlet:namespace />addressRegionId',
			selectData: Liferay.Address.getRegions,
			selectDesc: 'name',
			selectId: 'regionId',
			selectVal: '0'
		}
	]);
</aui:script>

<aui:script use="liferay-form">
	/* eslint-env es6 */
	const addressCountry = document.getElementById(
		'<portlet:namespace />addressCountryId'
	);

	function checkCountry(countryId) {
		Liferay.Service(
			'/country/get-country',
			{
				countryId: countryId
			},
			function(response, err) {
				if (err) {
					console.error(err);
				}
				else {
					updateAddressZipRequired(response.zipRequired);
				}
			}
		);
	}

	function handleSelectChange(event) {
		const value = Number(event.currentTarget.value);

		if (value > 0) {
			checkCountry(value);
		}
		else {
			updateAddressZipRequired(false);
		}
	}

	function updateAddressZipRequired(required) {
		const addressZipRequiredWrapper = document.getElementById(
			'<portlet:namespace />addressZipRequiredWrapper'
		);
		const formValidator = Liferay.Form.get('<portlet:namespace />fm')
			.formValidator;

		const rules = formValidator._getAttr('rules');

		if (required) {
			addressZipRequiredWrapper.removeAttribute('hidden');
		}
		else {
			addressZipRequiredWrapper.setAttribute('hidden', true);
		}

		rules.<portlet:namespace />addressZip = {required: required};
	}

	if (addressCountry) {
		addressCountry.addEventListener('change', handleSelectChange);
	}
</aui:script>
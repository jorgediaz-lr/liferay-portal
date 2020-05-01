<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<c:if test='<%= SessionMessages.contains(request, "securityBugsHelper") %>'>
	<liferay-util:buffer
		var="securityBugsMessage"
	>
		<%@ include file="/security_advisory_message.jspf" %>
	</liferay-util:buffer>

	<c:if test="<%= Validator.isNotNull(securityBugsMessage) %>">

		<%
		SecurityBugsHelper securityBugsHelper = (SecurityBugsHelper)SessionMessages.get(request, "securityBugsHelper");

		String securityBugsMessageType = null;

		if (ListUtil.isNotEmpty(securityBugsHelper.getSev1Issues()) || ListUtil.isNotEmpty(securityBugsHelper.getSev2Issues())) {
			securityBugsMessageType = "danger";
		}
		else if (ListUtil.isNotEmpty(securityBugsHelper.getSev3Issues())) {
			securityBugsMessageType = "warning";
		}
		%>

		<liferay-ui:alert
			icon="exclamation-full"
			message="<%= securityBugsMessage %>"
			targetNode="#controlMenuAlertsContainer"
			timeout="<%= 0 %>"
			type="<%= securityBugsMessageType %>"
		/>
	</c:if>
</c:if>
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

<c:if test='<%= SessionMessages.contains(request, "securityBugsIssues") %>'>

	<%
	String securityBugsIssues = (String)SessionMessages.get(request, "securityBugsIssues");
	String securityBugsLevel = (String)SessionMessages.get(request, "securityBugsLevel");
	String securityBugsType = (String)SessionMessages.get(request, "securityBugsType");
	Integer securityBugsNewFixpack = (Integer)SessionMessages.get(request, "securityBugsNewFixpack");
	%>

	<liferay-util:buffer
		var="alertMessage"
	>
		There are <%= securityBugsLevel %> security vulnerabilities not fixed in the system: <%= securityBugsIssues %><br />
		Please, update the patch level of your installation to the fixpack <%= securityBugsNewFixpack %> or a greater one.<br />
		For more information, go to <a href='https://help.liferay.com'>https://help.liferay.com</a>
	</liferay-util:buffer>

	<liferay-ui:alert
		icon="exclamation-full"
		message="<%= alertMessage %>"
		targetNode="#controlMenuAlertsContainer"
		timeout="<%= 0 %>"
		type="<%= securityBugsType %>"
	/>
</c:if>
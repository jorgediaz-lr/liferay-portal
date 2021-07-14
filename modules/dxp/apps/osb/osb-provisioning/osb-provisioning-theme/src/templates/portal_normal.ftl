<!DOCTYPE html>

<#include init />

<#if !stringUtil.equals(portlet_id, "com_liferay_osb_provisioning_web_portlet_AccountsPortlet")>
	<#assign the_title = the_title + " - " + company_name />
</#if>

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid" id="wrapper">
	<#include "${full_templates_path}/icons.ftl" parse=false />

	<section id="content">
		<h2 class="sr-only" role="heading" aria-level="1">${the_title}</h2>

		<div class="user-personal-bar">
			<@liferay.user_personal_bar />
		</div>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}
		</#if>
	</section>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>
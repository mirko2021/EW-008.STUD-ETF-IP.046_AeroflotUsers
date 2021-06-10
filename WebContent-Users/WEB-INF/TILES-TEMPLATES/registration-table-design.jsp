<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Начелна страница</title>
		<link rel="icon"  type="image/x-icon" href="${pageContext.request.contextPath}/WEB-ICON/java.png"></link>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/STYLESHEET/fonts.css'></link>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/STYLESHEET/tables.css'></link>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/WEB-DESIGN/tiles-design.css'></link>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/WEB-DESIGN/messages.css'></link>
		<script type='text/javascript' src='${pageContext.request.contextPath}/WEB-SOCKET/server-time-socket.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/WEB-FUNCTIONALS/messages.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/JAVASCRIPT/ajax_functionals.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/JAVASCRIPT/ajax_utils.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/OBJECT/AjaxMessage.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/OBJECT/AjaxRequest.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/OBJECT/AjaxResponse.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/OBJECT/AjaxType.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/CONTENT/classic/registration_form.js'></script>
		<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=" type="text/javascript"></script>
    	<script src="${pageContext.request.contextPath}/FOREIGN/Yandex-Code/yamp_functionals.js" type="text/javascript"></script>
    	<script type='text/javascript' src='${pageContext.request.contextPath}/AJAX-JS/front-page-refresher.js'></script>
	</head>
	<body>
		<script>
		    var client = new ServerTimeSocket(
				'${designGeneralBean.serviceBean.serverTimeSocketDescriptor.protocol}',
				'${designGeneralBean.serviceBean.serverTimeSocketDescriptor.host}',
				'${designGeneralBean.serviceBean.serverTimeSocketDescriptor.port}',
				'${pageContext.request.contextPath}${designGeneralBean.serviceBean.serverTimeSocketDescriptor.path}'); 
			client.connect();
		</script>
		<table border="1" style="width: 100%; height: 100%">
			<tr>
				<td width="200px" height='200px'><tiles:insertAttribute name="specific_form" /></td>
				<td><tiles:insertAttribute name="profile_content" /></td>
			</tr>
			<tr>
				<td height='30px' colspan="2"><tiles:insertAttribute name="header_message" /></td>
			</tr>
			<tr>
				<td width="200px" valign='top'><tiles:insertAttribute name="menu_content" /></td>
				<td valign='top'><tiles:insertAttribute name="body_content" /></td>
			</tr>
			<tr>
				<td height='30px' colspan="2"><tiles:insertAttribute name="page_zone" /></td>
			</tr>
			<tr>
				<td height='50px' colspan="2"><tiles:insertAttribute name="footer_message" /></td>
			</tr>
		</table>
	</body>
</html>
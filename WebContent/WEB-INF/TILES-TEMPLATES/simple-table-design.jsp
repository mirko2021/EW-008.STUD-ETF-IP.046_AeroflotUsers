<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Simple Tiles Page</title>
		<link rel="icon"  type="image/x-icon" href="${pageContext.request.contextPath}/WEB-ICON/java.png"></link>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/STYLESHEET/fonts.css'></link>
	</head>
	<body>
		<table border="1" style="width: 100%; height: 100%">
			<tr>
				<td width="200px"><tiles:insertAttribute name="specific_form" /></td>
				<td><tiles:insertAttribute name="profile_content" /></td>
			</tr>
			<tr>
				<td colspan="2"><tiles:insertAttribute name="header_message" /></td>
			</tr>
			<tr>
				<td width="200px"><tiles:insertAttribute name="menu_content" /></td>
				<td><tiles:insertAttribute name="body_content" /></td>
			</tr>
			<tr>
				<td colspan="2"><tiles:insertAttribute name="page_zone" /></td>
			</tr>
			<tr>
				<td colspan="2"><tiles:insertAttribute name="footer_message" /></td>
			</tr>
		</table>
	</body>
</html>
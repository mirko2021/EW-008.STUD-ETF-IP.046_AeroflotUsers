<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<c:if test='${param["login"] ne null}'>
	<c:out value='${designGeneralBean.operationalBean.login(pageContext.request)}'></c:out>
</c:if>

<form name='login_form' method='POST'>
	<table>
		<tr>
			<td>Корисничко име : </td>
			<td><input type='text' id='login_form_username' name='username' value='${baseBean.loginBean.username}'/></td>
		</tr>
		<tr>
			<td>Лозинка : </td>
			<td><input type='password' id='login_form_password' name='password'/></td>
		</tr>
	</table>
	<br>
	<input type='hidden' id='login_form_session' value='${pageContext.session.id}'/> 
	<input type='submit' name='login' value='Пријава'/>
</form>

<c:if test='${param["login"] ne null}'>
	<c:if test='${not designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
		<script>alert('Пријава корисника није успјела.');</script>
	</c:if>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userRegistrationBean' class='yatospace.web.user.basic.bean.UserRegistrationBean'  scope='session'></jsp:useBean>
<c:if test='${param["register_form_username"] ne null}'><jsp:setProperty name='userRegistrationBean' property='username' value='${param["register_form_username"]}'></jsp:setProperty></c:if>
<c:if test='${param["register_form_password"] ne null}'><jsp:setProperty name='userRegistrationBean' property='password' value='${param["register_form_password"]}'></jsp:setProperty></c:if>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>
<c:out value='${designGeneralBean.operationalBean.register(pageContext.request)?"":""}'></c:out>

<form name='register_form' method='POST'>
	<table class='form_table'>
		<tr>
			<td>Корисничко име: </td>
			<td><input type='text' name='register_form_username' id='register_form_username' value='${userRegistrationBean.username}'/></td>
		</tr>
		<tr>
			<td>Лозинка: </td>
			<td><input type='password' name='register_form_password' id='register_form_password'/></td>
		</tr>
		<tr>
			<td>Поновљена лозинка: </td>
			<td><input type='password' name='register_form_password2' id='register_form_password2'/></td>
		</tr>
	</table>
	<br>
	<input type='submit' name='register_form_register' value='Регистрација' onclick='return testPasswords()'/>
</form>


<c:if test='${param["register_form_register"] ne null}'>
	<script>alert('${userRegistrationBean.registerMessage}')</script>
	<c:out value="${userRegistrationBean.clean().avoidSyntaxStream()}"></c:out>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userRoleBean' class='yatospace.user.role.web.bean.UserRoleBean' scope='session'></jsp:useBean>
		
<c:out value="${userRoleBean.initialize(pageContext.request)}"></c:out>

<br>
<b>ПОСТАВЉАЊЕ КОРИСНИЧКЕ УЛОГЕ</b>
<br><br>
<form name='user_role_form' method='POST'>
  <input type="checkbox" id="role_user" name="role_user" value="USER" ${userRoleBean.user?"checked":""}>
  <label for="role_user"> Корисник </label><br>
  <input type="checkbox" id="role_worker" name="role_worker" value="WORKER" ${userRoleBean.worker?"checked":""}>
  <label for="role_worker"> Запослени</label><br>
  <input type="checkbox" id="role_administrator" name="role_administrator" value="ADMINISTRATOR" ${userRoleBean.administrator?"checked":""}>
  <label for="role_administrator"> Администратор</label><br><br>
  <input type="submit" name='submit_user_role' value="Потврда">
</form>
<br>
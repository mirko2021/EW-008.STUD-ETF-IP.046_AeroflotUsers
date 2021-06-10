<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userDataBean' class='yatospace.user.role.web.bean.UserDataBean' scope='session'></jsp:useBean>
<c:out value="${userDataBean.initialize(pageContext.request)}"></c:out>
	
<br>
<b>ПОСТАВЉАЊЕ КОРИСНИЧКИХ ПОДАТАКА</b>
<br><br>
<form name='user_data_form' method='POST'>
	<table>
		 <tr>
		 	<td>Име кориника : </td>
		 	<td><input type='text' name='first_name' value="${userDataBean.firstName}" style='width:100%'/></td>
		 </tr>
		 <tr>
		 	<td>Презиме корисника: </td>
		 	<td><input type='text' name='second_name' value="${userDataBean.secondName}" style='width:100%'/></td>
		 </tr>
		 <tr>
		 	<td colspan='2'>
		 		<br><textarea name='user_note' cols='50' rows='20'><c:out value='${userDataBean.userNote}'></c:out></textarea>
		 	</td>
		 </tr>
	</table><br>
	<input type='submit' name='submit_user_data' value='Потврда'/>
</form>
<br>
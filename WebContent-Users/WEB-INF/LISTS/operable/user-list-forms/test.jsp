<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>Провјера корисничке лозинке</h3>
<form name='users_list_form_test' id='users_list_test_update'>
	<table>
		<tr>
			<td>Корисничко име : </td>
			<td><input type='text' id='users_list_form_test_username'/></td>
		</tr>
		<tr>
			<td>Kорисничка лозинка : </td>
			<td><input type='password' id='users_list_form_test_password'/></td>
		</tr>
	</table>
	<br>
	<input type='button' value='Потврда операције' onclick='user_list_test("${pageContext.request.contextPath}")'/>
</form>
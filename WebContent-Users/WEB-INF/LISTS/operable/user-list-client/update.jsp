<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>Промјена корисничке лозинке</h3>
<form name='users_list_form_update' id='users_list_form_update'>
	<table>
		<tr>
			<td>Корисничко име : </td>
			<td><input type='text' id='users_list_form_update_username'/></td>
		</tr>
		<tr>
			<td>Корисничка лозинка : </td>
			<td><input type='password' id='users_list_form_update_password'/></td>
		</tr>
		<tr>
			<td>Нова корисничка лозинка : </td>
			<td><input type='password' id='users_list_form_update_new_password'/></td>
		</tr>
	</table>
	<br>
	<input type='button' value='Потврда операције' onclick='user_list_update_client("${pageContext.request.contextPath}")'/>
</form>
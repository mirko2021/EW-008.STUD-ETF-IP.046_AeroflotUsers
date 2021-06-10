<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>Промјена корисничког имена</h3>
<form name='users_list_form_update' id='users_list_form_rename'>
	<table>
		<tr>
			<td>Корисничко име : </td>
			<td><input type='text' id='users_list_form_rename_username'/></td>
		</tr>
		<tr>
			<td>Ново корисничко име : </td>
			<td><input type='text' id='users_list_form_rename_new_username'/></td>
		</tr>
	</table>
	<br>
	<input type='button' value='Потврда операције' onclick='user_list_rename("${pageContext.request.contextPath}")'/>
</form>
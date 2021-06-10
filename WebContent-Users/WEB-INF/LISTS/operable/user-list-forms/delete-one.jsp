<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>Брисање корисника</h3>
<form name='users_list_form_erase_one' id='users_list_form_erase_one'>
	<table>
		<tr>
			<td>Корисничко име : </td>
			<td><input type='text' id='users_list_form_erase_one_username'/></td>
		</tr>
	</table>
	<br>
	<input type='button' value='Потврда операције' onclick='user_list_erase_one("${pageContext.request.contextPath}")'/>
</form>
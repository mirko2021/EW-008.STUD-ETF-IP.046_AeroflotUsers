<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>Брисање изабраних корисника</h3>
<form name='users_list_form_erase_all' id='users_list_form_erase_all'>
	<input type='button' value='Потврда операције' onclick='user_list_erase_all("${pageContext.request.contextPath}")'/>
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<form name='session_list_table_form' method='POST'>
	<table class='simpletable' style='width:500px'>
		<thead class='simpletable'>
			<tr>
				<th class='simpletable'></th>
				<th class='simpletable'>Сесија</th>
				<th class='simpletable'>корисник</th>
			</tr>
		</thead>
		<tbody class='simpletable'>
			<c:forEach var='item' items='${baseBean.sessionListBean.listForUser(baseBean.loginBean.username)}'>
				<tr>
					<td class='simpletable'><input type='checkbox' id='select_${item.sessionId}' name='select_${item.sessionId}'/></td>
					<td class='simpletable'><c:out value='${item.sessionId}'></c:out></td>
					<td class='simpletable'><c:out value='${item.userId}'></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
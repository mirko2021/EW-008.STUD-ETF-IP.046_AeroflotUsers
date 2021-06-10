<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userListingBean' class='yatospace.web.user.basic.bean.UserListingBean'  scope='session'></jsp:useBean>
<c:out value='${userListingBean.register(pageContext.session).avoidSyntaxStream()}'></c:out>

<table class='simpletable'>
	<thead class='simpletable'>
		<tr>
			<th class='simpletable'>Корисничко име</th>
			<th class='simpletable'>запис лозинке</th>
		</tr>
	</thead>
	<tbody class='simpletable'>
		<c:forEach var='item' items='${userListingBean.list()}'>
			<tr>
				<td class='simpletable'><c:out value='${item.username}'></c:out></td>
				<td class='simpletable'><c:out value='${userListingBean.passwordRecord(item.username)}'></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
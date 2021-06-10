<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userRoleBean' class='yatospace.user.role.web.bean.UserRoleBean' scope='session'></jsp:useBean>
<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>
<c:out value="${userRoleBean.initialize(pageContext.request)}"></c:out>

<dl>
	<dt><b>КОРИСНИК</b></dt>
	<dd><br></dd>
	<dd><font face='YI Courier New'><c:out value='${baseBean.loginBean.username}'></c:out></font></dd>
	<dd><br></dd>
	<dt><b>ОПШТЕ</b></dt>
	<dd><br></dd>
	<dd><a href="${pageContext.request.contextPath}/home-admin.jsp">Администрација</a></dd>
	<dd><a href="${pageContext.request.contextPath}/home-logged.jsp">Начелна</a></dd>
	<dd><a href="${pageContext.request.contextPath}/home-logout.jsp">Одјава</a></dd>
	<dd><br></dd>
	<dd><a href='${pageContext.request.contextPath}/home-user-data.jsp'>Корисник</a></dd>
	<dd><a href='${pageContext.request.contextPath}/home-user-role.jsp'>Улога</a></dd>
	<c:if test="${userRoleBean.administrator}">
		<dd><br></dd>
		<dt><b>АДМИНИСТРАЦИЈА</b></dt>
		<dd><br></dd>
		<dd><a href="${pageContext.request.contextPath}/faces/home-admin-location.jsp">Мјеста</a></dd>
		<dd><a href="${pageContext.request.contextPath}/faces/home-admin-workers.jsp">Запослени</a></dd>
		<dd><a href="${pageContext.request.contextPath}/faces/home-admin-users.jsp">Корисници</a></dd>
		<dd><a href="${pageContext.request.contextPath}/faces/home-admin-administrators.jsp">Администратори</a></dd>
		<dd><br></dd>
		<dt><b>СХЕМЕ И ПОДАЦИ</b></dt>
		<dd><br></dd>
		<dd><a href="${pageContext.request.contextPath}/faces/home-admin-rt.jsp">Релације</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-admin-msg.jsp">Поруке</a></dd>
	</c:if>
	<c:if test="${userRoleBean.worker}">
		<dd><br></dd>
		<dt><b>ОПЕРАТЕР</b></dt>
		<dd><br></dd>
		<dd><a href="${pageContext.request.contextPath}/home-admin-flights.jsp">Летови</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-admin-reservations.jsp">Резервације</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-admin-messages.jsp">Поруке</a></dd>
	</c:if>
	<c:if test="${userRoleBean.user}">
		<dd><br></dd>
		<dt><b>ИПА КОРИСНИК</b></dt>
		<dd><br></dd>
		<dd><a href="${pageContext.request.contextPath}/home-user-communication.jsp">Комуникација</a></dd>
		<dd><a href="${pageContext.request.contextPath}/faces/home-user-location.jsp">Локација</a></dd>
		<dd><a href="${pageContext.request.contextPath}/faces/home-user-reservation.jsp">Резервације</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-user-application.jsp">Апликације</a></dd>
	</c:if>
	<dd><br></dd>
	<dt><b>ДНЕВНИ ЛЕТОВИ</b></dt>
	<dd><br></dd>
	<dd><a href="${pageContext.request.contextPath}/home-logall-today-income.jsp">Доласци</a></dd>
	<dd><a href="${pageContext.request.contextPath}/home-logall-today-outcome.jsp">Одласци</a></dd>
</dl>
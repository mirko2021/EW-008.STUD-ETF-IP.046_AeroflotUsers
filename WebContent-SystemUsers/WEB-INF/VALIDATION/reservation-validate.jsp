<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id='transportBean' class='yatospace.common.tt.web.bean.TransportBean' scope='session'></jsp:useBean>
<jsp:useBean id='loginBean' class='yatospace.session.bean.LoginBean' scope='session'></jsp:useBean>
<c:out value='${transportBean.initialize(pageContext.request)}'></c:out>

<sql:setDataSource var="myDSTransport" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>

<sql:query dataSource="${myDSTransport}" var="reservationQueryResult">
	SELECT COUNT(reservation_id) AS ex_result
	FROM yi_ip_reservations;
</sql:query>

<c:forEach var="row" items="${reservationQueryResult.rows}">
	<c:if test='${row.ex_result eq 0}'>
		<c:out value='${transportBean.resetReservation()}'></c:out>
	</c:if>
</c:forEach>
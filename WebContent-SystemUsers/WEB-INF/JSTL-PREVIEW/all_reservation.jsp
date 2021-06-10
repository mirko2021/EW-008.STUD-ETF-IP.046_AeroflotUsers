<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>
 
 <sql:query dataSource="${myDS}" var="reservation">
      SELECT yi_ip_reservations.fly_id AS fly_id,  fly_date, reservation_id, 
        place_count, article_description,  rt_type, yi_ip_reservations.username
      FROM yi_traffic_relations, yi_ip_flights, yi_ip_reservations, yi_users
      WHERE yi_ip_flights.relation = rt_word
        AND yi_ip_flights.fly_id = yi_ip_reservations.fly_id
        AND yi_ip_reservations.username = yi_users.username; 
 </sql:query>
 
 
 <table class='simpletable'> 
 	<tbody>
	    <c:forEach var="row" items="${reservation.rows}">
	      <tr class='simpletable'>
	        <td class='simpletable'><c:out value="${row.reservation_id}"/></td>
	        <td class='simpletable'><c:out value="${row.fly_id}"/></td>
	        <td class='simpletable'><c:out value="${row.fly_date}"/></td>
	        <td class='simpletable'><c:out value="${row.place_count}"/></td>
	        <td class='simpletable'><c:out value="${row.article_description}"/></td>
	      </tr>
		 </c:forEach>
	 </tbody>
 </table>
 <br>
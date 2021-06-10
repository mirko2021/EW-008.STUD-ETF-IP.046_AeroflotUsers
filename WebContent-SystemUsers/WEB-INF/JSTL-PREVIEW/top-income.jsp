<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

 <sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>
 
 <sql:query dataSource="${myDS}" var="incomming">
      SELECT fly_id, fly_date, rt_word, income, place, destination, direction 
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE direction = 'долазак'
      AND yi_ip_flights.relation = rt_word
      ORDER BY fly_date DESC, id_fly DESC
      LIMIT 5;
 </sql:query>
 
 <table class='simpletable'> 
 	<tbody>
	    <c:forEach var="row" items="${incomming.rows}">
	      <tr class='simpletable'>
	        <td class='simpletable'><c:out value="${row.fly_id}"/></td>
	        <td class='simpletable'><c:out value="${row.fly_date}"/></td>
	        <td class='simpletable'><c:out value="${row.direction}"/></td>
	        <td class='simpletable'>У: <c:out value="${row.destination}"/></td>
	        <td class='simpletable'>ИЗ: <c:out value="${row.place}"/></td>
	      </tr>
		 </c:forEach>
	 </tbody>
 </table>
 <br>
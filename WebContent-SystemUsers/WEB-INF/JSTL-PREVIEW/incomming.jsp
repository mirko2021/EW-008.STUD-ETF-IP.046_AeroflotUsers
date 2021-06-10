<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

 <sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>
 
 <sql:query dataSource="${myDS}" var="incomming">
      SELECT rt_word, income, place, destination, direction FROM yi_traffic_relations WHERE direction = 'долазак';
 </sql:query>
 
 <table class='simpletable'> 
 	<tbody>
	    <c:forEach var="row" items="${incomming.rows}">
	      <tr class='simpletable'>
	        <td class='simpletable'><c:out value="${row.rt_word}"/></td>
	        <td class='simpletable'><c:out value="${row.income}"/></td>
	        <td class='simpletable'><c:out value="${row.direction}"/></td>
	        <td class='simpletable'>У: <c:out value="${row.destination}"/></td>
	        <td class='simpletable'>ИЗ: <c:out value="${row.place}"/></td>
	      </tr>
		 </c:forEach>
	 </tbody>
 </table>
 <br>
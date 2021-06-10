<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

 <sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>

<jsp:useBean id='messagePropertiesIncomeBean' class='yatospace.ip_messaging.web.bean.MessagePropertiesBean' scope='session'></jsp:useBean>
 
 <sql:query dataSource="${myDS}" var="incomming">
      SELECT fly_id, fly_date, rt_word, income, place, destination, direction 
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE direction = 'долазак'
      AND yi_ip_flights.relation = rt_word
      ORDER BY fly_date DESC, id_fly DESC
      LIMIT ${messagePropertiesIncomeBean.pageSize} OFFSET ${messagePropertiesIncomeBean.pageSize*messagePropertiesIncomeBean.pageNo};
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
 

<jsp:setProperty property="pageNo" name="messagePropertiesIncomeBean" value='${messagePropertiesIncomeBean.pageNo+1}'/>

 <sql:query dataSource="${myDS}" var="incomming">
      SELECT fly_id 
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE direction = 'долазак'
      AND yi_ip_flights.relation = rt_word
      ORDER BY fly_date DESC, id_fly DESC
      LIMIT ${messagePropertiesIncomeBean.pageSize} OFFSET ${messagePropertiesIncomeBean.pageSize*messagePropertiesIncomeBean.pageNo};
 </sql:query>
 
<c:set var='neo_size' value='0'></c:set>
<c:forEach var="row" items="${incomming.rows}">
	<c:set var='neo_size' value='${neo_size+1}'></c:set>
</c:forEach>


 <c:if test='${neo_size eq 0}'>
 	<jsp:setProperty property="pageNo" name="messagePropertiesIncomeBean" value='0'/>
 </c:if>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id='messagePropertiesOutcomeBean' class='yatospace.ip_messaging.web.bean.MessagePropertiesBean' scope='session'></jsp:useBean>

 <sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>
 
 <sql:query dataSource="${myDS}" var="outcomming">
      SELECT fly_id, fly_date, rt_word, outcome, place, destination, direction 
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE direction = 'одлазак' 
      AND yi_ip_flights.relation = rt_word
      ORDER BY fly_date DESC, id_fly DESC
      LIMIT ${messagePropertiesOutcomeBean.pageSize} OFFSET ${messagePropertiesOutcomeBean.pageSize*messagePropertiesOutcomeBean.pageNo};
 </sql:query>
 
 <table class='simpletable'> 
 	<tbody>
	    <c:forEach var="row" items="${outcomming.rows}">
	      <tr class='simpletable'>
	        <td class='simpletable'><c:out value="${row.fly_id}"/></td>
	        <td class='simpletable'><c:out value="${row.fly_date}"/></td>
	        <td class='simpletable'><c:out value="${row.direction}"/></td>
	        <td class='simpletable'>ИЗ: <c:out value="${row.destination}"/></td>
	        <td class='simpletable'>У: <c:out value="${row.place}"/></td>
	      </tr>
		 </c:forEach>
	 </tbody>
 </table>
 <br>

<jsp:setProperty property="pageNo" name="messagePropertiesOutcomeBean" value='${messagePropertiesOutcomeBean.pageNo+1}'/>

 <sql:query dataSource="${myDS}" var="outcomming">
      SELECT fly_id
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE direction = 'одлазак'
      AND yi_ip_flights.relation = rt_word
      ORDER BY id_fly DESC
      LIMIT ${messagePropertiesOutcomeBean.pageSize} OFFSET ${messagePropertiesOutcomeBean.pageSize*messagePropertiesOutcomeBean.pageNo};
 </sql:query>
 
<c:set var='neo_size' value='0'></c:set>
<c:forEach var="row" items="${outcomming.rows}">
	<c:set var='neo_size' value='${neo_size+1}'></c:set>
</c:forEach>


 <c:if test='${neo_size eq 0}'>
 	<jsp:setProperty property="pageNo" name="messagePropertiesOutcomeBean" value='0'/>
 </c:if>

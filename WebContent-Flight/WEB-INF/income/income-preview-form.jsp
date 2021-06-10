<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='flightIncomeBean' class='yatospace.traffic.flight.bean.FlightIncomeBean' scope='session'></jsp:useBean>
<jsp:useBean id='flightOutcomeBean' class='yatospace.traffic.flight.bean.FlightOutcomeBean' scope='session'></jsp:useBean>
<c:out value='${flightIncomeBean.initialize(pageContext.request.session)}'></c:out>
<c:out value='${flightOutcomeBean.initialize(pageContext.request.session)}'></c:out>


<sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>
 
 <sql:query dataSource="${myDS}" var="incomming">
      SELECT fly_id, fly_date, rt_word, income, place, destination, direction 
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE direction = 'долазак'
      AND yi_ip_flights.relation = rt_word
      AND rt_type='АВИОСАОБРАЋАЈ'
      AND rt_subtype='ПУТНИЧКИ'
      ORDER BY id_fly DESC;
 </sql:query>
 
 <c:set var='dataRows' value='${flightIncomeBean.order(incomming.rows, pageContext.request)}'></c:set>

 <p>ПУТНИЧКИ САОБРАЋАЈ</p> 
 <table class='simpletable'> 
 	<tbody>
	    <c:forEach var="row" items="${dataRows}">
	      <tr class='simpletable'>
	        <td class='simpletable'><c:out value="${row.flyId}"/></td>
	        <td class='simpletable'><c:out value="${row.flyDate}"/></td>
	        <td class='simpletable'><c:out value="${row.direction}"/></td>
	        <td class='simpletable'>У: <c:out value="${row.destination}"/></td>
	        <td class='simpletable'>ИЗ: <c:out value="${row.place}"/></td>
	        <td class='simpletable'><c:out value="${flightIncomeBean.getIncome(row.flyId)},${flightOutcomeBean.getOutcome(row.flyId)}"/></td>
	      </tr>
		 </c:forEach>
	 </tbody>
 </table>
 <br>
 
  <sql:query dataSource="${myDS}" var="incomming">
      SELECT fly_id, fly_date, rt_word, income, place, destination, direction 
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE direction = 'долазак'
      AND yi_ip_flights.relation = rt_word
      AND rt_type='АВИОСАОБРАЋАЈ'
      AND rt_subtype='ТЕРЕТНИ'
      ORDER BY id_fly DESC;
 </sql:query>
 
 <c:set var='dataRows' value='${flightIncomeBean.order(incomming.rows, pageContext.request)}'></c:set>
 
 <p>ТЕРЕТНИ САОБРАЋАЈ</p>
 <table class='simpletable'> 
 	<tbody>
	    <c:forEach var="row" items="${dataRows}">
	      <tr class='simpletable'>
	        <td class='simpletable'><c:out value="${row.flyId}"/></td>
	        <td class='simpletable'><c:out value="${row.flyDate}"/></td>
	        <td class='simpletable'><c:out value="${row.direction}"/></td>
	        <td class='simpletable'>У: <c:out value="${row.destination}"/></td>
	        <td class='simpletable'>ИЗ: <c:out value="${row.place}"/></td>
	        <td class='simpletable'><c:out value="${flightIncomeBean.getIncome(row.flyId)},${flightOutcomeBean.getOutcome(row.flyId)}"/></td>
	      </tr>
		 </c:forEach>
	 </tbody>
 </table>
 <p style='color:gray'>Освјежено у: <%=new java.text.SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(new java.util.Date())%></p>
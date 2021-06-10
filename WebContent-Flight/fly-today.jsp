<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<jsp:useBean id='flightIncomeBean'  class='yatospace.traffic.flight.bean.FlightIncomeBean' scope='session'></jsp:useBean>
<jsp:useBean id='flightOutcomeBean' class='yatospace.traffic.flight.bean.FlightOutcomeBean' scope='session'></jsp:useBean>
<c:out value='${flightIncomeBean.initialize(pageContext.request.session)}'></c:out>
<c:out value='${flightOutcomeBean.initialize(pageContext.request.session)}'></c:out>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Данашњи летови</title>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/STYLESHEET/fonts.css'></link>
		<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/STYLESHEET/tables.css'></link>
	</head>
	<body>
		<sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      		url="jdbc:mysql://localhost:3306/yatospace_db" user="root"  password="root"/>
      		
      	 <sql:query dataSource="${myDS}" var="flights">
		      SELECT fly_id, fly_date, rt_word, income, outcome, place, destination, direction 
		      FROM yi_traffic_relations, yi_ip_flights 
		      WHERE yi_ip_flights.relation = rt_word
		      ORDER BY id_fly DESC;
		 </sql:query>
		 
	  <table class='simpletable'> 
	 	<tbody>
		    <c:forEach var="row" items="${flights.rows}">
		      <tr class='simpletable'>
		        <td class='simpletable' onclick='set_data("${row.fly_id.toString().replaceAll("\"","&quot")}")'><c:out value="${row.fly_id}"/></td>
		        <td class='simpletable'><c:out value="${row.fly_date}"/></td>
		        <td class='simpletable'><c:out value="${row.rt_word}"/></td>
		        <td class='simpletable'>У: <c:out value="${row.destination}"/></td>
		        <td class='simpletable'>ИЗ: <c:out value="${row.place}"/></td>
		        <td class='simpletable'><c:out value="${row.direction}"/></td>
		        <td class='simpletable'>
		        	<select>
		        		<option value='sleteo'    ${flightIncomeBean.getIncome(row.fly_id).toString() eq "DOSAO"? "selected":""}>Слетео</option>
		        		<option value='putuje'    ${flightIncomeBean.getIncome(row.fly_id).toString() eq "PUTUJE"? "selected":""}>Путује</option>
		        		<option value='nepoznato' ${flightIncomeBean.getIncome(row.fly_id).toString() eq "NEPOZNATO"? "selected":""}>Непозанто</option>
		        	</select>
		        </td>
		        <td class='simpletable'>
		        	<select>
		        		<option value='poleteo' ${flightOutcomeBean.getOutcome(row.fly_id).toString() eq "OTISAO"? "selected":""}>Пoлетео</option>
		        		<option value='ceka_se' ${flightOutcomeBean.getOutcome(row.fly_id).toString() eq "CEKA_SE"? "selected":""}>Чека се</option>
		        		<option value='nepoznato' ${flightOutcomeBean.getOutcome(row.fly_id).toString() eq "NEPOZNATO"? "selected":""}>Непознато</option>
		        	</select>
		        </td>
		       </tr>
			 </c:forEach>
		 </tbody>
	  </table>
	  <br>
	  
	  <script>
	  	function reset_data(){
	  		document.getElementById("fly_id").value='';
	  		document.getElementById("fly_id_view").innerHTML='';
	  	}
	  	function set_data(fly_id){
	  		document.getElementById("fly_id").value=fly_id.replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;');
	  		document.getElementById("fly_id_view").innerHTML=fly_id.replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;');
	  	}
	  </script>
	  
	  <form method='GET'>
	  	<input type='hidden' name='fly_id' id='fly_id' value="${param['fly_id']}">
	  	<div id='fly_id_view'><c:if test="${param['fly_id'] ne null}"><c:out value="${param['fly_id']}"></c:out></c:if></div>
	  	<br>
	  	<input type='submit' value='Потврда/освјежавање'/>
	  	<input type='button' value='ресетовање' onclick='reset_data()'/>
	  </form>
	</body>
</html>
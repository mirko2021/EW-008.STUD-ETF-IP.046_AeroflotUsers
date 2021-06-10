<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='flightIncomeBean'  class='yatospace.traffic.flight.bean.FlightIncomeBean' scope='session'></jsp:useBean>
<jsp:useBean id='flightOutcomeBean' class='yatospace.traffic.flight.bean.FlightOutcomeBean' scope='session'></jsp:useBean>
<c:out value='${flightIncomeBean.initialize(pageContext.request.session)}'></c:out>
<c:out value='${flightOutcomeBean.initialize(pageContext.request.session)}'></c:out>

<p>УПРАВЉАЊЕ СТАЊИМА ЛЕТА</p>

<c:if test='${param["flight_state_operation"] ne ""}'>
	<c:out value="${flightIncomeBean.putIncome(pageContext.request)}"></c:out>
	<c:out value="${flightOutcomeBean.putOutcome(pageContext.request)}"></c:out>
</c:if>

<sql:setDataSource var="myDS" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>
 
 <sql:query dataSource="${myDS}" var="flights">
      SELECT fly_id, fly_date, rt_word, income, outcome, place, destination, direction 
      FROM yi_traffic_relations, yi_ip_flights 
      WHERE yi_ip_flights.relation = rt_word
      ORDER BY id_fly DESC;
 </sql:query>

 <script>
    function adapte_data_of_fly_change_state(fly_id, fly_income_state, fly_outcome_state){
    	document.getElementById('flight_state_operation').value=fly_id; 
    	var fly_income_state_d  = ''; 
    	var fly_outcome_state_d = ''; 
    	if(fly_income_state==="sleteo")    fly_income_state_d = "DOSAO";
    	if(fly_income_state==="putuje")    fly_income_state_d = "PUTUJE";
    	if(fly_income_state==="nepoznato") fly_income_state_d = "NEPOZNATO";
    	if(fly_outcome_state==="poleteo")    fly_outcome_state_d = "OTISAO";
    	if(fly_outcome_state==="ceka_se")   fly_outcome_state_d = "CEKA_SE";
    	if(fly_outcome_state==="nepoznato") fly_outcome_state_d = "NEPOZNATO";
    	document.getElementById('flight_state_income').value=fly_income_state_d; 
    	document.getElementById('flight_state_outcome').value=fly_outcome_state_d;
    }
 </script>
 
 <form method='POST' name='flight_state_form' id='flight_state_form' action='#flight_state_form'>
	 <table class='simpletable'> 
	 	<tbody>
		    <c:forEach var="row" items="${flights.rows}">
		      <tr class='simpletable'>
		        <td class='simpletable'><c:out value="${row.fly_id}"/></td>
		        <td class='simpletable'><c:out value="${row.fly_date}"/></td>
		        <td class='simpletable'><c:out value="${row.rt_word}"/></td>
		        <td class='simpletable'>У: <c:out value="${row.destination}"/></td>
		        <td class='simpletable'>ИЗ: <c:out value="${row.place}"/></td>
		        <td class='simpletable'><c:out value="${row.direction}"/></td>
		        <td class='simpletable'>
		        	<select id='income_value_${row.fly_id}'>
		        		<option value='sleteo'    ${flightIncomeBean.getIncome(row.fly_id).toString() eq "DOSAO"? "selected":""}>Слетео</option>
		        		<option value='putuje'    ${flightIncomeBean.getIncome(row.fly_id).toString() eq "PUTUJE"? "selected":""}>Путује</option>
		        		<option value='nepoznato' ${flightIncomeBean.getIncome(row.fly_id).toString() eq "NEPOZNATO"? "selected":""}>Непозанто</option>
		        	</select>
		        </td>
		        <td class='simpletable'>
		        	<select id='outcome_value_${row.fly_id}'>
		        		<option value='poleteo' ${flightOutcomeBean.getOutcome(row.fly_id).toString() eq "OTISAO"? "selected":""}>Пoлетео</option>
		        		<option value='ceka_se' ${flightOutcomeBean.getOutcome(row.fly_id).toString() eq "CEKA_SE"? "selected":""}>Чека се</option>
		        		<option value='nepoznato' ${flightOutcomeBean.getOutcome(row.fly_id).toString() eq "NEPOZNATO"? "selected":""}>Непознато</option>
		        	</select>
		        </td>
		        <td class='simpletable'>
		        	<input type='submit' value='Потврда' onclick='adapte_data_of_fly_change_state("${row.fly_id}", document.getElementById("income_value_${row.fly_id}").value, document.getElementById("outcome_value_${row.fly_id}").value)'/>
		        </td>
		      </tr>
			 </c:forEach>
		 </tbody>
	 </table>
	 <br>
	 <input type='hidden' name='flight_state_operation' id='flight_state_operation' value='false'/>
	 <input type='hidden' name='flight_state_income'    id='flight_state_income'    value=''/>
	 <input type='hidden' name='flight_state_outcome'   id='flight_state_outcome'   value=''/>
 </form>
 
 <c:if test='${param["flight_state_operation"] ne ""}'>
 	<div>Извршено постављање стања.</div><br>
 </c:if>
 
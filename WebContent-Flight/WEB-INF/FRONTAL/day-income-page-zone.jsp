<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "sql" uri="http://java.sun.com/jsp/jstl/sql" %> 

<jsp:useBean id='flightIncomeBean' class='yatospace.traffic.flight.bean.FlightIncomeBean' scope='session'></jsp:useBean>

<p>ДОЛАСЦИ</p>

<form method='POST' name='day_income_flitgt_form' id='day_income_flitgt_form' action='#day_income_flitgt_form'>
	<input type='date' name='day_income_flight_date'      id='day_income_flight_date'/>
	<input type='time' name='day_income_flight_time_from' id='day_income_flight_time_from'/> - 
	<input type='time' name='day_income_flight_time_to'   id='day_income_flight_time_to'/>
	<br><br>
	<input type='submit' value='Освјежавање' name='day_income_flitgt_submit'/>
	<input type='button' value='поништавање датума'     onclick='document.getElementById("day_income_flight_date").value=""'>
	<input type='button' value='поништавање времена од' onclick='document.getElementById("day_income_flight_time_from").value=""'>
	<input type='button' value='поништавање времена до' onclick='document.getElementById("day_income_flight_time_to").value=""'>
	<input type='button' value='данас' onclick='document.getElementById("day_income_flight_date").value=generateCurrentDate()'>
	<br>
</form>
<p><code><c:out value="${flightIncomeBean.datetimeString(pageContext.request)}"></c:out></code></p>
<div id='income_page'><jsp:include page='/WEB-INF/income/income-preview-form.jsp'></jsp:include></div>
<script>default_app_path='${pageContext.request.contextPath}'</script>
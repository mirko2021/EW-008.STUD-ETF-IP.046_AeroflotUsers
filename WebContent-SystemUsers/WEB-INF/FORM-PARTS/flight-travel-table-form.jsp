<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id='travelBean' class='yatospace.common.tt.web.bean.TravelBean' scope='session'></jsp:useBean>
<c:out value='${travelBean.initialize(pageContext.request)}'></c:out>

<sql:setDataSource var="myDSTravel" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>

<sql:query dataSource="${myDSTravel}" var="flyExists">
	SELECT count(fly_id) AS counter FROM yi_ip_flights WHERE fly_id = ?
	<sql:param value="${param['travel_flight']}" />
</sql:query>

<c:if test='${param["travel_flight_accept"] ne null}'>
	<c:if test='${flyExists.rows[0].counter ne 0}'>
		<c:out value='${travelBean.acceptFlight(pageContext.request)}'></c:out>
	</c:if>
</c:if>

<c:if test='${param["travel_flight_reset"] ne null}'>
	<c:out value='${travelBean.resetFlight()}'></c:out>
</c:if>

<c:if test='${travelBean.sourceCity eq "" and travelBean.destinationCity eq ""}'>
	<sql:query dataSource="${myDSTravel}" var="flyTravel">
		SELECT yi_ip_flights.fly_id AS fly_id, rt_word, fly_date, place, destination, rt_notes
		FROM yi_ip_flights, yi_traffic_relations
		WHERE yi_ip_flights.relation = rt_word
		AND rt_type = 'АВИОСАОБРАЋАЈ'
		AND rt_subtype = 'ПУТНИЧКИ'
		ORDER BY fly_date ASC;
	</sql:query>
</c:if>

<c:if test='${travelBean.sourceCity ne "" and travelBean.destinationCity eq ""}'>
	<sql:query dataSource="${myDSTravel}" var="flyTravel">
		SELECT yi_ip_flights.fly_id AS fly_id, rt_word, fly_date, place, destination, rt_notes
		FROM yi_ip_flights, yi_traffic_relations, yi_locations
		WHERE yi_ip_flights.relation = rt_word
		AND location_name = place
		AND location_name = ?
		AND rt_type = 'АВИОСАОБРАЋАЈ'
		AND rt_subtype = 'ПУТНИЧКИ'
		ORDER BY fly_date ASC;
		<sql:param value="${travelBean.sourceCity}" />
	</sql:query>
</c:if>

<c:if test='${travelBean.sourceCity eq "" and travelBean.destinationCity ne ""}'>
	<sql:query dataSource="${myDSTravel}" var="flyTravel">
		SELECT yi_ip_flights.fly_id AS fly_id, rt_word, fly_date, place, destination, rt_notes
		FROM yi_ip_flights, yi_traffic_relations, yi_locations
		WHERE yi_ip_flights.relation = rt_word
		AND location_name = destination
		AND location_name = ?
		AND rt_type = 'АВИОСАОБРАЋАЈ'
		AND rt_subtype = 'ПУТНИЧКИ'
		ORDER BY fly_date ASC;
		<sql:param value="${travelBean.destinationCity}" />
	</sql:query>
</c:if>

<c:if test='${travelBean.sourceCity ne "" and travelBean.destinationCity ne ""}'>
	<sql:query dataSource="${myDSTravel}" var="flyTravel">
		SELECT yi_ip_flights.fly_id AS fly_id, rt_word, fly_date, place, destination, rt_notes
		FROM yi_ip_flights, yi_traffic_relations, yi_locations AS yi_location_place, yi_locations AS yi_location_destination
		WHERE yi_ip_flights.relation = rt_word
		AND yi_location_place.location_name = place
		AND yi_location_destination.location_name = destination
		AND yi_location_place.location_name = ?
		AND yi_location_destination.location_name = ?
		AND rt_type = 'АВИОСАОБРАЋАЈ'
		AND rt_subtype = 'ПУТНИЧКИ'
		ORDER BY fly_date ASC;
		<sql:param value="${travelBean.sourceCity}" />
		<sql:param value="${travelBean.destinationCity}" />
	</sql:query>
</c:if>

<br>
<table class='simpletable'>
	<thead class='simpletable'>
		<tr>
			<th class='simpletable' onclick='document.getElementById("travel_flight_form_current").innerHTML=""'>Лет</th>
			<th class='simpletable'>вријеме</th>
			<th class='simpletable'>полазак из</th>
			<th class='simpletable'>одредиште</th>
		</tr>
	</thead>
	<tbody class='simpletable'>
		<c:forEach var="row" items="${flyTravel.rows}">
			<tr class='simpletable'>
				<td class='simpletable' onclick='document.getElementById("travel_flight_form_current").innerHTML="${fn:escapeXml(row.fly_id)}"'><c:out value="${row.fly_id}"></c:out></td>
				<td class='simpletable'><c:out value="${row.fly_date}"></c:out></td>
				<td class='simpletable'><c:out value="${row.place}"></c:out></td>
				<td class='simpletable'><c:out value="${row.destination}"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<br>

<form id='travel_flight_form' method='POST'>
	<table>
		<tr>
			<td>Текуће : </td>
			<td><div><code id='travel_flight_form_current'></code></div></td>
		</tr>
		<tr>
			<td>Изабрано : </td>
			<td><div><code id='travel_flight_form_selected'><c:out value='${travelBean.flight}'></c:out></code></div></td>
		</tr>
	</table><br>
	<input type='hidden' name='travel_flight' id='travel_flight_data'/>
	<input type='submit' name='travel_flight_accept' value='Потврда'     onclick='document.getElementById("travel_flight_data").value=document.getElementById("travel_flight_form_current").innerHTML'/>
	<input type='submit' name='travel_flight_reset'  value='поништавање' onclick='document.getElementById("travel_flight_data").value=""'/>
</form>
<br>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:useBean id='travelBean' class='yatospace.common.tt.web.bean.TravelBean' scope='session'></jsp:useBean>
<jsp:useBean id='loginBean' class='yatospace.session.bean.LoginBean' scope='session'></jsp:useBean>
<c:out value='${travelBean.initialize(pageContext.request)}'></c:out>

<jsp:useBean id='reservationsStatesBean' class='yatospace.reservation.state.web.bean.ReservationsStatesBean' scope='session'></jsp:useBean>
<c:out value='${reservationsStatesBean.initialize(pageContext.request)}'></c:out>

<br> 

<c:set var='delete_travel_result' value='0'></c:set>
<c:if test='${param["travel_reservation_deleter_data"] ne null and param["travel_reservation_deleter_data"] ne ""}'>
	<c:set var='delete_travel_execute' value='${travelBean.delete(pageContext.request)}'></c:set>
	<c:if test='${delete_travel_execute}'>
		<c:set var='delete_travel_result' value='1'></c:set>
	</c:if>
	<c:if test='${not delete_travel_execute}'>
		<c:set var='delete_travel_result' value='2'></c:set>
	</c:if>
</c:if>

<sql:setDataSource var="myDSTravel" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>
      
<sql:query dataSource="${myDSTravel}" var="reservationsTravel">
	SELECT reservation_id, yi_ip_reservations.fly_id AS fly_id, place_count
	FROM yi_ip_reservations, yi_ip_flights, yi_traffic_relations
	WHERE yi_ip_reservations.fly_id = yi_ip_flights.fly_id
	AND yi_ip_flights.relation = yi_traffic_relations.rt_word
	AND rt_type='АВИОСАОБРАЋАЈ'
	AND rt_subtype = 'ПУТНИЧКИ'
	AND username = ?;
	<sql:param value='${loginBean.username}'></sql:param>
</sql:query>
<br>
<form method='POST' action='#travel_reservation_deleter' name='travel_reservation_deleter' id='travel_reservation_deleter'>
	<table class='simpletable'>
		<thead class='simpletable'>
			<tr>
				<th class='simpletable'>Резервација</th>
				<th class='simpletable'>лет</th>
				<th class='simpletable'>број мјеста</th>
				<th class='simpletable'>брисање</th>
			</tr>
		</thead>
		<tbody class='simpletable'>
			<c:forEach var='row' items='${reservationsTravel.rows}'>
				<tr class='simpletable'>
					<td class='simpletable'><c:out value='${row.reservation_id}'></c:out></td>
					<td class='simpletable'><c:out value='${row.fly_id}'></c:out></td>
					<td class='simpletable'>${row.place_count}</td>
					<td class='simpletable'>
						<c:if test='${reservationsStatesBean.isNew(row.reservation_id)}'>
							<input type='submit' value='Брисање' onclick='document.getElementById("travel_reservation_deleter_data").value=encodeURI("${row.reservation_id}")'/>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type='hidden' id='travel_reservation_deleter_data' name='travel_reservation_deleter_data' value=''/>
</form>

<c:if test='${delete_travel_result eq "1"}'>
	<br><p>Брисање резервације је успјело. <c:out value='${travelBean.rId}'></c:out></p>
</c:if>
<c:if test='${delete_travel_result eq "2"}'>
	<br><p>Брисање резервације није успјело.</p>
</c:if>
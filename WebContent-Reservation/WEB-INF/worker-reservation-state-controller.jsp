<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='reservationsStatesBean' class='yatospace.reservation.state.web.bean.ReservationsStatesBean' scope='session'></jsp:useBean>
<c:out value='${reservationsStatesBean.initialize(pageContext.request)}'></c:out>

<c:if test='${param["reservation_state_op"] ne null}'>
	<c:if test='${param["reservation_state_op"] eq "ACCEPT"}'>
		<c:out value='${reservationsStatesBean.accept(pageContext.request)}'></c:out>
	</c:if>
	<c:if test='${param["reservation_state_op"] eq "FORBIDE"}'>
		<c:out value='${reservationsStatesBean.forbide(pageContext.request)}'></c:out>
	</c:if>
	<c:if test='${param["reservation_state_op"] eq "REWIND"}'>
		<c:out value='${reservationsStatesBean.rewind(pageContext.request)}'></c:out>
	</c:if>
</c:if>

<script>
	function accept_reservation(id){
		document.getElementById('reservation_state_id').value = id; 
		document.getElementById('reservation_state_op').value = 'ACCEPT'; 
	}
	function forbide_reservation(id){
		document.getElementById('reservation_state_id').value = id; 
		document.getElementById('reservation_state_op').value = 'FORBIDE';
	}
	function rewind_reservation(id){
		document.getElementById('reservation_state_id').value = id;
		document.getElementById('reservation_state_op').value = 'REWIND';
	}
</script>

<form name='reservation_state_operation' action='#reservation_state_operation' method='POST'>
	<p>СТАЊА РЕЗЕРВАЦИЈА</p>
	<table class='simpletable'>
		<thead class='simpletable'>
			<tr>
				<th class='simpletable'>Резервација</th>
				<th class='simpletable'>корисник</th>
				<th class='simpletable'>датум</th>
				<th class='simpletable'>статус</th>
				<th class='simpletable'>операције</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var='reservationStateData' items='${reservationsStatesBean.list()}'>
				<tr class='simpletable'>
					<td class='simpletable'><c:out value="${reservationStateData.reservationId}"></c:out></td>
					<td class='simpletable'><c:out value="${reservationStateData.username}"></c:out></td>
					<td class='simpletable'><c:out value="${reservationStateData.standardFormatedServerDate()}"></c:out></td>
					<td class='simpletable'><c:out value="${reservationStateData.state}"></c:out></td>
					<td class='simpletable'>
						<input type='submit' value='Враћање'     onclick='rewind_reservation("${reservationStateData.idEncodedURI()}")'  ${not reservationsStatesBean.isAccept(reservationStateData.reservationId)?"disabled":""}/>
						<input type='submit' value='прихваћање'  onclick='accept_reservation("${reservationStateData.idEncodedURI()}")'  ${not reservationsStatesBean.isNew(reservationStateData.reservationId)?"disabled":""}/>
						<input type='submit' value='поништавање' onclick='forbide_reservation("${reservationStateData.idEncodedURI()}")' ${reservationsStatesBean.isCanceled(reservationStateData.reservationId)?"disabled":""}/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<input type='hidden' id='reservation_state_id' name='reservation_state_id' value=''/>
	<input type='hidden' id='reservation_state_op' name='reservation_state_op' value=''/>
</form>
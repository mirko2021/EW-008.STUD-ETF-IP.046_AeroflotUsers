<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<jsp:useBean id='transportBean' class='yatospace.common.tt.web.bean.TransportBean' scope='session'></jsp:useBean>
<jsp:useBean id='loginBean' class='yatospace.session.bean.LoginBean' scope='session'></jsp:useBean>
<c:out value='${transportBean.initialize(pageContext.request)}'></c:out>

<jsp:useBean id='reservationsStatesBean' class='yatospace.reservation.state.web.bean.ReservationsStatesBean' scope='session'></jsp:useBean>
<c:out value='${reservationsStatesBean.initialize(pageContext.request)}'></c:out>

<br>

<c:set var='delete_transport_result' value='0'></c:set>
<c:if test='${param["transport_reservation_deleter_data"] ne null and param["transport_reservation_deleter_data"] ne ""}'>
	<c:set var='delete_transport_execute' value='${transportBean.delete(pageContext.request)}'></c:set>
	<c:if test='${delete_transport_execute}'>
		<c:set var='delete_transport_result' value='1'></c:set>
	</c:if>
	<c:if test='${not delete_transport_execute}'>
		<c:set var='delete_transport_result' value='2'></c:set>
	</c:if>
</c:if>

<sql:setDataSource var="myDSTransport" driver="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/yatospace_db"
      user="root"  password="root"/>

<sql:query dataSource="${myDSTransport}" var="reservationsTransport">
	SELECT reservation_id, yi_ip_reservations.fly_id AS fly_id, article_description, article_transport_file
	FROM yi_ip_reservations, yi_ip_flights, yi_traffic_relations
	WHERE yi_ip_reservations.fly_id = yi_ip_flights.fly_id
	AND yi_ip_flights.relation = yi_traffic_relations.rt_word
	AND rt_type='АВИОСАОБРАЋАЈ'
	AND rt_subtype = 'ТЕРЕТНИ'
	AND username = ?;
	<sql:param value='${loginBean.username}'></sql:param>
</sql:query>
<br>

<script>
	function reset_current_reservation(){
		document.getElementById('reservation_transport_choose_form_actual').innerHTML='';
		document.getElementById('reservation_transport_choose_form_actual_data').value=''; 
	}
	function set_current_reservation(data){
		document.getElementById('reservation_transport_choose_form_actual').innerHTML=data
		    .replaceAll(/&/g, "&amp").replaceAll(/</g, "&lt;").replaceAll(/>/g, "&gt;");
		document.getElementById('reservation_transport_choose_form_actual_data').value=data;
	}
</script>

<form action='#transport_reservation_deleter' method='POST' name='transport_reservation_deleter' id='transport_reservation_deleter'>
	<table class='simpletable'>
		<thead class='simpletable'>
			<tr>
				<th class='simpletable' onclick='reset_current_reservation()'>Резервација</th>
				<th class='simpletable'>лет</th>
				<th class='simpletable'>опис робе</th>
				<th class='simpletable'>спецификација превоза</th>
				<th class='simpletable'>брисање</th>
			</tr>
		</thead>
		<tbody class='simpletable'>
			<c:forEach var="row" items="${reservationsTransport.rows}">
				<tr class='simpletable'>
					<td class='simpletable' onclick='set_current_reservation("${row.reservation_id}")'><c:out value='${row.reservation_id}'></c:out></td>
					<td class='simpletable'><c:out value='${row.fly_id}'></c:out></td>
					<td class='simpletable'><code style='white-space: pre'><c:out value='${row.article_description}'></c:out></code></td>
					<td class='simpletable'><a style='text-decoration:none' href='${pageContext.request.contextPath}/ASTFileDownloader?reservation_file=${row.article_transport_file}&reservation_id=${transportBean.decodeURL(row.reservation_id)}'><c:out value='${transportBean.encodeASTFilename(row.article_transport_file, row.reservation_id)}'></c:out></a></td>
					<td class='simpletable'>
						<c:if test='${reservationsStatesBean.isNew(row.reservation_id)}'>
							<input type='submit' value='Брисање' onclick='document.getElementById("transport_reservation_deleter_data").value=encodeURI("${row.reservation_id}")'/>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type='hidden' id='transport_reservation_deleter_data' name='transport_reservation_deleter_data' value=''/>
</form>

<c:if test='${param["reservation_transport_choose_form_submit"] ne null}'>
	<c:out value="${transportBean.acceptReservation(pageContext.request)}"></c:out>
	<jsp:include page='/WEB-INF/VALIDATION/reservation-validate.jsp'></jsp:include>
</c:if>

<c:if test='${param["reservation_transport_choose_form_reset"] ne null}'>
	<c:out value="${transportBean.resetReservation()}"></c:out>
</c:if>

<form id='reservation_transport_choose_form' name='reservation_transport_choose_form' method='POST' action='#reservation_transport_choose_form'>
	<br>
	<table>
		<tr>
			<td>Текуће : </td>
			<td><code id='reservation_transport_choose_form_actual'></code></td>
		</tr>
		<tr>
			<td>Изабрано : </td>
			<td><code id='reservation_transport_choose_form_selected'>${transportBean.reservation}</code></td>
		</tr>
	</table>
	<br>
	<input type='hidden' name='reservation_transport_choose_form_actual_data' id='reservation_transport_choose_form_actual_data' value=''/>
	<input type='submit' name='reservation_transport_choose_form_submit' value='Потврда'/>
	<input type='submit' name='reservation_transport_choose_form_reset' value='поништавање'/>
</form>

<c:if test='${delete_transport_result eq "1"}'>
	<br><p>Брисање резервације је успјело. <c:out value='${transportBean.rId}'></c:out></p>
</c:if>
<c:if test='${delete_transport_result eq "2"}'>
	<br><p>Брисање резервације није успјело.</p>
</c:if>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='internetAccessBean' class='yatospace.common.util.bean.InterentConnectionBean' scope='request'></jsp:useBean>
<jsp:useBean id='ipMessagingBean' class='yatospace.ip_messaging.web.bean.MessagingBean' scope='session'></jsp:useBean>
<c:out value='${ipMessagingBean.initialize(pageContext.request)}'></c:out>

<script>
	default_app_path = '${pageContext.request.contextPath}'; 
</script>

<c:if test='${param["frontal_message_submit"] ne null}'>
	<c:out value='${ipMessagingBean.addFrontalMessage(pageContext.request)}'></c:out>
</c:if>
    
<h1 align='center'>Yatospace Airlines</h1>
<div align='center'>
	<table>
		<tr>
			<td><a href='javascript:ajax_load("${pageContext.request.contextPath}", "/WEB-INF/JSTL-PREVIEW/incomming.jsp", "preview_place_of_reservation_forms")' style='text-decoration:none'>Доласци</a>&nbsp;&nbsp;&nbsp; </td>
			<td><a href='javascript:ajax_load("${pageContext.request.contextPath}", "/WEB-INF/JSTL-PREVIEW/outcomming.jsp", "preview_place_of_reservation_forms")' style='text-decoration:none'>одласци</a>&nbsp;&nbsp;&nbsp;</td>
			<td><a href='javascript:ajax_load("${pageContext.request.contextPath}", "/WEB-INF/JSTL-PREVIEW/fly_reservation.jsp", "preview_place_of_reservation_forms")' style='text-decoration:none'>резервације лета</a>&nbsp;&nbsp;&nbsp;</td>
			<td><a href='javascript:ajax_load("${pageContext.request.contextPath}", "/WEB-INF/JSTL-PREVIEW/all_reservation.jsp", "preview_place_of_reservation_forms")' style='text-decoration:none'>све резервације</a>&nbsp;&nbsp;&nbsp;</td>
			<td><a href='javascript:ajax_load("${pageContext.request.contextPath}", "/WEB-INF/JSTL-PREVIEW/flight.jsp", "preview_place_of_reservation_forms")' style='text-decoration:none'>летови</a>&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
</div>
<br>
<div align='center'><jsp:include page='/WEB-INF/FORMS/companies.jsp'></jsp:include></div>
<br>
<div align='center' id='preview_place_of_reservation_forms'></div>
<script>ajax_load("${pageContext.request.contextPath}", "/WEB-INF/JSTL-PREVIEW/incomming.jsp", "preview_place_of_reservation_forms")</script>

<div style='display:none' id="googleMap" style="width:100%;height:400px;"></div>
<div id="yandexMap" style="width:100%;height:400px;"></div>

<script>
	function myMap() {
		var mapProp= {
		  center:new google.maps.LatLng(51.508742,-0.120850),
		  zoom:5,
		};
		var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
	}
</script>


<script src="https://maps.googleapis.com/maps/api/js?key=&callback=myMap"></script>
<script>document.getElementById('yi_1_design_specific_menu_content').parentNode.style['vertical-align']='top'</script>

<c:set var='ia' value='${internetAccessBean.yandexAvailable()}'></c:set>
<c:if test='${ia}'>
	<script>yamp_go('yandexMap')</script>
</c:if>
<c:if test='${not ia}'>
	<script>document.getElementById('yandexMap').style.display='none'</script>
</c:if>
<br>
<div id='top_outcome_place' style='height:150px'><jsp:include page='/WEB-INF/JSTL-PREVIEW/top-outcome.jsp'></jsp:include></div>
<div id='top_income_place' style='height:150px'><jsp:include page='/WEB-INF/JSTL-PREVIEW/top-income.jsp'></jsp:include></div>
<br>
<style>
	@media screen and (max-width: 800px) {
		.left_logo {
  			display: none;
		}
	}
</style>
<div align='right'>
	<div class='left_logo' style='float: left;'>
		<h1>&nbsp;YATOSPACE AIRLINES</h1>
		<form method='POST' name='frontal_message'>
			<table>
				<tr>
					<td>Електронска пошта: </td>
					<td><input type='email' name='frontal_message_email' value=''/></td>
				</tr>
				<tr>
					<td colspan='2'><br>Садржај поруке: </td>
				</tr>
				<tr>
					<td colspan='2'>
						<textarea name='frontal_message_content' rows="5" cols="30"></textarea>
					</td>
				</tr>
			</table>
			<br>
			<div align='left'>&nbsp;&nbsp;&nbsp;<input type='submit' name='frontal_message_submit' value='ПОШАЉИ'/>
				<c:out value="${ipMessagingBean.frontalMessageNotification}"></c:out>
				<c:out value="${ipMessagingBean.resetFrontalMessageNotification()}"></c:out>
			</div>
		</form>
		<br>
	</div>
	<div style='background-image: url("http://localhost:8085/${pageContext.request.contextPath}/LOGOTYPE/Aeroplane.jpg"); width: 300px; height: 300px; background-size: 300px 300px;'>
		<div style='background-color: rgba(255,255,255,0.75); width: 300px; height: 300px;'>
			<p align='center'>
				<br>
				Yatospace 
				<br>
				Аеродром 10
				<br>
				Бања Лука
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<a href='https://www.yatospace.com'>www.yatospace.com</a>
				<br>
				<a href='mailto:contact@yatospace.com'>contact@yatospace.com</a>
			<p>
		</div>
	</div>
</div>
<br>
<p align='center'><b>ЕТФБЛ ИП 2021. Сва права задржана.</b></p>

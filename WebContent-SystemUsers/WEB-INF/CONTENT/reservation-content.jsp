<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='internetAccessBean' class='yatospace.common.util.bean.InterentConnectionBean' scope='request'></jsp:useBean>

<br>
<b>КОРИСНИК СИСТЕМА - ЛЕТОВИ И РЕЗЕРВАЦИЈЕ</b>
<br>
<br>
<h1 align='center'>Yatospace Airlines</h1>

<div align='justify'>На овој страници могуће је резервиасати карте за летове. 
Прегледати одласке, доласке и резервације, може се резервисати лет 
или превоз робе. </div>

<h3 align = 'center'>КОРИСНИК - РЕЗЕРВАЦИЈЕ </h3>
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


<div style='displaty:none' id="googleMap" style="width:100%;height:400px;"></div>
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

<p align='center'><b>ЕТФБЛ ИП 2021. Сва права задржана.</b></p>
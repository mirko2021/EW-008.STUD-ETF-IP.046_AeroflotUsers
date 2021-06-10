<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='travelBean' class='yatospace.common.tt.web.bean.TravelBean' scope='session'></jsp:useBean>
<c:out value='${travelBean.initialize(pageContext.request)}'></c:out>

<c:set var='msg_indicator' value='0'></c:set>
<c:if test='${param["travel_reservation_submit"] ne null}'>
	<c:set var='insert_result' value='${travelBean.reservate(pageContext.request)}'></c:set>
	<c:if test='${    insert_result}'><c:set var='msg_indicator' value='1'></c:set></c:if>
	<c:if test='${not insert_result}'><c:set var='msg_indicator' value='2'></c:set></c:if>
</c:if>

<p>РЕЗЕРВАЦИЈА ЛЕТА - ПУТОВАЊЕ</p>
<div>Мјесто полазака - држава : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/countries-travel-source-location.jsp'></jsp:include>
<div>Мјесто доласка - држава : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/countries-travel-destination-location.jsp'></jsp:include>
<div>Мјесто полазака - град и адреса : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/cities-travel-source-location.jsp'></jsp:include>
<div>Мјесто доласка - град и адреса : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/cities-travel-destination-location.jsp'></jsp:include><br>
<div>Летови : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/flight-travel-table-form.jsp'></jsp:include><br>
<form name='travel_form' method='POST'>
	<label for="place_count">Број мјеста за путнике : </label><br>
	<input type="text" id="place_count" name="place_count" /><br><br>
	<input type='submit' value='Резервација' id='travel_reservation_submit' name='travel_reservation_submit' onclick='return confirm("Да ли заиста желите резервисати лет?")'/>
</form>
<jsp:include page='/WEB-INF/FORM-PARTS/reservations_form_transport.jsp'></jsp:include>

<c:if test='${msg_indicator eq "1"}'><br><div>Резервација лета је успјешна. <c:out value='${travelBean.rId}'></c:out></div></c:if>
<c:if test='${msg_indicator eq "2"}'><br><div>Резервација лета није успјешна.</div></c:if>
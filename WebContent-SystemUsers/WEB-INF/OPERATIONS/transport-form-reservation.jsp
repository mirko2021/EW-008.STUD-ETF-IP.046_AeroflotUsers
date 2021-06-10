<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='transportBean' class='yatospace.common.tt.web.bean.TransportBean' scope='session'></jsp:useBean>
<c:out value='${transportBean.initialize(pageContext.request)}'></c:out>

<jsp:useBean id='travelBean' class='yatospace.common.tt.web.bean.TravelBean' scope='session'></jsp:useBean>
<c:out value='${travelBean.initialize(pageContext.request)}'></c:out>

<c:if test='${param["ast-upload"] ne null}'>
	<c:out value='${transportBean.setAST(pageContext.request)}'></c:out>
</c:if>
<c:if test='${param["ast-cancel"] ne null}'>
	<c:out value='${transportBean.resetAST(pageContext.request)}'></c:out>
</c:if>
<c:set var='msg_indicator' value='0'></c:set>
<c:if test='${param["transport_reservation_submit"] ne null}'>
	<c:set var='insert_result' value='${transportBean.reservate(pageContext.request)}'></c:set>
	<c:if test='${    insert_result}'><c:set var='msg_indicator' value='1'></c:set></c:if>
	<c:if test='${not insert_result}'><c:set var='msg_indicator' value='2'></c:set></c:if>
</c:if>

<p>РЕЗЕРВАЦИЈА - ТРАНСПОРТ</p>
<div>Мјесто полазака - држава : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/countries-transport-source-location.jsp'></jsp:include>
<div>Мјесто доласка - држава : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/countries-transport-destination-location.jsp'></jsp:include>
<div>Мјесто полазака - град и адреса : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/cities-transport-source-location.jsp'></jsp:include>
<div>Мјесто доласка - град и адреса : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/cities-transport-destination-location.jsp'></jsp:include><br>
<div>Летови : </div>
<jsp:include page='/WEB-INF/FORM-PARTS/flight-transport-table-form.jsp'></jsp:include><br>
<form name='transport_form' method='POST' enctype = "multipart/form-data">
	<label for="article_description">Опис робе за превоз : </label><br>
	<textarea id="article_description" name="article_description"></textarea><br><br>
	<label for="article_transport_spec_file">Датотека спецификације превоза робе : </label><br>
	<input type="file" id="article_transport_spec_file" name="article_transport_spec_file"/>
	<br><br>
	<input type='submit' id='reservation-submit' name='transport_reservation_submit' value='Резервација' formenctype='application/x-www-form-urlencoded' onclick='return confirm("Да ли заиста желите резервисати превоз робе?")'/>
	<input type='submit' id='ast-upload' name='ast-upload' value='подизање спецификације превоза робе'/>
	<input type='submit' id='ast-cancel' name='ast-cancel' value='поништавање спецификације превоза робе' formenctype='application/x-www-form-urlencoded'/>
</form>
<jsp:include page='/WEB-INF/FORM-PARTS/reservations_form_travel.jsp'></jsp:include>
<c:if test='${msg_indicator eq "1"}'><br><div>Резервација лета је успјешна. <c:out value='${transportBean.rId}'></c:out></div></c:if>
<c:if test='${msg_indicator eq "2"}'><br><div>Резервација лета није успјешна.</div></c:if>
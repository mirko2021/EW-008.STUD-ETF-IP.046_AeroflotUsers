<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='countriesFormBeanTravelDestination' class='yatospace.flag.web.bean.CountriesFormBean' scope='session'></jsp:useBean>
<jsp:useBean id='travelBean' class='yatospace.common.tt.web.bean.TravelBean' scope='session'></jsp:useBean>

<c:out value='${travelBean.initialize(pageContext.request)}'></c:out>
<c:out value='${locationBean.setSession(pageContext.session)}'></c:out>
<c:out value='${locationBean.initialize(pageContext.session)}'></c:out>
<c:out value="${countriesFormBeanTravelDestination.initialize(pageContext.request)}"></c:out>

<c:if test='${param["flag_io_travel_src_set"] ne null}'><c:out value='${travelBean.setSource(pageContext.request)}'></c:out></c:if>
<c:if test='${param["flag_io_travel_src_reset"] ne null}'><c:out value='${travelBean.resetSource(pageContext.request)}'></c:out></c:if>
<c:if test='${param["flag_io_travel_city_src_set"] ne null}'><c:out value='${travelBean.setSourceCity(param["flag_travel_src_io_city"])}'></c:out></c:if>
<c:if test='${param["flag_io_travel_city_src_reset"] ne null}'><c:out value='${travelBean.setSourceCity("")}'></c:out></c:if>

<form id='flag_io_travel_city_src' name='flag_io_travel_city_src' action='#flag_io_travel_city_src'>
	<select name='flag_travel_src_io_city'>
		<c:forEach var='city_address' items='${travelBean.getSourceAddresses()}'>
			<option><c:out value="${city_address}"></c:out></option>
		</c:forEach>
	</select>
	<br>
	<c:if test='${travelBean.sourceCity ne ""}'>
		<br><code><c:out value="${travelBean.sourceCity}"></c:out></code><br>
	</c:if>
	<br>
	<input type='submit' name='flag_io_travel_city_src_set'   value='Постављање'/>
	<input type='submit' name='flag_io_travel_city_src_reset' value='ресетовање'/>
	<br><br>
</form>
<br>
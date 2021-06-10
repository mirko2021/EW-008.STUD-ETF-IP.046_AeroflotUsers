<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='countriesFormBeanTransportSource' class='yatospace.flag.web.bean.CountriesFormBean' scope='session'></jsp:useBean>
<jsp:useBean id='transportBean' class='yatospace.common.tt.web.bean.TransportBean' scope='session'></jsp:useBean>

<c:out value='${transportBean.initialize(pageContext.request)}'></c:out>
<c:out value='${locationBean.setSession(pageContext.session)}'></c:out>
<c:out value='${locationBean.initialize(pageContext.session)}'></c:out>
<c:out value="${countriesFormBeanTransportSource.initialize(pageContext.request)}"></c:out>

<c:if test='${param["flag_io_transport_src_set"] ne null}'><c:out value='${transportBean.setSource(pageContext.request)}'></c:out></c:if>
<c:if test='${param["flag_io_transport_src_reset"] ne null}'><c:out value='${transportBean.resetSource(pageContext.request)}'></c:out></c:if>
<c:if test='${param["flag_io_transport_city_src_set"] ne null}'><c:out value='${transportBean.setSourceCity(param["flag_transport_src_io_city"])}'></c:out></c:if>
<c:if test='${param["flag_io_transport_city_src_reset"] ne null}'><c:out value='${transportBean.setSourceCity("")}'></c:out></c:if>

<form id='flag_io_transport_src' name='flag_io_transport_src' action='#flag_io_transport_src'>
	<select name='flag_transport_src_io_city'>
		<c:forEach var='city_address' items='${transportBean.getSourceAddresses()}'>
			<option><c:out value="${city_address}"></c:out></option>
		</c:forEach>
	</select>
	<br>
	<c:if test='${transportBean.sourceCountry ne ""}'>
		<br><code><c:out value="${transportBean.sourceCity}"></c:out></code><br>
	</c:if>
	<br>
	<input type='submit' name='flag_io_transport_city_src_set' value='Постављање'/>
	<input type='submit' name='flag_io_transport_city_src_reset' value='ресетовање'/>
	<br><br>
</form>
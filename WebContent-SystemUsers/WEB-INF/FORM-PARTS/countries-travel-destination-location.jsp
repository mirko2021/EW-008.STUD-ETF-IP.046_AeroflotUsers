<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='countriesFormBeanTravelDestination' class='yatospace.flag.web.bean.CountriesFormBean' scope='session'></jsp:useBean>
<jsp:useBean id='travelBean' class='yatospace.common.tt.web.bean.TravelBean' scope='session'></jsp:useBean>

<c:out value='${travelBean.initialize(pageContext.request)}'></c:out>
<c:out value='${locationBean.setSession(pageContext.session)}'></c:out>
<c:out value='${locationBean.initialize(pageContext.session)}'></c:out>
<c:out value="${countriesFormBeanTravelDestination.initialize(pageContext.request)}"></c:out>

<c:if test='${param["flag_io_travel_dest_set"] ne null}'><c:out value='${travelBean.setDestination(pageContext.request)}'></c:out></c:if>
<c:if test='${param["flag_io_travel_dest_reset"] ne null}'><c:out value='${travelBean.resetDestination(pageContext.request)}'></c:out></c:if>

<form id='flag_io_travel_dest' name='flag_io_travel_dest' action='#flag_io_travel_dest'>
	<select name='flag_travel_dest_io_country'>
		<c:forEach var='country' items='${countriesFormBeanTravelDestination.get()}'>
			<c:if test='${countriesFormBeanTravelDestination.previewNameSelcted().contentEquals(countriesFormBeanTravelDestination.previewName(country))}'>
				<option selected><c:out value="${countriesFormBeanTravelDestination.previewName(country)}"></c:out></option>
			</c:if>
			<c:if test='${not countriesFormBeanTravelDestination.previewNameSelcted().contentEquals(countriesFormBeanTravelDestination.previewName(country))}'>
				<option><c:out value="${countriesFormBeanTravelDestination.previewName(country)}"></c:out></option>
			</c:if>
		</c:forEach>
	</select>
	<br>
	<c:if test='${travelBean.destinationCountry ne ""}'>
		<br>
		<c:set var='cc' value='${travelBean.destinationCountry}'></c:set>
		<table>
			<tr>
				<td align='left'><c:out value="${countriesFormBeanTravelDestination.get(cc).name}"></c:out></td>
			</tr>
			<tr>
				<td align='left'><img style='border: 1px solid black; background-color: lightgray; padding: 10px 10px 10px 10px' width='70' height='50' src='${countriesFormBeanTravelDestination.get(cc).flagHref}'/></td>
			</tr>
			<tr>
				<td align='left'>${countriesFormBeanTravelDestination.get(cc).alpha3Code}</td>
			</tr>
			<tr>
				<td align='left'>${countriesFormBeanTravelDestination.get(cc).alpha2Code}</td>
			</tr>
		</table>
	</c:if>
	<br>
	<input type='submit' name='flag_io_travel_dest_set' value='Постављање'/>
	<input type='submit' name='flag_io_travel_dest_reset' value='ресетовање'/>
	<br><br>
</form>
<br>
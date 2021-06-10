<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='countriesFormBeanUIUpdtate' class='yatospace.flag.web.bean.CountriesFormBean' scope='session'></jsp:useBean>
<jsp:useBean id='locationBeanUIUpdtate' class='yatospace.common.user.web.bean.LocationBean' scope='session'></jsp:useBean> 

<c:out value="${countriesFormBeanUIUpdtate.initialize(pageContext.request)}"></c:out>

<form id='flag_io' name='flag_io' action='#flag_io'>
	<select name='flag_io_country'>
		<c:forEach var='country' items='${countriesFormBeanUIUpdtate.get()}'>
			<c:if test='${countriesFormBeanUIUpdtate.previewNameSelcted().contentEquals(countriesFormBeanUIUpdtate.previewName(country))}'>
				<option selected><c:out value="${countriesFormBeanUIUpdtate.previewName(country)}"></c:out></option>
			</c:if>
			<c:if test='${not countriesFormBeanUIUpdtate.previewNameSelcted().contentEquals(countriesFormBeanUIUpdtate.previewName(country))}'>
				<option><c:out value="${countriesFormBeanUIUpdtate.previewName(country)}"></c:out></option>
			</c:if>
		</c:forEach>
	</select>
	<br>
	<c:if test='${countriesFormBeanUIUpdtate.selectedA2C ne ""}'>
		<br>
		<c:set var='cc' value='${countriesFormBeanUIUpdtate.selectedA2C}'></c:set>
		<table>
			<tr>
				<td align='left'><c:out value="${countriesFormBeanUIUpdtate.get(cc).name}"></c:out></td>
			</tr>
			<tr>
				<td align='left'><img style='border: 1px solid black; background-color: lightgray; padding: 10px 10px 10px 10px' width='70' height='50' src='${countriesFormBeanUIUpdtate.get(cc).flagHref}'/></td>
			</tr>
			<tr>
				<td align='left'>${countriesFormBeanUIUpdtate.get(cc).alpha3Code}</td>
			</tr>
			<tr>
				<td align='left'>${countriesFormBeanUIUpdtate.get(cc).alpha2Code}</td>
			</tr>
		</table>
	</c:if>
	<br>
	<input type='submit' name='flag_io_set' value='Постављање'/>
	<input type='submit' name='flag_io_reset' value='ресетовање'/>
	<br><br>
</form>
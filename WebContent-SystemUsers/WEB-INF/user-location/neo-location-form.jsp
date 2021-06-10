<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='locationBeanUIUpdtate' class='yatospace.common.user.web.bean.LocationBean' scope='session'></jsp:useBean>
<jsp:useBean id='countriesFormBeanUIUpdtate' class='yatospace.flag.web.bean.CountriesFormBean' scope='session'></jsp:useBean>

<c:out value='${locationBeanUIUpdtate.initialize(pageContext.request, countriesFormBeanUIUpdtate)}'></c:out>

<form method='POST'>
	<table>
		<tr>
			<td>Локација: </td>
			<td><input id='uc_loc_city' name='uc_loc_city' type='text' value='${locationBeanUIUpdtate.escapeAddress()}'/></td>
		</tr>
		<tr>
			<td colspan='2'>Адреса, опис, информације, напомене: </td>
		</tr>
		<tr>	
			<td colspan='2'><textarea id='uc_loc_address' name='uc_loc_address' rows="30" cols="50"><c:out value='${locationBeanUIUpdtate.note}'></c:out></textarea></td>
		</tr>
	</table>
	<br>
	<input type='submit' id='uc_loc_accept_form'    name='uc_loc_accept_form'    value='Пријем информација о локацији'/>
	<input type='submit' id='uc_loc_accept_country' name='uc_loc_accept_country' value='пријем информација о држави'/>
	<input type='submit' id='uc_loc_reset'          name='uc_loc_reset'          value='ресетовање'/>
</form>
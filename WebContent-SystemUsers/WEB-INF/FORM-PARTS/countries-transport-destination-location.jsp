<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='countriesFormBeanTransportDestination' class='yatospace.flag.web.bean.CountriesFormBean' scope='session'></jsp:useBean>
<jsp:useBean id='transportBean' class='yatospace.common.tt.web.bean.TransportBean' scope='session'></jsp:useBean>

<c:out value='${transportBean.initialize(pageContext.request)}'></c:out>
<c:out value='${locationBean.setSession(pageContext.session)}'></c:out>
<c:out value='${locationBean.initialize(pageContext.session)}'></c:out>
<c:out value="${countriesFormBeanTransportDestination.initialize(pageContext.request)}"></c:out>

<c:if test='${param["flag_io_transport_dest_set"] ne null}'><c:out value='${transportBean.setDestination(pageContext.request)}'></c:out></c:if>
<c:if test='${param["flag_io_transport_dest_reset"] ne null}'><c:out value='${transportBean.resetDestination(pageContext.request)}'></c:out></c:if>

<form id='flag_io_transport_dest' name='flag_io_transport_dest' action='#flag_io_transport_dest'>
	<select name='flag_transport_dest_io_country'>
		<c:forEach var='country' items='${countriesFormBeanTransportDestination.get()}'>
			<c:if test='${countriesFormBeanTransportDestination.previewNameSelcted().contentEquals(countriesFormBeanTransportDestination.previewName(country))}'>
				<option selected><c:out value="${countriesFormBeanTransportDestination.previewName(country)}"></c:out></option>
			</c:if>
			<c:if test='${not countriesFormBeanTransportDestination.previewNameSelcted().contentEquals(countriesFormBeanTransportDestination.previewName(country))}'>
				<option><c:out value="${countriesFormBeanTransportDestination.previewName(country)}"></c:out></option>
			</c:if>
		</c:forEach>
	</select>
	<br>
	<c:if test='${transportBean.destinationCountry ne ""}'>
		<br>
		<c:set var='cc' value='${transportBean.destinationCountry}'></c:set>
		<table>
			<tr>
				<td align='left'><c:out value="${countriesFormBeanTransportDestination.get(cc).name}"></c:out></td>
			</tr>
			<tr>
				<td align='left'><img style='border: 1px solid black; background-color: lightgray; padding: 10px 10px 10px 10px' width='70' height='50' src='${countriesFormBeanTransportDestination.get(cc).flagHref}'/></td>
			</tr>
			<tr>
				<td align='left'>${countriesFormBeanTransportDestination.get(cc).alpha3Code}</td>
			</tr>
			<tr>
				<td align='left'>${countriesFormBeanTransportDestination.get(cc).alpha2Code}</td>
			</tr>
		</table>
	</c:if>
	<br>
	<input type='submit' name='flag_io_transport_dest_set' value='Постављање'/>
	<input type='submit' name='flag_io_transport_dest_reset' value='ресетовање'/>
	<br><br>
</form>
<br>
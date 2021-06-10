<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="locationTrafficRelationsUIUpdate"  class="studiranje.ip.admin.jsp.bean.LocationBean" scope='session'></jsp:useBean>

<form name='country_cities_explorer' method='POST'>
	<label>
		Мјесто : 
		<select name="location_for_trafic_rule">
		  <option value="0"></option>
		  <c:set var='counter' value='1'></c:set>
		  <c:forEach var='location' items='${locationTrafficRelationsUIUpdate.list(pageContext.session)}'>
			  <c:if test="${locationTrafficRelationsUIUpdate.getLocationValue(pageContext.session) eq location}">
			  	<option value="${counter}" selected><c:out value='${location}'></c:out></option>
			  </c:if>
			  <c:if test="${locationTrafficRelationsUIUpdate.getLocationValue(pageContext.session) ne location}">
			  	<option value="${counter}"><c:out value='${location}'></c:out></option>
			  </c:if>
		  	  <c:set var='counter' value='${counter+1}'></c:set>
		  </c:forEach>
		</select>
	</label>
	<input type="submit" name='city_for_submit' value="Постављање"/>
</form>
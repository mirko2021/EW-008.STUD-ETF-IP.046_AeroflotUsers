<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:out value='${locationBean.setSession(pageContext.session)}'></c:out>
<c:out value='${locationBean.initialize(pageContext.session)}'></c:out>

<c:if test='${param["flag_io_set"] ne null}'>
	<c:out value='${countriesFormBean.set(pageContext.request)}'></c:out>
</c:if>

<c:if test='${param["flag_io_reset"] ne null}'>
	<c:out value='${countriesFormBean.reset(pageContext.request)}'></c:out>
</c:if>

<br>АДМИНИСТРИРАЊЕ ЛОКАЦИЈА<br><br>
<f:view>
	<h:form id="location-form">  
		<table>
			<tr>
				<td><h:outputLabel for="location">Локација : </h:outputLabel></td>
    			<td><h:inputText id="location" value="#{locationBean.locationName}" required="true"/></td> 
    		</tr>
    	</table>
    	<br>
    	<h:commandButton id="add-button" value="Додавање" action='#{locationBean.add}'/>
    	<h:commandButton id="remove-button" value="брисање" action='#{locationBean.remove}'/>
    	<h:commandButton id="update-button" value="измјена" action='#{locationBean.update}'/>
    	<h:commandButton id="select-button" value="избор" action='#{locationBean.select}'/>
    	<h:commandButton id="reset-button" value="ресетовање" action='#{locationBean.reset}'/><br><br>
    </h:form>
</f:view>
<jsp:include page='/WEB-INF/WEB-FORM/countries-form.jsp'></jsp:include>
<div id='#location_lists'>
	<c:forEach var='location' items='${locationBean.list()}'>
		<a style='text-decoration: none' href='${pageContext.request.contextPath}/faces/home-admin-location.jsp#location_lists' target='_self'><c:out value='${location.locationName}'></c:out></a><br>
	</c:forEach>
</div>
<br><br>

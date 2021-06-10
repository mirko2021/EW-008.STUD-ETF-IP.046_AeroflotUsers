<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='countriesFormBeanUIUpdtate' class='yatospace.flag.web.bean.CountriesFormBean' scope='session'></jsp:useBean>
<jsp:useBean id='locationBeanUIUpdtate' class='yatospace.common.user.web.bean.LocationBean' scope='session'></jsp:useBean>

<c:out value='${locationBean.setSession(pageContext.session)}'></c:out>
<c:out value='${locationBean.initialize(pageContext.session)}'></c:out>

<c:out value="${countriesFormBeanUIUpdtate.initialize(pageContext.request)}"></c:out>

<c:if test='${param["flag_io_set"] ne null}'>
	<c:out value='${countriesFormBeanUIUpdtate.set(pageContext.request)}'></c:out>
	<c:out value='${locationBeanUIUpdtate.accept(countriesFormBeanUIUpdtate)}'></c:out>	
</c:if>

<c:if test='${param["flag_io_reset"] ne null}'>
	<c:out value='${countriesFormBeanUIUpdtate.reset(pageContext.request)}'></c:out>
	<c:out value='${locationBeanUIUpdtate.accept(countriesFormBeanUIUpdtate)}'></c:out>
</c:if>

<c:if test='${param["uc_loc_accept_form"] ne null}'>
	<c:out value='${locationBeanUIUpdtate.accept(pageContext.request)}'></c:out>
</c:if>

<c:if test='${param["uc_loc_accept_country"] ne null}'>
	<c:out value='${locationBeanUIUpdtate.accept(countriesFormBeanUIUpdtate)}'></c:out>
</c:if>

<c:if test='${param["uc_loc_reset"] ne null}'>
	<c:out value='${locationBeanUIUpdtate.reset()}'></c:out>
</c:if>

<c:if test='${param["ipm_ops_submit"] ne null}'>
	<c:out value='${locationBeanUIUpdtate.dataUpdate()}'></c:out>
</c:if>

<c:if test='${param["ipm_ops_reset"] ne null}'>
	<c:out value='${locationBeanUIUpdtate.dataReset()}'></c:out>
</c:if>

<c:if test='${param["ipm_ops_message_reset"] ne null}'>
	<c:out value='${locationBeanUIUpdtate.resetMessage()}'></c:out>
</c:if>

<c:out value='${locationBeanUIUpdtate.apply(countriesFormBeanUIUpdtate)}'></c:out>

<p>ИЗБОР И ПРЕГЛЕД ЛОКАЦИЈЕ КОРИСНИКА - ИЗБОР СВИХ ПОДАТАКА ЛОКАЦИЈЕ ИЗ БАЗЕ</p>
<jsp:include page="/WEB-INF/user-location/db-exists-location-form.jsp"></jsp:include>

<p>ИЗБОР И ПРЕГЛЕД ЛОКАЦИЈЕ КОРИСНИКА - ИЗБОР ДРЖАВЕ</p>
<jsp:include page="/WEB-INF/user-location/update-info-countries-form.jsp"></jsp:include>

<p>ИЗБОР И ПРЕГЛЕД ЛОКАЦИЈЕ КОРИСНИКА - ИЗБОР МЈЕСТА И ОПИС ЛИКАЦИЈЕ</p>
<jsp:include page="/WEB-INF/user-location/neo-location-form.jsp"></jsp:include>

<p>ИЗБОР И ПРЕГЛЕД ЛОКАЦИЈЕ КОРИСНИКА - ОПЕРАЦИЈЕ</p>
<jsp:include page="/WEB-INF/user-location/user-location-operations.jsp"></jsp:include>

<br>
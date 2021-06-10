<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean  id='userProfileBean' class='yatospace.common.user.web.bean.UserProfileBean' scope='session'></jsp:useBean>
<c:out value='${userProfileBean.initialize(pageContext.request)}'></c:out>

<jsp:include page='/WEB-INF/reservation/reservation-header.jsp'></jsp:include>
<c:if test='${userProfileBean.profile.transportModeEnabled}'>
	<jsp:include page='/WEB-INF/OPERATIONS/transport-form-reservation.jsp'></jsp:include>
	<br>
</c:if>

<c:if test='${userProfileBean.profile.travelModeEnables}'>
	<jsp:include page='/WEB-INF/OPERATIONS/travel-form-reservation.jsp'></jsp:include>
	<br>
</c:if>
<br>
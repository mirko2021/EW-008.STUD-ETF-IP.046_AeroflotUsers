<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='locationBeanUIUpdtate' class='yatospace.common.user.web.bean.LocationBean' scope='session'></jsp:useBean>

<form name='ipm_ops' method='POST'>
	<input type='submit' name='ipm_ops_submit' value='Потврда података о локацији'/>
	<input type='submit' name='ipm_ops_reset' value='поништавање података о локацији'/>
	<input type='submit' name='ipm_ops_message_reset' value='поништавање поруке'/>	
</form>
<c:out value='${locationBeanUIUpdtate.message}'></c:out>
<br>
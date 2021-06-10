<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<jsp:useBean id='userDataBean' class='yatospace.user.role.web.bean.UserDataBean' scope='session'></jsp:useBean>
<c:out value="${userDataBean.initialize(pageContext.request)}"></c:out>

<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>	
	<c:if test="${param['submit_user_data'] ne null}">
		<c:out value="${userDataBean.setFirstName(param['first_name'])}"></c:out>
		<c:out value="${userDataBean.setSecondName(param['second_name'])}"></c:out>
		<c:out value="${userDataBean.setUserNote(param['user_note'])}"></c:out>
		<c:out value="${userDataBean.accept()}"></c:out>
	</c:if>
</c:if>

<c:if test='${not designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>	
	<c:redirect url='/home-users.jsp'></c:redirect>
</c:if>
<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
	<tiles:insertDefinition name="userDataPage"/>
</c:if>

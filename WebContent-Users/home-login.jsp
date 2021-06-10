<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<c:if test='${param["login"] ne null}'>
	<tiles:insertDefinition name="loginPage" />
	<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
		<c:redirect url='/home-logout.jsp'></c:redirect>
	</c:if>
</c:if>

<c:if test='${param["login"] eq null}'>
	<jsp:useBean id='userDataBean' class='yatospace.user.role.web.bean.UserDataBean' scope='session'></jsp:useBean>
	<jsp:useBean id='userRoleBean' class='yatospace.user.role.web.bean.UserRoleBean' scope='session'></jsp:useBean>
	
	<c:out value="${userDataBean.initialize(pageContext.request)}"></c:out>
	<c:out value="${userDataBean.reset()}"></c:out>
	
	<c:out value="${userRoleBean.initialize(pageContext.request)}"></c:out>
	<c:out value="${userRoleBean.reset()}"></c:out>
	
	<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
		<c:redirect url='/home-logout.jsp'></c:redirect>
	</c:if>
	<c:if test='${not designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
		<tiles:insertDefinition name="loginPage" />
	</c:if>
</c:if>

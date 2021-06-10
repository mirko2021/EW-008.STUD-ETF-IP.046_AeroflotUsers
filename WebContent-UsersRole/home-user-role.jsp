<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<jsp:useBean id='userRoleBean' class='yatospace.user.role.web.bean.UserRoleBean' scope='session'></jsp:useBean>
		
<c:out value="${userRoleBean.initialize(pageContext.request)}"></c:out>


<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>	
	<c:if test="${param['submit_user_role'] ne null}">
		<c:out value="${userRoleBean.setUser(param['role_user'] ne null)}"></c:out>
		<c:out value="${userRoleBean.setWorker(param['role_worker'] ne null)}"></c:out>
		<c:out value="${userRoleBean.setAdministrator(param['role_administrator'] ne null)}"></c:out>
		<c:out value="${userRoleBean.accept()}"></c:out>
	</c:if>
</c:if>

<c:if test='${not designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>	
	<c:redirect url='/home-users.jsp'></c:redirect>
</c:if>
<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
	<tiles:insertDefinition name="userRolesPage"/>
</c:if>
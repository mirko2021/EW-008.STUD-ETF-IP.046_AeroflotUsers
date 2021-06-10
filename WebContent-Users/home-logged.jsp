<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<c:if test='${not designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>	
	<c:redirect url='/home-users.jsp'></c:redirect>
</c:if>
<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
	<tiles:insertDefinition name="loggedPage"/>
</c:if>
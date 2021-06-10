<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userRoleBean' class='yatospace.user.role.web.bean.UserRoleBean' scope='session'></jsp:useBean>
<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>
<c:out value="${userRoleBean.initialize(pageContext.request)}"></c:out>

<jsp:include page='/WEB-INF/common-points/index-menu.jsp'></jsp:include>
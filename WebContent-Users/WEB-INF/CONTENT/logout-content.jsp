<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<c:if test='${param["logout_user_choosed"] ne null}'>
	<c:out value='${designGeneralBean.operationalBean.logoutChoosed(pageContext.request)}'></c:out>
</c:if>

<br>
<b>ОДЈАВА КОРИСНИКА</b><br><BR>

<div id='sessions_list_page_place'><jsp:include page='/WEB-INF/LISTS/page_sessions_list.jsp'></jsp:include></div><br>
<div id='sessions_list_content_place'><jsp:include page='/WEB-INF/LISTS/content_sessions_list.jsp'></jsp:include></div>
<div id='sessions_list_operation_place'><jsp:include page='/WEB-INF/CONTENT/classic/logout-form.jsp'></jsp:include></div><br>
<div id='sessions_list_form_place'></div>
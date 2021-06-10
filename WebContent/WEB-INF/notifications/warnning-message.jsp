<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<div class='warnning' style='padding-top:5px; padding-bottom:5px'>
	&nbsp;<span id='server_time'><c:out value="${designGeneralBean.serviceBean.getCurrentServerDate()}"></c:out></span>
   	&nbsp;&nbsp;&nbsp;&nbsp;<a style='text-decoration:none; color:white' href='javascript:ajax_f_gui_message_reset("${pageContext.request.contextPath}")'>X</a>
	&nbsp;<a style='text-decoration:none; color:white' href='${pageContext.request.contextPath}/operations/message_preview.jsp' target='_blank'>V</a>
	&nbsp;&nbsp;&nbsp;&nbsp;<div>&nbsp;<span id='message'><c:out value="${designGeneralBean.messageBean.message}"></c:out></span></div>
	<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>	
		<c:if test="${baseBean.loginBean.tryLogin ne 0}">
				&nbsp;Имамо <c:out value='${baseBean.loginBean.tryLogin}'></c:out> неуспјешних покушаја. 
		</c:if>
		<c:out value='${baseBean.loginBean.resetTryLoginCount()}'></c:out>
	</c:if>
</div>
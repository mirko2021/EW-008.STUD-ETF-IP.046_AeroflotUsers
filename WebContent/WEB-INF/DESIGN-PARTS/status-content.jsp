<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>


<c:if test='${designGeneralBean.messageBean.isInformation()}'>
	<jsp:include page='/WEB-INF/notifications/info-message.jsp'></jsp:include>
</c:if>
<c:if test='${designGeneralBean.messageBean.isSuccess()}'>
	<jsp:include page='/WEB-INF/notifications/success-message.jsp'></jsp:include>
</c:if>
<c:if test='${designGeneralBean.messageBean.isError()}'>
	<jsp:include page='/WEB-INF/notifications/error-message.jsp'></jsp:include>
</c:if>
<c:if test='${designGeneralBean.messageBean.isWarnning()}'>
	<jsp:include page='/WEB-INF/notifications/warnning-message.jsp'></jsp:include>
</c:if>
<c:if test='${designGeneralBean.messageBean.isWait()}'>
	<jsp:include page='/WEB-INF/notifications/wait-message.jsp'></jsp:include>
</c:if>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userImagesBean' class='yatospace.web.bean.UserImagesBean' scope='session'></jsp:useBean>

<c:if test='${param["user_op"] ne null}'>
	<c:if test='${param["user_op"] eq "delete_user_image" }'>
		<c:out value='${userImagesBean.removeUserImage(pageContext.request)}'></c:out>
	</c:if>
	<c:if test='${param["user_op"] eq "reset_user_message"}'>
		<c:out value='${userImagesBean.resetUserImageMessage()}'></c:out>
	</c:if>
</c:if>
<c:if test='${param["profile_op"] ne null}'>
	<c:if test='${param["profile_op"] eq "delete_profile_image"}'>
		<c:out value='${userImagesBean.removeProfileImage(pageContext.request)}'></c:out>
	</c:if>
	<c:if test='${param["profile_op"] eq "reset_profile_message"}'>
		<c:out value='${userImagesBean.resetProfileImageMessage()}'></c:out>
	</c:if>
</c:if>


<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<c:if test='${not designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>	
	<c:redirect url='/home-users.jsp'></c:redirect>
</c:if>
<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
	<tiles:insertDefinition name="adminPage"/>
</c:if>
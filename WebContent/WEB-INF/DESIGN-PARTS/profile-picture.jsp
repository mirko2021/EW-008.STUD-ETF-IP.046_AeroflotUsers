<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userImagesBean' class='yatospace.web.bean.UserImagesBean' scope='session'></jsp:useBean>

<c:if test='${userImagesBean.getProfileImageLocation(pageContext.request) eq null}'>
	<img src='${pageContext.request.contextPath}/WEB-PICTURES/profile-picture.jpg' style='width: 100%; height: 200px'/>
</c:if>
<c:if test='${userImagesBean.getProfileImageLocation(pageContext.request) ne null}'>
	<img src='${pageContext.request.contextPath}/ProfilePictureDownloadServlet' style='width: 100%; height: 200px'/>
</c:if>

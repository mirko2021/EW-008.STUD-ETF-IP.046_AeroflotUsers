<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userImagesBean' class='yatospace.web.bean.UserImagesBean' scope='session'></jsp:useBean>

<c:if test='${userImagesBean.getUserImageLocation(pageContext.request) eq null}'>
	<img src='${pageContext.request.contextPath}/WEB-PICTURES/user-picture.jpeg' style='width: 200px; height: 200px'/>
</c:if>
<c:if test='${userImagesBean.getUserImageLocation(pageContext.request) ne null}'>
	<img src='${pageContext.request.contextPath}/UserPictureDownloadServlet' style='width: 200px; height: 200px'/>
</c:if>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<c:if test='${param["logout_button"] ne null}'>
	<c:out value='${designGeneralBean.operationalBean.logout(pageContext.request)}'></c:out>
</c:if>

<c:if test='${param["logout_user_all"] ne null}'>
	<c:out value='${designGeneralBean.operationalBean.logoutUser(pageContext.request)}'></c:out>
</c:if>


<form name='logout_form' method='POST'>
	<br>
	<input type='hidden' value='[]'               name='logout_form_many_sessions' id='logout_form_many_sessions'>
	<input type='submit' value='Одјава'           name='logout_button'/>
	<input type='submit' value='одјава свега'     name='logout_user_all'/>
	<input type='submit' value='одјава изабраног' name='logout_user_choosed' onclick='selected_session_set_to_json_form_param()'/>
</form>

<c:if test='${param["logout_button"] ne null}'>
	<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
		<script>alert('Одјава корисника није успјела.');</script>
	</c:if>
</c:if>

<c:if test='${param["logout_user_all"] ne null}'>
	<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session)}'>
		<script>alert('Општа одјава корисника није успјела.');</script>
	</c:if>
</c:if>

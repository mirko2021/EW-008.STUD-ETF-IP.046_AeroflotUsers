<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<form name='sessions_list_page_form'>
	<input type='button' value='&lt;' onclick='sessions_page_previous("${pageContext.request.contextPath}")'/>
	<input type='text' id='sessions_list_page_form_page_no' style='width: 40px' value='${baseBean.sessionListBean.page.pageNo}'/> / 
	<input type='text' id='sessions_list_page_form_page_size' style='width: 40px' value='${baseBean.sessionListBean.page.pageSize}'/> :
	<input type='text' id='sessions_list_page_form_total_items' style='width: 40px'  value='${baseBean.sessionListBean.countForUser(baseBean.loginBean.username)}' readonly/>
	<input type='button' value='&gt;' onclick='sessions_page_next("${pageContext.request.contextPath}")'/>
	<input type='text' id='sessions_list_page_form_username_start_filter' placeholder='username with' value='${baseBean.sessionListBean.page.encodeUsernameStartFilter()}'/> 
	<input type='text' id='sessions_list_page_form_session_start_filter' placeholder='session id with' value='${baseBean.sessionListBean.page.encodeSessionIdStartFilter()}'/>
</form>
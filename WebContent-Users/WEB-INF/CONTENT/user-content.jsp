<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<br>
<b>НАЧЕНЛА СТРАНИЦА</b>
<blockquote><p>Садржај не постоји.</p></blockquote>

<dl>
	<dt><b>СЕСИЈА</b></dt>
	<dd><br></dd>
	<dd><c:out value='${pageContext.session.id}'></c:out></dd>
</dl>
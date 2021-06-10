<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='userEmailBean' class='yatospace.email.web.bean.UserEmailBean' scope='session'></jsp:useBean>
<c:out value='${userEmailBean.initialize(pageContext.request)}'></c:out>

<c:if test='${param["uc_email_set"] ne null}'>
	<c:out value='${userEmailBean.put(pageContext.request)}'></c:out>
</c:if>
<c:if test='${param["uc_email_reset"] ne null}'>
	<c:out value='${userEmailBean.reset(pageContext.request)}'></c:out>
</c:if>
<c:if test='${param["uc_bean_reset"] ne null}'>
	<c:out value='${userEmailBean.reset()}'></c:out>
</c:if>
<c:if test='${param["uc_message_reset"] ne null}'>
	<c:out value='${userEmailBean.resetMessage()}'></c:out>
</c:if>

<p>БАРАТАЊЕ ПОДАТКОМ О АДРЕСИ ЕЛЕКТРОНСКОЈ ПОШТЕ ЗА КОРИСНИКА</p>
<form method='POST'>
	<table>
		<tr>
			<td>Електронска пошта : </td>
			<td><input name='uc_email' type='text' value='${userEmailBean.escapeEmail()}'/></td>
		</tr>
	</table>
	<br>
	<input type='submit' name='uc_email_set' value='Постављање'/>
	<input type='submit' name='uc_email_reset' value='поништавање'/>
	<input type='submit' name='uc_bean_reset' value='ресетовање зрна'/>
	<input type='submit' name='uc_message_reset' value='ресетовање поруке'/>
</form>
<c:out value='${userEmailBean.message}'></c:out>
<br>
<table>
	<tr>
		<td><b>ЕЛ. ПОШТА</b>&nbsp;</td>
		<td><b>КОРИСНИК</b>&nbsp;</td>
		<td><b>АДРЕСА</b>&nbsp;</td>
	</tr>
	<c:forEach var='comInfo' items='${userEmailBean.listEmails()}'>
		<tr>
			<td><code><c:out value='${comInfo.emailId}'></c:out></code>&nbsp;</td>
			<td><code><c:out value='${comInfo.username}'></c:out></code>&nbsp;</td>
			<td><code><c:out value='${comInfo.emailAddress}'></c:out></code>&nbsp;</td>
		</tr>
	</c:forEach>
</table>


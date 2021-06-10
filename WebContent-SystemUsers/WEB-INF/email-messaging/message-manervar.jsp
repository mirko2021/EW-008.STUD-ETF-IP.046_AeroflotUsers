<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='ipMessagingBean' class='yatospace.ip_messaging.web.bean.MessagingBean' scope='session'></jsp:useBean>
<c:out value='${ipMessagingBean.initialize(pageContext.request)}'></c:out>

<c:if test='${param["ipm_add"] ne null}'>
	<c:out value='${ipMessagingBean.add(pageContext.request)}'></c:out>
</c:if>
<c:if test='${param["ipm_put"] ne null}'>
	<c:out value='${ipMessagingBean.put(pageContext.request)}'></c:out>
</c:if>
<c:if test='${param["ipm_del"] ne null}'>
	<c:out value='${ipMessagingBean.cancel(pageContext.request)}'></c:out>
</c:if>
<c:if test='${param["ipm_bnr"] ne null}'>
	<c:out value='${ipMessagingBean.reset()}'></c:out>
</c:if>
<c:if test='${param["ipm_msr"] ne null}'>
	<c:out value='${ipMessagingBean.resetNotification()}'></c:out>
</c:if>
<c:if test='${param["ipm_rfr"] ne null}'>
	<c:out value='${ipMessagingBean.refresh(pageContext.request)}'></c:out>
</c:if>

<c:set var='messageFMT' value='${ipMessagingBean.generateFTM()}'></c:set>
    
<div>РАЗМЈЕНА ПОРУКА И ПИТАЊА ИЗМЕЂУ КОРИСНИКА ПРЕМА ЗАПОСЛЕНОМ</div>
<form method='POST'>
	<dl>
		<dt>Порука: </dt>
		<dd><br></dd>
		<dd><input type='text' name='ipm_message_id' value='${messageFMT.escapeMessageId()}' readonly id='ipm_message_id'/></dd>
		<dd><br></dd>
		<dt>Датум (сервером забиљежен): </dt>
		<dd><br></dd>
		<dd><input type='date' name='ipm_message_timestamp_date' value='${messageFMT.createdDateHTML()}' readonly/><input type='time' name='ipm_message_timestamp_time' value='${messageFMT.createdTimeHTML()}' readonly/></dd>
		<dd><br></dd>
		<dt>Измјена (серверски забиљежена): </dt>
		<dd><br></dd>
		<dd><input type='date' name='ipm_message_timestamp_date_change' value='${messageFMT.modifiedDateHTML()}' readonly/><input type='time' name='ipm_message_timestamp_time_change' value='${messageFMT.modifiedTimeHTML()}' readonly/></dd>
		<dd><br></dd>
		<dt>Садржај: </dt>
		<dd><br></dd>
		<dd><textarea name='ipm_message_content' cols='50' rows='10'><c:out value='${ipMessagingBean.message.messageContent}'></c:out></textarea></dd>
	</dl>
	<input type='submit' name='ipm_add' value='Додавање'/>
	<input type='submit' name='ipm_put' value='постављање'/>
	<input type='submit' name='ipm_del' value='поништавање'/>
	<input type='submit' name='ipm_bnr' value='ресетовање зрна/форме'/>
	<input type='submit' name='ipm_msr' value='ресетовање поруке'/>
	<input type='submit' name='ipm_rfr' value='освежавање'/>
</form>
<div><c:out value='${ipMessagingBean.notification}'></c:out></div>
<p>ПРОЧИТАНЕ ПОРУКЕ</p>
<c:forEach var='targetMsg' items='${ipMessagingBean.listRead()}'>
	<table>
		<tr onclick='document.getElementById("ipm_message_id").value=("${targetMsg.message.messageId}")'>
			<td>Порука: </td>
			<td><code><c:out value='${targetMsg.message.messageId}'></c:out></code></td>
		</tr>
		<tr>
			<td>Корисник: </td>
			<td><code><c:out value='${targetMsg.message.username}'></c:out></code></td>
		</tr>
		<tr>
			<td>Креирање: </td>
			<td><code><c:out value='${targetMsg.createdTimestampStandard()}'></c:out></code></td>
		</tr>
		<tr>
			<td>Промјена:</td>
			<td><code><c:out value='${targetMsg.modifiedTimestampStandard()}'></c:out></code></td>
		</tr>
		<tr>
			<td colspan='2'>Садржај:</td>
		</tr>
		<tr>
			<td colspan='2'><code style='white-space:pre'><c:out value='${targetMsg.message.messageContent}'></c:out></code></td>
		</tr>
	</table><br><br>
</c:forEach>
<p>НЕПРОЧИТАНЕ ПОРУКЕ</p>
<c:forEach var='targetMsg' items='${ipMessagingBean.listUnread()}'>
	<table>
		<tr onclick='document.getElementById("ipm_message_id").value=("${targetMsg.message.messageId}")'>
			<td>Порука: </td>
			<td><code><c:out value='${targetMsg.message.messageId}'></c:out></code></td>
		</tr>
		<tr>
			<td>Корисник: </td>
			<td><code><c:out value='${targetMsg.message.username}'></c:out></code></td>
		</tr>
		<tr>
			<td>Креирање: </td>
			<td><code><c:out value='${targetMsg.createdTimestampStandard()}'></c:out></code></td>
		</tr>
		<tr>
			<td>Промјена:</td>
			<td><code><c:out value='${targetMsg.modifiedTimestampStandard()}'></c:out></code></td>
		</tr>
		<tr>
			<td colspan='2'>Садржај:</td>
		</tr>
		<tr>
			<td colspan='2'><code style='white-space:pre'><c:out value='${targetMsg.message.messageContent}'></c:out></code></td>
		</tr>
	</table><br><br>
</c:forEach>
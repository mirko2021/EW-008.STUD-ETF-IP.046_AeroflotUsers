<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:useBean id='ipMessagingBean' class='yatospace.ip_messaging.web.bean.MessagingBean' scope='session'></jsp:useBean>
<c:out value='${ipMessagingBean.initialize(pageContext.request)}'></c:out>

<c:if test='${param["delete_message_id"] ne null and param["delete_message_id"].trim().length() ne 0}'>
	<c:out value='${ipMessagingBean.deleteMessage(pageContext.request)}'></c:out>
</c:if>

<p>ПРЕГЛЕД И УПРАВЉАЊЕ ПОРУКАМА</p>
<form method='POST' id='message_preview_form' name='message_preview_form' action='#message_preview_form'>
	<p>Елемент за претрагу садржаја : </p>
	<textarea rows='25' cols='50' id='message_preview_mf_content'></textarea>
	<br>
	<input type='hidden' value='' id='message_preview_mf_data' name='message_preview_mf_data'/>
	<input type='submit' value='Пријем/освјежавање' name='message_preview_submit'/>
	<input type='button' value='додавање филтра' onclick='addMF(document.getElementById("message_preview_mf_content").value)'/>
	<input type='button' value='брисање филтра' onclick='removeMF(msg_filter_choosed)'/>
</form>
<br>
<form method='POST'>
	<div id='message_preview_mf_list'></div>
</form> 
<script>refreshMFList()</script>
<p align='justify'>Може се одабрати више филтера за претрагу порука по садржају, од обавезних сваки мора бити испуњен, и додатно, ако постоје укључиви, 
од њих бар један мора бити испуњен, ако не постоје ниједна врста филтера, онда ће се приказати све поруке. </p>

<c:if test='${param["message_preview_submit"] ne null}'>
	<code><c:out value='${param["message_preview_mf_data"]}'></c:out></code>
</c:if>

<form name='delete_message_form' id='delete_message_form' action='#delete_message_form' method='POST'>
	<p>ПРОЧИТАНЕ ПОРУКЕ</p>
	<c:forEach var='msgItem' items='${ipMessagingBean.listReadFiltered(pageContext.request)}'>
		<table>
		   <tbody>
		        <tr>
					<td>Порука: </td>
					<td><code><c:out value='${msgItem.message.messageId}'></c:out></code></td>
				</tr>
				<tr>
					<td>Корисник: </td>
					<td><code><c:out value='${msgItem.message.username}'></c:out></code></td>
				</tr>
				<tr>
					<td>Креирање: </td>
					<td><code><c:out value='${msgItem.createdTimestampStandard()}'></c:out></code></td>
				</tr>
				<tr>
					<td>Промјена:</td>
					<td><code><c:out value='${msgItem.modifiedTimestampStandard()}'></c:out></code></td>
				</tr>
				
				<tr>
					<td>Е-Пошта:</td>
					<td><code><a style='text-decoration:none' href='mailto:${msgItem.message.responseEmail}'><c:out value='${msgItem.message.responseEmail}'></c:out></a></code></td>
				</tr>
				<tr>
					<td colspan="2">Садржај:</td>
				</tr>
				<tr>
					<td colspan="2"><code style="white-space:pre">${msgItem.escapeContent()}</code></td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type='submit' value='Брисање' onclick='document.getElementById("delete_message_id").value="${msgItem.escapeMessageId()}"'/>
		<br><br>
	</c:forEach>
	<p>НЕПРОЧИТАНЕ ПОРУКЕ</p>
	<c:forEach var='msgItem' items='${ipMessagingBean.listUnreadFiltered(pageContext.request)}'>
		<table>
		   <tbody>
		        <tr>
					<td>Порука: </td>
					<td><code><c:out value='${msgItem.message.messageId}'></c:out></code></td>
				</tr>
				<tr>
					<td>Корисник: </td>
					<td><code><c:out value='${msgItem.message.username}'></c:out></code></td>
				</tr>
				<tr>
					<td>Креирање: </td>
					<td><code><c:out value='${msgItem.createdTimestampStandard()}'></c:out></code></td>
				</tr>
				<tr>
					<td>Промјена:</td>
					<td><code><c:out value='${msgItem.modifiedTimestampStandard()}'></c:out></code></td>
				</tr>
				
				<tr>
					<td>Е-Пошта:</td>
					<td><code><a style='text-decoration:none' href='mailto:${msgItem.message.responseEmail}'><c:out value='${msgItem.message.responseEmail}'></c:out></a></code></td>
				</tr>
				<tr>
					<td colspan="2">Садржај:</td>
				</tr>
				<tr>
					<td colspan="2"><code style="white-space:pre">${msgItem.escapeContent()}</code></td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type='submit' value='Брисање'  onclick='document.getElementById("delete_message_id").value="${msgItem.escapeMessageId()}"'/><br><br>
		<br>
	</c:forEach>
	<input type='hidden' id='delete_message_id' name='delete_message_id' value=''/>
</form>
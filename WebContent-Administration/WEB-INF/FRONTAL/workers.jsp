<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:out value='${workerAdminBean.initializeSession(pageContext.session)}'></c:out>

<c:if test='${param["w"] ne null}'>
 	<c:out value='${workerAdminBean.set(param["w"])}'></c:out>
</c:if>

<span id='workers_form_target'></span>
<br>
АДМИНИСТРИРАЊЕ ПОДАЦИМА О ЗАПОСЛЕНИМ
<br><br>
<f:view>
	<h:form id="workers-form">  
		<table>
			<tr>
				<td><h:outputLabel for="username-wk"> Корисничко име : </h:outputLabel></td>
			</tr>
    		<tr>
    			<td><h:inputText id="username-wk" value="#{workerAdminBean.userName}"/></td> 
			</tr>
			<tr>
				<td><h:outputLabel for="password-wk"> Лозинка : </h:outputLabel></td>
    		</tr>
    		<tr>	
    			<td><h:inputSecret id="password-wk" value='#{workerAdminBean.userPassword}'/></td> 
    		</tr>
    		<tr>
    			<td><h:outputLabel for="firstname-wk"> Име : </h:outputLabel></td>
    		</tr>
    		<tr>
    			<td><h:inputText id="firstname-wk" value="#{workerAdminBean.firstName}"/></td>
    		</tr>
    		<tr>
    			<td><h:outputLabel for="secondname-wk"> Презиме : </h:outputLabel></td>
    		</tr>
    		<tr>
    			<td><h:inputText id="secondname-wk"  value="#{workerAdminBean.secondName}"/></td>
    		</tr>
    		<tr>
    			<td><h:outputLabel for="usernotes-wk"> Напомене : </h:outputLabel></td>
    		</tr>
    		<tr>
    			<td><h:inputTextarea cols='50' rows='20' id="usernotes-wk" value="#{workerAdminBean.userNotes}"/></td>
    		</tr>
    	</table>
    	<br>
    	<h:commandButton id="add-button-wk" value="Додавање" action='#{workerAdminBean.add}'/>
    	<h:commandButton id="remove-button-wk" value="брисање" action='#{workerAdminBean.remove}'/>
    	<h:commandButton id="update-button-data-wk" value="измјена података" action='#{workerAdminBean.updateData}'/>
    	<h:commandButton id="update-button-data-pw-wk" value="измјена лозинке" action='#{workerAdminBean.updatePassword}'/><br>
    	<h:commandButton id="update-button-data-un-wk" value="измјена корисничког имена" action='#{workerAdminBean.updateUsername}'/>
    	<h:commandButton id="select-button-wk" value="избор" action='#{workerAdminBean.select}'/>
    	<h:commandButton id="reset-button-wk" value="ресетовање" action='#{workerAdminBean.reset}'/><br>
    	<h:commandButton id="degrade-button-wk" value="поништавање привилегије" action='#{workerAdminBean.revokeRole}'/><br><br>
    </h:form>
</f:view>
<div>
	<c:forEach var='worker' items='${workerAdminBean.list()}'>
		<a style='text-decoration:none' href='${pageContext.request.contextPath}/faces/home-admin-workers.jsp?w=${workerAdminBean.url(worker.userName)}#workers_form_target'><c:out value='${worker.userName}'></c:out><br></a>
	</c:forEach>
</div>
<br>
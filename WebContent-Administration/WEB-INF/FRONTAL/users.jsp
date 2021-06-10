<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:out value='${userAdminBean.initializeSession(pageContext.session)}'></c:out>

<c:if test='${param["u"] ne null}'>
 	<c:out value='${userAdminBean.set(param["u"])}'></c:out>
</c:if>

<span id='users_form_target'></span>
<br>
АДМИНИСТРИРАЊЕ ПОДАЦИМА О КОРИСНИЦИМА
<br><br>
<f:view>
	<h:form id="users-form">  
		<table>
			<tr>
				<td><h:outputLabel for="username-wk"> Корисничко име : </h:outputLabel></td>
			</tr>
    		<tr>
    			<td><h:inputText id="username-wk" value="#{userAdminBean.userName}"/></td> 
			</tr>
			<tr>
				<td><h:outputLabel for="password-wk"> Лозинка : </h:outputLabel></td>
    		</tr>
    		<tr>	
    			<td><h:inputSecret id="password-wk" value='#{userAdminBean.userPassword}'/></td> 
    		</tr>
    		<tr>
    			<td><h:outputLabel for="firstname-wk"> Име : </h:outputLabel></td>
    		</tr>
    		<tr>
    			<td><h:inputText id="firstname-wk" value="#{userAdminBean.firstName}"/></td>
    		</tr>
    		<tr>
    			<td><h:outputLabel for="secondname-wk"> Презиме : </h:outputLabel></td>
    		</tr>
    		<tr>
    			<td><h:inputText id="secondname-wk"  value="#{userAdminBean.secondName}"/></td>
    		</tr>
    		<tr>
    			<td><h:outputLabel for="usernotes-wk"> Напомене : </h:outputLabel></td>
    		</tr>
    		<tr>
    			<td><h:inputTextarea cols='50' rows='20' id="usernotes-wk" value="#{userAdminBean.userNotes}"/></td>
    		</tr>
    	</table>
    	<br>
    	<h:commandButton id="add-button-wk" value="Додавање" action='#{userAdminBean.add}'/>
    	<h:commandButton id="remove-button-wk" value="брисање" action='#{userAdminBean.remove}'/>
    	<h:commandButton id="update-button-data-wk" value="измјена података" action='#{userAdminBean.updateData}'/>
    	<h:commandButton id="update-button-data-pw-wk" value="измјена лозинке" action='#{userAdminBean.updatePassword}'/><br>
    	<h:commandButton id="update-button-data-un-wk" value="измјена корисничког имена" action='#{userAdminBean.updateUsername}'/>
    	<h:commandButton id="select-button-wk" value="избор" action='#{userAdminBean.select}'/>
    	<h:commandButton id="reset-button-wk" value="ресетовање" action='#{userAdminBean.reset}'/><br>
    	<h:commandButton id="degrade-button-wk" value="поништавање привилегије" action='#{userAdminBean.revokeRole}'/><br><br>
    </h:form>
</f:view>
<div>
	<c:forEach var='user' items='${userAdminBean.list()}'>
		<a style='text-decoration:none' href='${pageContext.request.contextPath}/faces/home-admin-users.jsp?u=${userAdminBean.url(user.userName)}#users_form_target'><c:out value='${user.userName}'></c:out><br></a>
	</c:forEach>
</div>
<br>
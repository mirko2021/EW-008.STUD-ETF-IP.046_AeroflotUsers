<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean  id='userProfileBean' class='yatospace.common.user.web.bean.UserProfileBean' scope='session'></jsp:useBean>
<c:out value='${userProfileBean.initialize(pageContext.request)}'></c:out>

<p>ПОДЕШАВАЊА КОРИСНИЧКОГ ПРОФИЛА КАДА СУ У ПИТАЊУ РЕЗЕРВАЦИЈЕ</p>

<c:if test='${param["user_resrvation_mode"] ne null}'> 
	<c:out value='${userProfileBean.accept(pageContext.request)}'></c:out>
</c:if>

<fieldset>
	 <legend>Путнички саобраћај: </legend>
	 <input type="radio" id="transport_yes" name="transport" value="yes" ${userProfileBean.profile.transportModeEnabled?"checked":""}>
 		 <label for="transport_yes">Да</label><br>
        <input type="radio" id="transport_no" name="transport" value="no" ${userProfileBean.profile.transportModeEnabled?"":"checked"}>
        <label for="transport_no">Не</label><br> 
</fieldset>
<br>
<fieldset>
	 <legend>Теретни саобраћај :</legend>
	 <input type="radio" id="travel_yes" name="travel" value="yes" ${userProfileBean.profile.travelModeEnables?"checked":""}>
 		 <label for="travel_yes">Да</label><br>
        <input type="radio" id="travel_no" name="travel" value="no" ${userProfileBean.profile.travelModeEnables?"":"checked"}>
        <label for="travel_no">Не</label><br>
</fieldset> 
<br>
<script>
	function orderFormData(){
		if(document.getElementById('transport_yes').checked)
			document.getElementById('transport_data').value = 'true'; 
		else 
			document.getElementById('transport_data').value = 'false'; 
		
		
		if(document.getElementById('travel_yes').checked)
			document.getElementById('travel_data').value = 'true'; 
		else 
			document.getElementById('travel_data').value = 'false';
	}
</script>

<form name='tt_form' method='POST'>
	<input type='hidden' name='transport_data' id='transport_data' value=''/>
	<input type='hidden' name='travel_data' id='travel_data' value=''/>
	<input type='submit' name='user_resrvation_mode' value='Потврда' onclick='orderFormData()'/>
</form>

<br>
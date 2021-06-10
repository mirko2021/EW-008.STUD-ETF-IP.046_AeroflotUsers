<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
			
<br>
<b>ПОСТАВЉАЊЕ ПРОФИЛНЕ СЛИКЕ</b>
<br><br>
<div id="profile_image_form_operation_place">
	<form id="upload_target_profile_image" name="upload_target_profile_image" method="POST" accept-charset="UTF-8" enctype="multipart/form-data">
		<div style="width: 500px" align="center">
			<div style="display:none;" id="noneContentPI" align="center">
				<br><div align="center"><br>&nbsp; На ово мјесто треба превући жељену датотеку. &nbsp; </div>
			</div>
			<div id="dropContainerPI" style="border:1px solid black;height:100px; width:100px'; vertical-align: top; text-align: center">
				<br><div align="center"><br>&nbsp; На ово мјесто треба превући жељену датотеку. &nbsp; </div>
			</div>
			<input type="file" id="fileInputPI" name="fileInput" size="50" style="display:none;"><br>
			<script>
				document.getElementById('dropContainerPI').innerHTML=document.getElementById("noneContentPI").innerHTML;
				initDragAndDropFileChoose('dropContainerPI', 'fileInputPI');
			</script>
		</div>
		<div>
			<input type="hidden" value="${pageContext.request.contextPath}${pageContext.request.pathInfo}/home-admin.jsp" name="backward"/>
			<input type="submit" value="Постављање" formaction="${pageContext.request.contextPath}/ProfilePictureUploadServlet" formtarget="_self"/>
			<input type="button" value="брисање"    name='delete_profile_image'  form='profile_op_form' onclick='document.getElementById("profile_op").value="delete_profile_image";  document.getElementById("profile_op_form").submit()'/>
			<input type="button" value="ресетовање" name='reset_profile_message' form='profile_op_form' onclick='document.getElementById("profile_op").value="reset_profile_message"; document.getElementById("profile_op_form").submit()'/>
			<c:out value="${userImagesBean.getProfileImageMessage()}"></c:out>
		</div>
	</form>
	<form name='profile_op_form' id='profile_op_form' method='POST'> 
		<input type='hidden' name='profile_op' id='profile_op' value=''/>
	</form>
</div>
<br><br>
<b>ПОСТАВЉАЊЕ КОРИНСИЧКЕ СЛИКЕ</b>
<br><br>
<div id="user_image_form_operation_place">
	<form id="upload_target_profile_image" name="upload_target_profile_image" method="POST" accept-charset="UTF-8" enctype="multipart/form-data">
		<div style="width: 500px" align="center">
			<div style="display:none;" id="noneContentUI" align="center">
				<br><div align="center"><br>&nbsp; На ово мјесто треба превући жељену датотеку. &nbsp; </div>
			</div>
			<div id="dropContainerUI" style="border:1px solid black;height:100px; width:100px'; vertical-align: top; text-align: center">
				<br><div align="center"><br>&nbsp; На ово мјесто треба превући жељену датотеку. &nbsp; </div>
			</div>
			<input type="file" id="fileInputUI" name="fileInput" size="50" style="display:none;"><br>
			<script>
				document.getElementById('dropContainerUI').innerHTML=document.getElementById("noneContentUI").innerHTML;
				initDragAndDropFileChoose('dropContainerUI', 'fileInputUI');
			</script>
		</div>
		<div>
			<input type="hidden" value="${pageContext.request.contextPath}/home-admin.jsp" name="backward"/>
			<input type="submit" value="Постављање"  formaction="${pageContext.request.contextPath}/UserPictureUploadServlet" formtarget="_self"/>
			<input type="button" value="брисање"    name='delete_user_image'  form='user_op_form' onclick='document.getElementById("user_op").value="delete_user_image";  document.getElementById("user_op_form").submit()'/>
			<input type="button" value="ресетовање" name='reset_user_message' form='user_op_form' onclick='document.getElementById("user_op").value="reset_user_message"; document.getElementById("user_op_form").submit()'/>
			<c:out value="${userImagesBean.getUsersImageMessage()}"></c:out>
		</div>
	</form>
	<form name='user_op_form' id='user_op_form' method='POST'>
		<input type='hidden' name='user_op' id='user_op' value=''/>
	</form>
	<br>
</div>
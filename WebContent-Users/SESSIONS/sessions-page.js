"UTF-8"

/**
 * Функционалности које се односе на администрирање коринику са својим власитим сесијама.
 */
function sessions_page_next(app_path){
	var page_no = document.getElementById('sessions_list_page_form_page_no').value; 
	var page_size = document.getElementById('sessions_list_page_form_page_size').value; 
	var username_start_filter = document.getElementById("sessions_list_page_form_username_start_filter").value;
	var session_start_filter = document.getElementById("sessions_list_page_form_session_start_filter").value;
	back_function = function(response) {  
		if(response.response.success){
			ajax_load(app_path,'/WEB-INF/LISTS/page_sessions_list.jsp','sessions_list_page_place');
			ajax_load(app_path,'/WEB-INF/LISTS/content_sessions_list.jsp','sessions_list_content_place');
		}else{
			alert('Грешка у листању сесија.');
		} 
	}
	
	var request = form_ajax_request(); 
	request.request.beanName = "sessionsListBean";
	request.request.functionName = "next";
	request.request.parameters.page_no = page_no; 
	request.request.parameters.page_size = page_size; 
	request.request.parameters.user_start_filter = username_start_filter; 
	request.request.parameters.session_start_filter = session_start_filter; 
	
	ajax_exe(app_path, request.request.beanName, request.request.functionName, request.request.parameters, back_function); 
}

function sessions_page_previous(app_path){
	var page_no = document.getElementById('sessions_list_page_form_page_no').value; 
	var page_size = document.getElementById('sessions_list_page_form_page_size').value; 
	var username_start_filter = document.getElementById("sessions_list_page_form_username_start_filter").value;
	var session_start_filter = document.getElementById("sessions_list_page_form_session_start_filter").value;
	
	back_function = function(response) {  
		if(response.response.success){
			ajax_load(app_path,'/WEB-INF/LISTS/page_sessions_list.jsp','sessions_list_page_place');
			ajax_load(app_path,'/WEB-INF/LISTS/content_sessions_list.jsp','sessions_list_content_place');
		}else{
			alert('Грешка у листању сесија.');
		}
	}
	
	var request = form_ajax_request(); 
	request.request.beanName = "sessionsListBean";
	request.request.functionName = "previous";
	request.request.parameters.page_no = page_no; 
	request.request.parameters.page_size = page_size; 
	request.request.parameters.user_start_filter = username_start_filter; 
	request.request.parameters.session_start_filter = session_start_filter; 
	
	ajax_exe(app_path, request.request.beanName, request.request.functionName, request.request.parameters, back_function); 
}


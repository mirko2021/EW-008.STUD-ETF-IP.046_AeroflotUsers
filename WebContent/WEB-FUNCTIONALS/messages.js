"UTF-8"

/**
 *  Функционалности за потребе рада са порукама. 
 *  Динамичко учитавање порука и операциеј попут ресета. 
 */

function ajax_d_gui_status_content(app_path){
	ajax_load(app_path, "/WEB-INF/DESIGN-PARTS/basic-content.jsp" , "yi_1_design_specific_menu_content");
}

function ajax_d_m_gui_status_content(app_path){
	ajax_load(app_path, "/WEB-INF/DESIGN-PARTS/status-content.jsp" , "yi_1_design_specific_hedader_message");
}

function ajax_f_gui_message_reset(app_path){
	var back_function = function(response) {
		if(response.response.success){
			ajax_d_m_gui_status_content(app_path); 
		}
	}
	
	var request = form_ajax_request(); 
	request.request.beanName = "designMessageBean";
	request.request.functionName = "reset";
	request.request.parameters = {};

	ajax_exe(app_path, request.request.beanName, request.request.functionName, request.request.parameters, back_function);
}
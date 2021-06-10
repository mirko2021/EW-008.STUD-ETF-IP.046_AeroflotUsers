"UTF-8"

/**
 * Операбилност за брисање сесија. 
 */

function selected_session_set_to_json_form_param(){
	var dest = document.getElementById('logout_form_many_sessions'); 
	var selected = []; 
	var content_form = document.getElementById('sessions_list_content_place');
	var choosed_data_form = content_form.querySelectorAll('input[type="checkbox"]'); 
	for(var choosed_data_item of choosed_data_form)
		if(choosed_data_item.checked) selected[selected.length]=choosed_data_item.id.substring('select_'.length); 
	dest.value=JSON.stringify(selected);
}
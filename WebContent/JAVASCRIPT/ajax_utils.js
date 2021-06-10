"UTF-8"

/**
 * Алатке и класе објеката за баратање са АЈАКС-ЈСОН захтјевима. 
 */
function form_ajax_request(){ 
	return {request:new AjaxRequest(), type: AjaxType.generate()};
}

function form_ajax_response(){
	return {response: new AjaxResponse(), message: AjaxMessage.generate()}; 
}

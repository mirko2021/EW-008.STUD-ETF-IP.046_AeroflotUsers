"UTF-8"

/**
 * Освежавање података о одласцима, као и могућности страничења.
 */
 
function generateCurrentDate(){
	var today = new Date();
	var year = today.getFullYear();
	var mounth = today.getMonth()+1; 
	var day =  today.getDate(); 
	if((""+mounth).length==1) mounth = "0"+mounth; 
	if((""+day).length==1) day = "0"+day; 
	var date = year+'-'+mounth+'-'+day;
	return date; 
}

var incomePageRefresher = setInterval(outcome_page_refresh, 3000);
var default_app_path = ""; 
 
function outcome_page_refresh(){
	ajax_load(default_app_path, "/WEB-INF/outcome/outcome-preview-form.jsp", "outcome_page");
}
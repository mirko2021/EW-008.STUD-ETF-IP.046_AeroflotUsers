"UTF-8"

/**
 * Освежавање података о доласцима, као и могућности страничења.
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

var incomePageRefresher = setInterval(income_page_refresh, 3000);
var default_app_path = ""; 
 
function income_page_refresh(){
	ajax_load(default_app_path, "/WEB-INF/income/income-preview-form.jsp", "income_page");
}

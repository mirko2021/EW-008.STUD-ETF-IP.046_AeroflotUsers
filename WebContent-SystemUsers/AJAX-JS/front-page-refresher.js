"UTF-8"

/**
 * Одржавање података о доласцима, као и могућности страничења.
 */
 
var incomeRefresher = setInterval(income_refresh, 3000);
var outcomeRefresher = setInterval(outcome_refresh, 3000); 

var default_app_path = ""; 
 
function income_refresh(){
	ajax_load(default_app_path, "/WEB-INF/JSTL-PREVIEW/top-income-circular-counter.jsp", "top_income_place");
}

function outcome_refresh(){
	ajax_load(default_app_path, "/WEB-INF/JSTL-PREVIEW/top-outcome-circular-counter.jsp", "top_outcome_place");
}
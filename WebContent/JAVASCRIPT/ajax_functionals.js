"UTF-8"

/**
 *  Функционалности за рад са АЈАКС директним функционалностима.  
 */
function ajax_get(app_path, bean_name, parameters, back_function){
	 var xhttp = new XMLHttpRequest();  
	 xhttp.onreadystatechange = function(){
		if (xhttp.readyState == 4 && xhttp.status == 200) {
		   var response = JSON.parse(xhttp.response);
		   back_function(response);  
		}
	 }; 
	 var request = form_ajax_request(); 
	 request.type.type='GET';
	 request.request.beanName = bean_name;
	 request.request.functionName = "";
	 request.request.parameters = parameters;  
	 xhttp.open("POST", app_path+"/AjaxResolver", true);
	 xhttp.setRequestHeader("Content-type", "text/base64");  
	 xhttp.send(btoa(encodeURI(JSON.stringify(request))));
}

function ajax_set(app_path, bean_name, parameters, back_function){
		var xhttp = new XMLHttpRequest();  
		xhttp.onreadystatechange = function(){
			if (xhttp.readyState == 4 && xhttp.status == 200) {
    		    var response = JSON.parse(xhttp.response);
			    back_function(response); 
			}
		}; 
		var request = form_ajax_request(); 
		request.type.type='SET';
		request.request.beanName = bean_name;
		request.request.functionName = "";
		request.request.parameters = parameters;  
		xhttp.open("POST", app_path+"/AjaxResolver", true);
		xhttp.setRequestHeader("Content-type", "text/base64");  
		xhttp.send(btoa(encodeURI(JSON.stringify(request))));
}

function ajax_run(app_path, bean_name, function_name, parameters, back_function){
		var xhttp = new XMLHttpRequest();  
		xhttp.onreadystatechange = function(){
		   if (xhttp.readyState == 4 && xhttp.status == 200) {
		       var response = JSON.parse(xhttp.response);
		       back_function(response);
		   }
		}; 
		var request = form_ajax_request(); 
		request.type.type='RUN';
		request.request.beanName = bean_name;
		request.request.functionName = function_name;
		request.request.parameters = parameters;  
		xhttp.open("POST", app_path+"/AjaxResolver", true);
		xhttp.setRequestHeader("Content-type", "text/base64");
		xhttp.send(btoa(encodeURI(JSON.stringify(request))));
}

function ajax_exe(app_path, bean_name, function_name, parameters, back_function){
		var xhttp = new XMLHttpRequest();  
		xhttp.onreadystatechange = function(){
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
			   var response = JSON.parse(xhttp.response);
               back_function(response);
			}
		};
		var request = form_ajax_request(); 
		request.type.type='EXE';
		request.request.beanName = bean_name;
		request.request.functionName = function_name;
		request.request.parameters = parameters;  
		xhttp.open("POST", app_path+"/AjaxResolver", true);
		xhttp.setRequestHeader("Content-type", "text/base64");  
		xhttp.send(btoa(encodeURI(JSON.stringify(request))));
}

function ajax_load(app_path, resource_path_address, destination_id){
	  var xhttp = new XMLHttpRequest();  
	  xhttp.onreadystatechange = function(){
		  if (xhttp.readyState == 4 && xhttp.status == 200) {
			 document.getElementById(destination_id).innerHTML=xhttp.responseText; 
		  }
	  };
	  xhttp.open("POST", app_path+"/AjaxDownloader", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  
	  xhttp.send('path='+btoa(encodeURI(resource_path_address)));
}

function ajax_load_reactive(app_path, resource_path_address, destination_id, back_function){
	  var xhttp = new XMLHttpRequest();  
	  xhttp.onreadystatechange = function(){
		  if (xhttp.readyState == 4 && xhttp.status == 200) {
			 document.getElementById(destination_id).innerHTML=xhttp.responseText;
			 back_function();
		  }
	  };
	  xhttp.open("POST", app_path+"/AjaxDownloader", true);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send('path='+btoa(encodeURI(resource_path_address)));
}
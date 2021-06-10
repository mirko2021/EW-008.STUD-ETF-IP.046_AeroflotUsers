"UTF-8"

/**
 * Класа која се односи на типове захтјева. 
 */

const get_request = "GET"; 
const set_request = "SET"; 
const exe_request = "EXE"; 
const run_request = "RUN";
const non_request = "NON";

class AjaxType {
   constructor(type) {
	switch(type){
		case get_request: break;
		case set_request: break;
		case exe_request: break;
		case run_request: break;
		case non_request: break;
		default: type=non_request;
	}
    this.type = type;
  }

  static generate(){
	  return new AjaxType(non_request);
  }
}

AjaxType.prototype.toString = function() {
   return "[object AjaxType]"; 
};

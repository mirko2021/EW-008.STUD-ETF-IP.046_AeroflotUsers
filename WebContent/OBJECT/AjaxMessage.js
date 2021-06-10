"UTF-8"

/**
 * Класа која се односи на поруке које се тичу апликације. 
 */

const status_success = "SUCCESS"; 
const status_failure = "FAILURE"; 
const status_unknown = "UNKNOWN"; 

class AjaxMessage {
   constructor(messsage, status) {
    switch(status){
		case status_success: break;
		case status_failure: break;
		case status_unknown: break;
		default: status = status_unknown; break;
	}
	this.message = message;
    this.status = status;
  }

  static generate(){
	 return new AjaxMessage('',status_unknown); 
   }
}

AjaxMessage.prototype.toString = function() {
   return "[object AjaxMessage]"; 
};

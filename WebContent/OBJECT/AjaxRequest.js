"UTF-8"

/**
 *  Класа која се односи на објекте захтјева. 
 */

class AjaxRequest {
  constructor() {
	this.parameters = {};
	this.beanName = ""; 
	this.functionName = ""; 
  }
}

AjaxRequest.prototype.toString = function() {
   return "[object AjaxRequest]"; 
};

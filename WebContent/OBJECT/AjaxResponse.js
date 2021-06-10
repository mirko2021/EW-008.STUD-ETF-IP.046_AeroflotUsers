/**
 *  Класа која се односи на одговоре захтјева. 
 */

class AjaxResponse {
  constructor() {
	this.result = {};
  }
}

AjaxResponse.prototype.toString = function() {
   return "[object AjaxResponse]"; 
};

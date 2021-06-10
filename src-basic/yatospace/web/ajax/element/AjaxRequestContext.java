package yatospace.web.ajax.element;

import java.io.Serializable;

import com.google.gson.JsonObject;

/**
 * Контексти објеката за функционалности, 
 * постављање и очитавње АЈАКС објеката. 
 * @author MV
 * @version 1.0
 */
public class AjaxRequestContext implements Serializable{
	private static final long serialVersionUID = -2374729285040475235L;
	private JsonObject request  = new JsonObject(); 
	private JsonObject response = new JsonObject(); 
	private JsonObject messages = new JsonObject();
	private JsonObject type = new JsonObject();
	
	public JsonObject getRequest() {
		return request;
	}
	public JsonObject getResponse() {
		return response;
	}
	public JsonObject getMessages() {
		return messages;
	}
	public JsonObject getType() {
		return type;
	}
	
	public void setRequest(JsonObject request) {
		this.request = request;
	}
	public void setResponse(JsonObject response) {
		this.response = response;
	}
	public void setMessages(JsonObject messages) {
		this.messages = messages;
	}
	public void setType(JsonObject type) {
		this.type = type;
	} 
	
	public AjaxResponseContext getResponseContext() {
		AjaxResponseContext context = new AjaxResponseContext(); 
		context.setResponse(response);
		context.setMessages(messages);
		return context; 
	}
}

package yatospace.web.ajax.element;

import java.io.Serializable;

import com.google.gson.JsonObject;

/**
 * Контексти који се односе. 
 * @author MV
 * @version 1.0 
 */
public class AjaxResponseContext implements Serializable{
	private static final long serialVersionUID = 4056784352502262157L;
	 
	private JsonObject response = new JsonObject(); 
	private JsonObject messages = new JsonObject();
	
	public JsonObject getResponse() {
		return response;
	}
	public JsonObject getMessages() {
		return messages;
	}

	
	public void setResponse(JsonObject response) {
		this.response = response;
	}
	public void setMessages(JsonObject messages) {
		this.messages = messages;
	} 
}

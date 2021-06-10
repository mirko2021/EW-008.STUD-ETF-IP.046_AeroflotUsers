package yatospace.web.gui.bean;

import java.io.Serializable;

import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.model.AjaxExecutable;
import yatospace.web.gui.lang.MessageType;

/**
 * Зрно које се веже за поруке које би требале бити видљиве на корисничком интерфејсу. 
 * @author MV
 * @version 1.0
 */
public class MessageBean implements Serializable, AjaxExecutable{
	private static final long serialVersionUID = -1949700861846490823L;
	public static final String DEFAULT_MESSAGE = "Апликација је спремна за рад.";
	private MessageType type = MessageType.INFORMATION; 
	private String message = DEFAULT_MESSAGE;
	private Exception exception; 
	
	@Override
	public void importFrom(AjaxRequestContext request) {
		request.getResponse().addProperty("success", true);
	}

	@Override
	public void exportTo(AjaxRequestContext request) {
		request.getResponse().addProperty("success", true);
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		if(type==null) type=MessageType.INFORMATION; 
		this.type = type;
	}

	public MessageBean putType(MessageType type) {
		if(type==null) type=MessageType.INFORMATION; 
		this.type = type;
		return this;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(message==null) message = ""; 
		this.message = message;
	}

	public MessageBean putMessage(String message) {
		if(message==null) message = ""; 
		this.message = message;
		return this;
	}
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	public MessageBean putException(Exception exception) {
		this.exception = exception;
		return this;
	}
	
	public void avoidSyntaxLexicalStream() {}
	
	public MessageBean resetException() {
		exception = null; 
		return this; 
	}
	
	public MessageBean resetMessage() {
		message = DEFAULT_MESSAGE; 
		return this; 
	}
	
	public MessageBean resetType() {
		type = MessageType.INFORMATION;
		return this; 
	}
	
	public MessageBean reset() {
		return resetException().resetMessage().resetType(); 
	}
	
	public boolean isError() {
		return type == MessageType.ERROR; 
	}
	
	public boolean isSuccess() {
		return type == MessageType.SUCCESS; 
	}
	
	public boolean isWarnning() {
		return type == MessageType.WARNNING; 
	}
	
	public boolean isWait() {
		return type == MessageType.WAIT; 
	}
	
	public boolean isInformation() {
		return type == MessageType.INFORMATION; 
	}
	
	@AjaxAccessable("reset")
	public void reset(AjaxRequestContext context) {
		reset(); 
		context.getResponse().addProperty("success", true);
	}
	
	@AjaxAccessable("success")
	public void success(AjaxRequestContext context) {
		String message = context.getRequest().get("parameters").getAsJsonObject().get("message").getAsString(); 
		if(message==null) message = "";
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.SUCCESS;
		context.getResponse().addProperty("success", true);
	}
	
	public void success(String message) {
		if(message==null) message = ""; 
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.SUCCESS;
	}
	
	@AjaxAccessable("error")
	public void error(AjaxRequestContext context) {
		String message = context.getRequest().get("parameters").getAsJsonObject().get("message").getAsString(); 
		if(message==null) message = "";
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.ERROR;
		context.getResponse().addProperty("success", true);
	}
	
	public void error(String message) {
		if(message == null) message = ""; 
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.ERROR;
	}
	
	public void error(String message, Exception exception) {
		if(message==null) message = ""; 
		this.message = message; 
		this.exception = exception; 
		this.type = MessageType.ERROR;
	}
	
	@AjaxAccessable("warnning")
	public void warnning(AjaxRequestContext context) {
		String message = context.getRequest().get("parameters").getAsJsonObject().get("message").getAsString(); 
		if(message==null) message = "";
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.WARNNING;
		context.getResponse().addProperty("success", true);
	}
	
	public void warnning(String message) {
		if(message == null) message = ""; 
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.WARNNING;
	}
	
	@AjaxAccessable("wait")
	public void wait(AjaxRequestContext context) {
		String message = context.getRequest().get("parameters").getAsJsonObject().get("message").getAsString(); 
		if(message==null) message = "";
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.WAIT;
		context.getResponse().addProperty("success", true);
	}
	
	public void wait(String message) {
		if(message == null) message = ""; 
		this.message = message; 
		this.exception = null; 
		this.type = MessageType.WAIT;
	}
}

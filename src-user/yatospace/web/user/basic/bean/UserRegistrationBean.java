package yatospace.web.user.basic.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import yatospace.gui.program.shell.AppLogicCenter;
import yatospace.user.controller.UserCredentialsController;
import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.listener.AjaxRegisterListener;
import yatospace.web.ajax.model.AjaxExecutable;
import yatospace.web.user.basic.lang.BeanNamesConstants;

/**
 * Зрно које се користи за регистрацију.
 * @author MV
 * @version 1.0
 */
public class UserRegistrationBean implements AjaxExecutable, Serializable{
	private static final long serialVersionUID = -2783041531189132831L;
	private String username = ""; 
	private String password = "";
	private boolean registerSuccess; 
	private String registerMessage = ""; 
	
	public void register(HttpSession session) {
		AjaxRegisterListener.getAjaxRegister(session).put(BeanNamesConstants.USER_REGISTRATION_BEAN, this);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRegistrationBean resetUsername() {
		username = "";
		return this;
	}
	
	public UserRegistrationBean resetPassword() {
		password = ""; 
		return this; 
	}
	
	public UserRegistrationBean resetBean() {
		resetUsername(); 
		resetPassword();
		return this;
	}
	
	public UserRegistrationBean register() {
		UserCredentialsController credentialsTest = new UserCredentialsController();
		if(credentialsTest.setGoodUsername(username).trim().length()==0) {
			registerMessage = "Корисник није успјешно регистрован. Формат корисничког имена."; 
			registerSuccess = false;
		}
		else if(credentialsTest.setGoodPassword(password).trim().length()==0) {
			registerMessage = "Корисник није успјешно регистрован. Лоша лозинка."; 
			registerSuccess = false;
		}
		else if(AppLogicCenter.appLogicCenter.getController().getDataSource().getController(username)!=null) {
			registerMessage = "Корисник није успјешно регистрован. Корисник постоји."; 
			registerSuccess = false;
		}
		else if(!AppLogicCenter.appLogicCenter.getController().register(username, password)) {
			registerMessage = "Корисник није успјешно регистрован."; 
			registerSuccess = false;
		}
		else {
			registerMessage = "Корисник је успјешно регистрован."; 
			registerSuccess = true;
		}
		resetPassword();
		return this;
	}
	
	public boolean register(String username, String password) {
		try {
			UserCredentialsController credentialsTest = new UserCredentialsController();
			if(credentialsTest.setGoodUsername(username).trim().length()==0) {
				registerMessage = "Корисник није успјешно регистрован. Формат корисничког имена."; 
				return false; 
			}
			else if(credentialsTest.setGoodPassword(password).trim().length()==0) {
				registerMessage = "Корисник није успјешно регистрован. Лоша лозинка."; 
				return false;
			}
			else if(AppLogicCenter.appLogicCenter.getController().getDataSource().getController(username)!=null) {
				registerMessage = "Корисник није успјешно регистрован. Корисник постоји."; 
				return false;
			}
			else if(!AppLogicCenter.appLogicCenter.getController().register(username, password)) {
				registerMessage = "Корисник није успјешно регистрован."; 
				return false;
			}
			else {
				registerMessage = "Корисник је успјешно регистрован."; 
				return true;
			}
		}finally {
			resetPassword(); 
		}
	}

	public boolean isRegisterSuccess() {
		return registerSuccess;
	}
	

	public String getRegisterMessage() {
		return registerMessage;
	}
	
	public void setRegisterMessage(String registerMessage) {
		this.registerMessage = registerMessage;
	}
	
	public UserRegistrationBean cleanRegisterSuccess() {
		registerSuccess = false;
		return this;
	}
	
	public UserRegistrationBean cleanRegisterMessage() {
		registerMessage = "";
		return this;
	}
	
	public void clean() {
		cleanRegisterSuccess(); 
		cleanRegisterMessage();
	}
	
	public void avoidSyntaxStream() {}
	
	@Override
	public void importFrom(AjaxRequestContext request) {
		if(request==null) throw new RuntimeException();
	}
	
	@Override
	public void exportTo(AjaxRequestContext request) {
		if(request==null) throw new RuntimeException();
	}
	
	@AjaxAccessable("register")
	public void register(AjaxRequestContext context) { 
		username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		password = context.getRequest().get("parameters").getAsJsonObject().get("password").getAsString();
		System.out.println(username);
		System.out.println(password); 
		UserCredentialsController credentialsTest = new UserCredentialsController(); 
		if(credentialsTest.setGoodUsername(username).trim().length()==0) {
			System.out.println("A"); 
			registerMessage = "Корисник није успјешно регистрован. Формат корисничког имена."; 
			context.getResponse().getAsJsonObject().addProperty("success", false);
			context.getMessages().getAsJsonObject().addProperty("message", registerMessage);
		}
		else if(credentialsTest.setGoodPassword(password).trim().length()==0) {
			System.out.println("B"); 
			registerMessage = "Корисник није успјешно регистрован. Лоша лозинка."; 
			context.getResponse().getAsJsonObject().addProperty("success", false);
			context.getMessages().getAsJsonObject().addProperty("message", registerMessage);
		}
		else if(AppLogicCenter.appLogicCenter.getController().getDataSource().getController(username)!=null) {
			System.out.println("C"); 
			registerMessage = "Корисник није успјешно регистрован. Корисник постоји."; 
			context.getResponse().getAsJsonObject().addProperty("success", false);
			context.getMessages().getAsJsonObject().addProperty("message", registerMessage);
		}
		else if(!AppLogicCenter.appLogicCenter.getController().register(username, password)) {
			System.out.println("D"); 
			registerMessage = "Корисник није успјешно регистрован."; 
			context.getResponse().getAsJsonObject().addProperty("success", false);
			context.getMessages().getAsJsonObject().addProperty("message", registerMessage);
		}
		else {
			System.out.println("E"); 
			registerMessage = "Корисник је успјешно регистрован."; 
			context.getResponse().getAsJsonObject().addProperty("success", true);
			context.getMessages().getAsJsonObject().addProperty("message", registerMessage);
		}
		resetPassword();
	}
}

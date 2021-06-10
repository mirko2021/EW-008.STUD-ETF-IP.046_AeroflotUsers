package yatospace.web.gui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import yatospace.session.bean.BaseBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.web.user.basic.bean.UserRegistrationBean;
import yatospace.web.user.basic.util.BeansGeneratorUtil;

/**
 * Зрно за извршење операција. 
 * @author MV
 * @version 1.0
 */
public class OperationalBean implements Serializable{
	private static final long serialVersionUID = 6239851554365466591L;
	
	public String loggedUser(HttpSession session) {
		BaseBean sessionBean = SessionBeansGenerator.baseBean(session); 
		return sessionBean.getLoginBean().getUsername(); 
	}
	
	public boolean isLoggedUser(HttpSession session) {
		String loggedUser = loggedUser(session); 
		return loggedUser!=null && loggedUser.trim().length()!=0; 
	}
	
	public void login(HttpServletRequest request) {
		if(isLoggedUser(request.getSession())) return; 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sessionId = request.getSession().getId();
		if(username==null) username = "";
		if(password==null) password = "";
		BaseBean sessionBean = SessionBeansGenerator.baseBean(request.getSession());
		sessionBean.getLoginBean().login(username, password, sessionId);
	}
	
	public void logout(HttpServletRequest request) {
		if(!isLoggedUser(request.getSession())) return; 
		BaseBean sessionBean = SessionBeansGenerator.baseBean(request.getSession());
		sessionBean.getLoginBean().logout();
	}
	
	public boolean register(HttpServletRequest request) {
		UserRegistrationBean bean = BeansGeneratorUtil.gengetUserRegistrationBean(request.getSession());
		if(isLoggedUser(request.getSession())) {bean.setRegisterMessage("Регистрација није одоброна, кад је корисник пријављен."); return false;} 
		String username = request.getParameter("register_form_username");
		String password = request.getParameter("register_form_password");
		if(username==null) username = "";
		if(password==null) password = "";
		return bean.register(username, password);
	}
	
	public void logoutUser(HttpServletRequest request) {
		if(!isLoggedUser(request.getSession())) return; 
		BaseBean sessionBean = SessionBeansGenerator.baseBean(request.getSession());
		sessionBean.getLoginBean().logoutAll();
	}
	
	public void logoutChoosed(HttpServletRequest request) { 
		if(!isLoggedUser(request.getSession())) return; 
		try {
			BaseBean sessionBean = SessionBeansGenerator.baseBean(request.getSession());
			String logoutSessions = request.getParameter("logout_form_many_sessions"); 
			JsonParser parser = new JsonParser(); 
			JsonArray sessions = parser.parse(logoutSessions).getAsJsonArray();
			List<String> sessionArray = new ArrayList<>();
			for(int i=0; i<sessions.size(); i++)
				sessionArray.add(sessions.get(i).getAsString());
			sessionBean.getLoginBean().logout(sessionArray);
		}catch(Exception ex) {
			return; 
		}
	}
}

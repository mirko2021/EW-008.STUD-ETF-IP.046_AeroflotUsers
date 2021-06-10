package yatospace.session.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import yatospace.session.generator.SessionBeansGenerator;

/**
 * Основно зрно за приступ из ЈСП, задржавајући апликационе поставке 
 * специфичних зрна. 
 * @author MV
 * @version 1.0
 */
public class BaseBean implements Serializable{
	private static final long serialVersionUID = -7763254478588068518L;
	private SessionListBean sessionListBean; 
	private LoginBean loginBean; 
	private boolean initialized; 

	public BaseBean initialize(HttpSession session) {
		if(initialized) return this;
		loginBean = SessionBeansGenerator.loginBean(session); 
		sessionListBean = SessionBeansGenerator.sessionListBean(session); 
		initialized = true;
		return this;
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public SessionListBean getSessionListBean() {
		return sessionListBean;
	}

	public void avoidSyntaxLexicalStream() {} 
}

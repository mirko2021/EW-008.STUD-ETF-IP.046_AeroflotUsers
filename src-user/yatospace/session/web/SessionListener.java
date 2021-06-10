package yatospace.session.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Аутоматизација одјаве сесија на апликационом новоу. 
 * @author MV
 * @version 1.0 
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    public SessionListener() {}

    public void sessionCreated(HttpSessionEvent se)  {}
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	try{
    		LoginBean login = SessionBeansGenerator.loginBean(se.getSession()); 
    		login.logout();
    	}catch(Exception ex) {
    		return; 
    	}
    }
	
}

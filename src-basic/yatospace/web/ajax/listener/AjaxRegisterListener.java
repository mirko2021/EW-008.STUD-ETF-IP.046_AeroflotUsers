package yatospace.web.ajax.listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import yatospace.web.ajax.control.AjaxElementRegister;
import yatospace.web.ajax.execute.AjaxResolver;
import yatospace.web.ajax.security.AjaxAccess;
import yatospace.web.ajax.security.impl.AdminGeneralAccess;


/**
 * Мапирање и стварање регистара зрна, пара ајакс база у зависности од сесије. 
 * @author MV
 * @version 1.0 
 */
@WebListener
public class AjaxRegisterListener implements HttpSessionListener {
    private static final HashMap<HttpSession, AjaxElementRegister> ajaxRegisters = new HashMap<>(); 	
    private static final HashMap<HttpSession, AjaxResolver>        ajaxResolvers = new HashMap<>(); 
    private static final HashMap<HttpSession, AjaxAccess>          ajaxAccesses  = new HashMap<>(); 
    
    public AjaxRegisterListener() {}

    public void sessionCreated(HttpSessionEvent se)  { 
    	ajaxRegisters.put(se.getSession(), new AjaxElementRegister()); 
    	ajaxResolvers.put(se.getSession(), new AjaxResolver()); 
    	ajaxAccesses.put(se.getSession(), new AdminGeneralAccess()); 
    } 
    
    public void sessionDestroyed(HttpSessionEvent se)  { 
        ajaxRegisters.remove(se.getSession()); 
        ajaxResolvers.remove(se.getSession()); 
        ajaxAccesses.remove(se.getSession()); 
    }

	public static AjaxElementRegister getAjaxRegister(HttpSession session) {
		return ajaxRegisters.get(session);
	}

	public static AjaxResolver getAjaxResolver(HttpSession session) {
		return ajaxResolvers.get(session);
	}
	
	public static AjaxAccess getAjaxAccess(HttpSession session) {
		return ajaxAccesses.get(session);
	}
}

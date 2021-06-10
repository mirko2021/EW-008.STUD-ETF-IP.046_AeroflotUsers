package yatospace.gui.session.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import yatospace.gui.session.logic.AppControlCenter;

/**
 * Реакције које су јединствене за паљење и гашење сервера. 
 * @author MV
 * @version 1.0 
 */
@WebListener
public class ServerListener implements ServletContextListener {

    public ServerListener() {}
    public void contextInitialized(ServletContextEvent sce)  {}
    public void contextDestroyed(ServletContextEvent sce)  {
    	try { AppControlCenter.mainCenter.getController().getRegisterEngine().getSessionGC().close(); }
    	catch(Exception ex) { ex.printStackTrace(); }
    }
}

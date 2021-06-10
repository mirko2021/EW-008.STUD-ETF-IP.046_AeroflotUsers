package yatospace.gui.session.logic;

import yatospace.gui.session.util.PageParameters;
import yatospace.gui.session.util.SessionParameters;
import yatospace.user.session.application.GeneralUserSessionCenter;
import yatospace.user.session.application.GeneralUserSessionControlPoint;
import yatospace.user.session.controller.MongoRegisteredSessionController;

/**
 * @version 1.0
 * @author MV
 * Контролни центар кад су у питању апликације.
 */
public class AppControlCenter {
	public final static AppControlCenter mainCenter = new AppControlCenter();
	
	private GeneralUserSessionControlPoint engine = GeneralUserSessionCenter.userSessionEngine;
	private MongoRegisteredSessionController controller = engine.getSessionController();
	private SessionParameters sessionBean = new SessionParameters(); 
	private PageParameters userPageBean = new PageParameters(); 
	private PageParameters sessionPageBean =  new PageParameters();
	
	public GeneralUserSessionControlPoint getEngine() {
		return engine;
	}
	public MongoRegisteredSessionController getController() {
		return controller;
	}
	public SessionParameters getSessionBean() {
		return sessionBean;
	}
	public PageParameters getUserPageBean() {
		return userPageBean;
	}
	public PageParameters getSessionPageBean() {
		return sessionPageBean;
	}
}

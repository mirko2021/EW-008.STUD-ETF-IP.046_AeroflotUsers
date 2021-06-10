package yatospace.gui.program.shell;

import yatospace.user.database.controller.MySQLRegistrationController;
import yatospace.user.registration.application.GeneralUserRegistrationCenter;
import yatospace.user.registration.application.GeneralUserRegistrationControlPoint;
import yatospace.user.util.Page;
import yatospace.user.util.Passport;

/**
 * Центар за апликациону контролу при реги�?тровању кори�?ника. 
 * @author MV
 * @version 1.0
 */
public final class AppLogicCenter {
	public static final AppLogicCenter appLogicCenter = new AppLogicCenter();  
	public AppLogicCenter() {}
	
	private GeneralUserRegistrationControlPoint engine = GeneralUserRegistrationCenter.registrationEngine; 
	private MySQLRegistrationController controller = GeneralUserRegistrationCenter.registrationEngine.getController();
	private Passport passport = new Passport(); 
	private Page page = new Page(); 
	
	public GeneralUserRegistrationControlPoint getEngine() {
		return engine;
	}
	public MySQLRegistrationController getController() { 
		return controller; 
	}
	public Passport getPassport() {
		return passport;
	}
	public Page getPage() {
		return page;
	}
}

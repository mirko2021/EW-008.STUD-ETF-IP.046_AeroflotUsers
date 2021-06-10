package yatospace.common.user.web.bean;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import programiranje.ip.admin.listener.DatabaseListener;
import yatospace.common.user.controller.UserProfileController;
import yatospace.common.user.model.UserProfileMode;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Зрно са подацима о профилима који су дозвољени за корисника, 
 * односно за које је корисник заинтересован. Путнички и 
 * транспортни авиосаобаћај.  
 * @author VM
 * @version 1.0
 */
public class UserProfileBean implements Serializable{
	private static final long serialVersionUID = -6061995394615017846L;
	
	private UserProfileMode profile = new UserProfileMode();
	private String message = ""; 
	private boolean visited = false; 
	
	private transient LoginBean login;
	private transient UserProfileController controller; 

	public UserProfileMode getProfile() {
		return profile;
	}

	public void setProfile(UserProfileMode profile) {
		if(profile==null) profile = new UserProfileMode();  
		this.profile = profile;
		profile.setUsername(login.getUsername());
	}
	
	public void initialize(HttpServletRequest request) {
		if(controller==null) {
			controller = new UserProfileController(DatabaseListener.getUserProfile(request.getSession()));
		}
		if(login==null) {
			login = SessionBeansGenerator.loginBean(request.getSession());
			login.addPostLogin(getClass().getName()+"#initialize^set", ()->{});
			login.addPostLogout(getClass().getName()+"#initialize^reset", ()->{resetAll(); visited=false;});
			login.addPostLogoutAll(getClass().getName()+"#initialize^reset", ()->{resetAll(); visited=false;});
			resetData();
		}
		profile.setUsername(login.getUsername());
		visited = true; 
		load();
	}

	public void load() {
		try {
			String username = login.getUsername(); 
			UserProfileMode profile = controller.getDao().getByCombination(username).getUserProfileMode();
			if(profile!=null) this.profile = profile; 
		}catch(Exception ex) {
			return;
		}finally {
			profile.setUsername(login.getUsername());
		}
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(message==null) 
		this.message = message;
	}

	public LoginBean getLogin() {
		return login;
	}
	
	public void resetMessage() {
		message = "";
	}
	
	public void resetData() {
		profile = new UserProfileMode(); 
		profile.setUsername(login.getUsername());
	}
	
	public void resetAll() {
		resetMessage(); 
		resetData(); 
	}

	public boolean isVisited() {
		return visited;
	}
	
	public void acceptData(HttpServletRequest request) {
	    try {
	    	String transport = request.getParameter("transport_data");
	    	String travel = request.getParameter("travel_data");
	    	boolean transportB = Boolean.parseBoolean(transport); 
	    	boolean travelB = Boolean.parseBoolean(travel);
	    	profile.setTransportModeEnabled(transportB);
	    	profile.setTravelModeEnables(travelB);
	    	profile.setUsername(login.getUsername());
	    }catch(Exception ex) {
	    	return; 
	    }
	}
	
	public void accept(HttpServletRequest request) {
		acceptData(request);
		recordData();
	}
	
	public void recordData() {
		profile.setUsername(login.getUsername());
		controller.put(profile);
	}
}

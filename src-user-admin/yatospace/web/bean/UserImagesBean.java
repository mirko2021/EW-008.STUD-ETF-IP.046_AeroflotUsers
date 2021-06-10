package yatospace.web.bean;

import java.io.File;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Зрно за корисничке поруке.
 * @author VM
 * @version 1.0
 */
public class UserImagesBean implements Serializable{
	private static final long serialVersionUID = -5186402786511740233L;
	public final String LOCATION_DIR_CONFIGURATION_PROFILE = "C:\\Eclipse-Workspaces\\EW-008\\005_UserSpace\\DataImages\\profile"; 
	public final String LOCATION_DIR_CONFIGURATION_USER    = "C:\\Eclipse-Workspaces\\EW-008\\005_UserSpace\\DataImages\\user";
	
	public UserImagesBean() {
		File profileDir = new File(LOCATION_DIR_CONFIGURATION_PROFILE);
		File userDir    = new File(LOCATION_DIR_CONFIGURATION_USER);
		if(!profileDir.exists()) profileDir.mkdirs();
		if(!userDir.exists())    userDir.mkdirs(); 
	}
	
	private String profileImageMessage = "";
	private String usersImageMessage = "";
	
	public String getProfileImageMessage() {
		return profileImageMessage;
	}
	public void setProfileImageMessage(String profileImageMessage) {
		if(profileImageMessage == null) profileImageMessage = "";
		this.profileImageMessage = profileImageMessage;
	}
	public String getUsersImageMessage() {
		return usersImageMessage;
	}
	public void setUsersImageMessage(String usersImageMessage) {
		if(profileImageMessage == null) usersImageMessage = "";
		this.usersImageMessage = usersImageMessage;
	}
	
	public String consumeProfileImageMessage() {
		try {return profileImageMessage;}
		finally {profileImageMessage="";}
	}
	public String consumeUserImageMessage() {
		try {return usersImageMessage;}
		finally {usersImageMessage="";}
	}

	public void resetProfileImageMessage() {
		profileImageMessage = "";
	}
	
	public void resetUserImageMessage() {
		usersImageMessage = "";
	}
	
	public File getProfileImageLocation(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername();
			if(username==null) return null;
			if(username.trim().length()==0) return null;
			File file = new File(LOCATION_DIR_CONFIGURATION_PROFILE);
			for(File f: file.listFiles()) 
				if(f.getName().startsWith(username)) return f;
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public File getUserImageLocation(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername(); 
			File file = new File(LOCATION_DIR_CONFIGURATION_USER);
			if(username==null) return null;
			if(username.trim().length()==0) return null;
			for(File f: file.listFiles()) 
				if(f.getName().startsWith(username)) return f;
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void removeUserImage(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername(); 
			File file = new File(LOCATION_DIR_CONFIGURATION_USER);
			for(File f: file.listFiles()) 
				if(f.getName().startsWith(username)) {f.delete(); return;}
			usersImageMessage = "Брисање корисничке слике (аватара) је успјешно."; 
			return;
		}catch(Exception ex) {
			return;
		}
	}
	
	public void removeProfileImage(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername();
			File file = new File(LOCATION_DIR_CONFIGURATION_PROFILE);
			for(File f: file.listFiles()) 
				if(f.getName().startsWith(username)) {f.delete(); break;}
			profileImageMessage = "Брисање профилне слике (аватара) је успјешно."; 
			return;
		}catch(Exception ex) {
			return;
		}
	}
}

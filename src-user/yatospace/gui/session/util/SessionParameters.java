package yatospace.gui.session.util;

import java.io.Serializable;

/**
 * Параметри сесија. 
 * @author MV
 * @version 1.0 
 */
public class SessionParameters implements Serializable{
	private static final long serialVersionUID = 1798173722814477649L;
	private String sessionId = ""; 
	private String username = ""; 
	private String password = "";
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		if(sessionId==null) sessionId = "";  
		this.sessionId = sessionId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username == null) username = ""; 
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password==null) password = ""; 
		this.password = password;
	} 
}

package yatospace.common.user.model;

import java.io.Serializable;

/**
 * Информације о корисничком моду. 
 * @author VM
 * @version 1.0
 */
public class UserProfileMode implements Serializable, Cloneable, Comparable<UserProfileMode>{
	private static final long serialVersionUID = -1944486237228037730L;
	private boolean transportModeEnabled = true; 
	private boolean travelModeEnables = true; 
	private String username = ""; 
	private String modeId = "";
	
	public boolean isTransportModeEnabled() {
		return transportModeEnabled;
	}
	public void setTransportModeEnabled(boolean transportModeEnabled) {
		this.transportModeEnabled = transportModeEnabled;
	}
	public boolean isTravelModeEnables() {
		return travelModeEnables;
	}
	public void setTravelModeEnables(boolean travelModeEnables) {
		this.travelModeEnables = travelModeEnables;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username==null) throw new RuntimeException();
		this.username = username;
	}
	public String getModeId() {
		return modeId;
	}
	public void setModeId(String modeId) {
		this.modeId = modeId;
	}
	
	@Override
	public String toString() {
		return modeId;
	}
	
	@Override
	public int hashCode() {
		return modeId.hashCode(); 
	}
	
	@Override
	public UserProfileMode clone() {
		UserProfileMode userProfile = new UserProfileMode(); 
		userProfile.modeId               = userProfile.modeId;
		userProfile.transportModeEnabled = userProfile.transportModeEnabled; 
		userProfile.travelModeEnables    = userProfile.travelModeEnables; 
		userProfile.username             = userProfile.username; 
		return userProfile; 
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof UserProfileMode) {
			UserProfileMode mode = (UserProfileMode) object;
			return modeId.contentEquals(mode.modeId); 
		}
		return false; 
	}
	
	@Override
	public int compareTo(UserProfileMode o) {
		return modeId.compareTo(o.modeId);
	}
}

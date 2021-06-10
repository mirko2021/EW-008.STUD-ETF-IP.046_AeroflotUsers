package yatospace.common.user.object;

import java.io.Serializable;

import yatospace.common.user.model.UserProfileMode;

/**
 * Преносни подаци за корисничке профиле. 
 * @author VM
 * @version 1.0
 */
public class UserProfileDTO implements Serializable{
	private static final long serialVersionUID = -861178763160970722L;
	
	private UserProfileMode userProfileMode = new UserProfileMode();

	public UserProfileMode getUserProfileMode() {
		return userProfileMode;
	}

	public void setUserProfileMode(UserProfileMode userProfileMode) {
		this.userProfileMode = userProfileMode;
	}
}

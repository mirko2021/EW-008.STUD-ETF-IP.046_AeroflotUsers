package yatospace.common.user.object;

import java.io.Serializable;

import yatospace.common.user.model.UserLocation;

/**
 * Транспортни објекат за податке локације корисника.
 * @author VM
 * @version 1.0
 */
public class UserLocationDTO implements Serializable{
	private static final long serialVersionUID = 8420834823536763085L;
	private UserLocation location;

	public UserLocation getLocation() {
		return location;
	}

	public void setLocation(UserLocation location) {
		this.location = location;
	}
}

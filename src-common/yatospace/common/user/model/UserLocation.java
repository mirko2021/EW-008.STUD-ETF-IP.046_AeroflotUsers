package yatospace.common.user.model;

import java.io.Serializable;

/**
 * Општи објекат који се веже за локацију корисника.
 * @author VM
 * @version 1.0
 */
public class UserLocation implements Serializable, Cloneable, Comparable<UserLocation>{
	private static final long serialVersionUID = -156388627160118985L;
	private String address = "";
	private String city = ""; 
	private String country = "";
	private String locationId = "";
	private String username = "";
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(address==null) this.address = address; 
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if(city==null) this.city = city; 
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		if(country==null) this.country = country; 
		this.country = country;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		if(locationId == null) locationId="";  
		this.locationId = locationId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username == null) username=""; 
		this.username = username;
	}
	
	@Override
	public String toString() {
		return locationId;
	}
	
	@Override 
	public int hashCode() {
		return locationId.hashCode();
	}
	
	@Override 
	public UserLocation clone() {
		UserLocation userLocation = new UserLocation();
		userLocation.address = address;
		userLocation.city = city; 
		userLocation.country = country; 
		userLocation.username = username;
		return userLocation; 
	}
	
	@Override 
	public boolean equals(Object o) {
		if(o instanceof UserLocation) {
			UserLocation location = (UserLocation) o;
			return locationId.contentEquals(location.locationId); 
		}
		return false; 
	}
	
	@Override
	public int compareTo(UserLocation o) {
		return locationId.compareTo(o.locationId);
	}
}

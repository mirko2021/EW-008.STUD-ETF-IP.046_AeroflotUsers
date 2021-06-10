package programiranje.ip.admin.model;

import java.io.Serializable;

/**
 * Локација. 
 * @author VM
 * @version 1.0
 */
public class Location implements Serializable, Cloneable, Comparable<Location>{
	private static final long serialVersionUID = 8504699731712735L;
	
	private String locationId      = "";
	private String locationName    = "";
	private String locationAddress = ""; 
	private String locationNotes   = ""; 
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		if(locationId == null) locationId = "";
		this.locationId = locationId;
	}
	
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		if(locationName == null) locationName = "";
		this.locationName = locationName;
	}
	
	public String getLocationNotes() {
		return locationNotes;
	}
	public void setLocationNotes(String locationNotes) {
		if(locationNotes==null) locationNotes = ""; 
		this.locationNotes = locationNotes;
	}
		
	@Override 
	public String toString() {
		return this.locationName;
	}
	
	@Override 
	public int hashCode() {
		return this.locationName.hashCode(); 
	}
	
	@Override
	public Location clone() {
		Location location = new Location(); 
		location.setLocationAddress(locationAddress);
		location.setLocationId(locationId);
		location.setLocationName(locationName);
		location.setLocationNotes(locationNotes);
		return location; 
	}
	
	public void apply(Location location) {
		if(location==null) { reset(); return;} 
		locationAddress = location.locationAddress;
		locationName    = location.locationName; 
		locationId      = location.locationId; 
		locationNotes   = location.locationNotes;
	}
	
	public void reset() {
		locationAddress = "";
		locationName = ""; 
		locationId = ""; 
		locationNotes = "";
	}
	
	@Override 
	public boolean equals(Object object) {
		if(object instanceof Location) {
			Location location = (Location) object;
			if(!locationId.contentEquals(location.locationId)) return false; 
			if(!locationName.contentEquals(location.locationName)) return false;
			return true; 
		}
		return false;
	}
	
	@Override
	public int compareTo(Location o) {
		return locationName.compareTo(o.locationName); 
	}
}

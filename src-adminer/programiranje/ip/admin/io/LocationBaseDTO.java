package programiranje.ip.admin.io;

import java.io.Serializable;

import programiranje.ip.admin.model.Location;

/**
 * База локација.
 * @version 1.0
 * @author VM
 */
public class LocationBaseDTO implements Serializable{
	private static final long serialVersionUID = 1522404811255029220L;
	
	private String locationId = "";
	private String locationName = ""; 
	private String locationAddress = ""; 
	private String locationNote = "";
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		if(locationName == null) locationName = ""; 
		this.locationName = locationName;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		if(locationAddress == null) locationAddress = ""; 
		this.locationAddress = locationAddress;
	}
	public String getLocationNote() {
		return locationNote;
	}
	public void setLocationNote(String locationNote) {
		if(locationNote == null) locationNote = "";
		this.locationNote = locationNote;
	}
	
	public Location toLocaton() {
		Location location = new Location();
		location.setLocationAddress(locationAddress);
		location.setLocationId(locationId);
		location.setLocationName(locationName);
		location.setLocationNotes(locationNote);
		return location; 
	}
	
	public void toLocation(Location location) {
		if(location==null) return;
		location.setLocationAddress(locationAddress);
		location.setLocationId(locationId);
		location.setLocationName(locationName);
		location.setLocationNotes(locationNote);
	}
	
	public void fromLocation(Location location) {
		if(location==null) location = new Location();
		locationAddress = location.getLocationAddress(); 
		locationId = location.getLocationId(); 
		locationName = location.getLocationName(); 
		locationNote = location.getLocationNotes(); 
	}
}

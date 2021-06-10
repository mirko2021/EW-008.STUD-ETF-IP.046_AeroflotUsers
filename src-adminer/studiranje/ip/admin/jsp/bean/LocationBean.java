package studiranje.ip.admin.jsp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import programiranje.ip.admin.controller.LocationInfoController;
import programiranje.ip.admin.listener.DatabaseListener;
import programiranje.ip.admin.model.Location;


/**
 * Објекат података о локацијама.
 * @author VM
 * @version 1.0
 */
public class LocationBean implements Serializable{
	private static final long serialVersionUID = 9042477347912957250L;
	private String location = ""; 
		
	public String getLocation() {
		return location;
	}

	public String getLocationValue(HttpSession session) {
		if(location.contentEquals("0")) return ""; 
		try {
			List<Location> locations = list(session);
			int x = Integer.parseInt(location)-1;
			if(locations==null) return "";
			if(x<0) return "";
			if(x>=locations.size()) return "";
			return locations.get(x).getLocationName(); 
		}catch(Exception ex) {
			return "";
		}
		
	}
	
	public void setLocation(String location) {
		if(location==null) location=""; 
		this.location = location;
	}
	
	public List<Location> list(HttpSession session){
		try {
			LocationInfoController controller = DatabaseListener.getLocationController(session);
			ArrayList<Location> result = new ArrayList<>(controller.list());
			Collections.sort(result);
			return result;
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
}

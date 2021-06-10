package studiranje.ip.admin.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import programiranje.ip.admin.controller.LocationInfoController;
import programiranje.ip.admin.listener.DatabaseListener;
import programiranje.ip.admin.model.Location;
import yatospace.flag.web.bean.CountriesFormBean;
import yatospace.flag.web.database.LocationCountryDAO;
import yatospace.flag.web.listener.CountryDataEngineListener;

/**
 * Објекат података о локацијама.
 * @author VM
 * @version 1.0
 */

@ManagedBean(name = "locationBean", eager = true)
@SessionScoped
public class LocationBean implements Serializable{
	private static final long serialVersionUID = 9042477347912957250L;
	
	private String locationName    = ""; 
	private String locationAddress = ""; 
	private Location selectedLocation = null; 
	private HttpSession session = null; 
	
	private transient LocationCountryDAO countryDAO; 
	private transient CountriesFormBean countriesFormBean; 
	
	public CountriesFormBean getCountriesFormBean() {
		return countriesFormBean;
	}

	public void setCountriesFormBean(CountriesFormBean countriesFormBean) {
		this.countriesFormBean = countriesFormBean;
	}

	public LocationCountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void initialize(HttpSession session) {
		try {
			countryDAO = CountryDataEngineListener.getLocationCountryDAOMap(session); 
			countriesFormBean = (CountriesFormBean) session.getAttribute("countriesFormBean"); 
		}catch(Exception ex) {
			throw ex;
		}
	}

	public void setSession(HttpSession session) {
		if(session==null) return; 
		this.session = session; 
	}
	
	public void resetSession(HttpSession session) {
		this.session = null;
	}
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		if(locationName==null) locationName = "";
		this.locationName = locationName;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		if(locationAddress==null) locationAddress = "";
		this.locationAddress = locationAddress;
	}

	public Location getLocation() {
		Location location = new Location();
		location.setLocationName(locationName);
		location.setLocationAddress(locationAddress);
		location.setLocationId(locationName);
		return location;
	}
	
	public void get(HttpSession session, String locationName) {
		LocationInfoController controller = DatabaseListener.getLocationController(session);
		controller.get(locationName); 
	}
	
	public void add() {
		LocationInfoController controller = DatabaseListener.getLocationController(session);
		Location location = getLocation(); 
		if(location.getLocationName().trim().length()==0) return;
		location.setLocationName(locationName);
		location.setLocationAddress(locationAddress);
		location.setLocationId(locationName);
		controller.put(location);
	}
	
	public void remove() {
		LocationInfoController controller = DatabaseListener.getLocationController(session);
		controller.remove(locationName);
		resetData();
		reset();
	}
	
	public void update() {
		LocationInfoController controller = DatabaseListener.getLocationController(session);
		if(selectedLocation==null) return;
		Location location = getLocation();
		location.setLocationName(locationName);
		location.setLocationAddress(locationAddress);
		location.setLocationId(locationName);
		location.setLocationNotes("");
		controller.update(selectedLocation.getLocationName(), location); 
	}

	public Location getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(Location selectedLocation) {
		this.selectedLocation = selectedLocation;
	}
	
	public void select() {
		selectedLocation = getLocation(); 
		try {
			if(countriesFormBean != null) {
				String a2c = countriesFormBean.getCountryDAO().get(selectedLocation.getLocationName());
				countriesFormBean.setSelectedA2C(a2c);
			}
		}catch(Exception ex) {
			if(countriesFormBean != null)
				countriesFormBean.refresh();
		}
	}
	
	public void reset() {
		try {
			if(countriesFormBean != null)
				countriesFormBean.refresh();
			selectedLocation = null;
			resetData();
		}catch(Exception ex) {
			selectedLocation = null;
		}
	}
	
	public boolean isSelected() {
		return selectedLocation == null;
	}
	
	public void resetBean() {
		selectedLocation = null; 
		locationName    = ""; 
		locationAddress = "";
		session = null;
	}
	
	public void resetData() {
		selectedLocation = null; 
		locationName    = ""; 
		locationAddress = "";
	}
	
	
	public List<Location> list(){
		LocationInfoController controller = DatabaseListener.getLocationController(session);
		ArrayList<Location> result = new ArrayList<>(controller.list());
		Collections.sort(result);
		return result;
	}
}

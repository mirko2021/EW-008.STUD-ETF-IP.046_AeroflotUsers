package yatospace.common.user.web.bean;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import programiranje.ip.admin.listener.DatabaseListener;
import yatospace.common.user.controller.UserLocationController;
import yatospace.common.user.model.UserLocation;
import yatospace.common.user.object.UserLocationDTO;
import yatospace.flag.web.bean.CountriesFormBean;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Зрно које се веже за адресу, локацију, 
 * корисника. 
 * @author VM
 * @version 1.0
 */
public class LocationBean implements Serializable{
	private static final long serialVersionUID = 7364327332792879505L;
	private String note = ""; 
	private String username = ""; 
	private String address = ""; 
	private String country = ""; 
	private String location = "";
	private String message = "";
	private boolean visited = false; 
	
	private transient UserLocationController controller; 
	private transient LoginBean login; 
	
	public void initialize(HttpServletRequest request, CountriesFormBean cfb) {		
		if(controller==null) {
			var userDAO = DatabaseListener.getIpUser(request.getSession()); 
			var locationDAO = DatabaseListener.getUserLocation(request.getSession()); 
			controller = new UserLocationController(locationDAO, userDAO);
		}
		if(login==null) {
			login = SessionBeansGenerator.loginBean(request.getSession());
			login.addPostLogin(getClass().getName()+"#initialize^set", ()->{});
			login.addPostLogout(getClass().getName()+"#initialize^reset", ()->{reset(); cfb.setSelectedA2C(""); visited=false;});
			login.addPostLogoutAll(getClass().getName()+"#initialize^reset", ()->{reset(); cfb.setSelectedA2C(""); visited=false;});
		}
		
		username = login.getUsername();
		
		if(!visited) {
			reset();
			cfb.reset(request);
			UserLocationDTO userDTO = controller.getDao().getByUsername(username);
					
			if(userDTO!=null) {
				UserLocation location = userDTO.getLocation(); 
				address = location.getCity();
				note = location.getAddress();
				country = location.getCountry();
				cfb.setSelectedA2C(country);
			}
			message = ""; 
		}
		
		visited = true;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		if(this.note==null) this.note = ""; 
		this.note = note;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(this.username == null) username = "";
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(this.address == null) address = ""; 
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		if(this.country==null) this.country = "";
		this.country = country;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		if(this.location==null) this.location = ""; 
		this.location = location;
	} 
	
	public String escapeAddress() {
		return address.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'","&apos;");
	}
	
	public void accept(HttpServletRequest request) {
		String cityLocation = request.getParameter("uc_loc_city");
		String addressNotes = request.getParameter("uc_loc_address");
		if(cityLocation==null) cityLocation = ""; 
		if(addressNotes==null) addressNotes = ""; 
		this.note = addressNotes;
		this.address = cityLocation;
	}
	
	public void accept(CountriesFormBean cfb) {
		if(cfb==null) country = "";
		if(cfb==null) return; 
		country = cfb.getSelectedA2C(); 
		if(country==null) country = ""; 
	}
	
	public void reset() {
		this.address = ""; 
		this.location = ""; 
		this.country =""; 
		this.note = "";
	}
	
	public void apply(CountriesFormBean cfb) {
		if(cfb==null) return; 
		cfb.setSelectedA2C(country);
	}
	
	public UserLocation objLocation() {
		UserLocation userLocation = new UserLocation();
		userLocation.setAddress(note);
		userLocation.setCity(address);
		userLocation.setCountry(country);
		userLocation.setUsername(username);
		userLocation.setLocationId(username);
		return userLocation; 
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(message==null) message = ""; 
		this.message = message;
	}

	public void resetMessage() {
		message = ""; 
	}
	
	public void dataUpdate() {
		controller.set(objLocation());
		message = "Пријем података о корисничкој локацији је извршен."; 
	}
	
	public void dataReset() {
		controller.reset(username);
		reset();
		message = "Поништавање података о корисничкој локацији је извршено.";
	}
}

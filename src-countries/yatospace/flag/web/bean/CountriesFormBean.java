package yatospace.flag.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import studiranje.ip.admin.jsf.bean.LocationBean;
import yatospace.flag.io.CountriesController;
import yatospace.flag.model.Country;
import yatospace.flag.web.database.LocationCountryDAO;
import yatospace.flag.web.listener.CountryDataEngineListener;

/**
 * Зрно које се односи на баратање са формом за избор државе. 
 * @author VM
 * @version 1.0
 */
public class CountriesFormBean implements Serializable{
	private static final long serialVersionUID = 5068672900234358542L;
	private transient CountriesController controller;
	private transient LocationCountryDAO countryDAO;
	private LocationBean formBean;
	
	public LocationCountryDAO getCountryDAO() {
		return countryDAO;
	}

	public LocationBean getCountryFormBean() {
		return formBean; 
	}
	
	public void initialize(HttpServletRequest request) {
		if(controller==null)
			controller = CountryDataEngineListener.getCountriesController(request.getSession()); 
		LocationBean location = (LocationBean) request.getSession().getAttribute("locationBean");
		location.initialize(request.getSession());
		countryDAO = location.getCountryDAO(); 
		formBean = location; 
		formBean.setCountriesFormBean(this);
	}

	private String selectedA2C = "";
	
	public String getSelectedA2C() {
		return selectedA2C;
	}

	public void setSelectedA2C(String selectedA2C) {
		if(selectedA2C==null) selectedA2C = ""; 
		if(selectedA2C.length()>2) selectedA2C = selectedA2C.substring(0, 2);  
		this.selectedA2C = selectedA2C;
	}

	public CountriesController getController() {
		return controller;
	}
	
	public List<Country> get(){
		try {
			List<Country> countries = controller.countries();
			Collections.sort(countries);
			return countries; 
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public Country get(String a2c) {
		return controller.data(a2c);
	}
	
	public String previewNameSelcted() {
		Country c = get(selectedA2C);
		if(c==null) return "";
		return previewName(c);
	}
	
	public String previewName(Country c) {
		return c.toString().replaceAll("\\(", " (");
	}
	
	public void set(HttpServletRequest request) {
		String a2c = request.getParameter("flag_io_country");
		String location = formBean.getLocationName();
		if(a2c==null) return;
		if(a2c.length()<2) return;
		setSelectedA2C(a2c.substring(0, 2));
		Country c = get(a2c.substring(0, 2));
		c.getAlpha2Code();
		countryDAO.link(location, a2c);
	}
	
	public void reset(HttpServletRequest request) {
		selectedA2C = "";
		countryDAO.unlink(formBean.getLocationName());
	}
	
	public void refresh() {
		selectedA2C = "";
	}
}

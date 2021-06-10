package yatospace.flag.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import yatospace.flag.io.CountriesExporter;
import yatospace.flag.io.CountriesService;
import yatospace.flag.model.Country;

/**
 * Зрно које служи за баратање заставама и подацима о државама.
 * @author VM
 * @version 1.0
 */
public class CountriesBean implements Serializable{
	private static final long serialVersionUID = -8066344425648126305L;
	private transient CountriesService controller = new CountriesService();
	private transient CountriesExporter exporter = new CountriesExporter();
	
	public CountriesService getController() {
		return controller;
	}

	public CountriesExporter getExporter() {
		return exporter;
	}


	public List<Country> get(){
		List<Country> countries = controller.load();
		Collections.sort(countries);
		return countries; 
	}
	
	public Country get(String a2c) {
		return controller.get(a2c);
	}
	
	public void save(String a2c) {
		Country c = get(a2c);
		if(c==null) return; 
		exporter.store(c);
	}
}

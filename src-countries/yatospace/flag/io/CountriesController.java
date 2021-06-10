package yatospace.flag.io;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yatospace.flag.model.Country;

/**
 * Општи контролер за баратање са основним подацима о заставама и државама. 
 * @author VM
 * @author 1.0
 */
public class CountriesController {
	private CountriesImporter importer = new CountriesImporter();
	private CountriesExporter exporter = new CountriesExporter(); 
	private CountriesService  service  = new CountriesService();
	private List<Country> countriesCache = new ArrayList<>(); 
	
	public boolean isCountriesDataDownloadedToServer() {
		return testDownloading();
	}

	public boolean testDownloading() {
		return importer.countA2C()==51;
	}
	
	public CountriesImporter getImporter() {
		return importer;
	}
	public CountriesExporter getExporter() {
		return exporter;
	}
	public CountriesService getService() {
		return service;
	}
	
	public List<Country> countries(){
		boolean test = testDownloading();
		if(test) {
			List<Country> countries = new ArrayList<>();
			for(String a2c: importer.listA2C()) 
				countries.add(importer.dataFor(a2c));
			Collections.sort(countries);
			return countries;
		}else {
			if(countriesCache.size()==0)
			countriesCache = service.load(); 
			for(Country c: countriesCache) {
				if(importer.dataFor(c.getAlpha2Code())==null) {
					exporter.store(c);
					break;
				}
			}
			return countriesCache;
		}
	}
	
	public Country data(String a2c) {
		for(Country c: countries()) 
			if(c.getAlpha2Code().contentEquals(a2c))
				return c;
		return null; 
	}
	
	public byte[] image(String a2c) {
		try {
			Country c = importer.dataFor(a2c);
			if(c!=null) {
				byte[] image =  importer.imageFor(a2c);
				if(image!=null) return image; 
			}
			URL imageURL = new URL(importer.dataFor(a2c).getFlagHref());
			try(InputStream is = imageURL.openStream()){
				byte[] image = new byte[is.available()]; 
				is.read(image); 
				return image; 
			}
		}catch(Exception ex) {
			return null; 
		}
	}
}

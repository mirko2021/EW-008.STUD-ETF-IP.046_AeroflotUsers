package yatospace.flag.io;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import yatospace.flag.model.Country;

/**
 * Контролер за конзумацију застава и података о државама у ЕУ.
 * @author VM
 * @version 1.0
 */
public class CountriesService {
	public static final String SERVICE_URL = "https://restcountries.eu/rest/v2/region/europe";
	
	public Country get(String a2c) {
		for(Country country: load()) {
			if(country.getAlpha2Code().contentEquals(a2c))
				return country;
		}
		return null;
	}
	
	public List<Country> load(){
		try {
			ArrayList<Country> result = new ArrayList<>();
			URL url = new URL(SERVICE_URL);
			String json = ""; 
			try(Scanner scanner = new Scanner(url.openStream())){
				while(scanner.hasNextLine())
					json+=scanner.nextLine()+"\n"; 
			}
			json = json.trim();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(json).getAsJsonArray();
			for(int i=0; i<array.size(); i++) {
				try {
					Country c = new Country();
					c.setAlpha2Code(array.get(i).getAsJsonObject().get("alpha2Code").getAsString());
					c.setAlpha3Code(array.get(i).getAsJsonObject().get("alpha3Code").getAsString());
					c.setName(array.get(i).getAsJsonObject().get("name").getAsString());
					c.setFlagHref(array.get(i).getAsJsonObject().get("flag").getAsString());
					if(c.getAlpha2Code().contentEquals("XK")) continue;
					if(c.getAlpha2Code().contentEquals("IM")) continue;
					result.add(c);
				}catch(Exception ex) {
					continue; 
				}
			}
			return result;
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
}

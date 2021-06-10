package yatospace.session.counter.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;

import yatospace.session.counter.listener.LoginCounterApplicationListener;
import yatospace.session.counter.storage.LoginCounterRepository;

/**
 * Зрно за дистрибуцију и употребу 
 * бројача логовања. 
 * @author VM
 * @version 1.0
 */
public class LoginCounterBean implements Serializable{
	private static final long serialVersionUID = -4005051961158376604L;
	private transient LoginCounterRepository loginCounterRepository = LoginCounterApplicationListener.lcr; 
	private Map<Date, Integer> lastHour = new HashMap<>();
	
	public Map<Date, Integer> getLastHour() {
		return new HashMap<>(lastHour);
	}

	public LoginCounterBean() {
		 refreshLastHour();
	}
	
	public void refreshLastHour() {
		lastHour = loginCounterRepository.lastHourUser(); 
	}
	
	public LoginCounterRepository getLoginCounterRepository() {
		return loginCounterRepository;
	}

	public String getTimeJsonHeader() {
		JsonArray result = new JsonArray(); 
		ArrayList<Date> keys   = new ArrayList<>(lastHour.keySet());
		Collections.sort(keys);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for(Date key: keys) 
			result.add(sdf.format(key));
		return result.toString();
	}
	
	public String getFrecvenceJsonHeader() {
		JsonArray result = new JsonArray(); 
		ArrayList<Date> keys   = new ArrayList<>(lastHour.keySet());
		Collections.sort(keys);
		for(Date key: keys) 
			result.add(lastHour.get(key));
		return result.toString();
	}
	
	
}

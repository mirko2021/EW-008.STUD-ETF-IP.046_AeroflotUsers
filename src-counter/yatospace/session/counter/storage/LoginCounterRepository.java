package yatospace.session.counter.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import yatospace.session.counter.model.LoginCounterEntity;

/**
 * Репозиторијум који се односи на баратање и чување када
 * су бројачи пријава у питању као и префункционалност 
 * брисања биљешки са истеклим датумом и роком трајања. 
 * @author VM
 * @version 1.0
 */
public class LoginCounterRepository implements Serializable{
	private static final long serialVersionUID = -7427930054367969942L;
	
	private List<LoginCounterEntity> storage = new ArrayList<>();
	private int expiration = 10*60; 
	
	public synchronized List<LoginCounterEntity> storage(){
		for(LoginCounterEntity entity: new ArrayList<>(storage)) {
			if(entity.expired()) storage.remove(entity); 
		}
		Collections.sort(storage);
		return new ArrayList<>(storage);
	}
	
	public List<LoginCounterEntity> forUser(String username){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage())
			if(entity.getUsername().contentEquals(username))
				result.add(entity);
		return result; 
	}
	
	public List<LoginCounterEntity> before(Date date){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage())
			if(entity.getDate().before(date) || entity.getDate().equals(date))
				result.add(entity);
		return result; 
	}
	
	public List<LoginCounterEntity> forUserBefore(String username, Date date){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage())
			if(entity.getUsername().contentEquals(username))
				if(entity.getDate().before(date) || entity.getDate().equals(date))
					result.add(entity);
		return result; 
	}
	
	public List<LoginCounterEntity> after(Date date){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage())
			if(entity.getDate().after(date) || entity.getDate().equals(date))
				result.add(entity);
		return result; 
	}
	
	public List<LoginCounterEntity> forUserAfter(String username, Date date){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage())
			if(entity.getUsername().contentEquals(username))
				if(entity.getDate().after(date) || entity.getDate().equals(date))
					result.add(entity);
		return result; 
	}
	
	public List<LoginCounterEntity> into(Date start, Date end){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage())
			if(entity.getDate().equals(start) || entity.getDate().equals(end))
				result.add(entity);
			else if(entity.getDate().after(start) && entity.getDate().before(end))
				result.add(entity);
		return result; 
	}
	
	public List<LoginCounterEntity> userInto(Date start, Date end){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage()) {
			if(entity.getUsertype().contains("user"))
				if(entity.getDate().equals(start) || entity.getDate().equals(end))
					result.add(entity);
				else if(entity.getDate().after(start) && entity.getDate().before(end))
					result.add(entity);
		}
		return result; 
	}
	
	
	public List<LoginCounterEntity> forUserInto(String username, Date start, Date end){
		ArrayList<LoginCounterEntity> result = new ArrayList<>();
		for(LoginCounterEntity entity: storage())
			if(entity.getUsername().contentEquals(username))
				if(entity.getDate().equals(start) || entity.getDate().equals(end))
					result.add(entity);
				else if(entity.getDate().after(start) && entity.getDate().before(end))
					result.add(entity);
		return result; 
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		if(expiration<-1) expiration = -1; 
		this.expiration = expiration;
	}
	
	public void insert(LoginCounterEntity entity) {
		if(entity==null) return; 
		storage.add(entity); 
	}
	
	public void insert(Date date, String username, String usertype, int expiration) {
		if(date==null) date = new Date();
		if(username==null) username = ""; 
		if(usertype==null) usertype = ""; 
		if(expiration<-1) expiration = -1; 
		LoginCounterEntity entity = new LoginCounterEntity();
		entity.setDate(date);
		entity.setUsername(username);
		entity.setUsertype(usertype);
		entity.setExpiration(expiration);
		insert(entity);
	}
	
	public void insert(Date date, String username, String usertype) {
		if(date==null) date = new Date();
		if(username==null) username = ""; 
		if(usertype==null) usertype = ""; 
		LoginCounterEntity entity = new LoginCounterEntity();
		entity.setDate(date);
		entity.setUsername(username);
		entity.setUsertype(usertype);
		entity.setExpiration(expiration);
		insert(entity);
	}
	
	public void insert(String username, String usertype) {
		if(username==null) username = ""; 
		if(usertype==null) usertype = "";
		LoginCounterEntity entity = new LoginCounterEntity();
		entity.setDate(new Date());
		entity.setUsername(username);
		entity.setUsertype(usertype);
		entity.setExpiration(expiration);
		insert(entity);
	}
	
	public HashMap<Date, Integer> lastHour(){
		HashMap<Date, Integer> result = new HashMap<>();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		for(int i=0; i<5; i++) {
			Date current = calendar.getTime(); 
			calendar.add(Calendar.MINUTE, -2);
			Date start = calendar.getTime(); 
			result.put(current, into(start, current).size());
		}
		return result; 
	}
	
	public HashMap<Date, Integer> lastHourUser(){
		HashMap<Date, Integer> result = new HashMap<>();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		for(int i=0; i<5; i++) {
			Date current = calendar.getTime(); 
			calendar.add(Calendar.MINUTE, -2);
			Date start = calendar.getTime(); 
			result.put(current, userInto(start, current).size());
		}
		return result; 
	}
}

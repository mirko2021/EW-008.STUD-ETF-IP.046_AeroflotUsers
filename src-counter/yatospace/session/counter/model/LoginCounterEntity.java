package yatospace.session.counter.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Општи оквир за бројач пријава. 
 * @author VM
 * @version 1.0
 */
public class LoginCounterEntity implements Serializable, Cloneable, Comparable<LoginCounterEntity>{
	private static final long serialVersionUID = -2291458998685068985L;
	private Date date = new Date(); 
	private String username = "";
	private String usertype = ""; 
	private long expiration = 10*60;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		if(usertype==null) usertype = "";
		this.usertype = usertype;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		if(expiration<-1) expiration = -1;
		this.expiration = expiration;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		if(date==null) date = new Date();
		this.date = date;
	}
	
	@Override 
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		return username + " "+ sdf.format(date); 
	}
	
	@Override 
	public int hashCode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return (sdf.format(date)+" "+username).intern().hashCode(); 
	}
	
	@Override 
	public LoginCounterEntity clone() {
		LoginCounterEntity entity = new LoginCounterEntity();
		entity.date = date; 
		entity.expiration = expiration; 
		entity.username = username; 
		entity.usertype = usertype;
		return entity;
	}
	
	@Override 
	public boolean equals(Object object) {
		if(object instanceof LoginCounterEntity) {
			LoginCounterEntity entity = (LoginCounterEntity) object; 
			if(!entity.getDate().equals(date)) return false; 
			if(!entity.getUsername().equals(username)) return false; 
			return true; 
		}
		return false; 
	}
	
	public String idString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return (sdf.format(date)+" "+username).intern();
	}
	
	@Override
	public int compareTo(LoginCounterEntity o) {
		return idString().compareTo(o.idString());
	}
	
	public boolean expired() {
		if(expiration<0) return false; 
		return date.getTime()+expiration*1000<new Date().getTime(); 
	}
}

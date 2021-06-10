package programiranje.ip.admin.model;

import java.io.Serializable;

/**
 * Класа репрезентација администратора. 
 * @author VM
 * @version 1.0
 */
public class Administrator implements Serializable, Cloneable, Comparable<Administrator>{
	private static final long serialVersionUID = 1991255617684037557L;
	private String userName = ""; 
	private String password = "";
	private String firstName = "";
	private String secondName = ""; 
	private String userNotes = "";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if(userName == null) userName = ""; 
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password == null) password = ""; 
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName == null) firstName = ""; 
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		if(secondName == null) secondName = ""; 
		this.secondName = secondName;
	}

	public String getUserNotes() {
		return userNotes;
	}

	public void setUserNotes(String userNotes) {
		if(userNotes == null) userNotes = ""; 
		this.userNotes = userNotes;
	}
	
	@Override
	public String toString() {
		return userName; 
	}
	
	@Override 
	public int hashCode() {
		return userName.hashCode();
	}
	
	@Override
	public int compareTo(Administrator o) {
		return userName.compareTo(o.userName);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Administrator) {
			Administrator worker = (Administrator) o; 
			return worker.userName.contentEquals(worker.userName); 
		}
		return false;
	}
	
	@Override
	public Administrator clone() {
		Administrator worker = new Administrator();
		worker.firstName  = firstName; 
		worker.password   = password;
		worker.secondName = secondName; 
		worker.userNotes  = userNotes; 
		return worker; 
	}
	
	public void apply(Administrator worker) {
		if(worker==null) return; 
		firstName  = worker.firstName; 
		password   = worker.password; 
		secondName = worker.secondName; 
		userNotes  = worker.userNotes;
		userName   = worker.userName; 
	}
	
	public void reset() {
		firstName = ""; 
		password = ""; 
		secondName = ""; 
		userNotes  = ""; 
		userName = ""; 
	}
	
	public void resetPassword() {
		password = "";
	}
}

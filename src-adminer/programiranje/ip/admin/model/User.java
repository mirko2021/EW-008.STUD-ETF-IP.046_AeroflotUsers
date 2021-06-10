package programiranje.ip.admin.model;

import java.io.Serializable;

/**
 * Класа репрезентација корисника. 
 * @author VM
 * @version 1.0
 */
public class User implements Serializable, Cloneable, Comparable<User>{
	private static final long serialVersionUID = 839250223555635789L;
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
	public int compareTo(User o) {
		return userName.compareTo(o.userName);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof User) {
			User user = (User) o; 
			return userName.contentEquals(user.userName); 
		}
		return false;
	}
	
	@Override
	public User clone() {
		User user = new User();
		user.firstName  = firstName; 
		user.password   = password;
		user.secondName = secondName; 
		user.userNotes  = userNotes; 
		return user; 
	}
	
	public void apply(User user) {
		if(user==null) return; 
		firstName  = user.firstName; 
		password   = user.password; 
		secondName = user.secondName; 
		userNotes  = user.userNotes;
		userName   = user.userName; 
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

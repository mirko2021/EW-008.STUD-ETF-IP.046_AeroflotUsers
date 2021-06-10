package yatospace.user.role.dto;

import java.io.Serializable;

/**
 * Подаци о корисницима. 
 * @author VM
 * @version 1.0
 */
public class UserDataDto implements Serializable{
	private static final long serialVersionUID = 7428399068311232619L;
	
	private String username = "";
	private String firstname = ""; 
	private String secondname = ""; 
	private String usernote = "";
	
	public String getUsername() {
		return username;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getSecondname() {
		return secondname;
	}
	public String getUsernote() {
		return usernote;
	}
	
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}
	public void setFirstname(String firstname) {
		if(firstname==null) firstname = ""; 
		this.firstname = firstname;
	}
	public void setSecondname(String secondname) {
		if(secondname==null) secondname = "";
		this.secondname = secondname;
	}
	public void setUsernote(String usernote) {
		if(usernote==null) usernote = ""; 
		this.usernote = usernote;
	}
}
